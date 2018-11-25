package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.LocationVO;

public interface LocationDao {


	List<LocationVO> getLocationDetails(UserLoggingsPojo userLoggingsVo);

	String insertLocationDetailsAction(LocationMasterPojo locationpojo, String idupdate, UserLoggingsPojo pojo);

	String validateLocName(LocationMasterForm form1,UserLoggingsPojo userLoggingsVo);

	String validateLocNameUpdate(LocationVO validateUpdate,UserLoggingsPojo userLoggingsVo);

	LocationVO editLocation(String edit, String addId, UserLoggingsPojo pojo);

	String InactiveLocation(String[] locid,LocationVO locvo, UserLoggingsPojo pojo);

	List<LocationVO> searchLocationDetails(String searchName,String status,UserLoggingsPojo pojo);

	List<LocationVO> InActiveLocationList(LocationVO locvo, UserLoggingsPojo userLoggingsVo);

	List<LocationVO> getLocationList(UserLoggingsPojo dbdetails);

	List<LocationVO> searchLocationList(String searchName, String status, UserLoggingsPojo dbdetails);

	ArrayList<LocationVO> getLocationAllDetails(String loaction_id,UserLoggingsPojo userLoggingsPojo);

	String getLocationCount(UserLoggingsPojo dbdetails);

	List<LocationVO> SchoolList(LocationVO locvo, UserLoggingsPojo pojo);

	LocationVO editSchool(String locId, UserLoggingsPojo pojo);

	String insertLocationAddress(LocationMasterPojo locationpojo, String locationId, UserLoggingsPojo pojo);

}
