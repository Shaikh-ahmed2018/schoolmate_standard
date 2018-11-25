package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SettingsXLUploadBD;
import com.centris.campus.forms.SettingExcelUploadForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SettingsFileUploadPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.SettingsXLUploadServiceImpl;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SettingsFileUploadUtil;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.SettingsFileUploadVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
/**
 * @author Vedavathi
 *
 */
public class SettingXLUploadAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(SettingXLUploadAction.class);

	
//-- stream list
	public ActionForward StreamXLupload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLupload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_STREAM);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLupload Ending");

		return mapping.findForward("StreamXLupload");
	}
	
	
	public ActionForward StreamXLFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_STREAM);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_stream.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_stream.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLFormat Ending");

		return null;
	}
	
	
	public ActionForward StreamXLSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLSampleData Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_STREAM);

			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_stream.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_stream.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : StreamXLSampleData Ending");
		return null;
	}

	
	
	public ActionForward insertStreamXLFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingXLUploadAction : insertStreamXLFormat Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD StreamBD = new SettingsXLUploadBD();
		Set<StreamDetailsVO> StreamXLSList = new HashSet<StreamDetailsVO>();
	try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_STREAM);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String userId = user.getUserId();
			
			
			if (file != null) {
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				

				Map<String, Object> StreamMap = new SettingsFileUploadUtil().StreamSetting(fileURL,file,extension);

				List<StreamDetailsPojo> alList = (List<StreamDetailsPojo>) StreamMap.get("List");
			
				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				/*System.out.println("beforeInsert:::" + beforeInsert);*/
				String demo = (String) StreamMap.get("Error");
				//System.out.println("Going To Delegate");
				
				StreamXLSList = StreamBD.insertStreamXSL((List<StreamDetailsPojo>) StreamMap.get("List"),userId,log_audit_session,userLoggingsVo,currentLocId);
				//System.out.println("libXLSList::::::::::::::::::"+StreamXLSList.size());
				/*System.out.println("libXLSList::::::::::::::::::"+StreamXLSList.size());*/
				notInsertCount = StreamXLSList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (StreamXLSList.size()!=0) { 
					request.setAttribute("FailedStreamList", StreamXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "StreamXLupload" ;
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "StreamXLupload";
					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Stream Detail(s) Uploaded SuccessFully1111");
						//System.out.println("successssssssssss");
						request.setAttribute("successmessagediv",+successInsert + ":Stream Detail(s) Uploaded SuccessFully");
						/*System.out.println("successssssssssss");*/
						response.sendRedirect("menuslist.html?method=streamList");
						//forward = "StreamXLupload";
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
					forward = "StreamXLupload" ;
				}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "StreamXLupload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + "Control in SettingXLUploadAction : insertStreamXLFormat Ending");
		return mapping.findForward(forward);
	}

	
	
//----------- class excel upload
	public ActionForward ClassXLupload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLupload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CLASS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLupload Ending");

		return mapping.findForward("ClassXLupload");
	}
	

	public ActionForward ClassXLFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLFormat Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CLASS);

			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_class.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_class.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLFormat Ending");

		return null;
	}
	
	
	public ActionForward ClassXLSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLSampleData Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CLASS);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_class.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_class.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ClassXLSampleData Ending");
		return null;
	}

	
	
	
	public ActionForward insertClassXLFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingXLUploadAction : insertClassXLFormat Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
		Set<classVo> ClassXLSList = new HashSet<classVo>();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CLASS);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String userId = user.getUserId();
			
			//System.out.println("userId "+userId);
	
			if (file != null) {
				
				/*String extension= FilenameUtils.getExtension(fileName);*/
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				
				String basename = FilenameUtils.getBaseName(fileName);
				String extension= FilenameUtils.getExtension(fileName);
				//System.out.println("extension"+extension);

				Map<String, Object> classMap = new SettingsFileUploadUtil().ClassSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) classMap.get("List");
			
				if(alList != null){
				beforeInsert= alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) classMap.get("Error");
				//System.out.println("Going To Delegate");
				
				ClassXLSList = ClassBD.insertClassXSL((List<ClassPojo>) classMap.get("List"),userId,log_audit_session,userLoggingsVo,currentLocId);
				//System.out.println("libXLSList::::::::::::::::::"+ClassXLSList.size());
				notInsertCount = ClassXLSList.size();
				successInsert = beforeInsert - notInsertCount;
				
				 if (ClassXLSList.size()!=0) {
					request.setAttribute("FailedClassList", ClassXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "ClassXLupload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "ClassXLupload";

					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Class Detail(s) Uploaded SuccessFully");
						response.sendRedirect("menuslist.html?method=classList");
					}
				}
				else{
					request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
					forward = "ClassXLupload" ;
				}
		} 
		}catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "ClassXLupload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : insertClassXLFormat Ending");
		return mapping.findForward(forward);
	}
	
	
