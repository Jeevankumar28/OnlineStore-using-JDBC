package com.training.DAO;

import com.training.connect.Dataconnect;
import com.training.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class UserValidation {
	private Connection con;
	private PreparedStatement pstmt;
	private User u;
	private Scanner scan;
	private ProductDao pdao;
	private String choice = "y";
	private Map<Integer,User> us;
	private double totalPrice=0;
	int final_discount;

	public UserValidation() {
		con = Dataconnect.getConnect();
		u = new User();
		scan = new Scanner(System.in);
		us = new HashMap<>();
		pdao = new ProductDao();
	}
	public void valid() throws SQLException {
		System.out.println("enter user name");
		String name = scan.next();
		System.out.println("Enter password");
		String pin = scan.next();
		pstmt = con.prepareStatement("select * from User where username = ? and password = ?");
		pstmt.setString(1, name);
		pstmt.setString(2, pin);
		ResultSet res = pstmt.executeQuery();
		if(res.next()) {
			System.out.println("Welcome "+res.getString(1));
			System.out.println("Login Successful");
			System.out.println("Available products in the shop : ");
			pdao.display();
			while(choice.equals("y")) {
				System.out.println("1. Filter product based on Category");
				System.out.println("2. Filter product based on Price");
				System.out.println("Enter your choice");
				int ch = scan.nextInt();
				switch(ch) {
				case 1:
					System.out.println("Enter Category");
					String c = scan.next();
					pstmt = con.prepareStatement("select *  from Product where category = ?");
					pstmt.setString(1, c);
					ResultSet res1 = pstmt.executeQuery();
					while(res1.next()) {
						System.out.println("product name : "+res1.getString(2));
						System.out.println("Available Quantity : "+res1.getInt(4));
						System.out.println("product category : "+res1.getString(5));
						System.out.println("product price : "+res1.getDouble(3));
						System.out.println();
					}
						System.out.println("Enter product name to Purchase");
						String pname = scan.next();
						System.out.println("enter how many products you want to purchase");
						int noofprod = scan.nextInt();
							pstmt = con.prepareStatement("update Product set availableQuantity=availableQuantity-? where productname=?");
							pstmt.setInt(1, noofprod);
							pstmt.setString(2, pname);
							int res11 = pstmt.executeUpdate();
							PreparedStatement pstmt1 = con.prepareStatement("select * from Product where productname=?");
							pstmt1.setString(1, pname);
							ResultSet res12 = pstmt1.executeQuery();
							res12.next();
							PreparedStatement pstmt3 = con.prepareStatement("insert into purchase values(?,?,?,?)");
							pstmt3.setString(1, name);
							pstmt3.setString(2, pname);
							pstmt3.setInt(3,noofprod );
							pstmt3.setDouble(4, res12.getDouble(3)*noofprod);
							int res13 = pstmt3.executeUpdate();
							if(res13>0) {
								System.out.println("product add to cart");
								totalPrice+=res12.getDouble(3)*noofprod;
								System.out.println("Do you want to use superCoins(100 coins = 5rs) press 'y' to use 'n' not to use");
								String choice = scan.next();
								if(choice.equals("y")) {
									if(res.getInt(4)>0) {
										int discount=res.getInt(4)/100;
										final_discount=discount*5;
										PreparedStatement pstmt = con.prepareStatement("update User set superCoins=superCoins-? where username=?");
										pstmt.setInt(1, discount*100);
										pstmt.setString(2, name);
										int result = pstmt.executeUpdate();
										if(result>0) {
											System.out.println("after using superCoins. available superCoins : "+(res.getInt(4)-discount*100));
										}
									}
									
								}
								else {
									System.out.println("Ok");
								}
							}
							
					break;
				case 2:
					Statement stmt = con.createStatement();
					ResultSet res2 = stmt.executeQuery("select * from Product order by sellingprice");
					while(res2.next()) {
						System.out.println("product id : "+res2.getInt(1));
						System.out.println("Product name : "+res2.getString(2));
						System.out.println("Product Category : "+res2.getString(5));
						System.out.println("Available Quanity : "+res2.getInt(4));
						System.out.println("Product price : "+res2.getDouble(3));
						System.out.println();
					}
					
					System.out.println("Enter product name to purchase");
					String pname1 = scan.next();
					System.out.println("enter how many products you want to purchase");
					int noofprod1 = scan.nextInt();
						pstmt = con.prepareStatement("update Product set availableQuantity=availableQuantity-? where productname=?");
						pstmt.setInt(1, noofprod1);
						pstmt.setString(2, pname1);
						int res111 = pstmt.executeUpdate();
						PreparedStatement pstmt12 = con.prepareStatement("select * from Product where productname=?");
						pstmt12.setString(1, pname1);
						ResultSet res121 = pstmt12.executeQuery();
						res121.next();
						PreparedStatement pstmt13 = con.prepareStatement("insert into purchase values(?,?,?,?)");
						pstmt13.setString(1, name);
						pstmt13.setString(2, pname1);
						pstmt13.setInt(3,noofprod1 );
						pstmt13.setDouble(4, res121.getDouble(3)*noofprod1);
						int res131 = pstmt13.executeUpdate();
						if(res131>0) {
							System.out.println("product add to cart");
							totalPrice+=res121.getDouble(3)*noofprod1;
							System.out.println("Do you want to use superCoins(100 coins = 5rs) press 'y' to use 'n' not to use");
							String choice = scan.next();
							if(choice.equals("y")) {
								if(res.getInt(4)>0) {
									int discount=res.getInt(4)/100;
									final_discount=discount*5;
									PreparedStatement pstmt = con.prepareStatement("update User set supercoins=supercoins-? where username=?");
									pstmt.setInt(1, discount*100);
									pstmt.setString(2, name);
									int result = pstmt.executeUpdate();
									if(result>0) {
										System.out.println("after using superCoins. available superCoins : "+(res.getInt(4)-(discount*100)));
									}
								}
								
							}
							else {
								System.out.println("Ok");
							}
						}
					
					break;
				
					
				}
				
				System.out.println("Do you want to continue(y/n)");
				choice = scan.next();
				if(choice.equals("n")) {
					System.out.println("Final bill : "+totalPrice);
					totalPrice-=final_discount;
					System.out.println("after using super coins");
					System.out.println("Final bill : "+totalPrice);
				}
				
			}
		}
			
		}
	
	public void register() throws SQLException {
		PreparedStatement pstmt1 = con.prepareStatement("insert into User values(?,?,?,?)");
		System.out.println("enter user name");
		pstmt1.setString(1, scan.next());
		System.out.println("Enter mail Id");
		pstmt1.setString(2, scan.next());
		System.out.println("Enter user password");
		pstmt1.setString(3, scan.next());
		//System.out.println("Enter mail id");
		//pstmt1.setString(4, scan.next());
		pstmt1.setInt(4, 100);
		int res1 = pstmt1.executeUpdate();
		if(res1>0) {
			System.out.println("registered successfully.As a welcome bonus you got 100 super coins");
		}
		
		
	}
		
}
