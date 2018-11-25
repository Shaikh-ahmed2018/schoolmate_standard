package com.centris.campus.delegate;


import java.util.ArrayList;
import java.util.List;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LocationService;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.vo.LocationVO;

public class LocationBD {
	
	static LocationService locationbd ;
	static{
		locationbd = new LocationServiceImpl();
	}

	public List<LocationVO> getLocationDetails(UserLoggingsPojo userLoggingsVo) {
		return locationbd.getLocationDetails(userLoggingsVo);

	}
	public List<LocationVO> searchLocationDetails(String searchName, String status,UserLoggingsPojo userLoggingsVo) {
		return locationbd.searchLocationDetails(searchName,status,userLoggingsVo);
	}
	public String insertLocationDetailsAction(LocationMasterForm form, String locationId, UserLoggingsPojo pojo) {
		return locationbd.insertLocationDetailsAction(form,locationId,pojo);
	}
	public String validateLocName(LocationMasterForm form1,UserLoggingsPojo userLoggingsVo) {
		return locationbd.validateLocName(form1,userLoggingsVo);
	}
	
	public String validateLocNameUpdate(LocationVO validateUpdate,UserLoggingsPojo userLoggingsVo) {
		return locationbd.validateLocNameUpdate(validateUpdate,userLoggingsVo);
	}
	public LocationVO editLocation(String edit, String addId, UserLoggingsPojo pojo) {
		return locationbd.editLocation(edit,addId,pojo);
	}
	public String InactiveLocation(String[] locid,LocationVO locvo, UserLoggingsPojo pojo) {
		return locationbd.InactiveLocation(locid,locvo,pojo);
	}
	public List<LocationVO> InActiveLocationList(LocationVO locvo, UserLoggingsPojo pojo) {
		return locationbd.InActiveLocationList(locvo,pojo);
	}
	public List<LocationVO> getLocationList(UserLoggingsPojo dbdetails) {
		return locationbd.getLocationList(dbdetails);
	}
	public List<LocationVO> searchLocationList(String SearchName, String status, UserLoggingsPojo dbdetails) {
		return locationbd.searchLocationList(SearchName,status,dbdetails);
	}
	public ArrayList<LocationVO> getLocationAllDetails(String loaction_id, UserLoggingsPojo userLoggingsVo) {
		return locationbd.getLocationAllDetails( loaction_id,  userLoggingsVo) ;
	}
	public List<LocationVO> SchoolList(LocationVO locvo, UserLoggingsPojo pojo) {
		return locationbd.SchoolList(locvo, pojo); 
	}
	public LocationVO editSchool(String locId, UserLoggingsPojo pojo) {
		return locationbd.editSchool(locId, pojo); 
	}
	public String insertLocationAddress(LocationMasterForm form1, String locationId, UserLoggingsPojo pojo) {
		return locationbd.insertLocationAddress(form1,locationId, pojo); 
	}
}	

