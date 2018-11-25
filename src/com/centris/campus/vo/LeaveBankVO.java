package com.centris.campus.vo;

import java.util.ArrayList;

public class LeaveBankVO {
	

	private String sno;
	private String accyear;
	private String totalleaves;
	private String permonth;
	private String  accyearcode;
	private String academicyear;
	private String locationName;
	private String locId;
	private String consumedLeave;
	private double available_leave;
	//private String leavebankCheckBox;
	private String leaveName;
	private String shortName;
	private String noofleaves;
	private String leaveID;
	private String leavetypeName; 
	
	private double sl;
	private double pl;
	private double cl;
	private String createuser;
		
	private String cat_id;
	private String cat_name;
	private int count;
	
	private String userID;
	private String custid;
	
	private String leaveCodes[];
	private String leaveNames[];
	private String noofLeaves[];
	private String accumuLation[];
	private String carryF[];
	private String accumuLations;
	private String carryFs;
	private int leaveid;
	
	private String logaudit;
	private String mapstatus;
	private int slno;
	private String status;
	
	private String teacherId;
	private String teacherName;
	private String teaRegId;
	private int lmapId;
	private ArrayList<LeaveViewDetailsVo> details;
	private ArrayList<LeaveStatusListVO> leaveApprover;
	
	private String leave_cycle;
	private String custom_stdate;
	private String custom_enddate;
	private double isprobationary;
	private double min_lea_per_month;
	private double max_leav_per_month;
	private double max_consecu_lea_perm;
	private String week_off_inclusion;
	private String isGenderSpec;
	
	private double leavesapp;
	private double lconsumed;
	private double totLeaves;

	private ArrayList<String> daterange;
	
