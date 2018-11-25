package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.admin.SMSThread;
import com.centris.campus.dao.AbsentSMSDao;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.util.SuddenHolidaySqlUtil;


public class AbsentSMSDaoImpl implements AbsentSMSDao{

	private static final Logger logger = Logger.getLogger(AbsentSMSDaoImpl.class);

	public ArrayList<AbsentsSMSPojo> absentlist(AbsentsSMSPojo pojo, UserLoggingsPojo custdetails, String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : absentlist Starting");

		PreparedStatement ps_studentdetails = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<AbsentsSMSPojo> list = new ArrayList<AbsentsSMSPojo>();
		AbsentsSMSPojo absentsSMSPojo = null;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.GET_ABSENT_STUDENT_DETAILS);
			ps_studentdetails.setString(1, schoolLocation);
			rs = ps_studentdetails.executeQuery();

			while (rs.next()) {
				count++;
				absentsSMSPojo = new AbsentsSMSPojo();
				absentsSMSPojo.setSlNo(count);
				absentsSMSPojo.setAbsentcode(rs.getString("ABSENT_CODE"));
				absentsSMSPojo.setLocId(rs.getString("Location_Name"));
				absentsSMSPojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("ABSENT_DATE")));
				absentsSMSPojo.setStudentName(rs.getString("NAME"));
				absentsSMSPojo.setSection(rs.getString("classsection_name_var"));
				absentsSMSPojo.setClassname(rs.getString("classdetails_name_var"));
				absentsSMSPojo.setSmstext(rs.getString("ABSENT_CONTENT"));
				absentsSMSPojo.setStatus(rs.getString("sms_status"));
				absentsSMSPojo.setSenddate(HelperClass.convertDatabaseToUI(rs.getString("CREATE_TIME")));
				list.add(absentsSMSPojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(ps_studentdetails!=null && (!ps_studentdetails.isClosed())){
					ps_studentdetails.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : absentlist ending");
		return list;
	}

	public int storeAbsentDetails(AbsentsSMSPojo absentpojo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : storeAbsentDetails Starting");

		Connection conn = null;
		PreparedStatement pstmt1=null;
		ResultSet rs1 = null;
		int count = 0;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);

			if(absentpojo.getStudentid().length > absentpojo.getBalanceSMS()){
				
				count = 10;
			}else{
				pstmt1 = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_SMS_DETAILS);
				pstmt1.setString(1, absentpojo.getAbsentcode());
				pstmt1.setString(2, absentpojo.getDate());
				pstmt1.setString(3, absentpojo.getSmstext().trim());
				pstmt1.setString(4, absentpojo.getLocId());
				pstmt1.setString(5, absentpojo.getCreatedby());
				pstmt1.setString(6, absentpojo.getAccYearId());
				pstmt1.setInt(7, absentpojo.getIsstudent());

				count = pstmt1.executeUpdate();
				if(count > 0){
					HelperClass.recordLog_Activity(absentpojo.getLog_audit_session(),"Interaction","Absent","Insert",pstmt1.toString(),custdetails);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : storeAbsentDetails  Ending");

		return count;

	}

	public int storeAbsentStudent(AbsentsSMSPojo absentpojo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : storeAbsentStudent Starting");

		PreparedStatement ps_studentdetails = null;
		int count = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_STUDENT_DETAILS);

			for (int i = 0; i < absentpojo.getStudentid().length; i++) {

				ps_studentdetails.setString(1, absentpojo.getAbsentcode());
				ps_studentdetails.setString(2, absentpojo.getStudentid()[i]);
				ps_studentdetails.setString(3, absentpojo.getCreatedby());
				ps_studentdetails.setString(4, absentpojo.getLocId());
				count = ps_studentdetails.executeUpdate(); 
			}
			if (count > 0 || count >= 1) {
				HelperClass.recordLog_Activity(absentpojo.getLog_audit_session(),"Interaction","Absent","Insert",ps_studentdetails.toString(),custdetails);

				Runnable r = new SMSThread(absentpojo.getAbsentcode(),absentpojo.getDate(),"Absent",absentpojo.getLog_audit_session(),"Interaction","Absent",custdetails,absentpojo.getLocId());
				new Thread(r).start();

				count=1;
			}
			else
			{
				count=0;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
			try {

				if (ps_studentdetails != null && (!ps_studentdetails.isClosed())) {
					ps_studentdetails.close();
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
				+ " Control in AbsentSMSDaoImpl : storeAbsentStudent ending");
		return count;
	}

	public int storeAbsentSections(AbsentsSMSPojo absentpojo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in AbsentSMSDaoImpl: storeAbsentSections : Starting");

		PreparedStatement pstmt=null,pstmt1= null;
		ResultSet rs = null;
		Connection conn = null;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_SECTION_BY_STUID);

			for (int i = 0; i < absentpojo.getStudentid().length; i++) {
				pstmt.setString(1, absentpojo.getStudentid()[i]);
				pstmt.setString(2, absentpojo.getAccYearId());
				pstmt.setString(3, absentpojo.getLocId());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					String sectionval = rs.getString("classsection_id_int");
					pstmt1 = conn.prepareStatement(SuddenHolidaySqlUtil.STORE_ABSENT_SECTION_DETAILS);

					pstmt1.setString(1,absentpojo.getAbsentcode());
					pstmt1.setString(2,sectionval);
					pstmt1.setString(3,absentpojo.getCreatedby());
					pstmt1.setString(4,absentpojo.getClassname());
					count = pstmt1.executeUpdate();
					if(count > 0){
						HelperClass.recordLog_Activity(absentpojo.getLog_audit_session(),"Interaction","Absent","Insert",pstmt1.toString(),custdetails);
					}
				}
			}

			if(count > 0 || count >= 1){

				count=1;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
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
				+ " Control in AbsentSMSDaoImpl:storeAbsentSections : Ending");

		return count;
	}

	public boolean validateAbsentSms(String date, String smstext, AbsentsSMSPojo abpojo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : validateAbsentSms Starting");

		PreparedStatement ps_studentdetails = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean status = true;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);

			ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.GET_COUNT_ABSENT);
			ps_studentdetails.setString(1, smstext);
			ps_studentdetails.setString(2, date);

			rs = ps_studentdetails.executeQuery();



			while (rs.next()) {

				int count = rs.getInt(1);

				if(count>0)
				{
					status = true;
				}
				else
				{
					status=false;
				}
			}


		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(ps_studentdetails!=null && (!ps_studentdetails.isClosed())){
					ps_studentdetails.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSDaoImpl : validateAbsentSms ending");

		return status;
	}

}
