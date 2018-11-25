
 SET NAMES utf8 ;

SET SQL_MODE='';

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 ;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' ;
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 ;
SET global event_scheduler = 1;

/* Event structure for event `e_daily` */

DELIMITER //

CREATE EVENT `e_daily` ON SCHEDULE EVERY 1 MINUTE STARTS '2017-12-21 16:33:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Update TimeTable for Every Day' DO BEGIN
       DELETE FROM today_timetable;
        INSERT INTO today_timetable SELECT DISTINCT vd.timetableid id,cts.classId,cts.sectionid,cts.accyearid,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9,ctt.period1 tperiod1,ctt.period2 tperiod2,ctt.period3 tperiod3,ctt.period4 tperiod4,ctt.period5 tperiod5,ctt.period6 tperiod6,ctt.period7 tperiod7,ctt.period8 tperiod8,ctt.period9 tperiod9,d.dayname today,CURDATE() date,now() updatedTime FROM campus_timetable_studentdetails vd JOIN campus_timetable_day d ON d.daycode=vd.daycode LEFT JOIN `campus_timetable_teacherdetails` ctt ON ctt.teachertimetable_id=vd.timetableid AND ctt.daycode=vd.daycode JOIN campus_timetable_student cts ON vd.timetableid=cts.timetable_id WHERE d.dayname IN(SELECT DAYNAME(CURDATE()));
      END //
DELIMITER ;

/* Procedure structure for procedure `approveAbsentSMS` */

DELIMITER //

 CREATE PROCEDURE `approveAbsentSMS`(lo_code VARCHAR(20),lo_date VARCHAR(20))
BEGIN
DECLARE student INT(1) ;
DECLARE abs_code VARCHAR(20);
SELECT IsStudent,ABSENT_CODE INTO student,abs_code FROM sms_absent_details WHERE ABSENT_CODE=lo_code AND ABSENT_DATE=lo_date ;
	IF student = 1 THEN
SELECT  stu.student_admissionno_var,det.TemplateCode,det.CREATED_BY,stu.student_id_int,cscd.classsection_id_int,det.ABSENT_DATE smsDate,det.ABSENT_CONTENT AS smsContent,re.relationship,
(CASE WHEN re.relationship='father' THEN par.mobileno WHEN re.relationship='mother' THEN par.student_mothermobileno_var 
WHEN re.relationship='guardian' THEN par.student_gardian_mobileno ELSE '1' END)AS student_contact_mobileno,par.ParentID 
FROM campus_parents par 
JOIN campus_parentchildrelation re ON re.parentid = par.ParentID
JOIN campus_student stu ON stu.student_id_int = re.stu_addmissionNo 
JOIN sms_absent_student hol ON hol.STUDENT_ADM_NO = stu.student_id_int 
JOIN sms_absent_details det ON det.ABSENT_CODE = hol.ABSENT_CODE
JOIN campus_student_classdetails cscd ON cscd.student_id_int = stu.student_id_int AND cscd.fms_acadamicyear_id_int = det.AccyearId
WHERE det.ABSENT_CODE=abs_code;	
END IF;
    END //
DELIMITER ;

/* Procedure structure for procedure `approveHolidaySMS` */

DELIMITER //

 CREATE PROCEDURE `approveHolidaySMS`(lo_code VARCHAR(20),lo_date VARCHAR(20))
BEGIN
	
	DECLARE section INT(1) ;
	
SELECT `isSection` INTO section FROM sms_suddenholidays_details WHERE SUDDENHOLIDAYS_CODE=lo_code AND SUDDENHOLIDAYS_DATE=lo_date;
	IF section = 1 THEN
