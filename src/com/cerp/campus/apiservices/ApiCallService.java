package com.cerp.campus.apiservices;

import java.net.URI;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;


public class ApiCallService {
	
	private static final Logger logger = Logger.getLogger(ApiCallService.class);
	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String licser_service = res.getString("licser_service");
	private static String validateuser = res.getString("validateuser");
	
	public UserLoggingsPojo validateCustomer(String userName, String password, String domainName) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ApiCallService : validateCustomer  Starting");
		UserLoggingsPojo object = null;
		try {
		object = new UserLoggingsPojo(); 	
		JSONObject jsoninput = new JSONObject();
		jsoninput.put("appusername", userName);
		jsoninput.put("apppwd",password);
		jsoninput.put("domain",domainName);
		
		System.out.println(jsoninput);
		
		JSONObject jsonObj = new JSONObject(getclient(validateuser).request().post(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		System.out.println(jsonObj);
		
			if(jsonObj.getInt("code") == 201){
				object = JSON.parseObject(jsonObj.getJSONObject("customerinfo").toString(),UserLoggingsPojo.class); 
				
			}else if(jsonObj.getInt("code") == 404){
				object.setStatus(jsonObj.getString("message"));
			}else{
				object.setStatus("Unable to login please try after some time!!!");
			}
			
		} catch (Exception e) {
			object.setStatus("Internal Server Error");
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ApiCallService : checkValidateuser Ending");
		
		return object;
	}

	public JSONObject getTokenNo(JSONObject jsoninput) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ApiCallService : getTokenNo Starting");
		JSONObject jsonObj = null;
		try{
			jsonObj = new JSONObject(getclient(licser_service).path("TokenRequest").request().post(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ApiCallService : getTokenNo Ending");
		return jsonObj;
	} 
	
	
	 private WebTarget getclient(String serviceurl) {
		 	ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget service = client.target(getBaseURI(serviceurl)); 
		return service;
	}

	private static URI getBaseURI(String serviceurl) {  
		 return UriBuilder.fromUri(serviceurl).build();  
	 }

}
