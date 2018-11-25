package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class BankForm extends ActionForm{
	/**
	 * Creating Bank Master By Ashish
	 */
	private static final long serialVersionUID = 1L;

	private String bankname;
	private String bankshortname;
	private String status;
	
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankshortname() {
		return bankshortname;
	}
	public void setBankshortname(String bankshortname) {
		this.bankshortname = bankshortname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
