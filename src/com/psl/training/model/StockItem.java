package com.psl.training.model;

import com.psl.training.service.StockItemService;


public class StockItem {

	private int itemNumber;
	private String itemDescription;
	private double itemPrice;
	private Unit unitOfItem;
	private int quantity;
	
	public StockItem(String itemDescription, double itemPrice) {
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		if(itemDescription.equals("Milk")) {
			this.unitOfItem = Unit.GALLONS;
		}
		else if(itemDescription.equals("Chicken")) {
			this.unitOfItem = Unit.LBS;
		}
		else {
			this.unitOfItem = Unit.NUMBER;
		}
	}
	
	public void setId(int id) {
		this.itemNumber = id;
	}
	
	public int getId() {
		return this.itemNumber;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Unit getUnitOfItem() {
		return unitOfItem;
	}

	public void setUnitOfItem(Unit unitOfItem) {
		this.unitOfItem = unitOfItem;
	}
	
	public String getUnitNameOfItem() {
		return unitOfItem.name();
	}

	@Override
	public String toString() {
		return itemDescription + "(in " + unitOfItem + ")";
	}
	
}
