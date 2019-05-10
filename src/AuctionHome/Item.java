package AuctionHome;

import javax.swing.JButton;

public class Item {

	 private String itemName;
	 private String picture;
	 private Double price;
	 private String time;
	 private static JButton button;
	 
	   public Item(String itemName, String picture, Double price, String time, JButton button) {
	        this.itemName = itemName;
	        this.picture = picture;
	        this.price = price;
	        this.time = time;
	        this.button = button;
	    }
	   
	   public Item(String itemName, String picture, Double price, String time) {
	        this.itemName = itemName;
	        this.picture = picture;
	        this.price = price;
	        this.time = time;
	    }
	   
	   public Item() {
		   
	   }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	 public static JButton getButtonList() {
		return button;
	}
	public void setButtonList(JButton button) {
		this.button = button;
	}

	public String toString() {
			return itemName + "," + picture + "," + price + "," + time;
		}
	   
	   
}
