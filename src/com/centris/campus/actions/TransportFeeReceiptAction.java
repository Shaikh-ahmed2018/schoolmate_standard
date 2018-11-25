package com.centris.campus.actions;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.ReportMenuVo;


public class TransportFeeReceiptAction extends DispatchAction 
{
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	private static final Logger logger = Logger
			.getLogger(TransportFeeReceiptAction.class);

	public ActionForward TransportFeeReceipt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : TransportFeeReceipt Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_TRANSPORTFEE);

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			
			ArrayList<ReportMenuVo> classDetails = new ReportsMenuBD().getClassDetails(custdetails);
 
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("locationList", locationList);
			
			request.setAttribute("classDetails", classDetails);
			
			System.out.println("year name "+accYearList.get(0).getAccyearname());
			request.getSession(false).setAttribute("accYear",accYearList.get(0).getAccyearname());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : TransportFeeReceipt Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_FEE_REPORT);
	}
	
	
	public ActionForward getSectionByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getSectionByClass Starting");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD().getSectionsByClass(classId,location,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("SectionList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getSectionByClass Ending");

		return null;
	}
	
	
	public ActionForward getTransportFeeExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Starting");
		
		String filePath = null;

		try {
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			String classname=request.getParameter("classname");
			String accYear=request.getParameter("accYear");
		    String locname=request.getParameter("locname");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(custdetails,location);
		
		
			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
				accyear = HelperClass.getCurrentYearID(custdetails);
			}
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null || section == "" || section.equalsIgnoreCase("")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
		ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
			obj.setClass_and_section(classname);
				
			
			
			/*ArrayList<ReportMenuVo> list = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentList");*/
			
			ArrayList<ReportMenuVo> data = new ReportsMenuBD().gettransportfeeDetails(obj,custdetails);
