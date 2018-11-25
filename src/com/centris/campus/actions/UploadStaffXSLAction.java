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
import com.centris.campus.daoImpl.UploadStaffXLSDaoImpl;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.UploadStaffXSLBD;
import com.centris.campus.forms.UploadStaffXSLForm;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStaffXSLUtility;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadStaffXSLAction extends DispatchAction {

	private static Logger logger = Logger
			.getLogger(UploadStaffXSLAction.class);


	 
	public ActionForward insertStaffXSL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in insertStaffXSL : insertStageXSL : Starting");
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward=null;
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_STAFF);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_STAFF);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_STAFF_UPLOADSTAFFEXCELDATAFILE);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			

			UploadStaffXLSDaoImpl daoImpl = new UploadStaffXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkStaffCountBeforeInsert(custdetails);

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

			String username = userDetailVO.getUserId();

			UploadStaffXSLForm uploadStaffXSLForm = (UploadStaffXSLForm) form;
			

			FormFile file = uploadStaffXSLForm.getFormFile();

			String filePath = request.getRealPath("/");
			
			
			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				/*if(fileURL.length()<102400){*/
				if((((fileURL.length() / 1024) * 100) / 100) <102){
			    
			     String extension= FilenameUtils.getExtension(fileName); //extension
					
				Map<String, Object> staffMap = new UploadStaffXSLUtility().getExcelData(fileURL,file,extension);
                
                if(staffMap.size()>0){
                	List<UploadStaffXlsPOJO>alList = (List<UploadStaffXlsPOJO>) staffMap.get("List");
                    beforeInsert = alList.size();
                }else{
                	beforeInsert=0;
                	request.setAttribute("errorMessage","File is Corrupted or Empty.");
        			forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
        			return mapping.findForward(forward);
                }
				
				//System.out.println("after Reading the data in Excel: size of records: "+alList.size());
				
			/*	for (UploadStaffXlsPOJO UploadStaffXlsPOJO : alList) {
					
					System.out.println("name;:::"+UploadStaffXlsPOJO.getFirstName());
					
				}*/
				
				//System.out.println("beforeInsert in excel file::: "+beforeInsert);

				UploadStaffXSLBD staffXSLBD = new UploadStaffXSLBD();

				Set<UploadStaffXlsVO> staffXLSList = new HashSet<UploadStaffXlsVO>();
				
				
				String demo = (String) staffMap.get("Error");
				/*System.out.println("demo: "+demo);*/
				
				
				staffXLSList = staffXSLBD.insertStaffXSL((List<UploadStaffXlsPOJO>) staffMap.get("List"),username, demo,log_audit_session,custdetails);
				
				notInsertCount = staffXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				/*System.out.println("notInsertCount:::"+notInsertCount);
				System.out.println("<<-------- Returned Back to UploadStaffExcelFile Action Class -------->>");*/

				if (staffXLSList.size() != 0) {

					request.setAttribute("failedStaffList", staffXLSList);

					//request.setAttribute("failedStaffList",	request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					
					
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;

				} else {
					//successInsert = beforeInsert - notInsertCount;
					/*System.out.println("Total SuccessInsert= "+successInsert);*/
					request.setAttribute("successmessagediv", +successInsert+ ":Stage Rocords Registered SuccessFully");
					AllTeacherDetailsVo obj = new AllTeacherDetailsVo();
		        	obj.setLocid("%%");
					obj.setDeptid("%%");
					obj.setDesgId("%%");
					obj.setStatus("Y");
					ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
					list = new TeacherRegistrationBD().getAllTeacherDetails(custdetails,obj);
					request.setAttribute("allTeacherDetailsList", list);
					
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
				}
			 }
			 else {
				 request.setAttribute("errorMessage","file size exceeded.It should be less than 100KB.");
				 forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
			 }
			} else {
				forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
			}

		} catch (Exception e) {
			request.setAttribute("errorMessage","File is Corrupted or Empty.");
			forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);
		}

		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in insertStaffXSL : insertStageXSL : Ending");

		return mapping.findForward(forward);
	}

	
	  public ActionForward downloadStaffFileFormat(ActionMapping mapping, ActionForm
	  form,HttpServletRequest request, HttpServletResponse response) throws
	  Exception {
	  
	  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
	  Date()) + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date()) +
	  " Control in UploadStaffXSLAction : downloadStaffFileFormat : Starting");
	  try {
	  
	  String filePath = request.getRealPath("/") +"FIles/StaffFileUpload/StaffExcelFileFormat.xls"; 
	
	  
	  /*System.out.println("FILEPATH:::"+filePath);*/
	  
	  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
	  FileInputStream(filePath); HttpSession ses = request.getSession();
	  response.setContentType("application/vnd.ms-excel");
	  response.addHeader("content-disposition", "attachment; filename=" +
	  "StaffExcelFileFormat.xls");
	  
	  int octet; while ((octet = in.read()) != -1) out.write(octet);
	  
	  in.close(); out.close();
	  
	  } catch (Exception e) {
		  e.printStackTrace(); 
		  logger.error(e.getMessage(), e); 
	  }
	  
	  JLogger.log(0, JDate.getTimeString(new Date()) +
	  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	  + " Control in UploadStaffXSLAction : downloadStaffFileFormat : Ending");
	  
	  return null;
	  
	  }
	 
	  public ActionForward downloadStaffInstructionFormat(ActionMapping mapping, ActionForm
			  form,HttpServletRequest request, HttpServletResponse response) throws
			  Exception {
			  
			  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
			  Date()) + MessageConstants.START_POINT);
			  logger.info(JDate.getTimeString(new Date()) +
			  " Control in UploadStaffXSLAction : downloadStaffInstructionFormat : Starting");
			  
			  try {
			  
			  String filePath = request.getRealPath("/") +"FIles/StaffFileUpload/StaffInstructionFormat.pdf"; 
			  
			  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
			  FileInputStream(filePath); HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename=" +
			  "StaffInstructionFormat.pdf");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  } catch (Exception e) {
				  e.printStackTrace(); 
				  logger.error(e.getMessage(), e); 
			  }
			  
			  JLogger.log(0, JDate.getTimeString(new Date()) +
			  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
			  + " Control in UploadStaffXSLAction : downloadStaffInstructionFormat : Ending");
			  return null;
			  
			  }

}
