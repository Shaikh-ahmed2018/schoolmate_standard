package com.centris.campus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.SendSMS;
import com.centris.campus.daoImpl.IDGenerationByConnection;
import com.centris.campus.daoImpl.UserRolePermissionDAOImpl;
import com.centris.campus.vo.SMSAuditVO;
import com.centris.campus.vo.SmsIntegrationApiVO;
import com.cerp.rest.util.DBConnection;

public class BirthDayWishes {
	
	private static Logger logger = Logger.getLogger(UserRolePermissionDAOImpl.class);
	
	public static void main(String[] args) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BirthDayWishes : BirthDayWishes : Starting");

		java.sql.Date currDate = HelperClass.getCurrentSqlDate();
		PreparedStatement pst1 = null;
		ResultSet rst1 = null;

		Connection conn = DBConnection.getmasterConnection(); 
		List<SMSAuditVO> smsList = new ArrayList<SMSAuditVO>();

		try {

			pst1 = conn.prepareStatement("SELECT DISTINCT cmd.`customerID`,cpd.`sub_type`,ccd.`dbname`,ccd.`dbusername`,ccd.`dbpwd`,ccd.`dbhost`,ccd.`isActive` FROM `campus_customer_details` cmd JOIN `campus_product_details` cpd ON cpd.`cust_id` = cmd.`customerID` JOIN `campus_customer_dbdetails` ccd ON ccd.`custid` = cmd.`customerID` AND ccd.`sub_id` = cpd.`sub_id` WHERE ccd.`isActive`='Y' AND cmd.`isActive`='Y'");
			rst1 = pst1.executeQuery(); 
			while(rst1.next()){

				SMSAuditVO smsvo = new SMSAuditVO();
				smsvo.setCustomerId(rst1.getString("customerID"));
				smsvo.setDbName(rst1.getString("dbname"));
				smsvo.setDbUserName(rst1.getString("dbusername"));
				smsvo.setDbHost(rst1.getString("dbhost"));
				smsvo.setDbPassword(rst1.getString("dbpwd"));
				smsvo.setSubType(rst1.getString("sub_type"));
				smsvo.setStatus(rst1.getString("isActive"));

				smsList.add(smsvo);
			}
			
			for(int x=0;x<smsList.size();x++){

				String subType = smsList.get(x).getSubType();

				if(subType.equals("e-campB")){//For Basic, Only Student Birthday SMS.
					//For Student :
					birthdaySMSStudents(x,smsList, currDate);

				}else if(subType.equals("e-campS")){//For Standard, Both Student & Staff Birthday SMS.
					birthdaySMSStudents(x,smsList, currDate);
					birthdaySMSStaff(x,smsList, currDate);

				}else{//For Enterprise, Both Student & Staff Birthday SMS.
					birthdaySMSStudents(x,smsList, currDate);
					birthdaySMSStaff(x,smsList, currDate);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {

				if(rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if(pst1 != null && (!pst1.isClosed())) {
					pst1.close();
				}
				if(conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}


		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BirthDayWishes : BirthDayWishes : Ending");

	}

	public static void birthdaySMSStudents(int x, List<SMSAuditVO> smsList, java.sql.Date currDate)throws Exception{
		
		PreparedStatement pstmt = null,pst = null,pstmt1 = null,pst2 = null,pst3 = null,pstmt2 = null,pst4 = null;
		PreparedStatement pstmtDOB = null;
		ResultSet rs = null,rs1 = null,rst2 = null,rst4 = null;
		ResultSet rsDOB = null;
		int count = 0, count1 = 0;
		String cd = currDate.toString();

		String dbhost = smsList.get(x).getDbHost();
		String dbUSD = smsList.get(x).getDbUserName();
		String dbPWD = smsList.get(x).getDbPassword();
		String dbName = smsList.get(x).getDbName();
		String custId = smsList.get(x).getCustomerId();

		int totalSMSSent=0;
		int totalSMSBal=0;
		int totalSMS = 0;
		int totalcount = 0;
		int characters = 0;
		String result = null;
		String toAddress = null;
		String recipient = null;
		String locationName = null;

		String hostName = "jdbc:mysql" + "://" + dbhost + ":3306/";


		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection smscon = DriverManager.getConnection(hostName + dbName, dbUSD,dbPWD);
		
		SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo(smscon);
		
		try{

			pstmtDOB = smscon.prepareStatement(SmsUtilsConstants.GET_PHNO_FOR_BDAY_STU); 
			pstmtDOB.setString(1, currDate.toString().replace(cd.substring(0, 4), "%"));
			
			rsDOB = pstmtDOB.executeQuery();
			while (rsDOB.next()) {

				String locid = rsDOB.getString("locationId");

				pst2 = smscon.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count WHERE loc_id=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}
				
				pst4 = smscon.prepareStatement("SELECT  sch.`school_id`,sch.`school_name`,sch.`email_id`,sch.`cperson_email`,loc.`Location_Id`,loc.`Location_Name`,loc.`emailId` AS branchemail FROM `campus_location` loc JOIN `campus_school_info` sch ON sch.school_id = loc.`schoolId` WHERE loc.`Location_Id`=?");
				pst4.setString(1, locid);
				rst4 = pst4.executeQuery();
				
				while(rst4.next()){
					
					toAddress = rst4.getString("email_id");
					recipient = "support@cerpsoft.in"+","+rst4.getString("cperson_email")+","+rst4.getString("branchemail");
					locationName = rst4.getString("Location_Name");
				}
				
				
				int sucesscount=0;
				int failurecount=0;

				if(totalSMSBal <= 100){

					SendMail mail = new SendMail();

					String[] recipientList = recipient.split(",");
					InternetAddress[] ccList = new InternetAddress[recipientList.length];
					int counter = 0;
					for (String recipients : recipientList) {
						ccList[counter] = new InternetAddress(recipients.trim());
						counter++;
					}
					String subject = "Request To Renew The SMS Package";

					String Bodymess = "Dear Sir/Madam,\n\n\t\t Your SMS Balance is going to Finish in "+locationName+" Branch. Kindly renew your SMS package to Continue the Service. Until you renew the SMS Package, the Birthday Wishes won't send.";

					mail.sendMail(toAddress, ccList, subject, Bodymess);

				}else{

					pstmt2 = smscon.prepareStatement("Select count(*) FROM campus_student cs join campus_student_classdetails csd on csd.student_id_int = cs.student_id_int WHERE csd.student_status='STUDYING' AND cs.student_dob_var like ? "); 
					pstmt2.setString(1, currDate.toString().replace(cd.substring(0, 4), "%"));
					rs = pstmt2.executeQuery();

					while (rs.next()) {
						count = rs.getInt(1);
					}

					if(count > 0){

						String smsContent ="Dear "+rsDOB.getString("studentName")+", God has gifted us a Smart, Awesome, Beautiful & Interesting Student. Wish you a Happy Birthday and May All Your Dreams Come True!!";

						characters = smsContent.length();

						String response = new SendSMS().sendSMS(rsDOB.getString("student_contact_mobileno"),smsContent,smsdetails);

						String tableName = "birthday_sms";
						String bdayId = IDGenerationByConnection.getPrimaryKeyID(tableName, custId, smscon);

						pst = smscon.prepareStatement("Insert into birthday_sms (bdaycode,bdaydate,class_deptCode,section_desgnCode,student_teaCode,mobileNo,smscontent,createTime) values(?,?,?,?,?,?,?,?)");
						pst.setString(1, bdayId);
						pst.setString(2, currDate.toString());
						pst.setString(3, rsDOB.getString("classdetail_id_int"));
						pst.setString(4, rsDOB.getString("classsection_id_int"));
						pst.setString(5, rsDOB.getString("student_id_int"));
						pst.setString(6, rsDOB.getString("student_contact_mobileno"));
						pst.setString(7, smsContent);
						pst.setTimestamp(8, HelperClass.getCurrentTimestamp());
						pst.executeUpdate();

						String messageId=response.split(":")[4].split("<")[0];
						String resarray[] = response.split(":");
						SMSAuditVO smsAuditVO =  new SMSAuditVO();
						smsAuditVO.setCreatedBy("admin");
						smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
						smsAuditVO.setMobileNo(rsDOB.getString("student_contact_mobileno"));
						smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
						smsAuditVO.setSmsContent(smsContent);
						smsAuditVO.setSmsDate(currDate.toString());
						smsAuditVO.setSmsResponse(response);
						if(messageId != null && messageId.contains("success")){
							smsAuditVO.setDeliveryStatus("Sent");
							smsAuditVO.setSmsStatus("Sent");
							sucesscount++;
						}
						else{
							smsAuditVO.setDeliveryStatus("Not Sent");
							smsAuditVO.setSmsStatus("Not Sent");
							failurecount++;
						}
						if(!response.equalsIgnoreCase("Template Does Not Match")) {
							smsAuditVO.setSmsStatus(resarray[4].substring(0,7));
							smsAuditVO.setMsgId(resarray[1]);
						}
						else{
							smsAuditVO.setSmsStatus("Fail("+response+")");
							smsAuditVO.setMsgId(response);
						}

						smsAuditVO.setStudentAdmissionNo(rsDOB.getString("student_admissionno_var"));
						smsAuditVO.setTemplateCode("TEM1");
						smsAuditVO.setServiceCode(bdayId);


						new SendSMS().insertSMSDetails(smsAuditVO,smscon);
					}

					java.sql.Date date = HelperClass.getCurrentSqlDate();
					totalcount = sucesscount + failurecount;
					pstmt1 = smscon.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
					pstmt1.setDate(1,date);
					pstmt1.setString(2, locid);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()){
						count1 = rs1.getInt(1);

						if(characters > 160 && characters <= 306){

							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
							pst3.setInt(1, (totalcount+1));
							pst3.setInt(2, (totalcount+1));
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();


						}else if(characters >= 307 && characters <= 459){
							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
							pst3.setInt(1, (totalcount+2));
							pst3.setInt(2, (totalcount+2));
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();
						}	
						else{

							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=? ");
							pst3.setInt(1, totalcount);
							pst3.setInt(2, totalcount);
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();
						}

						if(characters > 160 && characters <= 306){
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount+1);
								pstmt.setInt(3, sucesscount+1);
								pstmt.setInt(4, failurecount+1);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount+1);
								pstmt.setInt(2, sucesscount+1);
								pstmt.setInt(3, failurecount+1);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}else if(characters >= 307 && characters <= 459){
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount+2);
								pstmt.setInt(3, sucesscount+2);
								pstmt.setInt(4, failurecount+2);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount+2);
								pstmt.setInt(2, sucesscount+2);
								pstmt.setInt(3, failurecount+2);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}else{
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount);
								pstmt.setInt(3, sucesscount);
								pstmt.setInt(4, failurecount);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount);
								pstmt.setInt(2, sucesscount);
								pstmt.setInt(3, failurecount);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if(rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if(rst4 != null && (!rst4.isClosed())) {
					rst4.close();
				}
				if(rst2 != null && (!rst2.isClosed())) {
					rst2.close();
				}
				if(rsDOB != null && (!rsDOB.isClosed())) {
					rsDOB.close();
				}
				if(pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if(pst3 != null && (!pst3.isClosed())) {
					pst3.close();
				}
				if(pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if(pst != null && (!pst.isClosed())) {
					pst.close();
				}
				if(pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if(pst4 != null && (!pst4.isClosed())) {
					pst4.close();
				}
				if(pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if(pstmtDOB != null && (!pstmtDOB.isClosed())) {
					pstmtDOB.close();
				}
				if(smscon != null && (!smscon.isClosed())) {
					smscon.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void birthdaySMSStaff(int x, List<SMSAuditVO> smsList, java.sql.Date currDate)throws Exception{

		PreparedStatement pstmt = null,psmt = null,ptmt = null,pstmt1 = null,pst2 = null,pst3 = null,pst4 = null;
		PreparedStatement tDOB = null;
		ResultSet rst = null,ts = null, rs1 = null,rst2=null,rst4= null;
		int count1 = 0;
		String cd = currDate.toString();

		String dbhost = smsList.get(x).getDbHost();
		String dbUSD = smsList.get(x).getDbUserName();
		String dbPWD = smsList.get(x).getDbPassword();
		String dbName = smsList.get(x).getDbName();
		String custId = smsList.get(x).getCustomerId();

		int totalSMSSent = 0;
		int totalSMSBal = 0;
		int totalcount = 0;
		int totalSMS = 0;
		int characters = 0;
		String result = null;
		String toAddress = null;
		String recipient = null;
		String locationName = null;

		String hostName = "jdbc:mysql" + "://" + dbhost + ":3306/";


		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection smscon = DriverManager.getConnection(hostName + dbName, dbUSD,dbPWD);
		
		SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo(smscon);

		try{

				tDOB = smscon.prepareStatement(SmsUtilsConstants.GET_PHNO_FOR_BDAY_STAFF); 
				tDOB.setString(1, currDate.toString().replace(cd.substring(0, 4), "%"));
				ts = tDOB.executeQuery();

				while (ts.next()) {

					String locid = ts.getString("Loc_ID");

					pst2 = smscon.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count WHERE loc_id=?");
					pst2.setString(1, locid);
					rst2 = pst2.executeQuery();
					while(rst2.next()){
						totalSMSSent = Integer.parseInt(rst2.getString("total_sent_sms"));
						totalSMSBal = Integer.parseInt(rst2.getString("total_remaining"));
						totalSMS = rst2.getInt("total_sms");
					}
					
					pst4 = smscon.prepareStatement("SELECT  sch.`school_id`,sch.`school_name`,sch.`email_id`,sch.`cperson_email`,loc.`Location_Id`,loc.`Location_Name`,loc.`emailId` AS branchemail FROM `campus_location` loc JOIN `campus_school_info` sch ON sch.school_id = loc.`schoolId` WHERE loc.`Location_Id`=?");
					pst4.setString(1, locid);
					rst4 = pst4.executeQuery();
					
					while(rst4.next()){
						
						toAddress = rst4.getString("email_id");
						recipient = "support@cerpsoft.in"+","+rst4.getString("cperson_email")+","+rst4.getString("branchemail");
						locationName = rst4.getString("Location_Name");
					}

					int sucesscount=0;
					int failurecount=0;

					if(totalSMSBal <= 100){

						SendMail mail = new SendMail();
						
						String[] recipientList = recipient.split(",");
						InternetAddress[] ccList = new InternetAddress[recipientList.length];
						int counter = 0;
						for (String recipients : recipientList) {
							ccList[counter] = new InternetAddress(recipients.trim());
						    counter++;
						}
						String subject = "Request To Renew The SMS Package";

						String Bodymess = "Dear Sir/Madam,\n\n\t\t Your SMS Balance is going to Finish in "+locationName+" Branch. Kindly renew your SMS package to Continue the Service. Until you renew the SMS Package, the Birthday Wishes won't send.";

						mail.sendMail(toAddress, ccList, subject, Bodymess);
						
					}else{
						
						psmt = smscon.prepareStatement("Select count(*) FROM campus_teachers ct WHERE ct.isActive='Y' AND ct.dateofBirth like ?"); 
						psmt.setString(1, currDate.toString().replace(cd.substring(0, 4), "%"));
						rst = psmt.executeQuery();

						while (rst.next()) {
							count1 = rst.getInt(1);
						}
						
						if(count1 > 0){

						String smsContent1 = "Dear "+ts.getString("teacherName")+", Words cannot express how Happy we are to work with you and we would love to extend this for many more Birthdays! Wish you a Happy Birthday!!";

						characters = smsContent1.length();

						String response = new SendSMS().sendSMS(ts.getString("mobileNo"),smsContent1,smsdetails);

						String tableName = "birthday_sms";
						String bdayId = IDGenerationByConnection.getPrimaryKeyID(tableName, custId, smscon);

						ptmt = smscon.prepareStatement("Insert into birthday_sms (bdaycode,bdaydate,class_deptCode,section_desgnCode,student_teaCode,mobileNo,smscontent,createTime) values(?,?,?,?,?,?,?,?)");
						ptmt.setString(1, bdayId);
						ptmt.setString(2, currDate.toString());
						ptmt.setString(3, ts.getString("department"));
						ptmt.setString(4, ts.getString("designation"));
						ptmt.setString(5, ts.getString("TeacherID"));
						ptmt.setString(6, ts.getString("mobileNo"));
						ptmt.setString(7, smsContent1);
						ptmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
						ptmt.executeUpdate();

						String messageId = response.split(":")[4].split("<")[0];
						String resarray[] = response.split(":");
						SMSAuditVO smsAuditVO =  new SMSAuditVO();
						smsAuditVO.setCreatedBy("admin");
						smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
						smsAuditVO.setMobileNo(ts.getString("mobileNo"));
						smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
						smsAuditVO.setSmsContent(smsContent1);
						smsAuditVO.setSmsDate(currDate.toString());
						smsAuditVO.setSmsResponse(response);
						if(messageId != null && messageId.contains("success")){
							smsAuditVO.setDeliveryStatus("Sent");
							smsAuditVO.setSmsStatus("Sent");
							sucesscount++;
						}
						else{
							smsAuditVO.setDeliveryStatus("Not Sent");
							smsAuditVO.setSmsStatus("Not Sent");
							failurecount++;
						}
						if(!response.equalsIgnoreCase("Template Does Not Match")) {
							smsAuditVO.setSmsStatus(resarray[4].substring(0,7));
							smsAuditVO.setMsgId(resarray[1]);
						}
						else{
							smsAuditVO.setSmsStatus("Fail("+response+")");
							smsAuditVO.setMsgId(response);
						}

						smsAuditVO.setStudentAdmissionNo(ts.getString("TeacherID"));
						smsAuditVO.setTemplateCode("TEM1");
						smsAuditVO.setServiceCode(bdayId);


						new SendSMS().insertSMSDetails(smsAuditVO,smscon);
					}

					java.sql.Date date = HelperClass.getCurrentSqlDate();
					totalcount = sucesscount + failurecount;
					pstmt1 = smscon.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
					pstmt1.setDate(1,date);
					pstmt1.setString(2, locid);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()){
						count1 = rs1.getInt(1);

						if(characters > 160 && characters <= 306){

							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
							pst3.setInt(1, (totalcount+1));
							pst3.setInt(2, (totalcount+1));
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();


						}else if(characters >= 307 && characters <= 459){
							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
							pst3.setInt(1, (totalcount+2));
							pst3.setInt(2, (totalcount+2));
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();
						}	
						else{

							pst3 = smscon.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=? ");
							pst3.setInt(1, totalcount);
							pst3.setInt(2, totalcount);
							pst3.setInt(3, totalSMS);
							pst3.setString(4, locid);
							pst3.executeUpdate();
						}

						if(characters > 160 && characters <= 306){
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount+1);
								pstmt.setInt(3, sucesscount+1);
								pstmt.setInt(4, failurecount+1);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount+1);
								pstmt.setInt(2, sucesscount+1);
								pstmt.setInt(3, failurecount+1);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}else if(characters >= 307 && characters <= 459){
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount+2);
								pstmt.setInt(3, sucesscount+2);
								pstmt.setInt(4, failurecount+2);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount+2);
								pstmt.setInt(2, sucesscount+2);
								pstmt.setInt(3, failurecount+2);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}else{
							if(count1 == 0){
								pstmt = smscon.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
								pstmt.setDate(1, date);
								pstmt.setInt(2, totalcount);
								pstmt.setInt(3, sucesscount);
								pstmt.setInt(4, failurecount);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();

								if(count3 > 0){
									result="success";
								}
							}
							else{
								pstmt = smscon.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
								pstmt.setInt(1, totalcount);
								pstmt.setInt(2, sucesscount);
								pstmt.setInt(3, failurecount);
								pstmt.setDate(4, date);
								pstmt.setString(5, locid);
								int count3 = pstmt.executeUpdate();
								if(count3 > 0){
									result="success";
								}
							}
						}
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if(rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if(rst4 != null && (!rst4.isClosed())) {
					rst4.close();
				}
				if(rst2 != null && (!rst2.isClosed())) {
					rst2.close();
				}
				if(ts != null && (!ts.isClosed())) {
					ts.close();
				}
				if(pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if(pst3 != null && (!pst3.isClosed())) {
					pst3.close();
				}
				if(pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if(ptmt != null && (!ptmt.isClosed())) {
					ptmt.close();
				}
				if(psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
				if(pst4 != null && (!pst4.isClosed())) {
					pst4.close();
				}
				if(pst2 != null && (!pst2.isClosed())) {
					pst2.close();
				}
				if(tDOB != null && (!tDOB.isClosed())) {
					tDOB.close();
				}
				if(smscon != null && (!smscon.isClosed())) {
					smscon.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
