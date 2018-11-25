package com.centris.campus.util;

//this is a file used to send mails
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.admin.EmailContent;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.Issuedmenuvo;
import com.centris.campus.vo.LocationVO;


public class SendMail {

	String FROM_ADDRESS = "donotreply@navtel.in";
	String SENDER_PASSWORD = "Donotreply@123";
	private static final Logger logger = Logger.getLogger(SendMail.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ClientURL = res.getString("ClientURL");
	private static String schoolName = res.getString("SchoolName");
	
	/*
	 * String FROM_ADDRESS = "ranjith.sivan@spectrumconsultants.com"; String
	 * SENDER_PASSWORD = "ranjith1234";
	 */

	public synchronized String sendMail(String toAddress,
			InternetAddress[] ccList, String subject, String Bodymess)
			throws MessagingException, AddressException, Exception {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMail Starting");

		String mailHost = JPropertyReader.getProperty("MAIL.SMTP.HOST").trim();
		String mailPort = JPropertyReader.getProperty("MAIL.SMTP.PORT").trim();
		String mailUser = JPropertyReader.getProperty("SENDER.MAIL.ID").trim();
		String mailProtocal = JPropertyReader.getProperty("MAIL.TRANSPORT.PROTOCAL").trim();
		String sendenName = JPropertyReader.getProperty("MAIL.SENDER.NAME").trim();
		String startttlsEnable = JPropertyReader.getProperty("MAIL.SMTP.STARTTTLS.ENABLE").trim();
		String mailPassword = JPropertyReader.getProperty("SENDER.MAIL.PASSWORD").trim();
		String smtpAuth = JPropertyReader.getProperty("MAIL.SMTP.AUTH").trim();
		FROM_ADDRESS = mailUser;
		SENDER_PASSWORD = mailPassword;

		System.out.println(" mailHost : " + mailHost);
		System.out.println(" mailPort : " + mailPort);
		System.out.println(" mailUser : " + mailUser);
		System.out.println(" mailProtocal : " + mailProtocal);
		System.out.println(" startttlsEnable : " + startttlsEnable);
		System.out.println(" mailPassword : " + mailPassword);
		System.out.println(" smtpAuth : " + smtpAuth);

		System.out.println("Starting send mail ");
		try {
			Properties props = System.getProperties();

			props.put("mail.transport.protocol", mailProtocal);
			props.put("mail.smtp.socketFactory.fallback", "true");
			props.put("mail.smtp.host", mailHost);
			props.put("mail.smtp.port", mailPort);
			props.put("mail.smtp.user", FROM_ADDRESS);
			props.put("mail.smtp.starttls.enable", startttlsEnable);
			props.put("mail.smtp.auth", smtpAuth);
			//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			String smtpSslTrust = JPropertyReader.getProperty("MAIL.SMTP.SSL.TRUST");

			if (smtpSslTrust == null || smtpSslTrust.equalsIgnoreCase("")) {

			} else {
				System.out.println(" smtpSslTrust : " + smtpSslTrust);
				props.setProperty("mail.smtp.ssl.trust", smtpSslTrust);

			}

			Session mailSession = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(FROM_ADDRESS, SENDER_PASSWORD);
						}
					});

			// SecurityManager security = System.getSecurityManager();

			Authenticator auth = new SMTPAuthenticator();

			// Get a mail session and authenticate user
			Session session = Session.getDefaultInstance(props, auth);

			// Define a new mail message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendenName + "<" + FROM_ADDRESS + ">"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setRecipients(Message.RecipientType.CC, ccList);
			message.setSubject(subject);
			// Create a message part to represent the body text
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(Bodymess);

			// use a MimeMultipart as we need to handle the file attachments
			Multipart multipart = new MimeMultipart();

			// add the message body to the mime message
			multipart.addBodyPart(messageBodyPart);

			System.out.println();
			// Put all message parts in the message
			message.setContent(multipart);

