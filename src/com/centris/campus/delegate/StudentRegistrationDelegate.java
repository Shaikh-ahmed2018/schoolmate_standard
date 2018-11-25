/**
 * 
 */
package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;

public class StudentRegistrationDelegate {
	public List<StudentRegistrationVo> getAcademicYear( UserLoggingsPojo userLoggingsVo) throws Exception {
		return new StudentRegistrationServiceImpl().getAcademicYear(userLoggingsVo);

	}

	public List<StudentRegistrationVo> getStudentquota(UserLoggingsPojo pojo) throws Exception {
		return new StudentRegistrationServiceImpl().getStudentquota(pojo);

	}

	public Map<String, String> saveStudentRegistration(StudentRegistrationForm studentRegistrationForm, UserLoggingsPojo dbdetails) throws Exception {
		return new StudentRegistrationServiceImpl().saveStudentRegistration(studentRegistrationForm,dbdetails);
	}

	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo studentSearch,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl()
				.studentSearch(studentSearch,userLoggingsVo);
	}

	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl()
				.searchStudentResult(registrationVo,userLoggingsVo);
	}

	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl()
				.studentSearchByParent(registrationVo,pojo);
	}

	public List<StudentRegistrationVo> getStudentCaste(UserLoggingsPojo userLoggingsVo) throws Exception {
		return new StudentRegistrationServiceImpl().getStudentCaste(userLoggingsVo);

	}

	public String modifyStudentDetails(StudentRegistrationForm registrationVo, UserLoggingsPojo dbdetails) {
		return new StudentRegistrationServiceImpl().modifyStudentDetails(registrationVo,dbdetails);
	}

	public int validateDuplicateData(StudentRegistrationForm formObj,
			String type, UserLoggingsPojo dbdetails) {
		return new StudentRegistrationServiceImpl().validateDuplicateData(
				formObj, type,dbdetails);
	}

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr, UserLoggingsPojo userLoggingsVo) {

		return new StudentRegistrationServiceImpl().getAllStudentsDetails(
				classname, accodamicyr,userLoggingsVo);
	}

	public String getTransportCategoryType(String trnsportTypeId, UserLoggingsPojo pojo) {

		return new StudentRegistrationServiceImpl()
				.getTransportCategoryType(trnsportTypeId,pojo);
	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo studentSearch,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl()
				.studentSearchbyClass(studentSearch,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getChildCategory(schoolLocation,pojo);
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory, String location, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getClassDetails(catecory,location,pojo);
	}

	public List<StudentRegistrationVo> getSection(String classval, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getSection(classval,pojo);
	}

	public List<StudentRegistrationVo> getConcessionDetails(UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConcessionDetails(userLoggingsVo);
	}

	public List<TransportTypeVO> transportypedetails(UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().transportypedetails(pojo);
	}

	public List<StageMasterVo> getStageDetails(UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getStageDetails(pojo);
	}

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year,String schoolLocation, UserLoggingsPojo userLoggingsVo) {
	
		return new StudentRegistrationServiceImpl().getStudentDetails1(userType,userCode,academic_year,schoolLocation,userLoggingsVo);
	}

	public boolean deactivateStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().deactivateStudent(registrationVo,pojo);
	}

	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID,UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getSubjectByClass(classID,custdetails);
	}
	
	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		
		return new StudentRegistrationServiceImpl().editStudent(registrationVo,pojo);
	}
	
	public List<registrationvo> StudentDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		return new StudentRegistrationServiceImpl().StudentDetails(academic_year,location,dbdetails);
	}
	
	public java.util.List<StudentRegistrationVo> getStudentDetails(String searchName,String location,UserLoggingsPojo dbinfo) {
		
		return new StudentRegistrationServiceImpl().getStudentDetails(searchName,location,dbinfo);
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String academic_year,UserLoggingsPojo userLoggingsVo) {
		
		return new StudentRegistrationServiceImpl().getStudentDetails(userType,userCode,academic_year,userLoggingsVo);
	}
	
	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		return new StudentRegistrationServiceImpl().getTranscationcategory(transloc) ;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getClassDetailWithOutStream(location,pojo);
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {
		return new StudentRegistrationServiceImpl().getClassDetailsByTemp();
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {
		return new StudentRegistrationServiceImpl().getClassDetailSrSecondary();
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getTempRegistrationDetails(registrationVo,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {
		return new StudentRegistrationServiceImpl().getClassDetailMontessori();
	}

	public java.util.List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String academic_year) {
		return new StudentRegistrationServiceImpl().getTransportStudentDetails(userType,userCode,academic_year);
	}

	public java.util.List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		return new StudentRegistrationServiceImpl().getTransportStudentDetails(searchTerm);
	}
	
	public List<StudentRegistrationVo> getStudentList(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		
		return new StudentRegistrationServiceImpl().getStudentList(academic_year,location,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getClassByLocation(String locationid, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getClassByLocation(locationid,pojo);
	}

	public java.util.List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getAllStudentDetails(academic_year,location,custdetails);
	}

	public List<StudentRegistrationVo> studentFullList(String studentId,String accYear, String locationId, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().studentFullList(studentId,accYear,locationId,pojo);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsList(String locationid ,String accyear,UserLoggingsPojo userLoggingsVo ) {
		return new StudentRegistrationServiceImpl().getStudentDetailsList(locationid,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname, String status, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getStudentDetailsLByFilter(locationid,accyear,classname,status,custdetails);
	}

	public List<StudentRegistrationVo> getStudentListBySections(String locationid, String accyear, String classname, String sectionid, String sortingby,String orderby,
			String status,String housename, UserLoggingsPojo custdetails, String search, String myorder,int show_per_page, int startPoint, String specialization){
			return new StudentRegistrationServiceImpl().getStudentListBySections(locationid,accyear,classname,sectionid,sortingby,orderby,status,housename,custdetails,search,myorder, show_per_page,  startPoint,  specialization);
	}
	
	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname, String sectionid, String status,UserLoggingsPojo custdetails){
		return new StudentRegistrationServiceImpl().getStudentListBySection(locationid,accyear,classname,sectionid,status,custdetails);
}

	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().singleStudentDetailsList(studentId,accYear,locationId,flag,pojo);
	}

	public String saveStudentPromotion(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().saveStudentPromotion(registrationVo,pojo);
	}

	public String getNextAcademicYearId(String academicyearid, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getNextAcademicYearId(academicyearid,custdetails);
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getPromotedListDetails(regVo,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentLocationList(academic_year,location,userLoggingsVo);
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().searchAllAcademicYearDetails(locationId,accYear,pojo);
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentSearchByList(searchTerm);
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		return new StudentRegistrationServiceImpl().getStudentSearchListByAccYear(searchTerm,accyear);
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentSearchListByLocation(searchTerm,locationid,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		return new StudentRegistrationServiceImpl().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		return new StudentRegistrationServiceImpl().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, String housename, String status, String sortby,
			String orderbyAdmissionAndAlpha, String orderbyGender, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,housename,status,sortby,orderbyAdmissionAndAlpha,orderbyGender,pojo);
	}
	
	public List<StudentRegistrationVo> getIDCard(String studentId,String accYear, String locationId, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getIDCard(studentId,accYear,locationId,userLoggingsVo);
	}
	
	
	
	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().getfeedetails(studentId,accYear,locationId);
	}

	public String AddConfidentialDetails(String entryDate,String comments, String studentId, String accyear, String locationid,String userName, String logactivity, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().AddConfidentialDetails(entryDate,comments,studentId,accyear,locationid,userName,logactivity,pojo);
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().searchAllAccYearDetailsConfReport(locationId,accYear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfDetailsLByFilter(locationid,accyear,classname,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(String searchTerm,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentSearchByConfReport(searchTerm,userLoggingsVo);
	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().singleStudentConfDetails(studentId,accYear,locationId,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfSearchListByAccYear(searchTerm,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfSearchListByLocation(searchTerm,locationid,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfSearchByFilter(searchTerm,locationid,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid, String accyear, String classname,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getConfSearchByClass(searchTerm,locationid,accyear,classname,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,custdetails);	
	}

	public String deleteConfidentialDetails(String deleteId, String remarks, String log_audit_session, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().deleteConfidentialDetails(deleteId,remarks,log_audit_session,pojo);	
	}

	public String editConfidentialDetails(String entryDate, String comments, String editId, String log_audit_session, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().editConfidentialDetails(entryDate,comments,editId,log_audit_session,pojo);
	}

	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getIDCardStaff(filterpojo,userLoggingsVo);
	}

	public List<ExaminationDetailsVo> getExaminationDetails(StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getExaminationDetails(svo,pojo);
	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,String acyear_id, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getExaminationCodes(loc_id,acyear_id,pojo);
	}

	public String getExamName(String examcode, UserLoggingsPojo pojo) {
		 
		return new StudentRegistrationServiceImpl().getExamName(examcode,pojo);
	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id, String accId, UserLoggingsPojo pojo) {
		 
		return new StudentRegistrationServiceImpl().getStudentAttendance(stud_id,accId,pojo);
	}

	public List<StudentAttendanceVo> getStudentAppraisal(StudentRegistrationVo spvo, UserLoggingsPojo pojo) {
		 
		return new StudentRegistrationServiceImpl().getStudentAppraisal(spvo,pojo);
	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		 
		return new StudentRegistrationServiceImpl().getExaminationDetailsBasedonExams(svo,pojo);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByList(String searchTerm, String accyear, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentFeeSearchByList(searchTerm,accyear,userLoggingsVo);

	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(String searchTerm, String accyear, UserLoggingsPojo userLoggingsVo) {
		 
		return new StudentRegistrationServiceImpl().getStudentFeeSearchListByAccYear(searchTerm,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentFeeSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(String searchTerm, String locationid, String accyear,	String classname, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentSearchByFeeClass(searchTerm,locationid,accyear,classname,userLoggingsVo);

	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(String searchTerm, String locationid, String accyear,UserLoggingsPojo userLoggingsVo) {
		 
		return new StudentRegistrationServiceImpl().getStudentSearchByFeeFilter(searchTerm,locationid,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentDetailsLByFeeFilter(locationid,accyear,classname,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentListByFeeSection(String locationid, String accyear, String classname,String sectionid,UserLoggingsPojo userLoggingsVo) {
		 
		return new StudentRegistrationServiceImpl().getStudentListByFeeSection(locationid,accyear,classname,sectionid,userLoggingsVo);
	}

	public java.util.List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(	String academic_year, String location, String classId,String sectionid, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid,custdetails);
	}

	public String toCheckNextClassAvailable(String toclass, String locationId, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().toCheckNextClassAvailable(toclass,locationId,pojo);
	}

	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getAllAcademicYearDemotedDetails(locationId,accYear,userLoggingsVo);
	}
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getAllAcademicYearPromotedDetails(locationId,accYear,userLoggingsVo);
	}
	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		return new StudentRegistrationServiceImpl().getStudentDemotedSearchByList(searchTerm);
	}

	public StudentRegistrationVo getStudentPromotedChange(String studentId, String accYear, String locationId,String promotedId, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getStudentPromotedChange(studentId,accYear,locationId,promotedId,custdetails);
	}
	
	public String toCheckNextYearAvailable(String accyear, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().toCheckNextYearAvailable(accyear,pojo);
	}
	
	public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getPromotedClassSectionList(regVo,userLoggingsVo);
	}
	
	public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getPromotedClassList(regVo, userLoggingsVo);
	}
	
	public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getDemotedListDetails(regVo,userLoggingsVo);
	}
	
	public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getDemotedClassSectionList(regVo,userLoggingsVo);
	}
	
	public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getDemotedClassList(regVo,userLoggingsVo);
	}
	
	public StudentRegistrationVo getStudentWisePromotion(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {
		return new StudentRegistrationServiceImpl().getStudentWisePromotion(studentId,accYear,locationId,custdetails);
	}
	
	public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm, String status, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByList(searchTerm,status, userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear, String locationid, String classname, String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchListByAccYear(searchTerm,accyear,locationid,classname,sectionid,status,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid, String status,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchListByLocation(searchTerm,locationid,status,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear, String status,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByFilter(searchTerm,locationid,accyear,status,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname, String status,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByClass(searchTerm,locationid,accyear,classname,status,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentPromotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,status,userLoggingsVo);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location,
			String currentaccyear, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentDetailsexcel(searchTerm,location,currentaccyear,userLoggingsVo);
	}

	public java.util.List<StudentRegistrationVo> getStudentDetailsByJs(
			StudentRegistrationVo data, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getStudentDetailsByJs(data,pojo);
	}

	public String getlocationteacher(String userCode) {
		return new StudentRegistrationServiceImpl().getlocationteacher(userCode);
	}

	public String toGetStreamName(String toClass, String locationId, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().toGetStreamName(toClass,locationId,userLoggingsVo);
	}

	public List<StudentRegistrationVo> individualStudentSearch(String studentId, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().individualStudentSearch(studentId,pojo);
	}

	public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location, UserLoggingsPojo userLoggingsVo ) {
		return new StudentRegistrationServiceImpl().getStudentWithheldList(academic_year,location, userLoggingsVo);
	}

	public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
		return new StudentRegistrationServiceImpl().singleStudentWithHeld(studentId,accYear,locationId);
	}

	public String AddWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().AddWithHeldDetails(studentvo,pojo);
	}

	public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
			String locationid, String flag, String status, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().singleStudentWithHeldDetailsList(studentId,accyear,locationid,flag,status,pojo);
	}

	public String EditWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().EditWithHeldDetails(studentvo,pojo);
	}

	public String deleteWithHeldDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().deleteWithHeldDetails(deleteId,log_audit_session,pojo);
	}

	public String AddApparisalDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
		
		return new StudentRegistrationServiceImpl().AddApparisalDetails(vo,pojo);
	}

	public List<StudentRegistrationVo> singleStudentDetailReport(String studentId, String accyear, String locationid, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().singleStudentDetailReport(studentId,accyear,locationid,pojo);
	}

	public String deleteApparaisalDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().deleteApparaisalDetails(deleteId,log_audit_session,pojo);
	}

	public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details,UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getStudentListBySpecialization(details,userLoggingsVo);
	}

	public String getAdmissionNo(String locationId, UserLoggingsPojo pojo) {
		return new StudentRegistrationServiceImpl().getAdmissionNo(locationId,pojo);
	}
	
	public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId,String accYear, String locationId, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().getIDCardPhotoSheet(sectionId,classId,accYear,locationId,userLoggingsVo);
	}

	public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
			String accYear, String locationId, UserLoggingsPojo pojo) {
		
		return new StudentRegistrationServiceImpl().ShowStudentAddress(studentId,accYear,locationId,pojo) ;
	}
	
	public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,
			String[] splitclassid,String examdetails,String reason,String remarks,String result, String log_audit_session, UserLoggingsPojo custdetails, StudentRegistrationVo vo) {
		return new StudentRegistrationServiceImpl().generateStudentTC(splitlocation,splitaccyear,splitstudentid,splitadmid,splitclassid,examdetails,reason,remarks,result,log_audit_session,custdetails,vo);
	}
	
	public List<StudentRegistrationVo> TCGeneration(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		return new StudentRegistrationServiceImpl().TCGeneration(academic_year,location,userLoggingsVo);
	}
	
