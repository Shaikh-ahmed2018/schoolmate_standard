package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ClassTeacherVo;

public interface ClassTeacherLsitService {

	ArrayList<ClassTeacherVo> getClassTeacherListService(UserLoggingsPojo custid);

	ClassTeacherVo editClassTeacherService(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo);

	String saveClassTeacherService(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo);

	boolean validateClassTeacherService(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<ClassTeacherVo> getSearchClassTeacherListService(String searchTerm, UserLoggingsPojo custid);

	ArrayList<ClassTeacherVo> getFilterdClassTeacherListBD(String accYear, String locationId, String classId,
			String sectionId, UserLoggingsPojo userLoggingsVo, String dep, String searchVal);
}
