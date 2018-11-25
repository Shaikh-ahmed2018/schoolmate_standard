package com.centris.campus.pojo;


public class RoleMasterPojo {
private String roleCode;
private String roleName;
private String roleDescription;
private String createdBy;
private String modifiedBy;
private String successMessage;
private String sno;
private String isadministrator; 
private String log_audit_session;
private String custid;


public String getCustid() {
	return custid;
}
public void setCustid(String custid) {
	this.custid = custid;
}
public String getIsadministrator() {
	return isadministrator;
}
public void setIsadministrator(String isadministrator) {
	this.isadministrator = isadministrator;
}
public String getSno() {
	return sno;
}
public void setSno(String sno) {
	this.sno = sno;
}
public String getSuccessMessage() {
	return successMessage;
}
public void setSuccessMessage(String successMessage) {
	this.successMessage = successMessage;
}
public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getRoleDescription() {
	return roleDescription;
}
public void setRoleDescription(String roleDescription) {
	this.roleDescription = roleDescription;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getModifiedBy() {
	return modifiedBy;
}
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
public String getLog_audit_session() {
	return log_audit_session;
}
public void setLog_audit_session(String log_audit_session) {
	this.log_audit_session = log_audit_session;
}


}
