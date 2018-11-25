package com.cerp.rest.model;

import java.io.Serializable;

public class CustomerInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7019782709224834345L;
	private String custId;
	private String custdbname;
	private String custdbpwd;
	private String custdbnuame;
	private String custdbhost;
	private String auserid;
	private String status;
	private String code;
	private String appusername;
	private String apppwd;
	private String domain;
	private String licsencekey;
	private String cust_refno;
	private String subtype;
	private String noofstudents;
	private String urlname;
	private int sub_id;
	
	
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getNoofstudents() {
		return noofstudents;
	}
	public void setNoofstudents(String noofstudents) {
		this.noofstudents = noofstudents;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getLicsencekey() {
		return licsencekey;
	}
	public void setLicsencekey(String licsencekey) {
		this.licsencekey = licsencekey;
	}
	public String getCust_refno() {
		return cust_refno;
	}
	public void setCust_refno(String cust_refno) {
		this.cust_refno = cust_refno;
	}
	public String getAuserid() {
		return auserid;
	}
	public void setAuserid(String auserid) {
		this.auserid = auserid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAppusername() {
		return appusername;
	}
	public void setAppusername(String appusername) {
		this.appusername = appusername;
	}
	public String getApppwd() {
		return apppwd;
	}
	public void setApppwd(String apppwd) {
		this.apppwd = apppwd;
	}
	public String getCustdbname() {
		return custdbname;
	}
	public void setCustdbname(String custdbname) {
		this.custdbname = custdbname;
	}
	public String getCustdbpwd() {
		return custdbpwd;
	}
	public void setCustdbpwd(String custdbpwd) {
		this.custdbpwd = custdbpwd;
	}
	public String getCustdbhost() {
		return custdbhost;
	}
	public void setCustdbhost(String custdbhost) {
		this.custdbhost = custdbhost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCustdbnuame() {
		return custdbnuame;
	}
	public void setCustdbnuame(String custdbnuame) {
		this.custdbnuame = custdbnuame;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
}