//---------- DIVISION
	public ActionForward DivisionExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_DIVISION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelUpload Ending");

		return mapping.findForward("DivisionExcelUpload");
	}
	
	public ActionForward DivisionExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_DIVISION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_division.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_division.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelFormat Ending");

		return null;
	}
	
	public ActionForward DivisionExcelSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelSampleData Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_DIVISION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_division.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_division.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : DivisionExcelSampleData Ending");
		return null;
	}

	public ActionForward insertDivisionExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingXLUploadAction : insertDivisionExcelFormat Starting");
		
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD ExcelUploadBD = new SettingsXLUploadBD();
		Set<classVo> DivisionXLSList = new HashSet<classVo>();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_DIVISION);
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String userId = user.getUserId();
			
			if (file != null) {
				
				fileName = file.getFileName();
				String extension= FilenameUtils.getExtension(fileName);
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> DivisionMap = new SettingsFileUploadUtil().DivisionSetting(fileURL,file,extension,custdetails);

				List<ClassPojo> alList = (List<ClassPojo>) DivisionMap.get("List");
				
				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) DivisionMap.get("Error");
				//System.out.println("Going To Delegate");
				
				DivisionXLSList = ExcelUploadBD.insertDivisionXSL((List<ClassPojo>) DivisionMap.get("List"),userId,log_audit_session,currentLocId,custdetails);
				//System.out.println("libXLSList::::::::::::::::::"+DivisionXLSList.size());
				notInsertCount = DivisionXLSList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (DivisionXLSList.size()!=0) {
					request.setAttribute("FailedDivisionList", DivisionXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "DivisionExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "DivisionExcelUpload";
					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Division Detail(s) Rocords Registered SuccessFully");
						response.sendRedirect("menuslist.html?method=sectionList");
					}
				}
				
				else
				{
					request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
					forward = "DivisionExcelUpload";
				}
			}
			else
			{
				request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
				forward = "DivisionExcelUpload";
			}
		}
			catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "DivisionExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
			}
			JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
			logger.info(
					JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : insertDivisionExcelFormat Ending");
			return mapping.findForward(forward);
		}
//-------------- Occupation
	public ActionForward OccupationExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : OccupationExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_OCCUPATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : OccupationExcelUpload Ending");

		return mapping.findForward("OccupationExcelUpload");
	}
	
	public ActionForward OccupationExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : OccupationExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_OCCUPATION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_occupation.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_occupation.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : OccupationExcelFormat Ending");

		return null;
	}
	
	public ActionForward OccupationExcelSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : OccupationExcelSampleData Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_OCCUPATION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_occupation.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_occupation.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : OccupationExcelSampleData Ending");
		return null;
	}
	
	public ActionForward insertOccupationXLFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : insertOccupationXLFormat Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_OCCUPATION);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String userId = user.getUserId();
			
			
			if (file != null) {
				
				fileName = file.getFileName();
				
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> OccupationMap = new SettingsFileUploadUtil().OccupationSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) OccupationMap.get("List");

				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				
				SettingsXLUploadBD occupationBD = new SettingsXLUploadBD();
				
				Set<classVo> OccupationList = new HashSet<classVo>();
				
				String demo = (String) OccupationMap.get("Error");
				//System.out.println("Going To Delegate");
				
				OccupationList = occupationBD.insertOccupationXSL((List<ClassPojo>) OccupationMap.get("List"),userId,log_audit_session,userLoggingsVo,currentLocId);
				//System.out.println("libXLSList::::::::::::::::::"+OccupationList.size());
				notInsertCount = OccupationList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (OccupationList.size()!=0) {
					request.setAttribute("FailedOccupationList", OccupationList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "OccupationExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "OccupationExcelUpload";

					} else {
						request.setAttribute("successmessagediv",+successInsert + ":Occupation Detail(s) Uploaded SuccessFully");
						response.sendRedirect("menuslist.html?method=occupationDetails");
					}
				}
				else{
						request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
						forward = "OccupationExcelUpload";
				}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "OccupationExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : insertOccupationXLFormat Ending");

		return mapping.findForward(forward);
	}
