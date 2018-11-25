package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LeaveBankService;
import com.centris.campus.serviceImpl.LeaveBankServiceImpl;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveViewDetailsVo;
import com.centris.campus.vo.UserDetailVO;


public class LeaveBankDelegate {
	
	static LeaveBankService service;
	
	public  ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo,UserLoggingsPojo userLoggingsVo){
		System.out.println("DesignationDetailsTable delegate is Working");
		return new LeaveBankServiceImpl().leavebanklist(vo,userLoggingsVo);
	}
     public String insertLeaveBankDelegate(LeaveBankForm aform){

			return new LeaveBankServiceImpl().insertLeaveBankservice(aform);
	}

	public LeaveBankForm editleavebankdelegate(LeaveBankForm aform){

			return new LeaveBankServiceImpl().editleavebank(aform);
	}
		
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal)

	{
		return new LeaveBankServiceImpl().getSearchDetails(searchTextVal);
		}
	
	public Boolean deleteLeave(String[] deletelist)
	{
		return new LeaveBankServiceImpl().deleteLeave(deletelist);
	}
	
	
	public  LeaveRequestVo getLeaveAprrovedDetails(LeaveRequestVo leavevo){
		
		System.out.println("DesignationDetailsTable delegate is Working");

			return new LeaveBankServiceImpl().getLeaveAprrovedDetails(leavevo);
		
	}
	public Boolean validAddLeave(String year) {
		return new LeaveBankServiceImpl().validAddLeave(year);
	}
	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {
		return new LeaveBankServiceImpl().getviewLeaveDetails(userId);
	}
	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location, String log_audit_session) {
		return new LeaveBankServiceImpl().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location,log_audit_session);
	}
	public ArrayList<LeaveBankVO> getleaveCatList(UserLoggingsPojo dbdetails) {
		return new LeaveBankServiceImpl().getleaveCatList(dbdetails);
	}
	public ArrayList<LeaveBankVO> getleavetypeDetails(String locId, String academic_year, UserLoggingsPojo dbdetails) {
		return new LeaveBankServiceImpl().getleavetypeDetails(locId,academic_year,dbdetails);
		
	}
	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		return new LeaveBankServiceImpl().editleavetypes(accyear,loc);
	}
	public String checkLeaveType(String accyear, String loc, String category) {
		return new LeaveBankServiceImpl().checkLeaveType(accyear,loc,category);
	}
	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year, UserLoggingsPojo dbdetails) {
		return new LeaveBankServiceImpl().getSearchleavetypeDetails(searchTearm,academic_year,dbdetails);
	}
	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankServiceImpl().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
	}
	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		
		return new LeaveBankServiceImpl().getaccLocCatList(accyear,loc);
	}
	public String checkDuplicacy(String accyear, String loc) {
		return new LeaveBankServiceImpl().checkDuplicacy(accyear,loc);
	}
	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		return new LeaveBankServiceImpl().getleavenamesList(academic_year);
	}
	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getleaveusertype(parentid,academic_year);
	}
	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getleaveBalance(parentid,academic_year);
	}
	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getLeaveCalculation(parentid,academic_year);
	}
	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankServiceImpl().getNewLeaveCalculation(parentid,academic_year);
	}
	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		return new LeaveBankServiceImpl().checkLeaveCount(obj);
	}
	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		return new LeaveBankServiceImpl().checkDateDuplicacy(startDate,toDate,leavetype,parentid);
	}
	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year, UserLoggingsPojo custdetails) {
		return new LeaveBankServiceImpl().getviewNewLeaveDetails(trim,academic_year,custdetails);
	}

	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location, UserLoggingsPojo userLoggingsVo) {
		return new LeaveBankServiceImpl().getLeaveTypes(academicYear,location,userLoggingsVo);
	}

	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		return new LeaveBankServiceImpl().checkMonthleave(academic_year,parentid,fromDate,leavetype);
	}
	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType,UserLoggingsPojo userLoggingsVo) {
		return new LeaveBankServiceImpl().getNoOfLeave(academicYear,location,leaveType,userLoggingsVo);
	}
	public String addLeaveBank(LeaveBankVO leaveVo, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.addLeaveBank(leaveVo,dbdetails);
	}

	public String updateLeaveDetails(LeaveBankVO ldetails, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.updateLeaveDetails(ldetails,dbdetails);
	}
	public String deleteLeaveDetails(int leaveid, UserLoggingsPojo dbdetails, String log_audit_session) {
		service = new LeaveBankServiceImpl();
		return service.deleteLeaveDetails(leaveid,dbdetails,log_audit_session);
	}
	public String checkLeaveCode(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.checkLeaveCode(leavedetails,dbdetails);
	}
	public String checkLeaveName(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.checkLeaveName(leavedetails,dbdetails);
	}
	public List<LeaveBankVO> getLeaveMapStatus(String academic_year, String location, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.getLeaveMapStatus(academic_year,location,dbdetails);
	}
	public List<LeaveBankVO> getStaffMapSt(UserLoggingsPojo dbdetails, String accyloc) {
		service = new LeaveBankServiceImpl();
		return service.getStaffMapSt(dbdetails,accyloc);
	}
	public String addStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.addStaffLeaves(vo,dbdetails);
	}
	public List<LeaveBankVO> getStaffMappedLeaves(UserLoggingsPojo dbdetails, String details) {
		service = new LeaveBankServiceImpl();
		return service.getStaffMappedLeaves(dbdetails,details);
	}
	public String updateStafLeaves(UserLoggingsPojo dbdetails, String data, String leaveids, String appleave) {
		service = new LeaveBankServiceImpl();
		return service.updateStafLeaves(dbdetails,data,leaveids,appleave);
	}
	public List<LeaveBankVO> getStaffUnmappedLeaves(UserLoggingsPojo dbdetails, String data) {
		service = new LeaveBankServiceImpl();
		return service.getStaffUnmappedLeaves(dbdetails,data);
	}
	public String addSingleStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.addSingleStaffLeaves(vo,dbdetails);
	}
	public List<LeaveBankVO> getStaffLeaveBankDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.getStaffLeaveBankDetails(academic_year,location,dbdetails);
	}
	public ArrayList<LeaveBankVO> getleavenamesList(UserLoggingsPojo custdetails, UserDetailVO userDetailVO, String academic_year) {
		service = new LeaveBankServiceImpl();
		return service.getleavenamesList(custdetails,userDetailVO,academic_year);
	}
	public ArrayList<LeaveBankVO> getApprovers(String aid, UserLoggingsPojo custdetails) {
		service = new LeaveBankServiceImpl();
		return service.getApprovers(aid,custdetails);
	}
	public ArrayList<LeaveBankVO> getleavepolicies(String aid, UserLoggingsPojo custdetails, String academic_year) {
		service = new LeaveBankServiceImpl();
		return service.getleavepolicies(aid,custdetails,academic_year);
	}
	public ArrayList<LeaveBankVO> getStaffleaveBalance(String academic_year, UserDetailVO userDetailVO,
			UserLoggingsPojo custdetails) {
		service = new LeaveBankServiceImpl();
		return service.getStaffleaveBalance(academic_year,userDetailVO,custdetails);
	}
	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(UserDetailVO userDetailVO, UserLoggingsPojo custdetails, String academic_year) {
		service = new LeaveBankServiceImpl();
		return service.getleaveRequestDetailsBD(userDetailVO,custdetails,academic_year);
	}
	public ArrayList<LeaveRequestVo> searchleaverequest(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,
			String searchtext, String academic_year) {
		service = new LeaveBankServiceImpl();
		return service.searchleaverequest(userDetailVO,custdetails,searchtext,academic_year);
	}
	public LeaveRequestVo getStaffLeaveReq(String leaveid, UserLoggingsPojo custdetails, String academic_year) {
		service = new LeaveBankServiceImpl();
		return service.getStaffLeaveReq(leaveid,custdetails,academic_year);
	}
	public String deleteStaffleave(LeaveRequestVo leavevo) {
		service = new LeaveBankServiceImpl();
		return service.deleteStaffleave(leavevo);
	}
	public ArrayList<LeaveRequestVo> searchleave(String searchTerm, LeaveRequestVo leavevo) {
		service = new LeaveBankServiceImpl();
		return service.searchleave(searchTerm,leavevo);
	}
	public ArrayList<LeaveRequestVo> getStaffleaveApproval(LeaveRequestVo leavevo) {
		service = new LeaveBankServiceImpl();
		return service.getStaffleaveApproval(leavevo);
	}
	public LeaveRequestVo getStaffLRDetails(LeaveRequestVo leavevo) {
		service = new LeaveBankServiceImpl();
		return service.getStaffLRDetails(leavevo);
	}
	public String ApprovingLeaveforleaveRequestBD(LeaveRequestVo leavevo) {
		service = new LeaveBankServiceImpl();
		return service.ApprovingLeaveforleaveRequestBD(leavevo);
	}

	public String addLeavePolicy(LeaveBankVO leaveVo, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.addLeavePolicy(leaveVo,dbdetails);
	}
	public List<LeaveBankVO> getLeavePolicy(String leaveid, UserLoggingsPojo dbdetails) {
		service = new LeaveBankServiceImpl();
		return service.getLeavePolicy(leaveid,dbdetails);
	}

	public String getleaveCountForApprove(String academic_year, UserLoggingsPojo userLoggingsVo) {
		service = new LeaveBankServiceImpl();
		return service.getleaveCountForApprove( academic_year,  userLoggingsVo);
	}
	public String getStaffLeaveCountForApprove(String academic_year, String userId, UserLoggingsPojo userLoggingsVo) {
		service = new LeaveBankServiceImpl();
		return service.getStaffLeaveCountForApprove(  academic_year,  userId,  userLoggingsVo);
	}
	public ArrayList<LeaveBankVO> getLeaveApprover(UserLoggingsPojo custdetails) {
		service = new LeaveBankServiceImpl();
		return service.getLeaveApprover(custdetails);
	}

}
