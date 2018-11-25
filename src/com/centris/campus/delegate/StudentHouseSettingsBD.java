package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentHouseSettingsService;
import com.centris.campus.serviceImpl.StudentHouseSettingsServiceImpl;
import com.centris.campus.vo.StudentHouseSettingsVO;

public class StudentHouseSettingsBD {

	static StudentHouseSettingsService service;
	static{
		service = new StudentHouseSettingsServiceImpl();
	}
	public static String savehouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails) {
		
		return service.savehouseSettings(pojo,custdetails);
	}
	public ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid, String accid, UserLoggingsPojo custdetails) {
		return service.gethouseSettings(locid,accid,custdetails);
	}
	public static String edithouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails) {
		return service.edithouseSettings(pojo,custdetails);
	}
	public static String deletehouseSettings(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails) {
		return service.deletehouseSettings(pojo,custdetails);
	}
	public static String checkduplicateHouse(StudentHouseSettingsPOJO pojo, UserLoggingsPojo custdetails) {
		return service.checkduplicateHouse(pojo,custdetails);
	}
	public int gettotalstudentcount(String accid, String locid, UserLoggingsPojo custdetails) {
		return service.gettotalstudentcount(accid,locid,custdetails);
	}
	public int gettotalallostudent(String accid, String locid, UserLoggingsPojo custdetails) {
		return service.gettotalallostudent(accid,locid,custdetails);
	}
	public ArrayList<StudentHouseSettingsVO> getclassdetails(String locid, String accid, UserLoggingsPojo custdetails) {
		return service.getclassdetails(locid,accid,custdetails);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid, String classname, String accid, String filter1,
			UserLoggingsPojo userLoggingsVo) {
		return service.getHouseSettingStudentWise(locid,classid,classname,accid,filter1,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.generateHousing(pojo,userLoggingsVo);
	}
	public String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid, String log_audit_session, UserLoggingsPojo custdetails) {
		return service.savegenerateHouseDetails(pojo,houseid,sectionid,studid,log_audit_session,custdetails);
	}
	public String checkHousing(String accyear, String locid, UserLoggingsPojo custdetails) {
		return service.checkHousing(accyear,locid,custdetails);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1, UserLoggingsPojo userLoggingsVo) {
		return service.displayHouseSettingStudentWise(pojo,classname,filter1,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo) {
		return service.houseadmiWise(pojo);
	}
	public ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.generateaddmiHousing(pojo,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid, UserLoggingsPojo userLoggingsVo) {
		return service.getHouseSettingAdminoWise(locid,classid,classname,accid,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.byadminodescHousing(pojo,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid,
			String classname, String accid, UserLoggingsPojo userLoggingsVo) {
		return service.getHouseSettingAdminodescWise(locid,classid,classname,accid,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.byadminoevenHousing(pojo,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid,
			String classname, String accid, String filter1, UserLoggingsPojo userLoggingsVo) {
		return service.getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> byadminoddHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.byadminooddHousing(pojo,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo, UserLoggingsPojo userLoggingsVo) {
		return service.bystudentdescHousing(pojo,userLoggingsVo);
	}
	public String checkselection(String accyear, String locid, UserLoggingsPojo custdetails) {
		return service.checkselection(accyear,locid,custdetails);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1, UserLoggingsPojo userLoggingsVo) {
		return service.displayHouseSettingAdminoWise(pojo,classname,filter1,userLoggingsVo);
	}
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo,
			String classname, String filter1, UserLoggingsPojo userLoggingsVo) {
		return service.displayHouseSettingAdminoEven(pojo,classname,filter1,userLoggingsVo);
	}
	public String regenerateHousedetails(String accyear, String locid, String genhouid,UserLoggingsPojo userLoggingsVo) {
		return service.regenerateHousedetails(accyear,locid,genhouid, userLoggingsVo);
	}
	
}
