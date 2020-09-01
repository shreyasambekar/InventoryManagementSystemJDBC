package com.psl.training.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.psl.training.dao.StockItemDao;
import com.psl.training.model.Customer;
import com.psl.training.model.StockItem;

public class StockItemService {

	StockItemDao stockItemDao = new StockItemDao();

	public void insertStockItem(StockItem stockItem) {
		if(stockItem.getUnitOfItem().getMinQuantity() > stockItem.getQuantity()) {
			stockItem.setQuantity(stockItem.getUnitOfItem().getMinQuantity());
		}
		stockItemDao.insertStockItem(stockItem);
		int id = stockItemDao.selectIdLastInsertedStockItem();
		stockItem.setId(id - 1);
	}
	
	public void addQuantity(StockItem stockItem, int quantity) {
		stockItemDao.addQuantity(stockItem, quantity);
		stockItem.setQuantity(stockItem.getQuantity() + quantity);
		//Always keeping the minimum stock in inventory
		if(stockItem.getQuantity() < stockItem.getUnitOfItem().getMinQuantity()) {
			stockItemDao.addQuantity(stockItem, stockItem.getUnitOfItem().getMinQuantity() - stockItem.getQuantity());
			stockItem.setQuantity(stockItem.getUnitOfItem().getMinQuantity());
		}
	}
	
	public StockItem getStockItemByDescription(String description) {
		return stockItemDao.selectStockItemByDescription(description);
	}

	public boolean isAvailable(StockItem stockItem, int numberOfItems) {
		if(numberOfItems > stockItem.getUnitOfItem().getMinQuantity()) {
			return false;
		}
		return true;
	}
	
	public void populateFromFile(String filename) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			line = reader.readLine();
			while(line != null) {
				String[] attributes = line.split(",");
				if(attributes.length < 2) {
					throw new InsufficientDataException("Insufficient data: Values for all required attributes not available");
				}
				
				String itemDescription = attributes[0];
				double itemPrice = Double.parseDouble(attributes[1]);
				StockItem stockItem = new StockItem(itemDescription, itemPrice);
				
				if(attributes.length == 3) {
					stockItem.setQuantity(Integer.parseInt(attributes[2]));
				}

				this.insertStockItem(stockItem);

				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
