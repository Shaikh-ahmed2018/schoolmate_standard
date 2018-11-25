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
import com.centris.campus.delegate.BankBranchBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.BankBranchVO;
import com.centris.campus.vo.BankVO;
import com.centris.campus.vo.UserDetailVO;


public class BankBranchMasterAction extends DispatchAction {
	private static Logger logger = Logger
			.getLogger(BankBranchMasterAction.class);

	public ActionForward bankbranchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction: bankbranchList Starting");
		
		List<BankBranchVO> list = null;
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String searchText = request.getParameter("searchText");
			String status="Y";
			if(searchText!=null){

				list = new BankBranchBD().getSearchBranchList(searchText,custdetails,status);
				
				request.setAttribute("searchText", searchText);

			}else{

				list = new BankBranchBD().getBranchList(custdetails);
			}
			
			request.setAttribute("bankbranchlist", list);
			request.setAttribute("searchname1", request.getParameter("historysearchname"));
			request.setAttribute("status1", request.getParameter("historystatus"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction: bankbranchList Ending");
		
		return mapping.findForward(MessageConstants.BANK_BRANCH_LIST);

	}

	public ActionForward addBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:addBank Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			String msg="New Bank Branch Master";
			request.setAttribute("msg", msg);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			List<BankVO> list = new BankBD().getBankList(custdetails);
			request.setAttribute("bankNameList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:addBank Ending");

		return mapping.findForward(MessageConstants.ADD_BANK_BRANCH);
	}

	public ActionForward saveBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:saveBranch Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String usercode = userDetailVO.getUserCode();
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String bankname = request.getParameter("bankname");
			String branchname = request.getParameter("branchname");
			String branchshortname = request.getParameter("branchshortname");
			String ifsccode = request.getParameter("ifsccode");
			String address = request.getParameter("address");
			String hbankbranchId = request.getParameter("hbankbranchId");

			BankBranchVO vo = new BankBranchVO();
			vo.setBankname(bankname);
			vo.setBranchName(branchname);
			vo.setBranchShortName(branchshortname);
			vo.setIfscCode(ifsccode);
			vo.setAddress(address);
			vo.setHbankBranchId(hbankbranchId);
            vo.setCustid(custdetails.getCustId());
            vo.setLog_audit_session(log_audit_session);
			String result = new BankBranchBD().saveBranchDetails(vo, usercode,custdetails);

			JSONObject obj = new JSONObject();
			obj.put("result", result);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:saveBranch Ending");

		return null;
	}


	public ActionForward editBranch(ActionMapping mapping , ActionForm form, HttpServletRequest request , HttpServletResponse response ){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:editBranch Starting");
		
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			String msg="Modify Bank Branch Master";
			request.setAttribute("msg", msg);
		String branchId = request.getParameter("branchId");
		System.out.println(branchId);
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		List<BankVO> list = new BankBD().getBankList(custdetails);
		request.setAttribute("bankNameList", list);
		
		BankBranchVO vo = new BankBranchBD().editBranchGet(branchId,custdetails);

        request.setAttribute("branchEditdata", vo);	
        
        request.setAttribute("status1", request.getParameter("status"));
		request.setAttribute("searchname1", request.getParameter("searchtext"));

		}
        catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:editBranch Ending");
		return mapping.findForward(MessageConstants.ADD_BANK_BRANCH);
	}
	
	
		public ActionForward removeBranch(ActionMapping mapping , ActionForm form , HttpServletRequest request , HttpServletResponse response)throws Exception{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:removeBranch Starting");
		
		String result =null;
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			String removeId[] = request.getParameter("removeId").split(",");
			/*String status=request.getParameter("status");
			System.out.println("status : "+ status);*/
			String reason=request.getParameter("reason");
			String button=request.getParameter("button");
			String status="";
			if(button.equalsIgnoreCase("Y")){
				status= "N";
			}else{
				status= "Y";
			}
			System.out.println("button : "+ button);
			result = new BankBranchBD().removeBranch(removeId,custdetails,status,reason,button,log_audit_session);
			//System.out.println("00000000000"+result);
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			obj.put("status", status);
			obj.put("button", button);
			response.getWriter().print(obj);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:removeBranch Ending");
		return null;
	}
	
	/* ajax */
	public ActionForward checkBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:checkBranch Starting");
		try{
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			
		UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
		String ifsccode = request.getParameter("ifsccode");
		System.out.println("in ifsc" + ifsccode);

		String status = new BankBranchBD().checkBranchName(ifsccode,custdetails);

		JSONObject jobj = new JSONObject();
		jobj.put("status", status);
		response.getWriter().print(jobj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction:checkBranch Ending");
		
		return null;
	}

	public ActionForward searchBankBranch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction: searchBankBranch Starting");
		
		List<BankBranchVO> list = null;
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
										LeftMenusHighlightMessageConstant.MODULE_SETTINGS_BANK_BRANCH_MASTER);
			
			UserLoggingsPojo custdetails = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String searchText = request.getParameter("searchText");
		    String status=request.getParameter("status");

			list = new BankBranchBD().getSearchBranchList(searchText,custdetails,status);
			
			JSONObject obj = new JSONObject();
			obj.put("searchBankBranchList", list);
			response.getWriter().print(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchMasterAction: searchBankBranch Ending");
		
		return null;

	}
	
}
