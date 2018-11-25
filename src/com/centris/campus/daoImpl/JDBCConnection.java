package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.ErrorMessage;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.cerp.rest.dao.CustomerDaoImpl;
import com.cerp.rest.model.Customer;

public class JDBCConnection {

	private static final Logger logger = Logger.getLogger(JDBCConnection.class);

	static Connection connection = null;
	static PreparedStatement statement = null;
	private static String DriverName = null;
	private static String protocal = null;
	private static String portno = null;

	
	static {
		try {
			ResourceBundle res = ResourceBundle
					.getBundle("com/centris/campus/properties/CAMPUS");
			DriverName = res.getString("mySqlDriver");
			protocal = res.getString("protocal");
			portno = res.getString("portno");
			
			logger.info("Starting Database Connection");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*public static Connection getSeparateConnection(String custid) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Starting");
		Connection conn = null;
		 
		try {

			//Customer dbdetails = new CustomerDaoImpl().getCustDBdetails(custid,10);
			
			String driverName = DriverName;
			String hostName = protocal + "://" + "192.168.1.99" + ":" + portno+"/";
			String dbName = "school_management_standard";
			String dbuserName = "data";
			String dbpassword = "cERP!1@2#3";

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Ending");
		return conn;
	}*/
	
	public static Connection getSeparateConnection(UserLoggingsPojo dbdetails) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Starting");
		Connection conn = null;
		 
		try {
			
			String driverName = DriverName;
			String hostName = protocal + "://" + dbdetails.getCustdbhost() + ":" + portno+"/";
			String dbName = dbdetails.getCustdbname();
			String dbuserName = dbdetails.getCustdbnuame();
			String dbpassword = dbdetails.getCustdbpwd();

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Ending");
		return conn;
	}
	
	public static Connection getSeparateConnection() throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Starting");
		Connection conn = null;
		 
		try {

			Customer dbdetails = new Customer();
			
			String driverName = DriverName;
			String hostName = protocal + "://" + "localhost" + ":" + portno+"/";
			String dbName = "school_management_standard";
			String dbuserName = "data";
			String dbpassword = "cERP!1@2#3";

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Ending");
		return conn;
	}
	
	
	public static Connection getConnection(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getConnection : Starting");
		Connection conn = null;
		try {
			String driverName = DriverName;
			String hostName = protocal + "://" + "localhost" + ":" + portno+"/";
			String dbName = "school_management_standard";
			String dbuserName = "data";
			String dbpassword = "cERP!1@2#3";

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);

		} catch (Exception e) {
			logger.error("Problem in getting Connection, Database Not Connected");
			logger.error(e.getMessage(), e);
			System.out.println(" Problem in getting ReConnection ");
			e.printStackTrace();
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Not Connected");
			System.out.println(EM.getMessage());
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getSeparateConnection : Ending");
		return conn;

	}

	public static Connection reConnect(String custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: reConnect : Starting");
		
		Connection conn = null;
		try {
			
			String driverName = DriverName;
			String hostName = protocal + "://" + "localhost" + ":" + portno+"/";
			String dbName = "school_management_standard";
			String dbuserName = "data";
			String dbpassword = "cERP!1@2#3";

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);} catch (Exception e) {
				logger.error("Trying to Reconnect,Problem in getting Connection, Database Not Connected");
				logger.error(e.getMessage(), e);
				ErrorMessage EM = ErrorMessage.getInstance();
				EM.setContent("DataBase Not Connected");
				System.out.println(EM.getMessage());
				return null;
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: reConnect : Ending");
		return conn;
	}

	public static Connection reConnect(UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: reConnect : Starting");
		
		Connection conn = null;
		try {
			String driverName = DriverName;
			String hostName = protocal + "://" + dbdetails.getCustdbhost() + ":" + portno+"/";
			String dbName = dbdetails.getCustdbname();
			String dbuserName = dbdetails.getCustdbnuame();
			String dbpassword = dbdetails.getCustdbpwd();

			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(hostName + dbName, dbuserName,
					dbpassword);
			} 
		catch (Exception e) {
				logger.error("Trying to Reconnect,Problem in getting Connection, Database Not Connected");
				logger.error(e.getMessage(), e);
				ErrorMessage EM = ErrorMessage.getInstance();
				EM.setContent("DataBase Not Connected");
				System.out.println(EM.getMessage());
				return null;
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: reConnect : Ending");
		return conn;
	}
	
	public static Statement getStatement(String sql,String custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Starting");
		Connection connection = null;
		
		try {
			connection = JDBCConnection.reConnect(custid);
			statement = (PreparedStatement) connection.prepareStatement(sql);

		} catch (SQLException e) {
			logger.error("Promlem in Creating Statement Object");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Problem");
			System.out.println(EM.getMessage());
			System.out.println(" Promlem in creating Statement object ");

			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Ending");
		return statement;
	}
	
	public static Statement getStatement(String sql) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Starting");
		Connection connection = null;
		
		try {
			//connection = JDBCConnection.reConnect(custid);
			statement = (PreparedStatement) connection.prepareStatement(sql);

		} catch (SQLException e) {
			logger.error("Promlem in Creating Statement Object");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Problem");
			System.out.println(EM.getMessage());
			System.out.println(" Promlem in creating Statement object ");

			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Ending");
		return statement;
	}
	
	
	public static Statement getStatement(String sql,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Starting");
		Connection connection = null;
		
		try {
			connection = JDBCConnection.reConnect(dbdetails);
			statement = (PreparedStatement) connection.prepareStatement(sql);

		} catch (SQLException e) {
			logger.error("Promlem in Creating Statement Object");
			logger.error(e.getMessage(), e);
			ErrorMessage EM = ErrorMessage.getInstance();
			EM.setContent("DataBase Problem");
			System.out.println(EM.getMessage());
			System.out.println(" Promlem in creating Statement object ");

			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in JDBCConnection: getStatement : Ending");
		return statement;
	}

	public static void closeStatement() {
		try {
			statement.close();
		} catch (SQLException e) {
			logger.info("Promlem in Closing Statement object");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}