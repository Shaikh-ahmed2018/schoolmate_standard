package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ParentVO;

public interface StudentTransferCertifivateReportDao {

	List<ClassPojo> getClassDetailsDao(UserLoggingsPojo userLoggingsVo);

	List<SectionPojo> getSectionListDao(String classname, String location, UserLoggingsPojo userLoggingsVo);

	List<ParentVO> getAllStudentNamesReportDao(String sectionid);

}
