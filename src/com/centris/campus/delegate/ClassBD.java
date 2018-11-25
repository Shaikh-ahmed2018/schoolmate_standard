package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassService;
import com.centris.campus.serviceImpl.ClassServiceImpl;

public class ClassBD {
	
	public List<ClassPojo> getClassDetails(String schoolLocation, UserLoggingsPojo custdetails) {
		ClassService classService=new ClassServiceImpl();
		List<ClassPojo> classList=classService.getClassDetails(schoolLocation,custdetails);
		return classList;
	}
	
	public String createClass(ClassPojo classPojo,String createUser, String classcode, UserLoggingsPojo userLoggingsVo) {
		ClassService classService=new ClassServiceImpl();
		return classService.createClass(classPojo,createUser,classcode,userLoggingsVo);
	}
	
	public List<ClassPojo> getStreamDetailBD(String school, UserLoggingsPojo userLoggingsVo) {
		ClassService classService=new ClassServiceImpl();
		List<ClassPojo> classList=classService.getStreamDetailService(school,userLoggingsVo);
		return classList;
	}
	
	public String classNameCheck(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo) {
		ClassService classService=new ClassServiceImpl();
		return classService.classNameCheck(classPojo,userLoggingsVo);
	}
	
	public String updateclassNameCheck(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo) {
		ClassService classService=new ClassServiceImpl();
		return classService.updateclassNameCheck(classPojo,userLoggingsVo);
	}
	
	public ClassPojo editClass(String classCode, String locId, UserLoggingsPojo dbdetails) {
		ClassService classService=new ClassServiceImpl();
		return classService.editClass(classCode,locId,dbdetails);
	}
	
	public boolean deleteClass(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo) {
		ClassService classService=new ClassServiceImpl();
		return classService.deleteClass(classPojo,userLoggingsVo);
	}
	
	public boolean updateClass(ClassPojo classPojo) {
		ClassService classService=new ClassServiceImpl();
		return classService.updateClass(classPojo);
	}
	
	public List<ClassPojo> searchClassDetails(String searchText, UserLoggingsPojo custdetails) {
		ClassService classService=new ClassServiceImpl();
		return classService.searchClassDetails(searchText,custdetails);
	}

	public List<ClassPojo> getClassDetailsOnChangeFunction(String streamId,String locationid, String status, UserLoggingsPojo custdetails) {
		return ClassServiceImpl.getClassDetailsOnChangeFunction( streamId, locationid,status,custdetails);
		
	}



	

}
