package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import com.centris.campus.dao.LocationDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.LocationDAOImpl;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LocationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;

public class LocationServiceImpl implements LocationService {

	private static Logger logger = Logger
			.getLogger(LocationServiceImpl.class);
	static LocationDao locationMasterDao;
	static{
		locationMasterDao = new LocationDAOImpl();
	}
	
	@Override

	public List<LocationVO> getLocationDetails(UserLoggingsPojo userLoggingsVo) {
		return locationMasterDao.getLocationDetails(userLoggingsVo);
	}

	@Override
	public String insertLocationDetailsAction(LocationMasterForm form,String idupdate, UserLoggingsPojo pojo) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationServiceImpl: insertLocationDetailsAction: Starting");

		String result = null;

		try {
			LocationMasterPojo locationpojo = new LocationMasterPojo();
				

			locationpojo.setLocId(form.getLoc_id());
			locationpojo.setLocationname(form.getLocationname());
			locationpojo.setCreatedBy(form.getCreatedBy());
					
			locationpojo.setLog_audit_session(form.getLog_audit_session());
			locationpojo.setAddress(form.getAddress());
			locationpojo.setWebsite(form.getWebsite());
			locationpojo.setEmailId(form.getEmailId());
			locationpojo.setSchoollogo(form.getSchoollogoFilePath());
			locationpojo.setCustomerId(form.getCustomerId());
			locationpojo.setAddress2(form.getAddress2());
			locationpojo.setCountry(form.getCountry());
			locationpojo.setState(form.getState());
			locationpojo.setCity(form.getCity());
			locationpojo.setPincode(form.getPincode());
			locationpojo.setCperson(form.getCperson());
			locationpojo.setCemailId(form.getCemailId());
			locationpojo.setClandline(form.getClandline());
			locationpojo.setAffilno(form.getAffilno());
			locationpojo.setCmobileno(form.getCmobileno());
			
			result = locationMasterDao.insertLocationDetailsAction(locationpojo,idupdate,pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationServiceImpl : insertLocationDetailsAction : Ending");
		return result;
	}

	@Override
	public String validateLocName(LocationMasterForm form1,UserLoggingsPojo userLoggingsVo) {
		return locationMasterDao.validateLocName(form1,userLoggingsVo);
	}

	@Override
	public String validateLocNameUpdate(LocationVO validateUpdate,UserLoggingsPojo userLoggingsVo) {
		return locationMasterDao.validateLocNameUpdate(validateUpdate,userLoggingsVo);
	}

	@Override
	public LocationVO editLocation(String edit,String addId,UserLoggingsPojo pojo) {
		return locationMasterDao.editLocation(edit,addId,pojo);
	}

	@Override
	public String  InactiveLocation(String[] locid,LocationVO locvo,UserLoggingsPojo pojo) {
		return locationMasterDao.InactiveLocation(locid,locvo, pojo);
	}

	@Override
	public List<LocationVO> searchLocationDetails(String searchName,String status,UserLoggingsPojo userLoggingsVo) {
		return locationMasterDao.searchLocationDetails(searchName,status,userLoggingsVo);
	}

	@Override
	public List<LocationVO> InActiveLocationList(LocationVO locvo,UserLoggingsPojo userLoggingsVo) {
		return locationMasterDao.InActiveLocationList(locvo,userLoggingsVo);
	}

	@Override
	public List<LocationVO> getLocationList(UserLoggingsPojo dbdetails) {
		return locationMasterDao.getLocationList(dbdetails);
	}

	@Override
	public List<LocationVO> searchLocationList(String searchName, String status, UserLoggingsPojo dbdetails) {
		return locationMasterDao.searchLocationList(searchName,status,dbdetails);
	}

	@Override
	public ArrayList<LocationVO> getLocationAllDetails(String loaction_id, UserLoggingsPojo userLoggingsVo) {
		
		return locationMasterDao.getLocationAllDetails( loaction_id,   userLoggingsVo);
	}

	@Override
	public List<LocationVO> SchoolList(LocationVO locvo, UserLoggingsPojo pojo) {
		return locationMasterDao.SchoolList(locvo,pojo);
	}

	@Override
	public LocationVO editSchool(String locId, UserLoggingsPojo pojo) {
		return locationMasterDao.editSchool(locId,pojo);
	}

	@Override
	public String insertLocationAddress(LocationMasterForm form, String locationId, UserLoggingsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationServiceImpl: insertLocationDetailsAction: Starting");

		String result = null;

		try {
			LocationMasterPojo locationpojo = new LocationMasterPojo();
			String s1 = IDGenerator.getPrimaryKeyID("campus_location",pojo);

			locationpojo.setLocId(form.getLoc_id());
			locationpojo.setLocationname(form.getLocationname());
			locationpojo.setLocAddId(form.getLocAddId());
			locationpojo.setCreatedBy(form.getCreatedBy());
			locationpojo.setLocation_id(s1);
			locationpojo.setLog_audit_session(form.getLog_audit_session());
			
			locationpojo.setBoard(form.getBoard());
			locationpojo.setAddress(form.getAddress());
			locationpojo.setAffilno(form.getAffilno());
			locationpojo.setSchoolcode(form.getSchoolcode());
			locationpojo.setWebsite(form.getWebsite());
			locationpojo.setEmailId(form.getEmailId());
			
			locationpojo.setBoardlogo(form.getBoardlogoFilePath());
			locationpojo.setCustomerId(form.getCustomerId());
			
			locationpojo.setContactno(form.getContactno());
			locationpojo.setLandlineno(form.getLandlineno());
			locationpojo.setAddress2(form.getAddress2());
			locationpojo.setCountry(form.getCountry());
			locationpojo.setState(form.getState());
			locationpojo.setCity(form.getCity());
			locationpojo.setPincode(form.getPincode());
			locationpojo.setHiddenaddId(form.getHiddenaddId());
			locationpojo.setHiddenlocaddressId(form.getHiddenlocaddressId());
			
			
			
			result = locationMasterDao.insertLocationAddress(locationpojo,locationId,pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationServiceImpl : insertLocationDetailsAction : Ending");
		return result;
	}
	
}
