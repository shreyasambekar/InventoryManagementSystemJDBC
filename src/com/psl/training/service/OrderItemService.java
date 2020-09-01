package com.psl.training.service;

import java.util.List;

import com.psl.training.dao.OrderItemDao;
import com.psl.training.model.OrderItem;
import com.psl.training.model.PurchaseOrder;

public class OrderItemService {
	
	OrderItemDao orderItemDao = new OrderItemDao();
	
	public void insertOrderItem(OrderItem orderItem, PurchaseOrder purchaseOrder) {
		StockItemService stockItemService = new StockItemService();
		//Decreasing the quantity in stock after an order is placed
		stockItemService.addQuantity(orderItem.getStockItem(), orderItem.getNumberOfItems() * (-1));
		
		orderItemDao.insertOrderItem(orderItem, purchaseOrder);
		int id = orderItemDao.selectIdLastInsertedOrderItem();
		orderItem.setId(id - 1);
		purchaseOrder.addOrderItem(orderItem);
	}

	public boolean isAvailable(OrderItem orderItem) {
		StockItemService stockItemService = new StockItemService();
		return stockItemService.isAvailable(orderItem.getStockItem(), orderItem.getNumberOfItems());
	}
	
	public List<OrderItem> getOrderItemsByPurchaseOrderId(int id) {
		return orderItemDao.selectOrderItemsByPurchaseOrderId(id);
	}

	
	public double getTotal(OrderItem orderItem) {
		return orderItem.getNumberOfItems() * (orderItem.getStockItem()).getItemPrice();
	}
}
