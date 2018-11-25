package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.json.JSONArray;

import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.delegate.LibraryDelegate;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.UploadLibraryXSLBD;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.UploadLibraryXSLForm;
import com.centris.campus.forms.UploadStudentXSLForm;
import com.centris.campus.pojo.StockEntryPOJO;
import com.centris.campus.pojo.UploadLibraryXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.LibraryMessageConstants;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadLibraryCategoryXSLUtility;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.StockEntryVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UploadLibraryXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadLibraryXSLAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(UploadLibraryXSLAction.class);

	public ActionForward insertCategoryTypeXSL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXSLAction : insertEmpXSL : Starting");

		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward = null;
		String location = null;
		ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
		String cateid = request.getParameter("categoryid");
		System.out.println("cateid is "+cateid);
		String status=request.getParameter("statusid");
		System.out.println("status is "+status);
	
		
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		try {

			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String username = HelperClass.getCurrentUserID(request);

			System.out.println("username:::" + username);

			UploadLibraryXSLForm uploadLibXSLForm = (UploadLibraryXSLForm) form;

			FormFile file = uploadLibXSLForm.getFormFile();

			String filePath = request.getRealPath("/");

			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				String extension= FilenameUtils.getExtension(fileName); //extension

				Map<String, Object> libraryMap = new UploadLibraryCategoryXSLUtility().getCategoryExcelData(fileURL,file,extension);

				List<UploadLibraryXlsPOJO> alList = (List<UploadLibraryXlsPOJO>) libraryMap.get("List");

				System.out.println("Action class >> size of list after utility class: " + alList.size());
				beforeInsert = alList.size();
				System.out.println("beforeInsert:::" + beforeInsert);
				UploadLibraryXSLBD empXSLBD = new UploadLibraryXSLBD();
				Set<UploadLibraryXlsVO> libXLSList = new HashSet<UploadLibraryXlsVO>();

				String demo = (String) libraryMap.get("Error");

				System.out.println("Going To Delegate");

				libXLSList = empXSLBD.insertLibXSL((List<UploadLibraryXlsPOJO>) libraryMap.get("List"),username,demo,schoolLocation,log_audit_session);
				notInsertCount = libXLSList.size();
				successInsert = beforeInsert - notInsertCount;
				if (libXLSList.size()!=0) {
					request.setAttribute("FailEmployeeList", libXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = LibraryMessageConstants.categorytypeexcelfileupload ;
					System.out.println(forward);
				} else if (demo!= null) {
					request.setAttribute("successmessagediv", demo);
					forward = LibraryMessageConstants.categorytypeexcelfileupload ;

				} else {
					// successInsert = beforeInsert - notInsertCount;
					request.setAttribute("successmessagediv",+successInsert + ":Category type(s) Rocords Registered SuccessFully");
					list= new  LibraryDelegate().getcategorylist(cateid,status, custdetails);
					request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);
					forward = LibraryMessageConstants.categorytypeexcelfileupload ;
				}
			}
		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "File is Corrupted or Empty");
			forward = LibraryMessageConstants.categorytypeexcelfileupload ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : insertEmpXSL : Ending");
		
		
		return mapping.findForward(forward);
	}

	public ActionForward downloadcategorytypefileformat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadcategorytypefileformat : Starting");
		System.out.println("downloadcategorytypefileformat");
		try {
			String filePath = request.getRealPath("/") + "FIles/LibraryFileUpload/CategoryTypeDetails.xlsx";
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "CategoryTypeDetails.xlsx");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadcategorytypefileformat : Ending");

		return null;

	}
	
	public ActionForward downloadsubscriberdetailfileformat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadsubscriberdetailfileformat : Starting");
		System.out.println("downloadcategorytypefileformat");
		try {
			String filePath = request.getRealPath("") + "\\FIles\\LibraryFileUpload\\subscriberdetailexcelfileupload.xls";
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "subscriberdetailexcelfileupload.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadsubscriberdetailfileformat : Ending");

		return null;

	}
	
	public ActionForward insertSubscriberDetailsXSL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXSLAction : insertEmpXSL : Starting");

		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward = null;
		String status=request.getParameter("statusid");
		System.out.println("status is "+status);
		try {
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String username = HelperClass.getCurrentUserID(request);


			UploadLibraryXSLForm uploadLibXSLForm = (UploadLibraryXSLForm) form;

			FormFile file = uploadLibXSLForm.getFormFile();

			String filePath = getServlet().getServletContext().getRealPath("/");

			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				String extension= FilenameUtils.getExtension(fileName); //extension

				Map<String, Object> libraryMap = new UploadLibraryCategoryXSLUtility().getSubscriberDetailsExcelData(fileURL,file,extension);

				
				
				if(libraryMap.size()>0){
					List<UploadLibraryXlsPOJO> alList = (List<UploadLibraryXlsPOJO>) libraryMap.get("List");
					beforeInsert = alList.size();
                }else{
                	beforeInsert=0;
                	request.setAttribute("errorMessage","File is Corrupted or Empty.");
        			forward=LibraryMessageConstants.subscriberdetailexcelfileupload;
        			return mapping.findForward(forward);
                }
				
				UploadLibraryXSLBD empXSLBD = new UploadLibraryXSLBD();
				Set<UploadLibraryXlsVO> libXLSList = new HashSet<UploadLibraryXlsVO>();

				String demo = (String) libraryMap.get("Error");

				String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");

				libXLSList = empXSLBD.insertsubscriberDetailXSL((List<UploadLibraryXlsPOJO>) libraryMap.get("List"),username,demo,
						schoolLocation,log_audit_session,custdetails);
				
				
				notInsertCount = libXLSList.size();
				successInsert = beforeInsert - notInsertCount;
				if (libXLSList.size()!=0) {
					request.setAttribute("FailEmployeeList", libXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = LibraryMessageConstants.subscriberdetailexcelfileupload ;
					System.out.println(forward);
				} else if (demo!= null) {
					request.setAttribute("successmessagediv", demo);
					forward = LibraryMessageConstants.subscriberdetailexcelfileupload ;

				} else {
					// successInsert = beforeInsert - notInsertCount;
					request.setAttribute("successmessagediv",+successInsert + ":Subscriber Detail(s) Rocords Registered SuccessFully");
					forward = LibraryMessageConstants.subscriberdetailexcelfileupload ;
				}
			}
			else {
				forward = LibraryMessageConstants.subscriberdetailexcelfileupload;
			}
		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "File is Corrupted or Empty");
			forward = LibraryMessageConstants.subscriberdetailexcelfileupload ;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadSubscriberDetalXSLAction : insertSubscriberDetalXSL : Ending");
		
		return mapping.findForward(forward);
	}
	
	public ActionForward downloadStockEntryfileformat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadStockEntryfileformat : Starting");
		System.out.println("downloadcategorytypefileformat");
		try {
			String filePath = request.getRealPath("") + "\\FIles\\LibraryFileUpload\\StockEntryDetail.xls";
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "StockEntryDetail.xls");
			int octet;
			while ((octet = in.read()) != -1)
				out.write(octet);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadLibraryCategoryXSLUtility : downloadStockEntryfileformat : Ending");
		return null;
	}
	
	
