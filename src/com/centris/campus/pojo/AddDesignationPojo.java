package com.centris.campus.pojo;




/**
 * @author seshuma
 *
 */
public class AddDesignationPojo {
	
	private String desgid;
	private String desgname;
	private String desgdes;
	private String createuser;
	private String designationcode;  
	private String log_audit_session;   
	private String custid; 
	
	public String getDesignationcode() {
		return designationcode;
	}
	public void setDesignationcode(String designationcode) {
		this.designationcode = designationcode;
	}
	public String getDesgid() {
		return desgid;
	}
	public void setDesgid(String desgid) {
		this.desgid = desgid;
	}
	public String getDesgname() {
		return desgname;
	}
	public void setDesgname(String desgname) {
		this.desgname = desgname;
	}
	public String getDesgdes() {
		return desgdes;
	}
	public void setDesgdes(String desgdes) {
		this.desgdes = desgdes;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
}
