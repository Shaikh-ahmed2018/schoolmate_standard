package com.centris.campus.util;

public class ClassUtilsConstants {
	public static final String GET_CLASS_DETAILS="select cl.Location_Name,cd.locationId,cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_id_int,cs.classstream_name_var,cd.isActive,cd.remarks from campus_classdetail cd,campus_classstream cs,campus_location cl where cd.locationId=cl.Location_Id and cd.classstream_id_int=cs.classstream_id_int and cd.locationId like ? AND cd.isActive='Y' AND cl.isActive='Y' AND cs.isActive='Y' order by length(cd.classdetail_id_int),cd.classdetail_id_int";
	public static final String CREATE_CLASS = "INSERT INTO campus_classdetail(classdetail_id_int,classstream_id_int, classdetails_name_var, createuser,createdate,locationId) VALUES (?,?,?,?,now(),?)";
	public static final String GET_CLASS_STREAM = "select classstream_id_int,classstream_name_var from campus_classstream ccs where locationId = ? AND isActive='Y' ORDER BY CASE WHEN ccs.classstream_name_var = 'Montessori' THEN '1' WHEN ccs.classstream_name_var = 'Nursery' THEN '1' WHEN ccs.classstream_name_var = 'Pre-Nursery' THEN '1' WHEN ccs.classstream_name_var = 'Pre-KG' THEN '1' WHEN ccs.classstream_name_var = 'Primary' THEN '2' WHEN  ccs.classstream_name_var = 'Secondary' THEN '3' WHEN ccs.classstream_name_var = 'Sr.Secondary' THEN '4' ELSE ccs.classstream_name_var END";
	public static final String CHECK_CLASS_NAME = "select count(*)Classname,isActive from campus_classdetail where classdetails_name_var = ? and classstream_id_int=? and locationId like ?";
	public static final String CHECK_UPDATE_CLASS_NAME="select count(*)Classname from campus_classdetail where classdetails_name_var = ? and classstream_id_int=? and classdetail_id_int!=? and locationId like ?";
	public static final String EDIT_CLASS = "select locationId,classdetail_id_int,classstream_id_int,classdetails_name_var from campus_classdetail where classdetail_id_int = ?";
	public static final String CLASS_COUNT_IN_CAMPUS_ASSIGNMENT = "select  count(*) from campus_assignment  where  ClassID=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_CLASSSECTION = "select count(*) from campus_classsection  where  classdetail_id_int=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_CLASSTEACHER = "select  count(*) from campus_classteacher  where  ClassCode=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_EXAMINATION_TIME_TABLE = "select  count(*) from campus_examination_timetable  where  classid=? and locationId=?";
	public static final String CLASS_COUNT_IN_FEE_SETUP = "select  count(*) from campus_fee_setup  where  ClassCode=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_FEE_STRUCTURE = "select  count(*) from campus_fee_structure  where  ClassCode=?";
	public static final String CLASS_COUNT_IN_CAMPUS_FEESETTINGS = "select  count(*) from campus_feesettings  where  classdetail_id_int=?";
	public static final String CLASS_COUNT_IN_MARKS_UPLOAD = "select  count(*) from campus_marks_upload  where  classid=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_MEETING = "select  count(*) from campus_meeting  where  classid=?";
	public static final String CLASS_COUNT_IN_CAMPUS_STUDENT = "select  count(*) from campus_student_classdetails  where  classdetail_id_int=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_STUDENT_PROMOTION = "select  count(*) from campus_studentpromotion  where  (studentpromotion_fromclass_var=? or studentpromotion_toclass_var=?) and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_TIME_TABLE = "select  count(*) from campus_timetable  where  classid=? and locationId=?";
	public static final String CLASS_COUNT_IN_CAMPUS_SUBJECT = "select  count(*) from campus_subject  where  classid=? and locationId=?";
	public static final String CLASS_COUNT_IN_TEACHERSETTINGS = "select  count(*) from campus_teachersettings  where  classID=? and locationId=?";
	public static final String DELETE_CLASS_DETAIL = "DELETE FROM campus_classdetail WHERE classdetail_id_int =? and locationId=?";
	/*public static final String UPDATE_CLASS_DETAIL = "UPDATE campus_classdetail SET classstream_id_int=?,classdetails_name_var= ?, modifyuser= ?, modifydate = ?  WHERE classdetail_id_int =?";*/
	
	public static final String UPDATE_CLASS_DETAIL = "UPDATE campus_classdetail SET classstream_id_int=?,classdetails_name_var= ?, modifyuser= ?, modifydate = ?  WHERE classdetail_id_int =? and locationId=?";
	
	public static final String GET_CLASS_DETAILS_BY_SEARCH_ID="select distinct cl.Location_Name,cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_name_var from campus_classdetail cd,campus_classstream cs,campus_location cl where cd.locationId=cl.Location_Id and cd.classstream_id_int=cs.classstream_id_int and (cd.classdetails_name_var like ? or cs.classstream_name_var like ? or cl.Location_Name like ?) and cd.locationId like ? order by length(cd.classdetail_id_int),cd.classdetail_id_int";
	public static final String GET_CLASS_DETAILS_BY_JS = "select cl.Location_Name,cd.locationId,cd.classdetail_id_int,cd.classdetails_name_var,cs.classstream_id_int,cs.classstream_name_var,cd.isActive,cd.remarks from campus_classdetail cd,campus_classstream cs,campus_location cl where cd.locationId=cl.Location_Id and cd.classstream_id_int=cs.classstream_id_int and cd.locationId like ? and cs.classstream_id_int like ? AND cd.isActive=? AND cl.isActive='Y' and cs.isActive='Y' order by length(cd.classdetail_id_int),cd.classdetail_id_int";
	public static final String UPDATE_CLASS_DETAIL_BY_STATUS ="UPDATE campus_classdetail SET isActive=?,remarks=? WHERE classdetail_id_int =? AND locationId=?";
	public static final String EDIT_CLASS_BY_CLASS = "SELECT ccd.locationId,ccd.classdetail_id_int,ccd.classstream_id_int,ccd.classdetails_name_var FROM campus_classdetail ccd JOIN campus_location cl ON cl.Location_Id=ccd.locationId JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int AND cl.Location_Id=ccs.locationId WHERE ccd.classdetail_id_int = ? AND ccd.locationId=?";

}
