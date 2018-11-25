package com.centris.campus.serviceImpl;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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

import com.centris.campus.daoImpl.LoginDaoImpl;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.UserDetailVO;

public class LoginServiceImpl {
	
	private static final Logger logger = Logger
			.getLogger(LoginServiceImpl.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String serviceurl = res.getString("serviceurl");
	
	public LoginVo validateUserServiceImpl(String UserName,
			String Password, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl:validateUserServiceImpl Starting");
		
		LoginVo result=null;
		
		try {
			 result = new LoginDaoImpl().validateUserDaoImpl(UserName,Password,pojo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: validateUserServiceImpl  Ending");
		
		return result;
	}

	public UserDetailVO loadUserServiceImpl(LoginVo loginvo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadUserServiceImpl Starting");
		UserDetailVO userdetails = null;
		
		try {
			
			userdetails = new LoginDaoImpl().loadUserDaoImpl(loginvo,userLoggingsVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadUserServiceImpl  Ending");
		return userdetails;

	}


	public int checkCurrentPassword(String user, String currentPassword,
			String currentuserId, UserLoggingsPojo custdetails) {

		return new LoginDaoImpl().checkCurrentPassword(user, currentPassword,
				currentuserId,custdetails);
	}

	public int changePassword(String user, String newpassword,
			String currentuserId, String log_audit_session,UserLoggingsPojo pojo) {

		return new LoginDaoImpl().changePassword(user, newpassword,
				currentuserId,log_audit_session, pojo);
	}

	public String userValidCase(String uname, String password, UserLoggingsPojo custdetails) {
		return new LoginDaoImpl().userValidCase(uname, password,custdetails);
	}

	public String saveDairy(String content, String rowid, String date,
			String userId, String userType, UserLoggingsPojo custdetails, String sessionid) {
		return new LoginDaoImpl().saveDairy(content, rowid,date,userId,userType,custdetails,sessionid);
	}

	public List<DairyDetailsVo> getDairy(String userId,UserLoggingsPojo custdetails) {
		return new LoginDaoImpl().getDairy(userId,custdetails);
	}

	public UserDetailVO loadNewUserBD(LoginVo loginVo,UserLoggingsPojo userLoggingsVo) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadNewUserBD Starting");
		UserDetailVO userdetails = null;
		
		try {
			
			userdetails = new LoginDaoImpl().loadNewUserBD(loginVo,userLoggingsVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadNewUserBD  Ending");
		return userdetails;
		
	}

	public UserDetailVO validateNewUserBD(String userid, UserLoggingsPojo custdetails) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadNewUserBD Starting");
		UserDetailVO userdetails = null;
		
		try {
			
			userdetails = new LoginDaoImpl().validateNewUserBD(userid,custdetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: loadNewUserBD  Ending");
		return userdetails;
		
	}

public String callPWDChangeService(String auserid, String custId, String newPassword) throws JSONException {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: callPWDChangeService Starting");
		
		String status = null;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());
	      
		JSONObject jsonserver = new JSONObject();
		jsonserver.put("auserid",auserid);
		jsonserver.put("custid",custId);
		jsonserver.put("pwd",newPassword);
		
		JSONObject jsonObj = new JSONObject(service.path("changepwd").request().put(Entity.entity(jsonserver.toString(),MediaType.APPLICATION_JSON),String.class));
		
		if(jsonObj.getInt("code") == 201){
			status = "true";
		}else{
			status = "false";
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginServiceImpl: callPWDChangeService  Ending");
		
		return status;
	}
	
	private URI getBaseURI() {
		return UriBuilder.fromUri(serviceurl).build();  
	}


}