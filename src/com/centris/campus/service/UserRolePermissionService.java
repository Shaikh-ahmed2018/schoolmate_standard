package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;


public interface UserRolePermissionService {
	public UserRolePermissionVO getUserRolePermission(UserLoggingsPojo custid);
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission,UserLoggingsPojo userLoggingsVo);
	public List<PermissionVO>  getPermissionByRole(String roleCode,UserLoggingsPojo custid);
	public PermissionVO  removePermission(String roleCode,String roleName,String log_audit_session,UserLoggingsPojo custid);
}
