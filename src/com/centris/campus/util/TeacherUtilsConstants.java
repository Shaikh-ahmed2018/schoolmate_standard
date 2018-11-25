package com.centris.campus.util;

public class TeacherUtilsConstants {

	public static final String ALL_TEACHER_DETAILS = "SELECT t.Loc_ID,t.registerId,t.Abbreviative_Id,CONCAT(t.FirstName,' ',t.LastName) AS teacherName,CASE WHEN t.Qualification IS NULL THEN '-' ELSE t.Qualification END Qualification,d.designationName,t.MobileNo,t.TeacherID,CASE WHEN t.emailId IS NULL THEN '-' ELSE t.emailId END emailId,dept.DEPT_NAME,CASE WHEN t.bankname IS NULL THEN '-' ELSE t.bankname END  bankname,CASE WHEN t.pannumber IS NULL THEN '-' ELSE t.pannumber END pannumber,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC,ss.totalSalary,CASE WHEN t.reason IS NULL THEN '' WHEN t.reason=''THEN ''ELSE t.reason END reason FROM campus_teachers t LEFT OUTER JOIN campus_staff_salarydetails ss ON ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept WHERE  t.designation=d.DesignationCode AND dept.DEPT_ID=t.department AND  t.isActive=? AND t.`Loc_ID` LIKE ? AND t.`department` LIKE ? AND t.`designation` LIKE ? ORDER BY t.registerId"; 
	public static final String ALL_TEACHER_DETAILS1 = "select t.registerId,t.Abbreviative_Id,concat(t.FirstName,' ',t.LastName) as teacherName,t.Qualification,t.department,d.designationName,t.MobileNo,t.TeacherID, t.emailId,dept.DEPT_NAME,case when t.bankname is null then '-' else t.bankname end  bankname,case when t.pannumber is null then '-' else t.pannumber end pannumber,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC from campus_teachers t left outer join campus_staff_salarydetails ss on ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept where  t.designation=d.DesignationCode and dept.DEPT_ID=t.department and  t.isActive='Y' order by concat(t.FirstName,'',t.LastName)"; 
	public static final String SEARCH_TEACHER_DETAILS="SELECT t.registerId,t.Abbreviative_Id,CONCAT(t.FirstName,' ',t.LastName) AS teacherName,t.Qualification,d.designationName,t.mobileNo,t.TeacherID,t.emailId FROM campus_teachers t,campus_designation d WHERE t.designation=d.DesignationCode AND t.isActive=? AND t.`Loc_ID` LIKE ? AND t.`department` LIKE ? AND t.`designation` LIKE ? AND (CONCAT(t.FirstName,' ',t.LastName) LIKE ? OR t.mobileNo LIKE ?  OR t.Qualification LIKE ? OR d.designationName LIKE ? OR t.emailId LIKE ? OR t.registerId LIKE ? OR t.Abbreviative_Id LIKE ?) order by t.registerId";
    public static final String DELETE_TEACHER_DETAILS="update campus_teachers  set isActive=?,`reason`=? where TeacherID=?";
    public static final String GET_SUBJECTS = "select distinct(subjectName),subjectID  from campus_subject WHERE classid=? and status='active' AND locationId=? group by subjectName order by subjectName";
    public static final String GET_TEACHER_COUNT = "select count(*) from campus_teachers where UserName=? and TeacherID!=?";
    public static final String GET_TEACHERMAIL_COUNT = "select count(*) from campus_teachers where emailId=? and TeacherID!=?";
    public static final String CHECK_TACHER_COUNT_ = "select count(*) from campus_teachers where UserName=? and department=? and designation=? and MobileNo=? and DateOfBirth=? and DateOfJoining=? ";
    public static final String INSERT_TEACHER ="insert into campus_teachers(TeacherID,FirstName,LastName,Qualification,presentAddress,MobileNo,UserName,emailId,profilePath,imagePath,DateOfBirth,DateOfJoining,designation,idProofPath,teachingType,department,gender,bankname,accountnumber,pannumber,bloodgroup,fathername,mothername,permanentAddress,createdby,createddate,registerId,password,role,userType,document1,document2,document3,document4,document5,reportingTo,is_student_studying,studentName,student_admission_id,fatherMobile,motherMobile,Abbreviative_Id,aadhaarnumber,maritalstatus,spousename,spouseMobile,Loc_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String GET_SERVER_URL = "select URL from campus_settings";
    public static final String GET_SINGLE_TEACHER_DEATILS="SELECT tea.*,loc.Location_Name, loc.Location_Id FROM campus_teachers tea left JOIN campus_user usr ON usr.employeecode = tea.TeacherID LEFT OUTER JOIN campus_location loc ON loc.Location_Id=tea.Loc_ID where tea.TeacherID=?";
    public static final String UPDATE_TEACHER="update campus_teachers set FirstName=?,LastName=?,Qualification=?,presentAddress=?,MobileNo=?,UserName=?,emailId=?,profilePath=?,imagePath=?,DateOfBirth=?,DateOfJoining=?,designation=?,idProofPath=?,teachingType=?,department=?,gender=?,bloodgroup=?,fathername=?,mothername=?,permanentAddress=?,updatedby=?,updateddate=?,role=?,reportingTo=?, bankname=?, accountnumber=?, pannumber=?, document1=?,document2=?,document3=?,document4=?,document5=?,is_student_studying=?,studentName=?,student_admission_id=?, fatherMobile=?, motherMobile=?,Abbreviative_Id=?,aadhaarnumber=?,maritalstatus=?,spousename=?,spouseMobile=?,Loc_ID=?,userType=?,password=? where TeacherID=?";
    public static final String GET_REGISTRAION_COUNT="select count(*)registerId,isActive from campus_teachers where registerId=? and TeacherID!=? AND Loc_ID=?";
 
   
   //For Class Mapping
   public static final String GET_MAPPING_CLASSES="select CTCode,ClassCode,SectionCode from campus_classteacher where TeacherCode=?";
   public static final String GET_UNMAPPING_SECTION="select se.classsection_id_int,se.classsection_name_var from campus_classsection se join campus_classdetail cl on cl.classdetail_id_int=se.classdetail_id_int and cl.locationId=se.locationId where se.classdetail_id_int=? and se.classsection_id_int not in (select SectionCode from campus_classteacher where TeacherCode!=? and  ClassCode=?) order by se.classsection_name_var";
   public static final String GET_SECTION_NAME="select classsection_name_var from campus_classsection where classsection_id_int=?";
   
