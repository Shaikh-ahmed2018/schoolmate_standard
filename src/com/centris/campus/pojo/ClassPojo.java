package com.centris.campus.pojo;

public class ClassPojo {
	private static final long serialVersionUID = 1L;
	private String streamId;
	private String classId;
	private String streamName;
	private String className;
	private String secDetailId;
	private String secDetailName;
	private String createUser;
	private String modifyUser;
	private String status;
	private String updateClassCode;
	private String updateclassname;
	private String locationId;
	private String locationName;
	private String divisionName;
	private String strength;
	private String occupationName;
	private String religionName;
	private String religionId;
	private String casteName;
	private String casteId;
	private String casteCategoryName;
	private String specializationName;
	private String accyearId;
	private String accyearName;
	
	private String holidayDate;
	private String holidayName;
	private String holidaytype;
	private String invalidStrenth;
	private UserLoggingsPojo custid;
	private String hiddenStreamId;
	
	private String subjectName;
	private String subjectCode;
	private String subjectType;
	private String isLanguage;
	private String isLaboratory;
	private String description;
	private String specId;
	
	
	
	public String getSpecId() {
		return specId;
	}
	public void setSpecId(String specId) {
		this.specId = specId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getIsLanguage() {
		return isLanguage;
	}
	public void setIsLanguage(String isLanguage) {
		this.isLanguage = isLanguage;
	}
	public String getIsLaboratory() {
		return isLaboratory;
	}
	public void setIsLaboratory(String isLaboratory) {
		this.isLaboratory = isLaboratory;
	}
	public UserLoggingsPojo getCustid() {
		return custid;
	}
	public void setCustid(UserLoggingsPojo custid) {
		this.custid = custid;
	}
	public String getInvalidStrenth() {
		return invalidStrenth;
	}
	public void setInvalidStrenth(String invalidStrenth) {
		this.invalidStrenth = invalidStrenth;
	}
	public String getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public String getHolidaytype() {
		return holidaytype;
	}
	public void setHolidaytype(String holidaytype) {
		this.holidaytype = holidaytype;
	}
	public String getCasteId() {
		return casteId;
	}
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}
	public String getCasteCategoryName() {
		return casteCategoryName;
	}
	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}
	private String[] locIds;
	private String[] classIds;
	private String inactiveReason;
	private String activeReason;
	private String otherReason; 
	private String remarks; 
	private String log_audit_session;
	private String excellStrength;
	
	
	public String getExcellStrength() {
		return excellStrength;
	}
	public void setExcellStrength(String excellStrength) {
		this.excellStrength = excellStrength;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String string) {
		this.strength = string;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUpdateclassname() {
		return updateclassname;
	}
	public void setUpdateclassname(String updateclassname) {
		this.updateclassname = updateclassname;
	}
	private String classid;
	
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSecDetailId() {
		return secDetailId;
	}
	public void setSecDetailId(String secDetailId) {
		this.secDetailId = secDetailId;
	}
	public String getSecDetailName() {
		return secDetailName;
	}
	public void setSecDetailName(String secDetailName) {
		this.secDetailName = secDetailName;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateClassCode() {
		return updateClassCode;
	}
	public void setUpdateClassCode(String updateClassCode) {
		this.updateClassCode = updateClassCode;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getOccupationName() {
		return occupationName;
	}
	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	public String getReligionName() {
		return religionName;
	}
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getReligionId() {
		return religionId;
	}
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}
	public String getSpecializationName() {
		return specializationName;
	}
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	public String getAccyearName() {
		return accyearName;
	}
	public void setAccyearName(String accyearName) {
		this.accyearName = accyearName;
	}
	public String getAccyearId() {
		return accyearId;
	}
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String[] getClassIds() {
		return classIds;
	}
	public void setClassIds(String[] classIds) {
		this.classIds = classIds;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String[] getLocIds() {
		return locIds;
	}
	public void setLocIds(String[] locIds) {
		this.locIds = locIds;
	}
	public String getHiddenStreamId() {
		return hiddenStreamId;
	}
	public void setHiddenStreamId(String hiddenStreamId) {
		this.hiddenStreamId = hiddenStreamId;
	}
}
