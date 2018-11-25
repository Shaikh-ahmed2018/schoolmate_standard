package com.cerp.rest.model;

import java.io.Serializable;

public class DomainVerification implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3989466267710790392L;
	private String status;
	private String messgae;
	private String cutomerrefno;
	private String licstatus;
	
	
	
	public DomainVerification() {
		super();
	}
	
	public String getCutomerrefno() {
		return cutomerrefno;
	}

	public void setCutomerrefno(String cutomerrefno) {
		this.cutomerrefno = cutomerrefno;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessgae() {
		return messgae;
	}
	
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
	
	public String getLicstatus() {
		return licstatus;
	}
	
	public void setLicstatus(String licstatus) {
		this.licstatus = licstatus;
	}
 	
}
