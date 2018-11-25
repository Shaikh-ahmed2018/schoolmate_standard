package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.OnlineFeePaymentDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.OnlinePaymentVo;


public class OnlineFeePaymentDaoImpl implements OnlineFeePaymentDAO {
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationDaoImpl.class);

	public List<OnlinePaymentVo> getStudentOnlineFeePaymentDetails(
			String userType, String userCode,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : getStudentOnlineFeePaymentDetails  Starting");
		List<OnlinePaymentVo> list = new ArrayList<OnlinePaymentVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement("SELECT stg.amount,csc.specilization,csc.student_imgurl_var,csc.locationId,csc.classdetail_id_int,csc.student_rollno,cs.student_id_int,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) studentName,CONCAT(cc.classdetails_name_var,'-',ccs.classsection_name_var) classdetails_name_var,cs.student_admissionno_var,stg.stage_name,stg.stage_id,rt.RouteCode,rt.RouteName FROM campus_student_classdetails csc  JOIN campus_student cs ON csc.student_id_int=cs.student_id_int JOIN campus_parentchildrelation cp ON csc.student_id_int=cp.stu_addmissionNo  JOIN campus_classdetail cc ON csc.classdetail_id_int=cc.classdetail_id_int AND cc.locationId=csc.locationId JOIN campus_classsection ccs ON csc.classsection_id_int=ccs.classsection_id_int LEFT JOIN `campus_student_transportdetails` ctr ON csc.student_id_int=ctr.student_id_int LEFT JOIN `campus_fee_stage` stg ON ctr.StageId=stg.stage_id LEFT JOIN `transport_route` rt ON ctr.route=rt.RouteCode WHERE  cp.parentid=? AND csc.fms_acadamicyear_id_int=?");
			pst.setString(1, userCode);
			pst.setString(2, HelperClass.getCurrentYearID(custdetails));
			
			System.out.println(pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				List<OnlinePaymentVo> termlist = new ArrayList<OnlinePaymentVo>();
				List<OnlinePaymentVo> transporttermlist = new ArrayList<OnlinePaymentVo>();
				
				OnlinePaymentVo feeVo = new OnlinePaymentVo();
				feeVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				feeVo.setStudentId(rs.getString("student_id_int"));
				feeVo.setStudentRollNo(rs.getString("student_rollno"));
				feeVo.setStudentName(rs.getString("studentName"));
				feeVo.setClassDetail(rs.getString("classdetails_name_var"));
				feeVo.setImgurl(rs.getString("student_imgurl_var"));
				feeVo.setStragename(rs.getString("stage_name"));
				feeVo.setRoutename(rs.getString("RouteName"));
				
				
				PreparedStatement pstmt=conn.prepareStatement("SELECT cfs.`feeSettingcode`,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM `campus_fee_setup` cfs JOIN `campus_fee_termdetails` cft ON cfs.TermCode=cft.termid WHERE TermCode NOT IN(SELECT termcode FROM `campus_fee_collection_details` WHERE accYear=? AND admissionNo=?) AND cfs.ClassCode=? AND cfs.locationId=? AND cfs.specialization=? AND cfs.AccyearCode=? ORDER BY LENGTH(cfs.TermCode),cfs.TermCode");
				pstmt.setString(1, HelperClass.getCurrentYearID(custdetails));
				pstmt.setString(2, rs.getString("student_id_int"));
				pstmt.setString(3, rs.getString("classdetail_id_int"));
				pstmt.setString(4, rs.getString("locationId"));
				pstmt.setString(5, rs.getString("specilization"));
				pstmt.setString(6, HelperClass.getCurrentYearID(custdetails));
				
				System.out.println("pstmtpstmt "+pstmt);
				ResultSet rst=pstmt.executeQuery();
				while(rst.next()) {
					OnlinePaymentVo feeVo1 = new OnlinePaymentVo();
					
					
					feeVo1.setFeeCode(rst.getString("feeSettingcode"));
					feeVo1.setTermid(rst.getString("TermCode"));
					feeVo1.setTerm(rst.getString("termname"));
					feeVo1.setClassId(rs.getString("classdetail_id_int"));
					feeVo1.setSpec(rs.getString("specilization"));
					
					int lateDay=rst.getInt("lateDay");
					double fineAmount=0.0;
					double feeAmount=0.0;
					
					
					int fiCount=0;
					PreparedStatement finecount=conn.prepareStatement("SELECT COUNT(*) FROM campus_fineconfiguration where classId=? and termid=?");
					finecount.setString(1, rs.getString("classdetail_id_int"));
					finecount.setString(2, rst.getString("TermCode"));
					ResultSet countRs=finecount.executeQuery();
					while(countRs.next()){
						fiCount=countRs.getInt(1);
					}
					if(fiCount>0){
						PreparedStatement finep=conn.prepareStatement("SELECT * FROM campus_fineconfiguration where classId=? and termid=? ORDER BY date");
						finep.setString(1, rs.getString("classdetail_id_int"));
						finep.setString(2, rst.getString("TermCode"));
						ResultSet finers=finep.executeQuery();
						while(finers.next()){
							
							
							String isApp=finers.getString("IsApplicable");
							if(isApp.equalsIgnoreCase("Y")){
								if(HelperClass.getCurrentSqlDate().compareTo(finers.getDate("date")) > 0){
									fineAmount=finers.getDouble("amount");
									System.out.println("Table"+fineAmount);
								}
								else{
									fineAmount=0.0;
								}
								
							}
							else{
								fineAmount=0.0;
							}
						}
					
					
					try {

						if (finers != null && (!finers.isClosed())) {
							finers.close();
						}
						if (finep != null && (!finep.isClosed())) {
							finep.close();
						}
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
					}
					else{
						fineAmount=0.0;
					}
					PreparedStatement feeAmt=conn.prepareStatement("SELECT SUM(feeAmount) feeAmount FROM `campus_fee_setupdetails` WHERE feeSettingCode=?");
					feeAmt.setString(1, rst.getString("feeSettingcode"));
					ResultSet fst=feeAmt.executeQuery();
					if(fst.next()) {
						feeAmount=fst.getDouble("feeAmount");
					}
					
					feeVo1.setDueFee(feeAmount+fineAmount);
					feeVo1.setFeeAmt(feeAmount);
					feeVo1.setFineAmt(fineAmount);
					termlist.add(feeVo1);
				
					
					
					try {

						if (fst != null && (!fst.isClosed())) {
							fst.close();
						}
						if (feeAmt != null && (!feeAmt.isClosed())) {
							feeAmt.close();
						}
						if (countRs != null && (!countRs.isClosed())) {
							countRs.close();
						}
						if (finecount != null && (!finecount.isClosed())) {
							finecount.close();
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
				
				
				if(rs.getString("stage_name") !=null) {
					PreparedStatement pstmt1=conn.prepareStatement("SELECT termname,termid,TIMESTAMPDIFF(MONTH,startdate,enddate) month FROM   `campus_fee_transport_termdetails` WHERE termid NOT IN(SELECT termcode FROM `campus_tranport_fee_collection_details` WHERE accYear=? AND admissionNo=?)  AND locationId=? ORDER BY LENGTH(termid),termid");
					pstmt1.setString(1, HelperClass.getCurrentYearID(custdetails));
					pstmt1.setString(2, rs.getString("student_id_int"));
					pstmt1.setString(3, rs.getString("locationId"));
					ResultSet rst1=pstmt1.executeQuery();
					while(rst1.next()) {
						OnlinePaymentVo feeVo1 = new OnlinePaymentVo();
						feeVo1.setFeeCode("transport");
						feeVo1.setTermid(rst1.getString("termid"));
						feeVo1.setTerm(rst1.getString("termname"));
						feeVo1.setClassId(rs.getString("classdetail_id_int"));
						feeVo1.setSpec(rs.getString("specilization"));
						feeVo1.setDueFee(rs.getDouble("amount")*(rst1.getInt("month")+1));
						feeVo1.setFeeAmt(rs.getDouble("amount")*(rst1.getInt("month")+1));
						feeVo1.setFineAmt(0.0);
						transporttermlist.add(feeVo1);
					}
					
					rst1.close();	
					pstmt1.close();
				}
				
				feeVo.setTermlist(termlist);
				feeVo.setTransporttermlist(transporttermlist);
				list.add(feeVo);
				rst.close();
				pstmt.close();	
				
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in OnlineFeePaymentDaoImpl : getStudentOnlineFeePaymentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public String onlinefeetransactionId(String studentId, String feeCode, String termcode, String bank, String tranID, String grandtotal, String tokenid, String fineAmt) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in OnlineFeePaymentDaoImpl : onlinefeetransactionId  Starting");
		String status ="";
		PreparedStatement pst = null;
		int count = 0;
		Connection conn = null;
		
		
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pst = conn.prepareStatement("INSERT INTO online_transactionid_table (transactionID,sudentID,feeCode,termId,bank,tokenid,totalamount,fineAmt) VALUES(?,?,?,?,?,?,?,?)");
			pst.setString(1, tranID);
			pst.setString(2, studentId);
			pst.setString(3, feeCode);
			pst.setString(4, termcode);
			pst.setString(5, bank);
			pst.setString(6, tokenid);
			pst.setDouble(7, Double.parseDouble(grandtotal));
			pst.setString(8, fineAmt);
			count=pst.executeUpdate();
			if(count>0) {
				status=tranID;
			}
			else {
				status="false";
			}
			
			
		} catch (Exception e) {
			status="false";
		}
		
		finally {
			try {

				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in OnlineFeePaymentDaoImpl : onlinefeetransactionId  Ending");
		return status;
	}

}
