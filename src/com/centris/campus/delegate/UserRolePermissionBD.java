package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.UserRolePermissionServiceImpl;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public class UserRolePermissionBD {

	public UserRolePermissionVO getUserRolePermission(UserLoggingsPojo custdetails){
		return new UserRolePermissionServiceImpl().getUserRolePermission(custdetails);
	}
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission, UserLoggingsPojo userLoggingsVo){
		return new UserRolePermissionServiceImpl().insertRolePermission(userRolePermission,userLoggingsVo);
	}
	public List<PermissionVO>  getPermissionByRole(String roleCode, UserLoggingsPojo custid){
		return new UserRolePermissionServiceImpl().getPermissionByRole(roleCode,custid);
	}
	public PermissionVO  removePermission(String roleCode,String roleName, String log_audit_session, UserLoggingsPojo custid){
		return new UserRolePermissionServiceImpl().removePermission(roleCode,roleName,log_audit_session,custid);
	}
	
}
