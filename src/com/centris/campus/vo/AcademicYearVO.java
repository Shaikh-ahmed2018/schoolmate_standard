package com.centris.campus.vo;

public class AcademicYearVO {

	private String acadamic_id;
	private String acadamic_name;
	private String startDate;
	private String endDate;
	private String description;
	private String createuser;
	private String sno;
	private String status;
	private String branchname;
	private String branchcode;
	
	private String[] accyearIds;
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

	public String getAcadamic_id() {
		return acadamic_id;
	}

	public void setAcadamic_id(String acadamic_id) {
		this.acadamic_id = acadamic_id;
	}

	public String getAcadamic_name() {
		return acadamic_name;
	}

	public void setAcadamic_name(String acadamic_name) {
		this.acadamic_name = acadamic_name;
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

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
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

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
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

	public String getLog_audit_session() {
		return log_audit_session;
	}

	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}

	public String[] getAccyearIds() {
		return accyearIds;
	}

	public void setAccyearIds(String[] accyearIds) {
		this.accyearIds = accyearIds;
	}

}
