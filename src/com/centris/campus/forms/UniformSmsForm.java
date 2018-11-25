package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;

public class UniformSmsForm extends ActionForm{
	
	private String category;
	private String classname;
	private String section;
	private String date;
	private String smstext;
	private String student;
	private String accYearId;
	private String locId;
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getAccYearId() {
		return accYearId;
	}
	public void setAccYearId(String accYearId) {
		this.accYearId = accYearId;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	
	
	
	
	
	

}
