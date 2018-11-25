package com.centris.campus.pojo;

public class SubjectPojo {

	
	private String subjectId;
	private String subjectName;
	private String custid;
	private String locId;
	private UserLoggingsPojo dbdetails;
	private String labCode;
	
	
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public UserLoggingsPojo getDbdetails() {
		return dbdetails;
	}
	public void setDbdetails(UserLoggingsPojo dbdetails) {
		this.dbdetails = dbdetails;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	
	
}
