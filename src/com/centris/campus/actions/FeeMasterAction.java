package com.centris.campus.actions;
import java.awt.print.PrinterJob;
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
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.daoImpl.FeeMasterDAOIMPL;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.delegate.ExpenditureBD;
import com.centris.campus.delegate.FeeCollectionBD;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ExpenditureVO;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UserDetailVO;

public class FeeMasterAction extends DispatchAction

{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	
	public ActionForward addfeedetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : addfeedetails Starting");
		try {
			
			String status = request.getParameter("result");
			if (status != null) {

				if (status.equalsIgnoreCase(MessageConstants.SuccessMsg)) {

					request.setAttribute("successmessagediv",
							MessageConstants.SuccessMsg);
				} else if ((status.equalsIgnoreCase(MessageConstants.ErrorMsg))) {

					request.setAttribute("errormessagediv",
							MessageConstants.ErrorMsg);
				} else if (status.equalsIgnoreCase(MessageConstants.SuccessUpMsg)) {
					request.setAttribute("successmessagediv",
							MessageConstants.SuccessUpMsg);
				} else if (status.equalsIgnoreCase(MessageConstants.ErrorUpMsg)) {
					request.setAttribute("successmessagediv",
							MessageConstants.ErrorUpMsg);
				}

			}
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
            String msge="New Fee Details";
            request.setAttribute("FeeDetail", msge);
             		
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(dbdetails);
			}
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("academic_year",academic_year);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : addfeedetails Ending");

