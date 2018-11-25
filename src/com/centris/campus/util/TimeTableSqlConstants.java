package com.centris.campus.util;

public class TimeTableSqlConstants {

	// Student Time Table

	public static final String TIMETABLE_DETAILS_COUNT = "select count(*) from campus_timetable_student where timetable_id=?";
	public static final String TIMETABLE_GET_DETAILS = "SELECT ctd.*,cday.dayname FROM `campus_timetable_details` ctd  JOIN  `campus_timetable_day` cday ON cday.daycode=ctd.daycode WHERE ctd.`timtable_id`=?";
	//public static final String TIMETABLE_GET_DETAILS = "select d.dayname,vd.daycode,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9,sub.subjectID,sub.subjectName from campus_timetable_studentdetails vd join campus_timetable_day d on d.daycode=vd.daycode join campus_timetable_student ts on vd.timetableid=ts.timetable_id join campus_subject sub on sub.classid=ts.classid where vd.timetableid=? and sub.classid=?";
	public static final String TIMETABLE_GET_DAYS = "select * from campus_timetable_day";
	public static final String TIMETABLE_GET_CLASSID = "select classdetail_id_int,classdetails_name_var from campus_classdetail order by classdetails_name_var asc";

	public static final String TIMETABLE_GET_SECTIONID = "select classsection_id_int,classsection_name_var from campus_classsection where classdetail_id_int=? order by classsection_name_var asc";
	public static final String TIMETABLE_DETAILS_UPDATECOUNT = "select count(*) from campus_timetable_student where classid=? and sectionid=? and accyearid=?";
	public static final String TIMETABLE_DETAILS_INSERT = "insert into campus_timetable_student (timetable_id,classid,sectionid,accyearid,created_date,created_by,locationId) values(?,?,?,?,?,?,?)";
	public static final String TIMETABLE_DETAILS_INSERTDETAILS = "insert into campus_timetable_studentdetails (timetableid,daycode,period1,period2,period3,period4,period5,period6,period7,period8,period9) values  (?,?,?,?,?,?,?,?,?,?,?)";

	public static final String TIMETABLE_DETAILS_ID = "select timetable_id from campus_timetable_student where classid=? and sectionid=? and accyearid=?";
	public static final String TIMETABLE_DETAILS_DELETE = "delete from campus_timetable_studentdetails where timetableid=?";

	public static final String GET_CLASS_NAME = "select classdetails_name_var,classdetail_id_int from campus_classdetail where  classdetail_id_int = ?";
	public static final String GET_SECTION_NAME = "select classsection_name_var from campus_classsection where  classsection_id_int = ?";
	
	
	
	// Teacher Time Table

	public static final String TEACHER_TIMETABLE_DETAILS_COUNT = "select count(*) from campus_timetable_teacher where teacherid=? and accyearid=?";
	public static final String TEACHER_TIMETABLE_GET_DETAILS = "select d.dayname,vd.daycode,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9 from campus_timetable_teacher v join campus_timetable_teacherdetails vd on vd.teachertimetable_id=v.teachertimetable_id join campus_timetable_day d on d.daycode=vd.daycode where v.teacherid=? and v.accyearid=?";
	public static final String TEACHER_TIMETABLE_GET_DAYS = "select * from campus_timetable_day";
	public static final String TEACHER_IDANDNAME = "select TeacherID, concat(FirstName,' ',LastName)TeacherName from campus_teachers where isActive='Y' and tstatus='active' order by FirstName asc";

	public static final String TEACHER_TIMETABLE_DETAILS_UPDATECOUNT = "select count(*) from campus_timetable_teacher where teacherid=? and accyearid=?";
	public static final String TEACHER_TIMETABLE_DETAILS_INSERT = "insert into campus_timetable_teacher (teachertimetable_id,teacherid,accyearid,created_date,created_by) values(?,?,?,?,?)";
	public static final String TEACHER_TIMETABLE_DETAILS_INSERTDETAILS = "insert into campus_timetable_teacherdetails (teachertimetable_id,daycode,period1,period2,period3,period4,period5,period6,period7,period8,period9) values  (?,?,?,?,?,?,?,?,?,?,?)";

	public static final String TEACHER_TIMETABLE_DETAILS_DELETE = "delete from campus_timetable_teacherdetails where teachertimetable_id in(select teachertimetable_id from campus_timetable_teacher where teacherid=? and accyearid=?)";
	public static final String TEACHER_TIMETABLE_DETAILS_ID = "select teachertimetable_id from campus_timetable_teacher where teacherid=? and accyearid=?";

	
	//Written by seshu
	
