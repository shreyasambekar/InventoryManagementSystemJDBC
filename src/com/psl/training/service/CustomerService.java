package com.psl.training.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.psl.training.dao.CustomerDao;
import com.psl.training.model.Customer;
import com.psl.training.model.PurchaseOrder;
import com.psl.training.model.OrderItem;

public class CustomerService {
	
	CustomerDao custDao = new CustomerDao();
	
	public void insertCustomer(Customer cust) {
		custDao.insertCustomer(cust);
		int id = custDao.selectIdLastInsertedCustomer();
		cust.setId(id - 1);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> custList = custDao.selectAllCustomers();
		PurchaseOrderService poService = new PurchaseOrderService();
		for(int i = 0; i < custList.size(); i++) {
			custList.get(i).setPurchaseOrdersList(poService.getPurchaseOrdersByCustomerId(custList.get(i).getId()));
		}
		return custList;
	}
	
	public Map<String, Map<String, List<PurchaseOrder>>> segregateOrdersByAreaCustomer() {
		PurchaseOrderService poService = new PurchaseOrderService();
		Map<String, Map<String, List<PurchaseOrder>>> orders = new HashMap<String, Map<String, List<PurchaseOrder>>>();
		List<Customer> custList = this.getAllCustomers();
		for(int i = 0; i < custList.size(); i++) {
			if(orders.containsKey(custList.get(i).getCity())) {
				orders.get(custList.get(i).getCity()).put(custList.get(i).getName(), poService.getPurchaseOrdersByCustomerId(custList.get(i).getId())); 
			}
			else {
				orders.put(custList.get(i).getCity(), new HashMap<String, List<PurchaseOrder>>());
				orders.get(custList.get(i).getCity()).put(custList.get(i).getName(), new ArrayList<PurchaseOrder>());
				orders.get(custList.get(i).getCity()).put(custList.get(i).getName(), poService.getPurchaseOrdersByCustomerId(custList.get(i).getId())); 
			}	
		}
		return orders;
	}

	public Customer getCustomerByName(String name) {
		Customer cust = custDao.selectCustomerByName(name);
		PurchaseOrderService poService = new PurchaseOrderService();
		cust.setPurchaseOrdersList(poService.getPurchaseOrdersByCustomerId(cust.getId()));
		return cust;
	}
	
	public Customer getCustomerById(int id) {
		Customer cust = custDao.selectCustomerById(id);
		PurchaseOrderService poService = new PurchaseOrderService();
		cust.setPurchaseOrdersList(poService.getPurchaseOrdersByCustomerId(cust.getId()));
		return cust;
	}

	public void printLabelsForOrdersToBeShipped(HashMap<Integer, List<PurchaseOrder>> custOrders) {
		for(Integer id : custOrders.keySet()) {
			Customer cust = this.getCustomerById(id);
			PurchaseOrderService poService = new PurchaseOrderService();
			
			for(int i = 0; i < custOrders.get(id).size(); i++) {
				cust.print();
				List<OrderItem> orderItems = custOrders.get(id).get(i).getItems();
				for(int j = 0; j < orderItems.size(); j++) {
					System.out.println(orderItems.get(j));
				}
				System.out.println("The order details are: " + custOrders.get(id).get(i));
				System.out.println("The total amount to be paid is: " + poService.sumItems(custOrders.get(id).get(i)) + "\n");
			}
		}	
	}

	public void printTotalSalesByCustomer() {
		List<Customer> custList = this.getAllCustomers();
		PurchaseOrderService poService = new PurchaseOrderService();
		for(int i = 0; i < custList.size(); i++) {
			double sale = 0;
			for(int j = 0; j < custList.get(i).getPurchaseOrders().size(); j++) {
				sale = sale + poService.sumItems(custList.get(i).getPurchaseOrders().get(j));
			}
			System.out.println("Customer Name: " + custList.get(i).getName() + " Sale: " + sale);
		}
	}

	public void printAllInvoices() {
		List<Customer> custList = this.getAllCustomers();
		PurchaseOrderService poService = new PurchaseOrderService();
		for(int i = 0; i < custList.size(); i++) {
			for(int j = 0; j < custList.get(i).getPurchaseOrders().size(); j++) {
				custList.get(i).print();
				List<OrderItem> orderItems = custList.get(i).getPurchaseOrders().get(j).getItems();
				for(int k = 0; k < orderItems.size(); k++) {
					System.out.println(orderItems.get(k));
				}
				System.out.println("The order details are: " + custList.get(i).getPurchaseOrders().get(j));
				System.out.println("The total amount to be paid is: " + poService.sumItems(custList.get(i).getPurchaseOrders().get(j)) + "\n");
			}
		}
	}
	
	public void populateFromFile(String filename) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			line = reader.readLine();
			while(line != null) {
				String[] attributes = line.split(",");
				if(attributes.length < 8) {
					throw new InsufficientDataException("Insufficient data: Values for all required attributes not available");
				}
				
				String name = attributes[0];
				String street = attributes[1];
				String city = attributes[2];
				String state = attributes[3];
				long zip = Long.parseLong(attributes[4]);
				String homephone = attributes[5];
				String cellphone = attributes[6];
				String workphone = attributes[7];

				Customer cust = new Customer(name);
				cust.setPrintingAddress(street, city, state, zip);
				cust.setPhoneNumbers(homephone, cellphone, workphone);
				
				this.insertCustomer(cust);

				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void printAreaWiseBillingResult(Map<String, Map<String, List<PurchaseOrder>>> orders) {
		PurchaseOrderService poService = new PurchaseOrderService();
		for(String city : orders.keySet()) {
			System.out.println("\n" + city);
			double bill = 0;
			for(String name : orders.get(city).keySet()) {
				System.out.println("\tCustomer name: " + name);
				List<PurchaseOrder> poList = orders.get(city).get(name);
				for(int i = 0; i < poList.size(); i++) {
					System.out.println("\t\tBill for " + poList.get(i) + " is: " + poService.sumItems(poList.get(i)));
					bill = bill + poService.sumItems(poList.get(i));
				}
			}
			System.out.println("The total bill for " + city + " area is: " + bill);
		}
	}
	
}

class InsufficientDataException extends RuntimeException {
	public InsufficientDataException(String msg) {
		super(msg);
	}
}
