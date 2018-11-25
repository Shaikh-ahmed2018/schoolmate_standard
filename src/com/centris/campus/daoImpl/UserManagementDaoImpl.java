package com.centris.campus.daoImpl;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.centris.campus.dao.UserManagementDao;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.UserManagementSqlutil;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementDaoImpl implements UserManagementDao {

	private static final Logger logger = Logger	.getLogger(UserManagementDaoImpl.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String serviceurl = res.getString("serviceurl");
	
	public List<UserRecordVO> getUserRecordsDao(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserRecordsDao  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			ps_getUsers = conn.prepareStatement(UserManagementSqlutil.USERRECORDS);
			
			rs_getUsers = ps_getUsers.executeQuery();
			
			while (rs_getUsers.next()) {
				
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs_getUsers.getString("username"));
				userRecordVO.setMobile(rs_getUsers.getString("mobileNo"));
				userRecordVO.setFirstName(rs_getUsers.getString("teacher"));
				
				if (rs_getUsers.getString("TeacherID").startsWith("PAR")) {
					userRecordVO.setDesignation("Parent");
				} else {
					userRecordVO.setDesignation("Teacher");
				}
				userRecordVO.setUserId(rs_getUsers.getString("usercode"));
				
				userRecordsList.add(userRecordVO);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserRecordsDao Ending");
	
		
		return userRecordsList;
	}




	public List<UserRecordVO> getSearchUserDetailsDao(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getSearchUserDetailsDao  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getType())){
				
				ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_TEACHERS);
				ps_getUsers.setString(1, userManagementPojo.getSearchtext()+"%");
				ps_getUsers.setString(2, userManagementPojo.getSearchtext()+"%");
				ps_getUsers.setString(3, userManagementPojo.getSearchtext()+"%");
				ps_getUsers.setString(4, userManagementPojo.getSearchtext()+"%");
				System.out.println("88888888888"+ps_getUsers);
				
			}else{
			
		      	ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_PARENTS);
		      	ps_getUsers.setString(1, userManagementPojo.getSearchtext()+"%");
		    	ps_getUsers.setString(2, userManagementPojo.getSearchtext()+"%");
		    	ps_getUsers.setString(3, userManagementPojo.getSearchtext()+"%");
		    	System.out.println("9999999999999"+ps_getUsers);
			}
			
			rs_getUsers = ps_getUsers.executeQuery();
			
			while (rs_getUsers.next()) {
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs_getUsers.getString("username"));
				userRecordVO.setMobile(rs_getUsers.getString("mobileno"));
				/*userRecordVO.setAddress(rs_getUsers.getString("address"));*/
				userRecordVO.setFirstName(rs_getUsers.getString("FirstName"));
				if (rs_getUsers.getString("Id").startsWith("PAR")) {
					userRecordVO.setDesignation("Parent");
				} else {
					userRecordVO.setDesignation("Teacher");
				}
				userRecordVO.setUserId(rs_getUsers.getString("Id"));
				
				userRecordsList.add(userRecordVO);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getSearchUserDetailsDao Ending");
	
		
		return userRecordsList;
	}





	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserDeatils  Starting");
		
		PreparedStatement ps_getUsers = null;
		ResultSet rs_getUsers= null;
		Connection conn = null;
		
		UserRecordVO  userRecordVO=new  UserRecordVO();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
            if(MessageConstants.PARENT_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getUserId().substring(0, 3))){
				
            	ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_PARENT);
		      	ps_getUsers.setString(1, userManagementPojo.getUserId());
				rs_getUsers = ps_getUsers.executeQuery();
				while (rs_getUsers.next()) {
					
					userRecordVO.setFirstName(rs_getUsers.getString("FatherName"));
					userRecordVO.setEmail(rs_getUsers.getString("UserName"));
					
				
				}
				
			}else{
				
				ps_getUsers = conn.prepareStatement(UserManagementSqlutil.GET_TEACHER);
				ps_getUsers.setString(1, userManagementPojo.getUserId());
				
				System.out.println("666666666"+ps_getUsers);
				rs_getUsers = ps_getUsers.executeQuery();
				while (rs_getUsers.next()) {
					
					userRecordVO.setFirstName(rs_getUsers.getString("teachername"));
					userRecordVO.setEmail(rs_getUsers.getString("username"));
					userRecordVO.setTechId(rs_getUsers.getString("TeacherID"));
					
				}

			
		      	

			}
			
			
			
			
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs_getUsers != null	&& !rs_getUsers.isClosed()) {
					rs_getUsers.close();
				}
				if (ps_getUsers != null && !ps_getUsers.isClosed()) {
					ps_getUsers.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserDeatils Ending");
	
		
		return userRecordVO;
	}




	public String changePassword(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : changePassword  Starting");
		
		PreparedStatement ps_updatePwd = null;
		Connection conn = null;
		String result=MessageConstants.FALSE;
		PreparedStatement ps_user = null;
		int rs_user = 0;
		int count = 0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
            if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getTecId().substring(0, 3))){
            	
            	ps_updatePwd = conn.prepareStatement(UserManagementSqlutil.CHANGE_TEACHER_PWD);
            	ps_updatePwd.setString(1, userManagementPojo.getPasswrd());
            	ps_updatePwd.setString(2, userManagementPojo.getTecId());
            	System.out.println("9999999999"+ps_updatePwd);
            	count  = ps_updatePwd.executeUpdate();
			}else{
				
				ps_updatePwd = conn.prepareStatement(UserManagementSqlutil.CHANGE_PARENT_PWD);
				ps_updatePwd.setString(1, userManagementPojo.getPasswrd());
            	ps_updatePwd.setString(2, userManagementPojo.getUserId());
            	count  = ps_updatePwd.executeUpdate();
            	
				
				
			}
			
            ps_user = conn.prepareStatement(UserManagementSqlutil.CHANGE_TEACHER_PWD_USER);
        	ps_user.setString(1, userManagementPojo.getPasswrd());
        	ps_user.setString(2, userManagementPojo.getUserId());
        	System.out.println("0000000000"+ps_user);
        	rs_user = ps_user.executeUpdate();
			 
        	if(rs_user>0){
        		HelperClass.recordLog_Activity(userManagementPojo.getLog_audit_session(),"Settings","PasswordMaintenance","Update",ps_user.toString(),userLoggingsVo);
        	}
			
			if(count>0){
				HelperClass.recordLog_Activity(userManagementPojo.getLog_audit_session(),"Settings","PasswordMaintenance","Update",ps_updatePwd.toString(),userLoggingsVo);
				result = MessageConstants.TRUE;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (ps_user != null && !ps_user.isClosed()) {
					ps_user.close();
				}
				if (ps_updatePwd != null && !ps_updatePwd.isClosed()) {
					ps_updatePwd.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : changePassword Ending");
	
		
		return result;
	}



	public String blockUser(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : blockUser  Starting");
		
		PreparedStatement ps_blockUser = null;
		Connection conn = null;
		String result=MessageConstants.FALSE;
	
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
            if(MessageConstants.TEACHER_SHORT_NAME.equalsIgnoreCase(userManagementPojo.getUserId().substring(0, 3))){
				
            	ps_blockUser = conn.prepareStatement(UserManagementSqlutil.BLOCK_TEACHER);
            	ps_blockUser.setString(1, userManagementPojo.getUserId());

			}else{
			
				ps_blockUser = conn.prepareStatement(UserManagementSqlutil.BLOCK_PARENT);
				ps_blockUser.setString(1, userManagementPojo.getUserId());
			}
			
			int count  = ps_blockUser.executeUpdate();
			
			if(count>0){
				
				result = MessageConstants.TRUE;
			}
			
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (ps_blockUser != null && !ps_blockUser.isClosed()) {
					ps_blockUser.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : blockUser Ending");
	
		
		return result;
	}




	@Override
	public String insertUserDetails(UserManagementPojo details,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : insertUserDetails  Starting");
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		String status = null;
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			if(details.getUserId()=="" || details.getUserId()==null){
			String userid = IDGenerator.getPrimaryKeyID("campus_user", userLoggingsVo);
			details.setUserId(userid);
			
			pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_USER_DETAILS);
			pstmt.setString(1, details.getUserId());
			pstmt.setString(2, details.getCustid());
			pstmt.setString(3, details.getCustid());
			pstmt.setString(4, details.getUname());
			pstmt.setString(5, details.getPasswrd());
			pstmt.setString(6, details.getRoleId());
			pstmt.setString(7, "user");
			pstmt.setString(8, details.getLocationId());
			pstmt.setString(9, details.getCuruserid());
			pstmt.setString(10, details.getMobileno());
			pstmt.setString(11, details.getEmail());
			
			/*pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());*/
			 count  = pstmt.executeUpdate();
			if(count>0){
				HelperClass.recordLog_Activity(details.getLog_audit_session(),"Settings","User Settings","Insert",pstmt.toString(),userLoggingsVo);
				callAddUserService(details,userLoggingsVo);
				status = "success";
			}else{
				status = "fail";
			}
		  }
		  else{
			    pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_USER_DETAILS);
			    pstmt.setString(1, details.getUname());
			    pstmt.setString(2, details.getPasswrd());
			    pstmt.setString(3, details.getEmail());
			    pstmt.setString(4, details.getMobileno());
			    pstmt.setString(5, details.getRoleId());
			    pstmt.setString(6, details.getLocationId());
			    pstmt.setString(7, details.getCuruserid());
			    pstmt.setString(8, details.getUserId());
			    
			     count  = pstmt.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(details.getLog_audit_session(),"Settings","User Settings","Update",pstmt.toString(),userLoggingsVo);
					status = "update";
					callUpdateUserService(details);
				}else{
					status = "updatefail";
				}
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : insertUserDetails Ending");
		
		return status;
	}

	private void callUpdateUserService(UserManagementPojo details) throws JSONException {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());  
		
		JSONObject jsoninput = new JSONObject();
		
		jsoninput.put("auserid", details.getAuserid());
		jsoninput.put("uname",details.getUname());
		jsoninput.put("pwd", details.getPasswrd());
		jsoninput.put("mobile", details.getMobileno());
		jsoninput.put("email", details.getEmail());
		
		System.out.println(jsoninput);
		
		JSONObject jsonObj = new JSONObject(service.path("modify").request().put(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		System.out.println(jsonObj);
		
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri(serviceurl).build();  
	}

	private void callAddUserService(UserManagementPojo details,UserLoggingsPojo userLoggingsVo) throws JSONException {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());  
		
		JSONObject jsoninput = new JSONObject();
		
		jsoninput.put("custid", userLoggingsVo);
		jsoninput.put("uname",details.getUname());
		jsoninput.put("pwd", details.getPasswrd());
		jsoninput.put("mobile", details.getMobileno());
		jsoninput.put("email", details.getEmail());
		
		System.out.println(jsoninput);
		
		JSONObject jsonObj = new JSONObject(service.path("add").request().post(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		System.out.println(jsonObj);
		if(jsonObj.getInt("code") == 201){
			details.setAuserid(jsonObj.getString("auserid"));
			updateUserDetails(details,userLoggingsVo);
		}
		
	}

	private void blockuser(String isActive,String string) throws JSONException {
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());  
		
		JSONObject jsoninput = new JSONObject();
		
		jsoninput.put("auserid", string);
		jsoninput.put("isActive",isActive);
		
		System.out.println(jsoninput);
		
		JSONObject jsonObj = new JSONObject(service.path("block").request().put(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		System.out.println(jsonObj);
		if(jsonObj.getInt("code") == 201){
			/*details.setAuserid(jsonObj.getString("auserid"));
			updateUserDetails(details);*/
		}
		
	}
	
	private void updateUserDetails(UserManagementPojo details,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : updateUserDetails  Starting");
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		String status = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_AUSER_ID);
			pstmt.setString(1,details.getAuserid());
			pstmt.setString(2,details.getCuruserid());
			pstmt.setString(3,details.getUserId());
			int count = pstmt.executeUpdate();
			if(count > 0){
				status = "success";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : updateUserDetails Ending");
	
	}

	@Override
	public List<UserRecordVO> getUserDetails(UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserDetails  Starting");
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		Connection conn = null;
		
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			
			pstm = conn.prepareStatement(UserManagementSqlutil.GET_ALL_USER_RECORDS);
			System.out.println("GET_ALL_USER_RECORDS -->>"+pstm);
            rs = pstm.executeQuery();
			while (rs.next()) {
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs.getString("username"));
				userRecordVO.setMobile(rs.getString("mobileno"));
				userRecordVO.setEmail(rs.getString("emailId"));
				userRecordVO.setRoleName(rs.getString("RoleName"));
				userRecordVO.setUserCode(rs.getString("usercode"));
				userRecordVO.setCustId(rs.getString("cust_id"));
				userRecordVO.setRemarks(rs.getString("remarks"));
				userRecordVO.setAuserid(rs.getString("app_userid"));
				userRecordsList.add(userRecordVO);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null	&& !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserDetails Ending");
	
		return userRecordsList;
	}




	@Override
	public UserRecordVO getUserDetailsforEdit(UserRecordVO details,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : getUserDetailsforEdit  Starting");
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		Connection conn = null;
		UserRecordVO userRecordVO=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstm = conn.prepareStatement(UserManagementSqlutil.GET_USER_DETAILS);
			pstm.setString(1, details.getUserId());
			System.out.println("getUserDetailsforEdit -->>"+pstm);
			rs = pstm.executeQuery();
			while (rs.next()){
				
				userRecordVO = new UserRecordVO();
				
				userRecordVO.setRoleName(rs.getString("role"));
				userRecordVO.setLocId(rs.getString("locationId"));
				userRecordVO.setUserName(rs.getString("username"));
				userRecordVO.setPassword(rs.getString("password"));
				userRecordVO.setMobile(rs.getString("mobileno"));
				userRecordVO.setEmail(rs.getString("emailId"));
				userRecordVO.setUserCode(rs.getString("usercode"));
				userRecordVO.setAuserid(rs.getString("app_userid"));
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null	&& !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : getUserDetailsforEdit Ending");
	
		return userRecordVO;
	}




	public List<UserRecordVO> InActiveUserList(UserManagementPojo details, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : InActiveUserList  Starting");
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		Connection conn = null;
		List<UserRecordVO> userRecordsList = new ArrayList<UserRecordVO>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstm = conn.prepareStatement(UserManagementSqlutil.GET_ALL_ACTIVE_INACTIVE_USER_RECORDS);
			pstm.setString(1, details.getStatus());
			
			System.out.println("GET_ALL_ACTIVE_INACTIVE_USER_RECORDS -->>"+pstm);
            rs = pstm.executeQuery();
			while (rs.next()) {
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs.getString("username"));
				userRecordVO.setMobile(rs.getString("mobileno"));
				userRecordVO.setEmail(rs.getString("emailId"));
				userRecordVO.setRoleName(rs.getString("RoleName"));
				userRecordVO.setUserCode(rs.getString("usercode"));
				userRecordVO.setCustId(rs.getString("cust_id"));
				userRecordVO.setRemarks(rs.getString("remarks"));
				userRecordsList.add(userRecordVO);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null	&& !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : InActiveUserList Ending");
	
		return userRecordsList;
	}




	@Override
	public String blockUserByStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : blockUserByStatus  Starting");
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		Connection conn = null;
		String status=null,value=null;
		int count=0;
		String isActive=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstm = conn.prepareStatement(UserManagementSqlutil.UPDATE_USER_RECORDS_STATUS);
			
			for(int i=0;i<userManagementPojo.getUserIds().length;i++){
				 if(userManagementPojo.getStatus().equalsIgnoreCase("Block")){
					 pstm.setString(1, "N");
					 pstm.setString(2, userManagementPojo.getRemarks()); 
					 value="InActive";
					 isActive = "N";
				 }
				 else{
					 pstm.setString(1, "Y");
					 pstm.setString(2, userManagementPojo.getRemarks()); 
					 value="Active";
					 isActive = "Y";
				 }
				 pstm.setString(3, userManagementPojo.getUserIds()[i]);
				 System.out.println("UPDATE_USER_RECORDS_STATUS "+userManagementPojo.getStatus()+" -->>"+pstm);
				 count= pstm.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(userManagementPojo.getLog_audit_session(),"Settings","User Settings",value,pstm.toString(),userLoggingsVo);
					
				 }
			}
			if(count > 0){
				status="true";
				 blockuser(isActive,userManagementPojo.getAuserid());
			}
			else{ 
				status="false";
			 }

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null	&& !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : blockUserByStatus Ending");
	
		return status;
	}
	


	public String validateusername(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementDaoImpl : validateusername : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status = null,value=null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT COUNT(username) as username,isActive FROM campus_user WHERE username=?");
			pstmt.setString(1,userManagementPojo.getUname());
			System.out.println("validateusername -->>"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("username");
				value = rs.getString("isActive");
			}
			
			if (count > 0 && value.equalsIgnoreCase("N")) {
				status = "inactive";
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
				status = "true";
			} 
			else {
				status = "false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementDaoImpl : validateusername : Ending");

		return status;
	}




	@Override
	public String blockUserStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UserManagementDaoImpl : blockUserStatus  Starting");
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		Connection conn = null;
		String status=null,value=null;
		int count=0;
		String isActive=null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstm = conn.prepareStatement(UserManagementSqlutil.UPDATE_USER_RECORDS_STATUS_TEACHER);
			
			for(int i=0;i<userManagementPojo.getUserIds().length;i++){
				 if(userManagementPojo.getStatus().equalsIgnoreCase("Block")){
					 pstm.setString(1, "N");
					 pstm.setString(2, userManagementPojo.getRemarks()); 
					 value="InActive";
					 isActive = "N";
				 }
				 else{
					 pstm.setString(1, "Y");
					 pstm.setString(2, userManagementPojo.getRemarks()); 
					 value="Active";
					 isActive = "Y";
				 }
				 pstm.setString(3, userManagementPojo.getUserIds()[i]);
				 System.out.println("UPDATE_USER_RECORDS_STATUS "+userManagementPojo.getStatus()+" -->>"+pstm);
				 count= pstm.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(userManagementPojo.getLog_audit_session(),"Settings","User Settings",value,pstm.toString(),custdetails);
					
				 }
			}
			if(count > 0){
				status="true";
				/* blockuser(isActive,userManagementPojo.getAuserid());*/
			}
			else{ 
				status="false";
			 }

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null	&& !rs.isClosed()) {
					rs.close();
				}
				if (pstm != null && !pstm.isClosed()) {
					pstm.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in Control in UserManagementDaoImpl : blockUserByStatus Ending");
	
		return status;
	}




	@Override
	public List<UserRecordVO> userListSearch(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementDaoImpl : userListSearch  Starting");
		
		List<UserRecordVO> searchlist = new ArrayList<UserRecordVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count1=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
	        
			String qry = "SELECT cu.`usercode`,cu.`username`,ct.`TeacherID`,CONCAT(ct.`FirstName`,' ',ct.`LastName`)AS teacher ,ct.`mobileNo` FROM `campus_user` cu JOIN `campus_role` cr ON cr.`RoleCode`=cu.`role` AND cr.`isActive`='Y' JOIN `campus_teachers` ct ON ct.`TeacherID`=cu.`employeecode`AND ct.`isActive`='Y' WHERE ";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!userManagementPojo.getStatus().equalsIgnoreCase("%%")) {
				map.put("cu.`isActive`",userManagementPojo.getStatus());
				vec.add("cu.`isActive`");
			}
			
			/*if(!userManagementPojo.getType().equalsIgnoreCase("%%")) {
				map.put("cu.employeecode",userManagementPojo.getType()+"%");
				vec.add("cu.employeecode");
			}*/
			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			
			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(cu.employeecode like ? and (CONCAT(ct.`FirstName`,' ',ct.`LastName`) LIKE ? OR cu.username LIKE ? OR ct.`mobileNo`LIKE ? or cu.employeecode like ? ))  ORDER BY ct.`TeacherID`"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(cu.employeecode like ? and (CONCAT(ct.`FirstName`,' ',ct.`LastName`) LIKE ? OR cu.username LIKE ? OR ct.`mobileNo`LIKE ? or cu.employeecode like ? ) )  ORDER BY ct.`TeacherID`"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, userManagementPojo.getType()+ "%");
			pstmt.setString(2, userManagementPojo.getSearchtext()+ "%%");
			pstmt.setString(3, userManagementPojo.getSearchtext()+ "%%");
			pstmt.setString(4, userManagementPojo.getSearchtext()+ "%%");
			pstmt.setString(5, userManagementPojo.getSearchtext()+ "%%");
			
			System.out.println("finalquery is "+pstmt);
			rs = pstmt.executeQuery();

               while (rs.next()) {
				
				UserRecordVO userRecordVO = new UserRecordVO();
				userRecordVO.setUserName(rs.getString("username"));
				userRecordVO.setMobile(rs.getString("mobileNo"));
				userRecordVO.setFirstName(rs.getString("teacher"));
				
				if (rs.getString("TeacherID").startsWith("PAR")) {
					userRecordVO.setDesignation("Parent");
				} else {
					userRecordVO.setDesignation("Teacher");
				}
				userRecordVO.setUserId(rs.getString("usercode"));
				
				searchlist.add(userRecordVO);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementDaoImpl : userListSearch  Ending");
		
		return searchlist;
	}

}
