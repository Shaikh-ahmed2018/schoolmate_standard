package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ReportsMenuDao;
import com.centris.campus.daoImpl.FormsManagementdaoImpl;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ReportsMenuService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;


public class ReportsMenuServiceImpl implements ReportsMenuService {

	private static final Logger logger = Logger
			.getLogger(ReportsMenuServiceImpl.class);

	static ReportsMenuDaoImpl daoimpl;
	static{
		daoimpl = new ReportsMenuDaoImpl();
	}
	
	@Override
	public ArrayList<ReportMenuVo> getAccYears(UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getAccYears Starting");
		ArrayList<ReportMenuVo> yearList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			yearList = dao.getAccYears(dbdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getAccYears Ending");

		return yearList;
	}

	@Override
	public ArrayList<ReportMenuVo> getStream(UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStream Starting");
		ArrayList<ReportMenuVo> streamList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			streamList = dao.getStream(dbdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStream Ending");

		return streamList;
	}
	
	public ArrayList<ReportMenuVo> getStudentClass(String location,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentClass Starting");
		ArrayList<ReportMenuVo> classList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			classList = dao.getStudentClass(location,userLoggingsVo);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentClass Ending");

		return classList;
	}

	@Override
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getClassesByStream Starting");
		ArrayList<ReportMenuVo> classList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			classList = dao.getClassesByStream(streamId,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getClassesByStream Ending");

		return classList;
	}

	@Override
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId,String location,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClass Starting");
		ArrayList<ReportMenuVo> sectionList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			sectionList = dao.getSectionsByClass(classId,location,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClass Ending");

		return sectionList;
	}

	@Override
	public ArrayList<StudentInfoVO> getStudentDetailsReport(
			ReportMenuForm reform,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.getStudentDetailsReport(reform,dbdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}

	public ReportMenuVo getSelectedItems(ReportMenuForm reform,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Starting");

		ReportMenuVo selected = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			selected = dao.getSelectedItems(reform,dbdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Ending");

		return selected;
	}

	@Override
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(
			ReportMenuForm reform,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Starting");

		HashMap<String, ArrayList<FeeReportDetailsVo>> feeStatusList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			feeStatusList = dao.getStdFeeStatusReportDetails(reform, userLoggingsVo);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Ending");

		return feeStatusList;
	}

	@Override
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(
			FeeStatusReportPojo reform,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Starting");

		ArrayList<FeeReportDetailsVo> feeStatusList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			feeStatusList = dao.getStdFeeStatusReportDownload(reform,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdFeeStatusReportDetails Ending");

		return feeStatusList;
	}

	@Override
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(
			ReportMenuForm reform,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetails Starting");

		HashMap<String, ArrayList<MarksUploadVO>> marksList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			marksList = dao.getStdMarksDetails(reform,userLoggingsVo);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetails Ending");

		return marksList;
	}

	@Override
	public ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetailsDownload Starting");

		ArrayList<MarksUploadVO> marksList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			marksList = dao.getStdMarksDetailsDownload(reform,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStdMarksDetailsDownload Ending");

		return marksList;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(
			ReportMenuForm reform,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : examReportClassWiseDetails Starting");

		ArrayList<ExaminationDetailsVo> examList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			examList = dao.examReportClassWiseDetails(reform,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : examReportClassWiseDetails Ending");

		return examList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(
			ReportMenuVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.geInactivetStudentDetailsReport(vo,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}

	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(
			ReportMenuForm reform,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Starting");

		ArrayList<StudentInfoVO> studentIfoList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			studentIfoList = dao.geInactivetStudentFeeDetailsReport(reform,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getStudentDetailsReport Ending");

		return studentIfoList;
	}
	
	public ReportMenuVo getSelectedoneItems(ReportMenuForm reform, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Starting");

		ReportMenuVo selected = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {

			selected = dao.getSelectedoneItems(reform,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSelectedItems Ending");

		return selected;
	}
	
	@Override
	public ArrayList<FeeReportDetailsVo> getSingleStdFeeStatusReportDetails(
			String stdId,UserLoggingsPojo custdetails) {
		return daoimpl.getSingleStdFeeStatusReportDetails(stdId,custdetails);
	}

	@Override
	public String gettempregid() {
		return daoimpl.gettempregid();
	}

	@Override
	public String getthirdRegNo() {
		return daoimpl.getthirdRegNo();
	}

	@Override
	public ArrayList<ReportMenuVo> getlocationList(UserLoggingsPojo dbdetails) {
		return daoimpl.getlocationList(dbdetails);
	}

	@Override
	public String insertadmissionDetailsAction(Issuedmenuvo vo, String enquriyid) {
		FormsManagementdaoImpl daoimpl=new FormsManagementdaoImpl();
		return daoimpl.insertadmissionDetailsAction(vo,enquriyid);
	}


	@Override
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getstudentDOBWise(vo,custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return daoimpl.getstudentFatherOccuWise(vo,custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getstudentMotherOccuWise(vo,custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getstudentDetailsReligionWise(vo,custdetails);
	}
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentCategoryWise(vo,userLoggingsVo);
	}
	
	@Override
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getclasssectionDetails(vo, custdetails);
	}

	@Override
	public ArrayList<ReportMenuVo> getClassDetails(UserLoggingsPojo custdetails){
		return daoimpl.getClassDetails(custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return daoimpl.getstudentParentWise(vo,custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentList(vo, userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getstudentDetailsList(ReportMenuVo vo) {
		return daoimpl.getstudentDetailsList(vo);
	}
	
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getstudentContactDetails(vo,custdetails);
	}
	
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentStandardWise(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getAccYearsSubject(String accyear,String locid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getAccYearsSubject(accyear,locid,userLoggingsVo);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> accYearListStatus(UserLoggingsPojo custdetails) {
		return daoimpl.accYearListStatus(custdetails);
	}

	@Override
	public ArrayList<ReportMenuVo> getAllLocationName() {
		return daoimpl.getAllLocationName();
	}

	@Override
	public ArrayList<ExaminationDetailsVo> accYearhouseSettings(String locid,String accyear,UserLoggingsPojo custdetails) {
		return daoimpl.accYearhouseSettings(locid,accyear,custdetails);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> accYeargeneratehouseSettings(String locid,String accyear,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.accYeargeneratehouseSettings(locid,accyear, userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentDepartmentList(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentDepartmentList(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentBusRouteWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentBusRouteWise(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentOptionalSubjectDetails(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentOptionalSubjectDetails(vo, userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getstudentWithPhoneNumber(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentWithPhoneNumber(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getOldStudentsList(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getOldStudentsList(vo,userLoggingsVo);	
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentsStrength(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getStudentsStrength(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentsNewAdmissionList(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getStudentsNewAdmissionList(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentPromotionList(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getStudentPromotionList(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getStudentListGenderWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getStudentListGenderWise(vo,userLoggingsVo);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> accYearListStatusGrade(String accyear, String location,UserLoggingsPojo pojo) {
		return daoimpl.accYearListStatusGrade(accyear,location,pojo);
	}
	@Override
	public ArrayList<SubjectPojo> getSubjectByClass(String classId,String locationId,UserLoggingsPojo userLoggingsVo,String spec) {
		return daoimpl.getSubjectByClass(classId,locationId,userLoggingsVo,spec);
	}
	@Override
	public ArrayList<ReportMenuVo> getaccessionNo(UserLoggingsPojo custdetails) {
		return daoimpl.getaccessionNo(custdetails);
	}
	@Override
	public List<ExaminationDetailsVo> getSubjectOnClass(String classId,String studentId,String accYear,String locationId,String examCode,	UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getSubjectOnClass(classId,studentId,accYear,locationId,examCode,userLoggingsVo);
		}

	@Override
	public ArrayList<ExaminationDetailsVo> getExam(String studentId,
			String accyear, String locationId, String classDetailId,
			String sectionId,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getExam(studentId,accyear,locationId,classDetailId,sectionId,userLoggingsVo);
		}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamDependencides(String examCode,String studentId, String accYear, String locationId, String classId, String sectionId,int scored,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getExamDependencides(examCode,studentId,accYear,locationId,classId,sectionId,scored,userLoggingsVo);
		}

	@Override
	public String getGradeBasedOnMarks(int grandtotal,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getGradeBasedOnMarks(grandtotal, userLoggingsVo);
		}


	@Override
	public ArrayList<ReportMenuVo> getAccessionNo() {
		return daoimpl.getAccessionNo();
		}


	@Override
	public List<ExaminationDetailsVo> getIndividualStudentMarksClass(
			String classId, String studentId, String accYear,
			String locationId, String examCode, String sectionId,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,examCode,sectionId,userLoggingsVo);
		}


	public ArrayList<ReportMenuVo> getTerm(String accyear,String location,UserLoggingsPojo custdetails) {
		return daoimpl.getTerm(accyear,location,custdetails);
	}



	@Override
	public ArrayList<ExaminationDetailsVo> reportservice() {
		
		return null;
	}

	@Override
	public ArrayList<FeeCollectionVo>getFeeCollectionReport(String locationid, String accyear,String termId,UserLoggingsPojo userLoggingsVo) {
		
		return daoimpl.getFeeCollectionReport(locationid,accyear,termId,userLoggingsVo);
	}

	@Override
	public ArrayList<FeeCollectionVo> getfeecollectionclasslist(
			String locationid, String accyear, String classid,String termId,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getfeecollectionclasslist(locationid,accyear,classid,termId,userLoggingsVo);
	}

	@Override
	public ArrayList<FeeCollectionVo> getFeeCollectionSectionReport(
			String locationid, String accyear, String classid, String setionid,String termId,UserLoggingsPojo userLoggingsVo) {
	
		return daoimpl.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId,userLoggingsVo);
	}

	@Override
	public ArrayList<FeeCollectionVo> getFeeCollectionPaymodeReport(
			String locationid, String accyear, String classid, String setionid,
			String paymodeid,String paymenttype,String termId,UserLoggingsPojo userLoggingsVo,String startdate,String enddate) {
		
		return daoimpl.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,enddate);
	}


	@Override
	public ArrayList<ReportMenuVo> getterms(String location,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getterms(location,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> DDReportList(String termid, String academic_year,String locationid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.DDReportList(termid,academic_year,locationid,userLoggingsVo);
	}


	@Override
	public ArrayList<FeeCollectionVo> getonlinelist(String locationid,
			String accyear, String classid, String setionid, String paymodeid,
			String paymenttype,String termId,UserLoggingsPojo userLoggingsVo,String startdate, String endate) {
		
		return daoimpl.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
	}


	@Override
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClassLoc Starting");
		ArrayList<ReportMenuVo> sectionList = null;
		ReportsMenuDao dao = new ReportsMenuDaoImpl();
		try {


			sectionList = dao.getSectionsByClassLoc(classId,location,custdetails);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuServiceImpl : getSectionsByClassLoc Ending");

		return sectionList;
	}


	@Override
	public ITFeeVo getITFee(String studentId, String accyer,String locationId,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.gettgetITFeeerms(studentId,accyer,locationId,userLoggingsVo);
		}

	@Override
	public List<ReportMenuVo> getStudentListAdmiWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getStudentListAdmiWise(vo,userLoggingsVo);
	}

	@Override
	public List<ReportMenuVo> getstudentRollNoWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentRollNoWise(vo,userLoggingsVo);
	}

	@Override
	public List<ReportMenuVo> getstudentAlphaWise(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getstudentAlphaWise(vo,userLoggingsVo);
	}

	@Override
	public ArrayList<ReportMenuVo> getExpenditureReport(ReportMenuForm reform,UserLoggingsPojo custdetails) {
		
		return daoimpl.getExpenditureReport(reform,custdetails);
	}

	@Override
	public List<ReportMenuVo> getStudentClassSectionWiseListForReport(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getStudentClassSectionWiseListForReport(vo,custdetails);
		}
	@Override
	public List<ReportMenuVo> getStudentClassSectionWiseListForReportByAll(ReportMenuVo vo,UserLoggingsPojo custdetails) {
		return daoimpl.getStudentClassSectionWiseListForReportByAll(vo,custdetails);
	}
	@Override
	public ReportMenuVo getTerm1Exams(String accyear,String classId, String locationid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getTerm1Exams(accyear,classId,locationid,userLoggingsVo);
	}
	@Override
	public ReportMenuVo getTerm2Exams(String accyear,String classId, String locationid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getTerm2Exams(accyear,classId,locationid,userLoggingsVo);
	}

	@Override
	public ReportMenuVo getFinalExams(String accyear,String classId, String locationid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getFinalExams(accyear,classId,locationid,userLoggingsVo);
	}
	@Override
	public List<ReportMenuVo> getTermWiseReportCard(ReportMenuVo vo,UserLoggingsPojo dbdetails) {
		return daoimpl.getTermWiseReportCard(vo,dbdetails);
	}

	@Override
	public List<ReportMenuVo> getAcademicYearWiseReportCard(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getAcademicYearWiseReportCard(vo,userLoggingsVo);
		}

	public ArrayList<ReportMenuVo> getClassByLocation(String locId, UserLoggingsPojo custdetails) {
		return new ReportsMenuDaoImpl().getClassByLocation(locId,custdetails);
	}

	public ArrayList<ReportMenuVo> getSpecByClassLoc(String classId, String locId, UserLoggingsPojo custdetails) {
		return new ReportsMenuDaoImpl().getSpecByClassLoc(classId,locId,custdetails);
	}

	public ArrayList<ReportMenuVo> getstudentsHouseWise(ReportMenuVo vo, UserLoggingsPojo custdetails) {
		return new ReportsMenuDaoImpl().getstudentsHouseWise(vo,custdetails);
		}

	@Override
	public ArrayList<ReportMenuVo> gettransportfeeDetails(ReportMenuVo obj, UserLoggingsPojo custdetails) {
		return new ReportsMenuDaoImpl().gettransportfeeDetails(obj,custdetails);
	}

	public String getDefaulterStudentsCount(UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuDaoImpl().getDefaulterStudentsCount(userLoggingsVo);
	}

	public String getCollectionCount(UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuDaoImpl().getCollectionCount(userLoggingsVo);
	}

	public String getStudentCount(String location_id, String academic_year, UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuDaoImpl().getStudentCount( location_id,  academic_year,  userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getAbsenteesDetails(String loaction_id, UserLoggingsPojo userLoggingsVo) {
		
		return new ReportsMenuDaoImpl(). getAbsenteesDetails( loaction_id,  userLoggingsVo);
	}

	public ArrayList<ReportMenuVo> getSchoolList(UserLoggingsPojo pojo) {
		return new ReportsMenuDaoImpl().getSchoolList(pojo);
	}
}
