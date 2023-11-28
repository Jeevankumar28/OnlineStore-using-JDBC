package com.training.menu;

import java.sql.SQLException;
import java.util.*;

import com.training.Service.UserService;
public class UserMenu {
	private Scanner scan;
	String choice ="y";
	UserService us;
	public UserMenu() {
		scan = new Scanner(System.in);
		us = new UserService();
	}
	public void menu() throws SQLException {
		while(choice.equals("y")) {
			System.out.println("1. Login as Admin");
			System.out.println("2. Login as User");
			System.out.println("3. Register");
			System.out.println("4. Exit");
			System.out.println("Enter your choice");
			int ch = scan.nextInt();
			switch(ch){
			case 1:
				us.validadmin();
				break;
			case 2:
				us.valid();
				break;
			case 3:
				us.acceptuser();
				break;
			case 4:
				System.exit(0);
			}
			System.out.println("Do you want to continue(y/n)");
			choice = scan.next();
		}
		
	}
}


