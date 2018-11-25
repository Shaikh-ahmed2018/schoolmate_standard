package com.cerp.rest.serviceImpl;

import java.util.Date;
import java.util.ResourceBundle;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.cerp.rest.api.ApiResponseMessage;
import com.cerp.rest.dao.*;
import com.cerp.rest.model.Customer;
import com.cerp.rest.model.DomainVerification;
import com.cerp.rest.service.CustomerApiService;
import com.cerp.rest.util.CreateCustDB;
import com.cerp.rest.util.DomParserExample;
import com.cerp.rest.util.PleskApiClient;
import com.cerp.rest.util.StreamConstants;

public class CustomerApiServiceImpl extends CustomerApiService {
	
	private static final Logger logger = Logger.getLogger(CustomerApiServiceImpl.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	static final String PUSERNAME = res.getString("pusername"); 
	static final String PPWD = res.getString("ppassword"); 
	static final String DOMAIN_NAME = res.getString("domain_name");
	
	private static CustomerRepository repository;
	
	static{
		repository = new CustomerDaoImpl();
	}
	@Override
	public int addCustomer(Customer customerdetails,@Context SecurityContext securityContext) {
		
		logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApiServiceImpl: addCustomer Starting");
		
			int id = 0;
			try{
				//id = repository.verifyCustomer(customerdetails);
				id = repository.addCustomer(customerdetails);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerApiServiceImpl : addCustomer Ending");
		 
		 return id;
		 
	}
	
	public Customer getcustomerDetails(String custId) {
		
		logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApiServiceImpl: getcustomerDetails Starting");
		
			Customer custodetails = new Customer();
			try{
				custodetails = repository.getcustomerDetails(custId);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerApiServiceImpl : getcustomerDetails Ending");
		 
		 return custodetails;
		 
	}

	@Override
	public Object validateCustomer(String userName, String password,String domainname) {
		
		logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApiServiceImpl: getcustomerDetails Starting");
		
			Object custodetails = new Customer();
			try{
				custodetails = repository.validateCustomer(userName,password,domainname);
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerApiServiceImpl : getcustomerDetails Ending");
		 
		 return custodetails;
		 
	}

	@Override
	public DomainVerification verifySubDomain(String input, SecurityContext securityContext) {
		
		logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApiServiceImpl: verifySubDomain Starting");
		
			DomainVerification verifysubdomain = new DomainVerification();
			PleskApiClient client = new PleskApiClient(DOMAIN_NAME);
	        client.setCredentials(PUSERNAME, PPWD);
			try{
				String url = input.trim()+"."+DOMAIN_NAME;
				String request = "<packet>"+
						 "<subdomain>"+
						 "<get>"+
						 "<filter>"+
						 "<name>"+url+"</name>"+
						 "</filter>"+
						 "</get>"+
						 "</subdomain>"+
						 "</packet>";
				String verify = client.request(request);
				System.out.println(verify);
				int statusid = DomParserExample.verifysubdoamindetails(verify);
				
				if(statusid!=0){
					//verifysubdomain = repository.verifySubDomain(statusid);
					verifysubdomain.setStatus(StreamConstants.SUCCESS);
					
				}
				else{
					verifysubdomain.setStatus(StreamConstants.FAIL);
					verifysubdomain.setMessgae(StreamConstants.DOMAIN_NOT_FOUND);
				}
					
					
			}catch(Exception e){
				e.printStackTrace();
			}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in CustomerApiServiceImpl : verifySubDomain Ending");
		 return verifysubdomain;
	}

}
