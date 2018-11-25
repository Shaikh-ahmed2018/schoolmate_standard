package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentAttendanceService;
import com.centris.campus.serviceImpl.StudentAttendanceServiceImpl;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceBD {

	static StudentAttendanceService attService;
	static {
		attService=new StudentAttendanceServiceImpl();
	}
	
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate,String endDate, UserLoggingsPojo custid) {
		return attService.getStudentsAttendanceList(startDate,endDate,custid);
	}
	
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo, UserLoggingsPojo custdetails) {
		return attService.getStudentAttendanceDetails(studentAttPojo,custdetails);
	}
	
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo, UserLoggingsPojo custdetails) {
		return attService.updateAttendanceStatus(studentAttPojo,custdetails);
	}
	
	public List<ParentVO> getAllStudent(String classVal, String sectionVal, String locationid, UserLoggingsPojo custid) {
		return attService.getAllStudentService(classVal,sectionVal,locationid,custid);
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportBD(
			StudentAttendanceReportVo vo) {
		return attService.getStudentAttendanceReportService(vo);
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportBD(
			StudentAttendanceReportVo stuvo,UserLoggingsPojo userLoggingsVo) {
			return attService.getStudentAttendanceListReportService(stuvo,userLoggingsVo);
	}

	public StreamDetailsVO getStreamNameBD(String stream, UserLoggingsPojo userLoggingsVo) {
			return attService.getStreamNameService(stream,userLoggingsVo);
	}

	public ClassPojo getClassNameBD(String classname, UserLoggingsPojo custid) {
			return attService.getClassNameService(classname,custid);
	}

	public SectionPojo getSectionNameBD(String sectionname, UserLoggingsPojo custid) {
			return attService.getSectionNameService(sectionname,custid);
	}

	public ParentVO getStudentNameBD(String student, UserLoggingsPojo custid) {
			return attService.getStudentNameService(student,custid);
	}

	
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
			return attService.getStudentPeriodAttendance(AttendancePojo,custdetails);
	}
	
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
			return attService.updateStudentPeriodAtt(AttendancePojo,custdetails);
	}

	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId, UserLoggingsPojo custid) {
			return attService.getteacherByClass(classId,sectionId,custid);
	}

	public ArrayList<StudentAttendanceVo> getClassSpec(String classId,String locationId, UserLoggingsPojo custid) {
		return attService.getClassSpec(classId,locationId,custid);
	}

	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo, UserLoggingsPojo custdetails) {
		return attService.editAttendance(pojo,custdetails);
	}

	public List<ParentVO> getStudentByTransport(String classId, String sectionId, UserLoggingsPojo custid) {
		return attService.getStudentByTransport(classId,sectionId,custid);
	}
	
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId,String accYear, UserLoggingsPojo custid) {
		return attService.searchStudentsAttendanceList(locationId,accYear,custid);
	}

	public List<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname, UserLoggingsPojo custid) {
		return attService.getAttendenceByClassList(locationid,accyear,classname,custid);
	}

	public List<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,String classname, 
			String sectionid, String specialization, String startdate, String enddate, UserLoggingsPojo custid) {
		return attService.getAttendenceByClassSectionList(locationid,accyear,classname,sectionid,specialization,startdate,enddate,custid);
	}

	public List<StudentAttendanceVo> getTeacherList(String locationid, UserLoggingsPojo custid) {
		return attService.getTeacherList(locationid,custid);
	}

	public List<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear, String classname,
			String sectionid, String teacherid, UserLoggingsPojo custid) {
		return attService.getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custid);
	}

	public List<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate, UserLoggingsPojo custid) {
		return attService.getAttendanceListByDate(startdate,enddate,custid);
	}

	public ArrayList<StudentAttendanceVo> getStudentsAttendanceListByDownload(String startdate, String endDate,
			String acyearid, String locId, String classId, String section, UserLoggingsPojo custId) {
		return attService.getStudentsAttendanceListByDownload(startdate,endDate,acyearid,locId,classId,section,custId);
	}

	public String getAccyID(String academic_year, UserLoggingsPojo custId, String location_id ) {
		return attService.getAccyID(academic_year,custId,location_id);
	}

	public ArrayList<StudentAttendanceVo> getStudentDetailsAcademicWise(UserLoggingsPojo userLoggingsVo, String location) {
		
		return attService.getStudentDetailsAcademicWise(userLoggingsVo, location);
	}


	public int getperiodcount(String locId, String clsId, UserLoggingsPojo custdetails) {
		
		return attService.getperiodcount(locId,clsId,custdetails);
	}

	public String NewupdateAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails) {
		// TODO Auto-generated method stub
		return attService.NewupdateAttendanceStatus(periodId,attendancepojo,custdetails);
	}


	public ArrayList<StudentAttendanceVo> todayStudentAttendance(UserLoggingsPojo userLoggingsVo, String location, String accYear) {
		
		return attService.todayStudentAttendance(userLoggingsVo, location,  accYear);
	}

	public ArrayList<StudentAttendanceVo> houseWiseStudent(UserLoggingsPojo userLoggingsVo, String location, String accYear) {
		
		return attService.houseWiseStudent(userLoggingsVo, location,  accYear);
	}

	public ArrayList<StudentAttendanceVo> getStudentAttendance(StudentAttendancePojo studentPojo,
			UserLoggingsPojo custdetails) {
		
		return attService.getStudentAttendance(studentPojo,custdetails);
	}

	public String updateNewAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails, String[] statusid) {
		
		return attService.updateNewAttendanceStatus(periodId,attendancepojo,custdetails,statusid);
	}

	
}