//------------ Religion	
	
	public ActionForward ReligionExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ReligionExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_RELIGION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ReligionExcelUpload Ending");

		return mapping.findForward("ReligionExcelUpload");
	}
	
	public ActionForward ReligionExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : ReligionExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_RELIGION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_religion.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_religion.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : ReligionExcelFormat Ending");

		return null;
	}
	
	public ActionForward ReligionExcelSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : ReligionExcelSampleData Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_RELIGION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_religion.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_religion.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : ReligionExcelSampleData Ending");
		return null;
	}

	public ActionForward ReligionExcelInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : ReligionExcelInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_RELIGION);

			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String userId = user.getUserId();
			
			
			if (file != null) {
				fileName = file.getFileName();
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> ReligionMap = new SettingsFileUploadUtil().ReligionSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) ReligionMap.get("List");

				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				
				//Set<classVo> OccupationList = new HashSet<classVo>();
				
				String demo = (String) ReligionMap.get("Error");
				//System.out.println("Going To Delegate");
				
				Set<classVo> ReligionList =  new SettingsXLUploadBD().insertReligionXSL((List<ClassPojo>) ReligionMap.get("List"),userId,log_audit_session,userLoggingsVo);
				//System.out.println("OccupationList::::::::::::::::::"+ReligionList.size());
				notInsertCount = ReligionList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (ReligionList.size()!=0) {
					request.setAttribute("FailedReligionList", ReligionList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "ReligionExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "ReligionExcelUpload";

					} else {
						request.setAttribute("successmessagediv",+successInsert + ":Religion Detail(s) Rocords Registered SuccessFully");
						response.sendRedirect("menuslist.html?method=religionDetails");
					}
				}else{
						request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
						forward = "ReligionExcelUpload";
				}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "ReligionExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : ReligionExcelInsert Ending");

		return mapping.findForward(forward);
	}
//-------------- caste
	public ActionForward CasteExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : CasteExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : CasteExcelUpload Ending");

		return mapping.findForward("CasteExcelUpload");
	}
	
	public ActionForward CasteExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTE);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_caste.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_caste.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteExcelFormat Ending");

		return null;
	}
	
	public ActionForward CasteExcelSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteExcelSampleData Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTE);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_caste.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_caste.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteExcelSampleData Ending");
		return null;
	}
	
	public ActionForward CasteExcelInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : CasteExcelInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		try {

			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTE);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			
			String userId = user.getUserId();
			
			if (file != null) {
				fileName = file.getFileName();
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> CasteMap = new SettingsFileUploadUtil().CasteSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) CasteMap.get("List");

				if( alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				
				//Set<classVo> OccupationList = new HashSet<classVo>();
				
				String demo = (String) CasteMap.get("Error");
				//System.out.println("Going To Delegate");
				
				Set<classVo> CasteList =  new SettingsXLUploadBD().insertCasteXSL((List<ClassPojo>) CasteMap.get("List"),userId,log_audit_session,userLoggingsVo);
				//System.out.println("CasteList::::::::::::::::::"+CasteList.size());
				notInsertCount = CasteList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (CasteList.size()!=0) {
					request.setAttribute("FailedCasteList", CasteList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "CasteExcelUpload" ;
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "CasteExcelUpload";

					} else {
						request.setAttribute("successmessagediv",+successInsert + ":Caste Detail(s) Uploaded SuccessFully");
						response.sendRedirect("menuslist.html?method=casteDetails");
					}
				}
				 else {
						request.setAttribute("errorMessage","Empty or Invalid excel sheet");
						forward = "CasteExcelUpload";
					}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "CasteExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteExcelInsert Ending");

		return mapping.findForward(forward);
	
}

