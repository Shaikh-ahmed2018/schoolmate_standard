package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SuddenHolidayListService;
import com.centris.campus.serviceImpl.SuddenHolidayListServiceImpl;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListBD {
	
	SuddenHolidayListService holidayListService = new SuddenHolidayListServiceImpl();

	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList(UserLoggingsPojo pojo1, String schoolLocation) {
		return holidayListService.SuddenHolidayList(pojo1,schoolLocation);
	}

	public String AddSuddenHoliday(SuddenHolidaySMSVO form2, UserLoggingsPojo pojo1) {
		return holidayListService.AddSuddenHoliday(form2,pojo1);
	}

	public boolean validateSuddenHolidaysSms(String date, String smstext, UserLoggingsPojo pojo) {
		return holidayListService.validateSuddenHolidaysSms(date,smstext,pojo);
	}

	public ArrayList<SuddenHolidaySMSVO> OtherSMSList(UserLoggingsPojo userLoggingsVo) {
		return holidayListService.OtherSMSList(userLoggingsVo);
	}
	

}
