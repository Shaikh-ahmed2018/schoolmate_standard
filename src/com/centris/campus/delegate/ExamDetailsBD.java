package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ExamDetailsService;
import com.centris.campus.serviceImpl.ExamDetailsServiceImpll;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamDetailsBD {

	static ExamDetailsService detailsercive;
	static{
		detailsercive = new ExamDetailsServiceImpll();
	}
	
	public List<ExaminationDetailsVo> getexamdeligate(UserLoggingsPojo custdetails) {
		List<ExaminationDetailsVo> allexamlist = new ArrayList<ExaminationDetailsVo>();
		allexamlist = detailsercive.getExamDetailsService(custdetails);
		return allexamlist;
	}

	public String getaccyName(String accyear, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getaccyName(accyear,userLoggingsVo);
	}

	public String insertGradeSettings(ExamDetailsPojo obj) {
		return detailsercive.insertGradeSettings(obj);
	}

	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location,UserLoggingsPojo custdetails) {
		return detailsercive.displayGradeSettings(accyear,location,custdetails);
	}

	public String deleteGradeSettings(String gradeid, String location, String accyear, ExamDetailsPojo obj) {
		return detailsercive.deleteGradeSettings(gradeid,location,accyear,obj);
	}

	public String editGradeSettings(ExaminationDetailsVo list,UserLoggingsPojo userLoggingsVo) {
		return detailsercive.editGradeSettings(list,userLoggingsVo);
	}

	public String checkduplicateGrade(String accyear, String gradename, String loc,UserLoggingsPojo custdetails) {
		return detailsercive.checkduplicateGrade(accyear,gradename,loc,custdetails);
	}

	public String getexamName(String examid, String accyear, String locid,UserLoggingsPojo custdetails) {
		return detailsercive.getexamName(examid,accyear,locid,custdetails);
	}

	
	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.getexamclassDetails(obj,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		return detailsercive.getSubjectmarksStatus(acyear);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation,UserLoggingsPojo custdetails) {
		return detailsercive.getSubjectmarksList(accyear,schoolLocation,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid, String locid,String classid, UserLoggingsPojo custdetails) {
		return detailsercive.getSubjectClass(accyear,examid,locid,classid,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return detailsercive.classWiseSubject(obj,userLoggingsVo);
	}

	public String getsubDetails(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getsubDetails(obj,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj, String schoolLocation, UserLoggingsPojo custdetails) {
		return detailsercive.getstudentsList(obj,schoolLocation,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.classWiseStudent(obj,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1, UserLoggingsPojo custdetails) {
		return detailsercive.getStudentDetails(obj1,custdetails);
	}

	public String insertmarkentrydetails(
			ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.insertmarkentrydetails(obj,userLoggingsVo);
	}

	public String getlocationname(String schoolLocation, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getlocationname(schoolLocation,userLoggingsVo);
	}

	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return detailsercive.insertmarkentrysubjectwise(obj,schoolLocation);
	}

	public String getclassname(String classid, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getclassname(classid,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid,UserLoggingsPojo userLoggingsVo, String classid) {
		return detailsercive.getsubjectstudent(accyear,examid,locid,userLoggingsVo,classid);
	}

	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation) {
		return detailsercive.getlistofExamCodes(schoolLocation);
	}

	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return detailsercive.updatemarkentrysubjectwise(obj,schoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String location, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.examTimeTableListYear(accyear,location,userLoggingsVo);
	}

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getExamClassByLocation(locid,accyear,userLoggingsVo);
	}

	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getexamlistbyclass(pojo,userLoggingsVo);
	}

	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return detailsercive.getexamdetails(pojo,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getsubdetails(pojo,userLoggingsVo);
	}

	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.savetimetabledetails(pojo,subid1,starttime1,endtime1,startdate,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getexamsbtselection(accyear,locid,userLoggingsVo);
	}

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, String examid, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getExamClassByLocation(accyear,locid,examid,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid,UserLoggingsPojo custdetails) {
		return detailsercive.getexamsettingslist(accyear,locid,custdetails);
	}

	public String checkduplicatedate(ExamTimetablePojo pojo) {
		return detailsercive.checkduplicatedate(pojo);
	}

	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return detailsercive.getexamdetailsbyset(pojo,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return detailsercive.getsubdetailsset(pojo,custdetails);
	}

	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate, UserLoggingsPojo custdetails) {
		return detailsercive.savetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate,custdetails);
	}

	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return detailsercive.updatetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {
		return detailsercive.getexamsettingslistfordep(accyear,locid);
		}
	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,
			String classid, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.getSubjectClassBySpec(accyear,examid,locid,classid,userLoggingsVo);
		}

	public String insertmarkentrydetailsSubjectWise(ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo) {
		return detailsercive.insertmarkentrydetailsSubjectWise(obj,userLoggingsVo);
		}

	public List<ExaminationDetailsVo> getExamByClassWise(String locid, String accyear, String classid,
			UserLoggingsPojo custdetails) {
		return detailsercive.getExamByClassWise(locid,accyear,classid,custdetails);
		}

	public List<ExaminationDetailsVo> getStudentMarkListSearch(ExaminationDetailsVo vo, UserLoggingsPojo custdetails) {
		return detailsercive.getStudentMarkListSearch(vo,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getReportCardSettingList(String accyear, String locid, UserLoggingsPojo custdetails) {
		return detailsercive.getReportCardSettingList(accyear,locid,custdetails);
	}

	public List<ExaminationDetailsVo> getMaximumMarkClassList(String locid, String accyear, UserLoggingsPojo custdetails, String classId) {
		return detailsercive.getMaximumMarkClassList(accyear,locid,custdetails,classId);
	}

	public ArrayList<ExaminationDetailsVo> classWiseSubjectInMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.classWiseSubjectInMaximumMark(obj,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> classWiseSubjectWithLabMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.classWiseSubjectWithLabMaximumMark(obj,custdetails);
	}

	public String insertMaximumExammarkDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.insertMaximumExammarkDetails(obj,custdetails);
	}

	public String insertReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.insertReportSetupDetails(obj,custdetails);
	}

	public ExaminationDetailsVo getTerm1ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.getTerm1ReportSetupDetails(obj,custdetails);
	}

	public ExaminationDetailsVo getTerm2ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.getTerm2ReportSetupDetails(obj,custdetails);
	}

	public ExaminationDetailsVo getAcademicReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return detailsercive.getAcademicReportSetupDetails(obj,custdetails);
	}

	
}

