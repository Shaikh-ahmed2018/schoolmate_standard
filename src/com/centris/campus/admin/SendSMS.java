package com.centris.campus.admin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.JPropertyReader;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PrincipalSMSVo;
import com.centris.campus.vo.SMSAuditVO;
import com.centris.campus.vo.SmsIntegrationApiVO;


public class SendSMS {

	private static Logger logger = Logger.getLogger(SendSMS.class);

public synchronized String sendSMS(String phoneNo, String content,SmsIntegrationApiVO smsdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: sendSMS Starting");
		String urlResponse = "";
		try {
			
			String message = content.trim();
           
			
			String smsURL = smsdetails.getSmsURL() +
					"APIKEY=" +smsdetails.getApiKey() +
					"&MobileNo=" +phoneNo+
					"&SenderID=" +smsdetails.getSenderId()+
					"&Message=" + URLEncoder.encode(message, "UTF-8") +
					"&ServiceName=" +smsdetails.getServiceName();
			
			System.out.println("smsURL : "+smsURL);
			URL url = new URL(smsURL);
			HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();
			httpsCon.setRequestMethod("GET");
			httpsCon.setConnectTimeout(10000);
			int intresult = httpsCon.getResponseCode();
			String strresult = httpsCon.getResponseMessage();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpsCon.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
			{
			response.append(inputLine);
			urlResponse = response.toString();
			}
			
			in.close();
			httpsCon.disconnect();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: sendSMS Ending");
		return urlResponse;
	}

	public synchronized String getDeliveryStatus(String messageId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: getDeliveryStatus Starting");
		String urlResponse = "";
		try {
			String deliveryURL = JPropertyReader.getProperty(
					"DELIVERY_STATUS_URL").trim();
			String smsMessageIDParameter = JPropertyReader.getProperty(
					"SMS_MESSAGE_ID_PARAMETER").trim();
			String smsString = deliveryURL + "&" + smsMessageIDParameter + "="
					+ messageId;
			logger.info("SMS Delivery String::" + smsString);
			Thread.sleep(1000);
			URL url = new URL(smsString);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				logger.error("Please enter an HTTP URL.");
				return "";
			}

			int code = connection.getResponseCode();
			logger.info("URL Status Code::::::::::" + code);
			if (code == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				String current;
				while ((current = in.readLine()) != null) {
					urlResponse += current;
				}
				logger.error(urlResponse);

				in.close();
			} else {
				logger.error("Not Connected to SMS Delivery URL");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: getDeliveryStatus Ending");
		return urlResponse;
	}

	public synchronized int insertSMSDetails(SMSAuditVO smsAuditVO,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: insertSMSDetails Starting");
		int count = 0;
		PreparedStatement insertSMSDetails = null;
		try {
			
			insertSMSDetails = (PreparedStatement) JDBCConnection
					.getStatement("insert into sms_audit(smsdate,approve_time,sms_content,createdby,sendby,approved_by,templateCode,student_admission_No,mobile_no,sms_status, delivery_status,delivery_time,sms_response,senttime,ServiceCode,msgId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",custdetails);
			insertSMSDetails.setString(1, smsAuditVO.getSmsDate());
			insertSMSDetails.setString(2, smsAuditVO.getApproveTime());
			insertSMSDetails.setString(3, smsAuditVO.getSmsContent());
			insertSMSDetails.setString(4, smsAuditVO.getCreatedBy());
			insertSMSDetails.setString(5, "");
			insertSMSDetails.setString(6, smsAuditVO.getApprovedBy());
			insertSMSDetails.setString(7, smsAuditVO.getTemplateCode());
			insertSMSDetails.setString(8, smsAuditVO.getStudentAdmissionNo());
			insertSMSDetails.setString(9, smsAuditVO.getMobileNo());
			insertSMSDetails.setString(10, smsAuditVO.getSmsStatus());
			insertSMSDetails.setString(11, smsAuditVO.getDeliveryStatus());
			insertSMSDetails.setString(12, smsAuditVO.getDeliveryTime());
			insertSMSDetails.setString(13, smsAuditVO.getSmsResponse());
			insertSMSDetails.setString(14, smsAuditVO.getSentTime());
			insertSMSDetails.setString(15, smsAuditVO.getServiceCode());
			insertSMSDetails.setString(16, smsAuditVO.getMsgId());

			count = insertSMSDetails.executeUpdate();
			if(count > 0){
				
				HelperClass.recordLog_Activity(smsAuditVO.getLog_audit_session(),smsAuditVO.getModule(),smsAuditVO.getSubmodule(),"Insert",insertSMSDetails.toString(),custdetails);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: insertSMSDetails Ending");
		return count;
	}

	public synchronized int insertPrincipalSMSDetails(PrincipalSMSVo smsVO,String custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS : insertPrincipalSMSDetails Starting");
		int count = 0;
		PreparedStatement insertSMSDetails = null;
		try {
			insertSMSDetails = (PreparedStatement) JDBCConnection
					.getStatement("insert into sms_audit(smsdate,sms_content,createdby,sendby,templateCode,student_admission_No,mobile_no,sms_status, delivery_status,delivery_time,sms_response,senttime,ServiceCode) values(?,?,?,?,?, ?,?,?,?,?, ?,?,?)",custid);
			insertSMSDetails.setString(1, smsVO.getSmsDate());
			insertSMSDetails.setString(2, smsVO.getSmsContent());
			insertSMSDetails.setString(3, smsVO.getCreatedBy());
			insertSMSDetails.setString(4, smsVO.getSendBy());
			insertSMSDetails.setString(5, smsVO.getTemplateCode());
			insertSMSDetails.setString(6, smsVO.getUserCode());
			insertSMSDetails.setString(7, smsVO.getMobileNo());
			insertSMSDetails.setString(8, smsVO.getSmsStatus());
			insertSMSDetails.setString(9, smsVO.getDeliveryStatus());
			insertSMSDetails.setString(10, smsVO.getDeliveryTime());
			insertSMSDetails.setString(11, smsVO.getSmsResponse());
			insertSMSDetails.setString(12, smsVO.getSentTime());
			insertSMSDetails.setString(13, smsVO.getServiceCode());
		
			count = insertSMSDetails.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!insertSMSDetails.isClosed()) {
					insertSMSDetails.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS : insertPrincipalSMSDetails Ending");
		return count;
	}

	public int insertSMSDetails(SMSAuditVO smsAuditVO, Connection smscon) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: insertSMSDetails Starting");
		int count = 0;
		PreparedStatement insertSMSDetails = null;
		try {
			
			insertSMSDetails = smscon.prepareStatement("insert into sms_audit(smsdate,approve_time,sms_content,createdby,sendby,approved_by,templateCode,student_admission_No,mobile_no,sms_status, delivery_status,delivery_time,sms_response,senttime,ServiceCode,msgId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			insertSMSDetails.setString(1, smsAuditVO.getSmsDate());
			insertSMSDetails.setString(2, smsAuditVO.getApproveTime());
			insertSMSDetails.setString(3, smsAuditVO.getSmsContent());
			insertSMSDetails.setString(4, smsAuditVO.getCreatedBy());
			insertSMSDetails.setString(5, "");
			insertSMSDetails.setString(6, smsAuditVO.getApprovedBy());
			insertSMSDetails.setString(7, smsAuditVO.getTemplateCode());
			insertSMSDetails.setString(8, smsAuditVO.getStudentAdmissionNo());
			insertSMSDetails.setString(9, smsAuditVO.getMobileNo());
			insertSMSDetails.setString(10, smsAuditVO.getSmsStatus());
			insertSMSDetails.setString(11, smsAuditVO.getDeliveryStatus());
			insertSMSDetails.setString(12, smsAuditVO.getDeliveryTime());
			insertSMSDetails.setString(13, smsAuditVO.getSmsResponse());
			insertSMSDetails.setString(14, smsAuditVO.getSentTime());
			insertSMSDetails.setString(15, smsAuditVO.getServiceCode());
			insertSMSDetails.setString(16, smsAuditVO.getMsgId());

			count = insertSMSDetails.executeUpdate();
			if(count > 0){
				
				HelperClass.recordLog_Activity(smsAuditVO.getLog_audit_session(),smsAuditVO.getModule(),smsAuditVO.getSubmodule(),"Insert",insertSMSDetails.toString(),smscon);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendSMS: insertSMSDetails Ending");
		return count;
	}

}
