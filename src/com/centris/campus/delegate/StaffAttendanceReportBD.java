package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffAttendanceReportService;
import com.centris.campus.serviceImpl.StaffAttendanceReportServiceImpl;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceReportBD {
	
	
	StaffAttendanceReportService service = new StaffAttendanceReportServiceImpl();

	public ReportMenuVo getSelectedItems(String acc, UserLoggingsPojo userLoggingsVo) {
		
		
		return service.getSelectedItemsService(acc,userLoggingsVo);
	}

	public ArrayList<StaffAttendanceVo> getStaffAttendanceReportBD(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
		
		return service.getStaffAttendanceReportService(vo,userLoggingsVo);
	}

	public List<AllTeacherDetailsVo> getTeachingListBD(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo) {
		
		return service.getTeachingListService(vo,userLoggingsVo);
	}

	public List<AllTeacherDetailsVo> getNonTeachingListBD(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo) {
		return service.getNonTeachingListService(vo,userLoggingsVo);
	}

	public StaffAttendanceVo getSelectedTeacherNameReportBD(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo) {
	
		return service.getSelectedTeacherNameReportService(vo,userLoggingsVo);
	}
	
	
	
	
	
	

}
