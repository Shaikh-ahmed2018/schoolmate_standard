package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.UserDetailVO;

public interface LeaveBankDAO {
	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo,UserLoggingsPojo userLoggingsVo);
	
	public String insertLeaveBanksDAO(LeaveBankVO vo);
	
	
	public LeaveBankForm editleavebank(LeaveBankForm aform);

	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) ;
	public Boolean deleteLeave(String[] deletelist);
	public LeaveRequestVo getLeaveAprrovedDetails(
			LeaveRequestVo leavevo);

	public String addLeaveBank(LeaveBankVO leaveVo,UserLoggingsPojo dbdetails);

	public String updateLeaveDetails(LeaveBankVO ldetails, UserLoggingsPojo dbdetails);

	public String deleteLeaveDetails(int leaveid, UserLoggingsPojo dbdetails, String log_audit_session);

	public String checkLeaveCode(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails);

	public String checkLeaveName(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails);

	public List<LeaveBankVO> getLeaveMapStatus(String academic_year, String location, UserLoggingsPojo dbdetails);

	public List<LeaveBankVO> getStaffMapSt(UserLoggingsPojo dbdetails, String accyloc);

	public String addStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails);

	public List<LeaveBankVO> getStaffMappedLeaves(UserLoggingsPojo dbdetails, String details);

	public String updateStafLeaves(UserLoggingsPojo dbdetails, String data, String leaveids, String appleave);

	public List<LeaveBankVO> getStaffUnmappedLeaves(UserLoggingsPojo dbdetails, String data);

	public String addSingleStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails);

	public List<LeaveBankVO> getStaffLeaveBankDetails(String academic_year, String location, UserLoggingsPojo dbdetails);

	public ArrayList<LeaveBankVO> getleavenamesList(UserLoggingsPojo custdetails, UserDetailVO userDetailVO,
			String academic_year);

	public ArrayList<LeaveBankVO> getApprovers(String aid, UserLoggingsPojo custdetails);

	public ArrayList<LeaveBankVO> getStaffleaveBalance(String academic_year, UserDetailVO userDetailVO,
			UserLoggingsPojo custdetails);

	public ArrayList<LeaveBankVO> getleavepolicies(String aid, UserLoggingsPojo custdetails, String academic_year);

	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(UserDetailVO userDetailVO, UserLoggingsPojo custdetails, String accyid);

	public ArrayList<LeaveRequestVo> searchleaverequest(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,
			String searchtext, String accyid);

	public LeaveRequestVo getStaffLeaveReq(String leaveid, UserLoggingsPojo custdetails, String academic_year);

	public String deleteStaffleave(LeaveRequestVo leavevo);

	public ArrayList<LeaveRequestVo> searchleave(String searchTerm, LeaveRequestVo leavevo);

	public ArrayList<LeaveRequestVo> getStaffleaveApproval(LeaveRequestVo leavevo);

	public LeaveRequestVo getStaffLRDetails(LeaveRequestVo leavevo);

	public String ApprovingLeaveforleaveRequestBD(LeaveRequestVo leavevo);


	public String addLeavePolicy(LeaveBankVO leaveVo, UserLoggingsPojo dbdetails);

	public List<LeaveBankVO> getLeavePolicy(String leaveid, UserLoggingsPojo dbdetails);


	public String getleaveCountForApprove(String academic_year, UserLoggingsPojo userLoggingsVo);

	public String getStaffLeaveCountForApprove(String academic_year, String userId, UserLoggingsPojo userLoggingsVo);

	public ArrayList<LeaveBankVO> getLeaveApprover(UserLoggingsPojo custdetails);


}
