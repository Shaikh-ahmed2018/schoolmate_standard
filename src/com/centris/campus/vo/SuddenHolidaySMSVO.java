package com.centris.campus.vo;

import java.sql.Timestamp;

import com.centris.campus.pojo.UserLoggingsPojo;

public class SuddenHolidaySMSVO {

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
	
	private String check;
	private String classname;
	private String student;
	private int slNo;
	private String custid;
	private String startDate;
	private String endDate;
	private String locaname;
	private String secname;
	private UserLoggingsPojo dbDetails;
    private String credate;
	private String hdate;
	private String sectionList;
	private String[] classListArray;
	private String createdUser;
	private String classList;
	private String[] sectionListArray;
	private String studentList;
	private String[] studentListArray;
	private String locId; 
	private String log_audit_session;
	private String accYear;
	private int smsCharacters;
	private int balanceSMS;
    
	
	public int getBalanceSMS() {
		return balanceSMS;
	}
	public void setBalanceSMS(int balanceSMS) {
		this.balanceSMS = balanceSMS;
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
	public String getAccYear() {
		return accYear;
	}
	public void setAccYear(String accYear) {
		this.accYear = accYear;
	}
	public int getSmsCharacters() {
		return smsCharacters;
	}
	public void setSmsCharacters(int smsCharacters) {
		this.smsCharacters = smsCharacters;
	}
	public String getCredate() {
		return credate;
	}
	public void setCredate(String credate) {
		this.credate = credate;
	}
	public UserLoggingsPojo getDbDetails() {
		return dbDetails;
	}
	public void setDbDetails(UserLoggingsPojo dbDetails) {
		this.dbDetails = dbDetails;
	}
	public String getSecname() {
		return secname;
	}
	public void setSecname(String secname) {
		this.secname = secname;
	}
	public String getLocaname() {
		return locaname;
	}
	public void setLocaname(String locaname) {
		this.locaname = locaname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
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
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