//-------------- caste category



	public ActionForward CasteCategoryExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : CasteCategoryExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTECATEGORY);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : CasteCategoryExcelUpload Ending");

		return mapping.findForward("CasteCategoryExcelUpload");
	}
	
	public ActionForward CasteCategoryExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : CasteCategoryExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTECATEGORY);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_castecategory.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_castecategory.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteCategoryExcelFormat Ending");

		return null;
	}
	
	public ActionForward CasteCategorySampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteCategorySampleData Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTECATEGORY);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_castecategory.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_castecategory.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteCategorySampleData Ending");
		return null;
	}

	public ActionForward CasteCategoryInsertData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : CasteCategoryInsertData Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		Set<classVo> cclist = new HashSet<classVo>();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_CASTECATEGORY);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String userId = user.getUserId();
			
			
			if (file != null) {
				
				fileName = file.getFileName();
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> ccmap = new SettingsFileUploadUtil().CasteCategorySetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) ccmap.get("List");
			
				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) ccmap.get("Error");
				//System.out.println("Going To Delegate");
				
				cclist = new SettingsXLUploadServiceImpl().insertCasteCategoryExcel((List<ClassPojo>) ccmap.get("List"),userId,log_audit_session,userLoggingsVo);
				//System.out.println("libXLSList::::::::::::::::::"+cclist.size());
				notInsertCount = cclist.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (cclist.size()!=0) {
					request.setAttribute("FailedCasteCategoryList", cclist);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "CasteCategoryExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "CasteCategoryExcelUpload";

					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Caste Category Detail(s) Uploaded SuccessFully");
						response.sendRedirect("menuslist.html?method=casteCategoryDetails");
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
					forward = "CasteCategoryExcelUpload";
				}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "CasteCategoryExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : CasteCategoryInsertData Ending");
		return mapping.findForward(forward);
	}

//--------specialization
	
	public ActionForward SpecializationExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : SpecializationExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SPECIALIZATION);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : SpecializationExcelUpload Ending");

		return mapping.findForward("SpecializationExcelUpload");
	}
	

	public ActionForward SpecializationExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SpecializationExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SPECIALIZATION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_specialization.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_specialization.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SpecializationExcelFormat Ending");

		return null;
	}
	
	
	public ActionForward SpecializationSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SpecializationSampleData Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SPECIALIZATION);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_specialization.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_specialization.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SpecializationSampleData Ending");
		return null;
	}

	
public ActionForward SpecializationExcelDataInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : SpecializationExcelDataInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
		Set<classVo> specList = new HashSet<classVo>();
		try {
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SPECIALIZATION);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String userId = user.getUserId();
			
			
			if (file != null) {
				fileName = file.getFileName();
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> specMap = new SettingsFileUploadUtil().specSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) specMap.get("List");
			
				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) specMap.get("Error");
				//System.out.println("Going To Delegate");
				
				specList = ClassBD.insertSpecXSL((List<ClassPojo>) specMap.get("List"),userId,log_audit_session,userLoggingsVo,currentLocId);
				//System.out.println("libXLSList::::::::::::::::::"+specList.size());
				notInsertCount = specList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (specList.size()!=0) {
					request.setAttribute("FailedSpecList", specList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "SpecializationExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "SpecializationExcelUpload";

					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Specialization Detail(s) Uploaded SuccessFully");
						forward = MessageConstants.SPECIALIZATION_LIST;
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
					forward = "SpecializationExcelUpload";
				}
			}

		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "SpecializationExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SpecializationExcelDataInsert Ending");
		return mapping.findForward(forward);
	}
