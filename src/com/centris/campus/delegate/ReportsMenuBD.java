package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.centris.campus.forms.FeeReportform;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LibraryService;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.serviceImpl.FeeReportServiceImpl;
import com.centris.campus.serviceImpl.LibraryServiceImpl;
import com.centris.campus.serviceImpl.ReportsMenuServiceImpl;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.feeReportVO;


public class ReportsMenuBD {
	
	static ReportsMenuService reportservice;
	static {
		reportservice=new ReportsMenuServiceImpl();
	}
	
	public ArrayList<ReportMenuVo> getAccYears(UserLoggingsPojo custdetails){
		return reportservice.getAccYears(custdetails);
	}
	
	public ArrayList<ReportMenuVo> getStream(UserLoggingsPojo dbdetails){
		
		return reportservice.getStream(dbdetails);
	}

	public ArrayList<ReportMenuVo> getStudentClass(String schoolLocation, UserLoggingsPojo userLoggingsVo){
		
		return reportservice.getStudentClass(schoolLocation,userLoggingsVo);
	}
	

	public ArrayList<ReportMenuVo> getClassesByStream(String streamId, UserLoggingsPojo custdetails){

		return reportservice.getClassesByStream(streamId,custdetails);

	}
	
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location,UserLoggingsPojo custdetails){
		
		return reportservice.getSectionsByClass(classId,location,custdetails);
		
	}
	
	
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform, UserLoggingsPojo dbdetails){
		
		
		
		return reportservice.getStudentDetailsReport(reform,dbdetails);
		
	}
	
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform, UserLoggingsPojo dbdetails){
		
		
		
		return reportservice.getSelectedItems(reform,dbdetails);
		
	}
	
	public HashMap<String, ArrayList<FeeReportDetailsVo>>  getStdFeeStatusReportDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo){
			
			
			
			return reportservice.getStdFeeStatusReportDetails(reform,userLoggingsVo);
			
		}
	
	public ArrayList<FeeReportDetailsVo>  getStdFeeStatusReportDownload(FeeStatusReportPojo reform, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.getStdFeeStatusReportDownload(reform,custdetails);
		
	}
	
	public HashMap<String, ArrayList<MarksUploadVO>>  getStdMarksDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo){
		
		
		
		return reportservice.getStdMarksDetails(reform,userLoggingsVo);
		
	}
	
	public ArrayList<MarksUploadVO>  getStdMarksDetailsDownload(MarksPOJO reform, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.getStdMarksDetailsDownload(reform,custdetails);
		
	}
	
	public ArrayList<ExaminationDetailsVo>  examReportClassWiseDetails(ReportMenuForm reform, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.examReportClassWiseDetails(reform,custdetails);
		
	}
	
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuVo vo, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.geInactivetStudentDetailsReport(vo,custdetails);
		
	}
	
	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.geInactivetStudentFeeDetailsReport(reform,custdetails);
		
	}
	
	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform, UserLoggingsPojo custdetails){
		
		
		
		return reportservice.getSelectedoneItems(reform,custdetails);
		
	}
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId, UserLoggingsPojo custdetails) {
		
		
		return reportservice.getSingleStdFeeStatusReportDetails(stdId,custdetails);
	}

	public String gettempregid() {
		
		return reportservice.gettempregid();

       }

	

	public String getthirdRegNo() {
		return  reportservice.getthirdRegNo();

}
	
	public ArrayList<ReportMenuVo> getlocationList(UserLoggingsPojo custdetails) {
		
		return reportservice.getlocationList(custdetails);
	}

	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid) {
		return reportservice.insertadmissionDetailsAction(vo,enquriyid);
	}


	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getstudentDOBWise(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getstudentFatherOccuWise(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getclasssectionDetails(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getstudentMotherOccuWise(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getstudentDetailsReligionWise(vo,custdetails);
	}


	public ArrayList<ReportMenuVo> getClassDetails(UserLoggingsPojo custdetails) {
		return reportservice.getClassDetails(custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentCategoryWise(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return reportservice.getstudentParentWise(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentList(vo,userLoggingsVo);
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		return reportservice.getstudentDetailsList(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return reportservice.getstudentContactDetails(vo,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentStandardWise(vo,userLoggingsVo);
	}


	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear, String locid,UserLoggingsPojo userLoggingsVo) {
		return reportservice.getAccYearsSubject(accyear,locid,userLoggingsVo);
	}
	
	public ArrayList<ExaminationDetailsVo> accYearListStatus(UserLoggingsPojo custdetails) {
		return reportservice.accYearListStatus(custdetails);
	}

	public ArrayList<ReportMenuVo> getAllLocationName() {
		return reportservice.getAllLocationName();
	}

	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear, UserLoggingsPojo pojo) {
		return reportservice.accYearhouseSettings(locid,accyear,pojo);
	}

	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear, UserLoggingsPojo userLoggingsVo) {
		return reportservice.accYeargeneratehouseSettings(locid,accyear,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentDepartmentList(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentBusRouteWise(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentOptionalSubjectDetails(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentWithPhoneNumber(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getOldStudentsList(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getStudentsStrength(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getStudentsNewAdmissionList(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getStudentPromotionList(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getStudentListGenderWise(vo,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location,UserLoggingsPojo pojo) {
		return reportservice.accYearListStatusGrade(accyear,location,pojo);
	}


	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId, UserLoggingsPojo userLoggingsVo, String spec) {
		return reportservice.getSubjectByClass(classId,locationId,userLoggingsVo,spec);
	}

	/*public List<PageFilterVo> getstaffDeatails() {
		ReportsMenuService list=new getstaffDeatailsServiceImpl();
		return list.accYeargeneratehouseSettings(locid);
	}*/

	public ArrayList<ReportMenuVo> getaccessionNo(UserLoggingsPojo custdetails) {
		return reportservice.getaccessionNo(custdetails);
	}


	public static List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getSubjectOnClass(classId,studentId,accYear,locationId,examCode,userLoggingsVo);
	}

	public static ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getExam(studentId,accyear,locationId,classDetailId,sectionId,userLoggingsVo);
	}

	public static ArrayList<ExaminationDetailsVo> getExamDependencides(
			String examCode, String studentId, String accYear, String locationId, String classId, String sectionId, int scored, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getExamDependencides(examCode,studentId,accYear,locationId,classId,sectionId,scored,userLoggingsVo);
	}
	
	public static String getGradeBasedOnMarks(int grandtotal, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getGradeBasedOnMarks(grandtotal,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getAccessionNo() {
		return reportservice.getAccessionNo();
	}
	
	public static List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId, UserLoggingsPojo userLoggingsVo) {
			return reportservice.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,examCode,sectionId,userLoggingsVo);
			}

	public ArrayList<ReportMenuVo> getTerm(String accyear, String location, UserLoggingsPojo custdetails) {
		return reportservice.getTerm(accyear,location,custdetails);
	}

	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj, UserLoggingsPojo custdetails) {
		return reportservice.gettransportfeeDetails(obj,custdetails);
	}

	public static ArrayList<ExaminationDetailsVo> reportservice() {
		
		return reportservice.reportservice();
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId, UserLoggingsPojo userLoggingsVo) {
		
		return reportservice.getFeeCollectionReport(locationid,accyear,termId,userLoggingsVo);
	}

	public static ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid, String termId, UserLoggingsPojo userLoggingsVo) {
		
		return reportservice.getfeecollectionclasslist(locationid,accyear,classid,termId,userLoggingsVo);
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(String locationid, String accyear, String classid, String setionid, String termId, UserLoggingsPojo userLoggingsVo) {
		
		return reportservice.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId,userLoggingsVo);
	}

	public static ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid, String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String endate) {
			return reportservice.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
	}


	public ArrayList<ReportMenuVo> getterms(String location, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getterms(location,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid, UserLoggingsPojo userLoggingsVo) {
		return reportservice.DDReportList(termid,academic_year,locationid,userLoggingsVo);
	}


	public static ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String endate) {
		return reportservice.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
	}


	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location, UserLoggingsPojo custdetails) {
		return reportservice.getSectionsByClassLoc(classId,location,custdetails);
	}
	public static ITFeeVo getITFee(String studentId, String accyer,
			String locationId, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getITFee(studentId,accyer,locationId,userLoggingsVo);
	}

	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getStudentListAdmiWise(vo,userLoggingsVo);
	}

	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentRollNoWise(vo,userLoggingsVo);
	}

	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getstudentAlphaWise(vo,userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getExpenditureReport(ReportMenuForm reform, UserLoggingsPojo custdetails) {
		ReportsMenuService reportservice=new ReportsMenuServiceImpl();
		return reportservice.getExpenditureReport(reform,custdetails);
	}
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getStudentClassSectionWiseListForReport(vo,custdetails);
		}
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return reportservice.getStudentClassSectionWiseListForReportByAll(vo,custdetails);
	}

	public ReportMenuVo getTerm1Exams(String accyear,String classId, String locationid,UserLoggingsPojo userLoggingsVo) {
		return reportservice.getTerm1Exams(accyear,classId,locationid,userLoggingsVo);
	}
	public ReportMenuVo getTerm2Exams(String accyear,String classId,String locationid,UserLoggingsPojo userLoggingsVo) {
		return reportservice.getTerm2Exams(accyear,classId,locationid,userLoggingsVo);
	}

	public ReportMenuVo getFinalExams(String accyear, String classId,String locationid,UserLoggingsPojo userLoggingsVo) {
		return reportservice.getFinalExams(accyear,classId,locationid,userLoggingsVo);
	}
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo,UserLoggingsPojo dbdetails) {
		return reportservice.getTermWiseReportCard(vo,dbdetails);
	}

	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return reportservice.getAcademicYearWiseReportCard(vo,userLoggingsVo);
		}

	public ArrayList<ReportMenuVo> getClassByLocation(String locId, UserLoggingsPojo custdetails) {
		return new ReportsMenuServiceImpl().getClassByLocation(locId,custdetails);
	}

	public ArrayList<ReportMenuVo> getSpecByClassLoc(String classId, String locId, UserLoggingsPojo custdetails) {
		return new ReportsMenuServiceImpl().getSpecByClassLoc(classId,locId,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentsHouseWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return new ReportsMenuServiceImpl().getstudentsHouseWise(vo,custdetails);
		}

	public String getDefaulterStudentsCount(UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuServiceImpl().getDefaulterStudentsCount(userLoggingsVo);
	}

	public String getCollectionCount(UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuServiceImpl().getCollectionCount(userLoggingsVo);
	}

	public String getStudentCount(String location_id, String academic_year, UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuServiceImpl().getStudentCount( location_id,  academic_year,  userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getAbsenteesDetails(String loaction_id, UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuServiceImpl().getAbsenteesDetails( loaction_id,  userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getSchoolList(UserLoggingsPojo pojo) {
		return new ReportsMenuServiceImpl().getSchoolList(pojo);
	}
}
