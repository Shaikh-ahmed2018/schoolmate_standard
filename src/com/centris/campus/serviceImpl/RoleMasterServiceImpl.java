package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.RoleMasterDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.RoleMasterDaoImpl;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.RoleMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class RoleMasterServiceImpl implements RoleMasterService {
	private static Logger logger = Logger
			.getLogger(RoleMasterServiceImpl.class);

	RoleMasterDao masterDao = new RoleMasterDaoImpl();

	public String addRole(RoleMasterPojo pojo,UserLoggingsPojo userLoggingsVo)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :addRole Starting");
		String successMessage = null;
		try {
			
			pojo.setRoleCode(IDGenerator.getPrimaryKeyID("campus_role",userLoggingsVo));
			
			successMessage = masterDao.addRole(pojo,userLoggingsVo);
			
			// TODO Auto-generated method stub
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :addRole Ending");

		return successMessage;
	}

	

	@Override
	public List<RoleMasterPojo> getRoles(UserLoggingsPojo custdetails,String location_id) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :getRoles Starting");
		List<RoleMasterPojo> getRoleList = new ArrayList<RoleMasterPojo>();
		try {
			getRoleList = masterDao.getRoles(custdetails, location_id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :getRoles Ending");
		return getRoleList;
	}

	public String deleteRole(RoleMasterPojo roleMasterPojo,UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :deleteRole Starting");
		String successMessage = null;
		try {
			successMessage = masterDao.deleteRole(roleMasterPojo, userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :deleteRole Ending");
		return successMessage;
	}

	@Override
	public RoleMasterPojo updateRole1(String roleCodeId,UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :updateRoles Starting");
		RoleMasterPojo updateRoleList = new RoleMasterPojo();
		try {
			updateRoleList = masterDao.updateRole1(roleCodeId,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :updateRoles Ending");
		return updateRoleList;
	}

	@Override
	public boolean validateRoleName(String roleNameValidate,UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :validateRoleName Starting");
		boolean role_Name_Available = false;
		try {
			
			role_Name_Available = masterDao.validateRoleName(roleNameValidate, userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :validateRoleName Ending");
		return role_Name_Available;
	}

	public boolean validateRoleNameUpdate(String roleNameValidate, String roleid, UserLoggingsPojo userLoggingsVo)

	
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :validateRoleNameUpdate Starting");
		boolean role_Name_Available = false;
		try {
			role_Name_Available = masterDao.validateRoleNameUpdate(roleNameValidate,roleid,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :validateRoleNameUpdate Ending");
		return role_Name_Available;
	}

	
	
	@Override
	public List<RoleMasterPojo> searchRole(String searchterm,UserLoggingsPojo custdetails) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :searchRole Starting");
		List<RoleMasterPojo> getRoleList = new ArrayList<RoleMasterPojo>();
		try {
			getRoleList = masterDao.searchRole(searchterm,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :searchRole Ending");
		return getRoleList;
	}



	public String updateRole(RoleMasterPojo pojo, UserLoggingsPojo userLoggingsVo)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :updateRole Starting");
		String successMessage = null;
		try {
			successMessage = masterDao.updateRole(pojo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleMasterServiceImpl :updateRole Ending");
		return successMessage;
	}
	
}
