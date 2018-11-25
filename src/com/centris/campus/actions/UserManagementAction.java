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

import com.centris.campus.daoImpl.UserManagementDaoImpl;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.RoleMasterBD;
import com.centris.campus.delegate.UserManagementBD;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserRecordVO;

public class UserManagementAction extends DispatchAction{
	
	
	private static final Logger logger = Logger.getLogger(UserManagementAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward changePasswordHome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePasswordHome Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PASSWORDMAINTAINANCE);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String userId = request.getParameter("userId");
			//System.out.println("88888888"+userId);
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserId(userId);
			
			UserRecordVO userrecordVo = new UserManagementBD().getUserDeatils(userManagementPojo,userLoggingsVo);
			request.setAttribute("userrecordVo", userrecordVo);
		
			request.setAttribute("UserId", userId);
			
			request.setAttribute("typename1", request.getParameter("typename"));
			request.setAttribute("status1", request.getParameter("status"));
			request.setAttribute("searchTextId1", request.getParameter("searchTextId"));
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePasswordHome Ending");

		return mapping.findForward(MessageConstants.CHANGE_PASSWORD);

	}
	
	public ActionForward changePassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePassword Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String userId=request.getParameter("selectUser");
			String passwrd=request.getParameter("confirmpasswrd");
			String techId=request.getParameter("hiddentechId");
			System.out.println("dfdfdgg"+techId);
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserId(userId);
			userManagementPojo.setPasswrd(passwrd);
			userManagementPojo.setLog_audit_session(log_audit_session);
			
			userManagementPojo.setTecId(techId);
			String result = new UserManagementBD().changePassword(userManagementPojo,userLoggingsVo);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", result);
			response.getWriter().print(jsonObject);
			
		
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: changePassword Ending");

		return null;

	}
	
	public ActionForward blockUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String userId =  request.getParameter("userId");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			
			
			String result =  new UserManagementBD().blockUser(userManagementPojo,custdetails);
				
			JSONObject jsonObject = new JSONObject();
			if(MessageConstants.TRUE.equalsIgnoreCase(result)){
				jsonObject.put("status", MessageConstants.BLOCK_USER_SUCCESS);
			}else{
				jsonObject.put("status", MessageConstants.BLOCK_USER_FAILURE);
				
			}
			
			
			response.getWriter().print(jsonObject);
			
			
			
			List<UserRecordVO>	 userRecords = new UserManagementBD().getUserRecordsBD(custdetails);
			request.setAttribute("userRecords", userRecords);
				
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Ending");

		return null;

	}
	
	public ActionForward downloaduserManagementXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction : downloaduserManagementXLS  Starting");
		
		try {
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/userdetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

			String searchterm = request.getParameter("searchterm");
			String type = request.getParameter("type");

			if (searchterm != "" && type != "") {

				UserManagementPojo userManagementPojo = new UserManagementPojo();
				userManagementPojo.setType(type);
				userManagementPojo.setSearchtext(searchterm);

				userRecords = new UserManagementBD()
						.getSearchUserDetailsBD(userManagementPojo,custdetails);
				
			} else {

				userRecords = new UserManagementBD().getUserRecordsBD(custdetails);
				
			}
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					userRecords);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/userdetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "User Details Excel Report" };
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
					request.getRealPath("Reports/userdetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=userdetailsxls.xls");
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
				+ " Control in UserManagementAction : downloaduserManagementXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloaduserManagementPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UserManagementAction : downloaduserManagementPDF  Starting");

			try {

				UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

				List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();

				String searchterm = request.getParameter("searchterm");
				String type = request.getParameter("type");

				
				if (searchterm != "" && type != "") {

					UserManagementPojo userManagementPojo = new UserManagementPojo();
					userManagementPojo.setType(type);
					userManagementPojo.setSearchtext(searchterm);

					userRecords = new UserManagementBD()
							.getSearchUserDetailsBD(userManagementPojo,custdetails);
				

				} else {

					userRecords = new UserManagementBD().getUserRecordsBD(custdetails);
					
				}
				
	
				String sourceFileName = request
						.getRealPath("Reports/userDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						userRecords);

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
						+ "userDetailsPDF.jrxml - " + ".pdf\"");

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
					+ " Control in UserManagementAction : downloaduserManagementPDF  Ending");
			return null;

		}

	public ActionForward addUsersettings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: addUsersettings Starting");
		try {

			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
			
			request.setAttribute("Page","New Users");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_USER_SETTINGS);

			List<RoleMasterPojo> roleMasterList = new RoleMasterBD().getRoles(userLoggingsVo,location);
			request.setAttribute("roleMasterList",roleMasterList);

			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: addUsersettings Ending");

		return mapping.findForward(MessageConstants.ADD_USER_SETTINGS);
	}
	
	public ActionForward getUserDetailsforEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: getUserDetailsforEdit Starting");
		try {

			String userid = request.getParameter("userid");
			request.setAttribute("Page","Modify Users");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String location = (String) request.getSession(false).getAttribute("current_schoolLocation");

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_USER_SETTINGS);

			List<RoleMasterPojo> roleMasterList = new RoleMasterBD().getRoles(userLoggingsVo,location);
			request.setAttribute("roleMasterList",roleMasterList);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			
			//UserManagementPojo details = new RoleMasterBD().getUserDetails(userid,userLoggingsVo.getCustId());
			
			UserRecordVO details = new UserRecordVO();
			details.setUserId(request.getParameter("userId").split(",")[0]);
			;
			
			UserRecordVO list = new UserManagementBD().getUserDetailsforEdit(details,userLoggingsVo);
			
			request.setAttribute("userdetail",list);
			
			request.setAttribute("historystatus",request.getParameter("status"));

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: getUserDetailsforEdit Ending");

		return mapping.findForward(MessageConstants.ADD_USER_SETTINGS);
	}
	
	
	public ActionForward insertUserDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: addUsersettings Starting");
		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_USER_SETTINGS);
			
			UserManagementPojo details = new UserManagementPojo();
			
			details.setRoleId(request.getParameter("roleName"));
			details.setLocationId(request.getParameter("location"));
			details.setUname(request.getParameter("username"));
			details.setPasswrd(request.getParameter("password"));
			details.setConfirmPasswrd(request.getParameter("confirmpassword"));
			details.setMobileno(request.getParameter("mobileno"));
			details.setEmail(request.getParameter("email"));
			details.setUserId(request.getParameter("hiddenuserid"));
			details.setLog_audit_session(log_audit_session);
			details.setCuruserid(HelperClass.getCurrentUserID(request));
			details.setAuserid(request.getParameter("auserid"));
			
			String status = new UserManagementBD().insertUserDetails(details,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("status", status);
			response.getWriter().print(json);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: addUsersettings Ending");

		return null;
	}
	
	
	public ActionForward InActiveUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: InActiveUserList Starting");
		try {
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_USER_SETTINGS);
			
			List<UserRecordVO> list = new ArrayList<UserRecordVO>();
			
			UserManagementPojo details = new UserManagementPojo();
			
			details.setStatus(request.getParameter("status"));
			details.setLog_audit_session(log_audit_session);
			
			
			list = new UserManagementDaoImpl().InActiveUserList(details,userLoggingsVo);
			JSONObject json = new JSONObject();
			json.put("list", list);
			response.getWriter().print(json);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: InActiveUserList Ending");

		return null;
	}
	
	public ActionForward blockUserByStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUserByStatus Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String userIds[] = request.getParameter("userId").split(",");
			String status = request.getParameter("status");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserIds(userIds);
			userManagementPojo.setCustid(custdetails.getCustId());
			userManagementPojo.setStatus(status);
			userManagementPojo.setLog_audit_session(log_audit_session);
			userManagementPojo.setRemarks(request.getParameter("remarks"));
			userManagementPojo.setAuserid(request.getParameter("auserid"));
			
			String result =  new UserManagementBD().blockUserByStatus(userManagementPojo,custdetails);
				
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
				
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUserByStatus Ending");
		return null;
	}
	
	public ActionForward validateusername(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String username = request.getParameter("username");
			
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
		
			userManagementPojo.setUname(username);
			
			
			String result =  new UserManagementDaoImpl().validateusername(userManagementPojo,custdetails);
				
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
				
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUser Ending");
		return null;
	}
	
	
	public ActionForward blockUserStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUserStatus Starting");
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			String teachId[] = request.getParameter("teachId").split(",");
			String status = request.getParameter("status");
		
			UserManagementPojo  userManagementPojo = new UserManagementPojo();
			userManagementPojo.setUserIds(teachId);
			userManagementPojo.setStatus(status);
			userManagementPojo.setLog_audit_session(log_audit_session);
			userManagementPojo.setRemarks(request.getParameter("remarks"));
			
			
			String result =  new UserManagementBD().blockUserStatus(userManagementPojo,custdetails);
				
			JSONObject object = new JSONObject();
			object.put("status", result);
			response.getWriter().print(object);
				
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: blockUserStatus Ending");
		return null;
	}
	
	
	
	public ActionForward userListSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: userlistsearch Starting");
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_PASSWORDMAINTAINANCE);

			List<UserRecordVO> userRecords = new ArrayList<UserRecordVO>();
			
			UserManagementPojo userManagementPojo = new UserManagementPojo();

			
			userManagementPojo.setType(request.getParameter("type"));
			userManagementPojo.setSearchtext(request.getParameter("searchText"));
			userManagementPojo.setStatus(request.getParameter("status"));
			
			
			if(userManagementPojo.getType().equalsIgnoreCase("all")){
				userManagementPojo.setType("%");
			}
			
			System.out.println("000000000"+userManagementPojo.getStatus());
			userRecords = new UserManagementBD().userListSearch(userManagementPojo,userLoggingsVo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userRecords", userRecords);
			response.getWriter().print(jsonObject);
	

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserManagementAction: userlistsearch Ending");

		return null;

	}
}
