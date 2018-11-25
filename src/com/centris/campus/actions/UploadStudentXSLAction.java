package com.centris.campus.actions;
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
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.SettingsXLUploadBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.UploadStudentXSLForm;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SettingsFileUploadUtil;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.studentExcelUploadVo;

public class UploadStudentXSLAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(UploadStudentXSLAction.class);

	public ActionForward insertStudentXSL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXSLAction : insertStudentXSL : Starting");

		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward = null;
		String location = null;
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_STUDENT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_STUDENT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STUDENT_UPLOADSTUDENTEXCELDATAFILE);
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			

			UploadStudentXLSDaoImpl daoImpl = new UploadStudentXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkEmpCountBeforeInsert(userLoggingsVo);

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String schoolLocation = (String) request.getSession(false).getAttribute("current_schoolLocation");
			String username = userDetailVO.getUserId();

			UploadStudentXSLForm uploadEmpXSLForm = (UploadStudentXSLForm) form;

			FormFile file = uploadEmpXSLForm.getFormFile();
			
			String filePath = request.getRealPath("/");

			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				

				/*if(fileURL.length()<102400){*/
				if((((fileURL.length() / 1024) * 100) / 100) <102){
				String extension= FilenameUtils.getExtension(fileName); //extension
					
				Map<String, Object> employeeMap = new UploadStudentXSLUtility().getExcelData(fileURL,file,extension);

				if(employeeMap.size()>0){
					List<UploadStudentXlsPOJO> alList = (List<UploadStudentXlsPOJO>) employeeMap.get("List");
                    beforeInsert = alList.size();
                }else{
                	beforeInsert=0;
                	request.setAttribute("errorMessage","File is Corrupted or Empty.");
        			forward=MessageConstants.STUDENT_EXCEL_UPLOAD;
        			return mapping.findForward(forward);
                }

				UploadStudentXSLBD empXSLBD = new UploadStudentXSLBD();

				Set<UploadStudentXlsVO> empXLSList = new HashSet<UploadStudentXlsVO>();

				String demo = (String) employeeMap.get("Error");

				empXLSList = empXSLBD.insertEmpXSL((List<UploadStudentXlsPOJO>) employeeMap.get("List"), username, demo,
						schoolLocation,log_audit_session,userLoggingsVo,userLoggingsVo.getNoOfStudent());

				notInsertCount = empXLSList.size();
				successInsert = beforeInsert - notInsertCount;

				// new code

				if (empXLSList.size() != 0) {
					request.setAttribute("FailEmployeeList", empXLSList);
					// request.setAttribute("failedStaffList",
					// request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

				} else {
					// successInsert = beforeInsert - notInsertCount;
					request.setAttribute("successmessagediv",
							+successInsert + ":Student(s) Rocords Registered SuccessFully");

					String currentaccyear = request.getSession(false).getAttribute("current_academicYear").toString();

					if (schoolLocation.equalsIgnoreCase("all")) {
						schoolLocation = "%%";
					}

					String searchTerm = "%%";
					List<StudentRegistrationVo> List = null;
					List = new StudentRegistrationDelegate().getStudentDetailsexcel(searchTerm, schoolLocation,
							currentaccyear,userLoggingsVo);
					request.setAttribute(MessageConstants.STUDENTDETAILSLIST, List);
					forward = MessageConstants.STUDENT_EXCEL_UPLOAD;
				}
			 }
			else {
				 request.setAttribute("errorMessage","file size exceeded.It should be less than 100KB.");
				forward = MessageConstants.STUDENT_EXCEL_UPLOAD;
			  }
			} else {
				forward = MessageConstants.STUDENT_EXCEL_UPLOAD;
			}

		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage", "File is Corrupted or Empty");
			forward = MessageConstants.STUDENT_EXCEL_UPLOAD;

			e.printStackTrace();

			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : insertStudentXSL : Ending");
		return mapping.findForward(forward);

	}

	public ActionForward downloadfileformat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadfileformat : Starting");
		System.out.println("downloadfileformat");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String filePath=null;
			if(userLoggingsVo.getSubtype().equalsIgnoreCase("e-campS")){
				 filePath = request.getRealPath("/")+ "FIles/StudentRegistrationFileUpload/StudentRegistrationFormate.xls";
			}else if(userLoggingsVo.getSubtype().equalsIgnoreCase("e-campB")){
				 filePath = request.getRealPath("/")+ "FIles/StudentRegistrationFileUpload/StudentRegistrationFormateB.xls";
			}
			
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "StudentRegistrationFormate.xls");
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
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadfileformat : Ending");
		return null;
	}
	
	public ActionForward downloadstudentInstructionfileformat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadstudentInstructionfileformat : Starting");
		System.out.println("downloadstudentInstructionfileformat");
		try {
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String filePath=null;
			
			
			if(userLoggingsVo.getSubtype().equalsIgnoreCase("e-campS")){
				filePath = request.getRealPath("/")+ "FIles/StudentRegistrationFileUpload/StudentRegistrationInstructionsFormate.pdf";
			}else if(userLoggingsVo.getSubtype().equalsIgnoreCase("e-campB")){
				filePath = request.getRealPath("/")+ "FIles/StudentRegistrationFileUpload/StudentRegistrationInstructionsFormate.pdf";
			}
			
			System.out.println("FILEPATH:::" + filePath);
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(filePath);
			HttpSession ses = request.getSession();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=" + "StudentRegistrationInstructionsFormate.pdf");
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
		logger.info(
				JDate.getTimeString(new Date()) + " Control in UploadStudentXSLAction : downloadstudentInstructionfileformat : Ending");
		return null;
	}
	public ActionForward excelUploadForStudentsMarks(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : excelUploadForStudentsMarks Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXCELUPLOAD_SUBJECTWISE);
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
	
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(userLoggingsVo);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(userLoggingsVo);
			request.setAttribute("locationList", locationList);
			request.setAttribute("AccYearList", accYearList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in ExamTimeTableAction : excelUploadForStudentsMarks Ending");
		
		return mapping.findForward("ExcelUploadforstudentMarks");

	}
	public ActionForward uploadStudentExamDetails_byExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in settingXLUploadAction : uploadStudentExamDetails_byExcel Starting");
		String fileName = null;
		int beforeInsert = 0;
		int notInsertCount = 0;
		int successInsert =0;
		String forward = null;
		UserDetailVO user = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_EXAM);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_EXAM);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_EXAM_EXCELUPLOAD_SUBJECTWISE);
				
			UploadStudentXSLForm lf =(UploadStudentXSLForm) form;
			FormFile file = lf.getFormFile();
			String filePath = request.getRealPath("/");
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
				if (file != null) {
					fileName = file.getFileName();
					File fileURL = new File(filePath, fileName);
					request.setAttribute("fileAttribute", fileName);
					Map<String, Object> locationMap = new SettingsFileUploadUtil().StudentExamExcelUpload(fileURL,file);

					List<StudentExcelUploadPojo> alList = (List<StudentExcelUploadPojo>) locationMap.get("List");

				
					beforeInsert = alList.size();
					
					Set<studentExcelUploadVo> locationXLSList = new HashSet<studentExcelUploadVo>();
					
					String demo = (String) locationMap.get("Error");
					 
				locationXLSList = new SettingsXLUploadBD().saveStudentExam((List<StudentExcelUploadPojo>) locationMap.get("List"),log_audit_session,custdetails,user);
			 
				notInsertCount = locationXLSList.size();
				successInsert = beforeInsert - notInsertCount;
				
				if (locationXLSList.size()!=0) {
					
					request.setAttribute("OverRideVisibility","visible");
					request.setAttribute("FailedExamUploadList", locationXLSList);
					request.setAttribute("errorMessage", successInsert + ": record(s) uploaded, " + notInsertCount
							+ ": Duplicate or Invalid record(s) found.");
					request.setAttribute("locationList", locationList);
					request.setAttribute("AccYearList", accYearList);
					forward = "ExcelUploadforstudentMarks" ;
				}
				 else {
					 request.setAttribute("locationList", locationList);
						request.setAttribute("AccYearList", accYearList);
						System.out.println("I m Success");
						// successInsert = beforeInsert - notInsertCount;
						request.setAttribute("successmessagediv",+successInsert + ":Student Exam detail(s) Uploaded SuccessFully");
					}
			}else{
				request.setAttribute("locationList", locationList);
				request.setAttribute("AccYearList", accYearList);
				request.setAttribute("errorMessage", "Empty or Invalid Excel sheet");
				forward = "ExcelUploadforstudentMarks";
				
			}
				
			forward = "ExcelUploadforstudentMarks";
		} catch (Exception e) {
			 ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears(custdetails);
			 ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList(custdetails);
			 request.setAttribute("locationList", locationList);
			 request.setAttribute("AccYearList", accYearList);
			 System.out.println("exception Block");
			 request.setAttribute("errorMessage", "File is Corrupted or Empty");
			 forward = "ExcelUploadforstudentMarks";
			
			/*logger.error(e.getMessage(), e);
			e.printStackTrace();*/
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in settingXLUploadAction : uploadStudentExamDetails_byExcel Ending");

		return mapping.findForward(forward);
	}
	
}
