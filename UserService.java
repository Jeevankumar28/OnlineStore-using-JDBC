package com.training.Service;

import java.sql.SQLException;

import com.training.DAO.AdminValidation;
import com.training.DAO.UserValidation;

public class UserService {
	UserValidation uv;
	AdminValidation av;
	public UserService() {
		uv = new UserValidation();
		av = new AdminValidation();
		}
	public void acceptuser() throws SQLException {
		uv.register();
	}
	public void valid() throws SQLException {
		uv.valid();
	}
	public void validadmin() throws SQLException {
		av.adminValid();
	}
}


