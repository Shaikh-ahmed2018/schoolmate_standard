package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.TeacherMappingClassesVo;

public interface TeacherService {
     

	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails(UserLoggingsPojo custdetails, AllTeacherDetailsVo obj);

	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1(UserLoggingsPojo custdetails);
	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(AllTeacherDetailsVo obj, UserLoggingsPojo userLoggingsVo);
	public boolean deleteStaffDetails(String[] teachercode, TeacherForm obj, String button, UserLoggingsPojo userLoggingsVo);
	public boolean teacherregister(TeacherForm obj, UserLoggingsPojo userLoggingsVo);
	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo);
	public String teacherUpdate(TeacherRegistrationPojo obj,UserLoggingsPojo userLoggingsVo);
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID,UserLoggingsPojo userLoggingsVo);
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID,UserLoggingsPojo userLoggingsVo);
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID, UserLoggingsPojo userLoggingsVo);
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName, UserLoggingsPojo userLoggingsVo);
	public ArrayList<AllTeacherDetailsVo> reportingToList(UserLoggingsPojo userLoggingsVo);
	public Object StudentAdmissionNumber(String academicYear, UserLoggingsPojo userLoggingsVo);
	public int checkStaffInTDS(String currentUser,UserLoggingsPojo userLoggingsVo);
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetailsByLocIdAndDeptId(StaffAttendanceVo vo,UserLoggingsPojo userLoggingsVo);
	
}
