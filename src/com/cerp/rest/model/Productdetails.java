package com.cerp.rest.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Productdetails extends Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995493870226042622L;

	private String sub_type;
	private String cust_domain;
	private int no_of_users;
	private String urlname;
	private String schoolname;
	private int no_of_students;
	private String license_key;
	private String lic_expdate;
	private String sub_start_date;
	private int sub_id;
	
	public Productdetails(){
		
	}
	
	
	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public String getSub_start_date() {
		return sub_start_date;
	}

	public void setSub_start_date(String sub_start_date) {
		this.sub_start_date = sub_start_date;
	}

	public String getSub_type() {
		return sub_type;
	}

	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public String getCust_domain() {
		return cust_domain;
	}

	public void setCust_domain(String cust_domain) {
		this.cust_domain = cust_domain;
	}

	public int getNo_of_users() {
		return no_of_users;
	}

	public void setNo_of_users(int no_of_users) {
		this.no_of_users = no_of_users;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public int getNo_of_students() {
		return no_of_students;
	}

	public void setNo_of_students(int no_of_students) {
		this.no_of_students = no_of_students;
	}

	public String getLicense_key() {
		return license_key;
	}

	public void setLicense_key(String license_key) {
		this.license_key = license_key;
	}

	public String getLic_expdate() {
		return lic_expdate;
	}

	public void setLic_expdate(String lic_expdate) {
		this.lic_expdate = lic_expdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
