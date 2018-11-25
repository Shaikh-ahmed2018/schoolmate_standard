package com.centris.campus.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LeaveBankDAO;
import com.centris.campus.delegate.LocationBD;

import com.centris.campus.forms.LeaveBankForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeaveBankSqlUtil;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ParentModuleUtil;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.TeacherUtilsConstants;

import com.centris.campus.vo.LeaveBankVO;
import com.centris.campus.vo.LeaveCalculation;
import com.centris.campus.vo.LeaveRequestVo;
import com.centris.campus.vo.LeaveStatusListVO;
import com.centris.campus.vo.LeaveViewDetailsVo;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.SystemSpecificationVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.util.HelperClass;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LeaveBankDAOIMPL implements LeaveBankDAO {
	private static Logger logger = Logger.getLogger(LeaveBankDAOIMPL.class);

	@Override
	public ArrayList<LeaveBankVO> leavebanklist(LeaveBankVO vo,UserLoggingsPojo userLoggingsVo)

	{

		//("Leave BAnk DAOIMPL is Working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL: leavebanklist : Starting");
		PreparedStatement leavebank_pstmt = null;

		ResultSet leavebank_rs = null;

		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();

		Connection conn = null;
		if (vo.getAccyearcode() == null || vo.getAccyearcode().equalsIgnoreCase(""))

		{
			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				leavebank_pstmt = conn.prepareStatement(SQLUtilConstants.LEAVEBANK_DETAILS);

				leavebank_rs = leavebank_pstmt.executeQuery();

				while (leavebank_rs.next()) {
					LeaveBankVO vo1 = new LeaveBankVO();
					vo1.setSno(leavebank_rs.getString("sno"));
					vo1.setAccyear(leavebank_rs.getString("acadamic_id"));
					vo1.setAcademicyear(leavebank_rs.getString("acadamic_year"));
					
					vo1.setTotalleaves(leavebank_rs.getString("total_leaves"));
					
					vo1.setPermonth(leavebank_rs.getString("allowed_per_month"));
					
					vo1.setsl(leavebank_rs.getDouble("SL"));
					vo1.setPl(leavebank_rs.getDouble("PL"));
					vo1.setCl(leavebank_rs.getDouble("CL"));
					list.add(vo1);
				}
			}

			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (leavebank_rs != null && (!leavebank_rs.isClosed())) {
						leavebank_rs.close();
					}
					if (leavebank_pstmt != null
							&& (!leavebank_pstmt.isClosed())) {
						leavebank_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}
		} else if (!vo.getAccyearcode().equalsIgnoreCase(""))

			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

				leavebank_pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_LEAVEBANKDETAILS);

				leavebank_pstmt.setString(1, "%" + vo.getAccyearcode() + "%");
				leavebank_pstmt.setString(2, "%" + vo.getAccyearcode() + "%");
				leavebank_pstmt.setString(3, "%" + vo.getAccyearcode() + "%");

				leavebank_rs = leavebank_pstmt.executeQuery();
				
				//("leavebank_pstmt"+leavebank_pstmt);

				while (leavebank_rs.next())

				{
					LeaveBankVO bat = new LeaveBankVO();

					bat.setAcademicyear(leavebank_rs.getString("acadamic_year"));
					
					bat.setTotalleaves(leavebank_rs.getString("total_leaves"));

					bat.setPermonth(leavebank_rs.getString("allowed_per_month"));

					list.add(bat);

				}

			} catch (SQLException e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
				try {
					if (leavebank_rs != null && (!leavebank_rs.isClosed())) {
						leavebank_rs.close();
					}
					if (leavebank_pstmt != null && (!leavebank_pstmt.isClosed())) {
						leavebank_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : leavebanklist Ending");
		return list;

	}

	
	@Override
	public String insertLeaveBanksDAO(LeaveBankVO vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:addLeave Starting");

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement psmtcheckjoiningdate = null;
		PreparedStatement pstmt_hrms = null;
		ResultSet rs=null;
		ResultSet rscheckjoiningdate=null;
		String dateofjoin = "",empid = "";
		String addstatus = "";
		if (!(validAddLeave(vo.getAcademicyear()))) {

			try {
				con = JDBCConnection.getSeparateConnection();
				java.sql.CallableStatement stmt= con.prepareCall("{call getAccYear()}");   
				 rs = stmt.executeQuery();  
				
				while(rs.next())
					
				{
				}
				
				psmtcheckjoiningdate = con.prepareStatement("select distinct TeacherID,FirstName,dateofJoining from campus_teachers where isActive='Y' ");
				
				rscheckjoiningdate = psmtcheckjoiningdate.executeQuery();
				
				while(rscheckjoiningdate.next())
				
				{
					
					empid = rscheckjoiningdate.getString("TeacherID");
					
					dateofjoin = rscheckjoiningdate.getString("dateofJoining");
					
					String [] doj = dateofjoin.split("-");
					
					String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");

					int noofmonths=(Integer.parseInt(currdate[0])-Integer.parseInt(doj[0]))*12;
					
					noofmonths=noofmonths+((12-(Integer.parseInt(doj[1])-1)+(Integer.parseInt(currdate[1])-12)));
					
						pstmt_hrms  = con.prepareStatement(SQLUtilConstants.ADD_LEAVE_TECHAER_LEAVE_BANK);
						
						
						pstmt_hrms.setString(1, vo.getAcademicyear());
						pstmt_hrms.setString(2, empid);
						pstmt_hrms.setDouble(3, vo.getsl());
						pstmt_hrms.setDouble(4, vo.getPl());
						pstmt_hrms.setDouble(5, vo.getCl());
						pstmt_hrms.setDouble(6, 0);
						pstmt_hrms.setString(7, vo.getTotalleaves());
						pstmt_hrms.setTimestamp(8, HelperClass.getCurrentTimestamp());
						pstmt_hrms.setString(9, vo.getCreateuser());
						
						int res = pstmt_hrms.executeUpdate();
						
						if (res > 0) {
							addstatus = "Leave Details Added successfully";
						}
				}		
				
				pstmt = con.prepareStatement(SQLUtilConstants.ADD_LEAVE);
				pstmt.setString(1, vo.getAcademicyear());
				pstmt.setString(2, vo.getTotalleaves());
				pstmt.setString(3, vo.getPermonth());
				pstmt.setString(4, vo.getCreateuser());
				pstmt.setDouble(5, vo.getsl());
				pstmt.setDouble(6, vo.getPl());
				pstmt.setDouble(7, vo.getCl());
				
				int res = pstmt.executeUpdate();
				if (res > 0) {
					addstatus = "Added successfully";
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error(e1.getMessage(), e1);
			} finally {
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
					if (rscheckjoiningdate != null && !rscheckjoiningdate.isClosed()) {
						rscheckjoiningdate.close();
					}
					
					if (psmtcheckjoiningdate != null && !psmtcheckjoiningdate.isClosed()) {
						psmtcheckjoiningdate.close();
					}
					if (pstmt_hrms != null && !pstmt_hrms.isClosed()) {
						pstmt_hrms.close();
					}

					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}

					if (con != null && (!con.isClosed())) {
						con.close();

					}

				} catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle.getMessage(), sqle);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1.getMessage(), e1);
				}
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:addLeave Ending");
		return addstatus;
}

	public String updateLeaveBanksDAO(LeaveBankVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:updateLeaveBanksDAO:Starting");

		PreparedStatement pstmt = null;

		String status = null;

		Connection conn = null;

		int result1 = 0;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.UPDATE_LEAVEBANK_DETAILS);
			pstmt.setString(1, vo.getTotalleaves());
			pstmt.setString(2, vo.getPermonth());
			pstmt.setDouble(3, vo.getsl());
			pstmt.setDouble(4, vo.getPl());
			pstmt.setDouble(5, vo.getCl());
			pstmt.setString(6, vo.getAcademicyear());
			pstmt.setString(7, vo.getSno());

			result1 = pstmt.executeUpdate();

			//("pstmtpstmt" + pstmt);
			//("result1" + result1);

			if (result1 > 0) {

				status = MessageConstants.UPDATE_LEAVEBANK_SUCCESS;

			} else {
				status = MessageConstants.UPDATE_LEAVEBANK_FAIL;

			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : updateLeaveBanksDAO Ending");
		return status;
	}

	public LeaveBankForm editleavebank(LeaveBankForm aform)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:editleavebank:Starting");

		PreparedStatement leavebank_pstmt = null;

		ResultSet leavebankrs = null;

		Connection conn = null;
		LeaveBankForm leavebankVO = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			leavebank_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_LEAVEBANK_DETAILS);

			leavebank_pstmt.setString(1, aform.getSno());

			leavebankrs = leavebank_pstmt.executeQuery();

			while (leavebankrs.next()) {
				leavebankVO = new LeaveBankForm();

				leavebankVO.setAcademicyear(leavebankrs
						.getString("AccyearCode"));
				leavebankVO.setTotalleaves(leavebankrs
						.getString("total_leaves"));
				leavebankVO.setPermonth(leavebankrs
						.getString("allowed_per_month"));
				leavebankVO.setCreatedby(leavebankrs.getString("CreatedBy"));
				leavebankVO.setSickleave(leavebankrs.getDouble("SL"));
				leavebankVO.setPaidleave(leavebankrs.getDouble("PL"));
				leavebankVO.setCasualleave(leavebankrs.getDouble("CL"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (leavebankrs != null && (!leavebankrs.isClosed())) {
					leavebankrs.close();
				}
				if (leavebank_pstmt != null && (!leavebank_pstmt.isClosed())) {
					leavebank_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :editleavebank Ending");
		return leavebankVO;

	}

	@Override
	public ArrayList<LeaveBankVO> getSearchDetails(String searchTextVal) {

		logger.setLevel(Level.DEBUG);

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getSearchDetails Starting");

		ArrayList<LeaveBankVO> bat_Details = new ArrayList<LeaveBankVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(SQLUtilConstants.GET_SEARCH_LEAVEBANKDETAILS);

			pstmt.setString(1, "%" + searchTextVal + "%");
			pstmt.setString(2, "%" + searchTextVal + "%");
			pstmt.setString(3, "%" + searchTextVal + "%");

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				LeaveBankVO bat = new LeaveBankVO();

				bat.setAcademicyear(rs.getString("AccyearCode"));

				bat.setTotalleaves(rs.getString("total_leaves"));

				bat.setPermonth(rs.getString("allowed_per_month"));

				bat_Details.add(bat);

			}

		} catch (SQLException e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getSearchDetails Ending");

		return bat_Details;

	}

	@Override
	public Boolean deleteLeave(String[] deletelist) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:delete Leave Starting");

		Connection con = null;
		PreparedStatement pstmt = null;

		Boolean deletestatus = false;

		try {
			con = JDBCConnection.getSeparateConnection();
			
			for (int i = 0; i < deletelist.length; i++) {
				pstmt = con.prepareStatement(SQLUtilConstants.DELETE_LEAVEBANK_DETAILS);
				pstmt.setString(1, deletelist[i]);
				int count = pstmt.executeUpdate();
				
				//("pstmt"+pstmt);
				
				if (count > 0) {
					deletestatus = true;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (con != null && (!con.isClosed())) {
					con.close();

				}
			} catch (SQLException sql) {
				logger.error(sql.getMessage(), sql);
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:deleteLeave  Ending");
		return deletestatus;
	
	}

	@Override
	public LeaveRequestVo getLeaveAprrovedDetails(
			LeaveRequestVo leavevo) {
		//("DAOIMPL Is Working");


		//("Leave BAnk DAOIMPL is Working");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL: getLeaveAprrovedDetails : Starting");
		
		PreparedStatement leaveapproved_pstmt = null;
		ResultSet leaveapproved_rs = null;
		PreparedStatement leaveapproved_pstmt1 = null;
		ResultSet leaveapproved_rs1 = null;
		LeaveRequestVo obj = new LeaveRequestVo();
		Connection conn = null;
		
		try {
				conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
				leaveapproved_pstmt = conn.prepareStatement("select trlq.ApprovedBy,trlq.AprovedDate,trlq.TotalDaysAproved,trlq.commennts,trlq.ApprovedStartDate,trlq.ApprovedEndDate,trlq.ApproveSessionStart,trlq.ApproveSessionEnd,concat(teach.FirstName,' ',teach.LastName)name from campus_teachers_leave_request trlq join campus_teachers teach on teach.TeacherID=trlq.ApprovedBy where SNO=?");
				leaveapproved_pstmt.setString(1, leavevo.getSno1());
				//("leaveapproved_pstmt::::"+leaveapproved_pstmt);
				leaveapproved_rs = leaveapproved_pstmt.executeQuery();
				while (leaveapproved_rs.next()) {
					
					obj.setUserid(leaveapproved_rs.getString("name"));
					obj.setApproveddate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("AprovedDate")));
					obj.setLeavesapproved(leaveapproved_rs.getString("TotalDaysAproved"));
					obj.setComments(leaveapproved_rs.getString("commennts"));
					obj.setApprovedstartdate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("ApprovedStartDate")));
					obj.setApprovedenddate(HelperClass.convertDatabaseToUI(leaveapproved_rs.getString("ApprovedEndDate")));
					obj.setApprovedstartsessionDay(leaveapproved_rs.getString("ApproveSessionStart"));
					obj.setApprovedendsessionDay(leaveapproved_rs.getString("ApproveSessionEnd"));
				
				}
			}
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (leaveapproved_rs != null && (!leaveapproved_rs.isClosed())) {
						leaveapproved_rs.close();
					}
					if (leaveapproved_pstmt != null
							&& (!leaveapproved_pstmt.isClosed())) {
						leaveapproved_pstmt.close();
					}
					if (leaveapproved_rs1 != null && (!leaveapproved_rs1.isClosed())) {
						leaveapproved_rs1.close();
					}
					if (leaveapproved_pstmt1 != null
							&& (!leaveapproved_pstmt1.isClosed())) {
						leaveapproved_pstmt1.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException sqle) {

					logger.error(sqle.getMessage(), sqle);
					sqle.printStackTrace();
				} catch (Exception e1) {

					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
			return obj;
	}

	public Boolean validAddLeave(String year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:validAddLeave Starting");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		Boolean status = false;
		try {
			con = JDBCConnection.getSeparateConnection();

			pstmt = con.prepareStatement(SQLUtilConstants.COUNT_LEAVE_YEAR);
			pstmt.setString(1, year);

			res = pstmt.executeQuery();
			int yearCount = 0;
			while (res.next()) {
				yearCount = res.getInt(1);

			}
			if (yearCount > 0) {
				status = true;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (res != null && !res.isClosed()) {
					res.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (con != null && (!con.isClosed())) {
					con.close();

				}
			} catch (SQLException sql) {
				logger.error(sql.getMessage(), sql);
				sql.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL:validAddLeave  Ending");
		return status;
	}

	public ArrayList<LeaveViewDetailsVo> getviewLeaveDetails(String userId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		PreparedStatement ps_leavebank = null,ps_attendance = null,ps_getemp = null,ps_getcl = null;
		ResultSet rs_leavebank = null,rs_attendance= null,rs_getemp= null,rs_getcl= null;
		
		Connection connection=null;
		
		ArrayList<LeaveViewDetailsVo> leaveviewlist = new ArrayList<LeaveViewDetailsVo>();
		
		try{

			boolean flag=true;
			connection=JDBCConnection.getSeparateConnection();

			ps_getemp=connection.prepareStatement(SQLUtilConstants.GET_EMP_DOJ_YEAR);
			ps_getemp.setString(1,userId);
			//("ps_getemp is "+ps_getemp);
			/*int currentYear=HelperClass.getCurrentYear();*/

			/*ArrayList<String> yearCode=new ArrayList<String>();*/

			rs_getemp = ps_getemp.executeQuery();
			if (rs_getemp.next()) {
				String 	empyear= rs_getemp.getString("acadamic_year");
				String  empyearcode = rs_getemp.getString("acadamic_id");
				String month=rs_getemp.getString("month");

				ps_leavebank=connection.prepareStatement(SQLUtilConstants.GET_SL_LEAVES);
				ps_leavebank.setString(1, empyearcode);
				ps_leavebank.setString(2, userId);
				//("ps_leavebank"+ps_leavebank);
				rs_leavebank=ps_leavebank.executeQuery();

				while (rs_leavebank.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear(empyear);
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_SL);

					double totopenings=rs_leavebank.getDouble("slopenbal");
					
					double monthopeningd=totopenings/12;

					if(flag){
						double afetrdojopenings=monthopeningd *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_leavebank.getDouble("slconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_leavebank.getDouble("slopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_leavebank.getDouble("slopenbal")-rs_leavebank.getDouble("slconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_leavebank.getDouble("slconsumebal"));


					leaveviewlist.add(leaveViewDetailsVo);

				}

				ps_attendance=connection.prepareStatement(SQLUtilConstants.GET_PL_LEAVES);
				ps_attendance.setString(1,empyearcode);
				ps_attendance.setString(2,userId);
				//("ps_attendance"+ps_attendance);
				rs_attendance=ps_attendance.executeQuery();

				while (rs_attendance.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear("");
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_PL);

					double totopenings=rs_attendance.getDouble("plopenbal");
					double monthopenings=totopenings/12;

					if(flag){

						double afetrdojopenings=monthopenings *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_attendance.getDouble("plconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_attendance.getDouble("plopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_attendance.getDouble("plopenbal")-rs_attendance.getDouble("plconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_attendance.getDouble("plconsumebal"));

					leaveviewlist.add(leaveViewDetailsVo);

				}


				ps_getcl=connection.prepareStatement(SQLUtilConstants.GET_CL_LEAVES);
				ps_getcl.setString(1,empyearcode);
				ps_getcl.setString(2,userId);
				//("ps_getcl"+ps_getcl);
				rs_getcl=ps_getcl.executeQuery();

				while (rs_getcl.next()) {

					LeaveViewDetailsVo leaveViewDetailsVo=new LeaveViewDetailsVo();

					leaveViewDetailsVo.setAccyear("");
					leaveViewDetailsVo.setLeavetype(MessageConstants.LEAVE_TYPE_CL);

					double totopenings=rs_getcl.getDouble("clopenbal");
					double monthopenings=totopenings/12;

					if(flag){

						double afetrdojopenings=monthopenings *(12-Integer.parseInt(month));
						leaveViewDetailsVo.setOpeningbal(afetrdojopenings);
						leaveViewDetailsVo.setClosingbal(afetrdojopenings-rs_getcl.getDouble("clconsumebal"));

					}else{

						leaveViewDetailsVo.setOpeningbal(rs_getcl.getDouble("clopenbal"));
						leaveViewDetailsVo.setClosingbal(rs_getcl.getDouble("clopenbal")-rs_getcl.getDouble("clconsumebal"));
					}

					leaveViewDetailsVo.setConsumebal(rs_getcl.getDouble("clconsumebal"));

					leaveviewlist.add(leaveViewDetailsVo);

				}

				flag=false;
			}
			else{
				
			}
		} catch (SQLException sqle) {
		       sqle.printStackTrace();
		        logger.error(sqle.getMessage(),sqle);
	           } catch (Exception e1) {
		        e1.printStackTrace();
		         logger.error(e1.getMessage(),e1);
	           } finally {
		    try {
	 
		    	
		    	
		    	
		    if (rs_attendance != null && (!rs_attendance.isClosed())) {

					rs_attendance.close();
			}
			if (rs_leavebank != null && (!rs_leavebank.isClosed())) {

				rs_leavebank.close();
			}
			if (rs_getemp != null && (!rs_getemp.isClosed())) {

				rs_getemp.close();
			}
			if (rs_getcl != null && (!rs_getcl.isClosed())) {

				rs_getcl.close();
			}
			
			if (ps_attendance != null && (!ps_attendance.isClosed())) {

				ps_attendance.close();
			}
			if (ps_leavebank != null && (!ps_leavebank.isClosed())) {

				ps_leavebank.close();
			}
			if (ps_getemp != null && (!ps_getemp.isClosed())) {

				ps_getemp.close();
			}
			if (ps_getcl != null && (!ps_getcl.isClosed())) {

				ps_getcl.close();
			}
			
			if (connection != null && (!connection.isClosed())) {

				connection.close();
			}
			
			
		    } catch (SQLException sqle) {
			         sqle.printStackTrace();
		        	logger.error(sqle.getMessage(),sqle);
	            	} catch (Exception e1) {
		           	e1.printStackTrace();
			        logger.error(e1.getMessage(),e1);
		       }
	         }
		

		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Ending");

		return leaveviewlist;
}


	public String addLeavesCategory(String[] categorynames, String[] shortnames, String[] noofleaves, String[] catId, String accyear, String location, String log_audit_session) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: addLeavesCategory : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
	
		String status = null;
		String teaId = null;
		String doj = null;
		String accyearStart = null;
		int count =0;
		String accEndDate=null;
		int noofmonthsInt = 0;
		String teacherType= null;
		int month = 0;
		int day = 0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement accyearpstmt = conn.prepareStatement("select startDate,endDate from campus_acadamicyear where acadamic_id = ?");
			accyearpstmt.setString(1,accyear);
			ResultSet accyearrs = accyearpstmt.executeQuery();
			while(accyearrs.next()){
				accyearStart = accyearrs.getString("startDate");
				accEndDate = accyearrs.getString("endDate");
			}
			accyearpstmt.close();
			accyearrs.close();
			pstmt = conn.prepareStatement("insert into campus_new_leave_bank(Leave_ID,Leave_name,No_Of_Leaves,ShortName,Cat_Id,Accy_Id,Loc_ID)values(?,?,?,?,?,?,?)");
				
				for(int i=0;i<categorynames.length;i++){
					
					String categoryNames = StringUtils.capitalize(categorynames[i]);
					
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_new_leave_bank"));
					pstmt.setString(2, categoryNames);
					pstmt.setString(3, noofleaves[i]);
					pstmt.setString(4, shortnames[i]);
					pstmt.setString(5, catId[i]);
					pstmt.setString(6, accyear);
					pstmt.setString(7, location);
					count = pstmt.executeUpdate();
				}
			
				if(count > 0){
					//HelperClass.recordLog_Activity(log_audit_session,"Leave","Leave Type","Insert",pstmt.toString());
					status = "Added Successfully";
				}
				
			PreparedStatement teacherpstmt = conn.prepareStatement("select TeacherID,teachingType,dateofJoining,Loc_ID from campus_teachers where Loc_ID = ?");
			teacherpstmt.setString(1, location);
			//(teacherpstmt);
			ResultSet teacherrs = teacherpstmt.executeQuery();
			while(teacherrs.next()){
				teaId = teacherrs.getString("TeacherID");
				doj = teacherrs.getString("dateofJoining");
				teacherrs.getString("Loc_ID");
				teacherType = teacherrs.getString("teachingType");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        Date dateofJoining = sdf.parse(doj);
		        Date accyearstDate = sdf.parse(accyearStart);
		        if (accyearstDate.compareTo(dateofJoining) > 0) {
		        	noofmonthsInt = 1;
		        }
		        else if (accyearstDate.compareTo(dateofJoining) == 0) {
		        	noofmonthsInt = 1;
		        }
		        else{
		        	 	String [] tdoj = doj.split("-");
						
		        	 	month = Integer.parseInt(tdoj[1]);
		        	 	day = Integer.parseInt(tdoj[2]);
						String [] astartcurrdate = accEndDate.split("-");

						noofmonthsInt=(Integer.parseInt(astartcurrdate[0])-Integer.parseInt(tdoj[0]))*12;
						
						noofmonthsInt=noofmonthsInt+((12-(Integer.parseInt(tdoj[1])-1)+(Integer.parseInt(astartcurrdate[1])-12)));
						
						//("noofmonthsInt :: "+noofmonthsInt+""+doj);
		        }
		        if(teacherType.equalsIgnoreCase("TEACHING") || teacherType.equalsIgnoreCase("NON TEACHING") || teacherType.equalsIgnoreCase("GENERAL")){
		        	
		        	//("staff types------------ ::");
		        	
		        	PreparedStatement insteacherLeave = conn.prepareStatement("insert into campus_teacher_new_leave_bank_details(AccYearCode,EmpId,Leave_Type,total_available,January,February,March,April,May,June,July,August,September,October,November,December,Date_Of_Join,LOC_Id,Leave_Name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		        	for(int i=0;i<categorynames.length;i++){
		        	
		        	String categoryNames = StringUtils.capitalize(categorynames[i]);
		        	double no_of_leaves = Double.parseDouble(noofleaves[i]);
		        	//("categorynames :: "+ categorynames[i]);
		        	
		        	double nol = 0.0;
		        	String leavetypes = null;
		        	if(!(categoryNames.toLowerCase().startsWith("earned"))){
		        		insteacherLeave.setString(1,accyear);
		        		insteacherLeave.setString(2,teaId);
		        	
		        		PreparedStatement leavidpstmt = conn.prepareStatement("select Leave_ID from campus_new_leave_bank where Leave_name = ? and Loc_ID=? and Accy_Id =?");
		        		leavidpstmt.setString(1,categoryNames);
		        		leavidpstmt.setString(2,location);
		        		leavidpstmt.setString(3,accyear);
		        		ResultSet leaveidrs = leavidpstmt.executeQuery();
		        		while(leaveidrs.next()){
		        		leavetypes = leaveidrs.getString("Leave_ID");
		        		}
		        		leavidpstmt.close();
		        		leaveidrs.close();
		        	
		        		insteacherLeave.setString(3,leavetypes);
					
		        		if(categoryNames.toLowerCase().startsWith("casual")){
						
		        			//("Inside if Statement"+categoryNames);
						
		        			if(noofmonthsInt == 1){
							
		        				insteacherLeave.setString(4,noofleaves[i]);
		        				insteacherLeave.setString(5,"1.0");
		        				insteacherLeave.setString(6,"1.5");
		        				insteacherLeave.setString(7,"1.0");
		        				insteacherLeave.setString(8,"1.5");
		        				insteacherLeave.setString(9,"1.0");
		        				insteacherLeave.setString(10,"1.5");
		        				insteacherLeave.setString(11,"1.0");
		        				insteacherLeave.setString(12,"1.5");
		        				insteacherLeave.setString(13,"1.0");
		        				insteacherLeave.setString(14,"1.5");
		        				insteacherLeave.setString(15,"1.0");
		        				insteacherLeave.setString(16,"1.5");
						   }
		        			else{
		        				nol =(noofmonthsInt*no_of_leaves)/12;
								
		        				if(month == 4){
								
		        					if(day < 15){
		        						insteacherLeave.setDouble(4,nol);
		        					}
		        					else{
		        						insteacherLeave.setDouble(4,(nol-1.5));
		        					}
								
		        					insteacherLeave.setString(5,"1.0");
		        					insteacherLeave.setString(6,"1.5");
		        					insteacherLeave.setString(7,"1.0");
		        					if(day < 15){
		        						insteacherLeave.setString(8,"1.5");
		        					}
		        					else{
		        						insteacherLeave.setString(8,"0.0");
		        					}
		        					insteacherLeave.setString(9,"1.0");
		        					insteacherLeave.setString(10,"1.5");
		        					insteacherLeave.setString(11,"1.0");
		        					insteacherLeave.setString(12,"1.5");
		        					insteacherLeave.setString(13,"1.0");
		        					insteacherLeave.setString(14,"1.5");
		        					insteacherLeave.setString(15,"1.0");
		        					insteacherLeave.setString(16,"1.5");
							}
							else if(month == 5){
								
									if(day < 15){
										insteacherLeave.setDouble(4,nol);
									}
									else{
										insteacherLeave.setDouble(4,(nol-1.0));
									}
								
									insteacherLeave.setString(5,"1.0");
									insteacherLeave.setString(6,"1.5");
									insteacherLeave.setString(7,"1.0");
									insteacherLeave.setString(8,"0.0");
									if(day < 15){
										insteacherLeave.setString(9,"1.0");
									}else{
										insteacherLeave.setString(9,"0.0");
									}
									insteacherLeave.setString(10,"1.5");
									insteacherLeave.setString(11,"1.0");
									insteacherLeave.setString(12,"1.5");
									insteacherLeave.setString(13,"1.0");
									insteacherLeave.setString(14,"1.5");
									insteacherLeave.setString(15,"1.0");
									insteacherLeave.setString(16,"1.5");
							}
							else if(month == 6){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								if(day < 15){
									insteacherLeave.setString(10,"1.5");
								}else{
									insteacherLeave.setString(10,"0.0");
								}
								insteacherLeave.setString(11,"1.0");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 7){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								if(day < 15){
									insteacherLeave.setString(11,"1.0");
								}else{
									insteacherLeave.setString(11,"0.0");
								}
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 8){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								if(day < 15){
									insteacherLeave.setString(12,"1.5");
								}else{
									insteacherLeave.setString(12,"0.0");
								}
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 9){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								if(day < 15){
									insteacherLeave.setString(13,"1.0");
								}else{
									insteacherLeave.setString(13,"0.0");
								}
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 10){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								if(day < 15){
									insteacherLeave.setString(14,"1.5");
								}else{
									insteacherLeave.setString(14,"0.0");
								}
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 11){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								if(day < 15){
									insteacherLeave.setString(15,"1.0");
								}else{
									insteacherLeave.setString(15,"0.0");
								}
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 12){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								if(day < 15){
									insteacherLeave.setString(16,"1.5");
								}else{
									insteacherLeave.setString(16,"0.0");
								}
							}
							else if(month == 1){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								if(day < 15){
									insteacherLeave.setString(5,"1.0");
								}else{
									insteacherLeave.setString(5,"0.0");
								}
						
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 2){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"0.0");
								if(day < 15){
									insteacherLeave.setString(6,"1.5");
								}else{
									insteacherLeave.setString(6,"0.0");
								}
								insteacherLeave.setString(7,"1");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 3){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"0.0");
								insteacherLeave.setString(6,"0.0");
								if(day < 15){
									insteacherLeave.setString(7,"1.0");
								}else{
									insteacherLeave.setString(7,"0.0");
								}
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
						}
					
								insteacherLeave.setString(17,doj);
								insteacherLeave.setString(18,location);
								insteacherLeave.setString(19,categoryNames);
								insteacherLeave.executeUpdate();
				}}
		      }
		   }else{

		        PreparedStatement insteacherLeave = conn.prepareStatement("insert into campus_teacher_new_leave_bank_details(AccYearCode,EmpId,Leave_Type,total_available,January,February,March,April,May,June,July,August,September,October,November,December,Date_Of_Join,LOC_Id,Leave_Name)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		        for(int i=0;i<categorynames.length;i++){
		        	
		        	double nol = 0.0;
		        	String leavetypes = null;
		        	String categoryNames = StringUtils.capitalize(categorynames[i]);
		        	double no_of_leaves = Double.parseDouble(noofleaves[i]);
		        	PreparedStatement leavidpstmt = conn.prepareStatement("select Leave_ID from campus_new_leave_bank where Leave_name = ? and Loc_ID=? and Accy_Id =?");
		        	leavidpstmt.setString(1,categoryNames);
		        	leavidpstmt.setString(2,location);
		        	leavidpstmt.setString(3,accyear);
		        	ResultSet leaveidrs = leavidpstmt.executeQuery();
		        	while(leaveidrs.next()){
		        		leavetypes = leaveidrs.getString("Leave_ID");
		        	}
		        	leavidpstmt.close();
		        	leaveidrs.close();
		        	
		        	insteacherLeave.setString(1,accyear);
		        	insteacherLeave.setString(2,teaId);
		        	insteacherLeave.setString(3,leavetypes);
					
					if(categoryNames.contains("Casual")){
						if(noofmonthsInt == 1){
							
							insteacherLeave.setString(4,noofleaves[i]);
							insteacherLeave.setString(5,"1.0");
							insteacherLeave.setString(6,"1.5");
							insteacherLeave.setString(7,"1.0");
							insteacherLeave.setString(8,"1.5");
							insteacherLeave.setString(9,"1.0");
							insteacherLeave.setString(10,"1.5");
							insteacherLeave.setString(11,"1.0");
							insteacherLeave.setString(12,"1.5");
							insteacherLeave.setString(13,"1.0");
							insteacherLeave.setString(14,"1.5");
							insteacherLeave.setString(15,"1.0");
							insteacherLeave.setString(16,"1.5");
						}
						else{
							nol =(noofmonthsInt*no_of_leaves)/12;
							
							if(month == 4){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								if(day < 15){
									insteacherLeave.setString(8,"1.5");
								}
								else{
									insteacherLeave.setString(8,"0.0");
								}
								insteacherLeave.setString(9,"1.0");
								insteacherLeave.setString(10,"1.5");
								insteacherLeave.setString(11,"1.0");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 5){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								if(day < 15){
									insteacherLeave.setString(9,"1.0");
								}else{
									insteacherLeave.setString(9,"0.0");
								}
								insteacherLeave.setString(10,"1.5");
								insteacherLeave.setString(11,"1.0");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 6){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								if(day < 15){
									insteacherLeave.setString(10,"1.5");
								}else{
									insteacherLeave.setString(10,"0.0");
								}
								insteacherLeave.setString(11,"1.0");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 7){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								if(day < 15){
									insteacherLeave.setString(11,"1.0");
								}else{
									insteacherLeave.setString(11,"0.0");
								}
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 8){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								if(day < 15){
									insteacherLeave.setString(12,"1.5");
								}else{
									insteacherLeave.setString(12,"0.0");
								}
								insteacherLeave.setString(13,"1.0");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 9){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								if(day < 15){
									insteacherLeave.setString(13,"1.0");
								}else{
									insteacherLeave.setString(13,"0.0");
								}
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 10){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								if(day < 15){
									insteacherLeave.setString(14,"1.5");
								}else{
									insteacherLeave.setString(14,"0.0");
								}
								insteacherLeave.setString(15,"1.0");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 11){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								if(day < 15){
									insteacherLeave.setString(15,"1.0");
								}else{
									insteacherLeave.setString(15,"0.0");
								}
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 12){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.0");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								if(day < 15){
									insteacherLeave.setString(16,"1.5");
								}else{
									insteacherLeave.setString(16,"0.0");
								}
							}
							else if(month == 1){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								if(day < 15){
									insteacherLeave.setString(5,"1.0");
								}else{
									insteacherLeave.setString(5,"0.0");
								}
						
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.0");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 2){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"0.0");
								if(day < 15){
									insteacherLeave.setString(6,"1.5");
								}else{
									insteacherLeave.setString(6,"0.0");
								}
								insteacherLeave.setString(7,"1");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 3){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.0));
								}
								
								insteacherLeave.setString(5,"0.0");
								insteacherLeave.setString(6,"0.0");
								if(day < 15){
									insteacherLeave.setString(7,"1.0");
								}else{
									insteacherLeave.setString(7,"0.0");
								}
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
						}
						insteacherLeave.setString(17,doj);
						insteacherLeave.setString(18,location);
						insteacherLeave.setString(19,categoryNames);
						insteacherLeave.executeUpdate();
					}
					else if(categoryNames.contains("Earned")){
						if(noofmonthsInt == 1){
							insteacherLeave.setString(4,noofleaves[i]);
							insteacherLeave.setString(5,"1.5");
							insteacherLeave.setString(6,"1.5");
							insteacherLeave.setString(7,"1.5");
							insteacherLeave.setString(8,"2.5");
							insteacherLeave.setString(9,"2.5");
							insteacherLeave.setString(10,"1.5");
							insteacherLeave.setString(11,"1.5");
							insteacherLeave.setString(12,"1.5");
							insteacherLeave.setString(13,"1.5");
							insteacherLeave.setString(14,"1.5");
							insteacherLeave.setString(15,"1.5");
							insteacherLeave.setString(16,"1.5");
						}
						else{
							nol =(noofmonthsInt*no_of_leaves)/12;
							
							if(month == 4){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-2.5));
								}
								insteacherLeave.setDouble(4,nol);
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								if(day < 15){
									insteacherLeave.setString(8,"2.5");
								}
								else{
									insteacherLeave.setString(8,"0.0");
								}
								insteacherLeave.setString(9,"2.5");
								insteacherLeave.setString(10,"1.5");
								insteacherLeave.setString(11,"1.5");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.5");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 5){
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-2.5));
								}
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								if(day < 15){
									insteacherLeave.setString(9,"2.5");
								}
								else{
									insteacherLeave.setString(9,"0.0");
								}
								insteacherLeave.setString(10,"1.5");
								insteacherLeave.setString(11,"1.5");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.5");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 6){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								if(day < 15){
									insteacherLeave.setString(10,"1.5");
								}
								else{
									insteacherLeave.setString(10,"0.0");
								}
								
								insteacherLeave.setString(11,"1.5");
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.5");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 7){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								if(day < 15){
									insteacherLeave.setString(11,"1.5");
								}
								else{
									insteacherLeave.setString(11,"0.0");
								}
								insteacherLeave.setString(12,"1.5");
								insteacherLeave.setString(13,"1.5");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 8){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								if(day < 15){
									insteacherLeave.setString(12,"1.5");
								}
								else{
									insteacherLeave.setString(12,"0.0");
								}
								insteacherLeave.setString(13,"1.5");
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 9){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								if(day < 15){
									insteacherLeave.setString(13,"1.5");
								}
								else{
									insteacherLeave.setString(13,"0.0");
								}
								
								insteacherLeave.setString(14,"1.5");
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 10){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								if(day < 15){
									insteacherLeave.setString(14,"1.5");
								}
								else{
									insteacherLeave.setString(14,"0.0");
								}
								insteacherLeave.setString(15,"1.5");
								insteacherLeave.setString(16,"1.5");
							}
							else if(month == 11){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								if(day < 15){
									insteacherLeave.setString(15,"1.5");
								}
								else{
									insteacherLeave.setString(15,"0.0");
								}
									insteacherLeave.setString(16,"1.5");
							}
							else if(month == 12){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"1.5");
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								if(day < 15){
									insteacherLeave.setString(16,"1.5");
								}
								else{
									insteacherLeave.setString(16,"0.0");
								}
								
							}
							else if(month == 1){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								if(day < 15){
									insteacherLeave.setString(5,"1.5");
								}
								else{
									insteacherLeave.setString(5,"0.0");
								}
								
								insteacherLeave.setString(6,"1.5");
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 2){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"0.0");
								if(day < 15){
									insteacherLeave.setString(6,"1.5");
								}
								else{
									insteacherLeave.setString(6,"0.0");
								}
								
								insteacherLeave.setString(7,"1.5");
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
							else if(month == 3){
								
								if(day < 15){
									insteacherLeave.setDouble(4,nol);
								}
								else{
									insteacherLeave.setDouble(4,(nol-1.5));
								}
								
								insteacherLeave.setString(5,"0.0");
								insteacherLeave.setString(6,"0.0");
								if(day < 15){
									insteacherLeave.setString(7,"1.5");
								}
								else{
									insteacherLeave.setString(7,"0.0");
								}
								
								insteacherLeave.setString(8,"0.0");
								insteacherLeave.setString(9,"0.0");
								insteacherLeave.setString(10,"0.0");
								insteacherLeave.setString(11,"0.0");
								insteacherLeave.setString(12,"0.0");
								insteacherLeave.setString(13,"0.0");
								insteacherLeave.setString(14,"0.0");
								insteacherLeave.setString(15,"0.0");
								insteacherLeave.setString(16,"0.0");
							}
						}
								insteacherLeave.setString(17,doj);
								insteacherLeave.setString(18,location);
								insteacherLeave.setString(19,categoryNames);
								 count=insteacherLeave.executeUpdate();
					}
				
				}
		        

				
		        
		        
		   }
		        if(count>0) {
		        	//HelperClass.recordLog_Activity(log_audit_session,"Leave","Leave Type","Insert",pstmt.toString());
		        }
		        
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			if (pstmt != null && (!pstmt.isClosed())) {

				pstmt.close();
			}
			
			if (conn != null && (!conn.isClosed())) {

				conn.close();
			}
			
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :addLeavesCategory Ending");
		return status;
	}

	public ArrayList<LeaveBankVO> getleaveCatList(UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleaveCatList : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		
		try{
			
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement("select * from campus_category_master order by length(Cat_Id),Cat_Id asc");
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCat_id(rs.getString("Cat_Id"));
				obj.setCat_name(rs.getString("Category_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			try{
				 if (rs != null && (!rs.isClosed())) {
						rs.close();
				 }
				 if (pstmt != null && (!pstmt.isClosed())) {

					 pstmt.close();
				 }
				 if (conn != null && (!conn.isClosed())) {
					 conn.close();
				 }
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleaveCatList Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleavetypeDetails(String locId, String academic_year, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleavetypeDetails : Starting");
		
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		int count =0;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			//pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_NAME_DETAILS);
			cstmt = conn.prepareCall("{CALL `getLeaveTypes`(?,?)}");
			cstmt.setString(1,academic_year);
			cstmt.setString(2,locId);
			//("ASAS"+cstmt);
			rs = cstmt.executeQuery();
			while(rs.next()){
				count ++;
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCount(count);
				obj.setLeaveName(rs.getString("leavename"));
				obj.setShortName(rs.getString("leavecode"));
				obj.setNoofleaves(rs.getString("noofleaves"));
				obj.setAccumuLations(rs.getString("acuumulation"));
				obj.setCarryFs(rs.getString("carryforward"));
				obj.setLeaveid(rs.getInt("leaveid"));
				obj.setLocId(rs.getString("locid"));
				list.add(obj);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				
			if (rs != null && (!rs.isClosed())) {
					rs.close();
			 }
			 if (cstmt != null && (!cstmt.isClosed())) {

				 cstmt.close();
			 }
			 if (conn != null && (!conn.isClosed())) {

				 conn.close();
			 }
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleavetypeDetails Ending");
		
		return list;
	}


	public ArrayList<LeaveBankVO> editleavetypes(String accyear, String loc) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: editleavetypes : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select Leave_ID,Cat_Id,Leave_name,No_Of_Leaves,ShortName,Accy_Id,Loc_ID from campus_new_leave_bank where Accy_Id =? and Loc_ID=?");
			pstmt.setString(1, accyear);
			pstmt.setString(2, loc);
			
			//(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveName(rs.getString("Leave_name"));
				obj.setShortName(rs.getString("ShortName"));
				obj.setNoofleaves(rs.getString("No_Of_Leaves"));
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setAccyear(accyear);
				obj.setLocId(loc);
				
				pstmt1 = conn.prepareStatement("select Cat_Id,Category_Name from campus_category_master where Cat_Id =?");
				pstmt1.setString(1, rs.getString("Cat_Id"));
				rs1= pstmt1.executeQuery();
				while(rs1.next()){
					obj.setCat_id(rs1.getString("Cat_Id"));
					obj.setCat_name(rs1.getString("Category_Name"));
					
				}
				pstmt1.close();
				rs1.close();
				list.add(obj);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
			finally{
				try{
					
					if (rs1 != null && (!rs1.isClosed())) {
						rs1.close();
				 }
					if (rs != null && (!rs.isClosed())) {
						rs.close();
				 }
				 if (pstmt1 != null && (!pstmt1.isClosed())) {

					 pstmt1.close();
				 }
				 if (pstmt != null && (!pstmt.isClosed())) {

					 pstmt.close();
				 }
				 if (conn != null && (!conn.isClosed())) {

					 conn.close();
				 }
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :editleavetypes Ending");
		return list;
	}


	public String editleavetypes(String accyear, String loc, String category) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) Leave_name from campus_new_leave_bank where Leave_name =? and Accy_Id=? and Loc_ID =?");
			pstmt.setString(1, category);
			pstmt.setString(2,accyear);
			pstmt.setString(3,loc);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count = rs.getInt(1);
			}
			
			if(count > 0){
				status = "true";
			}
			else {
				status = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {

						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {

						conn.close();
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getSearchleavetypeDetails(String searchTearm, String academic_year, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getSearchleavetypeDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			
			//("DAOIMPL");
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVENAME_SEARCH);
			
			pstmt.setString(1,academic_year);
			pstmt.setString(2, "%"+searchTearm+"%");
			pstmt.setString(3, "%"+searchTearm+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				count++;
				LeaveBankVO obj = new LeaveBankVO();
				obj.setCount(count);
				obj.setAcademicyear(rs.getString("acadamic_year"));
				obj.setLocationName(rs.getString("Location_Name"));
				obj.setAccyearcode(rs.getString("Accy_Id"));
				obj.setLocId(rs.getString("Loc_ID"));
				list.add(obj);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getSearchleavetypeDetails Ending");
		return list;
	}


	public String updateLeavesCategory(String[] hiddenLEaveIdArray, String[] categorynames, String[] shortnames,
			String[] noofleaves, String[] catId, String accyear, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getSearchleavetypeDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String status = null;
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		int count =0;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("update campus_new_leave_bank set Leave_name = ?,No_Of_Leaves = ?,ShortName = ?,Cat_Id = ?,Accy_Id= ?,Loc_ID = ? where Leave_ID = ?");
			
			if(location.equalsIgnoreCase("%%")){
				locationList = new  LocationBD().getLocationDetails(null);
				//(locationList.size());
			}

			if(locationList.size()!=0){
			
			for(int i=0;i<categorynames.length;i++){
				
			for(int j=0;j<locationList.size();j++){
				
				pstmt.setString(1, categorynames[i]);
				pstmt.setString(2, noofleaves[i]);
				pstmt.setString(3, shortnames[i]);
				pstmt.setString(4, catId[i]);
				pstmt.setString(5, accyear);
				pstmt.setString(6, locationList.get(j).getLocation_id());
				pstmt.setString(7,hiddenLEaveIdArray[i]);
				count = pstmt.executeUpdate();
				}
			}	
		}
			else{
				
				for(int i=0;i<hiddenLEaveIdArray.length;i++){
					
					pstmt.setString(1, categorynames[i]);
					pstmt.setString(2, noofleaves[i]);
					pstmt.setString(3, shortnames[i]);
					pstmt.setString(4, catId[i]);
					pstmt.setString(5, accyear);
					pstmt.setString(6, location);
					pstmt.setString(7,hiddenLEaveIdArray[i]);
					count = pstmt.executeUpdate();
				}
			}
			
			if(count > 0){
				
				status = "Updated Successfully";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			if (pstmt != null && (!pstmt.isClosed())) {

				pstmt.close();
			}
			if (conn != null && (!conn.isClosed())) {

				conn.close();
			}
			
			
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getaccLocCatList(String accyear, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select acadamic_year,Location_Name from campus_acadamicyear,campus_location where acadamic_id = ? and Location_Id = ?");
			pstmt.setString(1, accyear);
			pstmt.setString(2,loc);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setAcademicyear(rs.getString("acadamic_year"));
				obj.setLocationName(rs.getString("Location_Name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {

						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {

						conn.close();
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}


	public String checkDuplicacy(String accyear, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select count(*) Leave_name from campus_new_leave_bank where Accy_Id=? and Loc_ID =?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count = rs.getInt(1);
			}
			
			if(count > 0){
				status = "true";
			}
			else {
				status = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {

						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {

						conn.close();
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return status;
	}


	public ArrayList<LeaveBankVO> getleavenamesList(String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleavenamesList : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select Leave_ID,Leave_name from campus_new_leave_bank where Accy_Id=?");
			pstmt.setString(1, academic_year);
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setLeaveName(rs.getString("Leave_name"));
				list.add(obj);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleavenamesList Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleaveusertype(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select tnlb.Leave_Type,nlb.Leave_name from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details tnlb where nlb.Leave_ID = tnlb.Leave_Type and EmpId=? and AccYearCode = ?");
			pstmt.setString(1,parentid);
			pstmt.setString(2, academic_year);
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_Type"));
				obj.setLeaveName(rs.getString("Leave_name"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}


	public ArrayList<LeaveBankVO> getleaveBalance(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getleaveBalance : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select EmpId,Leave_Name,Leave_Type,total_available,total_consumed,total_avaliable_leaves as tot_year,total_consumed,total_avaliable_leaves from campus_teacher_new_leave_bank_details where AccYearCode = ? and EmpId = ?");
			pstmt.setString(1, academic_year);
			pstmt.setString(2,parentid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeavetypeName(rs.getString("Leave_Name"));
				obj.setLeaveID(rs.getString("Leave_Type"));
				obj.setTotalleaves(rs.getString("total_available"));
				obj.setConsumedLeave(rs.getString("total_consumed"));
				double tot_leaves = Double.parseDouble(rs.getString("total_available"));
				double tot_available =tot_leaves- rs.getDouble("total_consumed");
				obj.setAvailable_leave(tot_available);
				obj.setUserID(rs.getString("EmpId"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getleaveBalance Ending");
		return list;
	}


	public LeaveCalculation getLeaveCalculation(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getLeaveCalculation : Starting");
	
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	
		String accyyearStart = null;
		String leavename = null;
		Double total_available = 0.0;
		double tot_leave = 0.0;
		double leave_applicable = 0.0;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select startDate,endDate from campus_acadamicyear where acadamic_id = ?");
			pstmt.setString(1, academic_year);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				accyyearStart = rs.getString("startDate");
			}
			
			
			String [] doj = accyyearStart.split("-");
			
			String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");

			int noofmonths=(Integer.parseInt(currdate[0])-Integer.parseInt(doj[0]))*12;
			
			noofmonths=noofmonths+((12-(Integer.parseInt(doj[1])-1)+(Integer.parseInt(currdate[1])-12)));
			
			double leaveDivisor = 12/noofmonths;
			
			PreparedStatement casualpstmt = conn.prepareStatement("select nlb.Leave_name,ntlb.Leave_Type,ntlb.total_consumed,ntlb.total_available from campus_teacher_new_leave_bank_details ntlb,campus_new_leave_bank nlb where nlb.Leave_ID = ntlb.Leave_Type and  EmpId = ?");
			casualpstmt.setString(1,parentid);
			//(casualpstmt);
			ResultSet leavecal = casualpstmt.executeQuery();
			while(leavecal.next()){
				
				leavename = leavecal.getString("Leave_name");
				total_available = Double.parseDouble(leavecal.getString("total_available"));
				if(leavename.startsWith("Casual")){
					
					tot_leave = total_available/leaveDivisor;
					
					if(tot_leave % 0.5 == 0){
						leave_applicable = tot_leave;
					}else{
						leave_applicable = tot_leave-0.25;
					}
					
				}
				//(leave_applicable);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return null;
	}


	public LeaveCalculation getNewLeaveCalculation(String parentid, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getNewLeaveCalculation : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int month = 0;
		String monthName = null;
		LeaveCalculation leaveValue = new LeaveCalculation();
		double leaveCount =0;
		double leavecountSum =0.0;
		try{
			
			String [] currdate = HelperClass.getCurrentSqlDate().toString().split("-");
			month = Integer.parseInt(currdate[1]);
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select nlb.Leave_name,January,February,March,April,May,June,July,August,September,October,November,December,total_available,Leave_Type from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details where nlb.Leave_ID = Leave_Type and AccYearCode =? and EmpId =?");
			pstmt.setString(1,academic_year);
			pstmt.setString(2,parentid);
			rs = pstmt.executeQuery();
			while(rs.next()){

				double month1 = Double.parseDouble(rs.getString("January"));
				double month2 = Double.parseDouble(rs.getString("February"));
				double month3 = Double.parseDouble(rs.getString("March"));
				double month4 = Double.parseDouble(rs.getString("April"));
				double month5 = Double.parseDouble(rs.getString("May"));
				double month6 = Double.parseDouble(rs.getString("June"));
				double month7 = Double.parseDouble(rs.getString("July"));
				double month8 = Double.parseDouble(rs.getString("August"));
				double month9 = Double.parseDouble(rs.getString("September"));
				double month10 = Double.parseDouble(rs.getString("October"));
				double month11 = Double.parseDouble(rs.getString("November"));
				double month12 = Double.parseDouble(rs.getString("December"));
				String leaveType = rs.getString("Leave_name");
				leaveValue.setLeaveName(rs.getString("Leave_name"));
				leaveValue.setLeaveId(rs.getString("Leave_Type"));
				if(leaveType.startsWith("Casual")){
					 if(month == 04){
						monthName = "April";
						leaveCount =month4;
						//("leaveCount"+leaveCount);
						leavecountSum = leavecountSum+month4;
						leaveValue.setLeaveCount(leaveCount);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 05){
						monthName = "May";
						leaveCount =month5;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 06){
						monthName = "June";
						leaveCount = month6;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 07){
						monthName = "July";
						leaveCount = month7;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month7+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}else if(month == 8){
						monthName = "August";
						leaveCount = month8;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 9){
						monthName = "September";
						leaveCount = month9;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 10){
						monthName = "October";
						leaveCount = month10;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 11){
						monthName = "November";
						leaveCount = month11;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month11+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setCurrentMonCount(month);
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setMonthName(monthName);
					}else if(month == 12){
						monthName = "December";
						leaveCount = month12;
						leaveValue.setLeaveCount(leaveCount);
						leavecountSum = leavecountSum+month12+month11+month10+month9+month8+month7+month6+month5+month4;
						leaveValue.setLeaveCountSum(leavecountSum);
						leaveValue.setCurrentMonCount(month);
						leaveValue.setMonthName(monthName);
					}
					else if(month == 01){
							monthName = "January";
							leaveCount = month1;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setCurrentMonCount(month);
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setMonthName(monthName);
				   }else if(month == 02){
							monthName = "February";
							leaveCount = month2;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setCurrentMonCount(month);
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setMonthName(monthName);
					}else if(month == 03){
							monthName = "March";
							leaveCount = month3;
							leaveValue.setLeaveCount(leaveCount);
							leavecountSum = leavecountSum+month3+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							leaveValue.setLeaveCountSum(leavecountSum);
							leaveValue.setCurrentMonCount(month);
							leaveValue.setMonthName(monthName);
						}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getNewLeaveCalculation Ending");
		return leaveValue;
	}


	public ArrayList<LeaveCalculation> checkLeaveCount(LeaveCalculation obj) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: checkLeaveCount : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int month = 0;
		String monthName = null;
		double leaveCount =0;
		double leavecountSum =0.0;
		double tot_leave = 0.0;
		ArrayList<LeaveCalculation> leavelist = new ArrayList<LeaveCalculation>();
		try{
			
			String[] stDate = (obj.getLeaveStdate()).split("-");
			/*int stdate =Integer.parseInt(stDate[0]);*/
			month = Integer.parseInt(stDate[1]);
			
			conn = JDBCConnection.getSeparateConnection();
			
			PreparedStatement leaveConsumed = conn.prepareStatement("select total_consumed from campus_teacher_new_leave_bank_details where AccYearCode = ? and EmpId=? and  Leave_Type = ?");
			leaveConsumed.setString(1,obj.getAccyear());
			leaveConsumed.setString(2,obj.getUserid());
			leaveConsumed.setString(3,obj.getLeavetype());
			//(leaveConsumed);
			ResultSet rsleave = leaveConsumed.executeQuery();
			while(rsleave.next()){
				tot_leave = rsleave.getDouble("total_consumed");
			}
			rsleave.close();
			leaveConsumed.close();
			//("tot_leave :"+tot_leave);
			pstmt = conn.prepareStatement("select nlb.Leave_name,January,February,March,April,May,June,July,August,September,October,November,December,total_available,Leave_Type from campus_new_leave_bank nlb,campus_teacher_new_leave_bank_details where nlb.Leave_ID = Leave_Type and AccYearCode =? and EmpId =? and Leave_Type=?");
			pstmt.setString(1,obj.getAccyear());
			pstmt.setString(2,obj.getUserid());
			pstmt.setString(3, obj.getLeavetype());
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveCalculation list = new LeaveCalculation();
				double month1 = Double.parseDouble(rs.getString("January"));
				double month2 = Double.parseDouble(rs.getString("February"));
				double month3 = Double.parseDouble(rs.getString("March"));
				double month4 = Double.parseDouble(rs.getString("April"));
				double month5 = Double.parseDouble(rs.getString("May"));
				double month6 = Double.parseDouble(rs.getString("June"));
				double month7 = Double.parseDouble(rs.getString("July"));
				double month8 = Double.parseDouble(rs.getString("August"));
				double month9 = Double.parseDouble(rs.getString("September"));
				double month10 = Double.parseDouble(rs.getString("October"));
				double month11 = Double.parseDouble(rs.getString("November"));
				double month12 = Double.parseDouble(rs.getString("December"));
				String leaveType = rs.getString("Leave_name");
				
					if(month == 04){
						monthName = "April";
						leaveCount =month4;
						leavecountSum = leavecountSum+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 05){
						monthName = "May";
						leaveCount =month5;
						leavecountSum = leavecountSum+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						//("leavecountSum"+(leavecountSum - tot_leave));
						
					}else if(month == 06){
						monthName = "June";
						leaveCount = month6;
						leavecountSum = leavecountSum+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 07){
						monthName = "July";
						leaveCount = month7;
						leavecountSum = leavecountSum+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 8){
						monthName = "August";
						leaveCount = month8;
						leavecountSum = leavecountSum+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 9){
						monthName = "September";
						leaveCount = month9;
						leavecountSum = leavecountSum+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 10){
						monthName = "October";
						leaveCount = month10;
						leavecountSum = leavecountSum+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
						
					}else if(month == 11){
						monthName = "November";
						leaveCount = month11;
						leavecountSum = leavecountSum+month11+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
					}else if(month == 12){
						monthName = "December";
						leaveCount = month12;
						leavecountSum = leavecountSum+month12+month11+month10+month9+month8+month7+month6+month5+month4;
						list.setLeaveCount(leaveCount);
						list.setLeaveCountSum(leavecountSum - tot_leave);
					}
					else if(month == 01){
							monthName = "January";
							leaveCount = month1;
							leavecountSum = leavecountSum+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							list.setLeaveCount(leaveCount);
							list.setLeaveCountSum(leavecountSum - tot_leave);
				   }else if(month == 02){
							monthName = "February";
							leaveCount = month2;
							leavecountSum = leavecountSum+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							list.setLeaveCount(leaveCount);
							list.setLeaveCountSum(leavecountSum - tot_leave);
					}else if(month == 03){
							monthName = "March";
							leaveCount = month3;
							leavecountSum = leavecountSum+month3+month2+month1+month12+month11+month10+month9+month8+month7+month6+month5+month4;
							list.setLeaveCount(leaveCount);
							list.setLeaveCountSum(leavecountSum - tot_leave);
						}
					leavelist.add(list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//(leavecountSum);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :checkLeaveCount Ending");
		return leavelist;
	}

	public String checkDateDuplicacy(String startDate, String toDate, String leavetype, String parentid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 0;
		String result = null;
		int leaveStatus = 0;
		try{
			 conn = JDBCConnection.getSeparateConnection();
			 pstmt = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ?");
			 pstmt.setString(1,parentid);
			 pstmt.setString(2,leavetype);
			 pstmt.setString(3,HelperClass.convertUIToDatabase(startDate));
			 pstmt.setString(4,HelperClass.convertUIToDatabase(toDate));
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 status = rs.getInt(1);
			 }
			 if(status > 0){
				
				  PreparedStatement leavestatus = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ? and LeaveStatus = 'Pending'");
				 leavestatus.setString(1,parentid);
				 leavestatus.setString(2,leavetype);
				 leavestatus.setString(3,HelperClass.convertUIToDatabase(startDate));
				 leavestatus.setString(4,HelperClass.convertUIToDatabase(toDate));
				 ResultSet leavers = leavestatus.executeQuery();
				 while(leavers.next()){
					 leaveStatus = leavers.getInt(1);
				 }
				 leavers.close();
				 leavestatus.close();
				 
				 if(leaveStatus > 0){
					 result = "Leave applied pending for approve";
				 }
				 else{
					 int leavestatuss =0;
					 PreparedStatement leavestatus1 = conn.prepareStatement("select count(*) from campus_teachers_leave_request where RequestedBy = ? and LeaveType = ? and StartDate = ? and EndDate = ? and LeaveStatus = 'Approved'");
					 leavestatus1.setString(1,parentid);
					 leavestatus1.setString(2,leavetype);
					 leavestatus1.setString(3,HelperClass.convertUIToDatabase(startDate));
					 leavestatus1.setString(4,HelperClass.convertUIToDatabase(toDate));
					 ResultSet leavers1 = leavestatus1.executeQuery();
					 while(leavers1.next()){
						 leavestatuss = leavers1.getInt(1);
					 }
					 leavers1.close();
					 leavestatus1.close();
					 if(leavestatuss > 0){
					 result = "You already applied leave";
				 }
			 }
			 }
			 else{
				 result = "false";
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return result;
	}


	public ArrayList<LeaveViewDetailsVo> getviewNewLeaveDetails(String trim, String academic_year, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewNewLeaveDetails : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		ArrayList<LeaveViewDetailsVo> list = new ArrayList<LeaveViewDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select total_available,total_consumed,total_avaliable_leaves,Leave_Type,Leave_Name,acadamic_year from campus_teacher_new_leave_bank_details,campus_acadamicyear where acadamic_id = AccYearCode and AccYearCode = ? and EmpId = ? "); 
			pstmt.setString(1,academic_year);
			pstmt.setString(2,trim);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveViewDetailsVo obj = new LeaveViewDetailsVo();
				obj.setAccyear(rs.getString("acadamic_year"));
				
				obj.setConsumebal(rs.getDouble("total_consumed"));
				obj.setTotal_leave_year(rs.getString("total_available"));
				
				double tot_leaves = Double.parseDouble(rs.getString("total_available"));
				double tot_available =tot_leaves- rs.getDouble("total_consumed");
				obj.setTotalleaves(tot_available);
				
				obj.setLeavetype(rs.getString("Leave_Name"));
				obj.setLeaveID(rs.getString("Leave_Type"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getviewNewLeaveDetails Ending");
		return list;
	}



	public ArrayList<LeaveBankVO> getLeaveTypes(String academicYear, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getLeaveTypes : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_LEAVE_TYPES);
			pstmt.setString(1, academicYear);
			pstmt.setString(2,location);
			
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setLeaveID(rs.getString("Leave_ID"));
				obj.setLeaveName(rs.getString("Leave_name"));
				obj.setNoofleaves(rs.getString("No_Of_Leaves"));
				obj.setShortName(rs.getString("ShortName"));
				obj.setCat_id(rs.getString("Cat_Id"));
				obj.setAcademicyear(rs.getString("Accy_Id"));
				obj.setLocId(rs.getString("Loc_ID"));
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}



	public String checkMonthleave(String academic_year, String parentid, String fromDate, String leavetype) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getviewLeaveDetails : Starting");
		
	/*	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;*/
		
		try{
			
		/*	conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("");
			*/
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return null;
	}


	public List<LeaveBankVO> getNoOfLeave(String academicYear, String location,String leaveType,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: getNoOfLeave : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_NO_OF_LEAVES);
			pstmt.setString(1, academicYear);
			pstmt.setString(2,location);
			pstmt.setString(3, leaveType);
			
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO leaveBankVo = new LeaveBankVO();
				leaveBankVo.setNoofleaves(rs.getString("No_Of_Leaves"));
				list.add(leaveBankVo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {

					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :getLeaveAprrovedDetails Ending");
		return list;
	}


	@Override
	public String addLeaveBank(LeaveBankVO leaveVo,UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: addLeaveBank : Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String status = null;
		int result = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.ADD_LEAVE_TYPES);
			for(int i = 0; i < leaveVo.getLeaveCodes().length; i++){
				pstmt.setString(1,leaveVo.getLeaveCodes()[i]);
				pstmt.setString(2,leaveVo.getAccyear());
				pstmt.setString(3,leaveVo.getLocId());
				pstmt.setString(4,leaveVo.getLeaveNames()[i]);
				pstmt.setString(5,leaveVo.getNoofLeaves()[i]);
				pstmt.setString(6,leaveVo.getAccumuLation()[i]);
				pstmt.setString(7,leaveVo.getCarryF()[i]);
				pstmt.setString(8,leaveVo.getCreateuser());
				
				count = pstmt.executeUpdate();
				if(count > 0){
					result ++;
					HelperClass.recordLog_Activity(leaveVo.getLogaudit(),"Leave", "Leave Types", "Update",pstmt.toString(),dbdetails,conn);
				}
			}
			if(result == leaveVo.getLeaveCodes().length){
				conn.commit();
				status = "success";
				
			}else{
				conn.rollback();
				status = "fail";
			}
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			status = "fail";
		}
		catch(Exception e){
			status = "fail";
			e.printStackTrace();
		}
		finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "fail";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :addLeaveBank Ending");
		return status;
	}


	@Override
	public String updateLeaveDetails(LeaveBankVO ldetails, UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL: updateLeaveDetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.UPDATE_LEAVE_TYPES_DETAILS);
			pstmt.setString(1, ldetails.getShortName());
			pstmt.setString(2, ldetails.getLeaveName());
			pstmt.setString(3, ldetails.getNoofleaves());
			pstmt.setString(4, ldetails.getCarryFs());
			pstmt.setString(5, ldetails.getCreateuser());
			pstmt.setString(6, ldetails.getLeave_cycle());
			pstmt.setString(7, ldetails.getIsGenderSpec());
			pstmt.setDouble(8, ldetails.getIsprobationary());
			pstmt.setDouble(9, ldetails.getMin_lea_per_month());
			pstmt.setDouble(10, ldetails.getMax_leav_per_month());
			pstmt.setDouble(11, ldetails.getMax_consecu_lea_perm());
			pstmt.setString(12, ldetails.getWeek_off_inclusion());
			pstmt.setInt(13, ldetails.getLeaveid());
			count = pstmt.executeUpdate();
			if(count > 0){
				status = "success";
				HelperClass.recordLog_Activity(ldetails.getLogaudit(),"Leave", "Leave Types", "Update",pstmt.toString(),dbdetails);
			}else{
				status = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "fail";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL :updateLeaveDetails Ending");
		return status;
	}


	@Override
	public String deleteLeaveDetails(int leaveid, UserLoggingsPojo dbdetails,String log_audit_session) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : deleteLeaveDetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String status = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.DELETE_LEAVE_TYPES_DETAILS);
			pstmt.setInt(1, leaveid);
			count = pstmt.executeUpdate();
			if(count > 0){
				status = "success";
				HelperClass.recordLog_Activity(log_audit_session,"Leave", "Leave Types", "Delete",pstmt.toString(),dbdetails);
			}else{
				status = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "fail";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : deleteLeaveDetails Ending");
		return status;
	
	}

	@Override
	public String checkLeaveCode(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : checkLeaveCode Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		
		try{
			conn =JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.CHECK_LEAVE_CODE);
			pstmt.setString(1, leavedetails.getShortName());
			pstmt.setString(2, leavedetails.getAccyear());
			pstmt.setString(3, leavedetails.getLocId());
			rs = pstmt.executeQuery();
			//(pstmt);
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count > 0){
				status = "found";
			}else{
				status = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "fail";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : checkLeaveCode Ending");
		return status;
	}


	@Override
	public String checkLeaveName(LeaveBankVO leavedetails, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : checkLeaveName Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String status = null;
		
		try{
			conn =JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.CHECK_LEAVE_NAME);
			pstmt.setString(1, leavedetails.getLeaveName());
			pstmt.setString(2, leavedetails.getAccyear());
			pstmt.setString(3, leavedetails.getLocId());
			rs = pstmt.executeQuery();
			//(pstmt);
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count > 0){
				status = "found";
			}else{
				status = "notfound";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "notfound";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : checkLeaveName Ending");
		return status;
	}


	@Override
	public List<LeaveBankVO> getLeaveMapStatus(String academic_year, String location,UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		int count = 0;
		try{
			conn =JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_LEAVE_MAP_STATUS);
			pstmt.setString(1, academic_year);
			pstmt.setString(2, location);
			pstmt.setString(3, academic_year);
			pstmt.setString(4, location);
			rs = pstmt.executeQuery();
			//(pstmt);
			while(rs.next()){
				count++;
				LeaveBankVO vo = new LeaveBankVO();
				String status = rs.getString("mstatus");
				vo.setSlno(count);
				vo.setAccyearcode(rs.getString("acadamic_id"));
				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setLocId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setMapstatus(status);
				if(status.equalsIgnoreCase("Set")){
					vo.setStatus("Y");
				}else{
					vo.setStatus("N");
				}
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Ending");
		return list;
	}


	@Override
	public List<LeaveBankVO> getStaffMapSt(UserLoggingsPojo dbdetails, String accyloc) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LeaveBankVO> list = new ArrayList<LeaveBankVO>();
		int count = 0;
		try{
			conn =JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_STAFF_LEAVE_MAP_STATUS);
			pstmt.setString(1, accyloc.split(",")[0]);
			pstmt.setString(2, accyloc.split(",")[1]);
			pstmt.setString(3, accyloc.split(",")[0]);
			pstmt.setString(4, accyloc.split(",")[1]);
			pstmt.setString(5, accyloc.split(",")[0]);
			rs = pstmt.executeQuery();
			//(pstmt);
			while(rs.next()){
				count++;
				LeaveBankVO vo = new LeaveBankVO();
				String status = rs.getString("mstatus");
				vo.setSlno(count);
				vo.setAccyearcode(rs.getString("acadamic_id"));
				vo.setAccyear(rs.getString("acadamic_year"));
				vo.setLocId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setMapstatus(status);
				vo.setTeacherId(rs.getString("TeacherID"));
				vo.setTeacherName(rs.getString("teacher"));
				vo.setTeaRegId(rs.getString("registerId"));
				vo.setLeaveid(rs.getInt("staff_leave_id"));
				if(status.equalsIgnoreCase("Set")){
					vo.setStatus("Y");
				}else{
					vo.setStatus("N");
				}
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Ending");
		return list;
	}

	@Override
	public String addStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,insertle = null;
		int count = 0,count1 = 0, result = 0;
		String status = "fail";
		try{
			int tealen = vo.getTeacherId().split(",").length;
			int lealen = vo.getNoofleaves().split(",").length;
			
			conn =JDBCConnection.getSeparateConnection(dbdetails);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.INSERT_STAFF_LEAVE_DETAILS,Statement.RETURN_GENERATED_KEYS);
			
			for(int i=0 ;i<tealen ;i++){
				pstmt.setString(1, vo.getAccyearcode());
				pstmt.setString(2, vo.getLocId());
				pstmt.setString(3, vo.getTeacherId().split(",")[i]);
				pstmt.setString(4, vo.getCreateuser());
				count = pstmt.executeUpdate();
				if(count > 0){
					result++;
					int genkey = 0;
					ResultSet key = pstmt.getGeneratedKeys();
					while(key.next()){
						genkey = key.getInt(1);
					}
					insertle = conn.prepareStatement(LeaveBankSqlUtil.INSERT_STAFF_LEAVE_MAP_DETAILS);
					for(int j=0 ;j<lealen ;j++){
						insertle.setInt(1, genkey);
						insertle.setString(2, vo.getLeaveID().split(",")[j]);
						insertle.setString(3, vo.getNoofleaves().split(",")[j]);
						count1 = insertle.executeUpdate();
						if(count1 > 0){
						result++;
						}
					}
				}
			}
			//(tealen*lealen);
			//(result);
			if(result == (tealen*lealen)+tealen){
				conn.commit();
				status = "success";
			}else{
				conn.rollback();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getLeaveMapStatus Ending");
		return status;
	}


	@Override
	public List<LeaveBankVO> getStaffMappedLeaves(UserLoggingsPojo dbdetails, String details) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getStaffMappedLeaves Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<LeaveBankVO> list = new ArrayList<>();
		ResultSet rs = null;
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_STAFF_MAPPED_LEAVES);
			pstmt.setInt(1, Integer.parseInt(details.split(",")[2]));
			pstmt.setString(2, details.split(",")[0]);
			pstmt.setString(3, details.split(",")[1]);
			pstmt.setString(4, details.split(",")[3]);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO vo = new LeaveBankVO();
				count++;
				vo.setSlno(count);
				vo.setLeaveid(rs.getInt("leaveid"));
				vo.setShortName(rs.getString("leavecode"));
				vo.setLeaveName(rs.getString("leavename"));
				vo.setNoofleaves(rs.getString("noofleaves"));
				vo.setAvailable_leave(rs.getDouble("leaves_applicable"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getStaffMappedLeaves Ending");
		return list;
	}

	@Override
	public String updateStafLeaves(UserLoggingsPojo dbdetails, String data,String leaveids, String appleave) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : updateStafLeaves Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String status = "fail";
		int result = 0;
		int succcnt = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.UPDATE_STAFF_LEAVES);
			for(int i = 0; i<leaveids.split(",").length; i++){
				pstmt.setDouble(1, Double.parseDouble(appleave.split(",")[i]));
				pstmt.setInt(2, Integer.parseInt(data.split(",")[0]));
				pstmt.setInt(3, Integer.parseInt(leaveids.split(",")[i]));
				System.out.println(pstmt);
				count = pstmt.executeUpdate();
				if(count > 0){
					result++;
					status = "success";
				}else if(count == 0) {
					result++;
					status = "not";
				}
			}
			if(result == leaveids.split(",").length){
				conn.commit();
				status = "success";
			}else {
				conn.rollback();
				status = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : updateStafLeaves Ending");
		return status;
	}


	@Override
	public List<LeaveBankVO> getStaffUnmappedLeaves(UserLoggingsPojo dbdetails, String data) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getStaffUnmappedLeaves Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<LeaveBankVO> list = new ArrayList<>();
		ResultSet rs = null;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_UNMAPPED_STAFF_LEAVES);
			pstmt.setString(1, data.split(",")[1]);
			pstmt.setString(2, data.split(",")[2]);
			pstmt.setString(3, data.split(",")[0]);
			pstmt.setString(4, data.split(",")[1]);
			pstmt.setString(5, data.split(",")[2]);
			//(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO vo = new LeaveBankVO();
				vo.setLeaveid(rs.getInt("leaveid"));
				vo.setShortName(rs.getString("leavecode"));
				vo.setLeaveName(rs.getString("leavename"));
				vo.setNoofleaves(rs.getString("noofleaves"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getStaffUnmappedLeaves Ending");
		return list;
	}


	@Override
	public String addSingleStaffLeaves(LeaveBankVO vo, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : addSingleStaffLeaves Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmtst = null;
		ResultSet rs = null;
		List<LeaveBankVO> list = new ArrayList<>();
		int checkAva = 0;
		int status = 0;
		String result = "fail";
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmtst = conn.prepareStatement(LeaveBankSqlUtil.CHECK_LEAVE_MAP_STATUS);
			pstmtst.setString(1, vo.getAccyearcode());
			pstmtst.setString(2, vo.getLocId());
			pstmtst.setInt(3, vo.getLmapId());
			pstmtst.setString(4, vo.getTeacherId());
			rs = pstmtst.executeQuery();
			while(rs.next()){
				checkAva = rs.getInt(1);
			}
			if(checkAva > 0){
				pstmt = conn.prepareStatement(LeaveBankSqlUtil.INSERT_STAFF_LEAVE_MAP_DETAILS);
				for(int i = 0 ;i<vo.getLeaveID().split(",").length; i++){
					pstmt.setInt(1,vo.getLmapId());
					pstmt.setInt(2,Integer.parseInt(vo.getLeaveID().split(",")[i]));
					pstmt.setDouble(3, Double.parseDouble(vo.getNoofleaves().split(",")[i]));
					status = pstmt.executeUpdate();
				}
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : addSingleStaffLeaves Ending");
		return result;
	}


	@Override
	public List<LeaveBankVO> getStaffLeaveBankDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getLeaveBankDetails Starting");
		
		Connection conn = null;
		ResultSet rs = null,teacherrs = null, lrs = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		CallableStatement cstmt = null;
		List<LeaveBankVO> list = new ArrayList<>();
		int count = 0;
		HashMap<Integer,String> hm=new HashMap<Integer,String>();  
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			//pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_STAFF_LEAVE_BANK_DETAILS);
			//pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_NAME_DETAILS);
			cstmt = conn.prepareCall("{CALL `getLeaveTypes`(?,?)}");
			cstmt.setString(1,academic_year);
			cstmt.setString(2,location);
			//("ASAS"+cstmt);
			rs = cstmt.executeQuery();
			while(rs.next()){
				hm.put(rs.getInt("leaveid"),rs.getString("noofleaves"));  
			}
			
				pstmt = conn.prepareStatement(LeaveBankSqlUtil.STAFF_DETAILS);
				pstmt.setString(1,academic_year);
				pstmt.setString(2,location);
				pstmt.setString(3,academic_year);
				teacherrs = pstmt.executeQuery();
				//("Staff "+pstmt);
				while(teacherrs.next()){
					LeaveBankVO vo = new LeaveBankVO();
					ArrayList<LeaveViewDetailsVo> lists = new ArrayList<>();
					String teaid = teacherrs.getString("TeacherID");
					String locid = teacherrs.getString("tloc");
					vo.setTeaRegId(teacherrs.getString("registerId"));
					vo.setTeacherId(teaid);
					vo.setTeacherName(teacherrs.getString("teacher"));
					
					pstmt2 = conn.prepareStatement(LeaveBankSqlUtil.GET_STAFF_LEAVE_BANK_DETAILS);
					
					for(Map.Entry m:hm.entrySet()){  
						//(m.getKey()+" "+m.getValue());  
						pstmt2.setString(1, academic_year);
						pstmt2.setString(2, locid);
						pstmt2.setString(3, teaid);
						pstmt2.setInt(4, (int) m.getKey());
						lrs = pstmt2.executeQuery();
						//("LBank "+pstmt2);
					
							LeaveViewDetailsVo values = new LeaveViewDetailsVo();
							if(lrs.next()){
								values.setTotalleaves(lrs.getDouble("leaves_applicable"));
								values.setLconsumed(lrs.getDouble("total_leave_consumed"));
								values.setLbal(lrs.getDouble("bal_leaves"));
								values.setTotal_leave_year((String)m.getValue());
							}else{
								values.setTotalleaves(0.0);
								values.setLconsumed(0.0);
								values.setLbal(0.0);
								values.setTotal_leave_year((String)m.getValue());
							}
							lists.add(values);
						
					}
					vo.setDetails(lists);
					list.add(vo);
				}
				//map1s.put(rs.getString("leavename"),arraylist1s);
//			for (Map.Entry<String, List<LeaveBankVO>> entry : map1s.entrySet()) {
//			    String key = entry.getKey();
//			    List<LeaveBankVO> value = entry.getValue();
//			    for(LeaveBankVO aString : value){
//			        //("key : " + key + " value : " + aString.getNoofleaves());
//			    }
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getLeaveBankDetails Ending");
		return list;
	}


	@Override
	public ArrayList<LeaveBankVO> getleavenamesList(UserLoggingsPojo dbdetails, UserDetailVO userDetailVO,
			String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getleavenamesList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;
		ArrayList<LeaveBankVO> list = new ArrayList<>();
	
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_STAFF_LEAVES);
			pstmt.setString(1, academic_year);
			pstmt.setString(2, userDetailVO.getUserId());
			pstmt.setString(3, userDetailVO.getGender().substring(0,1).toUpperCase());
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				LeaveBankVO vo = new LeaveBankVO();
				vo.setLeaveID(rs.getString(1));
				vo.setLeaveName(rs.getString(2));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : getLeaveBankDetails Ending");
		return list;
	}


	@Override
	public ArrayList<LeaveBankVO> getApprovers(String aid,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getApprovers Starting");
		
		ArrayList<LeaveBankVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try{
		conn =JDBCConnection.getSeparateConnection(custdetails);
		pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_APPROVER_DETAILS);
		pstmt.setString(1, aid);
		rs = pstmt.executeQuery();
		while(rs.next()){
			LeaveBankVO vo = new LeaveBankVO();
			vo.setTeacherId(rs.getString(1));
			vo.setTeacherName(rs.getString(2));
			list.add(vo);
		}
			
	}catch (Exception e) {
			e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null && !(rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && !(pstmt.isClosed())){
				pstmt.close();
			}
			if(conn!=null && !(conn.isClosed())){
				conn.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in LeaveBankDAOIMPL : getApprovers Ending");
		return list;
}


	@Override
	public ArrayList<LeaveBankVO> getStaffleaveBalance(String academic_year, UserDetailVO userDetailVO,
			UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getApprovers Starting");
		
		ArrayList<LeaveBankVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try{
		conn =JDBCConnection.getSeparateConnection(custdetails);
		pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_BAL_DETAILS);
		pstmt.setString(1, userDetailVO.getGender().substring(0,1).toUpperCase());
		pstmt.setString(2, academic_year);
		pstmt.setString(3, userDetailVO.getUserId());
		rs = pstmt.executeQuery();
		while(rs.next()){
			LeaveBankVO vo = new LeaveBankVO();
			vo.setTotalleaves(rs.getString("leaves_applicable"));
			vo.setAvailable_leave(rs.getDouble("bal_leaves"));
			vo.setConsumedLeave(rs.getString("total_leave_consumed"));
			vo.setLeavetypeName(rs.getString("leavecode"));
			vo.setLeaveid(rs.getInt("leaveid"));
			vo.setAccyear(rs.getString("acadamic_year"));
			list.add(vo);
		}
	}catch (Exception e) {
			e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null && !(rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && !(pstmt.isClosed())){
				pstmt.close();
			}
			if(conn!=null && !(conn.isClosed())){
				conn.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
	+ " Control in LeaveBankDAOIMPL : getApprovers Ending");
	return list;
}


	@Override
	public ArrayList<LeaveBankVO> getleavepolicies(String aid, UserLoggingsPojo custdetails,String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getleavepolicies Starting");
		
		ArrayList<LeaveBankVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loc_id = null;
		ArrayList<String> datarange = new ArrayList<>();
		Set<String> hs = new HashSet<>();
	try{
		LocalDate today = LocalDate.now();
		conn =JDBCConnection.getSeparateConnection(custdetails);
		pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_LEAVE_POLICY);
		pstmt.setString(1, aid);
		rs = pstmt.executeQuery();
		while(rs.next()){
			LeaveBankVO vo = new LeaveBankVO();
			vo.setAccumuLations(rs.getString("acuumulation"));
			vo.setCarryFs(rs.getString("carryforward"));
			vo.setLeave_cycle(rs.getString("leave_cycle"));
			vo.setIsprobationary(rs.getDouble("isprobationary"));
			vo.setMin_lea_per_month(rs.getDouble("min_lea_per_month"));
			vo.setMax_leav_per_month(rs.getDouble("max_leav_per_month"));
			vo.setMax_consecu_lea_perm(rs.getDouble("max_consecu_lea_perm"));
			vo.setWeek_off_inclusion(rs.getString("week_off_inclusion"));
			loc_id = rs.getString("locid");
			//get leaves allowed to take
			//if(vo.getLeave_cycle().equalsIgnoreCase("A") && vo.getCarryFs().equalsIgnoreCase("YC")){
			if(vo.getLeave_cycle().equalsIgnoreCase("A")){
				
				//check for the next academic year
				//String nextdates = HelperClass.getNextAcadamicYearDates(custdetails,academic_year);
				
				//getcurr_accyear stdate and enddate
				String dates = HelperClass.getAcademicYearStartAndEndDate(academic_year,custdetails);
				vo.setCustom_enddate(dates.split(",")[1]);
				
				DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
				
				//convert String to LocalDate
				LocalDate localDate = LocalDate.parse(dates.split(",")[0],formatter);
				
				boolean result = today.minusMonths(1).isAfter(localDate);
				System.out.println("is second date after first date?: "+result);
				if(result){
					vo.setCustom_stdate(today.minusMonths(1).toString());
				}else{
					vo.setCustom_stdate(dates.split(",")[0]);
				}
				
			}else{
			
//				System.out.println("First day: " + today.withDayOfMonth(1));
//				System.out.println("Last day: " + today.withDayOfMonth(today.lengthOfMonth()));
//				
				vo.setCustom_enddate(today.withDayOfMonth(today.lengthOfMonth()).toString());
				vo.setCustom_stdate(today.withDayOfMonth(1).toString());
			}
			
			PreparedStatement getall = conn.prepareStatement("SELECT DISTINCT `StartDate`,`EndDate` FROM `campus_teachers_leave_request` WHERE RequestedBy =? AND `accyid` = ? AND `LeaveType` = ? AND `StartDate` between (? and ?) and `EndDate` <= ?");
			System.out.println(getall);
			getall.setString(1, custdetails.getUsercode());
			getall.setString(2, academic_year);
			getall.setString(3, aid);
			getall.setString(4, vo.getCustom_stdate());
			getall.setString(5, vo.getCustom_enddate());
			getall.setString(6, vo.getCustom_enddate());
			System.out.println(getall);
			ResultSet getalllea = getall.executeQuery();
			while(getalllea.next()){
				datarange.add(getalllea.getString(1));
				datarange.add(getalllea.getString(2));
			}
			hs.addAll(datarange);
			datarange.clear();
			datarange.addAll(hs);
			vo.setDaterange(datarange);
			list.add(vo);
		}
	}catch (Exception e) {
			e.printStackTrace();
	}
	finally{
		try{
			if(rs!=null && !(rs.isClosed())){
				rs.close();
			}
			if(pstmt!=null && !(pstmt.isClosed())){
				pstmt.close();
			}
			if(conn!=null && !(conn.isClosed())){
				conn.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date()) + " Control in LeaveBankDAOIMPL : getleavepolicies Ending");
	return list;
}


	@Override
	public ArrayList<LeaveRequestVo> getleaveRequestDetailsBD(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,String accyid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : getleaveRequestDetailsBD Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sno = 0;
		ArrayList<LeaveRequestVo> list = new ArrayList<>();
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_REQUEST_DETAILS);
			pstmt.setString(1,userDetailVO.getUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//("hello");
				sno++;
				LeaveRequestVo vo1 = new LeaveRequestVo();
				vo1.setSno(rs.getInt("SNO"));
				vo1.setTotalleave(rs.getString("NoofLeaves"));
				vo1.setFromdate(HelperClass.convertDatabaseToUI(rs
						.getString("StartDate")));
				vo1.setTodate(HelperClass.convertDatabaseToUI(rs
						.getString("EndDate")));
				vo1.setStarttime(rs.getString("SessionStart"));
				vo1.setEndtime(rs.getString("SessionEnd"));
				vo1.setStatus(rs.getString("LeaveStatus"));
				vo1.setReason(rs.getString("ReasonForLeave"));
				vo1.setLeavetype(rs.getString("leavename"));
				vo1.setRequestto(rs.getString("teachername"));
				vo1.setRequestby(rs.getString("type"));
				vo1.setLeavesapproved(rs.getString("TotalDaysAproved"));
				list.add(vo1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getleaveRequestDetailsBD  Ending");

		return list;
	}


	@Override
	public ArrayList<LeaveRequestVo> searchleaverequest(UserDetailVO userDetailVO, UserLoggingsPojo custdetails,
			String searchTerm,String accyid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : searchleaverequest Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sno = 0;
		ArrayList<LeaveRequestVo> list = new ArrayList<>();
		
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_LEAVE_REQUEST_DETAILS);
			pstmt.setString(1,userDetailVO.getUserId());
			pstmt.setString(2, "%"+searchTerm+"%");
			pstmt.setString(3, "%"+searchTerm+"%");
			pstmt.setString(4, "%"+searchTerm+"%");
			pstmt.setString(5, "%"+searchTerm+"%");
			pstmt.setString(6, "%"+searchTerm+"%");
			pstmt.setString(7, "%"+searchTerm+"%");
			pstmt.setString(8, "%"+searchTerm+"%");
			pstmt.setString(9, "%"+searchTerm+"%");
			pstmt.setString(10, "%"+searchTerm+"%");
			pstmt.setString(11, "%"+searchTerm+"%");
			
			System.out.println("pstmtpstmtpstmtpstmt"+pstmt);
			rs = pstmt.executeQuery();
			//("rsrsrsrs"+rs);
			
			while (rs.next()) {

				sno++;
				
				LeaveRequestVo vo1 = new LeaveRequestVo();
				vo1.setSno(rs.getInt("SNO"));
				vo1.setTotalleave(rs.getString("NoofLeaves"));
				vo1.setFromdate(HelperClass.convertDatabaseToUI(rs
						.getString("StartDate")));
				vo1.setTodate(HelperClass.convertDatabaseToUI(rs
						.getString("EndDate")));
				vo1.setStarttime(rs.getString("SessionStart"));
				vo1.setEndtime(rs.getString("SessionEnd"));
				vo1.setStatus(rs.getString("LeaveStatus"));
				vo1.setReason(rs.getString("ReasonForLeave"));
				vo1.setLeavetype(rs.getString("leavename"));
				/*vo1.setLeavename(rs.getString("Leave_name"));*/
				vo1.setRequestto(rs.getString("teachername"));
/*				vo1.setRequestby(rs.getString("type"));*/

				list.add(vo1);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}	finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : searchleaverequest  Ending");

		return list;
	}


	@Override
	public LeaveRequestVo getStaffLeaveReq(String leaveid, UserLoggingsPojo custdetails, String academic_year) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffLeaveReq Starting");

		LeaveRequestVo leavelist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			leavelist = new LeaveRequestVo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LEAVE_DETAILS);
			pstmt.setString(1, leaveid);
			rs = pstmt.executeQuery();
			//(pstmt);
			while (rs.next()) {

				leavelist.setRequestto(rs.getString("teachername"));
				leavelist.setTotalleave(rs.getString("NoofLeaves"));
				leavelist.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("StartDate")));
				leavelist.setTodate(HelperClass.convertDatabaseToUI(rs.getString("EndDate")));
				leavelist.setStarttime(rs.getString("SessionStart"));
				leavelist.setEndtime(rs.getString("SessionEnd"));
				leavelist.setLeavetype(rs.getString("LeaveType"));
				leavelist.setFileupload(rs.getString("filepaath"));
				leavelist.setReason(rs.getString("ReasonForLeave"));
				leavelist.setRequesttoid(rs.getString("RequestedTo"));
				leavelist.setLeavename(rs.getString("leavename"));
				leavelist.setSno(Integer.parseInt(leaveid));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
           
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffLeaveReq  Ending");

		return leavelist;
	}


	@Override
	public String deleteStaffleave(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : deleteStaffleave Starting");

		LeaveRequestVo leavelist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String check = MessageConstants.DELETE_FAIL_LEAVE;
		try {
		
			int leavmapid = 0;
			double noofleaves = 0.0;
			double totaldaysapproved = 0.0;
			String leaveType = null;
			double total_available_leave = 0.0;
			double consumed_leave =0.0;
			conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
			
			PreparedStatement leaveUpdate = conn.prepareStatement("select NoofLeaves,TotalDaysAproved,LeaveType from campus_teachers_leave_request where SNO = ?");	
			leaveUpdate.setInt(1,leavevo.getSno());
			ResultSet leavers = leaveUpdate.executeQuery();
			//(leaveUpdate);
			while(leavers.next()){
			noofleaves = leavers.getDouble("NoofLeaves");
			totaldaysapproved = leavers.getDouble("TotalDaysAproved");
			leaveType = leavers.getString("LeaveType");
			}
			leavers.close();
			leaveUpdate.close();
		
			PreparedStatement leaveupdation = conn.prepareStatement("SELECT sl.staff_leave_id,sm.`total_leave_consumed`,sm.`bal_leaves` FROM `staff_leaves_mapping` sm JOIN `staff_leaves_details` sl ON sm.`staff_leave_id` = sl.`staff_leave_id` WHERE `staff_id` = ? AND `accy_id` = ? AND `leave_id` = ?");
			leaveupdation.setString(1,leavevo.getCreateuser());
			leaveupdation.setString(2,leavevo.getAccid());
			leaveupdation.setString(3,leaveType);
			ResultSet leaveupdationrs = leaveupdation.executeQuery();
			//(leaveupdation);
			while(leaveupdationrs.next()){
				total_available_leave = leaveupdationrs.getDouble("bal_leaves");
				consumed_leave = leaveupdationrs.getDouble("total_leave_consumed");
				leavmapid = leaveupdationrs.getInt(1);
			}
			leaveupdationrs.close();
			leaveupdation.close();
			total_available_leave = total_available_leave + totaldaysapproved;
			consumed_leave = consumed_leave - totaldaysapproved;
			PreparedStatement leavecancel = conn.prepareStatement("update staff_leaves_mapping set total_leave_consumed=?,bal_leaves=? where staff_leave_id = ?");
			leavecancel.setDouble(1,consumed_leave);
			leavecancel.setDouble(2,total_available_leave);
			leavecancel.setInt(3,leavmapid);
			int count = leavecancel.executeUpdate();
			//(leavecancel);
			int count1 = 0;
			if(count > 0){
				pstmt = conn.prepareStatement("update campus_teachers_leave_request set LeaveStatus = 'Canceled' where SNO=?");
				pstmt.setInt(1, leavevo.getSno());
				count1 =pstmt.executeUpdate();
			}
		if (count1 > 0) {
			HelperClass.recordLog_Activity(leavevo.getLog_audit_session(),"Leave","Leave Request Details","Delete",pstmt.toString(),leavevo.getDbdetails());
			check = MessageConstants.DELETE_SUCCESS_LEAVE;
		} else {
			check = MessageConstants.DELETE_FAIL_LEAVE;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffLeaveReq  Ending");
		return check;
	}


	@Override
	public ArrayList<LeaveRequestVo> searchleave(String searchTerm, LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : searchleave Starting");
		ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
		int count=0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
		
			pstmt = conn.prepareStatement(ParentModuleUtil.GET_LEAVE_SEARCH_BY_ADMIN);
			pstmt.setString(1, searchTerm+"%");
			pstmt.setString(2, searchTerm+"%");
			pstmt.setString(3, searchTerm+"%");
			pstmt.setString(4, searchTerm+"%");
			pstmt.setString(5, searchTerm+"%");
			pstmt.setString(6, searchTerm+"%");
			pstmt.setString(7, searchTerm+"%");
			pstmt.setString(8, searchTerm+"%");
			pstmt.setString(9, searchTerm+"%");
			pstmt.setString(10,searchTerm+"%");
			pstmt.setString(11,searchTerm+"%");
			pstmt.setString(12,leavevo.getUserid());
			rs = pstmt.executeQuery();
			
			//("searchleavepstmt"+pstmt);
			
			while (rs.next()) {

				count++;

				LeaveRequestVo vo1 = new LeaveRequestVo();

				vo1.setSno(rs.getInt("SNO"));
				vo1.setRequestby(rs.getString("name"));

				vo1.setTotalleave(rs.getString("NoofLeaves"));
				vo1.setReason(rs.getString("ReasonForLeave").trim());
				vo1.setFromdate(HelperClass.convertDatabaseToUI(rs
						.getString("StartDate")));
				vo1.setTodate(HelperClass.convertDatabaseToUI(rs
						.getString("EndDate")));
				vo1.setRequesteddate(HelperClass.convertDatabaseToUI(rs
						.getString("RequestedDate")));
				vo1.setStatus(rs.getString("LeaveStatus"));
				vo1.setLeavetype(rs.getString("leavename"));
				leavelist.add(vo1);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : searchleave Ending");

		return leavelist;
	}


	@Override
	public ArrayList<LeaveRequestVo> getStaffleaveApproval(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffleaveApproval Starting");

		ArrayList<LeaveRequestVo> leavelist = new ArrayList<LeaveRequestVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
				conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
				pstmt = conn.prepareStatement(ParentModuleUtil.GET_LEAVE_APPROVED_BY_ADMIN);
				pstmt.setString(1, leavevo.getUserid());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					LeaveRequestVo vo1 = new LeaveRequestVo();

					vo1.setSno(rs.getInt("SNO"));
					vo1.setRequestby(rs.getString("name"));
					vo1.setTotalleave(rs.getString("NoofLeaves"));
					vo1.setReason(rs.getString("ReasonForLeave").trim());
					vo1.setFromdate(HelperClass.convertDatabaseToUI(rs.getString("StartDate")));
					vo1.setTodate(HelperClass.convertDatabaseToUI(rs.getString("EndDate")));
					vo1.setRequesteddate(HelperClass.convertDatabaseToUI(rs.getString("RequestedDate")));
					vo1.setStatus(rs.getString("LeaveStatus"));
					vo1.setLeavetype(rs.getString("leavename"));
					leavelist.add(vo1);
				}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffleaveApproval  Ending");

		return leavelist;
	}


	@Override
	public LeaveRequestVo getStaffLRDetails(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffLRDetails Starting");

		/*
		 * LeaveRequestVo leavevo = new LeaveRequestVo();
		 * 
		 * leavevo.setUserid("TEA23"); leavevo.setUsertype("admin");
		 * 
		 * JSONArray array=new JSONArray(); array.put(new
		 * ParentSettingsDaoImpl().getleaveApprovalDetailDao(leavevo));
		 * //(array);
		 */

		LeaveRequestVo leavelist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			leavelist = new LeaveRequestVo();
			conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
			pstmt = conn.prepareStatement(ParentModuleUtil.GET_LEAVE_FOR_APPROVE_DETAILS_TEACHER);
			pstmt.setInt(1,leavevo.getSno());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				leavelist.setRequestby(rs.getString("RequestedBy"));
				leavelist.setRequesttype(rs.getString("type"));
				leavelist.setFromdate(HelperClass.convertDatabaseToUI(rs
						.getString("StartDate")));

				leavelist.setTodate(HelperClass.convertDatabaseToUI(rs
						.getString("EndDate")));
				leavelist.setStartsessionDay(rs.getString("SessionStart"));
				leavelist.setEndsessionDay(rs.getString("SessionEnd"));

				leavelist.setLeavetype(rs.getString("leavename"));
				leavelist.setTotalleave(rs.getString("NoofLeaves"));
				leavelist.setReason(rs.getString("ReasonForLeave"));
			}
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherLeaveRequestDaoImpl : getStaffLRDetails Ending");

		return leavelist;
	}


	@Override
	public String ApprovingLeaveforleaveRequestBD(LeaveRequestVo leavevo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getleaveApprovalDetailDao Starting");
		
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		
		PreparedStatement pstmt = null;
	
		int rs = 0;
		Connection conn = null;
		String status = "false";
		
		try {
				//("Admin DAOimpl ForApprovingLeaveforleaveRequestDAO is working");

				conn = JDBCConnection.getSeparateConnection(leavevo.getDbdetails());
				pstmt = conn.prepareStatement("update campus_teachers_leave_request set TotalDaysAproved=?,ApprovedStartDate=?,ApprovedEndDate=?,LeaveStatus=?,commennts=?,ApprovedBy=?,AprovedDate=?,ApproveSessionStart=?,ApproveSessionEnd=? where SNO=?");

				pstmt.setString(1, leavevo.getLeavesapproved());
				pstmt.setString(2, leavevo.getApprovedstartdate());
				pstmt.setString(3, leavevo.getApprovedenddate());
				pstmt.setString(4, leavevo.getApprovedleavestatus());
				pstmt.setString(5, leavevo.getComments());
				pstmt.setString(6, leavevo.getUserid());
				pstmt.setTimestamp(7, new java.sql.Timestamp(today.getTime()));
				pstmt.setString(8, leavevo.getApprovedstartsessionDay());
				pstmt.setString(9, leavevo.getApprovedendsessionDay());
				pstmt.setInt(10, leavevo.getSno());
				//("pstmt" + pstmt);
				rs = pstmt.executeUpdate();
			if(rs > 0){
					HelperClass.recordLog_Activity(leavevo.getLog_audit_session(),"Leave",leavevo.getApprovedleavestatus(),"Update",pstmt.toString(),leavevo.getDbdetails());
					String empId=null,year=null,accYear=null;
					String leaveType=null;
					double leaveCount=0.0,leave_consumed=0,total_availableleave=0,totalDaysApproved=0;
					double totalTypeleave=0,totalConsumeLeave=0,totalAvailableLeave=0;
					double total_year_leaves =0.0;
					double total_avaliable_leaves =0.0;
					int mapId = 0;
					
					PreparedStatement leaveBankpstmt=conn.prepareStatement("select RequestedBy,LeaveType,TotalDaysAproved from campus_teachers_leave_request where SNO=?");
					leaveBankpstmt.setInt(1,leavevo.getSno());
					//(leaveBankpstmt);
					ResultSet leaveBankrs=leaveBankpstmt.executeQuery();
					while(leaveBankrs.next()){
						empId=(leaveBankrs.getString("RequestedBy"));
						leaveType=(leaveBankrs.getString("LeaveType"));
						//("year is "+year);
						totalDaysApproved=(leaveBankrs.getDouble("TotalDaysAproved"));
					}
					leaveBankrs.close();
					leaveBankpstmt.close();
					PreparedStatement leaveBankpstmt1=conn.prepareStatement("SELECT `total_leave_consumed`,`bal_leaves`,leaves_applicable,sm.`staff_leave_id` FROM `staff_leaves_mapping` sm JOIN `staff_leaves_details` sl ON sm.`staff_leave_id` = sl.`staff_leave_id`AND sl.`accy_id` = ? WHERE sl.`staff_id` = ? AND `leave_id` = ?");
					leaveBankpstmt1.setString(1, leavevo.getAccid());
					leaveBankpstmt1.setString(2, empId);
					leaveBankpstmt1.setString(3, leaveType);
					//(leaveBankpstmt1);
					ResultSet leaveBankrs1=leaveBankpstmt1.executeQuery();
					while(leaveBankrs1.next()){
						mapId = leaveBankrs1.getInt("staff_leave_id");
						total_avaliable_leaves = (leaveBankrs1.getDouble("bal_leaves"));
						leave_consumed=(leaveBankrs1.getDouble("total_leave_consumed"));
						total_year_leaves=Double.parseDouble(leaveBankrs1.getString("leaves_applicable"));
					}
					leaveBankrs1.close();
					leaveBankpstmt1.close();
					
					totalConsumeLeave=leave_consumed+totalDaysApproved;
					totalAvailableLeave=total_year_leaves-totalConsumeLeave;
					
					PreparedStatement leaveBankpstmt2=conn.prepareStatement("UPDATE `staff_leaves_mapping` SET `total_leave_consumed`=?,`bal_leaves`=? WHERE `staff_leave_id` = ? AND `leave_id` =?");
					leaveBankpstmt2.setDouble(1, totalConsumeLeave);
					leaveBankpstmt2.setDouble(2, totalAvailableLeave);
					leaveBankpstmt2.setInt(3, mapId);
					leaveBankpstmt2.setString(4, leaveType);
		
					leaveBankpstmt2.executeUpdate();
					leaveBankpstmt2.close();
					status = "true";
				}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}	finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getleaveApprovalDetailDao  Ending");

		return status;
	}



	@Override
	public String addLeavePolicy(LeaveBankVO leaveVo, UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : addLeavePolicy Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String status = null;
		int result = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.ADD_LEAVE_TYPES);
		
				pstmt.setString(1,leaveVo.getShortName());
				pstmt.setString(2,leaveVo.getAccyear());
				pstmt.setString(3,leaveVo.getLocId());
				pstmt.setString(4,leaveVo.getLeaveName());
				pstmt.setString(5,leaveVo.getNoofleaves());
				pstmt.setString(6,leaveVo.getCarryFs());
				pstmt.setString(7,leaveVo.getCreateuser());
				pstmt.setString(8,leaveVo.getIsGenderSpec());
				pstmt.setDouble(9,leaveVo.getIsprobationary());
				pstmt.setDouble(10,leaveVo.getMin_lea_per_month());
				pstmt.setDouble(11,leaveVo.getMax_leav_per_month());
				pstmt.setDouble(12,leaveVo.getMax_consecu_lea_perm());
				pstmt.setString(13,leaveVo.getWeek_off_inclusion());
				pstmt.setString(14,leaveVo.getLeave_cycle());
				count = pstmt.executeUpdate();
				if(count > 0){
					result ++;
					HelperClass.recordLog_Activity(leaveVo.getLogaudit(),"Leave","Leave Types","Insert",pstmt.toString(),dbdetails,conn);
					conn.commit();
					status = "success";
				}
				else{
					conn.rollback();
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			status = "fail";
		}
		catch(Exception e){
			status = "fail";
			e.printStackTrace();
		}
		finally{
			try{
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
				status = "fail";
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : addLeavePolicy Ending");
		return status;
	}


	@Override
	public List<LeaveBankVO> getLeavePolicy(String leaveid, UserLoggingsPojo dbdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LeaveBankDAOIMPL : addLeavePolicy Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LeaveBankVO> details = new ArrayList<>();
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_LEAVE_POLICY);
			pstmt.setString(1, leaveid);
			rs = pstmt.executeQuery();
			//(pstmt);
			while(rs.next()){
				LeaveBankVO obj = new LeaveBankVO();
				obj.setShortName(rs.getString("leavecode"));
				obj.setAccyear(rs.getString("accid"));
				obj.setLocId(rs.getString("locid"));
				obj.setLeaveName(rs.getString("leavename"));
				obj.setNoofleaves(rs.getString("noofleaves"));
				obj.setCarryFs(rs.getString("carryforward"));
				obj.setIsGenderSpec(rs.getString("isGenderSpec"));
				obj.setIsprobationary(rs.getDouble("isprobationary"));
				obj.setMin_lea_per_month(rs.getDouble("min_lea_per_month"));
				obj.setMax_leav_per_month(rs.getDouble("max_leav_per_month"));
				obj.setMax_consecu_lea_perm(rs.getDouble("max_consecu_lea_perm"));
				obj.setWeek_off_inclusion(rs.getString("week_off_inclusion"));
				obj.setLeave_cycle(rs.getString("leave_cycle"));
				details.add(obj);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDAOIMPL : addLeavePolicy Ending");
		return details;
	}



	@Override
public String getleaveCountForApprove(String academic_year, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getleaveCountForApprove Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		String status = "";
		ResultSet rs = null ;
		try {

			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ParentModuleUtil.GET_LEAVE_APPROVE_PENDING_LIST);
			pstmt.setString(1,academic_year);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				status=rs.getString("total");
			}
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	  finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			  } catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			  }
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportDaoImpl : getleaveCountForApprove  Ending");

		return status;
		
		
	}


	@Override
	public String getStaffLeaveCountForApprove(String academic_year, String userId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDaoImpl : getStaffLeaveCountForApprove Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		String status = "";
		ResultSet rs = null ;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ParentModuleUtil.GET_STAFF_LEAVE_APPROVE_PENDING_LIST);
			pstmt.setString(1,academic_year);
			pstmt.setString(2,userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				status=rs.getString("total");
			}
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	  finally {
			try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
			  } catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
			  }
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDaoImpl : getStaffLeaveCountForApprove  Ending");

		return status;
	}

	@Override
	public ArrayList<LeaveBankVO> getLeaveApprover(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDaoImpl : getStaffLeaveCountForApprove Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveBankVO> list = new ArrayList<>();
		String reques_to = null;
		String loc_id = null;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT Loc_ID,CASE WHEN reportingTo IS NULL THEN '-' WHEN reportingTo ='' THEN '-' ELSE reportingTo END reportingTo "+
                                          "FROM `campus_teachers` t WHERE `TeacherID` = ?");
			pstmt.setString(1,custdetails.getUsercode());
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				reques_to = rs.getString(2);
				loc_id = rs.getString(1);
			}
			rs.close();
			pstmt.close();
			pstmt = conn.prepareStatement(LeaveBankSqlUtil.GET_LEAVE_APPROVER);
			pstmt.setString(1,loc_id);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				LeaveBankVO vo = new LeaveBankVO();
				vo.setTeacherName(rs.getString(1));
				vo.setTeacherId(rs.getString(2));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LeaveBankDaoImpl : getStaffLeaveCountForApprove  Ending");
		return list;
	}

	
}
