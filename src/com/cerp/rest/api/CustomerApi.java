package com.cerp.rest.api;




import java.util.Date;
import java.util.ResourceBundle;


import javax.servlet.ServletConfig;
import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.cerp.rest.model.Productdetails;
import com.cerp.rest.model.Customer;
import com.cerp.rest.model.CustomerInfo;
import com.cerp.rest.model.DomainVerification;
import com.cerp.rest.service.CustomerApiService;

import com.cerp.rest.util.CustomerApiServiceFactory;
import com.cerp.rest.util.StreamConstants;

@Path("/customer")

public class CustomerApi{

	private static final Logger logger = Logger.getLogger(CustomerApi.class);

	private final CustomerApiService delegate;
	
	   public CustomerApi(@Context ServletConfig servletContext) {
	      CustomerApiService delegate = null;

	      if (servletContext != null) {
	         String implClass = servletContext.getInitParameter("CustomerApi.implementation");
	         if (implClass != null && !"".equals(implClass.trim())) {
	            try {
	               delegate = (CustomerApiService) Class.forName(implClass).newInstance();
	            } catch (Exception e) {
	               throw new RuntimeException(e);
	            }
	         } 
	      }

	      if (delegate == null) {
	         delegate = CustomerApiServiceFactory.getCustomerApi();
	      }

	      this.delegate = delegate;
	   }

	   	@POST
	   	@Path("/add")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response addCustomer(final Customer customerdetails,@Context final SecurityContext securityContext){
	   		
	   	 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi: addCustomer Starting");
			int custid = 0;
			try{
			
				custid = delegate.addCustomer(customerdetails,securityContext);
				   
			}
			catch(Exception e){
				e.printStackTrace();
			}
			      
	    	JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi : addCustomer Ending");
			
			 if(custid != 0 ){
				 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,"Customer Details Saved",custid)).build();
		      }
		      else{
		    	 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.ERROR,"Internal Server Error",0)).build();
		 	  } 
	    }
	   	
		@POST
	   	@Path("/verifyDomain")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response  verifySubDomain(String data,@Context final SecurityContext securityContext){
	   		
	   	 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi: verifySubDomain Starting");
			
			JSONObject outputJsonObj = new JSONObject();
			try{
				
				 JSONObject inputJsonObj = new JSONObject(data);
				 String input = (String) inputJsonObj.get("domainName"); 
				 System.out.println("input "+input);
				 DomainVerification subdomaindetails = delegate.verifySubDomain(input,securityContext);
				 
				 if(subdomaindetails.getStatus().equals(StreamConstants.SUCCESS)){
					 outputJsonObj.put("status", StreamConstants.SUCCESS);
					 outputJsonObj.put("message",StreamConstants.DOMAIN_FOUND);
					 /*outputJsonObj.put("customerId",  subdomaindetails.getCutomerrefno());
					 outputJsonObj.put("icenseStatus", subdomaindetails.getLicstatus());*/
				 }
				 else{
					 outputJsonObj.put("status", StreamConstants.FAIL);
					 outputJsonObj.put("message",StreamConstants.DOMAIN_NOT_FOUND);
				 }
			}
			catch(Exception e){
				e.printStackTrace();
			}
			      
	    	JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi : verifySubDomain Ending");
			
			return Response.status(201).entity(outputJsonObj.toString()).build();
			
	    }
	   	
		@POST
	   	@Path("/verifyCustomer")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response verifyCustomer(CustomerInfo customer,@Context final SecurityContext securityContext){
	   		
	   	 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi: verifyCustomer Starting");
			CustomerInfo details = null;
			try{
				details = (CustomerInfo) delegate.validateCustomer(customer.getAppusername(),customer.getApppwd(),customer.getDomain());
				System.out.println(details.getStatus());
			}
			catch(Exception e){
				e.printStackTrace();
			}
			      
	    	JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in CustomerApi : verifyCustomer Ending");
			
			if(details.getStatus().equalsIgnoreCase("licvalid")){
				System.out.println("hello");
				 return Response.ok().entity(new CustoInfoResponse(ApiResponseMessage.OK,"Valid User",details)).build();
			}else{
				 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.NOTFOUND,details.getStatus(),0)).build();
			}
			
	    }
}
