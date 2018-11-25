package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class ConcessionForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String concessionId;
	private String concesionName;
	private String description;
	private String createUser;
	private String modifyUser;
	private String percentage;
	private String createdby;
	private String createdtime;
	private String ConcessionDetailsCheckBox;
	private String custid;
	private String log_audit_session;
	private String locationnid;
	private String concessionType;
	private String concession;
	private String isApplicable;
	
	public String getConcession() {
		return concession;
	}
	public void setConcession(String concession) {
		this.concession = concession;
	}
	public String getConcessionType() {
		return concessionType;
	}
	public void setConcessionType(String concessionType) {
		this.concessionType = concessionType;
	}
	public String getIsApplicable() {
		return isApplicable;
	}
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}
	public String getConcessionDetailsCheckBox() {
		return ConcessionDetailsCheckBox;
	}
	public void setConcessionDetailsCheckBox(String concessionDetailsCheckBox) {
		ConcessionDetailsCheckBox = concessionDetailsCheckBox;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getConcessionId() {
		return concessionId;
	}
	public void setConcessionId(String concessionId) {
		this.concessionId = concessionId;
	}
	public String getConcesionName() {
		return concesionName;
	}
	public void setConcesionName(String concesionName) {
		this.concesionName = concesionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getLocationnid() {
		return locationnid;
	}
	public void setLocationnid(String locationnid) {
		this.locationnid = locationnid;
	}
	
	
	
	

}
