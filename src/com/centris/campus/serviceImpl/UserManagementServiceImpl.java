package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.UserManagementDao;
import com.centris.campus.daoImpl.UserManagementDaoImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.service.UserManagementService;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementServiceImpl implements UserManagementService {


	public List<UserRecordVO> getUserRecordsService(UserLoggingsPojo custdetails) {
		
	    UserManagementDao userManagementDao = new UserManagementDaoImpl();
		
		return userManagementDao.getUserRecordsDao(custdetails);
	}


	public List<UserRecordVO> getSearchUserDetailsService(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
	
		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			return userManagementDao.getSearchUserDetailsDao(userManagementPojo,custdetails);
	}



	public UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {

		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
	 return userManagementDao.getUserDeatils(userManagementPojo, userLoggingsVo);
	
	}



	public String changePassword(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		
		   UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			 return userManagementDao.changePassword(userManagementPojo,userLoggingsVo);
	}



	public String blockUser(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
		
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
			
			 return userManagementDao.blockUser(userManagementPojo,custdetails);
	}


	@Override
	public String insertUserDetails(UserManagementPojo details,UserLoggingsPojo userLoggingsVo) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.insertUserDetails(details, userLoggingsVo);
	}


	@Override
	public List<UserRecordVO> getUserDetails(UserLoggingsPojo pojo) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.getUserDetails(pojo);
	}


	@Override
	public UserRecordVO getUserDetailsforEdit(UserRecordVO details,UserLoggingsPojo userLoggingsVo) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.getUserDetailsforEdit(details,userLoggingsVo);
	}


	@Override
	public String blockUserByStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.blockUserByStatus(userManagementPojo,userLoggingsVo);
	}


	@Override
	public String blockUserStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo custdetails) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.blockUserStatus(userManagementPojo, custdetails);
	}


	@Override
	public List<UserRecordVO> userListSearch(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo) {
		UserManagementDao userManagementDao = new UserManagementDaoImpl();
		return userManagementDao.userListSearch(userManagementPojo, userLoggingsVo);
	}

}