			// Send the message

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(mailHost, FROM_ADDRESS, SENDER_PASSWORD);

			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("message sent");
		} catch (Exception e) {

			Logger loggger = Logger.getLogger(SendMail.class);
			loggger.info("Exception in sendMail part ." + e);
			e.printStackTrace();
			return null;
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMail Ending");
		return "sent";
	}

	// mehtod to authenticate user
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(FROM_ADDRESS, SENDER_PASSWORD);
		}
	}

	// ************************************************************

	public synchronized String sendMail(EmailContent em) throws IOException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMail Starting");
		String result = null;
		try {
			System.out.println("from Email Content");
			String[] recipients = em.getMailids();
			String subject = em.getSubject();
			String message = em.getMessage();
			String ccString = "";
			for (int i = 1; i < recipients.length; i++) {
				if (recipients[i] != null && !"".equals(recipients[i])
						&& !"null".equalsIgnoreCase(recipients[i]))
					ccString = ccString + "," + recipients[i];
			}
			if (!ccString.equals("")) {
				ccString = ccString.substring(1);
			}
			InternetAddress[] ccList = InternetAddress.parse(ccString);
			result = sendMail(recipients[0], ccList, subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMail Ending");
		return result;
	}

	public synchronized String sendMailtoChild(String mailid, String username, String domain, String locname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoChild Starting");
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from "+locname+"\n\n"
					+ "Thank you for Registering with us\n"
					/* This is not required for Basic Model 
					 * commented by Asha Mestha (01-02-2018)*/
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ domain+ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ username
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+locname+"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoChild Ending");
		return rst;
	}

	public synchronized String sendMailtoTeacher(String mailid,
			String username, String password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoTeacher Starting");
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view / update child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ password
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoTeacher Ending");
		return rst;
	}

	public synchronized String sendMailtoParent(String mailid, String username,
			String password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParent Starting");
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ password
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParent Ending");
		return rst;
	}
	
	public String sendMailToParentsOnRequestOfAdmisssionFromOnlinePortal(
			String emailId, String alternateemailId,
			String temporary_admission_id, String studentfirstName,
			String parentId,String dateofbirth) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailToParentsOnRequestOfAdmisssionFromOnlinePortal Starting");
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { emailId ,alternateemailId};
			em.setMailids(mails);
			em.setSubject("Application Confirmation Mail");
			em.setMessage("Dear Parent \n\n "
					+"Greetings from Centris School... \n\n"
					+"Your Application/Admission request number : "+ temporary_admission_id+" for student "+ studentfirstName +"  has been accepted. \n"
					+"We request you to come to the school for Interview/Discussion on :"+ dateofbirth +" \n\n"
					+ "Assuring Bright Future on best Grooming of your Child\n"
					+ "Centris School   \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailToParentsOnRequestOfAdmisssionFromOnlinePortal Ending");
		return rst;
	}

	public String sendMailtoParentsForreject(Issuedmenuvo obj) {
		System.out.println("it is going inside reject method for email:");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParentsForreject Starting");
        String rst1 = null;
		try {

			LocationVO vo = HelperClass.getCustSchoolInfo(obj.getPojo(),obj.getLocation_id());
			
			EmailContent em = new EmailContent();
			String[] mails = { obj.getParentEmailId() };
			em.setMailids(mails);
			em.setSubject("Regarding Admission"); 
			em.setMessage("Dear Parent," 
					+"\n"
					+ "Please to inform your child"+" "
					+ obj.getFullname()+" "
					+"admission rejected due to the"+" "
					+ obj.getRejectreason()+"."+" "
					+ "Thanks for your approach."
					+ "\n\nRegards,\n"
					+ vo.getSchname()+"\n"
					+ vo.getLocAddId()+"\n"
					+ vo.getCountryName()+"\n"
	                +"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			     rst1 = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParentsForreject Ending");
		return rst1;
	}
	
	public String sendMailtoParentsForCancel(Issuedmenuvo vo) {
		System.out.println("it is going inside reject method for email:");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParentsForCancel Starting");
        String rst1 = null;
		try {
			LocationVO obj = HelperClass.getCustSchoolInfo(vo.getPojo(),vo.getLocation_id());
			
			EmailContent em = new EmailContent();
			String[] mails = { vo.getParentEmailId() };
			em.setMailids(mails);
			em.setSubject("Regarding Admission"); 
			em.setMessage(
    	            "Dear Parent," 
					+"\n"
					+ "Please to inform your child"+" "
					+ vo.getFullname()+" "
					+"admission cancelled due to the"+" "
					+ vo.getCancelreason()+"."+" "
					+ "Thanks for your approach."
					+ "\n\nRegards,\n"
					+ obj.getSchname()+"\n"
					+ obj.getLocAddId()+"\n"
					+ obj.getCountryName()+"\n"
	                +"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			         rst1 = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParentsForCancel Ending");
		return rst1;
	}
	
	

	public static void main(String[] args) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : main Starting");
		try {

			EmailContent em = new EmailContent();
			String[] mails = { "dineshkumar.g@centrisinfotech.com" };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail ");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ "\nPassword : \n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			String rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : main Ending");
	}

	public  String validateClass(String classname, UserLoggingsPojo userLoggingsPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateClass Starting");
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsPojo);

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_DETAILSLIST);
			pstmt.setString(1, classname.trim());
			System.out.println("pstmt "+pstmt);
			rt=pstmt.executeQuery();

			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("returning  stream:" +stream);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateClass Ending");
		return stream;
	}
	
	private String validateLkgClass(String classname, UserLoggingsPojo userLoggingsPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateLkgClass Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		Connection conn = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsPojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LKG_DETAILS);
			pstmt.setString(1, classname.trim());
			rt=pstmt.executeQuery();
			
			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("rturn stream from primary:" +stream);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateLkgClass Ending");
		return stream;
	}
	private String validateSrSecondaryClass(String classname, UserLoggingsPojo userLoggingsPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateSrSecondaryClass Starting");
		PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		Connection conn = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsPojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SRSECONDARY_DETAILS);
			pstmt.setString(1, classname.trim());
			rt=pstmt.executeQuery();
			
			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("senior secondary class details:" +stream);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : validateSrSecondaryClass Ending");
		return stream;
	}
	public String sendMailtoParents(Issuedmenuvo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParents Starting");
		String rst1 = null;
		 
		try {
			System.out.println("classname is "+vo.getClassName());
			
			LocationVO obj = HelperClass.getCustSchoolInfo(vo.getPojo(), vo.getLocation_id());
			
			if(validateClass(vo.getClassName(),vo.getPojo()).equalsIgnoreCase(vo.getClassName())){
				EmailContent em = new EmailContent();
				String[] mails = { vo.getParentEmailId() };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
	            em.setMessage("Dear Parent,\nYour child"+" "+vo.getParentfirstName()+" "+"admission approved by our Management."+" "
	            		+"Please come to school with relevant documents for the admission within 3 days time."		
				        +"\n\n"
						+ "Regards,\n"
						+ obj.getSchname()+"\n"
						+ obj.getLocAddId()+"\n"
						+ obj.getCountryName()+"\n"
						+"----------------------------------------------------------------------\nThis is System generated mail, Please do not reply.");
				rst1 = new SendMail().sendMail(em);
			}else if(validateLkgClass(vo.getClassName(),vo.getPojo()).equalsIgnoreCase(vo.getClassName())){
				EmailContent em = new EmailContent();
				String[] mails = { vo.getParentEmailId() };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
				em.setMessage("Dear Parent,\nYour child"+" "+vo.getParentfirstName()+" "+"admission approved by our Management."+" "
	            		+ "Please come to school with relevant documents for the admission within 3 days time."		
				        +"\n\n"
						+ "Regards,\n"
						+ obj.getSchname()+"\n"
						+ obj.getLocAddId()+"\n"
						+ obj.getCountryName()+"\n"
						+"-----------------------------------------------------------------------\nThis is System generated mail, Please do not reply.");
				rst1 = new SendMail().sendMail(em);
			}else if(validateSrSecondaryClass(vo.getClassName(),vo.getPojo()).equalsIgnoreCase(vo.getClassName())){
				EmailContent em = new EmailContent();
				String[] mails = { vo.getParentEmailId() };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
				em.setMessage("Dear Parent,\nYour child"+" "+vo.getParentfirstName()+" "+"admission approved by our Management."+" "
	            		+ "Please come to school with relevant documents for the admission within 3 days time."		
	            		+"\n\n"
						+ "Regards,\n"
						+ obj.getSchname()+"\n"
						+ obj.getLocAddId()+"\n"
						+ obj.getCountryName()+"\n"
						+"-----------------------------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst1 = new SendMail().sendMail(em);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SendMail : sendMailtoParents Ending");
		return rst1;
	}
}