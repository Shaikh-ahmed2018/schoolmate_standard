package com.centris.campus.vo;

import java.util.ArrayList;

public class FeeNameVo {
	
	private int sno;
	private String feecode;
	private String feename;
	private double actualAmt;
	private double concessionAmt;
	private double paidAmt;
	
	private String isconcession;
	
	private String feeId;
	private String dateofJoinIdArray;
	private double feeAmountArray ;
	private double feePayingAmountArray;
	private double outStandingAmountArray;
	private double consfeeAmountArray;
	private double concessionpercentArray;
	private double payingAmountArray;
	private double updateFeeAmountArray;
	private String status;
	private String term;
	private String termId;
	private String paidDate;
	private int recieptNo; 
	private double fineAmount;
	private String monthName;
	private double advanceCarry;
	private double dueCarry;
	
	private String paymode;
	private String dddate;
	private String ddno;
	
	private String userid;
	
	private double actualamtarray;
	private double totBalAmt;
	private double totPaidAmt;
	private String feeindetailId;
	private String bankname;
	private double totalFeeAmt;
	private String feesettingCode;
	private double predues;
	private String feetype;
	private double disAmt;
	private String prefeecolcode;
	private double balanceAmt;
	private String custid;
	private String log_audit_session;
	private String remark;
	

	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public double getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	private ArrayList<FeePaidDetailsVo> feedetails;
	private int noofmonths;

	private ArrayList<String> termname;

	private String prevoiusTermId;

	public ArrayList<String> getTermname() {
		return termname;
	}
	public void setTermname(ArrayList<String> termname) {
		this.termname = termname;
	}

	public String getPrevoiusTermId() {
		return prevoiusTermId;
	}
	public void setPrevoiusTermId(String prevoiusTermId) {
		this.prevoiusTermId = prevoiusTermId;
	}

	public int getNoofmonths() {
		return noofmonths;
	}
	public void setNoofmonths(int noofmonths) {
		this.noofmonths = noofmonths;
	}
	public String getPrefeecolcode() {
		return prefeecolcode;
	}
	public void setPrefeecolcode(String prefeecolcode) {
		this.prefeecolcode = prefeecolcode;
	}
	public ArrayList<FeePaidDetailsVo> getFeedetails() {
		return feedetails;
	}
	public void setFeedetails(ArrayList<FeePaidDetailsVo> feedetails) {
		this.feedetails = feedetails;
	}
	/**
	 * @return the disAmt
	 */
	public double getDisAmt() {
		return disAmt;
	}
	/**
	 * @param disAmt the disAmt to set
	 */
	public void setDisAmt(double disAmt) {
		this.disAmt = disAmt;
	}
	/**
	 * @return the feetype
	 */
	public String getFeetype() {
		return feetype;
	}
	/**
	 * @param feetype the feetype to set
	 */
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	/**
	 * @return the predues
	 */
	public double getPredues() {
		return predues;
	}
	/**
	 * @param predues the predues to set
	 */
	public void setPredues(double predues) {
		this.predues = predues;
	}
	public String getFeesettingCode() {
		return feesettingCode;
	}
	public void setFeesettingCode(String feecollId) {
		this.feesettingCode = feecollId;
	}
	public double getTotalFeeAmt() {
		return totalFeeAmt;
	}
	public void setTotalFeeAmt(double totalFeeAmt) {
		this.totalFeeAmt = totalFeeAmt;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getFeeindetailId() {
		return feeindetailId;
	}
	public void setFeeindetailId(String feeindetailId) {
		this.feeindetailId = feeindetailId;
	}
	public double getTotPaidAmt() {
		return totPaidAmt;
	}
	public void setTotPaidAmt(double totPaidAmt) {
		this.totPaidAmt = totPaidAmt;
	}
	public double getTotBalAmt() {
		return totBalAmt;
	}
	public void setTotBalAmt(double totBalAmt) {
		this.totBalAmt = totBalAmt;
	}
	public double getActualamtarray() {
		return actualamtarray;
	}
	public void setActualamtarray(double actualamtarray) {
		this.actualamtarray = actualamtarray;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getDddate() {
		return dddate;
	}
	public void setDddate(String dddate) {
		this.dddate = dddate;
	}
	public String getDdno() {
		return ddno;
	}
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}
	public double getAdvanceCarry() {
		return advanceCarry;
	}
	public void setAdvanceCarry(double advanceCarry) {
		this.advanceCarry = advanceCarry;
	}
	public double getDueCarry() {
		return dueCarry;
	}
	public void setDueCarry(double dueCarry) {
		this.dueCarry = dueCarry;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public int getRecieptNo() {
		return recieptNo;
	}
	public void setRecieptNo(int recieptNo) {
		this.recieptNo = recieptNo;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeeId() {
		return feeId;
	}
	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}
	public String getDateofJoinIdArray() {
		return dateofJoinIdArray;
	}
	public void setDateofJoinIdArray(String dateofJoinIdArray) {
		this.dateofJoinIdArray = dateofJoinIdArray;
	}
	public double getFeeAmountArray() {
		return feeAmountArray;
	}
	public void setFeeAmountArray(double feeAmountArray) {
		this.feeAmountArray = feeAmountArray;
	}
	public double getFeePayingAmountArray() {
		return feePayingAmountArray;
	}
	public void setFeePayingAmountArray(double feePayingAmountArray) {
		this.feePayingAmountArray = feePayingAmountArray;
	}
	public double getOutStandingAmountArray() {
		return outStandingAmountArray;
	}
	public void setOutStandingAmountArray(double outStandingAmountArray) {
		this.outStandingAmountArray = outStandingAmountArray;
	}
	public double getConsfeeAmountArray() {
		return consfeeAmountArray;
	}
	public void setConsfeeAmountArray(double consfeeAmountArray) {
		this.consfeeAmountArray = consfeeAmountArray;
	}
	public double getConcessionpercentArray() {
		return concessionpercentArray;
	}
	public void setConcessionpercentArray(double concessionpercentArray) {
		this.concessionpercentArray = concessionpercentArray;
	}
	public double getPayingAmountArray() {
		return payingAmountArray;
	}
	public void setPayingAmountArray(double payingAmountArray) {
		this.payingAmountArray = payingAmountArray;
	}
	public double getUpdateFeeAmountArray() {
		return updateFeeAmountArray;
	}
	public void setUpdateFeeAmountArray(double updateFeeAmountArray) {
		this.updateFeeAmountArray = updateFeeAmountArray;
	}
	public String getIsconcession() {
		return isconcession;
	}
	public void setIsconcession(String isconcession) {
		this.isconcession = isconcession;
	}
	public String getFeecode() {
		return feecode;
	}
	public void setFeecode(String feecode) {
		this.feecode = feecode;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public double getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(double actualAmt) {
		this.actualAmt = actualAmt;
	}
	public double getConcessionAmt() {
		return concessionAmt;
	}
	public void setConcessionAmt(double concessionAmt) {
		this.concessionAmt = concessionAmt;
	}
	public double getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(double paidAmt) {
		this.paidAmt = paidAmt;
	}
	

}