public ActionForward insertStockEntryXSL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	
	request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_LIBRARY);
	request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_LIBRARY);
	request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
			LeftMenusHighlightMessageConstant.MODULE_LIBRARY_STOCKENTRY);
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXSLAction : insertEmpXSL : Starting");
	UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	int notInsertCount = 0;
	int beforeInsert = 0;
	int successInsert = 0;
	String fileName = null;
	String forward = null;
	String location = null;
	ArrayList<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
	String cateid = request.getParameter("categoryid");
	System.out.println("cateid is "+cateid);
	String status=request.getParameter("statusid");
	System.out.println("status is "+status);
	try {
		System.out.println("..........insertStockEntryXSL Action Is Working..........");

		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		String username = HelperClass.getCurrentUserID(request);

		System.out.println("username:::" + username);

		UploadLibraryXSLForm uploadLibXSLForm = (UploadLibraryXSLForm) form;

		FormFile file = uploadLibXSLForm.getFormFile();

		String filePath = getServlet().getServletContext().getRealPath("/");

		if (file != null) {

			fileName = file.getFileName();

			File fileURL = new File(filePath, fileName);
			request.setAttribute("fileAttribute", fileName);
			String extension= FilenameUtils.getExtension(fileName); //extension
			
			Map<String, Object> libraryMap=new UploadLibraryCategoryXSLUtility().getStockEntryExcelData(fileURL,file,extension);
			
			
			if(libraryMap.size()>0){
				List<StockEntryPOJO> alList = (List<StockEntryPOJO>) libraryMap.get("List");
                beforeInsert = alList.size();
            }else{
            	beforeInsert=0;
            	request.setAttribute("errorMessage", "File is Corrupted or Empty");
        		forward = LibraryMessageConstants.STOCK_ENTRY_EXCEL;
        		return mapping.findForward(forward);
            }
			 
			UploadLibraryXSLBD empXSLBD = new UploadLibraryXSLBD();
			Set<StockEntryVo> libXLSList = new HashSet<StockEntryVo>();

			String demo = (String) libraryMap.get("Error");

			String academic_year = (String) request.getSession(false).getAttribute("current_academicYear");

			//libXLSList = empXSLBD.insertLibXSL((List<UploadLibraryXlsPOJO>) libraryMap.get("List"),username,demo,schoolLocation);
			libXLSList = empXSLBD.insertStockEntryXSL((List<StockEntryPOJO>) libraryMap.get("List"),username,demo,schoolLocation,
					log_audit_session,custdetails,academic_year);
			notInsertCount = libXLSList.size();
			successInsert = beforeInsert - notInsertCount;
			if (libXLSList.size()!=0) {
				request.setAttribute("FailEmployeeList", libXLSList);
				request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
						+ ": Duplicate or Invalid record(s) found.");
				forward = LibraryMessageConstants.STOCK_ENTRY_EXCEL ;
				System.out.println("FORWARD "+forward);
			} else if (demo!= null) {
				request.setAttribute("successmessagediv", demo);
				forward = LibraryMessageConstants.STOCK_ENTRY_EXCEL ;

			} else {
				// successInsert = beforeInsert - notInsertCount;
				request.setAttribute("successmessagediv",+successInsert + ":Stock Entry(s) Rocords Enterd SuccessFully");
				list= new  LibraryDelegate().getcategorylist(cateid,status, custdetails);
				request.setAttribute(MessageConstants.STUDENTDETAILSLIST, list);
				forward = LibraryMessageConstants.STOCK_ENTRY_EXCEL ;
			}
		}
	} catch (Exception e) {
		System.out.println("exception Block");
		request.setAttribute("errorMessage", "File is Corrupted or Empty");
		forward = LibraryMessageConstants.categorytypeexcelfileupload ;
		e.printStackTrace();
		logger.error(e.getMessage(), e);
		return mapping.findForward(forward);
	}
	JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : insertEmpXSL : Ending");


	return mapping.findForward(LibraryMessageConstants.STOCK_ENTRY_EXCEL);
}
}
