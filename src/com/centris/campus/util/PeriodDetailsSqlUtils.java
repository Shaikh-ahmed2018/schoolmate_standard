package com.centris.campus.util;

public class PeriodDetailsSqlUtils {

	public static final String SAVE_PERIOD_DETAILS = "INSERT INTO `campus_period_master`(`loc_id`,`stream_id`,`class_id`,`no_of_period`,`created_by`,`created_date`)VALUES(?,?,?,?,?,now())";
	public static final String GET_PERIOD_LIST ="SELECT DISTINCT cpm.`period_id`,cl.`Location_Name`,ccs.`classstream_name_var`,ccd.`classdetails_name_var`,cpm.`no_of_period` FROM `campus_period_master` cpm JOIN `campus_location` cl ON cl.`Location_Id`=cpm.`loc_id` AND cl.`isActive` ='Y' JOIN `campus_classstream` ccs ON ccs.`classstream_id_int`=cpm.`stream_id`AND ccs.`isActive`='Y' JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cpm.`class_id` AND ccd.`isActive`='Y' order by cl.`Location_Name`,ccs.`classstream_name_var`  ";
	public static final String EDIT_PERIOD_LIST ="SELECT DISTINCT cpm.`loc_id`,cpm.`stream_id`,cpm.`class_id`,cpm.`no_of_period`,cpm.`period_id` FROM `campus_period_master` cpm JOIN `campus_location` cl ON cl.`Location_Id`=cpm.`loc_id` AND cl.`isActive` ='Y' JOIN `campus_classstream` ccs ON ccs.`classstream_id_int`=cpm.`stream_id`AND ccs.`isActive`='Y' JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cpm.`class_id` AND ccd.`isActive`='Y' WHERE  cpm.`period_id`=?";
	public static final String UPDATE_PERIOD_DETAILS = "UPDATE `campus_period_master` SET `loc_id`=? ,`stream_id`=?,`class_id`=?,`no_of_period`=?,`modified_by`=? WHERE `period_id`=?";
	public static final String DELETE_PERIOD_LIST = "DELETE FROM`campus_period_master` WHERE `period_id`=?";
	public static final String GET_PERIOD_LIST_FILTER = "SELECT DISTINCT cpm.`period_id`,cl.`Location_Name`,ccs.`classstream_name_var`,ccd.`classdetails_name_var`,cpm.`no_of_period` FROM `campus_period_master` cpm JOIN `campus_location` cl ON cl.`Location_Id`=cpm.`loc_id` AND cl.`isActive` ='Y' JOIN `campus_classstream` ccs ON ccs.`classstream_id_int`=cpm.`stream_id`AND ccs.`isActive`='Y' JOIN `campus_classdetail` ccd ON ccd.`classdetail_id_int`=cpm.`class_id` AND ccd.`isActive`='Y' WHERE  cpm.`loc_id` LIKE ? AND cpm.`stream_id` LIKE ? AND cpm.`class_id` LIKE ? order by ccs.`classstream_name_var` ,  CAST(SUBSTRING(ccd.`classdetail_id_int`,4,LENGTH(ccd.`classdetail_id_int`)-3) AS SIGNED) ";
	public static final String CHECK_CLASS_NAME ="SELECT COUNT(*) FROM `campus_period_master` WHERE `class_id`=? and loc_id = ?";

}
