package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public interface StaffAttendanceReportDao {

	ReportMenuVo getSelectedItemsDaoImpl(String acc, UserLoggingsPojo custid);

	ArrayList<StaffAttendanceVo> getStaffAttendanceReportDaoImpl(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<AllTeacherDetailsVo> getTeachingListDaoImpl(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<AllTeacherDetailsVo> getNonTeachingListDaoImpl(AllTeacherDetailsVo vo, UserLoggingsPojo userLoggingsVo);

	StaffAttendanceVo getSelectedTeacherNameReportDao(ReportMenuVo vo, UserLoggingsPojo userLoggingsVo);

}
