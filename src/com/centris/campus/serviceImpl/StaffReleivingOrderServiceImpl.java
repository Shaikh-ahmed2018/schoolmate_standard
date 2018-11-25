package com.centris.campus.serviceImpl;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StaffReleivingOrderDao;
import com.centris.campus.daoImpl.StaffReleivingOrderDaoImpl;
import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffReleivingOrderService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;

public class StaffReleivingOrderServiceImpl implements StaffReleivingOrderService{
	
	private static final Logger logger = Logger.getLogger(StaffReleivingOrderServiceImpl.class);
	
	StaffReleivingOrderDao dao = new StaffReleivingOrderDaoImpl();

	
	public List<AllTeacherDetailsVo> getTeachingListService(
			AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getTeachingListService Starting");
		
		 List<AllTeacherDetailsVo> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getTeachingListDaoImpl(vo,userLoggingsVo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getTeachingListService Ending");
		
		
		return teachinglist;
	}


	
	public List<AllTeacherDetailsVo> getNonTeachingListService(
			AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getNonTeachingListService Starting");
		
		 List<AllTeacherDetailsVo> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getNonTeachingListDaoImpl(vo,userLoggingsVo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getNonTeachingListService Ending");
		
		
		return teachinglist;
	}



	
	public List<UserDetailVO> getUsersListService(UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getUsersListService Starting");
		
		 List<UserDetailVO> teachinglist = null;
		 
		 try {
			
			 
			 teachinglist=dao.getUsersListDaoImpl(userLoggingsVo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getUsersListService Ending");
		
		
		return teachinglist;
	}



	
	public List<ReleivingOrderVo> getReleivingDetailsService(
			RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getReleivingDetailsService Starting");
		
       List<ReleivingOrderVo> list = null;
		 
		 try {
			
			 
			 list=dao.getReleivingDetailsDaoImpl(pojo,userLoggingsVo);
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderServiceImpl : getReleivingDetailsService Ending");
		
		return list;
	}



	@Override
	public List<RelievingOrderPojo> SearchRelievingOrder(RelievingOrderPojo pojo,UserLoggingsPojo custdetails) {
		StaffReleivingOrderDao dao = new StaffReleivingOrderDaoImpl();
		return dao.SearchRelievingOrder(pojo,custdetails);
	}



	@Override
	public List<RelievingOrderPojo> staffReleivingPDFReport(String[] teachercode,RelievingOrderPojo pojo, UserLoggingsPojo userLoggingsVo) {
		StaffReleivingOrderDao dao = new StaffReleivingOrderDaoImpl();
		return dao.staffReleivingPDFReport(teachercode,pojo,userLoggingsVo);
	}
	
	
	

}
