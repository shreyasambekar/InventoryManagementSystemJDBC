package com.psl.training.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private long zip;
	private String homephone, cellphone, workphone;
	private String name, street, city, state;
	private List<PurchaseOrder> poList;
	
	public Customer(String name) {
		this.name = name;
		this.poList = new ArrayList<PurchaseOrder>();
	}
	
	public Customer(int id) {
		this.id = id;
		this.poList = new ArrayList<PurchaseOrder>();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setPrintingAddress(String street, String city, String state, long zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public void setPhoneNumbers(String homephone, String cellphone, String workphone) {
		this.homephone = homephone;
		this.cellphone = cellphone;
		this.workphone = workphone;
	}
	
	public long getZip() {
		return this.zip;
	}
	
	public String getHomephone() {
		return this.homephone;
	}
	
	public String getCellphone() {
		return this.cellphone;
	}
	
	public String getWorkphone() {
		return this.workphone;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public List<PurchaseOrder> getPurchaseOrders() {
		return this.poList;
	}
	
	public void setPurchaseOrdersList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}
	
	public void print() {
		System.out.println("Customer id: " + this.id);
		System.out.println("Customer name: " + this.name);
		System.out.println("Homephone: " + this.homephone);
		System.out.println("Cellphone: " + this.cellphone);
		System.out.println("Workphone: " + this.workphone);
		System.out.println("Street: " + this.street);
		System.out.println("City: " + this.city);
		System.out.println("State: " + this.state);
		System.out.println("Zip: " + this.zip);
	}
	
	public void printPhoneNumbers() {
		System.out.println("The home phone number is: " + this.homephone);
		System.out.println("The cell phone number is: " + this.cellphone);
		System.out.println("The work phone number is: " + this.workphone);
	}
	
	public void printShippingAddress() {
		System.out.println("The shipping address is: " + this.street + ", "  + this.city + " - " + this.zip + ", " +  this.state + "\n");
	}


	public void addPurchaseOrder(PurchaseOrder pO) {
		this.poList.add(pO);
	}

	public void setName(String name) {
		this.name = name;
	}

}