/*			ArrayList<ReportMenuVo> datalist = new ReportsMenuBD().getAccYears(custdetails.getCustId());*/
			System.out.println("data length>>>"+data.size());
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
			
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			
			 String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			
			
			Map mapdata = new HashMap();

			mapdata.put("accYear",accYear);
			mapdata.put("class_section",classname);
			mapdata.put("totalstudents", Integer.toString(data.size()));
			mapdata.put("locationname", custSchoolInfo.getSchname());
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/TransportFeeReportXLS.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(request.getRealPath("Reports/TransportFee.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Transport Details" };
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

			pdfxls = new File(request.getRealPath("Reports/TransportFee.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=TransportFee.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			stream.close();

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTransportFeeExcelReport Ending");

		return null;
	}
	
	public ActionForward getTransportFeePDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Starting");

			try {
				ServletContext servletContext = request.getServletContext();
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
		
				
				String location = request.getParameter("location");
				String accyear = request.getParameter("accyear");
				String classId = request.getParameter("classId");
				String section = request.getParameter("section");
				String term = request.getParameter("term");
				String termstatusId = request.getParameter("termstatusId");
				String classname=request.getParameter("classname");
				String accYear=request.getParameter("accYear");
				String locname=request.getParameter("locationname");
				LocationVO custSchoolInfo=new LocationVO();
				custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,location);
				System.out.println("location"+location);
				System.out.println("accyear"+location);
				System.out.println("classId"+location);
				System.out.println("section"+location);
			
				if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
					accyear = HelperClass.getCurrentYearID(userLoggingsVo);
				}
				if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
					classId = "%%";
				}
				
				if(section == null || section == "" || section.equalsIgnoreCase("")) {
					section = "%%";
				}
				if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
					term = "%%";
				}
				if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
					termstatusId = "%%";
				}
				
				
			ReportMenuVo obj = new ReportMenuVo();
				
				obj.setAccyearId(accyear);
				obj.setSectionId(section);
				obj.setClassId(classId);
				obj.setLocationId(location);
				obj.setTermId(term);
				obj.setTermStatusId(termstatusId);
		
					
				
				ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj,userLoggingsVo);
				String sourceFileName = request
						.getRealPath("Reports/TransportFeeReportPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

				String PropfilePath=null;
				if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
					PropfilePath = request.getRealPath("/")
							+ "images/" + ImageName.trim();
				}
				else{
					PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
				}

				
				 String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);
				parameters.put("class_div", classname);
				parameters.put("accYear",accYear);
				parameters.put("total_stu_val",Integer.toString(list.size()));
				parameters.put("locationname", custSchoolInfo.getSchname());
			    parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
	            parameters.put("custSchoollogo", schoollogo);
	            parameters.put("custSchoolboardlogo", PropfilePath);
	            parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
	            parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
	            parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
	            parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
	            parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
				

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "TransportFeePDF" + ".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(bytes, 0, bytes.length);

				outstream.flush();

			}catch (Exception e){
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TransportFeeReceiptAction : getTransportFeePDFReport  Ending");
			return null;

		}
	
	public ActionForward gettransportfeeDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : gettransportfeeDetails Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			
		
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null || section == "" || section.equalsIgnoreCase("")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
			
			System.out.println("termstatusId"+termstatusId);
			
			
			ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
				
			
			ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj,userLoggingsVo);
			JSONObject object = new JSONObject();
			object.put("studentList",list);
			response.getWriter().print(object);
			
			request.getSession(false).setAttribute("studentList",list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : gettransportfeeDetails Ending");
		return null;
	}
	
	public ActionForward getTerm(ActionMapping map,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTerm Starting");
		try{
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String accyear=request.getParameter("accyear");
			String location=request.getParameter("location");
			ReportMenuVo terms = new ReportMenuVo();
			
			ArrayList<ReportMenuVo> termlist = new ReportsMenuBD().getTerm(accyear,location,custdetails);
			
			JSONObject object = new JSONObject();
			object.accumulate("termlist",termlist);
			response.getWriter().print(object);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTerm Ending");
		return null;
	}
	
	public ActionForm getTermFeeStatus(ActionMapping map,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTermFeeStatus Starting");
		
		try
		{
			ArrayList<ReportMenuVo> termstatus = new ArrayList<ReportMenuVo>();
			
			JSONObject object = new JSONObject();
			object.accumulate("termstatus",termstatus);
			response.getWriter().print(object);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportFeeReceiptAction : getTermFeeStatus Ending");
		
		return null;
	}
	
	public ActionForward printTransportFeeList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : getStudentBusCard Starting");
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
		
			
			String location = request.getParameter("location");
			String accyear = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String section = request.getParameter("section");
			String term = request.getParameter("term");
			String termstatusId = request.getParameter("termstatusId");
			String classname=request.getParameter("classname");
			String accYear=request.getParameter("accYear");
			
			System.out.println("location"+location);
			System.out.println("accyear"+location);
			System.out.println("classId"+location);
			System.out.println("section"+location);
		
			if(accyear == null || accyear.equalsIgnoreCase("") || accyear.equalsIgnoreCase("all")) {
				accyear = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId = "%%";
			}
			
			if(section == null || section == "" || section.equalsIgnoreCase("")) {
				section = "%%";
			}
			if(term == null || term == "" || term.equalsIgnoreCase("")||term.equalsIgnoreCase("all")) {
				term = "%%";
			}
			if(termstatusId == null || termstatusId == "" || termstatusId.equalsIgnoreCase("")||termstatusId.equalsIgnoreCase("all")) {
				termstatusId = "%%";
			}
			
			
		ReportMenuVo obj = new ReportMenuVo();
			
			obj.setAccyearId(accyear);
			obj.setSectionId(section);
			obj.setClassId(classId);
			obj.setLocationId(location);
			obj.setTermId(term);
			obj.setTermStatusId(termstatusId);
				
			
			ArrayList<ReportMenuVo> list = new ReportsMenuBD().gettransportfeeDetails(obj,userLoggingsVo);
			String sourceFileName = null;
			String termName = null;
			
			sourceFileName = request
					.getRealPath("Reports/TransportFeeReportPDF.jrxml");
			
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

			String PropfilePath = request.getRealPath("")+ "\\images\\" + ImageName.trim();

			

			Map parameters = new HashMap();
			parameters.put("Image", PropfilePath);
			parameters.put("class_div", classname);
			parameters.put("accYear",accYear);
			parameters.put("total_stu_val",Integer.toString(list.size()));
			
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
		return mapping.findForward(MessageConstants.TRANSPORT_FEE_REPORT);
	}
	
	
	
	
	

	
	

		
}
