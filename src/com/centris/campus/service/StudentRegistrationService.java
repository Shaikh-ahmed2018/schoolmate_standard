package com.centris.campus.service;

import java.util.List;
import java.util.Map;

import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.registrationvo;

public interface StudentRegistrationService {
	public List<StudentRegistrationVo> getAcademicYear( UserLoggingsPojo userLoggingsVo) throws Exception;

	public List<StudentRegistrationVo> getStudentquota(UserLoggingsPojo pojo) throws Exception;

	public Map<String, String> saveStudentRegistration(
			StudentRegistrationForm studentRegistrationForm,UserLoggingsPojo dbdetails) throws Exception;

	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo);

	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo);

	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo, UserLoggingsPojo pojo);

	public String modifyStudentDetails(StudentRegistrationForm modifyform,UserLoggingsPojo dbdetails);

	public List<StudentRegistrationVo> getStudentCaste(UserLoggingsPojo userLoggingsVo);

	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type,UserLoggingsPojo dbdetails);

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation,UserLoggingsPojo pojo);

	public List<StudentRegistrationVo> getClassDetails(String catecory,String location,UserLoggingsPojo pojo);

	public List<StudentRegistrationVo> getSection(String classval,UserLoggingsPojo pojo);

	public List<StudentRegistrationVo> getConcessionDetails(UserLoggingsPojo userLoggingsVo);

	public List<TransportTypeVO> transportypedetails(UserLoggingsPojo pojo);

	public List<StageMasterVo> getStageDetails(UserLoggingsPojo pojo);

	public List<StudentRegistrationVo> getStudentDetails(String userType,String userCode,String acadamic_year,UserLoggingsPojo userLoggingsVo);

	public boolean deactivateStudent(StudentRegistrationVo registrationVo,UserLoggingsPojo pojo);
	
	public List<registrationvo> StudentDetails(String academic_year,String location,UserLoggingsPojo dbdetails) ;
	
	public List<StudentRegistrationVo> getStudentList(String academic_year, String location,UserLoggingsPojo userLoggingsVo);
	
	public List<StudentRegistrationVo> studentFullList(String studentId,String accYear, String locationId,UserLoggingsPojo userLoggingsVo);
	
	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear, UserLoggingsPojo pojo);
	
	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location, UserLoggingsPojo userLoggingsVo);
	
	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm);
	
	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear);
	
	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid,UserLoggingsPojo userLoggingsVo);
	
	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear,String classname);
	
	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname);
	
	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid,String housename,String status,String sortby,String orderbyAdmissionAndAlpha, String orderbyGender,UserLoggingsPojo custid);
}
