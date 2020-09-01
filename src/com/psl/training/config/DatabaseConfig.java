package com.psl.training.config;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
	static Properties props = new Properties();
	static Connection con;
	static DatabaseConfig instance;

	private DatabaseConfig() {

	}

	static private void loadProperties(String fileName) {
		try {
			props.load(new FileReader(fileName));

			System.out.println(props.entrySet());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add one static method to provide instance

	public static Connection getConnection(String fileName) {

		// load driver
		if (instance == null) {
			instance = new DatabaseConfig();
			loadProperties(fileName);
			try {
				Class.forName(props.getProperty("db.driver"));
				System.out.println(" Driver loaded.....");
				String url = props.getProperty("db.url");
				String user = props.getProperty("db.username");
				String password = props.getProperty("db.password");
				con = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return con;
		}

		return con;
	}
}
