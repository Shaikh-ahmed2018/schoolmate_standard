package com.centris.campus.daoImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.StaffAttendanceDao;
import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StaffAttendanceSqlUtil;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceDaoImpl implements StaffAttendanceDao{
	
	private static final Logger logger = Logger.getLogger(StaffAttendanceDaoImpl.class);

	
	public ArrayList<StaffAttendanceVo> getStaffAttendance(String date,String department,UserLoggingsPojo custdetails,String locId) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance : Starting");
			
			PreparedStatement ps_insertPlan = null;
			ResultSet rs=null;
			Connection conn = null;
			ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
			int count=0;
			
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
			
				ps_insertPlan = conn.prepareStatement(StaffAttendanceSqlUtil.GET_STAFF_ATTENDANCE);
				
				ps_insertPlan.setString(1,date);
				ps_insertPlan.setString(2,department);
				ps_insertPlan.setString(3,locId);
				
				System.out.println("GET_STAFF_ATTENDANCE -->"+ps_insertPlan);
				
				rs=ps_insertPlan.executeQuery();
				
				while(rs.next()){
					
					count++;
					
					StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
					
					attendanceVo.setCount(count);
					attendanceVo.setTeacherId(rs.getString("TeacherID"));
					attendanceVo.setRegid(rs.getString("registerId"));
					attendanceVo.setTeacherName(rs.getString("teacherName"));
					attendanceVo.setDesignation(rs.getString("designationName"));
					attendanceVo.setDepartment(rs.getString("DEPT_NAME"));
					attendanceVo.setStatus(rs.getString("status"));
					
					staffAttendanceList.add(attendanceVo);
					
				}
				
				
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
						ps_insertPlan.close();
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return staffAttendanceList;
		}


	
	public String updateAttendanceStatus(StaffAttendancePojo attPojo,UserLoggingsPojo userLoggingsVo) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: updateAttendanceStatus : Starting");
			PreparedStatement ps_count = null;
			PreparedStatement ps_insert = null;
			ResultSet rs=null;
			Connection conn = null;
			int count=0;
			int statuscount=0;
			String result=null;
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				ps_count = conn.prepareStatement(StaffAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
				String teacherId[]=attPojo.getTeacherId().split(",");
				String attaStatus[] =attPojo.getStatus().split(",");
				for(int i=0;i<teacherId.length;i++){
					ps_count.setString(1,teacherId[i]);
					ps_count.setString(2,HelperClass.convertUIToDatabase(attPojo.getDate()));
					rs=ps_count.executeQuery();
					while(rs.next()){
						count=rs.getInt(1);
					}
					if(count>0){
						
						ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.UPDATE_ATTENDANCE);
						ps_insert.setString(1, attaStatus[i]);
						ps_insert.setString(2, attPojo.getUserId());
						ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
						ps_insert.setString(4, teacherId[i]);
						ps_insert.setString(5, HelperClass.convertUIToDatabase(attPojo.getDate()));
					//System.out.println("UPDATE "+ps_insert);
						statuscount=ps_insert.executeUpdate();
						if(statuscount>0){
							CallableStatement calst=conn.prepareCall("{call todayTimeTable_Procedure()}");
							calst.executeUpdate();
							HelperClass.recordLog_Activity(attPojo.getLog_audit_session(),"Staff","StaffAttendance","Update",ps_insert.toString(),userLoggingsVo);
							result="true";
						}else{
							result="false";
						}
					}else{
						ps_insert = conn.prepareStatement(StaffAttendanceSqlUtil.INSERT_ATTENDANCE);
						ps_insert.setString(1, teacherId[i]);
						ps_insert.setString(2, HelperClass.convertUIToDatabase(attPojo.getDate()));
						ps_insert.setString(3, attaStatus[i]);
						ps_insert.setString(4, attPojo.getUserId());
						ps_insert.setTimestamp(5, HelperClass.getCurrentTimestamp());
						ps_insert.setString(6, attPojo.getAccYear());
						statuscount=ps_insert.executeUpdate();
						if(statuscount>0){
							CallableStatement calst=conn.prepareCall("{call todayTimeTable_Procedure()}");
							calst.executeUpdate();
							
							HelperClass.recordLog_Activity(attPojo.getLog_audit_session(),"Staff","StaffAttendance","Insert",ps_insert.toString(),userLoggingsVo);
							result="true";
						}else{
							result="false";
						}
						
					}
					
				}
		
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} finally {
				try {
					if (rs != null&& (!rs.isClosed())) {
						rs.close();
					}
					if (ps_count != null&& (!ps_count.isClosed())) {
						ps_count.close();
					}
					if (ps_insert != null&& (!ps_insert.isClosed())) {
						ps_insert.close();
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

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceDaoImpl: updateAttendanceStatus: Ending");
			
			return result;
		}
	
	
	
	public ArrayList<StaffAttendanceVo> getStaffAttendanceList(String startdate,String enddate,UserLoggingsPojo userLoggingsVo,String locationId,String accYear) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceList : Starting");
		
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			String todayDate=null;
			String startDate=null;
			
			System.out.println("accYear -->>"+accYear);
			System.out.println("accYear -->>"+accYear);
			System.out.println("getAcademicYearStartAndEndDate -->>"+HelperClass.getAcademicYearStartAndEndDate(accYear, userLoggingsVo));
			System.out.println("getAcademicYearStartAndEndDate -->>"+HelperClass.getAcademicYearStartAndEndDate(accYear, userLoggingsVo));
			
			String[] date=HelperClass.getAcademicYearStartAndEndDate(accYear, userLoggingsVo).split(","); 
			
			
			
			if((startdate==null || startdate.equalsIgnoreCase(""))&&(enddate==null || enddate.equalsIgnoreCase(""))){
				startDate=date[0];
				todayDate=date[1];
			}
			else if(enddate==null || enddate.equalsIgnoreCase("")){
			 /*todayDate=HelperClass.getCurrentSqlDate().toString();
			 startDate=HelperClass.getLastThirtyDateFromNow(todayDate);*/
				startDate=HelperClass.convertUIToDatabase(startdate);
				todayDate=date[1];
				
			}else{
				
				 todayDate=HelperClass.convertUIToDatabase(enddate);
				 startDate=HelperClass.convertUIToDatabase(startdate);
				
			}
			
			
			ps_insertPlan = conn.prepareStatement(StaffAttendanceSqlUtil.GET_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,"%"+locationId+"%");
			ps_insertPlan.setString(2,accYear);
			ps_insertPlan.setString(3,startDate);
			ps_insertPlan.setString(4,todayDate);
			
			System.out.println("get attendance ::: "+ps_insertPlan);
			
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				count++;
				StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setStartDate(HelperClass.convertDatabaseToUI(startDate));
				attendanceVo.setEndDate(HelperClass.convertDatabaseToUI(todayDate));
				staffAttendanceList.add(attendanceVo);
			}
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceList: Ending");
		
		return staffAttendanceList;
	}



	@Override
	public ArrayList<StaffAttendanceVo> getStaffAttendanceCount(String startdate, UserLoggingsPojo userLoggingsVo,
			String msg, String location) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceCount : Starting");
		
		PreparedStatement ps_insertPlan = null,pstmt=null;
		ResultSet rs=null, rs1=null;
		Connection conn = null;
		ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			
			if(msg.equalsIgnoreCase("information")){
					pstmt = conn.prepareStatement("SELECT CONCAT(ct.`FirstName`,' ',ct.`LastName`) tname,ca.`TeacherID`,ca.`AttendenceStatus` FROM `campus_teacher_attendence` ca JOIN  `campus_teachers` ct ON ct.`TeacherID`=ca.`TeacherID` WHERE ca.`AttendenceDate`=CURDATE() AND ca.AttendenceStatus!='Present' AND ct.Loc_ID=?");
					pstmt.setString(1, location);
					rs1=pstmt.executeQuery(); 
					while(rs1.next()){
						StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
						attendanceVo.setTeacherName(rs1.getString("tname"));
						attendanceVo.setStatus(rs1.getString("AttendenceStatus"));
						staffAttendanceList.add(attendanceVo);
					}
			}
			else{
				ps_insertPlan = conn.prepareStatement("SELECT `DEPT_ID`,`DEPT_NAME` FROM `campus_department` WHERE `isActive`='Y'");
				
				rs=ps_insertPlan.executeQuery();
				while(rs.next()){
					StaffAttendanceVo attendanceVo=new StaffAttendanceVo();
					pstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_teacher_attendence` cta JOIN `campus_teachers` ct ON ct.`TeacherID`=cta.`TeacherID` WHERE cta.`AttendenceDate`=? AND ct.`department`=? AND (cta.`AttendenceStatus`='Absent' OR cta.`AttendenceStatus`='Holiday' OR cta.`AttendenceStatus`='Leave') AND ct.Loc_ID=?");
					pstmt.setString(1, startdate);
					pstmt.setString(2, rs.getString("DEPT_ID"));
					pstmt.setString(3, location);
					rs1=pstmt.executeQuery();
					while(rs1.next()){
						attendanceVo.setDepartment(rs.getString("DEPT_NAME"));
						attendanceVo.setAbsent_count(rs1.getString(1));
					}
					staffAttendanceList.add(attendanceVo);
				}
			}
			
			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceCount: Ending");
		return staffAttendanceList;
	}



	public List<StaffAttendanceVo> StaffAttendanceforDashboard(String location, String accid, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceCount : Starting");
		
		PreparedStatement ps_insertPlan = null,pstmt=null;
		ResultSet rs=null, rs1=null;
		Connection conn = null;
		ArrayList<StaffAttendanceVo> staffAttendanceList=new ArrayList<StaffAttendanceVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			ps_insertPlan = conn.prepareStatement("SELECT t.`TeacherID`,t.`AttendenceStatus`,CONCAT(s.`FirstName`,' ',s.`LastName`) staffName FROM `campus_teacher_attendence` t LEFT JOIN `campus_teachers` s ON s.`TeacherID`=t.TeacherID WHERE t.`AttendenceStatus` IN ('Absent','Leave') AND t.`academic_Year`=? AND s.`Loc_ID`=? AND s.`isActive`='Y' AND t.AttendenceDate=CURDATE()");
			ps_insertPlan.setString(1, accid);
			ps_insertPlan.setString(2, location);
			rs = ps_insertPlan.executeQuery();
			System.out.println("schafha="+ps_insertPlan);
			int count=0;
			while(rs.next()) {
				count++;
				StaffAttendanceVo vo=new StaffAttendanceVo();
				
				vo.setCount(count);
				
				vo.setStatus(rs.getString("AttendenceStatus"));
				vo.setTeacherName(rs.getString("staffName"));
				staffAttendanceList.add(vo);
				
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (conn != null&& (!conn.isClosed())) {
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceDaoImpl: getStaffAttendanceCount: Ending");
		
		return staffAttendanceList;
	}
}
