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
import javax.servlet.http.HttpSession;
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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
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
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.ReportsMenuDaoImpl;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ExamDetailsBD;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StudentAttendanceBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.StudentTransferCertifivateReportBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.CustomizableStudentReportExcell;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.ITFeeVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;
import com.centris.campus.vo.UserDetailVO;


public class ReportsMenuAction extends DispatchAction {

	static ResourceBundle res = ResourceBundle.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	private static String SchoolName = res.getString("SchoolName");
	private static final Logger logger = Logger.getLogger(ReportsMenuAction.class);
	private static String ReportCard_Dir = res.getString("ReportCard_Dir");
	private static String BoardLogo = res.getString("boardlogo");

	public ActionForward studentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(custdetails);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(custdetails);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("StreamList", streamList);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_REPORT);
	}

	public ActionForward getClassesByStream(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStream Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String streamId = request.getParameter("streamId");
			/*	String locationname = request.getParameter("locationname");*/

			System.out.println("streamId"+streamId);
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD().getClassesByStream(streamId,custdetails);

			JSONObject object = new JSONObject();
			object.put("ClassesList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStream Ending");

		return null;
	}

	public ActionForward getSectionByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClass Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String location=request.getParameter("location");

			if(classId == null || classId.equalsIgnoreCase("") || classId.equalsIgnoreCase("all")) {
				classId ="%%";
			}

			/*if(classId=="all"){
				classId="%%";
			}*/

			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD().getSectionsByClass(classId,location,custdetails);

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
				+ " Control in ReportsMenuAction : getSectionByClass Ending");

		return null;
	}

	public ActionForward getStudentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Starting");
		try {
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(dbdetails);
			request.setAttribute("StreamList", streamList);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD().getStudentDetailsReport(reform,dbdetails);
			request.setAttribute("StudentInfoList", studentInfoList);


			ReportMenuVo selectedItems = new ReportsMenuBD().getSelectedItems(reform,dbdetails);
			request.setAttribute("CurrentForm", selectedItems);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_INFORMATION_REPORT);
	}

	public ActionForward studentDetailsPDFReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentDetailsPDFReport Starting");

		String accYear = request.getParameter("AccId");
		String stream = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String PropfilePath =request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("accYear", accYear);
			mapdata.put("stream", stream);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			String filepath = request.getRealPath("Reports/StudentDetailsPDFReport.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, JDBCConnection.getConnection(userLoggingsVo));

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentDetails" + ".pdf\"");

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
				+ " Control in ReportsAction : studentDetailsPDFReport Ending");

		return null;

	}

	public ActionForward studentDetailsExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsAction : studentDetailsExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String streamId = request.getParameter("Stream");
		String classId = request.getParameter("Class");
		String section = request.getParameter("Section");

		String filePath = null;

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("stream", streamId);
			mapdata.put("classId", classId);
			mapdata.put("section", section);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/StudentDetailsExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,mapdata,JDBCConnection.getConnection(userLoggingsVo));
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
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

			pdfxls = new File(request.getRealPath("Reports/StudentDetails.xlsx"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentDetails.xlsx");
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
				+ " Control in ReportsAction : studentDetailsExcelReport Ending");

		return null;
	}

	public ActionForward studentFeeStatusReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentFeeStatusReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_CURRENTSTUDENTFEEPAYMENTS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%",custdetails);

			TermMasterVo vo = new TermMasterVo();

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo,custdetails);

			request.setAttribute("TermList", termlist);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentFeeStatusReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_FEE_STATUS_REPORT);
	}

	public ActionForward defaultFeeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : defaultFeeReport Starting");
		try{

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEE_DEFAULTER_LIST);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accyear = new ReportsMenuBD().getAccYears(custdetails);
			request.setAttribute("AccYearList", accyear);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : defaultFeeReport Ending");

		return mapping.findForward(MessageConstants.defenderFeeReport);

	}

	public ActionForward getStdFeeStatusReportDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Starting");
		try {
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

			reportForm.setStatus("ALL");

			request.setAttribute("reportForm", reportForm);

			HashMap<String, ArrayList<FeeReportDetailsVo>> feeStatusReport = new ReportsMenuBD()
					.getStdFeeStatusReportDetails(reportForm,dbdetails);

			request.setAttribute("feeStatusReport", feeStatusReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(dbdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%",dbdetails);

			TermMasterVo vo = new TermMasterVo();

			ArrayList<TermMasterVo> termlist = new TermMasterDelegate()
					.termList(vo,dbdetails);

			request.setAttribute("TermList", termlist);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdFeeStatusReportDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_FEE_STATUS_REPORT);
	}

	public ActionForward feeStatusExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : feeStatusExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String TermId = request.getParameter("TermId");
		String TermName = request.getParameter("TermName");
		String Status = request.getParameter("Status");
		String student=request.getParameter("studentname");
		if(student==null){
			student="%%";
		}

		String filePath = null;

		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			FeeStatusReportPojo feeStatusPojo = new FeeStatusReportPojo();

			feeStatusPojo.setAccyear(accYear);
			feeStatusPojo.setClassname(Classid);
			feeStatusPojo.setSection(SectionId);
			feeStatusPojo.setTerm(TermId);
			feeStatusPojo.setStatus(Status);
			feeStatusPojo.setStudent(student);


			ArrayList<FeeReportDetailsVo> feeStatusList = new ReportsMenuBD().getStdFeeStatusReportDownload(feeStatusPojo,custdetails);

			String sourceFileName = request.getRealPath("Reports/StudentFeeStatusReportExcel.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("termName", TermName);
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusList);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/TransportDetailsReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { TermName + " Class FeeSummary  Report" };
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
					request.getRealPath("Reports/TransportDetailsReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Class Fee Summary Report.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : feeStatusExcelReport Ending");

		return null;
	}

	public ActionForward feeStatusPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : feeStatusPdfReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String TermId = request.getParameter("TermId");
		String TermName = request.getParameter("TermName");
		String Status = request.getParameter("Status");
		String student=request.getParameter("studentname");
		if(student==null){
			student="%%";
		}

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("termName", TermName);


			String SchoolName = res.getString("SchoolName");
			String AddressLine1 = res.getString("AddressLine1");


			mapdata.put("SchoolName", SchoolName);
			mapdata.put("AddressLine1", AddressLine1);
			FeeStatusReportPojo feeStatusPojo = new FeeStatusReportPojo();

			feeStatusPojo.setAccyear(accYear);
			feeStatusPojo.setClassname(Classid);
			feeStatusPojo.setSection(SectionId);
			feeStatusPojo.setTerm(TermId);
			feeStatusPojo.setStatus(Status);
			feeStatusPojo.setStudent(student);

			ArrayList<FeeReportDetailsVo> feeStatusList = new ReportsMenuBD()
					.getStdFeeStatusReportDownload(feeStatusPojo,custdetails);

			String sourceFileName = request
					.getRealPath("Reports/StudentFeeStatusReportPdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusList);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeCollectionDetailsPDF - " + ".pdf\"");

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
				+ " Control in ReportsMenuAction : feeStatusPdfReport Ending");

		return null;

	}

	public ActionForward marksDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : marksDetailsReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_MARKSDETAILS);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(custdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%",custdetails);

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD().getexamdeligate(custdetails);

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);
			request.setAttribute("locationList", locationList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : marksDetailsReport Ending");

		return mapping.findForward(MessageConstants.STUDENT_MARKS_DETAILS);
	}

	public ActionForward getStudentBySection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Starting");
		try {

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			/*List<ParentVO> studentList = new StudentAttendanceBD()
					.getAllStudent(classId, sectionId);
			String locationid=request.getParameter("");
			List<ParentVO> studentList = new StudentAttendanceBD().getAllStudent(classId, sectionId,locationid);
			JSONObject object = new JSONObject();
			object.put("studentList", studentList);
			response.getWriter().print(object);*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentBySection Ending");

		return null;
	}

	public ActionForward getStdMarksDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdMarksDetails Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

			request.setAttribute("reportForm", reportForm);

			HashMap<String, ArrayList<MarksUploadVO>> marksDetailsReport = new ReportsMenuBD()
					.getStdMarksDetails(reportForm,custdetails);

			request.setAttribute("marksDetailsReport", marksDetailsReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(custdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%",custdetails);

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD()
					.getexamdeligate(custdetails);

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStdMarksDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_MARKS_DETAILS);
	}

	public ActionForward studentMarksExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentMarksExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		String filePath = null;

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);


			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo,custdetails);

			String sourceFileName = request
					.getRealPath("Reports/StudentMarksExcel.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					marksList);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentMarksReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { "Student Marks Report" };
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
					request.getRealPath("Reports/StudentMarksReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Student Marks Report.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentMarksExcelReport Ending");

		return null;
	}

	public ActionForward studentMarksPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentMarksPdfReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String PropfilePath = request.getRealPath("/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);

			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo,custdetails);
			String sourceFileName = request
					.getRealPath("Reports/StudentMarksPdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					marksList);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentMarksDetails - " + ".pdf\"");

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
				+ " Control in ReportsMenuAction : studentMarksPdfReport Ending");

		return null;

	}

	public ActionForward studentMarksGraph(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentMarksGraph Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String SectionId = request.getParameter("SectionId");
		String Exam = request.getParameter("Exam");
		String Student = request.getParameter("Student");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			MarksPOJO markspojo = new MarksPOJO();

			markspojo.setAccyear(accYear);
			markspojo.setClassid(Classid);
			markspojo.setSection(SectionId);
			markspojo.setStudentid(Student);
			markspojo.setExamid(Exam);


			ArrayList<MarksUploadVO> marksList = new ReportsMenuBD()
					.getStdMarksDetailsDownload(markspojo,custdetails);

			JSONObject object = new JSONObject();
			object.put("MARKSDETAILS", marksList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentMarksGraph Ending");

		return null;

	}

	public ActionForward examReportClassWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWise Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_EXAMREPORTCLASSWISE);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%",custdetails);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWise Ending");
		return mapping.findForward(MessageConstants.EXAM_CLASSWISE_REPORT);
	}

	public ActionForward examReportClassWiseDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWiseDetails Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reportForm = (ReportMenuForm) form;

			request.setAttribute("reportForm", reportForm);

			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(reportForm,custdetails);

			request.setAttribute("examDetailsReport", examDetailsReport);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(custdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD()
					.getClassesByStream("%%",custdetails);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : examReportClassWiseDetails Ending");

		return mapping.findForward(MessageConstants.EXAM_CLASSWISE_REPORT);
	}

	public ActionForward classWiseExamExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : classWiseExamExcelReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String ClassName = request.getParameter("ClassName");

		String filePath = null;

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			ReportMenuForm rform = new ReportMenuForm();
			rform.setAccyear(accYear);
			rform.setClassname(Classid);


			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(rform,custdetails);

			String sourceFileName = request
					.getRealPath("Reports/ClassWiseExamExcelReport.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ClassName", ClassName);
			stream = response.getOutputStream();

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examDetailsReport);

			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ClassWiseExamExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			String[] sheetNames = { "Class Wise Exam Report" };
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
					request.getRealPath("Reports/ClassWiseExamExcelReport.xls"));

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Student Marks Report.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : classWiseExamExcelReport Ending");

		return null;
	}

	public ActionForward classWiseExamPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : classWiseExamPdfReport Starting");

		String accYear = request.getParameter("AccId");
		String Classid = request.getParameter("ClassId");
		String ClassName = request.getParameter("ClassName");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("ClassName", ClassName);

			ReportMenuForm rform = new ReportMenuForm();
			rform.setAccyear(accYear);
			rform.setClassname(Classid);


			ArrayList<ExaminationDetailsVo> examDetailsReport = new ReportsMenuBD()
					.examReportClassWiseDetails(rform,custdetails);
			String sourceFileName = request
					.getRealPath("Reports/ClassWiseExamPdfReport.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					examDetailsReport);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					mapdata, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ClassExamDetails - " + ".pdf\"");

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
				+ " Control in ReportsMenuAction : classWiseExamPdfReport Ending");

		return null;

	}

	public ActionForward InactivatedstudentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : InactivatedstudentList Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_INACTIVATESTUDENTS);

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getClassesByStream("%%",custdetails);

			List<ExaminationDetailsVo> examlist = new ExamDetailsBD().getexamdeligate(custdetails);

			request.setAttribute("examlist", examlist);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("classList", classList);
			request.setAttribute("locationList", locationList);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : InactivatedstudentList Ending");

		return mapping.findForward(MessageConstants.INACTIVE_STUDENT_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentDetailsReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			/*ReportMenuForm reform = (ReportMenuForm) form;*/
			String location=request.getParameter("Location");
			String accId=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String secId=request.getParameter("section");

			ReportMenuVo vo=new ReportMenuVo();

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(custdetails);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("StreamList", streamList);
			vo.setLocationId(location);
			vo.setAccyearId(accId);
			vo.setClassId(classId);
			vo.setSectionId(secId);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD().geInactivetStudentDetailsReport(vo,custdetails);

			/*ReportMenuVo selectedItems = new ReportsMenuBD()
					.getSelectedItems(reform);*/

			/*	request.setAttribute("StudentInfoList", studentInfoList);*/
			/*request.setAttribute("CurrentForm", selectedItems);*/

			JSONObject object = new JSONObject();
			object.put("StudentInfoList", studentInfoList);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentDetailsReport Ending");

		return null;
	}

	public ActionForward geInactivetStudentDetailExcelsReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentDetailExcelsReport Starting");

		String accYear = request.getParameter("accyear");
		String classId = request.getParameter("classId");
		String section = request.getParameter("section");
		String locname=request.getParameter("locationname");
		String locId=request.getParameter("locId");
		String filePath = null;

		try {
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("classId", classId);
			mapdata.put("section", section);
			mapdata.put("schname", locname);
			mapdata.put("locId", locId);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InActiveStudentDetailsExcelReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,mapdata, JDBCConnection.getConnection(cpojo));
			JRXlsxExporter exporter = new JRXlsxExporter();
			File outputFile = new File(request.getRealPath("Reports/InActivateStudentDetails.xlsx"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/InActivateStudentDetails.xlsx"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename=In-ActiveStudentDetails.xlsx");
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
				+ " Control in ReportsMenuAction : geInactivetStudentDetailExcelsReport Ending");

		return null;
	}


	public ActionForward geInactivetStudentDetailPDFReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction :geInactivetStudentDetailPDFReport Starting");

		ServletContext servletContext = request.getServletContext();
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
		String locId=request.getParameter("location");
		LocationVO custSchoolInfo=new LocationVO();
		custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
		String accYear = request.getParameter("accyear");
		String stream = request.getParameter("Stream");
		String classId = request.getParameter("classId");
		String section = request.getParameter("section");
		String locationname=request.getParameter("locationname");

		try {

			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/") + "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map mapdata = new HashMap();

			mapdata.put("Image", PropfilePath);
			mapdata.put("accYear", accYear);
			mapdata.put("stream", stream);
			mapdata.put("classId", classId);
			mapdata.put("section", section);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("schnam", locationname);
			mapdata.put("locId", locId);
			String filepath = request
					.getRealPath("Reports/InActiveStudentDetailsPDFReport.jrxml");

			JasperDesign design = JRXmlLoader.load(filepath);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,mapdata, JDBCConnection.getConnection(cpojo));

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""+ "In-Active StudentDetails" + ".pdf\"");

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
				+ " Control in ReportsMenuAction :geInactivetStudentDetailPDFReport Ending");

		return null;

	}


	public ActionForward InActivestudentFeeReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : InActivestudentFeeReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD()
					.getAccYears(custdetails);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD()
					.getStream(custdetails);

			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("StreamList", streamList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : InActivestudentFeeReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_FEE_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentFeeDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailsReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ReportMenuForm reform = (ReportMenuForm) form;

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);


			request.setAttribute("AccYearList", accYearList);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentFeeDetailsReport(reform,custdetails);

			ReportMenuVo selectedItems = new ReportsMenuBD()
					.getSelectedoneItems(reform,custdetails);

			request.setAttribute("StudentInfoList", studentInfoList);
			request.setAttribute("CurrentForm", selectedItems);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailsReport Ending");

		return mapping
				.findForward(MessageConstants.INACTIVE_STUDENT_FEE_INFORMATION_REPORT);
	}

	public ActionForward geInactivetStudentFeeDetailExcelsReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailExcelsReport Starting");



		String filePath = null;

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/InActiveStudentFeeReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			String accYear = request.getParameter("AccId");

			System.out.println("accYear " + accYear);

			ReportMenuForm reform = (ReportMenuForm) form;

			reform.setAccyear(accYear);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentFeeDetailsReport(reform,custdetails);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentInfoList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/InActiveStudentFeeReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "InActive Student Fee Report" };
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
					request.getRealPath("Reports/InActiveStudentFeeReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=InActiveStudentFeeReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			} 

		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}





		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailExcelsReport Ending");

		return null;
	}

	public ActionForward geInactivetStudentFeeDetailPDFReport(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailPDFReport  Starting");


		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String accYear = request.getParameter("AccId");

			System.out.println("accYear " + accYear);

			ReportMenuForm reform = (ReportMenuForm) form;

			reform.setAccyear(accYear);


			ArrayList<StudentInfoVO> studentInfoList = new ReportsMenuBD()
					.geInactivetStudentFeeDetailsReport(reform,custdetails);

			String sourceFileName = request
					.getRealPath("Reports/InActiveStudentFeePDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentInfoList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "/images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Image", PropfilePath);




			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "Inactivated Student Fee Details - " + ".pdf\"");

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
				+ " Control in ReportsMenuAction : geInactivetStudentFeeDetailPDFReport  Ending");
		return null;

	}


	public ActionForward getSingleStdFeeStatusReportDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSingleStdFeeStatusReportDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String stdId=request.getParameter("idString");
			System.out.println("stdIdAction"+stdId);
			String section=request.getParameter("section");
			String term=request.getParameter("term");
			String classname=request.getParameter("classname");
			String accyear=request.getParameter("accyear");

			ArrayList<FeeReportDetailsVo> feeStatusReport = new ReportsMenuBD().getSingleStdFeeStatusReportDetails(stdId,custdetails);

			System.out.println(feeStatusReport);
			request.setAttribute("feeStatusReport", feeStatusReport);
			String studentName=feeStatusReport.get(0).getStudentName();
			String totalAmount=feeStatusReport.get(0).getTotalAmount();
			double paidAmount=feeStatusReport.get(0).getPaidAmount();
			double balanceAmount=feeStatusReport.get(0).getBalanceAmount();

			ArrayList<FeeReportDetailsVo> report=new ArrayList<FeeReportDetailsVo>();

			for(int i=1;i<feeStatusReport.size();i++){
				FeeReportDetailsVo feeStatusReportDownload=new FeeReportDetailsVo();
				feeStatusReportDownload.setOpeningfeeAmount(feeStatusReport.get(i).getOpeningfeeAmount());
				System.out.println("Fee Amount"+feeStatusReport.get(i).getOpeningfeeAmount());
				feeStatusReportDownload.setFeeName(feeStatusReport.get(i).getFeeName());
				feeStatusReportDownload.setOpeningfeeAmount(feeStatusReport.get(i).getOpeningfeeAmount());
				feeStatusReportDownload.setFeeAmountCollected(feeStatusReport.get(i).getFeeAmountCollected());
				feeStatusReportDownload.setBlancefeeAmount(feeStatusReport.get(i).getBlancefeeAmount());
				feeStatusReportDownload.setPaidDate(feeStatusReport.get(i).getPaidDate());
				report.add(feeStatusReportDownload);
			}


			request.getSession(false).setAttribute("feeStatusReport1", report);
			request.getSession(false).setAttribute("sectionName", section);	
			request.getSession(false).setAttribute("term", term);
			request.getSession(false).setAttribute("classname", classname);
			request.getSession(false).setAttribute("accyear", accyear);
			request.getSession(false).setAttribute("studentName", studentName);	
			request.getSession(false).setAttribute("totalAmount", totalAmount);
			request.getSession(false).setAttribute("paidAmount", paidAmount);
			request.getSession(false).setAttribute("balanceAmount", balanceAmount);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSingleStdFeeStatusReportDetails Ending");

		return mapping.findForward(MessageConstants.STUDENT_SINGLE_FEE_STATUS_REPORT);
	}

	public ActionForward downloadPDFfeeStatusReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadPDFfeeStatusReport  Starting");
		try{

			ArrayList<FeeReportDetailsVo> feeStatusReport = ( ArrayList<FeeReportDetailsVo>)request.getSession(false).getAttribute("feeStatusReport1");

			String sourceFileName = request
					.getRealPath("Reports/FeeStatusPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusReport);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("section",request.getSession(false).getAttribute("sectionName"));
			parameters.put("term",request.getSession(false).getAttribute("term"));
			parameters.put("class",request.getSession(false).getAttribute("classname"));
			parameters.put("accyear",request.getSession(false).getAttribute("accyear"));
			parameters.put("studentName",request.getSession(false).getAttribute("studentName"));
			parameters.put("totalAmount",request.getSession(false).getAttribute("totalAmount"));
			parameters.put("paidAmount",request.getSession(false).getAttribute("paidAmount"));
			parameters.put("balanceAmount",request.getSession(false).getAttribute("balanceAmount"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentFeeStatusPDFReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadPDFfeeStatusReport   Ending");

		return null;
	}

	public ActionForward downloadXLfeeStatusReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadXLfeeStatusReport  Starting");

		try{

			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/FeeStatusXLReport (2).jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			ArrayList<FeeReportDetailsVo> feeStatusReport = ( ArrayList<FeeReportDetailsVo>)request.getSession(false).getAttribute("feeStatusReport1");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeStatusReport);
			Map parameters = new HashMap();

			parameters.put("section",request.getSession(false).getAttribute("sectionName"));
			parameters.put("term",request.getSession(false).getAttribute("term"));
			parameters.put("class",request.getSession(false).getAttribute("classname"));
			parameters.put("accyear",request.getSession(false).getAttribute("accyear"));
			parameters.put("studentName",request.getSession(false).getAttribute("studentName"));
			parameters.put("totalAmount",request.getSession(false).getAttribute("totalAmount"));
			parameters.put("paidAmount",request.getSession(false).getAttribute("paidAmount"));
			parameters.put("balanceAmount",request.getSession(false).getAttribute("balanceAmount"));



			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeeStatusXLReport (2).xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "FeeStatus Detail Excel Report" };
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
					request.getRealPath("Reports/FeeStatusXLReport (2).xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeeStatusXLReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadXLfeeStatusReport   Ending");

		return null;
	}


	public ActionForward CustomizableStudentReportsExcell(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String formValueArray=request.getParameter("formValueArray");
			String labelValueArray=request.getParameter("labelValueArray");
			String[] fielldArray=formValueArray.split(",");
			String[] labelArray=labelValueArray.split(",");
			String location=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String className=request.getParameter("class");
			String section=request.getParameter("section");


			ArrayList<HashMap<String, String>> fullstudentList=new ReportsMenuDaoImpl().getCustomizableStudentReportsExcell(formValueArray,location,accyear,className,section,custdetails);


			request.getSession(false).setAttribute("fullstudentList", fullstudentList);
			request.getSession(false).setAttribute("fielldArray", fielldArray);
			request.getSession(false).setAttribute("labelArray", labelArray);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcell Ending");

		return null;
	}
	public ActionForward CustomizableStudentReportsExcellDownload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcellDownload Starting");
		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String filePath = request.getRealPath("/")
					+ "FIles/CUSTOMIZABLEREPORT/CustomizableStudentRepots.xls";

			String[] fielldArray=(String[]) request.getSession(false).getAttribute("fielldArray");
			ArrayList<HashMap<String, String>> fullstudentList=(ArrayList<HashMap<String, String>>) request.getSession(false).getAttribute("fullstudentList");
			String[] labelArray=(String[]) request.getSession(false).getAttribute("labelArray");

			CustomizableStudentReportExcell excelFile= new CustomizableStudentReportExcell(); 
			excelFile.download(fullstudentList,fielldArray,filePath,labelArray,custdetails);

			pdfxls = new File(
					request.getRealPath("FIles/CUSTOMIZABLEREPORT/CustomizableStudentRepots.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/CustomizableStudentRepots.xls");
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
				+ " Control in ReportsMenuAction : CustomizableStudentReportsExcellDownload Ending");

		return null;
	}
	public ActionForward CustomizableStudentReports(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReports Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, LeftMenusHighlightMessageConstant.MODULE_REPORTS_CUSTOMIZABLE_STUDENT);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			request.setAttribute("current_sch", schoolLocation);
			String currentlocation =null;
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
				request.setAttribute("currentlocation", "All");
			}


			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(custdetails);


			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation,custdetails);


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			request.setAttribute("AccYearList", accYearList);

			/*System.out.println("streamList ::: " + streamList.size());*/

			request.setAttribute("StreamList", streamList);

			request.setAttribute("classList", classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : CustomizableStudentReports Ending");

		return mapping.findForward(MessageConstants.CUSTOMIZABLE_STUDENT_REPORT);
	}	

	public ActionForward StudentReports(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentReports Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_STUDENT);

			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			request.setAttribute("current_loc", schoolLocation);
			String currentlocation =null;
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
				request.setAttribute("currentlocation", "All");
			}
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			ArrayList<ReportMenuVo> streamList = new ReportsMenuBD().getStream(custdetails);
			ArrayList<ReportMenuVo> classList = new ReportsMenuBD().getStudentClass(schoolLocation,custdetails);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
			/*System.out.println("streamList ::: " + streamList.size());*/
			request.setAttribute("StreamList", streamList);

			request.setAttribute("classList", classList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : StudentReports Ending");

		return mapping.findForward(MessageConstants.STUDENT_REPORT);
	}	

	public ActionForward getstudentDOBWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWise Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,custdetails);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentDOBWise(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentdobList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentdobList",arr);


			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWise Ending");

		return null;

	}

	public ActionForward getstudentDOBWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWiseXL  Starting");

		try{
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentWithDOBXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			String locId=request.getParameter("locId");

			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentdobList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentWithDOBXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentDOB Wise Excel Report" };
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
					request.getRealPath("Reports/StudentWithDOBXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentDOBExcelReport.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDOBWiseXL   Ending");

		return null;
	}

	public ActionForward studentDOBWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDOBWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentdobList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");



			String sourceFileName = request.getRealPath("Reports/StudentWithDOB.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath= request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentDOBWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDOBWisePDFReport   Ending");
		return null;
	}

	public ActionForward getstudentFatherOccuWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWise Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,custdetails);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentFatherOccuWise(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentfatheroccuList",arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentfatheroccuList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWise Ending");

		return null;

	}

	// pdf report

	public ActionForward getstudentFatherOccuWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentfatheroccuList");
			ServletContext servletContext = request.getServletContext();

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String sourceFileName = request.getRealPath("Reports/StudentFatherOccupation1.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);

			String PropfilePath=null;

			if(custSchoolInfo.getBoardlogo().trim().equals("-")){


				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}

			else{

				PropfilePath = servletContext.getRealPath("/") +custSchoolInfo.getBoardlogo().trim();

			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();



			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentFatherOccuWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWisePDFReport   Ending");
		return null;
	}

	public ActionForward getstudentFatherOccuWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWiseXL  Starting");

		try{
			String locId=request.getParameter("locId");


			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			//System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentFatherOccupation1XL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentfatheroccuList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);

			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentFatherOccupation1XL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentFatherOccu Wise Excel Report" };
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
					request.getRealPath("Reports/StudentFatherOccupation1XL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentFatherOccupation1XL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentFatherOccuWiseXL   Ending");

		return null;
	}
	// for mother occupation
	public ActionForward getstudentMotherOccuWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWise Starting");

		try {

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location"); 
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,custdetails);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentMotherOccuWise(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentmotheroccuList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentmotheroccuList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWise Ending");

		return null;

	}

	public ActionForward getstudentMotherOccuWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWiseXL  Starting");

		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String sourceFileName = request
					.getRealPath("Reports/StudentsMotherOccupationXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentmotheroccuList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);

			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsMotherOccupationXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentMotherOccu Wise Excel Report" };
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
					request.getRealPath("Reports/StudentsMotherOccupationXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentMotherOccupation1XL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWiseXL   Ending");

		return null;
	}

	public ActionForward getstudentMotherOccuWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentmotheroccuList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);



			String sourceFileName = request.getRealPath("Reports/StudentsMotherOccupation.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim(); 
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentMotherOccuWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentMotherOccuWisePDFReport   Ending");
		return null;
	}

	public ActionForward getstudentDetailsReligionWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDetailsReligionWise Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,custdetails);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentDetailsReligionWise(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentReligionWiseList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentReligionWiseList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());


		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDetailsReligionWise Ending");

		return null;

	}

	public ActionForward studentReligionWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWiseXL  Starting");

		try{
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentReligionWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*	String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();*/
			String PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentReligionWiseList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres",custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentReligionWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentDOB Wise Excel Report" };
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
					request.getRealPath("Reports/StudentReligionWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentReligionWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWiseXL   Ending");

		return null;
	}

	public ActionForward studentReligionWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentReligionWiseList");

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);

			String sourceFileName = request
					.getRealPath("Reports/StudentReligionWisePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}


			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentsReligionWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentReligionWisePDFReport   Ending");
		return null;
	}

	public ActionForward getStudentByTransport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentByTransport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			List<ParentVO> studentList = new StudentAttendanceBD()
					.getStudentByTransport(classId, sectionId,custdetails);

			JSONObject object = new JSONObject();
			object.put("studentList", studentList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentByTransport Ending");

		return null;
	}

	public ActionForward getTermDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Starting");
		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String location = request.getParameter("loc");
			String academic_year = request.getParameter("accyear");
			List<TermMasterVo> termList = new TermMasterDelegate().getTermDetails(academic_year,location,custdetails);

			JSONObject object = new JSONObject();
			object.put("termList",termList);
			response.getWriter().print(object);

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTermDetails Ending");

		return null;
	}

	public ActionForward getClassDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassDetails Starting");

		try{
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<ReportMenuVo> list = new ReportsMenuBD().getClassDetails(custdetails);

			JSONObject object = new JSONObject();
			object.put("ClassList",list);
			response.getWriter().print(object);

		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassDetails Ending");
		return null;
	}

	public ActionForward getstudentCategoryWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWise Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location"); 
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentCategoryWise(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentCategoryWiseList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentCategoryWiseList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWise Ending");

		return null;

	}

	public ActionForward getstudentCategoryWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWiseXL  Starting");

		try{

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/StudentCategoryWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentCategoryWiseList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentCategoryWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentCategory Wise Excel Report" };
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
					request.getRealPath("Reports/StudentCategoryWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentCategoryWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryWiseXL   Ending");

		return null;
	}

	public ActionForward getstudentCategoryPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentCategoryWiseList");

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);

			String sourceFileName = request.getRealPath("Reports/StudentCategoryWisePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){

				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentCategoryWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentCategoryPDFReport   Ending");
		return null;
	}

	public ActionForward getstudentParentWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWise Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location"); 
			String status = request.getParameter("status");
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);

			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentParentWise(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentParentList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentParentList",arr);


			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());


		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWise Ending");

		return null;

	}

	public ActionForward getstudentParentWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWiseXL  Starting");

		try{
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentParentWiseListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentParentList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentParentWiseListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentParent Wise Excel Report" };
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
					request.getRealPath("Reports/StudentParentWiseListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentParentWiseListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWiseXL   Ending");

		return null;
	}

	public ActionForward getstudentParentWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentParentList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String sourceFileName = request
					.getRealPath("Reports/StudentParentWiseListPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath =null;

			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentParentWiseListPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentParentWisePDFReport   Ending");
		return null;
	}

	public ActionForward getstudentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentList Starting");

		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("getstudentDetailsList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("getstudentDetailsList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentList Ending");

		return null;

	}

	public ActionForward getstudentListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListXL  Starting");

		try{
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");


			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("getstudentDetailsList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);

			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			/*  parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());*/
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentList Excel Report" };
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

			pdfxls = new File(request.getRealPath("Reports/StudentListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename=StudentListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction :getstudentListXL Ending");

		return null;
	}

	public ActionForward getstudentListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListPDFReport  Starting");
		try{

			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("getstudentDetailsList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");

			LocationVO custSchoolInfo=new LocationVO();	
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String sourceFileName = request.getRealPath("Reports/StudentList.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentListReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListPDFReport   Ending");
		return null;
	}

	public ActionForward getstudentStandardWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWise Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);

			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentStandardWise(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentStandardList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentStandardList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWise Ending");

		return null;

	}

	public ActionForward getstudentStandardWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWiseXL  Starting");

		try{
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsStandardWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentStandardList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);

			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsStandardWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "StudentStandard Wise Excel Report" };
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
					request.getRealPath("Reports/StudentsStandardWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsStandardWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWiseXL   Ending");

		return null;
	}

	public ActionForward getstudentStandardWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentStandardList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);



			String sourceFileName = request
					.getRealPath("Reports/StudentsStandardWisePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}

			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}


			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentSatandaradWiseReport - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentStandardWisePDFReport   Ending");
		return null;
	}

	//student contact wise

	public ActionForward getstudentContactDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetails Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);

			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentContactDetails(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentContactDetails", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentContactDetails",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetails Ending");

		return null;
	}

	public ActionForward getstudentContactDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsXL  Starting");

		try{
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsContactDetailXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentContactDetails");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsContactDetailXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Contact Details Excel Report" };
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
					request.getRealPath("Reports/StudentsContactDetailXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsContactDetailXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsXL   Ending");

		return null;
	}

	public ActionForward getstudentContactDetailsPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentContactDetails");

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String sourceFileName = request.getRealPath("Reports/StudentsContactDetailPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){

				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}


			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);

			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentsContactDetailPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentContactDetailsPDFReport   Ending");
		return null;
	}

	public ActionForward getstudentDepartmentList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentList Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);
			vo.setCustid(userLoggingsVo.getCustId());
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentDepartmentList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentDepartmentList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentDepartmentList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}
			else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentList Ending");

		return null;
	}

	public ActionForward getstudentDepartmentListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListXL  Starting");

		try{
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String locationname=request.getParameter("locationname");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentDepartmentListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){

				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentDepartmentList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres1",custSchoolInfo.getAddress2());
			parameters.put("branch", locationname);
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentDepartmentListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Specialization List Excel Report" };
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
					request.getRealPath("Reports/StudentDepartmentListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Student_SpecializationListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListXL   Ending");

		return null;
	}

	public ActionForward getstudentDepartmentListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction :getstudentDepartmentListPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentDepartmentList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String locationname=request.getParameter("locationname");


			String sourceFileName = request.getRealPath("Reports/StudentDepartmentListPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;

			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = getServlet().getServletContext().getRealPath("")+ "\\images\\" + ImageName.trim();
			}

			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}


			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			/*	parameters.put("schoollogo", PropfilePath);*/
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", locationname);
			parameters.put("status",request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentDepartmentListPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentDepartmentListPDFReport Ending");
		return null;
	}

	public ActionForward getstudentBusRouteWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWise Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentBusRouteWise(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentBusRouteWise", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentBusRouteWise",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWise Ending");

		return null;
	}

	public ActionForward getstudentBusRouteWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWiseXL  Starting");

		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentBusRouteXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();



			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentBusRouteWise");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentBusRouteXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Bus Route Excel Report" };
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
					request.getRealPath("Reports/StudentBusRouteXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentBusRouteXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWiseXL   Ending");

		return null;
	}

	public ActionForward getstudentBusRouteWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentBusRouteWise");



			String sourceFileName = request
					.getRealPath("Reports/StudentBusRoutePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentBusRoutePDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentBusRouteWisePDFReport   Ending");
		return null;
	}

	public ActionForward getstudentOptionalSubjectDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetails Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");  
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentOptionalSubjectDetails(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentOptionalSubject", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentOptionalSubject",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetails Ending");

		return null;
	}

	public ActionForward getstudentOptionalSubjectDetailsXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsXL  Starting");

		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String sourceFileName = request
					.getRealPath("Reports/StudentOptionalSubjectDetailsXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentOptionalSubject");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentOptionalSubjectDetailsXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Optional Subject Excel Report" };
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
					request.getRealPath("Reports/StudentOptionalSubjectDetailsXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentOptionalSubjectDetailsXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsXL   Ending");

		return null;
	}

	public ActionForward getstudentOptionalSubjectDetailsPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentOptionalSubject");



			String sourceFileName = request
					.getRealPath("Reports/StudentOptionalSubjectDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentOptionalSubjectPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentOptionalSubjectDetailsPDFReport   Ending");
		return null;
	}

	public ActionForward getstudentsWithPhoneNumbers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsWithPhoneNumbers Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");  
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentWithPhoneNumber(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentWithPhoneNumber", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentWithPhoneNumber",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsWithPhoneNumbers Ending");

		return null;
	}

	public ActionForward getstudentWithPhoneNumbersXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersXL  Starting");

		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String sourceFileName = request
					.getRealPath("Reports/StudentsWithPhoneNumbersXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithPhoneNumber");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsWithPhoneNumbersXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student With Phone Number Excel Report" };
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
					request.getRealPath("Reports/StudentsWithPhoneNumbersXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsWithPhoneNumbersXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersXL   Ending");

		return null;
	}

	public ActionForward getstudentWithPhoneNumbersPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentWithPhoneNumber");

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);



			String sourceFileName = request
					.getRealPath("Reports/StudentsWithPhoneNumbersPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			/*
				String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();*/
			String PropfilePath = servletContext.getRealPath("/")+ custSchoolInfo.getBoardlogo().trim();
			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentWithPhoneNUmberPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentWithPhoneNumbersPDFReport   Ending");
		return null;
	}

	public ActionForward getOldStudentsList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getOldStudentsList Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getOldStudentsList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("oldStudentList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("oldStudentList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getOldStudentsList Ending");

		return null;
	}

	public ActionForward getStudentsStrengthXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthXL  Starting");

		try{

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentsStrengthXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentsStrength");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));


			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentsStrengthXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students Strength Excel Report" };
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
					request.getRealPath("Reports/StudentsStrengthXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StudentsStrengthXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthXL   Ending");

		return null;
	}

	public ActionForward getStudentsStrengthPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentsStrength");



			String sourceFileName = request
					.getRealPath("Reports/StudentsStrengthPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",request.getSession(false).getAttribute("LocationName"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentsStrengthPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrengthPDFReport   Ending");
		return null;
	}

	public ActionForward getStudentsStrength(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrength Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getStudentsStrength(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentsStrength", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentsStrength",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsStrength Ending");

		return null;
	}

	public ActionForward getStudentsNewAdmissionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionList Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status=request.getParameter("status");
			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);

			vo.setStatus(status);
			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getStudentsNewAdmissionList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentNewAdmissionList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentNewAdmissionList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionList Ending");

		return null;
	}

	public ActionForward getStudentsNewAdmissionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListXL  Starting");

		try{
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentNewAdmissionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			/*	String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");*/

			String PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewAdmissionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			/*parameters.put("schoollogo", PropfilePath);
			parameters.put("schoolName", schName);
			parameters.put("Address1", schAddLine1);*/
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());

			System.out.println("custSchoolInfo.getAddress()--- "+custSchoolInfo.getAddress());
			
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentNewAdmissionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "New Admission List Report" };
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
					request.getRealPath("Reports/StudentNewAdmissionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentNewAdmissionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListXL   Ending");

		return null;
	}

	public ActionForward getStudentsNewAdmissionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewAdmissionList");

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String sourceFileName = request.getRealPath("Reports/StudentNewAdmissionListPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentList);
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


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status",  request.getParameter("status"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentNewAdmissionList - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsNewAdmissionListPDFReport   Ending");
		return null;
	}

	public ActionForward getStudentPromotionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionList Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);

			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getStudentPromotionList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentPromotionList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentPromotionList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionList Ending");

		return null;
	}

	public ActionForward getStudentPromotionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListXL  Starting");

		try{
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentPromotionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentPromotionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentPromotionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students Promotion List Excel Report" };
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
					request.getRealPath("Reports/StudentPromotionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentPromotionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListXL   Ending");

		return null;
	}

	public ActionForward getStudentPromotionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentPromotionList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String sourceFileName = request
					.getRealPath("Reports/StudentPromotionListPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			/*String PropfilePath = request.getRealPath(
						"/")
						+ "images/" + ImageName.trim();*/
			String PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();



			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch",request.getParameter("locationname"));
			parameters.put("status",request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentPromotionList - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentPromotionListPDFReport   Ending");
		return null;
	}

	public ActionForward getStudentListGenderWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWise Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String gender = request.getParameter("gender");
			String status = request.getParameter("status");

			if(gender.equalsIgnoreCase("all")){
				gender="%%";
			}else
			{
				gender=gender;
			}

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
				vo.setGender(gender);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setGender(gender);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getStudentListGenderWise(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentListGenderWise", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentListGenderWise",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWise Ending");

		return null;

	}

	public ActionForward getStudentListGenderWiseXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWiseXL  Starting");

		try{

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");

			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentListGenderWiseXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentListGenderWise");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName", custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentListGenderWiseXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Students List Gender Wise Excel Report" };
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
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.FALSE);

			exporter.exportReport();

			pdfxls = new File(
					request.getRealPath("Reports/StudentListGenderWiseXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentListGenderWiseXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWiseXL   Ending");

		return null;
	}

	public ActionForward getStudentListGenderWisePDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWisePDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentListGenderWise");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);



			String sourceFileName = request.getRealPath("Reports/StudentListGenderWisePDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			String PropfilePath=null;
			if (custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("branch", request.getParameter("locationname"));
			parameters.put("status", request.getParameter("status"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentListGenderWise - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListGenderWisePDFReport   Ending");
		return null;
	}
	public ActionForward progressReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : progressReport Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

			if(location.equalsIgnoreCase("all")){
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
			else{

				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);


			List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo);
			request.setAttribute("classlist", classlist);


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : progressReport Ending");

		return mapping.findForward(MessageConstants.PROGRESS_REPORT);
	}
	public ActionForward individualStudentProgress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : individualStudentProgress Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId,userLoggingsVo);
			request.setAttribute("studentSearchList",list);

			List<ExaminationDetailsVo> list1=new ArrayList<ExaminationDetailsVo>();
			list1 =	ReportsMenuBD.getSubjectOnClass(classId,studentId,accYear,locationId,ExamCode,userLoggingsVo);
			request.setAttribute("subjectList",list1);
			int total=0;
			int pass=0;
			int scored=0;
			for (int i = 0; i < list1.size(); i++) {
				total=total+Integer.parseInt(list1.get(i).getTot_marks());
			}
			for (int i = 0; i < list1.size(); i++) {
				pass=pass+Integer.parseInt(list1.get(i).getPassmarks());

			}
			for (int i = 0; i < list1.size(); i++) {
				if(list1.get(i).getScoredmarks() !=null){
					scored=scored+Integer.parseInt(list1.get(i).getScoredmarks());
				}else{
					scored=0;
				}
			}
			request.setAttribute("scored",scored);
			String result=null;
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).getScoredmarks() != null) {
					if (Integer.parseInt(list1.get(i).getPassmarks()) > Integer
							.parseInt(list1.get(i).getScoredmarks())) {
						result = "FAIL";
						break;
					} else {
						result = "PASS";
					}
				} else {
					result = "";
				}
			}
			request.setAttribute("result",result);
			request.setAttribute("total",total);
			request.setAttribute("pass",pass);

			ArrayList<ExaminationDetailsVo> list2=new ArrayList<ExaminationDetailsVo>();
			list2=ReportsMenuBD.getExamDependencides(ExamCode,studentId,accYear,locationId,classId,SectionId,scored,userLoggingsVo);
			request.setAttribute("Dependency",list2);

			int Grandtotal=0;
			int OutOf=0;
			for(int i=0; i<list2.size(); i++){
				if(list2.get(i).getMainexammark()!=0){
					Grandtotal=Grandtotal+list2.get(0).getTotalDepScoredMarks()+list2.get(i).getMainexammark();
					OutOf=list2.get(0).getOutOffG()+list2.get(i).getMaintotal();
				}
				else{
					Grandtotal=0;
				}	
			}
			double grademrk1=(Grandtotal/(double)OutOf)*100;
			String GrandGrade=null;
			int grademrk=(int)grademrk1;
			if(Grandtotal!=0){
				GrandGrade=ReportsMenuBD.getGradeBasedOnMarks(grademrk,userLoggingsVo);
			}else{
				GrandGrade=" ";
			}
			request.setAttribute("GrandGrade",GrandGrade);
			request.setAttribute("Grandtotal",Grandtotal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : individualStudentProgress Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_PROGRESS_REPORT);
	}

	public ActionForward getExam(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExam Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String studentId=request.getParameter("studentId");
			String accyear=request.getParameter("accyear");
			String locationId=request.getParameter("locationId");
			String classDetailId=request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");

			ArrayList<ExaminationDetailsVo> list=new ArrayList<ExaminationDetailsVo>();
			list=ReportsMenuBD.getExam(studentId,accyear,locationId,classDetailId,SectionId,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("ExamList", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExam Ending");

		return null;
	}

	public ActionForward classPerformance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : classPerformance Starting");

		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_CLASSPERFORMANCE);

		try {
			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

			if(location.equalsIgnoreCase("all")){
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,dbdetails);
			}
			else{

				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,dbdetails);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(dbdetails);
			}

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(dbdetails);
			request.setAttribute("locationList", locationList);



			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(dbdetails);
			request.setAttribute("AccYearList", accYearList);

			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : classPerformance Ending");

		return mapping.findForward(MessageConstants.CLASS_PERFORMANCE);
	}

	public ActionForward individualStudentClassPerformance(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : individualStudentClassPerformance Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_CLASSPERFORMANCE);
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accyear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");

			List<StudentRegistrationVo> list = new StudentRegistrationDelegate().studentFullList(studentId,accYear,locationId,userLoggingsVo);
			request.setAttribute("studentSearchList",list);

			List<ExaminationDetailsVo> list1=new ArrayList<ExaminationDetailsVo>();
			/*list1 =	ReportsMenuBD.getSubjectOnClass(classId,studentId,accYear,locationId,ExamCode,userLoggingsVo.getCustId());
			request.setAttribute("subjectList",list1);*/

			request.setAttribute("accYear", accYear);
			request.setAttribute("studentId", studentId);
			request.setAttribute("accYear", accYear);
			request.setAttribute("locationId", locationId);
			request.setAttribute("classId", classId);
			request.setAttribute("SectionId", SectionId);
			request.setAttribute("ExamCode", ExamCode);
			request.setAttribute("ClassName", HelperClass.getClassName(classId, locationId, userLoggingsVo));
			request.setAttribute("SectionName", HelperClass.getSectionName(classId, SectionId, locationId, userLoggingsVo));
			request.setAttribute("SchoolName", HelperClass.getSchoolName(locationId, userLoggingsVo));
			request.setAttribute("academicYearName", HelperClass.getAcademicYearFace(accYear, userLoggingsVo));


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : individualStudentClassPerformance Ending");

		return mapping.findForward(MessageConstants.INDIVIDUAL_CLASS_PERFORMANCE);
	}

	public ActionForward getChartDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getChartDetail Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String studentId = request.getParameter("studentId");
			String accYear = request.getParameter("accYear");
			String locationId = request.getParameter("locationId");
			String classId = request.getParameter("classId");
			String SectionId=request.getParameter("SectionId");
			String ExamCode=request.getParameter("ExamCode");

			List<ExaminationDetailsVo> list2=new ArrayList<ExaminationDetailsVo>();
			list2 =	ReportsMenuBD.getIndividualStudentMarksClass(classId,studentId,accYear,locationId,ExamCode,SectionId,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("detail",list2);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getChartDetail Ending");

		return null;
	}
	public ActionForward getTerm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_EXAM);
		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			String accId=request.getParameter("accId");

			ArrayList<ReportMenuVo> list = new ReportsMenuDaoImpl().getTermBaseOnLocation(locId,accId,userLoggingsVo);
			System.out.println("9999999999"+list.size());
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm Ending");

		return null;
	}


	public ActionForward FeeCollectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEECOLLECTION);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");

			System.out.println("current school Location:" +schoolLocation);
			String currentlocation =null;
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
				request.setAttribute("currentlocation", "All");
			}

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);

			String startdate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[0];
			String enddate = HelperClass.getAcademicYearStartAndEndDate(academic_year, custdetails).split(",")[1];


			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);

			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionReport Ending");

		return mapping.findForward(MessageConstants.FeeCollectionReport);
	}

	public ActionForward getFeeCollectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String termId=request.getParameter("termId");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";

			}
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";

			}
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";

			}



			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionReport(locationid,accyear,termId,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionReport Ending");

		return null;
	}


	public ActionForward getfeecollectionclasslist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String classid=request.getParameter("classid");
			String termId=request.getParameter("termId");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";

			}

			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";

			}
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";

			}
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getfeecollectionclasslist(locationid,accyear,classid,termId,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getfeecollectionclasslist Ending");

		return null;
	}

	public ActionForward getFeeCollectionSectionReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionSectionReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String classid=request.getParameter("classid");
			String setionid=request.getParameter("sectionid");
			String termId=request.getParameter("termId");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";

			}

			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";

			}
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";

			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";

			}

			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionSectionReport(locationid,accyear,classid,setionid,termId,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionSectionReport Ending");

		return null;
	}


	public ActionForward getFeeCollectionPaymodeReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String classid=request.getParameter("classid");
			String setionid=request.getParameter("sectionid");
			String paymodeid=request.getParameter("paymodid");
			String paymenttype =request.getParameter("paymenttype");
			String termId=request.getParameter("termId");
			String startdate = request.getParameter("startdate");
			String endate = request.getParameter("endate");


			if(termId.equalsIgnoreCase("all")){
				termId="%%";
			}
			if(locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if(classid.equalsIgnoreCase("all")){
				classid="%%";
			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";
			}
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			if(paymodeid.equalsIgnoreCase("all")){
				paymodeid="%%";
			}
			if(paymenttype.equalsIgnoreCase("all")){
				paymenttype="%%";
			}
			if(startdate.trim().equalsIgnoreCase("")){
				startdate = "0000-00-00";
			}else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(endate.trim().equalsIgnoreCase("")){
				endate = "9999-12-31";
			}else{
				endate = HelperClass.convertUIToDatabase(endate);
			}

			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);

			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFeeCollectionPaymodeReport Ending");

		return null;
	}

	public ActionForward DDReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReport Starting");

		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_DETAILOFDD);
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

			if(location.equalsIgnoreCase("all")){
				location="%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
			else{

				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
			}
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			request.setAttribute("AccYearList", accYearList);

			/*List<ClassPojo> classlist = new StudentTransferCertifivateReportBD().getClassDetails(userLoggingsVo.getCustId());
			request.setAttribute("classlist", classlist);*/


			request.setAttribute("studentList", list);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReport Ending");

		return mapping.findForward(MessageConstants.DD_REPORT);
	}

	public ActionForward TermList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : TermList Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String location = request.getParameter("loc");
			if(location.equalsIgnoreCase("all")){
				location="%%";
			}

			ArrayList<ReportMenuVo> termList = new ReportsMenuBD().getterms(location,userLoggingsVo);

			JSONObject json = new JSONObject();
			json.put("TermList",termList);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : TermList Ending");

		return null;
	}

	public ActionForward DDReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
				LeftMenusHighlightMessageConstant.MODULE_REPORTS_PROGRESSREPORT);
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String Termid = request.getParameter("Termid");
			String academic_year = request.getParameter("Acyearid");
			String locationid = request.getParameter("locationname");

			if(academic_year == null || academic_year.equalsIgnoreCase("") || academic_year.equalsIgnoreCase("all")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}
			if(Termid == null || Termid.equalsIgnoreCase("") || Termid.equalsIgnoreCase("all")) {
				Termid ="%%";
			}

			ArrayList<ReportMenuVo> termList = new ReportsMenuBD().DDReportList(Termid,academic_year,locationid,userLoggingsVo);

			JSONObject json = new JSONObject();
			json.put("SearchList",termList);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DDReportList Ending");
		return null;
	}

	public ActionForward DetailsOfDDPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DetailsOfDDPdfReport Starting");
		ServletContext servletContext = request.getServletContext();
		UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

		String schName=request.getParameter("locationname");
		LocationVO custSchoolInfo=new LocationVO();
		custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,schName);
		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			if(Termid.equalsIgnoreCase("all")) {
				Termid="%%";
			}

			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){

				PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{


				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}


			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();


			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("Image",PropfilePath);
			mapdata.put("accYear",accYear);
			mapdata.put("Termid",Termid);
			mapdata.put("schName",schName);
			mapdata.put("schAddLine1",schAddLine1);
			mapdata.put("locationid",locationid);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());

			/*examReportClassWiseDetails*/

			String sourceFileName=request.getRealPath("Reports/DDReportPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,JDBCConnection.getConnection(userLoggingsVo));

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "DetailsOfDD" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes,0,bytes.length);
			outstream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DetailsOfDDPdfReport Ending");

		return null;
	}

	public ActionForward DetailsOfDDExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : DetailsOfDDExcelReport Starting");


		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		String filePath = null;

		try {

			if(Termid.equalsIgnoreCase("all")){
				Termid="%%";
			}
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String schName=request.getParameter("locationname");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,schName);

			String schAddLine1=res.getString("AddressLine1");			

			Map mapdata = new HashMap();

			mapdata.put("accYear", accYear);
			mapdata.put("Termid", Termid);
			mapdata.put("schName", schName);
			mapdata.put("schAddLine1", schAddLine1);
			mapdata.put("locationid", locationid);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/DDReportxlsx.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, JDBCConnection.getConnection(cpojo));
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/DDDetails.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "DD Details" };
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

			pdfxls = new File(request.getRealPath("Reports/DDDetails.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=DDDetails.xls");
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
				+ " Control in ReportsMenuAction : DetailsOfDDExcelReport Ending");

		return null;
	}


	public ActionForward getonlinelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Starting");
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_REPORTS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_REPORTS);
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String locationid=request.getParameter("location");
			String accyear=request.getParameter("accyear");
			String classid=request.getParameter("classid");
			String setionid=request.getParameter("sectionid");
			String paymodeid=request.getParameter("paymodid");
			String paymenttype =request.getParameter("paymenttype");
			String termId=request.getParameter("termId");
			String startdate = request.getParameter("startdate");
			String endate = request.getParameter("endate");

			if(termId==null || termId.equalsIgnoreCase("all")){
				termId="%%";
			}
			if(locationid==null || locationid.equalsIgnoreCase("all")){
				locationid="%%";
			}
			if(accyear.equalsIgnoreCase("all")){
				accyear="%%";
			}
			if(classid.equalsIgnoreCase("all")){
				classid="%%";
			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";
			}
			if(paymodeid.equalsIgnoreCase("all")){
				paymodeid="%%";
			}
			if(paymenttype.equalsIgnoreCase("all")){
				paymenttype="%%";
			}

			if(startdate.trim().equalsIgnoreCase("")){
				startdate = "0000-00-00";
			}
			else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}

			if(endate.trim().equalsIgnoreCase("")){
				endate = "9999-12-31";
			}
			else{
				endate = HelperClass.convertUIToDatabase(endate);
			}

			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getonlinelist(locationid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
			JSONObject json = new JSONObject();
			json.put("FeeReport", list);
			response.getWriter().print(json);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getonlinelist Ending");

		return null;
	}

	public ActionForward FeeCollectionPdfReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionPdfReport Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ServletContext servletContext = request.getServletContext();

			String locid = request.getParameter("locid");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locid);
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();

			String accyear=request.getParameter("AccId");
			String classid=request.getParameter("ClassId");
			String setionid=request.getParameter("SectionId");
			String paymodeid=request.getParameter("Paymode");
			String paymenttype=request.getParameter("PaymentType");
			String locationid=request.getParameter("locationname");

			String classname=request.getParameter("Classname");
			String Sectionname=request.getParameter("Sectionname");
			String paymode=request.getParameter("paymode");
			String PaymentType=request.getParameter("PaymentType");
			String acyear=request.getParameter("accyear");
			String termId=request.getParameter("termId");
			String termName=request.getParameter("termName");
			String startdate = request.getParameter("startdate");
			String endate = request.getParameter("endate");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";
			}
			if(classid.equalsIgnoreCase("all")){
				classid="%%";
			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";
			}
			if(paymodeid.equalsIgnoreCase("all")){
				paymodeid="%%";
			}
			if(paymenttype.equalsIgnoreCase("all")){
				paymenttype="%%";
			}
			if(startdate.trim().equalsIgnoreCase("")){
				startdate = "0000-00-00";
			}
			else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(endate.trim().equalsIgnoreCase("")){
				endate = "9999-12-31";
			}else{
				endate = HelperClass.convertUIToDatabase(endate);
			}

			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);	
			String PropfilePath=null;

			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
			mapdata.put("class", classid);
			mapdata.put("setionid", setionid);
			mapdata.put("paymenttype", paymenttype);
			mapdata.put("sclname",custSchoolInfo.getSchname());
			mapdata.put("classname", classname+" "+Sectionname);
			mapdata.put("scenm", Sectionname);
			mapdata.put("paymode", paymode);
			mapdata.put("paytype", PaymentType);
			mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId",termName);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("branch",schName);


			String sourceFileName=request.getRealPath("Reports/Feecollectionreportpdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeCollectionReport" + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes,0,bytes.length);
			outstream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionPdfReport Ending");

		return null;
	}



	public ActionForward FeeCollectionExcelReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionExcelReport Starting");


		String filePath = null;

		try {

			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locid = request.getParameter("locid");
			LocationVO custSchoolInfo=new LocationVO();	
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locid);
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();
			System.out.println("//////////////"+userName);

			String accyear=request.getParameter("AccId");
			String classid=request.getParameter("ClassId");
			String setionid=request.getParameter("SectionId");
			String paymodeid=request.getParameter("Paymode");
			String paymenttype=request.getParameter("PaymentType");
			String locationid=request.getParameter("locationname");
			String classname=request.getParameter("Classname");
			String Sectionname=request.getParameter("Sectionname");
			String paymode=request.getParameter("paymode");
			String PaymentType=request.getParameter("PaymentType");
			String acyear=request.getParameter("accyear");
			String termId=request.getParameter("termId");
			String termName=request.getParameter("termName");
			String startdate = request.getParameter("startdate");
			String endate = request.getParameter("endate");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";
			}
			if(classid.equalsIgnoreCase("all")){
				classid="%%";
			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";
			}
			if(paymodeid.equalsIgnoreCase("all")){
				paymodeid="%%";

			}
			if(paymenttype.equalsIgnoreCase("all")){
				paymenttype="%%";

			}
			if(startdate.trim().equalsIgnoreCase("")){
				startdate = "0000-00-00";
			}
			else{
				startdate = HelperClass.convertUIToDatabase(startdate);
			}
			if(endate.trim().equalsIgnoreCase("")){
				endate = "9999-12-31";
			}else{
				endate = HelperClass.convertUIToDatabase(endate);
			}

			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId,cpojo,startdate,endate);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);		
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
			mapdata.put("class", classid);
			mapdata.put("setionid", setionid);
			mapdata.put("paymenttype", paymenttype);
			mapdata.put("sclname", custSchoolInfo.getSchname());
			mapdata.put("classname", classname);
			mapdata.put("scenm", Sectionname);
			mapdata.put("paymode", paymode);
			mapdata.put("paytype", PaymentType);
			mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId", termName);
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());


			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/FeecollectionreportXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					mapdata, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/FeecollectionExcelReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Fee Collection Details" };
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

			pdfxls = new File(request.getRealPath("Reports/FeecollectionExcelReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=FeecollectionExcelReport.xls");
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
				+ " Control in ReportsMenuAction : FeeCollectionExcelReport Ending");

		return null;
	}



	public ActionForward getSectionByClassLoc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClassLoc Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String classId = request.getParameter("classId");
			String location=request.getParameter("location");



			if(classId=="all"){
				classId="%%";
			}

			System.out.println("class id value>>>"+classId);
			ArrayList<ReportMenuVo> sectionlist = new ReportsMenuBD().getSectionsByClassLoc(classId,location,userLoggingsVo);


			JSONObject object = new JSONObject();
			object.put("SectionList", sectionlist);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getSectionByClassLoc Ending");

		return null;
	}


	public ActionForward PrintDDDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintDDDetails Starting");

		String accYear=request.getParameter("AccId");

		String Termid=request.getParameter("Termid");
		String locationid=request.getParameter("locationid");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			if(Termid.equalsIgnoreCase("all")) {
				Termid="%%";
			}
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("Image",PropfilePath);
			mapdata.put("accYear",accYear);
			mapdata.put("Termid",Termid);
			mapdata.put("schName",schName);
			mapdata.put("schAddLine1",schAddLine1);
			mapdata.put("locationid",locationid);


			/*Printing Details Of DD*/

			String sourceFileName=request.getRealPath("Reports/DDReportPDF.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,JDBCConnection.getConnection(userLoggingsVo));
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
			/* exporter.exportReport();*/
			job.print(printRequestAttributeSet);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : PrintDDDetails Ending");


		return null;
	}



	public ActionForward FeeCollectionPrintDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionPrintDetails Starting");

		String accYear=request.getParameter("AccId");
		String Termid=request.getParameter("Termid");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userName = userDetailVO.getUserName();

			String accyear=request.getParameter("AccId");
			String classid=request.getParameter("ClassId");
			String setionid=request.getParameter("SectionId");
			String paymodeid=request.getParameter("Paymode");
			String paymenttype=request.getParameter("PaymentType");
			String locationid=request.getParameter("locationname");
			String locid = request.getParameter("locid");
			String classname=request.getParameter("Classname");
			String Sectionname=request.getParameter("Sectionname");
			String paymode=request.getParameter("paymode");
			String PaymentType=request.getParameter("PaymentType");
			String acyear=request.getParameter("accyear");
			String termId=request.getParameter("termId");
			String termName=request.getParameter("termName");
			String startdate = request.getParameter("startdate");
			String endate = request.getParameter("endate");

			if(termId.equalsIgnoreCase("all")){
				termId="%%";

			}

			if(classid.equalsIgnoreCase("all")){
				classid="%%";
			}
			if(setionid.equalsIgnoreCase("all")){
				setionid="%%";

			}
			if(paymodeid.equalsIgnoreCase("all")){
				paymodeid="%%";

			}
			if(paymenttype.equalsIgnoreCase("all")){
				paymenttype="%%";

			}
			ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
			list=ReportsMenuBD.getFeeCollectionPaymodeReport(locid,accyear,classid,setionid,paymodeid,paymenttype,termId,userLoggingsVo,startdate,endate);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);		
			String PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();

			String schName=request.getParameter("locationname");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("image",PropfilePath);
			mapdata.put("accYear",accyear);
			mapdata.put("class", classid);
			mapdata.put("setionid", setionid);
			mapdata.put("paymenttype", paymenttype);
			mapdata.put("sclname",locationid);
			mapdata.put("classname", classname);
			mapdata.put("scenm", Sectionname);
			mapdata.put("paymode", paymode);
			mapdata.put("paytype", PaymentType);
			mapdata.put("acyear", acyear);
			mapdata.put("scadd",schAddLine1);
			mapdata.put("fromdat",list.get(0).getBilldate());
			mapdata.put("todate",list.get(list.size()-1).getBilldate());
			mapdata.put("userName",userName);
			mapdata.put("termId", termName);

			/*examReportClassWiseDetails*/

			String sourceFileName=request.getRealPath("Reports/Feecollectionreportpdf.jrxml");

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);

			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			mapdata.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,mapdata,beanColDataSource);
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : FeeCollectionPrintDetails Ending");


		return null;
	}

	public ActionForward ITFeeCollectionReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : ITFeeCollectionReport Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_IT_FEE_COLLECTION);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);


			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation,userLoggingsVo));

			List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();

			String userType = userDetailVO.getUserNametype();
			String userCode = userDetailVO.getUserId();
			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");


			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation = "%%";
				list = new StudentRegistrationDelegate().getStudentLocationList(academic_year,location,userLoggingsVo);
			}
			else{

				list = new StudentRegistrationDelegate().getStudentList(academic_year,location,userLoggingsVo);
				System.out.println("Location From Action:" +list.get(0).getLocation());
			}
			if (academic_year == null || academic_year == ""
					|| academic_year.equalsIgnoreCase("")) {
				System.out.println("HelperClass.getCurrentYearID()"
						+ HelperClass.getCurrentYearID(userLoggingsVo));
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			List<StudentRegistrationVo> List = null;
			String searchTerm = request.getParameter("stuId");
			if (searchTerm != null && !searchTerm.equalsIgnoreCase("")) {
				List = new StudentRegistrationDelegate().getStudentDetails(searchTerm,academic_year+","+location,userLoggingsVo);
				request.setAttribute("searchTerm",searchTerm);

			} else {
				List = new StudentRegistrationDelegate().getStudentDetails1(userType,userCode,academic_year,schoolLocation,userLoggingsVo);

			}		
			if(academic_year == null || academic_year == "" || academic_year.equalsIgnoreCase("")) {
				academic_year = HelperClass.getCurrentYearID(userLoggingsVo);
			}

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);

			request.setAttribute("AccYearList", accYearList);
			request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : ITFeeCollectionReport Ending");

		return mapping.findForward(MessageConstants.IT_FEE_COLLECTION);

	}
	public ActionForward printITFeeCollectionReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : printITFeeCollectionReport Starting");
		String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String StudentId=request.getParameter("StuId");
			String accyer=request.getParameter("accyear");
			String locationId=request.getParameter("locid");
			System.out.println(StudentId+" "+accyer+" "+locationId);
			ArrayList<ITFeeVo> list=new ArrayList<ITFeeVo>();
			ITFeeVo ITF=new ITFeeVo();
			ITF.setMaj("1");
			ITF=ReportsMenuBD.getITFee(StudentId,accyer,locationId,userLoggingsVo);
			System.out.println("OUT DIO impl");
			String PropfilePath = request.getRealPath("/")+"images/"+ImageName.trim();
			String sourceFileName = request.getRealPath("Reports/ITFeeCollection.jrxml");
			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");
			String Bracket = res.getString("Bracket");
			String Bracket1 = res.getString("Bracket1");
			String Bracket2 = res.getString("Bracket2");
			String Bracket3 = res.getString("Bracket3");
			String Bracket4 = res.getString("Bracket4");
			String[] acy=ITF.getAccyear().split("-");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			Map parameters = new HashMap();
			parameters.put("locname", schName);
			parameters.put("Bracket", Bracket);
			parameters.put("Bracket1", Bracket1);
			parameters.put("Bracket2", Bracket2);
			parameters.put("Bracket3", Bracket3);
			parameters.put("Bracket4", Bracket4);

			System.out.println(ITF.getClassSec());
			/*parameters.put("StudentName", ITF.getStuName());
			System.out.println("/////////////"+ITF.getStuName());
			parameters.put("ParentName", ITF.getParentname());
			parameters.put("Class", ITF.getClassSec());
			parameters.put("TutionFee", ITF.getTutorial());
			parameters.put("TutionFeeInN", ITF.getStringtution());
			parameters.put("LabFee", ITF.getClab());
			parameters.put("LabFeeInN", ITF.getStringclab());
			parameters.put("Sacy", acy[0]);
			parameters.put("Lacy", acy[1]);
			System.out.println("Sacy "+acy[0]);
			System.out.println("Sacy "+acy[1]);*/
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(200);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,parameters,beanColDataSource);
			System.out.println("JASPER VIVER");
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			System.out.println("AFTER JASPER VIVER");
			viewer.setVisible(true);
			PrinterJob job = PrinterJob.getPrinterJob();
			PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
			printRequestAttributeSet.add(MediaSizeName.ISO_A0); 
			printRequestAttributeSet.add(new Copies(1));
			JRPrintServiceExporter exporter;
			exporter = new JRPrintServiceExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,printRequestAttributeSet);
			exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
			exporter.exportReport();
			job.print(printRequestAttributeSet);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : printITFeeCollectionReport Ending");
		return null;

	}
	public ActionForward getStudentsTempNewAdmissionList1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionList1 Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";

			}
			if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,userLoggingsVo);
			ArrayList<ReportMenuVo> arr = new ReportsMenuDaoImpl().getStudentsTempNewAdmissionList(vo,userLoggingsVo);

			JSONObject jsonobj = new JSONObject();
			jsonobj.accumulate("studentNewAdmissionList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentNewTempAdmissionList",arr);

			if(classId.equalsIgnoreCase("%%") && sectionId.equalsIgnoreCase("%%"))
			{
				request.getSession(false).setAttribute("classanddiv","All");
			}else if(!(classId.equalsIgnoreCase("%%")) && sectionId.equalsIgnoreCase("%%")){
				request.getSession(false).setAttribute("classanddiv",(details.get(0).getClassname()+ " "+"(All Div)"));
			}
			else{
				request.getSession(false).setAttribute("classanddiv",details.get(0).getClass_and_section());
			}

			request.getSession(false).setAttribute("accyearid",details.get(0).getAccYear());
			request.getSession(false).setAttribute("Strength",Integer.toString(arr.size()));
			request.getSession(false).setAttribute("LocationName",details.get(0).getLocationName());
		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionList1 Ending");

		return null;
	}

	public ActionForward getStudentsTempNewAdmissionListXL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListXL  Starting");

		try{
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();	
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StudentNewTempAdmissionListXL.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String PropfilePath = request.getRealPath("/")
					+ "images/" + ImageName.trim();


			String schAddLine1 = res.getString("AddressLine1");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewTempAdmissionList");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());			
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StudentNewTempAdmissionListXL.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "New Admission List Excel Report" };
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
					request.getRealPath("Reports/StudentNewTempAdmissionListXL.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Reports/StudentNewTempAdmissionListXL.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListXL   Ending");

		return null;
	}

	public ActionForward getStudentsTempNewAdmissionListPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListPDFReport  Starting");
		try{
			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentNewTempAdmissionList");
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId=request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);
			String sourceFileName = request.getRealPath("Reports/StudentNewTempAdmissionPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);
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


			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();
			parameters.put("schoollogo", PropfilePath);
			parameters.put("Address1", schAddLine1);
			parameters.put("accyear",request.getSession(false).getAttribute("accyearid"));
			parameters.put("classDiv",request.getSession(false).getAttribute("classanddiv"));
			parameters.put("strength",request.getSession(false).getAttribute("Strength"));
			parameters.put("locationName",custSchoolInfo.getSchname());
			parameters.put("custSchoolAddres", custSchoolInfo.getAddress());
			parameters.put("custSchoollogo", schoollogo);
			parameters.put("custSchoolboardlogo", PropfilePath);
			parameters.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			parameters.put("custSchoolno", custSchoolInfo.getSchoolcode());
			parameters.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			parameters.put("custSchoolEmail", custSchoolInfo.getEmailId());
			parameters.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			parameters.put("branch", request.getParameter("locationname"));
			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentNewTempAdmissionPDF - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();

			outstream.write(bytes, 0, bytes.length);

			outstream.flush();
		}
		catch(Exception e){

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentsTempNewAdmissionListPDFReport   Ending");

		return null;
	}

	public ActionForward getStudentListAdmiWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListAdmiWise  Starting");

		try{

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));

			vo.setStatus(request.getParameter("status"));

			List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentListAdmiWise(vo,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("stuList", stuList);
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getStudentListAdmiWise   Ending");
		return null;
	}

	public ActionForward getstudentRollNoWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentRollNoWise  Starting");

		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String status=request.getParameter("status");
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			vo.setStatus(status);


			List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentRollNoWise(vo,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("stuList", stuList);
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentRollNoWise   Ending");
		return null;
	}

	public ActionForward getstudentAlphaWise(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentAlphaWise  Starting");

		try{
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			vo.setStatus(request.getParameter("status"));

			List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentAlphaWise(vo,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("stuList", stuList);
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentAlphaWise   Ending");
		return null;
	}

	public ActionForward getstudentListRollNoWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListRollNoWise Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			ServletContext servletContext = request.getServletContext();
			String locId = request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(userLoggingsVo,locId);

			String className = request.getParameter("classname");
			String sectionName = request.getParameter("sectionnname");
			String accyear = request.getParameter("name");	
			String selection = request.getParameter("report");
			String locName = request.getParameter("locName");
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			vo.setStatus(request.getParameter("status"));


			List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentRollNoWise(vo,userLoggingsVo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					stuList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			String schName=res.getString("SchoolName");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("SchoolName",schName);
			mapdata.put("SchoolAdd",schAddLine1);
			mapdata.put("accyear",accyear);
			mapdata.put("location",custSchoolInfo.getSchname());
			mapdata.put("classname",className);
			mapdata.put("section",sectionName);
			mapdata.put("Reporttype","Student List - Roll No. Wise");
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("branch", request.getParameter("locName"));
			mapdata.put("status", request.getParameter("stustatus"));
			if(selection.equalsIgnoreCase("pdf")) {

				String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentListRollNoWise"+".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes,0,bytes.length);
				outstream.flush();
			}else if(selection.equalsIgnoreCase("excel")){

				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				String sourceFileName = request.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						mapdata, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "Student Roll No Wise Excel Report" };
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

				pdfxls = new File(request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=StudentListRollNoWiseXL.xls");
				response.setContentLength((int) pdfxls.length());
				input = new FileInputStream(pdfxls);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
			}
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListRollNoWise Ending");

		return null;
	}  

	public ActionForward getstudentListAdmisNoWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListAdmisNoWise Starting");

		try {
			ServletContext servletContext = request.getServletContext();

			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");

			String locId = request.getParameter("locId");
			LocationVO custSchoolInfo=new LocationVO();
			custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);

			String className = request.getParameter("classname");
			String sectionName = request.getParameter("sectionnname");
			String accyear = request.getParameter("name");	
			String selection = request.getParameter("report");
			String locName = request.getParameter("locName");
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			vo.setStatus(request.getParameter("status"));


			List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentListAdmiWise(vo,cpojo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stuList);
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();
			String schName=res.getString("SchoolName");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("SchoolName",schName);
			mapdata.put("SchoolAdd",schAddLine1);
			mapdata.put("accyear",accyear);
			mapdata.put("location",custSchoolInfo.getSchname());
			mapdata.put("classname",className);
			mapdata.put("section",sectionName);
			mapdata.put("Reporttype","Student List - Admission No. Wise"); 
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("branch",request.getParameter("locName"));
			mapdata.put("status",request.getParameter("stustatus"));
			if(selection.equalsIgnoreCase("pdf")) {

				String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);
				byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""+ "StudentList_Admission_No._Wise"+".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes,0,bytes.length);
				outstream.flush();
			}else if(selection.equalsIgnoreCase("excel")){

				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				String sourceFileName = request
						.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						mapdata, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(
						request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "Student List Admission No Wise Excel Report" };
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
						request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=StudentList_Admission_No._Wise.xls");
				response.setContentLength((int) pdfxls.length());
				input = new FileInputStream(pdfxls);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
			}
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListAdmisNoWise Ending");

		return null;
	} 

	public ActionForward getstudentListAlphaWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListAlphaWise Starting");

		try {
			ServletContext servletContext = request.getServletContext();
			UserLoggingsPojo cpojo=(UserLoggingsPojo)request.getSession(false).getAttribute("token_information");
			String locId = request.getParameter("locId");

			LocationVO custSchoolInfo=HelperClass.getCustSchoolInfo(cpojo,locId);


			String className = request.getParameter("classname");
			String sectionName = request.getParameter("sectionnname");
			String accyear = request.getParameter("name");	
			String selection = request.getParameter("report");
			String locName = request.getParameter("locName");
			ReportMenuVo vo = new ReportMenuVo();
			vo.setLocationId(request.getParameter("location"));
			vo.setAccyearId(request.getParameter("accyear"));
			vo.setClassId(request.getParameter("classId"));
			vo.setSectionId(request.getParameter("section"));
			vo.setStatus(request.getParameter("status"));


			List<ReportMenuVo> stuList = new ReportsMenuBD().getstudentAlphaWise(vo,cpojo);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					stuList);	
			String PropfilePath=null;
			if(custSchoolInfo.getBoardlogo().trim().equalsIgnoreCase("-")){
				PropfilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			}
			else{
				PropfilePath = servletContext.getRealPath("/") + custSchoolInfo.getBoardlogo().trim();
			}

			String schoollogo = servletContext.getRealPath("/") + custSchoolInfo.getSchoollogo().trim();

			String schName=res.getString("SchoolName");
			String schAddLine1=res.getString("AddressLine1");

			Map mapdata=new HashMap();

			mapdata.put("SchoolName",schName);
			mapdata.put("SchoolAdd",schAddLine1);
			mapdata.put("accyear",accyear);
			mapdata.put("location",custSchoolInfo.getSchname());
			mapdata.put("classname",className);
			mapdata.put("section",sectionName);
			mapdata.put("Reporttype","Student List - Alphabetic Wise");
			mapdata.put("custSchoolAddres", custSchoolInfo.getAddress());
			mapdata.put("custSchoolAddres1", custSchoolInfo.getAddress2());
			mapdata.put("custSchoollogo", schoollogo);
			mapdata.put("custSchoolboardlogo", PropfilePath);
			mapdata.put("custSchoolaffilno", custSchoolInfo.getAffilno());
			mapdata.put("custSchoolno", custSchoolInfo.getSchoolcode());
			mapdata.put("custSchoolwebsit", custSchoolInfo.getWebsite());
			mapdata.put("custSchoolEmail", custSchoolInfo.getEmailId());
			mapdata.put("branch", request.getParameter("locName"));
			mapdata.put("status", request.getParameter("stustatus"));
			if(selection.equalsIgnoreCase("pdf")) {

				String sourceFileName=request.getRealPath("Reports/StudentListRollNoWise.jrxml");

				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager.compileReport(design);

				byte[] bytes =JasperRunManager.runReportToPdf(jasperreport,mapdata,beanColDataSource);

				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "StudentListAlphabeticWise"+".pdf\"");

				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(bytes,0,bytes.length);
				outstream.flush();
			}else if(selection.equalsIgnoreCase("excel")){

				File pdfxls = null;
				FileInputStream input = null;
				BufferedInputStream buf = null;
				ServletOutputStream stream = null;

				String sourceFileName = request
						.getRealPath("Reports/StudentListRollNoWiseXL.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);
				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				stream = response.getOutputStream();
				JasperPrint print = JasperFillManager.fillReport(jasperreport,
						mapdata, beanColDataSource);
				JRXlsExporter exporter = new JRXlsExporter();
				File outputFile = new File(
						request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				FileOutputStream fos = new FileOutputStream(outputFile);
				String[] sheetNames = { "StudentList AlphabeticWise Excel Report" };
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
						request.getRealPath("Reports/StudentListRollNoWiseXL.xls"));
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=StudentListAlphabeticWise.xls");
				response.setContentLength((int) pdfxls.length());
				input = new FileInputStream(pdfxls);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				stream = response.getOutputStream();
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
			}
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentListAlphaWise Ending");

		return null;
	} 

	public ActionForward getExpenditureReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExpenditureReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_INCOMEANDEXPENCE);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);



			System.out.println("From date in Actin Class: "+request.getParameter("fromdate"));
			System.out.println("To date in Actin Class: "+request.getParameter("todate"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExpenditureReport Ending");

		return mapping.findForward(MessageConstants.EXPENDITURE_REPORT);
	}


	public ActionForward getExpenditureDetailsReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExpenditureDetailsReport Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			ReportMenuVo vo=new ReportMenuVo();

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			request.setAttribute("locationList", locationList);

			ReportMenuForm reform = (ReportMenuForm) form;
			reform.setFromdate(request.getParameter("fromdate"));
			reform.setTodate(request.getParameter("todate"));
			reform.setLocation(request.getParameter("schoolname"));
			reform.setLocName(request.getParameter("locName"));

			ArrayList<ReportMenuVo> expenditure = new ReportsMenuBD().getExpenditureReport(reform,custdetails);

			request.setAttribute("fromdate", (request.getParameter("fromdate")));
			request.setAttribute("todate", (request.getParameter("todate")));
			request.setAttribute("locName",request.getParameter("schoolname"));
			request.setAttribute("expenditure", expenditure);
			request.getSession(false).setAttribute("expenditureReport", expenditure);
			request.getSession(false).setAttribute("fromdate", (request.getParameter("fromdate")));
			request.getSession(false).setAttribute("todate", (request.getParameter("todate")));
			request.getSession(false).setAttribute("locName",request.getParameter("schoolname"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getExpenditureDetailsReport Ending");

		return mapping.findForward(MessageConstants.EXPENDITURE_REPORT);
	}


	public ActionForward ExpenditureXLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : ExpenditureXLS  Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			System.out.println("DOWNLOADING EXCEL");
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/ExpenditureXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			/*
			 * List<AddFeeVO> List = new ArrayList<AddFeeVO>(); List =
			 * (List<AddFeeVO>) request.getSession(false)
			 * .getAttribute("feelistdownload");
			 */
			String currentlocation =new ExamDetailsBD().getlocationname(request.getSession(false).getAttribute("locName").toString(),userLoggingsVo);



			ArrayList<ReportMenuVo> List = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("expenditureReport");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					List);
			Map parameters = new HashMap();
			parameters.put("fromdate", request.getSession(false).getAttribute("fromdate"));
			parameters.put("todate", request.getSession(false).getAttribute("todate"));
			parameters.put("locationname",currentlocation);

			/*System.out.println("////////////"+currentlocation);*/
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/ExpenditureXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Expenditure Detail Excel Report" };
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
					request.getRealPath("Reports/ExpenditureXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=ExpenditureXLSReport.xls");
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
				+ " Control in ReportsMenuAction : ExpenditureXLS   Ending");
		return null;

	}

	public ActionForward ExpenditurePDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : ExpenditurePDF  Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			System.out.println("downloading pdf");

			/*
			 * List<AddFeeVO> Details = new ArrayList<AddFeeVO>(); Details =
			 * (List<AddFeeVO>)
			 * request.getSession(false).getAttribute("feelistdownload");
			 */

			String currentlocation =new ExamDetailsBD().getlocationname(request.getSession(false).getAttribute("locName").toString(),userLoggingsVo);

			ArrayList<ReportMenuVo> Details = (ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("expenditureReport");


			String sourceFileName = request
					.getRealPath("Reports/ExpenditurePDFReport.jrxml");
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
			parameters.put("fromdate", request.getSession(false).getAttribute("fromdate"));
			parameters.put("todate", request.getSession(false).getAttribute("todate"));
			parameters.put("locationname",currentlocation);
			parameters.put("Image", PropfilePath);

			/*
			 * parameters.put("Image", clientImage);
			 * 
			 * parameters.put("ClientName", ClientName);
			 * 
			 * parameters.put("ClientAddress", ClientAddress_l1);
			 */

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "ExpenditurePDFReport - " + ".pdf\"");

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
				+ " Control in ReportsMenuAction : ExpenditurePDF  Ending");
		return null;

	}

	public ActionForward getClassesByStreamAndLocation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStreamAndLocation Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);

			String streamId = request.getParameter("streamId");
			String locationname = request.getParameter("locationname");

			System.out.println("streamId"+streamId);
			ArrayList<ReportMenuVo> classesList = new ReportsMenuDaoImpl().getClassesByStreamAndLocation(streamId,locationname,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("ClassesList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getClassesByStreamAndLocation Ending");

		return null;
	}
	public ActionForward reportCardDownload(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : reportCardDownload  Starting");

		try{

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_REPORTS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_REPORTCARD);

			String accYear = (String) request.getSession(false).getAttribute("current_academicYear");

			request.setAttribute("accYear", accYear);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);

		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : reportCardDownload  Ending");

		return mapping.findForward(MessageConstants.ReportCardDownload);
	}
	public ActionForward getTerm1Exams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm1Exams  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");

			ReportMenuVo examid = new ReportsMenuBD().getTerm1Exams(accyear,classId,locationid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("examsid", examid.getExamname());
			obj.put("examstypeid", examid.getExamtypeid());
			obj.put("examstypeprefix", examid.getExamtypeprefix());
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm1Exams  Ends");


		return null;
	}
	public ActionForward getTerm2Exams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm2Exams  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");

			ReportMenuVo examid = new ReportsMenuBD().getTerm2Exams(accyear,classId,locationid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("examsid", examid.getExamname());
			obj.put("examstypeid", examid.getExamtypeid());
			obj.put("examstypeprefix", examid.getExamtypeprefix());
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getTerm2Exams  Ends");


		return null;
	}

	public ActionForward getFinalExams(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFinalExams  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");

			ReportMenuVo examid = new ReportsMenuBD().getFinalExams(accyear,classId,locationid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("examsid", examid.getExamname());
			obj.put("examstypeid", examid.getExamtypeid());
			obj.put("examstypeprefix", examid.getExamtypeprefix());
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getFinalExams  Ends");


		return null;
	}
	public ActionForward downloadReportCard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadReportCard Starting");

		try {

			UserLoggingsPojo dbdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String locationId = request.getParameter("locationId");
			String accyearId = request.getParameter("accyearId");
			String term1 = request.getParameter("terms1");
			String term2 = request.getParameter("terms2");
			String examstypeid = request.getParameter("examstypeid");
			String examstypeidterm2 = request.getParameter("examstypeidterm2");
			String stdId = request.getParameter("stdId");
			String sectionId = request.getParameter("sectionId");

			ReportMenuVo vo=new ReportMenuVo();
			vo.setAccYear(accyearId);
			vo.setClassId(classId);
			vo.setLocationId(locationId);
			//vo.setTermname(checkedTermValue);
			vo.setExamtypeid(examstypeid);
			vo.setExamstypeidterm2(examstypeidterm2);
			vo.setStudentId(stdId);
			vo.setSectionId(sectionId);
			vo.setTerm1(term1);
			vo.setTerm2(term2);

			List<ReportMenuVo> stuList = new ReportsMenuBD().getTermWiseReportCard(vo,dbdetails);
			List<ReportMenuVo> marksheet=null;
			ArrayList<ReportMenuVo> singlelist=null;
			String sourceFileName = null;		
			FileOutputStream outputFile=null;
			File secondDir = null;
			File firstDir = null;
			JRBeanCollectionDataSource beanColDataSource = null;
			if(!term2.equals("")){
				sourceFileName=request.getRealPath("Reports/ReportCardByClassTerms2.jrxml");
			}else{
				sourceFileName=request.getRealPath("Reports/ReportCardByClassTerms.jrxml");
			}


			/*ReportCard_Dir*/

			firstDir = new File(ReportCard_Dir);
			if (firstDir.exists()) {
				secondDir = new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId,dbdetails));
				secondDir.mkdir();
			} else {
				new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId,dbdetails)).mkdirs();
			}

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			String imageFilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			String boardFilePath=request.getRealPath("/")+ "images/" + BoardLogo.trim();
			ServletOutputStream outstream =null;
			byte[] bytes=null;
			Map parameters = new HashMap();
			parameters.put("schoollogo",imageFilePath);
			parameters.put("boardlogo",boardFilePath);

			beanColDataSource = new JRBeanCollectionDataSource(stuList);

			bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""+HelperClass.getAcademicYearFace(accyearId,dbdetails)+"-"+HelperClass.getClassName(classId, locationId,dbdetails)+".pdf\"");
			outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadReportCard Ending");

		return null;
	}

	public ActionForward downloadAcademicYearReportCard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadAcademicYearReportCard Starting");

		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String classId = request.getParameter("classId");
			String locationId = request.getParameter("locationId");
			String accyearId = request.getParameter("accyearId");
			String checkedTermValue = request.getParameter("terms");
			String examstypeid = request.getParameter("examstypeid");
			String stdId = request.getParameter("stdId");
			String sectionId = request.getParameter("sectionId");
			System.out.println("stdId is "+ stdId);

			String[] arrStringVal=checkedTermValue.split(",");

			ReportMenuVo vo=new ReportMenuVo();
			vo.setAccYear(accyearId);
			vo.setClassId(classId);
			vo.setLocationId(locationId);
			vo.setTermname(checkedTermValue);
			vo.setExamtypeid(examstypeid);
			vo.setStudentId(stdId);
			vo.setSectionId(sectionId);

			List<ReportMenuVo> stuList = new ReportsMenuBD().getAcademicYearWiseReportCard(vo,userLoggingsVo);
			System.out.println("size is "+stuList.size());
			List<ReportMenuVo> marksheet=null;
			ArrayList<ReportMenuVo> singlelist=null;
			String sourceFileName = null;		
			FileOutputStream outputFile=null;
			File secondDir = null;
			File firstDir = null;
			JRBeanCollectionDataSource beanColDataSource = null;
			sourceFileName=request.getRealPath("Reports/ReportCardByClassAcademicYear.jrxml");


			/*ReportCard_Dir*/

			firstDir = new File(ReportCard_Dir);
			if (firstDir.exists()) {
				secondDir = new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId,userLoggingsVo));
				secondDir.mkdir();
			} else {
				new File(ReportCard_Dir +  "/" + HelperClass.getAcademicYearFace(accyearId,userLoggingsVo)).mkdirs();
			}

			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			String imageFilePath=request.getRealPath("/")+ "images/" + ImageName.trim();
			String boardFilePath=request.getRealPath("/")+ "images/" + BoardLogo.trim();
			ServletOutputStream outstream =null;
			byte[] bytes=null;
			Map parameters = new HashMap();
			parameters.put("schoollogo",imageFilePath);
			parameters.put("boardlogo",boardFilePath);

			beanColDataSource = new JRBeanCollectionDataSource(stuList);

			bytes = JasperRunManager.runReportToPdf(jasperreport,parameters, beanColDataSource);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "outline; filename=\""+HelperClass.getAcademicYearFace(accyearId,userLoggingsVo)+"-"+HelperClass.getClassName(classId, locationId,userLoggingsVo)+".pdf\"");
			outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
		}	
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : downloadAcademicYearReportCard Ending");

		return null;
	}
	public ActionForward getstudentsHouseWise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsHouseWise Starting");

		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ReportMenuVo vo = new ReportMenuVo();

			String selection = request.getParameter("selection");
			String sectionId = request.getParameter("section");
			String accyearid = request.getParameter("accyear");
			String classId = request.getParameter("classId");
			String location = request.getParameter("location");
			String status = request.getParameter("status");

			if(location.equalsIgnoreCase("all"))
			{
				location="%%";
				vo.setLocationId(location);
			}
			else if(classId.equalsIgnoreCase("all"))
			{
				classId="%%";
				sectionId="%%";
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}
			else if(sectionId.equalsIgnoreCase("all"))
			{
				sectionId="%%";
				vo.setSectionId(sectionId);
			}
			else{
				vo.setAccyearId(accyearid);
				vo.setLocationId(location);
				vo.setClassId(classId);
				vo.setSectionId(sectionId);
			}

			vo.setAccyearId(accyearid);
			vo.setLocationId(location);
			vo.setClassId(classId);
			vo.setSectionId(sectionId);
			vo.setStatus(status);


			ArrayList<ReportMenuVo> details = new ReportsMenuBD().getclasssectionDetails(vo,custdetails);

			ArrayList<ReportMenuVo> arr = new ReportsMenuBD().getstudentsHouseWise(vo,custdetails);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("studentdobList", arr);
			response.getWriter().print(jsonobj);
			request.getSession(false).setAttribute("studentdobList",arr);

			String locname=HelperClass.getSchoolName(location, custdetails);
			String accYearName=HelperClass.getAcademicYearFace(accyearid, custdetails);
			String branch = HelperClass.getLocationFace(location, custdetails);  
			String filePath = getServlet().getServletContext().getRealPath("")+ "\\Files\\CUSTOMIZABLEREPORT\\StudentHouseWise.xlsx";
			HouseWisePDFFile download=new HouseWisePDFFile();
			download.download(arr,filePath,locname,accYearName,branch);

			String pdffilePath = getServlet().getServletContext().getRealPath("")+ "\\Files\\CUSTOMIZABLEREPORT\\StudentHouseWise.pdf";
			HouseWiseExcelFile download1=new HouseWiseExcelFile();
			download1.download(arr,pdffilePath,accYearName,locname,branch);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getstudentsHouseWise Ending");

		return null;

	}

	public ActionForward studentHouseWiseExcelReportInternally(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentHouseWiseExcelReportInternally  Starting");
		try{

			String filePath = getServlet().getServletContext().getRealPath("") + "\\Files\\CUSTOMIZABLEREPORT\\StudentHouseWise.xlsx";
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();

			response.addHeader("content-disposition", "attachment; filename=House Wise Student Details.xls");

			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);

			in.close();
			out.close();

			//return mapping.findForward(MessageConstants.MOONTHLY_LEGEND_REPORT);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentHouseWiseExcelReportInternally Ending");
		return null;
	}
	public ActionForward studentHouseWisePDFReportInternally(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentHouseWisePDFReportInternally  Starting");
		try{
			/*ArrayList<ReportMenuVo> list=(ArrayList<ReportMenuVo>) request.getSession(false).getAttribute("studentdobList");

			String PropfilePath = request.getRealPath("/")+ "images/" + ImageName.trim();
			HousewiseExcelDownload download=new HousewiseExcelDownload();
			download.generatePDF(list,PropfilePath);*/



			String filePath = getServlet().getServletContext().getRealPath("") + "\\Files\\CUSTOMIZABLEREPORT\\StudentHouseWise.pdf";
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();

			response.addHeader("content-disposition", "attachment; filename=House Wise Student Details.pdf");

			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);

			in.close();
			out.close();

			//return mapping.findForward(MessageConstants.MOONTHLY_LEGEND_REPORT);



		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentHouseWisePDFReportInternally Ending");
		return null;
	}

	//Analytical performance

	public ActionForward StudentAnalyticalPerformanceReportsPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : studentDOBWisePDFReport  Starting");
		try{

			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<ReportMenuVo> studentList = ( ArrayList<ReportMenuVo>)request.getSession(false).getAttribute("studentdobList");
			String sourceFileName = request
					.getRealPath("Reports/StudentAnalyticalPerformanceReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					studentList);

			String PropfilePath = request.getRealPath(
					"/")
					+ "images/" + ImageName.trim();


			Map parameters = new HashMap();

			parameters.put("studentId",request.getParameter("studentId"));
			parameters.put("accyearId",request.getParameter("accyear"));
			parameters.put("locId",request.getParameter("locId"));
			parameters.put("className",request.getParameter("className"));
			parameters.put("sectionName",request.getParameter("sectionName"));
			parameters.put("studentname",request.getParameter("studentname")); 
			parameters.put("AdmissionNo",request.getParameter("AdmissionNo")); 
			parameters.put("classsection",request.getParameter("classsection"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, JDBCConnection.getConnection(custdetails));

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StudentAnalyticalPerformanceReport_"+"studentname - " + ".pdf\"");

			ServletOutputStream outstream = response.getOutputStream();
			outstream.write(bytes, 0, bytes.length);
			outstream.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getReportType(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");

			String  reporttype= new ReportsMenuDaoImpl().getReportType(accyear,classId,locationid,userLoggingsVo);
			String  setup= new ReportsMenuDaoImpl().getMaximumMarkSetup(accyear,classId,locationid,userLoggingsVo);

			JSONObject obj = new JSONObject();
			obj.put("reporttype", reporttype);
			obj.put("setup", setup);
			response.getWriter().print(obj);

		}catch(Exception e){
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Ends");


		return null;
	}
	
	public ActionForward getTerm1ExamDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");
			
			ReportMenuVo vo= new ReportsMenuDaoImpl().getTerm1ExamDetails(accyear,classId,locationid,userLoggingsVo);
			
			JSONObject obj = new JSONObject();
			obj.put("examsid", vo.getExamId());
			obj.put("examstypeid", vo.getExamtypeid());
			obj.put("examstypeprefix", vo.getExamtypeprefix());
			obj.put("count", vo.getCount());
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Ends");
		
		
		return null;
	}
	
	public ActionForward getTerm2ExamDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");
			
			ReportMenuVo vo= new ReportsMenuDaoImpl().getTerm2ExamDetails(accyear,classId,locationid,userLoggingsVo);
			
			JSONObject obj = new JSONObject();
			obj.put("examsid", vo.getExamId());
			obj.put("examstypeid", vo.getExamtypeid());
			obj.put("examstypeprefix", vo.getExamtypeprefix());
			obj.put("count", vo.getCount());
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Ends");
		
		
		return null;
	}
	
	public ActionForward getAcademicExamDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Starting");
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		try{
			String accyear=request.getParameter("accyear");
			String classId=request.getParameter("classId");
			String locationid=request.getParameter("locationid");
			
			ReportMenuVo vo= new ReportsMenuDaoImpl().getAcademicExamDetails(accyear,classId,locationid,userLoggingsVo);
			
			JSONObject obj = new JSONObject();
			obj.put("examsid", vo.getExamId());
			obj.put("examstypeid", vo.getExamtypeid());
			obj.put("examstypeprefix", vo.getExamtypeprefix());
			obj.put("count", vo.getCount());
			response.getWriter().print(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReportsMenuAction : getReportType  Ends");
		
		
		return null;
	}
}