public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {
		
		return new StudentRegistrationServiceImpl().TCGeneration1(academic_year,location);
	}

public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getNotGenTC(vo,custdetails);
}

public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname, String sectionid, String sortingby,String orderby, UserLoggingsPojo custdetails){
	return new StudentRegistrationServiceImpl().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby,custdetails);
}

public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid,
		String classid, UserLoggingsPojo cpojo){
	return new StudentRegistrationServiceImpl().TransferCertificateDownload(locationId,accyear,studentid,admid,classid,cpojo);
}

public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().GenTCListFilter(vo,custdetails);
}

public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().GenTCListSearch(vo,custdetails);
}

public String updateStudentPromotion(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
	return new StudentRegistrationServiceImpl().updateStudentPromotion(registrationVo,pojo);
}

public List<StudentRegistrationVo> getStudentdisciplinaryactionListDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationServiceImpl().getStudentdisciplinaryactionListDetails(vo,pojo);
}

public String activedeleteConfidentialDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationServiceImpl().activedeleteConfidentialDetails(vo,pojo);
}

public List<StudentRegistrationVo> searchAllAcademicYearDetails(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().searchAllAcademicYearDetails(vo,custdetails);
}
 

public List<StudentRegistrationVo> getStudentDisciplinaryActionSearchByList(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationServiceImpl().getStudentDisciplinaryActionSearchByList(vo,pojo);
}
 