	public ArrayList<String> getDaterange() {
		return daterange;
	}
	public void setDaterange(ArrayList<String> daterange) {
		this.daterange = daterange;
	}
	public double getLeavesapp() {
		return leavesapp;
	}
	public void setLeavesapp(double leavesapp) {
		this.leavesapp = leavesapp;
	}
	public double getTotLeaves() {
		return totLeaves;
	}
	public void setTotLeaves(double totLeaves) {
		this.totLeaves = totLeaves;
	}
	public double getLconsumed() {
		return lconsumed;
	}
	public void setLconsumed(double lconsumed) {
		this.lconsumed = lconsumed;
	}
	public String getIsGenderSpec() {
		return isGenderSpec;
	}
	public void setIsGenderSpec(String isGenderSpec) {
		this.isGenderSpec = isGenderSpec;
	}
	public String getWeek_off_inclusion() {
		return week_off_inclusion;
	}
	public void setWeek_off_inclusion(String week_off_inclusion) {
		this.week_off_inclusion = week_off_inclusion;
	}
	public String getLeave_cycle() {
		return leave_cycle;
	}
	public void setLeave_cycle(String leave_cycle) {
		this.leave_cycle = leave_cycle;
	}
	public String getCustom_stdate() {
		return custom_stdate;
	}
	public void setCustom_stdate(String custom_stdate) {
		this.custom_stdate = custom_stdate;
	}
	public String getCustom_enddate() {
		return custom_enddate;
	}
	public void setCustom_enddate(String custom_enddate) {
		this.custom_enddate = custom_enddate;
	}
	public double getIsprobationary() {
		return isprobationary;
	}
	public void setIsprobationary(double isprobationary) {
		this.isprobationary = isprobationary;
	}
	public double getMin_lea_per_month() {
		return min_lea_per_month;
	}
	public void setMin_lea_per_month(double min_lea_per_month) {
		this.min_lea_per_month = min_lea_per_month;
	}
	public double getMax_leav_per_month() {
		return max_leav_per_month;
	}
	public void setMax_leav_per_month(double max_leav_per_month) {
		this.max_leav_per_month = max_leav_per_month;
	}
	public double getMax_consecu_lea_perm() {
		return max_consecu_lea_perm;
	}
	public void setMax_consecu_lea_perm(double max_consecu_lea_perm) {
		this.max_consecu_lea_perm = max_consecu_lea_perm;
	}
	public ArrayList<LeaveStatusListVO> getLeaveApprover() {
		return leaveApprover;
	}
	public void setLeaveApprover(ArrayList<LeaveStatusListVO> leaveApprover) {
		this.leaveApprover = leaveApprover;
	}
	public ArrayList<LeaveViewDetailsVo> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<LeaveViewDetailsVo> details) {
		this.details = details;
	}
	public int getLmapId() {
		return lmapId;
	}
	public void setLmapId(int lmapId) {
		this.lmapId = lmapId;
	}
	public String getTeaRegId() {
		return teaRegId;
	}
	public void setTeaRegId(String teaRegId) {
		this.teaRegId = teaRegId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMapstatus() {
		return mapstatus;
	}
	public void setMapstatus(String mapstatus) {
		this.mapstatus = mapstatus;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public int getLeaveid() {
		return leaveid;
	}
	public void setLeaveid(int leaveid) {
		this.leaveid = leaveid;
	}
	public String getLogaudit() {
		return logaudit;
	}
	public void setLogaudit(String logaudit) {
		this.logaudit = logaudit;
	}
	public String getAccumuLations() {
		return accumuLations;
	}
	public void setAccumuLations(String accumuLations) {
		this.accumuLations = accumuLations;
	}
	public String getCarryFs() {
		return carryFs;
	}
	public void setCarryFs(String carryFs) {
		this.carryFs = carryFs;
	}
	public String[] getLeaveCodes() {
		return leaveCodes;
	}
	public void setLeaveCodes(String[] leaveCodes) {
		this.leaveCodes = leaveCodes;
	}
	public String[] getLeaveNames() {
		return leaveNames;
	}
	public void setLeaveNames(String[] leaveNames) {
		this.leaveNames = leaveNames;
	}
	public String[] getNoofLeaves() {
		return noofLeaves;
	}
	public void setNoofLeaves(String[] noofLeaves) {
		this.noofLeaves = noofLeaves;
	}
	public String[] getAccumuLation() {
		return accumuLation;
	}
	public void setAccumuLation(String[] accumuLation) {
		this.accumuLation = accumuLation;
	}
	public String[] getCarryF() {
		return carryF;
	}
	public void setCarryF(String[] carryF) {
		this.carryF = carryF;
	}
	public String getLeavetypeName() {
		return leavetypeName;
	}
	public void setLeavetypeName(String leavetypeName) {
		this.leavetypeName = leavetypeName;
	}
	public double getAvailable_leave() {
		return available_leave;
	}
	public void setAvailable_leave(double available_leave) {
		this.available_leave = available_leave;
	}
	public String getConsumedLeave() {
		return consumedLeave;
	}
	public void setConsumedLeave(String consumedLeave) {
		this.consumedLeave = consumedLeave;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count2) {
		this.count = count2;
	}
	public String getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
	}
	public String getLeaveName() {
		return this.leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getNoofleaves() {
		return noofleaves;
	}
	public void setNoofleaves(String noofleaves) {
		this.noofleaves = noofleaves;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/*public String getLeavebankCheckBox() {
		return leavebankCheckBox;
	}
	public void setLeavebankCheckBox(String leavebankCheckBox) {
		this.leavebankCheckBox = leavebankCheckBox;
	}*/
	
	
	
	
	public String getAcademicyear() {
		return academicyear;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public double getSl() {
		return sl;
	}
	public void setSl(double sl) {
		this.sl = sl;
	}
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getAccyear() {
		return accyear;
	}
	public void setAccyear(String accyear) {
		this.accyear = accyear;
	}
	public String getTotalleaves() {
		return totalleaves;
	}
	public void setTotalleaves(String totalleaves) {
		this.totalleaves = totalleaves;
	}
	public String getPermonth() {
		return permonth;
	}
	public void setPermonth(String permonth) {
		this.permonth = permonth;
	}
	public String getAccyearcode() {
		return accyearcode;
	}
	public void setAccyearcode(String accyearcode) {
		this.accyearcode = accyearcode;
	}
	
	public double getsl() {
		return sl;
	}
	public void setsl(double hl) {
		this.sl = hl;
	}
	public double getPl() {
		return pl;
	}
	public void setPl(double pl) {
		this.pl = pl;
	}
	public double getCl() {
		return cl;
	}
	public void setCl(double cl) {
		this.cl = cl;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	
	

}
