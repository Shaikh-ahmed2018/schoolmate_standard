package com.cerp.rest.model;

import java.io.Serializable;

import javax.swing.SwingConstants;

/**
 * @author User
 *
 */
public class CustomerLoginDetails extends Productdetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3141631373296826707L;
	private int login_id; 
	private String appusername;
	private String apppwd;
	
	public CustomerLoginDetails(){
		
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
