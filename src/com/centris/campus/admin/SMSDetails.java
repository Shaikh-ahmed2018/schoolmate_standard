package com.centris.campus.admin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.vo.SMSAuditVO;
import com.centris.campus.vo.SmsIntegrationApiVO;
import com.centris.campus.vo.SmsVo;

public class SMSDetails {

	private static final Logger logger = Logger.getLogger(SMSDetails.class);

	public void getHolidayDetails(String holidayCode,String holidayDate, String log_audit_session, String module, String submodule, UserLoggingsPojo custdetails, int characters, String locid){


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getHolidayDetails Starting");


		PreparedStatement pstmt=null,pstmt1 = null,pst2 = null, pst3 = null, pstmt2 = null;
		Connection conn=null;
		ResultSet rs = null, rs1 = null, rst2 = null;
		String result=null;
		int count1=0;

		int totalcount=0;
		try{

			SMSAuditVO smsAuditVO = null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt2 = conn.prepareStatement(SQLUtilConstants.HOLIDAY_SMS_LIST);
			pstmt2.setString(1, holidayDate.trim());
			pstmt2.setString(2, holidayCode.trim());
			pstmt2.setString(3, locid);
			rs = pstmt2.executeQuery();

			while(rs.next()){

				int sucesscount=0;
				int failurecount=0;
				int totalSMSSent=0;
				int totalSMSBal=0;
				int totalSMS = 0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count WHERE loc_id=?");
				pst2.setString(1, locid);
                rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){
				}else{
					String response = new SendSMS().sendSMS(rs.getString("student_contact_mobileno"), rs.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setCreatedBy(rs.getString("created_by"));
					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(rs.getString("smsContent"));
					smsAuditVO.setSmsDate(rs.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);
					if(messageId!=null && messageId.contains("success")){
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

					smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
					String templateCode=rs.getString("templateCode");
					if(templateCode == null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(holidayCode);


					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}

				java.sql.Date date = HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2, locid);
				rs1=pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);
					
					if(characters > 160 && characters <= 306){
						
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
						pst3.setInt(1, (totalcount+1));
						pst3.setInt(2, (totalcount+1));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
						
					
					}else if(characters >= 307 && characters <= 459){
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
						pst3.setInt(1, (totalcount+2));
						pst3.setInt(2, (totalcount+2));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}	
					else{
					
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=? ");
						pst3.setInt(1, totalcount);
						pst3.setInt(2, totalcount);
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}

					if(characters > 160 && characters <= 306){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+1);
							pstmt.setInt(3, sucesscount+1);
							pstmt.setInt(4, failurecount+1);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount+1);
							pstmt.setInt(2, sucesscount+1);
							pstmt.setInt(3, failurecount+1);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else if(characters >= 307 && characters <= 459){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+2);
							pstmt.setInt(3, sucesscount+2);
							pstmt.setInt(4, failurecount+2);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount+2);
							pstmt.setInt(2, sucesscount+2);
							pstmt.setInt(3, failurecount+2);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else{
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount);
							pstmt.setInt(3, sucesscount);
							pstmt.setInt(4, failurecount);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount);
							pstmt.setInt(2, sucesscount);
							pstmt.setInt(3, failurecount);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pstmt2!=null && !pstmt2.isClosed()){
					pstmt2.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
				

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getHolidayDetails Ending");
		}
	}

	public void getMeetingDetails(String meetingCode,String meetingDate, String log_audit_session, String module, String submodule, UserLoggingsPojo custdetails, int characters, String locid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getMeetingDetails Starting");
		Connection conn=null;
		ResultSet rs=null,rs1 = null,rst2 = null;
		PreparedStatement pstmt=null,pst2 =null,pst3 =null,pstmt1 = null,pst = null;
		String result=null;
		int count1=0;
		int totalcount=0;

		try{
			SMSAuditVO smsAuditVO =null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pst = conn.prepareStatement(SQLUtilConstants.GET_MEETING_DETAILS);
			pst.setString(1, meetingCode);
			pst.setString(2, meetingDate);
			pst.setString(3, locid);
			rs = pst.executeQuery();
			
			while(rs.next()){

				int sucesscount=0;
				int failurecount=0;
				int totalSMSSent=0;
				int totalSMSBal=0;
				int totalSMS = 0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where loc_id=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){
				}else{
					String response = new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					String status=null;
					String msgId=null;
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setApprovedBy(rs.getString("createuser"));
					smsAuditVO.setCreatedBy(rs.getString("createuser"));

					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
					smsAuditVO.setSendBy(rs.getString("createuser"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(rs.getString("smsContent"));
					smsAuditVO.setSmsDate(rs.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);

					if(messageId!=null && messageId.contains("success")){
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
					smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
					String templateCode=rs.getString("TemplateCode");
					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(meetingCode);

					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}

				java.sql.Date date=HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;

				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2, locid);
				rs1=pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);
					
					if(characters > 160 && characters <= 306){
						
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
						pst3.setInt(1, (totalcount+1));
						pst3.setInt(2, (totalcount+1));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
						
					
					}else if(characters >= 307 && characters <= 459){
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
						pst3.setInt(1, (totalcount+2));
						pst3.setInt(2, (totalcount+2));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}	
					else{
					
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=?");
						pst3.setInt(1, totalcount);
						pst3.setInt(2, totalcount);
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}

					if(characters > 160 && characters <= 306){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+1);
							pstmt.setInt(3, sucesscount+1);
							pstmt.setInt(4, failurecount+1);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount+1);
							pstmt.setInt(2, sucesscount+1);
							pstmt.setInt(3, failurecount+1);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else if(characters >= 307 && characters <= 459){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+2);
							pstmt.setInt(3, sucesscount+2);
							pstmt.setInt(4, failurecount+2);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount+2);
							pstmt.setInt(2, sucesscount+2);
							pstmt.setInt(3, failurecount+2);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else{
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount);
							pstmt.setInt(3, sucesscount);
							pstmt.setInt(4, failurecount);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
							pstmt.setInt(1, totalcount);
							pstmt.setInt(2, sucesscount);
							pstmt.setInt(3, failurecount);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}
				}
			}
		}

		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pst!=null && !pst.isClosed()){
					pst = null;
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getMeetingDetails Ending");
		}

	}

	public void getEventDetails(String eventCode,String eventDate, String log_audit_session, String module, String submodule, UserLoggingsPojo custdetails, int characters, String locid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getEventDetails Starting");
		
		ResultSet rs=null,rst1 = null,rst2 = null,rs1 = null;
		Connection conn=null;
		PreparedStatement pstmt=null,pstmt1 = null,pst2 = null,pst3 = null,pst = null;
		String result=null;
		int count1=0;
		int totalcount=0;

		try{
			SMSAuditVO smsAuditVO=null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.GET_MEETING_DETAILS);
			pst.setString(1, eventCode);
			pst.setString(2, eventDate);
			pst.setString(3, locid);
			rs = pst.executeQuery();

			while(rs.next()){

				int sucesscount=0;
				int failurecount=0;
				int totalSMSSent=0;
				int totalSMSBal=0;
				int totalSMS = 0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where loc_id=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){

				}else{

					String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					String status=null;
					String msgId=null;
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setCreatedBy(rs.getString("createuser"));
					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(rs.getString("smsContent"));
					smsAuditVO.setSmsDate(rs.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);

					if(messageId!=null && messageId.contains("success")){
						smsAuditVO.setDeliveryStatus("Sent");
						smsAuditVO.setSmsStatus("Sent");
						sucesscount++;
					}
					else{
						smsAuditVO.setSmsStatus("Not Sent");
						smsAuditVO.setDeliveryStatus("Not Sent");
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
					smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
					String templateCode=rs.getString("TemplateCode");
					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(eventCode);

					int p=new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}

				java.sql.Date date=HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;
				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id = ?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2, locid);
				rs1=pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);
					
					if(characters > 160 && characters <= 306){
						
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id = ?");
						pst3.setInt(1, (totalcount+1));
						pst3.setInt(2, (totalcount+1));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
						
					
					}else if(characters >= 307 && characters <= 459){
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id = ?");
						pst3.setInt(1, (totalcount+2));
						pst3.setInt(2, (totalcount+2));
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}	
					else{
					
						pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id = ?");
						pst3.setInt(1, totalcount);
						pst3.setInt(2, totalcount);
						pst3.setInt(3, totalSMS);
						pst3.setString(4, locid);
						int count2 = pst3.executeUpdate();
					}

					if(characters > 160 && characters <= 306){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+1);
							pstmt.setInt(3, sucesscount+1);
							pstmt.setInt(4, failurecount+1);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id = ?" );
							pstmt.setInt(1, totalcount+1);
							pstmt.setInt(2, sucesscount+1);
							pstmt.setInt(3, failurecount+1);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else if(characters >= 307 && characters <= 459){
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount+2);
							pstmt.setInt(3, sucesscount+2);
							pstmt.setInt(4, failurecount+2);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id = ?" );
							pstmt.setInt(1, totalcount+2);
							pstmt.setInt(2, sucesscount+2);
							pstmt.setInt(3, failurecount+2);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}else{
						if(count1 == 0){
							pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
							pstmt.setDate(1, date);
							pstmt.setInt(2, totalcount);
							pstmt.setInt(3, sucesscount);
							pstmt.setInt(4, failurecount);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();

							if(count>0){
								result="success";
							}
						}
						else{
							pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id = ?" );
							pstmt.setInt(1, totalcount);
							pstmt.setInt(2, sucesscount);
							pstmt.setInt(3, failurecount);
							pstmt.setDate(4, date);
							pstmt.setString(5, locid);
							int count=pstmt.executeUpdate();
							if(count>0){
								result="success";
							}
						}
					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pst!=null && !pst.isClosed()){
					pst = null;
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getEventDetails Ending");
		}

	}

	public void getLateComingStudentDetails(String lateCode,String lateDate,UserLoggingsPojo custdetails,String log_audit_session,String module,String submodule, String locid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getLateComingStudentDetails Starting");
		Connection conn=null;
		PreparedStatement pstmt=null,pstmt2 = null,pstmt1 = null,pst2= null,pst3 = null;
		ResultSet result=null,rs1 = null,rst2 = null;
		String result1=null;
		int count1=0;
		int totalcount=0;
		try{
			SMSAuditVO smsAuditVO = null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt2=conn.prepareStatement(SQLUtilConstants.LATE_COMING_STUDENT);
			pstmt2.setString(1,lateCode);
			pstmt2.setString(2, HelperClass.convertUIToDatabase(lateDate));
			pstmt2.setString(3, locid);
			
			result=pstmt2.executeQuery();

			while(result.next()){

				int sucesscount=0;
				int failurecount=0;
				int totalSMSSent=0;
				int totalSMSBal=0;
				int totalSMS = 0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where loc_id=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){

				}else{

					String response=new SendSMS().sendSMS(result.getString("student_contact_mobileno"),result.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setCreatedBy(result.getString("CreatedUser"));
					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(result.getString("student_contact_mobileno"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(result.getString("smsContent"));
					smsAuditVO.setSmsDate(result.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);
					if(messageId!=null && messageId.contains("success")){
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

					smsAuditVO.setStudentAdmissionNo(result.getString("student_admissionno_var"));
					String templateCode=result.getString("TemplateCode");
					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(lateCode);


					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}
				java.sql.Date date=HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;

				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2, locid);
				rs1=pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);

					pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ?  and loc_id=?");
					pst3.setInt(1, totalcount);
					pst3.setInt(2, totalcount);
					pst3.setInt(3, totalSMS);
					pst3.setString(4, locid);
					int count2 = pst3.executeUpdate();

					if(count1 == 0){
						pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id) values(?,?,?,?,?)");
						pstmt.setDate(1, date);
						pstmt.setInt(2, totalcount);
						pstmt.setInt(3, sucesscount);
						pstmt.setInt(4, failurecount);
						pstmt.setString(5, locid);
						int count=pstmt.executeUpdate();

						if(count>0){
							result1="success";
						}
					}
					else{
						pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ?  and loc_id=?" );
						pstmt.setInt(1, totalcount);
						pstmt.setInt(2, sucesscount);
						pstmt.setInt(3, failurecount);
						pstmt.setDate(4, date);
						pstmt.setString(5, locid);
						int count=pstmt.executeUpdate();
						if(count>0){
							result1="success";
						}
					}

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(result!=null && (!result.isClosed())){
					result.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pstmt2!=null && !pstmt2.isClosed()){
					pstmt2 = null;
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getEventDetails Ending");
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails : getLateComingStudentDetails Ending");

	}


	public void getUniformDetails(String uniformCode,String uniformDate,UserLoggingsPojo custdetails){

		try{

			/*CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveUniformSMS(?,?)}");
		callableStatement.setString(1, uniformCode);
		callableStatement.setString(2, uniformDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(uniformCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void getAbsentDetails(String absentCode,String absentDate,String log_audit_session,String module,String submodule,UserLoggingsPojo custdetails, String locid){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getAbsentDetails Starting");

		ResultSet rs = null, rst2 = null,rs1 = null;
		PreparedStatement pstmt=null,pst2 = null,pst3 = null,pstmt1 = null,pst = null;
		Connection conn=null;
		String result=null;
		int count1=0;
		int totalcount=0;

		try{
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			SMSAuditVO smsAuditVO = null;

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.GET_ABSENT_DETAILS);
			pst.setString(1, absentCode.trim());
			pst.setString(2, absentDate.trim());
			pst.setString(3, locid);
			rs = pst.executeQuery();

			while(rs.next()){

				int sucesscount=0;
				int failurecount=0;
				int totalSMSSent=0;
				int totalSMSBal=0;
				int totalSMS = 0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where loc_id=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){
					
				}else{
					String response = new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");

					smsAuditVO =  new SMSAuditVO();

					smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
					smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
					smsAuditVO.setSendBy(rs.getString("CREATED_BY"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(rs.getString("smsContent"));
					smsAuditVO.setSmsDate(rs.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);
					if(messageId!=null && messageId.contains("success")){
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
					smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));

					String templateCode=rs.getString("TemplateCode");

					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);

					smsAuditVO.setServiceCode(absentCode);
					smsAuditVO.setLog_audit_session(log_audit_session);
					smsAuditVO.setModule(module);
					smsAuditVO.setSubmodule(submodule);

					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);

				}

				java.sql.Date date = HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;
				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and loc_id=?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2, locid);
				rs1=pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);

					pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and loc_id=? ");
					pst3.setInt(1, totalcount);
					pst3.setInt(2, totalcount);
					pst3.setInt(3, totalSMS);
					pst3.setString(4, locid);
					int count2 = pst3.executeUpdate();

					if(count1 == 0){
						pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,loc_id ) values(?,?,?,?,?)");
						pstmt.setDate(1, date);
						pstmt.setInt(2, totalcount);
						pstmt.setInt(3, sucesscount);
						pstmt.setInt(4, failurecount);
						pstmt.setString(5, locid);
						int count=pstmt.executeUpdate();

						if(count>0){
							result="success";
						}
					}
					else{
						pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
						pstmt.setInt(1, totalcount);
						pstmt.setInt(2, sucesscount);
						pstmt.setInt(3, failurecount);
						pstmt.setDate(4, date);
						pstmt.setString(5, locid);
						int count=pstmt.executeUpdate();
						if(count>0){
							result="success";
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pst!=null && !pst.isClosed()){
					pst = null;
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getAbsentDetails Ending");
		}

	}

	public void getBirthdayWishesDetails(String birthdayCode,String birthdayDate,UserLoggingsPojo custdetails){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getBirthdayWishesDetails Starting");

		CallableStatement callableStatement=null;
		ResultSet rs=null;
		try{
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			callableStatement= JDBCConnection.getSeparateConnection(custdetails).prepareCall("{call approveBirthdayWishesSMS(?,?)}");
			callableStatement.setString(1, birthdayCode);
			callableStatement.setString(2, birthdayDate);
			rs=callableStatement.executeQuery();
			SMSAuditVO smsAuditVO=null;
			while(rs.next()){
				String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"),smsdetails);
				String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
				String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
				smsAuditVO =  new SMSAuditVO();
				smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
				String modifyTime=rs.getString("MODIFIED_TIME");
				if(modifyTime==null){
					smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
				}
				else{
					smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
				}
				smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
				smsAuditVO.setDeliveryStatus(deliveryresponse);
				smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
				smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
				smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setSmsContent(rs.getString("smsContent"));
				smsAuditVO.setSmsDate(rs.getString("smsDate"));
				smsAuditVO.setSmsResponse(response);
				if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
					smsAuditVO.setSmsStatus("Sent");
				else{
					smsAuditVO.setSmsStatus("Not Sent");
				}
				smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				String templateCode=rs.getString("TemplateCode");
				if(templateCode==null){
					templateCode="TEM1";
				}
				smsAuditVO.setTemplateCode(templateCode);
				smsAuditVO.setServiceCode(birthdayCode);

				int p=new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(callableStatement!=null){
					callableStatement.close();
				}

			}
			catch(SQLException e){
				e.printStackTrace();

			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails : getBirthdayWishesDetails Ending");
	}

	public String getFeeDetails(String declarationCode,String declarationDate,UserLoggingsPojo custdetails){

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getFeeDetails Starting");


		PreparedStatement callableStatement=null;
		ResultSet rs=null;
		try{
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			callableStatement = JDBCConnection.getSeparateConnection(custdetails).prepareStatement(SmsUtilsConstants.SEND_FEE_DETAILS);
			callableStatement.setString(1, declarationCode);
			callableStatement.setString(2, declarationDate);
			rs=callableStatement.executeQuery();

			SMSAuditVO smsAuditVO =null;
			while(rs.next()){
				String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"),smsdetails);
				String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
				String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
				smsAuditVO =  new SMSAuditVO();

				smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
				smsAuditVO.setDeliveryStatus(deliveryresponse);
				smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
				smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setSmsContent(rs.getString("smsContent"));
				smsAuditVO.setSmsDate(rs.getString("smsDate"));
				smsAuditVO.setSmsResponse(response);
				if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
					smsAuditVO.setSmsStatus("Sent");
				else{
					smsAuditVO.setSmsStatus("Not Sent");
				}
				smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
				String templateCode="TEM8";

				smsAuditVO.setTemplateCode(templateCode);
				smsAuditVO.setServiceCode(declarationCode);

				int p=new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(callableStatement!=null){
					callableStatement.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();

			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails : getFeeDetails Ending");

		return "Declaration SMS";
	}

	public void getHomeWorkDetails(String code, String latecomingdate, String log_audit_session, String module, String submodule,
			UserLoggingsPojo custdetails, String locid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getHomeWorkDetails Starting");

		ResultSet result = null;
		PreparedStatement pstmt = null,pst2 = null,pst3 = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs1 = null,rst2 = null;
		Connection conn=null;
		String result1=null;
		int count1=0;

		int sucesscount=0;
		int failurecount=0;
		int totalcount=0;

		int totalSMS = 0;
		try{
			SmsVo vo = new SmsVo();
			SMSAuditVO smsAuditVO = null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt2 = conn.prepareStatement(SQLUtilConstants.HOME_WORK);
			pstmt2.setString(1,code);
			pstmt2.setString(2,HelperClass.convertUIToDatabase(latecomingdate));
			pstmt2.setString(3, locid);
			result = pstmt2.executeQuery();

			while(result.next()){

				int totalSMSSent=0;
				int totalSMSBal=0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where `loc_id`=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){

				}else{

					String response = new SendSMS().sendSMS(result.getString("student_contact_mobileno"),result.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setCreatedBy(result.getString("createuser"));
					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(result.getString("student_contact_mobileno"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(result.getString("smsContent"));
					smsAuditVO.setSmsDate(result.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);

					if(messageId!=null && messageId.contains("success")){
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

					smsAuditVO.setStudentAdmissionNo(result.getString("student_admissionno_var"));
					String templateCode=result.getString("TemplateCode");
					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(code);


					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}

				java.sql.Date date = HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;

				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and `loc_id`=?");
				pstmt1.setDate(1,date);
				pstmt1.setString(2,locid);
				rs1 = pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);

					pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and `loc_id`=?");
					pst3.setInt(1, totalcount);
					pst3.setInt(2, totalcount);
					pst3.setInt(3, totalSMS);
					pst3.setString(4,locid);
					int count2 = pst3.executeUpdate();

					if(count1 == 0){
						pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,`loc_id`) values(?,?,?,?,?)");
						pstmt.setDate(1, date);
						pstmt.setInt(2, totalcount);
						pstmt.setInt(3, sucesscount);
						pstmt.setInt(4, failurecount);
						pstmt.setString(5,locid);
						int count = pstmt.executeUpdate();

						if(count>0){
							result1="success";
						}
					}
					else{
						pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and `loc_id`=?");
						pstmt.setInt(1, totalcount);
						pstmt.setInt(2, sucesscount);
						pstmt.setInt(3, failurecount);
						pstmt.setDate(4, date);
						pstmt.setString(5,locid);
						int count = pstmt.executeUpdate();
						if(count>0){
							result1="success";
						}
					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(result!=null && (!result.isClosed())){
					result.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pstmt2!=null && !pstmt2.isClosed()){
					pstmt2.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getHomeWorkDetails Ending");
		}

	}

	public void getOtherSMSDetails(String othercode, String otherdate, String log_audit_session, String module, String submodule,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getOtherSMSDetails Starting");
		ResultSet result = null;

		PreparedStatement pstmt=null;
		Connection conn=null;
		String result1=null;
		int count1=0;

		int sucesscount=0;
		int failurecount=0;
		int totalcount=0;
		try{
			SmsVo vo = new SmsVo();
			SMSAuditVO smsAuditVO = null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn=JDBCConnection.getSeparateConnection(custdetails);
			pstmt= conn.prepareStatement(SQLUtilConstants.OTHER_SMS_LIST);
			pstmt.setString(1,othercode);
			pstmt.setString(2,HelperClass.convertUIToDatabase(otherdate));
			result = pstmt.executeQuery();


			while(result.next()){

				String response=new SendSMS().sendSMS(result.getString("student_contact_mobileno"),result.getString("smsContent"),smsdetails);
				/*String messageId = response.substring(response.lastIndexOf("=")+1,response.length());*/
				String messageId=response.split(":")[4].split("<")[0];
				//String deliveryresponse = new SendSMS().getDeliveryStatus(messageId);
				String resarray[] = response.split(":");
				smsAuditVO =  new SMSAuditVO();
				smsAuditVO.setCreatedBy(result.getString("createdby"));
				//smsAuditVO.setDeliveryStatus(deliveryresponse);
				smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setMobileNo(result.getString("student_contact_mobileno"));
				smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
				smsAuditVO.setSmsContent(result.getString("smsContent"));
				smsAuditVO.setSmsDate(result.getString("smsDate"));
				smsAuditVO.setSmsResponse(response);
				if(messageId!=null && messageId.contains("success")){
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

				smsAuditVO.setStudentAdmissionNo(result.getString("student_admissionno_var"));
				String templateCode=result.getString("TemplateCode");
				if(templateCode==null){
					templateCode="TEM1";
				}
				smsAuditVO.setTemplateCode(templateCode);
				smsAuditVO.setServiceCode(othercode);


				int p= new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
			}
			pstmt.close();
			java.sql.Date date=HelperClass.getCurrentSqlDate();
			totalcount = sucesscount + failurecount;
			pstmt=conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ?");
			pstmt.setDate(1,date);
			ResultSet rs1=pstmt.executeQuery();
			while (rs1.next()){
				count1 = rs1.getInt(1);

				if(count1 == 0){
					pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`) values(?,?,?,?)");
					pstmt.setDate(1, date);
					pstmt.setInt(2, totalcount);
					pstmt.setInt(3, sucesscount);
					pstmt.setInt(4, failurecount);
					int count=pstmt.executeUpdate();

					if(count>0){
						result1="success";
					}
				}
				else{
					pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ?" );
					pstmt.setInt(1, totalcount);
					pstmt.setInt(2, sucesscount);
					pstmt.setInt(3, failurecount);
					pstmt.setDate(4, date);
					int count=pstmt.executeUpdate();
					if(count>0){
						result1="success";
					}
				}
			}
			rs1.close();
			pstmt.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null && (!result.isClosed())){
					result.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}

				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getOtherSMSDetails Ending");
		}

	}

	public void getFeeSMSList(String code, String date, String log_audit_session, String module, String submodule,
			UserLoggingsPojo custdetails, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SMSDetails: getHomeWorkDetails Starting");

		ResultSet result = null;
		PreparedStatement pstmt = null,pst2 = null,pst3 = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs1 = null,rst2 = null;
		Connection conn=null;
		String result1=null;
		int count1=0;

		int sucesscount=0;
		int failurecount=0;
		int totalcount=0;

		int totalSMS = 0;
		try{
			SmsVo vo = new SmsVo();
			SMSAuditVO smsAuditVO = null;
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt2 = conn.prepareStatement(SQLUtilConstants.FEE_SMS);
			pstmt2.setString(1,code);
			pstmt2.setString(2,HelperClass.convertUIToDatabase(date));
			pstmt2.setString(3, locid);
			result = pstmt2.executeQuery();

			while(result.next()){

				int totalSMSSent=0;
				int totalSMSBal=0;

				pst2 = conn.prepareStatement("SELECT total_sent_sms,total_remaining,total_sms FROM campus_total_sms_count where `loc_id`=?");
				pst2.setString(1, locid);
				rst2 = pst2.executeQuery();
				while(rst2.next()){
					totalSMSSent = rst2.getInt("total_sent_sms");
					totalSMSBal = rst2.getInt("total_remaining");
					totalSMS = rst2.getInt("total_sms");
				}

				if(totalSMSBal <= 0){

				}else{

					String response = new SendSMS().sendSMS(result.getString("student_contact_mobileno"),result.getString("smsContent"),smsdetails);
					String messageId=response.split(":")[4].split("<")[0];
					String resarray[] = response.split(":");
					smsAuditVO =  new SMSAuditVO();
					smsAuditVO.setCreatedBy(result.getString("created_by"));
					smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setMobileNo(result.getString("student_contact_mobileno"));
					smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
					smsAuditVO.setSmsContent(result.getString("smsContent"));
					smsAuditVO.setSmsDate(result.getString("smsDate"));
					smsAuditVO.setSmsResponse(response);

					if(messageId!=null && messageId.contains("success")){
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

					smsAuditVO.setStudentAdmissionNo(result.getString("student_admissionno_var"));
					String templateCode=result.getString("term_id");
					if(templateCode==null){
						templateCode="TEM1";
					}
					smsAuditVO.setTemplateCode(templateCode);
					smsAuditVO.setServiceCode(code);


					int p = new SendSMS().insertSMSDetails(smsAuditVO,custdetails);
				}

			    java.sql.Date date1 = HelperClass.getCurrentSqlDate();
				totalcount = sucesscount + failurecount;

				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_daily_smscount` WHERE `date`= ? and `loc_id`=? ");
				pstmt1.setDate(1,date1);
				pstmt1.setString(2, locid);
				rs1 = pstmt1.executeQuery();
				while (rs1.next()){
					count1 = rs1.getInt(1);

					pst3 = conn.prepareStatement("UPDATE `campus_total_sms_count` SET `total_sent_sms` = total_sent_sms + ?,`total_remaining` = total_remaining - ? WHERE `total_sms` = ? and `loc_id`=?");
					pst3.setInt(1, totalcount);
					pst3.setInt(2, totalcount);
					pst3.setInt(3, totalSMS);
					pst3.setString(4, locid);
					int count2 = pst3.executeUpdate();

					
					if(count1 == 0){
						pstmt = conn.prepareStatement("INSERT INTO `campus_daily_smscount`(`date`,`total_sms_sent`,`sucess_count`,`failure_count`,`loc_id`) values(?,?,?,?,?)");
						pstmt.setDate(1, date1);
						pstmt.setInt(2, totalcount);
						pstmt.setInt(3, sucesscount);
						pstmt.setInt(4, failurecount);
						pstmt.setString(5, locid);
						int count = pstmt.executeUpdate();

						if(count>0){
							result1="success";
						}
					}
					else{
						pstmt = conn.prepareStatement("UPDATE `campus_daily_smscount` SET `total_sms_sent`=total_sms_sent + ?,`sucess_count`=sucess_count + ?,`failure_count`=failure_count +? where date = ? and loc_id=?" );
						pstmt.setInt(1, totalcount);
						pstmt.setInt(2, sucesscount);
						pstmt.setInt(3, failurecount);
						pstmt.setDate(4, date1);
						pstmt.setString(5, locid);
						int count = pstmt.executeUpdate();
						if(count>0){
							result1="success";
						}
					}
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs1!=null && (!rs1.isClosed())){
					rs1.close();
				}
				if(rst2!=null && (!rst2.isClosed())){
					rst2.close();
				}
				if(result!=null && (!result.isClosed())){
					result.close();
				}
				if(pstmt!=null && !pstmt.isClosed()){
					pstmt.close();
				}
				if(pst3!=null && !pst3.isClosed()){
					pst3.close();
				}
				if(pstmt1!=null && !pstmt1.isClosed()){
					pstmt1.close();
				}
				if(pst2!=null && !pst2.isClosed()){
					pst2.close();
				}
				if(pstmt2!=null && !pstmt2.isClosed()){
					pstmt2.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in SMSDetails : getHomeWorkDetails Ending");
		}

	
		
	}

}
