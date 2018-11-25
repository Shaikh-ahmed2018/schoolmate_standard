package com.centris.campus.util;

public class UserManagementSqlutil {

	
	public static final String USERRECORDS ="SELECT cu.`usercode`,cu.`username`,ct.`TeacherID`,CONCAT(ct.`FirstName`,' ',ct.`LastName`)AS teacher ,ct.`mobileNo` FROM `campus_user` cu JOIN `campus_role` cr ON cr.`RoleCode`=cu.`role` AND cr.`isActive`='Y' JOIN `campus_teachers` ct ON ct.`TeacherID`=cu.`employeecode`AND ct.`isActive`='Y' WHERE cu.`isActive`='Y'";
	public static final String GET_TEACHERS = "SELECT distinct TeacherID as Id,UserName as username,case when LastName is null then FirstName  else concat(FirstName, ' ',LastName) end FirstName ,mobileno FROM campus_teachers where FirstName like ? or LastName like ? or UserName like ? or mobileno like ? and isActive='Y'";
	public static final String GET_PARENTS = "SELECT ParentID as Id,UserName as username,FatherName as firstname,mobileno FROM campus_parents where FatherName like ? or UserName like ? or mobileno like ? and pstatus='active'";
	public static final String GET_TEACHER = "SELECT  CONCAT(ct.`FirstName`, ' ',ct.`LastName`)AS teachername ,cu.`username`,ct.TeacherID FROM `campus_user` cu JOIN `campus_teachers` ct ON ct.`TeacherID`=cu.`employeecode` WHERE cu.`usercode`=?";
	public static final String GET_PARENT = "select FatherName,UserName from  campus_parents where ParentID=?";
	public static final String CHANGE_TEACHER_PWD = "update campus_teachers  set password=? where TeacherID=?";
	public static final String CHANGE_PARENT_PWD = "update campus_parents  set password=? where ParentID=?";
	public static final String BLOCK_TEACHER = "update campus_teachers set isActive='N' where TeacherID=? ";
	public static final String BLOCK_PARENT = "update campus_parents set pstatus='deactive' where ParentID=? ";
	
	
	public static final String CHANGE_TEACHER_PWD_USER = "update campus_user set password=? where usercode=?";
	public static final String GET_ALL_USER_RECORDS = "SELECT case when cu.remarks IS NULL then '' when cu.remarks='' then '' else cu.remarks end remarks,cr.RoleName,cu.usercode,cu.cust_id,cu.username,cu.emailId,cu.mobileno,cu.app_userid FROM campus_user cu JOIN campus_role cr ON cr.RoleCode=cu.role where cu.isActive='Y' ";
	public static final String GET_ALL_ACTIVE_INACTIVE_USER_RECORDS = "SELECT case when cu.remarks IS NULL then '' when cu.remarks='' then '' else cu.remarks end remarks,cr.RoleName,cu.usercode,cu.cust_id,cu.username,cu.emailId,cu.mobileno FROM campus_user cu JOIN campus_role cr ON cr.RoleCode=cu.role where cu.isActive=?";
	public static final String UPDATE_USER_RECORDS_STATUS = "UPDATE campus_user SET isActive=?,remarks=? WHERE usercode=?";
	public static final String GET_USER_DETAILS = "SELECT cu.usercode,cu.role,cu.locationId,cu.username,cu.password,cu.emailId,cu.mobileno,cu.app_userid FROM campus_user cu WHERE cu.usercode=?";
	public static final String UPDATE_USER_RECORDS_STATUS_TEACHER = "UPDATE campus_user SET isActive=?,remarks=? WHERE usercode=?";
}
