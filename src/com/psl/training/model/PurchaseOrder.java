package com.psl.training.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {

	private int poNumber;
	private Date orderDate;
	private Date shipDate;
	private List<OrderItem> orderItemList;
	
	public PurchaseOrder() {
		this.orderItemList = new ArrayList<OrderItem>();
	}
	
	public void setId(int id) {
		this.poNumber = id;
	}
	
	public int getId() {
		return this.poNumber;
	}
	
	public void setShipDate(Date date) {
		this.shipDate = date;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getShipDate() {
		return this.shipDate;
	}
	
	public Date getOrderDate() {
		return this.orderDate;
	}
	
	public void create(int poNumber, Date orderDate) {
		this.poNumber = poNumber;
		this.orderDate = orderDate;
	}
	
	public List<OrderItem> getItems() {
		return this.orderItemList;
	}
	
	public void setItems(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		this.orderItemList.add(orderItem);
	}
	
	@Override
	public String toString() {
		return "PurchaseOrder [poNumber=" + poNumber + ", orderDate=" + orderDate + ", shipDate=" + shipDate + "]";
	}
}
