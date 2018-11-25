package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
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

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(StudentAttendanceAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward studentattendaceUploadEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : studentattendaceUploadEntry Starting");
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation"); 
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
		request.setAttribute("locationList", locationList);
		ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
		request.setAttribute("AccYearList", accYearList);
		request.setAttribute("hloc", location);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : studentattendaceUploadEntry Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_ATT_ENTRY);
	}
	
	public ActionForward getStudentAttendanceDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_STUDENT);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_STUDENT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String location = (String) request.getSession(false).getAttribute("current_schoolLocation"); 
		
		
		String classId=request.getParameter("classId");
		String section=request.getParameter("section");
		String date=request.getParameter("date");
		String spec = request.getParameter("spec");
		String teacher=request.getParameter("teacher"); 
		String Acyearid=request.getParameter("Acyearid");
		
		StudentAttendancePojo studentPojo=new StudentAttendancePojo();
		studentPojo.setClassId(classId);
		studentPojo.setSectinId(section);
		studentPojo.setDate(date);
		studentPojo.setSpecID(spec);
		studentPojo.setAccYearId(Acyearid);
	
		
		ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(studentPojo,custdetails);
		
		request.setAttribute("attendanceList", studentAttendanceList);
		request.setAttribute("attenid",studentAttendanceList.get(0).getAttid());
		request.setAttribute("classId", classId);
		request.setAttribute("section", section);
		request.setAttribute("date", date);
		request.setAttribute("teacher", teacher);
		request.setAttribute("spec", spec);
		request.setAttribute("hloc", location);
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceDetails Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_ATT_ENTRY);
	}
	
	public ActionForward updateAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateAttendanceStatus Starting");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String date = request.getParameter("date");
		String locationId=request.getParameter("locationId");
		String teacherId=request.getParameter("teacherid");
		String studentId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		String period1 = request.getParameter("period1");
		String period2 = request.getParameter("period2");
		String period3 = request.getParameter("period3");
		String period4 = request.getParameter("period4");
		String period5 = request.getParameter("period5");
		String period6 = request.getParameter("period6");
		String period7 = request.getParameter("period7");
		String period8 = request.getParameter("period8");
		String period9 = request.getParameter("period9");
		
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setPeriod1(period1);
		attendancepojo.setPeriod2(period2);
		attendancepojo.setPeriod3(period3);
		attendancepojo.setPeriod4(period4);
		attendancepojo.setPeriod5(period5);
		attendancepojo.setPeriod6(period6);
		attendancepojo.setPeriod7(period7);
		attendancepojo.setPeriod8(period8);
		attendancepojo.setPeriod9(period9);
		attendancepojo.setStudentid(studentId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setLocationId(locationId);
		attendancepojo.setLog_audit_session(log_audit_session); 
		attendancepojo.setAccYearId(request.getParameter("Acyearid"));
		attendancepojo.setSpecID(request.getParameter("specialization"));
		
		
		String status=new StudentAttendanceBD().updateAttendanceStatus(attendancepojo,custdetails);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateAttendanceStatus Ending");
		
		return null;
	}
	
	public ActionForward getStudentPeriodAttendance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentPeriodAttendance Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String stuId = request.getParameter("stuId");
		String classId=request.getParameter("classId");
		String sectionId=request.getParameter("section");
		String date=request.getParameter("date");
		String status=request.getParameter("status");
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setStudentid(stuId.split(",")[0]);
		attendancepojo.setStudentname(stuId.split(",")[1]);
		attendancepojo.setStatus(status);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setClassId(classId.split(",")[0]);
		attendancepojo.setClassname(classId.split(",")[1]);
		attendancepojo.setSectinId(sectionId.split(",")[0]);
		attendancepojo.setSectionname(sectionId.split(",")[1]);
		
		
		StudentAttendanceVo stuAttVo=new StudentAttendanceBD().getStudentPeriodAttendance(attendancepojo,custdetails);
		
		request.setAttribute("stuAttVo", stuAttVo);
		request.setAttribute("attendancepojo", attendancepojo);
	
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentPeriodAttendance Ending");
	
		
		return mapping.findForward(MessageConstants.GET_STU_PERIOD_ATT);
	}
	
	public ActionForward updateStudentPeriodAtt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateStudentPeriodAtt Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String stuId = request.getParameter("studentId");
		String classId=request.getParameter("classsId");
		String sectionId=request.getParameter("sectionId");
		String date=request.getParameter("date");
		
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setStudentid(stuId);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setClassId(classId);
		attendancepojo.setSectinId(sectionId);
		attendancepojo.setPeriod1(request.getParameter("period1"));
		attendancepojo.setPeriod2(request.getParameter("period2"));
		attendancepojo.setPeriod3(request.getParameter("period3"));
		attendancepojo.setPeriod4(request.getParameter("period4"));
		attendancepojo.setPeriod5(request.getParameter("period5"));
		attendancepojo.setPeriod6(request.getParameter("period6"));
		attendancepojo.setPeriod7(request.getParameter("period7"));
		attendancepojo.setPeriod8(request.getParameter("period8"));
	
		
		String status=new StudentAttendanceBD().updateStudentPeriodAtt(attendancepojo,custdetails);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
	
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateStudentPeriodAtt Ending");
	
		
		return null;
	}
	
	public ActionForward downloadStudentAttendanceDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS  Starting");
		
		try {
			 UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			 String locId=request.getParameter("locationname");
			LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/Studentattendancexls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			String startdate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			String schname=request.getParameter("locname");
			
			String Acyearid=request.getParameter("Acyearid");
			String classId=request.getParameter("classId"); 
			String section=request.getParameter("section");
			
			
			if(locId.equalsIgnoreCase("all")){
				locId="%%";
			}
			if(classId.equalsIgnoreCase("all")){
				classId="%%";
			}
			if(section.equalsIgnoreCase("all")){
				section="%%";
			}
			
			ArrayList<StudentAttendanceVo> studentAttendance=new StudentAttendanceBD().getStudentsAttendanceListByDownload(startdate,endDate,Acyearid,locId,classId,section,cpojo);
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentAttendance);
			Map parameters = new HashMap();
			parameters.put("schname", schname);
			parameters.put("locname",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/Studentattendancexls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Attendance Excel Report" };
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
					request.getRealPath("Reports/Studentattendancexls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Studentattendancexls.xls");
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
				+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStudentAttendanceDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsPDF  Starting");

			try {
				ServletContext servletContext = request.getServletContext();
				UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
				String locId=request.getParameter("locationname");
				
				LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
				String startdate=request.getParameter("startDate");
				String endDate=request.getParameter("endDate");
				String schName=request.getParameter("locname");
				
				String Acyearid=request.getParameter("Acyearid");
				String classId=request.getParameter("classId"); 
				String section=request.getParameter("section");
				
				
				if(locId.equalsIgnoreCase("all")){
					locId="%%";
				}
				if(classId.equalsIgnoreCase("all")){
					classId="%%";
				}
				if(section.equalsIgnoreCase("all")){
					section="%%";
				}
				
				String PropfilePath = "";
				
				ArrayList<StudentAttendanceVo> studentAttendance=new StudentAttendanceBD().getStudentsAttendanceListByDownload(startdate,endDate,Acyearid,locId,classId,section,cpojo);
				
				String sourceFileName = request.getRealPath("Reports/studentattendancedetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentAttendance);

				/*String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();
*/
				 
				 String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
				 
				 if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
						
						PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
					}
				 else{
					 PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
					}
				 
				/*String schName = res.getString("locationname");*/
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
			/*	parameters.put("Image", PropfilePath);*/
				parameters.put("schName", schName);
				parameters.put("locname", custSchoolInfo.getSchname());
				parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
				parameters.put("custSchoollogo", schoollogo);
				parameters.put("custSchoolboardlogo", PropfilePath);
				parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
				parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
				parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
				parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentAttendanceDetailsPDF - " + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}

			catch (Exception e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceAction : downloadStudentAttendanceDetailsPDF  Ending");
			return null;

		}
	
	public ActionForward getteacherByClass(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getteacherByClass  Starting");

	try{
		UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
		String classId=request.getParameter("classId");
		String sectionId = request.getParameter("sectionId");
		
		StudentAttendanceBD obj= new StudentAttendanceBD();
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		list = obj.getteacherByClass(classId,sectionId,pojo);
		
		JSONObject jsonObject = new JSONObject(list);
		jsonObject.accumulate("teacherName", list);
		response.getWriter().print(jsonObject);
		
	}
	catch(Exception e){
		
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getteacherByClass  Ending");
	return null;
}

	public ActionForward getClassSpec(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getClassSpec  Starting");

	try{
		UserLoggingsPojo pojo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String classId = request.getParameter("classidVal");
		String locationId = request.getParameter("locationId");
		
		StudentAttendanceBD obj= new StudentAttendanceBD();
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		list = obj.getClassSpec(classId,locationId,pojo);
		
		request.setAttribute("specList", list);
		JSONObject jsonObject = new JSONObject(list);
		jsonObject.accumulate("specList", list);
		response.getWriter().print(jsonObject);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentAttendanceAction : getClassSpec Ending");
	
	return null;
}

	public ActionForward editAttendance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : editAttendance  Starting");
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String input=request.getParameter("idString");
			String AccYearId=request.getParameter("Acyearid");
			
			StudentAttendancePojo pojo = new StudentAttendancePojo();
			pojo.setClassId(input.split(",")[0]);
			pojo.setSectinId(input.split(",")[1]);
			pojo.setDate(input.split(",")[2]);
			pojo.setSpecID(input.split(",")[3]);
			pojo.setAccYearId(AccYearId);
			
			pojo.setLocationId(input.split(",")[4]);
			
			request.setAttribute("AccYearId", AccYearId);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accYearList);
			
			//ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(pojo);
			
			StudentAttendanceVo stuAttVo=new StudentAttendanceBD().editAttendance(pojo,custdetails);
			
		
			//stuAttVo.setDate(pojo.getDate());
			//request.setAttribute("attendancelist", studentAttendanceList);	
			request.setAttribute("stuattEdit", stuAttVo);
			
			request.setAttribute("locId", input.split(",")[4]);
			request.setAttribute("accYearId", AccYearId);
			
			request.setAttribute("attendetails", pojo);
			
			request.setAttribute("historyacademicId", AccYearId);
			request.setAttribute("historylocId", request.getParameter("locId"));
			request.setAttribute("historyclassname", request.getParameter("classname"));
			request.setAttribute("historysectionid", request.getParameter("sectionid"));
			request.setAttribute("historyspecId", request.getParameter("specId"));
			request.setAttribute("historystartdate", request.getParameter("startdate"));
			request.setAttribute("historyenddate", request.getParameter("enddate"));
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : editAttendance Ending");
		
		return mapping.findForward(MessageConstants.GET_EDIT_ATT);
}

	public ActionForward getStudentAttendanceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceList Starting");
	    
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String classId=request.getParameter("classId");
		String section=request.getParameter("section");
		String date=request.getParameter("date");
		String spec = request.getParameter("spec");
		String Acyearid = request.getParameter("Acyearid");
	    String locId=request.getParameter("locId");
		
	    StudentAttendancePojo studentPojo=new StudentAttendancePojo();
		
	    studentPojo.setClassId(classId);
		studentPojo.setSectinId(section);
		studentPojo.setDate(date);
		studentPojo.setSpecID(spec);
		studentPojo.setAccYearId(Acyearid);
		studentPojo.setCustid(custdetails.getCustId());
		studentPojo.setLocationId(locId);
		
		ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendance(studentPojo,custdetails);
	
		request.setAttribute("classId", classId);
		request.setAttribute("section", section);
		request.setAttribute("date", date);
		 
		request.setAttribute("spec", spec);
		request.setAttribute("Acyearid", Acyearid);
	
		JSONObject jsonObject = new JSONObject(studentAttendanceList);
		jsonObject.accumulate("attendanceList", studentAttendanceList);
		response.getWriter().print(jsonObject);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getStudentAttendanceList Ending");
	
	
		return null;
	}

	public ActionForward searchAttendanceYearList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction    : searchAttendanceYearList Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accYear = request.getParameter("accyear");
		String locationId = request.getParameter("locationId");
		
		List<StudentAttendanceVo> list= null;
		
		try {
			
 			if(locationId.equalsIgnoreCase("all") && !(accYear.equalsIgnoreCase("all"))){
				
				locationId="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear,custdetails);
			}
			else if(accYear.equalsIgnoreCase("all") && !(locationId.equalsIgnoreCase("all"))){
				
				accYear="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear,custdetails);
			}
			else if(accYear.equalsIgnoreCase("all") && locationId.equalsIgnoreCase("all")){
				
				locationId="%%";
				accYear="%%";
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear,custdetails);
			}else{
				list = new StudentAttendanceBD().searchStudentsAttendanceList(locationId,accYear,custdetails);
			}
			
			//request.setAttribute("SearchAttendanceList", list);
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SearchAttendanceList", list);
			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : searchAttendanceYearList Ending");

		return null;
	}

	public ActionForward getAttendenceByClassList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendenceByClassList Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classname = request.getParameter("classId");
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			if(classname.equalsIgnoreCase("all")){
				classname="%%";
				list = new StudentAttendanceBD().getAttendenceByClassList(locationid,accyear,classname,custdetails);
			}
			else{
				list = new StudentAttendanceBD().getAttendenceByClassList(locationid,accyear,classname,custdetails);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendenceByClassList Ending");

		return null;

	}
	
	public ActionForward getAttendenceByClassSectionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendenceByClassSectionList Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String accyear=request.getParameter("accyear");
			String locationid=request.getParameter("location");
			String classname=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid"); 
			String specialization=request.getParameter("specialization");
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			if(specialization==null||  specialization==""){
				specialization="%%";
			}
			
			
			if(classname.equalsIgnoreCase("all") || classname=="" || classname==null){
				classname="%%";
			}
			if(sectionid.equalsIgnoreCase("all") || sectionid=="" || sectionid==null){
				sectionid="%%";
			}
			 
				list = new StudentAttendanceBD().getAttendenceByClassSectionList(locationid,accyear,classname,sectionid,specialization,startdate,enddate,custdetails);
		 
			
			//list = delegateObj.getPromotedClassSectionList(regVo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendenceByClassSectionList Ending");

		return null;
	}
	
	public ActionForward getTeacherList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getTeacherList Starting");

		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid = request.getParameter("locationId");
			
			List<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
			
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
				list = new StudentAttendanceBD().getTeacherList(locationid,custdetails);
			}
			else{
				list = new StudentAttendanceBD().getTeacherList(locationid,custdetails);
			}

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("getTeacherList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getTeacherList Ending");

		return null;

	}
	
	public ActionForward getAttendanceListByTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendanceListByTeacher Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String accyear=request.getParameter("accyear");
			String locationid=request.getParameter("location");
			String classname=request.getParameter("classId");
			String sectionid=request.getParameter("sectionid");
			String teacherid=request.getParameter("teacherid");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			
			if(!locationid.equalsIgnoreCase("all") && teacherid.equalsIgnoreCase("all")){
				teacherid="%%";
				sectionid="%%";
				classname="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custdetails);
			}else if(locationid.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all") && !teacherid.equalsIgnoreCase("all")){
				sectionid="%%";
				classname="%%";
				locationid="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custdetails);
			}else if(!locationid.equalsIgnoreCase("all") && !teacherid.equalsIgnoreCase("all")){
				sectionid="%%";
				classname="%%";
				
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custdetails);
			}else if(locationid.equalsIgnoreCase("all") && teacherid.equalsIgnoreCase("all") && classname.equalsIgnoreCase("all")){
				teacherid="%%";
				sectionid="%%";
				classname="%%";
				locationid="%%";
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custdetails);
			}else{
				list = new StudentAttendanceBD().getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custdetails);
			}
			
			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendanceListByTeacher Ending");

		return null;
	}
	
	public ActionForward daterange(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : daterange Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear = request.getParameter("accyear");

			String startDate=Integer.toString(HelperClass.getPastDateofAcademicYear(accyear,custdetails)+1);
			String enddate=Integer.toString(HelperClass.getForDateofAcademicYear(accyear,custdetails));

			JSONObject obj=new JSONObject();

			obj.put("startDate", startDate+","+enddate);

			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : daterange Ending");

		return null;

	}
	
	public ActionForward getAttendanceListByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendanceListByDate Starting");

		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STUDENT);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
						
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			
			List<StudentAttendanceVo> list= new ArrayList<StudentAttendanceVo>();
			
			
			list = new StudentAttendanceBD().getAttendanceListByDate(startdate,enddate,custdetails);			

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("SearchAttendanceList", list);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getAttendanceListByDate Ending");

		return null;
	}
	
	public ActionForward modifyAttendance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : modifyAttendance  Starting");
		try{
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_ATTENDANCE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		/*	String input=request.getParameter("idString");*/
			
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String date = request.getParameter("date");
			String spec = request.getParameter("spec");
			String teacher = request.getParameter("teacher");
			String acyId = request.getParameter("acyId");
			String locid = request.getParameter("locid");
			
			StudentAttendancePojo pojo = new StudentAttendancePojo();
			
			pojo.setClassId(classId);
			pojo.setSectinId(section);
			pojo.setDate(date);
			pojo.setSpecID(spec);
			pojo.setTeacherId(teacher);
			pojo.setCustid(custdetails.getCustId());
			pojo.setAccYearId(acyId);
			pojo.setLocationId(locid);
			
			System.out.println(date);			
			
			//StudentAttendanceVo stuAttVo=new StudentAttendanceBD().editAttendance(pojo);
			ArrayList<StudentAttendanceVo> studentAttendanceList=new StudentAttendanceBD().getStudentAttendanceDetails(pojo,custdetails);
		
			//stuAttVo.setDate(pojo.getDate());
			//request.setAttribute("attendancelist", studentAttendanceList);	
			//request.setAttribute("stuattEdit", stuAttVo);

			request.setAttribute("attendancepojo", pojo);
			
			JSONObject object=new JSONObject();
			object.put("attendancelist", studentAttendanceList);
			response.getWriter().print(object); 
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : modifyAttendance Ending");
		
		return null;
	}
	
	public ActionForward getperiodcount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getperiodcount Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		 	 
            String locId=request.getParameter("locId");
            String clsId=request.getParameter("clsId");
            int count= new StudentAttendanceBD().getperiodcount(locId,clsId,custdetails);

			JSONObject obj=new JSONObject();

			obj.put("noofperiod", count);

			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : getperiodcount Ending");

		return null;

	}
	
	
	//NewStudentattendencefor Dynamical period
	public ActionForward NewupdateAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : NewupdateAttendanceStatus Starting");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String date = request.getParameter("date");
		String locationId=request.getParameter("locationId");
		String teacherId=request.getParameter("teacherid");
		String studentId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		String[] periodId=request.getParameter("periodvalue").split(":");
		
		System.out.println("periodId="+periodId);
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setStudentid(studentId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setLocationId(locationId);
		attendancepojo.setLog_audit_session(log_audit_session); 
		attendancepojo.setAccYearId(request.getParameter("Acyearid"));
		attendancepojo.setSpecID(request.getParameter("specialization"));
		attendancepojo.setClassId(request.getParameter("clsId"));
		attendancepojo.setDivid(request.getParameter("divId"));
		attendancepojo.setNoofperiod(Integer.parseInt(request.getParameter("noofperiod")));
		String status=new StudentAttendanceBD().NewupdateAttendanceStatus(periodId,attendancepojo,custdetails);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : NewupdateAttendanceStatus Ending");
		
		return null;
	}
	
	public ActionForward updateNewAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateNewAttendanceStatus Starting");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String date = request.getParameter("date");
		String locationId=request.getParameter("locationId");
		String teacherId=request.getParameter("teacherid");
		String studentId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		String[] periodId=request.getParameter("periodvalue").split(":");
		String[] statusid=request.getParameter("statusids").split(",");
		String attid=request.getParameter("attenid");
		System.out.println("periodId="+periodId);
		StudentAttendancePojo attendancepojo=new StudentAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setUserId(HelperClass.getCurrentUserID(request));
		attendancepojo.setStudentid(studentId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setLocationId(locationId);
		attendancepojo.setLog_audit_session(log_audit_session); 
		attendancepojo.setAccYearId(request.getParameter("Acyearid"));
		attendancepojo.setSpecID(request.getParameter("specialization"));
		attendancepojo.setClassId(request.getParameter("clsId"));
		attendancepojo.setDivid(request.getParameter("divId"));
		attendancepojo.setNoofperiod(Integer.parseInt(request.getParameter("noofperiod")));
		attendancepojo.setAtenid(Integer.parseInt(attid));

		
		String status=new StudentAttendanceBD().updateNewAttendanceStatus(periodId,attendancepojo,custdetails,statusid);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceAction : updateNewAttendanceStatus Ending");
		
		return null;
	}
	
	
}
