package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.ClassTeacherLsitDao;
import com.centris.campus.daoImpl.ClassTeacherLsitDaoImpl;
import com.centris.campus.daoImpl.ClassTeacherMappingDAOIMPL;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassTeacherLsitService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherVo;
import com.centris.campus.vo.TeacherVo;

public class ClassTeacherLsitServiceImpl implements ClassTeacherLsitService{

	private static final Logger logger = Logger.getLogger(ClassTeacherLsitServiceImpl.class);
	
	ClassTeacherLsitDao dao = new ClassTeacherLsitDaoImpl();
	
	public ArrayList<ClassTeacherVo> getClassTeacherListService(UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getClassTeacherListService Starting");
		
		ArrayList<ClassTeacherVo> list = null;
		try {
			
			list = dao.getClassTeacherListDao(custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getClassTeacherListService Ending");
		
		return list;
	}

	
	
	
	public ClassTeacherVo editClassTeacherService(ClassTeacherVo vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : editClassTeacherService Starting");
		
		
		ClassTeacherVo teaval=null;
		
		try {
			
			teaval = dao.editClassTeacherDao(vo,userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : editClassTeacherService Ending");
		
		return teaval;
	}




	
	public String saveClassTeacherService(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : saveClassTeacherService Starting");
		
		String check = "";
		try {
			check = dao.saveClassTeacherDao(vo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : saveClassTeacherService Ending");
		
		return check;
	}




	public boolean validateClassTeacherService(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : validateClassTeacherService Starting");
		
		

		boolean classteacher_validate = false;
		
		try {
			classteacher_validate = dao.validateClassTeacherDao(vo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : validateClassTeacherService Ending");
		
		
		return classteacher_validate;
	}




	
	public ArrayList<ClassTeacherVo> getSearchClassTeacherListService(String searchTerm,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getSearchClassTeacherListService Starting");
		
		
		ArrayList<ClassTeacherVo> list = null;
		try {
			
			list = dao.getSearchClassTeacherListDao(searchTerm,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getSearchClassTeacherListService Ending");
		
		
		
		return list;
	}




	@Override
	public ArrayList<ClassTeacherVo> getFilterdClassTeacherListBD(String accYear, String locationId, String classId,
			String sectionId, UserLoggingsPojo userLoggingsVo, String dep, String searchVal) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getFilterdClassTeacherListBD Starting");
		
		
		ArrayList<ClassTeacherVo> list = null;
		try {
			list = dao.getFilterdClassTeacherListBD(accYear,locationId,classId,sectionId,userLoggingsVo,dep,searchVal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitServiceImpl : getFilterdClassTeacherListBD Ending");
		return list;
	
	}
	

}
