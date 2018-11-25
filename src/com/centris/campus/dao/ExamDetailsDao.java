package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamDetailsDao {

	List<ExamDetailsPojo> getExamDetailsDao(UserLoggingsPojo custdetails);

	String getaccyName(String accyear, UserLoggingsPojo userLoggingsVo);


	String insertGradeSettings(ExamDetailsPojo obj);

	ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location,UserLoggingsPojo custdetails);

	String deleteGradeSettings(String gradeid, String locname, String accyear, ExamDetailsPojo obj);

	String editGradeSettings(ExaminationDetailsVo gradeid,UserLoggingsPojo userLoggingsVo);

	String checkduplicateGrade(String accyear, String gradename, String loc,UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear);

	ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation,UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid, String locid, String custid, UserLoggingsPojo custdetails);

	String getexamName(String examid, String accyear, String locid, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getexamclassDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo);

	String getsubDetails(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo);


	ArrayList<ExaminationDetailsVo> classWiseStudent(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

    ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	String insertmarkentrydetails(
			ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo);


	String getlocationname(String schoolLocation, UserLoggingsPojo pojo);


	ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,
			String schoolLocation,UserLoggingsPojo custdetails);

	String insertmarkentrysubjectwise(ExaminationDetailsVo obj, String schoolLocation);

	String getclassname(String classid, UserLoggingsPojo pojo);

	ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid,UserLoggingsPojo userLoggingsVo, String classid);

	ArrayList<ExaminationDetailsVo> getlistofExamCodes(String schoolLocation);

	String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation);

	ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String loc, UserLoggingsPojo userLoggingsVo);

	List<ExaminationDetailsVo> getExamClassByLocation(String loc, String accyear, String examid, UserLoggingsPojo userLoggingsVo);

	List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo);

	ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo);

	String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate, UserLoggingsPojo userLoggingsVo);

	ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid, UserLoggingsPojo userLoggingsVo);

	List<ExaminationDetailsVo> getExamClassByLocation(String accyear, String locid, UserLoggingsPojo userLoggingsVo);

	ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid,UserLoggingsPojo custdetails);

	String checkduplicatedate(ExamTimetablePojo pojo);

	ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);

	String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate, UserLoggingsPojo custdetails);

	String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate);

	ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear,
			String locid);

	ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid, String classid,
			UserLoggingsPojo userLoggingsVo);

	String insertmarkentrydetailsSubjectWise(ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo);

	List<ExaminationDetailsVo> getExamByClassWise(String locid, String accyear, String classid,
			UserLoggingsPojo custdetails);

	List<ExaminationDetailsVo> getStudentMarkListSearch(ExaminationDetailsVo vo, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> getReportCardSettingList(String accyear, String locid, UserLoggingsPojo custdetails);

	List<ExaminationDetailsVo> getMaximumMarkClassList(String accyear, String locid, UserLoggingsPojo custdetails, String classId);

	ArrayList<ExaminationDetailsVo> classWiseSubjectInMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	ArrayList<ExaminationDetailsVo> classWiseSubjectWithLabMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	String insertMaximumExammarkDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	String insertReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	ExaminationDetailsVo getTerm1ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	ExaminationDetailsVo getTerm2ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	ExaminationDetailsVo getAcademicReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

}
