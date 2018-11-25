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
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.StaffAttendanceBD;
import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceAction extends DispatchAction {
	
	private static final Logger logger = Logger.getLogger(StaffAttendanceAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward staffattendaceUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : staffattendaceUpload Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFATTENDANCE);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String todayDate=HelperClass.getCurrentSqlDate().toString();
		String locId=request.getParameter("historylocId");
		
		ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails(custdetails);
		
		request.setAttribute("departmentList", departmentList);
		
		ArrayList<StaffAttendanceVo> attendanceList= new StaffAttendanceBD().getStaffAttendance(todayDate,"%%",custdetails,locId);
		
		request.setAttribute("attendanceList", attendanceList);
		request.setAttribute("TodayDate", HelperClass.convertDatabaseToUI(todayDate));
		
		request.setAttribute("historylocId", locId);
		request.setAttribute("historyacademicId", request.getParameter("historyacademicId"));
		request.setAttribute("historystartdate", request.getParameter("historystartdate"));
		request.setAttribute("historyenddate", request.getParameter("historyenddate"));
		request.setAttribute("historyback", "historyback");
		request.setAttribute("historystatus", request.getParameter("historystatus"));
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : staffattendaceUpload Ending");
	
		
		return mapping.findForward(MessageConstants.StaffAttendance_upload);
	}
	
	public ActionForward searchStaffAttendaceUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : searchStaffAttendaceUpload Starting");
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String departmentname = request.getParameter("department");
		String attendanceDate = request.getParameter("attDate"); 
		String locId = request.getParameter("locId");
		
		/*ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails(custdetails.getCustId());
		
		request.setAttribute("departmentList", departmentList);
		
		request.setAttribute("departmentname",departmentname);*/
		
		if(departmentname==null){
			departmentname="%%";
		}
		else if(departmentname.equalsIgnoreCase("all")){
			departmentname="%%";
		}
		
		ArrayList<StaffAttendanceVo> attendanceList= new StaffAttendanceBD().getStaffAttendance(HelperClass.convertUIToDatabase(attendanceDate),departmentname,custdetails,locId);
		/*request.setAttribute("attendanceList", attendanceList);
		request.setAttribute("TodayDate",attendanceDate);*/
		JSONObject object=new JSONObject();
		object.put("attendanceList", attendanceList);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : searchStaffAttendaceUpload Ending");
	
		return null;
		//return mapping.findForward(MessageConstants.StaffAttendance_upload);
	}
	
	public ActionForward updateAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : updateAttendanceStatus Starting");
		
		String userId=HelperClass.getCurrentUserID(request);
		
		String date = request.getParameter("date");
		String teacherId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		
		System.out.println("date :: "+date);
		System.out.println("teacherId :: "+teacherId);
		System.out.println("attendanceStatus :: "+attendanceStatus);
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		StaffAttendancePojo attendancepojo=new StaffAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setUserId(userId);
		attendancepojo.setLog_audit_session(log_audit_session);
		attendancepojo.setAccYear(academic_year);
	
		String status=new StaffAttendanceBD().updateAttendanceStatus(attendancepojo,userLoggingsVo);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : updateAttendanceStatus Ending");
	
		
		return null;
	}
	
	public ActionForward downloadStaffAttendanceDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsXLS  Starting");
		
		try {
		
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/staffattendancedetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String locationId = request.getParameter("locationId");
			String accYear = request.getParameter("accYear");
			
		   LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locationId);
			
			
			ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate,userLoggingsVo,locationId,accYear);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					staffAttendanceList);
			
			Map parameters = new HashMap();
			parameters.put("SchoolName", custSchoolInfo.getSchname()+"("+custSchoolInfo.getLocationname()+")");
			parameters.put("accYear",  HelperClass.getAcademicYearFace(accYear, userLoggingsVo));
			parameters.put("fromdate",  staffAttendanceList.get(0).getStartDate());
			parameters.put("todate",  staffAttendanceList.get(0).getEndDate());
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/staffattendancedetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Staff Attendance Details Excel Report" };
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
					request.getRealPath("Reports/staffattendancedetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffAttendanceDetailsxls.xls");
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
				+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStaffAttendanceDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsPDF  Starting");
			try {
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				System.out.println("downloading pdf");
				String startdate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				String locationId = request.getParameter("locationId");
				String branchName = request.getParameter("branchName");
				
				
				if(locationId.equalsIgnoreCase("All")){
					locationId="%%";
				}
				
				LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locationId);
				
				String accYear = request.getParameter("accYear");
				ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate,userLoggingsVo,locationId,accYear);
				String sourceFileName = request.getRealPath("Reports/staffattendanceDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(staffAttendanceList);
				String PropfilePath = getServlet().getServletContext().getRealPath("")+ "\\images\\" + ImageName.trim();
				 
				
				Map parameters = new HashMap();
				parameters.put("Image", PropfilePath);
				parameters.put("SchoolName", custSchoolInfo.getSchname()+"("+custSchoolInfo.getLocationname()+")");
				parameters.put("accYear",  HelperClass.getAcademicYearFace(accYear, userLoggingsVo));
				parameters.put("fromdate",  staffAttendanceList.get(0).getStartDate());
				parameters.put("todate",  staffAttendanceList.get(0).getEndDate());
				/*parameters.put("Image", clientImage);
				parameters.put("ClientName", ClientName);
				parameters.put("ClientAddress", ClientAddress_l1);*/
				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StaffAttendanceDetailsPDF - " + ".pdf\"");
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
					+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsPDF  Ending");
			return null;
		}
	
	public ActionForward getStaffAttendanceSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : getStaffAttendanceSearch Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String locationId = request.getParameter("locationId");
			String accYear = request.getParameter("accYear");
			
			if(locationId==null){
				locationId="%%";
			}
			else if(locationId.equalsIgnoreCase("All")){
				locationId="%%";
			}
			ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate,custdetails,locationId,accYear);
			
			JSONObject object=new JSONObject();
			object.put("staffAttendanceList", staffAttendanceList);
			response.getWriter().print(object);
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : getStaffAttendanceSearch Ending");
		return null;

	}
	public ActionForward getAccyearNames(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : getAccyearNames Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String accYear = request.getParameter("accYear");
			String dates=HelperClass.getAcademicYearStartAndEndDate(accYear, custdetails);
			JSONObject object=new JSONObject();
			object.put("dates", dates);
			response.getWriter().print(object);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : getAccyearNames Ending");
		return null;
		
	}

}
