package com.centris.campus.vo;

import java.util.List;

public class HolidayMasterVo {

	private String accYearId;
	private String locId;
	private String locName;
	private String desc;
	private String depLoc;
	private String locManagerMail;
	private String hrManagerMail;
	private String onsiteManagerMail;
	private String year;
	private String holidaysName;
	private String holidayType;
	private String accyname;
	private String holId;
	
	private String[] holidayIds;
	private String status;
	private String inactiveReason;
	private String activeReason;
	private String otherReason;   
	private String remarks;
	private String log_audit_session;
	private String custid;
	
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
	public String getHolId() {
		return holId;
	}
	public void setHolId(String holId) {
		this.holId = holId;
	}
	public String getAccyname() {
		return accyname;
	}
	public void setAccyname(String accyname) {
		this.accyname = accyname;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getHolidaysName() {
		return holidaysName;
	}
	public void setHolidaysName(String holidaysName) {
		this.holidaysName = holidaysName;
	}                                                           
	public String getDate() {
		return date;                                           
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	String date;
	String weekDay;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	List depRecords;
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDepLoc() {
		return depLoc;
	}
	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}
	public String getLocManagerMail() {
		return locManagerMail;
	}
	public void setLocManagerMail(String locManagerMail) {
		this.locManagerMail = locManagerMail;
	}
	public String getHrManagerMail() {
		return hrManagerMail;
	}
	public void setHrManagerMail(String hrManagerMail) {
		this.hrManagerMail = hrManagerMail;
	}
	
	public List getDepRecords() {
		return depRecords;
	}
	public void setDepRecords(List depRecords) {
		this.depRecords = depRecords;
	}
	public String getOnsiteManagerMail() {
		return onsiteManagerMail;
	}
	public void setOnsiteManagerMail(String onsiteManagerMail) {
		this.onsiteManagerMail = onsiteManagerMail;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getAccYearId() {
		return accYearId;
	}
	public void setAccYearId(String accYearId) {
		this.accYearId = accYearId;
	}
	public String[] getHolidayIds() {
		return holidayIds;
	}
	public void setHolidayIds(String[] holidayIds) {
		this.holidayIds = holidayIds;
	}

}
