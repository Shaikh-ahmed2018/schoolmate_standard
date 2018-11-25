package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SuddenHolidayListDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.SuddenHolidayListDaoImpl;
import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SuddenHolidayListService;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListServiceImpl implements SuddenHolidayListService{
	private static Logger logger = Logger.getLogger(SuddenHolidayListServiceImpl.class);

	SuddenHolidayListDao holidayListDao = new SuddenHolidayListDaoImpl();


	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList(UserLoggingsPojo pojo1,String schoolLocation) {

		return holidayListDao.SuddenHolidayList(pojo1,schoolLocation);
	}

	public String AddSuddenHoliday(SuddenHolidaySMSVO form2,UserLoggingsPojo pojo1) {


		String suddenholidayscode = null;
		String status = null;

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListServiceImpl: AddSuddenHoliday : Starting");

		try {
			suddenholidayscode = IDGenerator.getPrimaryKeyID("sms_suddenholidays_details",pojo1);

			SuddenHolidaysPojo upojo = new SuddenHolidaysPojo();
			upojo.setDbDetails(form2.getDbDetails());
			upojo.setClassid(form2.getClassListArray());
			upojo.setLocId(form2.getLocId());
			upojo.setSectionid(form2.getSectionListArray());
			upojo.setHdate(HelperClass.convertUIToDatabase(form2.getHdate()));
			upojo.setSmstext(form2.getSmstext());
			upojo.setCreatedby(form2.getCreatedUser());
			upojo.setIssection(1);
			upojo.setIsstudent(0);
			upojo.setCreatedate(HelperClass.getCurrentTimestamp());
			upojo.setAcc_Year(form2.getAccYear());
			upojo.setSmsCharacters(form2.getSmsCharacters());
			upojo.setSuddenholidayscode(suddenholidayscode);
			upojo.setBalanceSMS(form2.getBalanceSMS());
			
			status = holidayListDao.storeSuudenHolidaysSections(upojo,pojo1);
			
			if(status.equalsIgnoreCase("true")){
				
				status = holidayListDao.AddSuddenHoliday(upojo,pojo1);
				
			}else if(status.equalsIgnoreCase("insufficientSMSBalance")){
				
				status = "insufficientSMSBalance";
			}else{
				status = "false";
			}


		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListServiceImpl: AddSuddenHoliday Ending");
		
		return status;

	}

	@Override
	public boolean validateSuddenHolidaysSms(String date, String smstext, UserLoggingsPojo pojo) {

		return holidayListDao.validateSuddenHolidaysSms(date,smstext,pojo);
	}

	@Override
	public ArrayList<SuddenHolidaySMSVO> OtherSMSList(UserLoggingsPojo userLoggingsVo) {
		return  holidayListDao.OtherSMSList(userLoggingsVo);
	}

}
