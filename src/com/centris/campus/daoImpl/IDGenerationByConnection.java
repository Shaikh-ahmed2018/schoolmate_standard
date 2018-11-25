package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class IDGenerationByConnection {

	private static final Logger logger = Logger.getLogger(IDGenerationByConnection.class);

	public static String getPrimaryKeyID(String tableName,String custid, Connection myConnection) throws SQLException {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Starting");
		String NextID = null;
		try {
			tableName = tableName.trim().toLowerCase();
			int currentID = 0;
			String pre = null;
			String previousID = getPreviousID(tableName,custid,myConnection);
			if (previousID == null) {
				currentID = 0;
			} else {
				String id = previousID.substring(3);
				currentID = Integer.parseInt(id);
			}

			if (tableName.equalsIgnoreCase("birthday_sms")) {
				pre = "BDY";
			}
			else if (tableName.equalsIgnoreCase("sms_absent_details")) {
				pre = "ABS";
			}

			NextID = pre + ++currentID;


		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPrimaryKeyID Ending");

		return NextID;
	}

	private static String getPreviousID(String tableName,String custid, Connection myConnection) throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Starting");
		int counter = 0;
		String columnName = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {


			String sql = "SHOW KEYS FROM " + tableName
					+ " WHERE Key_name = 'PRIMARY'";

			ps = (PreparedStatement) myConnection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				columnName = (String) rs.getString("Column_name");
			}
			ps.close();
			rs.close();
			String sql2 = "select max(" + columnName + ") from " + tableName
					+ " group by length(" + columnName + ") desc limit 1";
			ps = (PreparedStatement) myConnection.prepareStatement(sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				counter++;
				columnName = (String) rs.getString(1);
			}

			ps.close();
			if (counter == 0) {
				return null;
			} else {

			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
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
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in IDGenerator: getPreviousID Ending");
		return columnName;
	}

}
