package com.centris.campus.util;

public class AcadamicYearPlanSqlUtil {

	public static final String INSERT_ACADAMICYEAR_PLAN = "insert into campus_accyearplan(AccYearPlanCode,Title,Date,WeekDay,StartTime,EndTime,InvitationFile,Description,AcadamicYear,CreatedBy,CreateDate,venue_details,locationId)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_ALL_ACADAMICYEAR_PLANS = "select DISTINCT cl.Location_Name,ca.*from campus_accyearplan ca left join campus_location cl on ca.locationId=cl.Location_Id where ca.AcadamicYear=? and ca.locationId like ? order by Title";
	public static final String GET_SINGLE_ACADAMICYEAR_PLAN = "select *from campus_accyearplan where AccYearPlanCode=?";
	public static final String GET_SEARCH_ACADAMICYEAR_PLAN ="SELECT ca.AccYearPlanCode,cl.Location_Name,ca.Title,ca.Date,ca.StartTime,ca.EndTime,ca.venue_details,ca.WEEKDAY,ca.InvitationFile,ca.Description FROM campus_accyearplan ca JOIN campus_location cl ON cl.Location_Id=ca.locationId WHERE (Location_Name LIKE ? OR Title LIKE ? OR DATE LIKE ? OR venue_details LIKE ? OR StartTime LIKE ? OR EndTime LIKE ?)";
	public static final String DELETE_ACADAMICYEAR_PLAN = "delete from campus_accyearplan where AccYearPlanCode=?";
	public static final String UPDATE_ACADAMICYEAR_PLAN = "update campus_accyearplan set Title=?,Date=?,WeekDay=?,StartTime=?,EndTime=?,InvitationFile=?,Description=?,ModifiedBy=?,ModifiedDate=?,venue_details =?,locationId=? where AccYearPlanCode=?";
	public static final String VALIDATE_ACADAMICYEAR_PLAN = "select count(*) from campus_accyearplan where Date=? and (? between StartTime and EndTime or ? between StartTime and EndTime)  and venue_details =?";
	public static final String VALIDATE_ACADAMICYEAR_PLAN_UPDATE = "select count(*) from campus_accyearplan where Date=? and (? between StartTime and EndTime or ? between StartTime and EndTime) and venue_details =? and AccYearPlanCode!=?";
	public static final String COUNT = "select count(*) from campus_accyearplan where AccYearPlanCode=?";
}
