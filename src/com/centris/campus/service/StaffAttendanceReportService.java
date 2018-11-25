package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceReportService {

	ReportMenuVo getSelectedItemsService(String acc, UserLoggingsPojo userLoggingsVo);

	ArrayList<StaffAttendanceVo> getStaffAttendanceReportService(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);

	List<AllTeacherDetailsVo> getTeachingListService(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo);

	List<AllTeacherDetailsVo> getNonTeachingListService(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo);

	StaffAttendanceVo getSelectedTeacherNameReportService(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);

}
