package com.training.main;

import java.sql.SQLException;

import com.training.menu.UserMenu;

public class AppMain {
	public static void main(String[] args) {
		UserMenu m = new UserMenu();
		try {
			m.menu();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

