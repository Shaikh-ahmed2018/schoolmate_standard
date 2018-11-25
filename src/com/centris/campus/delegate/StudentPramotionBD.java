package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.StudentPromotionPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.StudentPramotionServiceImpl;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.ClassPromotionList;
import com.centris.campus.vo.StudentPramotionVO;

public class StudentPramotionBD {

	public List<AcadamicYearVO2> getAcadamicYear(UserLoggingsPojo userLoggingsVo) {
		List<AcadamicYearVO2> yeardetailslist = new ArrayList<AcadamicYearVO2>();
		yeardetailslist = new StudentPramotionServiceImpl().getAcadamicYear(userLoggingsVo);
		return yeardetailslist;
	}

	public List<StudentPramotionVO> getStudentData(String acadamicYear,
			String section,UserLoggingsPojo userLoggingsVo) {
		List<StudentPramotionVO> studentlist = new ArrayList<StudentPramotionVO>();
		studentlist = new StudentPramotionServiceImpl().getStudentData(
				acadamicYear,section,userLoggingsVo);
		return studentlist;
	}

	public StudentPramotionVO insertStudentPromotion(
			StudentPromotionPOJO studentPramotionPOJO,UserLoggingsPojo userLoggingsVo) {

		StudentPramotionVO result = new StudentPramotionServiceImpl()
				.insertStudentPromotion(studentPramotionPOJO,userLoggingsVo);

		return result;
	}

	public ArrayList<StudentPramotionVO> getpromotionslist(UserLoggingsPojo userLoggingsVo) {
		ArrayList<StudentPramotionVO> yeardetailslist = new ArrayList<StudentPramotionVO>();
		yeardetailslist = new StudentPramotionServiceImpl().getpromotionslist(userLoggingsVo);
		return yeardetailslist;
	}

	public ArrayList<StudentPramotionVO> getpromotionssearchlist(String className, String sectionName) {
		ArrayList<StudentPramotionVO> getpromotionssearchlist = new ArrayList<StudentPramotionVO>();
		getpromotionssearchlist=new StudentPramotionServiceImpl().getpromotionssearchlist(className,sectionName);
		return getpromotionssearchlist;
	}

	public List<ClassPromotionList> getClassListForPromotion(String currentYear, UserLoggingsPojo userLoggingsVo) {
		
		return new StudentPramotionServiceImpl().getClassListForPromotion(currentYear,userLoggingsVo);
	}

}
