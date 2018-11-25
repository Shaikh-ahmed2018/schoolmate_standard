package com.cerp.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.cerp.rest.model.User;
import com.cerp.rest.util.DBConnection;
import com.cerp.rest.util.GenerateId;
import com.cerp.rest.util.SQLConstants;

public class UserDaoImpl implements UserDao{

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	public String addUser(User user) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: addUser Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String result = null;
		try{
			String auserid = GenerateId.getPrimaryKeyID("campus_cust_userdetails");
			conn = DBConnection.getmasterConnection();
			
			pstmt = conn.prepareStatement(SQLConstants.INSERT_USER_DETAILS);
			pstmt.setString(1, auserid);
			pstmt.setString(2, user.getCustid());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getPwd());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getMobile());
			int count = pstmt.executeUpdate();
			pstmt.close();
			
			if(count > 0){
				result = auserid;
			}else{
				result = "fail";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null&& (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : addUser Ending");
		return result;
	}

	@Override
	public String modifyUser(User user) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: modifyUser Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status = null;
		try{
			
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.MODIFY_USER_DETAILS);
			pstmt.setString(1, user.getUname());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getMobile()); 
			pstmt.setString(5, user.getAuserid());
			int count = pstmt.executeUpdate();
			if(count > 0){
				status = "success";
			}else{
				status = "fail";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null&& (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : modifyUser Ending");
		return status;
	}

	@Override
	public String blockUser(User user) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: blockUser Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String status = null;
		int count = 0;
		int result = 0;
		try{
			String auserids[] = user.getAuserid().split(",");
			conn = DBConnection.getmasterConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQLConstants.BLOCK_USER_DETAILS);
			
			for(int i=0;i<auserids.length;i++){
				pstmt.setString(1, user.getIsActive());
				pstmt.setString(2, auserids[i]);
				count = pstmt.executeUpdate();
				if(count > 0){
					result ++  ;
				}
			}
			
			if(result == auserids.length){
				conn.commit();
				status = "success";
			}else{
				conn.rollback();
				status = "fail";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null&& (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : blockUser Ending");
		return status;
	}

	@Override
	public int verifyUser(String username) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: verifyUser Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 0;
		
		try{
			conn = DBConnection.getmasterConnection();
			pstmt = conn.prepareStatement(SQLConstants.VERIFY_USER_NAME);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				status = rs.getInt(1);
			}
			if(status > 0){
				status = 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null&& (!conn.isClosed())) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : verifyUser Ending");
		return status;
	}

	@Override
	public int changePwd(User user) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi :UpdateMasterDB Starting");
		Connection con=null;
		PreparedStatement pstmt = null;
		int result = 0;
		int status = 0;
		try{
			con=  DBConnection.getmasterConnection();
			pstmt =  con.prepareStatement("UPDATE `campus_cust_userdetails` SET `upwd` = ?  WHERE `cust_id` = ? and user_id = ?"); 
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getCustid());
			pstmt.setString(3, user.getAuserid());
			result = pstmt.executeUpdate();
			if(result > 0){
				status = 1;
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		finally{
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL: UpdateMasterDB Ending");
		return status;
		
	}

}
