package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.service.UserManagementService;
import com.centris.campus.serviceImpl.UserManagementServiceImpl;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementBD {

	public List<UserRecordVO> getUserRecordsBD(UserLoggingsPojo custdetails) {
		
		UserManagementService  	userManagementService = new  UserManagementServiceImpl();
		
		return userManagementService.getUserRecordsService(custdetails);
	}


	public List<UserRecordVO> getSearchUserDetailsBD(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails) {

	   UserManagementService  	userManagementService = new  UserManagementServiceImpl();
		
	 return userManagementService.getSearchUserDetailsService(userManagementPojo,custdetails);

	}


	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo) {
		
		  UserManagementService userManagementService = new  UserManagementServiceImpl();
			
		return userManagementService.getUserDeatils(userManagementPojo,userLoggingsVo);
	}


	public String changePassword(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo) {
		
		  UserManagementService userManagementService = new  UserManagementServiceImpl();
			
		 return userManagementService.changePassword(userManagementPojo,userLoggingsVo);
	}


	public String blockUser(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails) {
		
		  UserManagementService  	userManagementService = new  UserManagementServiceImpl();
			
			 return userManagementService.blockUser(userManagementPojo,custdetails);
	}


	public String insertUserDetails(UserManagementPojo details, UserLoggingsPojo userLoggingsVo) {
		  
		UserManagementService  	userManagementService = new  UserManagementServiceImpl();
		return userManagementService.insertUserDetails(details,userLoggingsVo);
	}


	public List<UserRecordVO> getUserDetails(UserLoggingsPojo pojo) {
		UserManagementService userManagementService = new  UserManagementServiceImpl();
		return userManagementService.getUserDetails(pojo);
	}


	public UserRecordVO getUserDetailsforEdit(UserRecordVO details, UserLoggingsPojo userLoggingsVo) {
		UserManagementService userManagementService = new  UserManagementServiceImpl();
		return userManagementService.getUserDetailsforEdit(details,userLoggingsVo);
	}


	public String blockUserByStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		UserManagementService userManagementService = new  UserManagementServiceImpl();
		return userManagementService.blockUserByStatus(userManagementPojo,userLoggingsVo);
	}


	public String blockUserStatus(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails) {
		UserManagementService userManagementService = new  UserManagementServiceImpl();
		return userManagementService.blockUserStatus(userManagementPojo,custdetails);
	}


	public List<UserRecordVO> userListSearch(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo) {
		UserManagementService userManagementService = new  UserManagementServiceImpl();
		return userManagementService.userListSearch(userManagementPojo,userLoggingsVo);
	}

	
}
