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
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddStageVO;

public class StageAction extends DispatchAction

{
	

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	

	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	
public  ActionForward Edit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageAction: Edit Starting");
		
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_STAGEMASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String args = "Modify Stage";
			request.setAttribute("Stage", args);
			
			
			
			System.out.println("Edit Stage Action Is Working");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			AddStageForm aform = new AddStageForm();

			aform.setCustid(custdetails.getCustId());
			aform.setCreatedby(HelperClass.getCurrentUserID(request));
		
			 String stageid = request.getParameter("stageCode");
		    aform.setStageid(stageid);	
		    
		    System.out.println(stageid);
		  		    
		    String createUser = HelperClass.getCurrentUserID(request);
		
		    System.out.println(createUser);
			
		    AddStageForm delegate=new StageDelegateBD().EditStageDetails(aform,custdetails);
	
            request.setAttribute("StageLIst", delegate);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageAction: Edit Ending");
    	return mapping.findForward(MessageConstants.ADD_STAGE);


	}


public  ActionForward Delete(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction: Delete Starting");
	try {
		
		System.out.println("Delete Stage Action Is Working");
		
		request.setAttribute(MessageConstants.MODULE_NAME,
				MessageConstants.BACKOFFICE_SETTINGS);
		
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
				MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
	  		    
	    String stageid = request.getParameter("stageCode");
	    String[] code=stageid.split(",");
	   /* AddStageVO vo= new AddStageVO();*/
	    
	    /*vo.setStageCode(stageid);*/
	    
	    String status = new StageDelegateBD().deleteStage(code,log_audit_session,custdetails);
		
		System.out.println("Ststus:::"+status);

		JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("jsonResponse", status);
		
		response.getWriter().print(jsonobj);

	}
	catch (Exception e)
	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction: Delete Ending");


	
	return null;


}

public ActionForward getstagecount(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception 
		
	{
		
	System.out.println("getnamecount  Action class working");
	

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : getstagecount Starting");
	
	boolean status = false;
	String name=request.getParameter("name");
	String id=request.getParameter("id");
	System.out.println(name);
	System.out.println(id);
	
	try {
		
		AddStageVO vo=new AddStageVO();
		vo.setStageCode(id);
		vo.setStageName(name);
		
		StageDelegateBD delegate= new StageDelegateBD();
		
		status=delegate.getstagecount(vo);
		
		JSONObject jsonObject=new JSONObject();
		
		jsonObject.put("message", status);
		
		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : getstagecount Ending");

	return null;
 }

public ActionForward downloadStageDetailsXLS(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : downloadStageDetailsXLS  Starting");
			
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StageDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		/*	List<AddStageVO> MasterList = new ArrayList<AddStageVO>();
			MasterList = (List<AddStageVO>) request.getSession(false)
					.getAttribute("EXcel");*/
			String username = null;
			username = HelperClass.getCurrentUserID(request);

			AddStageVO vo = new AddStageVO();

			
			
			vo.setStageName(request.getParameter("searchTerm"));

			ArrayList<AddStageVO> list = new StageDelegateBD().StageDetails(userLoggingsVo);

			request.setAttribute("StageDetails", list);
			request.setAttribute("searchnamelist", request.getParameter("searchTerm"));
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StageDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Stage Details Excel Report" };
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
					request.getRealPath("Reports/StageDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StageDetailsXLSReport.xls");
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
				+ " Control in StageAction : downloadRoleDetailsXLS  Ending");
		return null;

	}
	




public ActionForward downloadStageDetailsPDF(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : downloadStageDetailsPDF  Starting");

		try {

		   	/*AddStageVO vo = new AddStageVO();

			vo.setStageName(request.getParameter("searchvalue"));

			ArrayList<AddStageVO> list = new StageDelegateBD().StageDetails(vo);*/
			
			
		/*	List<AddStageVO> MasterList = new ArrayList<AddStageVO>();
			MasterList = (List<AddStageVO>) request.getSession(false)
					.getAttribute("EXcel");*/
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String username = null;
			username = HelperClass.getCurrentUserID(request);

			AddStageVO vo = new AddStageVO();

			
			
			vo.setStageName(request.getParameter("searchTerm"));

			ArrayList<AddStageVO> list = new StageDelegateBD().StageDetails(userLoggingsVo);

			request.setAttribute("StageDetails", list);
			request.setAttribute("searchnamelist", request.getParameter("searchTerm"));
			
			
			
			String sourceFileName = request
					.getRealPath("Reports/StageDetailsPdfReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("Image", PropfilePath);


			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "StageDetailsPdfReport - " + ".pdf\"");

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
			+ " Control in StageAction : downloadStageDetailsPDF  Ending");
	return null;

}

public ActionForward stagedetailsListByStatus(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception 
	{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : stagedetailsListByStatus Starting");
	
	
	String status=request.getParameter("status");
	String location=request.getParameter("location");
	String searchvalue=request.getParameter("searchvalue");
	
	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		List<AddStageVO> list = new ArrayList<AddStageVO>();
		
		AddStageVO vo=new AddStageVO();
		vo.setStatus(status);
		vo.setSearchName(searchvalue.trim());
		vo.setLocId(location);
		vo.setAccyearCode(HelperClass.getCurrentYearID(custdetails));
		
		
		StageDelegateBD delegate= new StageDelegateBD();
		list=delegate.stagedetailsListByStatus(vo,custdetails);
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", list);
		response.getWriter().println(jsonObject);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction : stagedetailsListByStatus Ending");

	return null;
 }

public  ActionForward deleteStageByStatus(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction: deleteStageByStatus Starting");
	try {
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
	  		    
	    String[] stageid = request.getParameter("stageCode").split(",");
	    String inactiveReason = request.getParameter("inactivereason");
		String otherReason = request.getParameter("otherreason"); 
		String status = request.getParameter("status");  
		String activeReason = request.getParameter("activereason"); 
	    
		AddStageVO vo= new AddStageVO();
		vo.setStageId(stageid);
		vo.setActiveReason(activeReason);
		vo.setInactiveReason(inactiveReason);
		vo.setOtherReason(otherReason);
		vo.setStatus(status);
		vo.setLog_audit_session(log_audit_session);
		vo.setLocId(request.getParameter("locId"));
	    
	    String result = new StageDelegateBD().deleteStageByStatus(vo,custdetails);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("status", result);
		response.getWriter().print(jsonobj);
	}
	catch (Exception e)
	{
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StageAction: deleteStageByStatus Ending");
	return null;
  }

}
	




