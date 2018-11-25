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
import com.centris.campus.delegate.StaffAttendanceReportBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceReportAction extends DispatchAction{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	private static final Logger logger = Logger.getLogger(AdminMenuAction.class);
	
	
	public ActionForward staffAttendanceReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendanceReport Starting");
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STAFFATTENDANCEDETAILS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
		
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			request.setAttribute("academic_year", academic_year);
	      ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(userLoggingsVo);
		  request.setAttribute("AccYearList", accYearList);

		  ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
		  request.setAttribute("startDate", HelperClass.getAcademicYearStartAndEndDate(academic_year, userLoggingsVo).split(",")[0]);
		  request.setAttribute("enddate", HelperClass.getAcademicYearStartAndEndDate(academic_year, userLoggingsVo).split(",")[1]);
		  
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendanceReport Ending");
		
		
		return mapping.findForward(MessageConstants.STAFF_ATTENDANCE_REPORT);
	}
	
	
	
	
	public ActionForward getStaffAttendanceAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceAction Starting");
		
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
		/*	ReportMenuForm reform=(ReportMenuForm)form;*/
			ReportMenuVo vo = new ReportMenuVo();
			
			
			vo.setAccyearname(request.getParameter("accyear"));
			vo.setTeachertype(request.getParameter("teachertype"));
			vo.setFromdate(HelperClass.convertUIToDatabase(request.getParameter("Fromdate")));
			vo.setTodate(HelperClass.convertUIToDatabase(request.getParameter("todate")));
			
			vo.setTeachername(request.getParameter("teachername"));
			/*if(reform.getTeachername().equalsIgnoreCase("all")){
				
				
				reform.setTeachername("%%");
				
			}
			vo.setTeachertId(reform.getTeachername());
			
			*/
			
			
			
	      if(vo.getTeachername().equalsIgnoreCase("all")){
				vo.setTeachertId("%%");
			}
			else{
				
				vo.setTeachertId(vo.getTeachername());
			}
	      
			String acc = vo.getAccyear();
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(userLoggingsVo);
		   ReportMenuVo selectedItems=new StaffAttendanceReportBD().getSelectedItems(acc,userLoggingsVo);
				
				 request.setAttribute("AccYearList", accYearList);
			
				request.setAttribute("selecteddetails", vo);
				
				request.setAttribute("accyearname", selectedItems);
				
				
				
				
				String teacher =vo.getTeachername();
				StaffAttendanceVo selectedteacher = new StaffAttendanceReportBD().getSelectedTeacherNameReportBD(vo,userLoggingsVo);
				request.setAttribute("selectedteacher", selectedteacher);
				
				
				
				ArrayList<StaffAttendanceVo> staffattendanceList=new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo,userLoggingsVo);	
				request.setAttribute("success", "success");
				request.setAttribute("staffattendanceList", staffattendanceList);
				request.getSession(false).setAttribute("EXcel",staffattendanceList);				
				
				JSONObject obj =new JSONObject();
				obj.put("staffattendanceList", staffattendanceList);
				response.getWriter().print(obj);
				
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceAction Ending");
		
		return mapping.findForward(MessageConstants.STAFF_ATTENDANCE_REPORT);
		
	}
	
	
	
	
	public ActionForward staffAttendanceExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendanceExcelReport Starting");
		

		String filePath = null;
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String teachername = "";
			String accyear = request.getParameter("accyear").trim();
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");
			String teachertype = request.getParameter("teachertype");
			teachername=request.getParameter("teachername").trim();
			String locid=request.getParameter("locid");
			LocationVO custSchoolInfo=new LocationVO();
			 custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locid);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StaffAttendanceDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*List<ReportMenuVo> MasterList = new ArrayList<ReportMenuVo>();
			MasterList = (List<ReportMenuVo>) request.getSession(false).getAttribute("EXcel");*/
	
			ReportMenuVo vo = new ReportMenuVo();
			
			vo.setTeachertype(teachertype);
			vo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
			vo.setTodate(HelperClass.convertUIToDatabase(todate));
			vo.setTeachertId(teachername);
			vo.setAccyearname(accyear);
			vo.setLocationId(locid);

			
			if(vo.getLocationId().equalsIgnoreCase("All")){
				vo.setLocationId("%%");
			}
			 if(teachername.equalsIgnoreCase("all")){
				 
				 vo.setTeachertId("%%");
				 
			 }
			 
			
			 String location_name=request.getParameter("locationname");
			 String acadyear_name=request.getParameter("accyearname");
			 String teach_Ttype=request.getParameter("teachertypeselect");
			 
			
			
			
			ArrayList<StaffAttendanceVo> MasterList = new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo,userLoggingsVo);

			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					MasterList);
			Map parameters = new HashMap();
			parameters.put("location_name", location_name);
			parameters.put("acadyear_name", acadyear_name);
			parameters.put("form_date", fromdate);
			parameters.put("to_date", todate);
			parameters.put("teach_Ttype", teach_Ttype);
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StaffAttendanceDetailsXLSReport.xls"));
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
					request.getRealPath("Reports/StaffAttendanceDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffAttendanceDetailsXLSReport.xls");
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
				+ " Control in ReportsAction : staffAttendanceExcelReport Ending");
		
		return null;
	}
	
	public ActionForward staffAttendancePDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : staffAttendancePDFReport Starting");
		
		
	
		
		try {


			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			 ServletContext servletContext = request.getServletContext();
			 
			/*List<AddFeeVO> Details = new ArrayList<AddFeeVO>();
			Details = (List<AddFeeVO>) request.getSession(false).getAttribute("feelistdownload");
*/              /*  List<DriverMsaterListVo> Details = new TransportBD()
              .getdriverList();
*/

			String teachername = "";
			String accyear = request.getParameter("accyear").trim();
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");
			String teachertype = request.getParameter("teachertype");
			teachername=request.getParameter("teachername").trim();
			String locid=request.getParameter("locid");
			
		
			 LocationVO custSchoolInfo=new LocationVO();
			 custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locid);
			
			ReportMenuVo vo = new ReportMenuVo();

			vo.setTeachertype(teachertype);
			vo.setFromdate(HelperClass.convertUIToDatabase(fromdate));
			vo.setTodate(HelperClass.convertUIToDatabase(todate));
			vo.setTeachertId(teachername);
			vo.setAccyearname(accyear);
			vo.setLocationId(locid);
			
			
			if(vo.getLocationId().equalsIgnoreCase("All")){
				vo.setLocationId("%%");
			}
			 if(teachername.equalsIgnoreCase("all")){
				 
				 vo.setTeachertId("%%");
				 
			 }
			 
			
			 String location_name=request.getParameter("locationname");
			 String acadyear_name=request.getParameter("accyearname");
			 String teach_Ttype=request.getParameter("teachertypeselect");
			
			
			
			
			
			List<StaffAttendanceVo> Details = new ArrayList<StaffAttendanceVo>();
			Details = new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo,userLoggingsVo);

			String sourceFileName = request.getRealPath("Reports/StaffAttendancePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Details);

			/*String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();*/

			String PropfilePath = servletContext.getRealPath("/")+ custSchoolInfo.getBoardlogo().trim();
			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			
			parameters.put("Image", PropfilePath);
			parameters.put("location_name", location_name);
			parameters.put("acadyear_name", acadyear_name);
			parameters.put("form_date", fromdate);
			parameters.put("to_date", todate);
			parameters.put("teach_Ttype", teach_Ttype);
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
 

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StaffAttendancePDFReport - " + ".pdf\"");

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
				+ " Control in StaffAttendanceReportAction : staffAttendancePDFReport Ending");
		
		return null;
	}
	
	
	public ActionForward getTeachernameAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getTeachernameAction Starting");
		
		
		try {
			
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String teachertype = "";
			teachertype = request.getParameter("teachertype");
			
			
			AllTeacherDetailsVo vo = new AllTeacherDetailsVo();
			vo.setTeacherType(teachertype);
		    vo.setLocid(request.getParameter("locationname"));
			

			
			
			
			if(teachertype.equalsIgnoreCase("teaching")){
				
				
				List<AllTeacherDetailsVo> teachinglist = new StaffAttendanceReportBD().getTeachingListBD(vo,userLoggingsVo);
				
				
				 JSONObject object=new JSONObject();
				 
				 object.put("teachinglist", teachinglist);
				 
				 response.getWriter().print(object);	
			
			}
			else{
				
			
				List<AllTeacherDetailsVo> nonteachinglist = new StaffAttendanceReportBD().getNonTeachingListBD(vo,userLoggingsVo);
				
				
				
				 JSONObject object=new JSONObject();
				 
				 object.put("nonteachinglist", nonteachinglist);
				 
				 response.getWriter().print(object);
				
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getTeachernameAction Ending");
		
		return null;
	}
	
	
	
	public ActionForward getStaffAttendanceList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceList Starting");
		
		
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			
	
			ReportMenuVo vo = new ReportMenuVo();
			
			
			vo.setAccyearname(request.getParameter("accyear"));
			vo.setTeachertype(request.getParameter("teachertype"));
			vo.setFromdate(HelperClass.convertUIToDatabase(request.getParameter("Fromdate")));
			vo.setTodate(HelperClass.convertUIToDatabase(request.getParameter("todate")));

			vo.setTeachername(request.getParameter("teachername"));
			vo.setLocationId(request.getParameter("locid"));
			
			if(vo.getLocationId().equalsIgnoreCase("All")){
				vo.setLocationId("%%");
			}
			
			
	      if(vo.getTeachername().equalsIgnoreCase("all")){
				vo.setTeachertId("%%");
			}
			else{
				
				vo.setTeachertId(vo.getTeachername());
			}
	      
				
				ArrayList<StaffAttendanceVo> staffattendanceList=new StaffAttendanceReportBD().getStaffAttendanceReportBD(vo,userLoggingsVo);	
				System.out.println("11111111111111"+staffattendanceList);		
				
				JSONObject obj =new JSONObject();
				obj.put("staffattendanceList", staffattendanceList);
				response.getWriter().print(obj);
				
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceReportAction : getStaffAttendanceList Ending");
		
		return null;
		
	}
	
	
	
}
