package com.psl.training.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.psl.training.dao.OrderItemDao;

import com.psl.training.dao.PurchaseOrderDao;
import com.psl.training.model.Customer;
import com.psl.training.model.OrderItem;
import com.psl.training.model.PurchaseOrder;

public class PurchaseOrderService {
	
	PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDao();
	
	public void insertPurchaseOrder(PurchaseOrder purchaseOrder, Customer cust) {
		purchaseOrderDao.insertPurchaseOrder(purchaseOrder, cust);
		int id = purchaseOrderDao.selectIdLastInsertedPurchaseOrder();
		purchaseOrder.setId(id - 1);
		cust.addPurchaseOrder(purchaseOrder);
	}

	public HashMap<Integer,List<PurchaseOrder>> getAllOrdersToBeShipped() {
		OrderItemService orderItemService = new OrderItemService();
		HashMap<Integer, List<PurchaseOrder>> custOrders = purchaseOrderDao.selectAllOrdersToBeShipped();
		for(Integer id : custOrders.keySet()) {
			for(int i = 0; i < custOrders.get(id).size(); i++) {
				custOrders.get(id).get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(custOrders.get(id).get(i).getId()));
			}
		}
		return custOrders;
	}
	
	public void shipOrder(PurchaseOrder purchaseOrder, Date date) {
		purchaseOrder.setShipDate(date);
		purchaseOrderDao.shipOrder(purchaseOrder, date);
	}

	public List<PurchaseOrder> getPurchaseOrdersByCustomerId(int id) {
		List<PurchaseOrder> poList =  purchaseOrderDao.selectPurchaseOrdersByCustomerId(id);
		OrderItemService orderItemService = new OrderItemService();
		for(int i = 0; i < poList.size(); i++) {
			poList.get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(i).getId()));
		}
		return poList;
	}
	
	public void printShippedOrdersLastMonth() {
		List<PurchaseOrder> poList = purchaseOrderDao.selectShippedOrdersLastMonth();
		OrderItemService orderItemService = new OrderItemService();
		for(int i = 0; i < poList.size(); i++) {
			poList.get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(i).getId()));
		}
		System.out.println("The orders shipped in last month are: ");
		for(int i = 0; i < poList.size(); i++) {
			System.out.println(poList.get(i));
		}
	}
	
	public List<PurchaseOrder> findOrdersToBeShippedOn(Date date) {
		List<PurchaseOrder> poList = purchaseOrderDao.selectOrdersToBeShippedOn(date);
		OrderItemService orderItemService = new OrderItemService();
		for(int i = 0; i < poList.size(); i++) {
			poList.get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(i).getId()));
		}
		return poList;
	}
	
	public List<PurchaseOrder> getAllOrdersLastMonth() {
		List<PurchaseOrder> poList = purchaseOrderDao.selectAllOrdersLastMonth();
		OrderItemService orderItemService = new OrderItemService();
		for(int i = 0; i < poList.size(); i++) {
			poList.get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(i).getId()));
		}
		return poList;
	}
	
	public double sumItems(PurchaseOrder po) {
		OrderItemService orderItemService = new OrderItemService();
		double sum = 0;
		for(int i = 0; i < (po.getItems()).size(); i++) {
			sum = sum + orderItemService.getTotal((po.getItems().get(i)));
		}
		return sum;
	}

	public void printSaleLastMonth() {
		List<PurchaseOrder> poList = this.getAllOrdersLastMonth();
		OrderItemService orderItemService = new OrderItemService();
		double sale = 0;
		for(int i = 0; i < poList.size(); i++) {
			double sum = 0;
			for(int j = 0; j < (poList.get(i).getItems()).size(); j++) {
				sum = sum + orderItemService.getTotal((poList.get(i).getItems().get(j)));
			}
			sale = sale + sum;
		}
		System.out.println("The total sale in the last month is: " + sale + "\n\n");
	}

	public void printAllItemsToShipCurrentWeek() {
		List<PurchaseOrder> poList = purchaseOrderDao.selectAllItemsToShipCurrentWeek();
		OrderItemService orderItemService = new OrderItemService();
		for(int i = 0; i < poList.size(); i++) {
			poList.get(i).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(i).getId()));
		}
		System.out.println("The items to be shipped in the current week are as follows: ");
		for(int i = 0; i < poList.size(); i++) {
			System.out.println("For purchase order with details: " + poList.get(i) + " items are as follows: ");
			for(int j = 0; j < poList.get(i).getItems().size(); j++) {
				System.out.println(poList.get(i).getItems().get(j));
			}
		}
	}
}
