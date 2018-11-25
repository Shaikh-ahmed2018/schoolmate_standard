package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffServiceReportService;
import com.centris.campus.serviceImpl.StaffServiceReportServiceImpl;
import com.centris.campus.vo.PayCertificateReportVO;
import com.centris.campus.vo.ReportMenuVo;

public class StaffServiceReportBD {

	
	StaffServiceReportService service = new StaffServiceReportServiceImpl();

	public ArrayList<TeacherRegistrationPojo> getTeacherListDetails(UserLoggingsPojo userLoggingsVo) {
	
		return service. getTeacherListDetailsService(userLoggingsVo);
	}

	public ArrayList<TeacherRegistrationPojo> getTeacherDetailReport(
			TeacherRegistrationPojo teacherpojo) {
		
		return service. getTeacherDetailReportService(teacherpojo);
	}

	public ArrayList<PayCertificateReportVO> getTeacherPayDetails(String accyear, String month, String teachername) {
		
		return service.getTeacherPayDetailsReportService(accyear,month,teachername);
	}

	public ArrayList<TeacherRegistrationPojo> getTeacherListDetailsbyLocations(String locid, UserLoggingsPojo custdetails) {
		
		return service.getTeacherListDetailsbyLocations(locid,custdetails);
	}

	
	
	
	
	
	
	
	//------- veda : Class - Specialization Maping (submodule:3)
	
	
	
	
	
	
	
	
	
	
}
