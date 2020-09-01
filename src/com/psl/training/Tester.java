package com.psl.training;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.psl.training.model.Customer;
import com.psl.training.model.OrderItem;
import com.psl.training.model.PurchaseOrder;
import com.psl.training.model.StockItem;
import com.psl.training.service.CustomerService;
import com.psl.training.service.OrderItemService;
import com.psl.training.service.PurchaseOrderService;
import com.psl.training.service.StockItemService;

public class Tester {
	public static void main(String[] args) {
		
		//Call isAvailable every time before inserting order item
		
		/*Customer Jamie = new Customer("Jamie");
		Jamie.setPhoneNumbers("7038566424", "9423742625", "9426318787");
		Jamie.setPrintingAddress("Sathe Path", "Pune", "Maharashtra", 411016);
	
		CustomerService custService = new CustomerService();
		
		custService.insertCustomer(Jamie);
		
		StockItem stockItem = new StockItem("Chicken", 30);
		StockItemService stockItemService = new StockItemService();
		stockItemService.insertStockItem(stockItem);
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOrderDate(Date.valueOf("2020-08-09"));
		purchaseOrder.setShipDate(Date.valueOf("2020-09-03"));
		PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
		purchaseOrderService.insertPurchaseOrder(purchaseOrder, Jamie);
		
		OrderItem orderItem = new OrderItem(stockItem, 10);
		OrderItemService orderItemService = new OrderItemService();
		orderItemService.insertOrderItem(orderItem, purchaseOrder);
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderItemList.add(orderItem);
		purchaseOrder.setItems(orderItemList);
		
		List<PurchaseOrder> list = purchaseOrderService.getAllOrdersToBeShipped();*/
		/*StockItem stockItem = new StockItem("Milk", 30);
		StockItemService stockItemService = new StockItemService();
		stockItemService.insertStockItem(stockItem);
		stockItemService.addQuantity(stockItem, 20);*/
		
		
		/*
		Customer Jamie = custService.getCustomerByName("Jamie");
		System.out.println(Jamie.getCity());
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOrderDate(Date.valueOf("2020-08-09"));
		purchaseOrder.setShipDate(Date.valueOf("2020-09-03"));
		PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
		purchaseOrderService.insertPurchaseOrder(purchaseOrder, Jamie);
		
		StockItem stockItem = new StockItem("Chicken", 30);
		stockItemService.insertStockItem(stockItem);
		
		OrderItem orderItem = new OrderItem(stockItem, 10);
		orderItemService.insertOrderItem(orderItem, purchaseOrder);
		
		
		Jamie.setPurchaseOrdersList(poService.getPurchaseOrdersByCustomerId(Jamie.getId()));
		
		
		
		List<PurchaseOrder> poList = poService.getPurchaseOrdersByCustomerId(Jamie.getId());
		
		System.out.println(poList.get(0));
		
		
		poList.get(0).setItems(orderItemService.getOrderItemsByPurchaseOrderId(poList.get(0).getId()));
		
		System.out.println(((poList.get(0)).getItems()).get(0));
		*/
		
		/*StockItem stockItem = stockItemService.getStockItemByDescription("Chicken");
		
		Customer Jamie = custService.getCustomerByName("Jamie");
		
		List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
		poList = poService.getPurchaseOrdersByCustomerId(Jamie.getId());
		System.out.println(poList.get(0));
		
		List<OrderItem> orderItemList = orderItemService.getOrderItemsByPurchaseOrderId(poList.get(0).getId());
		
		System.out.println(orderItemList.get(0));-*/
		
		/*HashMap<Integer, List<PurchaseOrder>> custOrders = poService.getAllOrdersToBeShipped();
		custService.printLabelsForOrdersToBeShipped(custOrders);
	
		System.out.println(poService.findOrdersToBeShippedOn(Date.valueOf("2020-02-02")));
		
		custService.printTotalSalesByCustomer();
		
		custService.printAllInvoices();
		
		poService.printShippedOrdersLastMonth();
		
		poService.printSaleLastMonth();
		
		poService.printAllItemsToShipCurrentWeek();*/
		/*
		StockItem stockItem = new StockItem("Milk", 60);
		stockItem.setQuantity(20);
		stockItemService.insertStockItem(stockItem);
		stockItemService.addQuantity(stockItem, -50);
		System.out.println(stockItem.getQuantity());*/
		
		
		CustomerService custService = new CustomerService();
		PurchaseOrderService poService = new PurchaseOrderService();
		OrderItemService orderItemService = new OrderItemService();
		StockItemService stockItemService = new StockItemService();
		
		//Create 3 customers
		Customer jamie = new Customer("Jamie");
		jamie.setPhoneNumbers("7038566424", "9423742625", "9426318787");
		jamie.setPrintingAddress("Sathe Path", "Pune", "Maharashtra", 411016);
		Customer bill = new Customer("Bill");	
		bill.setPhoneNumbers("7000066424", "9123742625", "9123418787");
		bill.setPrintingAddress("Darga Marg", "Hyderabad", "Telangana", 443201);
		Customer joe = new Customer("Joe");	
		joe.setPhoneNumbers("7000061234", "9123741234", "9123416789");
		joe.setPrintingAddress("Railway Station Road", "Aurangabad", "Maharashtra", 411003);
		
		//Inserting data of all customers in database
		custService.insertCustomer(jamie);
		custService.insertCustomer(bill);
		custService.insertCustomer(joe);

		//Create the stock items
		StockItem milk = new StockItem("Milk", 60);
		StockItem chicken = new StockItem("Chicken", 120);
		StockItem egg = new StockItem("Egg", 6);
		StockItem apple = new StockItem("Apple", 30);
		StockItem orange = new StockItem("Orange", 10);
		
		//Inserting data of all stock items in database
		stockItemService.insertStockItem(milk);
		stockItemService.insertStockItem(chicken);
		stockItemService.insertStockItem(egg);
		stockItemService.insertStockItem(apple);
		stockItemService.insertStockItem(orange);
		
		//Creating purchase order 1
		PurchaseOrder po1 = new PurchaseOrder();
		po1.setOrderDate(Date.valueOf("2020-08-20"));
		poService.insertPurchaseOrder(po1, jamie);
		OrderItem item = new OrderItem(milk, 2);
		//Testing the availability of order item
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po1);
		}
		item = new OrderItem(chicken, 2);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po1);
		}
		item = new OrderItem(egg, 12);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po1);
		}
		
		//Creating purchase order 2
		PurchaseOrder po2 = new PurchaseOrder();
		po2.setOrderDate(Date.valueOf("2020-08-31"));
		poService.insertPurchaseOrder(po2, jamie);
		item = new OrderItem(apple, 5);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po2);
		}
		item = new OrderItem(orange, 10);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po2);
		}
		
		//Creating purchase order 3
		PurchaseOrder po3 = new PurchaseOrder();
		po3.setOrderDate(Date.valueOf("2020-07-31"));
		poService.insertPurchaseOrder(po3, bill);
		item = new OrderItem(chicken, 5);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po3);
		}
		item = new OrderItem(apple, 10);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po3);
		}
		
		//Creating purchase order 4 for Joe
		PurchaseOrder po4 = new PurchaseOrder();
		po4.setOrderDate(Date.valueOf("2020-07-11"));
		po4.setShipDate(Date.valueOf("2020-07-21"));
		poService.insertPurchaseOrder(po4, joe);
		item = new OrderItem(orange, 10);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po4);
		}
		item = new OrderItem(milk, 10);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po4);
		}
		
		//Creating purchase order 5 for Joe
		PurchaseOrder po5 = new PurchaseOrder();
		po5.setOrderDate(Date.valueOf("2020-07-15"));
		po5.setShipDate(Date.valueOf("2020-07-27"));
		poService.insertPurchaseOrder(po5, joe);
		item = new OrderItem(chicken, 5);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po5);
		}
		item = new OrderItem(apple, 7);
		if(orderItemService.isAvailable(item)) {
			orderItemService.insertOrderItem(item, po5);
		}
		
		//Print labels for all orders to be shipped
		System.out.println("The labels for all orders to be shipped are as follows: ");
		custService.printLabelsForOrdersToBeShipped(poService.getAllOrdersToBeShipped());
		System.out.println("\n");
		
		//Ship first order of Jamie
		poService.shipOrder(po1, Date.valueOf("2020-09-04"));
		
		//Ship order of Bill
		poService.shipOrder(po2, Date.valueOf("2020-09-10"));
		
		//Ship second order of Jamie
		poService.shipOrder(po3, Date.valueOf("2020-07-31"));
		
		//Print total sales by customer
		System.out.println("The total sales for each customer are as follows: ");
		custService.printTotalSalesByCustomer();
		System.out.println("\n");
		
		//Print all invoices
		System.out.println("The invoices for all orders are as follows");
		custService.printAllInvoices();
		System.out.println("\n");
		
		//Populate customers data from customersinfo.txt
		try {
			custService.populateFromFile("customersinfo.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		//Populate stockitems data from stockitem.txt
		try {
			stockItemService.populateFromFile("stockitem.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Segregate orders by area and customer - Here I have considered customers in same city belong to same area
		Map<String, Map<String, List<PurchaseOrder>>> orders = custService.segregateOrdersByAreaCustomer();
		System.out.println("Area wise total billing results are as follows: \n");
		custService.printAreaWiseBillingResult(orders);
		System.out.println("\n");
		
		//Find all purchase orders placed by a customer
		System.out.println("The orders placed by Joe are: ");
		List<PurchaseOrder> poList = poService.getPurchaseOrdersByCustomerId(joe.getId());
		for(int i = 0; i < poList.size(); i++) {
			System.out.println(poList.get(i));
		}
		System.out.println("\n");
	
		//The order shipped on specific date can be found as follows
		System.out.println("The orders details for orders shipped on date 2020-07-31 are: ");
		poList = poService.findOrdersToBeShippedOn(Date.valueOf("2020-07-31"));
		for(int i = 0; i < poList.size(); i++) {
			System.out.println(poList.get(i));
		}
		System.out.println("\n");
		
		//Printing all orders shipped in last month
		poService.printShippedOrdersLastMonth();
		System.out.println("\n");
		
		//Find total sale in last month
		poService.printSaleLastMonth();
		
		//Print all items to ship in current week
		poService.printAllItemsToShipCurrentWeek();
	}
}
