package com.cerp.rest.api;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.cerp.rest.model.User;
import com.cerp.rest.service.UserService;
import com.cerp.rest.util.UserApiServiceFactory;

@Path("/user")
public class UserApi {

	private static final Logger logger = Logger.getLogger(UserApi.class);
	
	private final UserService delegate;
	
	public UserApi(@Context ServletConfig servletContext) {
		UserService delegate = null;

	      if (servletContext != null) {
	         String implClass = servletContext.getInitParameter("UserApi.implementation");
	         if (implClass != null && !"".equals(implClass.trim())) {
	            try {
	               delegate = (UserService) Class.forName(implClass).newInstance();
	            } catch (Exception e) {
	               throw new RuntimeException(e);
	            }
	         } 
	      }

	      if (delegate == null) {
	         delegate = UserApiServiceFactory.getCustomerApi();
	      }

	      this.delegate = delegate;
	   }
	

   	@POST
   	@Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user,@Context SecurityContext securityContext){
		
   		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: addUser Starting");
   		Response response = null;
		try{
   			
			response = delegate.addUser(user,securityContext);
   			
   			
   		}catch (Exception e) {
			e.printStackTrace();
		}
   		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : addUser Ending");
		
		return response;
	}
	
	@PUT
   	@Path("/modify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response modifyUser(User user,@Context SecurityContext securityContext){
		
   		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: addUser Starting");
   		Response response = null;
		try{
   			
			response = delegate.modifyUser(user,securityContext);
   			
   		}catch (Exception e) {
			e.printStackTrace();
		}
   		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : addUser Ending");
		
		return response;
	}
   	
	@PUT
   	@Path("/block")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response blockUser(User user,@Context SecurityContext securityContext){
		
   		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: blockUser Starting");
   		Response response = null;
		try{
   			
			response = delegate.blockUser(user,securityContext);
   			
   		}catch (Exception e) {
			e.printStackTrace();
		}
   		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : blockUser Ending");
		
		return response;
	}
	
	@GET
   	@Path("/verify/{username}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response verifyUser(@PathParam("username") String username,@Context SecurityContext securityContext){
		
   		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: verifyUser Starting");
   		Response response = null;
   	
		try{
			 response = delegate.verifyUser(username,securityContext);
   			
   		}catch (Exception e) {
			e.printStackTrace();
		}
   		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : verifyUser Ending");
		
		return response;
	}
	
	@PUT
   	@Path("/changepwd")
    @Produces(MediaType.APPLICATION_JSON)
	public Response changePwd(User user,@Context SecurityContext securityContext){
		
   		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi: verifyUser Starting");
   		Response response = null;
   	
		try{
			 response = delegate.changePwd(user,securityContext);
   			
   		}catch (Exception e) {
			e.printStackTrace();
		}
   		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserApi : verifyUser Ending");
		
		return response;
	}
	
	
	
}
