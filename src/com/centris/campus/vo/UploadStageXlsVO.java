package com.centris.campus.vo;

import java.io.Serializable;

public class UploadStageXlsVO implements Serializable {

	
	private String stageid;
	private String stage_name;
	private String stage_description;
	private String createdby;
	private String createddate;
	private String amount;
	private String reason;
	
	private String locationname;
	private String accyear;
	private String locationid;
	private String accyearid;
	
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getAccyearid() {
		return accyearid;
	}
	public void setAccyearid(String accyearid) {
		this.accyearid = accyearid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStageid() {
		return stageid;
	}
	public void setStageid(String stageid) {
		this.stageid = stageid;
	}
	public String getStage_name() {
		return stage_name;
	}
	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}
	public String getStage_description() {
		return stage_description;
	}
	public void setStage_description(String stage_description) {
		this.stage_description = stage_description;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	

}
