package com.centris.campus.pojo;

import java.sql.Timestamp;

public class SuddenHolidaysPojo {

	private String categoryid;
	private String[] classid;
	private String[] sectionid;
	private String date;
	private String smstext;
	private String createdby;
	private Timestamp createdate;
	private String modifiedby;
	private Timestamp modifieddate;
	private String smsstatus;
	private String suddenholidayscode;
	private int issection;
	private int isstudent;
	private String[] studentid;
	private String hdate;
	private String locId;  
	private String log_audit_session; 
	private String className;
	private String acc_Year;
	private String custid;
	private UserLoggingsPojo dbDetails;
	private int smsCharacters;
	private int balanceSMS;
	
	
	
	public int getBalanceSMS() {
		return balanceSMS;
	}
	public void setBalanceSMS(int balanceSMS) {
		this.balanceSMS = balanceSMS;
	}
	public int getSmsCharacters() {
		return smsCharacters;
	}
	public void setSmsCharacters(int smsCharacters) {
		this.smsCharacters = smsCharacters;
	}
	public UserLoggingsPojo getDbDetails() {
		return dbDetails;
	}
	public void setDbDetails(UserLoggingsPojo dbDetails) {
		this.dbDetails = dbDetails;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getAcc_Year() {
		return acc_Year;
	}
	public void setAcc_Year(String acc_Year) {
		this.acc_Year = acc_Year;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String[] getClassid() {
		return classid;
	}
	public void setClassid(String[] classid) {
		this.classid = classid;
	}
	public String[] getSectionid() {
		return sectionid;
	}
	public void setSectionid(String[] sectionid) {
		this.sectionid = sectionid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getSmsstatus() {
		return smsstatus;
	}
	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}
	public String getSuddenholidayscode() {
		return suddenholidayscode;
	}
	public void setSuddenholidayscode(String suddenholidayscode) {
		this.suddenholidayscode = suddenholidayscode;
	}
	public int getIssection() {
		return issection;
	}
	public void setIssection(int issection) {
		this.issection = issection;
	}
	public int getIsstudent() {
		return isstudent;
	}
	public void setIsstudent(int isstudent) {
		this.isstudent = isstudent;
	}
	public String[] getStudentid() {
		return studentid;
	}
	public void setStudentid(String[] studentid) {
		this.studentid = studentid;
	}
	public String getHdate() {
		return hdate;
	}
	public void setHdate(String hdate) {
		this.hdate = hdate;
	}
}
