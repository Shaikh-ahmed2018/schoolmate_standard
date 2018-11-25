package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentTransferCertifivateReportService;
import com.centris.campus.serviceImpl.StudentTransferCertifivateReportServiceImpl;
import com.centris.campus.vo.ParentVO;

public class StudentTransferCertifivateReportBD {

	
	StudentTransferCertifivateReportService service = new StudentTransferCertifivateReportServiceImpl();
	
	public List<ClassPojo> getClassDetails(UserLoggingsPojo userLoggingsVo) {
		
		return service.getClassDetailsService(userLoggingsVo);
	}

	public List<SectionPojo> getSectionList(String classname, String location, UserLoggingsPojo userLoggingsVo) {
		
		return service.getSectionListService(classname,location,userLoggingsVo);
	}

	public List<ParentVO> getAllStudentNamesReportBD(String sectionid) {

		return service.getAllStudentNamesReportService(sectionid);
	}

	

}
