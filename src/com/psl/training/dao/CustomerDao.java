package com.psl.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.psl.training.config.DatabaseConfig;
import com.psl.training.model.Customer;

public class CustomerDao {

	Connection con = DatabaseConfig.getConnection("config.properties");;
	
	public void insertCustomer(Customer cust){
		String query = "INSERT INTO customers(zip, homephone, cellphone, workphone, name, street, city, state)"; 
		query += "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement statement = this.con.prepareStatement(query)) {
			statement.setLong(1, cust.getZip());
	        statement.setString(2, cust.getHomephone());
	        statement.setString(3, cust.getCellphone());
	        statement.setString(4, cust.getWorkphone());
	        statement.setString(5, cust.getName());
	        statement.setString(6, cust.getStreet());
	        statement.setString(7, cust.getCity());
	        statement.setString(8, cust.getState());
	        statement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public int selectIdLastInsertedCustomer() {
		String query = "select AUTO_INCREMENT from information_schema.tables where table_name = 'customers'";
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
	
	public List<Customer> selectAllCustomers() {
		String query = "select * from customers";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				ResultSet resultSet = pstmt.executeQuery();
				List<Customer> custList = new ArrayList<Customer>();
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					long zip = resultSet.getInt("zip");
					String homephone = resultSet.getString("homephone");
					String cellphone = resultSet.getString("cellphone");
					String workphone = resultSet.getString("workphone");
					String name = resultSet.getString("name");
					String street = resultSet.getString("street");
					String city = resultSet.getString("city");
					String state = resultSet.getString("state");

					Customer cust = new Customer(name);
					cust.setPhoneNumbers(homephone, cellphone, workphone);
					cust.setPrintingAddress(street, city, state, zip);
					cust.setId(id);
					custList.add(cust);
				}
				return custList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer selectCustomerByName(String name) {
		String query = "select * from customers where name = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				pstmt.setString(1, name);
				ResultSet resultSet = pstmt.executeQuery();
				Customer cust = new Customer(name);
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					long zip = resultSet.getInt("zip");
					String homephone = resultSet.getString("homephone");
					String cellphone = resultSet.getString("cellphone");
					String workphone = resultSet.getString("workphone");
					String street = resultSet.getString("street");
					String city = resultSet.getString("city");
					String state = resultSet.getString("state");
					
					cust.setPhoneNumbers(homephone, cellphone, workphone);
					cust.setPrintingAddress(street, city, state, zip);
					cust.setId(id);
				}
				return cust;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Customer selectCustomerById(int id) {
		String query = "select * from customers where id = ?";
		try(PreparedStatement pstmt = this.con.prepareStatement(query)) {
				pstmt.setInt(1, id);
				ResultSet resultSet = pstmt.executeQuery();
				Customer cust = new Customer(id);
				while(resultSet.next()) {
					String name = resultSet.getString("name");
					long zip = resultSet.getInt("zip");
					String homephone = resultSet.getString("homephone");
					String cellphone = resultSet.getString("cellphone");
					String workphone = resultSet.getString("workphone");
					String street = resultSet.getString("street");
					String city = resultSet.getString("city");
					String state = resultSet.getString("state");
					
					cust.setPhoneNumbers(homephone, cellphone, workphone);
					cust.setPrintingAddress(street, city, state, zip);
					cust.setName(name);
				}
				return cust;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
