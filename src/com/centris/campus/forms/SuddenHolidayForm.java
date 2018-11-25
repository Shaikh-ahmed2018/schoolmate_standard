package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

import com.centris.campus.pojo.UserLoggingsPojo;

public class SuddenHolidayForm extends ActionForm{

	private String date;
	private String hdate;
	private String sectionList;
	private String[] classListArray;
	private String smstext;
	private String createdUser;
	private String classList;
	private String[] sectionListArray;
	private String studentList;
	private String[] studentListArray;
	private String locId; 
	private String log_audit_session;
	private String accYear;
	private String custid;
	private UserLoggingsPojo dbDetails;
	private int smsCharacters;
	
	
	
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
	public String getAccYear() {
		return accYear;
	}
	public void setAccYear(String accYear) {
		this.accYear = accYear;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHdate() {
		return hdate;
	}
	public void setHdate(String hdate) {
		this.hdate = hdate;
	}
	public String getSectionList() {
		return sectionList;
	}
	public void setSectionList(String sectionList) {
		this.sectionList = sectionList;
	}
	public String[] getClassListArray() {
		return classListArray;
	}
	public void setClassListArray(String[] classListArray) {
		this.classListArray = classListArray;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getClassList() {
		return classList;
	}
	public void setClassList(String classList) {
		this.classList = classList;
	}
	public String[] getSectionListArray() {
		return sectionListArray;
	}
	public void setSectionListArray(String[] sectionListArray) {
		this.sectionListArray = sectionListArray;
	}
	public String getStudentList() {
		return studentList;
	}
	public void setStudentList(String studentList) {
		this.studentList = studentList;
	}
	public String[] getStudentListArray() {
		return studentListArray;
	}
	public void setStudentListArray(String[] studentListArray) {
		this.studentListArray = studentListArray;
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
	
	
	
	
	
	
	
	
	
}
