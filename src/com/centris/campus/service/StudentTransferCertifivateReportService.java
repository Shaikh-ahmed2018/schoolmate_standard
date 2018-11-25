package com.centris.campus.service;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ParentVO;

public interface StudentTransferCertifivateReportService {

	List<ClassPojo> getClassDetailsService(UserLoggingsPojo userLoggingsVo);

	List<SectionPojo> getSectionListService(String classname, String location, UserLoggingsPojo userLoggingsVo);

	List<ParentVO> getAllStudentNamesReportService(String sectionid);

	
}
