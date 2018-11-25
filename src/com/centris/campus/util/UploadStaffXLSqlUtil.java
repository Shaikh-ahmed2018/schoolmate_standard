package com.centris.campus.util;

public class UploadStaffXLSqlUtil {
	
	public static final String CHECK_BEFORINSERT_COUNT= "select count(*) from campus_teachers";
	
	public static final String REGISTRATION_DUPLICATE= "select count(*) from campus_teachers where  registerId=?";
	
	public static final String INSERT_TEACHER = "insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,presentAddress,MobileNo,UserName,emailId,DateOfBirth,DateOfJoining,designation,teachingType,department,gender,bankname,accountnumber,pannumber,bloodgroup,fathername,mothername,permanentAddress,createdby,createddate,registerId,password,role,reportingTo,is_student_studying,student_admission_id,fatherMobile,motherMobile,Abbreviative_Id,Loc_ID,maritalstatus,spousename,spouseMobile,aadhaarnumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String GET_DEPARTMENT_ID="select dept_id from campus_department where dept_name=? and isActive='Y'";
	
	public static final String GET_DESIGNATION_ID="select DesignationCode from campus_designation where designationName=? and isActive='Y'";
	
	public static final String GET_SUBJECT_ID="select subjectID from campus_subject where subjectName=?";
	
	public static final String GET_ROLE_ID="select RoleCode from campus_role where RoleName=?";
	
	public static final String GET_REPORTING_CODE="select TeacherID from campus_teachers where Abbreviative_Id=? AND isActive='Y'  ORDER BY FirstName";

	public static final String GET_REGISTRATION_ID = "SELECT COUNT(*) FROM campus_teachers WHERE registerId=? AND Loc_ID=?";

	public static final String GET_ABBRIVATION_ID_COUNT = "SELECT COUNT(`Abbreviative_Id`) FROM campus_teachers WHERE Abbreviative_Id=? AND Loc_ID=?";

	public static final String GET_STUDENT_ADMISSION_NO_VALID = "SELECT student_admissionno_var FROM campus_student WHERE student_admissionno_var=? and student_status_var='active' AND locationId=?";
}

