package com.psl.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.psl.training.config.DatabaseConfig;
import com.psl.training.model.OrderItem;
import com.psl.training.model.PurchaseOrder;
import com.psl.training.model.StockItem;

public class OrderItemDao {
	
	Connection con = DatabaseConfig.getConnection("config.properties");
	
	public void insertOrderItem(OrderItem orderItem, PurchaseOrder purchaseOrder){
		String query = "INSERT INTO orderitems(stockItemId, numberOfItems, purchaseOrderId)"; 
		query += "VALUES(?, ?, ?)";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setInt(1, orderItem.getStockItemId());
	        pstmt.setInt(2, orderItem.getNumberOfItems());
	        pstmt.setInt(3, purchaseOrder.getId());
	        
	        pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public int selectIdLastInsertedOrderItem() {
		String query = "select AUTO_INCREMENT from information_schema.tables where table_name = 'orderitems'";
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

	public List<OrderItem> selectOrderItemsByPurchaseOrderId(int id) {
		String query = "select * from orderitems where purchaseOrderId = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				pstmt.setInt(1, id);
				ResultSet resultSet = pstmt.executeQuery();
				List<OrderItem> orderItemList = new ArrayList<OrderItem>();
				while(resultSet.next()) {
					int orderItemId = resultSet.getInt("id");
					int numberOfItems = resultSet.getInt("numberOfItems");
					int stockItemId = resultSet.getInt("stockItemId");
					
					String sql = "select * from stockitems where itemNumber = ?";
					PreparedStatement stmt = this.con.prepareStatement(sql);
					stmt.setInt(1, stockItemId);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					
					int itemNumber = rs.getInt("itemNumber");
					String itemDescription = rs.getString("itemDescription");
					double itemPrice = rs.getDouble("itemPrice");
					int quantity = rs.getInt("quantity");
					
					StockItem stockItem = new StockItem(itemDescription, itemPrice);
					stockItem.setId(itemNumber);
					stockItem.setQuantity(quantity);
					
					OrderItem orderItem = new OrderItem(stockItem, numberOfItems);
					orderItem.setId(orderItemId);
				
					orderItemList.add(orderItem);
				}
				return orderItemList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
