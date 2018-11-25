package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.TimeTableDaoImpl;
import com.centris.campus.delegate.TimeTableBD;
import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;


public class TimeTableAction extends DispatchAction {
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName"); 
	
	private static final Logger logger = Logger
			.getLogger(TimeTableAction.class);
	
	public ActionForward getTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTimeTableDetails Starting");
		
		TimeTableBD obj = new TimeTableBD();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_TIMETABLEMANAGEMNET);
			
			String title ="Modify Time Table";
			request.setAttribute("title", title);
			String TimeTableDetails = request.getParameter("TimeTableDetails").trim();

			String locationId=request.getParameter("locationId");
			String academic_year=request.getParameter("accyear");
			
			//String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			//String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			

			String[] details=TimeTableDetails.split(",");
			if(details[0].equalsIgnoreCase("-")) {
				
			}
			else {
				request.setAttribute("timeTableDetails",obj.getTimeTableDetails(details[0],details[1],userLoggingsVo));
			}
			
			request.setAttribute("TimetableID", details[0]);
			request.setAttribute("selected_teacher", obj.getTeacherNameDetails(details[1],details[2],userLoggingsVo));
			request.setAttribute("selected_classid", obj.getClassNameDetails(details[1],userLoggingsVo));
			request.setAttribute("selected_sectionid", obj.getSectionNameDetailsBD(details[2],userLoggingsVo));
			request.setAttribute("classId", details[1]);
			request.setAttribute("locationId", locationId);
			request.setAttribute("locationname", HelperClass.getLocationFace(locationId, userLoggingsVo));
			request.setAttribute("accyear", HelperClass.getAcademicYearFace(academic_year, userLoggingsVo));
			request.setAttribute("accyearId",academic_year);
			request.setAttribute("sectionId", details[2]);
			
			request.setAttribute("historylocId", request.getParameter("locationId"));
			request.setAttribute("historyacademicId", request.getParameter("accyear"));
			request.setAttribute("historyclass",request.getParameter("class"));
			request.setAttribute("historysection",request.getParameter("section"));
			request.setAttribute("editoperation","editoperation");
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTimeTableDetails  Ending");
		return mapping.findForward(MessageConstants.VIEW_TIMETABLE);
	}
	public ActionForward getTimeTableDetailsbyJs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTimeTableDetailsbyJs Starting");
		
		TimeTableBD obj = new TimeTableBD();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			

			String locationId=request.getParameter("locationId");
			String academic_year=request.getParameter("accyearId");
			String classId=request.getParameter("classId");
			String timeTableId=request.getParameter("timeTableId");
			String sectionId=request.getParameter("sectionId");
			
			JSONObject objec=new JSONObject();
			if(timeTableId.equalsIgnoreCase("-")) {
				
			}
			else {
				objec.put("timeTableDetails",obj.getTimeTableDetails(timeTableId,classId,userLoggingsVo));
			}
			response.getWriter().print(objec);
		
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTimeTableDetailsbyJs  Ending");
		return null;
	}
	public ActionForward gettodaytimetableDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : gettodaytimetableDetail Starting");

		TimeTableDaoImpl obj = new TimeTableDaoImpl();
		TimeTableBD obj1 = new TimeTableBD();
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_TIMETABLESUB);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String timetableId = request.getParameter("timetableId");
			ArrayList<TimeTableVo> list=obj.getTodayTimeTableDetails(timetableId,userLoggingsVo);
			String classid=list.get(0).getClassid();
			String sectionId=list.get(0).getSectionid();
			request.setAttribute("timeTableDetails",list);
			request.setAttribute("selected_classid", obj1.getClassNameDetails(classid,userLoggingsVo));
			request.setAttribute("selected_sectionid", obj1.getSectionNameDetailsBD(sectionId,userLoggingsVo));
			request.setAttribute("timetableId", timetableId);
			request.setAttribute("title", "Time Table Substitution of "+list.get(0).getDayname());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : gettodaytimetableDetail Ending");

		return mapping.findForward("viewTodayTimetable");

	}
	public ActionForward getClassName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getClassName Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
			details = obj.getClassName();

			JSONObject jsonObject = new JSONObject(details);
			jsonObject.accumulate("classList", details);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getClassName  Ending");
		return null;
	}

	public ActionForward getSectionName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getSectionName Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			String classid = request.getParameter("classIdVal").trim();
			ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
			details = obj.getSectionName(classid);
			JSONObject jsonObject = new JSONObject(details);
			jsonObject.accumulate("sectionList", details);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getSectionName  Ending");
		return null;
	}

	public ActionForward updateTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTimeTableDetails Starting");
		
		
		TimeTableBD obj = new TimeTableBD();
		try {

			String user = HelperClass.getCurrentUserID(request);

			TimeTablePojo pojoObj = new TimeTablePojo();
			pojoObj.setUserid(user.trim());
			
			//String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
			
		
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
			String accyearid=request.getParameter("accYear");
			pojoObj.setAccyearid(accyearid);
			pojoObj.setClassid(request.getParameter("ClassId"));
			pojoObj.setSectionid(request.getParameter("SectionId"));
			pojoObj.setTimetableID(request.getParameter("timetableID").trim());
			pojoObj.setLocationId(request.getParameter("locationId"));

			pojoObj.setPeriods(request.getParameter("periodArrayString"));
			pojoObj.setTperiods(request.getParameter("tperiodArrayString"));
			
			pojoObj.setDayid(request.getParameter("dayidArray").split(","));
			pojoObj.setLog_audit_session(log_audit_session);
		

			String result = obj.updateTimeTableDetails(pojoObj,userLoggingsVo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("timetable_Result", result);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTimeTableDetails  Ending");
		return null;
	}
	public ActionForward updateTodayTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTimeTableDetails Starting");
		
		
		TimeTableDaoImpl obj = new TimeTableDaoImpl();
		try {

			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String user = HelperClass.getCurrentUserID(request);

			TimeTablePojo pojoObj = new TimeTablePojo();
			pojoObj.setUserid(user.trim());
			String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			pojoObj.setAccyearid(accyearid);
			pojoObj.setClassid(request.getParameter("ClassId"));
			pojoObj.setSectionid(request.getParameter("SectionId"));
			pojoObj.setTimetableID(request.getParameter("timetableID").trim());
			

			pojoObj.setPeriod1(request.getParameter("period1Array").split(","));
			pojoObj.setPeriod2(request.getParameter("period2Array").split(","));
			pojoObj.setPeriod3(request.getParameter("period3Array").split(","));

			pojoObj.setPeriod4(request.getParameter("period4Array").split(","));
			pojoObj.setPeriod5(request.getParameter("period5Array").split(","));
			pojoObj.setPeriod6(request.getParameter("period6Array").split(","));

			pojoObj.setPeriod7(request.getParameter("period7Array").split(","));
			pojoObj.setPeriod8(request.getParameter("period8Array").split(","));
			pojoObj.setPeriod9(request.getParameter("period9Array").split(","));
			
			
			
			pojoObj.setTperiod1(request.getParameter("tperiod1Array").split(","));
			pojoObj.setTperiod2(request.getParameter("tperiod2Array").split(","));
			pojoObj.setTperiod3(request.getParameter("tperiod3Array").split(","));

			pojoObj.setTperiod4(request.getParameter("tperiod4Array").split(","));
			pojoObj.setTperiod5(request.getParameter("tperiod5Array").split(","));
			pojoObj.setTperiod6(request.getParameter("tperiod6Array").split(","));

			pojoObj.setTperiod7(request.getParameter("tperiod7Array").split(","));
			pojoObj.setTperiod8(request.getParameter("tperiod8Array").split(","));
			pojoObj.setTperiod9(request.getParameter("tperiod9Array").split(","));
			
			pojoObj.setDayid(request.getParameter("dayidArray").split(","));
			pojoObj.setLog_audit_session(log_audit_session);
			
			String result = obj.updateTodayTimeTableDetails(pojoObj,userLoggingsVo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("timetable_Result", result);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTimeTableDetails  Ending");
		return null;
	}

	public ActionForward getTeacherTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTeacherTimeTableDetails Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			String accyearid = request.getSession(false).getAttribute("current_academicYear").toString();
			String[] teacherdetails = request.getParameter("TimeTableDetails").trim().split(",");
			
			
			request.setAttribute("teacherTimeTableDetails",obj.getTeacherTimeTableDetails(teacherdetails[0], accyearid));
			request.setAttribute("selected_teacherid", teacherdetails[0]);
			request.setAttribute("selected_teacherName", teacherdetails[1]);
			request.setAttribute("selected_accyearid", accyearid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTeacherTimeTableDetails  Ending");
		return mapping.findForward(MessageConstants.VIEW_TEACHER_TIMETABLE);
	}

	public ActionForward getTeacherName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTeacherName Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			ArrayList<TeacherTimeTableVo> details = new ArrayList<TeacherTimeTableVo>();
			details = obj.getTeacherName();

			JSONObject jsonObject = new JSONObject(details);
			jsonObject.accumulate("teacherList", details);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getTeacherName  Ending");
		return null;
	}
	
	public ActionForward updateTeacherTimeTableDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTeacherTimeTableDetails Starting");
		
		
		
		
		TimeTableBD obj = new TimeTableBD();
		try {

			String user = HelperClass.getCurrentUserID(request);

			TeacherTimeTablePojo pojoObj = new TeacherTimeTablePojo();
			
			
			
			pojoObj.setUserid(user.trim());
			pojoObj.setAccyearid(request.getSession(false).getAttribute("current_academicYear").toString());
			pojoObj.setTeacherid(request.getParameter("ClassId").trim());

			pojoObj.setPeriod1(request.getParameter("period1Array").split(","));
			pojoObj.setPeriod2(request.getParameter("period2Array").split(","));
			pojoObj.setPeriod3(request.getParameter("period3Array").split(","));

			pojoObj.setPeriod4(request.getParameter("period4Array").split(","));
			pojoObj.setPeriod5(request.getParameter("period5Array").split(","));
			pojoObj.setPeriod6(request.getParameter("period6Array").split(","));

			pojoObj.setPeriod7(request.getParameter("period7Array").split(","));
			pojoObj.setPeriod8(request.getParameter("period8Array").split(","));
			pojoObj.setPeriod9(request.getParameter("period9Array").split(","));
			pojoObj.setDayid(request.getParameter("dayidArray").split(","));

			String result = obj.updateTeacherTimeTableDetails(pojoObj);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("timetable_Result", result);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : updateTeacherTimeTableDetails  Ending");
		return null;
	}
	
	public ArrayList<TimeTableVo> getClassSectionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getClassSectionList Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
			details = obj.getClassSectionList(userLoggingsVo);

			JSONObject jsonObject = new JSONObject(details);
			jsonObject.accumulate("ClassSectionList", details);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : getClassSectionList  Ending");
		return null;
	}
	
	
	
	public ArrayList<TimeTableVo> classTimeTableDownloadXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : classTimeTableDownloadXLS Starting");
	
		
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/TimeTableXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
           /* String viewBy=request.getParameter("viewBy");
			
			String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
			
			
			ArrayList<TimeTableVo> ClassTimeTableList =  new TimeTableBD().getClassTimeTableList( accyearid,viewBy,userLoggingsVo.getCustId());*/
			String classId = request.getParameter("classId");
			String viewBy = request.getParameter("viewBy");
			String locationId=request.getParameter("locationId");
			String accyear=request.getParameter("accyer");
			String section=request.getParameter("section");
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			if(classId.equalsIgnoreCase("ALL")){
				classId="%%";
			}
			if(section.equalsIgnoreCase("all")){
				section="%%";
			}
			ArrayList<TimeTableVo> arr = new TimeTableBD().getClassTimeTableListByClass(accyear,viewBy,classId,locationId,userLoggingsVo,section);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					arr);
			Map parameters = new HashMap();
			parameters.put("SchoolName", HelperClass.getSchoolName(locationId, userLoggingsVo));
			parameters.put("accYear", HelperClass.getAcademicYearFace(accyear, userLoggingsVo));
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TimeTableXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Role Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/TimeTableXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TimeTableXLSReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : classTimeTableDownloadXLS  Ending");
		return null;
	}
	
	
	public ArrayList<TimeTableVo> teacherTimeTableDownloadXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : teacherTimeTableDownloadXLS Starting");
		
		
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/TeacherTimeTableXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
            String viewBy=request.getParameter("viewBy");
			
			String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
			
			
			ArrayList<TimeTableVo> TeacherTimeTableList =  new TimeTableBD().getClassTimeTableList(accyearid,viewBy,userLoggingsVo);
			
		
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					TeacherTimeTableList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TeacherTimeTableXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Role Details Excel Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNames);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/TeacherTimeTableXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TeacherTimeTableXLSReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : teacherTimeTableDownloadXLS  Ending");
		return null;
	}
	
	
	
	
	
	public ArrayList<TimeTableVo> classTimeTableDownloadPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : classTimeTableDownloadPDF Starting");
	
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			/* String viewBy=request.getParameter("viewBy");
			 String accYear=request.getParameter("accYear");
			 String location=request.getParameter("location");
				
		//	String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
				
				
				ArrayList<TimeTableVo> TeacherTimeTableList =  new TimeTableBD().getClassTimeTableList(accYear,viewBy,userLoggingsVo.getCustId(),location);*/
				
				
				String classId = request.getParameter("classId");
				String viewBy = request.getParameter("viewBy");
				String locationId=request.getParameter("locationId");
				String accyear=request.getParameter("accyer");
				String section=request.getParameter("section");
				
				if(locationId.equalsIgnoreCase("all")){
					locationId="%%";
				}
				if(classId.equalsIgnoreCase("ALL")){
					classId="%%";
				}
				if(section.equalsIgnoreCase("all")){
					section="%%";
				}
				ArrayList<TimeTableVo> arr=new TimeTableBD().getClassTimeTableListByClass(accyear,viewBy,classId,locationId,userLoggingsVo,section);
			/*ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD()
			.getBdayListDetails();*/

			String sourceFileName = request
					.getRealPath("Reports/classTimaTablePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					arr);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			parameters.put("SchoolName", HelperClass.getSchoolName(locationId, userLoggingsVo));
			parameters.put("accYear", HelperClass.getAcademicYearFace(accyear, userLoggingsVo));

			
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ClassTimeTablePDFReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : classTimeTableDownloadPDF  Ending");
		
		return null;
	}
	
	
	
	

	
	public ArrayList<TimeTableVo> teacherTimeTableDownloadPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : teacherTimeTableDownloadPDF Starting");
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			 String viewBy=request.getParameter("viewBy");
				
				String accyearid=request.getSession(false).getAttribute("current_academicYear").toString();
				
				
				ArrayList<TimeTableVo> TeacherTimeTableList =  new TimeTableBD().getClassTimeTableList(accyearid,viewBy,userLoggingsVo);
			/*ArrayList<UpcomingBdayVo> bdaylist = new CommunicationSettingsBD()
			.getBdayListDetails();*/

			String sourceFileName = request
					.getRealPath("Reports/teacherTimaTablePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					TeacherTimeTableList);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "teacherTimaTablePDFReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : teacherTimeTableDownloadPDF  Ending");
		
		return null;
	}
	
	public ActionForward checkTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : checkTeacher Starting");
		TimeTableBD obj = new TimeTableBD();
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String dayId=request.getParameter("dayid");
			String teacherId=request.getParameter("teacherId");
			String period=request.getParameter("period");
			userLoggingsVo.setAcaYearId(request.getParameter("accyearId"));
			String status = obj.checkTeacher(dayId,teacherId,userLoggingsVo,location,period);

			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("status", status);
			response.getWriter().print(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableAction : checkTeacher  Ending");
		return null;
	}
	
	
	
	
	
	
}