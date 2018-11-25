package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.HolidayMasterVo;

public interface HolidayMasterSDAO {

	ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation, UserLoggingsPojo custdetails);

	HolidayMasterVo editHolidayDetail(String deptId, String date);

}
