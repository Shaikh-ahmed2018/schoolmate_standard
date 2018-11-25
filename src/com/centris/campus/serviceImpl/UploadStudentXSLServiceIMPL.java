package com.centris.campus.serviceImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
//import org.apache.catalina.startup.SetAllPropertiesRule;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadStudentXlsVO;


public class UploadStudentXSLServiceIMPL implements UploadStudentXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadStudentXSLServiceIMPL.class);


	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> list, String username, String duplicate,String currentLoc, String log_audit_session, UserLoggingsPojo userLoggingsVo, long stucount) 
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStudentXSLServiceIMPL : insertEmpXSL : Starting");
		
		Connection connection = null;
	/*	int checkShiftCode=0;
		int checkcategory=0;
		int checkClassCode=0;
		int checkSectionCode=0;*/
		List<UploadStudentXlsPOJO> successlist=new ArrayList<UploadStudentXlsPOJO>();
		UploadStudentXLSDaoImpl daoImpl=new UploadStudentXLSDaoImpl();
	
		int count = 0;
	/*	boolean pancardflag=false;
		boolean personalemailflag=false;
		boolean officialemailflag=false;
		int checkAdmisionDate=0;*/
	
		Set<UploadStudentXlsVO> failurelist = new LinkedHashSet<UploadStudentXlsVO>();
	
		failurelist.clear();
		successlist.clear();

		try {
			
			connection=JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			String int_regex="^[0-9]*$";
			String numbers_regx = "[0-9//]{10}";
			String string_regx="([a-zA-Z.,-)(]+\\s+)*[a-zA-Z.,-)(]+";
			String datePattern = "\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
			String regexpforEmailId="/[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]*$/i";
			String EMAIL_PATTERN =
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			 
			for (Iterator<UploadStudentXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadStudentXlsPOJO uploadStudentXSLPOJO = (UploadStudentXlsPOJO) iterator.next();
				UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO();
				uploadStudentXSLPOJO.setCreateUser(username);
				/*uploadStudentXSLPOJO.setCurrentLocation(currentLoc);*/
				uploadStudentXSLVo.setCreateUser(username);
				uploadStudentXSLVo.setCurrentLocation(uploadStudentXSLPOJO.getSchoolName());
				uploadStudentXSLVo.setStudentFirstName(uploadStudentXSLPOJO.getStudentFirstName()+" "+uploadStudentXSLPOJO.getStudentLastName());
				uploadStudentXSLVo.setStudentLastName(uploadStudentXSLPOJO.getStudentLastName());
				uploadStudentXSLVo.setStudentAdmissionNo(uploadStudentXSLPOJO.getStudentAdmissionNo());
				uploadStudentXSLVo.setStudentRegNo(uploadStudentXSLPOJO.getApplicationNo());
				uploadStudentXSLVo.setDateofJoin(uploadStudentXSLPOJO.getDateofJoin());
				
				uploadStudentXSLVo.setAcademicYear(uploadStudentXSLPOJO.getAcademicYear());
				uploadStudentXSLVo.setCategory(uploadStudentXSLPOJO.getCategory());
				uploadStudentXSLVo.setClassname(uploadStudentXSLPOJO.getClassname());
				
				uploadStudentXSLVo.setRollno(uploadStudentXSLPOJO.getRollno());
				uploadStudentXSLVo.setHousename(uploadStudentXSLPOJO.getHousename());
			
				uploadStudentXSLVo.setSectionname(uploadStudentXSLPOJO.getSectionname());
				uploadStudentXSLVo.setStudentquotaname(uploadStudentXSLPOJO.getStudentquotaname());
				uploadStudentXSLVo.setGrade(uploadStudentXSLPOJO.getGrade());
				uploadStudentXSLVo.setRte(uploadStudentXSLPOJO.getRte());
				uploadStudentXSLVo.setEmisNo(uploadStudentXSLPOJO.getEmisNo());
				uploadStudentXSLVo.setHostel(uploadStudentXSLPOJO.getHostel());
				uploadStudentXSLVo.setConcession_applicable(uploadStudentXSLPOJO.getConcession_applicable());
				uploadStudentXSLVo.setConcession_type(uploadStudentXSLPOJO.getConcession_type());
				uploadStudentXSLVo.setTransport(uploadStudentXSLPOJO.getTransport());
				uploadStudentXSLVo.setTranscategory(uploadStudentXSLPOJO.getTranscategory());
				uploadStudentXSLVo.setTranslocation(uploadStudentXSLPOJO.getTranslocation());
				uploadStudentXSLVo.setDateofBirth(uploadStudentXSLPOJO.getDateofBirth());
				uploadStudentXSLVo.setAge(uploadStudentXSLPOJO.getAge());
				uploadStudentXSLVo.setGender(uploadStudentXSLPOJO.getGender());
				uploadStudentXSLVo.setIdentificationMarks(uploadStudentXSLPOJO.getIdentificationMarks());
				uploadStudentXSLVo.setBloodGroup(uploadStudentXSLPOJO.getBloodGroup());
				uploadStudentXSLVo.setReligion(uploadStudentXSLPOJO.getReligion());
				uploadStudentXSLVo.setCaste(uploadStudentXSLPOJO.getCaste());
				uploadStudentXSLVo.setMedicalhistory(uploadStudentXSLPOJO.getMedicalhistory());
				uploadStudentXSLVo.setPhysicallyChallenged(uploadStudentXSLPOJO.getPhysicallyChallenged());
				uploadStudentXSLVo.setNationality(uploadStudentXSLPOJO.getNationality());
				uploadStudentXSLVo.setPhysicalchalreason(uploadStudentXSLPOJO.getPhysicalchalreason());
				uploadStudentXSLVo.setRemarks(uploadStudentXSLPOJO.getRemarks());
				uploadStudentXSLVo.setStudentstatus(uploadStudentXSLPOJO.getStudentstatus());
				uploadStudentXSLVo.setSibilingadminno(uploadStudentXSLPOJO.getStudentsiblingadmission());
				uploadStudentXSLVo.setSibilingClass(uploadStudentXSLPOJO.getStudentsiblingclass());
				uploadStudentXSLVo.setSibilingName(uploadStudentXSLPOJO.getStudentsiblingname());
				uploadStudentXSLVo.setPrimaryPerson(uploadStudentXSLPOJO.getPrimaryPerson());
				uploadStudentXSLVo.setFatherName(uploadStudentXSLPOJO.getFatherName());
				uploadStudentXSLVo.setFatherMobileNo(uploadStudentXSLPOJO.getFatherMobileNo());
				uploadStudentXSLVo.setFatherQualification(uploadStudentXSLPOJO.getFatherQualification());
				uploadStudentXSLVo.setFatheroccupation(uploadStudentXSLPOJO.getFatheroccupation());
				uploadStudentXSLVo.setFatheremailId(uploadStudentXSLPOJO.getFatheremailId());
				uploadStudentXSLVo.setMotherName(uploadStudentXSLPOJO.getMotherName());
				uploadStudentXSLVo.setMotherMobileNo(uploadStudentXSLPOJO.getMotherMobileNo());
				uploadStudentXSLVo.setMotherQualification(uploadStudentXSLPOJO.getMotherQualification());
				uploadStudentXSLVo.setMotheroccupation(uploadStudentXSLPOJO.getMotheroccupation());
				uploadStudentXSLVo.setMotheremailId(uploadStudentXSLPOJO.getMotheremailId());
				uploadStudentXSLVo.setGuardianName(uploadStudentXSLPOJO.getGuardianName());
				uploadStudentXSLVo.setGuardianemailId(uploadStudentXSLPOJO.getGuardianemailId());
				uploadStudentXSLVo.setGuardianMobileNo(uploadStudentXSLPOJO.getGuardianMobileNo());
				uploadStudentXSLVo.setAddress(uploadStudentXSLPOJO.getAddress());
				uploadStudentXSLVo.setPrimaryPerson(uploadStudentXSLPOJO.getPrimaryPerson());
				uploadStudentXSLVo.setMotherTounge(uploadStudentXSLPOJO.getMotherTounge());
				
				
				String siblingId="";
				String siblingName="";
				String siblingClassId="";
				String routeId="";
				String sectionId="",checkclassbystreammap="";
				String specilizationId="";
				String houseid = null;
				int checkClass=0;
				int checkSection=0;
				int checkSpecilization = 0;
				String classId = null;
				String stuimgurl = "FIles/STUDENTIMAGES/"+uploadStudentXSLPOJO.getAcademicYear()+"/"+uploadStudentXSLPOJO.getStudentAdmissionNo()+".jpg";
				String academicYearId = null;
				String stream = null;
				String schoolLocationId = null;
				String religionId = null;
				String casteId = null;
				String casteCategoryId = null;
				String fatherOccupationId = null;
				String motherOccupationId = null;
				String guardianOccupation = null;
				String transportTypeId="";
				String transportLocationId="";
				int ageOnDays=0;
				int status = 0,checkStudentStrength=0,seactionstrength=0;
				Boolean dateofjoin = false;
				Boolean dobisvalid = true;
				String schoolId=null;
				
				academicYearId=daoImpl.getAcademicYearId(uploadStudentXSLPOJO.getAcademicYear(),connection);
				
				  schoolLocationId=daoImpl.getSchoolLocationId(uploadStudentXSLPOJO.getSchoolName(),connection);
				 
				if(academicYearId.trim().equalsIgnoreCase("")||academicYearId==null || academicYearId.equalsIgnoreCase("notfound"))
				{
					uploadStudentXSLVo.setReason("Enter the Valid Academic Year");
					uploadStudentXSLVo.setAcademicYear(uploadStudentXSLVo.getAcademicYear());
					failurelist.add(uploadStudentXSLVo);
				}
				
				String dob=uploadStudentXSLPOJO.getDateofBirth();
				
				String admissionDate=uploadStudentXSLPOJO.getDateofJoin();
				
				if(!uploadStudentXSLPOJO.getDateofJoin().trim().equals("") || uploadStudentXSLPOJO.getDateofJoin()!=null){
					dateofjoin = HelperClass.DateValidator(uploadStudentXSLPOJO.getDateofJoin().replace("-","/"));
					if(dateofjoin == true)
						uploadStudentXSLPOJO.setDateofJoin(uploadStudentXSLPOJO.getDateofJoin().replace("/","-"));
				}
				
				if(uploadStudentXSLPOJO.getDateofBirth().trim()!=""&&!uploadStudentXSLPOJO.getDateofBirth().equalsIgnoreCase("")){
					dobisvalid = HelperClass.DateValidator(uploadStudentXSLPOJO.getDateofBirth().replace("-","/"));
					if(dobisvalid == true)
						uploadStudentXSLPOJO.setDateofBirth(uploadStudentXSLPOJO.getDateofBirth().replace("/","-"));
				}
				
				if(dob.trim() != "" && dob != null && !dob.equalsIgnoreCase("") && admissionDate !=null && !admissionDate.equalsIgnoreCase("") && dateofjoin == true && dobisvalid == true)
				{
					List<String> dayList=HelperClass.getDateListBetweenDates(HelperClass.convertUIToDatabase(uploadStudentXSLPOJO.getDateofBirth().replace("/","-")), HelperClass.convertUIToDatabase(uploadStudentXSLPOJO.getDateofJoin().replace("/","-")));
					ageOnDays=dayList.size();
				}
				if(!schoolLocationId.equalsIgnoreCase("notfound"))
				{
					schoolId=daoImpl.getSchoolIdByName(uploadStudentXSLPOJO.getSchoolName(),connection,userLoggingsVo);
					
					if(uploadStudentXSLPOJO.getCategory().trim() !="" || !uploadStudentXSLPOJO.getCategory().equalsIgnoreCase(""))
					{
					 stream=daoImpl.getStreamId(uploadStudentXSLPOJO.getCategory(),connection,schoolLocationId,userLoggingsVo);
					}
					 if(stream !=null ){
						 classId=daoImpl.getClassId(uploadStudentXSLPOJO.getClassname(),connection,schoolLocationId,userLoggingsVo); 
					 }
					
					
					 if(classId!=null && !classId.equalsIgnoreCase("notfound")  && !uploadStudentXSLPOJO.getSectionname().equalsIgnoreCase("")){

						 checkclassbystreammap=daoImpl.getClassByStreamMap(schoolLocationId,stream,uploadStudentXSLPOJO.getClassname(),connection);
						 
						 sectionId=daoImpl.getSectionId(uploadStudentXSLPOJO.getSectionname(), classId,connection,schoolLocationId,userLoggingsVo);
					
						 seactionstrength=daoImpl.getSectionStrength(sectionId,schoolId,userLoggingsVo,connection);
						 checkStudentStrength=daoImpl.checkStudentStrength(sectionId,schoolId,academicYearId,userLoggingsVo,connection);
					 }
					 
					 if(!uploadStudentXSLPOJO.getSpecilization().trim().equalsIgnoreCase("") && classId != null && !classId.equalsIgnoreCase("notfound")){
							 
							specilizationId=daoImpl.getSpecilizationId(uploadStudentXSLPOJO.getSpecilization(),stream,classId,connection,schoolLocationId,userLoggingsVo);
					 }
					 
					 if(uploadStudentXSLPOJO.getHousename()!=null && !uploadStudentXSLPOJO.getHousename().trim().equalsIgnoreCase("")){
							houseid = daoImpl.getHouseId(uploadStudentXSLPOJO.getHousename(),schoolLocationId,academicYearId,connection,userLoggingsVo);
					 }
					 
					 religionId=daoImpl.getReligionId(uploadStudentXSLPOJO.getReligion(),connection,userLoggingsVo);
						
					 casteId=daoImpl.getCasteId(uploadStudentXSLPOJO.getCaste(),religionId,connection,userLoggingsVo);
					
					 casteCategoryId=daoImpl.getCasteCategoryId(uploadStudentXSLPOJO.getCasteCategory(),casteId,religionId,connection,userLoggingsVo);
					
					 /*if(!uploadStudentXSLPOJO.getFatheroccupation().trim().equalsIgnoreCase("") && uploadStudentXSLPOJO.getFatheroccupation() != null && uploadStudentXSLPOJO.getFatheroccupation().trim() != ""){
							fatherOccupationId=daoImpl.getOccupationId(uploadStudentXSLPOJO.getFatheroccupation(),connection,custid);
					 }
					 if(!uploadStudentXSLPOJO.getGuardianOccupation().trim().equalsIgnoreCase("") && uploadStudentXSLPOJO.getGuardianOccupation() != null && uploadStudentXSLPOJO.getGuardianOccupation().trim() != ""){
							guardianOccupation= daoImpl.getOccupationId(uploadStudentXSLPOJO.getGuardianOccupation(), connection,custid);
					}
					if(!uploadStudentXSLPOJO.getMotheroccupation().trim().equalsIgnoreCase("") && uploadStudentXSLPOJO.getMotheroccupation() != null && uploadStudentXSLPOJO.getMotheroccupation().trim() != ""){
							motherOccupationId = daoImpl.getOccupationId(uploadStudentXSLPOJO.getMotheroccupation(), connection,custid);
					}*/
				
					
					/*if(uploadStudentXSLPOJO.getStudentsiblingname() !=null || uploadStudentXSLPOJO.getStudentsiblingname().trim() !="" ){
						siblingName=daoImpl.getSiblingNameValidation(uploadStudentXSLPOJO.getStudentsiblingname(),uploadStudentXSLPOJO.getStudentsiblingadmission(),connection); 
					
						if(uploadStudentXSLPOJO.getStudentsiblingadmission() !=null && uploadStudentXSLPOJO.getStudentsiblingadmission().trim() !="" ){
							siblingId=daoImpl.getSiblingId(uploadStudentXSLPOJO.getStudentsiblingadmission(),connection);
							
							if(uploadStudentXSLPOJO.getStudentsiblingclass() !=null && uploadStudentXSLPOJO.getStudentsiblingclass().trim() !="" ){
								siblingClassId=daoImpl.getSiblingClassName(uploadStudentXSLPOJO.getStudentsiblingname(),uploadStudentXSLPOJO.getStudentsiblingadmission(),uploadStudentXSLPOJO.getStudentsiblingclass(),connection); 
							}
						}
					}*/
					
					if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") || !uploadStudentXSLPOJO.getTranscategory().equalsIgnoreCase("")){
						transportTypeId=daoImpl.getTransportTypeId(uploadStudentXSLPOJO.getTranscategory(),connection);
					}
					
					if(transportTypeId!="" && !uploadStudentXSLPOJO.getTranslocation().equalsIgnoreCase("")){
						transportLocationId=daoImpl.getTransportLocationId(uploadStudentXSLPOJO.getTranslocation(),connection);
					}
					
					if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && !uploadStudentXSLPOJO.getRoute().equalsIgnoreCase("")){
						routeId=daoImpl.getRouteId(uploadStudentXSLPOJO.getRoute(),connection);	
					}
				}
				count = daoImpl.checkStudentID(uploadStudentXSLPOJO.getStudentAdmissionNo(),schoolId,connection,userLoggingsVo);
				
				
				 if(!(currentLoc.equals(schoolLocationId))) {
					   uploadStudentXSLVo.setReason("Incorrect Branch");
						failurelist.add(uploadStudentXSLVo);
				   }
				else if(uploadStudentXSLPOJO.getStudentFirstName()==null ||uploadStudentXSLPOJO.getStudentFirstName().equalsIgnoreCase("") ||uploadStudentXSLPOJO.getStudentFirstName().trim()=="" || uploadStudentXSLPOJO.getStudentFirstName().equals("-"))
				 {
					uploadStudentXSLVo.setReason("Student First Name Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				 else if(uploadStudentXSLPOJO.getStudentAdmissionNo()==null ||uploadStudentXSLPOJO.getStudentAdmissionNo().equalsIgnoreCase("") ||uploadStudentXSLPOJO.getStudentAdmissionNo().trim()=="" || uploadStudentXSLPOJO.getStudentAdmissionNo().equals("-"))
				 {
					uploadStudentXSLVo.setReason("Student AdmissionNo Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				 else if(count!=0){
					uploadStudentXSLVo.setReason("Student Admission No. Already Exist");
					failurelist.add(uploadStudentXSLVo);
				
				}
				else if(uploadStudentXSLPOJO.getStudentAdmissionNo().trim()=="" || uploadStudentXSLPOJO.getStudentAdmissionNo().equals("-")){
					uploadStudentXSLVo.setReason("Student Admission No Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getDuplicateInExcel()==1){
					uploadStudentXSLVo.setReason("Duplicate Admission No. in Excel file.");
					failurelist.add(uploadStudentXSLVo);
				
				}
				else if(uploadStudentXSLPOJO.getDateofJoin().trim()=="" || uploadStudentXSLPOJO.getDateofJoin().equals("-")){
					uploadStudentXSLVo.setReason("Admision Date Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(dateofjoin == false){
					uploadStudentXSLVo.setReason("Invalid Admision Date"); 
					uploadStudentXSLVo.setDateofJoin(uploadStudentXSLVo.getDateofJoin());
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getAcademicYear().trim()=="" || uploadStudentXSLPOJO.getAcademicYear().equals("-")){
					uploadStudentXSLVo.setReason("Student Academic Year Should Not Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(academicYearId.trim() == "" ||academicYearId == null || academicYearId.equalsIgnoreCase("notfound")){
					uploadStudentXSLVo.setReason("Academic Year Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
			   }
			   else  if(uploadStudentXSLPOJO.getSchoolName().trim().equalsIgnoreCase("")){
					  uploadStudentXSLVo.setReason("School Name Should not be Empty");
					  failurelist.add(uploadStudentXSLVo);
			   }
			   else if(schoolLocationId == null || schoolLocationId.equalsIgnoreCase("notfound")) {
				   uploadStudentXSLVo.setReason("School Name Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
			   }  
			   else if(uploadStudentXSLPOJO.getCategory().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getCategory().equals("-")){
					uploadStudentXSLVo.setReason("Stream Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
			   else if(stream==null || stream.trim().equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Stream Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
			   }
			   else if(uploadStudentXSLPOJO.getClassname().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getClassname().equals("-")){
					uploadStudentXSLVo.setReason("Class Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
			   else if(classId==null || classId.equalsIgnoreCase("notfound")){
					uploadStudentXSLVo.setReason("Class is not avilable for selected stream.");
					failurelist.add(uploadStudentXSLVo);
				} 
			   else if(checkclassbystreammap==null || checkclassbystreammap.equalsIgnoreCase("notfound")){
					uploadStudentXSLVo.setReason("Class is not Mapped with selected stream.");
					failurelist.add(uploadStudentXSLVo);
				}
			   else if(uploadStudentXSLPOJO.getSectionname().trim()=="" || uploadStudentXSLPOJO.getSectionname().equals("-")){
					uploadStudentXSLVo.setReason("Section Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
			   else if(sectionId==null || sectionId.trim().equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Section is not available for selected class.");
					failurelist.add(uploadStudentXSLVo);
				}
			   else if(checkStudentStrength>=seactionstrength){
					uploadStudentXSLVo.setReason("No. of Seats are exceeded in this section."); 
					failurelist.add(uploadStudentXSLVo);
				}
				 
			  	else if((!uploadStudentXSLPOJO.getSpecilization().trim().equals("") || !uploadStudentXSLPOJO.getSpecilization().trim().equals("-")) 
			  			&& ( specilizationId.equalsIgnoreCase("notfound") )){
			  		uploadStudentXSLVo.setReason("Specilization is not available for selected class.");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(!(uploadStudentXSLPOJO.getRollno().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Roll No.");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if((!uploadStudentXSLPOJO.getHousename().trim().equalsIgnoreCase("")||!uploadStudentXSLPOJO.getHousename().equalsIgnoreCase("")) && (houseid.trim().equalsIgnoreCase("") ||houseid==null || houseid.equalsIgnoreCase("not found"))){
					uploadStudentXSLVo.setReason("House Name Not Found for this Academic Year and School");
					failurelist.add(uploadStudentXSLVo);
				}
			  /*else if(uploadStudentXSLPOJO.getIsParentsGuardianWorking().equalsIgnoreCase("yes") && uploadStudentXSLPOJO.getWorkingParentsGuardianName().trim().equals("")){
					uploadStudentXSLVo.setReason("Working parents/guardian name is empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getIsParentsGuardianWorking().equalsIgnoreCase("yes") && !uploadStudentXSLPOJO.getIsParentsGuardianWorking().equalsIgnoreCase("no")){
					uploadStudentXSLVo.setReason("Working parents/guardian should be  yes or no");
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getTransport().trim()=="" || uploadStudentXSLPOJO.getTransport().equals("-")){
					uploadStudentXSLVo.setReason("Transport Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getTranscategory()==null || uploadStudentXSLPOJO.getTranscategory().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Category Should not be Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (transportTypeId==null || transportTypeId.trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Category Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getTranslocation()==null || uploadStudentXSLPOJO.getTranslocation().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Location Should not be Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (transportLocationId==null || transportLocationId.trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Transport Location(Stage) Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (uploadStudentXSLPOJO.getRoute()==null || uploadStudentXSLPOJO.getRoute().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Route Should not be Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getTransport().equalsIgnoreCase("yes") && (routeId==null || routeId.trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Route Doesn't Exist");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getDateofBirth().trim().equalsIgnoreCase("") ||uploadStudentXSLPOJO.getDateofBirth().equalsIgnoreCase("") || uploadStudentXSLPOJO.getDateofBirth().equals("-")){
					uploadStudentXSLVo.setReason("Enter Date of Birth");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(dobisvalid == false){
					uploadStudentXSLVo.setReason("Invalid DOB");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(ageOnDays < 730){
					   uploadStudentXSLVo.setReason("Age of Student is less than 2 years.");
					   failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getGender().trim().equalsIgnoreCase("")  ||uploadStudentXSLPOJO.getGender()==""  || uploadStudentXSLPOJO.getGender().equals("-")){
					uploadStudentXSLVo.setReason("Gender Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getGender().equalsIgnoreCase("male")  && !uploadStudentXSLPOJO.getGender().equalsIgnoreCase("female")){
					uploadStudentXSLVo.setReason("Gender Should be male or female");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getReligion().trim().equalsIgnoreCase("")  ||uploadStudentXSLPOJO.getReligion()==""  || uploadStudentXSLPOJO.getReligion().equals("-")){
					uploadStudentXSLVo.setReason("Religion Should not be Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if((uploadStudentXSLPOJO.getReligion().trim()!="" ||uploadStudentXSLPOJO.getReligion()!=null) && (religionId==null)){
					uploadStudentXSLVo.setReason("Religion Doesn't Exist.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getCaste().trim().equalsIgnoreCase("")  ||uploadStudentXSLPOJO.getCaste()==""  || uploadStudentXSLPOJO.getCaste().equals("-")){
					uploadStudentXSLVo.setReason("Caste Should not be Empty.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if((!uploadStudentXSLPOJO.getCaste().trim().equalsIgnoreCase("")||!uploadStudentXSLPOJO.getCaste().equalsIgnoreCase("")) && (casteId==null)){
					uploadStudentXSLVo.setReason("Enter valid Caste.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getCasteCategory().trim().equalsIgnoreCase("")  ||uploadStudentXSLPOJO.getCasteCategory()==""  || uploadStudentXSLPOJO.getCasteCategory().equals("-")){
					uploadStudentXSLVo.setReason("Caste Category Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if((!uploadStudentXSLPOJO.getCasteCategory().trim().equalsIgnoreCase("")||!uploadStudentXSLPOJO.getCasteCategory().equalsIgnoreCase("")) && (casteCategoryId==null)){
					uploadStudentXSLVo.setReason("Enter valid Caste Category.");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if(uploadStudentXSLPOJO.getNationality()==null || uploadStudentXSLPOJO.getNationality()==""  || uploadStudentXSLPOJO.getNationality().equals("-")){
					System.out.println("NATIONALITY");
					uploadStudentXSLVo.setReason("Nationality Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
			
				}*/
				/*else if(uploadStudentXSLPOJO.getPhysicallyChallenged().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getPhysicallyChallenged()==null){
					   uploadStudentXSLVo.setReason("Physicall Challenged(yes/no) Should not be Empty");
					   failurelist.add(uploadStudentXSLVo);
			     }
				else if(!uploadStudentXSLPOJO.getPhysicallyChallenged().equalsIgnoreCase("Yes") && !uploadStudentXSLPOJO.getPhysicallyChallenged().equalsIgnoreCase("no"))
				{
						System.out.println("PHYSICAL REASON");
						uploadStudentXSLVo.setReason("Physicall Challenged Should be yes or no");
						failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getPhysicallyChallenged().equalsIgnoreCase("Yes") && (uploadStudentXSLPOJO.getPhysicalchalreason().trim().equalsIgnoreCase("") ||uploadStudentXSLPOJO.getPhysicalchalreason()==null || uploadStudentXSLPOJO.getPhysicalchalreason().equalsIgnoreCase("")))
				{
						System.out.println("PHYSICAL REASON");
						uploadStudentXSLVo.setReason("Physicall Challenged Reason Should not be Empty");
						failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getNationality().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getNationality().equals("-") || uploadStudentXSLPOJO.getStudentstatus()==null){
					uploadStudentXSLVo.setReason("Enter the Student Nationality");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getStudentstatus().trim()=="" || uploadStudentXSLPOJO.getStudentstatus().equals("-") || uploadStudentXSLPOJO.getStudentstatus()==null){
					uploadStudentXSLVo.setReason("Enter the Student Status.");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getStudentstatus().trim().equalsIgnoreCase("active") && !uploadStudentXSLPOJO.getStudentstatus().trim().equalsIgnoreCase("inactive")){
					uploadStudentXSLVo.setReason("Enter the Student Status either Active or Inactive");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getMotherTounge().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getMotherTounge().equals("-") || uploadStudentXSLPOJO.getMotherTounge()==null){
					uploadStudentXSLVo.setReason("Enter the Mother Tounge");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getFatherName().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getFatherName().equals("-")){
					uploadStudentXSLVo.setReason("Father Name Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getFatherMobileNo().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getFatherMobileNo().trim().equals("-")){
				
					uploadStudentXSLVo.setReason("Father Mobile Number Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getFatherMobileNo().trim().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getFatherMobileNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getFatherMobileNo().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Father Mobile Number");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getFatherMobileNo().length()!=10){
					uploadStudentXSLVo.setReason("Father Mobile Number should be 10 digits");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if((fatherOccupationId==null)&& (uploadStudentXSLPOJO.getFatheroccupation().trim()!=""||!uploadStudentXSLPOJO.getFatheroccupation().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Father Occupation");
					failurelist.add(uploadStudentXSLVo);
				} 
				else if((!uploadStudentXSLPOJO.getFatheremailId().matches(EMAIL_PATTERN))&& (uploadStudentXSLPOJO.getFatheremailId().trim()!=""||!uploadStudentXSLPOJO.getFatheremailId().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Father Email Id"); 
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getMotherName().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getMotherName().equals("-")){
					uploadStudentXSLVo.setReason("Mother Name Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}else if(uploadStudentXSLPOJO.getMotherMobileNo().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getMotherMobileNo().trim().equals("-")){
					uploadStudentXSLVo.setReason("Mother Mobile Number Should not be Empty");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getMotherMobileNo().trim().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getMotherMobileNo().equalsIgnoreCase("0") &&!(uploadStudentXSLPOJO.getMotherMobileNo().matches(int_regex))){
							uploadStudentXSLVo.setReason("Enter Valid Mother Mobile Number");
							failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getMotherMobileNo().length()!=10){
					uploadStudentXSLVo.setReason("Mother Mobile Number should be 10 digits");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if((motherOccupationId==null) && (uploadStudentXSLPOJO.getMotheroccupation().trim()!=""||!uploadStudentXSLPOJO.getMotheroccupation().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Mother Occupation");
					failurelist.add(uploadStudentXSLVo);
				}
				else if((!uploadStudentXSLPOJO.getMotheremailId().matches(EMAIL_PATTERN))&& (uploadStudentXSLPOJO.getMotheremailId().trim()!=""||!uploadStudentXSLPOJO.getMotheremailId().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Mother Email Id"); 
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getGuardianMobileNo().trim().equalsIgnoreCase("") && !uploadStudentXSLPOJO.getGuardianMobileNo().equalsIgnoreCase("0") && !(uploadStudentXSLPOJO.getGuardianMobileNo().matches(int_regex))){
					uploadStudentXSLVo.setReason("Enter Valid Guardian Mobile Number");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getGuardianMobileNo().length()!=10 && !uploadStudentXSLPOJO.getGuardianMobileNo().trim().equalsIgnoreCase("")){
					uploadStudentXSLVo.setReason("Guardian Mobile Number should be 10 digits");
					failurelist.add(uploadStudentXSLVo);
				}
				else if((guardianOccupation==null)&& (uploadStudentXSLPOJO.getGuardianOccupation().trim()!=""||!uploadStudentXSLPOJO.getGuardianOccupation().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Guardian Occupation"); 
					failurelist.add(uploadStudentXSLVo);
				}
				else if((!uploadStudentXSLPOJO.getGuardianemailId().matches(EMAIL_PATTERN))&& (uploadStudentXSLPOJO.getGuardianemailId().trim()!=""||!uploadStudentXSLPOJO.getGuardianemailId().trim().equalsIgnoreCase(""))){
					uploadStudentXSLVo.setReason("Enter valid Guardian Email Id");  
					failurelist.add(uploadStudentXSLVo);
				}*/
				else if(uploadStudentXSLPOJO.getPrimaryPerson().trim()=="" ||uploadStudentXSLPOJO.getPrimaryPerson().trim().equalsIgnoreCase("-") || uploadStudentXSLPOJO.getPrimaryPerson()==null){
					uploadStudentXSLVo.setReason("Enter Primary Person");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(!uploadStudentXSLPOJO.getPrimaryPerson().trim().equalsIgnoreCase("father") && !uploadStudentXSLPOJO.getPrimaryPerson().trim().equalsIgnoreCase("mother") 
						&& !uploadStudentXSLPOJO.getPrimaryPerson().trim().equalsIgnoreCase("guardian")){
					uploadStudentXSLVo.setReason("Enter Primary Person either Father or Mother");
					failurelist.add(uploadStudentXSLVo);
				}
				else if(uploadStudentXSLPOJO.getPresentAddress().trim()=="" ||uploadStudentXSLPOJO.getPresentAddress().trim().equals("") || uploadStudentXSLPOJO.getPresentAddress()==null){
					uploadStudentXSLVo.setReason("Enter Present Address");
					failurelist.add(uploadStudentXSLVo);
				}
				/*else if((!uploadStudentXSLPOJO.getStudentsiblingname().trim().equals("")) && (siblingName.equalsIgnoreCase("notfound") ||siblingName==null || siblingName.trim().equalsIgnoreCase("")))
				{
						uploadStudentXSLVo.setReason("Invalid Sibling Name");
						failurelist.add(uploadStudentXSLVo);
				}
				else if((uploadStudentXSLPOJO.getStudentsiblingadmission()==null) && (siblingId=="null" ||siblingId==null || siblingId.trim().equalsIgnoreCase("")) && (!uploadStudentXSLPOJO.getStudentsiblingname().trim().equals("")))
				{
						uploadStudentXSLVo.setReason("Sibling Admissio No. Should not be Empty");
						failurelist.add(uploadStudentXSLVo);
				}
				else if((uploadStudentXSLPOJO.getStudentsiblingadmission()!=null) && (siblingId=="null" ||siblingId==null || siblingId.trim().equalsIgnoreCase("")) && (!uploadStudentXSLPOJO.getStudentsiblingname().trim().equals("")))
				{
						uploadStudentXSLVo.setReason("Invalid Sibling Admissio No.");
						failurelist.add(uploadStudentXSLVo);
				}
				else if((uploadStudentXSLPOJO.getStudentsiblingclass().trim().equalsIgnoreCase("") || uploadStudentXSLPOJO.getStudentsiblingclass().trim()=="") && (siblingClassId.equalsIgnoreCase("notfound"))
						&&(uploadStudentXSLPOJO.getStudentsiblingadmission()!=null) && (!uploadStudentXSLPOJO.getStudentsiblingname().trim().equals("")))
				{
						uploadStudentXSLVo.setReason("Invalid Sibling Class or Not Empty");
						failurelist.add(uploadStudentXSLVo);
				}*/
				 else{
					uploadStudentXSLPOJO.setStreamValue(uploadStudentXSLPOJO.getCategory());
					uploadStudentXSLPOJO.setCategoryName(uploadStudentXSLPOJO.getCategory());
					uploadStudentXSLPOJO.setCategory(stream);
					uploadStudentXSLPOJO.setClassNameById(uploadStudentXSLPOJO.getClassname());
					uploadStudentXSLPOJO.setCalssValue(uploadStudentXSLPOJO.getClassname());
					uploadStudentXSLPOJO.setClassname(classId);
					uploadStudentXSLPOJO.setSectionNameById(uploadStudentXSLPOJO.getSectionname());
					uploadStudentXSLPOJO.setSectionValue(uploadStudentXSLPOJO.getSectionname());
					uploadStudentXSLPOJO.setSectionname(sectionId);
					
					uploadStudentXSLPOJO.setReligion(religionId);
					uploadStudentXSLPOJO.setSibilingadminno(siblingId);
					uploadStudentXSLPOJO.setCaste(casteId);
					uploadStudentXSLPOJO.setTranscategory(transportTypeId);
					uploadStudentXSLPOJO.setTranslocation(transportLocationId);
					uploadStudentXSLPOJO.setRoute(routeId);
					uploadStudentXSLPOJO.setSpecilization(specilizationId);
					uploadStudentXSLPOJO.setCasteCategory(casteCategoryId);
					uploadStudentXSLPOJO.setSchoolName(schoolLocationId);
					uploadStudentXSLPOJO.setHousename(houseid);
					uploadStudentXSLPOJO.setFatheroccupation(fatherOccupationId);
					uploadStudentXSLPOJO.setMotheroccupation(motherOccupationId);
					uploadStudentXSLPOJO.setGuardianOccupation(guardianOccupation);
					uploadStudentXSLPOJO.setStuimgurl(stuimgurl);
					successlist.add(uploadStudentXSLPOJO);
				}
			}
			
	
			Set<UploadStudentXlsVO> failureListFromDiompl=new LinkedHashSet<UploadStudentXlsVO>();
			if(successlist.size()>0){
				failureListFromDiompl = daoImpl.insertEmpXSL(successlist,connection,log_audit_session,userLoggingsVo,stucount);
			  }
			
			for (Iterator<UploadStudentXlsVO> it = failureListFromDiompl.iterator(); it.hasNext(); ) {
				UploadStudentXlsVO failureDiomplVo = (UploadStudentXlsVO) it.next();
				UploadStudentXlsVO uploadStudentXSLVo = new UploadStudentXlsVO();
				uploadStudentXSLVo.setStudentLastName(failureDiomplVo.getStudentLastName());
				
				uploadStudentXSLVo.setStudentFirstName(failureDiomplVo.getStudentFirstName());
				uploadStudentXSLVo.setStudentAdmissionNo(failureDiomplVo.getStudentAdmissionNo());
				uploadStudentXSLVo.setStudentRegNo(failureDiomplVo.getApplicationNo());
				uploadStudentXSLVo.setDateofJoin(failureDiomplVo.getDateofJoin());
				
				uploadStudentXSLVo.setAcademicYear(failureDiomplVo.getAcademicYear());
				uploadStudentXSLVo.setCategory(failureDiomplVo.getCategory());
				uploadStudentXSLVo.setClassname(failureDiomplVo.getClassname());
				uploadStudentXSLVo.setSectionname(failureDiomplVo.getSectionname());
				uploadStudentXSLVo.setReason(failureDiomplVo.getReason());
				
				failurelist.add(uploadStudentXSLVo);
		    }
			
			
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
				+ " Control in UploadStudentXSLServiceIMPL : insertEmpXSL : Ending");

		return failurelist;
	}

}
