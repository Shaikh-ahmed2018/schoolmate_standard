package com.centris.campus.util;

public class UploadStageXLSqlUtil {
	
	public static final String CHECK_BEFORINSERT_COUNT= "select count(*) from campus_teachers";
	
	
	public static final String CHECK_STAGE_ID= "select count(*) from campus_fee_stage where  stage_id=?";
	
	public static final String INSERT_STAGE="insert into campus_fee_stage( stage_name, description, createdby, createdtime,location,accyear) values (?,?,?,?,?,?)";
	
	public static final String STAGE_DUPLICATE = "select count(*)  from campus_fee_stage where stage_name=? AND location=?";


	public static final String GET_STAGE_ID = "SELECT `stage_id` FROM `campus_fee_stage` WHERE `stage_name`=?";


	public static final String INSERT_STAGE_AMOUNT = "INSERT INTO `campus_fee_stage_amount`(`stageId`,`accyearId`,`locationId`,`stageamount`,`createdby`,createdtime) VALUES(?,?,?,?,?,now())";
	
}
