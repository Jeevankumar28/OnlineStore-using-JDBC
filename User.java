package com.training.pojo;

import java.util.Map;

	public class User {
		private String username;
		private String emailid;
		private String password;
		private int supercoins;
		
		private Map<Integer,User> map;
		
		public Map<Integer, User> getMap() {
			return map;
		}
		public void setMap(Map<Integer, User> map) {
			this.map = map;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getSupercoins() {
			return supercoins;
		}
		public void setSupercoins(int supercoins) {
			this.supercoins = supercoins;
		}
		

}
