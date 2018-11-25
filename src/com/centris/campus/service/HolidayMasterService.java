package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.HolidayMasterVo;

public interface HolidayMasterService {

	ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation, UserLoggingsPojo custdetails);

	HolidayMasterVo editHolidayDetail(String deptId, String date);

	HolidayMasterVo editHolidayDetails(String deptId,UserLoggingsPojo userLoggingsVo);

	ArrayList<HolidayMasterVo> HolidayListByStatus(HolidayMasterVo vo, UserLoggingsPojo userLoggingsVo);

}
