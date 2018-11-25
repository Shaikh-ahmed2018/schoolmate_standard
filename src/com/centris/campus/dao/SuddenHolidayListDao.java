package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListDao {

	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList(UserLoggingsPojo pojo1, String schoolLocation);

	String AddSuddenHoliday(SuddenHolidaysPojo upojo, UserLoggingsPojo pojo1);

	String storeSuudenHolidaysSections(SuddenHolidaysPojo upojo, UserLoggingsPojo pojo1);

	boolean validateSuddenHolidaysSms(String date, String smstext, UserLoggingsPojo pojo);

	ArrayList<SuddenHolidaySMSVO> OtherSMSList(UserLoggingsPojo userLoggingsVo);

}
