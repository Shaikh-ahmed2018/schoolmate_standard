package com.centris.campus.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.centris.campus.pojo.UserLoggingsPojo;

public class LeaveRequestForm extends ActionForm{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String leavecode;
	private String requestto;
	private String fromdate;
	private String todate;
	private String starttime;
	private String endtime;
	private String leavetype;
	private String totalleave;
	private String reason;
	private FormFile fileupload;
	
	private String fileupload1;
	private String studentRegno;
	private String studentFnameVar;
	private String studentid;
	private String srudentname;
	private String createuser;
	private int sno;
	
	private String studentFname;
	private String createuserval;
	private String payrollid;
	private String log_audit_session;

	private String accyid;
	private String feedbackid;
	private String feedbackto;
	private String description;
	private FormFile addfile;
	private String createUser;
	private String accyear;
	private String addfile1;
	
	
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getFeedbackto() {
		return feedbackto;
	}
	public void setFeedbackto(String feedbackto) {
		this.feedbackto = feedbackto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FormFile getAddfile() {
		return addfile;
	}
	public void setAddfile(FormFile addfile) {
		this.addfile = addfile;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getAddfile1() {
		return addfile1;
	}
	public void setAddfile1(String addfile1) {
		this.addfile1 = addfile1;
	}
	public String getAccyid() {
		return accyid;
	}
	public void setAccyid(String accyid) {
		this.accyid = accyid;
	}

	private UserLoggingsPojo userLoginingVo;
	

	public UserLoggingsPojo getUserLoginingVo() {
		return userLoginingVo;
	}
	public void setUserLoginingVo(UserLoggingsPojo userLoginingVo) {
		this.userLoginingVo = userLoginingVo;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}

	
	//private int sno1;
	
	
	
	
	public String getPayrollid() {
		return payrollid;
	}
	public void setPayrollid(String payrollid) {
		this.payrollid = payrollid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreateuserval() {
		return createuserval;
	}
	public void setCreateuserval(String createuserval) {
		this.createuserval = createuserval;
	}
	public String getLeavecode() {
		return leavecode;
	}
	public void setLeavecode(String leavecode) {
		this.leavecode = leavecode;
	}
	public String getRequestto() {
		return requestto;
	}
	public void setRequestto(String requestto) {
		this.requestto = requestto;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getTotalleave() {
		return totalleave;
	}
	public void setTotalleave(String totalleave) {
		this.totalleave = totalleave;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public FormFile getFileupload() {
		return fileupload;
	}
	public void setFileupload(FormFile fileupload) {
		this.fileupload = fileupload;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getFileupload1() {
		return fileupload1;
	}
	public void setFileupload1(String fileupload1) {
		this.fileupload1 = fileupload1;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSrudentname() {
		return srudentname;
	}
	public void setSrudentname(String srudentname) {
		this.srudentname = srudentname;
	}
	public String getStudentRegno() {
		return studentRegno;
	}
	public void setStudentRegno(String studentRegno) {
		this.studentRegno = studentRegno;
	}
	public String getStudentFnameVar() {
		return studentFnameVar;
	}
	public void setStudentFnameVar(String studentFnameVar) {
		this.studentFnameVar = studentFnameVar;
	}
	public String getStudentFname() {
		return studentFname;
	}
	public void setStudentFname(String studentFname) {
		this.studentFname = studentFname;
	}
	
	
	
	

}
