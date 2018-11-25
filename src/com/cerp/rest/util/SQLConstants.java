package com.cerp.rest.util;

public class SQLConstants {
	
	//Related to customers
	public static final String INSERT_CUSTOMER = "INSERT INTO campus_customer_details(customerID,cust_fname,cust_lname,cust_phone_no,sub_type,sub_start_date,cust_email,cust_address,license_key,lic_expdate,cust_refno,urlname,schoolname,no_of_users,cust_domain,no_of_students,isActive) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y')";
	public static final String INSERT_USER = "INSERT INTO campus_user (usercode,employeecode,username,PASSWORD,role,TYPE,cust_id,app_userid,emailId,mobileno,createuser,createdate) VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
	public static final String VERIFY_CUSTOMER = "select count(*),customerID from campus_customer_details where cust_refno = ? and BINARY urlname = ?";
	public static final String UPDATE_CUSTOMER = "UPDATE campus_customer_details SET license_key=?,lic_expdate = ?,sub_type = ?,no_of_users=? WHERE cust_refno = ?";
	public static final String INSERT_CUSTOMER_DBDETAILS = "INSERT INTO campus_customer_dbdetails (plesk_dbid,custid,dbname,dbusername,dbpwd,dbhost,isActive,STATUS,pleskdb_userid,sub_id,createdtime) VALUES (?,?,?,?,?,?,?,?,?,?,now())";
	public static final String GET_CUSTOMER_DETAILS = "SELECT `cust_uname`,`cust_pwd`,`subdoamin_name`,cust_email FROM `campus_customer_details` JOIN `campus_customer_subdomain` ON `customerID` = `cust_id` WHERE `customerID` = ?";
	public static final String VALIDATE_CUSTOMER = "SELECT COUNT(*),sub_type,cust_refno,customerID,license_key from `campus_customer_details` WHERE BINARY `cust_uname` = ? AND BINARY `cust_pwd` = ? and isActive = 'Y'";

	public static final String CHECK_LIC_VALIDATY = "SELECT COUNT(*),p.no_of_students,c.`cust_refno`, CONCAT(c.`cust_fname`,' ',c.`cust_lname`)AS cust,p.`sub_type`,p.`sub_start_date`,p.`no_of_users`,p.`no_of_schools`,p.`license_key`,p.`lic_expdate`,p.cust_domain,p.urlname FROM `campus_customer_details` c JOIN `campus_product_details` p ON c.`customerID` = p.`cust_id` WHERE CURDATE()<=lic_expdate AND customerID = ? AND p.`sub_id` = ?";
	public static final String GET_DB_DETAILS = "SELECT db.dbname,db.dbusername,db.dbpwd,db.dbhost FROM `campus_customer_dbdetails` db JOIN `campus_product_details` p ON p.`sub_id` = db.`sub_id` JOIN `campus_customer_details` cd ON `customerID` = db.`custid` WHERE db.custid =? AND db.`sub_id` =? AND db.isActive = 'Y'";
	public static final String CUSTOMER_DETAILS = "SELECT * FROM `campus_customer_details` WHERE `customerID` = ?";
	public static final String CHECK_DOMAIN_NAME = "SELECT COUNT(*) FROM `campus_customer_details` WHERE BINARY `urlname` = ? AND BINARY `cust_uname` = ? AND `cust_pwd` = ?";
	public static final String INSERT_USER_DETAILS = "INSERT INTO `campus_cust_userdetails`(`user_id`,`cust_id`,`uname`,`upwd`,`email`,`mobile_no`,created_time) VALUES (?,?,?,?,?,?,now())";
	public static final String INSERT_APPUSERS = "INSERT INTO `campus_cust_userdetails`(`user_id`,`cust_id`,`uname`,`upwd`,`email`,`mobile_no`,created_time) VALUES (?,?,?,?,?,?,now())";;
	
	public static final String VALIDATE_APP_USER = "SELECT COUNT(*),`user_id`,`cust_id`,sub_id FROM `campus_cust_userdetails` WHERE BINARY `uname` = ? AND BINARY `upwd` = ? and isActive ='Y'";

	public static final String INSERT_CUSTUSER_MAPPING = "INSERT INTO campus_custuser_mapping(`custid`,`userid`) VALUES (?,?)";
	public static final String MODIFY_USER_DETAILS = "UPDATE `campus_cust_userdetails` SET `uname` = ?,`upwd`=?,`email`=?,`mobile_no`=? WHERE `user_id` = ?";
	public static final String BLOCK_USER_DETAILS = "UPDATE `campus_cust_userdetails` SET `isActive` = ? WHERE `user_id` = ?";
	public static final String VERIFY_USER_NAME = "SELECT COUNT(*) FROM `campus_cust_userdetails` WHERE BINARY `uname` = ?";
	
	
	//Related to DB creation dynamically in plesk
	public static final String GET_DOMAIN_DETAILS = "SELECT * FROM campus_domain_details";
	public static final String INSERT_SUBDOMAIN_DETAILS = "INSERT INTO campus_customer_subdomain (cust_id,subdomain_id,subdoamin_name,physical_uri,isActive,STATUS,sub_id,created_time) VALUES(?,?,?,?,?,?,?,now())";
	public static final String INSERT_REG_MAIL = "INSERT INTO `campus_customer_regmail` (`cust_id`,`reg_confirm_status`) VALUES(?,?)";
	public static final String INSERT_ACTIVATION_MAIL = "INSERT INTO `campus_customer_activationmail` (`custid`,`mail_status`,sub_id,sent_time) VALUES(?,?,?,now())";
	public static final String INSERT_TABLECREATE_STATUS = "INSERT INTO `campus_customer_tablestatus` (`cust_id`,`creation_status`) VALUES(?,?)";
	public static final String INSERT_RENEWAL_MAIL = "INSERT INTO `campus_customer_renewalmail` (`cust_id`,`mail_status`) VALUES(?,?)";
	
	
	public static final String CHECK_SUBDOMAIN_DETAILS = "SELECT `cust_refno`,CASE WHEN  lic_expdate >= CURDATE() THEN 'active' ELSE 'inactive' END licstatus FROM `campus_customer_details` cd JOIN `campus_customer_subdomain` cs ON cd.`customerID` = cs.`cust_id` WHERE `subdomain_id` = ?";
	public static final String CHECK_CUST_LIC_VALIDATY = "select count(*),cust_domain FROM `campus_customer_details` WHERE CURDATE()<=lic_expdate AND customerID = ?";
	
