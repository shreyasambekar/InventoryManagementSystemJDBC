package com.psl.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.psl.training.config.DatabaseConfig;
import com.psl.training.model.Customer;
import com.psl.training.model.OrderItem;
import com.psl.training.model.PurchaseOrder;

public class PurchaseOrderDao {

	Connection con = DatabaseConfig.getConnection("config.properties");
	
	public void insertPurchaseOrder(PurchaseOrder purchaseOrder, Customer cust){
		String query = "INSERT INTO purchaseorders(orderDate, customerid)"; 
		query += "VALUES(?, ?)";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setDate(1, purchaseOrder.getOrderDate());
	        pstmt.setInt(2, cust.getId());
	        
	        pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public int selectIdLastInsertedPurchaseOrder() {
		String query = "select AUTO_INCREMENT from information_schema.tables where table_name = 'purchaseorders'";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				ResultSet resultSet = pstmt.executeQuery();
				resultSet.next();
				int id = resultSet.getInt(1);
				resultSet.close();
				return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public HashMap<Integer, List<PurchaseOrder>> selectAllOrdersToBeShipped() {
		String query = "select * from purchaseorders where shipDate is NULL";
		HashMap<Integer, List<PurchaseOrder>> custOrders = new HashMap<Integer, List<PurchaseOrder>>();
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				int poNumber = resultSet.getInt("poNumber");
				Date orderDate = resultSet.getDate("orderDate");
				int customerId = resultSet.getInt("customerid");
				
				PurchaseOrder purchaseOrder = new PurchaseOrder();
				purchaseOrder.setId(poNumber);
				purchaseOrder.setOrderDate(orderDate);
				purchaseOrder.setShipDate(null);
				
				if(custOrders.containsKey(customerId)) {
					custOrders.get(customerId).add(purchaseOrder);
				}
				else {
					custOrders.put(customerId, new ArrayList<PurchaseOrder>());
					custOrders.get(customerId).add(purchaseOrder);
				}
			}
			
			return custOrders;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	public void shipOrder(PurchaseOrder purchaseOrder, Date date) {
		String query = "update purchaseorders set shipDate = ? where poNumber = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setDate(1, date);
	        pstmt.setInt(2, purchaseOrder.getId());
	        
	        pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<PurchaseOrder> selectPurchaseOrdersByCustomerId(int id) {
		String query = "select * from purchaseorders where customerid = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				pstmt.setInt(1, id);
				ResultSet resultSet = pstmt.executeQuery();
				List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
				while(resultSet.next()) {
					int poNumber = resultSet.getInt("poNumber");
					Date orderDate = resultSet.getDate("orderDate");
					Date shipDate = resultSet.getDate("shipDate");
					int customerId = resultSet.getInt("customerid");
					
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					
					purchaseOrder.setOrderDate(orderDate);
					purchaseOrder.setShipDate(shipDate);
					purchaseOrder.setId(poNumber);
				
					poList.add(purchaseOrder);
				}
				return poList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PurchaseOrder> selectOrdersToBeShippedOn(Date date) {
		String query = "select * from purchaseorders where shipDate = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				pstmt.setDate(1, date);
				ResultSet resultSet = pstmt.executeQuery();
				List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
				while(resultSet.next()) {
					int poNumber = resultSet.getInt("poNumber");
					Date orderDate = resultSet.getDate("orderDate");
					Date shipDate = resultSet.getDate("shipDate");
					int customerId = resultSet.getInt("customerid");
					
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					
					purchaseOrder.setOrderDate(orderDate);
					purchaseOrder.setShipDate(shipDate);
					purchaseOrder.setId(poNumber);
				
					poList.add(purchaseOrder);
				}
				return poList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PurchaseOrder> selectShippedOrdersLastMonth() {
		String query = "select * from purchaseorders where month(shipDate) = month(curdate()) - 1";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				ResultSet resultSet = pstmt.executeQuery();
				List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
				while(resultSet.next()) {
					int poNumber = resultSet.getInt("poNumber");
					Date orderDate = resultSet.getDate("orderDate");
					Date shipDate = resultSet.getDate("shipDate");
					
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					
					purchaseOrder.setOrderDate(orderDate);
					purchaseOrder.setShipDate(shipDate);
					purchaseOrder.setId(poNumber);
				
					poList.add(purchaseOrder);
				}
				return poList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PurchaseOrder> selectAllOrdersLastMonth() {
		String query = "select * from purchaseorders where month(orderDate) = month(curdate()) - 1";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				ResultSet resultSet = pstmt.executeQuery();
				List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
				while(resultSet.next()) {
					int poNumber = resultSet.getInt("poNumber");
					Date orderDate = resultSet.getDate("orderDate");
					Date shipDate = resultSet.getDate("shipDate");
					
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					
					purchaseOrder.setOrderDate(orderDate);
					purchaseOrder.setShipDate(shipDate);
					purchaseOrder.setId(poNumber);
				
					poList.add(purchaseOrder);
				}
				return poList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PurchaseOrder> selectAllItemsToShipCurrentWeek() {
		String query = "select * from purchaseorders where week(shipDate) = week(curdate())";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				ResultSet resultSet = pstmt.executeQuery();
				List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
				while(resultSet.next()) {
					int poNumber = resultSet.getInt("poNumber");
					Date orderDate = resultSet.getDate("orderDate");
					Date shipDate = resultSet.getDate("shipDate");
					
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					
					purchaseOrder.setOrderDate(orderDate);
					purchaseOrder.setShipDate(shipDate);
					purchaseOrder.setId(poNumber);
				
					poList.add(purchaseOrder);
				}
				return poList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
