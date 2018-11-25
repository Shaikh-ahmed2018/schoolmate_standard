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
import com.centris.campus.delegate.LeaveBankDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.ReportMenuVo;



public class LeaveBankAction extends DispatchAction {
	
	private static Logger logger = Logger
			.getLogger(LeaveBankAction.class);
	
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward LeaveCategoryList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction :LeaveCategoryList Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_LEAVE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVETYPES);

			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			
			String searchTearm = request.getParameter("searchvalue");
			ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();

			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);	
			request.setAttribute("locationList", locationList);	
			
			if (searchTearm != null) {
				list = new LeaveBankDelegate().getSearchleavetypeDetails(searchTearm,academic_year,dbdetails);
				request.setAttribute("searchname", searchTearm);
				request.setAttribute("searchnamelist", searchTearm);
			} else {
				list = new LeaveBankDelegate().getleavetypeDetails(schoolLocation,academic_year,dbdetails);
			}
			request.setAttribute("LeaveListDetails", list);
			request.setAttribute("academic_year",academic_year);
			request.setAttribute("curr_loc",schoolLocation);
			
		} catch (Exception e) {

			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : LeaveCategoryList Ending");
		return mapping.findForward(MessageConstants.LEAVECAT_LIST);
	}
	
	
	public ActionForward addingleavebankscreen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		try {
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);

		/*	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);*/
			 
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			  
			  
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : adddesignation");

		return mapping.findForward(MessageConstants.ADD_LEAVEBANK_LIST);
	}
	
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			 
			LeaveBankForm aform = new LeaveBankForm();

		
			String createUser = HelperClass.getCurrentUserID(request);
			String academicyear = request.getParameter("academicyear");
			String totalleave = request.getParameter("totalleave");
			String allowedleave = request.getParameter("allowedleave");
			//String sno = request.getParameter("sno");
			String casualleave = request.getParameter("casualleave");
			String paidleave = request.getParameter("paidleave");
			String sickleave = request.getParameter("sickleave");
			String sno=request.getParameter("snoid");
			
			double sickleave1 = Double.parseDouble(sickleave);
			double paidleave1 = Double.parseDouble(paidleave);
			double casualleave1 = Double.parseDouble(casualleave);
			
			
			aform.setAcademicyear(academicyear);
			aform.setTotalleaves(totalleave);
			aform.setPermonth(allowedleave);
			aform.setSno(sno);
			aform.setSickleave(sickleave1);
			aform.setPaidleave(paidleave1);
			aform.setCasualleave(casualleave1);
			aform.setCreatedby(createUser);
			//("academicyear"+academicyear);
			//("totalleave"+totalleave);
			//("allowedleave"+allowedleave);
			//("sickleave"+sickleave1);
			//("paidleave"+paidleave1);
			//("setCasualleave"+casualleave1);
			//("sno"+sno);
			
			
			String delegate = new LeaveBankDelegate().insertLeaveBankDelegate(aform);
			
			request.setAttribute("attnhidden", sno);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", delegate);

			response.getWriter().print(jsonobj);

	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return null;

	}
	
	
	public ActionForward editleavebank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Starting");

		try {
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			//("edit leave banck action");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			
			LeaveBankForm aform = new LeaveBankForm();
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			
			String sno = request.getParameter("snoid");
			aform.setSno(sno);
		 			
			LeaveBankForm result = new LeaveBankDelegate().editleavebankdelegate(aform);	
         
		request.setAttribute("attnhidden", sno);
			
			request.setAttribute("editleavebank", result);
			
			 
		

	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : submit Ending");

		return mapping.findForward(MessageConstants.ADD_LEAVEBANK_LIST);

	}
	
	
	public  ActionForward deleteLeavebank(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action: delete Leave Starting");
		try {
			//("deleteaction is working");
			
			
		

			String[] deletelist = request.getParameterValues("year[]");
			
			//("deletelist"+deletelist);
		

			Boolean deletestatus = new LeaveBankDelegate().deleteLeave(deletelist);
			
			//("delete status======" + deletestatus);
			
			request.setAttribute("message", deletestatus);
			
			JSONObject json = new JSONObject();
			json.accumulate("deletestatus", deletestatus);
			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward leavebankexcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action : leavebankexcel  Starting");
		
		try {
		//("DOWNLOADING Leave bank EXCEL");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/LeaveBankDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			
			LeaveBankVO vo = new LeaveBankVO();
			ArrayList<LeaveBankVO> List = new LeaveBankDelegate().leavebanklist(vo,userLoggingsVo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/LeaveBankDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Class Details Excel Report" };
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
					request.getRealPath("Reports/LeaveBankDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=LeaveBankDetailsXLSReport.xls");
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
				+ " Control in LeaveBank Action : leavebankexcel   Ending");
				return null;
		
		
	}
	
	public ActionForward leavebankpdf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassAction : classPathDetailsPDFReport  Starting");

			try {

				//("downloading leave bank pdf");
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				LeaveBankVO vo = new LeaveBankVO();
				ArrayList<LeaveBankVO> Details = new LeaveBankDelegate()
						.leavebanklist(vo,userLoggingsVo);
				
 				
				String sourceFileName = request
						.getRealPath("Reports/LeaveBankPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						Details);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "LeaveBankPDF - " + ".pdf\"");

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
					+ " Control in ClassAction : classPathDetailsPDFReport  Ending");
			return null;

		}

	public  ActionForward searchleavbankList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Starting");
		
			
			String searchTextVal = request.getParameter("searchvalue");

			try {
				
				//("Search Action Is Working");
				//("Search Action Is Working"+searchTextVal);
				request.setAttribute(MessageConstants.MODULE_NAME,
						MessageConstants.BACKOFFICE_SETTINGS);
				
				request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
						MessageConstants.MODULE_SETTINGS);

				ArrayList<LeaveBankVO> list = new LeaveBankDelegate()
						.getSearchDetails(searchTextVal);
										
				request.setAttribute("leaveBank", list);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationAction: Update Ending");

	
				
		return mapping.findForward(MessageConstants.LEAVEBANK_LIST);

	}

	public synchronized ActionForward validAddLeave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validAddLeave Starting");
		try {
			String year = request.getParameter("academicyear");

			Boolean validate = new LeaveBankDelegate().validAddLeave(year);

			JSONObject json = new JSONObject();
			json.accumulate("validate", validate);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
	
	// Leave Category
	
	public ActionForward addingleaveCategoryscreen(ActionMapping mapping,ActionForm form,HttpServletRequest 
			request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addingleaveCategoryscreen Starting");
		try{
		
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_LEAVE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_TEACHERS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_LEAVE_LEAVETYPES);
			
			
			String title ="Add New Leave Type";
			request.setAttribute("title", title);
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			
			  
			ArrayList<LeaveBankVO>   catList = new LeaveBankDelegate().getleaveCatList(dbdetails);
			request.setAttribute("LeaveCatList", catList);
			 
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);	
			request.setAttribute("locationList", locationList);	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addingleaveCategoryscreen Ending");

		return mapping.findForward(MessageConstants.LEAVECAT_ADD__LIST);
	}
	
	public ActionForward addLeavesCategory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate addLeavesCategory Starting");
		try{
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String  validate  = null;
			//("Inside Action leave Bank");
			
//			String[] categorynames = request.getParameterValues("categorynames[]");
//			String[] shortnames = request.getParameterValues("shortnames[]");
//			String[] noofleaves = request.getParameterValues("noofleaves[]");
//			String[] catId = request.getParameterValues("catId[]");
//			String accyear = request.getParameter("accyear");
//			String location = request.getParameter("location");
//			String[] hiddenLEaveIdArray = request.getParameterValues("hiddenLEaveIdArray[]");
			/*
			if( hiddenLEaveIdArray !=null){
			 validate = new LeaveBankDelegate().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
			}*/
			//existing method
			//validate = new LeaveBankDelegate().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location,log_audit_session);
		
			//New Implementation
			LeaveBankVO leaveVo = new LeaveBankVO();
			leaveVo.setLeaveCodes(request.getParameter("leaveCodes").split(","));
			leaveVo.setLeaveNames(request.getParameter("leaveNames").split(","));
			leaveVo.setNoofLeaves(request.getParameter("noofLeaves").split(","));
			leaveVo.setAccumuLation(request.getParameter("accumuLation").split(","));
			leaveVo.setCarryF(request.getParameter("carryF").split(","));
			leaveVo.setAccyear(request.getParameter("accYear"));
			leaveVo.setLocId(request.getParameter("locId"));
			leaveVo.setCreateuser(HelperClass.getCurrentUserID(request));
			leaveVo.setLogaudit(log_audit_session);
			
			String status = new LeaveBankDelegate().addLeaveBank(leaveVo,dbdetails);
			
			JSONObject json = new JSONObject();
			json.accumulate("status", status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:addLeavesCategory Ending");
		
		return null;
	}
	
	public ActionForward editleavetypes(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate editleavetypes Starting");
		
		try{
				UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
				String accyear = request.getParameter("accyear");
				String loc =request.getParameter("loc");
				
				request.setAttribute("Accyear", accyear);
				request.setAttribute("loc", loc);
				
				
				String title = "Modify Leave Type";
				request.setAttribute("title", title);
			
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
				request.setAttribute("AccYearList", accYearList);
				
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);	
				request.setAttribute("locationList", locationList);	
				
				ArrayList<LeaveBankVO> catList = new LeaveBankDelegate().getaccLocCatList(accyear,loc);
				
				request.setAttribute("AccName", catList.get(0).getAcademicyear());
				request.setAttribute("Locname",catList.get(0).getLocationName());
			
				ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
				list = new LeaveBankDelegate().editleavetypes(accyear,loc);
				request.setAttribute("editList",list);
				request.setAttribute("LeaveCatList", list);
				
				//(list.get(0).getLeaveName());
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:editleavetypes Ending");
		
		
		return mapping.findForward(MessageConstants.LEAVECAT_EDIT__LIST);
	}
	
public ActionForward checkLeaveType (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBank Action:validate addingleaveCategoryscreen Starting");
		
		try{
			
			String accyear = request.getParameter("accyear");
			String loc =request.getParameter("location");
			String category = request.getParameter("cat");
			
			String status = new LeaveBankDelegate().checkLeaveType(accyear,loc,category);
			
			//(status);
			
			JSONObject json = new JSONObject();
			json.accumulate("status", status);
			response.getWriter().print(json);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	return null;
	}
	
public ActionForward checkDuplicacy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action:validate addingleaveCategoryscreen Starting");
	
	try{
		
		String accyear = request.getParameter("accyear");
		String loc =request.getParameter("location");
		
		String status = new LeaveBankDelegate().checkDuplicacy(accyear,loc);
		
		//(status);
		
		JSONObject json = new JSONObject();
		json.accumulate("status", status);
		response.getWriter().print(json);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	return null;
}
	
public ActionForward getLeavetypes(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : validate getLeavetypes Starting");
	
	try{
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String accyear = request.getParameter("accyear");
		String loc =request.getParameter("location");
		
		if(loc.equalsIgnoreCase("all")){
			loc = "%%";
		}
		
		ArrayList<LeaveBankVO>list = new LeaveBankDelegate().getleavetypeDetails(loc,accyear,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("leaveDetails",list);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : getLeavetypes Ending");
	return null;
}

public ActionForward updateLeaveDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : updateLeaveDetails Starting");
	
	try{
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		
		LeaveBankVO leaveVo = new LeaveBankVO();
		leaveVo.setShortName(request.getParameter("leavecode"));
		leaveVo.setLeaveName(request.getParameter("leavename"));
		leaveVo.setNoofleaves(request.getParameter("noofleave"));
		leaveVo.setLeave_cycle(request.getParameter("lcycle"));
		leaveVo.setCarryFs(request.getParameter("carryf"));
		leaveVo.setAccyear(request.getParameter("accdemicY"));
		leaveVo.setLocId(request.getParameter("locId"));
		leaveVo.setMax_leav_per_month(Float.parseFloat(request.getParameter("maxleave")));
		leaveVo.setMax_consecu_lea_perm(Float.parseFloat(request.getParameter("maxcons")));
		leaveVo.setMin_lea_per_month(Float.parseFloat(request.getParameter("minleave")));
		leaveVo.setIsprobationary(Float.parseFloat(request.getParameter("isprob")));
		leaveVo.setWeek_off_inclusion(request.getParameter("weekoff"));
		leaveVo.setCreateuser(HelperClass.getCurrentUserID(request));
		leaveVo.setLogaudit(log_audit_session);
		leaveVo.setIsGenderSpec(request.getParameter("isgender"));
		leaveVo.setLeaveid(Integer.parseInt(request.getParameter("leaveid")));
		
		String result = new LeaveBankDelegate().updateLeaveDetails(leaveVo,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("status",result);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : getLeavetypes Ending");
	return null;
}

public ActionForward deleteLeaveDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : updateLeaveDetails Starting");
	
	try{
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		int leaveid = 0;
		String lid = request.getParameter("leaveid");
		String result = "fail";
		
		if(lid!=null && !lid.trim().equalsIgnoreCase("")){
			leaveid = Integer.parseInt(lid);
			result = new LeaveBankDelegate().deleteLeaveDetails(leaveid,dbdetails,log_audit_session);
		}
		
		JSONObject json = new JSONObject();
		json.accumulate("status",result);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : getLeavetypes Ending");
	return null;
}	

public ActionForward checkLeaveCode(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : updateLeaveDetails Starting");
	
	try{
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		LeaveBankVO leavedetails = new LeaveBankVO();
		leavedetails.setAccyear(request.getParameter("accyear"));
		leavedetails.setLocId(request.getParameter("locid"));
		leavedetails.setShortName(request.getParameter("leavecode"));
		
		String result = new LeaveBankDelegate().checkLeaveCode(leavedetails,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("status",result);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : getLeavetypes Ending");
	return null;
}
public ActionForward checkLeaveName(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : checkLeaveName Starting");
	
	try{
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		LeaveBankVO leavedetails = new LeaveBankVO();
		leavedetails.setAccyear(request.getParameter("accyear"));
		leavedetails.setLocId(request.getParameter("locid"));
		leavedetails.setLeaveName(request.getParameter("leavename"));
		
		String result = new LeaveBankDelegate().checkLeaveName(leavedetails,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("status",result);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : checkLeaveName Ending");
	return null;
}

public ActionForward StaffLeaveBank(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction :StaffLeaveBank Starting");
	try {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LEAVE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LEAVE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LEAVE_STAFF_MAPPING);
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

		ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
		request.setAttribute("accYearList", accYearList);
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);	
		request.setAttribute("locationList", locationList);	
		 
		List<LeaveBankVO> staus = new LeaveBankDelegate().getLeaveMapStatus(academic_year,schoolLocation,dbdetails);
		
		request.setAttribute("academic_year",academic_year);
		request.setAttribute("leave_map",staus);
		
		
	} catch (Exception e) {

		e.printStackTrace();

	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : StaffLeaveBank Ending");
	return mapping.findForward(MessageConstants.STAFFLEAVEMAPPING);
}

public ActionForward LeaveStaffList(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction :StaffLeaveBank Starting");
	try {

		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LEAVE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LEAVE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_LEAVE_STAFF_MAPPING);
	
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<LeaveBankVO> getStatus = new LeaveBankDelegate().getStaffMapSt(dbdetails,accyear+","+locid);
		
		ArrayList<LeaveBankVO> list = new LeaveBankDelegate().getleavetypeDetails(locid,accyear,dbdetails);
		
		request.setAttribute("allleaves",list);
		
		request.setAttribute("academicname",HelperClass.getAcademicYearFace(accyear, dbdetails));
		request.setAttribute("accyid",accyear);
		request.setAttribute("locid",locid);
		request.setAttribute("locatinname",HelperClass.getLocationFace(locid, dbdetails));
		request.setAttribute("getStatus",getStatus);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AdminMenuAction : StaffLeaveBank Ending");
	return mapping.findForward(MessageConstants.LEAVESTAFFLIST);
}

public ActionForward getLeavesMapStatusByFilter(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		String academic_year = request.getParameter("accyear");
		String location = request.getParameter("locid");
		
		if(location.equalsIgnoreCase("all")){
			location = "%%";
		}
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		List<LeaveBankVO> staus = new LeaveBankDelegate().getLeaveMapStatus(academic_year,location,dbdetails);
	
		JSONObject json = new JSONObject();
		json.put("maplist", staus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward addStaffLeaves(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		
		LeaveBankVO vo = new LeaveBankVO();
		vo.setAccyearcode(request.getParameter("accyear"));
		vo.setLocId(request.getParameter("locid"));
		vo.setTeacherId(request.getParameter("teaids"));
		vo.setLeaveID(request.getParameter("lids"));
		vo.setNoofleaves(request.getParameter("appl"));
		vo.setCreateuser(HelperClass.getCurrentUserID(request));
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String staus = new LeaveBankDelegate().addStaffLeaves(vo,dbdetails);
	
		JSONObject json = new JSONObject();
		json.put("staus", staus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward getStaffMapSt(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<LeaveBankVO> getStatus = new LeaveBankDelegate().getStaffMapSt(dbdetails,accyear+","+locid);
		
		JSONObject json = new JSONObject();
		json.put("maplist", getStatus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward getStaffMappedLeaves(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		String accyear = request.getParameter("accyear");
		String locid = request.getParameter("locid");
		String mapId = request.getParameter("mapid");
		String teaid = request.getParameter("teaid");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<LeaveBankVO> getStatus = new LeaveBankDelegate().getStaffMappedLeaves(dbdetails,accyear+","+locid+","+mapId+","+teaid);
		
		JSONObject json = new JSONObject();
		json.put("MappedLeaves", getStatus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward updateStafLeaves(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		
		String data = request.getParameter("mapid")+","+request.getParameter("teaid");
		String leaveids = request.getParameter("leaid");
		String appleave = request.getParameter("appleave");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String getStatus = new LeaveBankDelegate().updateStafLeaves(dbdetails,data,leaveids,appleave);
		
		JSONObject json = new JSONObject();
		json.put("status", getStatus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward getStaffUnmappedLeaves(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		
		String data = request.getParameter("teaid")+","+request.getParameter("accyear")+","+request.getParameter("locid");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<LeaveBankVO> list = new LeaveBankDelegate().getStaffUnmappedLeaves(dbdetails,data);
		
		JSONObject json = new JSONObject();
		json.put("list", list);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward addSingleStaffLeaves(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		LeaveBankVO vo = new LeaveBankVO();
		vo.setAccyearcode(request.getParameter("accyear"));
		vo.setLocId(request.getParameter("locid"));
		vo.setTeacherId(request.getParameter("teaids"));
		vo.setLeaveID(request.getParameter("lids"));
		vo.setNoofleaves(request.getParameter("appl"));
		vo.setCreateuser(HelperClass.getCurrentUserID(request));
		vo.setLmapId(Integer.parseInt(request.getParameter("mapid")));
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String staus = new LeaveBankDelegate().addSingleStaffLeaves(vo,dbdetails);
	
		JSONObject json = new JSONObject();
		json.put("staus", staus);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public ActionForward StaffLeaveBankDetails(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LEAVE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LEAVE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STAFF_LEAVE_BANK);
		
		String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
		String curr_loc = request.getSession(false).getAttribute("current_schoolLocation").toString();
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears(dbdetails);
		request.setAttribute("accYearList", accYearList);
		
		ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);	
		request.setAttribute("locationList", locationList);	
		
		List<LeaveBankVO> llist = new LeaveBankDelegate().getleavetypeDetails(curr_loc,academic_year,dbdetails);
		List<LeaveBankVO> lbank = new LeaveBankDelegate().getStaffLeaveBankDetails(academic_year,curr_loc,dbdetails);
		
		String[] total = new String[]{"Tot.Leaves","Appl.Leaves", "Consumed", "Available"};
		//(lbank.size());
		
		//("sadasdas"+lbank.get(0).getDetails().size());
		
		request.setAttribute("llist",llist);
		request.setAttribute("total",total);
		request.setAttribute("totalsize", total.length);
		request.setAttribute("lbank", lbank);
		request.setAttribute("curr_accy", academic_year);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return mapping.findForward(MessageConstants.STAFF_LEAVE_BANK_DISPLAY);
}

public ActionForward addLeavePolicy(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action:validate addLeavePolicy Starting");
	try{
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String  validate  = null;
		//("Inside Action leave Bank");
		
		//New Implementation
		LeaveBankVO leaveVo = new LeaveBankVO();
		leaveVo.setShortName(request.getParameter("leavecode"));
		leaveVo.setLeaveName(request.getParameter("leavename"));
		leaveVo.setNoofleaves(request.getParameter("noofleaves"));
		leaveVo.setLeave_cycle(request.getParameter("lcycle"));
		leaveVo.setCarryFs(request.getParameter("carryf"));
		leaveVo.setAccyear(request.getParameter("accdemicY"));
		leaveVo.setLocId(request.getParameter("locId"));
		leaveVo.setMax_leav_per_month(Float.parseFloat(request.getParameter("maxleave")));
		leaveVo.setMax_consecu_lea_perm(Float.parseFloat(request.getParameter("maxcons")));
		leaveVo.setMin_lea_per_month(Float.parseFloat(request.getParameter("minleave")));
		leaveVo.setIsprobationary(Float.parseFloat(request.getParameter("isprob")));
		leaveVo.setWeek_off_inclusion(request.getParameter("weekoff"));
		leaveVo.setCreateuser(HelperClass.getCurrentUserID(request));
		leaveVo.setLogaudit(log_audit_session);
		leaveVo.setIsGenderSpec(request.getParameter("isgender"));
		
		String status = new LeaveBankDelegate().addLeavePolicy(leaveVo,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("status", status);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : addLeavePolicy Ending");
	
	return null;
}

public ActionForward getLeavePolicy(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action:getLeavePolicy Starting");
	try{
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String leaveid = request.getParameter("id");
		
		List<LeaveBankVO> list = new LeaveBankDelegate().getLeavePolicy(leaveid,dbdetails);
		
		JSONObject json = new JSONObject();
		json.accumulate("dataList", list);
		response.getWriter().print(json);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBank Action : getLeavePolicy Ending");
	
	return null;
}

public ActionForward getStaffLeaveBank(ActionMapping mapping,ActionForm form, HttpServletRequest request,
		HttpServletResponse response){
	
	try{
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_LEAVE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_LEAVE);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STAFF_LEAVE_BANK);
		
		String academic_year = request.getParameter("academic_year");
		String location = request.getParameter("location");
		
		UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		List<LeaveBankVO> llist = new LeaveBankDelegate().getleavetypeDetails(location,academic_year,dbdetails);
		List<LeaveBankVO> lbank = new LeaveBankDelegate().getStaffLeaveBankDetails(academic_year,location,dbdetails);
		
		String[] total = new String[]{"Tot.Leaves","Appl.Leaves", "Consumed", "Available"};
		//(lbank.size());
		
		//("sadasdas"+lbank.get(0).getDetails().size());
		JSONObject json = new JSONObject();
		json.put("llist", llist);
		json.put("total",total);
		json.put("totalsize", total.length);
		json.put("lbank", lbank);
		response.getWriter().print(json);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


}
	
	