//---------holiday list

public ActionForward HolodaylistExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : HolodaylistExcelUpload Starting");
	try {
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_HOLIDAYLIST);

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}

	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : HolodaylistExcelUpload Ending");

	return mapping.findForward("HolidaylistExcelUpload");
}

	
public ActionForward HolidayExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelFormat Starting");
	try {
		
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_HOLIDAYLIST);

		String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_holiday.pdf";
		//System.out.println("FILEPATH:::" + filePath);
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		response.setContentType("application/pdf");
		response.addHeader("content-disposition", "attachment; filename=" + "Instructions_holiday.pdf");
		int octet;
		while ((octet = in.read()) != -1) 
			out.write(octet);
		in.close();
		out.close();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelFormat Ending");

	return null;
}
	
public ActionForward HolidaySampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidaySampleData Starting");
	try {

		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_HOLIDAYLIST);
		
		String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_holiday.xls";
		//System.out.println("FILEPATH:::" + filePath);
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		response.setContentType("application/pdf");
		response.addHeader("content-disposition", "attachment; filename=" + "Template_holiday.xls");
		int octet;
		while ((octet = in.read()) != -1)
			out.write(octet);
		in.close();
		out.close(); 

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidaySampleData Ending");
	return null;
}



public ActionForward HolidayExcelInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in settingXLUploadAction : HolidayExcelInsert Starting");
	String fileName = null;
	int beforeInsert = 0;
	int notInsertCount = 0;
	int successInsert =0;
	String forward = null;
	
	SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
	Set<classVo> holidayList = new HashSet<classVo>();
	try {
		
		UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		
		request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_HOLIDAYLIST);
		
		SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
		FormFile file = lf.getFormFile();
		String filePath = request.getRealPath("/");
		
		UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
		String currentLocId = (String) request.getSession(false).getAttribute("current_schoolLocation");
		String userId = user.getUserId();
		String accyear = user.getAcademicyear();
		
		if (file != null) {
			
			fileName = file.getFileName();
			String extension= FilenameUtils.getExtension(fileName);
			File fileURL = new File(filePath, fileName);
			request.setAttribute("fileAttribute", fileName);

			Map<String, Object> holdiayMap = new SettingsFileUploadUtil().holidaySetting(fileURL,file,extension);

			List<ClassPojo> alList = (List<ClassPojo>) holdiayMap.get("List");
		
			List<ClassPojo> empty = (List<ClassPojo>) holdiayMap.get("empty");
			
			//System.out.println("Emptyyy  "+empty);
		
			if(alList != null){
			beforeInsert = alList.size();
			//System.out.println("beforeInsert:::" + beforeInsert);
			String demo = (String) holdiayMap.get("Error");
			//System.out.println("Going To Delegate");
			
			holidayList = ClassBD.insertHolidayXSL((List<ClassPojo>) holdiayMap.get("List"),userId,accyear,log_audit_session,userLoggingsVo,currentLocId);
			//System.out.println("libXLSList::::::::::::::::::"+holidayList.size());
			notInsertCount = holidayList.size();
			successInsert = beforeInsert - notInsertCount;
		
			if (holidayList.size()!=0) {
				request.setAttribute("FailedHolidayList", holidayList);
				request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
						+ ": Duplicate or Invalid record(s) found.");
				forward = "HolidaylistExcelUpload" ;
				//System.out.println(forward);
			}
			 else if (demo!= null) {
					request.setAttribute("successmessagediv", demo);
					forward = "HolidaylistExcelUpload";

				} else {
					// successInsert = beforeInsert - notInsertCount;
					request.setAttribute("successmessagediv",+successInsert + ":Holiday Detail(s) Uploaded SuccessFully");
					response.sendRedirect("menuslist.html?method=holidaymaster");
				}
			}else{
				request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
				forward = "HolidaylistExcelUpload";
			}
		}
	} catch (Exception e) {
		//System.out.println("exception Block");
		request.setAttribute("errorMessage", "Session Timeout! Try again");
		forward = "HolidaylistExcelUpload" ;
		e.printStackTrace();
		logger.error(e.getMessage(), e);
		return mapping.findForward(forward);
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(
			JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : HolidayExcelInsert Ending");
	return mapping.findForward(forward);
}
public ActionForward downloadClassWiseExcelUploadFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Starting");
	try {
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String locationid = request.getParameter("locationId");
		String accyear = request.getParameter("accyearId");
		String classid = request.getParameter("classId");
		String sectionid = request.getParameter("sectionId");
		String examid = request.getParameter("examid");
		String subjectid = request.getParameter("subjectid");
	
	 	File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;
		ReportMenuVo vo=new ReportMenuVo();
		vo.setLocationId(locationid);
		vo.setAccyearId(accyear);
		vo.setClassId(classid);
		vo.setSectionId(sectionid);
		vo.setExamId(examid);
		vo.setSubjectid(subjectid);
		
		String classname=HelperClass.getClassName(classid, locationid,custdetails);
		String sectionname=HelperClass.getSectionName(classid,sectionid,locationid,custdetails);
		String subjectname=HelperClass.getSubjectName(subjectid,classid,locationid,custdetails);
		String examtypename=HelperClass.getExamTypeName(examid,classid,locationid,custdetails);
		String examtype=HelperClass.getExamType(examid,classid,locationid,custdetails,accyear);

		List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentClassSectionWiseListForReport(vo,custdetails);
		if(stuList.size() > 0) {
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stuList);	
			String sourceFileName=null;
			if(examtype.equalsIgnoreCase("prdxm")){
				sourceFileName = request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.jrxml");
			}
			else{
				sourceFileName = request.getRealPath("Reports/StudentExamMarksUploadExcelFormatHY.jrxml");
			}
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,null, beanColDataSource);
			JRXlsxExporter exporter = new JRXlsxExporter();
			File outputFile = new File(request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,new Boolean(true));
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,new Boolean(true));

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/StudentExamMarksUploadExcelFormat.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename="+classname+"-"+sectionname+"_"+subjectname+"_"+examtypename+".xlsx");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		}else {
			String filePath=null;
			if(examtype.equalsIgnoreCase("prdxm")){
				filePath = request.getRealPath("/")+ "/FIles/SettingXLFileUpload/StudenMarksUploadFormate.xls";
			}else{
				filePath = request.getRealPath("/")+ "/FIles/SettingXLFileUpload/StudenMarksUploadFormateYH.xls";
			}
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition","attachment; filename="+classname+"-"+sectionname+"_"+subjectname+"_"+examtypename+".xlsx");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		}
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Ending");
	return null;
}


