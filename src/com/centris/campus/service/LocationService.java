package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.LocationVO;

public interface LocationService {

	List<LocationVO> getLocationDetails(UserLoggingsPojo custId);

	String insertLocationDetailsAction(LocationMasterForm form, String locationId, UserLoggingsPojo pojo);

	String validateLocName(LocationMasterForm form1,UserLoggingsPojo userLoggingsVo);

	String validateLocNameUpdate(LocationVO validateUpdate,UserLoggingsPojo userLoggingsVo);

	LocationVO editLocation(String edit, String addId, UserLoggingsPojo pojo);

	String  InactiveLocation(String[] locid,LocationVO locvo, UserLoggingsPojo pojo); 

	List<LocationVO> searchLocationDetails(String searchName,String status,UserLoggingsPojo userLoggingsVo);

	List<LocationVO> InActiveLocationList(LocationVO locvo, UserLoggingsPojo pojo);

	List<LocationVO> getLocationList(UserLoggingsPojo dbdetails);

	List<LocationVO> searchLocationList(String searchName, String status, UserLoggingsPojo dbdetails);

	ArrayList<LocationVO> getLocationAllDetails(String loaction_id, UserLoggingsPojo userLoggingsVo);

	List<LocationVO> SchoolList(LocationVO locvo, UserLoggingsPojo pojo);

	LocationVO editSchool(String locId, UserLoggingsPojo pojo);

	String insertLocationAddress(LocationMasterForm form1, String locationId, UserLoggingsPojo pojo);

}
