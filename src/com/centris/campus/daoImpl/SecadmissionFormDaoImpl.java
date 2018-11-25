      package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.SecadmissionformatDao;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;

public class SecadmissionFormDaoImpl implements SecadmissionformatDao {    

	private static final Logger logger = Logger
			.getLogger(SecadmissionFormDaoImpl.class);


	@Override
	public String InsertSecadmissionform(ParentRequiresAppointmentForm secform,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SecadmissionFormDaoImpl: InsertSecadmissionform : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2=null;
		ResultSet rs2= null;

	


		Connection conn = null;
	
		String admissionno="",stuname="";
		int i=0;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			System.out.println("enquiry id is "+secform.getEnquiryid());
			if(secform.getEnquiryid() != null){
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERTING_SECOND_REGISTRATION_FORMS);
				pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details",userLoggingsVo));
				pstmt.setString(2, secform.getStudentfirstName());
				pstmt.setString(3, secform.getStudentLastName());
				pstmt.setString(4, HelperClass.convertUIToDatabase(secform.getDateofBirth().trim()));
				pstmt.setString(5, secform.getGender());
				pstmt.setString(6, secform.getNationality());
				pstmt.setString(7, secform.getState());
				pstmt.setString(8, secform.getReligion());
				pstmt.setString(9, secform.getOtherreligion());
				pstmt.setString(10, secform.getCaste());
				pstmt.setString(11, secform.getOthercaste());
				pstmt.setString(12, secform.getMothertongue());
				pstmt.setString(13, secform.getAadharno());
				pstmt.setString(14, secform.getAddress());
				pstmt.setString(15, secform.getAddressofcommunication());
				pstmt.setDouble(16, secform.getDistance());
				pstmt.setString(17, secform.getPreferedphno());
				pstmt.setString(18, secform.getSchoolLocation());
				pstmt.setString(19, secform.getSchemeofstudy());
				pstmt.setString(20, secform.getAnyotherboard());
				pstmt.setString(21, secform.getSecondlanguage());
				pstmt.setString(22, secform.getThirdlanguage());
				pstmt.setString(23, secform.getClassname());
				pstmt.setString(24, secform.getActivities());
				pstmt.setString(25, secform.getIsSibling());
				pstmt.setString(26, secform.getIsSiblingId());
				pstmt.setString(27, secform.getIsSiblingName());
				pstmt.setString(28, secform.getFatherName());
				pstmt.setString(29, secform.getFatherMobileNo());
				pstmt.setString(30, secform.getFatherQualification());
				pstmt.setString(31, secform.getFatherOccupation());
				pstmt.setString(32, secform.getFatherDesignation());
				
				pstmt.setString(33, secform.getFatherDepartment());
				
				pstmt.setDouble(34, secform.getFatherMonthlyIncome());
				pstmt.setString(35, secform.getFatherEmail());
				pstmt.setString(36, secform.getFathersOfficialAddress());
				pstmt.setString(37, secform.getMothername());
				pstmt.setString(38, secform.getMotherMobileNo());
				pstmt.setString(39, secform.getMotherQualification());
				pstmt.setString(40, secform.getMotherOccupation());
				pstmt.setString(41, secform.getMotherDesignation());
				
				pstmt.setString(42, secform.getMotherDepartment());
				
				pstmt.setDouble(43, secform.getMotherMonthlyIncome());
				pstmt.setString(44, secform.getMotherEmail());
				pstmt.setString(45, secform.getMothersOfficialAddress());
				pstmt.setString(46, secform.getParentsAlumni());
				pstmt.setString(47, secform.getFatherAlumniname());
				pstmt.setString(48, secform.getFatherAlumniyear());
				pstmt.setString(49, secform.getMotherAlumniname());
				pstmt.setString(50, secform.getMotherAlumniyear());
				pstmt.setTimestamp(51, HelperClass.getCurrentTimestamp());
				pstmt.setString(52, secform.getImageString());
				pstmt.setString(53, secform.getEnquiryid());
				pstmt.setString(54, secform.getCasteCategory());
				pstmt.setString(55, secform.getOthercastecategory());
				pstmt.setString(56, secform.getStream());

				 

				i=pstmt.executeUpdate();
				if(i>0){
					HelperClass.recordLog_Activity(secform.getLog_audit_session(),"Admission","Registration","Insert",pstmt.toString(),userLoggingsVo);
				}
				
				pstmt1=conn.prepareStatement(SQLUtilConstants.UPDATE_ENQUIRY_DETAILS);
				pstmt1.setString(1, secform.getEnquiryid());
		        int value=pstmt1.executeUpdate();
		        if(value > 0){
					HelperClass.recordLog_Activity(secform.getLog_audit_session(),"Admission","Registration","Update",pstmt.toString(),userLoggingsVo);
				}
			   }
			   pstmt1=conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORMNO);
			   pstmt1.setString(1, secform.getEnquiryid());
			   rs2=pstmt1.executeQuery();
			   while(rs2.next())
			   {
				   admissionno=rs2.getString("temporary_admission_id");
				   stuname=rs2.getString("studentname");
			   }
			   
			  /* if(i>0)
				{
					msg="inserted successfully"+"-"+admissionno;
				}
				else
				{
					msg="insertion failed";
				}*/
			   
			   
			   
			   conn.commit();
			   
			   
			

		   }   catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(rs2!=null && (!rs2.isClosed())){
					rs2.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if(pstmt1!=null && (!pstmt1.isClosed())){
					pstmt1.close();
				}

				if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
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
				+ " Control in SecadmissionFormDaoImpl: InsertSecadmissionform : Ending");
		return admissionno;
	}


	@Override
	public String InsertThirdadmissionform(ParentRequiresAppointmentForm secform) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SecadmissionFormDaoImpl: InsertThirdadmissionform : Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2=null;
		ResultSet rs2= null;

		
		Connection conn = null;

		String admissionno="",stuname="";
		int i=0;

		try {

			conn = JDBCConnection.getSeparateConnection();
			conn.setAutoCommit(false);  
			System.out.println("enquiry id is "+secform.getEnquiryid());
			if(secform.getEnquiryid() != null){
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERTING_THIRD_REGISTRATION_FORMS);
				
				pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_temporary_admisssion_details"));
				pstmt.setString(2, secform.getStudentfirstName());
				pstmt.setString(3, secform.getStudentLastName());
				pstmt.setString(4, HelperClass.convertUIToDatabase(secform.getDateofBirth().trim()));
				pstmt.setString(5, secform.getGender());
				pstmt.setString(6, secform.getNationality());
				pstmt.setString(7, secform.getPlaceofBirth());
				pstmt.setString(8, secform.getState());
				pstmt.setString(9, secform.getReligion());
				pstmt.setString(10, secform.getOtherreligion());
				pstmt.setString(11, secform.getCaste());
				pstmt.setString(12, secform.getOthercaste());
				pstmt.setString(13, secform.getCasteCategory());
				pstmt.setString(14, secform.getOthercastecategory());
				pstmt.setString(15, secform.getMothertongue());
				pstmt.setString(16, secform.getAadharno());
				pstmt.setString(17, secform.getAddress());
				pstmt.setString(18, secform.getAddressofcommunication());
				pstmt.setString(19, secform.getPreferedphno());
				pstmt.setString(20, secform.getSchoolLocation());
				pstmt.setString(21, secform.getSchemeofstudy());
				pstmt.setString(22, secform.getAnyotherboard());
				pstmt.setString(23, secform.getQualifying());
				pstmt.setString(24, secform.getActivities());
				pstmt.setString(25, secform.getClassname());
				pstmt.setString(26, secform.getSpecilization());
				pstmt.setString(27, secform.getOptionalsubject());
                pstmt.setString(28, secform.getFatherName());
				pstmt.setString(29, secform.getFatherMobileNo());
				pstmt.setString(30, secform.getFatherOccupation());
				
				pstmt.setString(31, secform.getFatherDesignation());
				pstmt.setString(32, secform.getFatherDepartment());
				
				
				pstmt.setDouble(33, secform.getFatherMonthlyIncome());
				pstmt.setString(34, secform.getFatherEmail());
				pstmt.setString(35, secform.getFathersOfficialAddress());
				pstmt.setString(36, secform.getMothername());
				pstmt.setString(37, secform.getMotherMobileNo());
				pstmt.setString(38, secform.getMotherOccupation());
				
				pstmt.setString(39, secform.getMotherDesignation());
				pstmt.setString(40, secform.getMotherDepartment());
				
				pstmt.setDouble(41, secform.getMotherMonthlyIncome());
				pstmt.setString(42, secform.getMotherEmail());
				pstmt.setString(43, secform.getMothersOfficialAddress());
                pstmt.setTimestamp(44, HelperClass.getCurrentTimestamp());
				pstmt.setString(45, secform.getImageString());
				pstmt.setString(46, secform.getMarkImageFile());
				pstmt.setString(47, secform.getEnquiryid());
				pstmt.setString(48, secform.getStream());
				System.out.println("temp reg "+pstmt);

				i=pstmt.executeUpdate();

				pstmt1=conn.prepareStatement(SQLUtilConstants.UPDATE_ENQUIRY_DETAILS);
				pstmt1.setString(1, secform.getEnquiryid());
				pstmt1.executeUpdate();
			}
			pstmt1=conn.prepareStatement(SQLUtilConstants.GET_ADMISSION_FORMNO);
			pstmt1.setString(1, secform.getEnquiryid());
			rs2=pstmt1.executeQuery();
			while(rs2.next())
			{
				admissionno=rs2.getString("temporary_admission_id");
				stuname=rs2.getString("studentname");
			}

			/*if(i>0)
			{
				msg="inserted successfully"+"-"+admissionno;
			}
			else
			{
				msg="insertion failed";
			}*/

			conn.commit();
		}   catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if(rs2!=null && (!rs2.isClosed())){
					rs2.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}

				if(pstmt2!=null && (!pstmt2.isClosed())){
					pstmt2.close();
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
				+ " Control in SecadmissionFormDaoImpl: InsertThirdadmissionform : Ending");
		return admissionno;
	}
	
}
