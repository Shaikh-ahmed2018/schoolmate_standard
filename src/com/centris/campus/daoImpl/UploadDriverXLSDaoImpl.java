package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TransportUtilConstants;
import com.centris.campus.util.UploadDriverXLSqlUtil;
import com.centris.campus.util.UploadStageXLSqlUtil;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;

public class UploadDriverXLSDaoImpl {

	private static Logger logger = Logger.getLogger(UploadDriverXLSDaoImpl.class);

	public int checkStageCountBeforeInsert() {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(UploadStageXLSqlUtil.CHECK_BEFORINSERT_COUNT);

			//System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);
			}
			//System.out.println("In DIOMPL Before Insert: " + beforeInsertCount);
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
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

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStageXLSDaoImpl: Ending");

		return beforeInsertCount;
	}

	public int checkStageName(String stageId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStageXSLDaoImpl : checkStageID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {
			ps_emp_count = connection.prepareStatement(UploadStageXLSqlUtil.STAGE_DUPLICATE);
			ps_emp_count.setString(1, stageId);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				int count = rs_emp_count.getInt(1);
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {
				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {
					rs_emp_count.close();
				}
				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {
					ps_emp_count.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return 0;
	}

	public Set<UploadDriverXlsVO> insertDriverXSL(List<UploadDriverXlsVO> successlist, Connection connection,
			String log_audit_session, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");

		ResultSet rs_emp_count = null;
		PreparedStatement pstmcount = null;
		PreparedStatement psDriverAdd = null;
		PreparedStatement ps_emp_count = null;
		Connection conn = null;
		int conutDuplicateRecord = 0;
		String returnCount = null;
		Set<UploadDriverXlsVO> failurelistOnDiompl = new LinkedHashSet<UploadDriverXlsVO>();
		try {

			 
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			psDriverAdd = conn.prepareStatement(UploadDriverXLSqlUtil.INSERT_DRIVER);

			//System.out.println("successlist:::" + successlist.size());

			UploadDriverXlsVO uploadDriverXlsVO = new UploadDriverXlsVO();

			for (int i = 0; i < successlist.size(); i++) {
				int dlNocount = checkDuplicateDLNo(successlist.get(i).getDrivingLicenseNo().trim(),connection,userLoggingsVo,successlist.get(i).getLocationnid());
				
				if (dlNocount == 0) {
					Timestamp createdDate = HelperClass.getCurrentTimestamp();
					psDriverAdd.setString(1, new IDGenerator().getPrimaryKeyID("transport_driver", userLoggingsVo));
					psDriverAdd.setString(2, "driver");
					psDriverAdd.setString(3, successlist.get(i).getDriverName().trim());
					psDriverAdd.setString(4, successlist.get(i).getFatherName().trim());
					psDriverAdd.setString(5, HelperClass.convertUIToDatabase(successlist.get(i).getDob().trim()));
					psDriverAdd.setString(6, successlist.get(i).getMobile().trim());
					psDriverAdd.setString(7, successlist.get(i).getEmergencyContactNo().trim());
					psDriverAdd.setString(8, successlist.get(i).getExperiance().trim());
					psDriverAdd.setString(9, successlist.get(i).getAddress().trim());
					psDriverAdd.setString(10, HelperClass.convertUIToDatabase(successlist.get(i).getDoj().trim()));
					psDriverAdd.setString(11, successlist.get(i).getAge().trim());
					psDriverAdd.setString(12, successlist.get(i).getGender().trim());
					psDriverAdd.setString(13,HelperClass.convertUIToDatabase(successlist.get(i).getDrivingLicenseValidityDate().trim()));
					psDriverAdd.setString(14, successlist.get(i).getLicenseToDrive().trim());
					psDriverAdd.setTimestamp(15, createdDate);
					psDriverAdd.setString(16, successlist.get(i).getCreatedby().trim());
					psDriverAdd.setString(17, successlist.get(i).getDrivingLicenseNo().trim());
					psDriverAdd.setString(18, successlist.get(i).getLocationnid().trim());
					
					//System.out.println("INSERT_DRIVER -->>" + psDriverAdd);

					int value = psDriverAdd.executeUpdate();
					if (value > 0) {
						HelperClass.recordLog_Activity(log_audit_session,"Transport","DriverExcelUpload","Insert",psDriverAdd.toString(), conn);
					}
				} else {
					uploadDriverXlsVO.setDriverName(successlist.get(i).getDriverName().trim());
					uploadDriverXlsVO.setDrivingLicenseNo(successlist.get(i).getDrivingLicenseNo().trim());
					uploadDriverXlsVO.setLicenseToDrive(successlist.get(i).getLicenseToDrive().trim());
					uploadDriverXlsVO.setReason("DL No. Already Exist");
					failurelistOnDiompl.add(uploadDriverXlsVO);
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(), sqle);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (psDriverAdd != null && (!psDriverAdd.isClosed())) {
					psDriverAdd.close();
				}
				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {
					rs_emp_count.close();
				}

				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {
					ps_emp_count.close();
				}

				if (pstmcount != null && (!pstmcount.isClosed())) {
					pstmcount.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadStageXSLDaoImpl : insertStageXSL : Ending");

		returnCount = "" + conutDuplicateRecord++;
		//System.out.println("In DAIOMPL duplicate Count= " + returnCount);
		return failurelistOnDiompl;
	}

	private int checkDuplicateDLNo(String dlNo, Connection connection, UserLoggingsPojo userLoggingsVo, String locId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadDriverXLSDaoImpl : checkDuplicateDLNo : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int licence = 0;

		try {
			psmt = connection.prepareStatement(TransportUtilConstants.VALIDATE_LICENSE);
			psmt.setString(1, dlNo.trim()); 
			psmt.setString(2, locId);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				licence = rs.getInt("driverCount");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadDriverXLSDaoImpl : checkDuplicateDLNo :  Ending");
		return licence;
	}
}
