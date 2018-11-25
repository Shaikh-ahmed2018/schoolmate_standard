package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TeacherService;
import com.centris.campus.serviceImpl.TeacherServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.TeacherMappingClassesVo;

public class TeacherRegistrationBD {
	TeacherService serviceobj = new TeacherServiceImpl();


	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails(UserLoggingsPojo custdetails, AllTeacherDetailsVo obj) {
		return serviceobj.getAllTeacherDetails(custdetails,obj);

	}
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1(UserLoggingsPojo custdetails) {
		return serviceobj.getAllTeacherDetails1(custdetails);
	}
	
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.getSearchTeacherDetails(serchName,userLoggingsVo);
	}

	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(AllTeacherDetailsVo obj, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.searchStaffDetails(obj,userLoggingsVo);
	}

	public boolean deleteStaffDetails(String[] teachercode, TeacherForm obj, String button, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.deleteStaffDetails(teachercode,obj,button,userLoggingsVo);
	}

	public boolean teacherregister(TeacherForm obj, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.teacherregister(obj,userLoggingsVo);
	}

	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.getTeacherDetails(obj,userLoggingsVo);

	}

	public String teacherUpdate(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {
		return serviceobj.teacherUpdate(obj,userLoggingsVo);
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID,UserLoggingsPojo userLoggingsVo) {
		return serviceobj.getMappingClasses(teacherID,userLoggingsVo);
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID,UserLoggingsPojo userLoggingsVo) {
		return serviceobj.getMappedSection(teacherID,classId,SectionID,userLoggingsVo);
	}
	
	
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID,UserLoggingsPojo userLoggingsVo) {
		return serviceobj.getMappingSubjects(teacherID,userLoggingsVo);
	}

	public ArrayList<AllTeacherDetailsVo> reportingToList(UserLoggingsPojo userLoggingsVo) {
		
		return serviceobj.reportingToList(userLoggingsVo);
	}

	public Object StudentAdmissionNumber(String academicYear, UserLoggingsPojo userLoggingsVo) {
		 
		return serviceobj.StudentAdmissionNumber(academicYear,userLoggingsVo);
	}

	public int checkStaffInTDS(String currentUser,UserLoggingsPojo userLoggingsVo) {
		 
		return serviceobj.checkStaffInTDS(currentUser,userLoggingsVo);
	}

	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetailsByLocIdAndDeptId(StaffAttendanceVo vo,UserLoggingsPojo userLoggingsVo) {
		 
		return serviceobj.getAllTeacherDetailsByLocIdAndDeptId(vo,userLoggingsVo);
	}


}
