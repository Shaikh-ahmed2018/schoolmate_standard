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
import org.json.JSONObject;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.UploadStageXSLBD;
import com.centris.campus.forms.UploadStageXSLForm;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStageXSLUtility;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadStageXSLAction extends DispatchAction {

	private static Logger logger = Logger
			.getLogger(UploadStageXSLAction.class);
	
	@SuppressWarnings("unchecked")
	public ActionForward insertStageXSL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADSTAGEEXCELDATAFILE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
		
		
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLAction : insertStageXSL : Starting");
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward=null;
		File fileURL=null;
		try {
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			//System.out.println("InsertStageXSL Action Is Working");

			UploadStageXLSDaoImpl daoImpl = new UploadStageXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkStageCountBeforeInsert(userLoggingsVo);

			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");

			String username = userDetailVO.getUserId();

			//System.out.println("username:::" +username);

			UploadStageXSLForm uploadStageXSLForm = (UploadStageXSLForm) form;

			FormFile file = uploadStageXSLForm.getFormFile();

			String filePath =request.getRealPath("/");
			if (file != null) {

				fileName = file.getFileName();

				 fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				 
				
				if((((fileURL.length() / 1024) * 100) / 100) <102){
				
			    String extension= FilenameUtils.getExtension(fileName); //extension
			    
				Map<String, Object> stageMap = new UploadStageXSLUtility().getExcelData(fileURL,file,extension);
             
				if(stageMap.size()>0){
					List<UploadStageXlsPOJO> alList = (List<UploadStageXlsPOJO>) stageMap.get("List");
                    beforeInsert = alList.size();
                   
                }else{
                	beforeInsert=0;
                	request.setAttribute("errorMessage","File is Corrupted or Empty.");
        			forward=MessageConstants.STAGE_EXCEL_UPLOAD;
        			return mapping.findForward(forward);
                }
				
				/*for (UploadStageXlsPOJO uploadStageXlsPOJO : alList) {
					
					System.out.println("name;:::"+uploadStageXlsPOJO.getStage_name());
				}*/

				UploadStageXSLBD stageXSLBD = new UploadStageXSLBD();

				Set<UploadStageXlsVO> stageXLSList = new HashSet<UploadStageXlsVO>();
				
				String location = (String) request.getSession(false).getAttribute("current_schoolLocation");
				
				String demo = (String) stageMap.get("Error");
				
				stageXLSList = stageXSLBD.insertStageXSL((List<UploadStageXlsPOJO>) stageMap.get("List"),username,
						demo,log_audit_session,userLoggingsVo,location);
				
				notInsertCount = stageXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;

				if (stageXLSList.size() != 0) {

					request.setAttribute("failedStageList", stageXLSList);

					request.setAttribute("failedStageList",	request.getAttribute("failedStageList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;

				} else {
					//successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":Stage Rocords Registered SuccessFully");
					
					AddStageVO vo = new AddStageVO();

					ArrayList<AddStageVO> list = new StageDelegateBD().StageDetails(userLoggingsVo);
					
					request.setAttribute("StageDetails", list);
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;
				 }
			  }
				else {
					 request.setAttribute("errorMessage","file size exceeded.It should be less than 1MB.");
					 forward=MessageConstants.STAGE_EXCEL_UPLOAD;
				 }
			} 
			
			else {
				forward=MessageConstants.STAGE_EXCEL_UPLOAD;
			}

		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage","File is Corrupted or Empty.");
			forward=MessageConstants.STAGE_EXCEL_UPLOAD;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		finally {
			if(fileURL!=null) {
				fileURL.delete();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLAction : insertStageXSL : Ending");
		return mapping.findForward(forward);
	}
	
	public ActionForward downloadStageFileFormat(ActionMapping mapping, ActionForm
			  form,HttpServletRequest request, HttpServletResponse response) throws
			  Exception {
			  
			  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
			  Date()) + MessageConstants.START_POINT);
			  logger.info(JDate.getTimeString(new Date()) +
			  " Control in UploadStageXSLAction : downloadStageFileFormat : Starting");
			  //System.out.println("downloadfileformat");
			  try {
			  String filePath = request.getRealPath("/") +"FIles/StageFileUpload/StageExcelFileFormat.xls"; 
			  System.out.println("FILEPATH:::"+filePath);
			  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
			  FileInputStream(filePath); HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename=" +
			  "StageExcelFileFormat.xls");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  } catch (Exception e) {
				  e.printStackTrace(); 
				  logger.error(e.getMessage(), e); 
			  }
			  
			  
			  JLogger.log(0, JDate.getTimeString(new Date()) +
			  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
			  + " Control in UploadStageXSLAction : downloadStageFileFormat : Ending");
			  
			  return null;
			  
			  }
	
	  public ActionForward updateStageXSL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)

		{

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStageXSLAction : updateStageXSL Starting");

			try {
				
				String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
				UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
				UploadStageXSLBD stageXSLBD = new UploadStageXSLBD();
				System.out.println("stageId "+request.getParameter("stageId"));
				String[] stageId=request.getParameter("stageId").split(",");
				String[] acc=request.getParameter("accid").split(",");
				String[] loc=request.getParameter("locid").split(",");
				String[] amount=request.getParameter("amount").split(",");
				String user=HelperClass.getCurrentUserID(request);
				UploadStageXlsPOJO pojo = new UploadStageXlsPOJO();
				pojo.setLog_audit_session(log_audit_session);
			
				String ststus=stageXSLBD.updateStageXSL(stageId,acc,loc,amount,user,pojo,userLoggingsVo);
				
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("status", ststus);
				response.getWriter().print(jsonobject);

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStageXSLAction : updateStageXSL Ending");

			return null;
		}
	
	  public ActionForward StageInstructionFormat(ActionMapping mapping, ActionForm
			  form,HttpServletRequest request, HttpServletResponse response) throws
			  Exception {
			  
			  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
			  Date()) + MessageConstants.START_POINT);
			  logger.info(JDate.getTimeString(new Date()) +
			  " Control in UploadStageXSLAction : StageInstructionFormat : Starting");
			  //System.out.println("downloadfileformat");
			  try {
			  String filePath = request.getRealPath("/") +"FIles/StageFileUpload/StageInstructionFormat.pdf"; 
			  System.out.println("FILEPATH:::"+filePath);
			  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
			  FileInputStream(filePath); HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename=" +
			  "StageInstructionFormat.pdf");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  } catch (Exception e) {
				  e.printStackTrace(); 
				  logger.error(e.getMessage(), e); 
			  }
			  
			  
			  JLogger.log(0, JDate.getTimeString(new Date()) +
			  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
			  + " Control in UploadStageXSLAction : StageInstructionFormat : Ending");
			  
			  return null;
			  
			  }

}
