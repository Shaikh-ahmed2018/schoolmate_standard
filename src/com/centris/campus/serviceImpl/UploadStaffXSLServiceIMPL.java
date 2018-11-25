package com.centris.campus.serviceImpl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;


import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStaffXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.delegate.UploadStaffXSLBD;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;



public class UploadStaffXSLServiceIMPL extends UploadStaffXSLBD {
	
	private static Logger logger = Logger.getLogger(UploadStaffXSLServiceIMPL.class);

public Set<UploadStaffXlsVO> insertStaffXSL(
			List<UploadStaffXlsPOJO> list, String username, String duplicate,String log_audit_session,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStaffXSL : Starting");
		
		Connection connection = null;
		
		List<UploadStaffXlsPOJO> successlist=new ArrayList<UploadStaffXlsPOJO>();
		UploadStaffXLSDaoImpl daoImpl=new UploadStaffXLSDaoImpl();
	
	
		Set<UploadStaffXlsVO> failurelist = new LinkedHashSet<UploadStaffXlsVO>();
	
		failurelist.clear();
		successlist.clear();
		
		String validStaffId=null,validAbbrivationId=null,studentAdmissionValid=null;
		Boolean dobisvalid = true,dojisvalid = true,staffTypevalid=true,gendervalid=true,isStudentStudyvalid=true,maritelvalid=true;

		try {
			/*System.out.println("Service IMPL is working for Excel file student");*/
			connection=JDBCConnection.getSeparateConnection(custdetails);
			
			String int_regex="^[0-9]*$";
			String numbers_regx = "[0-9//]{10}";
			
			String string_regx="([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			String regexpforEmailId="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			 
			for (Iterator<UploadStaffXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {
				
				UploadStaffXlsPOJO uploadStaffXSLPOJO = (UploadStaffXlsPOJO) iterator.next();
				
				String locationId=null;
				if(uploadStaffXSLPOJO.getLocation() !=null || !uploadStaffXSLPOJO.getLocation().equalsIgnoreCase("")){
					locationId=new UploadStudentXLSDaoImpl().getSchoolLocationId(uploadStaffXSLPOJO.getLocation(), connection);
				}
				
				UploadStaffXlsVO uploadStaffXSLVo = new UploadStaffXlsVO();
				uploadStaffXSLPOJO.setCreatedby(username);
				
				uploadStaffXSLVo.setFirstName(uploadStaffXSLPOJO.getFirstName());
				String firstName=uploadStaffXSLPOJO.getFirstName();
				String lastName=uploadStaffXSLPOJO.getLastName();
				String fullName=firstName+" "+lastName;
				
				validStaffId=daoImpl.getStaffId(uploadStaffXSLPOJO.getRegistrationId(),locationId,connection,custdetails);
				validAbbrivationId=daoImpl.getStaffAbbrivationId(uploadStaffXSLPOJO.getAbbreviation(),locationId,connection,custdetails);
				
				studentAdmissionValid=daoImpl.getStudentAdmissionValid(uploadStaffXSLPOJO.getAdmissionNo(),locationId,connection,custdetails);
				
				dojisvalid = HelperClass.DateValidator(uploadStaffXSLPOJO.getDateOfJoining().replace("-", "/"));
				dobisvalid= HelperClass.DateValidator(uploadStaffXSLPOJO.getDob().replace("-", "/"));
				
				if(uploadStaffXSLPOJO.getTeachingType().equalsIgnoreCase("Teaching") || uploadStaffXSLPOJO.getTeachingType().equalsIgnoreCase("Non-Teaching") 
					 || uploadStaffXSLPOJO.getTeachingType().equalsIgnoreCase("GENERAL") || uploadStaffXSLPOJO.getTeachingType().equalsIgnoreCase("Office Staff")){
					staffTypevalid=false;
				}
				if(uploadStaffXSLPOJO.getGender().equalsIgnoreCase("male") || uploadStaffXSLPOJO.getGender().equalsIgnoreCase("female")){
					   gendervalid=false;
				 }
				
				if(uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("yes") || uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("no")){
					isStudentStudyvalid=false;
				}
				if(uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") || uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("no")){
					maritelvalid=false; 
				}
				
				String departmentId=null;
				if(uploadStaffXSLPOJO.getDepartment() !=null || !uploadStaffXSLPOJO.getDepartment().equalsIgnoreCase("")){
					/*System.out.println("department id bolck");*/
					departmentId=daoImpl.getDepartmentId(uploadStaffXSLPOJO.getDepartment(),connection);
				}
			
				String designationId=null;
				if(uploadStaffXSLPOJO.getDesignation() !=null || !uploadStaffXSLPOJO.getDesignation().equalsIgnoreCase("")){
					designationId=daoImpl.getDesignationId(uploadStaffXSLPOJO.getDesignation(),connection);
				}
				
				 
				/*String reportingToId=null;
				if(uploadStaffXSLPOJO.getReportingTo() !=null || !uploadStaffXSLPOJO.getReportingTo().equalsIgnoreCase("")){
					reportingToId=daoImpl.getReportingToId(uploadStaffXSLPOJO.getReportingTo());
				}
				String roleId=null;
				if(uploadStaffXSLPOJO.getRole() !=null || !uploadStaffXSLPOJO.getRole().equalsIgnoreCase("")){
					roleId=daoImpl.getRoleId(uploadStaffXSLPOJO.getRole());
				}*/
				 
				
				uploadStaffXSLVo.setRegistrationId(uploadStaffXSLPOJO.getRegistrationId());
				uploadStaffXSLVo.setDepartment(uploadStaffXSLPOJO.getDepartment());
				uploadStaffXSLVo.setDateOfJoining(uploadStaffXSLPOJO.getDateOfJoining());
				uploadStaffXSLVo.setDesignation(uploadStaffXSLPOJO.getDesignation());
				uploadStaffXSLVo.setTeachingType(uploadStaffXSLPOJO.getTeachingType());
				//uploadStaffXSLVo.setPrimarySubject(uploadStaffXSLPOJO.getPrimarySubject());
				/*uploadStaffXSLVo.setUserName(uploadStaffXSLPOJO.getUserName());
				uploadStaffXSLVo.setUserType(uploadStaffXSLPOJO.getUserType());
				uploadStaffXSLVo.setRole(uploadStaffXSLPOJO.getRole());*/
				uploadStaffXSLVo.setIsStudentStudying(uploadStaffXSLPOJO.getIsStudentStudying());
				uploadStaffXSLVo.setGender(uploadStaffXSLPOJO.getGender());
				uploadStaffXSLVo.setDob(uploadStaffXSLPOJO.getDob());
				uploadStaffXSLVo.setQualification(uploadStaffXSLPOJO.getQualification());
				uploadStaffXSLVo.setMobileNo(uploadStaffXSLPOJO.getMobileNo());
				uploadStaffXSLVo.setFatherName(uploadStaffXSLPOJO.getFatherName());
				uploadStaffXSLVo.setMotherName(uploadStaffXSLPOJO.getMotherName());
				uploadStaffXSLVo.setFatherMobile(uploadStaffXSLPOJO.getFatherMobile());
				uploadStaffXSLVo.setMotherMobile(uploadStaffXSLPOJO.getMotherMobile());
				uploadStaffXSLVo.setPresentAddress(uploadStaffXSLPOJO.getPresentAddress());
				uploadStaffXSLVo.setPermanentAddress(uploadStaffXSLPOJO.getPermanentAddress());
				uploadStaffXSLVo.setMaritalStatus(uploadStaffXSLPOJO.getMaritalStatus());
				 
				uploadStaffXSLPOJO.getSpouseName();
				uploadStaffXSLPOJO.getSpouseMobile();
				
				if(uploadStaffXSLPOJO.getRegistrationId()==null || uploadStaffXSLPOJO.getRegistrationId().trim().equalsIgnoreCase("")){
					uploadStaffXSLVo.setFirstName(fullName);
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Staff Id Should Not be Empty.");
				}
				else if(validStaffId.equalsIgnoreCase("duplicate")){
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Staff Id already exist..!!");
				}
				/*else if(uploadStaffXSLPOJO.getAbbreviation()==null || uploadStaffXSLPOJO.getAbbreviation().trim().equalsIgnoreCase("")){
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Abbreviation Should Not be Empty.");
				}*/
				else if(validAbbrivationId.equalsIgnoreCase("duplicate") &&(uploadStaffXSLPOJO.getAbbreviation()!=null && !uploadStaffXSLPOJO.getAbbreviation().trim().equalsIgnoreCase(""))){
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Abbreviation Id already exist..!!");
				}
				else if(uploadStaffXSLPOJO.getFirstName().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getFirstName().equals("-") || uploadStaffXSLPOJO.getFirstName()==null){
					uploadStaffXSLVo.setReason("First Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!(uploadStaffXSLPOJO.getFirstName().matches(string_regx))){
					uploadStaffXSLVo.setReason("First Name Should be Alphabet");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(daoImpl.checkRegistrainId(uploadStaffXSLPOJO.getRegistrationId(),connection) !=0){
					failurelist.add(uploadStaffXSLVo);
					uploadStaffXSLVo.setReason("Teacher Id Already Exist in Database.");
				}
				/*else if(uploadStaffXSLPOJO.getDuplicateInExcel()==1){
					uploadStaffXSLVo.setFirstName(fullName);
					System.out.println("Duplicate Record in Excel! ");
					uploadStaffXSLVo.setReason("Duplicate in Excel");
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(uploadStaffXSLPOJO.getLocation().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getLocation()==null){
					uploadStaffXSLVo.setReason("School Name should not be empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!uploadStaffXSLPOJO.getLocation().equalsIgnoreCase("") && (locationId==null || locationId.equalsIgnoreCase("notfound")) ){
					uploadStaffXSLVo.setReason("Invalid Branch Name.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if (dojisvalid == false) {
					uploadStaffXSLVo.setReason("Enter valid Date Of Joining");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!uploadStaffXSLPOJO.getDepartment().trim().equalsIgnoreCase("") && departmentId==null){
					uploadStaffXSLVo.setReason("Invalid Department.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!uploadStaffXSLPOJO.getDesignation().equalsIgnoreCase("") && designationId==null){
					uploadStaffXSLVo.setReason("Invalid Designation.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(staffTypevalid){
					uploadStaffXSLVo.setReason("Enter valid Staff Type");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(gendervalid){
					uploadStaffXSLVo.setReason("Gender should be either male or female only");
					failurelist.add(uploadStaffXSLVo);
				}
				else if (dobisvalid == false) {
					uploadStaffXSLVo.setReason("Enter valid Date Of Birth");
					failurelist.add(uploadStaffXSLVo);
				}
				/*else if(uploadStaffXSLPOJO.getQualification().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getQualification()==null){
					uploadStaffXSLVo.setReason("Enter Staff Qualification.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(uploadStaffXSLPOJO.getMobileNo().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getMobileNo()==null){
					uploadStaffXSLVo.setReason("Enter Staff MobileNo.");
					failurelist.add(uploadStaffXSLVo);
				}
				/*else if(!uploadStaffXSLPOJO.getRole().equalsIgnoreCase("") && roleId==null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Role.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!uploadStaffXSLPOJO.getReportingTo().equalsIgnoreCase("") && reportingToId == null){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Invalid Reporting Person.");
					failurelist.add(uploadStaffXSLVo);		
				}*/
				/*else if(uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getAdmissionNo()==null || uploadStaffXSLPOJO.getAdmissionNo().equalsIgnoreCase(""))){
					uploadStaffXSLVo.setReason("Enter Student Admission No.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(uploadStaffXSLPOJO.getGender()=="" || uploadStaffXSLPOJO.getGender().equals("-") || uploadStaffXSLPOJO.getGender()==null){
					uploadStaffXSLVo.setReason("Gender Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getDob()=="" || uploadStaffXSLPOJO.getDob().equals("-") || uploadStaffXSLPOJO.getDob()==null){
					uploadStaffXSLVo.setReason("Date Of Birth Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMobileNo().trim()=="" || uploadStaffXSLPOJO.getMobileNo().equals("-") || uploadStaffXSLPOJO.getMobileNo()==null){
					uploadStaffXSLVo.setReason("Mobile No Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(!(uploadStaffXSLPOJO.getMobileNo().matches(int_regex))){
					uploadStaffXSLVo.setReason("Enter Valid Mobile Number");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMobileNo().equals(numbers_regx)){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Mobile No should be in Numbers.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMobileNo().length() > 10 || uploadStaffXSLPOJO.getMobileNo().length() < 10){
					uploadStaffXSLVo.setReason("Mobile Number No should be 10 digit");
					failurelist.add(uploadStaffXSLVo);
					}	
				else if((!uploadStaffXSLPOJO.getEmail().matches(regexpforEmailId))&& (uploadStaffXSLPOJO.getEmail().trim()!="" && !uploadStaffXSLPOJO.getEmail().trim().equalsIgnoreCase(""))){
					uploadStaffXSLVo.setReason("Enter valid Staff Email Id"); 
					failurelist.add(uploadStaffXSLVo);
				} 
				else if(uploadStaffXSLPOJO.getPanNumber().length()!=10 && (uploadStaffXSLPOJO.getPanNumber().trim()!="" && !uploadStaffXSLPOJO.getPanNumber().trim().equalsIgnoreCase("")))
				{
					uploadStaffXSLVo.setReason("Enter valid PAN Number."); 
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getAadharNo().length()!=12 && (uploadStaffXSLPOJO.getAadharNo().trim()!="" &&!uploadStaffXSLPOJO.getAadharNo().trim().equalsIgnoreCase(""))){
					uploadStaffXSLVo.setReason("Enter valid Aadhar Number."); 
					failurelist.add(uploadStaffXSLVo);
				}
				
				/*else if(uploadStaffXSLPOJO.getIsStudentStudying().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getIsStudentStudying()==null){
					uploadStaffXSLVo.setReason("Is Student Studying should not be empty"); 
					failurelist.add(uploadStaffXSLVo);
				}
				else if(isStudentStudyvalid)
				{
					uploadStaffXSLVo.setReason("Is Student Studying should be Yes or No"); 
					failurelist.add(uploadStaffXSLVo);
				}*/
				/*else if(uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getStudentName().trim().equalsIgnoreCase("") ||uploadStaffXSLPOJO.getStudentName()==null)){
					uploadStaffXSLVo.setReason("Enter Student name."); 
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(uploadStaffXSLPOJO.getIsStudentStudying().equalsIgnoreCase("yes") && studentAdmissionValid.equalsIgnoreCase("notfound") && (uploadStaffXSLPOJO.getAdmissionNo()!=null && uploadStaffXSLPOJO.getAdmissionNo().trim()!="")){
					uploadStaffXSLVo.setReason("Enter valid student admission number");
					failurelist.add(uploadStaffXSLVo);
				}
				
				/*else if(uploadStaffXSLPOJO.getFatherName()=="" || uploadStaffXSLPOJO.getFatherName().equals("-") || uploadStaffXSLPOJO.getFatherName()==null){
					uploadStaffXSLVo.setReason("Father Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMotherName()=="" || uploadStaffXSLPOJO.getMotherName().equals("-") || uploadStaffXSLPOJO.getMotherName()==null){
					uploadStaffXSLVo.setReason("Mother Name Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				
				/*else if(uploadStaffXSLPOJO.getFatherMobile().trim()=="" || uploadStaffXSLPOJO.getFatherMobile().equals("-") || uploadStaffXSLPOJO.getFatherMobile()==null){
					uploadStaffXSLVo.setReason("Father Mobile No Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(!(uploadStaffXSLPOJO.getFatherMobile().matches(int_regex)) &&(uploadStaffXSLPOJO.getFatherMobile().trim()!="" && uploadStaffXSLPOJO.getFatherMobile()!=null)){
					uploadStaffXSLVo.setReason("Enter Valid Father Mobile Number");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getFatherMobile().equals(numbers_regx) &&(uploadStaffXSLPOJO.getFatherMobile().trim()!="" && uploadStaffXSLPOJO.getFatherMobile()!=null)){
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Father Mobile No should be in Numbers.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if((uploadStaffXSLPOJO.getFatherMobile().length() > 10 || uploadStaffXSLPOJO.getFatherMobile().length() < 10 ) &&(uploadStaffXSLPOJO.getFatherMobile().trim()!="" && uploadStaffXSLPOJO.getFatherMobile()!=null)){
					uploadStaffXSLVo.setReason("Father Mobile Number No should be 10 digit");
					failurelist.add(uploadStaffXSLVo);
				 }
				
				/*else if(uploadStaffXSLPOJO.getMotherMobile().trim()=="" || uploadStaffXSLPOJO.getMotherMobile().equals("-") || uploadStaffXSLPOJO.getMotherMobile()==null)
				{
					uploadStaffXSLVo.setReason("Mother Mobile No Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				else if(!(uploadStaffXSLPOJO.getMotherMobile().matches(int_regex)) && (uploadStaffXSLPOJO.getMotherMobile().trim()!="" && uploadStaffXSLPOJO.getMotherMobile()!=null) )
				{
					uploadStaffXSLVo.setReason("Enter Valid Mother Mobile Number");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMotherMobile().equals(numbers_regx) && (uploadStaffXSLPOJO.getMotherMobile().trim()!="" && uploadStaffXSLPOJO.getMotherMobile()!=null))
				{
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Mother Mobile No should be in Numbers.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if((uploadStaffXSLPOJO.getMotherMobile().length() > 10 || uploadStaffXSLPOJO.getMotherMobile().length()< 10) && (uploadStaffXSLPOJO.getMotherMobile().trim()!="" && uploadStaffXSLPOJO.getMotherMobile()!=null))
				{
					uploadStaffXSLVo.setReason("Mother Mobile Number No should be 10 digit");
					failurelist.add(uploadStaffXSLVo);
				}
				/*else if(uploadStaffXSLPOJO.getMaritalStatus()=="" || uploadStaffXSLPOJO.getMaritalStatus().equals("-") || uploadStaffXSLPOJO.getMaritalStatus()==null){
					uploadStaffXSLVo.setReason("Marital Status Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				} 
				else if(maritelvalid){
					uploadStaffXSLVo.setReason("Marital Status Should be Yes or No.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getSpouseName().trim().equalsIgnoreCase("") || uploadStaffXSLPOJO.getSpouseName()==null)){
					uploadStaffXSLVo.setReason("SPOUSE NAME Should not be empty.");
					failurelist.add(uploadStaffXSLVo);
				}

				else if( uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getSpouseMobile().trim()=="" || uploadStaffXSLPOJO.getSpouseMobile().equals("-") || uploadStaffXSLPOJO.getSpouseMobile()==null))
				{
					uploadStaffXSLVo.setReason("Spouse Mobile Mobile No Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}*/
				
				else if(uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") && !(uploadStaffXSLPOJO.getSpouseMobile().matches(int_regex)) && (uploadStaffXSLPOJO.getSpouseMobile().trim()!="" && uploadStaffXSLPOJO.getSpouseMobile()!=null))
				{
					uploadStaffXSLVo.setReason("Enter Valid Spouse Mobile Mobile Number");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") && uploadStaffXSLPOJO.getSpouseMobile().equals(numbers_regx) && (uploadStaffXSLPOJO.getSpouseMobile().trim()!="" && uploadStaffXSLPOJO.getSpouseMobile()==null))
				{
					uploadStaffXSLVo.setFirstName(fullName);
					uploadStaffXSLVo.setReason("Spouse Mobile Mobile No should be in Numbers.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getMaritalStatus().equalsIgnoreCase("yes") && (uploadStaffXSLPOJO.getSpouseMobile().length() > 10 || uploadStaffXSLPOJO.getSpouseMobile().length()< 10) && (uploadStaffXSLPOJO.getSpouseMobile().trim()!="" && uploadStaffXSLPOJO.getSpouseMobile()==null))
				{
					uploadStaffXSLVo.setReason("Spouse Mobile Mobile Number No should be 10 digit");
					failurelist.add(uploadStaffXSLVo);
				}
				
				else if(uploadStaffXSLPOJO.getPresentAddress()=="" || uploadStaffXSLPOJO.getPresentAddress().equals("-") || uploadStaffXSLPOJO.getPresentAddress()==null){
					uploadStaffXSLVo.setReason("Present Address Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else if(uploadStaffXSLPOJO.getPermanentAddress()=="" || uploadStaffXSLPOJO.getPermanentAddress().equals("-") || uploadStaffXSLPOJO.getPermanentAddress()==null){
					uploadStaffXSLVo.setReason("Permanent Address Should Not be Empty.");
					failurelist.add(uploadStaffXSLVo);
				}
				else{
					uploadStaffXSLPOJO.setDepartment(departmentId);
					uploadStaffXSLPOJO.setDesignation(designationId);
					
					uploadStaffXSLPOJO.setDepartmentName(uploadStaffXSLPOJO.getDepartment());
					uploadStaffXSLPOJO.setDesignationName(uploadStaffXSLPOJO.getDesignation());
					/*uploadStaffXSLPOJO.setRole(roleId);*/
					/*uploadStaffXSLPOJO.setReportingTo(reportingToId);*/
					uploadStaffXSLPOJO.setLocation(locationId);
					successlist.add(uploadStaffXSLPOJO);
					JSONArray j=new JSONArray(successlist);
				  	/*System.out.println("j:::"+j);*/
			   }
		}
		
			Set<UploadStaffXlsVO> failureListFromDiompl=new LinkedHashSet<UploadStaffXlsVO>();
			
			if(successlist.size()>0){
				failureListFromDiompl=daoImpl.insertStaffXSL(successlist,connection,log_audit_session,custdetails);
			}
			for (Iterator<UploadStaffXlsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				UploadStaffXlsVO failureDiomplVo = (UploadStaffXlsVO) it.next();
				UploadStaffXlsVO uploadStaffXlsVO = new UploadStaffXlsVO();
				
				uploadStaffXlsVO.setFirstName(failureDiomplVo.getFirstName()+" "+failureDiomplVo.getLastName());
				uploadStaffXlsVO.setMobileNo(failureDiomplVo.getMobileNo());
				uploadStaffXlsVO.setDesignation(failureDiomplVo.getDesignation());
				uploadStaffXlsVO.setDepartment(failureDiomplVo.getDepartment());
				uploadStaffXlsVO.setReason(failureDiomplVo.getReason());
				
				failurelist.add(uploadStaffXlsVO);
		    }
			
		//System.out.println("in service IMPL: faulurelist list size::::= "+failurelist.size());
		
		
		}catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}finally{
			try {
				
				if(connection!=null && (!connection.isClosed())){
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
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Ending");

		return failurelist;
	}

}
