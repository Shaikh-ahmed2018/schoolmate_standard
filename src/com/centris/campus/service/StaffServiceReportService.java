package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PayCertificateReportVO;

public interface StaffServiceReportService {

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsService(UserLoggingsPojo userLoggingsVo);

	ArrayList<TeacherRegistrationPojo> getTeacherDetailReportService(TeacherRegistrationPojo teacherpojo);

	ArrayList<PayCertificateReportVO> getTeacherPayDetailsReportService(
			String accyear, String month, String teachername);

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsbyLocations(String locid, UserLoggingsPojo custdetails);
	
	
	

}
