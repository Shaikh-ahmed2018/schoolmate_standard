package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.SuddenHolidayForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public interface SuddenHolidayListService {

	ArrayList<SuddenHolidaySMSVO> SuddenHolidayList(UserLoggingsPojo pojo1, String schoolLocation);

	String AddSuddenHoliday(SuddenHolidaySMSVO form2, UserLoggingsPojo pojo1);

	boolean validateSuddenHolidaysSms(String date, String smstext, UserLoggingsPojo pojo);

	ArrayList<SuddenHolidaySMSVO> OtherSMSList(UserLoggingsPojo userLoggingsVo);

}