	//multiple product single customer
	public static final String INSERT_CUSTOMERS = "INSERT INTO `campus_customer_details` (`cust_fname`,`cust_lname`,`cust_phone_no`,`cust_email`,`cust_address`,`cust_refno`,`isActive`)VALUES (?,?,?,?,?,?,'Y')";
	public static final String INSERT_CUSTOMER_PRODUCT_DETAILS = "INSERT INTO `campus_product_details` (`cust_id`,`sub_type`,`sub_start_date`,`cust_domain`,`no_of_users`,`urlname`,`schoolname`,`no_of_students`,`license_key`,`lic_expdate`)VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	//service upgradation
	public static final String UPDATE_LICENSE_PERIOD = "UPDATE `campus_customer_details` SET `license_key` =?,`lic_expdate`=?,`no_of_users`=?,`no_of_students`=?,`sub_type`=?,`sub_start_date`=? WHERE `customerID` = ?";


	// reports : Transport Fee details
	
	public static final String TRANSPORT_FEE_LIST_ALL = "SELECT DISTINCT ft.termid,st.student_admissionno_var,st.student_id_int,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname, cd.classdetails_name_var,fc.amount_paid,cs.classsection_name_var,stage.stage_name,fc.is_paid FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.locationId = csc.locationId AND (cd.isActive='Y') JOIN  campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.locationId = csc.locationId AND (cd.isActive='Y') JOIN campus_student_transportdetails tra ON tra.student_id_int = csc.student_id_int JOIN campus_fee_stage stage ON stage.stage_id = tra.StageId  JOIN campus_fee_transport_termdetails ft ON ft.locationId = csc.locationId AND ft.accyear = csc.fms_acadamicyear_id_int LEFT JOIN campus_tranport_fee_collection_details fc ON fc.admissionNo = csc.student_id_int AND fc.termcode = ft.termid WHERE `student_status_var`='active' AND  tra.isTransport='Y' AND  csc.fms_acadamicyear_id_int =? AND csc.classdetail_id_int LIKE ? AND csc.classsection_id_int LIKE ? AND csc.locationId=? AND ft.termid LIKE ?  ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student";
	public static final String TRANSPORT_FEE_LIST_PAID="SELECT DISTINCT ft.termid,st.student_admissionno_var,st.student_id_int,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname, cd.classdetails_name_var,fc.amount_paid,cs.classsection_name_var,fc.is_paid,fc.`totalamount` FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.locationId = csc.locationId  AND (cd.`isActive`='Y') JOIN campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.locationId = csc.locationId AND (cs.`isActive`='Y') JOIN campus_tranport_fee_collection_details fc ON fc.admissionNo = csc.student_id_int JOIN campus_fee_transport_termdetails ft ON ft.`termid` = fc.`termcode` AND fc.`accYear` = ? WHERE st.`student_status_var`='active' and  csc.fms_acadamicyear_id_int =? AND csc.classdetail_id_int LIKE ? AND csc.classsection_id_int LIKE ? AND csc.locationId=? AND ft.termid LIKE ? ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student";
	public static final String TRANSPORT_FEE_LIST_UNPAID="SELECT DISTINCT st.student_id_int,ft.termid,st.student_admissionno_var,st.student_id_int,CONCAT(st.student_fname_var,' ',st.student_lname_var) AS student,ft.termname,cd.classdetails_name_var,cs.classsection_name_var,stage.stage_name,sa.`stageamount`FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int = csc.student_id_int JOIN  campus_classdetail cd ON cd.classdetail_id_int = csc.classdetail_id_int AND cd.isActive='Y' and cd.locationId = csc.locationId JOIN campus_classsection cs ON cs.classsection_id_int = csc.classsection_id_int AND cs.isActive='Y' and cs.locationId = csc.locationId JOIN campus_student_transportdetails tra ON tra.student_id_int = csc.student_id_int JOIN campus_fee_stage stage ON stage.stage_id = tra.StageId JOIN `campus_fee_stage_amount` sa ON sa.`stageId` = stage.`stage_id` AND sa.`accyearId` = ? AND sa.`locationId` =  ? JOIN campus_fee_transport_termdetails ft ON ft.locationId = csc.locationId AND ft.accyear = csc.fms_acadamicyear_id_int WHERE st.`student_status_var`='active' and csc.student_id_int NOT IN (SELECT `admissionNo` FROM campus_tranport_fee_collection_details where termid LIKE ?)  AND tra.isTransport='Y' AND csc.fms_acadamicyear_id_int = ?  AND csc.classdetail_id_int LIKE ? AND csc.classsection_id_int LIKE ? AND  csc.locationId = ? AND ft.termid LIKE ? ORDER BY startdate,LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,cs.classsection_name_var,student";
	
	
	
	public static final String VALIDATE_USER_DOMAIN = "SELECT COUNT(*),`cust_id`,`sub_id` FROM `campus_product_details`p WHERE `cust_domain` = ? and p.isActive = 'Y'";


}
