package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.StaffAttendanceReportDao;
import com.centris.campus.daoImpl.StaffAttendanceReportDaoImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffAttendanceReportService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceReportServiceImpl implements StaffAttendanceReportService {
	
	private static final Logger logger = Logger
			.getLogger(StaffAttendanceReportServiceImpl.class);
	
	
	StaffAttendanceReportDao dao = new StaffAttendanceReportDaoImpl();



	public ReportMenuVo getSelectedItemsService(String acc,UserLoggingsPojo custid) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getSelectedItemsService Starting");
		
		
		
		ReportMenuVo selected = null;
		try {
			
			selected=dao.getSelectedItemsDaoImpl(acc,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getSelectedItemsService Ending");
		
		return selected;
	}



	
	public ArrayList<StaffAttendanceVo> getStaffAttendanceReportService(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getStaffAttendanceReportService Starting");
		
		
		ArrayList<StaffAttendanceVo> list = null;
		
		try {
			
			list =dao.getStaffAttendanceReportDaoImpl(vo,userLoggingsVo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getStaffAttendanceReportService Ending");  
		
		return list;
	}




	
	public List<AllTeacherDetailsVo> getTeachingListService(
			AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getTeachingListService Starting");
		
		
		ArrayList<AllTeacherDetailsVo> list = null;
		
		try {
			
			list =dao.getTeachingListDaoImpl(vo,userLoggingsVo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getTeachingListService Ending");  
		
		return list;
	}




	
	public List<AllTeacherDetailsVo> getNonTeachingListService(
			AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getNonTeachingListService Starting");
		
		
		ArrayList<AllTeacherDetailsVo> list = null;
		
		try {
			
			list =dao.getNonTeachingListDaoImpl(vo,userLoggingsVo);
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getNonTeachingListService Ending");  
		
		return list;
	}





	public StaffAttendanceVo getSelectedTeacherNameReportService(ReportMenuVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getSelectedTeacherNameReportService Starting");
		
		StaffAttendanceVo list = null;
		
		try {
			
			list = dao.getSelectedTeacherNameReportDao(vo,userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportServiceImpl : getSelectedTeacherNameReportService Ending");  
		
		
		return list;
	}



	
	
	

}
