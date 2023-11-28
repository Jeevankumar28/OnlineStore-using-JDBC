package com.training.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.training.connect.Dataconnect;
import com.training.pojo.Admin;
import com.training.pojo.User;
public class AdminValidation {
	private Connection con;
	private PreparedStatement pstmt;
	private Admin a;
	private Scanner scan;
	ProductDao pdao;
	String choice ="y";
	User u;
	private Map<Integer,User> us;

	public AdminValidation() {
		con = Dataconnect.getConnect();
		a = new Admin();
		scan = new Scanner(System.in);
		pdao = new ProductDao();
		u = new User();
		us = new HashMap<>();

	}
	public void adminValid() throws SQLException {
		
		System.out.println("Enter admin name");
		String name = scan.next();
		System.out.println("Enter password");
		String pin = scan.next();
		pstmt = con.prepareStatement("select * from User where username=? and password=?");
		pstmt.setString(1, name);
		pstmt.setString(2, pin);
		ResultSet res = pstmt.executeQuery();
		if(res.next()) {
			System.out.println("Admin login Successful");
			System.out.println();
			while(choice.equals("y")) {
				System.out.println("1. List of Products in the shop");
				System.out.println("2. Search the product by using product ID");
				System.out.println("3. Product list by category");
				System.out.println("4. product details based on product Name");
				System.out.println("5. Total amount spend on product");
				System.out.println("6. Profit amount that gets on category wise");
				System.out.println("7. Add New Products");
				
				System.out.println("Enter your choice");
				int ch = scan.nextInt();
				switch(ch) {
				case 1:
					pdao.display();
					break;
				case 2:
					pdao.searchByProductId();
					break;
				case 3:
					pdao.searchByCategory();
					break;
				case 4:
					pdao.searchByName();
					break;
				case 5:
					pdao.totalAmount();
					break;
				case 6:
					pdao.profitAmount();
					break;
				case 7:
					pdao.insertProduct();
					break;
				}
				System.out.println("Do you want to continue(y/n)");
				choice = scan.next();
				
				}
				
			}
		else {
			System.out.println("Invalid login details! Please try again");
		}
		
			
	}
	
	
	public void valid() throws SQLException {
		System.out.println("enter user name");
		String name = scan.next();
		System.out.println("Enter password");
		String pin = scan.next();
		Set<Map.Entry<Integer,User>> user = us.entrySet();
		for(Map.Entry<Integer,User> u:user)
		{
			if(u.getValue().getUsername().equals(name) && u.getValue().getPassword().equals(pin)) {
				System.out.println("Welcome "+u.getValue().getUsername());
				System.out.println("Login Successful");
				System.out.println("Available products in the shop");
				pdao.display();
				while(choice.equals("y")) {
					System.out.println("1. Filter product based on category");
					System.out.println("2. Filter product based on price");
					System.out.println("Enter your choice");
					int ch = scan.nextInt();
					switch(ch) {
					case 1:
						pdao.searchByCategory();
						break;
					case 2:
						pdao.filter();
						break;
					}
					System.out.println("Do you want to continue(y/n)");
					choice = scan.next();
				}
			}
			else {
				System.out.println("Invalid details!Please try again");
			}
		}
		
	}
	public void register() {
		System.out.println("enter user name");
		u.setUsername(scan.next());
		System.out.println("Enter user mailId");
		u.setEmailid(scan.next());
		System.out.println("Enter user password");
		u.setPassword(scan.next());
		System.out.println("You got 100 superCoins");
		u.setSupercoins(100);
		us.put(1, u);
		u.setMap(us);
	}
}
