package com.centris.campus.serviceImpl;


import java.util.ArrayList;

import com.centris.campus.dao.HolidayMasterSDAO;
import com.centris.campus.daoImpl.HolidayMasterDAOImpl;
import com.centris.campus.forms.HolidayMasterForm;
import com.centris.campus.pojo.HolidayMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.HolidayMasterService;
import com.centris.campus.vo.HolidayMasterVo;
 
public class HolidayMasterServiceImpl  implements HolidayMasterService {

	HolidayMasterSDAO dao;
	public String addMultipleHoliday(HolidayMasterForm formObj, String usercode, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().addMultipleHoliday(formObj,usercode,userLoggingsVo);
	}

	@Override
	public ArrayList<HolidayMasterVo> getHolidayDetails(String academic_year,String schoolLocation,UserLoggingsPojo custdetails) {
		dao = new HolidayMasterDAOImpl();
		return dao.getHolidayDetails(academic_year,schoolLocation,custdetails);
	}

	@Override
	public HolidayMasterVo editHolidayDetail(String deptId, String date) {
		dao = new HolidayMasterDAOImpl();
		return dao.editHolidayDetail(deptId,date);
	}

	public String addSingleHoliday(HolidayMasterPojo hpojo,UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().addSingleHolidayDetailDaoImpl(hpojo,userLoggingsVo);
	}

	public Boolean deleteSingleHoliday(HolidayMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().deleteSingleHoliday(vo,userLoggingsVo);
	}

	public String dateValidate(String dateval, String location, String accYear, UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().dateValidate(dateval,location,accYear,userLoggingsVo);
	}

	public ArrayList<HolidayMasterVo> searchLocationDetails(String searchterm, String academic_year,String status, UserLoggingsPojo custdetails) {
		return new HolidayMasterDAOImpl().searchLocationDetails(searchterm,academic_year,status,custdetails);
	}


	@Override
	public HolidayMasterVo editHolidayDetails(String deptId,UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().editHolidayDetails(deptId, userLoggingsVo);
	}

	@Override
	public ArrayList<HolidayMasterVo> HolidayListByStatus(HolidayMasterVo vo,UserLoggingsPojo userLoggingsVo) {
		return new HolidayMasterDAOImpl().HolidayListByStatus(vo,userLoggingsVo);
	}

}
