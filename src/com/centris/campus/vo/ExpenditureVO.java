package com.centris.campus.vo;

import java.io.Serializable;

public class ExpenditureVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String expId;
	private String expenditureTitle;
	private double amount;
	private String description;
	private String date;
	private String createUser;
	private String modifyUser;
	private String createDate;
	private String modifyDate;
	private String locationname;   
	private String log_audit_session;
	private String isActive;
	private String remark;
	
	private String[] expIds;
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getExpId() {
		return expId;
	}
	public void setExpId(String expId) {
		this.expId = expId;
	}
	public String getExpenditureTitle() {
		return expenditureTitle;
	}
	public void setExpenditureTitle(String expenditureTitle) {
		this.expenditureTitle = expenditureTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String[] getExpIds() {
		return expIds;
	}
	public void setExpIds(String[] expIds) {
		this.expIds = expIds;
	}

	
}
