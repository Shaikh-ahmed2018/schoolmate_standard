package com.centris.campus.vo;

import com.centris.campus.pojo.UserLoggingsPojo;

public class ViewallSubjectsVo {

	private String category;
	private String classid;
	private String classname;
	private String subjectid;
	private String subjectname;
	private String path;
	private String description;
	private String status;
	private int sno;
	private String searchName;
	private String streamId;
	private String subjectCode;
	private int totalMarks;
	private int passMarks;

	private String hiddensubject;
	private String locationId;
	private String locationName;
	private String specializationId;
	private String specializationName;

	private String isLanguage;
	private String labCode;
	private String custid;
	private String islab;
	private String subType;
	private String remark;
	private String log_audit_session;
	private String accyear;
	private UserLoggingsPojo dbdetails;
	private String labName;
	private String labId;
	
	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getAccyear() {
		return accyear;
	}

	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	
	public UserLoggingsPojo getDbdetails() {
		return dbdetails;
	}

	public void setDbdetails(UserLoggingsPojo dbdetails) {
		this.dbdetails = dbdetails;
	}

	public String getLog_audit_session() {
		return log_audit_session;
	}

	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}

	public String getIslab() {
		return islab;
	}

	public void setIslab(String islab) {
		this.islab = islab;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getIsLanguage() {
		return isLanguage;
	}

	public void setIsLanguage(String isLanguage) {
		this.isLanguage = isLanguage;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String classdetailid;
	private String classstreamid;

	public String getClassdetailid() {
		return classdetailid;
	}

	public void setClassdetailid(String classdetailid) {
		this.classdetailid = classdetailid;
	}

	public String getClassstreamid() {
		return classstreamid;
	}

	public void setClassstreamid(String classstreamid) {
		this.classstreamid = classstreamid;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getHiddensubject() {
		return hiddensubject;
	}

	public void setHiddensubject(String hiddensubject) {
		this.hiddensubject = hiddensubject;
	}

	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

}