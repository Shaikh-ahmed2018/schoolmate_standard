package com.centris.campus.pojo;

public class ProfessionalTaxPojo {
	private String locationid;
	private String monthly_salary;
	private String pt_levied;
	private String countryId;
	private String stateId;
	private String userId;
	private UserLoggingsPojo userloggingVo;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getMonthly_salary() {
		return monthly_salary;
	}
	public void setMonthly_salary(String monthly_salary) {
		this.monthly_salary = monthly_salary;
	}
	public String getPt_levied() {
		return pt_levied;
	}
	public void setPt_levied(String pt_levied) {
		this.pt_levied = pt_levied;
	}
	public UserLoggingsPojo getUserloggingVo() {
		return userloggingVo;
	}
	public void setUserloggingVo(UserLoggingsPojo userloggingVo) {
		this.userloggingVo = userloggingVo;
	}
	
	

}
