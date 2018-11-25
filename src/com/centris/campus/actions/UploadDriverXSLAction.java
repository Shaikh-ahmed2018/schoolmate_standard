package com.centris.campus.actions;
import java.io.File;
import java.io.FileInputStream;
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
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.delegate.TransportBD;
import com.centris.campus.delegate.UploadDriverXSLBD;
import com.centris.campus.forms.UploadDriverXSLForm;
import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadDriverXSLUtility;
import com.centris.campus.vo.DriverMsaterListVo;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadDriverXSLAction extends DispatchAction {
	private static Logger logger = Logger
			.getLogger(UploadDriverXSLAction.class);
	
	public ActionForward insertDriverXSL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadDriverXSLAction : insertDriverXSL : Starting");
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward=null;
		try {
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADDRIVEREXCELDATAFILE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");

            String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			//System.out.println("InsertDriverXSL Action Is Working");

			UploadStageXLSDaoImpl daoImpl = new UploadStageXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkStageCountBeforeInsert(userLoggingsVo);

			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");

			String username = userDetailVO.getUserId();

			//System.out.println("username:::" +username);

			UploadDriverXSLForm uploadDriverXSLForm = (UploadDriverXSLForm) form;

			FormFile file = uploadDriverXSLForm.getFormFile();

			String filePath = getServlet().getServletContext().getRealPath("/");

			if (file != null) {
				
				fileName = file.getFileName();
				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				
				/*if(fileURL.length()<102400){*/
				if((((fileURL.length() / 1024) * 100) / 100) <102){
				 
			    String extension= FilenameUtils.getExtension(fileName); //extension
					
				Map<String, Object> driverMap = new UploadDriverXSLUtility().getExcelData(fileURL,file,extension);
				
				 if(driverMap.size()>0){
					 List<UploadDriverXlsPOJO> alList = (List<UploadDriverXlsPOJO>) driverMap.get("List");
	                    beforeInsert = alList.size();
	                }else{
	                	beforeInsert=0;
	                	request.setAttribute("errorMessage","File is Corrupted or Empty.");
	        			forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
	        			return mapping.findForward(forward);
	                }
				
				//System.out.println("size of alList Upload Driver Action class  "+alList.size());
				
				/*for (UploadDriverXlsPOJO uploadDriverXlsPOJO : alList) {
					System.out.println("name;:::"+uploadDriverXlsPOJO.getDriverName());
				}*/
				
				//System.out.println("beforeInsert in excel file::: "+beforeInsert);

				UploadDriverXSLBD driverXSLBD = new UploadDriverXSLBD();

				Set<UploadDriverXlsVO> driverXLSList = new HashSet<UploadDriverXlsVO>();
				
				String demo = (String) driverMap.get("Error");
				String branchName = (String) request.getSession(false).getAttribute("current_schoolLocation");
				
				driverXLSList = driverXSLBD.insertDriverXSL((List<UploadDriverXlsPOJO>) driverMap.get("List"),username,demo,
						log_audit_session,userLoggingsVo,branchName);
				notInsertCount = driverXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				//System.out.println("notInsertCount:::"+notInsertCount);

				if (driverXLSList.size() != 0) {
					request.setAttribute("failedDriverList", driverXLSList);
					request.setAttribute("failedDriverList",request.getAttribute("failedDriverList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.DRIVER_EXCEL_UPLOAD;

				} else {
					//successInsert = beforeInsert - notInsertCount;
					//System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":Driver Rocords Registered SuccessFully");
					
					List<DriverMsaterListVo> driverList = new TransportBD().getdriverList(userLoggingsVo);
					request.setAttribute("driverList", driverList);
					forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
				}
			 }
			else {
				 request.setAttribute("errorMessage","file size exceeded.It should be less than 100KB.");
				 forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
			  }
		   }
			else {
				forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
			}

		} catch (Exception e) {
			request.setAttribute("errorMessage","File is Corrupted or Empty.");
			forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLAction : insertStageXSL : Ending");

		return mapping.findForward(forward);

	}

	
	  public ActionForward downloadDriverFileFormat(ActionMapping mapping, ActionForm
	  form,HttpServletRequest request, HttpServletResponse response) throws
	  Exception {
	  
	  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
	  Date()) + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date()) +
	  " Control in UploadDriverXSLAction : downloadDriverFileFormat : Starting");
	  
	  //System.out.println("downloadfileformat");
	  
	  try {
	    
	  String filePath = request.getRealPath("/") +"FIles/DriverFileUpload/DriverExcelFileFormat.xls"; 
	  
	  //System.out.println("FILEPATH:::"+filePath);
	  
	  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
	  FileInputStream(filePath); HttpSession ses = request.getSession();
	  response.setContentType("application/vnd.ms-excel");
	  response.addHeader("content-disposition", "attachment; filename=" +
	  "DriverExcelFileFormat.xls");
	  
	  int octet; while ((octet = in.read()) != -1) out.write(octet);
	  
	  in.close(); out.close();
	  
	  } catch (Exception e) {
		  e.printStackTrace(); 
		  logger.error(e.getMessage(), e); 
	  }
	  
	  
	  JLogger.log(0, JDate.getTimeString(new Date()) +
	  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	  + " Control in UploadDriverXSLAction : downloadDriverFileFormat : Ending");
	  
	  return null;
	  
	  }
	  public ActionForward insertStockEntryXSL(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadDriverXSLAction : insertDriverXSL : Starting");
			int notInsertCount = 0;
			int beforeInsert = 0;
			int successInsert = 0;
			String fileName = null;
			String forward=null;
			try {
				
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				
				String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
				/*System.out.println("InsertDriverXSL Action Is Working");*/

				UploadStageXLSDaoImpl daoImpl = new UploadStageXLSDaoImpl();

				int countBeforeInsert = daoImpl.checkStageCountBeforeInsert(userLoggingsVo);

				UserDetailVO userDetailVO = (UserDetailVO) request
						.getSession(false).getAttribute("UserDetails");

				String username = userDetailVO.getUserId();

				//System.out.println("username:::" +username);

				UploadDriverXSLForm uploadDriverXSLForm = (UploadDriverXSLForm) form;

				FormFile file = uploadDriverXSLForm.getFormFile();

				String filePath = getServlet().getServletContext().getRealPath("/");

				if (file != null) {
					fileName = file.getFileName();
					File fileURL = new File(filePath, fileName);
					request.setAttribute("fileAttribute", fileName);
					
					String extension= FilenameUtils.getExtension(fileName); //extension
					
					Map<String, Object> driverMap = new UploadDriverXSLUtility().getExcelData(fileURL,file,extension);

					List<UploadDriverXlsPOJO> alList = (List<UploadDriverXlsPOJO>) driverMap.get("List");
					
					/*for (UploadDriverXlsPOJO uploadDriverXlsPOJO : alList) {
						System.out.println("name;:::"+uploadDriverXlsPOJO.getDriverName());
					}*/

					beforeInsert = alList.size();
					
					UploadDriverXSLBD driverXSLBD = new UploadDriverXSLBD();

					Set<UploadDriverXlsVO> driverXLSList = new HashSet<UploadDriverXlsVO>();
					
					String branchName = (String) request.getSession(false).getAttribute("current_schoolLocation");
					
					String demo = (String) driverMap.get("Error");
					 
					driverXLSList = driverXSLBD.insertDriverXSL((List<UploadDriverXlsPOJO>) driverMap.get("List"),username,demo,
							log_audit_session,userLoggingsVo,branchName);
					notInsertCount = driverXLSList.size();
					
					successInsert = beforeInsert - notInsertCount;
					

					if (driverXLSList.size() != 0) {

						request.setAttribute("failedDriverList", driverXLSList);
						request.setAttribute("failedDriverList",
						request.getAttribute("failedDriverList"));
						request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
						forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
						
					} else if (demo != null) {
						request.setAttribute("successmessagediv", demo);
						forward=MessageConstants.DRIVER_EXCEL_UPLOAD;

					} else {
						//successInsert = beforeInsert - notInsertCount;
						//System.out.println("Total SuccessInsert= "+successInsert);
						request.setAttribute("successmessagediv", +successInsert+ ":Driver Rocords Registered SuccessFully");
						
						List<DriverMsaterListVo> driverList = new TransportBD().getdriverList(userLoggingsVo);
						request.setAttribute("driverList", driverList);
						forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
					}

				} else {

					forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
				}

			} catch (Exception e) {
				System.out.println("exception Block");
				request.setAttribute("errorMessage","File is Corrupted or Empty.");
				forward=MessageConstants.DRIVER_EXCEL_UPLOAD;
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStageXSLAction : insertStageXSL : Ending");

			return mapping.findForward(forward);
		}

	  public ActionForward downloadDriverInstructionFormat(ActionMapping mapping, ActionForm
			  form,HttpServletRequest request, HttpServletResponse response) throws
			  Exception {
			  
			  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
			  Date()) + MessageConstants.START_POINT);
			  logger.info(JDate.getTimeString(new Date()) +
			  " Control in UploadDriverXSLAction : downloadDriverInstructionFormat : Starting");
			  
			  //System.out.println("downloadfileformat");
			  
			  try {
			    
			  String filePath = request.getRealPath("/") +"FIles/DriverFileUpload/downloadDriverInstructionFormat.pdf"; 
			  
			  //System.out.println("FILEPATH:::"+filePath);
			  
			  ServletOutputStream out  = response.getOutputStream();
			  FileInputStream in = new FileInputStream(filePath);
			  HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename=" +
			  "downloadDriverInstructionFormat.pdf");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  } catch (Exception e) {
				  e.printStackTrace(); 
				  logger.error(e.getMessage(), e); 
			  }
			  
			  
			  JLogger.log(0, JDate.getTimeString(new Date()) +
			  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
			  + " Control in UploadDriverXSLAction : downloadDriverInstructionFormat : Ending");
			  
			  return null;
			  
			  }
}