public ActionForward downloadClassWiseCoScholasticAreaExcelUploadFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Starting");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	try {

		String locationid = request.getParameter("locationId");
		String accyear = request.getParameter("accyearId");
		String classid = request.getParameter("classId");
		String sectionid = request.getParameter("sectionId");
		String examid = request.getParameter("examid");
		String custid=custdetails.getCustId();
		
	 	File pdfxls = null;
		FileInputStream input = null;
		BufferedInputStream buf = null;
		ServletOutputStream stream = null;
		ReportMenuVo vo=new ReportMenuVo();
		vo.setLocationId(locationid);
		vo.setAccyearId(accyear);
		vo.setClassId(classid);
		vo.setSectionId(sectionid);
		vo.setExamId(examid);
		vo.setCustid(custid);
		
		String classname=HelperClass.getClassName(classid, locationid,custdetails);
		String sectionname=HelperClass.getSectionName(classid,sectionid,locationid,custdetails);
		String examtypename=HelperClass.getExamTypeName(examid,classid,locationid,custdetails);

		List<ReportMenuVo> stuList = new ReportsMenuBD().getStudentClassSectionWiseListForReportByAll(vo,custdetails);
		if(stuList.size() > 0) {
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(stuList);	
			String sourceFileName = request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,null, beanColDataSource);
			JRXlsxExporter exporter = new JRXlsxExporter();
			File outputFile = new File(request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Student Details" };
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheetNames);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,new Boolean(true));
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,new Boolean(true));

			exporter.exportReport();

			pdfxls = new File(request.getRealPath("Reports/StudentExamScholasticAreaUploadExcelFormat.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename="+stuList.get(0).getClassname()+"-"+stuList.get(0).getSectionname()+"_StudentCo-Scholastic AreasUploadExcelFormat.xls");
			response.setContentLength((int) pdfxls.length());
			input = new FileInputStream(pdfxls);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			stream = response.getOutputStream();
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		}else {
			String filePath = request.getRealPath("/")+ "/FIles/SettingXLFileUpload/StudentCo-ScholasticAreasUploadExcelFormat.xls";
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition","attachment; filename="+classname+"-"+sectionname+"_"+examtypename+"_StudentCo-ScholasticAreasUploadExcelFormat.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		}

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : studentExcelUpload_format Ending");
	return null;
}
public ActionForward ExamGradeSetting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in settingXLUploadAction : ExamGradeSetting Starting");
	String fileName = null;
	int beforeInsert = 0;
	int notInsertCount = 0;
	int successInsert =0;
	String forward = null;
	Set<studentExcelUploadVo> GradeList = new HashSet<studentExcelUploadVo>();
	UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
	try {
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXCELUPLOAD_SUBJECTWISE);
		SettingExcelUploadForm lf =(SettingExcelUploadForm) form;
		FormFile file = lf.getFormFile();
		String filePath = request.getRealPath("/");
		String userId = user.getUserId();
		String currentLoc = (String)request.getSession(false).getAttribute("current_schoolLocation");
		
			if (file != null) {
				String extension= FilenameUtils.getExtension(fileName);
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> gradeMap = new SettingsFileUploadUtil().ExamGrades(fileURL,file,extension);

				List<StudentExcelUploadPojo> alList = (List<StudentExcelUploadPojo>) gradeMap.get("List");
				ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
				ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
				if(alList != null){
				
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				//System.out.println("Going To Delegate");
				String demo = (String) gradeMap.get("Error");
				
				GradeList = new SettingsXLUploadBD().SaveGradeSetting((List<StudentExcelUploadPojo>) gradeMap.get("List"),userId,log_audit_session,custdetails,currentLoc);
			
				//System.out.println("libXLSList::::::::::::::::::"+GradeList.size());
				notInsertCount = GradeList.size();
				//System.out.println("notInsertCount "+notInsertCount);
				//System.out.println("beforeInsert "+beforeInsert);
				successInsert = beforeInsert - notInsertCount;
				//System.out.println("successInsert "+successInsert);
				
				
				if (GradeList.size()!=0) {
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					request.setAttribute("FailedGradeList", GradeList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "ExamGradeUpload" ;
					//System.out.println(forward);
				}
				else if (demo!= null) {
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					request.setAttribute("successmessagediv", demo);
					forward = "ExamGradeUpload";

				} else {
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					// successInsert = beforeInsert - notInsertCount;
					request.setAttribute("successmessagediv",+successInsert +  ": Co_Scholastic_Area Details Uploaded SuccessFully");
					forward = "ExamGradeUpload";
				}
				}
				else{
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
					forward = "ExamGradeUpload";
				}
				}
			}
			catch (Exception e) {
				 	ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
					ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					//System.out.println("exception Block");
					request.setAttribute("errorMessage", "Session Timeout! Try again");
				forward = "ExamGradeUpload" ;
				/*e.printStackTrace();
				logger.error(e.getMessage(), e);*/
			return mapping.findForward(forward);
		}
	
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : ExamGradeSetting Ending");
		return mapping.findForward(forward);
	}
	public ActionForward stuMarkInstruction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : stuMarkInstruction Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXCELUPLOAD_SUBJECTWISE);
		
		String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/StudentExamMarksUploadInstruction.pdf";
		//System.out.println("FILEPATH:::" + filePath);
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		HttpSession ses = request.getSession();
		response.setContentType("application/pdf");
		response.addHeader("content-disposition", "attachment; filename=" + "StudentExamMarksUploadInstruction.pdf");
		int octet;
		while ((octet = in.read()) != -1) 
			out.write(octet);
		in.close();
		out.close();

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : stuMarkInstruction Ending");

	return null;
}
	public ActionForward stuGradeInstruction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : stuMarkInstruction Starting");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXCELUPLOAD_SUBJECTWISE);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/StudentGradeExcelUploadInstruction.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "StudentGradeExcelUploadInstruction.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : stuMarkInstruction Ending");
		
		return null;
	}
	
	
	
