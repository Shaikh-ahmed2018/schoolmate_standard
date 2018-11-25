package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TimeTableService;
import com.centris.campus.serviceImpl.TimeTableServiceImpl;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;

public class TimeTableBD {

	TimeTableService obj = new TimeTableServiceImpl();

	public ArrayList<TimeTableVo> getTimeTableDetails(String timetableDetails, String details2, UserLoggingsPojo userLoggingsVo) {
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();

		details = obj.getTimeTableDetails(timetableDetails,details2,userLoggingsVo);

		return details;
	}

	public ArrayList<TimeTableVo> getClassName() {
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();

		details = obj.getClassName();

		return details;
	}

	public ArrayList<TimeTableVo> getSectionName(String classid) {
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();

		details = obj.getSectionName(classid);

		return details;
	}

	public String updateTimeTableDetails(TimeTablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		String result = obj.updateTimeTableDetails(pojo,userLoggingsVo);
		return result;
	}

	public ArrayList<TimeTableVo> getTeacherTimeTableDetails(
			String teacherid, String accyearid) {
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();

		details = obj.getTeacherTimeTableDetails(teacherid, accyearid);

		return details;
	}

	public ArrayList<TeacherTimeTableVo> getTeacherName() {
		ArrayList<TeacherTimeTableVo> details = new ArrayList<TeacherTimeTableVo>();

		details = obj.getTeacherName();

		return details;
	}
	
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo) {
		String result = obj.updateTeacherTimeTableDetails(pojo);
		return result;
	}

	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String viewBy, UserLoggingsPojo custdetails) {
		
		return obj.getClassTimeTableList(accyearid,viewBy,custdetails);
	}
	
	public ArrayList<TimeTableVo> getClassSectionList(UserLoggingsPojo userLoggingsVo){
		
		return obj.getClassSectionList(userLoggingsVo);
	}

	public Object getClassNameDetails(String classid, UserLoggingsPojo userLoggingsVo) {
		
		return obj.getClassNameDetailsService(classid,userLoggingsVo);
	}

	public Object getSectionNameDetailsBD(String sectionid, UserLoggingsPojo userLoggingsVo) {
		
		return obj.getSectionNameDetailsService(sectionid,userLoggingsVo);
	}

	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy,String classId, String locationId,UserLoggingsPojo custdetails, String section) {
		return obj.getClassTimeTableListByClass(accyearid,viewBy,classId,locationId,custdetails,section);
	}
	
	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid,String classId,String sectionId,UserLoggingsPojo custdetails) {
		return obj.getClassTimeTableListBySection(accyearid,classId,sectionId,custdetails);
	}

	public Object getTeacherNameDetails(String classId, String sectionId,UserLoggingsPojo userLoggingsVo) {
		return obj.getTeacherNameDetails(classId,sectionId,userLoggingsVo);
	}

	public String checkTeacher(String dayId, String teacherId, UserLoggingsPojo userLoggingsVo, String location, String period) {
		return obj.checkTeacher(dayId,teacherId,userLoggingsVo,location,period);
		}
	

}