public List<StudentRegistrationVo> newClassList(String location, String preClass, UserLoggingsPojo pojo) {
 
	
	return new StudentRegistrationServiceImpl().newClassList(location,preClass,pojo);
}

public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
		String sectionid,UserLoggingsPojo custdetails,String searchvalue) {
	
	return new StudentRegistrationServiceImpl().getStudentListBySection(locationid,accyear,classname,sectionid,custdetails,searchvalue);
}

public List<StudentRegistrationVo> getSectionForSMS(String classval, StudentRegistrationVo vo,UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationServiceImpl().getSectionForSMS(classval,vo,userLoggingsVo);
}

public StudentRegistrationVo singleStudentDetails(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().singleStudentDetails(studentId,accYear,locationId,custdetails);
}

public List<StudentRegistrationVo> getTempRegistrationList(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationServiceImpl().getTempRegistrationList(registrationVo,userLoggingsVo);
}

public java.util.List<StudentRegistrationVo> getStudentRegistrationList(StudentRegistrationVo data, UserLoggingsPojo pojo) {
	return new StudentRegistrationServiceImpl().getStudentRegistrationList(data,pojo);
}

public List<StudentRegistrationVo> getTransportConcessionStudentList(String locationid, String accyear, String classid,
		String sectionid, String searchname, UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationServiceImpl().getTransportConcessionStudentList(locationid,accyear,classid,sectionid,searchname,userLoggingsVo);
}