SELECT DISTINCT stu.student_admissionno_var,det.CREATED_BY,det.TemplateCode,stu.student_id_int,cscd.classsection_id_int,det.SUDDENHOLIDAYS_DATE smsDate,det.SUDDENHOLIDAYS_CONTENT AS smsContent,re.relationship,
(CASE WHEN re.relationship='father' THEN par.mobileno WHEN re.relationship='mother' THEN par.student_mothermobileno_var WHEN re.relationship='guardian' THEN par.student_gardian_mobileno ELSE '1' END) AS student_contact_mobileno,
par.ParentID FROM campus_parents par
JOIN campus_parentchildrelation re ON re.parentid = par.ParentID
JOIN campus_student stu ON stu.student_id_int = re.stu_addmissionNo 
JOIN campus_student_classdetails cscd ON cscd.student_id_int = stu.student_id_int 
JOIN sms_suddenholidays_section hol ON hol.SECTION_CODE = cscd.classsection_id_int and cscd.classdetail_id_int = hol.CLASS_CODE
AND cscd.fms_acadamicyear_id_int = hol.ACC_YEAR
JOIN sms_suddenholidays_details det ON det.SUDDENHOLIDAYS_CODE = hol.SUDDENHOLIDAYS_CODE
where det.SUDDENHOLIDAYS_CODE=lo_code;

	END IF;
    END //
DELIMITER ;

/* Procedure structure for procedure `approveMeetingEventSMS` */

DELIMITER //

 CREATE PROCEDURE `approveMeetingEventSMS`(lo_code VARCHAR(20),lo_date VARCHAR(20))
BEGIN
                DECLARE stuid VARCHAR(20);
	SELECT `studentname` INTO stuid FROM `sms_meeting` WHERE `meetingid`=lo_code AND `meetingdate`=lo_date;
	
	SELECT stu.student_admissionno_var,sm.`createuser`,stu.student_id_int,cscd.classsection_id_int,sm.`meetingdate` AS smsDate,sm.`description` 
	AS smsContent,sm.`TemplateCode`,re.relationship,
	(CASE WHEN re.relationship='father' THEN par.mobileno WHEN re.relationship='mother' THEN par.student_mothermobileno_var 
	WHEN re.relationship='guardian' THEN par.student_gardian_mobileno ELSE '1' END)AS student_contact_mobileno,par.ParentID 
	FROM campus_parents par 
	JOIN campus_parentchildrelation re ON re.parentid = par.ParentID
	JOIN campus_student stu ON stu.student_id_int = re.stu_addmissionNo 
	JOIN `sms_meeting` sm ON sm.`studentname` = stu.student_id_int 
	JOIN campus_student_classdetails cscd ON cscd.student_id_int = stu.student_id_int AND cscd.fms_acadamicyear_id_int = sm.`accyear`
	WHERE sm.`meetingid`= lo_code AND stu.student_id_int  = stuid;

	END //
DELIMITER ;

/* Procedure structure for procedure `approveUniformSMS` */

DELIMITER //

 CREATE PROCEDURE `approveUniformSMS`(lo_code VARCHAR(20),lo_date VARCHAR(20))
BEGIN
	DECLARE section INT(1);
	DECLARE student INT(1);
	DECLARE uni_code VARCHAR(20);
SELECT IsSection,IsStudent,UNIFORM_CODE INTO section,student,uni_code FROM sms_uniform_details WHERE UNIFORM_CODE=lo_code 
AND UNIFORM_DATE=lo_date AND SMS_STATUS='APPROVED';
	IF section = 1 THEN
SELECT sd.UNIFORM_DATE smsDate,sd.MODIFIED_TIME,sd.UNIFORM_CONTENT smsContent,sd.CREATED_BY,sd.MODIFIED_BY,sd.TemplateCode,cs.student_admissionno_var,
cs.student_contact_mobileno  FROM campus_student cs,sms_uniform_details sd WHERE sd.UNIFORM_CODE=uni_code AND  
cs.classsection_id_int=(SELECT SECTION_CODE FROM sms_uniform_section WHERE UNIFORM_CODE=uni_code);
	END IF;
	IF student = 1 THEN
SELECT sd.UNIFORM_DATE smsDate,sd.MODIFIED_TIME,sd.UNIFORM_CONTENT smsContent,sd.CREATED_BY,sd.MODIFIED_BY,sd.TemplateCode,
cs.student_admissionno_var,cs.student_contact_mobileno FROM campus_student cs,sms_uniform_details sd WHERE sd.UNIFORM_CODE=uni_code 
AND  cs.student_admissionno_var IN (SELECT STUDENT_ADM_NO FROM sms_uniform_student WHERE UNIFORM_CODE=uni_code);
	END IF;
    END //
DELIMITER ;

/* Procedure structure for procedure `getAccYear` */

DELIMITER //

 CREATE PROCEDURE `getAccYear`()
