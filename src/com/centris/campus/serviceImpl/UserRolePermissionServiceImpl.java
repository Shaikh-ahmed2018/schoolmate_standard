package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.daoImpl.UserRolePermissionDAOImpl;
import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.UserRolePermissionService;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;


public class UserRolePermissionServiceImpl implements UserRolePermissionService {
	public UserRolePermissionVO getUserRolePermission(UserLoggingsPojo custdetails){
		return new UserRolePermissionDAOImpl().getUserRolePermission(custdetails);
	}
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission, UserLoggingsPojo userLoggingsVo){
		return new UserRolePermissionDAOImpl().insertRolePermission(userRolePermission,userLoggingsVo);
	}
	public List<PermissionVO>  getPermissionByRole(String roleCode, UserLoggingsPojo custid){
		return new UserRolePermissionDAOImpl().getPermissionByRole(roleCode,custid);
	}
	public PermissionVO  removePermission(String roleCode,String roleName, String log_audit_session, UserLoggingsPojo custid){
		return new UserRolePermissionDAOImpl().removePermission(roleCode,roleName,log_audit_session,custid);
	}
}
