package com.centris.campus.dao;

import com.centris.campus.forms.UserRolePermissionForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PermissionVO;
import com.centris.campus.vo.UserRolePermissionVO;



public interface UserRolePermissionDAO {
	public UserRolePermissionVO getUserRolePermission(UserLoggingsPojo custid);
	public UserRolePermissionVO insertRolePermission(UserRolePermissionForm userRolePermission,UserLoggingsPojo userLoggingsVo);
	public PermissionVO  removePermission(String roleCode,String roleName,String log_audit_session,UserLoggingsPojo custid);
}
