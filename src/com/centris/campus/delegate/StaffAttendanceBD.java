package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffAttendanceService;
import com.centris.campus.serviceImpl.StaffAttendanceServiceImpl;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceBD {
	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department, UserLoggingsPojo custdetails, String locId) {
			
			StaffAttendanceService service=new StaffAttendanceServiceImpl();
			
			return service.getStaffAttendance(date, department,custdetails,locId);
	}
	
	public String updateAttendanceStatus(StaffAttendancePojo attPojo, UserLoggingsPojo userLoggingsVo) {
		
		StaffAttendanceService service=new StaffAttendanceServiceImpl();
		
		return service.updateAttendanceStatus(attPojo,userLoggingsVo);
	}
	
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate,UserLoggingsPojo userLoggingsVo, String locationId, String accYear) {
		
		StaffAttendanceService service=new StaffAttendanceServiceImpl();
		
		return service.getStaffAttendanceList(startdate,enddate,userLoggingsVo,locationId,accYear);
	}

	public ArrayList<StaffAttendanceVo> getStaffAttendanceCount(String startdate, UserLoggingsPojo userLoggingsVo, String msg, String location) {
		
		StaffAttendanceService service=new StaffAttendanceServiceImpl();
		
		return service.getStaffAttendanceCount(startdate,userLoggingsVo,msg,location);
	}

}
