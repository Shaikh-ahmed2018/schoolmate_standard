package com.cerp.rest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import com.centris.campus.util.ErrorMessage;

public class DBConnection {

	private static final Logger logger = Logger.getLogger(DBConnection.class);

	static Connection connection = null;
	static PreparedStatement statement = null;
	private static String DriverName = null;
	private static String protocal = null;
	private static String ipaddress = null;
	private static String portno = null;
	private static String database = null;
	private static String username = null;
	private static String password = null;
	private static String ConnecionString = null;
	
	
	static {

		try {

			ResourceBundle res = ResourceBundle
					.getBundle("com/centris/campus/properties/CAMPUS");
			DriverName = res.getString("mySqlDriver");
			protocal = res.getString("protocal");
			ipaddress = res.getString("ipaddress");
			portno = res.getString("portno");
			database = res.getString("database");
			username = res.getString("username");
			password = res.getString("password");

			ConnecionString = protocal + "://" + ipaddress + ":" + portno + "/"
					+ database;
			logger.info("Starting Database Connection");
			Class.forName(DriverName);
			connection = (Connection) DriverManager.getConnection(
					ConnecionString, username, password);
		} catch (Exception e) {
			logger.error("Problem in getting Connection, Database Not Connected");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Not Connected");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("---------------------------");
			System.out.println(" Connection is established ");
			System.out.println("---------------------------");

		} else {
			System.out.println("---------------------------");
			System.out.println(" Connection is NOT established ");
			System.out.println("---------------------------");
		}

	}
	
	public static Connection getmasterConnection() throws Exception {
		Connection connection = null;
		try {

			String driverName = DriverName;
			String hostName = protocal + "://" + ipaddress + ":" + portno+"/";
			String dbName = database;
			String dbuserName = username;
			String dbpassword = password;
		
				Class.forName(driverName).newInstance();
				connection = DriverManager.getConnection(hostName + dbName, dbuserName,
						dbpassword);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