   public static final String DELETE_CLAA_MAPPINGS="delete from campus_classteacher where TeacherCode=?";
   public static final String INSERT_CLASS_MAPPINGS="insert into campus_classteacher(CTCode,ClassCode,SectionCode,locationId,TeacherCode,CreateTime,CreateUser) values(?,?,?,?,?,?,?)";
   
   
   //For Subject Mappings
   
   public static final String DELETE_SUBJECT_MAPPINGS="delete from campus_teachersettings where teacherID=?";
   public static final String INSERT_SUBJECTS_MAPPINGS="insert into campus_teachersettings(teacherID,classID,subjectID,locationId,createdBy,createTime) values(?,?,?,?,?,?)";
   public static final String GET_MAPPING_SUBJECTS="select teacherID,classID,subjectID from campus_teachersettings where teacherID=?";
   

   public static final String SEARCH_TEACHER_EMPLOYEMENT_DETAILS = "select t.registerId,concat(t.FirstName,'',t.LastName) as teacherName,t.Qualification,d.designationName,t.MobileNo,t.TeacherID,t.emailId,dept.DEPT_NAME,ss.BankAccNumber,ss.EmployeePfNo,ss.CTC,ss.totalSalary from campus_teachers t left outer join campus_staff_salarydetails ss on ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept where  t.designation=d.DesignationCode and dept.DEPT_ID=t.department and  t.isActive='Y' and (t.FirstName like ? or t.registerId like ? or dept.DEPT_NAME like ?)";
   public static final String REPORTING_TO_LIST = "SELECT TeacherId, CONCAT(FirstName,' ',LastName) AS NAME FROM campus_teachers WHERE isActive='Y'  ORDER BY FirstName";
   
   
   //Insert Into user table
   
