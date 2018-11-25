package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.ClassDao;
import com.centris.campus.daoImpl.ClassDaoImpl;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class ClassServiceImpl implements ClassService{
	private static final Logger logger = Logger.getLogger(ClassServiceImpl.class);
	@Override
	public synchronized List<ClassPojo> getClassDetails(String schoolLocation,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl: getClassDetails Starting");
		
		List<ClassPojo> classList = new ArrayList<ClassPojo>();
		ClassDao classDao=new ClassDaoImpl();
		//classList = classDao.getClassDetails(schoolLocation);
		try {
			classList=classDao.getClassDetails(schoolLocation,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl: getClassDetails Ending");
		return classList;
	}
	
	@Override
	public synchronized List<ClassPojo> getStreamDetailService(String school,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl :getStreamDetailService  Starting");
		// TODO Auto-generated method stub
		List<ClassPojo> classPojoList = new ArrayList<ClassPojo>();
		ClassDao classDao=new ClassDaoImpl();
		
		try {
			classPojoList = classDao.getStreamDetailDao(school,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl :getStreamDetailService Ending");
		return classPojoList;
	}
	
	public synchronized String createClass(ClassPojo classPojo,String createUser, String classcode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : createClass  Starting");
		String status = null;
		ClassDao classDao=new ClassDaoImpl();
		try {

			status = classDao.createClass(classPojo,createUser,classcode,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : createClass  Starting");
		return status;
	}
	
	public synchronized String classNameCheck(ClassPojo classPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : classNameCheck  Starting");
		String status = null;
		ClassDao classDao=new ClassDaoImpl();
		try {

			status = classDao.classNameCheck(classPojo,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : classNameCheck  Starting");
		return status;

	}
	
	public String updateclassNameCheck(ClassPojo classPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : classNameCheck  Starting");
		String status = null;
		ClassDao classDao=new ClassDaoImpl();
		try {

			status = classDao.classNameCheck(classPojo,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : classNameCheck  Starting");
		return status;

	}
	
	public synchronized ClassPojo editClass(String classCode,String locId,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : editClass  Starting");
		ClassPojo classDetails = null;
		ClassDao classDao=new ClassDaoImpl();
		try {

			classDetails = classDao.editClass(classCode,locId,dbdetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : editClass  Starting");
		return classDetails;
	}
	
	public synchronized boolean deleteClass(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : deleteClass  Starting");
		boolean status = false;
		ClassDao classDao=new ClassDaoImpl();
		try {
			status = classDao.deleteClass(classPojo,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : deleteClass  Starting");
		return status;
	}
	
	public synchronized boolean updateClass(ClassPojo classPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : updateClass  Starting");
		boolean status = false;
		ClassDao classDao=new ClassDaoImpl();
		try {

			status = classDao.updateClass(classPojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl : updateClass  Starting");
		return status;

	}
	
	@Override
	public synchronized List<ClassPojo> searchClassDetails(String searchText,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl :searchClassDetails Starting");
		List<ClassPojo> getCourseDetailsBySearchText=new ArrayList<ClassPojo>();
		ClassDao classDao=new ClassDaoImpl();
		try{
			getCourseDetailsBySearchText=classDao.searchClassDetails(searchText,custdetails);
			
		}
		catch(Exception exception){
			exception.printStackTrace();
			
			logger.error(exception.getMessage(),exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassServiceImpl :searchClassDetails  Ending");
		return getCourseDetailsBySearchText;
	}

	public static List<ClassPojo> getClassDetailsOnChangeFunction(String streamId, String locationid, String status, UserLoggingsPojo custdetails) {
		return ClassDaoImpl.getClassDetailsOnChangeFunction( streamId,locationid,status,custdetails);
	}

	
}
