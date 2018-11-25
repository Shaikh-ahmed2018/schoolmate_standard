package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LeaveBankDAO;
import com.centris.campus.daoImpl.LeaveBankDAOIMPL;
import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.LeaveBankService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveViewDetailsVo;
import com.centris.campus.vo.UserDetailVO;

public class LeaveBankServiceImpl implements LeaveBankService {
	
	private static Logger logger = Logger
			.getLogger(LeaveBankServiceImpl.class);
	
	static LeaveBankDAO leavDao;

	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo,UserLoggingsPojo userLoggingsVo) {

		return new LeaveBankDAOIMPL().leavebanklist(vo,userLoggingsVo);
	}
	
	public String insertLeaveBankservice(LeaveBankForm aform){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankServiceImpl:insertLeaveBankservice: Starting");

		LeaveBankDAOIMPL dao = new LeaveBankDAOIMPL();

		String check = "";

		if(aform.getSno()==null||aform.getSno()=="")
		{
			

			LeaveBankVO vo = new LeaveBankVO();

			vo.setAcademicyear(aform.getAcademicyear());
			vo.setsl(aform.getSickleave());
			vo.setPl(aform.getPaidleave());
			vo.setCl(aform.getCasualleave());
			vo.setTotalleaves(aform.getTotalleaves());
			vo.setPermonth(aform.getPermonth());
			vo.setCreateuser(aform.getCreatedby());
			check = dao.insertLeaveBanksDAO(vo);
		}

		else if (!(aform.getSno() == null))

		{
			LeaveBankVO vo = new LeaveBankVO();
			System.out.println("Updateleavebank  serviceImpl is Working");

			vo.setAcademicyear(aform.getAcademicyear());

			vo.setsl(aform.getSickleave());

			vo.setCl(aform.getCasualleave());

			vo.setTotalleaves(aform.getTotalleaves());

			vo.setPermonth(aform.getPermonth());

			vo.setAcademicyear(aform.getAcademicyear());

			vo.setSno(aform.getSno());

			vo.setCreateuser(aform.getCreatedby());

			check = dao.updateLeaveBanksDAO(vo);

		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankServiceImpl:insertLeaveBankservice: Ending");
		return check;
	}

	public LeaveBankForm editleavebank(LeaveBankForm aform)
	{
		return new LeaveBankDAOIMPL().editleavebank(aform);
	}

	@Override
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) {
		return new LeaveBankDAOIMPL().getSearchDetails(searchTextVal);

	}

	@Override
	public Boolean deleteLeave(String[] deletelist)
	{
		// TODO Auto-generated method stub
		return new LeaveBankDAOIMPL().deleteLeave(deletelist);
	}

	@Override
	public LeaveRequestVo getLeaveAprrovedDetails(LeaveRequestVo leavevo)
			{
		return new LeaveBankDAOIMPL().getLeaveAprrovedDetails(leavevo);
	}

	public Boolean validAddLeave(String year) {
		return new LeaveBankDAOIMPL().validAddLeave(year);
	}

	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {
		return new LeaveBankDAOIMPL().getviewLeaveDetails(userId);
	}

	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location, String log_audit_session) {
		return new LeaveBankDAOIMPL().addLeavesCategory(categorynames,shortnames,noofleaves,catId,accyear,location,log_audit_session);
	}

	public ArrayList<LeaveBankVO> getleaveCatList(UserLoggingsPojo dbdetails) {
		return new LeaveBankDAOIMPL().getleaveCatList(dbdetails);
	}

	public ArrayList<LeaveBankVO> getleavetypeDetails(String locId, String academic_year, UserLoggingsPojo dbdetails) {
		return new LeaveBankDAOIMPL().getleavetypeDetails(locId,academic_year,dbdetails);
	}

	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		return new LeaveBankDAOIMPL().editleavetypes(accyear,loc);
	}

	public String checkLeaveType(String accyear, String loc, String category) {
		return new LeaveBankDAOIMPL().editleavetypes(accyear,loc,category);
	}

	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year, UserLoggingsPojo dbdetails) {
		return new LeaveBankDAOIMPL().getSearchleavetypeDetails(searchTearm,academic_year,dbdetails);
	}

	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		return new LeaveBankDAOIMPL().updateLeavesCategory(hiddenLEaveIdArray,categorynames,shortnames,noofleaves,catId,accyear,location);
	}

	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		// TODO Auto-generated method stub
		return new LeaveBankDAOIMPL().getaccLocCatList(accyear,loc);
	}

	public String checkDuplicacy(String accyear, String loc) {
		return new LeaveBankDAOIMPL().checkDuplicacy(accyear,loc);
	}

	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		return new LeaveBankDAOIMPL().getleavenamesList(academic_year);
	}

	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getleaveusertype(parentid,academic_year);
	}

	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getleaveBalance(parentid,academic_year);
	}

	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getLeaveCalculation(parentid,academic_year);
	}

	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		return new LeaveBankDAOIMPL().getNewLeaveCalculation(parentid,academic_year);
	}

	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		return new LeaveBankDAOIMPL().checkLeaveCount(obj);
	}

	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		return new LeaveBankDAOIMPL().checkDateDuplicacy(startDate,toDate,leavetype,parentid);
	}

	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year, UserLoggingsPojo custdetails) {
		return new LeaveBankDAOIMPL().getviewNewLeaveDetails(trim,academic_year,custdetails);
	}


	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location,UserLoggingsPojo userLoggingsVo) {
		return new LeaveBankDAOIMPL().getLeaveTypes(academicYear,location,userLoggingsVo);
	}



	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		return new LeaveBankDAOIMPL().checkMonthleave(academic_year,parentid,fromDate,leavetype);
	}

	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType, UserLoggingsPojo userLoggingsVo) {
		return new LeaveBankDAOIMPL().getNoOfLeave(academicYear, location, leaveType,userLoggingsVo);
	}

	@Override
	public String addLeaveBank(LeaveBankVO leaveVo,UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.addLeaveBank(leaveVo,dbdetails);
	}

	@Override
	public String updateLeaveDetails(LeaveBankVO ldetails, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.updateLeaveDetails(ldetails,dbdetails);
	}

	@Override
	public String deleteLeaveDetails(int leaveid, UserLoggingsPojo dbdetails,String log_audit_session) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.deleteLeaveDetails(leaveid,dbdetails,log_audit_session);
	}

	@Override
	public String checkLeaveCode(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.checkLeaveCode(leavedetails,dbdetails);
	}

	@Override
	public String checkLeaveName(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.checkLeaveName(leavedetails,dbdetails);
	}

	@Override
	public List<LeaveBankVO> getLeaveMapStatus(String academic_year, String location,UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getLeaveMapStatus(academic_year,location,dbdetails);
	}

	@Override
	public List<LeaveBankVO> getStaffMapSt(UserLoggingsPojo dbdetails, String accyloc) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffMapSt(dbdetails,accyloc);
	}

	@Override
	public String addStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.addStaffLeaves(vo,dbdetails);
	}

	@Override
	public List<LeaveBankVO> getStaffMappedLeaves(UserLoggingsPojo dbdetails, String details) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffMappedLeaves(dbdetails,details);
	}

	@Override
	public String updateStafLeaves(UserLoggingsPojo dbdetails, String data, String leaveids, String appleave) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.updateStafLeaves(dbdetails,data,leaveids,appleave);
	}

	@Override
	public List<LeaveBankVO> getStaffUnmappedLeaves(UserLoggingsPojo dbdetails, String data) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffUnmappedLeaves(dbdetails,data);
	}

	@Override
	public String addSingleStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.addSingleStaffLeaves(vo,dbdetails);
	}

	@Override
	public List<LeaveBankVO> getStaffLeaveBankDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffLeaveBankDetails(academic_year,location,dbdetails);
	}

	@Override
	public ArrayList<LeaveBankVO> getleavenamesList(UserLoggingsPojo custdetails, UserDetailVO userDetailVO,
			String academic_year) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getleavenamesList(custdetails,userDetailVO,academic_year);
	}

	@Override
	public ArrayList<LeaveBankVO> getApprovers(String aid,UserLoggingsPojo custdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getApprovers(aid,custdetails);
	}

	@Override
	public ArrayList<LeaveBankVO> getleavepolicies(String aid, UserLoggingsPojo custdetails,String academic_year) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getleavepolicies(aid,custdetails,academic_year);
	}

	@Override
	public ArrayList<LeaveBankVO> getStaffleaveBalance(String academic_year, UserDetailVO userDetailVO,
			UserLoggingsPojo custdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffleaveBalance(academic_year,userDetailVO,custdetails);
	}

	@Override
	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,String accyid) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getleaveRequestDetailsBD(userDetailVO,custdetails,accyid);
	}

	@Override
	public ArrayList<LeaveRequestVo> searchleaverequest(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,
			String searchtext,String accyid) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.searchleaverequest(userDetailVO,custdetails,searchtext,accyid);
	}

	@Override
	public LeaveRequestVo getStaffLeaveReq(String leaveid, UserLoggingsPojo custdetails, String academic_year) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffLeaveReq(leaveid,custdetails,academic_year);
	}

	@Override
	public String deleteStaffleave(LeaveRequestVo leavevo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.deleteStaffleave(leavevo);
	}

	@Override
	public ArrayList<LeaveRequestVo> searchleave(String searchTerm, LeaveRequestVo leavevo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.searchleave(searchTerm,leavevo);
	}

	@Override
	public ArrayList<LeaveRequestVo> getStaffleaveApproval(LeaveRequestVo leavevo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffleaveApproval(leavevo);
	}

	@Override
	public LeaveRequestVo getStaffLRDetails(LeaveRequestVo leavevo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffLRDetails(leavevo);
	}

	@Override
	public String ApprovingLeaveforleaveRequestBD(LeaveRequestVo leavevo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.ApprovingLeaveforleaveRequestBD(leavevo);
	}

	@Override
	public String addLeavePolicy(LeaveBankVO leaveVo, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.addLeavePolicy(leaveVo,dbdetails);
	}

	@Override
	public List<LeaveBankVO> getLeavePolicy(String leaveid, UserLoggingsPojo dbdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getLeavePolicy(leaveid,dbdetails);
	}


	@Override
	public String getleaveCountForApprove(String academic_year, UserLoggingsPojo userLoggingsVo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getleaveCountForApprove( academic_year,  userLoggingsVo);
	}

	@Override
	public String getStaffLeaveCountForApprove(String academic_year, String userId, UserLoggingsPojo userLoggingsVo) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getStaffLeaveCountForApprove( academic_year,  userId,  userLoggingsVo);
	}

	@Override
	public ArrayList<LeaveBankVO> getLeaveApprover(UserLoggingsPojo custdetails) {
		leavDao = new LeaveBankDAOIMPL();
		return leavDao.getLeaveApprover(custdetails);
	}
	
}
