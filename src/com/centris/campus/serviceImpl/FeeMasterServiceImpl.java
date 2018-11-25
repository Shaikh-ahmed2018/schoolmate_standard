package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.FeeMasterDAO;
import com.centris.campus.daoImpl.FeeMasterDAOIMPL;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;

import com.centris.campus.vo.ReportMenuVo;


public class FeeMasterServiceImpl implements FeeMasterService

{
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	
	FeeMasterDAO obj_fee = new FeeMasterDAOIMPL();
	
	public String insertFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo) {
		
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterServiceImpl :insertFeeDetails  Starting");
		// TODO Auto-generated method stub
		
		String check="";
		FeeMasterDAO feeSetupDao = new FeeMasterDAOIMPL();
		
		if (vo.getId().equalsIgnoreCase(""))
			
		{
		try
		{
			check = feeSetupDao.insertFeeDetails(vo,pojo);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		}
		
		else if (!vo.getId().equalsIgnoreCase(""))
		{
			try
			{
				check = feeSetupDao.updateFeeDetails(vo,pojo);
			}
			
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterServiceImpl :insertFeeDetails Ending");
		return check;
		
		
	}

	
	public boolean getnamecount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		
		return obj_fee.getnamecount(vo,userLoggingsVo);
	}


	
	public ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo,UserLoggingsPojo custdetails ) {
		
		return obj_fee.getfeedetails(vo,custdetails);
	}


	
	public AddFeeVO editFeeDetails(AddFeeVO vo,UserLoggingsPojo dbdetails) {
		
		return obj_fee.editFeeDetails(vo,dbdetails);
	}


	
	public boolean deleteFeeDetails(AddFeeVO vo,String button, UserLoggingsPojo userLoggingsVo) {
		
		return obj_fee.deleteFeeDetails(vo,button,userLoggingsVo);
		
	}


	
	public ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo,UserLoggingsPojo userLoggingsVo) {
	
		return obj_fee.searchFeeDetails(vo,userLoggingsVo);
	}

	@Override
	public List<ReportMenuVo> getclasslistService() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterServiceImpl :getclasslistService  Starting");
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterServiceImpl :getclasslistService Ending");
		
		return obj_fee.getclasslistDao();
	}



	public ArrayList<AddFeeVO> feeTypeListService(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		return obj_fee.feeTypeListDao(vo,userLoggingsVo);
	}


	public String getFeeTypeCount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		
		return obj_fee.getFeeTypeCount(vo,userLoggingsVo);
	}


	
	
}