BEGIN
	DECLARE lo_year VARCHAR(10);
	  DECLARE lo_month INT;
          DECLARE lo_year_next VARCHAR(10);
          DECLARE lo_year_before VARCHAR(10);
          DECLARE lo_accYear VARCHAR(20);
          SET lo_year = YEAR(NOW());
          
           SET  lo_month = MONTH(NOW());
           SET lo_year_next = YEAR(DATE_ADD(NOW( ), INTERVAL 1 YEAR));
           SET lo_year_before = YEAR(DATE_ADD(NOW( ), INTERVAL -1 YEAR));
           
           
            IF lo_month > 3 THEN
             SET lo_accYear = CONCAT(lo_year,"-", SUBSTRING(lo_year_next, -2));
            ELSE
              SET lo_accYear = CONCAT(lo_year_before,"-", SUBSTRING(lo_year, -2) );
            END IF;
              
         
            
          SELECT acadamic_id , acadamic_year FROM campus_acadamicyear WHERE acadamic_year = lo_accYear;
    END //
DELIMITER ;

/* Procedure structure for procedure `getAllAccYear` */

DELIMITER //

 CREATE PROCEDURE `getAllAccYear`()
BEGIN
     
      select acadamic_id ACCYEARID,acadamic_year from campus_acadamicyear;
    END //
DELIMITER ;

/* Procedure structure for procedure `getAllClassName` */

DELIMITER //

 CREATE PROCEDURE `getAllClassName`()
BEGIN
     
      SELECT distinct `campus_classdetail` FROM `campus_classdetail`;
    END //
DELIMITER ;

/* Procedure structure for procedure `getAllocatedList` */

DELIMITER //

 CREATE PROCEDURE `getAllocatedList`(eventId varchar(50))
BEGIN
declare counts int(10);
SELECT count(*) INTO counts  FROM campus_event_stage_allocation;
IF 
counts > 0 
THEN
DROP TEMPORARY TABLE IF EXISTS temp;
CREATE TEMPORARY TABLE temp( val VARCHAR(50) );
SET @sql = CONCAT("insert into temp (val) values ('", REPLACE(( SELECT GROUP_CONCAT(DISTINCT ParticipantsName) AS DATA FROM campus_event_stage_allocation), ",", "'),('"),"');");
PREPARE stmt1 FROM @sql;EXECUTE stmt1;
SELECT reg.captain_admissionNo,CONCAT(cs.student_fname_var,cs.student_lname_var) AS NAME FROM campus_event_studentregistration reg LEFT JOIN campus_student cs ON cs.student_admissionno_var=reg.captain_admissionNo  WHERE reg.event_id=eventId AND captain_admissionNo  NOT IN (SELECT DISTINCT(val) FROM temp);
ELSE
 SELECT reg.captain_admissionNo,CONCAT(cs.student_fname_var,cs.student_lname_var) AS NAME FROM campus_event_studentregistration reg LEFT JOIN campus_student cs ON cs.student_admissionno_var=reg.captain_admissionNo  WHERE reg.event_id=eventId AND captain_admissionNo  NOT IN (SELECT ParticipantsName  FROM campus_event_stage_allocation);   
END IF;
END //
DELIMITER ;

/* Procedure structure for procedure `getAllSchoolList` */

DELIMITER //

 CREATE PROCEDURE `getAllSchoolList`()
BEGIN
    select Location_Id,Location_Name from campus_location
where Location_Id LIKE '%%';
    END //
DELIMITER ;

/* Procedure structure for procedure `getAllTeacher` */

DELIMITER //

 CREATE PROCEDURE `getAllTeacher`()
BEGIN
     
      select TeacherID,Abbreviative_Id,FirstName,LastName,registerId from campus_teachers where teachingType='TEACHING';
    END //
DELIMITER ;

/* Procedure structure for procedure `getCurrentAccYear` */

DELIMITER //

 CREATE PROCEDURE `getCurrentAccYear`()
BEGIN
    select acadamic_id ACCYEARID,acadamic_year from campus_acadamicyear
where CURDATE() >=startDate and CURDATE() <=endDate;
    END //
DELIMITER ;

/* Procedure structure for procedure `getCurrentAccYearStartDateAndEndDate` */

DELIMITER //

 CREATE PROCEDURE `getCurrentAccYearStartDateAndEndDate`()
