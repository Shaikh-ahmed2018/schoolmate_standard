package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public interface StudentAttendanceDao {

	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo, UserLoggingsPojo custdetails);
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo, UserLoggingsPojo custdetails);

	List<LstmsStudentPOJO> getAllStudentDao(String classVal, String sectionVal, String locationid, UserLoggingsPojo custid);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportDao(
			StudentAttendanceReportVo vo);

	ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportDao(
			StudentAttendanceReportVo stuvo,UserLoggingsPojo userLoggingsVo);

	StreamDetailsVO getStreamNameDaoImpl(String stream, UserLoggingsPojo custid);

	ClassPojo getClassNameDaoImpl(String classname, UserLoggingsPojo custid);

	SectionPojo getSectionNameDaoImpl(String sectionname, UserLoggingsPojo custid);

	ParentVO getStudentNameDaoImpl(String student, UserLoggingsPojo custid);
	
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails);
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails);
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId,String locationId, UserLoggingsPojo custid);
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo, UserLoggingsPojo custdetails);
	public List<LstmsStudentPOJO> getStudentByTransport(String classId, String sectionId, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, String sectionid, String specialization, String startdate, String enddate, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getTeacherList(String locationid, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear,String classname, String sectionid, String teacherid, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate, UserLoggingsPojo custid);
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceListByDownload(String startdate, String endDate,
			String acyearid, String locId, String classId, String section, UserLoggingsPojo custid);
	public String getAccyID(String academic_year, UserLoggingsPojo custId, String location_id);
	public ArrayList<StudentAttendanceVo> getStudentDetailsAcademicWise(UserLoggingsPojo userLoggingsVo, String location);

	public int getperiodcount(String locId, String clsId, UserLoggingsPojo custdetails);
	public String NewupdateAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails);

	public ArrayList<StudentAttendanceVo> todayStudentAttendance(UserLoggingsPojo userLoggingsVo, String location, String accYear);
	public ArrayList<StudentAttendanceVo> houseWiseStudent(UserLoggingsPojo userLoggingsVo,String location, String accYear);
	public ArrayList<StudentAttendanceVo> getStudentAttendance(StudentAttendancePojo studentPojo,
			UserLoggingsPojo custdetails);
	public String updateNewAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,UserLoggingsPojo custdetails, String[] statusid);

	



}
