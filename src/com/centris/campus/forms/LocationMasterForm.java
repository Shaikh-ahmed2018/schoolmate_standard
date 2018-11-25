package com.centris.campus.forms;


import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class LocationMasterForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6792693780188185560L;
	private String location_id;
	private String locationname;
	private String desc;
	private String modifyBy;
	private String createdBy;
	private String createdDate;
	private String modifiedDate;
	private String loc_id; 
	private String log_audit_session;
	
	private String affilno;
	private String website;
	private String contactno;
	private FormFile schoollogo;
	private FormFile boardlogo;
	private String board;
	private String schoolcode;
	private String emailId;
	private String address;
	
	private String schoollogoFilePath;
	private String boardlogoFilePath;
	private String customerId;
	
	private String hiddenboardlogoId;
	private String hiddenschoollogoId; 
	
	private String country; 
	private String city; 
	private String landlineno; 
	private String address2; 
	private String state; 
	private String pincode; 
	private String hiddenaddId; 
	private String locAddId;
	
	private String hiddenlocaddressId;
	private String operation;
	
	private String cperson;
	private String cmobileno;
	private String saffiliationno;
	private String cemailId;
	private String clandline;
	
	public String getCperson() {
		return cperson;
	}
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	public String getCmobileno() {
		return cmobileno;
	}
	public void setCmobileno(String cmobileno) {
		this.cmobileno = cmobileno;
	}
	public String getSaffiliationno() {
		return saffiliationno;
	}
	public void setSaffiliationno(String saffiliationno) {
		this.saffiliationno = saffiliationno;
	}
	public String getCemailId() {
		return cemailId;
	}
	public void setCemailId(String cemailId) {
		this.cemailId = cemailId;
	}
	public String getClandline() {
		return clandline;
	}
	public void setClandline(String clandline) {
		this.clandline = clandline;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getAffilno() {
		return affilno;
	}
	public void setAffilno(String affilno) {
		this.affilno = affilno;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	 
	 
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getSchoolcode() {
		return schoolcode;
	}
	public void setSchoolcode(String schoolcode) {
		this.schoolcode = schoolcode;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getSchoollogoFilePath() {
		return schoollogoFilePath;
	}
	public void setSchoollogoFilePath(String schoollogoFilePath) {
		this.schoollogoFilePath = schoollogoFilePath;
	}
	public String getBoardlogoFilePath() {
		return boardlogoFilePath;
	}
	public void setBoardlogoFilePath(String boardlogoFilePath) {
		this.boardlogoFilePath = boardlogoFilePath;
	}
 
	public String getHiddenboardlogoId() {
		return hiddenboardlogoId;
	}
	public void setHiddenboardlogoId(String hiddenboardlogoId) {
		this.hiddenboardlogoId = hiddenboardlogoId;
	}
	public String getHiddenschoollogoId() {
		return hiddenschoollogoId;
	}
	public void setHiddenschoollogoId(String hiddenschoollogoId) {
		this.hiddenschoollogoId = hiddenschoollogoId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public FormFile getSchoollogo() {
		return schoollogo;
	}
	public void setSchoollogo(FormFile schoollogo) {
		this.schoollogo = schoollogo;
	}
	public FormFile getBoardlogo() {
		return boardlogo;
	}
	public void setBoardlogo(FormFile boardlogo) {
		this.boardlogo = boardlogo;
	}
    
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLandlineno() {
		return landlineno;
	}
	public void setLandlineno(String landlineno) {
		this.landlineno = landlineno;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getHiddenaddId() {
		return hiddenaddId;
	}
	public void setHiddenaddId(String hiddenaddId) {
		this.hiddenaddId = hiddenaddId;
	}
	public String getLocAddId() {
		return locAddId;
	}
	public void setLocAddId(String locAddId) {
		this.locAddId = locAddId;
	}
	public String getHiddenlocaddressId() {
		return hiddenlocaddressId;
	}
	public void setHiddenlocaddressId(String hiddenlocaddressId) {
		this.hiddenlocaddressId = hiddenlocaddressId;
	}
	
}