//--------------- subject excel upload
	
	public ActionForward SubjectExcelUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : SubjectExcelUpload Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SUBJECT);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SettingXLUploadAction : SubjectExcelUpload Ending");

		return mapping.findForward("SubjectExcelUpload");
	}
	
	public ActionForward SubjectExcelFormat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectExcelFormat Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SUBJECT);
			
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Instructions_subject.pdf";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Instructions_subject.pdf");
			int octet;
			while ((octet = in.read()) != -1) 
				out.write(octet);
			in.close();
			out.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectExcelFormat Ending");

		return null;
	}
	
	
	public ActionForward SubjectSampleData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectSampleData Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SUBJECT);
			
			String filePath = request.getRealPath("/") + "/FIles/SettingXLFileUpload/Template_subject.xls";
			//System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "attachment; filename=" + "Template_subject.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close(); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectSampleData Ending");
		return null;
	}

	
 public ActionForward SubjectExcelDataInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : SubjectExcelDataInsert Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		
		SettingsXLUploadBD ClassBD = new SettingsXLUploadBD();
		Set<classVo> subjectList = new HashSet<classVo>();
		try {
			File fileURL = null;
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			request.setAttribute(MessageConstants.MODULE_NAME, MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME, MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_EXCELUPLOADS_SUBJECT);
			
			SettingExcelUploadForm lf = (SettingExcelUploadForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			
			UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String)request.getSession(false).getAttribute("log_audit_session");
			String currentLoc =  (String) request.getSession(false).getAttribute("current_schoolLocation");
			String userId = user.getUserId();
			
			if (file != null) {
				fileName = file.getFileName();
				String basename = FilenameUtils.getBaseName(fileName); //name
				String extension= FilenameUtils.getExtension(fileName); //extension
				fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);

				Map<String, Object> subjectMap = new SettingsFileUploadUtil().SubjectSetting(fileURL,file,extension);

				List<ClassPojo> alList = (List<ClassPojo>) subjectMap.get("List");
			
				if(alList != null){
				beforeInsert = alList.size();
				//System.out.println("beforeInsert:::" + beforeInsert);
				String demo = (String) subjectMap.get("Error");
				//System.out.println("Going To Delegate");
				
				
				subjectList = ClassBD.insertSubjectXSL((List<ClassPojo>) subjectMap.get("List"),userId,log_audit_session,userLoggingsVo,currentLoc);
				//System.out.println("libXLSList::::::::::::::::::"+subjectList.size());
				notInsertCount = subjectList.size();
				successInsert = beforeInsert - notInsertCount;
			
				if (subjectList.size()!=0) {
					request.setAttribute("FailedSubjectList", subjectList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = "SubjectExcelUpload" ;
					//System.out.println(forward);
				}
				 else if (demo!= null) {
						request.setAttribute("successmessagediv", demo);
						forward = "SubjectExcelUpload";

					} else {
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Subject Detail(s) Uploaded SuccessFully");
						Thread.sleep(2000);
						response.sendRedirect("menuslist.html?method=subjectdetails");
						//forward = "SubjectExcelUpload";
					}
				}else{
					request.setAttribute("errorMessage", "Empty or Invalid excel sheet");
					forward = "SubjectExcelUpload";
				}
			}
			
			
		} catch (Exception e) {
			//System.out.println("exception Block");
			request.setAttribute("errorMessage", "Session Timeout! Try again");
			forward = "SubjectExcelUpload" ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : SubjectExcelDataInsert Ending");
		
		
		return mapping.findForward(forward);
	}
	
}



