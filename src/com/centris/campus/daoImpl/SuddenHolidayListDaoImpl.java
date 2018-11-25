package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.SMSDetails;
import com.centris.campus.admin.SMSThread;
import com.centris.campus.dao.SuddenHolidayListDao;
import com.centris.campus.pojo.SuddenHolidaysPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SuddenHolidaySqlUtil;
import com.centris.campus.vo.SuddenHolidaySMSVO;

public class SuddenHolidayListDaoImpl implements SuddenHolidayListDao {

	private static Logger logger = Logger.getLogger(SuddenHolidayListDaoImpl.class);
	
	public ArrayList<SuddenHolidaySMSVO> SuddenHolidayList(UserLoggingsPojo pojo1, String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in SuddenHolidayListDaoImpl: SuddenHolidayList : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SuddenHolidaySMSVO> list = new ArrayList<SuddenHolidaySMSVO>();
		Connection conn = null;
		int count=0;

		try {
			
			conn = JDBCConnection.getSeparateConnection(pojo1);
			pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.GET_SUDDEN_HOLIDAY_DETAILS);
			pstmt.setString(1, schoolLocation);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				count++;
				SuddenHolidaySMSVO vo = new SuddenHolidaySMSVO();
				vo.setSlNo(count);
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("smsDate")));
				vo.setLocaname(rs.getString("Location_Name"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSecname(rs.getString("classsection_name_var"));
				vo.setSmstext(rs.getString("smsContent"));
				vo.setSuddenholidayscode(rs.getString("smsCode"));
				vo.setSmsstatus(rs.getString("sms_status"));
				vo.setCredate(HelperClass.convertDatabaseToUI(rs.getString("created_time")));
				list.add(vo);
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
				+ " Control in SuddenHolidayListDaoImpl: SuddenHolidayList : Ending");

		return list;
	}

	public String AddSuddenHoliday(SuddenHolidaysPojo upojo,UserLoggingsPojo pojo1){
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : AddSuddenHoliday Starting");

		PreparedStatement pstmt=null;
		int count=0;
		String status=null;
		Connection conn = null;

		try {
			
			conn = JDBCConnection.getSeparateConnection(pojo1);
			
				pstmt= conn.prepareStatement(SuddenHolidaySqlUtil.STORE_SUDDENHOLIDAYS_SMS_DETAILS);

				pstmt.setString(1, upojo.getSuddenholidayscode());
				pstmt.setString(2, upojo.getHdate());
				pstmt.setString(3, upojo.getSmstext().trim());
				pstmt.setTimestamp(4, upojo.getCreatedate());
				pstmt.setString(5, upojo.getCreatedby());
				pstmt.setString(6, upojo.getLocId());

				count = pstmt.executeUpdate();

				if(count>0)
				{
					Runnable r = new SMSThread(upojo.getSuddenholidayscode(),upojo.getHdate(),"Holiday",upojo.getLog_audit_session(),"Interaction","Sudden Holiday",pojo1,upojo.getSmsCharacters(), upojo.getLocId());
					new Thread(r).start();
					
					HelperClass.recordLog_Activity(upojo.getLog_audit_session(),"Interaction","Sudden Holiday","Insert",upojo.toString(),pojo1);
					
					status="true";
				}
				else
				{
					status="false";
				}
				
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : AddSuddenHoliday  Ending");

		return status;
	}

	public String storeSuudenHolidaysSections(SuddenHolidaysPojo suddenholidayspojo,UserLoggingsPojo pojo1){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : storeSuudenHolidaysSections : Starting");

		PreparedStatement ps_sectiondetails=null, pstmt1 = null;
		ResultSet rs = null;
		int count=0;
		Connection conn = null;
		String status=null;
		int selctedCount = 0;

		try {

			conn = JDBCConnection.getSeparateConnection(pojo1);

			for(int i=0;i<suddenholidayspojo.getSectionid().length;i++){

				pstmt1 = conn.prepareStatement(SuddenHolidaySqlUtil.STUDENT_COUNT);
				pstmt1.setString(1, suddenholidayspojo.getAcc_Year());
				pstmt1.setString(2, suddenholidayspojo.getClassid()[i]);
				pstmt1.setString(3, suddenholidayspojo.getSectionid()[i]);
				pstmt1.setString(4, suddenholidayspojo.getLocId());
				
				rs = pstmt1.executeQuery();

				while(rs.next()){
					selctedCount = rs.getInt("total");
				}
			}
			
			if(selctedCount > suddenholidayspojo.getBalanceSMS()){
				
				status = "insufficientSMSBalance";

			}else{
				
				ps_sectiondetails=conn.prepareStatement(SuddenHolidaySqlUtil.STORE_SUDDENHOLIDAYS_SECTION_DETAILS);

				for(int i=0;i<suddenholidayspojo.getSectionid().length;i++){

					ps_sectiondetails.setString(1, suddenholidayspojo.getSuddenholidayscode());
					ps_sectiondetails.setString(2, suddenholidayspojo.getSectionid()[i]);
					ps_sectiondetails.setTimestamp(3, suddenholidayspojo.getCreatedate());
					ps_sectiondetails.setString(4, suddenholidayspojo.getCreatedby());
					ps_sectiondetails.setString(5, suddenholidayspojo.getClassid()[i]);
					ps_sectiondetails.setString(6, suddenholidayspojo.getAcc_Year());
					count=ps_sectiondetails.executeUpdate();

					if(count>0)
					{
						HelperClass.recordLog_Activity(suddenholidayspojo.getLog_audit_session(),"Interaction","Sudden Holiday","Insert",ps_sectiondetails.toString(),pojo1);
						status = "true";
					}
					else
					{
						status = "false";
					}
				}

			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {

				if (ps_sectiondetails != null && (!ps_sectiondetails.isClosed())) {
					ps_sectiondetails.close();
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
				+ " Control in SuddenHolidayListDaoImpl : storeSuudenHolidaysSections  Ending");

		return status;
	}

	public boolean validateSuddenHolidaysSms(String date, String smstext, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SuddenHolidayListDaoImpl : validateSuddenHolidaysSms Starting");

		PreparedStatement ps_checkvalidation=null;
		int count=0;
		ResultSet rs_checkvalidation=null;
		boolean status=false;
		Connection conn = null;


		try {
			
			conn =  JDBCConnection.getSeparateConnection(pojo);

			ps_checkvalidation=conn.prepareStatement(SuddenHolidaySqlUtil.VALIDATE_SUDDENHOLIDAYS_SMS);

			ps_checkvalidation.setString(1,date);
			ps_checkvalidation.setString(2, smstext.trim());

			rs_checkvalidation = ps_checkvalidation.executeQuery();

			while(rs_checkvalidation.next()){

				count=rs_checkvalidation.getInt(1);

			}

			if(count>0){

				status=true;
			}else{

				status=false;
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rs_checkvalidation != null&& (!rs_checkvalidation.isClosed())) {
					rs_checkvalidation.close();
				}
				if (ps_checkvalidation != null&& (!ps_checkvalidation.isClosed())) {
					ps_checkvalidation.close();
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
				+ " Control in SuddenHolidayListDaoImpl : validateSuddenHolidaysSms ending");

		return status;
	}

	@Override
	public ArrayList<SuddenHolidaySMSVO> OtherSMSList(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in SuddenHolidayListDaoImpl: OtherSMSList : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SuddenHolidaySMSVO> list = new ArrayList<SuddenHolidaySMSVO>();
		Connection conn = null;
		int count=0;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.GET_OTHER_SMS_DETAILS);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				count++;
				SuddenHolidaySMSVO vo = new SuddenHolidaySMSVO();
				vo.setSlNo(count);
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				vo.setSmstext(rs.getString("Description"));
				vo.setClassname(rs.getString("classname"));
				vo.setStudent(rs.getString("name"));

				list.add(vo);
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
				+ " Control in SuddenHolidayListDaoImpl: OtherSMSList : Ending");

		return list;
	}

}
