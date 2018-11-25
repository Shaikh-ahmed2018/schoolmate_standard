package com.cerp.rest.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Level;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.centris.campus.admin.SendSMS;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UserLoggingsVo;
import com.cerp.rest.dao.CustomerDaoImpl;

public class Main {

	static File file = new File("./src/com/centris/campus/properties/CAMPUS.properties");
	static Properties props = new Properties(); 
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	static final String JDBC_DRIVER = res.getString("JDBC_DRIVER");  
	static final String DB_URL = res.getString("protocal")+"://"+"localhost"+"/";
	static final String USER = res.getString("username");
	static final String PASS = res.getString("password");
/*	static final String PUSERNAME = res.getString("pusername"); 
	static final String PPWD = res.getString("ppassword"); */
	
	
	public static void main(String[] args) throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI("http://localhost:8080/schoolmate/services/customer/verifyCustomer"));  
		
		JSONObject jsoninput = new JSONObject();
		
		jsoninput.put("appusername", "admin");
		jsoninput.put("apppwd","admin");
		jsoninput.put("domain","localhost");
		
		System.out.println(jsoninput);
		
		JSONObject jsonObj = new JSONObject(getclient("http://localhost:8080/schoolmate/services/customer/verifyCustomer").request().post(Entity.entity(jsoninput.toString(),MediaType.APPLICATION_JSON),String.class));
		System.out.println(jsonObj);
		
			if(jsonObj.getInt("code") == 201){
				
				UserLoggingsPojo object = JSON.parseObject(jsonObj.getJSONObject("customerinfo").toString(),UserLoggingsPojo.class); 
				
			}else if(jsonObj.getInt("code") == 400){
				
			}
			
		}
	private static URI getBaseURI(String serviceurl) {  
		 return UriBuilder.fromUri(serviceurl).build();  
	} 
	private static WebTarget getclient(String validateuser) {
	 	ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI(validateuser)); 
	return service;
}
}

