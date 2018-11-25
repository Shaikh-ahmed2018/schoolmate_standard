package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;

public interface TimeTableService {
	public ArrayList<TimeTableVo> getTimeTableDetails(String timetableDetails, String details2, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TimeTableVo> getClassName();

	public ArrayList<TimeTableVo> getSectionName(String classid);

	public String updateTimeTableDetails(TimeTablePojo pojo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TimeTableVo> getTeacherTimeTableDetails(
			String teacherid, String accyearid);
	
	public ArrayList<TeacherTimeTableVo> getTeacherName();
	
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo);
	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String viewBy, UserLoggingsPojo custdetails);
	
	public ArrayList<TimeTableVo> getClassSectionList(UserLoggingsPojo userLoggingsVo);

	public Object getClassNameDetailsService(String classid, UserLoggingsPojo userLoggingsVo);

	public Object getSectionNameDetailsService(String sectionid, UserLoggingsPojo userLoggingsVo);

	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy,	String classId, String locationId,UserLoggingsPojo custdetails, String section);

	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid, String classId, String sectionId,UserLoggingsPojo custdetails);

	public Object getTeacherNameDetails(String classId, String sectionId, UserLoggingsPojo userLoggingsVo);

	public String checkTeacher(String dayId, String teacherId, UserLoggingsPojo userLoggingsVo, String location, String period);
}
