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
import com.centris.campus.delegate.UserRolePermissionBD;
import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public class UserRolePermissionAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public ActionForward insertRolePermission(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : insertRolePermission Starting");
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			
			UserRolePermissionForm	userRolePermissionform=(UserRolePermissionForm)form;
			
			System.out.println("i'm in insert");
			
			System.out.println("role ID ::: "+userRolePermissionform.getRoleCode());
			
			System.out.println("Permission ID ::: "+userRolePermissionform.getPermissionCode());
			
			System.out.println("Shortname ID ::: "+userRolePermissionform.getPermissionShortName());
			
			System.out.println("Is Applicable ID ::: "+userRolePermissionform.getIsPermissionAllowed());
			
			String roleCode =userRolePermissionform.getRoleCode();
			String roleName="";
			if(roleCode!=null && roleCode.equals("All")){
				roleCode="All";
				roleName="All";
			}
			else{
				String[] role=roleCode.split(",");
				roleCode=role[0];
				roleName=role[1];
			}
			
			userRolePermissionform.setLog_audit_session(log_audit_session);
			userRolePermissionform.setRoleCode(roleCode);
			userRolePermissionform.setRoleName(roleName);
			userRolePermissionform.setCustid(userLoggingsVo.getCustId());
			userRolePermissionform.setUserLoggingVo(userLoggingsVo);
			UserRolePermissionVO userRolePermissionVO= new UserRolePermissionBD().insertRolePermission(userRolePermissionform,userLoggingsVo);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("status", userRolePermissionVO.getServerMessage().toString());
			response.getWriter().print(jsonobject);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : insertRolePermission Ending");
		return null;
	}
	public ActionForward getPermissionByRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : getPermissionByRole Starting");
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String roleCode = request.getParameter("roleCode");
			String roleName="";
			if(roleCode!=null && roleCode.equals("All")){
				roleCode="%%";
				roleName="All";
			}
			else{
				String[] role=roleCode.split(",");
				roleCode=role[0];
				roleName=role[1];
			}
			 List<PermissionVO> permissionVOList = new UserRolePermissionBD().getPermissionByRole(roleCode,userLoggingsVo);
			 JSONObject jsonObject = new JSONObject();
			 jsonObject.put("permission", permissionVOList);
			 response.getWriter().print(jsonObject);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : getPermissionByRole Ending");
		return null;
	}
	
	public ActionForward removePermission(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : removePermission Starting");
		try{
			
			UserLoggingsPojo userLoggingsVo = (UserLoggingsPojo) request.getSession(false).getAttribute("token_information");
			
			String roleCode = request.getParameter("roleCode");
			String roleName="";
			String log_audit_session = (String) request.getSession(false).getAttribute("log_audit_session");
			if(roleCode!=null && roleCode.equals("All")){
				roleCode="%%";
				roleName="All";
			}
			else{
				String[] role=roleCode.split(",");
				roleCode=role[0];
				roleName=role[1];
			}
			 PermissionVO permissionVO = new UserRolePermissionBD().removePermission(roleCode,roleName,log_audit_session,userLoggingsVo);
			 request.getSession().setAttribute("message", permissionVO.getServerMessage());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UserRolePermissionAction : removePermission Ending");
		return null;
	}
	
	
	
}
