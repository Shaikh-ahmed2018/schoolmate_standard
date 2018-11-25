package com.centris.campus.util;

public class AddSyllabusUtilConstants {
	
public static final String GET_SUB_SYLABUS = "SELECT CASE WHEN sy.`syllabus_id` IS NULL THEN '-' ELSE sy.`syllabus_id` END `syllabus_id`,`subjectID` ,`subjectName`,`classid`,`specialization`,s.`locationId`,Specialization_name,`classdetails_name_var`,CASE WHEN `syllabus_url` IS NULL THEN '-' ELSE syllabus_url END syllabus_url,l.`Location_Name` FROM `campus_subject` s JOIN `campus_location` l ON s.`locationId` = l.`Location_Id` AND s.`isActive` = 'Y' JOIN `campus_classdetail` cd ON cd.`classdetail_id_int` = s.`classid` AND cd.`isActive` = 'Y' AND cd.`locationId` = s.`locationId` LEFT JOIN `campus_class_specialization` sp ON sp.`Specialization_Id` = s.`specialization` AND sp.`isActive` = 'Y' LEFT JOIN `campus_syllabus_details` sy ON sy.`sub_lab_id` = s.`subjectID` AND `accy_id` = ? AND `isApplicable` = ?  WHERE s.locationId = ? AND s.classid LIKE ? AND specialization LIKE  ?  ORDER BY CAST(SUBSTRING(classdetail_id_int,4,LENGTH(classdetail_id_int)-3)AS SIGNED),subjectName,`Specialization_name`";
public static final String GET_LAB_SYLABUS = "SELECT DISTINCT `Class_Name`,classdetails_name_var,Lab_Name,lab_id,Location_Name , CASE WHEN sy.`syllabus_id` IS NULL THEN '-' ELSE sy.`syllabus_id` END `syllabus_id` ,cs.`subjectName`,`classid`,s.`Specialization`,s.`School_Name`,Specialization_name,`classdetails_name_var`,CASE WHEN `syllabus_url` IS NULL THEN '-' ELSE syllabus_url END syllabus_url FROM `laboratory_details` s JOIN `campus_subject` cs  ON cs.`subjectID` = s.`Subject` AND cs.`isActive` = 'Y' JOIN `campus_location` loc ON loc.`Location_Id` = s.`School_Name` JOIN `campus_classdetail` cd ON cd.`classdetail_id_int` = s.`Class_Name` AND cd.`isActive` = 'Y' AND cd.`locationId` = s.`School_Name` LEFT JOIN `campus_class_specialization` sp ON sp.`Specialization_Id` = s.`Specialization` AND sp.`isActive` = 'Y'  LEFT JOIN `campus_syllabus_details` sy ON sy.`sub_lab_id` = s.`lab_id` AND `accy_id` = ?  AND `isApplicable` = ?  WHERE s.`status` = 'Y' AND s.`School_Name` = ? AND s.`Class_Name` LIKE ? AND s.`Specialization` LIKE  ?   ORDER BY CAST(SUBSTRING(classdetail_id_int,4,LENGTH(classdetail_id_int)-3)AS SIGNED),subjectName,`Specialization_name`";

}
