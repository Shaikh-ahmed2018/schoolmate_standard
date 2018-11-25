package com.centris.campus.util;

public class HolidayMasterSqlUtil {


	public static final String GET_ALL_YEARS = "select accyear from onsite_accyear order by accyear";
	public static final String INSERT_HOLIDAY = "insert into campus_holidaymaster(HOL_ID,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,HOLIDAY_TYPE,CURRENT_YEAR,CREATEDBY,LOC_ID,isActive)values(?,?,?,?,?,?,?,?,?)";
	public static final String GET_ALL_HOLIDAYS = "select distinct loc.LocationCode,loc.Location,HOLIDAY_DATE,HOLIDAY_NAME,WEEKDAY from onsite_holidaymaster hm,ai_emp_location loc where hm.Location=loc.LocationCode and hm.Location like ? and hm.CURRENT_YEAR=? order by HOLIDAY_DATE  asc";
	public static final String GET_EDIT_HOLIDAY = "select HOL_ID,LOC_ID,HOLIDAY_DATE,WEEKDAY,HOLIDAY_NAME,CURRENT_YEAR,HOLIDAY_TYPE from campus_holidaymaster where HOL_ID like ?";
	public static final String DELETE_SINGLE_HOLIDAY = "delete from campus_holidaymaster where HOL_ID = ?";
	public static final String DATE_VALIDATE = "select count(*) from campus_holidaymaster where HOLIDAY_DATE=? and LOC_ID like ? and CURRENT_YEAR like ?";
	public static final String GET_DISTINCT_HOLIDAYLIST = "select distinct HOLIDAY_DATE,Location from onsite_holidaymaster";
	public static final String UPDATE_HOLIDAY = "UPDATE campus_holidaymaster SET HOLIDAY_NAME=?,HOLIDAY_TYPE=?,MODIFIED_BY=?,MODIFIED_DATE=?,HOLIDAY_DATE=?,LOC_ID=?,CURRENT_YEAR=? WHERE HOL_ID like ?";
	public static final String CHECK_DUPLICATE_HOLIDAY="select count(*) from onsite_holidaymaster where HOLIDAY_DATE=? and Location=?";
	public static final String SELECT_LOCATION = "select LocationCode,Location from ai_emp_location where isActive='Y' and LocationCode in (select LocationCode from onsite_groupmapping where Groupid=?)";
	public static final String GET_ALL_HOLIDAY = "SELECT ch.isActive,ch.Remarks,ch.HOLIDAY_NAME,ch.WEEKDAY,ch.HOLIDAY_DATE,ch.HOLIDAY_TYPE,ch.LOC_ID,ch.HOL_ID FROM campus_holidaymaster ch JOIN campus_location cl ON ch.LOC_ID=cl.Location_Id WHERE ch.CURRENT_YEAR=? AND ch.LOC_ID LIKE ? AND ch.isActive='Y' AND cl.isActive='Y' ORDER BY ch.HOLIDAY_DATE";
	public static final String GET_SEARCH_HOLIDAY = "select * from campus_holidaymaster where  LOC_ID like ? AND CURRENT_YEAR=? AND isActive=? ";
	public static final String HOLIDAYNAME_VALIDATE = "SELECT COUNT(*) FROM campus_holidaymaster WHERE HOLIDAY_NAME=? AND LOC_ID like ? AND CURRENT_YEAR=?";
	public static final String GET_ALL_HOLIDAY_BY_STATUS ="SELECT chm.isActive,chm.Remarks,chm.HOLIDAY_NAME,chm.WEEKDAY,chm.HOLIDAY_DATE,chm.HOLIDAY_TYPE,chm.LOC_ID,chm.HOL_ID FROM campus_holidaymaster chm JOIN campus_location cl ON cl.Location_Id=chm.LOC_ID JOIN campus_acadamicyear cay ON cay.acadamic_id=chm.CURRENT_YEAR WHERE chm.CURRENT_YEAR=? AND chm.LOC_ID LIKE ? AND chm.isActive=? AND cl.isActive='Y' AND cay.isActive='Y' ORDER BY chm.HOLIDAY_DATE";
	public static final String ACTIVE_INACTIVE_HOLIDAY_DETAILS_BY_STATUS ="UPDATE  campus_holidaymaster SET isActive=?,Remarks=? WHERE HOL_ID=?";


}
