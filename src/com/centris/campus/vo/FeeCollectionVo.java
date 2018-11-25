package com.centris.campus.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ashish
 *
 */
public class FeeCollectionVo {

	private String addmissionno;
	private String studentname;
	private String term;
	private String termid;
	private String classId;
	private String classname;
	private String sectionId;
	private String sectionname;
	private String accYear;
	private String accYearname;
	private String amount;
	private String status;
	private String studentIdNo;
	private String doj;
	private int sno;
	private String reason;
	private double concession;
	private double tot_actual_amt;
	private double tot_concession_amt;
	private double tot_paid_amt;
	private String tot_opning_amt;
	private String userID;
	private double amount_paid_so_far;
	private double opening_balance;
	private double current_payment;
	private double outstanding_balance;
	private String studentid;
	private String specialization;
	private double fineAmount;
	private String[] termIdArray;
	private String[] monthName;
	private String[] monthlyAmount;
	private String refundstatus;
	private double advanceCarry;
	private double duesCarry;
	private String paymentMode;
	private String paymentPatriculars;
	private String dd_cheque_date;
	private String dd_cheque_bank;
	private String imgurl;
	private String billdate;
	private String chlnno;
	private String permanentaddress;
	private String termName;
	private String refrecieptNo; 
	private String log_audit_session;
    private String custid;
    private double cashAmount;
    private double cardAmount;
    private String feecolcode;
    private String accYearId;
    private String receiptno;
    private String locationId;
    private double advanceAmount;

	private String amountInWords;
	private String bankname;
	private String chequeno;

	private String username;
	private String prefeecolcode;
	private String feesettingCode;
	private String months;
	private String termNames;
	private Double amountPaidByTerm;
	private String trasVechicleName;
	private int noOfStudents;
	private String driverName;
	private String contactNo;
	private String emergenceyContactNo;
	private String age;
	private int successSMS;
	private int failSMS;
	private int totalSMS;
	
	private String transportTermname;
	private double transportTermwiseFeeCollection;
	private String feeTermName;
	private double feeTermwiseCollection;
	
