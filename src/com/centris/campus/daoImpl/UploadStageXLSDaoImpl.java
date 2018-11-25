package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;

import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStageXLSqlUtil;
import com.centris.campus.vo.UploadStageXlsVO;



public class UploadStageXLSDaoImpl {

	private static Logger logger = Logger.getLogger(UploadStageXLSDaoImpl.class);

	public int checkStageCountBeforeInsert(UserLoggingsPojo userLoggingsVo) {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(UploadStageXLSqlUtil.CHECK_BEFORINSERT_COUNT);

			/*System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);*/

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
			//System.out.println("In DIOMPL Before Insert: "+beforeInsertCount);
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
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
				+ " Control in UploadStageXLSDaoImpl: Ending");

		return beforeInsertCount;
	}

	public int checkStageName(String stageId, String acc, String loc, String amount, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Starting");
		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;
		int count1=0;
		try {
			ps_emp_count = connection.prepareStatement(UploadStageXLSqlUtil.STAGE_DUPLICATE);
			ps_emp_count.setString(1, stageId);
			ps_emp_count.setString(2, loc);
			rs_emp_count = ps_emp_count.executeQuery();
			while (rs_emp_count.next()) {
				 count1 = rs_emp_count.getInt(1);
			}
			if(count1==0) {
				return count1;
			}else {
				String id=getStageId(stageId, connection);
				int m=checkAmountForAccYear(id,acc,acc,amount,connection);
			    return m;
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

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return 0;
	}



	public int checkAmountForAccYear(String stageId, String acc, String loc,String amount, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkAmountForAccYear : Starting");

		PreparedStatement ps_emp_count = null,pstmt1 = null;
		ResultSet rs_emp_count = null;
		int count=0;
		try {
			ps_emp_count = connection.prepareStatement("SELECT COUNT(*) FROM `campus_fee_stage_amount` WHERE `stageId`=? AND `accyearId`=? AND `locationId`=?");
			ps_emp_count.setString(1, stageId);
			ps_emp_count.setString(2, acc);
			ps_emp_count.setString(3, loc);
			//System.out.println("9999999999999"+ps_emp_count);
			rs_emp_count = ps_emp_count.executeQuery();
			while (rs_emp_count.next()) {
				count = rs_emp_count.getInt(1);
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

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkAmountForAccYear : Ending");
		return count;
	}

	public String getStageId(String stage_name, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : getStageID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {
			ps_emp_count = connection.prepareStatement(UploadStageXLSqlUtil.GET_STAGE_ID);
			ps_emp_count.setString(1, stage_name.trim());
			rs_emp_count = ps_emp_count.executeQuery();
			while (rs_emp_count.next()) {
				String id = rs_emp_count.getString(1);
				return id;
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

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : getStageID : Ending");
		return null;
	}

	public Set<UploadStageXlsVO> insertStageXSL(List<UploadStageXlsPOJO> successlist,
			Connection conn,Set<UploadStageXlsVO> failurelist,String log_audit_session,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");
		Map<String, String> studentIDAdmissionNOMap = new HashMap<String, String>();
		ResultSet rs_emp_count=null,rs=null;
		PreparedStatement pstmcount = null,pstmt1=null,pstmt2=null;
		PreparedStatement psStageAdd = null,psStageAdd1 = null;
		PreparedStatement ps_emp_count= null;
		PreparedStatement countDuplicate=null;
		int stageDuplicateCount = 0;
		int conutDuplicateRecord=0;
		String returnCount=null;
		try{
			
			/*			    conn.setAutoCommit(false);
			 */
			psStageAdd = conn.prepareStatement(UploadStageXLSqlUtil.INSERT_STAGE,Statement.RETURN_GENERATED_KEYS);
			countDuplicate = conn.prepareStatement(UploadStageXLSqlUtil.STAGE_DUPLICATE);
			psStageAdd1 = conn.prepareStatement(UploadStageXLSqlUtil.INSERT_STAGE_AMOUNT);
			for(int i=0;i<successlist.size();i++){
				//duplicate count
				countDuplicate.setString(1, successlist.get(i).getStage_name().trim());
				countDuplicate.setString(2, successlist.get(i).getLocationid().trim());
				ResultSet duplicateRs = countDuplicate.executeQuery();
				while (duplicateRs.next()) {
					stageDuplicateCount = duplicateRs.getInt(1);
				}
				//now not checking the duplicate
				//stageDuplicateCount=0;
				if (stageDuplicateCount != 0) {
					UploadStageXlsVO vo =new UploadStageXlsVO();
					String id=getStageId(successlist.get(i).getStage_name().trim(), conn);
					 
					
					int m=checkAmountForAccYear(id,successlist.get(i).getAccyearid().trim(),successlist.get(i).getLocationid().trim(),successlist.get(i).getAmount().trim(),conn);
					 
					if(m==0) {
						pstmt1=conn.prepareStatement("INSERT INTO campus_fee_stage_amount(`stageId`,`accyearId`,`locationId`,`stageamount`,`createdby`,`createdtime`) VALUES(?,?,?,?,?,now())");
						pstmt1.setString(1, id);
						pstmt1.setString(2, successlist.get(i).getAccyearid().trim());
						pstmt1.setString(3, successlist.get(i).getLocationid().trim());
						pstmt1.setString(4, successlist.get(i).getAmount().trim());
						pstmt1.setString(5,successlist.get(i).getCreatedby().trim());
                        
						pstmt1.executeUpdate();
					}else {
						pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_fee_stage_amount` WHERE `stageId`=? AND `accyearId`=? AND `locationId`=?");
						pstmt1.setString(1, id);
						pstmt1.setString(2, successlist.get(i).getAccyearid().trim());
						pstmt1.setString(3, successlist.get(i).getLocationid().trim());
						 
						rs=pstmt1.executeQuery();
						while(rs.next()) {
							rs.getInt(1);
							if(rs.getInt(1)>0) {
								vo.setStageid(id);
								vo.setAccyearid(successlist.get(i).getAccyearid().trim());
								vo.setLocationid(successlist.get(i).getLocationid().trim());
								vo.setReason("Stage Already Registered with these Details");
								vo.setStage_name(successlist.get(i).getStage_name().trim());
								vo.setStage_description(successlist.get(i).getStage_description().trim());
								vo.setAmount(successlist.get(i).getAmount().trim());
								studentIDAdmissionNOMap.put("errorMessage","Stage Already Registered with these Details");
								failurelist.add(vo);
								conutDuplicateRecord++;
							}
						}
					}
				}
				else {
					/*						conn.setAutoCommit(false);
					 */				 
					//stage insert
				 
					/*String stageId=IDGenerator.getPrimaryKeyID("campus_fee_stage",userLoggingsVo);*/
					
					int stageId=0;
					
					Timestamp createdDate = HelperClass.getCurrentTimestamp();
					 
					/*psStageAdd.setString(1,stageId);*/
					psStageAdd.setString(1, successlist.get(i).getStage_name().trim()); 
					psStageAdd.setString(2, successlist.get(i).getStage_description().trim());
					psStageAdd.setString(3, successlist.get(i).getCreatedby().trim());
					psStageAdd.setTimestamp(4, createdDate);
					psStageAdd.setString(5, successlist.get(i).getLocationid().trim());
					psStageAdd.setString(6, successlist.get(i).getAccyearid().trim());
					 
					int count=psStageAdd.executeUpdate();
					
					rs=psStageAdd.getGeneratedKeys();
					while(rs.next()){
						stageId=rs.getInt(1);
					}
					
					if(count>0) {
						HelperClass.recordLog_Activity(log_audit_session, "Transport", "Upload Stage Excel Data File","insert", psStageAdd.toString(), conn);
						/*String stage_fee=IDGenerator.getPrimaryKeyID("campus_fee_stage_amount",userLoggingsVo);*/
						
						psStageAdd1.setInt(1,stageId);
						psStageAdd1.setString(2,successlist.get(i).getAccyearid().trim());
						psStageAdd1.setString(3,successlist.get(i).getLocationid().trim());
						if(successlist.get(i).getAmount()==""||successlist.get(i).getAmount()==" ") {
							psStageAdd1.setInt(4,0);
						}else {
							psStageAdd1.setInt(4,Integer.parseInt(successlist.get(i).getAmount().trim()));
						}
						psStageAdd1.setString(5,successlist.get(i).getCreatedby().trim());
						psStageAdd1.executeUpdate();
					}
				}
			}
             
			/*			 conn.commit();
			 */		
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{

			try {
				
				if (psStageAdd != null && (!psStageAdd.isClosed())) {

				psStageAdd.close();
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



			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(),sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);

			}


		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Ending");

		returnCount=""+conutDuplicateRecord++;
		System.out.println("In DAIOMPL duplicate Count= "+returnCount);
		return failurelist;
	}

	public String updateStageXSL(String[] stageId, String[] acc, String[] loc, String[] amount, String user, UploadStageXlsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkAmountForAccYear : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;
		Connection connection=null;
		String status=null;
		int count =0;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0; i<stageId.length; i++) {
				ps_emp_count = connection.prepareStatement("UPDATE `campus_fee_stage_amount` SET `stageamount`=?,`modifyby`=? WHERE `stageId`=? AND `accyearId`=? AND `locationId`=? ");
				ps_emp_count.setString(1, amount[i]);
				ps_emp_count.setString(2, user);
				ps_emp_count.setString(3, stageId[i]);
				ps_emp_count.setString(4, acc[i]);
				ps_emp_count.setString(5, loc[i]);
				ps_emp_count.executeUpdate();
				count++;
			}
			if(count > 0) {
				status=count+" : Record Updated Successfully";
			}else {
				status="Records Not Updated Successfully";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkAmountForAccYear : Ending");
		return status;
	}
	
}
