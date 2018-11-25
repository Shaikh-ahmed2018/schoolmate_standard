package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StringUtilContants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.centris.campus.vo.UploadStudentXlsVO;



public class UploadStudentXLSDaoImpl {

	private static Logger logger = Logger
			.getLogger(UploadStudentXLSDaoImpl.class);

	public int checkEmpCountBeforeInsert(UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in UploadStudentXLSDaoImpl: checkEmpCountBeforeInsert : Starting");
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(UploadStudentXLSqlUtil.CHECK_BEFORINSERT_COUNT);
		/*	System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);*/

			rs = pstmt.executeQuery();
			while (rs.next()) {
				beforeInsertCount = rs.getInt(1);

			}
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} 
		finally {

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
				+ " Control in UploadStudentXLSDaoImpl:checkEmpCountBeforeInsert Ending");

		return beforeInsertCount;
}

	
	public int checkStudentID(String studentId,String schoolId,Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkStudentID : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {
			ps_emp_count = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_STUDENT_ID);
			ps_emp_count.setString(1, studentId);
			ps_emp_count.setString(2, schoolId);
			//System.out.println("CHECK_STUDENT_ID -->>"+ps_emp_count);
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
				+ " Control in UploadStudentXLSDaoImpl : checkStudentID :  Ending");
		return 0;
	}

	public int checkCategorycode(String category, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkCategorycode : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CATEGORY_ID);
			ps_emp_count.setString(1, category);
			/*System.out.println("category = "+category);*/
			rs_emp_count = ps_emp_count.executeQuery();
			/*System.out.println("check_category_id "+ps_emp_count);*/
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
				+ " Control in UploadStudentXLSDaoImpl : checkCategorycode : Ending");
		return 0;
	}

	public int checkClassCode(String classname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkClassCode : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {
			ps_emp_count = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_ID);
			ps_emp_count.setString(1, classname);
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
				+ " Control in  UploadStudentXLSDaoImpl : checkClassCode : Ending");
		return 0;
	}

	public int checkSectionCode(String sectionname, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkSectionCode : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;

		try {

			ps_emp_count = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_ID);
			ps_emp_count.setString(1, sectionname);
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
				+ " Control in UploadStudentXLSDaoImpl : checkSectionCode :  Ending");
		return 0;
	}

	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> successlist,Connection connection, String log_audit_session,
			UserLoggingsPojo userLoggingsVo, long stucount) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + "Control in UploadStudentXLSDaoImpl : insertEmpXSL : Starting");
	
		Set<UploadStudentXlsVO> failurelistOnDiompl = new LinkedHashSet<UploadStudentXlsVO>();
		ResultSet rs_emp_count=null;
		ResultSet rsCountStudentSection=null;
		String registrationNo = null;
		PreparedStatement pstmcount = null;
		PreparedStatement academicYearpre = null;
		PreparedStatement prclassName=null;
		PreparedStatement preParentReg=null;
		PreparedStatement preChildParentUpdate=null;
		PreparedStatement precategoryName=null;
		 PreparedStatement pstmclasscount =null;
		PreparedStatement prsectionName=null;
		PreparedStatement ps_student_add = null;
		PreparedStatement ps_emp_count= null;
		
		PreparedStatement PScountStudentPerSection=null;
		PreparedStatement PSsectionStrength=null;
		Connection conn = null;
		
		String academicYear = "";
		String relationship = "";
		String parentId="";
		int noOfSutdentInSection=0;
	   long count1=0;
		try{
			
			/*System.out.println("DAOIMPL Is Working Student Excel file Upload");*/
			    conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			   
			    conn.setAutoCommit(false);
		    	
			    ps_student_add = conn.prepareStatement(UploadStudentXLSqlUtil.INSERT_STUDENT);
				//academicYearpre = conn.prepareStatement(StudentRegistrationSQLUtilConstants.GET_ACADEMIC_YEAR_BYID);
				pstmcount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_ACADEMIC_YEAR);
				prclassName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_NAME);
			    preParentReg = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_REG_EXCEL_UPLOAD);
			    preChildParentUpdate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.PARENT_CHILD_INSERT);
			    //precategoryName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.CLASS_STREAM);
			    //prsectionName = conn.prepareStatement(StudentRegistrationSQLUtilConstants.SECTION_NAME);
			    pstmclasscount = conn.prepareStatement(StudentRegistrationSQLUtilConstants.COUNT_CLASS);
				//countDuplicate = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_DUPLICATE);
				
				PScountStudentPerSection=conn.prepareStatement(UploadStudentXLSqlUtil.COUNT_STUDENT_PER_SECTION);
				PSsectionStrength=conn.prepareStatement(UploadStudentXLSqlUtil.SECTION_STRENGTH);
				
				long studentcount=HelperClass.getNumberOFStudentCount("loc","academic",userLoggingsVo);
				
				long custStudentCount=getStudentCountFromCustermertable(conn,userLoggingsVo);
				UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO(); 
				
				 
				if((custStudentCount-studentcount)>successlist.size()){
					//count1=studentcount-successlist.size();
				
			    for(int i=0;i<successlist.size();i++){
			    	 UploadStudentXlsVO uploadStudentXSLVo1 = new UploadStudentXlsVO(); 
				     
			    	
			    	
				 int admissioncount=checkStudentID(successlist.get(i).getStudentAdmissionNo().trim(),
						 successlist.get(i).getSchoolName(),connection,userLoggingsVo);
				 
				 if(admissioncount==0){
					 
					uploadStudentXSLVo.setStudentFirstName(successlist.get(i).getStudentFirstName()+" "+successlist.get(i).getStudentLastName());
					uploadStudentXSLVo.setStudentAdmissionNo(successlist.get(i).getStudentAdmissionNo());
					uploadStudentXSLVo.setStudentRegNo(successlist.get(i).getApplicationNo());
					uploadStudentXSLVo.setDateofJoin(successlist.get(i).getDateofJoin());
					uploadStudentXSLVo.setAcademicYear(successlist.get(i).getAcademicYear());
					uploadStudentXSLVo.setCategory(successlist.get(i).getStreamValue());
					uploadStudentXSLVo.setClassname(successlist.get(i).getCalssValue());
					/*System.out.println("=============diompl class===========");
						System.out.println(uploadStudentXSLVo.getClassname());
					
					System.out.println("=============diompl class===========");*/
					uploadStudentXSLVo.setSectionname(successlist.get(i).getSectionValue());
				 	
				 	String studentidgenerator = IDGenerator.getPrimaryKeyID("campus_student",userLoggingsVo);
				 	Timestamp createdDate = HelperClass.getCurrentTimestamp();
				 
					conn.setAutoCommit(false);
				 	
				 	String sectionId=successlist.get(i).getSectionname();
				 	String accyear = successlist.get(i).getAcademicYear();
				 	String school =  successlist.get(i).getSchoolName();
				 	String academicYearId=getAcademicYearId(successlist.get(i).getAcademicYear(),connection);
				 	//fetching section Strength
				 	PSsectionStrength.setString(1,sectionId);
				 	PSsectionStrength.setString(2,school);
				 	
				 	ResultSet rsSectionCount=PSsectionStrength.executeQuery();
				 	
				 	rsSectionCount.next();
				 	int sectionStrength=rsSectionCount.getInt(1);
				 	
				 	
				 	//counting the current no of student in section
				 	PScountStudentPerSection.setString(1, sectionId);
				 	PScountStudentPerSection.setString(2,academicYearId);
				 	PScountStudentPerSection.setString(3,school);
				 	
				 	/*System.out.println("COUNT_STUDENT_PER_SECTION -->>"+PScountStudentPerSection);*/
				 	
				 	rsCountStudentSection=PScountStudentPerSection.executeQuery();
				 	
				 	while(rsCountStudentSection.next()){
				 		noOfSutdentInSection=rsCountStudentSection.getInt(1);
				 	}
				 	
				 	/*System.out.println("noOfSutdentInSection>=sectionStrength "+noOfSutdentInSection+" -- "+sectionStrength);*/
				 	
					if(noOfSutdentInSection>=sectionStrength){
						uploadStudentXSLVo1.setStudentFirstName(successlist.get(i).getStudentFirstName().trim()+"  "+successlist.get(i).getStudentLastName().trim());
						uploadStudentXSLVo1.setStudentAdmissionNo(successlist.get(i).getStudentAdmissionNo().trim());
						uploadStudentXSLVo1.setDateofJoin(successlist.get(i).getDateofJoin().trim());
						uploadStudentXSLVo1.setAcademicYear(successlist.get(i).getAcademicYear());
						uploadStudentXSLVo1.setCategory(successlist.get(i).getCategoryName()); 
						uploadStudentXSLVo1.setClassname(successlist.get(i).getClassNameById());
						uploadStudentXSLVo1.setSectionname(successlist.get(i).getSectionNameById());
						uploadStudentXSLVo1.setReason("No. of Seats are exceeded in this section.");
						failurelistOnDiompl.add(uploadStudentXSLVo1);
					}else{

						ResultSet rs = pstmcount.executeQuery();
						rs.next();
						int count = rs.getInt(1);
							
						pstmclasscount.setString(1, successlist.get(i).getStudClassId());
						ResultSet rsClass = pstmclasscount.executeQuery();
						
						rsClass.next();
						int classcount = rsClass.getInt(1);
						String studentRegNo = null;
						
						
						/*System.out.println("DIOMPL :: class count :: "+classcount);*/
						studentRegNo = StringUtilContants.STUDENT_REGISTRATION_NO + "-";
						if (classcount < 9) {
							int k = classcount;
							studentRegNo = studentRegNo + "000" + (++k);
						} else if (classcount < 99) {
							int k = classcount;
							studentRegNo = studentRegNo + "00" + (++k);
						} else if (classcount < 999) {
							int k = classcount;
							studentRegNo = studentRegNo + "0" + (++k);
						} else {
							int k = classcount;
							studentRegNo = studentRegNo + (++k);
						}
						
						academicYear=successlist.get(i).getAcademicYear();
						/*System.out.println("Academic Year: "+academicYear);*/
						registrationNo = StringUtilContants.STUDENT_ADMISSION_NO + "-";
						
						if (count < 9) {
							int j = count;
							registrationNo = registrationNo + "000" + (++j) + "/"+ academicYear;
						} else if (count < 99) {
							int j = count;
							registrationNo = registrationNo + "00" + (++j) + "/"+ academicYear;
						} else if (count < 999) {
							int j = count;
							registrationNo = registrationNo + "0" + (++j) + "/"+ academicYear;
						} else {
							int j = count;
							registrationNo = registrationNo + (++j) + "/" + academicYear;
						}
						
						String dob1=successlist.get(i).getDateofBirth();
						Date today=HelperClass.getCurrentSqlDate();
						List<String> monthList=HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(dob1), today.toString());
						int ageOnDays=monthList.size();
						String ageOnYears=""+ageOnDays/365;

						   
						
						//student registration
						
					   	ps_student_add.setString(1,studentidgenerator);
					 	ps_student_add.setString(2, studentRegNo); //For roll number
					 	ps_student_add.setString(3, successlist.get(i).getStudentAdmissionNo().trim()); //For addmission Number
					 	ps_student_add.setString(4, academicYearId);
					 	ps_student_add.setString(5, registrationNo); // For registartion number
					 	ps_student_add.setString(6, successlist.get(i).getStudentFirstName().trim());
					    ps_student_add.setString(7, successlist.get(i).getStudentLastName().trim());
						ps_student_add.setString(8, HelperClass.convertUIToDatabase((successlist.get(i).getDateofBirth().trim())));
						String genderconvert = successlist.get(i).getGender().trim().toLowerCase();
						String gender = StringUtils.capitalize(genderconvert);
						ps_student_add.setString(9, gender);
						ps_student_add.setString(10, successlist.get(i).getBloodGroup());
						ps_student_add.setString(11,ageOnYears.trim());
						ps_student_add.setDate(12, HelperClass.getSqlDateFromDdMmYyFormat(successlist.get(i).getDateofJoin().trim()));
						ps_student_add.setString(13, successlist.get(i).getReligion());
						String nationalityconvert = successlist.get(i).getNationality().trim().toLowerCase();
						String nationality = StringUtils.capitalize(nationalityconvert);
						ps_student_add.setString(14, nationality);
						ps_student_add.setString(15, successlist.get(i).getPhysicallyChallenged());
						ps_student_add.setString(16, successlist.get(i).getIdentificationMarks());
						ps_student_add.setString(17,successlist.get(i).getStudentSibilingIdInt());
						if(successlist.get(i).getStudentstatus().equalsIgnoreCase("active")){
							ps_student_add.setString(18, "active");
						}else if(successlist.get(i).getStudentstatus().equalsIgnoreCase("inactive")){
							ps_student_add.setString(18, "inactive");
						}
						ps_student_add.setString(19, successlist.get(i).getMedicalhistory());
						ps_student_add.setString(20, successlist.get(i).getRemarks());
						ps_student_add.setString(21, successlist.get(i).getCreateUser());
						ps_student_add.setTimestamp(22, createdDate);
						ps_student_add.setString(23, successlist.get(i).getCaste());
						ps_student_add.setString(24, successlist.get(i).getApplicationNo());
						ps_student_add.setString(25, successlist.get(i).getPhysicalchalreason());
						ps_student_add.setString(26, successlist.get(i).getSchoolName().trim());
						ps_student_add.setString(27, successlist.get(i).getIsParentsGuardianWorking());
						ps_student_add.setString(28, successlist.get(i).getTransferCertificateNo());
						/*String mediumconvert = successlist.get(i).getMedium().trim().toLowerCase();
						String medium = StringUtils.capitalize(mediumconvert);
						ps_student_add.setString(29, medium);*/
						ps_student_add.setString(29, successlist.get(i).getCasteCategory());
						ps_student_add.setString(30, successlist.get(i).getAadharNo());
						String mothertongueconvert = successlist.get(i).getMotherTounge().trim().toLowerCase();
						String mothertongue = StringUtils.capitalize(mothertongueconvert);
						ps_student_add.setString(31, mothertongue);
						ps_student_add.setString(32, successlist.get(i).getWorkingParentsGuardianName());
					
						/*System.out.println("INSERT_STUDENT -->>"+ps_student_add);*/
						
						int add_student =  ps_student_add.executeUpdate();
						/*System.out.println("add_student -->>"+add_student);*/
						if(add_student >0){
							HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",ps_student_add.toString(),conn);
						}
						//To store student class details
						
						PreparedStatement scpstmt = conn.prepareStatement(UploadStudentXLSqlUtil.STUDENT_CLASS_REGISTRATION);

						scpstmt.setString(1,studentidgenerator.trim());
						scpstmt.setString(2,academicYearId);
						scpstmt.setString(3,successlist.get(i).getCategory());
						scpstmt.setString(4,successlist.get(i).getClassname());
						scpstmt.setString(5,successlist.get(i).getSectionname());
						if(successlist.get(i).getSpecilization()==null || successlist.get(i).getSpecilization().equalsIgnoreCase("")){
							scpstmt.setString(6, "-");
						}else{
							scpstmt.setString(6, successlist.get(i).getSpecilization());
						}
						scpstmt.setString(7,successlist.get(i).getCreateUser());
						scpstmt.setTimestamp(8,createdDate);
						scpstmt.setString(9,successlist.get(i).getSchoolName());
						scpstmt.setString(10,successlist.get(i).getSchoolName());
						scpstmt.setString(11,successlist.get(i).getRollno());
						scpstmt.setString(12,successlist.get(i).getStuimgurl());
						scpstmt.setString(13,successlist.get(i).getHousename());
						
						if(successlist.get(i).getStudentstatus().equalsIgnoreCase("Active")){
							scpstmt.setString(14,"STUDYING");
						}else{
							scpstmt.setString(14,"InActive");
						}
						
						
						int stu_class_add = scpstmt.executeUpdate();
						
						if(stu_class_add > 0){
							HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",scpstmt.toString(),userLoggingsVo);
						}
						scpstmt.close();
						
						//to store house details
						
						if(successlist.get(i).getHousename()!=null|| successlist.get(i).getHousename()!="" || successlist.get(i).getHousename().trim().length() !=0){
							
							PreparedStatement pstmthouse = conn.prepareStatement(UploadStudentXLSqlUtil.STUDENT_HOUSE_REGISTRATION);
							pstmthouse.setString(1,studentidgenerator.trim());
							pstmthouse.setString(2,successlist.get(i).getClassname());
							pstmthouse.setString(3,successlist.get(i).getSectionname());
							pstmthouse.setString(4,successlist.get(i).getHousename());
							pstmthouse.setString(5,academicYearId);
							pstmthouse.setString(6,successlist.get(i).getSchoolName());
							int stu_house = pstmthouse.executeUpdate();
							
							if(stu_house> 0){
								HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",pstmthouse.toString(),conn);
							}
							pstmthouse.close();
						}
						
						//To store student transport details
						String istransport = null;
						String stageID = "";
						if(successlist.get(i).getTransport().equalsIgnoreCase("No")){
							istransport = "N";
							stageID=null;
						
						}
						else if(successlist.get(i).getTransport().equalsIgnoreCase("Yes")){
							istransport = "Y";
							stageID = successlist.get(i).getTranslocation();
						}
						
						PreparedStatement stpstmt = conn.prepareStatement(StudentRegistrationSQLUtilConstants.STUDENT_TRANSPORT_REGISTRATION);

						stpstmt.setString(1, studentidgenerator.trim());
						stpstmt.setString(2, academicYearId);
						stpstmt.setString(3,istransport);
						stpstmt.setString(4,successlist.get(i).getTranscategory());
						stpstmt.setString(5,stageID);
 
						stpstmt.setString(6,successlist.get(i).getRoute());
						stpstmt.setString(7,successlist.get(i).getCreateUser());
						stpstmt.setTimestamp(8, createdDate);
						stpstmt.setString(9,successlist.get(i).getSchoolName());

						int stu_transport = stpstmt.executeUpdate();
						if(stu_transport > 0){
							HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",stpstmt.toString(),conn);
						}
						stpstmt.close();	
						
					// Parent Registration	
							parentId = IDGenerator.getPrimaryKeyID("campus_parents",userLoggingsVo);
							
							preParentReg.setString(1, parentId);
							preParentReg.setString(2, successlist.get(i).getFatherName());
							preParentReg.setString(3, successlist.get(i).getFatherMobileNo());
							preParentReg.setString(4, successlist.get(i).getFatheroccupation());
							preParentReg.setString(5, successlist.get(i).getFatherPanNO());
							if(successlist.get(i).getFatherAnnualIncome() != null && !successlist.get(i).getFatherAnnualIncome().equalsIgnoreCase("")){
								preParentReg.setDouble(6, Double.parseDouble(successlist.get(i).getFatherAnnualIncome()));
							}else{
								preParentReg.setDouble(6,0.0);
							}
							preParentReg.setString(7, successlist.get(i).getFatheremailId());
							preParentReg.setString(8, successlist.get(i).getFatherQualification());
							preParentReg.setString(9, successlist.get(i).getMotherName());
							preParentReg.setString(10, successlist.get(i).getMotherMobileNo());
							preParentReg.setString(11, successlist.get(i).getMotheroccupation());
							preParentReg.setString(12, successlist.get(i).getMotherPanNo());
							
							if(successlist.get(i).getMotherAnnualIncome() != null && !successlist.get(i).getMotherAnnualIncome().equalsIgnoreCase("")){
								preParentReg.setDouble(13, Double.parseDouble(successlist.get(i).getMotherAnnualIncome()));
							}else{
								preParentReg.setDouble(13,0.0);
							}
							preParentReg.setString(14, successlist.get(i).getMotheremailId());
							preParentReg.setString(15, successlist.get(i).getMotherQualification());
							
							preParentReg.setString(16, successlist.get(i).getGuardianName());
							preParentReg.setString(17, successlist.get(i).getGuardianMobileNo());
							preParentReg.setString(18, successlist.get(i).getGuardianOccupation());
							preParentReg.setString(19, successlist.get(i).getGuardianPanNo());
							
							if(successlist.get(i).getGuardianAnnualIncome() != null && !successlist.get(i).getGuardianAnnualIncome().equalsIgnoreCase("")){
								preParentReg.setDouble(20, Double.parseDouble(successlist.get(i).getGuardianAnnualIncome()));
							}else{
								preParentReg.setDouble(20,0.0);
							}
							preParentReg.setString(21, successlist.get(i).getGuardianemailId());
							preParentReg.setString(22, successlist.get(i).getGuardianQualification());
							preParentReg.setString(23, successlist.get(i).getPermanentAddress());
							preParentReg.setString(24, successlist.get(i).getPresentAddress());
							preParentReg.setString(25, successlist.get(i).getCreateUser());
							preParentReg.setTimestamp(26, HelperClass.getCurrentTimestamp());
							
							preParentReg.execute();
							HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",preParentReg.toString(),conn);
							
							//Parent Child Relation
							
							if (successlist.get(i).getPrimaryPerson().equalsIgnoreCase("father")) {
								relationship = "father";
							} else if (successlist.get(i).getPrimaryPerson().equalsIgnoreCase("mother")) {
								relationship = "mother";
							} else {
								relationship = "guardian";
							}
							
							preChildParentUpdate.setString(1, relationship);
							preChildParentUpdate.setString(2, parentId.trim());
							preChildParentUpdate.setString(3, studentidgenerator);
							
							int parent_child = preChildParentUpdate.executeUpdate();
							/*System.out.println(preChildParentUpdate);*/
							if(parent_child > 0){
								HelperClass.recordLog_Activity(log_audit_session,"Student","StudentExcelUpload","Insert",preChildParentUpdate.toString(),conn);
							}
							conn.commit();
					}
			 }
			 else{
					uploadStudentXSLVo.setStudentFirstName(successlist.get(i).getStudentFirstName().trim()+"  "+successlist.get(i).getStudentLastName().trim());
					uploadStudentXSLVo.setStudentAdmissionNo(successlist.get(i).getStudentAdmissionNo().trim());
					uploadStudentXSLVo.setDateofJoin(successlist.get(i).getDateofJoin().trim());
					uploadStudentXSLVo.setAcademicYear(successlist.get(i).getAcademicYear());
					uploadStudentXSLVo.setCategory(successlist.get(i).getCategoryName()); 
					uploadStudentXSLVo.setClassname(successlist.get(i).getClassNameById());
					uploadStudentXSLVo.setSectionname(successlist.get(i).getSectionNameById());
					uploadStudentXSLVo.setReason("Student Admission No. Already Exist");
					
				    failurelistOnDiompl.add(uploadStudentXSLVo); 
				 }
	 	    }
		}else{
			uploadStudentXSLVo.setReason("Number of student limit exceeded..!!");
		    failurelistOnDiompl.add(uploadStudentXSLVo); 
		}
			 
		 }catch (SQLException sqle) {
		       	sqle.printStackTrace();
			   logger.error(sqle.getMessage(),sqle);
		    } 
          catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			
			try {
				
		   if (ps_student_add != null && (!ps_student_add.isClosed())) 
		   {
				ps_student_add.close();
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

			if (pstmclasscount != null && (!pstmclasscount.isClosed())) {
				pstmclasscount.close();
			}

			if (precategoryName != null && (!precategoryName.isClosed())) {
				precategoryName.close();
			}

			if (preChildParentUpdate != null && (!preChildParentUpdate.isClosed())) {
				preChildParentUpdate.close();
			}

			if (preParentReg != null && (!preParentReg.isClosed())) {
				preParentReg.close();
			}

			if (prsectionName != null && (!prsectionName.isClosed())) {
				prsectionName.close();
			}

			if (prclassName != null && (!prclassName.isClosed())) {
				prclassName.close();
			}

			if (academicYearpre != null && (!academicYearpre.isClosed())) {
				academicYearpre.close();
			}
			if(PScountStudentPerSection !=null && (!PScountStudentPerSection.isClosed())){
				PScountStudentPerSection.close();
			}
			if(PSsectionStrength !=null && (!PSsectionStrength.isClosed())){
				PSsectionStrength.close();
			}
			
			if(conn!=null && (!conn.isClosed())){
				conn.close();
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
				+ " Control in UploadStudentXLSDaoImpl : insertEmpXSL : Ending");
		
		
		return failurelistOnDiompl;
	}


	private long getStudentCountFromCustermertable(Connection conn, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getStreamId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		long Id=0;

		try {
			psmt = conn.prepareStatement("SELECT no_of_students FROM campus_customer_details WHERE customerID=?");
			psmt.setString(1, userLoggingsVo.getCustId());
			rs = psmt.executeQuery();

			while(rs.next()) {
				Id=rs.getLong("no_of_students");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  UploadStudentXLSDaoImpl : getStreamId : Ending");
		return Id;
	}


	public String getStreamId(String category, Connection connection, String schoolLocationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getStreamId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String steramId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_STREAM_ID);
			psmt.setString(1, category);
			psmt.setString(2, schoolLocationId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				steramId=rs.getString("classstream_id_int");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  UploadStudentXLSDaoImpl : getStreamId : Ending");
		return steramId;
	}


	public String getClassId(String classname, Connection connection, String schoolLocationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String classId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID);
			psmt.setString(1, classname);
			psmt.setString(2, schoolLocationId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				classId=rs.getString("classdetail_id_int");
			}
			else{
				classId = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassId : Ending");
		return classId;
	}


	public String getSectionId(String sectionname, String classId, Connection connection, String schoolLocationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSectionId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sectionId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_SECTION_ID);
			psmt.setString(1, sectionname);
			psmt.setString(2, classId);
			psmt.setString(3, schoolLocationId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				sectionId=rs.getString("classsection_id_int");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSectionId : Ending");
		return sectionId;
	}


	public String getReligionId(String religion, Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getReligionId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String religionId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_RELIGION_ID);
			psmt.setString(1, religion);
			/*System.out.println("GET_RELIGION_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			while (rs.next()) {
				religionId=rs.getString("religionId");

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getReligionId : Ending");
		return religionId;
	}


	public String getSiblingId(String sibilingadminno, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String siblingId=null;
        
		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_SIBLING_ID);
			psmt.setString(1, sibilingadminno);
			/*System.out.println("GET_SIBLING_ID -->>"+psmt);*/
			rs = psmt.executeQuery();
			while (rs.next()) {
				siblingId=rs.getString("student_id_int");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingId : Ending");
		return siblingId;
	}


	public String getCasteId(String caste, String religionId, Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getCasteId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_ID);
			psmt.setString(1, caste);
			psmt.setString(2, religionId);
			/*System.out.println("GET_CASTE_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteId=rs.getString("casteId");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getCasteId : Ending");
		return casteId;
	}


	public String getTransportTypeId(String transcategory, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : UploadStudentXLSDaoImpl : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String trasportTypeId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_ID);
			psmt.setString(1, transcategory);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				trasportTypeId=rs.getString("type_id");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : UploadStudentXLSDaoImpl : Ending");
		return trasportTypeId;
	}


	public String getTransportLocationId(String translocation,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getTransportLocationId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String transportLocationId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_TRANSPORT_LOCATION_ID);
			psmt.setString(1, translocation);
			rs = psmt.executeQuery();

			while (rs.next()) {
				transportLocationId=rs.getString("stage_id");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getTransportLocationId : Ending");
		return transportLocationId;
	}


	public String getRouteId(String route, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getRouteId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String routeId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_ROUTE_ID);
			psmt.setString(1, route);
			/*System.out.println("GET_ROUTE_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			while (rs.next()) {
				routeId=rs.getString("RouteCode");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getRouteId : Ending");
		return routeId;
	}


	public String getSpecilizationId(String specilization, String stream, String classId, Connection connection, String schoolLocationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSpecilizationId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String specilizationId=null;

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_SPECILIZATINON_ID);
			psmt.setString(1, specilization);
			psmt.setString(2, stream);
			psmt.setString(3, classId);
			psmt.setString(4, schoolLocationId);
			/*System.out.println("GET_SPECILIZATINON_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			if (rs.next()) {
				specilizationId=rs.getString("Specialization_Id");
			}
			else{
				specilizationId = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSpecilizationId : Ending");
		return specilizationId;
	}


	public String getCasteCategoryId(String casteCategory, String casteId, String religionId, Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getCasteCategoryId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String casteCategoryId=null;

		try {

			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_CASTE_CATEGORY_ID);
			psmt.setString(1, casteCategory);
			psmt.setString(2, religionId);
			psmt.setString(3,casteId);
			/*System.out.println("GET_CASTE_CATEGORY_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			while (rs.next()) {
				casteCategoryId=rs.getString("castCatId");

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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getCasteCategoryId : Ending");
		return casteCategoryId;
	}


	public String getSchoolLocationId(String schoolName, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSchoolLocationId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String schoolNameId=null;
	
		try {
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_SCHOOL_LOCATION_ID);
			psmt.setString(1, schoolName.trim());
			/*System.out.println("GET_SCHOOL_LOCATION_ID-->>"+psmt);*/
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				schoolNameId=rs.getString("Location_Id");
			}
			else{
				schoolNameId = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSchoolLocationId :  Ending");
		return schoolNameId;
	}


	public String getAcademicYearId(String academicYear, Connection conn) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getAcademicYearId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String academicYearId=null;
		try {
			
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_ACADEMIC_YEAR_ID);
			psmt.setString(1, academicYear.trim());
			/*System.out.println("GET_ACADEMIC_YEAR_ID -->>"+psmt);*/
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				academicYearId=rs.getString("acadamic_id");
			}else{
				academicYearId = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getAcademicYearId : Ending");
		return academicYearId;
	}


	public int checkAdmissionDate(String academicYearId, String dateofJoin, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkAdmissionDate : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_START_END_ACADEMIC_YEAR);
			psmt.setString(1, academicYearId);
			psmt.setString(2, HelperClass.convertUIToDatabase(dateofJoin));
			psmt.setString(3,  HelperClass.convertUIToDatabase(dateofJoin));
			/*System.out.println(psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  UploadStudentXLSDaoImpl : checkAdmissionDate :  Ending");
		return result;
	}


	public int checkClassByStream(String stream, String classId,Connection connection, String schoolLocationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkClassByStream : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_CLASS_BY_STREAM);
			psmt.setString(1, stream);
			psmt.setString(2, classId);
			psmt.setString(3, schoolLocationId);
			
			/*System.out.println(psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkClassByStream :  Ending");
		return result;
	}

	public int checkSectionByClass(String classId, String sectionId,Connection connection, String schoolLocationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkSectionByClass : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;
		
		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SECTION_BY_CLASS);
			psmt.setString(1, classId);
			psmt.setString(2, sectionId);
			psmt.setString(3, schoolLocationId);
			
			/*System.out.println(psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkSectionByClass : Ending");
		return result;
	}


	public int checkSpecilizationByStream(String stream, String classId,String specilizationId, Connection connection, String schoolLocationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : checkSpecilizationByStream : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result=0;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.CHECK_SPECILIZATION_BY_CLASS_STREAM);
			psmt.setString(1, specilizationId);
			psmt.setString(2, classId);
			psmt.setString(3, stream);
			psmt.setString(4, schoolLocationId);
			
			/*System.out.println(psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  UploadStudentXLSDaoImpl : checkSpecilizationByStream :  Ending");
		return result;
	}


	public String getOccupationId(String occupation,Connection connection, String custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getOccupationId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String occupationId=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_OCCUPATION_ID);
			psmt.setString(1, occupation);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next()) {
				occupationId=rs.getString("occupationId");
			}else{
				occupationId="notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getOccupationId : Ending");
		return occupationId;
	}


	public String getHouseId(String housename, String schoolLocationId, String academicYearId, Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getHouseId : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String houseid=null;

		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.GET_HOUSE_ID);
			psmt.setString(1, housename);
			psmt.setString(2, schoolLocationId);
			psmt.setString(3, academicYearId);
			/*System.out.println("GET_HOUSE_ID -->>"+psmt);*/
			rs = psmt.executeQuery();

			if (rs.next()) {
				houseid=rs.getString("house_id");
			}else{
				houseid = "not found";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getHouseId :  Ending");
		return houseid;
	}


	public String getClassIdByLoc(String classId, Connection connection, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassIdByLoc : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String class_id = null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_CLASS_ID_BY_LOC);
			psmt.setString(1, classId);
			psmt.setString(2, locationid);
			rs = psmt.executeQuery();

			if (rs.next()) {
				class_id=rs.getString("classdetail_id_int");
			}
			else{
				class_id = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassIdByLoc :  Ending");
		return class_id;
	}


	public String getstuloc(String student, String accyearId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getstuloc : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String loc_id = null;
		Connection conn =null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt = conn.prepareStatement(UploadStudentXLSqlUtil.GET_LOC_ID_BY_STU);
			psmt.setString(1, student);
			psmt.setString(2, accyearId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				loc_id=rs.getString("locationId");
			}
			else{
				loc_id = "notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getstuloc : Ending");
		return loc_id;
	}


	public int validateStudent(String stuid,String accyearId, String locationid, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : validateStudent : Starting");
		int count = 0;
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs =null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT DISTINCT COUNT(*) FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ? AND locationId = ?");
			pstmt.setString(1,stuid);
			pstmt.setString(2,accyearId);
			pstmt.setString(3,locationid);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
				+ " Control in UploadStudentXLSDaoImpl : validateStudent : Ending");
		return count;
	}


	public String getstudetails(String student, String accyearId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getstudetails : Starting");
		String count = null;
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs =null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT locationId,classdetail_id_int,classsection_id_int,specilization FROM campus_student_classdetails WHERE student_id_int = ? AND fms_acadamicyear_id_int = ?");
			pstmt.setString(1,student);
			pstmt.setString(2,accyearId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4);
			}else{
				count = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
				+ " Control in UploadStudentXLSDaoImpl : getstudetails :  Ending");
		return count;
	}


	public String getSiblingNameValidation(String studentsiblingname, String studentsiblingadmission,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingNameValidation : Starting");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int siblingId=0;
		String value=null;
		try {
			psmt = connection.prepareStatement("select DISTINCT count(*) from campus_student where CONCAT(student_fname_var,' ',student_lname_var)=? "); /*and student_admissionno_var=?*/
			psmt.setString(1, studentsiblingname);
			/*psmt.setString(2, studentsiblingadmission);*/
			/*System.out.println("getSiblingNameValidation -->>"+psmt);*/
			rs = psmt.executeQuery();
			while (rs.next()) {
				siblingId=rs.getInt(1);
			}
			if(siblingId>0){
				value="found";
			}else{
				value="notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingNameValidation : Ending");
		return value;
	}

	public String getSiblingClassName(String studentsiblingname, String studentsiblingadmission,
			String studentsiblingclass, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingClassName : Starting");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int siblingId=0;
		String value=null;
		try {
			psmt = connection.prepareStatement("SELECT DISTINCT COUNT(*) FROM campus_student st JOIN campus_student_classdetails csc ON csc.student_id_int=st.student_id_int WHERE CONCAT(st.student_fname_var,' ',st.student_lname_var)=? AND st.student_admissionno_var=? AND  csc.classdetail_id_int=?");
			psmt.setString(1, studentsiblingname);
			psmt.setString(2, studentsiblingadmission);
			psmt.setString(3, studentsiblingclass);
			/*System.out.println("getSiblingNameValidation -->>"+psmt);*/
			rs = psmt.executeQuery();
			while (rs.next()) {
				siblingId=rs.getInt(1);
			}
			if(siblingId>0){
				value="found";
			}else{
				value="notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSiblingClassName : Ending");
		return value;
	}
	
	public String getSchoolIdByName(String schoolName, Connection connection, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSchoolIdByName : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String schoolNameId=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			psmt = conn.prepareStatement("SELECT DISTINCT Location_Id FROM campus_location WHERE Location_Name=?");
			psmt.setString(1, schoolName.trim());
			/*System.out.println("getSchoolIdByName-->>"+psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				schoolNameId=rs.getString("Location_Id");
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getSchoolIdByName : Ending");
		return schoolNameId;
	}


	public String getClassByStreamMap(String schoolLocationId, String stream, String classname, Connection connection)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String streamcheck=null;
		Connection conn = null;
		try {
			psmt = connection.prepareStatement("SELECT DISTINCT ccd.classdetails_name_var FROM campus_classdetail ccd JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int JOIN campus_location cl ON cl.Location_Id=ccd.locationId WHERE ccd.locationId=? AND ccd.classstream_id_int=? AND ccd.classdetails_name_var LIKE ?");
			psmt.setString(1, schoolLocationId);
			psmt.setString(2, stream);
			psmt.setString(3, classname);
			/*System.out.println("getSchoolIdByName-->>"+psmt);*/
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				streamcheck=rs.getString("classdetails_name_var");
			}else{
				streamcheck="notfound";
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Ending");
		return streamcheck;
	}


	public int getSectionStrength(String sectionId, String schoolId, UserLoggingsPojo userLoggingsVo,
			Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int streamcheck=0;
		 
		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.SECTION_STRENGTH);
			psmt.setString(1, sectionId);
			psmt.setString(2, schoolId);
			 
			/*System.out.println("getSchoolIdByName-->>"+psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				streamcheck=rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Ending");
		return streamcheck;
	}


	public int checkStudentStrength(String sectionId, String schoolId, String academicYearId,
			UserLoggingsPojo userLoggingsVo, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Starting");

		PreparedStatement psmt = null;
		ResultSet rs = null;
		int streamcheck=0;
		 
		try {
			psmt = connection.prepareStatement(UploadStudentXLSqlUtil.COUNT_STUDENT_PER_SECTION);
			psmt.setString(1, sectionId);
			psmt.setString(2, academicYearId);
			psmt.setString(3, schoolId);
			 
			/*System.out.println("COUNT_STUDENT_PER_SECTION-->>"+psmt);*/
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				streamcheck=rs.getInt(1);
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
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXLSDaoImpl : getClassByStreamMap : Ending");
		return streamcheck;
	}

	
}
