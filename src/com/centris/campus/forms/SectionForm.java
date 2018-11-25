package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class SectionForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String sectionId;
	private String sectionName;
	private String secDetailsId;
	private String secDetailsName;
	private String sectionStrength;
	private String streamName;
	private String streamId;
	private String createUser;
	private String modifyUser;
	private String status;
	private String secId;
	private String campusClassSectionCheckBox;
	private String classId;
	private String locationId;
	private String locationName;
	
	private String className;
	
	private String strength; 

	private String remarks; 
	private String inactiveReason;
	private String activeReason;
	private String otherReason;   
	private String log_audit_session;
	private String custid;
	
	
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
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
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSecDetailsId() {
		return secDetailsId;
	}
	public void setSecDetailsId(String secDetailsId) {
		this.secDetailsId = secDetailsId;
	}
	public String getSecDetailsName() {
		return secDetailsName;
	}
	public void setSecDetailsName(String secDetailsName) {
		this.secDetailsName = secDetailsName;
	}
	public String getSectionStrength() {
		return sectionStrength;
	}
	public void setSectionStrength(String sectionStrength) {
		this.sectionStrength = sectionStrength;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
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
	public String getCampusClassSectionCheckBox() {
		return campusClassSectionCheckBox;
	}
	public void setCampusClassSectionCheckBox(String campusClassSectionCheckBox) {
		this.campusClassSectionCheckBox = campusClassSectionCheckBox;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSecId() {
		return secId;
	}
	public void setSecId(String secId) {
		this.secId = secId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getInactiveReason() {
		return inactiveReason;
	}
	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}
	public String getActiveReason() {
		return activeReason;
	}
	public void setActiveReason(String activeReason) {
		this.activeReason = activeReason;
	}
	public String getOtherReason() {
		return otherReason;
	}
	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	

}
