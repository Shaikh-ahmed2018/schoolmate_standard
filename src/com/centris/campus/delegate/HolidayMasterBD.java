package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.HolidayMasterService;
import com.centris.campus.serviceImpl.HolidayMasterServiceImpl;
import com.centris.campus.serviceImpl.LocationServiceImpl;
import com.centris.campus.vo.HolidayMasterVo;


public class HolidayMasterBD {

	HolidayMasterService service;
	public String addMultipleHoliday(HolidayMasterForm formObj, String usercode, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterServiceImpl().addMultipleHoliday(formObj,usercode,userLoggingsVo);
	}

	public ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year, String schoolLocation, UserLoggingsPojo custdetails) {
		service = new HolidayMasterServiceImpl();
		return service.getHolidayDetails(academic_year,schoolLocation, custdetails);
	}

	public HolidayMasterVo editHolidayDetail(String deptId, String date) {
		service = new HolidayMasterServiceImpl();
		return service.editHolidayDetail(deptId,date);
	}

	public String addSingleHoliday(HolidayMasterPojo hpojo,UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterServiceImpl().addSingleHoliday(hpojo,userLoggingsVo);
	}

	public Boolean deleteSingleHoliday(HolidayMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterServiceImpl().deleteSingleHoliday(vo,userLoggingsVo);
	}

	public String dateValidate(String dateval, String location, String accYear, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterServiceImpl().dateValidate(dateval,location,accYear,userLoggingsVo);
	}

	public ArrayList<HolidayMasterVo> searchLocationDetails(String searchterm, String academic_year,String status, UserLoggingsPojo custdetails) {
		return new HolidayMasterServiceImpl().searchLocationDetails(searchterm,academic_year,status,custdetails);
	}



	public HolidayMasterVo editHolidayDetails(String deptId, UserLoggingsPojo userLoggingsVo) {
		service = new HolidayMasterServiceImpl();
		return service.editHolidayDetails(deptId,userLoggingsVo);
	}

	public ArrayList<HolidayMasterVo> HolidayListByStatus(HolidayMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		service = new HolidayMasterServiceImpl();
		return service.HolidayListByStatus(vo,userLoggingsVo);
	}

}
