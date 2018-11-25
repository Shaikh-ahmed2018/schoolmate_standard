package com.centris.campus.delegate;
import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.PeriodDetailsService;
import com.centris.campus.serviceImpl.PeriodDetailsServiceImpl;
import com.centris.campus.vo.PeriodVo;

public class PeriodDetailsBD {
  
PeriodDetailsService service=new PeriodDetailsServiceImpl();
   
	public String insertperiod(PeriodVo vo, UserLoggingsPojo userLoggingsVo) {
     
		return service.insertperiod(vo,userLoggingsVo);
	}

	public ArrayList<PeriodVo> periodList(UserLoggingsPojo custdetails) {
		
		return service.periodList(custdetails);
	}

	public PeriodVo editPeriod(String periodId, UserLoggingsPojo userLoggingsVo) {
		
		return service.editPeriod(periodId,userLoggingsVo);
	}

	public String deletePeriod(String[] periodId, UserLoggingsPojo userLoggingsVo) {
		
		return service.deletePeriod(periodId,userLoggingsVo);
	}

	public ArrayList<PeriodVo> getperiodlist(PeriodVo vo, UserLoggingsPojo custdetails) {
		return service.getperiodlist(vo,custdetails);
	}

	public String validateclassName(String clsId, UserLoggingsPojo custdetails, String locId) {
		
		return service.validateclassName(clsId,custdetails,locId);
	}

	
}
