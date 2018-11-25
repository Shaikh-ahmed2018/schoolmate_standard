package com.centris.campus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;


public interface ReportsMenuService {
	
	public ArrayList<ReportMenuVo> getAccYears(UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getStream(UserLoggingsPojo dbdetails);
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location, UserLoggingsPojo custdetails);
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform, UserLoggingsPojo dbdetails);
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform, UserLoggingsPojo dbdetails);
	public HashMap<String, ArrayList<FeeReportDetailsVo>>  getStdFeeStatusReportDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo reform, UserLoggingsPojo custdetails);
	public HashMap<String, ArrayList<MarksUploadVO>>  getStdMarksDetails(ReportMenuForm reform,UserLoggingsPojo userLoggingsVo);
	public ArrayList<MarksUploadVO>  getStdMarksDetailsDownload(MarksPOJO reform, UserLoggingsPojo custdetails);
	public ArrayList<ExaminationDetailsVo>  examReportClassWiseDetails(ReportMenuForm reform, UserLoggingsPojo custdetails);
   public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuVo vo, UserLoggingsPojo custdetails);
   public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform, UserLoggingsPojo custdetails);

	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform, UserLoggingsPojo custdetails);
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId, UserLoggingsPojo custdetails);
	public String gettempregid();
	public String getthirdRegNo();
	public ArrayList<ReportMenuVo> getlocationList(UserLoggingsPojo custdetails);
	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid);

	public ArrayList<ReportMenuVo> getStudentClass(String schoolLocation, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getClassDetails(UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo,UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo,UserLoggingsPojo custdetails);
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String schoolLocation, String locid,UserLoggingsPojo userLoggingsVo);
	public ArrayList<ExaminationDetailsVo> accYearListStatus(UserLoggingsPojo custdetails);
	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid, String accyear, UserLoggingsPojo pojo);
	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid, String accyear, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getAllLocationName();
	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location,UserLoggingsPojo pojo);
	public ArrayList<SubjectPojo> getSubjectByClass(String classId, String locationId, UserLoggingsPojo userLoggingsVo, String spec);
	public ArrayList<ReportMenuVo> getaccessionNo(UserLoggingsPojo custdetails);
	public List<ExaminationDetailsVo> getSubjectOnClass(String classId, String studentId, String accYear, String locationId, String examCode, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,String accyear, String locationId, String classDetailId,String sectionId, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode, String studentId, String accYear, String locationId, String classId, String sectionId, int scored, UserLoggingsPojo userLoggingsVo);
	public String getGradeBasedOnMarks(int grandtotal, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ReportMenuVo> getAccessionNo();

	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getTerm(String accyear, String location, UserLoggingsPojo custdetails);
	
	ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> reportservice();
	public ArrayList<FeeCollectionVo> getFeeCollectionReport(String locationid, String accyear, String termId, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(String locationid, String accyear, String classid, String termId, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid, String termId, UserLoggingsPojo userLoggingsVo);
	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(String locationid, String accyear, String classid, String setionid,String paymodeid, String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String endate);

	public ArrayList<ReportMenuVo> getterms(String location, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid, UserLoggingsPojo userLoggingsVo);

	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,String accyear, String classid, String setionid, String paymodeid,String paymenttype, String termId, UserLoggingsPojo userLoggingsVo, String startdate, String endate);

	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location, UserLoggingsPojo custdetails);
	public ITFeeVo getITFee(String studentId, String accyer, String locationId, UserLoggingsPojo userLoggingsVo);
	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ReportMenuVo> getExpenditureReport(ReportMenuForm reform, UserLoggingsPojo custdetails);
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo, UserLoggingsPojo custdetails);
	public ReportMenuVo getTerm1Exams(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo);
	public ReportMenuVo getTerm2Exams(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo);
	public ReportMenuVo getFinalExams(String accyear, String classId, String locationid, UserLoggingsPojo userLoggingsVo);
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo, UserLoggingsPojo dbdetails);
	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);

}