	private List<FeeCollectionVo> vechileNameList;
	private List<FeeCollectionVo> termNameList;
	private List<FeeCollectionVo> driverContactNoList;
	private List<FeeCollectionVo> transportDashboard;
	private List<FeeCollectionVo> dashboardFeeCollection;
	
	
	

	
	public String getFeeTermName() {
		return feeTermName;
	}
	public void setFeeTermName(String feeTermName) {
		this.feeTermName = feeTermName;
	}
	public double getFeeTermwiseCollection() {
		return feeTermwiseCollection;
	}
	public void setFeeTermwiseCollection(double feeTermwiseCollection) {
		this.feeTermwiseCollection = feeTermwiseCollection;
	}
	public List<FeeCollectionVo> getDashboardFeeCollection() {
		return dashboardFeeCollection;
	}
	public void setDashboardFeeCollection(List<FeeCollectionVo> dashboardFeeCollection) {
		this.dashboardFeeCollection = dashboardFeeCollection;
	}
	public List<FeeCollectionVo> getTransportDashboard() {
		return transportDashboard;
	}
	public void setTransportDashboard(List<FeeCollectionVo> transportDashboard) {
		this.transportDashboard = transportDashboard;
	}
	public String getTransportTermname() {
		return transportTermname;
	}
	public void setTransportTermname(String transportTermname) {
		this.transportTermname = transportTermname;
	}
	public double getTransportTermwiseFeeCollection() {
		return transportTermwiseFeeCollection;
	}
	public void setTransportTermwiseFeeCollection(double transportTermwiseFeeCollection) {
		this.transportTermwiseFeeCollection = transportTermwiseFeeCollection;
	}
	public int getSuccessSMS() {
		return successSMS;
	}
	public void setSuccessSMS(int successSMS) {
		this.successSMS = successSMS;
	}
	public int getFailSMS() {
		return failSMS;
	}
	public void setFailSMS(int failSMS) {
		this.failSMS = failSMS;
	}
	public int getTotalSMS() {
		return totalSMS;
	}
	public void setTotalSMS(int totalSMS) {
		this.totalSMS = totalSMS;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmergenceyContactNo() {
		return emergenceyContactNo;
	}
	public void setEmergenceyContactNo(String emergenceyContactNo) {
		this.emergenceyContactNo = emergenceyContactNo;
	}

	
	
	
	public List<FeeCollectionVo> getDriverContactNoList() {
		return driverContactNoList;
	}
	public void setDriverContactNoList(List<FeeCollectionVo> driverContactNoList) {
		this.driverContactNoList = driverContactNoList;
	}
	public List<FeeCollectionVo> getVechileNameList() {
		return vechileNameList;
	}
	public void setVechileNameList(List<FeeCollectionVo> vechileNameList) {
		this.vechileNameList = vechileNameList;
	}
	public List<FeeCollectionVo> getTermNameList() {
		return termNameList;
	}
	public void setTermNameList(List<FeeCollectionVo> termNameList) {
		this.termNameList = termNameList;
	}
	public String getTrasVechicleName() {
		return trasVechicleName;
	}
	public void setTrasVechicleName(String trasVechicleName) {
		this.trasVechicleName = trasVechicleName;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public Double getAmountPaidByTerm() {
		return amountPaidByTerm;
	}
	public void setAmountPaidByTerm(Double amountPaidByTerm) {
		this.amountPaidByTerm = amountPaidByTerm;
	}
	public String getTermNames() {
		return termNames;
	}
	public void setTermNames(String termNames) {
		this.termNames = termNames;
	}
	public String getFeesettingCode() {
		return feesettingCode;
	}
	public void setFeesettingCode(String feesettingCode) {
		this.feesettingCode = feesettingCode;
	}
	public String getPrefeecolcode() {
		return prefeecolcode;
	}
	public void setPrefeecolcode(String prefeecolcode) {
		this.prefeecolcode = prefeecolcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	public double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String getAccYearId() {
		return accYearId;
	}
	public void setAccYearId(String accYearId) {
		this.accYearId = accYearId;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getFeecolcode() {
		return feecolcode;
	}
	public void setFeecolcode(String feecolcode) {
		this.feecolcode = feecolcode;
	}
	public double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}

	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getStudentIdNo() {
		return studentIdNo;
	}
	public void setStudentIdNo(String studentIdNo) {
		this.studentIdNo = studentIdNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getRefrecieptNo() {
		return refrecieptNo;
	}
	public void setRefrecieptNo(String refrecieptNo) {
		this.refrecieptNo = refrecieptNo;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getPermanentaddress() {
		return permanentaddress;
	}
	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}
	public String getChlnno() {
		return chlnno;
	}
	public void setChlnno(String chlnno) {
		this.chlnno = chlnno;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public String getDd_cheque_date() {
		return dd_cheque_date;
	}
	public void setDd_cheque_date(String dd_cheque_date) {
		this.dd_cheque_date = dd_cheque_date;
	}
	public String getDd_cheque_bank() {
		return dd_cheque_bank;
	}
	public void setDd_cheque_bank(String dd_cheque_bank) {
		this.dd_cheque_bank = dd_cheque_bank;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentPatriculars() {
		return paymentPatriculars;
	}
	public void setPaymentPatriculars(String paymentPatriculars) {
		this.paymentPatriculars = paymentPatriculars;
	}
	public double getAdvanceCarry() {
		return advanceCarry;
	}
	public void setAdvanceCarry(double advanceCarry) {
		this.advanceCarry = advanceCarry;
	}
	public double getDuesCarry() {
		return duesCarry;
	}
	public void setDuesCarry(double duesCarry) {
		this.duesCarry = duesCarry;
	}
	private ArrayList<FeeNameVo> feeNamelist;
	private String feeSettingList; 
	
	public String getFeeSettingList() {
		return feeSettingList;
	}
	public void setFeeSettingList(String feeSettingList) {
		this.feeSettingList = feeSettingList;
	}
	
	
	public String getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	public String[] getTermIdArray() {
		return termIdArray;
	}
	public void setTermIdArray(String[] termIdArray) {
		this.termIdArray = termIdArray;
	}
	public String[] getMonthName() {
		return monthName;
	}
	public void setMonthName(String[] monthName) {
		this.monthName = monthName;
	}
	public String[] getMonthlyAmount() {
		return monthlyAmount;
	}
	public void setMonthlyAmount(String[] monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getTot_opning_amt() {
		return tot_opning_amt;
	}
	public void setTot_opning_amt(String tot_opning_amt) {
		this.tot_opning_amt = tot_opning_amt;
	}
	public double getAmount_paid_so_far() {
		return amount_paid_so_far;
	}
	public void setAmount_paid_so_far(double amount_paid_so_far) {
		this.amount_paid_so_far = amount_paid_so_far;
	}
	public double getOpening_balance() {
		return opening_balance;
	}
	public void setOpening_balance(double opening_balance) {
		this.opening_balance = opening_balance;
	}
	public double getCurrent_payment() {
		return current_payment;
	}
	public void setCurrent_payment(double current_payment) {
		this.current_payment = current_payment;
	}
	public double getOutstanding_balance() {
		return outstanding_balance;
	}
	public void setOutstanding_balance(double outstanding_balance) {
		this.outstanding_balance = outstanding_balance;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getAccYearname() {
		return accYearname;
	}
	public void setAccYearname(String accYearname) {
		this.accYearname = accYearname;
	}
	
	public double getConcession() {
		return concession;
	}
	public void setConcession(double concession) {
		this.concession = concession;
	}
	public double getTot_actual_amt() {
		return tot_actual_amt;
	}
	public void setTot_actual_amt(double tot_actual_amt) {
		this.tot_actual_amt = tot_actual_amt;
	}
	public double getTot_concession_amt() {
		return tot_concession_amt;
	}
	public void setTot_concession_amt(double tot_concession_amt) {
		this.tot_concession_amt = tot_concession_amt;
	}
	public double getTot_paid_amt() {
		return tot_paid_amt;
	}
	public void setTot_paid_amt(double tot_paid_amt) {
		this.tot_paid_amt = tot_paid_amt;
	}
	public ArrayList<FeeNameVo> getFeeNamelist() {
		return feeNamelist;
	}
	public void setFeeNamelist(ArrayList<FeeNameVo> feeNamelist) {
		this.feeNamelist = feeNamelist;
	}
	public String getAccYear() {
		return accYear;
	}
	public void setAccYear(String accYear) {
		this.accYear = accYear;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getAddmissionno() {
		return addmissionno;
	}
	public void setAddmissionno(String addmissionno) {
		this.addmissionno = addmissionno;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLog_audit_session() {
		return log_audit_session;
	}
	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	
	
	
	
}