BEGIN
SELECT acadamic_id ACCYEARID,startDate,endDate,acadamic_year FROM campus_acadamicyear
WHERE CURDATE() >=startDate AND CURDATE() <=endDate;
    END //
DELIMITER ;

/* Procedure structure for procedure `getCustSchoolInfo` */

DELIMITER //

 CREATE PROCEDURE `getCustSchoolInfo`(custID VARCHAR(20))
BEGIN
		SELECT `Location_Id`,`Location_Name`,`Location_Address`,`Location_Phone`,`emailId`,`website`,`board`,`affilation`,`schoolcode`,`schoollogo`,
		 case when `boardlogo`is null then '-' when `boardlogo`="" then '-' else `boardlogo` end `boardlogo`, `Description` from `campus_location` WHERE Cust_Id=custID AND isActive='Y';
	END //
DELIMITER ;

/* Procedure structure for procedure `getHouse` */

DELIMITER //

 CREATE PROCEDURE `getHouse`(location VARCHAR(20),acy varchar(20))
BEGIN
		SELECT *  FROM  campus_house_settings WHERE loc_id=location and accyear_id = acy AND `status` = 'active';
    END //
DELIMITER ;

/* Procedure structure for procedure `getHouseNameByCategory` */

DELIMITER //

 CREATE PROCEDURE `getHouseNameByCategory`(catId VARCHAR(50))
BEGIN
DROP TEMPORARY TABLE IF EXISTS temp;
CREATE TEMPORARY TABLE temp( val VARCHAR(50) );
SET @sql = CONCAT("insert into temp (val) values ('", REPLACE(( SELECT GROUP_CONCAT(DISTINCT classList) AS DATA FROM campus_event_category where categoryId=catId), ",", "'),('"),"');");
PREPARE stmt1 FROM @sql;EXECUTE stmt1;
SELECT `student_id_int`,`student_house` FROM `campus_student_classdetails` WHERE `classdetail_id_int` IN (SELECT DISTINCT(val) FROM temp);
END //
DELIMITER ;

/* Procedure structure for procedure `getSchoolName` */

DELIMITER //

 CREATE PROCEDURE `getSchoolName`(locationId varchar(20))
BEGIN
    SELECT Location_Id,Location_Name FROM campus_location
WHERE Location_Id=locationId;
    END //
DELIMITER ;

/* Procedure structure for procedure `getTodayTeacher` */

DELIMITER //

 CREATE PROCEDURE `getTodayTeacher`(period varchar(20), Id varchar(20))
BEGIN
     
       SELECT cts.TeacherID,Abbreviative_Id,FirstName,LastName,registerId FROM `campus_teacher_attendence` cts JOIN campus_teachers ct  ON ct.TeacherID=cts.TeacherID WHERE cts.AttendenceDate=CURDATE() AND cts.AttendenceStatus='Present' AND ct.teachingType='TEACHING' and cts.TeacherID Not in(select period from today_timetable where id!=Id); 
    END //
DELIMITER ;

/* Procedure structure for procedure `todaysTimetable` */

DELIMITER //

 CREATE PROCEDURE `todaysTimetable`()
BEGIN
      DELETE FROM today_timetable;
        INSERT INTO today_timetable SELECT DISTINCT vd.timetableid id,cts.classId,cts.sectionid,cts.accyearid,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9,ctt.period1 tperiod1,ctt.period2 tperiod2,ctt.period3 tperiod3,ctt.period4 tperiod4,ctt.period5 tperiod5,ctt.period6 tperiod6,ctt.period7 tperiod7,ctt.period8 tperiod8,ctt.period9 tperiod9,d.dayname today,CURDATE() DATE,NOW() updatedTime FROM campus_timetable_studentdetails vd JOIN campus_timetable_day d ON d.daycode=vd.daycode LEFT JOIN `campus_timetable_teacherdetails` ctt ON ctt.teachertimetable_id=vd.timetableid AND ctt.daycode=vd.daycode JOIN campus_timetable_student cts ON vd.timetableid=cts.timetable_id WHERE d.dayname IN(SELECT DAYNAME(CURDATE()));
      END //
DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE ;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS ;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
SET SQL_NOTES=@OLD_SQL_NOTES ;
