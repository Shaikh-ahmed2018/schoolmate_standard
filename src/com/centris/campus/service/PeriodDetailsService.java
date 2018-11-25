package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PeriodVo;

public interface PeriodDetailsService {

	String insertperiod(PeriodVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<PeriodVo> periodList(UserLoggingsPojo custdetails);

	PeriodVo editPeriod(String periodId, UserLoggingsPojo userLoggingsVo);

	String deletePeriod(String[] periodId, UserLoggingsPojo userLoggingsVo);

	ArrayList<PeriodVo> getperiodlist(PeriodVo vo, UserLoggingsPojo custdetails);

	String validateclassName(String clsId, UserLoggingsPojo custdetails, String locId);

}
