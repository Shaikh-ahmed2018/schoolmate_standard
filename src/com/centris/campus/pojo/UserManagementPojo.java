package com.centris.campus.pojo;

import java.io.Serializable;

public class UserManagementPojo implements Serializable{
	
	private String userId;
	private String passwrd;
	private String type;
	private String searchtext; 
	private String log_audit_session;
	private String custid;
	private String roleId;
	private String locationId;
	private String uname;
	private String mobileno;
	private String email;
	private String curuserid;
	private String confirmPasswrd;
	private String status;
	private String remarks;
	private String auserid;
	private String auserids[];
    private String tecId;
	
	public String getTecId() {
		return tecId;
	}
	public void setTecId(String tecId) {
		this.tecId = tecId;
	}
	
	public String[] getAuserids() {
		return auserids;
	}
	public void setAuserids(String[] auserids) {
		this.auserids = auserids;
	}
	public String getAuserid() {
		return auserid;
	}
	public void setAuserid(String auserid) {
		this.auserid = auserid;
	}
	private String[] userIds;
	
	public String getCuruserid() {
		return curuserid;
	}
	public void setCuruserid(String curuserid) {
		this.curuserid = curuserid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSearchtext() {
		return searchtext;
	}
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswrd() {
		return passwrd;
	}
	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getConfirmPasswrd() {
		return confirmPasswrd;
	}
	public void setConfirmPasswrd(String confirmPasswrd) {
		this.confirmPasswrd = confirmPasswrd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getUserIds() {
		return userIds;
	}
	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	
	

}