public List<ConcessionDetailsPojo> getConcessionTypes(UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getConcessionTypes(custdetails);
}

public List<StudentRegistrationVo> getStudentListByTransportRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getStudentListByTransportRequestCancel(vo,custdetails);
}

public List<StudentRegistrationVo> getStudentListBySectionReportCard(String locationid, String accyear,
		String classname, String sectionid, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getStudentListBySectionReportCard(locationid,accyear,classname,sectionid,custdetails);
	}

public List<StudentRegistrationVo> getStudentListByFeeRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getStudentListByFeeRequestCancel(vo,custdetails);
}

public StudentRegistrationVo getFeeRequestStudentWise(StudentRegistrationVo stuvo,UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationServiceImpl().getFeeRequestStudentWise(stuvo,userLoggingsVo);
}

public List<ConcessionDetailsPojo> getSubConcessionTypes(UserLoggingsPojo custdetails) {
	return new StudentRegistrationServiceImpl().getSubConcessionTypes(custdetails);
	}

public List<StudentRegistrationVo> getStudentListByAnalyticalPerformance(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().getStudentListByAnalyticalPerformance(vo);
}

public String SaveStudentAnalyticalPerformance(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().SaveStudentAnalyticalPerformance(vo);
}

public List<StudentRegistrationVo> getStudentAnalyticPerformanceSearchByList(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().getStudentAnalyticPerformanceSearchByList(vo);
}

public List<StudentRegistrationVo> getStudentFilteredListByAnalyticalPerformance(StudentRegistrationVo vo) {
	return new StudentRegistrationServiceImpl().getStudentFilteredListByAnalyticalPerformance(vo);
}
}
