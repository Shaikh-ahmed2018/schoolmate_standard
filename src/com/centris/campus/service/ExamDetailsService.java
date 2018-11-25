package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ExaminationDetailsVo;

public interface ExamDetailsService {

	public List<ExaminationDetailsVo> getExamDetailsService(UserLoggingsPojo custdetails);


	public String getaccyName(String accyear, UserLoggingsPojo userLoggingsVo);

	public String insertGradeSettings(ExamDetailsPojo obj);

	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear, String location,UserLoggingsPojo custdetails);

	public String deleteGradeSettings(String gradeid, String location, String accyear, ExamDetailsPojo obj);

	public String editGradeSettings(ExaminationDetailsVo list,UserLoggingsPojo userLoggingsVo);

	public String checkduplicateGrade(String accyear, String gradename, String loc,UserLoggingsPojo custdetails);


    public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear,
			String examid, String locid, String classid, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear);

	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear, String schoolLocation,UserLoggingsPojo custdetails);

    public String getexamName(String examid, String accyear, String locid, UserLoggingsPojo custdetails);

    public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo);

	public String getsubDetails(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj, String schoolLocation, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	public String insertmarkentrydetails(
			ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo);

	public String getlocationname(String schoolLocation, UserLoggingsPojo userLoggingsVo);

	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj, String schoolLocation);

	public String getclassname(String classid, UserLoggingsPojo userLoggingsVo);

	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid,UserLoggingsPojo userLoggingsVo, String classid);

	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation);


	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation);


	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear, String location, UserLoggingsPojo userLoggingsVo);

	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, String examid, UserLoggingsPojo userLoggingsVo);
	
	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, UserLoggingsPojo userLoggingsVo);

	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo);


	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo);


	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1, String[] startdate, UserLoggingsPojo userLoggingsVo);


	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid, UserLoggingsPojo userLoggingsVo);


	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid,UserLoggingsPojo custdetails);


	public String checkduplicatedate(ExamTimetablePojo pojo);


	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails);


	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate, UserLoggingsPojo custdetails);


	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate);


	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid);


	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,
			String classid,UserLoggingsPojo userLoggingsVo);


	public String insertmarkentrydetailsSubjectWise(ExaminationDetailsVo obj, UserLoggingsPojo userLoggingsVo);


	public List<ExaminationDetailsVo> getExamByClassWise(String locid, String accyear, String classid,
			UserLoggingsPojo custdetails);


	public List<ExaminationDetailsVo> getStudentMarkListSearch(ExaminationDetailsVo vo, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> getReportCardSettingList(String accyear, String locid, UserLoggingsPojo custdetails);


	public List<ExaminationDetailsVo> getMaximumMarkClassList(String accyear, String locid, UserLoggingsPojo custdetails, String classId);


	public ArrayList<ExaminationDetailsVo> classWiseSubjectInMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);


	public ArrayList<ExaminationDetailsVo> classWiseSubjectWithLabMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

	public String insertMaximumExammarkDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);


	public String insertReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);


	public ExaminationDetailsVo getTerm1ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);


	public ExaminationDetailsVo getTerm2ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);


	public ExaminationDetailsVo getAcademicReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails);

}
