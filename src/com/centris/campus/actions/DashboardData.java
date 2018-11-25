package com.centris.campus.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import com.centris.campus.delegate.SmsDeligate;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.TemporaryRegBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.UserDetailVO;

public class DashboardData extends DispatchAction {

	private static final Logger logger = Logger.getLogger(MenusAction.class);
	 
	
	
	
public ActionForward StudentDetailsYearWise(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

			 logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in MenusAction:StudentDetails Starting");
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			try {
				
				String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
				ArrayList<StudentAttendanceVo> list = new StudentAttendanceBD().getStudentDetailsAcademicWise(userLoggingsVo,location);
				
				
				JSONObject obj = new JSONObject();
				obj.put("data", list);
				response.getWriter().print(obj);
				
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				e.printStackTrace();
			}
			
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in MenusAction : StudentDetails Ending");
			
			return null;
		}
	
	
public ActionForward todayStudentAttendance(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DashBoardAction : todayStudentAttendance Starting");
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear").toString();

		
		
		ArrayList<StudentAttendanceVo> list = new StudentAttendanceBD().todayStudentAttendance(userLoggingsVo,location,accYear);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DashBoardAction : todayStudentAttendance Ending");
	
	return null;
}

public ActionForward classWiseStudent(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DashBoardAction:houseWiseStudent Starting");
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear").toString();

		
		ArrayList<StudentAttendanceVo> list = new StudentAttendanceBD().houseWiseStudent(userLoggingsVo,location,accYear);
		
		
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DashBoardAction:houseWiseStudent Ending");
	
	return null;
}

public ActionForward admissionDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DashBoardAction:admissionDetails Starting");
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear").toString();

		
		ArrayList<StudentAttendanceVo> list = new StudentAttendanceBD().houseWiseStudent(userLoggingsVo,location,accYear);
		

		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String location_id = (String) request.getSession(false).getAttribute("current_schoolLocation");


		List<Issuedmenuvo> appliedList = new ArrayList<Issuedmenuvo>();
		
		appliedList = new TemporaryRegBD().getissuedforms("%%", userLoggingsVo ,location+","+academic_year);
		
		int appliedForm = (appliedList.size());
        String x = Integer.toString(appliedForm);
		
		String approvedDetails = new TemporaryRegBD().getApprovedFormsCount(academic_year, userLoggingsVo,location_id);
		String[] da = approvedDetails.split(",");
		
		
	 

		JSONObject obj = new JSONObject();
		obj.put("data",da);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DashBoardAction:admissionDetails Ending");
	
	return null;
}


public ActionForward smsStatus(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DashBoardAction:admissionDetails Starting");
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear").toString();
		
	
		
	
		LstmsUpcomingMeetingVO vo = new SmsDeligate().getSmsDetails(userLoggingsVo,accYear,location);

		String status = Integer.toString(vo.getTotalsms())+","+Integer.toString(vo.getSuccesscount())+","+Integer.toString(vo.getFailurecount());
		String[] sp = status.split(",");

		JSONObject obj = new JSONObject();
		obj.put("data",sp);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DashBoardAction:admissionDetails Ending");
	
	return null;
}



public ActionForward classStudentCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	 logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DashBoardAction:classStudentCount Starting");
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
		String accYear = (String) request.getSession(false).getAttribute("current_academicYear").toString();
		
		
		String locId = request.getParameter("schoolLocationId");
		String classId = request.getParameter("studClassId");
		String sectionId = request.getParameter("studSectionId");
		String academicYear = request.getParameter("academicYear");
		
		
	
	    String count = HelperClass.getClassStudentCount(locId,classId,sectionId,userLoggingsVo,academicYear);
	    String[] studentCount = count.split(",");
		

		JSONObject obj = new JSONObject();
		obj.put("data",studentCount);
		response.getWriter().print(obj);
		
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in DashBoardAction:classStudentCount Ending");
	
	return null;
}




	
	
}
