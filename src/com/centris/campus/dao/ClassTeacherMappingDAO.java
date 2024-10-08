package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.RemainderMasterVO;

public interface ClassTeacherMappingDAO {

	ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo);

	String addmappingdetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo);

	LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo);

	ArrayList<ClassTeacherMappingVO> editclassdetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getclassupdate(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupclasslist(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getsectionupdate(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getupteacherlist(ClassTeacherMappingVO vo);

	String updatemappingDetails(ClassTeacherMappingVO vo);

	String deletemappingDetails(ClassTeacherMappingVO vo);

	ArrayList<ClassTeacherMappingVO> getDownloadDetails(ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo);

}
