package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceDao {
	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department, UserLoggingsPojo custdetails, String locId);
	public String updateAttendanceStatus(StaffAttendancePojo attPojo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate, UserLoggingsPojo userLoggingsVo, String locationId, String accYrar);
	public ArrayList<StaffAttendanceVo> getStaffAttendanceCount(String startdate, UserLoggingsPojo userLoggingsVo, String msg, String location);

}
