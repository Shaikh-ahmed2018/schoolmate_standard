package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.PeriodDetailsDao;
import com.centris.campus.daoImpl.PeriodDetailsDaoImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.PeriodDetailsService;
import com.centris.campus.vo.PeriodVo;

public class PeriodDetailsServiceImpl implements PeriodDetailsService{

	PeriodDetailsDao dao=new PeriodDetailsDaoImpl();
	@Override
	public String insertperiod(PeriodVo vo, UserLoggingsPojo userLoggingsVo) {
		return dao.insertperiod(vo,userLoggingsVo);
	}
	@Override
	public ArrayList<PeriodVo> periodList(UserLoggingsPojo custdetails) {
		
		return dao.periodList(custdetails);
	}
	@Override
	public PeriodVo editPeriod(String periodId, UserLoggingsPojo userLoggingsVo) {
		
		return dao.editPeriod(periodId,userLoggingsVo);
	}
	@Override
	public String deletePeriod(String[] periodId, UserLoggingsPojo userLoggingsVo) {
		return dao.deletePeriod(periodId,userLoggingsVo);
	}
	@Override
	public ArrayList<PeriodVo> getperiodlist(PeriodVo vo, UserLoggingsPojo custdetails) {
		
		return dao.getperiodlist(vo,custdetails);
	}
	@Override
	public String validateclassName(String clsId, UserLoggingsPojo custdetails,String classId) {
		
		return dao.validateclassName(clsId,custdetails,classId);
	}

}
