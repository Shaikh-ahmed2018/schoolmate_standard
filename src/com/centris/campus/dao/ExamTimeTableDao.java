package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamTimeTableDao {

	public List<ExamTimetablePojo> getExamDetails();

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam, UserLoggingsPojo userLoggingsVo);

	public ExamTimetablePojo getExamDate(String examId, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId);

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist,UserLoggingsPojo userLoggingsVo, String log_audit_session);

	public ArrayList<ExamTimetablePojo> getExamdetails(UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExamTimetablePojo> getclassdetails(UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> getEaxmListYear(String custid, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear();

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear);

	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo);

	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo);

	public String insertExam(ExamDetailsForm addExam);

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear, String schoolLocation, UserLoggingsPojo custdetails);

	public String deleteexamSettings(String deleteid, String location, String accyear,ExamDetailsForm addExam);

	public String editexamsettings(String editid, ExamDetailsForm editExam);

	public String checkduplicateExam(String accyear, String gradename, String location,UserLoggingsPojo userLoggingsVo, String classid);

	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation, UserLoggingsPojo custdetails);

	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo);

	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExamTimetablePojo> getExamdetails(String locid);

	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode);

	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname, String examId, String log_audit_session);

	public String insertGradeDependent(String project, String assignment,
			String internal, String attendance, String classId, String sectionId, String exam, String location, String accyear, String log_audit_session);

	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation);

	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid);

	public ArrayList<ExaminationDetailsVo> getExamTypeList(UserLoggingsPojo custdetails);

	public ArrayList<ExaminationDetailsVo> getexamsettingsDetailsonClass(String accyearid, String location,
			UserLoggingsPojo custdetails, String classid);

	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear,
			UserLoggingsPojo userLoggingsVo);

	public String resultDeclare(String accyear, String examcode, String location, UserLoggingsPojo custdetails, String msg);

	public String timetableset(String examid, String location, String accyear, UserLoggingsPojo custdetails);

	public ArrayList<ExaminationDetailsVo> getexamDeclared(String academic_year, String location, UserLoggingsPojo custdetails, String classid);

	public ArrayList<ExaminationDetailsVo> getExamNameListByPeriodicExam(String locationid, String accyear, UserLoggingsPojo custdetails, String classid);

	public ArrayList<ExaminationDetailsVo> getHalflyExamNameList(String locationid, String accyear, UserLoggingsPojo custdetails, String classid);

	public ArrayList<ExaminationDetailsVo> getYearlyExamNameList(String locationid, String accyear, UserLoggingsPojo custdetails, String classid);

	

}
