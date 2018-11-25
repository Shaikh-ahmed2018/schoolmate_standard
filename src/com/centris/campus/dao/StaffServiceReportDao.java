package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PayCertificateReportVO;

public interface StaffServiceReportDao {

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsDao(UserLoggingsPojo userLoggingsVo);

	ArrayList<TeacherRegistrationPojo> getTeacherDetailReportDao(
			TeacherRegistrationPojo teacherpojo);

	
	ArrayList<PayCertificateReportVO> getTeacherPayDetailsReportDao(
			String accyear, String month, String teachername);

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsbyLocations(String locid, UserLoggingsPojo custdetails);

}
