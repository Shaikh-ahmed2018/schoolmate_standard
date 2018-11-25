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
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;
public class AddDepartmentDetailsAction extends DispatchAction {
	

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	
	private static String ImageName = res.getString("ImageName");
	
	private static final Logger logger = Logger
			.getLogger(AddDepartmentDetailsAction.class);

	public ActionForward Add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : Add Starting");

		try {

			String title ="New Department";
			request.setAttribute("title", title);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DEPARTMENTDETAILS);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : Add Ending");

		return mapping.findForward(MessageConstants.ADD_DEPARTMENT);

	}

	public ActionForward addDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : addDepartment Starting");

		String username;

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

			DepartmentMasterForm deptform = new DepartmentMasterForm();
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			username = HelperClass.getCurrentUserID(request);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String deptname = request.getParameter("departmname");
			String description = request.getParameter("description");
			String update_dept = request.getParameter("update_dept");

			deptform.setDept_name(deptname);
			deptform.setDepartment_description(description);
			deptform.setDepartmentid((update_dept));
			deptform.setUsername(username);
			deptform.setLog_audit_session(log_audit_session);

			String status = new DepartmentMasterBD().insertDepartmentDetails(deptform, update_dept,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : addDepartment Ending");

		return null;

	}

	public ActionForward validatedepartmentname(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Starting");

		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String deptname = request.getParameter("deptname").trim();
			String deptid = request.getParameter("deptid").trim();

			DepartmentMasterForm deptform = new DepartmentMasterForm();

			deptform.setDept_name(deptname);
			deptform.setDepartmentid(deptid);

			String deptname_available = new DepartmentMasterBD().validateDeptName(deptform,userLoggingsVo);
            //ln("//////////////"+deptname_available);
			
            JSONObject object = new JSONObject();
			object.put("des_available", deptname_available);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : validatedepartmentname: Ending");
		return null;
	}

	public ActionForward validateDeptNameUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction: validateDeptNameUpdate: Starting");

		try {
			String deptname = request.getParameter("deptname");
			String deptid = request.getParameter("deptid");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			DepartmentMasterVO validateUpdate = new DepartmentMasterVO();
			validateUpdate.setDepName(deptname);
			validateUpdate.setDepId(deptid);
			boolean desname_available = new DepartmentMasterBD().validateDeptNameUpdate(validateUpdate,userLoggingsVo);

			JSONObject object = new JSONObject();
			object.put("des_available", desname_available);
			response.getWriter().print(desname_available);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction: validateDeptNameUpdate: Ending");
		return null;
	}

	public ActionForward deleteDepartment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteDepartment Starting");
		@SuppressWarnings("unused")
		String username = null;

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			DepartmentMasterVO deptvo = new DepartmentMasterVO();
			String[] deptid = request.getParameter("getDataArray").split(",");
			deptvo.setStatus(request.getParameter("status"));
			deptvo.setRemark(request.getParameter("reason"));
			deptvo.setLog_audit_session(log_audit_session);
			String button=request.getParameter("button");
			
			String status = new DepartmentMasterBD().deleteDepartmentDetails(deptid,custdetails,deptvo,button);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : deleteDepartment Ending");

		return null;

	}

	public ActionForward editDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Starting");
		try {
			String title ="Modify Department";
			request.setAttribute("title", title);
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_DEPARTMENTDETAILS);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			String edit = request.getParameter("name"); 
			String status = request.getParameter("status");
			String searchname = request.getParameter("searchname");

			DepartmentMasterVO edit_list = new DepartmentMasterBD().getEditDepartmentDetails(edit,userLoggingsVo);

			request.setAttribute("editlist", edit_list);
			
			request.setAttribute("status",status);
			request.setAttribute("searchname",searchname);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : editDepartment Ending");

		return mapping.findForward(MessageConstants.ADD_DEPARTMENT);
	}

	public ActionForward searchDepartment(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : searchDepartment Starting");

		try {
			String Search_Name = request.getParameter("searchname");
			String SearchName = Search_Name.trim();
			String status=request.getParameter("status");
            //ln("//////////////////////////"+status);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
			searchvo.setSearch_name(SearchName);*/
			ArrayList<DepartmentMasterVO> getdeptlist = new DepartmentMasterBD().searchDepartment(SearchName,custdetails,status);

			request.setAttribute("DepartmentDetails", getdeptlist);

			request.setAttribute("searchdetails", SearchName);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : searchDepartment Ending");

		return mapping.findForward(MessageConstants.DEPARTMENT_DETAILS);

	}

	
	public ActionForward downloadDepartmentDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsXLS  Starting");
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/DepartmentDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			ArrayList<DepartmentMasterVO> deplist = new ArrayList<DepartmentMasterVO>();
				
			String SearchName = request.getParameter("searchTerm");
			String status=request.getParameter("status");
			DepartmentMasterVO searchvo = new DepartmentMasterVO();
			/*searchvo.setSearch_name(SearchName);*/
			
			if(SearchName != null){
				
				deplist = new DepartmentMasterBD().searchDepartment(SearchName.trim(),custdetails,status);
				request.setAttribute("DepartmentDetails", deplist);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				 deplist = new DepartmentMasterBD().getDepartmentDetails(custdetails);
				
			}

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					deplist);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/DepartmentDetailsXLSReport.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Department Details Excel Report" };
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
					request.getRealPath("Reports/DepartmentDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=DepartmentDetailsXLSReport.xls");
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
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsXLS  Ending");
		return null;

	}
	
	
	
	
	public ActionForward downloadDepartmentDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Starting");
		
		
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			ArrayList<DepartmentMasterVO> deplist = new ArrayList<DepartmentMasterVO>();
			
			String SearchName = request.getParameter("searchTerm");
			
			/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
			searchvo.setSearch_name(SearchName);*/
			String status=request.getParameter("status");
			if(SearchName != null){
				
				deplist = new DepartmentMasterBD().searchDepartment(SearchName.trim(),custdetails,status);
				request.setAttribute("DepartmentDetails", deplist);
				request.setAttribute("searchnamelist", SearchName);
			}
			else
			{
				 deplist = new DepartmentMasterBD().getDepartmentDetails(custdetails);
				
			}
			
			
			String sourceFileName = request
					.getRealPath("Reports/DepartmentDetailsPDFReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					deplist);

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
					+ "DepartmentDetailsPDFReport - " + ".pdf\"");

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
				+ " Control in AddDepartmentDetailsAction : downloadDepartmentDetailsPDF  Ending");
	
		return null;

	}
	
	public ActionForward searchDepartmentDetailList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : searchDepartmentDetailList Starting");

		try {
			String Search_Name = request.getParameter("searchname");
			String SearchName = Search_Name.trim();
			String status=request.getParameter("status");
            //ln("//////////////////////////"+status);
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

			/*DepartmentMasterVO searchvo = new DepartmentMasterVO();
			searchvo.setSearch_name(SearchName);*/
			ArrayList<DepartmentMasterVO> getdeptlist = new DepartmentMasterBD().searchDepartment(SearchName,custdetails,status);

			JSONObject json = new JSONObject();
			json.put("searchDepartmentDetails", getdeptlist);
			response.getWriter().print(json);

		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDepartmentDetailsAction : searchDepartmentDetailList Ending");

		return null;

	}
	
	
	
	
}
