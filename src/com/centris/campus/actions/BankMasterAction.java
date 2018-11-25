package com.centris.campus.actions;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import com.centris.campus.delegate.BankBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.BankVO;
import com.centris.campus.vo.UserDetailVO;



public class BankMasterAction extends DispatchAction{
	
    private static Logger logger = Logger.getLogger(BankMasterAction.class);
	
	public ActionForward bankEntry(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:bankEntry Starting");
		
		List<BankVO> list  = null;
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String searchText = request.getParameter("searchText");
			String status="Y";
			if(searchText!=null){

				list = new BankBD().getSearchBankList(searchText,custdetails,status);
				
				request.setAttribute("searchText", searchText);

			}
			else{
			
				list = new BankBD().getBankList(custdetails);
			    request.setAttribute("banklist", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:bankEntry Ending");
		
		return mapping.findForward(MessageConstants.BANK_LIST);
	}
	
	
	public ActionForward addBank(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:addBank Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			String msg="New Bank";
			request.setAttribute("msg",msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:addBank Ending");
		
		
		return mapping.findForward(MessageConstants.ADD_BANK);
	}
	
	public ActionForward removeBank(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:removeBank Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String bankId[] = request.getParameter("bankId").split(",");
			String reason=request.getParameter("reason");
			String status=request.getParameter("status");
			String button=request.getParameter("button");
			String result = new BankBD().removeBank(bankId,custdetails,reason,status,log_audit_session,button);
			
			
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:removeBank Ending");
	    
		return null;
	}

	public ActionForward saveBank(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:saveBank Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String groupcode=userDetailVO.getGroupCode();
			String usercode=userDetailVO.getUserCode();
			
			String name = request.getParameter("name");
			String shortname = request.getParameter("shortname");
			String hbankId = request.getParameter("hbankId");
			
			
			BankVO vo=new BankVO();
			vo.setName(name);
			vo.setShortname(shortname);
			vo.setHbankId(hbankId);
			vo.setCustid(custdetails.getCustId());
			vo.setLog_audit_session(log_audit_session);
			String result=new BankBD().saveBank(vo, usercode,custdetails);
			
			JSONObject jobj=new JSONObject();
			jobj.put("result", result);
			response.getWriter().print(jobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:saveBank Ending");
		return null;
	}
	
	public ActionForward editBank(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:editBank Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			String msg="Modify Bank";
			request.setAttribute("msg",msg);
			String bankId=request.getParameter("bankId");
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			BankVO vo=new BankBD().editBankDetail(bankId,custdetails);
			request.setAttribute("bankrecord", vo);
			
			request.setAttribute("status1", request.getParameter("status"));
			request.setAttribute("searchname1", request.getParameter("searchtext"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:editBank Ending");
		
		return mapping.findForward(MessageConstants.ADD_BANK);
	}
	
	//pending for edit bank page ...
	
	
	public ActionForward validateBankName(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:validateBankName Starting");
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
					
			String name = request.getParameter("name");
			
			
			String result=new BankBD().validateBankName(name,custdetails);
			
			JSONObject jobj=new JSONObject();
			jobj.put("result", result);
			response.getWriter().print(jobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:validateBankName Ending");
		return null;
	}
	public ActionForward bankEntrySearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:bankEntrySearch Starting");
		
		List<BankVO> list  = null;
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String searchText = request.getParameter("searchText");
			 String status=request.getParameter("status");
		   list = new BankBD().getSearchBankList(searchText,custdetails,status);
				
				

		   JSONObject jsonobj = new JSONObject();
			jsonobj.put("bankEntrySearchlist", list);
			response.getWriter().print(jsonobj);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankMasterAction:bankEntrySearch Ending");
		
		return null;
	}
	
	
	
}
