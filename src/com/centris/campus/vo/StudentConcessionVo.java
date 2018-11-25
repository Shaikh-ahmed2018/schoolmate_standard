package com.centris.campus.vo;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;

public class StudentConcessionVo {

private String studentId;	
private String admissionNo;
private String student;
private String class_section;
private String contype;
private String termcode;
private String term;
private String concessionAmount;
private String termTuitionFeeAmount;
private String classId;
private String feecode;
private String feename;
private String specialization;
private String academicYear;  
private String log_audit_session;
private String custid;
private String status;
private int noofmonths;
private int termAmount;
private int stageAmount;
private String locID;
private String isTransport;
private String StageId;
private String stage_name;
private String route;
private String RouteName;
private String concessionId;
private String concessionName;
private String concessionType;
private String applicableConcession;
private String concessionBy;
private int feeAmount;
private ArrayList<StudentConcessionVo> termFees;
private int sno;
private String termId;
private String locationId; 
private String academicId; 
private String[] concessionAmountArray;
private String[] feecodeArray;
private String cancelFees;
private String concessionStyle;
private String concessionPercent;
private String hiddentrmcode;
private String hiddenfeecode;
private String hiddenacyId;
private String hiddencontype;
private String hiddenConId;
private String hiddenConBy;
private String feeCollectionCode;
private UserLoggingsPojo dbDetails;



public UserLoggingsPojo getDbDetails() {
	return dbDetails;
}
public void setDbDetails(UserLoggingsPojo dbDetails) {
	this.dbDetails = dbDetails;
}
public String getFeeCollectionCode() {
	return feeCollectionCode;
}
public void setFeeCollectionCode(String feeCollectionCode) {
	this.feeCollectionCode = feeCollectionCode;
}
public String getHiddentrmcode() {
	return hiddentrmcode;
}
public void setHiddentrmcode(String hiddentrmcode) {
	this.hiddentrmcode = hiddentrmcode;
}
public String getHiddenfeecode() {
	return hiddenfeecode;
}
public void setHiddenfeecode(String hiddenfeecode) {
	this.hiddenfeecode = hiddenfeecode;
}
public String getHiddenacyId() {
	return hiddenacyId;
}
public void setHiddenacyId(String hiddenacyId) {
	this.hiddenacyId = hiddenacyId;
}
public String getHiddencontype() {
	return hiddencontype;
}
public void setHiddencontype(String hiddencontype) {
	this.hiddencontype = hiddencontype;
}
public String getHiddenConId() {
	return hiddenConId;
}
public void setHiddenConId(String hiddenConId) {
	this.hiddenConId = hiddenConId;
}
public String getHiddenConBy() {
	return hiddenConBy;
}
public void setHiddenConBy(String hiddenConBy) {
	this.hiddenConBy = hiddenConBy;
}
public String getConcessionPercent() {
	return concessionPercent;
}
public void setConcessionPercent(String concessionPercent) {
	this.concessionPercent = concessionPercent;
}
public String getConcessionStyle() {
	return concessionStyle;
}
public void setConcessionStyle(String concessionStyle) {
	this.concessionStyle = concessionStyle;
}
public String getCancelFees() {
	return cancelFees;
}
public void setCancelFees(String cancelFees) {
	this.cancelFees = cancelFees;
}
public String[] getFeecodeArray() {
	return feecodeArray;
}
public void setFeecodeArray(String[] feecodeArray) {
	this.feecodeArray = feecodeArray;
}
public String[] getConcessionAmountArray() {
	return concessionAmountArray;
}
public void setConcessionAmountArray(String[] concessionAmountArray) {
	this.concessionAmountArray = concessionAmountArray;
}
public String getLocationId() {
	return locationId;
}
public void setLocationId(String locationId) {
	this.locationId = locationId;
}
public String getAcademicId() {
	return academicId;
}
public void setAcademicId(String academicId) {
	this.academicId = academicId;
}
public String getTermId() {
	return termId;
}
public void setTermId(String termId) {
	this.termId = termId;
}
public int getFeeAmount() {
	return feeAmount;
}
public void setFeeAmount(int feeAmount) {
	this.feeAmount = feeAmount;
}
public int getSno() {
	return sno;
}
public void setSno(int sno) {
	this.sno = sno;
}
public ArrayList<StudentConcessionVo> getTermFees() {
	return termFees;
}
public void setTermFees(ArrayList<StudentConcessionVo> termFees) {
	this.termFees = termFees;
}
public String getConcessionBy() {
	return concessionBy;
}
public void setConcessionBy(String concessionBy) {
	this.concessionBy = concessionBy;
}
public String getConcessionId() {
	return concessionId;
}
public void setConcessionId(String concessionId) {
	this.concessionId = concessionId;
}
public String getConcessionName() {
	return concessionName;
}
public void setConcessionName(String concessionName) {
	this.concessionName = concessionName;
}
public String getConcessionType() {
	return concessionType;
}
public void setConcessionType(String concessionType) {
	this.concessionType = concessionType;
}
public String getApplicableConcession() {
	return applicableConcession;
}
public void setApplicableConcession(String applicableConcession) {
	this.applicableConcession = applicableConcession;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getCustid() {
	return custid;
}
public void setCustid(String custid) {
	this.custid = custid;
}
public String getAcademicYear() {
	return academicYear;
}
public void setAcademicYear(String academicYear) {
	this.academicYear = academicYear;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
public String getFeecode() {
	return feecode;
}
public void setFeecode(String feecode) {
	this.feecode = feecode;
}
public String getFeename() {
	return feename;
}
public void setFeename(String feename) {
	this.feename = feename;
}
public String getStudentId() {
	return studentId;
}
public void setStudentId(String studentId) {
	this.studentId = studentId;
}
public String getConcessionAmount() {
	return concessionAmount;
}
public void setConcessionAmount(String concessionAmount) {
	this.concessionAmount = concessionAmount;
}
public String getTermTuitionFeeAmount() {
	return termTuitionFeeAmount;
}
public void setTermTuitionFeeAmount(String termTuitionFeeAmount) {
	this.termTuitionFeeAmount = termTuitionFeeAmount;
}
public String getAdmissionNo() {
	return admissionNo;
}
public void setAdmissionNo(String admissionNo) {
	this.admissionNo = admissionNo;
}
public String getStudent() {
	return student;
}
public void setStudent(String student) {
	this.student = student;
}
public String getClass_section() {
	return class_section;
}
public void setClass_section(String class_section) {
	this.class_section = class_section;
}
public String getContype() {
	return contype;
}
public void setContype(String contype) {
	this.contype = contype;
}
public String getTermcode() {
	return termcode;
}
public void setTermcode(String termcode) {
	this.termcode = termcode;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}
public String getClassId() {
	return classId;
}
public void setClassId(String classId) {
	this.classId = classId;
}
public String getLog_audit_session() {
	return log_audit_session;
}
public void setLog_audit_session(String log_audit_session) {
	this.log_audit_session = log_audit_session;
}
public int getNoofmonths() {
	return noofmonths;
}
public void setNoofmonths(int noofmonths) {
	this.noofmonths = noofmonths;
}
public int getTermAmount() {
	return termAmount;
}
public void setTermAmount(int termAmount) {
	this.termAmount = termAmount;
}
public int getStageAmount() {
	return stageAmount;
}
public void setStageAmount(int stageAmount) {
	this.stageAmount = stageAmount;
}
public String getLocID() {
	return locID;
}
public void setLocID(String locID) {
	this.locID = locID;
}
public String getIsTransport() {
	return isTransport;
}
public void setIsTransport(String isTransport) {
	this.isTransport = isTransport;
}
public String getStageId() {
	return StageId;
}
public void setStageId(String stageId) {
	StageId = stageId;
}
public String getStage_name() {
	return stage_name;
}
public void setStage_name(String stage_name) {
	this.stage_name = stage_name;
}
public String getRoute() {
	return route;
}
public void setRoute(String route) {
	this.route = route;
}
public String getRouteName() {
	return RouteName;
}
public void setRouteName(String routeName) {
	RouteName = routeName;
}


}
