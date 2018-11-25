package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentAttendanceDao;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StudentAttendanceSqlUtil;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceDaoImpl implements StudentAttendanceDao{
	
	private static final Logger logger = Logger.getLogger(StudentAttendanceDaoImpl.class);

	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null,pstm=null,pstm1=null;
		ResultSet rs=null,rs1=null,rs2=null;
		Connection conn = null;
		 
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		 
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);

			pstm = conn.prepareStatement("SELECT DISTINCT specializationId FROM campus_attendence");
			rs1=pstm.executeQuery();
			while(rs1.next()){
				String values=rs1.getString("specializationId"); 

				if(!values.equalsIgnoreCase("-") || values!="-")
				{
					pstm1 = conn.prepareStatement(StudentAttendanceSqlUtil.GET_LIST_OF_STUDENT_ATTENDENCE);
					if(startDate==null || "".equalsIgnoreCase(startDate)){
						pstm1.setString(1,lastdate);
						pstm1.setString(2,todayDate);
					}
					else
					{
						pstm1.setString(1,HelperClass.convertUIToDatabase(startDate));
						pstm1.setString(2,HelperClass.convertUIToDatabase(endDate));
					}
					pstm1.setString(3,values);
					System.out.println(pstm1);
					rs2=pstm1.executeQuery();

					while(rs2.next()){
						count++;
						StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
						attendanceVo.setCount(count);
						attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs2.getString("attendencedate")));
						attendanceVo.setTot_count(rs2.getString("total_streangth"));
						attendanceVo.setAbsent_count(rs2.getString("TotalAbsent"));
						attendanceVo.setPresent_count(rs2.getString("TotalPresent"));
						attendanceVo.setLeave_count(rs2.getString("Totalleave"));
						attendanceVo.setClasssection(rs2.getString("classsection"));
						attendanceVo.setClassId(rs2.getString("classdetail_id_int"));
						attendanceVo.setSectionId(rs2.getString("classsection_id_int"));
						attendanceVo.setSpecId(rs2.getString("specilization"));
						staffAttendanceList.add(attendanceVo);
					}
				}
			}

			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST);
			if(startDate==null || "".equalsIgnoreCase(startDate)){
				ps_insertPlan.setString(1,lastdate);
				ps_insertPlan.setString(2,todayDate);
			}else{
				ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(startDate));
				ps_insertPlan.setString(2,HelperClass.convertUIToDatabase(endDate));
			}
			System.out.println("GET_ATTENDANCE_LIST 2-->:>"+ps_insertPlan);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){
				count++;
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("attendencedate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecId(rs.getString("specilization"));

				staffAttendanceList.add(attendanceVo);
			}
			Collections.sort(staffAttendanceList, new StudentAttendanceVo());
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
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstm1 != null&& (!pstm1.isClosed())) {
					pstm1.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstm != null&& (!pstm.isClosed())) {
					pstm.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(
			StudentAttendancePojo studentAttPojo,UserLoggingsPojo custdetails) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails : Starting");
		String value=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
				if(studentAttPojo.getSpecID() == "" || studentAttPojo.getSpecID() == null ){
					
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_ATTENDANCE_LIST);
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
					ps_insertPlan.setString(4,studentAttPojo.getAccYearId());
					
					value="without Spec";
				}
				else{
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SPEC_STUDENT_ATTENDANCE_LIST);	
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
					ps_insertPlan.setString(4,studentAttPojo.getSpecID());
					ps_insertPlan.setString(5,studentAttPojo.getAccYearId());
					
					value="with Spec";
				}
				 
				
				 
			rs=ps_insertPlan.executeQuery();
			//ln("GET_STUDENT_ATTENDANCE_LIST "+value+" -->>"+ps_insertPlan);
			while(rs.next()){
				
				count++;
				
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setStudentid(rs.getString("stud_id"));
				attendanceVo.setAddmissionNo(rs.getString("student_admissionno_var"));
				attendanceVo.setStudentname(rs.getString("studentname"));
				/*attendanceVo.setClassname(rs.getString("classdetails_name_var"));
				attendanceVo.setSectionname(rs.getString("classsection_name_var"));*/
	/*			attendanceVo.setLocationId(rs.getString("locationId"));*/
				attendanceVo.setStatus(rs.getString("status"));
				attendanceVo.setAttendancestatus(rs.getString("periodstatus"));
			/*	attendanceVo.setAccYear(rs.getString("fms_acadamicyear_id_int"));*/
				attendanceVo.setAttid(rs.getString("attend_id"));
				attendanceVo.setSatusid(rs.getString("status_id"));
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
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceDaoImpl: updateAttendanceStatus : Starting");
			
			PreparedStatement ps_count = null;
			PreparedStatement ps_insert = null;
			
			ResultSet rs=null;
			Connection conn = null;
			int count=0;
			
			int statuscount=0;
			
			String result=null;
			
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
			
				ps_count = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
				
				String teacherId[]=studentAttPojo.getStudentid().split(",");
				String attaStatus[] =studentAttPojo.getStatus().split(",");
				String period1[] = studentAttPojo.getPeriod1().split(",");
				String period2[] = studentAttPojo.getPeriod2().split(",");
				String period3[] = studentAttPojo.getPeriod3().split(",");
				String period4[] = studentAttPojo.getPeriod4().split(",");
				String period5[] = studentAttPojo.getPeriod5().split(",");
				String period6[] = studentAttPojo.getPeriod6().split(",");
				String period7[] = studentAttPojo.getPeriod7().split(",");
				String period8[] = studentAttPojo.getPeriod8().split(",");
				String period9[] = studentAttPojo.getPeriod9().split(",");
				
				for(int i=0;i<teacherId.length;i++){
				
					ps_count.setString(1,teacherId[i]);
					ps_count.setString(2,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					rs=ps_count.executeQuery();
					
					while(rs.next()){
						count=rs.getInt(1);
					}
					
					if(count>0){
						
						ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.UPDATE_ATTENDANCE);
						ps_insert.setString(1, attaStatus[i]);
						ps_insert.setString(2, studentAttPojo.getUserId());
						ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
						ps_insert.setString(4,period1[i]);
						ps_insert.setString(5,period2[i]);
						ps_insert.setString(6,period3[i]);
						ps_insert.setString(7,period4[i]);
						ps_insert.setString(8,period5[i]);
						ps_insert.setString(9,period6[i]);
						ps_insert.setString(10,period7[i]);
						ps_insert.setString(11,period8[i]);
						ps_insert.setString(12,period9[i]);
						/*ps_insert.setString(13,studentAttPojo.getAccYearId());*/
						ps_insert.setString(13, teacherId[i]);
						ps_insert.setString(14, HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
						
						//ln("UPDATE_ATTENDANCE -->>"+ps_insert);
						
						statuscount = ps_insert.executeUpdate();
						if(statuscount > 0){
							HelperClass.recordLog_Activity(studentAttPojo.getLog_audit_session(),"Student","Attendance","Update",ps_insert.toString(),custdetails);
						}
						
					}else{
						
						ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_ATTENDANCE);
						ps_insert.setString(1, teacherId[i]);
						ps_insert.setString(2, HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
						ps_insert.setString(3, attaStatus[i]);
						ps_insert.setString(4, studentAttPojo.getUserId());
						ps_insert.setTimestamp(5, HelperClass.getCurrentTimestamp());
						ps_insert.setString(6,period1[i]);
						ps_insert.setString(7,period2[i]);
						ps_insert.setString(8,period3[i]);
						ps_insert.setString(9,period4[i]);
						ps_insert.setString(10,period5[i]);
						ps_insert.setString(11,period6[i]);
						ps_insert.setString(12,period7[i]);
						ps_insert.setString(13,period8[i]);
						ps_insert.setString(14,period9[i]);
						ps_insert.setString(15,studentAttPojo.getTeacherId());
						ps_insert.setString(16,studentAttPojo.getLocationId());
						ps_insert.setString(17,studentAttPojo.getAccYearId());
						if(studentAttPojo.getSpecID()=="" || studentAttPojo.getSpecID()==null){
							ps_insert.setString(18,"-");
						}else{
							ps_insert.setString(18,studentAttPojo.getSpecID());
						}
						
						//ln("INSERT_ATTENDANCE -->>"+ps_insert);
						
						statuscount=ps_insert.executeUpdate();
						if(statuscount > 0){
							HelperClass.recordLog_Activity(studentAttPojo.getLog_audit_session(),"Student","Attendance","Insert",ps_insert.toString(),custdetails);
						}
					}
				}
				
				if(statuscount>0){
					
					result="true";
				
				}else{
					
					result="false";
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
					+ " Control in StudentAttendanceDaoImpl: updateAttendanceStatus: Ending");
			
			return result;
		}

	public List<LstmsStudentPOJO> getAllStudentDao(String classVal, String sectionVal,String locationid,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Starting");
		
		
		ArrayList<LstmsStudentPOJO> lstmsStudentPOJOList = new ArrayList<LstmsStudentPOJO>();
		LstmsStudentPOJO lstmsStudentPOJO = null;
		ResultSet resultSetClass = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(custid);
			
			 pstmt = (PreparedStatement) conn.prepareStatement(SQLUtilConstants.ALLSTUDENTNAME);
			
				pstmt.setString(1, classVal);
				pstmt.setString(2, sectionVal);
				pstmt.setString(3, locationid);
				
				resultSetClass = pstmt.executeQuery();
				
				while(resultSetClass.next()){
					
					lstmsStudentPOJO = new LstmsStudentPOJO();
					lstmsStudentPOJO.setStudentIdInt(resultSetClass
							.getString("student_id_int"));

					lstmsStudentPOJO.setStudentFnameVar(resultSetClass
							.getString("studentname"));
					lstmsStudentPOJO.setStudentAdmissionnoVar(resultSetClass
							.getString("student_admissionno_var"));
					
					lstmsStudentPOJOList.add(lstmsStudentPOJO);
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (resultSetClass != null&& (!resultSetClass.isClosed())) {
					resultSetClass.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getAllStudentDao Ending");
		
		return lstmsStudentPOJOList;
	}
	
	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportDao(
			StudentAttendanceReportVo vo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceReportVo> attendList=new ArrayList<StudentAttendanceReportVo>();
		int count=0;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ATTENDANCE_LIST);
			
			
			pstmt.setString(1, vo.getFromdate());
			pstmt.setString(2, vo.getTodate());
			pstmt.setString(3, vo.getStudentid());
			
		
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				count++;
				StudentAttendanceReportVo attvo = new StudentAttendanceReportVo();
				
				attvo.setCount(count);
				attvo.setStudentname(rs.getString("studentname"));
				attvo.setStreamname(rs.getString("classstream_name_var"));
				attvo.setClassname(rs.getString("classdetails_name_var"));
				attvo.setSectionname(rs.getString("classsection_name_var"));
				
				attvo.setAttdancedate(HelperClass.convertDatabaseToUI(rs.getString("attendencedate")));
				attvo.setAttendancestatus(rs.getString("attendence"));
				attvo.setAccyrname(rs.getString("acadamic_year"));
				
				attendList.add(attvo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceReportDao Ending");
		
		return attendList;
	}

	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportDao(
			StudentAttendanceReportVo stuvo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceListReportDao Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceReportVo> attendList=new ArrayList<StudentAttendanceReportVo>();
		int count=0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_ATTENDANCE_LIST);
			
			
			pstmt.setString(1, stuvo.getFromdate());
			pstmt.setString(2, stuvo.getTodate());
			pstmt.setString(3, stuvo.getStudentid());
			
			
			//ln("STUEDENT REPORT "+pstmt);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				count++;
				StudentAttendanceReportVo attvo = new StudentAttendanceReportVo();
				
				attvo.setCount(count);
				attvo.setStudentname(rs.getString("studentname"));
				attvo.setStreamname(rs.getString("classstream_name_var"));
				attvo.setClassname(rs.getString("classdetails_name_var"));
				attvo.setSectionname(rs.getString("classsection_name_var"));
				
				attvo.setAttdancedate(HelperClass.convertDatabaseToUI(rs.getString("attendencedate")));
				attvo.setAttendancestatus(rs.getString("attendence"));
				attvo.setAccyrname(rs.getString("acadamic_year"));
				
				attendList.add(attvo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getStudentAttendanceListReportDao Ending");
		
		return attendList;
	}
	
	public StreamDetailsVO getStreamNameDaoImpl(String stream,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStreamNameDaoImpl Starting");
		
		StreamDetailsVO selecteditems=new StreamDetailsVO();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
/*		int count=0; */
		
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STREAM_NAME);
			pstmt.setString(1, stream);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				selecteditems.setStreamName(rs.getString("classstream_name_var"));
				selecteditems.setStreamId(rs.getString("classstream_id_int"));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getStreamNameDaoImpl Ending");
		return selecteditems;
	}

	public ClassPojo getClassNameDaoImpl(String classname,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getClassNameDaoImpl Starting");
		ClassPojo selecteditems=new ClassPojo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
	/*	int count=0; */
		
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_NAME);
			pstmt.setString(1, classname);
			rs = pstmt.executeQuery();
			while(rs.next()){
				selecteditems.setClassId(rs.getString("classdetail_id_int"));
				selecteditems.setClassName(rs.getString("classdetails_name_var"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getClassNameDaoImpl Ending");
		return selecteditems;
	}
	
	public SectionPojo getSectionNameDaoImpl(String sectionname,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getSectionNameDaoImpl Starting");

		SectionPojo selecteditems=new SectionPojo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		/*int count=0; */
		try {
			
			conn = JDBCConnection.getSeparateConnection(custid);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_SECTION_NAME);
			pstmt.setString(1, sectionname);
			rs = pstmt.executeQuery();
			while(rs.next()){
				selecteditems.setSectionId(rs.getString("classsection_id_int"));
				selecteditems.setSectionName(rs.getString("classsection_name_var"));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getSectionNameDaoImpl Ending");
		
		return selecteditems;
	}
	
	public ParentVO getStudentNameDaoImpl(String student,UserLoggingsPojo custid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentNameDaoImpl Starting");
		ParentVO selecteditems=new ParentVO();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		/*int count=0; */
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_STUDENT_NAME);
			pstmt.setString(1, student);
			rs = pstmt.executeQuery();
			while(rs.next()){
				selecteditems.setStudentid(rs.getString("student_id_int"));
				selecteditems.setStudentFnameVar(rs.getString("studentname"));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getStudentNameDaoImpl Ending");
		
		return selecteditems;
	}

	@Override
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentPeriodAttendance Starting");
		
		StudentAttendanceVo selecteditems=new StudentAttendanceVo();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.GET_STU_PERIOD_ATT);
			
			pstmt.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
			pstmt.setString(2, AttendancePojo.getStudentid());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				selecteditems.setStudentid(rs.getString("StudentId"));
				selecteditems.setDate(rs.getString("AttendanceDate"));
				selecteditems.setPeriod1(rs.getString("Period1"));
				selecteditems.setPeriod2(rs.getString("Period2"));
				selecteditems.setPeriod3(rs.getString("Period3"));
				selecteditems.setPeriod4(rs.getString("Period4"));
				selecteditems.setPeriod5(rs.getString("Period5"));
				selecteditems.setPeriod6(rs.getString("Period6"));
				selecteditems.setPeriod7(rs.getString("Period7"));
				selecteditems.setPeriod8(rs.getString("Period8"));
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
				+ " Control in StudentAttendanceDaoImpl : getStudentPeriodAttendance Ending");
		
		return selecteditems;
	
	}

	@Override
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Starting");
		String status=null;

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		PreparedStatement pstmt1 = null;
		
		Connection conn = null;
		
		int count=0;
		int result=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.CHECK_STU_PERIOD_ATT);
			pstmt.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
			pstmt.setString(2, AttendancePojo.getStudentid());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				count=rs.getInt(1);
			}
			
			if(count>0){
				
				pstmt1 = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.UPDATE_STU_PERIOD_ATT);
				
				pstmt1.setString(1, AttendancePojo.getPeriod1());
				pstmt1.setString(2, AttendancePojo.getPeriod2());
				pstmt1.setString(3, AttendancePojo.getPeriod3());
				pstmt1.setString(4, AttendancePojo.getPeriod4());
				pstmt1.setString(5, AttendancePojo.getPeriod5());
				pstmt1.setString(6, AttendancePojo.getPeriod6());
				pstmt1.setString(7, AttendancePojo.getPeriod7());
				pstmt1.setString(8, AttendancePojo.getPeriod8());
				pstmt1.setString(9, AttendancePojo.getUserId());
				pstmt1.setTimestamp(10, HelperClass.getCurrentTimestamp());
				pstmt1.setString(11, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
				pstmt1.setString(12, AttendancePojo.getStudentid());
				
				
				
				result=pstmt1.executeUpdate();
				
				
			}else{
				
				pstmt1 = (PreparedStatement) conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_STU_PERIOD_ATT);
				pstmt1.setString(1, HelperClass.convertUIToDatabase(AttendancePojo.getDate()));
				pstmt1.setString(2, AttendancePojo.getStudentid());
				pstmt1.setString(3, AttendancePojo.getPeriod1());
				pstmt1.setString(4, AttendancePojo.getPeriod2());
				pstmt1.setString(5, AttendancePojo.getPeriod3());
				pstmt1.setString(6, AttendancePojo.getPeriod4());
				pstmt1.setString(7, AttendancePojo.getPeriod5());
				pstmt1.setString(8, AttendancePojo.getPeriod6());
				pstmt1.setString(9, AttendancePojo.getPeriod7());
				pstmt1.setString(10, AttendancePojo.getPeriod8());
				pstmt1.setString(11, AttendancePojo.getUserId());
				pstmt1.setTimestamp(12, HelperClass.getCurrentTimestamp());
				
				
				
				result=pstmt1.executeUpdate();
			}
			
			if(result>0){
				
				status="true";
			}else{
				
				status="false";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : updateStudentPeriodAtt Ending");
		
		return status;
	
	}

	@Override
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getteacherByClass Starting");
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custid);
			pstmt = conn.prepareStatement(StudentAttendanceSqlUtil.GET_TEACHERBY_CLASS);
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionId);
			rs= pstmt.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setTeacherID(rs.getString("TeacherID"));
				if(rs.getString("teacher").equalsIgnoreCase(""))
					vo.setTeacherName(rs.getString(""));
				else
				vo.setTeacherName(rs.getString("teacher"));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
				+ " Control in StudentAttendanceDaoImpl : getteacherByClass Ending");
		return list;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId,String locationId,UserLoggingsPojo custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getClassSpec Starting");
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		try{
			conn= JDBCConnection.getSeparateConnection(custid);
			pstmt = conn.prepareStatement("SELECT DISTINCT cs.Specialization_Id,cs.Specialization_name FROM campus_class_specialization cs JOIN campus_classdetail cd ON cs.ClassDetails_Id=cd.classdetail_id_int JOIN campus_location cl ON cl.Location_Id=cs.locationId WHERE cs.ClassDetails_Id = cd.classdetail_id_int AND cs.ClassDetails_Id = ? AND cs.locationId=? ");
			pstmt.setString(1, classId);
			pstmt.setString(2, locationId);
			//ln("getClassSpec -->>"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setSpecId(rs.getString("Specialization_Id"));
				vo.setSpecName(rs.getString("Specialization_name"));
				list.add(vo);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
				+ " Control in StudentAttendanceDaoImpl : getClassSpec Ending");
		return list;
	}

	@Override
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : editAttendance Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		StudentAttendanceVo  listvo = new StudentAttendanceVo();
		
		try{
			
			conn=JDBCConnection.getSeparateConnection(custdetails);
			if( pojo.getSpecID().equalsIgnoreCase("-")){
				
				//pstmt=conn.prepareStatement("select distinct ta.attendencedate,ta.locationId,clt.ClassCode,clt.SectionCode,ct.TeacherID,concat(ct.FirstName,' ',ct.LastName) as teachername from campus_attendence ta join campus_classteacher clt on clt.TeacherCode=ta.teacherId and clt.locationId=ta.locationId join campus_teachers ct on ct.TeacherID=clt.TeacherCode where clt.ClassCode=? and clt.SectionCode=? and clt.TeacherCode = ?");
				pstmt=conn.prepareStatement("SELECT DISTINCT csc.fms_acadamicyear_id_int,cd.classdetails_name_var,cd.classdetail_id_int,cs.classsection_name_var,cs.classsection_id_int,cd.locationId FROM campus_classdetail cd JOIN campus_classsection cs ON cd.`classdetail_id_int`=cs.`classdetail_id_int` JOIN campus_student_classdetails csc ON csc.classdetail_id_int=cd.classdetail_id_int AND csc.classsection_id_int=cs.classsection_id_int WHERE cd.classdetail_id_int=? AND cs.classsection_id_int LIKE ?");
				pstmt.setString(1, pojo.getClassId());
				if(pojo.getSectinId()=="" || pojo.getSectinId()==null){
					pstmt.setString(2, "%%");
				}else{
				    pstmt.setString(2, pojo.getSectinId());
				}
				System.out.println("editAttendance without11 Spec-->>"+pstmt);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					listvo.setClassname(rs.getString("classdetails_name_var"));
					listvo.setClassId(rs.getString("classdetail_id_int"));
					listvo.setSectionname(rs.getString("classsection_name_var"));
					listvo.setSectionId(rs.getString("classsection_id_int"));
					listvo.setSpecId("-");
					listvo.setSpecName("-");
					listvo.setLocationId(rs.getString("locationId"));
					listvo.setAccYear(rs.getString("fms_acadamicyear_id_int"));
				}
			}
			else{
				pstmt=conn.prepareStatement("SELECT DISTINCT csc.fms_acadamicyear_id_int,cd.classdetails_name_var,cd.classdetail_id_int,cd.locationId,cs.classsection_name_var,cs.classsection_id_int,ccs.Specialization_name,ccs.Specialization_Id FROM campus_class_specialization ccs JOIN campus_classdetail cd ON cd.classdetail_id_int=ccs.`ClassDetails_Id`JOIN campus_classsection cs ON cd.`classdetail_id_int`=cs.`classdetail_id_int` JOIN campus_student_classdetails csc ON csc.classdetail_id_int=cd.classdetail_id_int AND csc.classsection_id_int=cs.classsection_id_int WHERE cd.classdetail_id_int=? AND cs.classsection_id_int LIKE ? AND ccs.Specialization_Id=?");
				pstmt.setString(1, pojo.getClassId());
				if(pojo.getSectinId()=="" || pojo.getSectinId()==null){
					pstmt.setString(2, "%%");
				}else{
				    pstmt.setString(2, pojo.getSectinId());
				}
				pstmt.setString(3, pojo.getSpecID());
				System.out.println("editAttendance with22 Spec-->>"+pstmt);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					listvo.setClassname(rs.getString("classdetails_name_var"));
					listvo.setClassId(rs.getString("classdetail_id_int"));
					listvo.setSectionname(rs.getString("classsection_name_var"));
					listvo.setSectionId(rs.getString("classsection_id_int"));
					listvo.setSpecId(rs.getString("Specialization_Id"));
					listvo.setSpecName(rs.getString("Specialization_name"));
					listvo.setLocationId(rs.getString("locationId"));
					listvo.setAccYear(rs.getString("fms_acadamicyear_id_int"));
			   }
			
			}	
		}catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			try{
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
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
				+ " Control in StudentAttendanceDaoImpl : editAttendance Ending");
		return listvo;
	}
	
	public ArrayList<StudentAttendanceVo> getEditStudentAttendanceDetails(
			StudentAttendancePojo studentAttPojo) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getEditStudentAttendanceDetails : Starting");
		
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
				if(studentAttPojo.getSpecID() == "" || studentAttPojo.getSpecID() == null ){
					
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_ATTENDANCE_LIST);
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
				
				}
				else{
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SPEC_STUDENT_ATTENDANCE_LIST);	
					ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(studentAttPojo.getDate()));
					ps_insertPlan.setString(2,studentAttPojo.getClassId());
					ps_insertPlan.setString(3,studentAttPojo.getSectinId());
					ps_insertPlan.setString(4,studentAttPojo.getSpecID());
				}
			
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				
				count++;
				
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setStudentid(rs.getString("student_id_int"));
				attendanceVo.setAddmissionNo(rs.getString("student_admissionno_var"));
				attendanceVo.setStudentname(rs.getString("studentname"));
				attendanceVo.setClassname(rs.getString("classdetails_name_var"));
				attendanceVo.setSectionname(rs.getString("classsection_name_var"));
				attendanceVo.setStatus(rs.getString("status"));
				attendanceVo.setPeriod1(rs.getString("period1Status"));
				attendanceVo.setPeriod2(rs.getString("period2Status"));
				attendanceVo.setPeriod3(rs.getString("period3Status"));
				attendanceVo.setPeriod4(rs.getString("period4Status"));
				attendanceVo.setPeriod5(rs.getString("period5Status"));
				attendanceVo.setPeriod6(rs.getString("period6Status"));
				attendanceVo.setPeriod7(rs.getString("period7Status"));
				attendanceVo.setPeriod8(rs.getString("period8Status"));
				attendanceVo.setPeriod9(rs.getString("period9Status"));
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
				+ " Control in StudentAttendanceDaoImpl: getEditStudentAttendanceDetails: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public List<LstmsStudentPOJO> getStudentByTransport(String classId, String sectionId,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl : getStudentByTransport Starting");
		
		
		ArrayList<LstmsStudentPOJO> lstmsStudentPOJOList = new ArrayList<LstmsStudentPOJO>();
		LstmsStudentPOJO lstmsStudentPOJO = null;
		ResultSet resultSetClass = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(custid);
			 pstmt = (PreparedStatement) conn.prepareStatement(SQLUtilConstants.ALLSTUDENTNAME);
				pstmt.setString(1, classId);
				pstmt.setString(2, sectionId);
				
				resultSetClass = pstmt.executeQuery();
				
				while(resultSetClass.next()){
					lstmsStudentPOJO = new LstmsStudentPOJO();
					lstmsStudentPOJO.setStudentIdInt(resultSetClass.getString("student_id_int"));
					lstmsStudentPOJO.setStudentFnameVar(resultSetClass.getString("studentname"));
					lstmsStudentPOJO.setStudentAdmissionnoVar(resultSetClass.getString("student_admissionno_var"));
					lstmsStudentPOJOList.add(lstmsStudentPOJO);
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (resultSetClass != null&& (!resultSetClass.isClosed())) {
					resultSetClass.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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
				+ " Control in StudentAttendanceDaoImpl : getStudentByTransport Ending");
		
		return lstmsStudentPOJOList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: searchStudentsAttendanceList : Starting");

		PreparedStatement ps_insertPlan = null,ps_insertPlan1=null;
		ResultSet rs=null,rs1=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		String StartDate=null,EndDate=null;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			ps_insertPlan1 = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ACADAMICYEAR_DATE);
			ps_insertPlan1.setString(1,accYear);
			rs1=ps_insertPlan1.executeQuery();
			while(rs1.next()){
				StartDate=rs1.getString("startDate");
				EndDate=rs1.getString("endDate");
			}
			
			//String todayDate=HelperClass.getCurrentSqlDate().toString();
			//String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SEARCH_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,locationId);
			ps_insertPlan.setString(2, StartDate);
			ps_insertPlan.setString(3, EndDate);
			
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

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
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_insertPlan1 != null&& (!ps_insertPlan1.isClosed())) {
					ps_insertPlan1.close();
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
				+ " Control in StudentAttendanceDaoImpl: searchStudentsAttendanceList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear,String classname,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAttendenceByClassList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_ATTENDANCE_LIST);
			ps_insertPlan.setString(1,locationid);
			ps_insertPlan.setString(2,classname);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

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
				+ " Control in StudentAttendanceDaoImpl: getAttendenceByClassList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,
			String classname, String sectionid,String specialization,String startdate,String enddate,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAttendenceByClassSectionList : Starting");

		PreparedStatement ps_insertPlan = null,pstm=null,pstm1=null;
		ResultSet rs=null,rs1=null,rs2=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);

			String academicstartdate=getacademicyearstartdate(accyear,custid);

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);  

			/*pstm = conn.prepareStatement("SELECT DISTINCT specializationId FROM campus_attendence WHERE `locationId` = ?"); 
			pstm.setString(1, locationid);
			rs1=pstm.executeQuery();
			while(rs1.next()){
				String values=rs1.getString("specializationId");*/  

				if(startdate.trim().equalsIgnoreCase("")|| startdate==null){

					pstm1 = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_SECTION_ATTENDANCE_LIST_BY_SPECIALIZATION);
					pstm1.setString(1,specialization);
					pstm1.setString(2,locationid);
					pstm1.setString(3,accyear);
					pstm1.setString(4,classname);
					pstm1.setString(5,sectionid);

					//System.out.println("GET_CLASS_SECTION_ATTENDANCE_LIST_BY_SPECIALIZATION 1-->>"+pstm1);
					rs2=pstm1.executeQuery();
				}else{

					pstm1 = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_SECTION_ATTENDANCE_LIST_BY_DATE_BY_SPECIALIZATION);
					pstm1.setString(1,specialization);
					pstm1.setString(2,locationid);
					pstm1.setString(3,accyear);
					pstm1.setString(4,classname);
					pstm1.setString(5,sectionid);
					pstm1.setString(6,HelperClass.convertUIToDatabase(startdate));
					if(enddate=="" ||enddate==null){
						pstm1.setString(7,todayDate);
					}else{
						pstm1.setString(7,HelperClass.convertUIToDatabase(enddate));
					}

					//System.out.println("GET_CLASS_SECTION_ATTENDANCE_LIST_BY_DATE_BY_SPECIALIZATION 2-->>"+pstm1);
					rs2=pstm1.executeQuery();
				}

				while(rs2.next()){
					count++;

					StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

					attendanceVo.setCount(count);
					attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs2.getString("attendencedate")));
					attendanceVo.setTot_count(rs2.getString("total_streangth"));
					attendanceVo.setAbsent_count(rs2.getString("TotalAbsent"));
					attendanceVo.setPresent_count(rs2.getString("TotalPresent"));
					attendanceVo.setLeave_count(rs2.getString("Totalleave"));
					attendanceVo.setClasssection(rs2.getString("classsection"));
					attendanceVo.setClassId(rs2.getString("classdetail_id_int"));
					attendanceVo.setSectionId(rs2.getString("classsection_id_int"));
					attendanceVo.setSpecName(rs2.getString("Specialization_name"));
					attendanceVo.setSpecId(rs2.getString("specilization"));
					attendanceVo.setAccYear(rs2.getString("accId"));
					attendanceVo.setLocationId(rs2.getString("locationId"));
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
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstm != null&& (!pstm.isClosed())) {
					pstm.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstm1 != null&& (!pstm1.isClosed())) {
					pstm1.close();
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
				+ " Control in StudentAttendanceDaoImpl: getAttendenceByClassSectionList: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getTeacherList(String locationid,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getTeacherList : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffList=new ArrayList<StudentAttendanceVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custid);

			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_TEACHER_LIST);
			ps_insertPlan.setString(1,locationid);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){
				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
				attendanceVo.setTeacherID(rs.getString("TeacherID"));
				attendanceVo.setTeacherName(rs.getString("teachername"));
				staffList.add(attendanceVo);
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
				+ " Control in StudentAttendanceDaoImpl: getTeacherList: Ending");

		return staffList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear,String classname,
			String sectionid, String teacherid,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByTeacher : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST_BY_TEACHER);
			ps_insertPlan.setString(1,locationid);
			ps_insertPlan.setString(2,classname);
			ps_insertPlan.setString(3,sectionid);
			ps_insertPlan.setString(4, teacherid);
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

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
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByTeacher: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByDate : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			//ln("startdate -->>"+HelperClass.convertUIToDatabase(startdate));
			//ln("enddate -->>"+HelperClass.convertUIToDatabase(enddate));
			
			ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_LIST_BY_DATE);
			ps_insertPlan.setString(1,HelperClass.convertUIToDatabase(startdate));
			ps_insertPlan.setString(2,HelperClass.convertUIToDatabase(enddate));
			rs=ps_insertPlan.executeQuery();

			while(rs.next()){

				count++;

				StudentAttendanceVo attendanceVo=new StudentAttendanceVo();

				attendanceVo.setCount(count);
				attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs.getString("AttendenceDate")));
				attendanceVo.setTot_count(rs.getString("total_streangth"));
				attendanceVo.setAbsent_count(rs.getString("TotalAbsent"));
				attendanceVo.setPresent_count(rs.getString("TotalPresent"));
				attendanceVo.setHoliday_count(rs.getString("TotalHoliday"));
				attendanceVo.setLeave_count(rs.getString("Totalleave"));
				attendanceVo.setTeacherName(rs.getString("teachers"));
				attendanceVo.setClasssection(rs.getString("classsection"));
				attendanceVo.setClassId(rs.getString("classdetail_id_int"));
				attendanceVo.setSectionId(rs.getString("classsection_id_int"));
				attendanceVo.setSpecName(rs.getString("Specialization_name"));
				attendanceVo.setSpecId(rs.getString("specilization"));
				attendanceVo.setTeacherID(rs.getString("TeacherCode"));

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
				+ " Control in StudentAttendanceDaoImpl: getAttendanceListByDate: Ending");

		return staffAttendanceList;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceListByDownload(String startdate, String endDate,
			String acyearid, String locId, String classId, String section, UserLoggingsPojo custId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceListByDownload : Starting");

		PreparedStatement ps_insertPlan = null,pstm=null,pstm1=null;
		ResultSet rs=null,rs1=null,rs2=null;
		Connection conn = null;
		String academicyearstartdate="";
		 
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		 
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection(custId);

			academicyearstartdate=getacademicyearstartdate(acyearid,custId);

			String todayDate=HelperClass.getCurrentSqlDate().toString();
			String lastdate=HelperClass.getLastThirtyDateFromNow(todayDate);

			pstm = conn.prepareStatement("SELECT DISTINCT specializationId FROM campus_attendence");
			rs1=pstm.executeQuery();
			while(rs1.next()){
				String values=rs1.getString("specializationId"); 

				if(!values.equalsIgnoreCase("-") || values!="-"){
					pstm1 = conn.prepareStatement(SQLUtilConstants.DOWNLOAD_ATTENDENCE_LIST);

					if(startdate==null || "".equalsIgnoreCase(startdate)){
						pstm1.setString(1,academicyearstartdate);
						pstm1.setString(2,todayDate);
					}else{
						pstm1.setString(1,HelperClass.convertUIToDatabase(startdate));
						pstm1.setString(2,HelperClass.convertUIToDatabase(endDate));
					}
					pstm1.setString(3,values);
					pstm1.setString(4,locId);
					pstm1.setString(5,acyearid);
					pstm1.setString(6,classId);
					pstm1.setString(7,section);


					//ln("getStudentsAttendanceListByDownload GET_ATTENDANCE_LIST 1-->:>"+pstm1);
					rs2=pstm1.executeQuery();

					while(rs2.next()){
						count++;
						StudentAttendanceVo attendanceVo=new StudentAttendanceVo();
						attendanceVo.setCount(count);
						attendanceVo.setDate(HelperClass.convertDatabaseToUI(rs2.getString("attendencedate")));
						attendanceVo.setTot_count(rs2.getString("total_streangth"));
						attendanceVo.setAbsent_count(rs2.getString("TotalAbsent"));
						attendanceVo.setPresent_count(rs2.getString("TotalPresent"));
						attendanceVo.setLeave_count(rs2.getString("Totalleave"));
						attendanceVo.setClasssection(rs2.getString("classsection"));
						attendanceVo.setClassId(rs2.getString("classdetail_id_int"));
						attendanceVo.setSectionId(rs2.getString("classsection_id_int"));
						attendanceVo.setSpecName(rs2.getString("Specialization_name"));
						attendanceVo.setSpecId(rs2.getString("specilization"));
						attendanceVo.setLocationId(rs2.getString("locationId"));
						attendanceVo.setAccYear(rs2.getString("accId"));

						staffAttendanceList.add(attendanceVo);
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
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstm1 != null&& (!pstm1.isClosed())) {
					pstm1.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstm != null&& (!pstm.isClosed())) {
					pstm.close();
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
				+ " Control in StudentAttendanceDaoImpl: getStudentsAttendanceListByDownload: Ending");

		return staffAttendanceList;
	}

	private String getacademicyearstartdate(String acyearid,UserLoggingsPojo custId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getacademicyearstartdate : Starting");

		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		String value="";
		try {
			conn = JDBCConnection.getSeparateConnection(custId);

			ps_insertPlan = conn.prepareStatement("SELECT startDate FROM campus_acadamicyear WHERE acadamic_id=?");
			ps_insertPlan.setString(1,acyearid);
			rs=ps_insertPlan.executeQuery();
			while(rs.next()){
				value=rs.getString(1);
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
				+ " Control in StudentAttendanceDaoImpl: getacademicyearstartdate: Ending");

		return value;
	}

	@Override
	public String getAccyID(String academic_year, UserLoggingsPojo custId, String location_id ) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getAccyID : Starting");

		PreparedStatement pst1 = null;
		
		ResultSet rs=null;
		
		Connection conn = null;
		String value="";
		
		try {
			conn = JDBCConnection.getSeparateConnection(custId);

			pst1 = conn.prepareStatement("");
			//pst1.setString(1,location_id);
			//pst1.setString(2,academic_year);
			rs=pst1.executeQuery();
			////ln(pst1);

			while(rs.next()){
				value=rs.getString(1);
				//ln(value);
			}
		} 
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				
				if (pst1 != null&& (!pst1.isClosed())) {
					pst1.close();
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
				+ " Control in StudentAttendanceDaoImpl: getAccyID: Ending");

		return value;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentDetailsAcademicWise(UserLoggingsPojo userLoggingsVo,String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in getStudentDetailsAcademicWise : Starting");

		PreparedStatement pst = null;
		
		ResultSet rs=null;
		
		Connection conn = null;
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_COUNT_ACADEMIC_YEAR);
			//ln(pst);
			pst.setString(1,location);
			rs=pst.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setAccYear(rs.getString("acadamic_year"));
				vo.setLocation(rs.getString("Location_Name"));
				vo.setTot_count(rs.getString("Total"));
				
				list.add(vo);
				
			}
		} 
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				
				if (pst != null&& (!pst.isClosed())) {
					pst.close();
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
				+ " Control in getStudentDetailsAcademicWise : Ending");

		return list;
	}

	@Override
	public ArrayList<StudentAttendanceVo> todayStudentAttendance(UserLoggingsPojo userLoggingsVo,String location, String accYear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in todayStudentAttendance : Starting");

		PreparedStatement pst = null;
		
		ResultSet rs=null;
		
		Connection conn = null;
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentAttendanceSqlUtil.GET_TODAY_STUDENT_ATTENDANCE);
			//ln(pst);
			pst.setString(1,accYear );
			pst.setString(2,location);
			pst.setString(3,location);
			pst.setString(4,accYear );
			pst.setDate(5,HelperClass.getCurrentSqlDate());
			//ln("helooooooo:"+pst);
			rs=pst.executeQuery();
			while(rs.next()){
				
				StudentAttendanceVo vo = new StudentAttendanceVo();
				//ln(rs.getString("Total")+rs.getString("Absent")+rs.getString("Present")+rs.getString("leave"));
				vo.setTot_count(rs.getString("Total"));
				vo.setAbsent_count(rs.getString("Absent"));
				vo.setPresent_count(rs.getString("Present"));
				vo.setLeave_count(rs.getString("leave"));
				
				list.add(vo);
			}
		} 
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				
				if (pst != null&& (!pst.isClosed())) {
					pst.close();
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
				+ " Control in todayStudentAttendance : Ending");
		return list;
	}


	@Override
	public int getperiodcount(String locId, String clsId, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl:validateclassName Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		String result=null;
		int count=0;
		ResultSet rs=null;
	
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(SQLUtilConstants.GET_PERIOD_COUNT);
			pst.setString(1, locId);
			pst.setString(2, clsId);
			//ln("88888888"+pst);
			rs = pst.executeQuery();
			while(rs.next()) {
				count=rs.getInt("no_of_period");
				
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(pst !=null && con!=null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl : validateclassName : Ending");
		return count;
	}

	@Override

	public String NewupdateAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: NewupdateAttendanceStatus : Starting");
		
		PreparedStatement ps_count = null;
		PreparedStatement ps_insert = null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		int statuscount=0;
		int count1=0;
		String result=null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
		    conn.setAutoCommit(false);
			ps_count = conn.prepareStatement(StudentAttendanceSqlUtil.GET_ATTENDANCE_COUNT);
			ps_count.setString(1, HelperClass.convertUIToDatabase(attendancepojo.getDate()));
			ps_count.setString(2, attendancepojo.getLocationId());
			ps_count.setString(3, attendancepojo.getClassId());
			ps_count.setString(4, attendancepojo.getDivid());
			rs=ps_count.executeQuery();
			
			while(rs.next()){
				count=rs.getInt(1);
			}
			if(count==0){
			String StuId[]=attendancepojo.getStudentid().split(",");
			String attaStatus[] =attendancepojo.getStatus().split(",");
			
			        ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_ATTENDANCE,Statement.RETURN_GENERATED_KEYS);
					ps_insert.setString(1, HelperClass.convertUIToDatabase(attendancepojo.getDate()));
					ps_insert.setString(2, attendancepojo.getUserId());
					ps_insert.setTimestamp(3, HelperClass.getCurrentTimestamp());
					ps_insert.setString(4,attendancepojo.getTeacherId());
					ps_insert.setString(5,attendancepojo.getLocationId());
					ps_insert.setString(6,attendancepojo.getAccYearId());
					if(attendancepojo.getSpecID()=="" || attendancepojo.getSpecID()==null){
						ps_insert.setString(7,"-");
					}else{
						ps_insert.setString(7,attendancepojo.getSpecID());
					}
					ps_insert.setString(8, attendancepojo.getClassId());
					ps_insert.setString(9, attendancepojo.getDivid());
					//ln("INSERT_ATTENDANCE -->>"+ps_insert);
					
					statuscount=ps_insert.executeUpdate();
					if(statuscount > 0){
						HelperClass.recordLog_Activity(attendancepojo.getLog_audit_session(),"Student","Attendance","Insert",ps_insert.toString(),custdetails);
						int genkey = 0;
						ResultSet key = ps_insert.getGeneratedKeys();
						while(key.next()){
							genkey = key.getInt(1);
						}
						for(int i=0;i<StuId.length;i++){
						
						pst=conn.prepareStatement(SQLUtilConstants.INSERT_STUDENT_ATTENDENCE_STATUS,Statement.RETURN_GENERATED_KEYS);
						pst.setInt(1, genkey);
						pst.setString(2, StuId[i]);
						pst.setString(3, attaStatus[i]);
						//ln("INSERT_ATTENDANCE 1-->>"+pst);
						count=pst.executeUpdate();
						if(count>0){
							HelperClass.recordLog_Activity(attendancepojo.getLog_audit_session(),"Student","Attendance","Insert",pst.toString(),custdetails);
							int genkey1 = 0;
							ResultSet key1 = pst.getGeneratedKeys();
							while(key1.next()){
								genkey1 = key1.getInt(1);
							}
							for(int j=0;j<attendancepojo.getNoofperiod();j++){
							
							pst1=conn.prepareStatement(SQLUtilConstants.INSERT_PERIOD_ATTENDANCE);
							pst1.setInt(1,genkey1);
							pst1.setString(2, periodId[i].substring(1).split(",")[j].split("-")[0]);
							pst1.setString(3, periodId[i].substring(1).split(",")[j].split("-")[1]);
							//ln("INSERT_ATTENDANCE3 -->>"+pst1);
							count1=pst1.executeUpdate();
							if(count1>0){
								HelperClass.recordLog_Activity(attendancepojo.getLog_audit_session(),"Student","Attendance","Insert",pst1.toString(),custdetails);
							}
						
						}
					}	
					}
				}
			
			
			if(statuscount>0){
				conn.commit();
				result="true";
			
			}else{
				conn.rollback();
				result="false";
			}
			
			}
			
			else{
				result="duplicate";
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
				+ " Control in StudentAttendanceDaoImpl: NewupdateAttendanceStatus: Ending");
		
		return result;
	}



	public ArrayList<StudentAttendanceVo> houseWiseStudent(UserLoggingsPojo userLoggingsVo,String location, String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in houseWiseStudent : Starting");

		PreparedStatement pst = null;
		
		ResultSet rs=null;
		
		Connection conn = null;
		ArrayList<StudentAttendanceVo> list = new ArrayList<StudentAttendanceVo>();
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst = conn.prepareStatement(StudentAttendanceSqlUtil.GET_CLASS_WISE_STUDENT);
			pst.setString(1, accYear);
			pst.setString(2, location);
			
			//ln(pst);
			rs=pst.executeQuery();
			while(rs.next()){
				StudentAttendanceVo vo = new StudentAttendanceVo();
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setTot_count(rs.getString("Total"));
				list.add(vo);
			}
		} 
		catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				
				if (pst != null&& (!pst.isClosed())) {
					pst.close();
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
				+ " Control in houseWiseStudent : Ending");
		return list;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendance(StudentAttendancePojo studentPojo,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails : Starting");
		String value=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
				if(studentPojo.getSpecID() == "" || studentPojo.getSpecID() == null ){
					
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_STUDENT_ATTENDANCE_LIST_ADD);
					ps_insertPlan.setString(1,studentPojo.getClassId());
					ps_insertPlan.setString(2,studentPojo.getSectinId());
					ps_insertPlan.setString(3,studentPojo.getAccYearId());
					ps_insertPlan.setString(4, studentPojo.getLocationId());
					
					value="without Spec";
				}
				else{
					ps_insertPlan = conn.prepareStatement(StudentAttendanceSqlUtil.GET_SPEC_STUDENT_ATTENDANCE_LIST_ADD);	
					ps_insertPlan.setString(1,studentPojo.getClassId());
					ps_insertPlan.setString(2,studentPojo.getSectinId());
					ps_insertPlan.setString(3,studentPojo.getSpecID());
					ps_insertPlan.setString(4,studentPojo.getAccYearId());
					ps_insertPlan.setString(5, studentPojo.getLocationId());
					
					value="with Spec";
				}
				 
				
				 
			rs=ps_insertPlan.executeQuery();
			
			while(rs.next()){
				
				count++;
				
				StudentAttendanceVo attendanceVo = new StudentAttendanceVo();
				
				attendanceVo.setCount(count);
				attendanceVo.setStudentid(rs.getString("student_id_int"));
				attendanceVo.setAddmissionNo(rs.getString("student_admissionno_var"));
				attendanceVo.setStudentname(rs.getString("studentname"));
				
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
				+ " Control in StudentAttendanceDaoImpl: getStudentAttendanceDetails: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public String updateNewAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails, String[] statusid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceDaoImpl: updateNewAttendanceStatus : Starting");
		
		
		PreparedStatement ps_insert = null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		int count1=0;
		String result=null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
		    for(int i=0;i<statusid.length;i++){
		    	pst=conn.prepareStatement(SQLUtilConstants.DELETE_PERIOD_TABLE);
		    	pst.setString(1, statusid[i]);
			    count=pst.executeUpdate();
		      }
			    if(count>0){
			    	pst=conn.prepareStatement(SQLUtilConstants.DELETE_STUDENT_STATUS_TABLE);
			    	pst.setInt(1, attendancepojo.getAtenid());
			    	count=pst.executeUpdate();
			    }
			    if(count>0){
			    	String StuId[]=attendancepojo.getStudentid().split(",");
				      String attaStatus[] =attendancepojo.getStatus().split(",");

                       for(int i=0;i<StuId.length;i++){
							pst=conn.prepareStatement(SQLUtilConstants.INSERT_STUDENT_ATTENDENCE_STATUS,Statement.RETURN_GENERATED_KEYS);
							pst.setInt(1, attendancepojo.getAtenid());
							pst.setString(2, StuId[i]);
							pst.setString(3, attaStatus[i]);
							//ln("INSERT_ATTENDANCE 1-->>"+pst);
							count=pst.executeUpdate();
							if(count>0){
								HelperClass.recordLog_Activity(attendancepojo.getLog_audit_session(),"Student","Attendance","Insert",pst.toString(),custdetails);
								int genkey1 = 0;
								ResultSet key1 = pst.getGeneratedKeys();
								while(key1.next()){
									genkey1 = key1.getInt(1);
								}
								for(int j=0;j<attendancepojo.getNoofperiod();j++){
								
								pst1=conn.prepareStatement(SQLUtilConstants.INSERT_PERIOD_ATTENDANCE);
								pst1.setInt(1,genkey1);
								pst1.setString(2, periodId[i].substring(1).split(",")[j].split("-")[0]);
								pst1.setString(3, periodId[i].substring(1).split(",")[j].split("-")[1]);
								//ln("INSERT_ATTENDANCE3 -->>"+pst1);
								count1=pst1.executeUpdate();
								if(count>0){
									HelperClass.recordLog_Activity(attendancepojo.getLog_audit_session(),"Student","Attendance","Insert",pst1.toString(),custdetails);
								}
							
							}
						}	
						}
			    }
			    
			   
			    if(count>0){
			    	result="true";
			    }
			    else{
			    	result="false";
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
				+ " Control in StudentAttendanceDaoImpl: updateNewAttendanceStatus: Ending");
		
		return result;
	}


}
