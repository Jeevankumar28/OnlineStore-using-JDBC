package com.training.pojo;


	//This is sub class of item class which will include all properties of item
	public class Product extends Item {
		private int productid;
		private String productName;
		private double sellingPrice;
		private int availableQuantity;
		public int getProductid() {
			return productid;
		}
		public void setProductid(int productid) {
			this.productid = productid;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public double getSellingPrice() {
			return sellingPrice;
		}
		public void setSellingPrice(double sellingPrice) {
			this.sellingPrice = sellingPrice;
		}
		public int getAvailableQuantity() {
			return availableQuantity;
		}
		public void setAvailableQuantity(int availableQuantity) {
			this.availableQuantity = availableQuantity;
		}
		
}