   public static final String INSERT_USER_DETAILS = "insert into campus_user(usercode,employeecode,username,password,role,type,createuser,createdate,locationId,`cust_id`) values(?,?,?,?,?,?,?,?,?,?)";
   
   public static final String UPDATE_USER_DETAILS = "update campus_user set username=?,role=?,type=?,modifyuser=?,modifydate=? where employeecode=?";
   public static final String GET_STUDENT_ADMISSION_DETAILS = "select student_admissionno_var,student_id_int from campus_student where fms_acadamicyear_id_int=?";
   public static final String GET_SINGLE_TEACHER_DEATIL="select * from campus_teachers where registerId=?";

   
   public static final String GET_ABBREVATIVE_COUNT="select count(*)`Abbreviative_Id`,`isActive` from campus_teachers where Abbreviative_Id =? AND Loc_ID=?";
   
   public static final String GET_LEAVE_TYPES="select * from campus_new_leave_bank where Accy_Id=? AND Loc_ID=?";
   public static final String GET_NO_OF_LEAVES="select No_Of_Leaves from campus_new_leave_bank where Accy_Id=? AND Loc_ID=? AND Leave_ID=?";
   //`campus_teacher_new_leave_bank_details``AccYearCode``EmpId``Leave_Type``Leave_Name``total_available``total_consumed``total_avaliable_leaves``LastUpdate``UpdatedBy``Date_Of_Join``LOC_Id`
   public static final String INSERT_LEAVE = "";
   
   public static final String GET_STAFF_COUNT="select count(TeacherID) from campus_staff_income_section where TeacherID=?";
   public static final String ALL_TEACHER_DETAILS_BY_LOCID_AND_DEPRTID ="SELECT t.registerId,t.Abbreviative_Id,CONCAT(t.FirstName,' ',t.LastName) AS teacherName,t.Qualification,d.designationName,t.MobileNo,t.TeacherID, t.emailId,dept.DEPT_NAME,CASE WHEN t.bankname IS NULL THEN '-' ELSE t.bankname END  bankname,CASE WHEN t.pannumber IS NULL THEN '-' ELSE t.pannumber END pannumber,CASE WHEN ss.BankAccNumber IS NULL THEN '-' WHEN ss.BankAccNumber='' THEN '-' ELSE ss.BankAccNumber END BankAccNumber,CASE WHEN ss.EmployeePfNo IS NULL THEN '-' WHEN ss.EmployeePfNo='' THEN '-' ELSE ss.EmployeePfNo END EmployeePfNo,ss.CTC,ss.totalSalary FROM campus_location cl JOIN campus_teachers t ON cl.Location_Id=t.Loc_ID LEFT OUTER JOIN campus_staff_salarydetails ss ON ss.TeacherID=t.TeacherID,campus_designation d,campus_department dept WHERE t.designation=d.DesignationCode AND dept.DEPT_ID=t.department AND  t.isActive='Y' AND t.Loc_ID LIKE ? AND dept.DEPT_ID LIKE ? AND (t.registerId LIKE ? OR t.FirstName LIKE ? OR t.LastName LIKE ? OR dept.DEPT_NAME LIKE ? OR ss.BankAccNumber LIKE ? OR ss.EmployeePfNo LIKE ? OR CONCAT(t.FirstName,' ',t.LastName) LIKE ?)  ORDER BY CONCAT(t.FirstName,'',t.LastName)";
   public static final String GET_STAFF_COUNT_USERTABLE ="SELECT COUNT(*)`employeecode` FROM `campus_user` WHERE `employeecode` =?";
   public static final String DELETE_TEACHER_DETAILS_USERTABLE = "UPDATE `campus_user` SET `isActive`=?, `remarks`=? WHERE `employeecode`=?";
   public static final String DELETE_USER_DETAILS = "DELETE FROM `campus_user` WHERE `employeecode`=?";
   

}
