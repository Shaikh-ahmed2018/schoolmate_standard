package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import com.centris.campus.dao.FeeCollectionDao;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.FeeCollectionSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class FeeCollectionDaoImpl implements FeeCollectionDao{

	private static final Logger logger = Logger.getLogger(FeeCollectionDaoImpl.class);

	@Override
	public ArrayList<FeeNameVo> getFeeCollectionDetails(String FeeDetails, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails : Starting");

		/*String studentId=FeeDetails.split(",")[0];*/
		String accyearId=FeeDetails.split(",")[1];
		String classId=FeeDetails.split(",")[2];
		String termId=FeeDetails.split(",")[3];
		String specialization=FeeDetails.split(",")[4];

		PreparedStatement ps_feelist=null;
		ResultSet rs_feelist=null;

		Connection conn = null;
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		int count=0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_AMOUNT);
			ps_feelist.setString(1, classId);
			ps_feelist.setString(2, accyearId);
			ps_feelist.setString(3, termId);
			ps_feelist.setString(4, specialization);

			rs_feelist=ps_feelist.executeQuery();

			while(rs_feelist.next()){
				count++;
				FeeNameVo feeNameVo=new FeeNameVo();
				feeNameVo.setSno(count);
				feeNameVo.setFeecode(rs_feelist.getString("FeeCode"));
				feeNameVo.setFeename(rs_feelist.getString("FeeName"));

				double actualamt=rs_feelist.getDouble("feeAmount");
				feeNameVo.setActualAmt(actualamt);
				feeCollectionList.add(feeNameVo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionDetails: Ending");

		return feeCollectionList;
	}

	public FeeCollectionVo getFeeCollectionAmount(String feecollectioncode,UserLoggingsPojo custdetails) {
		   
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionAmount : Starting");
		PreparedStatement ps_feeInformation=null;
		ResultSet rs_getfeeInformation=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		
		PreparedStatement ps_collection_count= null;
		ResultSet rs_collection_count=null;
		PreparedStatement ps_feelist= null;
		ResultSet rs_feelist=null;

		int lateDay=0;
		String paymode = null;
		String ddno = null;
		String dddate =null;
		double fineAmount=0.00;
		
		FeeCollectionVo collectionVo=new FeeCollectionVo();
		ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
		try {
			
			String[] feeDatails=feecollectioncode.split(",");
			
			String addmissionno=feeDatails[0];
			
			String accyearId=feeDatails[1];
			String locationId=feeDatails[2];
			String termId=null;
			String classId=null;
			String specCode=null;
			String feeSettingcode=null;
			String termname=null;
			double advanceCarry=0.0;
			double duesCarry=0.0;
			conn = JDBCConnection.getSeparateConnection(custdetails);
		
			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_DEDDER);
			ps_insertPlan.setString(1,addmissionno);
			ps_insertPlan.setString(2,accyearId);
			ps_insertPlan.setString(3,locationId);
			rs=ps_insertPlan.executeQuery();
			while(rs.next()){
				String concessionType="no";
				double concessionPercent=0.0;
				PreparedStatement sclpstmt=conn.prepareStatement("SELECT concessionType FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
				sclpstmt.setString(1, rs.getString("student_admissionno_var"));
				sclpstmt.setString(2, accyearId);
				
				ResultSet sclRs=sclpstmt.executeQuery();
				while(sclRs.next()){
					concessionType=sclRs.getString("concessionType");
				}
				sclRs.close();
				collectionVo.setStudentid(addmissionno);
				collectionVo.setAddmissionno(rs.getString("student_admissionno_var"));
				collectionVo.setStudentname(rs.getString("studentname"));
				
				collectionVo.setClassname(rs.getString("classdetails_name_var"));
				specCode=rs.getString("specilization");
				classId=rs.getString("classdetail_id_int");
				collectionVo.setClassId(classId);
				collectionVo.setSpecialization(specCode);
				collectionVo.setSectionname(rs.getString("classsection_name_var"));
				collectionVo.setAccYear(accyearId);
				collectionVo.setAccYearname(rs.getString("acadamic_year"));
				 
				collectionVo.setImgurl(rs.getString("imgurl"));
				
				if(rs.getString("isTransferInMiddle").equalsIgnoreCase("Yes")) {
					ps_feeInformation=conn.prepareStatement("SELECT cfs.feeSettingcode,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM campus_fee_setup cfs JOIN campus_fee_termdetails cft ON cfs.TermCode=cft.termid JOIN `campus_student_classdetails` csc ON cfs.ClassCode=csc.classdetail_id_int JOIN  `campus_student_transfer_fee_applicaple` cstfa ON cstfa.student_id_int=csc.student_id_int AND cfs.TermCode=cstfa.termid WHERE cfs.ClassCode=? AND cfs.AccyearCode=? AND cfs.specialization=? AND cfs.locationId=? AND csc.student_id_int=? AND cft.isActive='Y' ORDER BY CAST(SUBSTR(cfs.TermCode,4) AS UNSIGNED)");
					ps_feeInformation.setString(1, classId);
					ps_feeInformation.setString(2, accyearId);
					ps_feeInformation.setString(3, specCode);
					ps_feeInformation.setString(4, locationId);
					ps_feeInformation.setString(5, addmissionno);
				}
				else {
					ps_feeInformation=conn.prepareStatement("SELECT cfs.feeSettingcode,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM campus_fee_setup cfs JOIN campus_fee_termdetails cft ON cfs.TermCode=cft.termid WHERE cfs.ClassCode=? AND cfs.AccyearCode=? AND cfs.specialization=? AND cfs.locationId=? and cft.isActive='Y' ORDER BY CAST(SUBSTR(cfs.TermCode,4) AS UNSIGNED)");
					ps_feeInformation.setString(1, classId);
					ps_feeInformation.setString(2, accyearId);
					ps_feeInformation.setString(3, specCode);
					ps_feeInformation.setString(4, locationId);
				}
				rs_getfeeInformation=ps_feeInformation.executeQuery();
				while(rs_getfeeInformation.next()){
					PreparedStatement concessionStatement=null;
					ResultSet concessionRs=null;
					double concession=0.0;
					
					feeSettingcode=rs_getfeeInformation.getString(1);
					termId=rs_getfeeInformation.getString(2);
					termname=rs_getfeeInformation.getString("termname");
					lateDay=rs_getfeeInformation.getInt("lateDay");
					
					ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.FEE_COLLECTION_COUNT);
					
					ps_collection_count.setString(1, addmissionno);
					ps_collection_count.setString(2, accyearId);
					ps_collection_count.setString(3, termId);
					rs_collection_count=ps_collection_count.executeQuery();
					
					if(concessionType.equalsIgnoreCase("2")){
						 concessionStatement=conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE admissionNo=? AND academic_year=? AND classId=? ");
						 concessionStatement.setString(1, rs.getString("student_admissionno_var"));
						 concessionStatement.setString(2, accyearId);
						 concessionStatement.setString(3, classId);
						}
						else{
							 concessionStatement=conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE admissionNo=? AND academic_year=? AND classId=? AND termcode=?");
							 concessionStatement.setString(1, rs.getString("student_admissionno_var"));
							 concessionStatement.setString(2, accyearId);
							 concessionStatement.setString(3, classId);
							 concessionStatement.setString(4, termId);
						}
					
					concessionRs=concessionStatement.executeQuery();
					while(concessionRs.next()){
						concession=concessionRs.getDouble("concession");
					}
					concessionRs.close();
					int feeCollectionCount=0;
					String paidDate=null;
					
					int recieptNo=0;
					while(rs_collection_count.next()){
						feeCollectionCount=rs_collection_count.getInt(1);
						if(rs_collection_count.getString(2)!=null){
							paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
							advanceCarry=rs_collection_count.getDouble("advance_amount");
							duesCarry=rs_collection_count.getDouble("due_amount");
							paymode = rs_collection_count.getString("paymentMode");
							ddno = rs_collection_count.getString("paymentParticulars");
							dddate = rs_collection_count.getString("dd_cheque_date");
						}
							recieptNo=rs_collection_count.getInt(3);
							fineAmount=rs_collection_count.getDouble(4);
					}
					rs_collection_count.close();
					if(feeCollectionCount==0){
						int fiCount=0;
						PreparedStatement finecount=conn.prepareStatement("SELECT COUNT(*) FROM campus_fineconfiguration");
						ResultSet countRs=finecount.executeQuery();
						while(countRs.next()){
							fiCount=countRs.getInt(1);
						}
						countRs.close();
						if(fiCount>0){
						PreparedStatement finep=conn.prepareStatement("SELECT * FROM campus_fineconfiguration ORDER BY days");
						ResultSet finers=finep.executeQuery();
						while(finers.next()){
							
							String isApp=finers.getString("IsApplicable");
							if(isApp.equalsIgnoreCase("Y")){
								if(lateDay > finers.getInt("days")){
									fineAmount=finers.getDouble("amount");
								}
								else{
									fineAmount=0.0;
								}
							}
							else{
								fineAmount=0.0;
							}
						}
						finers.close();
						}
						else{
							fineAmount=0.0;
						}
						
						ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTION_TOTAL_AMOUNT);
						ps_feelist.setString(1, feeSettingcode);
						rs_feelist=ps_feelist.executeQuery();
						while(rs_feelist.next()){
							
							FeeNameVo feeNameVo=new FeeNameVo();
							count++;
							feeNameVo.setSno(count);
							feeNameVo.setActualAmt(rs_feelist.getDouble("totalFeeAmount"));
							feeNameVo.setPayingAmountArray(rs_feelist.getDouble("totalFeeAmount"));
							feeNameVo.setStatus("Not Paid");
							feeNameVo.setTerm(termname);
							feeNameVo.setTermId(termId);
							feeNameVo.setFeeId(feeSettingcode);
							feeNameVo.setFineAmount(fineAmount);
							feeNameVo.setAdvanceCarry(advanceCarry);
							feeNameVo.setDueCarry(duesCarry);
							feeNameVo.setPaidAmt(0.0);
							feeNameVo.setPaymode(paymode);
							feeNameVo.setDdno(ddno);
							feeNameVo.setDddate(dddate);
							feeNameVo.setConcessionAmt(concession);
							feeNameList.add(feeNameVo);
						}
						collectionVo.setFeeNamelist(feeNameList);
					}
					else{
						ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEEPAID_TOTAL_AMOUNT);
						ps_feelist.setString(1, addmissionno);
						ps_feelist.setString(2, accyearId);
						ps_feelist.setString(3, termId);
						rs_feelist=ps_feelist.executeQuery();
						while(rs_feelist.next()){
							FeeNameVo feeNameVo=new FeeNameVo();
							count++;
							feeNameVo.setSno(count);
							feeNameVo.setActualAmt(rs_feelist.getDouble("actualamount"));
							feeNameVo.setStatus("Paid");
							feeNameVo.setPayingAmountArray(rs_feelist.getDouble("totalamount")-fineAmount);
							feeNameVo.setPaidDate(paidDate);
							feeNameVo.setRecieptNo(recieptNo);
							feeNameVo.setTerm(termname);
							feeNameVo.setTermId(termId);
							feeNameVo.setFineAmount(fineAmount);
							feeNameVo.setAdvanceCarry(advanceCarry);
							feeNameVo.setDueCarry(duesCarry);
							feeNameVo.setPaymode(paymode);
							feeNameVo.setDdno(ddno);
							feeNameVo.setDddate(dddate);
							feeNameVo.setPaidAmt(rs_feelist.getDouble("amount_paid"));
							feeNameVo.setConcessionAmt(rs_feelist.getDouble("concessionAmt"));
							feeNameList.add(feeNameVo);
						}
						rs_feelist.close();
						collectionVo.setFeeNamelist(feeNameList);
					}
				}
				rs_getfeeInformation.close();
				ps_feeInformation.close();
				if(feeNameList.size()<1){
					collectionVo.setFeeNamelist(feeNameList);
				}
			}
			
			JSONArray array=new JSONArray();
			array.put(collectionVo.getFeeNamelist());
			
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
				+ " Control in FeeCollectionDaoImpl: getFeeCollectionAmount: Ending");
		
		return collectionVo;
	}

	@Override
	public String saveFeeCollection(FeeCollectionVo collectionVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: saveFeeCollection : Starting");

		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_insertPlan1=null;
		PreparedStatement ps_insertReciept=null;
		Connection conn = null;
		int count=0;
		PreparedStatement ps1= null;
		PreparedStatement ps2= null;

		int result=0;
		String status=null;
		int count_insertReciept=0;

		PreparedStatement ps_collection_count=null;
		ResultSet rs_collection_count=null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);

			ps_collection_count = conn.prepareStatement(FeeCollectionSqlUtils.GET_COLLECTION_UPDATE_CNT);

			ps_collection_count.setString(1, collectionVo.getAddmissionno());
			ps_collection_count.setString(2, collectionVo.getAccYear());
			ps_collection_count.setString(3, collectionVo.getTermid());

			rs_collection_count=ps_collection_count.executeQuery();

			String feeCollectionCount=null;

			while(rs_collection_count.next()){
				feeCollectionCount=rs_collection_count.getString(1);
			}
			rs_collection_count.close();
			ps_collection_count.close();
			String primaryKey=IDGenerator.getPrimaryKeyID("campus_fee_collection",userLoggingsVo);
			if(feeCollectionCount==null){

				ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION);

				ps_insertPlan.setString(1,primaryKey);
				ps_insertPlan.setString(2,collectionVo.getAddmissionno());
				ps_insertPlan.setString(3,collectionVo.getAccYear());
				ps_insertPlan.setString(4,collectionVo.getTermid());

				if(collectionVo.getOutstanding_balance()==0.0||collectionVo.getOutstanding_balance()==0||collectionVo.getOutstanding_balance()==0.00)
				{
					ps_insertPlan.setString(5,"Y");
				}
				else
				{
					ps_insertPlan.setString(5,"N");
				}

				ps_insertPlan.setDouble(6,collectionVo.getTot_actual_amt());
				ps_insertPlan.setDouble(7,collectionVo.getTot_actual_amt()-collectionVo.getFineAmount());
				ps_insertPlan.setDouble(8,collectionVo.getDuesCarry());
				ps_insertPlan.setDouble(9,collectionVo.getAdvanceCarry());
				ps_insertPlan.setDate(10,HelperClass.getCurrentSqlDate());
				ps_insertPlan.setString(11,collectionVo.getUserID());
				ps_insertPlan.setTimestamp(12,HelperClass.getCurrentTimestamp());
				ps_insertPlan.setDouble(13,collectionVo.getCurrent_payment());
				ps_insertPlan.setDouble(14,collectionVo.getDuesCarry());
				ps_insertPlan.setDouble(15,collectionVo.getFineAmount());
				ps_insertPlan.setString(16,collectionVo.getPaymentPatriculars());
				ps_insertPlan.setString(17,collectionVo.getPaymentMode());
				ps_insertPlan.setString(18,collectionVo.getDd_cheque_date());
				ps_insertPlan.setString(19,collectionVo.getDd_cheque_bank());
				ps_insertPlan.setDouble(20,collectionVo.getConcession());
				ps_insertPlan.setDouble(21,collectionVo.getCurrent_payment());

				count=ps_insertPlan.executeUpdate();


				ps_insertPlan.close();
				ps2 = conn.prepareStatement("insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount," +
						"balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps2.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_indetail",userLoggingsVo));
				ps2.setString(2,collectionVo.getAddmissionno());
				ps2.setString(3,collectionVo.getAccYear());
				ps2.setString(4,collectionVo.getTermid());
				ps2.setDouble(5,collectionVo.getTot_paid_amt());
				ps2.setDouble(6,collectionVo.getTot_actual_amt());
				ps2.setDouble(7,collectionVo.getOutstanding_balance());
				ps2.setDouble(8,collectionVo.getCurrent_payment());
				ps2.setDouble(9,collectionVo.getTot_concession_amt());
				ps2.setDouble(10,collectionVo.getConcession());
				ps2.setDate(11,HelperClass.getCurrentSqlDate());
				ps2.setString(12,collectionVo.getUserID());
				ps2.setTimestamp(13,HelperClass.getCurrentTimestamp());
				int count1=ps2.executeUpdate();

				if(count1>0){
					HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps2.toString(),userLoggingsVo);
				}
				ps2.close();
				if(count>0){
					HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps_insertPlan.toString(),userLoggingsVo);
					ps1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_DETAILS);

					for(int i=0;i<collectionVo.getFeeNamelist().size();i++){

						ps1.setString(1,primaryKey);
						ps1.setString(2,collectionVo.getFeeNamelist().get(i).getFeecode());
						ps1.setDouble(3,collectionVo.getFeeNamelist().get(i).getFeeAmountArray());
						ps1.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
						ps1.setDouble(5,collectionVo.getFeeNamelist().get(i).getOutStandingAmountArray());
						ps1.setDouble(6,collectionVo.getFeeNamelist().get(i).getConcessionpercentArray());
						ps1.setDouble(7,collectionVo.getFeeNamelist().get(i).getConsfeeAmountArray());

						result=ps1.executeUpdate();
						if(result>0){
							HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps1.toString(),userLoggingsVo);
						}
					}
					ps1.close();
					String genkey="";

					ps_insertReciept = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_RECIEPT);

					for(int i=0;i<collectionVo.getFeeNamelist().size();i++){

						ps_insertReciept.setString(1,primaryKey);
						ps_insertReciept.setString(2,collectionVo.getFeeNamelist().get(i).getFeecode());
						ps_insertReciept.setDouble(3,collectionVo.getFeeNamelist().get(i).getFeeAmountArray());
						ps_insertReciept.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
						ps_insertReciept.setDouble(5,collectionVo.getFeeNamelist().get(i).getOutStandingAmountArray());
						ps_insertReciept.setDouble(6,collectionVo.getFeeNamelist().get(i).getConcessionpercentArray());
						ps_insertReciept.setDouble(7,collectionVo.getFeeNamelist().get(i).getConsfeeAmountArray());
						genkey=primaryKey;
						count_insertReciept=ps_insertReciept.executeUpdate();
						
						if(count_insertReciept>0){
							HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps_insertReciept.toString(),userLoggingsVo);
						}
					}
					
					PreparedStatement ps_updatereciept = conn.prepareStatement("UPDATE campus_fee_reciept SET concessionPercent=?,consfeeAmount=? WHERE feeCollectionCode=? AND feeCode=?");
					
					PreparedStatement ps_getFeeCode = conn.prepareStatement("SELECT studentId,feecode,concession,concessionPercent FROM campus_scholorship WHERE studentId=? AND academic_year=?");
					 ps_getFeeCode.setString(1,collectionVo.getAddmissionno());
					 ps_getFeeCode.setString(2,collectionVo.getAccYear());

					 ResultSet rs1=ps_getFeeCode.executeQuery();
					 while(rs1.next()){
						 ps_updatereciept.setString(1,rs1.getString("concessionPercent"));
						 ps_updatereciept.setString(2,rs1.getString("concession"));
						 ps_updatereciept.setString(3,genkey);
						 ps_updatereciept.setString(4,rs1.getString("feecode"));

						 ps_updatereciept.executeUpdate();
						}
					 ps_updatereciept.close();
					 ps_getFeeCode.close();
					
					ps_insertReciept.close();
					for(int i=0;i<collectionVo.getFeeNamelist().size();i++){
						if(collectionVo.getFeeNamelist().get(i).getPayingAmountArray() != 0.0 || collectionVo.getFeeNamelist().get(i).getPayingAmountArray() !=0 || collectionVo.getFeeNamelist().get(i).getPayingAmountArray()!=0.00){
							ps_insertPlan1 = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_IN_DETAILS);
							ps_insertPlan1.setString(1,primaryKey);
							ps_insertPlan1.setString(2,collectionVo.getFeeNamelist().get(i).getFeecode());
							ps_insertPlan1.setDouble(3,collectionVo.getFeeNamelist().get(i).getFeeAmountArray());
							ps_insertPlan1.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
							ps_insertPlan1.setDouble(5,collectionVo.getFeeNamelist().get(i).getOutStandingAmountArray());

							int count2=ps_insertPlan1.executeUpdate();

							if(count2 > 0){
								HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps_insertPlan1.toString(),userLoggingsVo);
							}
						}
					}
				}
			}

			if(result>0){
				PreparedStatement ps_insert_detail=conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_COLLECTION_D);

				ps_insert_detail.setString(1,collectionVo.getAddmissionno());
				ps_insert_detail.setString(2,collectionVo.getAccYear());
				ps_insert_detail.setString(3,collectionVo.getTermid());
				ps_insert_detail.setString(4,"Y");
				ps_insert_detail.setString(5,collectionVo.getTot_opning_amt());
				ps_insert_detail.setDouble(6,collectionVo.getTot_actual_amt());
				ps_insert_detail.setDouble(7,collectionVo.getTot_concession_amt());
				ps_insert_detail.setDouble(8,collectionVo.getConcession());
				ps_insert_detail.setDate(9,HelperClass.getCurrentSqlDate());
				ps_insert_detail.setString(10,collectionVo.getUserID());
				ps_insert_detail.setTimestamp(11,HelperClass.getCurrentTimestamp());
				ps_insert_detail.setDouble(12,collectionVo.getCurrent_payment());
				ps_insert_detail.setDouble(13,collectionVo.getOutstanding_balance());

				int counts=ps_insert_detail.executeUpdate();
				if(counts>0){
					HelperClass.recordLog_Activity(collectionVo.getLog_audit_session(),"Fee","Fee Collection","Insert",ps_insert_detail.toString(),userLoggingsVo);
					status="true";
					conn.commit();
				}
				else{
					status="false";
				}
				ps_insert_detail.close();
			}
			else{
				status="false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

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
				+ " Control in FeeCollectionDaoImpl: saveFeeCollection: Ending");

		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getSearchFeeCollectionDetails(
			String currentYear, String classid, String sectionId, String termId, String stuId) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getSearchFeeCollectionDetails : Starting");



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getSearchFeeCollectionDetails: Ending");

		return null;
	}

	public List<ParentVO> getAllStudentNamesReportDao(String sectionid ,String classname, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllStudentNamesReportDao : Starting");

		List<ParentVO> seclist = new ArrayList<ParentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_STUDENT_DETAILS);

			pstmt.setString(1, sectionid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, classname);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ParentVO pojo = new ParentVO();

				pojo.setStudentid(rs.getString("student_id_int"));
				pojo.setStudentFnameVar(rs.getString("studentname"));

				seclist.add(pojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} 
			catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}




		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllStudentNamesReportDao : Ending");

		return seclist;
	}
	public ArrayList<ClassFeeSetupVo> getAllFeesDao(
			ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllFeesDao : Starting");

		ArrayList<ClassFeeSetupVo> allfeeslist = new ArrayList<ClassFeeSetupVo>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String amt = null;
		ClassFeeSetupVo feeSetupVo = null; 

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_ALL_FEES);

			pstmt.setString(1, feeSetupPojo.getAccYear());
			pstmt.setString(2, feeSetupPojo.getClassId());
			rs = pstmt.executeQuery();

			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;

			pstmt1 = conn.prepareStatement(FeeCollectionSqlUtils.GET_TOTAL_FEES);

			pstmt1.setString(1, feeSetupPojo.getAccYear());
			pstmt1.setString(2, feeSetupPojo.getClassId());

			rs1 = pstmt1.executeQuery();

			while (rs1.next()) {
				amt = rs1.getString("amount");

			}
			rs1.close();
			pstmt1.close();

			while (rs.next()) {
				count++;
				feeSetupVo = new ClassFeeSetupVo();
				feeSetupVo.setSno(count);
				feeSetupVo.setFeecode(rs.getString("FeeCode"));
				feeSetupVo.setFeename(rs.getString("FeeName"));
				feeSetupVo.setFeeamount(rs.getString("feeAmount"));
				feeSetupVo.setFeesettingcode(rs.getString("feeSettingCode"));
				feeSetupVo.setTotalfee(amt);

				allfeeslist.add(feeSetupVo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} 
			catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAllFeesDao : Ending");

		return allfeeslist;
	}

	public ClassFeeSetupVo getStudentValDao(ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getStudentValDao : Starting");

		ClassFeeSetupVo feeSetupVo = new ClassFeeSetupVo();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_STUDENT_VAL);

			pstmt.setString(1, feeSetupPojo.getStudentId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				feeSetupVo.setStudentid(rs.getString("student_id_int"));
				feeSetupVo.setStudentname(rs.getString("studentname"));
				feeSetupVo.setStdadmissionNo(rs
						.getString("student_admissionno_var"));
				feeSetupVo.setClassid(rs.getString("classdetail_id_int"));
				feeSetupVo.setClassname(rs.getString("classdetails_name_var"));

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} 
			catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getStudentValDao : Ending");

		return feeSetupVo;
	}

	public ArrayList<ClassFeeSetupVo> getPaymentTypeDAO(
			ClassFeeSetupPojo feeSetupPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getPaymentTypeDAO : Starting");

		ArrayList<ClassFeeSetupVo> paymentlist = new ArrayList<ClassFeeSetupVo>();
		ClassFeeSetupVo feeSetupVo = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		Double paidamount = 0.0;

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_PAMENT_TYPE_DETAILS);

			pstmt.setString(1, feeSetupPojo.getStudentId().trim());
			pstmt.setString(2, feeSetupPojo.getAddmissionNo().trim());
			pstmt.setString(3, feeSetupPojo.getFeesettingCode().trim());
			pstmt.setString(4, feeSetupPojo.getClassId().trim());

			rs = pstmt.executeQuery();

			feeSetupVo = new ClassFeeSetupVo();

			if (rs.next()) {

				feeSetupVo.setPaymenyid(rs.getString("patment_id"));
				feeSetupVo.setPaymentdate(HelperClass.convertDatabaseToUI(rs
						.getString("payment_date")));

				paidamount = rs.getDouble("paid_amount");

				feeSetupVo.setPaidamount(paidamount);

				/*
				 * feeSetupVo.setJanuary(rs.getString("january"));
				 * feeSetupVo.setFebruary(rs.getString("february"));
				 * feeSetupVo.setMarch(rs.getString("march"));
				 * feeSetupVo.setApril(rs.getString("april"));
				 * feeSetupVo.setMay(rs.getString("may"));
				 * feeSetupVo.setJune(rs.getString("june"));
				 * feeSetupVo.setJuly(rs.getString("july"));
				 * feeSetupVo.setAugust(rs.getString("august"));
				 * feeSetupVo.setSeptember(rs.getString("september"));
				 * feeSetupVo.setOctober(rs.getString("october"));
				 * feeSetupVo.setNovember(rs.getString("november"));
				 * feeSetupVo.setDecember(rs.getString("december"));
				 */

				if (rs.getString("january").equals("Y")) {

					feeSetupVo.setJanuary("JAN");

				} else {

				}

				if (rs.getString("february").equals("Y")) {

					feeSetupVo.setFebruary("FEB");

				} else {

				}
				if (rs.getString("march").equals("Y")) {

					feeSetupVo.setMarch("MAR");

				} else {

				}
				if (rs.getString("april").equals("Y")) {

					feeSetupVo.setApril("APR");

				} else {

				}
				if (rs.getString("may").equals("Y")) {

					feeSetupVo.setMay("MAY");

				} else {

				}
				if (rs.getString("june").equals("Y")) {

					feeSetupVo.setJune("JUN");

				} else {

				}
				if (rs.getString("july").equals("Y")) {

					feeSetupVo.setJuly("JUL");

				} else {

				}
				if (rs.getString("august").equals("Y")) {

					feeSetupVo.setAugust("AUG");

				} else {

				}
				if (rs.getString("september").equals("Y")) {

					feeSetupVo.setSeptember("SEP");

				} else {

				}
				if (rs.getString("october").equals("Y")) {

					feeSetupVo.setOctober("OCT");

				} else {

				}
				if (rs.getString("november").equals("Y")) {

					feeSetupVo.setNovember("NOV");

				} else {

				}
				if (rs.getString("december").equals("Y")) {

					feeSetupVo.setDecember("DEC");

				} else {

				}

			}

			else

			{

				feeSetupVo.setPaymenyid("");
				feeSetupVo.setPaymentdate("");
				feeSetupVo.setPaidamount(0.0);
				feeSetupVo.setJanuary("");
				feeSetupVo.setFebruary("");
				feeSetupVo.setMarch("");
				feeSetupVo.setApril("");
				feeSetupVo.setMay("");
				feeSetupVo.setJune("");
				feeSetupVo.setJuly("");
				feeSetupVo.setAugust("");
				feeSetupVo.setSeptember("");
				feeSetupVo.setOctober("");
				feeSetupVo.setNovember("");
				feeSetupVo.setDecember("");

			}

			paymentlist.add(feeSetupVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} 
			catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getPaymentTypeDAO : Ending");

		return paymentlist;
	}

	@Override
	public int getstudentcount(String studentid)

	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getstudentcount : Starting");

		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn
					.prepareStatement(FeeCollectionSqlUtils.GET_FEECOLLECTIONCOUNT);
			pstmt.setString(1, studentid.trim());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

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
				+ " Control in FeeCollectionDaoImpl: getstudentcount : Ending");
		return count;

	}

	@Override
	public boolean inserfeecollection(ClassFeeSetupVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: inserfeecollection : Starting");

		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		int count = 0;


		boolean status = false;





		PreparedStatement pstmt = null;


		String january = "N";
		String february = "N";
		String march = "N";
		String april = "N";
		String may = "N";
		String june = "N";
		String july = "N";
		String august = "N";
		String september = "N";
		String october = "N";
		String november = "N";
		String december = "N";

		try {

			String primaryKey = IDGenerator
					.getPrimaryKeyID("campus_payment_collection");

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_COLLECTION_DETAILS);

			pstmt.setString(1, primaryKey);
			pstmt.setString(2, vo.getFeecode());
			pstmt.setString(3, vo.getStudentid());
			pstmt.setString(4, vo.getPaymentmode());
			pstmt.setString(5, vo.getCheque_no());
			pstmt.setString(6, vo.getPaymenttype());
			pstmt.setString(7, vo.getMonthlist());
			pstmt.setDouble(8, vo.getPaidamount());
			pstmt.setDouble(9, vo.getDueamount());
			pstmt.setDouble(10,vo.getTotalfeeamount());
			pstmt.setString(11,HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			pstmt.setString(12,vo.getCurrentuser());
			pstmt.setTimestamp(13, HelperClass.getCurrentTimestamp());

			pstmt.executeUpdate();

			String s1 = vo.getMonthlist();

			String s2[] = s1.split(",");

			for (int i = 0; i < s2.length; i++)

			{

				// JAN
				if (s2[i].equals("JAN")) {

					january = "Y";
				} else {

					if (january.equals("Y")) {
						january = "Y";
					} else {
						january = "N";
					}
				}

				// FEBRUARY

				if (s2[i].equals("FEB")) {
					february = "Y";
				} else {
					if (february.equals("Y")) {
						february = "Y";
					} else {
						february = "N";
					}
				}

				// march

				if (s2[i].equals("MAR")) {
					march = "Y";
				} else {
					if (march.equals("Y"))

					{
						march = "Y";
					}

					else

					{
						march = "N";
					}
				}

				// April

				if (s2[i].equals("APR")) {
					april = "Y";
				} else {

					if (april.equals("Y"))

					{

						april = "Y";

					}

					else

					{

						april = "N";

					}
				}

				// MAY

				if (s2[i].equals("MAY")) {
					may = "Y";
				} else {

					if (may.equals("Y"))

					{

						may = "Y";

					}

					else

					{

						may = "N";

					}
				}

				// JUNE

				if (s2[i].equals("JUN")) {
					june = "Y";
				} else {

					if (june.equals("Y"))

					{

						june = "Y";

					}

					else

					{

						june = "N";

					}
				}

				// JULY

				if (s2[i].equals("JUL")) {
					july = "Y";
				} else {

					if (july.equals("Y"))

					{

						july = "Y";

					}

					else

					{

						july = "N";

					}
				}

				// AUGUST

				if (s2[i].equals("AUG")) {
					august = "Y";
				} else {

					if (august.equals("Y"))

					{

						august = "Y";

					}

					else

					{

						august = "N";

					}
				}

				// SEP

				if (s2[i].equals("SEP")) {

					september = "Y";
				} else {

					if (september.equals("Y"))

					{

						september = "Y";

					}

					else

					{

						september = "N";

					}
				}

				// OCTOBER

				if (s2[i].equals("OCT")) {

					october = "Y";
				} else {

					if (october.equals("Y"))

					{

						october = "Y";

					}

					else

					{

						october = "N";

					}
				}

				// November

				if (s2[i].equals("NOV")) {

					november = "Y";
				} else {

					if (november.equals("Y"))

					{

						november = "Y";

					}

					else

					{

						november = "N";

					}
				}

				// December

				if (s2[i].equals("DEC")) {

					december = "Y";
				} else {

					if (december.equals("Y"))

					{

						december = "Y";

					}

					else

					{

						december = "N";

					}
				}

			}





			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.INSERT_FEE_PAYMENT_COLLECTION);

			ps_insertPlan.setString(1, primaryKey);
			ps_insertPlan.setString(2, vo.getAcadamicyear());
			ps_insertPlan.setString(3, vo.getFeecode());
			ps_insertPlan.setString(4, vo.getStdadmissionNo());
			ps_insertPlan.setString(5, vo.getStudentid());
			ps_insertPlan.setString(6, vo.getPaymentmode());
			ps_insertPlan.setString(7, vo.getPaymenttype());
			ps_insertPlan.setDouble(8, vo.getPaidamount());
			ps_insertPlan.setDouble(9, vo.getTotalfeeamount());
			ps_insertPlan.setDouble(10, vo.getDueamount());
			ps_insertPlan.setString(11,	HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			ps_insertPlan.setString(12, january);
			ps_insertPlan.setString(13, february);
			ps_insertPlan.setString(14, march);
			ps_insertPlan.setString(15, april);
			ps_insertPlan.setString(16, may);
			ps_insertPlan.setString(17, june);
			ps_insertPlan.setString(18, july);
			ps_insertPlan.setString(19, august);
			ps_insertPlan.setString(20, september);
			ps_insertPlan.setString(21, october);
			ps_insertPlan.setString(22, november);
			ps_insertPlan.setString(23, december);
			ps_insertPlan.setString(24, vo.getClassname());
			ps_insertPlan.setString(25, vo.getCurrentuser());
			ps_insertPlan.setTimestamp(26, HelperClass.getCurrentTimestamp());

			count = ps_insertPlan.executeUpdate();

			if (count > 0) {

				status = true;

				storeFeeSMSDetails(primaryKey,vo);


			} else {

				status = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
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
				+ " Control in FeeCollectionDaoImpl: inserfeecollection: Ending");

		return status;
	}

	@Override

	public boolean updatefeecollection(ClassFeeSetupVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: updatefeecollection : Starting");

		PreparedStatement ps_insertPlan = null;
		Connection conn = null;
		int count = 0;


		boolean status = false;

		PreparedStatement pst = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;


		String patment_id=null;

		String january = "N";
		String february = "N";
		String march = "N";
		String april = "N";
		String may = "N";
		String june = "N";
		String july = "N";
		String august = "N";
		String september = "N";
		String october = "N";
		String november = "N";
		String december = "N";
		double paidamount=0;
		double dueamount=0;
		double totalamount=0;




		try {

			String s1 = vo.getMonthlist();

			String s2[] = s1.split(",");

			for (int i = 0; i < s2.length; i++)

			{

				// JAN
				if (s2[i].equals("JAN")) {

					january = "Y";
				} else {

					if (january.equals("Y")) {
						january = "Y";
					} else {
						january = "N";
					}
				}

				// FEBRUARY

				if (s2[i].equals("FEB")) {
					february = "Y";
				} else {
					if (february.equals("Y")) {
						february = "Y";
					} else {
						february = "N";
					}
				}

				// march

				if (s2[i].equals("MAR")) {
					march = "Y";
				} else {
					if (march.equals("Y"))

					{
						march = "Y";
					}

					else

					{
						march = "N";
					}
				}

				// April

				if (s2[i].equals("APR")) {
					april = "Y";
				} else {

					if (april.equals("Y"))

					{

						april = "Y";

					}

					else

					{

						april = "N";

					}
				}

				// MAY

				if (s2[i].equals("MAY")) {
					may = "Y";
				} else {

					if (may.equals("Y"))

					{

						may = "Y";

					}

					else

					{

						may = "N";

					}
				}

				// JUNE

				if (s2[i].equals("JUN")) {
					june = "Y";
				} else {

					if (june.equals("Y"))

					{

						june = "Y";

					}

					else

					{

						june = "N";

					}
				}

				// JULY

				if (s2[i].equals("JUL")) {
					july = "Y";
				} else {

					if (july.equals("Y"))

					{

						july = "Y";

					}

					else

					{

						july = "N";

					}
				}

				// AUGUST

				if (s2[i].equals("AUG")) {
					august = "Y";
				} else {

					if (august.equals("Y"))

					{

						august = "Y";

					}

					else

					{

						august = "N";

					}
				}

				// SEP

				if (s2[i].equals("SEP")) {

					september = "Y";
				} else {

					if (september.equals("Y"))

					{

						september = "Y";

					}

					else

					{

						september = "N";

					}
				}

				// OCTOBER

				if (s2[i].equals("OCT")) {

					october = "Y";
				} else {

					if (october.equals("Y"))

					{

						october = "Y";

					}

					else

					{

						october = "N";

					}
				}

				// November

				if (s2[i].equals("NOV")) {

					november = "Y";
				} else {

					if (november.equals("Y"))

					{

						november = "Y";

					}

					else

					{

						november = "N";

					}
				}

				// December

				if (s2[i].equals("DEC")) {

					december = "Y";
				} else {

					if (december.equals("Y"))

					{

						december = "Y";

					}

					else

					{

						december = "N";

					}
				}

			}

			conn = JDBCConnection.getSeparateConnection();




			pst=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_PARTICULARS);

			pst.setString(1,vo.getStudentid());
			pst.setString(2,vo.getAcadamicyear());

			rs=pst.executeQuery();

			while(rs.next())
			{

				paidamount=rs.getDouble("paid_amount");
				totalamount=rs.getDouble("total_amount");
				dueamount=rs.getDouble("due_amount");
				patment_id=rs.getString("patment_id");

			}

			double totalpaidamount=0;
			double total_dueamount=0;


			totalpaidamount=paidamount+vo.getPaidamount();

			total_dueamount=totalamount-totalpaidamount;

			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.UPDATE_FEE_PAYMENT_COLLECTION);

			ps_insertPlan.setString(1, vo.getFeecode());
			ps_insertPlan.setString(2, vo.getStdadmissionNo());
			ps_insertPlan.setString(3, vo.getPaymentmode());
			ps_insertPlan.setString(4, vo.getPaymenttype());
			ps_insertPlan.setDouble(5, totalpaidamount);
			ps_insertPlan.setDouble(6, vo.getTotalfeeamount());
			ps_insertPlan.setDouble(7, total_dueamount);
			ps_insertPlan.setString(8, HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			ps_insertPlan.setString(9, january);
			ps_insertPlan.setString(10, february);
			ps_insertPlan.setString(11, march);
			ps_insertPlan.setString(12, april);
			ps_insertPlan.setString(13, may);
			ps_insertPlan.setString(14, june);
			ps_insertPlan.setString(15, july);
			ps_insertPlan.setString(16, august);
			ps_insertPlan.setString(17, september);
			ps_insertPlan.setString(18, october);
			ps_insertPlan.setString(19, november);
			ps_insertPlan.setString(20, december);
			ps_insertPlan.setString(21, vo.getCurrentuser());
			ps_insertPlan.setTimestamp(22, HelperClass.getCurrentTimestamp());
			ps_insertPlan.setString(23, vo.getStudentid());
			ps_insertPlan.setString(24, vo.getAcadamicyear());
			ps_insertPlan.setString(25, vo.getClassname());

			count = ps_insertPlan.executeUpdate();

			pstmt = conn.prepareStatement(FeeCollectionSqlUtils.GET_FEE_COLLECTION_DETAILS);

			pstmt.setString(1, patment_id);
			pstmt.setString(2, vo.getFeecode());
			pstmt.setString(3, vo.getStudentid());
			pstmt.setString(4, vo.getPaymentmode());
			pstmt.setString(5, vo.getCheque_no());
			pstmt.setString(6, vo.getPaymenttype());
			pstmt.setString(7, vo.getMonthlist());
			pstmt.setDouble(8, vo.getPaidamount());
			pstmt.setDouble(9, vo.getDueamount());
			pstmt.setDouble(10,vo.getTotalfeeamount());
			pstmt.setString(11,HelperClass.convertUIToDatabase(vo.getPaymentdate()));
			pstmt.setString(12,vo.getCurrentuser());
			pstmt.setTimestamp(13, HelperClass.getCurrentTimestamp());

			pstmt.executeUpdate();

			if (count > 0) {

				status = true;


				storeFeeSMSDetails(vo.getFeecode(),vo);

			} else {

				status = false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst!=null && !pst.isClosed()){
					pst.close();
				}
				if (ps_insertPlan != null && (!ps_insertPlan.isClosed())) {
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
				+ " Control in FeeCollectionDaoImpl: updatefeecollection: Ending");

		return status;
	}



	public void storeFeeSMSDetails(String feecode,ClassFeeSetupVo vo)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: storeFeeSMSDetails : Starting");

		PreparedStatement ps_fee = null;
		PreparedStatement ps_fee_details=null;
		Connection conn = null;
		int count = 0;
		ResultSet rs =null;

		try {

			String feeId = IDGenerator.getPrimaryKeyID("sms_fee_details");

			conn = JDBCConnection.getSeparateConnection();

			ps_fee_details = conn.prepareStatement(SmsUtilsConstants.GET_FEE_DETAILS);
			ps_fee_details.setString(1, feecode);

			rs = ps_fee_details.executeQuery();

			while(rs.next())
			{

				String name =  rs.getString("name");

				ps_fee = conn.prepareStatement(SmsUtilsConstants.INSERT_FEE_DETAILS);
				ps_fee.setString(1, feeId );
				ps_fee.setTimestamp(2, HelperClass.getCurrentTimestamp());
				ps_fee.setString(3, vo.getStudentid());
				ps_fee.setString(4, "Fees paid for Academic Year "+ HelperClass.getAcademicYear() +" for your ward " + name +" bearing Admission No. " + rs.getString("student_admissionno_var") + " is Rs." + rs.getString("paid_amount") + " and Balance Amount is Rs." + rs.getString("due_amount"));
				ps_fee.setString(5, rs.getString("total_amount"));
				ps_fee.setString(6, rs.getString("paid_amount"));
				ps_fee.setString(7, rs.getString("due_amount"));
				ps_fee.setString(8, vo.getCurrentuser());
				ps_fee.setTimestamp(9, HelperClass.getCurrentTimestamp());

				count = ps_fee.executeUpdate();

			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

				if(rs!=null && ! rs.isClosed()){
					rs.close();
				}
				if (ps_fee_details!=null && !ps_fee_details.isClosed()){
					ps_fee_details.close();
				}
				if (ps_fee != null && (!ps_fee.isClosed())) {
					ps_fee.close();
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
				+ " Control in FeeCollectionDaoImpl: storeFeeSMSDetails: Ending");


	}

	

	@Override
	public String saveTransportFeeCollection(FeeCollectionVo collectionVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: saveTransportFeeCollection : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement getReiptPs=null;
		PreparedStatement transportdetails = null;
		ResultSet getReiptRs=null;
		int count=0;
		String status=null;
		int recieptNo=0;
		double advance=0.0;
		double balance=0.0;
		int countTrans=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			if(collectionVo.getRefundstatus()==null || collectionVo.getRefundstatus().equalsIgnoreCase("null"))
			{
				getReiptPs=conn.prepareStatement("SELECT CASE WHEN  MAX(reciept_no) IS NULL THEN '1001' ELSE MAX(reciept_no) END reciept_no FROM campus_tranport_fee_collection_details");
				getReiptRs=getReiptPs.executeQuery();
				while(getReiptRs.next()){
					recieptNo=getReiptRs.getInt("reciept_no")+1;
				}
				transportdetails=conn.prepareStatement("INSERT INTO campus_transport_fees_payments(receiptno,totalamt,paidAmount,balance,advance) VALUES(?,?,?,?,?)");
				transportdetails.setInt(1, recieptNo);
				transportdetails.setDouble(2, collectionVo.getTot_actual_amt());
				if(collectionVo.getCurrent_payment()<0){
					advance= Math.abs(collectionVo.getCurrent_payment());
				}
				else if(collectionVo.getTot_actual_amt()>collectionVo.getCurrent_payment()){
					balance=collectionVo.getTot_actual_amt()-collectionVo.getCurrent_payment();
				}
				else{
					advance=collectionVo.getCurrent_payment()-collectionVo.getTot_actual_amt();
				}
				transportdetails.setDouble(3, collectionVo.getCurrent_payment());
				transportdetails.setDouble(4, balance);
				transportdetails.setDouble(5, advance);
				countTrans=transportdetails.executeUpdate();
				transportdetails.close();

				if(countTrans>0){	 
					for(int i=0;i<collectionVo.getTermIdArray().length;i++){
						ps_insertPlan = conn.prepareStatement("INSERT INTO campus_tranport_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,amount_paid,balance_amount,paidDate,createdby,createdtime,MonthName,reciept_no,modeofpayment,bankname,dd_cheque_date,dd_cheque_no,locId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						ps_insertPlan.setString(1,collectionVo.getAddmissionno());
						ps_insertPlan.setString(2,collectionVo.getAccYear());
						ps_insertPlan.setString(3,collectionVo.getTermIdArray()[i]);
						ps_insertPlan.setString(4,"Y");
						ps_insertPlan.setDouble(5,Double.parseDouble(collectionVo.getMonthlyAmount()[i]));
						ps_insertPlan.setDouble(6,Double.parseDouble(collectionVo.getMonthlyAmount()[i]));
						ps_insertPlan.setDouble(7,Double.parseDouble(collectionVo.getMonthlyAmount()[i])-Double.parseDouble(collectionVo.getMonthlyAmount()[i]));	
						ps_insertPlan.setDate(8,HelperClass.getCurrentSqlDate());
						ps_insertPlan.setString(9,collectionVo.getUserID());
						ps_insertPlan.setTimestamp(10,HelperClass.getCurrentTimestamp());
						ps_insertPlan.setString(11, collectionVo.getMonthName()[i]);
						ps_insertPlan.setInt(12, recieptNo);
						ps_insertPlan.setString(13, collectionVo.getPaymentMode());
						ps_insertPlan.setString(14, collectionVo.getDd_cheque_bank());
						ps_insertPlan.setString(15, collectionVo.getDd_cheque_date());
						ps_insertPlan.setString(16, collectionVo.getPaymentPatriculars());
						ps_insertPlan.setString(17, collectionVo.getLocationId());
						
						count=ps_insertPlan.executeUpdate();

						if(count>0){
							status="true";
						}else{
							status="false";
						}
					}
					conn.commit();
				}
				else{
					status="false";
				}
			}
			else{
				PreparedStatement transportdetailsDelete=null;
				double subAmount=0.0;
				double subPaidAmount=0.0;
				int cnt=0;
				transportdetails=conn.prepareStatement("SELECT * FROM campus_transport_fees_payments WHERE receiptno=?");
				transportdetails.setString(1, collectionVo.getRefrecieptNo());
				ResultSet rs=transportdetails.executeQuery();
				if(rs.next()){
					subAmount=rs.getDouble("totalamt")-Double.parseDouble(collectionVo.getMonthlyAmount()[0]);
					subPaidAmount=rs.getDouble("paidAmount")-Double.parseDouble(collectionVo.getMonthlyAmount()[0]);
				}
				rs.close();
				transportdetails.close();
				if(subAmount>0){
					transportdetailsDelete=conn.prepareStatement("UPDATE campus_transport_fees_payments SET totalamt=?,paidAmount=? WHERE receiptno=?");
					transportdetailsDelete.setDouble(1, subAmount);
					transportdetailsDelete.setDouble(2, subPaidAmount);
					transportdetailsDelete.setString(3, collectionVo.getRefrecieptNo());
					cnt=transportdetailsDelete.executeUpdate();


				}
				else{
					transportdetailsDelete=conn.prepareStatement("DELETE FROM campus_transport_fees_payments WHERE receiptno=?");
					transportdetailsDelete.setString(1, collectionVo.getRefrecieptNo());
					cnt=transportdetailsDelete.executeUpdate();

				}
				for(int i=0;i<collectionVo.getTermIdArray().length;i++){
					ps_insertPlan = conn.prepareStatement("DELETE FROM campus_tranport_fee_collection_details WHERE admissionNo=? AND accYear=? AND termcode=? AND MonthName=?");
					ps_insertPlan.setString(1,collectionVo.getAddmissionno());
					ps_insertPlan.setString(2,collectionVo.getAccYear());
					ps_insertPlan.setString(3,collectionVo.getTermIdArray()[i]);
					ps_insertPlan.setString(4, collectionVo.getMonthName()[i]);

					count=ps_insertPlan.executeUpdate();

					if(count>0){
						status="true";
					}else{
						status="false";
					}
				}
				if(cnt>0){
					conn.commit();
				}
				transportdetailsDelete.close();
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (getReiptRs != null&& (!getReiptRs.isClosed())) {
					getReiptRs.close();
				}
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (getReiptPs != null&& (!getReiptPs.isClosed())) {
					getReiptPs.close();
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
				+ " Control in FeeCollectionDaoImpl: saveTransportFeeCollection: Ending");

		return status;
	}

	@Override
	public ArrayList<FeeNameVo> getFeePaidDetails(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getFeePaidDetails : Starting");

		String studentId=feeCodeDetails.split(",")[0];
		String accyearId=feeCodeDetails.split(",")[1];
		/*String classId=feeCodeDetails.split(",")[2];*/
		String termId=feeCodeDetails.split(",")[3];
		/*	String specialization=feeCodeDetails.split(",")[4];*/

		PreparedStatement ps_feelist=null;
		ResultSet rs_feelist=null;

		Connection conn = null;
		ArrayList<FeeNameVo> feeCollectionList=new ArrayList<FeeNameVo>();
		int count=0;
		PreparedStatement ps1= null;
		ResultSet rs1=null;
		String feeCollectionCode=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			ps_feelist=conn.prepareStatement(FeeCollectionSqlUtils.GET_FEEPAID_AMOUNT);
			ps_feelist.setString(1, studentId);
			ps_feelist.setString(2, accyearId);
			ps_feelist.setString(3, termId);

			rs_feelist=ps_feelist.executeQuery();

			while(rs_feelist.next()){
				feeCollectionCode=rs_feelist.getString("feeCollectionCode");
			}

			ps1=conn.prepareStatement("SELECT cfr.feeCode,cfr.feeAmount,cfm.FeeName FROM campus_fee_reciept cfr JOIN  campus_fee_master cfm ON cfr.feeCode=cfm.FeeCode WHERE cfr.feeCollectionCode=?");
			ps1.setString(1, feeCollectionCode);
			rs1=ps1.executeQuery();
			while(rs1.next()){
				count++;
				FeeNameVo feeNameVo=new FeeNameVo();
				feeNameVo.setSno(count);
				feeNameVo.setFeecode(rs1.getString("feeCode"));
				feeNameVo.setFeename(rs1.getString("FeeName"));

				double actualamt=rs1.getDouble("feeAmount");
				feeNameVo.setActualAmt(actualamt);
				feeCollectionList.add(feeNameVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs1!=null && !rs1.isClosed()){
					rs1.close();
				}
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
				}
				if(ps1!=null && !ps1.isClosed()){
					ps1.close();
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
				+ " Control in FeeCollectionDaoImpl: getFeePaidDetails: Ending");

		return feeCollectionList;
	}

	public String addScholorshipStudent(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudent : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_check=null;
		ResultSet rs_check=null;
		int count=0;
		int countcheck=0;
		String status=null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			ps_check=conn.prepareStatement("SELECT COUNT(*) FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");

			ps_check.setString(1, vo.getAdmissionNo());
			ps_check.setString(2, vo.getAcademicYear());
			rs_check=ps_check.executeQuery();
			if(rs_check.next()){
				countcheck=rs_check.getInt(1);
			}
			if(countcheck ==0){	
				for(int i=0;i<vo.getConcessionAmount().split(",").length;i++){

					ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year,isActive) VALUES(?,?,?,?,?,?,?,?)");
					ps_insertPlan.setString(1, vo.getAdmissionNo());
					ps_insertPlan.setString(2, vo.getClassId());
					ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
					ps_insertPlan.setString(4, vo.getFeecode().split(",")[i]);
					ps_insertPlan.setString(5, vo.getContype());
					ps_insertPlan.setString(6, vo.getConcessionAmount().split(",")[i]);
					ps_insertPlan.setString(7, vo.getAcademicYear());
					ps_insertPlan.setString(8, "Y");

					count=ps_insertPlan.executeUpdate();
					if(count>0){
						HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Insert",ps_insertPlan.toString(),userLoggingsVo);
						status="true";
					}
					else{
						status="false";
					}
				}

			}
			else{
				PreparedStatement pstmtd=conn.prepareStatement("DELETE FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
				pstmtd.setString(1, vo.getAdmissionNo());
				pstmtd.setString(2, vo.getAcademicYear());
				int abc=pstmtd.executeUpdate();
				if(abc > 0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Delete",pstmtd.toString(),userLoggingsVo);
				}
				pstmtd.close();
				for(int i=0;i<vo.getConcessionAmount().split(",").length;i++){

					ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year,isActive) VALUES(?,?,?,?,?,?,?,?)");
					ps_insertPlan.setString(1, vo.getAdmissionNo());
					ps_insertPlan.setString(2, vo.getClassId());
					ps_insertPlan.setString(3, vo.getTermcode().split(",")[i]);
					ps_insertPlan.setString(4, vo.getFeecode().split(",")[i]);
					ps_insertPlan.setString(5, vo.getContype());
					ps_insertPlan.setString(6, vo.getConcessionAmount().split(",")[i]);
					ps_insertPlan.setString(7, vo.getAcademicYear());
					ps_insertPlan.setString(8, "Y");
					count=ps_insertPlan.executeUpdate();
					if(count>0){
						HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Insert",ps_insertPlan.toString(),userLoggingsVo);
						status="true";
					}
					else{
						status="false";
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
				if(rs_check!=null && !rs_check.isClosed()){
					rs_check.close();
				}
				if(ps_check!=null && !ps_check.isClosed()){
					ps_check.close();
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
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudent: Ending");

		return status;
	}

	public String deleteScholorDetails(AddFeeVO vo, String button, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: deleteScholorDetails : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		int count=0;
		String status=null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0;i<vo.getGetDataArray().length;i++){
				ps_insertPlan=conn.prepareStatement("update campus_scholorship set isActive=?,remark=? WHERE admissionNo=? AND academic_year=?");
				ps_insertPlan.setString(1, vo.getStatus());
				ps_insertPlan.setString(2, vo.getRemark());
				ps_insertPlan.setString(3, vo.getGetDataArray()[i]);
				ps_insertPlan.setString(4, vo.getAccYearArray()[i]);
				count=ps_insertPlan.executeUpdate();

				if(count>0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession",button,ps_insertPlan.toString(),userLoggingsVo);
				}
			}
			if(count>0){
				status="true";
			}
			else{
				status="false";
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

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
				+ " Control in FeeCollectionDaoImpl: deleteScholorDetails: Ending");

		return status;
	}
	public List<StudentRegistrationVo> studentSearchbyadmissionNo(StudentRegistrationVo registrationVo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : studentSearchbyadmissionNo Starting");
		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmObj = conn.prepareStatement("SELECT DISTINCT student_admissionno_var,student_id_int FROM campus_student  WHERE student_admissionno_var LIKE ? AND locationId=? AND student_status_var='active' ");
			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, registrationVo.getLocationId());
			
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setAdmissionNo(rs.getString("student_admissionno_var"));
				studentRegistrationVo.setStudentId(rs.getString("student_id_int"));
				registrationList.add(studentRegistrationVo);

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
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
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
				+ " Control in FeeCollectionDaoImpl : studentSearchbyadmissionNo Ending");

		return registrationList;
	}


	public ArrayList<AddFeeVO> getDefaulterFeeList(String locId,String classId, String divId, String termId, String accId, UserLoggingsPojo cpojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getDefaulterFeeList : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		String stuid = null;
		String dbcolumn = null;
		try{
			String previous = HelperClass.getPreAcadamicYearId(cpojo);
			conn=JDBCConnection.getSeparateConnection(cpojo);
			psmt=conn.prepareStatement(FeeCollectionSqlUtils.getDefaulterFeeList);

			psmt.setString(1, accId);
			psmt.setString(2, termId);
			psmt.setString(3, locId);
			psmt.setString(4, classId);
			psmt.setString(5, divId);
			psmt.setString(6, termId);
			psmt.setString(7, accId);
			rs=psmt.executeQuery();
			while(rs.next()){
				
				double dueAmt=0.0;
				
				stuid = rs.getString("student_id_int");
				
				Boolean stutype = HelperClass.getStudentType(previous, stuid, cpojo);
				if(stutype){
					dbcolumn = "feeAmount";
				}else{
					dbcolumn = "newstufeeAmount";
				}
				
				PreparedStatement psmt1 = conn.prepareStatement("SELECT SUM("+dbcolumn+") dueAmt FROM `campus_fee_setupdetails` WHERE `feeSettingCode` IN (SELECT `feeSettingcode` FROM `campus_fee_setup` WHERE `ClassCode`=? AND `AccyearCode`=? AND `locationId`=? AND `Termcode`=?)");
				psmt1.setString(1, rs.getString("classdetail_id_int"));
				psmt1.setString(2, rs.getString("fms_acadamicyear_id_int"));
				psmt1.setString(3, rs.getString("locationId"));
				psmt1.setString(4, rs.getString("termid"));
				ResultSet rs1 = psmt1.executeQuery();	
				while(rs1.next()){
					dueAmt=rs1.getDouble("dueAmt");
				}
				rs1.close();
				psmt1.close();
				AddFeeVO vo = new AddFeeVO();
				vo.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setDivisionName(rs.getString("classsection_name_var"));
				vo.setTermName(rs.getString("termname"));
				vo.setDueAmt(dueAmt);
				list.add(vo);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getDefaulterFeeList: Ending");
		return list;
	}

	public ArrayList<AddFeeVO> getAdvanceOrBalanceForTransportFee(String stuId, String[] termId,String acyId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null,rs2 =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			AddFeeVO vo = new AddFeeVO();
			double dueAmt=0.0;
			double advance=0.0;
			String value="",balencevalue="";
			double concession=0.0,concession1=0.0,checkAdvance=0.0,balence=0.0;
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt=conn.prepareStatement("SELECT * FROM campus_tranport_fee_collection_details WHERE admissionNo=? ORDER BY createdtime DESC LIMIT 1");
			psmt.setString(1, stuId);
			rs=psmt.executeQuery();
			
			if(rs.next()){
			
		    PreparedStatement psmt3=conn.prepareStatement("SELECT * FROM campus_tranport_fee_collection_details WHERE admissionNo=? ORDER BY createdtime DESC LIMIT 1");
			psmt3.setString(1, stuId);
			ResultSet rs3=psmt3.executeQuery();
				
			while(rs3.next()){
				    PreparedStatement psmt1 = conn.prepareStatement("SELECT * FROM campus_transport_fees_payments WHERE receiptno=?");
					psmt1.setString(1, rs3.getString("reciept_no"));
					ResultSet rs1 = psmt1.executeQuery();	 
					while(rs1.next()){
						dueAmt=rs1.getDouble("balance");
						advance=rs1.getDouble("advance");
					}
					rs1.close();
					psmt1.close();
				 
				vo.setAdvanceAmt(advance);
				vo.setDueAmt(dueAmt);
				list.add(vo);
			}
			rs3.close();
			psmt3.close();
		}
		else{
			String PrevAcaYearId=HelperClass.getPreAcadamicYearId(userLoggingsVo);
			
			 PreparedStatement psmt4 = conn.prepareStatement("SELECT ctfp.balance,CASE WHEN ctfp.advance IS NULL THEN '' WHEN ctfp.advance='' THEN '' ELSE ctfp.advance END advance FROM campus_tranport_fee_collection_details ctfcd JOIN campus_transport_fees_payments ctfp ON ctfp.receiptno=ctfcd.reciept_no WHERE ctfcd.admissionNo=? AND ctfcd.accYear=? AND ctfcd.reciept_no=(SELECT MAX(reciept_no) FROM campus_tranport_fee_collection_details WHERE admissionNo=? AND accYear=?)");
			 psmt4.setString(1, stuId);
			 psmt4.setString(2, PrevAcaYearId);
			 psmt4.setString(3, stuId);
			 psmt4.setString(4, PrevAcaYearId);
			 ResultSet rs4 = psmt4.executeQuery();
			 while(rs4.next()){
				 checkAdvance=rs4.getDouble("advance");
				 value=rs4.getString("advance");
				 balencevalue=rs4.getString("balance");
				 balence=rs4.getDouble("balance");
				}
			 rs4.close();
			 psmt4.close();
			 if(checkAdvance<0 || value.equalsIgnoreCase("")){
				 vo.setAdvanceAmt(advance);
			 }else{
				 vo.setAdvanceAmt(checkAdvance); 
			 }
			 
			 if(balence<0 || balencevalue.equalsIgnoreCase("")){
				 vo.setDueAmt(dueAmt);
			 }else{
				 vo.setDueAmt(balence); 
			 }
			 
			list.add(vo);
		 }
			PreparedStatement psmt2 = conn.prepareStatement("SELECT concession FROM campus_transport_concessiondetails WHERE studentIdNo=?  AND academic_year=? AND termcode=?");
		    for(int i=0;i<termId.length;i++){
		    	psmt2.setString(1, stuId);
		    	psmt2.setString(2, acyId);
		    	psmt2.setString(3, termId[i]);
		    	rs2 = psmt2.executeQuery();
		    	while(rs2.next()){
		    		concession1=rs2.getDouble("concession");
		    		concession=concession+concession1;
				}
		    	rs2.close();
				psmt2.close();
				
				vo.setConcession(String.valueOf(concession));
				list.add(vo);
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee: Ending");
		return list;
	}
		

	public ArrayList<AddFeeVO> getfeeCancellationList(String studentId,String accYear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt=conn.prepareStatement("SELECT cfc.*,CONCAT(cs.student_fname_var, ' ',cs.student_lname_var) studentName,cs.student_admissionno_var,cc.classdetails_name_var,cft.termname FROM campus_fee_collection cfc JOIN campus_student cs ON cfc.admissionNo=cs.student_id_int JOIN campus_student_classdetails csc ON (cfc.admissionNo=csc.student_id_int AND cfc.accYear=csc.fms_acadamicyear_id_int) JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_fee_termdetails cft ON cfc.termcode=cft.termid WHERE cfc.admissionNo=? AND cfc.accYear=? ");
			psmt.setString(1, studentId);
			psmt.setString(2, accYear);
			rs=psmt.executeQuery();

			while(rs.next()){		
				AddFeeVO vo = new AddFeeVO();
				vo.setId(rs.getString("feeCollectionCode"));
				vo.setAdmissionNo(rs.getString("student_admissionno_var"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setTermName(rs.getString("termname"));
				vo.setPaidAmt(rs.getDouble("amount_paid"));

				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: getfeeCancellationList: Ending");
		return list;
	}

	public String cancelFee(String feecode,String log_audit_session, UserLoggingsPojo userLoggingsVo, String accYear, String user) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: cancelFee : Starting");
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		String status = null;
		try{
			String[] feeCode=feecode.split(",");
			for(int i=0; i<feeCode.length; i++){
			String studentId=null;
			String termcode=null;

			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);

			conn.setAutoCommit(false);
			psmt=conn.prepareStatement("SELECT admissionNo,accYear,termcode FROM campus_fee_collection WHERE feeCollectionCode=?");
			psmt.setString(1,feeCode[i]);
			rs=psmt.executeQuery();
			while(rs.next()){
				studentId=rs.getString("admissionNo");
				accYear=rs.getString("accYear");
				termcode=rs.getString("termcode");
			}

			PreparedStatement insert=conn.prepareStatement("SELECT `admissionNo`,`accYear`,`termcode`,`chln_no`,`paidDate`,`totalamount`,`actualamount`,`amount_paid`,`balance_amount`,`advance_amount`,`fineAmount` FROM `campus_fee_collection` WHERE `feeCollectionCode`=?");
			insert.setString(1, feeCode[i]);
			ResultSet inrs=insert.executeQuery();
			PreparedStatement insertcancel=null;
			while(inrs.next()){
				insertcancel=conn.prepareStatement("INSERT INTO `campus_feecancel_details`(`stu_id`,`accy_id`,`term_id`,`chln_no`,`paid_date`,`total_amt`,`actual_amt`,`paid_amt`,`bal_amt`,`advance_amt`,`fine_amt`,`created_date`,`created_by`) VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW(),?)");
				insertcancel.setString(1, inrs.getString("admissionNo"));
				insertcancel.setString(2, inrs.getString("accYear"));
				insertcancel.setString(3, inrs.getString("termcode"));
				insertcancel.setString(4, inrs.getString("chln_no"));
				insertcancel.setString(5, inrs.getString("paidDate"));
				insertcancel.setString(6, inrs.getString("totalamount"));
				insertcancel.setString(7, inrs.getString("actualamount"));
				insertcancel.setString(8, inrs.getString("amount_paid"));
				insertcancel.setString(9, inrs.getString("balance_amount"));
				insertcancel.setString(10, inrs.getString("advance_amount"));
				insertcancel.setString(11, inrs.getString("fineAmount"));
				insertcancel.setString(12, user);
				int cnt=insertcancel.executeUpdate();
				if(cnt>0){
					HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","insert",insertcancel.toString(),userLoggingsVo);
				}
			}
			insertcancel.close();
			insert.close();
			inrs.close();
			
			
			PreparedStatement delete=conn.prepareStatement("DELETE FROM campus_feepaid_details WHERE receiptNo IN(SELECT receipt_no FROM campus_fee_reciept WHERE feeCollectionCode=?)");
			delete.setString(1,feeCode[i]);
			int as=delete.executeUpdate();
			if(as>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}
			delete.close();
			
			 delete=conn.prepareStatement("DELETE FROM campus_fee_reciept WHERE feeCollectionCode=?");
			delete.setString(1,feeCode[i]);
			int c=delete.executeUpdate();
			if(c>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}
			delete.close();

			delete=conn.prepareStatement("DELETE FROM campus_fee_collectiondetails WHERE feeCollectionCode=?");
			delete.setString(1,feeCode[i]);
			int b=delete.executeUpdate();
			if(b>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}
			delete.close();

			delete=conn.prepareStatement("DELETE FROM campus_fee_collection WHERE feeCollectionCode=?");
			delete.setString(1,feeCode[i]);
			int a=delete.executeUpdate();
			if(a>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}
			delete.close();

			delete=conn.prepareStatement("DELETE FROM campus_fee_indetail WHERE admissionNo=? AND term_id=? AND accYear=?");
			delete.setString(1, studentId);
			delete.setString(2, termcode);
			delete.setString(3, accYear);
			int d=delete.executeUpdate();
			if(d>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}
			delete.close();

			delete=conn.prepareStatement("DELETE FROM campus_fee_collection_details WHERE admissionNo=? AND termcode=? AND accYear=?");
			delete.setString(1, studentId);
			delete.setString(2, termcode);
			delete.setString(3, accYear);
			int e=delete.executeUpdate();
			if(e>0){
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Cancellation","Delete",delete.toString(),userLoggingsVo);
			}

			if(a > 0 && b>0 && c>0 && d>0 && e>0){
				conn.commit();
				status="true";
			}else{
				conn.rollback();
			}
			delete.close();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
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
				+ " Control in FeeCollectionDaoImpl: cancelFee: Ending");
		return status;
	}

	public String addScholorshipStudentForEqual(StudentConcessionVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudentForEqual : Starting");
		Connection conn = null;
		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_check=null;
		ResultSet rs_check=null;
		int count=0;
		int countcheck=0;
		String status=null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			ps_check=conn.prepareStatement("SELECT COUNT(*) FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");

			ps_check.setString(1, vo.getAdmissionNo());
			ps_check.setString(2, vo.getAcademicYear());
			rs_check=ps_check.executeQuery();
			if(rs_check.next()){
				countcheck=rs_check.getInt(1);
			}
			if(countcheck ==0){	

				ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
				ps_insertPlan.setString(1, vo.getAdmissionNo());
				ps_insertPlan.setString(2, vo.getClassId());
				ps_insertPlan.setString(3, vo.getTermcode());
				ps_insertPlan.setString(4, vo.getFeecode());
				ps_insertPlan.setString(5, vo.getContype());
				ps_insertPlan.setString(6, vo.getConcessionAmount());
				ps_insertPlan.setString(7, vo.getAcademicYear());
				count=ps_insertPlan.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Insert",ps_insertPlan.toString(),userLoggingsVo);
					status="true";
				}
				else{
					status="false";
				}


			}
			else{
				PreparedStatement pstmtd=conn.prepareStatement("DELETE FROM campus_scholorship WHERE admissionNo=? AND academic_year=?");
				pstmtd.setString(1, vo.getAdmissionNo());
				pstmtd.setString(2, vo.getAcademicYear());
				int abc=pstmtd.executeUpdate();
				if(abc>0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Delete",pstmtd.toString(),userLoggingsVo);
				}
				pstmtd.close();


				ps_insertPlan=conn.prepareStatement("INSERT INTO campus_scholorship (admissionNo,classId,termcode,feecode,concessionType,concession,academic_year) VALUES(?,?,?,?,?,?,?)");
				ps_insertPlan.setString(1, vo.getAdmissionNo());
				ps_insertPlan.setString(2, vo.getClassId());
				ps_insertPlan.setString(3, vo.getTermcode());
				ps_insertPlan.setString(4, vo.getFeecode());
				ps_insertPlan.setString(5, vo.getContype());
				ps_insertPlan.setString(6, vo.getConcessionAmount());
				ps_insertPlan.setString(7, vo.getAcademicYear());
				count=ps_insertPlan.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Concession","Insert",ps_insertPlan.toString(),userLoggingsVo);
					status="true";
				}
				else{
					status="false";
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
				if(rs_check!=null && !rs_check.isClosed()){
					rs_check.close();
				}

				if(ps_check!=null && ! ps_check.isClosed()){
					ps_check.close();
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
				+ " Control in FeeCollectionDaoImpl: addScholorshipStudentForEqual: Ending");

		return status;
	}

	public List<StudentConcessionVo> getstudentbyaccy(StudentConcessionVo vo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getstudentbyaccy : Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentConcessionVo obj = new StudentConcessionVo();
		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();

		try {

			conn  = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STUDENT_DETAILS_BY_ACY);
			pstmt.setString(1, vo.getAcademicYear());
			pstmt.setString(2, vo.getAdmissionNo());
			rs = pstmt.executeQuery();
			if(rs.next()){
				obj.setStudent(rs.getString("student"));
				obj.setClass_section(rs.getString("classsec"));
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setSpecialization(rs.getString("specilization"));
				obj.setStatus("found");
				list.add(obj);
			}else{
				obj.setStatus("notfound");
				list.add(obj);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
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
				+ " Control in FeeCollectionDaoImpl: getstudentbyaccy: Ending");

		return list;
	}

	public List<StudentRegistrationVo> studentSearchbyStudentID(StudentRegistrationVo registrationVo, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionDaoImpl : studentSearchbyStudentID Starting");

		String searchTerm = registrationVo.getSearchTerm() + "%";
		List<StudentRegistrationVo> registrationList = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pstmObj = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmObj = conn.prepareStatement("SELECT DISTINCT st.student_admissionno_var FROM campus_student st  JOIN campus_student_transportdetails cst ON cst.student_id_int=st.student_id_int where st.student_admissionno_var LIKE ? AND st.locationId=? AND st.student_status_var='active' AND cst.isTransport='Y' ORDER BY st.student_admissionno_var");
			pstmObj.setString(1, searchTerm);
			pstmObj.setString(2, registrationVo.getLocationId());
			rs = pstmObj.executeQuery();

			while (rs.next()) {
				StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
				studentRegistrationVo.setStudentIdNo(rs.getString("student_admissionno_var"));
				registrationList.add(studentRegistrationVo);
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
				if (pstmObj != null && (!pstmObj.isClosed())) {
					pstmObj.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in FeeCollectionDaoImpl : studentSearchbyStudentID Ending");

		return registrationList;
	}

	public List<StudentConcessionVo> getstudenttransportbyaccy(StudentConcessionVo registrationVo,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Starting");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		StudentConcessionVo stuReg = new StudentConcessionVo();
		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement("SELECT DISTINCT cs.locationId,st.student_admissionno_var,st.student_id_int,st.student_fname_var,st.student_lname_var,cc.classdetail_id_int,cc.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var,csc.specilization,cst.`isTransport`,cst.`StageId`,cfs.`stage_name`,cst.`route`,tr.`RouteName` FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int=csc.student_id_int JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection cs ON (csc.classsection_id_int=cs.classsection_id_int AND csc.locationId=cs.locationId) LEFT JOIN `campus_student_transportdetails` cst ON (cst.student_id_int=csc.student_id_int AND cst.`fms_acadamicyear_id_int`=csc.fms_acadamicyear_id_int) LEFT JOIN `campus_fee_stage` cfs ON cfs.`stage_id`=cst.`StageId` LEFT JOIN `transport_route_stage_mapping` trsm ON trsm.`RouteCode`=cst.`route` AND trsm.`StageCode`=cfs.`stage_id` LEFT JOIN `transport_route` tr ON tr.`RouteCode`=cst.`route` WHERE st.student_admissionno_var = ? AND csc.fms_acadamicyear_id_int=? AND cst.locationId=?");
			pst.setString(1, registrationVo.getAdmissionNo());
			pst.setString(2, registrationVo.getAcademicYear());
			pst.setString(3, registrationVo.getLocID());
			rs = pst.executeQuery();
			if (rs.next()) {
				stuReg.setAdmissionNo(rs.getString("student_admissionno_var"));
				stuReg.setStudentId(rs.getString("student_id_int"));
				stuReg.setStudent(rs.getString("student_fname_var") + " " + rs.getString("student_lname_var"));
				stuReg.setClassId(rs.getString("classdetail_id_int"));
				stuReg.setClass_section(rs.getString("classdetails_name_var") + " - " + rs.getString("classsection_name_var"));
				stuReg.setSpecialization(rs.getString("specilization"));
				stuReg.setLocID(rs.getString("locationId"));
				stuReg.setIsTransport(rs.getString("isTransport"));
				stuReg.setStageId(rs.getString("StageId"));
				stuReg.setStage_name(rs.getString("stage_name"));
				stuReg.setRoute(rs.getString("route"));
				stuReg.setRouteName(rs.getString("RouteName"));
				stuReg.setStatus("found");
				list.add(stuReg);
			}else{
				stuReg.setStatus("notfound");
				list.add(stuReg);
			}

		} catch (Exception e) {
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Ending");

		return list;
	}

	public ArrayList<StudentConcessionVo> getConcessions(String locId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getConcessions Starting");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT `concessionid`,`locId`,`concessionname`,`concessionType`,`isApplicableFor`,`concessionBy` FROM `campus_fee_concessiondetails` WHERE (`isApplicableFor` = 'SF' OR SUBSTRING(isApplicableFor,4,4) = 'SF') AND isActive='Y' AND locId = ?");
			pst.setString(1, locId);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stuReg = new StudentConcessionVo();
				stuReg.setConcessionId(rs.getString("concessionid"));
				stuReg.setConcessionName(rs.getString("concessionname"));
				stuReg.setConcessionType(rs.getString("concessionType"));
				stuReg.setApplicableConcession(rs.getString("isApplicableFor"));
				stuReg.setConcessionBy(rs.getString("concessionBy"));

				list.add(stuReg);
			}

		} catch (Exception e) {
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getConcessions Ending");

		return list;
	}

	public List<StudentConcessionVo> getTermFees(String loc_id, String acy_id, String studentid, UserLoggingsPojo userLoggingsVo, String class_id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermFees Starting");

		PreparedStatement pst = null, pst1 = null;
		ResultSet rs = null, rst = null;
		Connection conn = null;
		int sno = 0;
		String dbcolumn = null;

		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		try {
			
			String previous = HelperClass.getPreAcadamicYearId(userLoggingsVo);
			boolean stutype = HelperClass.getStudentType(previous, studentid, userLoggingsVo);

			if(stutype){
				dbcolumn = "feeAmount";
			}else{
				dbcolumn = "newstufeeAmount";
			}

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT fee.`Termcode`,fee.`ClassCode`,tm.`termname`,tm.`ternOrder`,tm.`termid`,SUM(fsd."+dbcolumn+") as termAmount FROM `campus_fee_setup` fee JOIN `campus_fee_termdetails` tm ON tm.`termid` = fee.`Termcode` JOIN `campus_fee_setupdetails` fsd ON fsd.`feeSettingCode` = fee.`feeSettingcode` WHERE tm.`isActive`='Y' AND fee.`AccyearCode` = ? AND fee.`locationId` = ? AND fee.`ClassCode`= ? GROUP BY (tm.`termid`)");
			pst.setString(1, acy_id);
			pst.setString(2, loc_id);
			pst.setString(3, class_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stuReg = new StudentConcessionVo();
				sno++;
				
				pst1 = conn.prepareStatement("SELECT COUNT(is_paid) FROM campus_fee_collection WHERE `is_paid`='Y' AND `admissionNo`=? AND `accYear`=? AND termcode=?");
				pst1.setString(1, studentid);
				pst1.setString(2, acy_id);
				pst1.setString(3, rs.getString("Termcode"));
				rst = pst1.executeQuery();

				while(rst.next()){

					if(rst.getInt(1) == 1){
						stuReg.setStatus("Paid");
					}else{
						stuReg.setStatus("Not Paid");
					}

				}

				stuReg.setSno(sno);
				stuReg.setTermcode(rs.getString("Termcode"));
				stuReg.setTerm(rs.getString("termname"));
				stuReg.setTermAmount(rs.getInt("termAmount"));
				stuReg.setTermId(rs.getString("termid"));

				list.add(stuReg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermFees Ending");

		return list;
	}

	public List<StudentConcessionVo> getTermWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermWiseAllFees Starting");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String dbcolumn = null;

		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
		try {
			
			String previous = HelperClass.getPreAcadamicYearId(userLoggingsVo);
			boolean stutype = HelperClass.getStudentType(previous, feevo.getStudentId(), userLoggingsVo);

			if(stutype){
				dbcolumn = "feeAmount";
			}else{
				dbcolumn = "newstufeeAmount";
			}
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT fs.`feeSettingcode`,fsd.`feecode`,fsd."+dbcolumn+" as feesAmount,fm.`FeeName` FROM `campus_fee_setup` fs JOIN `campus_fee_setupdetails` fsd ON fsd.`feeSettingCode` = fs.`feeSettingcode` JOIN `campus_fee_master` fm ON fm.`FeeCode` = fsd.`feecode` WHERE fsd."+dbcolumn+" > 0 AND fs.`Termcode`=? AND fs.`AccyearCode`=? AND fs.`locationId`=? AND fs.ClassCode=?");
			pst.setString(1, feevo.getTermId());
			pst.setString(2, feevo.getAcademicId());
			pst.setString(3, feevo.getLocationId());
			pst.setString(4, feevo.getClassId());
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stufee = new StudentConcessionVo();

				stufee.setFeecode(rs.getString("feecode"));
				stufee.setFeeAmount(rs.getInt("feesAmount"));
				stufee.setFeename(rs.getString("FeeName"));
				list.add(stufee);

			}

		} catch (Exception e) {
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



		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermWiseAllFees Ending");

		return list;
	}

	public String saveFeeConcessionRequest(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : saveFeeConcessionRequest Starting");

		PreparedStatement pst = null, pst1 = null, pst2 = null;
		ResultSet rs1 = null;
		Connection conn = null;
		String status = null;
		int count = 0;
		int result = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);

			for(int i=0;i<feevo.getFeecode().split(",").length;i++) {

				if(feevo.getHiddencontype().equalsIgnoreCase("1")){
					pst2 = conn.prepareStatement("DELETE FROM campus_scholorship WHERE studentId=? AND admissionNo=? AND classId=? AND termcode=? AND feecode=? AND concessionType=? AND concessionId=? AND academic_year=?");
					pst2.setString(1, feevo.getStudentId());
					pst2.setString(2, feevo.getAdmissionNo());
					pst2.setString(3, feevo.getClassId());
					pst2.setString(4, feevo.getHiddentrmcode());
					pst2.setString(5, feevo.getHiddenfeecode());
					pst2.setString(6, feevo.getHiddencontype());
					pst2.setString(7, feevo.getHiddenConId());
					pst2.setString(8, feevo.getHiddenacyId());
					pst2.executeUpdate();
				}

				pst1 = conn.prepareStatement("SELECT COUNT(feecode) FROM campus_scholorship WHERE studentId=? AND admissionNo=? AND classId=? AND termcode=? AND feecode=? AND concessionType=? AND concessionId=? AND academic_year=?");
				pst1.setString(1, feevo.getStudentId());
				pst1.setString(2, feevo.getAdmissionNo());
				pst1.setString(3, feevo.getClassId());
				pst1.setString(4, feevo.getTermcode().split(",")[i]);
				pst1.setString(5, feevo.getFeecode().split(",")[i]);
				pst1.setString(6, feevo.getConcessionId());
				pst1.setString(7, feevo.getConcessionType());
				pst1.setString(8, feevo.getAcademicId());
				rs1 = pst1.executeQuery();
				while(rs1.next()){
					result = rs1.getInt(1);
				}
				
				if(result > 0){
					pst = conn.prepareStatement("UPDATE campus_scholorship SET concessionPercent=?,concession=? WHERE studentId=? AND admissionNo=? AND classId=? AND termcode=? AND feecode=? AND concessionId=? AND concessionType=? AND academic_year=?");

					if(feevo.getConcessionPercent().equalsIgnoreCase(null) || feevo.getConcessionPercent().equalsIgnoreCase("")){
						pst.setInt(1, '0');
					}else{
						pst.setString(1, feevo.getConcessionPercent().split(",")[i]);
					}
					pst.setString(2, feevo.getConcessionAmount().split(",")[i]);
					pst.setString(3, feevo.getStudentId());
					pst.setString(4, feevo.getAdmissionNo());
					pst.setString(5, feevo.getClassId());
					pst.setString(6, feevo.getTermcode().split(",")[i]);
					pst.setString(7, feevo.getFeecode().split(",")[i]);
					pst.setString(8, feevo.getConcessionType());
					pst.setString(9, feevo.getConcessionId());
					pst.setString(10, feevo.getAcademicId());
					count = pst.executeUpdate();
					if(count>0){
						HelperClass.recordLog_Activity(feevo.getLog_audit_session(),"Fee","Fee Request/Cancel","Update",pst.toString(),userLoggingsVo);
					}

				}else{
					pst = conn.prepareStatement("INSERT INTO `campus_scholorship` (`studentId`,`admissionNo`,`classId`,`termcode`,`feecode`,concessionId,`concessionType`,`concession`,`academic_year`,`concessionPercent`) VALUES (?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, feevo.getStudentId());
					pst.setString(2, feevo.getAdmissionNo());
					pst.setString(3, feevo.getClassId());
					pst.setString(4, feevo.getTermcode().split(",")[i]);
					pst.setString(5, feevo.getFeecode().split(",")[i]);
					pst.setString(6, feevo.getConcessionType());
					pst.setString(7, feevo.getConcessionId());
					pst.setString(8, feevo.getConcessionAmount().split(",")[i]);
					pst.setString(9, feevo.getAcademicId());

					if(feevo.getConcessionPercent().equalsIgnoreCase(null) || feevo.getConcessionPercent().equalsIgnoreCase("")){
						pst.setInt(10, '0');
					}else{
						pst.setString(10, feevo.getConcessionPercent().split(",")[i]);
					}
					count = pst.executeUpdate();
					if(count>0){
						
						/*PreparedStatement pmst=conn.prepareStatement("UPDATE campus_student_classdetails SET isConcession='Y' WHERE student_id_int=? AND fms_acadamicyear_id_int=?");
						pmst.setString(1, feevo.getStudentId());
						pmst.setString(2, feevo.getAcademicId());
						pmst.executeUpdate();
						pmst.close();*/
						HelperClass.recordLog_Activity(feevo.getLog_audit_session(),"Fee","Fee Request/Cancel","Insert",pst.toString(),userLoggingsVo);
					}
				}
			}

			if(count > 0) {
				conn.commit();
				status = "success";
			}else {
				conn.rollback();
				status = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : saveFeeConcessionRequest Ending");
		
		return status;
	}

	public List<StudentConcessionVo> getRequestedFees(StudentConcessionVo convo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getRequestedFees Starting");

		PreparedStatement pst = null, pst1 = null;
		ResultSet rs = null, rs1 = null;
		Connection conn = null;
		int sno = 0;
		String dbcolumn = null;
		
		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();

		try {
			
			String previous = HelperClass.getPreAcadamicYearId(userLoggingsVo);
			boolean stutype = HelperClass.getStudentType(previous, convo.getStudentId(), userLoggingsVo);

			if(stutype){
				dbcolumn = "feeAmount";
			}else{
				dbcolumn = "newstufeeAmount";
			}
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT DISTINCT stu.student_id_int,sc.`concessionId`,sc.`concession`,sc.`isActive`,cd.`concessionname`,cd.`concessionType`,ct.`concession_name`,sc.`termcode`,sc.`feecode`,cd.`concessionBy`,sc.`concessionPercent` FROM `campus_scholorship` sc JOIN `campus_student_classdetails` stu ON stu.`student_id_int` = sc.`studentId` JOIN `campus_fee_concessiondetails` cd ON cd.`concessionid` = sc.`concessionId` JOIN `campus_concession_types`ct ON ct.`contype_id` = cd.`concessionType` WHERE sc.`studentId`=? AND stu.`locationId`=? AND sc.`academic_year`=? AND sc.`classId`=?");
			pst.setString(1, convo.getStudentId());
			pst.setString(2, convo.getLocationId());
			pst.setString(3, convo.getAcademicId());
			pst.setString(4, convo.getClassId());
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stufee = new StudentConcessionVo();
				sno++;
				stufee.setSno(sno);
				stufee.setTermcode(rs.getString("termcode"));
				stufee.setConcessionType(rs.getString("concessionType"));
				stufee.setConcessionAmount(rs.getString("concession"));
				stufee.setConcessionName(rs.getString("concessionname"));
				stufee.setConcessionId(rs.getString("concessionId"));
				stufee.setConcessionStyle(rs.getString("concessionBy"));
				stufee.setConcessionPercent(rs.getString("concessionPercent"));
				
				if(!rs.getString("feecode").equalsIgnoreCase("all") && !rs.getString("termcode").equalsIgnoreCase("all")){
					pst1 = conn.prepareStatement("SELECT fs.`feeSettingcode`,fsd.`feecode`,fsd."+dbcolumn+" AS feesAmount,fm.`FeeName`,trm.`termid`,trm.`termname` FROM `campus_fee_setup` fs JOIN `campus_fee_setupdetails` fsd ON fsd.`feeSettingCode` = fs.`feeSettingcode` JOIN `campus_fee_master` fm ON fm.`FeeCode` = fsd.`feecode` JOIN `campus_fee_termdetails` trm ON trm.`termid` = fs.`Termcode` WHERE fs.`Termcode`=? AND fs.`AccyearCode`=? AND fs.`locationId`=? AND fsd.`feecode`=? AND fs.ClassCode=?");
					pst1.setString(1, rs.getString("termcode"));
					pst1.setString(2, convo.getAcademicId());
					pst1.setString(3, convo.getLocationId());
					pst1.setString(4, rs.getString("feecode"));
					pst1.setString(5, convo.getClassId());
					rs1 = pst1.executeQuery();
					while(rs1.next()){
						stufee.setTerm(rs1.getString("termname"));
						stufee.setFeename(rs1.getString("FeeName"));
						stufee.setFeeAmount(rs1.getInt("feesAmount"));
						stufee.setFeecode(rs1.getString("feecode"));
					}
				}
				else{
					stufee.setFeecode("all");
					stufee.setTerm("all");
				}
				list.add(stufee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getRequestedFees Ending");
		
		return list;
	}

	public String getClassWiseAllFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getClassWiseAllFees Starting");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String amount = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement("SELECT SUM(fsd.`feeAmount`) as Amount FROM `campus_fee_setup` fs JOIN `campus_fee_setupdetails` fsd ON fsd.`feeSettingCode` = fs.`feeSettingcode` JOIN `campus_fee_master` fm ON fm.`FeeCode` = fsd.`feecode` WHERE fs.`ClassCode`=? AND fs.`AccyearCode`=? AND fs.`locationId`=?");
			pst.setString(1, feevo.getClassId());
			pst.setString(2, feevo.getAcademicId());
			pst.setString(3, feevo.getLocationId());
			rs = pst.executeQuery();
			while (rs.next()) {
				amount = rs.getString("Amount");
			}

		} catch (Exception e) {
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getClassWiseAllFees Ending");

		return amount;
	}

	@Override
	public ArrayList<FeeCollectionVo> getSchoolFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Starting");

		PreparedStatement pst = null,pst1 = null,pst2 = null,pst3 = null;
		ResultSet rs = null,rs1 = null,rs2 = null,rs3 = null;
		Connection conn = null;
		ArrayList<FeeCollectionVo> list = new ArrayList<FeeCollectionVo>();
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pst = conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` cft JOIN `campus_acadamicyear` ca ON cft.`accyear`=ca.`acadamic_id` JOIN `campus_location` cl ON cft.`locationId`=cl.`Location_Id` WHERE cft.locationId=? AND cft.`accyear`=? AND ca.`isActive`='Y' AND cl.`isActive`='Y'");
			pst.setString(1, feeCodeDetails.split(",")[2]);
			pst.setString(2, feeCodeDetails.split(",")[1]);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				FeeCollectionVo vo=new FeeCollectionVo();
				pst2 = conn.prepareStatement("SELECT `feeSettingcode` FROM `campus_fee_setup` WHERE `ClassCode`=? AND `AccyearCode`=? AND `Termcode`=? AND `locationId`=?");
				pst2.setString(1,feeCodeDetails.split(",")[3]);
				pst2.setString(2,feeCodeDetails.split(",")[1]);
				pst2.setString(3,rs.getString("termid"));
				pst2.setString(4,feeCodeDetails.split(",")[2]);
				
				rs2 = pst2.executeQuery();
				int totalAmt=0;
				while (rs2.next()) {
					//pst3 = conn.prepareStatement("SELECT SUM(`feeAmount`) as fee FROM `campus_fee_setupdetails` WHERE `feeSettingCode`=?");
					pst3 = conn.prepareStatement("SELECT SUM(`newstufeeAmount`) as fee FROM `campus_fee_setupdetails` WHERE `feeSettingCode`=?");
					pst3.setString(1,rs2.getString("feeSettingcode"));
					
					rs3= pst3.executeQuery();
					while (rs3.next()) {
						totalAmt=totalAmt+rs3.getInt("fee");
					}
				}
				vo.setTot_actual_amt(totalAmt);
				int count=0;
				vo.setTerm(rs.getString("termname"));
				pst1 = conn.prepareStatement("SELECT `actualamount`,`balance_amount`,`amount_paid`,`fineAmount`,`due_amount`,`advance_amount` FROM `campus_fee_collection` WHERE `admissionNo`=? AND `accYear`=? AND `termcode`=?");
				pst1.setString(1,feeCodeDetails.split(",")[0]);
				pst1.setString(2,feeCodeDetails.split(",")[1]);
				pst1.setString(3,rs.getString("termid"));
				
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					count++;
					//vo.setTot_actual_amt(rs1.getDouble("actualamount"));
					vo.setOpening_balance(rs1.getDouble("balance_amount"));
					vo.setAmount_paid_so_far(rs1.getDouble("amount_paid"));
					vo.setFineAmount(rs1.getDouble("fineAmount"));
					vo.setDuesCarry(rs1.getDouble("due_amount"));
					vo.setAdvanceCarry(rs1.getDouble("advance_amount"));
					if(rs1.getDouble("due_amount")==0){
						vo.setStatus("Paid");
					}else{
						vo.setStatus("Pending");
					}
				}
				if(count==0){
					vo.setStatus("Not Paid");
				}
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationDaoImpl : getTransportAvailableStudent Ending");

		return  list;
}
	
	@Override
	public ArrayList<FeeCollectionVo> getTransportFeeCollectionAmount(String feeCodeDetails,UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount : Starting");
		PreparedStatement ps_feeInformation=null;
		ResultSet rs_getfeeInformation=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		
		PreparedStatement ps_collection_count= null;
		ResultSet rs_collection_count=null;
		PreparedStatement ps_feelist= null;
		ResultSet rs_feelist=null;
		PreparedStatement ps_transporttermStartMont= null;
		ResultSet rs_transporttermStartMont=null;
		
		/*double amount_paid_so_far =0.00;
		double opening_balance =0.00;*/
		String stageid=null;
		
		ArrayList<FeeCollectionVo> list=new ArrayList<FeeCollectionVo>();
		
		ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
		try {
			
			String[] feeDatails=feeCodeDetails.split(",");
			
			String addmissionno=feeDatails[0];
			
			String accyearId=feeDatails[1];
			String locId=feeDatails[2];
			String termId=null;
			String classId=null;
			String specCode=null;
			String termname=null;
			int start_month=0;
			int end_month=0;
			int month=0;
			int startmonth=0;
			int noOfMonth=0;
			conn = JDBCConnection.getSeparateConnection(pojo);
		
			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.GET_TRANSPORT_FEECOLLECTION_DEDDER);
			 
			ps_insertPlan.setString(1,addmissionno);
			ps_insertPlan.setString(2,locId);
			ps_insertPlan.setString(3,accyearId);
			
			rs=ps_insertPlan.executeQuery();
			while(rs.next()){
				FeeCollectionVo collectionVo=new FeeCollectionVo();
				collectionVo.setStudentid(addmissionno);
				collectionVo.setAddmissionno(rs.getString("student_admissionno_var"));
				collectionVo.setStudentname(rs.getString("studentname"));
				collectionVo.setClassname(rs.getString("classdetails_name_var"));
				specCode=rs.getString("specilization");
				classId=rs.getString("classdetail_id_int");
				collectionVo.setClassId(classId);
				collectionVo.setSpecialization(specCode);
				collectionVo.setSectionname(rs.getString("classsection_name_var"));
				collectionVo.setAccYear(accyearId);
				collectionVo.setAccYearname(rs.getString("acadamic_year"));
				stageid=rs.getString("StageId");
				if(rs.getString("srtm")!=null)
				start_month=Integer.parseInt(rs.getString("srtm").split("-")[1]);
				if(rs.getString("endm")!=null)
				end_month=Integer.parseInt(rs.getString("endm").split("-")[1]);
				noOfMonth=rs.getInt("NumberOfMonth");
				
				ps_feeInformation=conn.prepareStatement("SELECT DISTINCT termid,termname,CAST(SUBSTR(startdate,6,2) AS UNSIGNED) startmonth,TIMESTAMPDIFF(MONTH,startdate,enddate)+1 MONTH FROM campus_fee_transport_termdetails WHERE accyear=? AND locationId = ?");
				ps_feeInformation.setString(1, accyearId);
				ps_feeInformation.setString(2, locId);
				rs_getfeeInformation=ps_feeInformation.executeQuery();
				int monthCountVal=0;
				ps_transporttermStartMont=conn.prepareStatement("SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(startdate,'-',2),'-',-1) startmonthVal FROM campus_fee_transport_termdetails WHERE accyear = ? AND locationId = ? ORDER BY startdate LIMIT 1");
				ps_transporttermStartMont.setString(1,accyearId);
				ps_transporttermStartMont.setString(2,locId);
				rs_transporttermStartMont=ps_transporttermStartMont.executeQuery();
				while(rs_transporttermStartMont.next()){
					monthCountVal=rs_transporttermStartMont.getInt("startmonthVal");
				}
				
				while(rs_getfeeInformation.next()){
					
					termId=rs_getfeeInformation.getString("termid");
					termname=rs_getfeeInformation.getString("termname");
					month=rs_getfeeInformation.getInt("month");
					startmonth=rs_getfeeInformation.getInt("startmonth");
					if(rs.getString("srtm")==null){
						start_month=startmonth;
					}
					for(int i=startmonth;i<(startmonth+month);i++){
						ps_collection_count = conn.prepareStatement("select count(*),paidDate,reciept_no from campus_tranport_fee_collection_details where admissionNo=? and accYear=? and termcode=? and MonthName=?");

						ps_collection_count.setString(1, addmissionno);
						ps_collection_count.setString(2, accyearId);
						ps_collection_count.setString(3, termId);
						ps_collection_count.setString(4, HelperClass.getMonthFullName(Integer.toString(i)));
						
						rs_collection_count=ps_collection_count.executeQuery();
						int feeCollectionCount=0;
						int recieptNo=0;
						String paidDate=null;
						while(rs_collection_count.next()){
							feeCollectionCount=rs_collection_count.getInt(1);
							if(rs_collection_count.getString(2)!=null)
							paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
							recieptNo=rs_collection_count.getInt(3);
						}
						if(feeCollectionCount==0){
							
							if((monthCountVal >=start_month && monthCountVal <(noOfMonth+start_month))){
								 
							ps_feelist=conn.prepareStatement("SELECT DISTINCT cfsa.stageamount FROM campus_fee_stage cfs join campus_fee_stage_amount cfsa on cfs.stage_id=cfsa.stageId WHERE cfs.stage_id=? AND cfsa.accyearId=?");
							ps_feelist.setString(1, stageid);
							ps_feelist.setString(2, accyearId);
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("stageamount"));
								feeNameVo.setStatus("Not Paid");
								feeNameVo.setTerm(termname); 
								feeNameVo.setTermId(termId);
								feeNameVo.setMonthName(HelperClass.getMonthFullName(Integer.toString(i)));
								feeNameVo.setActualAmt(0);
								feeNameVo.setPaidAmt(0);
								feeNameVo.setBalanceAmt(0);
								feeNameVo.setRecieptNo(0);
								feeNameVo.setPaidDate("-");
								feeNameList.add(feeNameVo);
							}
							collectionVo.setFeeNamelist(feeNameList);
							}
						 }
						else{
							ps_feelist=conn.prepareStatement("SELECT DISTINCT amount_paid,MonthName,totalamount,balance_amount FROM campus_tranport_fee_collection_details WHERE admissionNo=? and MonthName=? and accYear=?");
							ps_feelist.setString(1, addmissionno);
							ps_feelist.setString(2, HelperClass.getMonthFullName(Integer.toString(i)));
							ps_feelist.setString(3, accyearId);
							
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("totalamount"));
								feeNameVo.setPaidAmt(rs_feelist.getDouble("amount_paid"));
								feeNameVo.setBalanceAmt(rs_feelist.getDouble("balance_amount"));
								if(rs_feelist.getDouble("balance_amount")!=0){
									feeNameVo.setStatus("Pending");
								}else{
									feeNameVo.setStatus("Paid");
								}
								feeNameVo.setPaidDate(paidDate);
								feeNameVo.setRecieptNo(recieptNo);
								feeNameVo.setTerm(termname);
								feeNameVo.setTermId(termId);
								feeNameVo.setMonthName(rs_feelist.getString("MonthName"));
								feeNameList.add(feeNameVo);
							}
							collectionVo.setFeeNamelist(feeNameList);
						}
						monthCountVal++;
					}
				}
				if(feeNameList.size()<1){
					collectionVo.setFeeNamelist(feeNameList);
				}
				list.add(collectionVo);
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
				if (rs_getfeeInformation != null&& (!rs_getfeeInformation.isClosed())) {
					rs_getfeeInformation.close();
				}
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (rs_collection_count != null&& (!rs_collection_count.isClosed())) {
					rs_collection_count.close();
				}
				
				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_feeInformation != null&& (!ps_feeInformation.isClosed())) {
					ps_feeInformation.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
				}
				if (ps_collection_count != null&& (!ps_collection_count.isClosed())) {
					ps_collection_count.close();
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
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount: Ending");
		
		return list;
	}
	
	public ArrayList<AddFeeVO> transportFeeCollectionDetailsPrint(AddFeeVO vo1, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee : Starting");
		
		Connection conn=null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			AddFeeVO vo = new AddFeeVO();
			int count=0;
			double dueAmt=0.0,concession=0.0,concession1=0.0,paidAmount=0.0,advance=0.0,precheckAdvance=0.0,prebalence=0.0;
			String paymentmode="",prevalue="",prebalencevalue="";
			 
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			PreparedStatement psmt5 = conn.prepareStatement("SELECT COUNT(ctfp.`receiptno`) FROM campus_tranport_fee_collection_details ctfcd JOIN campus_transport_fees_payments ctfp ON ctfp.receiptno=ctfcd.reciept_no WHERE ctfcd.admissionNo=? AND ctfcd.accYear=? ");
			 psmt5.setString(1, vo1.getStudentName());
			 psmt5.setString(2, vo1.getAcademicYear());
			 ResultSet rs5 = psmt5.executeQuery();
			 while(rs5.next()){
				 count=rs5.getInt(1);
				}
			 rs5.close();
			 psmt5.close();
			 
			 String PrevAcaYearId=HelperClass.getPreAcadamicYearId(userLoggingsVo);
				
			 PreparedStatement psmt4 = conn.prepareStatement("SELECT ctfp.balance,CASE WHEN ctfp.advance IS NULL THEN '' WHEN ctfp.advance='' THEN '' ELSE ctfp.advance END advance FROM campus_tranport_fee_collection_details ctfcd JOIN campus_transport_fees_payments ctfp ON ctfp.receiptno=ctfcd.reciept_no WHERE ctfcd.admissionNo=? AND ctfcd.accYear=? AND ctfcd.reciept_no=(SELECT MAX(reciept_no) FROM campus_tranport_fee_collection_details WHERE admissionNo=? AND accYear=?)");
			 psmt4.setString(1, vo1.getStudentName());
			 psmt4.setString(2, PrevAcaYearId);
			 psmt4.setString(3, vo1.getStudentName());
			 psmt4.setString(4, PrevAcaYearId);
			 ResultSet rs4 = psmt4.executeQuery();
			 while(rs4.next()){
				 precheckAdvance=rs4.getDouble("advance");
				 prevalue=rs4.getString("advance");
				 prebalencevalue=rs4.getString("balance");
				 prebalence=rs4.getDouble("balance");
				}
			 rs4.close();
			 psmt4.close();
			 
		    PreparedStatement psmt1 = conn.prepareStatement("SELECT cfp.*,cfsd.modeofpayment FROM campus_transport_fees_payments cfp JOIN campus_tranport_fee_collection_details cfsd ON cfp.receiptno=cfsd.reciept_no WHERE cfp.receiptno=? ORDER BY cfsd.reciept_no DESC LIMIT 1");
			psmt1.setString(1, vo1.getReceptNo());
			 
			ResultSet rs1 = psmt1.executeQuery();	
			while(rs1.next()){
				dueAmt=rs1.getDouble("balance");
				advance=rs1.getDouble("advance");
				paidAmount=rs1.getDouble("paidAmount");
				paymentmode=rs1.getString("modeofpayment");
			}
			rs1.close();
			psmt1.close();
		 
			if(count<2){
				vo.setAdvanceAmt(precheckAdvance);
			     vo.setDueAmt(prebalence);
			 }else{
				 vo.setAdvanceAmt(advance);
				 vo.setDueAmt(dueAmt); 
			 }
			
			vo.setPaidAmt(paidAmount);
			vo.setFeeType(paymentmode);
			list.add(vo);
	 
			PreparedStatement psmt2 = conn.prepareStatement("SELECT concession FROM campus_transport_concessiondetails WHERE studentIdNo=?  AND academic_year=? AND termcode=?");
	    	psmt2.setString(1, vo1.getStudentName());
	    	psmt2.setString(2, vo1.getAcademicYear());
	    	psmt2.setString(3, vo1.getTermName());
	    	 
	    	ResultSet rs2 = psmt2.executeQuery();
	    	while(rs2.next()){
	    		concession1=rs2.getDouble("concession");
	    		concession=concession+concession1;
			}
	    	rs2.close();
			psmt2.close();
			
			vo.setConcession(String.valueOf(concession));
			list.add(vo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
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
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee: Ending");
		return list;
	}

	public String cancelRequestConcessionFees(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : cancelRequestConcessionFees Starting");

		PreparedStatement pst = null, pst1 = null;
		Connection conn = null;
		String status = null;
		int count = 0;
		ResultSet rst = null;
		int value = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			for(int j=1;j<feevo.getCancelFees().split(":").length;j++){
				
				pst1 = conn.prepareStatement("SELECT COUNT(is_paid) FROM campus_fee_collection WHERE `is_paid`='Y' AND `admissionNo`=? AND `accYear`=? AND `termcode`=? ");
				pst1.setString(1, feevo.getCancelFees().split(":")[j].split(",")[4]);
				pst1.setString(2, feevo.getCancelFees().split(":")[j].split(",")[2]);
				pst1.setString(3, feevo.getCancelFees().split(":")[j].split(",")[0]);
				rst = pst1.executeQuery();
				
				while(rst.next()){
					value = rst.getInt(1);
				}
				
				if(value == 0){
				pst = conn.prepareStatement("DELETE FROM `campus_scholorship` WHERE `termcode`=? AND `feecode`=? AND `academic_year`=? AND `classId`=? AND `studentId`=?");
				pst.setString(1, feevo.getCancelFees().split(":")[j].split(",")[0]);
				pst.setString(2, feevo.getCancelFees().split(":")[j].split(",")[1]);
				pst.setString(3, feevo.getCancelFees().split(":")[j].split(",")[2]);
				pst.setString(4, feevo.getCancelFees().split(":")[j].split(",")[3]);
				pst.setString(5, feevo.getCancelFees().split(":")[j].split(",")[4]);
				
				int result = pst.executeUpdate();
				if(result>0){
					HelperClass.recordLog_Activity(feevo.getLog_audit_session(),"Fee","Fee Request/Cancel","Delete",pst.toString(),userLoggingsVo);
				}
				count++;
				}
			}
			if(count > 0) {
				status = "success";
			}else {
				status = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}



		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : cancelRequestConcessionFees Ending");
		
		return status;
	}
	
	@Override
	public ArrayList<StudentRegistrationVo> getFeeCancelledStudentList(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getFeeCancelledStudentList  Starting");
		ArrayList<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;
		String locationid=vo.getLocationId();
		String classname=vo.getClassName();
		String sectionid=vo.getDivisionName();
		
		if(locationid.equalsIgnoreCase("all")){
			locationid="%%";
		}
		if(classname.equalsIgnoreCase("all")){
			classname="%%";
		}
		if(sectionid.equalsIgnoreCase("all")){
			sectionid="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		//	String query="SELECT st.`student_admissionno_var`,st.`student_fname_var`,st.`student_lname_var`,cc.`classdetails_name_var`,cs.`classsection_name_var`,cfcd.`term_id`,ct.`termname`,cfcd.`paid_amt` FROM `campus_feecancel_details` cfcd JOIN `campus_student` st ON st.`student_id_int`=cfcd.stu_id JOIN `campus_student_classdetails` csc ON st.`student_id_int`=csc.`student_id_int` AND csc.`fms_acadamicyear_id_int`=cfcd.`accy_id` JOIN `campus_classdetail` cc ON cc.`classdetail_id_int`=csc.`classdetail_id_int` AND csc.`locationId` =cc.`locationId` JOIN `campus_classsection` cs ON cs.`classsection_id_int`=csc.`classsection_id_int` JOIN  `campus_fee_termdetails` ct ON ct.`termid`=cfcd.`term_id` WHERE"; 
				String query="SELECT DISTINCT st.`student_id_int`,st.`student_admissionno_var`,st.`student_fname_var`,st.`student_lname_var`,cc.`classdetails_name_var`,cs.`classsection_name_var` FROM `campus_feecancel_details` cfcd JOIN `campus_student` st ON st.`student_id_int`=cfcd.stu_id JOIN `campus_student_classdetails` csc ON st.`student_id_int`=csc.`student_id_int` AND csc.`fms_acadamicyear_id_int`=cfcd.`accy_id` JOIN `campus_classdetail` cc ON cc.`classdetail_id_int`=csc.`classdetail_id_int` AND csc.`locationId` =cc.`locationId` JOIN `campus_classsection` cs ON cs.`classsection_id_int`=csc.`classsection_id_int` JOIN  `campus_fee_termdetails` ct ON ct.`termid`=cfcd.`term_id` WHERE"; 
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("csc.locationId",locationid);
				vec.add("csc.locationId");
			}
			if(!vo.getAcademicYear().equalsIgnoreCase("%%") ) {
				map.put("cfcd.accy_id",vo.getAcademicYear());
				vec.add("cfcd.accy_id");
			}
			if(!classname.equalsIgnoreCase("%%")) {
				map.put("csc.classdetail_id_int",classname);
				vec.add("csc.classdetail_id_int");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("csc.classsection_id_int",sectionid);
				vec.add("csc.classsection_id_int");
			}
			
			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			
			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql+" AND (CONCAT(st.`student_fname_var`,' ',st.`student_lname_var`) LIKE ? OR st.`student_lname_var` LIKE ? OR cs.`classsection_name_var` LIKE ?  OR st.`student_admissionno_var` LIKE ?) ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),st.`student_fname_var`"; 
			}else {
				finalquery=query+" "+" AND (CONCAT(st.`student_fname_var`,' ',st.`student_lname_var`) LIKE ? OR st.`student_lname_var` LIKE ? OR cs.`classsection_name_var` LIKE ? OR st.`student_admissionno_var` LIKE ?) ORDER BY CAST(SUBSTRING(csc.`classdetail_id_int`,4,LENGTH(csc.`classdetail_id_int`)-3) AS SIGNED),st.`student_fname_var`";
			}
			pst = conn.prepareStatement(finalquery);
			pst.setString(1,  vo.getName() + "%");
			pst.setString(2,  vo.getName() + "%");
			pst.setString(3,  vo.getName() + "%");
			pst.setString(4,  vo.getName() + "%");
			/*pst.setString(4,  searchvalue + "%");
			pst.setString(5,  searchvalue + "%");
			pst.setString(6,  searchvalue + "%");
			pst.setString(7,  searchvalue + "%");
			pst.setString(8,  searchvalue + "%");*/

				rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				registrationVo.setCount(sno);
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentnamelabel(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
				registrationVo.setClasssection(rs.getString("classdetails_name_var")+"-"+rs.getString("classsection_name_var"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				//registrationVo.setSearchTerm(rs.getString("termname"));
				//registrationVo.setPaidupto(rs.getString("paid_amt"));
				list.add(registrationVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getFeeCancelledStudentList  Ending");

		return list;
}

	@Override
	public ArrayList<FeeCollectionVo> getIndividualFeeCancelledStudent(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getIndividualFeeCancelledStudent  Starting");
		ArrayList<FeeCollectionVo> list = new ArrayList<FeeCollectionVo>();
		PreparedStatement pst = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rst = null;
		Connection conn = null;
		int sno=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst = conn.prepareStatement(" SELECT cfd.`term_id`,ct.`termname`,cfd.`total_amt`,cfd.`actual_amt`,cfd.`paid_amt`,cfd.`bal_amt`,cfd.`advance_amt`,cfd.`fine_amt`,cfd.`paid_date` FROM `campus_feecancel_details` cfd JOIN `campus_fee_termdetails` ct ON ct.`termid`=cfd.`term_id` WHERE `stu_id`=? AND `accy_id`=?");
			pst.setString(1,  vo.getStudentName());
			pst.setString(2,  vo.getAcademicYear());
			rs = pst.executeQuery();
			while (rs.next()) {
				sno++;
				FeeCollectionVo vo1 = new FeeCollectionVo();
				vo1.setTerm(rs.getString("termname"));
				vo1.setTot_actual_amt(Double.parseDouble(rs.getString("actual_amt")));
				vo1.setTot_paid_amt(Double.parseDouble(rs.getString("paid_amt")));
				vo1.setOpening_balance(Double.parseDouble(rs.getString("bal_amt")));
				vo1.setAdvanceCarry(Double.parseDouble(rs.getString("advance_amt")));
				vo1.setFineAmount(Double.parseDouble(rs.getString("fine_amt")));
				vo1.setDd_cheque_date(HelperClass.convertDatabaseToUI(rs.getString("paid_date")));
				list.add(vo1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst != null && (!pst.isClosed())) {
					pst.close();
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getIndividualFeeCancelledStudent  Ending");

		return list;
}

	public String checkFeesPaidStatus(String studId, String acyId, String termCode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : checkFeesPaidStatus Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		String status = null;
		ResultSet rst = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pst1 = conn.prepareStatement("SELECT COUNT(is_paid) FROM campus_fee_collection WHERE `is_paid`='Y' AND `admissionNo`=? AND `accYear`=? AND termcode like ?");
			pst1.setString(1, studId);
			pst1.setString(2, acyId);
			pst1.setString(3, "%%");
			rst = pst1.executeQuery();

			while(rst.next()){

				if(rst.getInt(1) == 1){
					status = "Paid";
				}else{
					status = "Not Paid";
				}

			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}



		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : checkFeesPaidStatus Ending");

		return status;
	}

	public List<StudentRegistrationVo> getStudentDetailsByJs(StudentRegistrationVo data, String custId) {
		return null;
	}

	public Double getTodayTransaction(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTodayTransaction Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		Double status = null;
		ResultSet rst = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst1 = conn.prepareStatement("SELECT CASE WHEN SUM(amount_paid) IS NULL THEN '0' WHEN SUM(amount_paid)='' THEN '0' ELSE SUM(amount_paid) END amount_paid FROM campus_fee_collection  WHERE `paidDate`= CURDATE()");

			rst = pst1.executeQuery();

			while(rst.next()){
				status=rst.getDouble("amount_paid");
			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTodayTransaction Ending");

		return status;
	}

	public int getNoOfStudentsPayed(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getNoOfStudentsPayed Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		int status = 0;
		ResultSet rst = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst1 = conn.prepareStatement("SELECT DISTINCT admissionNo FROM campus_fee_collection WHERE `paidDate`= CURDATE()");

			rst = pst1.executeQuery();

			while(rst.next()){
				status++; 
			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getNoOfStudentsPayed Ending");

		return status;
	}

	public Double getMonthlyTransaction(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getMonthlyTransaction Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		Double status = null;
		ResultSet rst = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst1 = conn.prepareStatement("SELECT CASE WHEN SUM(amount_paid) IS NULL THEN '0' WHEN SUM(amount_paid)='' THEN '0' ELSE SUM(amount_paid) END amount_paid FROM campus_fee_collection  WHERE MONTH(`paidDate`)= MONTH(CURRENT_DATE())");
			rst = pst1.executeQuery();

			while(rst.next()){
				status=rst.getDouble("amount_paid");
			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getMonthlyTransaction Ending");

		return status;
	}
	
	public int getNoOfStudentsPayedInMonth(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getNoOfStudentsPayedInMonth Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		int status = 0;
		ResultSet rst = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst1 = conn.prepareStatement("SELECT DISTINCT admissionNo FROM campus_fee_collection WHERE MONTH(`paidDate`)= MONTH(CURRENT_DATE())");
			rst = pst1.executeQuery();

			while(rst.next()){
				status++; 
			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getNoOfStudentsPayedInMonth Ending");

		return status;
	}

	public List<StudentRegistrationVo> getPrevMonthStatus(String startDate,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getPrevMonthStatus Starting");

		PreparedStatement pst1 = null;
		Connection conn = null;
		List<StudentRegistrationVo> list =new ArrayList<StudentRegistrationVo>();
		
		int status1=0,status2=0,j=0,k=0;
		ResultSet rst = null;
		
		String months[] = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"}; 
		double total1=0;
		int startdate=Integer.parseInt(startDate);
		try {
			
			Calendar cal = Calendar.getInstance();
		   /* int year = cal.get(cal.YEAR);*/
		    int month = cal.get(cal.MONTH)+1;
		     
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pst1 = conn.prepareStatement("SELECT  CASE WHEN SUM(amount_paid) IS NULL THEN '0' WHEN SUM(amount_paid)='' THEN '0' ELSE SUM(amount_paid) END amount FROM campus_fee_collection  WHERE MONTH(`paidDate`)=?");
			 
			if(month>startdate){
				for(int i=month;i>=startdate;i--){
					pst1.setInt(1, i);
					rst = pst1.executeQuery();

					while(rst.next()){
						StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
						studentRegistrationVo.setAmount(rst.getString("amount"));
						studentRegistrationVo.setMonth(months[i-1]);
						list.add(studentRegistrationVo);
					}
					status1++;
					if(status1==5){
						break;
					}
				}	
			}
			else{
				for(int i=month+12;i>month;i--)
		           {
		        	   StudentRegistrationVo studentRegistrationVo = new StudentRegistrationVo();
						 j=i;
						 if(j>12){
							k=j;
							k=k-12;
							pst1.setInt(1, i);
							rst = pst1.executeQuery();
							while(rst.next()){
								studentRegistrationVo.setAmount(rst.getString("amount"));
								studentRegistrationVo.setMonth(months[k-1]);
								list.add(studentRegistrationVo);
							}
						 }else{
							   pst1.setInt(1, i);
								rst = pst1.executeQuery();
								while(rst.next()){
									studentRegistrationVo.setAmount(rst.getString("amount"));
									studentRegistrationVo.setMonth(months[i-1]);
									list.add(studentRegistrationVo);
								}
						 }
						 status2++;
						if(status2==5){
							break;
						}
					}
			}
           
			 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getPrevMonthStatus Ending");

		return list;
	}

	public List<StudentConcessionVo> getTermConcessionFees(StudentConcessionVo convo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermConcessionFees Starting");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		
		List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();

		try {
			conn = JDBCConnection.getSeparateConnection(convo.getDbDetails());
			pst = conn.prepareStatement("SELECT `termcode`,`feecode`,`concessionId`,`concessionType`,`concessionPercent`,`concession` FROM `campus_scholorship` WHERE `studentId`=? AND `classId`=? AND `academic_year`=? AND termcode=?");
			pst.setString(1, convo.getStudentId());
			pst.setString(2, convo.getClassId());
			pst.setString(3, convo.getAcademicId());
			pst.setString(4, convo.getTermId());
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentConcessionVo stufee = new StudentConcessionVo();
				sno++;
				stufee.setSno(sno);
				stufee.setTermcode(rs.getString("termcode"));
				stufee.setConcessionType(rs.getString("concessionType"));
				stufee.setConcessionId(rs.getString("concessionId"));
				stufee.setFeecode(rs.getString("feecode"));
				stufee.setTermcode(rs.getString("termcode"));
				stufee.setConcessionPercent(rs.getString("concessionPercent"));
				stufee.setConcessionAmount(rs.getString("concession"));

				list.add(stufee);
			}

		} catch (Exception e) {
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

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl : getTermConcessionFees Ending");
		
		return list;
		
	}
	
	public ArrayList<AddFeeVO> feeCollectionDetailsPrintHistory(AddFeeVO vo1, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: feeCollectionDetailsPrintHistory : Starting");
		
		Connection conn=null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		try{
			AddFeeVO vo = new AddFeeVO();
		 
			double dueAmt=0.0,concession=0.0,paidAmount=0.0,advance=0.0,totalamount=0.0;
			String paymentmode="",paidDate="";
			 
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			PreparedStatement psmt5 = conn.prepareStatement("SELECT concession FROM campus_transport_concessiondetails WHERE studentIdNo=? AND termcode=? AND academic_year=?");
			
			for(int i=0;i<vo1.getGetDataArray().length;i++){
				psmt5.setString(1, vo1.getStudentName());
				psmt5.setString(2, vo1.getGetDataArray()[i]);
			    psmt5.setString(3, vo1.getAcademicYear());
				 ResultSet rs5 = psmt5.executeQuery();
				 while(rs5.next()){
					 concession=concession+rs5.getDouble(1);
					}
			 rs5.close();
			 psmt5.close();
			}
			
			vo.setConcession(String.valueOf(concession));
			list.add(vo);
			 
			 
		    PreparedStatement psmt1 = conn.prepareStatement("SELECT DISTINCT ctfc.paidDate,SUM(ctfc.totalamount) AS totalamount,ctfp.advance,ctfp.balance,SUM(ctfc.amount_paid) AS amount_paid,ctfc.modeofpayment FROM campus_tranport_fee_collection_details ctfc JOIN campus_transport_fees_payments ctfp ON ctfp.receiptno=ctfc.reciept_no WHERE ctfc.reciept_no=?");
			psmt1.setString(1, vo1.getReceptNo());
			ResultSet rs1 = psmt1.executeQuery();	 
			while(rs1.next()){
				
				dueAmt=rs1.getDouble("balance");
				advance=rs1.getDouble("advance");
				paidAmount=rs1.getDouble("amount_paid");
				paymentmode=rs1.getString("modeofpayment");
				totalamount=rs1.getDouble("totalamount");
				paidDate=rs1.getString("paidDate");
			}
			rs1.close();
			psmt1.close();
		 
			vo.setDueAmt(dueAmt);
			vo.setAdvanceAmt(advance); 
			vo.setTotalAmount(totalamount); 
			vo.setPaidAmt(paidAmount);
			vo.setFeeType(paymentmode);
			vo.setPaidDate(HelperClass.convertDatabaseToUI(paidDate));
			list.add(vo);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
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
				+ " Control in FeeCollectionDaoImpl: feeCollectionDetailsPrintHistory: Ending");
		return list;
	}

	public ArrayList<AddFeeVO> getTermnameAndAmountPrintHistory(AddFeeVO vo1, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee : Starting");
		
		Connection conn=null;
		ArrayList<AddFeeVO> list = new ArrayList<AddFeeVO>();
		PreparedStatement psmt5 =null;
		ResultSet rs5=null;
		
		try{
			
			 
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			 psmt5 = conn.prepareStatement("SELECT cftt.termname,ctfc.MonthName,ctfc.totalamount FROM campus_fee_transport_termdetails cftt JOIN campus_tranport_fee_collection_details ctfc ON cftt.termid=ctfc.termcode WHERE ctfc.reciept_no=? AND ctfc.accYear=? AND ctfc.admissionNo=?");
			
			psmt5.setString(1, vo1.getReceptNo());
		    psmt5.setString(2, vo1.getAcademicYear());
		    psmt5.setString(3, vo1.getStudentName());
			  rs5 = psmt5.executeQuery();
			 while(rs5.next()){
				 AddFeeVO vo = new AddFeeVO();
				 vo.setTermName(rs5.getString("termname"));
				 vo.setMonthName(rs5.getString("MonthName")); 
				 vo.setTotalAmount(rs5.getDouble("totalamount"));
				 list.add(vo);
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs5!=null && ! rs5.isClosed()){
					rs5.close();
				}
				if(psmt5!=null && ! psmt5.isClosed()){
					psmt5.close();
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
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee: Ending");
		return list;
	}
	public ArrayList<FeeNameVo> getTransportRequestCanTerms(String feeCodeDetails, UserLoggingsPojo dbdetails, int start_month, int end_month, int noOfMonth, String stageid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount : Starting");
		Connection conn = null;
		ArrayList<String> months = new ArrayList<>();
		FeeCollectionVo collectionVo=new FeeCollectionVo();
		ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
		int count=0;
 		try{
			int termCnt = 0;
			String[] feeDatails=feeCodeDetails.split(",");
			String addmissionno=feeDatails[0];
			String accyearId=feeDatails[1];
			String locId=feeDatails[2];
			String termid = null;
			String termname = null;
			int termstmnth = 0;
			int termendmnth = 0;
			String stageAmt = null;
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(`termid`) FROM `campus_fee_termdetails` WHERE `accyear` = ? AND `locationId` = ?"); 
			pstmt.setString(1, accyearId);
			pstmt.setString(2, locId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				termCnt = rs.getInt(1);
			}
			rs.close();
			PreparedStatement ps_feeInformation=conn.prepareStatement("SELECT DISTINCT termid,termname,CAST(SUBSTR(startdate,6,2) AS UNSIGNED) startmonth,CAST(SUBSTR(enddate,6,2) AS UNSIGNED) endmonth,TIMESTAMPDIFF(MONTH,startdate,enddate)+1 MONTH FROM campus_fee_transport_termdetails WHERE accyear=? AND locationId = ?");
			ps_feeInformation.setString(1, accyearId);
			ps_feeInformation.setString(2, locId);
			rs = ps_feeInformation.executeQuery();
			while(rs.next()){
				termid = rs.getString(1);
				termname = rs.getString(2);
				termstmnth = rs.getInt(3);
				termendmnth = rs.getInt(4);
				int totmnth =  rs.getInt(5);
				int init = 0;
				int range = 0;
				PreparedStatement ps_feelist=conn.prepareStatement("SELECT DISTINCT cfsa.stageamount FROM campus_fee_stage cfs join campus_fee_stage_amount cfsa on cfs.stage_id=cfsa.stageId WHERE cfs.stage_id=? AND cfsa.accyearId=? AND cfs.location=?");
				ps_feelist.setString(1, stageid);
				ps_feelist.setString(2, accyearId);
				ps_feelist.setString(3, locId);
				ResultSet rsamt = ps_feelist.executeQuery();
				while(rsamt.next()){
					stageAmt = rsamt.getString(1);
				}
				
			/*	if(totmnth == noOfMonth){
					init = termstmnth;
					range = termstmnth+totmnth;
				}else{*/
					if(termstmnth < termendmnth){
						
						if(start_month <= termstmnth){
						
							init = termstmnth;
							range = termstmnth+totmnth;
						}else if(start_month > termstmnth){
							
							init = start_month;
							range = termstmnth+totmnth;
						}
						else{
							init = 0;
							range = 0;
						}
					}
					else if(termstmnth > termendmnth){
						if((termstmnth+totmnth) > start_month){
							init = start_month;
						}
						else if((termstmnth+totmnth) < start_month){
							init = termstmnth;
						}else{
							init = 0;
							range = 0;
						}
						range = termstmnth+totmnth;
					}else{
						init = termstmnth;
						range = termstmnth+totmnth;
					}
				/*}*/
					
					for(int i=init; i <range;i++){
						if(months.size() != noOfMonth){
							PreparedStatement ps_collection_count = conn.prepareStatement("select count(*),paidDate,reciept_no from campus_tranport_fee_collection_details where admissionNo=? and accYear=? and termcode=? and MonthName=? AND locId=? ");

							ps_collection_count.setString(1, addmissionno);
							ps_collection_count.setString(2, accyearId);
							ps_collection_count.setString(3, termid);
							ps_collection_count.setString(4, HelperClass.getMonthFullName(Integer.toString(i)));
							ps_collection_count.setString(5, locId);
							
							ResultSet rs_collection_count=ps_collection_count.executeQuery();
							int feeCollectionCount=0;
							int recieptNo=0;
							String paidDate=null;
							while(rs_collection_count.next()){
								feeCollectionCount=rs_collection_count.getInt(1);
								if(rs_collection_count.getString(2)!=null)
									paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
								recieptNo=rs_collection_count.getInt(3);
							}
							if(feeCollectionCount==0){


								PreparedStatement ps_feelist1=conn.prepareStatement("SELECT DISTINCT cfsa.stageamount FROM campus_fee_stage cfs join campus_fee_stage_amount cfsa on cfs.stage_id=cfsa.stageId WHERE cfs.stage_id=? AND cfsa.accyearId=? AND cfs.location=?");
								ps_feelist1.setString(1, stageid);
								ps_feelist1.setString(2, accyearId);
								ps_feelist1.setString(3, locId);
								
								ResultSet rs_feelist=ps_feelist1.executeQuery();
								while(rs_feelist.next()){
									FeeNameVo feeNameVo=new FeeNameVo();
									count++;
									feeNameVo.setSno(count);
									feeNameVo.setActualAmt(rs_feelist.getDouble("stageamount"));
									feeNameVo.setStatus("Not Paid");
									feeNameVo.setTerm(termname); 
									feeNameVo.setTermId(termid);
									feeNameVo.setMonthName(HelperClass.getMonthFullName(Integer.toString(i)));
									feeNameList.add(feeNameVo);
								}
								/*collectionVo.setFeeNamelist(feeNameList);*/
							
							}else{
								ps_feelist=conn.prepareStatement("SELECT DISTINCT amount_paid,MonthName FROM campus_tranport_fee_collection_details WHERE admissionNo=? and MonthName=? and accYear=? AND locId=?");
								ps_feelist.setString(1, addmissionno);
								ps_feelist.setString(2, HelperClass.getMonthFullName(Integer.toString(i)));
								ps_feelist.setString(3, accyearId);
								ps_feelist.setString(4, locId);
								
								ResultSet rs_feelist=ps_feelist.executeQuery();
								while(rs_feelist.next()){
									FeeNameVo feeNameVo=new FeeNameVo();
									count++;
									feeNameVo.setSno(count);
									feeNameVo.setActualAmt(rs_feelist.getDouble("amount_paid"));
									feeNameVo.setStatus("Paid");
									feeNameVo.setPaidDate(paidDate);
									feeNameVo.setRecieptNo(recieptNo);
									feeNameVo.setTerm(termname);
									feeNameVo.setTermId(termid);
									feeNameVo.setMonthName(rs_feelist.getString("MonthName"));
									feeNameList.add(feeNameVo);
								}
								/*collectionVo.setFeeNamelist(feeNameList);*/
							}
							months.add(HelperClass.getMonthFullName(String.valueOf(i)));
						}else{
							break;
						}
						if(i==12){
							i=0;
						}
					}
					start_month = start_month+months.size();
				}
			System.out.println("--------------");
			months.forEach(System.out::println);
			System.out.println("--------------");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getAdvanceOrBalanceForTransportFee: Ending");
	return feeNameList;	
	}
	
	@Override
	public FeeCollectionVo getTranportFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount : Starting");
		PreparedStatement ps_feeInformation=null;
		ResultSet rs_getfeeInformation=null;
		PreparedStatement ps_insertPlan = null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;

		PreparedStatement ps_collection_count= null;
		ResultSet rs_collection_count=null;
		PreparedStatement ps_feelist= null;
		ResultSet rs_feelist=null;
		PreparedStatement ps_transporttermStartMont= null;
		ResultSet rs_transporttermStartMont=null;

		/*double amount_paid_so_far =0.00;
		double opening_balance =0.00;*/
		String stageid=null;


		FeeCollectionVo collectionVo=new FeeCollectionVo();
		ArrayList<FeeNameVo> feeNameList=new ArrayList<FeeNameVo>();
		try {

			String[] feeDatails=feeCodeDetails.split(",");

			String addmissionno=feeDatails[0];

			String accyearId=feeDatails[1];
			String locId=feeDatails[2];
			String termId=null;
			String classId=null;
			String specCode=null;
			String termname=null;
			int start_month=0;
			int end_month=0;
			int month=0;
			int startmonth=0;
			int noOfMonth=0;
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			ps_insertPlan = conn.prepareStatement(FeeCollectionSqlUtils.GET_TRANSPORT_FEECOLLECTION_DEDDER);

			ps_insertPlan.setString(1,addmissionno);
			ps_insertPlan.setString(2,locId);
			ps_insertPlan.setString(3,accyearId);
			
			rs=ps_insertPlan.executeQuery();
			while(rs.next()){
				collectionVo.setStudentid(addmissionno);
				collectionVo.setAddmissionno(rs.getString("student_admissionno_var"));
				collectionVo.setStudentname(rs.getString("studentname"));
				collectionVo.setClassname(rs.getString("classdetails_name_var"));
				specCode=rs.getString("specilization");
				classId=rs.getString("classdetail_id_int");
				collectionVo.setClassId(classId);
				collectionVo.setSpecialization(specCode);
				collectionVo.setSectionname(rs.getString("classsection_name_var"));
				collectionVo.setAccYear(accyearId);
				collectionVo.setAccYearname(rs.getString("acadamic_year"));
				stageid=rs.getString("StageId");
				
				if(rs.getString("srtm")!=null)
					start_month=Integer.parseInt(rs.getString("srtm").split("-")[1]);
				if(rs.getString("endm")!=null)
					end_month=Integer.parseInt(rs.getString("endm").split("-")[1]);
				noOfMonth=rs.getInt("NumberOfMonth");
				
				if(rs.getString("srtm")!=null){
				feeNameList = getTransportRequestCanTerms(feeCodeDetails,userLoggingsVo,start_month,end_month,noOfMonth,stageid);
				collectionVo.setFeeNamelist(feeNameList);
				}
				else{
				ps_feeInformation=conn.prepareStatement("SELECT DISTINCT termid,termname,CAST(SUBSTR(startdate,6,2) AS UNSIGNED) startmonth,CAST(SUBSTR(enddate,6,2) AS UNSIGNED) endmonth,TIMESTAMPDIFF(MONTH,startdate,enddate)+1 MONTH FROM campus_fee_transport_termdetails WHERE accyear=? AND locationId = ?");
				ps_feeInformation.setString(1, accyearId);
				ps_feeInformation.setString(2, locId);
				
				rs_getfeeInformation=ps_feeInformation.executeQuery();
				int monthCountVal=0;
				ps_transporttermStartMont=conn.prepareStatement("SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(startdate,'-',2),'-',-1) startmonthVal FROM campus_fee_transport_termdetails WHERE accyear = ? AND locationId = ? AND startdate BETWEEN CURDATE() AND (SELECT MAX(enddate) AS MAX FROM campus_fee_transport_termdetails WHERE accyear = ? AND locationId = ?) ORDER BY startdate LIMIT 1");
				ps_transporttermStartMont.setString(1,accyearId);
				ps_transporttermStartMont.setString(2,locId);
				ps_transporttermStartMont.setString(3,accyearId);
				ps_transporttermStartMont.setString(4,locId);
				
				rs_transporttermStartMont=ps_transporttermStartMont.executeQuery();
				while(rs_transporttermStartMont.next()){
					monthCountVal=rs_transporttermStartMont.getInt("startmonthVal");
				}
				int endMonth = 0;
				while(rs_getfeeInformation.next()){

					termId=rs_getfeeInformation.getString("termid");
					termname=rs_getfeeInformation.getString("termname");
					month=rs_getfeeInformation.getInt("month");
					startmonth=rs_getfeeInformation.getInt("startmonth");
					endMonth = rs_getfeeInformation.getInt("endmonth");
					
					int totvalue = 0;
					if(startmonth > endMonth){
						totvalue = startmonth+month;
					}else{
						totvalue = endMonth+1;
					}
					for(int i=startmonth;i<totvalue;i++){
						ps_collection_count = conn.prepareStatement("select count(*),paidDate,reciept_no from campus_tranport_fee_collection_details where admissionNo=? and accYear=? and termcode=? and MonthName=? AND locId=? ");

						ps_collection_count.setString(1, addmissionno);
						ps_collection_count.setString(2, accyearId);
						ps_collection_count.setString(3, termId);
						ps_collection_count.setString(4, HelperClass.getMonthFullName(Integer.toString(i)));
						ps_collection_count.setString(5, locId);
						
						rs_collection_count=ps_collection_count.executeQuery();
						int feeCollectionCount=0;
						int recieptNo=0;
						String paidDate=null;
						while(rs_collection_count.next()){
							feeCollectionCount=rs_collection_count.getInt(1);
							if(rs_collection_count.getString(2)!=null)
								paidDate=HelperClass.convertDatabaseToUI(rs_collection_count.getString(2));
							recieptNo=rs_collection_count.getInt(3);
						}
						if(feeCollectionCount==0){

							

								ps_feelist=conn.prepareStatement("SELECT DISTINCT cfsa.stageamount FROM campus_fee_stage cfs join campus_fee_stage_amount cfsa on cfs.stage_id=cfsa.stageId WHERE cfs.stage_id=? AND cfsa.accyearId=? AND cfs.location=?");
								ps_feelist.setString(1, stageid);
								ps_feelist.setString(2, accyearId);
								ps_feelist.setString(3, locId);
								
								rs_feelist=ps_feelist.executeQuery();
								while(rs_feelist.next()){
									FeeNameVo feeNameVo=new FeeNameVo();
									count++;
									feeNameVo.setSno(count);
									feeNameVo.setActualAmt(rs_feelist.getDouble("stageamount"));
									feeNameVo.setStatus("Not Paid");
									feeNameVo.setTerm(termname); 
									feeNameVo.setTermId(termId);
									feeNameVo.setMonthName(HelperClass.getMonthFullName(Integer.toString(i)));
									feeNameList.add(feeNameVo);
								}
								collectionVo.setFeeNamelist(feeNameList);
							
						}
						else{
							ps_feelist=conn.prepareStatement("SELECT DISTINCT amount_paid,MonthName FROM campus_tranport_fee_collection_details WHERE admissionNo=? and MonthName=? and accYear=? AND locId=?");
							ps_feelist.setString(1, addmissionno);
							ps_feelist.setString(2, HelperClass.getMonthFullName(Integer.toString(i)));
							ps_feelist.setString(3, accyearId);
							ps_feelist.setString(4, locId);
							
							rs_feelist=ps_feelist.executeQuery();
							while(rs_feelist.next()){
								FeeNameVo feeNameVo=new FeeNameVo();
								count++;
								feeNameVo.setSno(count);
								feeNameVo.setActualAmt(rs_feelist.getDouble("amount_paid"));
								feeNameVo.setStatus("Paid");
								feeNameVo.setPaidDate(paidDate);
								feeNameVo.setRecieptNo(recieptNo);
								feeNameVo.setTerm(termname);
								feeNameVo.setTermId(termId);
								feeNameVo.setMonthName(rs_feelist.getString("MonthName"));
								feeNameList.add(feeNameVo);
							}
							collectionVo.setFeeNamelist(feeNameList);
						}
						monthCountVal++;
						/*if(i==12){
							i=0;
							totvalue = endMonth+1;
						}*/
					}
				}}
				if(feeNameList.size()<1){
					collectionVo.setFeeNamelist(feeNameList);
				}
			}
			JSONArray array=new JSONArray();
			array.put(collectionVo.getFeeNamelist());

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
				if (rs_getfeeInformation != null&& (!rs_getfeeInformation.isClosed())) {
					rs_getfeeInformation.close();
				}
				if (rs_feelist != null&& (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (rs_collection_count != null&& (!rs_collection_count.isClosed())) {
					rs_collection_count.close();
				}

				if (ps_insertPlan != null&& (!ps_insertPlan.isClosed())) {
					ps_insertPlan.close();
				}
				if (ps_feeInformation != null&& (!ps_feeInformation.isClosed())) {
					ps_feeInformation.close();
				}
				if (ps_feelist != null&& (!ps_feelist.isClosed())) {
					ps_feelist.close();
				}
				if (ps_collection_count != null&& (!ps_collection_count.isClosed())) {
					ps_collection_count.close();
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
				+ " Control in FeeCollectionDaoImpl: getTranportFeeCollectionAmount: Ending");

		return collectionVo;
	}

	
	public boolean checkFeeSetup(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: checkFeeSetup : Starting");

		Connection conn=null;
		PreparedStatement psmt5 =null;
		ResultSet rs5=null;
		boolean status = false;

		try{
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);

			psmt5 = conn.prepareStatement("SELECT COUNT(*)as cnt FROM `campus_fee_setupdetails` fsd JOIN `campus_fee_setup` fs ON fs.`feeSettingcode` = fsd.`feeSettingCode` WHERE fs.`ClassCode` = ? AND `AccyearCode`=? AND `locationId`=?");
			psmt5.setString(1, feevo.getClassId());
			psmt5.setString(2, feevo.getAcademicId());
			psmt5.setString(3, feevo.getLocationId());
			rs5 = psmt5.executeQuery();
			while(rs5.next()){
				if(rs5.getInt("cnt") > 0){
					status = true;
				}else{
					status = false;
				}
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs5!=null && ! rs5.isClosed()){
					rs5.close();
				}
				if(psmt5!=null && ! psmt5.isClosed()){
					psmt5.close();
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
				+ " Control in FeeCollectionDaoImpl: checkFeeSetup: Ending");

		return status;
	}

	public int getTotalAmountForClass(StudentConcessionVo feevo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionDaoImpl: getTotalAmountForClass : Starting");

		Connection conn=null;
		PreparedStatement psmt5 =null;
		ResultSet rs5=null;
		int value = 0;
		String dbcolumn = null;

		try{
			
			String previous = HelperClass.getPreAcadamicYearId(userLoggingsVo);
			boolean stutype = HelperClass.getStudentType(previous, feevo.getStudentId(), userLoggingsVo);

			if(stutype){
				dbcolumn = "feeAmount";
			}else{
				dbcolumn = "newstufeeAmount";
			}
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);

			psmt5 = conn.prepareStatement("SELECT SUM("+dbcolumn+") AS totalfees FROM `campus_fee_setupdetails` fsd JOIN `campus_fee_setup` fs ON fs.`feeSettingcode` = fsd.`feeSettingCode` WHERE fs.`ClassCode` = ? AND `AccyearCode`=? AND `locationId`=?");
			psmt5.setString(1, feevo.getClassId());
			psmt5.setString(2, feevo.getAcademicId());
			psmt5.setString(3, feevo.getLocationId());
			rs5 = psmt5.executeQuery();
			while(rs5.next()){
				value = rs5.getInt("totalfees");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs5!=null && ! rs5.isClosed()){
					rs5.close();
				}
				if(psmt5!=null && ! psmt5.isClosed()){
					psmt5.close();
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
				+ " Control in FeeCollectionDaoImpl: getTotalAmountForClass: Ending");

		return value;
	
	}

}
