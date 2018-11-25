package com.centris.campus.vo;

public class SpecializationVo {

	private String spec_Id;
	private String class_Id;
	private String stream_Id;
	private String spec_Name;
	private String create_User;
	private String create_Date;
	private String modify_User;
	private String modify_Date;
	private String class_Name;
	private String stream_Name;
	private String locationId;
	private String locationName;
	
	private String[] specIds;
	private String remarks;
	private String status;
	private String inactiveReason;
	private String activeReason;
	private String otherReason;   
	private String log_audit_session;
	private String custid;
	
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
	public String getClass_Name() {
		return class_Name;
	}
	public void setClass_Name(String class_Name) {
		this.class_Name = class_Name;
	}
	public String getStream_Name() {
		return stream_Name;
	}
	public void setStream_Name(String stream_Name) {
		this.stream_Name = stream_Name;
	}
	public String getSpec_Id() {
		return spec_Id;
	}
	public void setSpec_Id(String spec_Id) {
		this.spec_Id = spec_Id;
	}
	public String getClass_Id() {
		return class_Id;
	}
	public void setClass_Id(String class_Id) {
		this.class_Id = class_Id;
	}
	public String getStream_Id() {
		return stream_Id;
	}
	public void setStream_Id(String stream_Id) {
		this.stream_Id = stream_Id;
	}
	public String getSpec_Name() {
		return spec_Name;
	}
	public void setSpec_Name(String spec_Name) {
		this.spec_Name = spec_Name;
	}
	public String getCreate_User() {
		return create_User;
	}
	public void setCreate_User(String create_User) {
		this.create_User = create_User;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getModify_User() {
		return modify_User;
	}
	public void setModify_User(String modify_User) {
		this.modify_User = modify_User;
	}
	public String getModify_Date() {
		return modify_Date;
	}
	public void setModify_Date(String modify_Date) {
		this.modify_Date = modify_Date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String[] getSpecIds() {
		return specIds;
	}
	public void setSpecIds(String[] specIds) {
		this.specIds = specIds;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
}
