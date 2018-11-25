package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ExamTimeTableService;
import com.centris.campus.serviceImpl.ExamTimeTableServiceImpl;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamTimeTableBD {

	public Map<String, List<ExamTimetablePojo>> getExamDetails(
			ExamTimetablePojo groupLogReportPojo) {
		return new ExamTimeTableServiceImpl()
				.getExamDetails(groupLogReportPojo);
	}

	public ExamTimetablePojo getExamDate(String examId, UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();
		return serviceobj.getExamDate(examId,userLoggingsVo);
	}

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();
		return serviceobj.getAllSubjects(classId);
	}

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam, UserLoggingsPojo userLoggingsVo) {

		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamTimeTableDetails(classId, exam,userLoggingsVo);
	}

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist, UserLoggingsPojo userLoggingsVo, String log_audit_session) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.saveExaminationClassMapping(examinationclassmappinglist,userLoggingsVo,log_audit_session);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails(UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamdetails(userLoggingsVo);
	}

	public ArrayList<ExamTimetablePojo> getclassdetails(UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getclassdetails(userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmListYear(String currentaccyear, UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmListYear(currentaccyear,userLoggingsVo);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear(String currentaccyear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableListYear();
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableClassList(accYear);
	}

	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getHeading(exampojo);
	}

	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getClassExamTimeTableDetails(exampojo);
	}

	public String insertExam(ExamDetailsForm addExam) {
		ExamTimeTableService insertobj = new ExamTimeTableServiceImpl();

		return insertobj.insertExam(addExam);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear, String schoolLocation, UserLoggingsPojo custdetails) {
		ExamTimeTableService insertobj = new ExamTimeTableServiceImpl();
		return insertobj.getexamsettingsDetails(accyear,schoolLocation,custdetails);
	}

	public String deleteexamSettings(String deleteid, String location, String accyear, ExamDetailsForm addExam) {
		ExamTimeTableService deleteobj = new ExamTimeTableServiceImpl();
		return deleteobj.deleteexamSettings(deleteid,location,accyear,addExam);
	}

	public String editexamsettings(String editid, ExamDetailsForm editExam) {
		ExamTimeTableService editobj = new ExamTimeTableServiceImpl();
		return editobj.editexamsettings(editid,editExam);
	}

	public String checkduplicateExam(String accyear, String examcode, String location,UserLoggingsPojo userLoggingsVo, String classid) {
		ExamTimeTableService dupexam = new ExamTimeTableServiceImpl();
		return dupexam.checkduplicateExam(accyear,examcode,location,userLoggingsVo,classid);
	}

	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(String accyear, String schoolLocation,UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getstatusexamsettingsDetails(accyear,schoolLocation,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmTimeTableClassSubjectList(exampojo);
	}

	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid,UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getEaxmMarksListYear(accyear,locid,userLoggingsVo);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails(String locid) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamdetails(locid);
	}

	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);
	}

	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname, String examId, String log_audit_session) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.saveDependency(examcode,dependency,mainexamcode,mainexamname,examId,log_audit_session);
	}

	public String insertGradeDependent(String project, String assignment,
			String practical,String attendance,String classId,String sectionId,String exam,String location,String accyear, String log_audit_session) {
		ExamTimeTableService serviceobj = new ExamTimeTableServiceImpl();

		return serviceobj.insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear,log_audit_session);
	}

	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getstatusgrdDepDetails(accyear,hschoolLocation);
	}

	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.disstudepdetails(accyear,locid);
	}

	public ArrayList<ExaminationDetailsVo> getExamTypeList(UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamTypeList(custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetailsonClass(String accyearid, String location,
			UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getexamsettingsDetailsonClass(accyearid,location,custdetails,classid);
	}

	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear,
			UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamNameList(locationid,classid,accyear,userLoggingsVo);
	}

	public String resultDeclare(String accyear, String examcode, String location, UserLoggingsPojo custdetails, String msg) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.resultDeclare(accyear,examcode,location,custdetails,msg);
	}

	public String timetableset(String examid, String location, String accyear, UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.timetableset(examid,location,accyear,custdetails);
	}

	public ArrayList<ExaminationDetailsVo> getexamDeclared(String academic_year, String location, UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getexamDeclared(academic_year,location,custdetails,classid);
	}

	public ArrayList<ExaminationDetailsVo> getExamNameListByPeriodicExam(String locationid, String classid, String accyear, UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getExamNameListByPeriodicExam(locationid,accyear,custdetails,classid);
	}

	public ArrayList<ExaminationDetailsVo> getHalflyExamNameList(String locationid, String classid, String accyear, UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getHalflyExamNameList(locationid,accyear,custdetails,classid);
	}

	public ArrayList<ExaminationDetailsVo> getYearlyExamNameList(String locationid, String classid, String accyear, UserLoggingsPojo custdetails) {
		ExamTimeTableService getexamdetails = new ExamTimeTableServiceImpl();
		return getexamdetails.getYearlyExamNameList(locationid,accyear,custdetails,classid);
	}
}
