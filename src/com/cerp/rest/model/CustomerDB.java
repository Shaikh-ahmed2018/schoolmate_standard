package com.cerp.rest.model;

import java.io.Serializable;

public class CustomerDB extends Productdetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1126888779998001574L;
	private int dbid;
	private int plesk_dbid;
	private int pleskdb_userid;
	private String dbname;
	private String dbusername;
	private String dbpwd;
	private String dbhost;
	private char isActive;
	private String status;
	
	public CustomerDB(){
		
	}

	public int getDbid() {
		return dbid;
	}

	public void setDbid(int dbid) {
		this.dbid = dbid;
	}

	public int getPlesk_dbid() {
		return plesk_dbid;
	}

	public void setPlesk_dbid(int plesk_dbid) {
		this.plesk_dbid = plesk_dbid;
	}

	public int getPleskdb_userid() {
		return pleskdb_userid;
	}

	public void setPleskdb_userid(int pleskdb_userid) {
		this.pleskdb_userid = pleskdb_userid;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbusername() {
		return dbusername;
	}

	public void setDbusername(String dbusername) {
		this.dbusername = dbusername;
	}

	public String getDbpwd() {
		return dbpwd;
	}

	public void setDbpwd(String dbpwd) {
		this.dbpwd = dbpwd;
	}

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
