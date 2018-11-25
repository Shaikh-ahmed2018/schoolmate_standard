package com.centris.campus.vo;

import org.apache.struts.action.ActionForm;

import com.centris.campus.pojo.UserLoggingsPojo;

public class SmsVo extends ActionForm{
	
	private String homeworkid;
	private String date;
	private String classname;
	private String classid;
	private String sectionname;
	private String sectionid;
	private String subjectid;
	private String subjectname;
	private String description;
	
	private String locId;
	private String meetingid; 
	private String[] studentList;
	private String locationname;
	private String accid;
	private String smsStatus;
	private UserLoggingsPojo dbDetails;
	private String currentdate;
	private int balanceSMS;
	
	
	public int getBalanceSMS() {
		return balanceSMS;
	}

	public void setBalanceSMS(int balanceSMS) {
		this.balanceSMS = balanceSMS;
	}

	public String getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}

	public UserLoggingsPojo getDbDetails() {
		return dbDetails;
	}

	public void setDbDetails(UserLoggingsPojo dbDetails) {
		this.dbDetails = dbDetails;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	private String createuser;
	
	private String studentid [];
	private int slNo;  
	private String log_audit_session;
	private String custid;

	public String getHomeworkid() {
		return homeworkid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public void setHomeworkid(String homeworkid) {
		this.homeworkid = homeworkid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String[] getStudentid() {
		return studentid;
	}

	public void setStudentid(String[] studentid) {
		this.studentid = studentid;
	}

	public String[] getStudentList() {
		return studentList;
	}

	public void setStudentList(String[] studentList) {
		this.studentList = studentList;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public String getLog_audit_session() {
		return log_audit_session;
	}

	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}

	

}
