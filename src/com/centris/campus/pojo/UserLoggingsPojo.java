package com.centris.campus.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserLoggingsPojo implements Serializable{

	private static final long serialVersionUID = 8088915577967607447L;
	private int sno;
	private String usercode;
	private String ipaddress;
	private Timestamp logintime;
	private Timestamp logouttime;
	private String sessionId;
	private String tokenNo;
	private String custId;
	private String remarks;
	private String auserid;
	private long noOfStudent;
	private String custdbname;
	private String custdbpwd;
	private String custdbhost;
	private String custdbnuame;

	private String status;
	private String licsencekey;
	private String cust_refno;
	private String subtype;
	private String noofstudents;
	private String urlname;
	private String domain;
	

	private String locId;
	private String acaYearId;
	private CustomerDBDetails dbdetails;


	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public CustomerDBDetails getDbdetails() {
		return dbdetails;
	}
	public void setDbdetails(CustomerDBDetails dbdetails) {
		this.dbdetails = dbdetails;
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
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getNoofstudents() {
		return noofstudents;
	}
	public void setNoofstudents(String noofstudents) {
		this.noofstudents = noofstudents;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCustdbnuame() {
		return custdbnuame;
	}
	public void setCustdbnuame(String custdbnuame) {
		this.custdbnuame = custdbnuame;
	}
	public String getAuserid() {
		return auserid;
	}
	public void setAuserid(String auserid) {
		this.auserid = auserid;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public Timestamp getLogintime() {
		return logintime;
	}
	public void setLogintime(Timestamp timestamp) {
		this.logintime = timestamp;
	}
	public Timestamp getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(Timestamp logouttime) {
		this.logouttime = logouttime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public long getNoOfStudent() {
		return noOfStudent;
	}
	public void setNoOfStudent(long noOfStudent) {
		this.noOfStudent = noOfStudent;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getAcaYearId() {
		return acaYearId;
	}
	public void setAcaYearId(String acaYearId) {
		this.acaYearId = acaYearId;
	}
	
}
