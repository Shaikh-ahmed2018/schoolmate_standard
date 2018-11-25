package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.StudentHouseSettingsDAO;
import com.centris.campus.daoImpl.StudentHouseSettingsDAOImpl;
import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentHouseSettingsService;
import com.centris.campus.vo.StudentHouseSettingsVO;

public class StudentHouseSettingsServiceImpl implements StudentHouseSettingsService {

	static StudentHouseSettingsDAO dao;
	static{
		dao = new StudentHouseSettingsDAOImpl();
	}
	
	@Override
	public String savehouseSettings(StudentHouseSettingsPOJO pojo,UserLoggingsPojo custdetails) {
		
		return dao.savehouseSettings(pojo,custdetails);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> gethouseSettings(String locid,String accid, UserLoggingsPojo custdetails) {
		return dao.gethouseSettings(locid,accid,custdetails);
	}

	@Override
	public String edithouseSettings(StudentHouseSettingsPOJO pojo,UserLoggingsPojo custdetails) {
		return dao.edithouseSettings(pojo,custdetails);
	}

	@Override
	public String deletehouseSettings(StudentHouseSettingsPOJO pojo,UserLoggingsPojo custdetails) {
		return dao.deletehouseSettings(pojo,custdetails);
	}

	@Override
	public String checkduplicateHouse(StudentHouseSettingsPOJO pojo,UserLoggingsPojo custdetails) {
		return dao.checkduplicateHouse(pojo,custdetails);
	}

	@Override
	public int gettotalstudentcount(String accyear,String locid,UserLoggingsPojo custdetails) {
		return dao.gettotalstudentcount(accyear,locid, custdetails);
	}

	@Override
	public int gettotalallostudent(String accyear,String locid,UserLoggingsPojo custdetails) {
		return dao.gettotalallostudent(accyear,locid, custdetails);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getclassdetails(String locid,String accid,UserLoggingsPojo custdetails) {
		return dao.getclassdetails(locid,accid, custdetails);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingStudentWise(String locid, String classid,
			String classname,String accid,String filter1,UserLoggingsPojo userLoggingsVo) {
		return dao.getHouseSettingStudentWise(locid,classid,classname,accid,filter1,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.generateHousing(pojo,userLoggingsVo);
	}

	@Override
	public String savegenerateHouseDetails(StudentHouseSettingsPOJO pojo, String[] houseid,
			String[] sectionid, String[] studid,String log_audit_session,UserLoggingsPojo custdetails) {
		return dao.savegenerateHouseDetails(pojo,houseid,sectionid,studid,log_audit_session, custdetails);
	}

	@Override
	public String checkHousing(String accyear, String locid,UserLoggingsPojo custdetails) {
		return dao.checkHousing(accyear,locid, custdetails);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingStudentWise(StudentHouseSettingsPOJO pojo,
			String classname,String filter1,UserLoggingsPojo userLoggingsVo) {
		return dao.displayHouseSettingStudentWise(pojo,classname,filter1,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> houseadmiWise(StudentHouseSettingsPOJO pojo) {
		return dao.houseadmiWise(pojo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> generateaddmiHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.generateaddmiHousing(pojo,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoWise(String locid, String classid, String classname,
			String accid, UserLoggingsPojo userLoggingsVo) {
		return dao.getHouseSettingAdminoWise(locid,classid,classname,accid,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminodescHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.byadminodescHousing(pojo,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminodescWise(String locid, String classid,
			String classname, String accid,UserLoggingsPojo userLoggingsVo) {
		return dao.getHouseSettingAdminodescWise(locid,classid,classname,accid,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminoevenHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.byadminoevenHousing(pojo,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> getHouseSettingAdminoevenWise(String locid, String classid,
			String classname, String accid,String filter1,UserLoggingsPojo userLoggingsVo) {
		return dao.getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> byadminooddHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.byadminooddHousing(pojo, userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> bystudentdescHousing(StudentHouseSettingsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		return dao.bystudentdescHousing(pojo, userLoggingsVo);
	}

	@Override
	public String checkselection(String accyear, String locid,UserLoggingsPojo custdetails) {
		return dao.checkselection(accyear,locid, custdetails);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoWise(StudentHouseSettingsPOJO pojo,
			String classname, String filter1,UserLoggingsPojo userLoggingsVo) {
		return dao.displayHouseSettingAdminoWise(pojo,classname,filter1,userLoggingsVo);
	}

	@Override
	public ArrayList<StudentHouseSettingsVO> displayHouseSettingAdminoEven(StudentHouseSettingsPOJO pojo,
			String classname, String filter1,UserLoggingsPojo userLoggingsVo) {
		return dao.displayHouseSettingAdminoEven(pojo,classname,filter1,userLoggingsVo);
	}

	@Override
	public String regenerateHousedetails(String accyear, String locid, String genhouid,UserLoggingsPojo userLoggingsVo) {
		return dao.regenerateHousedetails(accyear,locid,genhouid,userLoggingsVo);
	}
}
