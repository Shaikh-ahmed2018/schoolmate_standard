package com.cerp.rest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.centris.campus.daoImpl.JDBCConnection;

public class GenerateId {

@SuppressWarnings("unused")
public static String getPrimaryKeyID(String tableName) throws SQLException {
		
		System.out.println("getPrimaryKeyID Started");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
		
			int currentID = 0;
			String pre = null;
			String custid = null;
			String previousID = getPreviousID(tableName);
			System.out.println(previousID);
			
			
			if (previousID == null) {
				currentID = 0;
			}else{
				String id = previousID.substring(5);
				currentID = Integer.parseInt(id);
			}
			
			if(tableName.equalsIgnoreCase("campus_customer_details")){
				pre = "eCamp";
			}else if(tableName.equalsIgnoreCase("campus_cust_userdetails")){
				pre = "AUSER";
			}
				
			NextID = pre + ++currentID;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return NextID;
}
	
	
	
	private static  String getPreviousID(String tableName) throws SQLException {
		Connection conn = null;
		int counter = 0;
		String columnName = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = "SHOW KEYS FROM " + tableName
					+ " WHERE Key_name = 'PRIMARY'";

			conn = DBConnection.getmasterConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				columnName = (String) rs.getString("Column_name");
			}
			ps.close();
			rs.close();
			String sql2 = "select max(" + columnName + ") from " + tableName
					+ " group by length(" + columnName + ") desc limit 1";
			
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				counter++;
				columnName = (String) rs.getString(1);
			}
			if (counter == 0) {
				return null;
			} else {

			}
		} catch (Exception sqlExp) {
			sqlExp.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if(conn != null && !conn.isClosed()){
					conn.close();
				}

			} catch (Exception exception) {
				exception.getStackTrace();
			}
		}
		return columnName;
	}
	
public static String getPrimaryKeyIDForUser(String tableName,Connection conn) throws SQLException {
		
		System.out.println("getPrimaryKeyID Started");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
		
			int currentID = 0;
			String pre = null;
			String previousID = getPreviousUSERID(tableName,conn);
			System.out.println(previousID);
			
			if (previousID == null) {
				currentID = 0;
			}else{
				String id = previousID.substring(3);
				currentID = Integer.parseInt(id);
			}
			
			if(tableName.equalsIgnoreCase("campus_user")){
				pre = "USR";
			}
				
			NextID = pre + ++currentID;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return NextID;
}
	
private static String getPreviousUSERID(String tableName,Connection conn) throws SQLException {
	int counter = 0;
	String columnName = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String sql = "SHOW KEYS FROM " + tableName
				+ " WHERE Key_name = 'PRIMARY'";

		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			columnName = (String) rs.getString("Column_name");
		}
		ps.close();
		rs.close();
		String sql2 = "select max(" + columnName + ") from " + tableName
				+ " group by length(" + columnName + ") desc limit 1";
		
		ps = conn.prepareStatement(sql2);
		rs = ps.executeQuery();
		while (rs.next()) {
			counter++;
			columnName = (String) rs.getString(1);
		}
		if (counter == 0) {
			return null;
		} else {

		}
	} catch (Exception sqlExp) {
		sqlExp.getStackTrace();
	} finally {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}
	return columnName;
}	
	
	
}