		return mapping.findForward(MessageConstants.FEE_ADD_DETAILS_LIST);

	}

	public ActionForward AddFeeDetailsMaster(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : AddFeeDetailsMaster Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			UserLoggingsPojo pojo = (UserLoggingsPojo ) request.getSession(false).getAttribute("token_information");

			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			AddFeeVO vo = new AddFeeVO();
			
			vo.setFeeTypeId(request.getParameter("feeTypeId"));
			vo.setId(request.getParameter("id"));
			vo.setName(request.getParameter("name"));
			vo.setAcademicYear(request.getParameter("academicYear"));
			vo.setLocationId(request.getParameter("locationId"));
			vo.setLog_audit_session(log_audit_session);
			vo.setCustid(pojo.getCustId());
			//("name" + request.getParameter("name"));

			vo.setDescription(request.getParameter("description"));

			vo.setCreatedby(HelperClass.getCurrentUserID(request));

			vo.setConcessiontype(request.getParameter("concessiontype"));

			//("fee type  " + request.getParameter("feeTypeId"));

			FeeMasterDelegate delegate = new FeeMasterDelegate();

			String result = delegate.insertFeeDetails(vo,pojo);

			JSONObject jsonobj = new JSONObject();

			jsonobj.put("jsonResponse", result);

			response.getWriter().print(jsonobj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : AddFeeDetailsMaster Ending");

		/* return mapping.findForward(MessageConstants.FEE_DETAILS_LIST); */
		return null;

	}

	
	public ActionForward getnamecount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getnamecount Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

	
		boolean status = false;
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String locationId=request.getParameter("locationId");
		String accyear=request.getParameter("accyear");

		try {

			AddFeeVO vo = new AddFeeVO();
			vo.setId(id);
			vo.setName(name);
			vo.setLocationId(locationId);
			vo.setAcademicYear(accyear);
            vo.setCustid(userLoggingsVo.getCustId());
			FeeMasterDelegate delegate = new FeeMasterDelegate();
			status = delegate.getnamecount(vo,userLoggingsVo);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("message", status);

			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getnamecount Ending");

		return null;

	}

	public ActionForward editFeeDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : editFeeDetails Starting");

		String name = request.getParameter("name");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEESETUP);
			
			String msge="Modify Fee Details";
            request.setAttribute("FeeDetail", msge);
			
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);
			
			AddFeeVO vo = new AddFeeVO();

			vo.setId(name);

			AddFeeVO editlist = new FeeMasterDelegate().editFeeDetails(vo,dbdetails);
			

			request.setAttribute("editlist", editlist); 
			
			request.setAttribute("historylocId", request.getParameter("historylocId"));
			request.setAttribute("historyacyearId", request.getParameter("historyacyearId"));
			request.setAttribute("historystatus", request.getParameter("historystatus"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : editFeeDetails Ending");

		return mapping.findForward(MessageConstants.FEE_ADD_DETAILS_LIST);

	}

	public ActionForward deleteFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : deleteFeeDetails Starting");

		boolean result = false;
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String name = request.getParameter("getDataArray");//Name should be according to js name4
		String getDataArray[]=name.split(",");

		try {
             
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String reson=request.getParameter("reason");
			String status=request.getParameter("status");
			String button=request.getParameter("button");
			AddFeeVO vo = new AddFeeVO();
			vo.setGetDataArray(getDataArray);//-------------5
			vo.setLog_audit_session(log_audit_session);
			vo.setRemark(reson);
			vo.setStatus(status);
             
			result = new FeeMasterDelegate().deleteFeeDetails(vo,button,userLoggingsVo);
			//("result::::::::::"+result);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("jsonResponse", result);

			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : deleteFeeDetails Ending");

		return null;

	}

	public ActionForward searchFeeDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : searchFeeDetails Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_ADMIN);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_ADMIN);

			AddFeeVO vo = new AddFeeVO();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

	
			vo.setName(request.getParameter("searchvalue"));
            vo.setCustid(userLoggingsVo.getCustId());
			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate()
					.searchFeeDetails(vo,userLoggingsVo);

			request.setAttribute("feelist", feelist);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : searchFeeDetails Ending");

		return mapping.findForward(MessageConstants.FEE_DETAILS_LIST);
	}

	public ActionForward FeeDetailsXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : FeeDetailsXLS  Starting");

		try {
			//("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FeeDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			AddFeeVO vo = new AddFeeVO();

			vo.setName(request.getParameter("searchvalue"));
			vo.setCustid(userLoggingsVo.getCustId());
			ArrayList<AddFeeVO> List = new FeeMasterDelegate().getfeedetails(vo,userLoggingsVo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(List);
			
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Stream Details Excel Report" };
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
					request.getRealPath("Reports/FeeDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeDetailsXLSReport.xls");
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
				+ " Control in FeeMasterAction : FeeDetailsXLS   Ending");
		return null;
	}

	public ActionForward FeeDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : FeeDetailsPDFReport  Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			//("downloading pdf");
			AddFeeVO vo = new AddFeeVO();
			vo.setName(request.getParameter("searchvalue"));
            vo.setCustid(userLoggingsVo.getCustId());
			ArrayList<AddFeeVO> Details = new FeeMasterDelegate()
					.getfeedetails(vo,userLoggingsVo);

			String sourceFileName = request
					.getRealPath("Reports/FeeDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Details);

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
					+ "FeeDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeMasterAction : FeeDetailsPDFReport  Ending");
		return null;

	}
	
	

	
	public ActionForward getFeeTypeCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeCount Starting");

		
		String feeTypeId = request.getParameter("feeTypeId");
		String feeType = request.getParameter("feeType");
		String locationId=request.getParameter("locationId");
		String accyear=request.getParameter("accyear");
		//("FeeType COunt method in Action: "+feeTypeId+" --- "+feeType);

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			AddFeeVO vo = new AddFeeVO();
			vo.setFeeTypeId(feeTypeId);
			vo.setFeeType(feeType);
			vo.setAcademicYear(accyear);
			vo.setLocationId(locationId);
			 
			
			FeeMasterDelegate delegate = new FeeMasterDelegate();
		    String status = delegate.getFeeTypeCount(vo,userLoggingsVo);
           
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("message", status);

			response.getWriter().println(jsonObject);
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeCount Ending");

		return null;

	}
	
	public ActionForward getFeeTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeList Starting");

		List<AddFeeVO> lists = new ArrayList<AddFeeVO>();
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String locationId=request.getParameter("locationId");
		
			
			AddFeeVO vo = new AddFeeVO();
             vo.setCustid(userLoggingsVo.getCustId());
             vo.setLocationId(locationId);
			FeeMasterDelegate delegate = new FeeMasterDelegate();
			lists = delegate.feeTypeListBD(vo,userLoggingsVo);
			
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("FeeTypeList", lists);
			
			response.getWriter().println(jsonObject);
			
			//("Action class feeTypelist after JSON setting");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeList Ending");

		return null;

	}
	public ActionForward saveFineConfiguration(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFineConfiguration Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

		String userCode = userDetailVO.getUserId();
		
		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String locationArray[]=request.getParameter("locationArray").split(",");
			String classArray[]=request.getParameter("classArray").split(",");
			String accyearArray[]=request.getParameter("accyearArray").split(",");
			String termArray[]=request.getParameter("termArray").split(",");
			String days[]=request.getParameter("dayArray").split(",");
			String amountArray[]=request.getParameter("amountArray").split(",");
			String isApplicable=request.getParameter("isApplicable");
			String accyear=request.getParameter("accyear");
			String status=new FeeMasterDAOIMPL().saveFineConfiguration(accyearArray,termArray,locationArray,classArray,days,amountArray,isApplicable,userCode,accyear,log_audit_session,userLoggingsVo);
			
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFineConfiguration Ending");
		
		return null;
	}
	
	public ActionForward FeeDetailsDefaulterPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : FeeDetailsDefaulterPDF  Starting");

		try {
			//("downloading pdf");
			  ServletContext servletContext = request.getServletContext();
			  UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			LocationVO custSchoolInfo=new LocationVO();  
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId,cpojo);
			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterPdf.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}
			
			 String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			Map parameters = new HashMap();
			
			/*parameters.put("Image", PropfilePath);*/
			parameters.put("locname",custSchoolInfo.getSchname());
			parameters.put("classname",classname+" & "+divname);
			parameters.put("divname", divname);
			parameters.put("termname", termname);
			parameters.put("accname", accname);
			parameters.put("schladd", schladd);

            parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
            parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
            parameters.put("custSchoollogo", schoollogo);
            parameters.put("custSchoolboardlogo", PropfilePath);
            parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
            parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
            parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
            parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
            parameters.put("branch", locname);
            
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\"" + "FeeDetailsPDF - " + ".pdf\"");
			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
		}
		catch (Exception e)
		{logger.error(e.getMessage(), e);
			e.printStackTrace();}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : FeeDetailsDefaulterPDF   Ending");
		return null;
	}
		
	
	public ActionForward FeeDetailsDefaulterXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : FeeDetailsDefaulterXLS  Starting");

		try {
			 UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			//("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId,cpojo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			
			Map parameters = new HashMap();
			//parameters.put("Image", PropfilePath);
			parameters.put("locname",custSchoolInfo.getSchname());
			parameters.put("classname",classname+" & "+divname); 
			parameters.put("divname", divname);
			parameters.put("termname", termname);
			parameters.put("accname", accname);
			parameters.put("schladd", schladd);
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", locname);
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/FeeReportDefaulterExcel.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Defaulter Fee Report" };
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);
			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/FeeReportDefaulterExcel.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeReportDefaulterExcel.xls");
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
				+ " Control in FeeMasterAction :FeeDetailsDefaulterXLS  Ending");
		return null;
	}
	
	
	public ActionForward PrintDefautFeeReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : PrintDefautFeeReport Starting");
		try{
			 UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			String locname = request.getParameter("locname");
			String classname = request.getParameter("classname");
			String divname = request.getParameter("divname");
			String termname = request.getParameter("termname");
			String accname = request.getParameter("accname");
			String schladd = res.getString("AddressLine1");
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			
			Map parameters = new HashMap();
				//parameters.put("Image", PropfilePath);
				parameters.put("locname", locname);
				parameters.put("classname",classname+" & "+divname);
				parameters.put("divname", divname);
				parameters.put("termname", termname);
				parameters.put("accname", accname);
				parameters.put("schladd", schladd);
			
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId,cpojo);
			String sourceFileName = request.getRealPath("Reports/FeeReportDefaulterExcel.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();


			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			//JasperViewer.viewReport(jasperPrint, false);
			//JasperExportManager.exportReportToPdfFile( jasperPrint, "buspasscard"+termName+".pdf" );
			 //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setVisible(true);
			
			PrinterJob job = PrinterJob.getPrinterJob();
			   int selectedService = 0;
			   selectedService = 0;
			   PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			   printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			/*   MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
			   printRequestAttributeSet.add(mediaSizeName);*/
			   printRequestAttributeSet.add(new Copies(1));
			   JRPrintServiceExporter exporter;
			   exporter = new JRPrintServiceExporter();
			   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			   exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			   exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			   exporter.exportReport();
			   job.print(printRequestAttributeSet);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction :PrintDefautFeeReport  Ending");
		return mapping.findForward(MessageConstants.STUDENTBUSCARD);
	}
	
	//Add fee type master
	
	public ActionForward addFeeTypeMaster(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : addFeeTypeMaster Starting");

		try {
			
			String arg = "Add Fee Type Details";
			request.setAttribute("Fee", arg);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : addFeeTypeMaster Ending");

		 return mapping.findForward(MessageConstants.FEE_TYPE_MASTER); 

	}
	
	public ActionForward saveFeeType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFeeType Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		/*	String userType = userDetailVO.getUserNametype();*/
			String userCode = userDetailVO.getUserId();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String feetypeId = request.getParameter("feetypeid");
			String feetypename = request.getParameter("feetypename");
	/*		String hiddenfeeid = request.getParameter("hiddentypeid");*/
			
			FeeNameVo vo = new FeeNameVo();
			vo.setFeeId(feetypeId);
			vo.setFeename(feetypename);
			vo.setUserid(userCode);
			vo.setLog_audit_session(log_audit_session);
			vo.setCustid(userLoggingsVo.getCustId());
			String result = new FeeMasterDAOIMPL().saveFeeType(vo,userLoggingsVo);
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : saveFeeType Ending");

		 return null; 

	}
	
	public ActionForward updateFeeType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : updateFeeType Starting");

		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			//("update");
			
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	/*		String userType = userDetailVO.getUserNametype();*/
			String userCode = userDetailVO.getUserId();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String feetypeId = request.getParameter("feetypeid");
			String feetypename = request.getParameter("feetypename");
			String hiddenfeeid = request.getParameter("hiddentypeid");
			
			
			FeeNameVo vo = new FeeNameVo();
			vo.setFeeId(feetypeId);
			vo.setFeename(feetypename);
			vo.setUserid(userCode);
			vo.setSno(Integer.parseInt(hiddenfeeid));
			vo.setLog_audit_session(log_audit_session);
			String result = new FeeMasterDAOIMPL().saveFeeType(vo,userLoggingsVo);
			
			JSONObject json = new JSONObject();
			json.put("results",result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : updateFeeType Ending");

		 return null; 

	}
	
	public ActionForward getFeeTypeById(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeById Starting");

		try {
			
			String title = "Modify Fee Type Details";
			request.setAttribute("Fee", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String feetypeId = request.getParameter("feetypeid");
			
			FeeNameVo feedetails = new FeeMasterDAOIMPL().getFeeTypeById(feetypeId,userLoggingsVo);
			request.setAttribute("feedetails",feedetails);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeTypeById Ending");

		 return mapping.findForward(MessageConstants.FEE_TYPE_MASTER); 
	}
	
	public ActionForward deletefeetype(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : deletefeetype Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String feetypeId[] = request.getParameterValues("feetypeid[]");
			
			String reason=request.getParameter("reason");
			String status=request.getParameter("status");
			String button=request.getParameter("button");
			
			String result = new FeeMasterDAOIMPL().deletefeetype(feetypeId,log_audit_session,reason,status,button,userLoggingsVo);
		
 
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : deletefeetype Ending");

		 return null; 
	}
	
	public ActionForward checkduplicatefeeId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : checkduplicatefeeId Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		
			String feetypeId = request.getParameter("feetypeid");
			
			String result = new FeeMasterDAOIMPL().checkduplicatefeeId(feetypeId,userLoggingsVo);
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : checkduplicatefeeId Ending");

		 return null; 
	}
	
	public ActionForward checkduplicatefeeTypeName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : checkduplicatefeeTypeName Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_TYPE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			String feetypename= request.getParameter("feetypename");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			
			String result = new FeeMasterDAOIMPL().checkduplicatefeeTypeName(feetypename,userLoggingsVo);
			
			JSONObject json = new JSONObject();
			json.put("status",result);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : checkduplicatefeeTypeName Ending");

		 return null; 
	}
	
	public ActionForward getStatusList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getStatusList Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			String status=request.getParameter("status");
			List<FeeNameVo> feetypelist = new FeeMasterDAOIMPL().getFeeTypeList(status,userLoggingsVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("FeeTypeList", feetypelist);
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getStatusList Ending");
		return null;
	}
	
	
	public ActionForward getFeeStatusList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	  {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeStatusList Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

	
			AddFeeVO vo = new AddFeeVO();
			vo.setName(request.getParameter("searchvalue"));
			vo.setAcademicYear(request.getParameter("academicYear"));
			vo.setLocationId(request.getParameter("locationId"));
			vo.setStatus(request.getParameter("status"));
			vo.setCustid(userLoggingsVo.getCustId());
			//List<AddFeeVO> feedetaillist = new FeeMasterDAOIMPL().getFeeStatusList(locId,accId,status);
			
			
			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate().getfeedetails(vo,userLoggingsVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("feedetaillist", feelist);
			response.getWriter().println(jsonObject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeStatusList Ending");

		return null;
	
	}
	
	
	
	
	
	
	
	public ActionForward getFeeconseStatusList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeconseStatusList Starting");

	
		try {
			 
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			
			List<StudentRegistrationVo> List = null;
			
			StudentRegistrationVo data= new StudentRegistrationVo();
			String locationId=request.getParameter("locationId");
			String academicYear=request.getParameter("academicYear");
			String classId=request.getParameter("classId");
			String divisionId=request.getParameter("divisionId");
			String searchTerm=request.getParameter("searchTerm");
		    String status=request.getParameter("status");
			data.setLocationId(locationId);
			data.setAcademicYear(academicYear);
			data.setClasscode(classId);
			data.setSection_id(divisionId);
			data.setSearchTerm(searchTerm);
            data.setIsActive(status);
             
			//List<AddFeeVO> feedetaillist = new FeeMasterDAOIMPL().getFeeStatusList(locId,accId,status);
			
        	List = new StudentRegistrationDaoImpl().getStudentListByJsForScholorship(data,userLoggingsVo);
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("studentdetailslist", List);
			
			response.getWriter().println(jsonObject);
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getFeeconseStatusList Ending");

		return null;
	
	}
	public ActionForward getExpendeStatusList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getExpendeStatusList Starting");

	
		try {
			 
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_EXPENDITURE);
			String status=request.getParameter("status"); 
			ExpenditureVO vo = new ExpenditureVO();

			
            vo.setIsActive(status);
			

			ArrayList<ExpenditureVO> expndList = new ExpenditureBD().getExpenditureDetails(vo);
			
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("expndList", expndList);
			
			response.getWriter().println(jsonObject);
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterAction : getExpendeStatusList Ending");

		return null;
	
	}
	
	
}