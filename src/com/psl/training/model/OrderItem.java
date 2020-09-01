package com.psl.training.model;

public class OrderItem {
	private int id;
	private StockItem stockItem;
	private int numberOfItems;
	
	public OrderItem(StockItem stockItem, int numberOfItems) {
		this.stockItem = stockItem;
		this.numberOfItems = numberOfItems;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public StockItem getStockItem() {
		return this.stockItem;
	}
	
	public int getStockItemId() {
		return this.stockItem.getId();
	}
	
	public int getNumberOfItems() {
		return this.numberOfItems;
	}

	public void setNumberOfItems(int number) {
		this.numberOfItems = number;
	}

	@Override
	public String toString() {
		return this.stockItem + "\tQuantity: "+ this.numberOfItems + "\tPrice per item: " + this.stockItem.getItemPrice();
	}
}