	//public static final String GET_CLASS_TIMETABLE_LIST = "select cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,st.created_date,st.created_by,st.updated_by,st.updated_time from campus_classdetail cd,campus_classsection sec left outer join campus_timetable_student st on sec.classsection_id_int=st.sectionid and st.accyearid = ?   where  cd.classdetail_id_int=sec.classdetail_id_int order by length(cd.classdetail_id_int),cd.classdetail_id_int,length(sec.classsection_id_int),sec.classsection_id_int";
//	public static final String GET_CLASS_TIMETABLE_LIST="select DISTINCT cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,cast(st.created_date as date) as created_date,st.created_by,st.updated_by,cast(st.updated_time as date) as updated_time,concat(ct.FirstName,' ',ct.LastName) as teachername from campus_classdetail cd,campus_classsection sec left outer join campus_timetable_student st on sec.classsection_id_int=st.sectionid and st.accyearid = ? join campus_classteacher t on sec.classsection_id_int=t.SectionCode join campus_teachers ct on t.TeacherCode=ct.TeacherID	where  cd.classdetail_id_int=sec.classdetail_id_int and cd.locationId like ? order by length(cd.classdetail_id_int),cd.classdetail_id_int,length(sec.classsection_id_int),sec.classsection_id_int";
	public static final String GET_CLASS_TIMETABLE_LIST="SELECT cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,CAST(st.created_date AS DATE) AS created_date,st.created_by,st.updated_by,CAST(st.updated_time AS DATE) AS updated_time,CONCAT(ct.FirstName,' ',ct.LastName) AS teachername FROM campus_classdetail cd JOIN campus_classsection sec ON cd.classdetail_id_int=sec.classdetail_id_int AND cd.`classdetail_id_int`=sec.`classdetail_id_int` AND sec.`locationId`=cd.locationId LEFT JOIN campus_timetable_student st ON sec.classsection_id_int=st.sectionid AND cd.classdetail_id_int=st.`classid` AND st.accyearid = ? JOIN campus_classteacher t ON sec.classsection_id_int=t.SectionCode AND t.`ClassCode`=cd.classdetail_id_int JOIN campus_teachers ct ON t.TeacherCode=ct.TeacherID WHERE cd.locationId=?";
	public static final String GET_STAFF_NAME = "select concat(FirstName,' ',LastName) as StaffName from campus_teachers where TeacherID=?";
	public static final String GET_SUBJECT_NAME = "select distinct subjectName from campus_subject where subjectID=?";
	
	//For teacher list
	
	public static final String GET_TEACHER_TIMETABLE_LIST = "select t.TeacherID,t.registerId,concat(t.FirstName,' ',t.LastName) as teachername,tt.teachertimetable_id,tt.created_date,tt.created_by,tt.updated_date,tt.updated_time from campus_teachers t left outer join campus_timetable_teacher tt on t.TeacherID=tt.teacherid and tt.accyearid = ? where t.isActive='Y'  order by t.FirstName ";
	
	public static final String GET_CLASS_SECTION_LIST = "select  concat(c.classdetail_id_int,'-',s.classsection_id_int) classid,concat(c.classdetails_name_var,'-',s.classsection_name_var) classname from campus_classdetail c,campus_classsection s where s.classdetail_id_int=c.classdetail_id_int order by length(c.classdetail_id_int),c.classdetail_id_int,c.classdetails_name_var,length(s.classsection_id_int),s.classsection_id_int,s.classsection_name_var";
	public static final String GET_CLASS_ID_BY_NAME = "select classdetail_id_int from campus_classdetail where classdetails_name_var=?";
	public static final String GET_SECTION_ID_BY_NAME = "select sec.classsection_id_int from campus_classsection  sec join campus_classdetail cls on cls.classdetail_id_int=sec.classdetail_id_int  where sec. classsection_name_var =? and cls.classdetails_name_var=?";

	public static final String GET_CLASS_TIMETABLE_LIST_BY_CLASS = "select cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,st.created_date,st.created_by,st.updated_by,st.updated_time,concat(ct.FirstName,' ',ct.LastName) as teachername from campus_classdetail cd,campus_classsection sec left outer join campus_timetable_student st on sec.classsection_id_int=st.sectionid and st.accyearid = ? join campus_classteacher t on sec.classsection_id_int=t.SectionCode join campus_teachers ct on t.TeacherCode=ct.TeacherID where cd.classdetail_id_int like ? and cd.locationId like ? and cd.classdetail_id_int=sec.classdetail_id_int order by length(cd.classdetail_id_int),cd.classdetail_id_int,length(sec.classsection_id_int),sec.classsection_id_int";
	public static final String GET_CLASS_TIMETABLE_LIST_BY_SECTION = "select cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,st.created_date,st.created_by,st.updated_by,st.updated_time,concat(ct.FirstName,' ',ct.LastName) as teachername from campus_classdetail cd,campus_classsection sec left outer join campus_timetable_student st on sec.classsection_id_int=st.sectionid and st.accyearid = ? join campus_classteacher t on sec.classsection_id_int=t.SectionCode join campus_teachers ct on t.TeacherCode=ct.TeacherID  where cd.classdetail_id_int=? and sec.classsection_id_int=? and cd.classdetail_id_int=sec.classdetail_id_int order by length(cd.classdetail_id_int),cd.classdetail_id_int,length(sec.classsection_id_int),sec.classsection_id_int";
	public static final String GET_TEACHERNAME_BY_CLASS_SECTION = "select concat(ct.FirstName,' ',ct.LastName) as teachername from campus_classdetail cd,campus_classsection sec left outer join campus_timetable_student st on sec.classsection_id_int=st.sectionid join campus_classteacher t on sec.classsection_id_int=t.SectionCode join campus_teachers ct on t.TeacherCode=ct.TeacherID where cd.classdetail_id_int=sec.classdetail_id_int and t.ClassCode=? and SectionCode=?";
	
	public static final String TIMETABLE_DETAILS_MODIFY = "update campus_timetable_student set updated_by=?,updated_time=? where timetable_id=?";
}
