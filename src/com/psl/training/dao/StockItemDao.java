package com.psl.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.psl.training.config.DatabaseConfig;
import com.psl.training.model.Customer;
import com.psl.training.model.StockItem;

public class StockItemDao {

	Connection con = DatabaseConfig.getConnection("config.properties");

	public void insertStockItem(StockItem stockItem){
		String query = "INSERT INTO stockitems(itemDescription, itemPrice, unitOfItem, quantity)"; 
		query += "VALUES(?, ?, ?, ?)";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setString(1, stockItem.getItemDescription());
	        pstmt.setDouble(2, stockItem.getItemPrice());
	        pstmt.setString(3, stockItem.getUnitNameOfItem());
	        //Always keeping the defined minimum value in stock
	        pstmt.setInt(4, stockItem.getQuantity());
	        
	        pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public int selectIdLastInsertedStockItem() {
		String query = "select AUTO_INCREMENT from information_schema.tables where table_name = 'stockitems'";
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

	public void addQuantity(StockItem stockItem, int quantity) {
		String query = "update stockitems set quantity = ? where itemNumber = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setInt(1, stockItem.getQuantity() + quantity);
	        pstmt.setInt(2, stockItem.getId());
	        
	        pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public StockItem selectStockItemByDescription(String description) {
		String query = "select * from stockitems where itemDescription = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
			pstmt.setString(1,  description);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int itemNumber = rs.getInt("itemNumber");
			String itemDescription = rs.getString("itemDescription");
			double itemPrice = rs.getDouble("itemPrice");
			int quantity = rs.getInt("quantity");
			
			StockItem stockItem = new StockItem(itemDescription, itemPrice);
			stockItem.setId(itemNumber);
			stockItem.setQuantity(quantity);
			
			return stockItem;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
