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

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.AmountInWords;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class feeCollectionNewDaoImpl {

	private static final Logger logger = Logger.getLogger(feeCollectionNewDaoImpl.class);

	public FeeCollectionVo getNewFeeCollectionAmount(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: getNewFeeCollectionAmount : Starting");

		PreparedStatement getfeesetup = null;
		ResultSet rsgetfeesetup = null;
		PreparedStatement getfeesetupdetails = null;
		ResultSet rsgetfeesetupdetails = null;
		PreparedStatement geclasstfee = null;
		ResultSet rsgeclasstfee = null;
		PreparedStatement isfeepaid = null;
		ResultSet rsisfeepaid = null;
		PreparedStatement feeindetail = null;
		ResultSet feeindetailrs = null;
		Connection conn = null;
		FeeCollectionVo collectionvo = new FeeCollectionVo();
		ArrayList<FeeNameVo> list = new ArrayList<FeeNameVo>();
		int isfound = 0;
		String isPaid = null;
		try{
			String accyear = feeCodeDetails.split(",")[1];
			String stuId = feeCodeDetails.split(",")[0];
			String classId = feeCodeDetails.split(",")[3];
			String locId = feeCodeDetails.split(",")[2];
			String specId = feeCodeDetails.split(",")[4];
			String feeSettingcode = null;
			String termId = null;
			String styType = null;
			String termName = null;
			double lstyrtotfee = 0.0;
			double lstyrstutotfees = 0.0;
			double lstyrstutotconn = 0.0;
			double lstyrstutotdis = 0.0;
			double lstyrstutotfine = 0.0;
			double lstyrstutotpaid = 0.0;
			double due = 0.0;
			double advanceAmt = 0.0;
			String feecolcode = null;
			int latedays = 0;
			int feestatus = 0;
			String paymentMode=null;
			String isTransferInMiddle="No";
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			PreparedStatement getcuryrfeepaidst = conn.prepareStatement("SELECT COUNT(*) FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ?");
			getcuryrfeepaidst.setString(1, stuId);
			getcuryrfeepaidst.setString(2, accyear);
		//	System.out.println("79 : "+ getcuryrfeepaidst);
			ResultSet curyrfeepaidstrs = getcuryrfeepaidst.executeQuery();
			while(curyrfeepaidstrs.next()){
				feestatus = curyrfeepaidstrs.getInt(1);
			}
			//System.out.println("====feestatusfeestatusfeestatusfeestatusfeestatus===" +feestatus);
			if(feestatus == 0){
				String preaccyear = null;
				//System.out.println("====feestatusfeestatusfeestatusfeestatusfeestatus===");
				preaccyear = HelperClass.getPreAcadamicYearId(userLoggingsVo);

				if(preaccyear!=null){
					PreparedStatement stduentexists = conn.prepareStatement("SELECT classdetail_id_int,classsection_id_int FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ?");
					stduentexists.setString(1,stuId);
					stduentexists.setString(2,preaccyear);

					//System.out.println("stduentexists=="+stduentexists);
					ResultSet rs = stduentexists.executeQuery();
					if(rs.next()){
						PreparedStatement getstufeestotal = conn.prepareStatement("SELECT SUM(totalamount)totalamount,SUM(actualamount) actualamt,SUM(concessionAmt)concessionAmt,SUM(discount)discount,SUM(amount_paid)amount_paid,SUM(balance_amount)balance_amount,SUM(fineAmount)fineAmount FROM campus_fee_collection WHERE admissionNo = ?  AND accYear = ? ");
						getstufeestotal.setString(1,stuId);
						getstufeestotal.setString(2,preaccyear);
					//	System.out.println("101 : "+ getstufeestotal);
						ResultSet stufeestotalrs = getstufeestotal.executeQuery();
						while(stufeestotalrs.next()){
							lstyrstutotfees = stufeestotalrs.getDouble("totalamount");
							lstyrstutotconn = stufeestotalrs.getDouble("concessionAmt");
							lstyrstutotdis = stufeestotalrs.getDouble("discount");
							lstyrstutotfine = stufeestotalrs.getDouble("fineAmount");
							lstyrstutotpaid = stufeestotalrs.getDouble("amount_paid");
						}
						////System.out.println("lstyrstutotfees:: "+lstyrstutotfees);
						////System.out.println("lstyrstutotconn :: "+lstyrstutotconn);
						//.out.println("lstyrstutotdis :: " +lstyrstutotdis);
						////System.out.println("lstyrstutotfine:: "+lstyrstutotfine);

						lstyrstutotfees = (lstyrstutotpaid  + lstyrstutotdis) - lstyrstutotfine;

						//System.out.println("lstyrstutotfees::: "+lstyrstutotfees);

						PreparedStatement getfeestotal = conn.prepareStatement("SELECT SUM(feeAmount) as total,Termcode,fs.feeSettingcode FROM campus_fee_setupdetails fst JOIN campus_fee_setup fs ON fs.feeSettingcode = fst.feeSettingCode WHERE fst.feeSettingCode IN (SELECT feeSettingcode FROM campus_fee_setup WHERE ClassCode = ? AND AccyearCode = ?) GROUP BY fst.feeSettingCode");
						getfeestotal.setString(1,rs.getString("classdetail_id_int"));
						getfeestotal.setString(2,preaccyear);
					//	System.out.println("feestotalrs === "+getfeestotal);
						ResultSet feestotalrs = getfeestotal.executeQuery();
						
						while(feestotalrs.next()){
							lstyrtotfee = lstyrtotfee + feestotalrs.getDouble("total");
						}
					}
					due = lstyrtotfee - lstyrstutotfees;
				}
			}

			PreparedStatement pstmt20 = conn.prepareStatement("SELECT isTransferInMiddle FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ?");
			pstmt20.setString(1,stuId);
			pstmt20.setString(2,accyear);
			//System.out.println("pstmt20 === "+pstmt20);
			ResultSet rs20=pstmt20.executeQuery();
			if(rs20.next()) {
				isTransferInMiddle=rs20.getString("isTransferInMiddle");
			}
			rs20.close();
			pstmt20.close();
			getfeesetup = conn.prepareStatement("SELECT count(*) FROM campus_fee_setup WHERE ClassCode = ? AND AccyearCode = ? AND locationId = ? AND specialization = ?");
			getfeesetup.setString(1,classId);
			getfeesetup.setString(2,accyear);
			getfeesetup.setString(3,locId);
			getfeesetup.setString(4,specId);
		//	System.out.println("1 "+getfeesetup);
			rsgetfeesetup = getfeesetup.executeQuery();
			
			while(rsgetfeesetup.next()){

				if(rsgetfeesetup.getInt(1) >0){
					Boolean type = HelperClass.getStudentType(accyear,stuId,userLoggingsVo);
					if(type){
						styType = "newstufeeAmount";
					}else{
						styType = "feeAmount";
					}

				//	System.err.println("styType :  "+styType);

					if(isTransferInMiddle.equalsIgnoreCase("Yes")) {
						getfeesetupdetails=conn.prepareStatement("SELECT cfs.feeSettingcode,cfs.TermCode,cft.termname,TIMESTAMPDIFF(DAY,cft.startdate,NOW( )) lateDay FROM campus_fee_setup cfs JOIN campus_fee_termdetails cft ON cfs.TermCode=cft.termid JOIN `campus_student_classdetails` csc ON cfs.ClassCode=csc.classdetail_id_int JOIN  `campus_student_transfer_fee_applicaple` cstfa ON cstfa.student_id_int=csc.student_id_int AND cfs.TermCode=cstfa.termid WHERE cfs.ClassCode=? AND cfs.AccyearCode=? AND cfs.specialization=? AND cfs.locationId=? AND csc.student_id_int=? AND cft.isActive='Y' ORDER BY CAST(SUBSTR(cfs.TermCode,4) AS UNSIGNED)");
						getfeesetupdetails.setString(1, classId);
						getfeesetupdetails.setString(2, accyear);
						getfeesetupdetails.setString(3, specId);
						getfeesetupdetails.setString(4, locId);
						getfeesetupdetails.setString(5, stuId);
					}
					else {
						getfeesetupdetails = conn.prepareStatement("SELECT cfs.feeSettingcode,cfs.TermCode,cft.termname,DATEDIFF(CURDATE(),cft.startdate) lateDay FROM campus_fee_setup cfs JOIN campus_fee_termdetails cft ON cfs.TermCode=cft.termid WHERE cfs.ClassCode=? AND cfs.AccyearCode=? AND cfs.specialization=? AND cfs.locationId=? ORDER BY CAST(SUBSTR(cfs.TermCode,4) AS UNSIGNED)");
						getfeesetupdetails.setString(1,classId);
						getfeesetupdetails.setString(2,accyear);
						getfeesetupdetails.setString(3,specId);
						getfeesetupdetails.setString(4,locId);
					}

				//	System.out.println("getfeesetupdetails===="+getfeesetupdetails);

					rsgetfeesetupdetails = getfeesetupdetails.executeQuery();
					while(rsgetfeesetupdetails.next()){
						feeSettingcode = rsgetfeesetupdetails.getString("feeSettingcode");
						termId = rsgetfeesetupdetails.getString("Termcode");
						termName = rsgetfeesetupdetails.getString("termname");

						latedays = Integer.parseInt(rsgetfeesetupdetails.getString("lateDay"));

					//	System.out.println("latedays----- "+latedays);
						double fineAmount = 0.0;
						double conceAmount = 0.0;
						int fiCount = 0;
						PreparedStatement finecount=conn.prepareStatement("SELECT COUNT(*) FROM campus_fineconfiguration where classId=? and termid=?");
						finecount.setString(1, classId);
						finecount.setString(2, termId);


						ResultSet countRs=finecount.executeQuery();
						while(countRs.next()){
							fiCount=countRs.getInt(1);
						}
						if(fiCount>0){
							PreparedStatement finep=conn.prepareStatement("SELECT * FROM campus_fineconfiguration where classId=? and termid=? ORDER BY date");
							finep.setString(1, classId);
							finep.setString(2, termId);
							ResultSet finers=finep.executeQuery();
							while(finers.next()){
								String isApp=finers.getString("IsApplicable");
								if(isApp.equalsIgnoreCase("Y")){
									if(HelperClass.getCurrentSqlDate().compareTo(finers.getDate("date")) > 0){
										fineAmount=finers.getDouble("amount");
										//System.out.println("Table"+fineAmount);
									}
									else{
										fineAmount=0.0;
									}

								}
								else{
									fineAmount=0.0;
								}
							}
						}
						else{
							fineAmount=0.0;
						}


						//System.out.println("fineAmount" + fineAmount);

						String concessionType = null;
						PreparedStatement concessionStatement = null;

						PreparedStatement sclpstmt = conn.prepareStatement("SELECT concessionType FROM campus_scholorship WHERE studentId=? AND academic_year=? AND classId=?");
						sclpstmt.setString(1, stuId);
						sclpstmt.setString(2, accyear);
						sclpstmt.setString(3, classId);
						ResultSet sclRs = sclpstmt.executeQuery();
						System.out.println(sclpstmt);
						while (sclRs.next()) {
							concessionType = sclRs.getString("concessionType");
						}

						if (concessionType !=null && concessionType.equalsIgnoreCase("2")) {
							concessionStatement = conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE studentId=? AND academic_year=? AND classId=? ");
							concessionStatement.setString(1, stuId);
							concessionStatement.setString(2, accyear);
							concessionStatement.setString(3, classId);
							ResultSet concessionRs = concessionStatement.executeQuery();

							while (concessionRs.next()) {
								conceAmount = concessionRs.getDouble("concession");
							}
						}else if(concessionType !=null && concessionType.equalsIgnoreCase("3")) {
							concessionStatement = conn.prepareStatement("SELECT concession FROM campus_scholorship WHERE studentId=? AND academic_year=? AND classId=? AND termcode=?");
							concessionStatement.setString(1, stuId);
							concessionStatement.setString(2, accyear);
							concessionStatement.setString(3, classId);
							concessionStatement.setString(4, termId);
							ResultSet concessionRs = concessionStatement.executeQuery();
System.out.println(concessionStatement);
							while (concessionRs.next()) {
								conceAmount = concessionRs.getDouble("concession");
							}
						}


						//to get advance_amount(current year)
						String paymode =null;
						PreparedStatement	ps_advanceAmt = conn.prepareStatement("SELECT `advance_amt`,pay_mode FROM `campus_feepaid_details` WHERE `stu_id` =? AND `accyear_id` = ? ORDER BY `receiptNo` DESC LIMIT 1");
						ps_advanceAmt.setString(1, stuId);
						ps_advanceAmt.setString(2, accyear);
						ResultSet rs_advanceAmt = ps_advanceAmt.executeQuery();
						if(rs_advanceAmt.next()){
							advanceAmt = rs_advanceAmt.getDouble(1);
							paymode  = rs_advanceAmt.getString(2);
						}

						double totalamt = 0.0;
						double actualamount = 0.0;
						double balance_amount = 0.0;
						double amount_paid = 0.0;

						String feeCollectionCode = null;

						//to check whether the student has paid the fees or not if yes execute this query
						isfeepaid = conn.prepareStatement("SELECT feeCollectionCode,totalamount,actualamount,concessionAmt,balance_amount,amount_paid,is_paid,fineAmount,concessionAmt,advance_amount FROM campus_fee_collection WHERE admissionNo=? AND accYear = ? AND termcode = ?");
						isfeepaid.setString(1,stuId);
						isfeepaid.setString(2,accyear);
						isfeepaid.setString(3,termId);
						rsisfeepaid = isfeepaid.executeQuery();
						while(rsisfeepaid.next()){
							totalamt = rsisfeepaid.getDouble("totalamount");
							actualamount = rsisfeepaid.getDouble("actualamount");
							balance_amount = rsisfeepaid.getDouble("balance_amount");
							due = due+balance_amount;
							amount_paid = rsisfeepaid.getDouble("amount_paid");
							isPaid = rsisfeepaid.getString("is_paid");
							feeCollectionCode = rsisfeepaid.getString("feeCollectionCode");
							feecolcode = feeCollectionCode;
							//advanceAmt = rsisfeepaid.getDouble("advance_amount");
							//feeindetail = conn.prepareStatement("SELECT fine,FeeInDetailedCode,conc_amount,chln_no,totalamount,amount_paid,balance_amount,paidDate,payment_type,dd_cheque_no,dd_cheque_date,dd_cheque_bankname FROM campus_fee_indetail WHERE feecollection_code = ? ORDER BY chln_no");
							//feeindetail.setString(1,feeCollectionCode);
							//feeindetailrs = feeindetail.executeQuery();

							//while(feeindetailrs.next()){

							FeeNameVo vo = new FeeNameVo();
							//vo.setFeeindetailId(feeindetailrs.getString("FeeInDetailedCode"));

							//vo.setAdvanceCarry(rsisfeepaid.getDouble("advance_amount"));
							//vo.setAdvanceCarry(advanceAmt);
							vo.setPaidAmt(rsisfeepaid.getDouble("amount_paid"));
							vo.setTotalFeeAmt(totalamt); //need to find the fee concession
							vo.setActualAmt(actualamount); //actual amt may vary depending upon the concession
							vo.setDueCarry(balance_amount);
							//vo.setPaidDate(HelperClass.convertDatabaseToUI(feeindetailrs.getString("paidDate")));
							//vo.setPaymode(feeindetailrs.getString("payment_type"));
							//vo.setDddate(feeindetailrs.getString("dd_cheque_date"));
							//.setDdno(feeindetailrs.getString("dd_cheque_no"));
							//vo.setBankname(feeindetailrs.getString("dd_cheque_bankname"));
							//vo.setRecieptNo(feeindetailrs.getInt("chln_no"));
							vo.setTotalFeeAmt(totalamt);
							vo.setTerm(termName);
							vo.setTermId(termId);
							if(balance_amount==0.0)
								vo.setStatus("Paid");
							else
								vo.setStatus("Pending");
							vo.setFeesettingCode(feeCollectionCode);
							vo.setFineAmount(rsisfeepaid.getDouble("fineAmount"));
							vo.setConcessionAmt(rsisfeepaid.getDouble("concessionAmt"));
							list.add(vo);
							//}

							/*if(isPaid.equalsIgnoreCase("P")){
							FeeNameVo vo = new FeeNameVo();
							vo.setPaidAmt(0.0);
							vo.setDueCarry(balance_amount);
							vo.setTotalFeeAmt(totalamt);// need to find the fee concession
							vo.setActualAmt(actualamount);//actual amt may vary depending upon the concession
							vo.setTerm(termName);
							vo.setTermId(termId);
							vo.setStatus("Not Paid");
							vo.setFeesettingCode(feeCollectionCode);
							vo.setFeeindetailId("");
							vo.setPaidDate("-");
							list.add(vo);
						}*/
						}



						//if stduent has not paid the fees first time
						if(feeCollectionCode == null)
						{
							/*String preaccyear = accyear.substring(0,3)+(Integer.parseInt(accyear.substring(3,4))-1);
						PreparedStatement getaccyid  = conn.prepareStatement("SELECT COUNT(*) FROM campus_acadamicyear WHERE acadamic_id = ?");
						getaccyid.setString(1,preaccyear);
						ResultSet accyid = getaccyid.executeQuery();
						if(accyid.next()){
							PreparedStatement getdueamt = conn.prepareStatement("SELECT SUM(balance_amount)as bal FROM campus_fee_collection WHERE admissionNo = ? AND accYear = ? AND is_paid = 'P'");
							getdueamt.setString(1,stuId);
							getdueamt.setString(2,preaccyear);
							ResultSet getduers = getdueamt.executeQuery();
							if(getduers.next()){
								due = getduers.getDouble(1);
							}
						}*/

							geclasstfee = conn.prepareStatement("SELECT SUM("+styType+")as total,t.termid,t.termname FROM campus_fee_setupdetails fc JOIN campus_fee_setup fs ON fs.feeSettingcode = fc.feeSettingCode JOIN campus_fee_termdetails t ON t.termid = fs.Termcode JOIN campus_fee_master fm ON fc.feecode = fm.FeeCode WHERE fc.feeSettingCode = ?");
							geclasstfee.setString(1,feeSettingcode);
							rsgeclasstfee = geclasstfee.executeQuery();
							while(rsgeclasstfee.next()){
								FeeNameVo vo = new FeeNameVo();
								vo.setPaidAmt(0.0);
								vo.setDueCarry(0.0);
								vo.setPredues(due);
								vo.setTotalFeeAmt(rsgeclasstfee.getInt(1));// need to find the fee concession
								vo.setActualAmt(rsgeclasstfee.getInt(1));//actual amt may vary depending upon the concession
								vo.setTerm(rsgeclasstfee.getString("termname"));
								vo.setTermId(rsgeclasstfee.getString("termid"));
								vo.setStatus("Not Paid");
								vo.setFeesettingCode("");
								vo.setFeeindetailId("");
								vo.setPaidDate("-");
								vo.setFineAmount(fineAmount);
								vo.setConcessionAmt(conceAmount);
								vo.setPrefeecolcode(feecolcode);
								vo.setAdvanceCarry(advanceAmt);
								vo.setPaymode(paymode);
								//System.out.println("getpaymode############## daoIMl "+vo.getPaymode());
								list.add(vo);
								due = 0.0;
								advanceAmt = 0.0;
								feecolcode = null;
							}
							collectionvo.setFeeNamelist(list);
						}else{
							collectionvo.setFeeNamelist(list);
						}
					}
				}
				else{
					collectionvo.setReason("notfound");
				}
			}
			PreparedStatement ps_insertPlan = conn.prepareStatement("select s.student_id_int,a.acadamic_year,cstd.student_imgurl_var imgurl,cstd.classdetail_id_int,cstd.specilization,s.student_doj_var,concat(s.student_fname_var,' ',s.student_lname_var) studentname,s.student_admissionno_var,a.acadamic_year,c.classdetails_name_var,sec.classsection_name_var,conc.percentage from campus_acadamicyear a,campus_classdetail c,campus_classsection sec,campus_student s left outer join campus_fee_concessiondetails conc on s.student_scholorship_var=conc.concessionid left join campus_student_classdetails cstd on s.student_id_int=cstd.student_id_int where  c.classdetail_id_int=cstd.classdetail_id_int and c.locationId=cstd.locationId and sec.classsection_id_int=cstd.classsection_id_int and s.student_id_int=? and cstd.fms_acadamicyear_id_int=a.acadamic_id and a.acadamic_id=? and cstd.locationId=?");
			ps_insertPlan.setString(1, stuId);
			ps_insertPlan.setString(2, accyear);
			ps_insertPlan.setString(3, locId);
			ResultSet rs = ps_insertPlan.executeQuery();
			if(rs.next()){
				collectionvo.setStudentid(stuId);
				collectionvo.setAddmissionno(rs.getString("student_admissionno_var"));
				//collectionvo.setStudentIdNo(rs.getString("student_id_no"));
				collectionvo.setStudentname(rs.getString("studentname"));
				collectionvo.setClassname(rs.getString("classdetails_name_var"));
				classId = rs.getString("classdetail_id_int");
				collectionvo.setClassId(classId);
				collectionvo.setSpecialization(rs.getString("specilization"));
				collectionvo.setSectionname(rs.getString("classsection_name_var"));
				collectionvo.setAccYear(accyear);
				collectionvo.setAccYearname(rs.getString("acadamic_year"));
				collectionvo.setConcession(rs.getDouble("percentage"));
				collectionvo.setImgurl(rs.getString("imgurl"));
				collectionvo.setDoj(rs.getString("student_doj_var"));

			}
			else {
				collectionvo=null;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: getNewFeeCollectionAmount: Ending");

		return collectionvo;
	}

	public FeeCollectionVo paymenthistory(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: paymenthistory Starting");
		Connection conn =null;
		PreparedStatement isfeepaid = null;
		ResultSet rsisfeepaid = null;
		int feestatus = 0;
		FeeCollectionVo collectionvo = new FeeCollectionVo();
		ArrayList<FeeNameVo> list = new ArrayList<FeeNameVo>();
		try{
			String accyear = feeCodeDetails.split(",")[1];
			String stuId = feeCodeDetails.split(",")[0];
			String classId = feeCodeDetails.split(",")[3];
			String locId = feeCodeDetails.split(",")[2];
			String specId = feeCodeDetails.split(",")[4];

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			String feeCollectionCode = null;

			isfeepaid = conn.prepareStatement("SELECT termId,paiddate,`receiptNo`,`total_Amt`,`discount`,`concession`,`fine`,`Amt_paid`,`bal_amt`,`advance_amt`,pay_mode,dd_cheque_no,dd_cheque_date,bankname FROM `campus_feepaid_details` WHERE `stu_id` = ? AND `accyear_id` = ? ORDER BY receiptNo DESC");
			isfeepaid.setString(1,stuId);
			isfeepaid.setString(2,accyear);
			rsisfeepaid = isfeepaid.executeQuery();
			//System.out.println(isfeepaid);
			while(rsisfeepaid.next()){
				feestatus = 1;
				FeeNameVo vo = new FeeNameVo();
				String termname=getTermsName(rsisfeepaid.getString("termId"),accyear,locId,userLoggingsVo);
				vo.setTerm(termname);
				vo.setDisAmt(rsisfeepaid.getDouble("discount"));
				vo.setAdvanceCarry(rsisfeepaid.getDouble("advance_amt"));
				vo.setPaidAmt(rsisfeepaid.getDouble("Amt_paid"));
				vo.setTotalFeeAmt(rsisfeepaid.getDouble("total_Amt")); //need to find the fee concession
				vo.setDueCarry(rsisfeepaid.getDouble("bal_amt"));
				vo.setPaidDate(HelperClass.convertDatabaseToUI(rsisfeepaid.getString("paiddate")));
				vo.setPaymode(rsisfeepaid.getString("pay_mode"));
				vo.setDddate(rsisfeepaid.getString("dd_cheque_date"));
				vo.setDdno(rsisfeepaid.getString("dd_cheque_no"));
				vo.setBankname(rsisfeepaid.getString("bankname"));
				vo.setRecieptNo(rsisfeepaid.getInt("receiptNo"));
				vo.setFeesettingCode(feeCollectionCode);
				vo.setFineAmount(rsisfeepaid.getDouble("fine"));
				vo.setConcessionAmt(rsisfeepaid.getDouble("concession"));
				list.add(vo);
			}
			if(feestatus == 0){
				collectionvo.setStatus("notpaid");
			}else{
				collectionvo.setFeeNamelist(list);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rsisfeepaid != null && (!rsisfeepaid.isClosed())) {
					rsisfeepaid.close();
				}
				if (isfeepaid != null && (!isfeepaid.isClosed())) {
					isfeepaid.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: paymenthistory Ending");
		return collectionvo;
	}



	private String getTermsName(String termsId, String accyear, String locId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: getFeeNewPaidDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String termsname="";
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			String comma=",";
			int count=0;
			String termid[]=termsId.split(",");
			for(int i=0;i<termid.length;i++){
				count++;
				pstmt=conn.prepareStatement("SELECT `termname` FROM `campus_fee_termdetails` WHERE `termid`=? AND `accyear`=? AND `locationId`=?");
				pstmt.setString(1, termid[i]);
				pstmt.setString(2, accyear);
				pstmt.setString(3, locId);
				rs=pstmt.executeQuery();
				while(rs.next()){
					if(count > 1){
						termsname=termsname+comma+rs.getString("termname");
					}else{
						termsname=termsname+rs.getString("termname");
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: getFeeNewPaidDetails Ending");
		return termsname;
	}

	public List<StudentRegistrationVo> getStudentDetailsByJs(StudentRegistrationVo data, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl : getStudentDetailsByJs  Starting");
		List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			if (data.getSearchTerm().equalsIgnoreCase("all") && data.getLocationId().equalsIgnoreCase("all")) {
				pst = conn.prepareStatement("select distinct csc.specilization,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.classdetail_id_int,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where stu.`student_status_var`='active' AND csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType ='1' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getAcademicYear());
				pst.setString(2, data.getAcademicYear());
			} else if (data.getSearchTerm().equalsIgnoreCase("all") && data.getClasscode().equalsIgnoreCase("all")) {
				pst = conn.prepareStatement("select distinct csc.specilization,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.classdetail_id_int,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where stu.`student_status_var`='active' AND csc.locationId=? and csc.fms_acadamicyear_id_int=?  and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType ='1' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getAcademicYear());
			} else if (data.getSearchTerm().equalsIgnoreCase("all") && data.getSection_id().equalsIgnoreCase("all")) {
				pst = conn.prepareStatement("select distinct csc.specilization,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.student_status,csc.classdetail_id_int,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var, case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where stu.`student_status_var`='active' AND csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType ='1' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getAcademicYear());
			} else if (data.getSearchTerm().equalsIgnoreCase("all")) {
				pst = conn.prepareStatement("select distinct csc.specilization,ca.acadamic_year,stu.student_dob_var,csc.student_imgurl_var imgurl,csc.classdetail_id_int,csc.student_status,csc.locationId,csc.fms_acadamicyear_id_int,stu.student_id_int,stu.student_admissionno_var,case when stu.student_lname_var is null then stu.student_fname_var else concat(stu.student_fname_var,' ',stu.student_lname_var) end studentname,case when csc.student_rollno is null then '-' else csc.student_rollno end student_rollno,ccd.classdetails_name_var,ccs.classsection_name_var from campus_student stu join campus_student_classdetails csc on stu.student_id_int=csc.student_id_int join campus_classdetail ccd on (csc.classdetail_id_int=ccd.classdetail_id_int and csc.locationId=ccd.locationId) join campus_classsection ccs on csc.classsection_id_int=ccs.classsection_id_int left join campus_acadamicyear ca on csc.fms_acadamicyear_id_int=ca.acadamic_id where stu.`student_status_var`='active' AND csc.locationId=? and csc.fms_acadamicyear_id_int=? and csc.classdetail_id_int=? and ccs.classsection_id_int=? and stu.student_admissionno_var NOT IN(select admissionNo from campus_scholorship where concessionType ='1' and academic_year=?) order by CAST(SUBSTR(csc.fms_acadamicyear_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.locationId,4) AS UNSIGNED),CAST(SUBSTR(csc.classdetail_id_int,4) AS UNSIGNED),CAST(SUBSTR(csc.classsection_id_int,4) AS UNSIGNED),studentname");
				pst.setString(1, data.getLocationId());
				pst.setString(2, data.getAcademicYear());
				pst.setString(3, data.getClasscode());
				pst.setString(4, data.getSection_id());
				pst.setString(5, data.getAcademicYear());
			} else {
				pst = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_STUDENT_LIST_SEARCH_BY_JS_FOR_FEE);
				if (data.getLocationId().equalsIgnoreCase("all")) {
					pst.setString(1, "%%");
				} else {
					pst.setString(1, data.getLocationId());
				}

				if (data.getAcademicYear().equalsIgnoreCase("all")) {
					pst.setString(2, "%%");
				} else {
					pst.setString(2, data.getAcademicYear());
				}
				if (data.getClasscode().equalsIgnoreCase("all")) {
					pst.setString(3, "%%");
				} else {
					pst.setString(3, data.getClasscode());
				}
				if (data.getSection_id().equalsIgnoreCase("all")) {
					pst.setString(4, "%%");
				} else {
					pst.setString(4, data.getSection_id());
				}

				pst.setString(5, data.getSearchTerm() + "%");
				pst.setString(6, data.getSearchTerm() + "%");
				pst.setString(7, data.getSearchTerm() + "%");
				pst.setString(8, data.getSearchTerm() + "%");
				pst.setString(9, data.getSearchTerm() + "%");
				pst.setString(10, data.getSearchTerm() + "%");
				pst.setString(11, data.getSearchTerm() + "%");
				pst.setString(12, data.getSearchTerm() + "%");
				pst.setString(13, data.getSearchTerm() + "%");
				pst.setString(14, data.getSearchTerm() + "%");
				pst.setString(15, data.getAcademicYear());

			}
			System.out.println("7777777777777777777"+pst);
			rs = pst.executeQuery();

			while (rs.next()) {
				ArrayList<FeeNameVo> feeStatusList = new ArrayList<FeeNameVo>();
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				sno++;
				registrationVo.setSno(String.valueOf(sno));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setStudentId(rs.getString("student_id_int"));
				registrationVo.setLocationId(rs.getString("locationId"));
				registrationVo.setImage(rs.getString("imgurl"));
				registrationVo.setClassDetailId(rs.getString("classdetail_id_int"));
				registrationVo.setSpecilization(rs.getString("specilization"));
				registrationVo.setAcademicYearId(rs.getString("fms_acadamicyear_id_int"));
				registrationVo.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				registrationVo.setStudentFullName(rs.getString("studentname"));
				registrationVo.setStudentrollno(rs.getString("student_rollno"));
				registrationVo.setClassname(rs.getString("classdetails_name_var"));
				registrationVo.setSectionnaem(rs.getString("classsection_name_var"));
				registrationVo.setAcademicYear(rs.getString("acadamic_year"));
				registrationVo.setClassSectionId(rs.getString("classdetails_name_var") + "-" + rs.getString("classsection_name_var"));
				registrationVo.setDateofBirth(rs.getString("student_dob_var"));
				PreparedStatement termpstmt = conn.prepareStatement("SELECT termid,termname FROM `campus_fee_termdetails` WHERE locationId=? AND accyear=?");
				termpstmt.setString(1, rs.getString("locationId"));
				termpstmt.setString(2, data.getAcademicYear());
				ResultSet termRs = termpstmt.executeQuery();
				while (termRs.next()) {
					int getcount = 0;
					String paymentStatus = "Not Paid";
					FeeNameVo fStatus = new FeeNameVo();
					PreparedStatement getStatus = conn.prepareStatement("SELECT COUNT(*) FROM  campus_fee_collection WHERE termcode=? AND admissionNo=?");
					getStatus.setString(1, termRs.getString("termid"));
					getStatus.setString(2, rs.getString("student_id_int"));
					ResultSet getstRs = getStatus.executeQuery();
					if (getstRs.next()) {
						getcount = getstRs.getInt(1);
					}
					if (getcount > 0) {
						paymentStatus = "Paid";
					}
					fStatus.setTerm(termRs.getString("termname"));
					fStatus.setStatus(paymentStatus);
					feeStatusList.add(fStatus);
				}
				registrationVo.setFeeStatus(feeStatusList);

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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl : getStudentDetailsByJs  Ending");

		return list;
	}



	public String paybalFees(FeeCollectionVo vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : paybalFees Starting");

		PreparedStatement pstmObj = null;
		Connection conn = null;

		int result = 0;
		String status = null;
		double advanceAmt = 0;
		int result1 = 0;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			int receiptno = getReceiptNo(conn);
			if(vo.getTot_paid_amt() > vo.getTot_actual_amt()){
				advanceAmt = vo.getTot_paid_amt() - vo.getTot_actual_amt();
			}

			pstmObj = conn.prepareStatement("UPDATE `campus_fee_collection` SET `amount_paid` = `amount_paid` + ? , `balance_amount` = `balance_amount` - ?,advance_amount=advance_amount+?  WHERE `feeCollectionCode` = ?");
			pstmObj.setDouble(1, vo.getTot_paid_amt());
			pstmObj.setDouble(2, vo.getTot_paid_amt()-advanceAmt);
			pstmObj.setDouble(3, advanceAmt);
			pstmObj.setString(4, vo.getFeecolcode());
			int count = pstmObj.executeUpdate();

			pstmObj.close();
			if(count > 0){
				pstmObj = conn.prepareStatement("INSERT INTO `campus_fee_indetail` (`FeeInDetailedCode`,`feecollection_code`,`admissionNo`,`term_id`,`accYear`,`totalamount`,`actualamount`,`balance_amount`,`amount_paid`,`paidDate`,`payment_type`,`dd_cheque_no`,`dd_cheque_date`,`dd_cheque_bankname`,createdby,chln_no,`cashamt`,`cardamt`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmObj.setString(1, IDGenerator.getPrimaryKeyID("campus_fee_indetail",userLoggingsVo));
				pstmObj.setString(2, vo.getFeecolcode());
				pstmObj.setString(3, vo.getAddmissionno());
				pstmObj.setString(4, vo.getTermid());
				pstmObj.setString(5, vo.getAccYear());
				pstmObj.setDouble(6, vo.getTot_actual_amt());
				pstmObj.setDouble(7, vo.getTot_actual_amt());
				pstmObj.setDouble(8, (vo.getTot_actual_amt()+advanceAmt)-vo.getTot_paid_amt());
				pstmObj.setDouble(9, vo.getTot_paid_amt());
				pstmObj.setDate(10, HelperClass.getCurrentSqlDate());
				pstmObj.setString(11, vo.getPaymentMode());
				pstmObj.setString(12, vo.getPaymentPatriculars());
				pstmObj.setString(13, vo.getDd_cheque_date());
				pstmObj.setString(14, vo.getDd_cheque_bank());
				pstmObj.setString(15, vo.getUserID());
				pstmObj.setInt(16, receiptno);
				pstmObj.setDouble(17, vo.getCashAmount());
				pstmObj.setDouble(18, vo.getCardAmount());
				result = pstmObj.executeUpdate();
				pstmObj.close();

				pstmObj = conn.prepareStatement("INSERT INTO `campus_feepaid_details` (`receiptNo`,`stu_id`,`total_Amt`,`discount`,`concession`,`fine`,`Amt_paid`,`bal_amt`,`advance_amt`,accyear_id,paiddate,termId,isBalance,createdby,pay_mode,dd_cheque_no,`dd_cheque_date`,`bankname`,`cashamt`,`cardamt`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmObj.setInt(1, receiptno);
				pstmObj.setString(2, vo.getAddmissionno());
				pstmObj.setDouble(3, vo.getTot_actual_amt());
				pstmObj.setDouble(4, 0);
				pstmObj.setDouble(5, 0);
				pstmObj.setDouble(6, 0);
				pstmObj.setDouble(7, vo.getTot_paid_amt());
				pstmObj.setDouble(8, (vo.getTot_actual_amt()+advanceAmt)-vo.getTot_paid_amt());
				pstmObj.setDouble(9, advanceAmt);
				pstmObj.setString(10, vo.getAccYear());
				pstmObj.setDate(11, HelperClass.getCurrentSqlDate());
				pstmObj.setString(12, vo.getTermid());
				pstmObj.setString(13, "Y");
				pstmObj.setString(14, vo.getUserID());
				pstmObj.setString(15, vo.getPaymentMode());
				pstmObj.setString(16, vo.getPaymentPatriculars());
				pstmObj.setString(17, HelperClass.convertUIToDatabase(vo.getDd_cheque_date()));
				pstmObj.setString(18, vo.getDd_cheque_bank());
				pstmObj.setDouble(19, vo.getCashAmount());
				pstmObj.setDouble(20, vo.getCardAmount());
				result1 = pstmObj.executeUpdate();
				pstmObj.close();
			}

			if(result1 > 0){
				status = "success";
				double balAmt = 0.0;
				pstmObj = conn.prepareStatement("select balance_amount from campus_fee_collection where feeCollectionCode = ?");
				pstmObj.setString(1, vo.getFeecolcode());
				ResultSet rs = pstmObj.executeQuery();
				while(rs.next()){
					balAmt = rs.getDouble(1);
				}
				rs.close();
				pstmObj.close();
				if(balAmt == 0.0){
					pstmObj = conn.prepareStatement("UPDATE `campus_fee_collection` SET `is_paid` = 'Y' where feeCollectionCode = ?");
					pstmObj.setString(1, vo.getFeecolcode());
					pstmObj.executeUpdate();
				}
			}else{
				status = "fail";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : paybalFees Ending");

		return status;

	}

	public int getReceiptNo(Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : getReceiptNo Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int receiptNo =0;

		try{
			pstmt = conn.prepareStatement("SELECT MAX(`chln_no`) FROM `campus_fee_indetail`");
			rs = pstmt.executeQuery();
			while(rs.next()){
				receiptNo = rs.getInt(1)+1;
			}

		}catch(Exception e){
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
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} 
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : getReceiptNo Ending");
		return receiptNo;
	}



	public ArrayList<FeeNameVo> feeCollectionDetailsDues(String feeCodeDetails, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: feeCollectionDetailsDues : Starting");

		String studentId = feeCodeDetails.split(",")[0];
		String accyearId = feeCodeDetails.split(",")[1];
		String classId = feeCodeDetails.split(",")[2];
		String termId = feeCodeDetails.split(",")[3];
		String specialization = feeCodeDetails.split(",")[4];
		String feecollectioncode = feeCodeDetails.split(",")[5];

		PreparedStatement ps_feelist = null;
		ResultSet rs_feelist = null;

		Connection conn = null;
		ArrayList<FeeNameVo> feeCollectionList = new ArrayList<FeeNameVo>();
		int count = 0;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			ps_feelist = conn.prepareStatement(
					"SELECT fr.feeCode,`outstandingfee`,FeeName,feeAmount FROM `campus_fee_collectiondetails` fr JOIN campus_fee_master fm ON fm.FeeCode = fr.feeCode WHERE `outstandingfee` != 0 AND `feeCollectionCode` = ?; ");
			ps_feelist.setString(1, feecollectioncode);

			//System.out.println("fee name list :: " + ps_feelist);
			rs_feelist = ps_feelist.executeQuery();

			while (rs_feelist.next()) {
				count++;
				FeeNameVo feeNameVo = new FeeNameVo();

				feeNameVo.setSno(count);
				feeNameVo.setFeecode(rs_feelist.getString("feeCode"));
				feeNameVo.setFeename(rs_feelist.getString("FeeName"));
				feeNameVo.setOutStandingAmountArray(rs_feelist.getDouble("outstandingfee"));
				double actualamt = rs_feelist.getDouble("feeAmount");

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
				if (rs_feelist != null && (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null && (!ps_feelist.isClosed())) {
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: feeCollectionDetailsDues: Ending");

		return feeCollectionList;
	}



	public ArrayList<FeeCollectionVo> getFeeCollectionDetailsDiscount(String FeeDetails, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: getFeeCollectionDetails : Starting");

		String studentId = FeeDetails.split(",")[0];
		String accyearId = FeeDetails.split(",")[1];
		String classId = FeeDetails.split(",")[2];
		String termId = FeeDetails.split(",")[3];
		String specialization = FeeDetails.split(",")[4];

		PreparedStatement ps_feelist = null;
		ResultSet rs_feelist = null;

		Connection conn = null;
		ArrayList<FeeCollectionVo> list = new ArrayList<FeeCollectionVo>();

		int count = 0;
		String accyear = null;
		String doj = null;
		boolean flag = false;
		String column = null;
		try {


			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			flag = HelperClass.getStudentType(accyearId,studentId,userLoggingsVo);
			if(flag){
				column = "newstufeeAmount";
			}else{
				column = "feeAmount";
			}
			ps_feelist = conn.prepareStatement("select fm.FeeCode,fm.FeeName,fm.IsConcession,fm.feeType,fsd."+column+" "+"from campus_fee_master fm,campus_fee_setup fs,campus_fee_setupdetails fsd where fs.feeSettingcode=fsd.feeSettingCode and fm.FeeCode=fsd.feecode and fs.ClassCode=? and fs.AccyearCode=? and fs.Termcode=? and fs.specialization=? and"+" "+column+"!=0 ORDER BY feeType,FeeName");

			for(int i=0;i<termId.split("-").length;i++){
				ps_feelist.setString(1, classId);
				ps_feelist.setString(2, accyearId);
				ps_feelist.setString(3, termId.split("-")[i]);
				ps_feelist.setString(4, specialization);

				//System.out.println("fee name list :: " + ps_feelist);
				rs_feelist = ps_feelist.executeQuery();
				FeeCollectionVo collection = new FeeCollectionVo();
				ArrayList<FeeNameVo> feeCollectionList = new ArrayList<FeeNameVo>();
				while (rs_feelist.next()) {
					count++;
					FeeNameVo feeNameVo = new FeeNameVo();
					feeNameVo.setSno(count);
					feeNameVo.setFeecode(rs_feelist.getString("FeeCode"));
					feeNameVo.setFeename(rs_feelist.getString("FeeName"));
					feeNameVo.setFeetype(rs_feelist.getString("feeType"));
					double actualamt = rs_feelist.getDouble(column);
					feeNameVo.setActualAmt(actualamt);
					feeCollectionList.add(feeNameVo);

				}
				collection.setFeeNamelist(feeCollectionList);
				list.add(collection);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs_feelist != null && (!rs_feelist.isClosed())) {
					rs_feelist.close();
				}
				if (ps_feelist != null && (!ps_feelist.isClosed())) {
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: getFeeCollectionDetails: Ending");

		return list;
	}




	public FeeCollectionVo getFeeNewPaidDetails(FeeCollectionVo vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: getFeeNewPaidDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs = null,rs1 = null;

		FeeCollectionVo list = new FeeCollectionVo();
		ArrayList<FeeNameVo> col = new ArrayList<FeeNameVo>();
		int count=0;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			String amountinwords="",wordsamount="",isBalance="";
			String query="SELECT cfpd.`paiddate`,cfpd.`total_Amt`,cfpd.`Amt_paid`,cfpd.`bal_amt`,cfpd.`advance_amt`,cfpd.fine,cfpd.`pay_mode`,cfpd.`dd_cheque_no`,cfpd.`dd_cheque_date`,cfpd.`bankname`,cfpd.`cashamt`,cfpd.`cardamt`,cfpd.`isBalance`,CONCAT(ct.`FirstName`,' ',ct.`LastName`) username FROM `campus_feepaid_details` cfpd LEFT JOIN `campus_teachers` ct ON cfpd.`createdby`=ct.`TeacherID` WHERE `receiptNo`=? AND `stu_id`=? AND `accyear_id`=? ";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, vo.getReceiptno());
			pstmt.setString(2, vo.getStudentid());
			pstmt.setString(3, vo.getAccYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				list.setTot_actual_amt(rs.getDouble("total_Amt"));
				list.setTot_paid_amt(rs.getDouble("Amt_paid")+rs.getDouble("advance_amt")+rs.getDouble("fine"));
				list.setOpening_balance(rs.getDouble("bal_amt"));
				list.setAdvanceAmount(rs.getDouble("advance_amt"));
				AmountInWords aiw=new AmountInWords();
				wordsamount=aiw.convertToWords((int)list.getTot_paid_amt());
				amountinwords="RUPEES "+wordsamount.toUpperCase()+" ONLY";
				list.setAmountInWords(amountinwords);
				list.setPaymentMode(rs.getString("pay_mode"));
				list.setBankname(rs.getString("bankname"));
				list.setChequeno(rs.getString("dd_cheque_no"));

				if(rs.getString("dd_cheque_date") != null){
					if(!rs.getString("dd_cheque_date").equalsIgnoreCase("")){
						list.setDd_cheque_date(rs.getString("dd_cheque_date"));
					}
				}
				list.setUsername(rs.getString("username"));
				list.setBilldate(HelperClass.convertDatabaseToUI(rs.getString("paiddate")));
				list.setCashAmount(rs.getDouble("cashamt"));
				list.setCardAmount(rs.getDouble("cardamt"));
				list.setFineAmount(rs.getDouble("fine"));
				isBalance=rs.getString("isBalance");
			}
			if(isBalance.equalsIgnoreCase("Y")){
				pstmt1 = conn.prepareStatement("SELECT total_Amt from campus_feepaid_details WHERE `receiptNo` = ? ");
				pstmt1.setString(1, vo.getReceiptno());
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					count++;
					FeeNameVo vo2 = new FeeNameVo();
					vo2.setSno(count);
					vo2.setFeename("Balance Amount");
					vo2.setTotPaidAmt(rs1.getDouble("total_Amt"));
					col.add(vo2);
				}
				list.setFeeNamelist(col);

			}else{
				pstmt1 = conn.prepareStatement("SELECT SUM(`feeAmount`) as total,ft.`feeType` FROM `campus_fee_reciept` fr JOIN `campus_fee_type` ft ON ft.`feeTypeId` = fr.`feetype` WHERE `receipt_no` = ? GROUP BY fr.`feetype`");
				pstmt1.setString(1, vo.getReceiptno());
				rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					count++;
					FeeNameVo vo2 = new FeeNameVo();
					vo2.setSno(count);
					vo2.setFeename(rs1.getString("feeType"));
					vo2.setTotPaidAmt(rs1.getDouble("total"));
					col.add(vo2);
				}
				list.setFeeNamelist(col);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: getFeeNewPaidDetails Ending");
		return list;

	}



	public String saveFeeCollection(List<FeeCollectionVo> termwise, String discountAmt, String distermid,
			String termId, UserLoggingsPojo userLoggingsVo, String extraAmtCarryFrwd) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: saveFeeCollection : Starting");

		PreparedStatement ps_insertPlan = null;
		PreparedStatement ps_insertPlan1 = null;
		PreparedStatement ps_insertReciept = null;
		Connection conn = null;
		int count = 0;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		int result = 0,count_insertReciept = 0;
		String status = null;
		double ispaid = 0,pertermdis = 0.0,totAmt = 0.0,actualAmt = 0.0,totFineAmt = 0.0,totconcessionAmt = 0.0,totadvanceAmt = 0.0,totBalAmt = 0.0,AmtPaid = 0.0;
		double totalcash=0,totalcard=0;
		PreparedStatement ps_collection_count = null;
		ResultSet rs_collection_count = null;
		FeeCollectionVo obj = new FeeCollectionVo();
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			int receiptNo = getReceiptNo(conn);

			for(int i=0;i<termwise.size();i++){

				totAmt = totAmt + termwise.get(i).getTot_actual_amt();
				AmtPaid = AmtPaid + termwise.get(i).getCurrent_payment();
				totFineAmt = totFineAmt + termwise.get(i).getFineAmount();
				totconcessionAmt = totconcessionAmt + termwise.get(i).getConcession();
				totadvanceAmt = totadvanceAmt + termwise.get(i).getAdvanceCarry();
				totBalAmt = totBalAmt + termwise.get(i).getDuesCarry();
				totalcash = totalcash + termwise.get(i).getCashAmount();
				totalcard = totalcard + termwise.get(i).getCardAmount();

				ispaid = (termwise.get(i).getTot_actual_amt()+termwise.get(i).getAdvanceCarry()) - termwise.get(i).getCurrent_payment();
				ps_collection_count = conn.prepareStatement("select feeCollectionCode from campus_fee_collection where admissionNo=? and accYear=? and termcode=?");
				ps_collection_count.setString(1, termwise.get(i).getAddmissionno());
				ps_collection_count.setString(2, termwise.get(i).getAccYear());
				ps_collection_count.setString(3, termwise.get(i).getTermid());
				rs_collection_count = ps_collection_count.executeQuery();

				String feeCollectionCount = null;
				while (rs_collection_count.next()) {
					feeCollectionCount = rs_collection_count.getString(1);
				}

				String primaryKey = IDGenerator.getPrimaryKeyID("campus_fee_collection",userLoggingsVo);

				if (feeCollectionCount == null) {

					ps_insertPlan = conn.prepareStatement("insert into campus_fee_collection(feeCollectionCode,admissionNo,accYear,termcode,is_paid,totalamount,actualamount,due_amount,advance_amount,paidDate,createdby,createdtime,amount_paid,balance_amount,fineAmount,concessionAmt,paidByExcel,chln_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					ps_insertPlan.setString(1, primaryKey);
					ps_insertPlan.setString(2, termwise.get(i).getAddmissionno());
					ps_insertPlan.setString(3, termwise.get(i).getAccYear());
					ps_insertPlan.setString(4, termwise.get(i).getTermid());

					if (ispaid == 0.0 || ispaid == 0 || ispaid == 0.00) {
						ps_insertPlan.setString(5, "Y");
					} else {
						ps_insertPlan.setString(5, "P");
					}

					ps_insertPlan.setDouble(6, termwise.get(i).getTot_actual_amt());
					ps_insertPlan.setDouble(7, termwise.get(i).getTot_actual_amt() - termwise.get(i).getFineAmount());
					ps_insertPlan.setDouble(8, termwise.get(i).getDuesCarry());
					ps_insertPlan.setDouble(9, termwise.get(i).getAdvanceCarry());
					ps_insertPlan.setDate(10, HelperClass.getCurrentSqlDate());
					//ps_insertPlan.setString(10, HelperClass.convertUIToDatabase(termwise.get(i).getBilldate()));
					ps_insertPlan.setString(11, termwise.get(i).getUserID());
					ps_insertPlan.setTimestamp(12, HelperClass.getCurrentTimestamp());
					ps_insertPlan.setDouble(13, termwise.get(i).getCurrent_payment());
					ps_insertPlan.setDouble(14, (termwise.get(i).getTot_actual_amt()+termwise.get(i).getAdvanceCarry()) - termwise.get(i).getCurrent_payment());
					ps_insertPlan.setDouble(15, termwise.get(i).getFineAmount());
					ps_insertPlan.setDouble(16, termwise.get(i).getConcession());
					ps_insertPlan.setDouble(17, termwise.get(i).getCurrent_payment());
					ps_insertPlan.setInt(18, receiptNo);
					//System.out.println("campus_fee_collection::::::" + ps_insertPlan);

					count = ps_insertPlan.executeUpdate();

					String campus_fee_indetail = IDGenerator.getPrimaryKeyID("campus_fee_indetail",userLoggingsVo);
					ps2 = conn.prepareStatement(
							"insert into campus_fee_indetail (FeeInDetailedCode,admissionNo,accYear,term_id,totalamount,actualamount,"
									+ "balance_amount,amount_paid,conc_amount,conc_percent,paidDate,createdby,createdtime,payment_type,dd_cheque_date,dd_cheque_bankname,dd_cheque_no,feecollection_code,fine,chln_no,advance_amt,`cashamt`,`cardamt`)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					ps2.setString(1, campus_fee_indetail);
					ps2.setString(2, termwise.get(i).getAddmissionno());
					ps2.setString(3, termwise.get(i).getAccYear());
					ps2.setString(4, termwise.get(i).getTermid());
					ps2.setDouble(5, termwise.get(i).getTot_actual_amt());
					ps2.setDouble(6, termwise.get(i).getTot_actual_amt() - termwise.get(i).getFineAmount());
					ps2.setDouble(7, (termwise.get(i).getTot_actual_amt()+termwise.get(i).getAdvanceCarry()) - termwise.get(i).getCurrent_payment());
					ps2.setDouble(8, termwise.get(i).getCurrent_payment() - pertermdis);
					ps2.setDouble(9, termwise.get(i).getConcession());
					ps2.setDouble(10, termwise.get(i).getTot_concession_amt());
					ps2.setDate(11, HelperClass.getCurrentSqlDate());
					//ps2.setString(11, HelperClass.convertUIToDatabase(termwise.get(i).getBilldate()));
					ps2.setString(12, termwise.get(i).getUserID());
					ps2.setTimestamp(13, HelperClass.getCurrentTimestamp());
					ps2.setString(14, termwise.get(i).getPaymentMode());
					ps2.setString(15, termwise.get(i).getDd_cheque_date());
					ps2.setString(16, termwise.get(i).getDd_cheque_bank());
					ps2.setString(17, termwise.get(i).getPaymentPatriculars());
					ps2.setString(18, primaryKey);
					ps2.setDouble(19, termwise.get(i).getFineAmount());
					ps2.setDouble(20, receiptNo);
					ps2.setDouble(21, termwise.get(i).getAdvanceCarry());
					ps2.setDouble(22, termwise.get(i).getCashAmount());
					ps2.setDouble(23, termwise.get(i).getCardAmount());
					ps2.executeUpdate();
					if (count > 0) {
						ps1 = conn.prepareStatement("insert into campus_fee_collectiondetails(feeCollectionCode, feeCode,feeAmount,finalFeeAmtcollected,outstandingfee,concessionPercent,consfeeAmount)values(?,?,?,?,?,?,?)");

						for (int k = 0; k < termwise.get(i).getFeeNamelist().size(); k++) {

							ps1.setString(1, primaryKey);
							ps1.setString(2, termwise.get(i).getFeeNamelist().get(k).getFeecode());
							ps1.setDouble(3, termwise.get(i).getFeeNamelist().get(k).getActualamtarray());
							ps1.setDouble(4, termwise.get(i).getFeeNamelist().get(k).getFeeAmountArray());
							// ps1.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
							ps1.setDouble(5, termwise.get(i).getFeeNamelist().get(k).getActualamtarray()
									- termwise.get(i).getFeeNamelist().get(k).getFeeAmountArray());
							ps1.setDouble(6, termwise.get(i).getFeeNamelist().get(k).getConcessionpercentArray());
							ps1.setDouble(7, termwise.get(i).getFeeNamelist().get(k).getConsfeeAmountArray());

							result = ps1.executeUpdate();
							////System.out.println("INSERT_FEE_COLLECTION_DETAILS  " + ps1);
						}

						ps_insertReciept = conn.prepareStatement("insert into campus_fee_reciept(feeCollectionCode, feeCode,feeAmount,finalFeeAmtcollected,outstandingfee,concessionPercent,consfeeAmount,feeindetails_code,feepaiddate,feetype,receipt_no)values(?,?,?,?,?,?,?,?,?,?,?)");

						for (int m = 0; m < termwise.get(i).getFeeNamelist().size(); m++) {

							ps_insertReciept.setString(1, primaryKey);
							ps_insertReciept.setString(2, termwise.get(i).getFeeNamelist().get(m).getFeecode());
							ps_insertReciept.setDouble(3, termwise.get(i).getFeeNamelist().get(m).getActualamtarray());
							ps_insertReciept.setDouble(4, termwise.get(i).getFeeNamelist().get(m).getFeeAmountArray());
							// ps_insertReciept.setDouble(4,collectionVo.getFeeNamelist().get(i).getFeePayingAmountArray());
							ps_insertReciept.setDouble(5, termwise.get(i).getFeeNamelist().get(m).getActualamtarray()
									- termwise.get(i).getFeeNamelist().get(m).getFeeAmountArray());
							ps_insertReciept.setDouble(6, termwise.get(i).getFeeNamelist().get(m).getConcessionpercentArray());
							ps_insertReciept.setDouble(7, termwise.get(i).getFeeNamelist().get(m).getConsfeeAmountArray());
							ps_insertReciept.setString(8, campus_fee_indetail);
							ps_insertReciept.setDate(9, HelperClass.getCurrentSqlDate());
							//ps_insertReciept.setString(9, HelperClass.convertUIToDatabase(termwise.get(i).getBilldate()));
							ps_insertReciept.setString(10, termwise.get(i).getFeeNamelist().get(m).getFeetype());
							ps_insertReciept.setInt(11, receiptNo);
							count_insertReciept = ps_insertReciept.executeUpdate();
							////System.out.println("INSERT_FEE_RECIEPT:::" + ps_insertReciept);
						}

					}
				}

				if (result > 0) {

					PreparedStatement ps_insert_detail = conn
							.prepareStatement("insert into campus_fee_collection_details(admissionNo,accYear,termcode,is_paid,totalamount,actualamount,conc_amount,conc_percent,paidDate,createdby,createdtime,amount_paid,balance_amount,chln_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					ps_insert_detail.setString(1, termwise.get(i).getAddmissionno());
					ps_insert_detail.setString(2, termwise.get(i).getAccYear());
					ps_insert_detail.setString(3, termwise.get(i).getTermid());
					ps_insert_detail.setString(4, "Y");

					ps_insert_detail.setString(5, termwise.get(i).getTot_opning_amt());
					ps_insert_detail.setDouble(6, termwise.get(i).getTot_actual_amt());
					ps_insert_detail.setDouble(7, termwise.get(i).getTot_concession_amt());
					ps_insert_detail.setDouble(8, termwise.get(i).getConcession());
					ps_insert_detail.setDate(9, HelperClass.getCurrentSqlDate());
					//ps_insert_detail.setString(9, HelperClass.convertUIToDatabase(termwise.get(i).getBilldate()));
					ps_insert_detail.setString(10, termwise.get(i).getUserID());
					ps_insert_detail.setTimestamp(11, HelperClass.getCurrentTimestamp());
					ps_insert_detail.setDouble(12, termwise.get(i).getCurrent_payment());
					ps_insert_detail.setDouble(13, termwise.get(i).getOutstanding_balance());
					ps_insert_detail.setInt(14, receiptNo);
					int counts = ps_insert_detail.executeUpdate();
					//System.out.println("ps_insert_detail:::::" + ps_insert_detail);
					if (counts > 0) {
						status = "true";
						//conn.commit();
						if(termwise.get(i).getDuesCarry()!=0.0){
							updatePaidStatus(termwise.get(i).getPrefeecolcode(),termwise.get(i).getDuesCarry(),termwise.get(i).getCurrent_payment(),userLoggingsVo);
						}

					} else {
						//conn.rollback();
						status = "false";
					}

				} else {
					//conn.rollback();
					status = "false";
					//System.out.println(status);
				}

			}

			obj.setTot_actual_amt(totAmt-totFineAmt);
			obj.setTot_paid_amt(AmtPaid);
			obj.setFineAmount(totFineAmt);
			obj.setConcession(totconcessionAmt);
			obj.setAdvanceCarry(totadvanceAmt);
			obj.setStudentid(termwise.get(0).getAddmissionno());
			obj.setAccYear(termwise.get(0).getAccYear());
			obj.setOutstanding_balance(totBalAmt);
			obj.setPaymentPatriculars(termwise.get(0).getPaymentPatriculars());
			obj.setDd_cheque_bank(termwise.get(0).getDd_cheque_bank());
			obj.setDd_cheque_date(termwise.get(0).getDd_cheque_date());
			obj.setPaymentMode(termwise.get(0).getPaymentMode());
			obj.setBilldate(termwise.get(0).getBilldate());
			obj.setUserID(termwise.get(0).getUserID());
			obj.setCashAmount(totalcash);
			obj.setCardAmount(totalcard);

			savefeepaiddetails(obj,discountAmt,conn,receiptNo,termId,userLoggingsVo,extraAmtCarryFrwd);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {

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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl: getFeeCollectionDetails: Ending");

		return status;
	}


	private void updatePaidStatus(String feecolcode,double dueAmt, double paidamt, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(
				JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : updatePaidStatus Starting");

		PreparedStatement pstmObj = null;
		PreparedStatement update = null;
		ResultSet rs = null;
		Connection conn = null;
		double totbalAmt = 0.0;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			if(dueAmt <= paidamt){
				totbalAmt = dueAmt;
			}
			else if(dueAmt>paidamt){
				totbalAmt = dueAmt - paidamt;
			}else{
				totbalAmt = paidamt - dueAmt;
			}

			pstmObj = conn.prepareStatement("UPDATE `campus_fee_collection` SET `balance_amount` = `balance_amount` - ? WHERE `feeCollectionCode` = ?");
			pstmObj.setDouble(1, totbalAmt);
			pstmObj.setString(2, feecolcode);
			pstmObj.executeUpdate();

			pstmObj.close();
			double balAmt = 0.0;
			pstmObj = conn.prepareStatement("select balance_amount from campus_fee_collection where feeCollectionCode = ?");
			pstmObj.setString(1, feecolcode);
			rs = pstmObj.executeQuery();
			while(rs.next()){
				balAmt = rs.getDouble(1);
			}
			rs.close();
			pstmObj.close();
			if(balAmt == 0.0){
				pstmObj = conn.prepareStatement("UPDATE `campus_fee_collection` SET `is_paid` = 'Y' where feeCollectionCode = ?");
				pstmObj.setString(1, feecolcode);
				pstmObj.executeUpdate();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
		logger.info(JDate.getTimeString(new Date()) + " Control in feeCollectionNewDaoImpl : updatePaidStatus Ending");

	}

	public String savefeepaiddetails(FeeCollectionVo obj, String discountAmt, Connection conn, int receiptNo, String termId, UserLoggingsPojo userLoggingsVo, String extraAmtCarryFrwd) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: savefeepaiddetails Starting");

		PreparedStatement pstmt = null;

		try{


			if(extraAmtCarryFrwd == null){
				discountAmt = "0";
			}

			if(discountAmt == null){
				discountAmt = "0";
			}



			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement("INSERT INTO `campus_feepaid_details` (`receiptNo`,`stu_id`,`total_Amt`,`discount`,`concession`,`fine`,`Amt_paid`,`bal_amt`,`advance_amt`,accyear_id,paiddate,pay_mode,dd_cheque_no,bankname,dd_cheque_date,termId,createdby,`cashamt`,`cardamt`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,receiptNo);
			pstmt.setString(2,obj.getStudentid());
			pstmt.setDouble(3,obj.getTot_actual_amt());
			pstmt.setDouble(4,Double.parseDouble(discountAmt));
			pstmt.setDouble(5,obj.getConcession());
			pstmt.setDouble(6,obj.getFineAmount());
			pstmt.setDouble(7,obj.getTot_paid_amt()-Double.parseDouble(discountAmt));
			pstmt.setDouble(8,(obj.getTot_actual_amt()+obj.getAdvanceCarry())-obj.getTot_paid_amt()+obj.getFineAmount());
			pstmt.setDouble(9,Double.parseDouble(extraAmtCarryFrwd));
			pstmt.setString(10,obj.getAccYear());
			pstmt.setDate(11,HelperClass.getCurrentSqlDate());
			//	pstmt.setString(11,HelperClass.convertUIToDatabase(obj.getBilldate()));
			pstmt.setString(12,obj.getPaymentMode());
			pstmt.setString(13,obj.getPaymentPatriculars());
			pstmt.setString(14,obj.getDd_cheque_bank());
			pstmt.setString(15,obj.getDd_cheque_date());
			pstmt.setString(16,termId);
			pstmt.setString(17,obj.getUserID());
			pstmt.setDouble(18,obj.getCashAmount());
			pstmt.setDouble(19,obj.getCardAmount());

			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
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
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in feeCollectionNewDaoImpl: savefeepaiddetails Ending");
		return null;
	}

}
