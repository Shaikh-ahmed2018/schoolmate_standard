package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ClassTeacherVo;

public interface ClassTeacherLsitDao {

	ArrayList<ClassTeacherVo> getClassTeacherListDao(UserLoggingsPojo custid);

	ClassTeacherVo editClassTeacherDao(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo);

	String saveClassTeacherDao(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo);

	boolean validateClassTeacherDao(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<ClassTeacherVo> getSearchClassTeacherListDao(String searchTerm, UserLoggingsPojo custid);

	ArrayList<ClassTeacherVo> getFilterdClassTeacherListBD(String accYear, String locationId, String classId,
			String sectionId, UserLoggingsPojo userLoggingsVo, String dep, String searchVal);

}
