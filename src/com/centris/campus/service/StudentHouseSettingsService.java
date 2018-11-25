package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StudentHouseSettingsVO;

public interface StudentHouseSettingsService {

	String savehouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails);

	ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid, String accid, UserLoggingsPojo custdetails);

	String edithouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails);

	String deletehouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails);

	String checkduplicateHouse(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails);

	int gettotalstudentcount(String accid, String locid, UserLoggingsPojo custdetails);

	int gettotalallostudent(String accid, String locid, UserLoggingsPojo custdetails);

	ArrayList<StudentHouseSettingsVO> getclassdetails(String locid, String accid, UserLoggingsPojo custdetails);

	ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid, String classname, String accid, String filter1, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid, String log_audit_session, UserLoggingsPojo custdetails);

	String checkHousing(String accyear, String locid, UserLoggingsPojo custdetails);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo, String classname, String filter1, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo);

	ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid, String classname,
			String accid, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid, String classname,
			String accid, String filter1, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> byadminooddHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo);

	String checkselection(String accyear, String locid, UserLoggingsPojo custdetails);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo, String classname,
			String filter1, UserLoggingsPojo userLoggingsVo);

	ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo, String classname,
			String filter1, UserLoggingsPojo userLoggingsVo);

	String regenerateHousedetails(String accyear, String locid, String genhouid,UserLoggingsPojo userLoggingsVo);

}
