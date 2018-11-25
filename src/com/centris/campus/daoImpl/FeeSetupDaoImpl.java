package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.FeeSetupDao;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.FeeConcessionVO;


public class FeeSetupDaoImpl implements FeeSetupDao

{
	private static final Logger logger = Logger
			.getLogger(FeeSetupDaoImpl.class);

	public synchronized List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getconcessiondetails Starting");
		 
		PreparedStatement pstmt = null;
		List<ConcessionDetailsPojo> concessiondetailsList = new ArrayList<ConcessionDetailsPojo>();

		ResultSet rs = null;
		Connection conn = null;

			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_CONCESSION_DETAILS);
				
				pstmt.setString(1, vo.getLocId());
				pstmt.setString(2, vo.getStatus());
				pstmt.setString(3, vo.getSearchName() + "%");
				pstmt.setString(4, vo.getSearchName() + "%");
				pstmt.setString(5, vo.getSearchName() + "%");
				pstmt.setString(6, vo.getSearchName() + "%");
				System.out.println("GET_CONCESSION_DETAILS -->>"+pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ConcessionDetailsPojo pojo = new ConcessionDetailsPojo();
					pojo.setLocId(rs.getString("Location_Name"));
					pojo.setRemarks(rs.getString("remarks"));
					pojo.setConcessionId(rs.getString("concessionid"));
					pojo.setConcessionName(rs.getString("concessionname"));
					pojo.setDescription(rs.getString("description"));
					concessiondetailsList.add(pojo);
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: getconcessiondetails : Ending");
		return concessiondetailsList;

	}

	public synchronized String insertConcesssionDetails(ConcessionDetailsPojo detailsPojo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : insertConcesssionDetails  Starting");
		PreparedStatement concession_pstmt = null;
		String status = null;
		Connection conn = null;
		int result1 = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			concession_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_CONCESSION_DETAILS);
			concession_pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_fee_concessiondetails",custdetails));
			concession_pstmt.setString(2, detailsPojo.getConcessionName().trim());
			concession_pstmt.setString(3, detailsPojo.getLocId());
			concession_pstmt.setString(4, detailsPojo.getDescription().trim());
			concession_pstmt.setString(5, detailsPojo.getCreateUser());
			concession_pstmt.setString(6, detailsPojo.getConcessionType());
			concession_pstmt.setString(7, detailsPojo.getIsApplicable());
			concession_pstmt.setString(7, detailsPojo.getIsApplicable());
			concession_pstmt.setString(8, detailsPojo.getConcession());
         //   System.out.println("INSERT_CONCESSION_DETAILS -->>"+concession_pstmt);
			result1 = concession_pstmt.executeUpdate();
			if (result1 > 0) {
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Master","Concession Details","Insert",concession_pstmt.toString(),custdetails);
				status = "success";
			} else {
				status = "fail";
			}
		}

		catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(), sqle);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			try {
				if (concession_pstmt != null && (!concession_pstmt.isClosed())) {
					concession_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl :insertConcesssionDetails Ending");

		return status;
	}

	/*
	 * PreparedStatement pstmt = null; java.util.Date today = new
	 * java.util.Date(); java.sql.Timestamp time_stamp = new
	 * java.sql.Timestamp(today.getTime());
	 * 
	 * String USER_NAME = detailsPojo.getCreateUser(); FeeSetupDaoImpl impl =
	 * new FeeSetupDaoImpl(); String concesionName =
	 * detailsPojo.getConcessionName(); String desc =
	 * detailsPojo.getDescription(); String perc = detailsPojo.getPercentage();
	 * 
	 * Connection conn = null;
	 * 
	 * boolean check = impl.checkconcessionName(detailsPojo); if (!check) { try
	 * {
	 * 
	 * System.out.println("Daoimpl concession details"); conn =
	 * JDBCConnection.getSeparateConnection(); pstmt = conn
	 * .prepareStatement(SQLUtilConstants.INSERT_CONCESSION_DETAILS);
	 * pstmt.setString(1, IDGenerator
	 * .getPrimaryKeyID("campus_fee_concessiondetails")); pstmt.setString(2,
	 * concesionName.trim()); pstmt.setString(3, perc.trim());
	 * pstmt.setString(4, desc.trim()); pstmt.setString(5, USER_NAME);
	 * pstmt.setTimestamp(6, time_stamp); pstmt.setString(7, null);
	 * pstmt.setString(8, null);
	 * 
	 * pstmt.executeUpdate(); } catch (SQLException e) {
	 * logger.error(e.getMessage(), e); e.printStackTrace(); } catch (Exception
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } finally {
	 * try { if (pstmt != null && (!pstmt.isClosed())) { pstmt.close(); } if
	 * (conn != null && !conn.isClosed()) { conn.close(); } } catch (Exception
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } } }
	 * JLogger.log(0, JDate.getTimeString(new Date()) +
	 * MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	 * + " Control in FeeSetupDaoImpl : insertConcesssionDetails  Ending");
	 * return check; }
	 * 
	 * 
	 * public synchronized boolean checkconcessionName( ConcessionDetailsPojo
	 * detailsPojo) { logger.setLevel(Level.DEBUG); JLogger.log(0,
	 * JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
	 * logger.info(JDate.getTimeString(new Date()) +
	 * " Control in FeeSetupDaoImpl : checkconcessionName Starting"); // TODO
	 * Auto-generated method stub
	 * 
	 * PreparedStatement pstmt = null;
	 * 
	 * boolean checkStream = false;
	 * 
	 * ResultSet rs = null; Connection conn = null;
	 * 
	 * try {
	 * 
	 * conn = JDBCConnection.getSeparateConnection(); pstmt = conn
	 * .prepareStatement(SQLUtilConstants.CHECK_CONCESSION_NAME);
	 * pstmt.setString(1, detailsPojo.getConcessionName());
	 * 
	 * rs = pstmt.executeQuery(); rs.next(); if (rs.getInt("usercount") > 0) {
	 * checkStream = true; } else { checkStream = false; } } catch (SQLException
	 * e) { logger.error(e.getMessage(), e); e.printStackTrace(); } catch
	 * (Exception e) { logger.error(e.getMessage(), e); e.printStackTrace(); }
	 * finally { try { if (rs != null && (!rs.isClosed())) { rs.close(); } if
	 * (pstmt != null && (!pstmt.isClosed())) { pstmt.close(); } if (conn !=
	 * null && !conn.isClosed()) { conn.close(); } } catch (Exception e) {
	 * logger.error(e.getMessage(), e); e.printStackTrace(); } } JLogger.log(0,
	 * JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
	 * logger.info(JDate.getTimeString(new Date()) +
	 * " Control in FeeSetupDaoImpl : checkconcessionName  Ending"); return
	 * checkStream; }
	 */

	public synchronized ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeSetupDaoImpl : EditConcesssionDetails Starting");

		PreparedStatement concession_pstmt = null;
		ResultSet concession_rs = null;
		Connection conn = null;
		ConcessionForm concessionVO = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			concession_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_CONCESSION);
			concession_pstmt.setString(1, detailsForm.getConcessionId());
			//System.out.println("EDIT_CONCESSION -->>"+concession_pstmt);
			concession_rs = concession_pstmt.executeQuery();
			while (concession_rs.next()) {
				concessionVO = new ConcessionForm();
				concessionVO.setConcessionId(concession_rs.getString("concessionid"));
				concessionVO.setConcesionName(concession_rs.getString("concessionname"));
				concessionVO.setLocationnid(concession_rs.getString("locId"));
				concessionVO.setDescription(concession_rs.getString("description"));
				concessionVO.setConcessionType(concession_rs.getString("concessionType"));
				concessionVO.setIsApplicable(concession_rs.getString("isApplicableFor"));
				concessionVO.setConcession(concession_rs.getString("concessionBy"));
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (concession_rs != null && (!concession_rs.isClosed())) {
					concession_rs.close();
				}
				if (concession_pstmt != null && (!concession_pstmt.isClosed())) {
					concession_pstmt.close();
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
				+ " Control in FeeSetupDaoImpl : EditConcesssionDetails  Ending");
		return concessionVO;

	}

	@Override
	public String deleteconcession(FeeConcessionVO vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: deleteconcession : Starting");
		PreparedStatement pstmt = null;
		int count=0;
		String status = null,value=null;
		Connection conn = null;
		int resultst = 0;
		int unmapcnt = 0;
		int found = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQLUtilConstants.ACTIVE_INACTIVE_CONCESSION);
			
			if(vo.getStatus().equalsIgnoreCase("InActive")){
				for(int i=0;i<vo.getConcId().length;i++){
					
					PreparedStatement getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_scholorship` WHERE `concessionId` = ?");
					getst.setString(1, vo.getConcId()[i]);
					ResultSet rsst = getst.executeQuery();
					System.out.println(getst);
					while (rsst.next()) {
						found = rsst.getInt(1);
					}
					
					if(found == 0){
						unmapcnt++;
						pstmt.setString(1, "N");
						 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
							 pstmt.setString(2, vo.getInactiveReason()); 
						 }
						 else{
							 pstmt.setString(2, vo.getOtherReason());
						 }
						 value="InActive";
						 pstmt.setString(3, vo.getConcId()[i]);
						 count= pstmt.executeUpdate();
						 System.out.println(pstmt);
						 if(count > 0){
							 resultst++;
							 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Concession Details",value,pstmt.toString(),conn);
						 }
					}
				}
				
				if(unmapcnt!=0 && resultst == unmapcnt){
					status = "inactivetrue";
					conn.commit();
				}else if(resultst!=0 && resultst!=unmapcnt){
					status = "fail";
					conn.rollback();
				}else{
					conn.rollback();
					status = "inactivefalse";
				}
			 }else{
				 for(int i=0;i<vo.getConcId().length;i++){
					 unmapcnt++;
					 pstmt.setString(1, "Y");
					 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
						 pstmt.setString(2, vo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherReason());
					 }
					 value="Active";
				 
				 pstmt.setString(3, vo.getConcId()[i]);
				 count= pstmt.executeUpdate();
				
				 if(count > 0){
					 resultst++;
					 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Concession Details",value,pstmt.toString(),conn);
					}
				 }
				 if(unmapcnt!=0 && resultst == unmapcnt){
					 conn.commit();
					status = "activetrue";
				}else if(resultst!=0 && resultst!=unmapcnt){
					conn.rollback();
					status = "fail";
				}
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
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl: deleteconcession : Ending");

		return status;

	}

	public String getnamecount(FeeConcessionVO vo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String result = null,status=null;
		if (vo.getConcessionId().equalsIgnoreCase(""))

		{
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_CONCESSION_COUNT);
				pstmt.setString(1, vo.getConcesionName().trim());
				pstmt.setString(2, vo.getLocId());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					status=rs.getString(2);
				}
				
				if (count > 0 && status.equalsIgnoreCase("Y"))
				{
					result = "duplicate";
				}
				else if(count > 0 && status.equalsIgnoreCase("N")){
					result = "duplicateInactive";
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
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

		}

		if (!vo.getConcessionId().equalsIgnoreCase(""))

		{
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_CONCESSION_COUNT);
				pstmt.setString(1, vo.getConcessionId().trim());
				pstmt.setString(2, vo.getConcesionName().trim());
				pstmt.setString(3, vo.getLocId());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					status=rs.getString(2);
				}

				if (count > 0 && status.equalsIgnoreCase("Y"))
				{
					result = "duplicate";
				}
				else if(count > 0 && status.equalsIgnoreCase("N")){
					result = "duplicateInactive";
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
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

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl : getnamecount Ending");
		return result;

	}

	public String updateConcessionDao(ConcessionDetailsPojo detailsPojo,UserLoggingsPojo custdetails)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupDaoImpl :updateConcessionDao");

		PreparedStatement concession_pstmt = null;
		String status = null;
		Connection conn = null;
		int result1=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			concession_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_FEE_CONCESSION_DETAILS);
			concession_pstmt.setString(1, detailsPojo.getConcessionName().trim());
			concession_pstmt.setString(2, detailsPojo.getDescription().trim());
			concession_pstmt.setString(3, detailsPojo.getLocId());
			concession_pstmt.setString(4, detailsPojo.getCreateUser());
			concession_pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			concession_pstmt.setString(6, detailsPojo.getConcessionType());
			concession_pstmt.setString(7, detailsPojo.getIsApplicable());
			concession_pstmt.setString(8, detailsPojo.getConcession());
			concession_pstmt.setString(9, detailsPojo.getConcessionId());
			System.out.println("UPDATE_FEE_CONCESSION_DETAILS -->>"+concession_pstmt);
			result1 = concession_pstmt.executeUpdate(); 

			if (result1 > 0) {
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Master","Concession Details","Update",concession_pstmt.toString(),custdetails);
				status = "updatesuccess";
			} 
			else {
				status = "updatefail";
			}
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {

				if (concession_pstmt != null && !concession_pstmt.isClosed()) {
					concession_pstmt.close();
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
				+ " Control in FeeSetupDaoImpl : updateConcessionDao Ending");
			return status;
		}

}
