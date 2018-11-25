package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassTeacherLsitService;
import com.centris.campus.serviceImpl.ClassTeacherLsitServiceImpl;
import com.centris.campus.vo.ClassTeacherVo;
import com.centris.campus.vo.TeacherVo;

public class ClassTeacherLsisBD {

	
	ClassTeacherLsitService servise = new ClassTeacherLsitServiceImpl();
	
	
	
	public ArrayList<ClassTeacherVo> getClassTeacherListBD(UserLoggingsPojo custid) {
		
		return servise.getClassTeacherListService(custid);
	}



	public ClassTeacherVo editClassTeacherBD(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		
		return servise. editClassTeacherService(vo,userLoggingsVo);
	}



	public String saveClassTeacherBD(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo) {
		
		return servise.saveClassTeacherService(vo,userLoggingsVo);
	}



	public boolean validateClassTeacherBD(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo) {
		
		return servise.validateClassTeacherService(vo,userLoggingsVo);
	}



	public ArrayList<ClassTeacherVo> getSearchClassTeacherListBD(
			String searchTerm, UserLoggingsPojo custid) {
		
		return servise. getSearchClassTeacherListService(searchTerm,custid);
	}



	public ArrayList<ClassTeacherVo> getFilterdClassTeacherListBD(String accYear, String locationId, String classId,
			String sectionId, UserLoggingsPojo userLoggingsVo, String dep, String searchVal) {
		return servise. getFilterdClassTeacherListBD(accYear,locationId,classId,sectionId,userLoggingsVo,dep,searchVal);
	}



	

}
