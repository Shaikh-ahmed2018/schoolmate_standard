package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.centris.campus.daoImpl.ClassTeacherMappingDAOIMPL;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassTeacherMappingService;
import com.centris.campus.serviceImpl.ClassTeacherMappingServiceIMPL;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.classVo;

public class ClassTeacherMappingDelegate {
	
	ClassTeacherMappingService obj_map = new ClassTeacherMappingServiceIMPL();

	public ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo) {
		
		return obj_map.getclass(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo) {
		
		return obj_map.getsection(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo) {
		
		return obj_map.getteacher(vo);
	}

	public String addmappingdetails(ClassTeacherMappingVO vo) {
		
		return obj_map.addmappingdetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo) {
		
		return obj_map.teachermapping(vo);
	}

	public LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
		
		return obj_map.getclassdetails(vo,userLoggingsVo);
	}

	public ArrayList<ClassTeacherMappingVO> editclassdetails(
			ClassTeacherMappingVO vo) {
		
		return obj_map.editclassdetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getclassupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getclassupdate(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupclasslist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupclasslist(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getsectionupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getsectionupdate(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupdateteacherlist(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getupteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupteacherlist(vo);
	}

	public String deletemappingDetails(ClassTeacherMappingVO vo) {
		
		return obj_map.deletemappingDetails(vo);
	}

	public ArrayList<ClassTeacherMappingVO> getDownloadDetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
		
		return obj_map.getDownloadDetails(vo,userLoggingsVo);
	}

	
	
	
	// ------ submoldule 3 : in staff

	public ArrayList<TeacherVo> getTeacherByLoc(UserLoggingsPojo custdetails, String locId, String accId) {
		return new ClassTeacherMappingServiceIMPL().getTeacherByLoc(custdetails,locId,accId);
	}

	public String saveTeacherClassMapping(classVo vo, UserLoggingsPojo custdetails, HttpServletRequest request) {
		return new ClassTeacherMappingServiceIMPL().saveTeacherClassMapping(vo,custdetails,request);
	}
	

	public String checkTeacherStatus(classVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingServiceIMPL().checkTeacherStatus(vo,custdetails);
	}

	public List<TeacherVo> getAccyearLocationList(UserLoggingsPojo custdetails, String accYear, String loc) {
		return new ClassTeacherMappingServiceIMPL().getAccyearLocationList(custdetails,accYear,loc);
	}

	public List<classVo> displayClassTeacherList(UserLoggingsPojo custdetails, classVo vo) {
		return new ClassTeacherMappingServiceIMPL().displayClassTeacherList(custdetails,vo);
	}


	public String deleteClassTeacherMap(UserLoggingsPojo custdetails, String id) {
		return new ClassTeacherMappingServiceIMPL().deleteClassTeacherMap(custdetails,id);
	}

	public List<TeacherVo> SubjectwiseTeacherList(TeacherVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingServiceIMPL().SubjectwiseTeacherList(vo,custdetails);
	}

	public List<TeacherVo> getAllSubject(TeacherVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingServiceIMPL().getAllSubject(vo,custdetails);
	}

	public String saveTeacherSubjectMapInfo(TeacherVo vo, UserLoggingsPojo custdetails, String classSecSpecIds) {
		return new ClassTeacherMappingServiceIMPL().saveTeacherSubjectMapInfo(vo,custdetails,classSecSpecIds);
	}

	public List<classVo> getSubjectTeacherList(UserLoggingsPojo custdetails, TeacherVo vo) {
		return new ClassTeacherMappingServiceIMPL().getSubjectTeacherList(custdetails,vo);
	}

	public String validateClassSectionSpec(UserLoggingsPojo custdetails, classVo vo) {
		return new ClassTeacherMappingServiceIMPL().validateClassSectionSpec(custdetails,vo);
	}

	


}
