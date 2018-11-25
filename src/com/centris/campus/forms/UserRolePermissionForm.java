package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

import com.centris.campus.pojo.UserLoggingsPojo;

public class UserRolePermissionForm extends ActionForm{
private String roleCode;
private String roleName;
private String permissionCode;
private String permissionShortName;
private String isPermissionAllowed; 
private String log_audit_session; 
private String custid; 
private String selectall;
private UserLoggingsPojo userLoggingVo;


public UserLoggingsPojo getUserLoggingVo() {
	return userLoggingVo;
}
public void setUserLoggingVo(UserLoggingsPojo userLoggingVo) {
	this.userLoggingVo = userLoggingVo;
}
public String getCustid() {
	return custid;
}
public void setCustid(String custid) {
	this.custid = custid;
}
public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
public String getPermissionCode() {
	return permissionCode;
}
public void setPermissionCode(String permissionCode) {
	this.permissionCode = permissionCode;
}
public String getPermissionShortName() {
	return permissionShortName;
}
public void setPermissionShortName(String permissionShortName) {
	this.permissionShortName = permissionShortName;
}
public String getIsPermissionAllowed() {
	return isPermissionAllowed;
}
public void setIsPermissionAllowed(String isPermissionAllowed) {
	this.isPermissionAllowed = isPermissionAllowed;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getLog_audit_session() {
	return log_audit_session;
}
public void setLog_audit_session(String log_audit_session) {
	this.log_audit_session = log_audit_session;
}
public String getSelectall() {
	return selectall;
}
public void setSelectall(String selectall) {
	this.selectall = selectall;
}
}
