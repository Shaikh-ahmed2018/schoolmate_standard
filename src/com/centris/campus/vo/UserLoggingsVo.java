package com.centris.campus.vo;

import java.sql.Timestamp;

public class UserLoggingsVo {

	private int sno;
	private String usercode;
	private String ipaddress;
	private  Timestamp logintime;
	private Timestamp logouttime;
	private  String sessionId;
	
	
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
	public  Timestamp getLogintime() {
		return logintime;
	}
	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
	public Timestamp getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(Timestamp logouttime) {
		this.logouttime = logouttime;
	}
	public  String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
