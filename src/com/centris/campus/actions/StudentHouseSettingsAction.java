package com.centris.campus.actions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentHouseSettingsBD;
import com.centris.campus.pojo.StudentHouseSettingsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentHouseSettingsVO;
import com.centris.campus.vo.UserDetailVO;

public class StudentHouseSettingsAction extends DispatchAction  {

	private static final Logger logger = Logger
			.getLogger(StudentHouseSettingsAction.class);
	
	public ActionForward houseSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction    : houseSetting Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_HOUSESETTING);
			
			   String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			   request.setAttribute("academic_year", academic_year);
			   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
	    		   request.setAttribute("currentlocation", currentlocation);
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	 
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails(userLoggingsVo);
				request.setAttribute("locationDetailsList", list);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
				request.setAttribute("accYearList", accYearList);
				request.setAttribute("academic_year", academic_year);
				
				request.setAttribute("historyaccyear",request.getParameter("historyaccyear"));
				request.setAttribute("historylocId",request.getParameter("historylocId"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : houseSetting Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING);
	}
	public ActionForward generateHouse(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : generateHouse Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			 
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			   request.setAttribute("academic_year", academic_year);
			   String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
	    	   System.out.println("current school Location:" +schoolLocation);
	    	   String currentlocation =null;
	    	   if(schoolLocation.equalsIgnoreCase("all")){
	    		   schoolLocation="%%";
	    		   request.setAttribute("currentlocation", "All");
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	  else{
	    		   currentlocation =new ExamDetailsBD().getlocationname(schoolLocation,userLoggingsVo);
	    		   request.setAttribute("currentlocation", currentlocation);
	    		   request.setAttribute("locationId",schoolLocation);
	    	   }
	    	 
	    	   LocationBD obj = new LocationBD();
				List<LocationVO> list = new ArrayList<LocationVO>();
				list = obj.getLocationDetails(userLoggingsVo);
				request.setAttribute("locationDetailsList", list);
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
						.getAccYears(userLoggingsVo);
				request.setAttribute("accYearList", accYearList);
				
				request.setAttribute("historyaccyear",request.getParameter("historyaccyear"));
				request.setAttribute("historylocId",request.getParameter("historylocId"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : generateHouse Ending");
		
		return mapping.findForward(MessageConstants.GENERATE_HOUSE);
	}
	
	
	public ActionForward gethouseSettings(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : gethouseSettings Starting");
		
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			
			/*if(locid.equalsIgnoreCase("all")){
				locid = "%%";
			}*/
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD().accYearhouseSettings(locid.trim(),accyear.trim(),custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			JSONObject json = new JSONObject();
			json.put("AccYearList",accYearList);
			response.getWriter().print(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : gethouseSettings Ending");
		return null;
	}
	
	public ActionForward AcademicYearHouseSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction    : AcademicYearHouseSetting Starting");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_HOUSESETTING);
			
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String locName = null;
			String accyname =new ExamDetailsBD().getaccyName(accid,custdetails);
			
			System.out.println(locid);
			
			if(locid == null){
				request.setAttribute("locName","All");
				locid = "%%";
			}
			else{
			 locName =new ExamDetailsBD().getlocationname(locid,custdetails);
			 request.setAttribute("locName",locName);
			}
			request.setAttribute("accyName",accyname);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().gethouseSettings(locid,accid,custdetails);
			request.setAttribute("gethouseSettingsList",list);
			
			request.setAttribute("historyaccyear",request.getParameter("historyaccyear"));
			request.setAttribute("historylocId",request.getParameter("historylocId"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : AcademicYearHouseSetting Ending");
		
		return mapping.findForward(MessageConstants.ACADEMICYEAR_HOUSE_SETTING);
	}
	
	
	public ActionForward savehouseSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction    : savehouseSettings Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			
			String accid = request.getParameter("accid");
			String locid = request.getParameter("locid");
			String housename = request.getParameter("housename");
			
			if(locid.equalsIgnoreCase("all")){
				locid = "%%";
			}
			
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setHousename(housename);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			pojo.setUserid(userCode);
			pojo.setUsrname(userType);
			pojo.setLog_activity_session(log_audit_session); 
		
			
			String status = StudentHouseSettingsBD.savehouseSettings(pojo,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : savehouseSettings Ending");
		
		return null;
	}
	
	public ActionForward edithouseSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction    : edithouseSettings Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			
			String accid = request.getParameter("accid");
			String locid = request.getParameter("locid");
			String housename = request.getParameter("housename");
			String houseid = request.getParameter("editid");
			
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setHousename(housename);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			pojo.setUserid(userCode);
			pojo.setUsrname(userType);
			pojo.setHouseid(houseid);
			pojo.setLog_activity_session(log_audit_session); 
			
			
			String status = StudentHouseSettingsBD.edithouseSettings(pojo,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : edithouseSettings Ending");
		
		return null;
	}
	
	public ActionForward deletehouseSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : deletehouseSettings Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String accid = request.getParameter("accid");
			String locid = request.getParameter("locid");
			String houseid = request.getParameter("deleteid");
			
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			pojo.setUserid(userCode);
			pojo.setUsrname(userType);
			pojo.setHouseid(houseid);
			pojo.setLog_activity_session(log_audit_session);
		
			
			String status = StudentHouseSettingsBD.deletehouseSettings(pojo,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : deletehouseSettings Ending");
		
		return null;
	}
	
	public ActionForward checkduplicateHouse(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : checkduplicateHouse Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");
			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			
			String accid = request.getParameter("accid");
			String locid = request.getParameter("locid");
			String housename = request.getParameter("housename");
			
			if(locid.equalsIgnoreCase("all")){
				locid = "%%";
			}
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			pojo.setUserid(userCode);
			pojo.setUsrname(userType);
			pojo.setHousename(housename);
			
			
			String status = StudentHouseSettingsBD.checkduplicateHouse(pojo,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : checkduplicateHouse Ending");
		
		return null;
	}
	
	public ActionForward HouseSettingClassWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : HouseSettingClassWise Starting");
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String selection1 = request.getParameter("selection1");
			
			String accyname = new ExamDetailsBD().getaccyName(accid,custdetails);
			String locName = new ExamDetailsBD().getlocationname(locid,custdetails);
			 
			request.setAttribute("selection1",selection1);
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter","Name Wise");
			request.setAttribute("filter1",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,custdetails);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,custdetails);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,custdetails);
			request.setAttribute("classwisehouse",list);
			
			request.setAttribute("historyaccyear",request.getParameter("historyaccyear"));
			request.setAttribute("historylocId",request.getParameter("historylocId"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : HouseSettingClassWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}

	public ActionForward ordernameDescWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : ordernameDescWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String selection1 = request.getParameter("selection1");
			
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter","Name Wise Desc");
			request.setAttribute("filter1",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,userLoggingsVo);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,userLoggingsVo);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,userLoggingsVo);
			request.setAttribute("classwisehouse",list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : ordernameDescWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}
	
	public ActionForward houseadmiWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : houseadmiWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,userLoggingsVo);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,userLoggingsVo);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,userLoggingsVo);
			request.setAttribute("classwisehouse",list);
			JSONArray array = new JSONArray();
			System.out.println(array.put(list));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : houseadmiWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}
	public ActionForward orderadmiDescWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : orderadmiDescWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			
			String selection1 = request.getParameter("selection1"); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter","By Admission No. Desc");
			request.setAttribute("filter1",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,userLoggingsVo);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,userLoggingsVo);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,userLoggingsVo);
			request.setAttribute("classwisehouse",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : orderadmiDescWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}
	
	public ActionForward orderadmiEvenWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : orderadmiEvenWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter","By Admission No. Even");
			request.setAttribute("filter1",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,userLoggingsVo);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,userLoggingsVo);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,userLoggingsVo);
			request.setAttribute("classwisehouse",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : orderadmiEvenWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}

	public ActionForward orderadmiOddWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : orderadmiOddWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("filter","By Admission No. Odd");
			request.setAttribute("filter1",selection1);
			int noofstudents = new StudentHouseSettingsBD().gettotalstudentcount(accid,locid,userLoggingsVo);
			request.setAttribute("noofstudents",noofstudents);
			
			int allocatedstudents = new StudentHouseSettingsBD().gettotalallostudent(accid,locid,userLoggingsVo);
			request.setAttribute("allocatedstudents",allocatedstudents);
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getclassdetails(locid,accid,userLoggingsVo);
			request.setAttribute("classwisehouse",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : orderadmiOddWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_CLASS_WISE);
	}
	
	public ActionForward getgenratehouseSettings(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getgenratehouseSettings Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			
			ArrayList<ExaminationDetailsVo> accYearList = new ReportsMenuBD()
					.accYeargeneratehouseSettings(locid.trim(),accyear.trim(),userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);
			
			JSONObject json = new JSONObject();
			json.put("AccYearList",accYearList);
			response.getWriter().print(json);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getgenratehouseSettings Ending");
		
		return null;
	}
	
	public ActionForward getHouseSettingStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingStudentWise Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String generateid = request.getParameter("generatehouseid");
			String filter1 = "namewise";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("genhouid",generateid);
			request.setAttribute("filter","Name Wise");
			request.setAttribute("filter1",filter1);
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingStudentWise(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingStudentWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	public ActionForward displayHouseStuNameWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : displayHouseStuNameWise Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "namewisedesc";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","Name Wise Desc");
			request.setAttribute("filter1",filter1);
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
		
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingStudentWise(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : displayHouseStuNameWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	
	public ActionForward displayHouseSettingAdminoWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : displayHouseSettingAdminoWise Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "admissionwiseasc";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No.");
			request.setAttribute("filter1",filter1);
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingAdminoWise(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : displayHouseSettingAdminoWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	public ActionForward displayHouseSettingAdminodescWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : displayHouseSettingAdminodescWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "admissionwisedesc";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Desc");
			request.setAttribute("filter1",filter1);
			
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
		
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingAdminoWise(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : displayHouseSettingAdminodescWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	public ActionForward displayHouseSettingAdminoevenWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : displayHouseSettingAdminoevenWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "admissionwiseeven";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Even");
			request.setAttribute("filter1",filter1);
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
			
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingAdminoEven(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : displayHouseSettingAdminoevenWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	
	public ActionForward dispalyHouseSettingAdminooddWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : dispalyHouseSettingAdminooddWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "admissionwiseodd";
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Odd");
			request.setAttribute("filter1",filter1);
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setClassid(classid);
			pojo.setLocid(locid);
			pojo.setAccid(accid);
	
			
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().displayHouseSettingAdminoEven(pojo,classname,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : dispalyHouseSettingAdminooddWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_DISPLY_SETTING_STUDENT_WISE);
	}
	
	
	public ActionForward getHouseSettingAdminoWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingAdminoWise Starting");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No.");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingAdminoWise(locid,classid,classname,accid,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
			/*JSONArray array = new JSONArray();
			System.out.println(array.put(list));*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingAdminoWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	public ActionForward getHouseSettingAdminodescWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingAdminodescWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Desc");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingAdminodescWise(locid,classid,classname,accid,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingAdminodescWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	public ActionForward getHouseSettingAdminoevenWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingAdminoevenWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 ="byadminoeven";
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Even");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingAdminoevenWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	public ActionForward getHouseSettingAdminooddWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingAdminooddWise Starting");
		
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 ="byadminoodd";
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","By Admission No. Odd");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingAdminoevenWise(locid,classid,classname,accid,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingAdminooddWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	
	
	public ActionForward HouseSettingStudentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : HouseSettingStudentWise Starting");
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "namewise";
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","Name Wise");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingStudentWise(locid,classid,classname,accid,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : HouseSettingStudentWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	public ActionForward getHouseSettingnamedescWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : getHouseSettingnamedescWise Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accid = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			String classid = request.getParameter("classId");
			String filter1 = "namewisedesc";
			String selection1 = request.getParameter("selection1");
			String accyname =new ExamDetailsBD().getaccyName(accid,userLoggingsVo);
			String locName =new ExamDetailsBD().getlocationname(locid,userLoggingsVo);
			String classname = new ExamDetailsBD().getclassname(classid,userLoggingsVo); 
			request.setAttribute("accyName",accyname);
			request.setAttribute("locName",locName);
			request.setAttribute("locid",locid);
			request.setAttribute("accid",accid);
			request.setAttribute("classname",classname);
			request.setAttribute("classid",classid);
			request.setAttribute("totalstu",request.getParameter("total"));
			request.setAttribute("allostu",request.getParameter("allocated"));
			request.setAttribute("filter","Name Wise Desc");
			request.setAttribute("filter1",selection1);
			ArrayList<StudentHouseSettingsVO> list = new StudentHouseSettingsBD().getHouseSettingStudentWise(locid,classid,classname,accid,filter1,userLoggingsVo);
			request.setAttribute("housestudentwise",list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : getHouseSettingnamedescWise Ending");
		
		return mapping.findForward(MessageConstants.HOUSE_SETTING_STUDENT_WISE);
	}
	
	
	
	public ActionForward generateHousing(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : generateHousing Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locid = request.getParameter("locid");
			String accyear = request.getParameter("accyear");
			String classid = request.getParameter("hiddenclassname");
			String selection = request.getParameter("selection");
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setLocid(locid);
			pojo.setAccid(accyear);
			pojo.setClassid(classid);
			
			
			ArrayList<StudentHouseSettingsVO> houses = new ArrayList<StudentHouseSettingsVO>();
			
			if(selection.equalsIgnoreCase("Name Wise")){
				houses = new StudentHouseSettingsBD().generateHousing(pojo,userLoggingsVo);
			}else if(selection.equalsIgnoreCase("By Admission No.")){
				houses = new StudentHouseSettingsBD().generateaddmiHousing(pojo,userLoggingsVo);
			}else if(selection.equalsIgnoreCase("By Admission No. Desc")){
				houses = new StudentHouseSettingsBD().byadminodescHousing(pojo,userLoggingsVo);
			}else if(selection.equalsIgnoreCase("By Admission No. Even")){
				houses = new StudentHouseSettingsBD().byadminoevenHousing(pojo,userLoggingsVo);
			}else if(selection.equalsIgnoreCase("By Admission No. Odd")){
				houses = new StudentHouseSettingsBD().byadminoddHousing(pojo,userLoggingsVo);
			}else if(selection.equalsIgnoreCase("Name Wise Desc")){
				houses = new StudentHouseSettingsBD().bystudentdescHousing(pojo,userLoggingsVo);
			}
			 
			JSONObject json = new JSONObject();
			json.put("HousesList",houses);
			response.getWriter().print(json);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : generateHousing Ending");
		
		return null;
	}
	
	public ActionForward savegenerateHouseDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : savegenerateHouseDetails Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String locid = request.getParameter("hiddenlocid");
			String accyear = request.getParameter("hiddenaccyear");
			String classid = request.getParameter("hiddenclassid");
			String noofstudents =  request.getParameter("noofstudents");
			String houseid1= request.getParameter("houseid");
			String sectionid1 = request.getParameter("sectionid");
			String studid1 = request.getParameter("studid");
			String filter1 = request.getParameter("filter1");
			
			String houseid[] = houseid1.split(",");
			String sectionid[] = sectionid1.split(",");
			String studid[] = studid1.split(",");
			
			StudentHouseSettingsPOJO pojo = new StudentHouseSettingsPOJO();
			pojo.setLocid(locid);
			pojo.setAccid(accyear);
			pojo.setClassid(classid);
			pojo.setTotclsstre(noofstudents);
			pojo.setFilter1(filter1); 
			
			
			String status = new StudentHouseSettingsBD().savegenerateHouseDetails(pojo,houseid,sectionid,studid,log_audit_session,custdetails);
			
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : savegenerateHouseDetails Ending");
		
		return null;
	}
	
	public ActionForward checkHousing(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : checkHousing Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			
			String status = new StudentHouseSettingsBD().checkHousing(accyear,locid,custdetails);
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : checkHousing Ending");
		
		return null;
	}
	
	public ActionForward checkselection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : checkselection Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("locid");
			
			String status = new StudentHouseSettingsBD().checkselection(accyear,locid,custdetails);
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : checkselection Ending");
		
		return null;
	}
	public ActionForward regenerateHousedetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction  : regenerateHousedetails Starting");
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_GENERATEHOUSE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = request.getParameter("accyear");
			String locid = request.getParameter("location");
			String genhouid = request.getParameter("genhouid");
			
			String status = new StudentHouseSettingsBD().regenerateHousedetails(accyear,locid,genhouid,custdetails);
			JSONObject json = new JSONObject();
			json.put("status",status);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentHouseSettingsAction : regenerateHousedetails Ending");
		return null;
	}
}
