

SET NAMES utf8;

SET SQL_MODE='';

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0;

CREATE TABLE `birthday_sms` (
  `bdaycode` varchar(180) DEFAULT NULL,
  `bdaydate` varchar(180) DEFAULT NULL,
  `class_deptCode` varchar(180) DEFAULT NULL,
  `section_desgnCode` varchar(180) DEFAULT NULL,
  `student_teaCode` varchar(180) DEFAULT NULL,
  `mobileNo` varchar(180) DEFAULT NULL,
  `smscontent` varchar(1800) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_acadamicyear` */

CREATE TABLE `campus_acadamicyear` (
  `acadamic_id` varchar(11) NOT NULL,
  `acadamic_year` varchar(30) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `createuser` varchar(20) NOT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`acadamic_id`),
  KEY `academic_userId` (`createuser`),
  KEY `academic_modified_Id` (`modifyuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_accyearplan` */

CREATE TABLE `campus_accyearplan` (
  `AccYearPlanCode` varchar(20) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `locationId` varchar(20) NOT NULL,
  `Date` date NOT NULL,
  `WeekDay` varchar(20) NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  `InvitationFile` varchar(500) DEFAULT NULL,
  `venue_details` varchar(100) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `AcadamicYear` varchar(20) NOT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(20) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`AccYearPlanCode`),
  KEY `accyplan_locId` (`locationId`),
  KEY `accyplan_accId` (`AcadamicYear`),
  CONSTRAINT `accyplan_accId` FOREIGN KEY (`AcadamicYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `accyplan_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_admin` */

CREATE TABLE `campus_admin` (
  `AdminID` varchar(20) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Qualification` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT '',
  `mobileno` varchar(11) DEFAULT NULL,
  `gender` varchar(7) DEFAULT '',
  `email` varchar(40) DEFAULT NULL,
  `createdby` varchar(50) DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedby` varchar(50) DEFAULT NULL,
  `modifiedate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `adminstatus` varchar(10) DEFAULT 'deactive',
  `UserName` varchar(40) DEFAULT '',
  `password` varchar(51) DEFAULT NULL,
  `Role` varchar(20) DEFAULT NULL,
  `lastLogin` timestamp NULL DEFAULT NULL,
  `UserType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_assignment` */

CREATE TABLE `campus_assignment` (
  `AssignmentCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `StreamID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `ClassID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `SectionID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `AssignmentDate` date DEFAULT NULL,
  `CompletionDate` date DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `SubjectID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `SpecializationId` varchar(20) DEFAULT '-',
  `MaxMarks` double DEFAULT NULL,
  `Status` varchar(20) DEFAULT 'Assigned',
  `AcadamicID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`AssignmentCode`),
  KEY `assign_locId` (`locationId`),
  KEY `assign_accId` (`AcadamicID`),
  KEY `assign_classId` (`ClassID`),
  KEY `assign_secId` (`SectionID`),
  KEY `assign_streamId` (`StreamID`),
  KEY `assign_subId` (`SubjectID`),
  CONSTRAINT `assign_accId` FOREIGN KEY (`AcadamicID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assign_classId` FOREIGN KEY (`ClassID`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assign_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assign_secId` FOREIGN KEY (`SectionID`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assign_streamId` FOREIGN KEY (`StreamID`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assign_subId` FOREIGN KEY (`SubjectID`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_assignmentdetails` */

CREATE TABLE `campus_assignmentdetails` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `Acc_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `Loc_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `AssignmentCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `student_id` varchar(60) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `ActualCompletionDate` date DEFAULT NULL,
  `AcquiredMarks` double DEFAULT NULL,
  `MaxMarks` double DEFAULT NULL,
  `SpecializationId` varchar(20) DEFAULT '-',
  `Remarks` varchar(250) DEFAULT NULL,
  `Status` varchar(20) DEFAULT 'assigned',
  `CreatedBy` varchar(20) NOT NULL,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `assigndetails_accId` (`Acc_id`),
  KEY `assigndetails_locId` (`Loc_id`),
  KEY `assigndetails_assignCode` (`AssignmentCode`),
  KEY `assigndetails_stuId` (`student_id`),
  CONSTRAINT `assigndetails_accId` FOREIGN KEY (`Acc_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assigndetails_assignCode` FOREIGN KEY (`AssignmentCode`) REFERENCES `campus_assignment` (`AssignmentCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assigndetails_locId` FOREIGN KEY (`Loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assigndetails_stuId` FOREIGN KEY (`student_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_attendence` */

CREATE TABLE `campus_attendence` (
  `attendencedate` date NOT NULL,
  `addmissionno` varchar(60) CHARACTER SET utf8 NOT NULL,
  `teacherId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `specializationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `accId` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `attendence` varchar(50) DEFAULT NULL,
  `period1` varchar(20) DEFAULT NULL,
  `period2` varchar(20) DEFAULT NULL,
  `period3` varchar(20) DEFAULT NULL,
  `period4` varchar(20) DEFAULT NULL,
  `period5` varchar(20) DEFAULT NULL,
  `period6` varchar(20) DEFAULT NULL,
  `period7` varchar(20) DEFAULT NULL,
  `period8` varchar(20) DEFAULT NULL,
  `period9` varchar(20) DEFAULT NULL,
  `createdBy` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `updatedBy` varchar(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`attendencedate`,`addmissionno`),
  KEY `atten_teaId` (`teacherId`),
  KEY `atten_locId` (`locationId`),
  KEY `atten_stuId` (`addmissionno`),
  CONSTRAINT `atten_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `atten_stuId` FOREIGN KEY (`addmissionno`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `atten_teaId` FOREIGN KEY (`teacherId`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_auto_generation` */

CREATE TABLE `campus_auto_generation` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `LocationId` varchar(20) DEFAULT NULL,
  `ConstantId` varchar(1) DEFAULT NULL,
  `IncrementValue` int(11) DEFAULT NULL,
  `Createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CreatedBy` varchar(10) DEFAULT NULL,
  `ModifyDate` date DEFAULT NULL,
  `ModifyBy` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  KEY `autogen_locId` (`LocationId`),
  CONSTRAINT `autogen_locId` FOREIGN KEY (`LocationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_careers` */

CREATE TABLE `campus_careers` (
  `jobcode` varchar(21) NOT NULL,
  `title` varchar(50) NOT NULL,
  `qualification` varchar(50) NOT NULL,
  `noofpositions` int(11) NOT NULL,
  `experience` varchar(20) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'active',
  `createdby` varchar(20) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedby` varchar(20) DEFAULT NULL,
  `updatedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`jobcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_caste` */

CREATE TABLE `campus_caste` (
  `religionId` varchar(20) NOT NULL,
  `casteId` varchar(20) NOT NULL,
  `caste` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(150) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdTime` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`casteId`),
  KEY `caste_religion` (`religionId`),
  CONSTRAINT `caste_religion` FOREIGN KEY (`religionId`) REFERENCES `campus_religion` (`religionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_caste_category` */

CREATE TABLE `campus_caste_category` (
  `castCatId` varchar(50) NOT NULL,
  `casteCategory` varchar(50) DEFAULT NULL,
  `religionId` varchar(50) DEFAULT NULL,
  `casteId` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(150) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdTime` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`castCatId`),
  KEY `castecat_reliId` (`religionId`),
  KEY `castecat_casteId` (`casteId`),
  CONSTRAINT `castecat_casteId` FOREIGN KEY (`casteId`) REFERENCES `campus_caste` (`casteId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `castecat_reliId` FOREIGN KEY (`religionId`) REFERENCES `campus_religion` (`religionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_category_master` */

CREATE TABLE `campus_category_master` (
  `Cat_Id` varchar(10) NOT NULL,
  `Category_Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cat_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_class_specialization` */

CREATE TABLE `campus_class_specialization` (
  `Specialization_Id` varchar(20) NOT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `ClassDetails_Id` varchar(20) DEFAULT NULL,
  `Stream_Id` varchar(20) DEFAULT NULL,
  `Specialization_name` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `CreateUser` varchar(20) DEFAULT NULL,
  `CreateDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ModifyUser` varchar(20) DEFAULT NULL,
  `ModifyDate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Specialization_Id`),
  KEY `spec_locId` (`locationId`),
  KEY `spec_streamId` (`Stream_Id`),
  KEY `spec_classId` (`ClassDetails_Id`),
  CONSTRAINT `spec_classId` FOREIGN KEY (`ClassDetails_Id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `spec_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `spec_streamId` FOREIGN KEY (`Stream_Id`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_classdetail` */

CREATE TABLE `campus_classdetail` (
  `classdetail_id_int` varchar(20) NOT NULL,
  `locationId` varchar(20) NOT NULL,
  `classstream_id_int` varchar(20) DEFAULT NULL,
  `classdetails_name_var` varchar(20) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`classdetail_id_int`,`locationId`),
  KEY `locationId` (`locationId`),
  KEY `classstream_id_int` (`classstream_id_int`),
  CONSTRAINT `class_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `class_streamId` FOREIGN KEY (`classstream_id_int`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_classsection` */

CREATE TABLE `campus_classsection` (
  `classsection_id_int` varchar(20) NOT NULL,
  `classdetail_id_int` varchar(20) DEFAULT NULL,
  `classsection_name_var` varchar(10) DEFAULT NULL,
  `classsection_strength_int` int(10) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `Remarks` varchar(250) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`classsection_id_int`),
  KEY `sec_locId` (`locationId`),
  KEY `sec_classId` (`classdetail_id_int`),
  CONSTRAINT `sec_classId` FOREIGN KEY (`classdetail_id_int`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sec_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_classstream` */

CREATE TABLE `campus_classstream` (
  `classstream_id_int` varchar(20) NOT NULL,
  `classstream_name_var` varchar(20) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `Remarks` varchar(250) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`classstream_id_int`),
  KEY `stream_locId` (`locationId`),
  CONSTRAINT `stream_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_classteacher` */

CREATE TABLE `campus_classteacher` (
  `CTCode` varchar(20) NOT NULL,
  `ClassCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `SectionCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `TeacherCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CreateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CTCode`),
  KEY `clstea_classId` (`ClassCode`),
  KEY `clstea_secId` (`SectionCode`),
  KEY `clastea_locId` (`locationId`),
  KEY `clastea_teaId` (`TeacherCode`),
  CONSTRAINT `clastea_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clastea_teaId` FOREIGN KEY (`TeacherCode`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clstea_classId` FOREIGN KEY (`ClassCode`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clstea_secId` FOREIGN KEY (`SectionCode`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_confidential_report` */

CREATE TABLE `campus_confidential_report` (
  `confidential_id` varchar(25) NOT NULL,
  `student_id` varchar(60) DEFAULT NULL,
  `academicyear_id` varchar(11) DEFAULT NULL,
  `location_id` varchar(20) DEFAULT NULL,
  `entrydate` date DEFAULT NULL,
  `comments` varchar(250) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `status` varchar(25) DEFAULT 'AVAILABLE',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`confidential_id`),
  KEY `confen_accyId` (`academicyear_id`),
  KEY `confen_locId` (`location_id`),
  KEY `confen_stuId` (`student_id`),
  CONSTRAINT `confen_accyId` FOREIGN KEY (`academicyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `confen_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `confen_stuId` FOREIGN KEY (`student_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_customer_details` */

CREATE TABLE `campus_customer_details` (
  `customerID` varchar(25) NOT NULL,
  `cust_refno` varchar(25) DEFAULT NULL,
  `cust_fname` varchar(35) DEFAULT NULL,
  `cust_lname` varchar(35) DEFAULT NULL,
  `cust_phone_no` longblob,
  `cust_tel_no` longblob,
  `cust_email` varchar(100) DEFAULT NULL,
  `cust_address` text,
  `sub_type` varchar(25) NOT NULL,
  `sub_start_date` date DEFAULT NULL,
  `cust_uname` varchar(25) DEFAULT NULL,
  `cust_pwd` varchar(15) DEFAULT NULL,
  `cust_domain` varchar(100) DEFAULT NULL,
  `no_of_users` int(11) DEFAULT NULL,
  `urlname` varchar(25) DEFAULT NULL,
  `schoolname` varchar(250) DEFAULT NULL,
  `no_of_schools` int(11) DEFAULT '1',
  `no_of_students` int(11) DEFAULT NULL,
  `license_key` varchar(25) NOT NULL,
  `lic_expdate` date NOT NULL,
  `isActive` char(1) DEFAULT 'N',
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_daily_smscount` */

CREATE TABLE `campus_daily_smscount` (
  `slno` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `total_sms_sent` int(11) DEFAULT NULL,
  `sucess_count` int(11) DEFAULT NULL,
  `failure_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`date`),
  KEY `slno` (`slno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_dairy` */

CREATE TABLE `campus_dairy` (
  `rowId` varchar(20) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `contentDate` date DEFAULT NULL,
  `createdBy` varchar(20) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_department` */

CREATE TABLE `campus_department` (
  `DEPT_ID` varchar(20) NOT NULL,
  `DEPT_NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `isActive` char(1) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `createdby` varchar(20) NOT NULL,
  `UpdatedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_designation` */

CREATE TABLE `campus_designation` (
  `DesignationCode` varchar(20) NOT NULL,
  `designationName` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isActive` char(1) DEFAULT NULL,
  `CreatedBy` varchar(20) NOT NULL,
  PRIMARY KEY (`DesignationCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_detailed_timetable` */

CREATE TABLE `campus_detailed_timetable` (
  `examtimetablecode` varchar(25) NOT NULL,
  `sub_id` varchar(25) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `starttime` varchar(25) DEFAULT NULL,
  `endtime` varchar(25) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `detailtimetable_subId` (`sub_id`),
  KEY `detailtimetable_ttId` (`examtimetablecode`),
  CONSTRAINT `detailtimetable_subId` FOREIGN KEY (`sub_id`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailtimetable_ttId` FOREIGN KEY (`examtimetablecode`) REFERENCES `campus_exam_timetable` (`timetable_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_booth_setting` */

CREATE TABLE `campus_election_booth_setting` (
  `boothNameId_int` varchar(30) NOT NULL,
  `boothName_var` varchar(30) DEFAULT NULL,
  `staffIncharge_id_int` varchar(20) DEFAULT NULL,
  `centralSystem` varchar(30) DEFAULT NULL,
  `centralSystemIp` varchar(30) DEFAULT NULL,
  `voterClass_id` varchar(50) DEFAULT NULL,
  `accyearID` varchar(11) DEFAULT NULL,
  `groupID` varchar(25) DEFAULT NULL,
  `electionSettingID` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`boothNameId_int`),
  KEY `ele_boot_staffid` (`staffIncharge_id_int`),
  KEY `ele_boot_accid` (`accyearID`),
  KEY `ele_boot_grpid` (`groupID`),
  KEY `ele_boot_eleSettingid` (`electionSettingID`),
  KEY `ele_boot_classId` (`voterClass_id`),
  CONSTRAINT `ele_boot_accid` FOREIGN KEY (`accyearID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_boot_classId` FOREIGN KEY (`voterClass_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_boot_eleSettingid` FOREIGN KEY (`electionSettingID`) REFERENCES `campus_election_election_setting` (`electionSettingID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_boot_grpid` FOREIGN KEY (`groupID`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_boot_staffid` FOREIGN KEY (`staffIncharge_id_int`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_category_setting` */

CREATE TABLE `campus_election_category_setting` (
  `electionCategoryId` varchar(30) NOT NULL,
  `electionCategory` varchar(30) DEFAULT NULL,
  `priority` int(10) DEFAULT NULL,
  `classId` varchar(20) DEFAULT NULL,
  `genderWise` varchar(30) DEFAULT NULL,
  `houseWise` varchar(30) DEFAULT NULL,
  `classWise` varchar(30) DEFAULT NULL,
  `nominationFor` varchar(30) DEFAULT NULL,
  `nominationLevel` varchar(30) DEFAULT NULL,
  `electionSettingID` varchar(25) DEFAULT NULL,
  `accyearID` varchar(11) DEFAULT NULL,
  `groupID` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`electionCategoryId`),
  KEY `ele_cat_stng_elstid` (`electionSettingID`),
  KEY `ele_cat_stng_accid` (`accyearID`),
  KEY `ele_cat_stng_grpid` (`groupID`),
  KEY `ele_cat_stng_classId` (`classId`),
  CONSTRAINT `ele_cat_stng_accid` FOREIGN KEY (`accyearID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_cat_stng_classId` FOREIGN KEY (`classId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_cat_stng_elstid` FOREIGN KEY (`electionSettingID`) REFERENCES `campus_election_election_setting` (`electionSettingID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_cat_stng_grpid` FOREIGN KEY (`groupID`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_election_setting` */

CREATE TABLE `campus_election_election_setting` (
  `electionSettingID` varchar(25) NOT NULL,
  `accyearID` varchar(11) DEFAULT NULL,
  `groupID` varchar(25) DEFAULT NULL,
  `electionTitle` varchar(30) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startTime` varchar(30) DEFAULT NULL,
  `endTime` varchar(30) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`electionSettingID`),
  KEY `ele_ele_stng_accid` (`accyearID`),
  KEY `ele_ele_stng_grpid` (`groupID`),
  CONSTRAINT `ele_ele_stng_accid` FOREIGN KEY (`accyearID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_ele_stng_grpid` FOREIGN KEY (`groupID`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_group_settings` */

CREATE TABLE `campus_election_group_settings` (
  `election_group_id` varchar(25) NOT NULL,
  `accyear_id` varchar(11) DEFAULT NULL,
  `groupname` varchar(100) DEFAULT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`election_group_id`),
  KEY `ele_grp_accy_id` (`accyear_id`),
  CONSTRAINT `ele_grp_accy_id` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_nomiation_registration` */

CREATE TABLE `campus_election_nomiation_registration` (
  `electionCategoryId` varchar(30) NOT NULL,
  `admissionNo` varchar(30) DEFAULT NULL,
  `studentId` varchar(60) DEFAULT NULL,
  `classdetail_id_int` varchar(20) DEFAULT NULL,
  `classsection_id_int` varchar(20) DEFAULT NULL,
  `electionSettingID` varchar(25) DEFAULT NULL,
  `accyearID` varchar(11) DEFAULT NULL,
  `groupID` varchar(25) DEFAULT NULL,
  `isApproved` varchar(20) NOT NULL DEFAULT 'Pending',
  `voteCount` int(30) DEFAULT '0',
  KEY `ele_nomreg_elecatid` (`electionCategoryId`),
  KEY `ele_nomreg_class` (`classdetail_id_int`),
  KEY `ele_nomreg_section` (`classsection_id_int`),
  KEY `ele_nomreg_accyear` (`accyearID`),
  KEY `ele_nomreg_studentId` (`studentId`),
  KEY `ele_nomreg_groupId` (`groupID`),
  CONSTRAINT `ele_nomreg_accyear` FOREIGN KEY (`accyearID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_nomreg_class` FOREIGN KEY (`classdetail_id_int`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_nomreg_section` FOREIGN KEY (`classsection_id_int`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_nomreg_studentId` FOREIGN KEY (`studentId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_polling_machine_setting` */

CREATE TABLE `campus_election_polling_machine_setting` (
  `pollingMachineId_int_var` varchar(30) NOT NULL,
  `boothNameId_int` varchar(30) DEFAULT NULL,
  `machineName_var` varchar(30) DEFAULT NULL,
  `systemName_var` varchar(30) DEFAULT NULL,
  `systemIp_var` varchar(30) DEFAULT NULL,
  `accyearID` varchar(11) DEFAULT NULL,
  `groupID` varchar(25) DEFAULT NULL,
  `electionSettingID` varchar(25) DEFAULT NULL,
  `status` varchar(5) DEFAULT 'N',
  `activationFor` varchar(20) DEFAULT '-',
  PRIMARY KEY (`pollingMachineId_int_var`),
  KEY `ele_pollm_bootid` (`boothNameId_int`),
  KEY `ele_pollm_accid` (`accyearID`),
  KEY `ele_pollm_grpid` (`groupID`),
  KEY `ele_pollm_eleSetting` (`electionSettingID`),
  CONSTRAINT `ele_pollm_accid` FOREIGN KEY (`accyearID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_pollm_bootid` FOREIGN KEY (`boothNameId_int`) REFERENCES `campus_election_booth_setting` (`boothNameId_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_pollm_eleSetting` FOREIGN KEY (`electionSettingID`) REFERENCES `campus_election_election_setting` (`electionSettingID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_pollm_grpid` FOREIGN KEY (`groupID`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_election_school_setting` */

CREATE TABLE `campus_election_school_setting` (
  `group_sett_school_id` varchar(25) NOT NULL,
  `election_group_id` varchar(25) DEFAULT NULL,
  `Loc_Id` varchar(20) DEFAULT NULL,
  `createdtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_sett_school_id`),
  KEY `ele_schl_sett_grpid` (`election_group_id`),
  KEY `ele_schl_sett_locid` (`Loc_Id`),
  CONSTRAINT `ele_schl_sett_grpid` FOREIGN KEY (`election_group_id`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ele_schl_sett_locid` FOREIGN KEY (`Loc_Id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_category` */

CREATE TABLE `campus_event_category` (
  `categoryId` varchar(20) NOT NULL,
  `categoryName` varchar(200) DEFAULT NULL,
  `eventId` varchar(20) DEFAULT NULL,
  `classList` blob,
  PRIMARY KEY (`categoryId`),
  KEY `eventcategory_eventId` (`eventId`),
  CONSTRAINT `eventcategory_eventId` FOREIGN KEY (`eventId`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_chestno_list` */

CREATE TABLE `campus_event_chestno_list` (
  `slno` int(11) NOT NULL AUTO_INCREMENT,
  `admission_no` varchar(25) DEFAULT NULL,
  `chest_no` varchar(25) DEFAULT NULL,
  KEY `slno` (`slno`),
  KEY `chestlist_stuId` (`admission_no`),
  CONSTRAINT `chestlist_stuId` FOREIGN KEY (`admission_no`) REFERENCES `campus_student` (`student_admissionno_var`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_criteria` */

CREATE TABLE `campus_event_criteria` (
  `Criteria_Id` varchar(50) NOT NULL,
  `Event` varchar(50) DEFAULT NULL,
  `Programme_Name` varchar(50) DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `Stage` varchar(50) DEFAULT NULL,
  `GreenRoom` varchar(50) DEFAULT NULL,
  `Criteria` varchar(250) DEFAULT NULL,
  `Max_marks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Criteria_Id`),
  KEY `criteria_eventId` (`Event`),
  KEY `criteria_progcreationId` (`Programme_Name`),
  KEY `criteria_categoryId` (`Category`),
  CONSTRAINT `criteria_categoryId` FOREIGN KEY (`Category`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `criteria_eventId` FOREIGN KEY (`Event`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `criteria_progcreationId` FOREIGN KEY (`Programme_Name`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_greenroom` */

CREATE TABLE `campus_event_greenroom` (
  `greenroom_id` varchar(20) NOT NULL,
  `event_id` varchar(20) DEFAULT NULL,
  `greenroom_name` varchar(40) DEFAULT NULL,
  `greenroom_type` varchar(40) DEFAULT NULL,
  `building` varchar(40) DEFAULT NULL,
  `floor` varchar(40) DEFAULT NULL,
  `roomno` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`greenroom_id`),
  KEY `eventgreenroom_eventId` (`event_id`),
  CONSTRAINT `eventgreenroom_eventId` FOREIGN KEY (`event_id`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_marksentry` */

CREATE TABLE `campus_event_marksentry` (
  `marksEntry_id` varchar(20) NOT NULL,
  `chestNo` varchar(40) DEFAULT NULL,
  `scoredMarks` varchar(50) DEFAULT NULL,
  `event_id` varchar(20) DEFAULT NULL,
  `category_id` varchar(20) DEFAULT NULL,
  `program_id` varchar(20) DEFAULT NULL,
  `AccYear` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`marksEntry_id`),
  KEY `eventmarksentry_eventId` (`event_id`),
  KEY `eventmarksentry_categoryId` (`category_id`),
  KEY `eventmarksentry_progId` (`program_id`),
  KEY `eventmarksentry_accyear` (`AccYear`),
  CONSTRAINT `eventmarksentry_accyear` FOREIGN KEY (`AccYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventmarksentry_categoryId` FOREIGN KEY (`category_id`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventmarksentry_eventId` FOREIGN KEY (`event_id`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventmarksentry_progId` FOREIGN KEY (`program_id`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_marksentrycriteriawise` */

CREATE TABLE `campus_event_marksentrycriteriawise` (
  `marksEntry_id` varchar(20) DEFAULT NULL,
  `judge_id` varchar(20) DEFAULT NULL,
  `criteria` varchar(100) DEFAULT NULL,
  `marksForEachCriteria` int(20) DEFAULT NULL,
  KEY `markscriteria_marksId` (`marksEntry_id`),
  KEY `markscriteria_judgeId` (`judge_id`),
  CONSTRAINT `markscriteria_judgeId` FOREIGN KEY (`judge_id`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `markscriteria_marksId` FOREIGN KEY (`marksEntry_id`) REFERENCES `campus_event_marksentry` (`marksEntry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_prizelevel` */

CREATE TABLE `campus_event_prizelevel` (
  `prize_id` varchar(50) NOT NULL,
  `event_name` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `program_name` varchar(60) DEFAULT NULL,
  `seq_no` varchar(50) DEFAULT NULL,
  `prize_level` varchar(50) DEFAULT NULL,
  `points` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`prize_id`),
  KEY `eventprizlevel_eventId` (`event_name`),
  KEY `eventprizlevel_categoryId` (`category`),
  KEY `eventprizlevel_progId` (`program_name`),
  CONSTRAINT `eventprizlevel_categoryId` FOREIGN KEY (`category`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventprizlevel_eventId` FOREIGN KEY (`event_name`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventprizlevel_progId` FOREIGN KEY (`program_name`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_program_numbering` */

CREATE TABLE `campus_event_program_numbering` (
  `program_number_id` varchar(25) NOT NULL,
  `regId` varchar(20) DEFAULT NULL,
  `stageId` varchar(20) DEFAULT NULL,
  `event` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `program` varchar(60) DEFAULT NULL,
  `participants` varchar(50) DEFAULT NULL,
  `programnumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`program_number_id`),
  KEY `programno_studentregId` (`regId`),
  KEY `programno_stageId` (`stageId`),
  KEY `programno_eventId` (`event`),
  KEY `programno_categoryId` (`category`),
  KEY `programno_programId` (`program`),
  CONSTRAINT `programno_categoryId` FOREIGN KEY (`category`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `programno_eventId` FOREIGN KEY (`event`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `programno_programId` FOREIGN KEY (`program`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `programno_stageId` FOREIGN KEY (`stageId`) REFERENCES `campus_event_stage` (`stageId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `programno_studentregId` FOREIGN KEY (`regId`) REFERENCES `campus_event_studentregistration` (`registration_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_program_scheduling` */

CREATE TABLE `campus_event_program_scheduling` (
  `Scheduling_id` varchar(50) NOT NULL,
  `Event_name` varchar(20) DEFAULT NULL,
  `Program_name` varchar(60) DEFAULT NULL,
  `Program_date` date DEFAULT NULL,
  `Stage` varchar(20) DEFAULT NULL,
  `Green_room` varchar(20) DEFAULT NULL,
  `Makeup_report` varchar(50) DEFAULT NULL,
  `BackStage_report` varchar(50) DEFAULT NULL,
  `Program_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Scheduling_id`),
  KEY `progschedule_eventId` (`Event_name`),
  KEY `progschedule_progcreationId` (`Program_name`),
  KEY `progschedule_stageId` (`Stage`),
  KEY `progschedule_greenroomId` (`Green_room`),
  CONSTRAINT `progschedule_eventId` FOREIGN KEY (`Event_name`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `progschedule_greenroomId` FOREIGN KEY (`Green_room`) REFERENCES `campus_event_greenroom` (`greenroom_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `progschedule_progcreationId` FOREIGN KEY (`Program_name`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `progschedule_stageId` FOREIGN KEY (`Stage`) REFERENCES `campus_event_stage` (`stageId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_programcreation` */

CREATE TABLE `campus_event_programcreation` (
  `program_id` varchar(60) NOT NULL,
  `event_id` varchar(20) DEFAULT NULL,
  `category_id` varchar(20) DEFAULT NULL,
  `program_name` varchar(150) DEFAULT NULL,
  `noOfJudge` varchar(150) DEFAULT NULL,
  `programType` varchar(150) DEFAULT NULL,
  `participantsType` varchar(150) DEFAULT NULL,
  `teamsAllowed` varchar(150) DEFAULT '-',
  `noOfparticipants` varchar(70) DEFAULT NULL,
  `duration` varchar(40) DEFAULT NULL,
  `isHouseWise` varchar(10) DEFAULT 'no',
  `noOfHouseChildren` varchar(40) DEFAULT NULL,
  `programDate` varchar(20) DEFAULT NULL,
  `info_staff` varchar(700) DEFAULT NULL,
  `info_general` varchar(700) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`program_id`),
  KEY `eventprogcreation_eventId` (`event_id`),
  KEY `eventprogcreation_categoryId` (`category_id`),
  CONSTRAINT `eventprogcreation_categoryId` FOREIGN KEY (`category_id`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventprogcreation_eventId` FOREIGN KEY (`event_id`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_registration` */

CREATE TABLE `campus_event_registration` (
  `event_Id` varchar(20) NOT NULL,
  `eventName` varchar(200) DEFAULT NULL,
  `eventType` varchar(200) DEFAULT NULL,
  `startsOn` varchar(20) DEFAULT NULL,
  `endsOn` varchar(20) DEFAULT NULL,
  `startRegistation` varchar(20) DEFAULT NULL,
  `endRegstation` varchar(20) DEFAULT NULL,
  `regStatus` varchar(30) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `isHouseWise` varchar(10) DEFAULT 'no',
  `isAprvPartReg` varchar(10) DEFAULT 'no',
  `accyer_ID` varchar(20) DEFAULT NULL,
  `location_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`event_Id`),
  KEY `eventreg_locId` (`location_ID`),
  KEY `eventreg_accId` (`accyer_ID`),
  CONSTRAINT `eventreg_accId` FOREIGN KEY (`accyer_ID`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventreg_locId` FOREIGN KEY (`location_ID`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_regno` */

CREATE TABLE `campus_event_regno` (
  `reg_no` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_stage` */

CREATE TABLE `campus_event_stage` (
  `stageId` varchar(20) NOT NULL,
  `eventId` varchar(20) DEFAULT NULL,
  `stageName` varchar(500) DEFAULT NULL,
  `building` varchar(500) DEFAULT NULL,
  `floorName` varchar(500) DEFAULT NULL,
  `roomNumber` varchar(500) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`stageId`),
  KEY `eventstage_eventId` (`eventId`),
  CONSTRAINT `eventstage_eventId` FOREIGN KEY (`eventId`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_stage_allocation` */

CREATE TABLE `campus_event_stage_allocation` (
  `AllocationId` varchar(50) NOT NULL,
  `Event` varchar(20) DEFAULT NULL,
  `StageName` varchar(20) DEFAULT NULL,
  `ParticipantsName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`AllocationId`),
  KEY `eventstageallocation_eventId` (`Event`),
  KEY `eventstageallocation_stageId` (`StageName`),
  CONSTRAINT `eventstageallocation_eventId` FOREIGN KEY (`Event`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventstageallocation_stageId` FOREIGN KEY (`StageName`) REFERENCES `campus_event_stage` (`stageId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_studentregistration` */

CREATE TABLE `campus_event_studentregistration` (
  `registration_id` varchar(20) NOT NULL,
  `registrationNo` varchar(50) DEFAULT NULL,
  `event_id` varchar(20) DEFAULT NULL,
  `category_id` varchar(20) DEFAULT NULL,
  `program_id` varchar(20) DEFAULT NULL,
  `house_id` varchar(20) DEFAULT NULL,
  `captain_admissionNo` varchar(30) DEFAULT NULL,
  `participants_admissionNo` varchar(3000) DEFAULT NULL,
  `info_staff` varchar(3000) DEFAULT NULL,
  `info_synopsys` varchar(3000) DEFAULT NULL,
  `info_req` varchar(3000) DEFAULT NULL,
  `participateType` varchar(20) DEFAULT NULL,
  `accId` varchar(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`registration_id`),
  KEY `eventsturegno_eventId` (`event_id`),
  KEY `eventsturegno_categoryId` (`category_id`),
  KEY `eventsturegno_progcreationId` (`program_id`),
  KEY `eventsturegno_accyearId` (`accId`),
  CONSTRAINT `eventsturegno_accyearId` FOREIGN KEY (`accId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventsturegno_categoryId` FOREIGN KEY (`category_id`) REFERENCES `campus_event_category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventsturegno_eventId` FOREIGN KEY (`event_id`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eventsturegno_progcreationId` FOREIGN KEY (`program_id`) REFERENCES `campus_event_programcreation` (`program_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_event_volunteerregistation` */

CREATE TABLE `campus_event_volunteerregistation` (
  `volunteer_id` varchar(30) NOT NULL,
  `event_id` varchar(20) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `admission_no` varchar(20) DEFAULT NULL,
  `greenroom_id` varchar(20) DEFAULT NULL,
  `stage_id` varchar(20) DEFAULT NULL,
  `start_time` varchar(20) DEFAULT NULL,
  `end_time` varchar(30) DEFAULT NULL,
  `createuser` varchar(30) DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyuser` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`volunteer_id`),
  KEY `volunteerreg_eventId` (`event_id`),
  KEY `volunteerreg_locId` (`loc_id`),
  KEY `volunteerreg_greenroomId` (`greenroom_id`),
  KEY `volunteerreg_stageId` (`stage_id`),
  CONSTRAINT `volunteerreg_eventId` FOREIGN KEY (`event_id`) REFERENCES `campus_event_registration` (`event_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `volunteerreg_greenroomId` FOREIGN KEY (`greenroom_id`) REFERENCES `campus_event_greenroom` (`greenroom_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `volunteerreg_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `volunteerreg_stageId` FOREIGN KEY (`stage_id`) REFERENCES `campus_event_stage` (`stageId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_exam_dependency` */

CREATE TABLE `campus_exam_dependency` (
  `depcy_id` int(10) NOT NULL AUTO_INCREMENT,
  `Exam_code` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Exam_name` varchar(100) DEFAULT NULL,
  `Dependency_Exam_code` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `Dependency_perce` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`depcy_id`),
  KEY `examdependency_examId` (`Exam_code`),
  KEY `examdependency_dependexamId` (`Dependency_Exam_code`),
  CONSTRAINT `examdependency_dependexamId` FOREIGN KEY (`Dependency_Exam_code`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examdependency_examId` FOREIGN KEY (`Exam_code`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_exam_gradesettings` */

CREATE TABLE `campus_exam_gradesettings` (
  `grade_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `grade_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `comments` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `min_marks` varchar(25) DEFAULT NULL,
  `max_marks` varchar(25) DEFAULT NULL,
  `accyear` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`grade_id`),
  KEY `examgrasetting_accyearId` (`accyear`),
  KEY `examgrasetting_locId` (`loc_id`),
  CONSTRAINT `examgrasetting_accyearId` FOREIGN KEY (`accyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examgrasetting_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_exam_timetable` */

CREATE TABLE `campus_exam_timetable` (
  `timetable_id` varchar(25) NOT NULL,
  `accyear_id` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `section_id` varchar(20) DEFAULT NULL,
  `examcode` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`timetable_id`),
  KEY `examtimetable_accyearId` (`accyear_id`),
  KEY `examtimetable_locId` (`loc_id`),
  KEY `examtimetable_classId` (`class_id`),
  KEY `examtimetable_sectionId` (`section_id`),
  KEY `examtimetable_examId` (`examcode`),
  CONSTRAINT `examtimetable_accyearId` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examtimetable_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examtimetable_examId` FOREIGN KEY (`examcode`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examtimetable_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examtimetable_sectionId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_examination` */

CREATE TABLE `campus_examination` (
  `examid` varchar(30) NOT NULL,
  `examcode` varchar(20) DEFAULT NULL,
  `examname` varchar(50) NOT NULL,
  `acadamicyear` varchar(11) CHARACTER SET utf8 NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `createuser` varchar(20) NOT NULL DEFAULT '',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedby` varchar(20) DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `loc_id` varchar(30) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`examid`),
  KEY `examination_accyearId` (`acadamicyear`),
  KEY `examination_locId` (`loc_id`),
  CONSTRAINT `examination_accyearId` FOREIGN KEY (`acadamicyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examination_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_expenditure` */

CREATE TABLE `campus_expenditure` (
  `ExpId` varchar(30) NOT NULL,
  `ExpenditureTitle` varchar(150) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Description` varchar(750) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `loc_Id` varchar(20) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remark` varchar(250) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ExpId`),
  KEY `exp_locId` (`loc_Id`),
  CONSTRAINT `exp_locId` FOREIGN KEY (`loc_Id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_collection` */

CREATE TABLE `campus_fee_collection` (
  `feeCollectionCode` varchar(60) NOT NULL,
  `admissionNo` varchar(60) NOT NULL,
  `accYear` varchar(11) NOT NULL,
  `termcode` varchar(20) DEFAULT NULL,
  `chln_no` int(20) NOT NULL AUTO_INCREMENT,
  `chln_gen_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_paid` char(3) DEFAULT NULL,
  `totalamount` double DEFAULT NULL,
  `actualamount` double DEFAULT NULL,
  `balance_amount` double DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `fineAmount` double DEFAULT NULL,
  `due_amount` double DEFAULT NULL,
  `advance_amount` double DEFAULT NULL,
  `paymentMode` varchar(30) DEFAULT NULL,
  `dd_cheque_no` varchar(75) DEFAULT NULL,
  `dd_cheque_date` varchar(33) DEFAULT NULL,
  `bank_name` varchar(150) DEFAULT NULL,
  `paymentParticulars` varchar(60) DEFAULT NULL,
  `paidDate` date DEFAULT NULL,
  `createdby` varchar(60) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updatedby` varchar(60) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `concessionAmt` double DEFAULT '0',
  `paidByExcel` double DEFAULT NULL,
  PRIMARY KEY (`feeCollectionCode`),
  UNIQUE KEY `chln_no` (`chln_no`),
  KEY `fcollec_accyId` (`accYear`),
  KEY `fcollec_termId` (`termcode`),
  KEY `fcollec_stuId` (`admissionNo`),
  CONSTRAINT `fcollec_accyId` FOREIGN KEY (`accYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fcollec_stuId` FOREIGN KEY (`admissionNo`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fcollec_termId` FOREIGN KEY (`termcode`) REFERENCES `campus_fee_termdetails` (`termid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_collection_details` */

CREATE TABLE `campus_fee_collection_details` (
  `admissionNo` varchar(60) DEFAULT NULL,
  `accYear` varchar(11) DEFAULT NULL,
  `termcode` varchar(20) DEFAULT NULL,
  `chln_no` int(20) NOT NULL AUTO_INCREMENT,
  `chln_gen_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_paid` char(3) DEFAULT NULL,
  `totalamount` double DEFAULT NULL,
  `actualamount` double DEFAULT NULL,
  `balance_amount` double DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `conc_amount` double DEFAULT NULL,
  `conc_percent` double DEFAULT NULL,
  `paidDate` date DEFAULT NULL,
  `createdby` varchar(60) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updatedby` varchar(60) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `chln_no` (`chln_no`),
  KEY `colldetails_accId` (`accYear`),
  KEY `colldetails_stuId` (`admissionNo`),
  KEY `colldetails_trmId` (`termcode`),
  CONSTRAINT `colldetails_accId` FOREIGN KEY (`accYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `colldetails_stuId` FOREIGN KEY (`admissionNo`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `colldetails_trmId` FOREIGN KEY (`termcode`) REFERENCES `campus_fee_termdetails` (`termid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_collectiondetails` */

CREATE TABLE `campus_fee_collectiondetails` (
  `feeCollectionCode` varchar(20) NOT NULL,
  `feeCode` varchar(20) NOT NULL,
  `feeAmount` double NOT NULL,
  `finalFeeAmtcollected` double DEFAULT NULL,
  `outstandingfee` double DEFAULT NULL,
  `feepaiddate` date DEFAULT NULL,
  `concessionPercent` double DEFAULT '0',
  `consfeeAmount` double DEFAULT '0',
  KEY `feecolldetail_fcolId` (`feeCollectionCode`),
  KEY `feecolldetail_feeCode` (`feeCode`),
  CONSTRAINT `feecolldetail_fcolId` FOREIGN KEY (`feeCollectionCode`) REFERENCES `campus_fee_collection` (`feeCollectionCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feecolldetail_feeCode` FOREIGN KEY (`feeCode`) REFERENCES `campus_fee_master` (`FeeCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_concessiondetails` */

CREATE TABLE `campus_fee_concessiondetails` (
  `concessionid` varchar(20) NOT NULL,
  `concessionname` varchar(30) NOT NULL,
  `percentage` double DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createdby` varchar(30) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedby` varchar(30) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`concessionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_indetail` */

CREATE TABLE `campus_fee_indetail` (
  `FeeInDetailedCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `admissionNo` varchar(60) CHARACTER SET utf8 NOT NULL,
  `term_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `accYear` varchar(20) CHARACTER SET utf8 NOT NULL,
  `totalamount` double DEFAULT '0',
  `actualamount` double DEFAULT '0',
  `balance_amount` double DEFAULT '0',
  `amount_paid` double DEFAULT '0',
  `conc_amount` double DEFAULT NULL,
  `conc_percent` double DEFAULT NULL,
  `paidDate` date DEFAULT NULL,
  `loc_Id` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `createdby` varchar(20) NOT NULL,
  `createdtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`FeeInDetailedCode`),
  KEY `findetail_accyId` (`accYear`),
  KEY `findetail_termId` (`term_id`),
  KEY `findetail_stuId` (`admissionNo`),
  CONSTRAINT `findetail_accyId` FOREIGN KEY (`accYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `findetail_stuId` FOREIGN KEY (`admissionNo`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `findetail_termId` FOREIGN KEY (`term_id`) REFERENCES `campus_fee_termdetails` (`termid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_fee_master` */

CREATE TABLE `campus_fee_master` (
  `FeeCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `FeeName` varchar(50) NOT NULL,
  `IsConcession` char(1) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remark` varchar(250) DEFAULT NULL,
  `createdby` varchar(20) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedby` varchar(20) DEFAULT NULL,
  `modifiedtime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `feeType` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `academicYear` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`FeeCode`),
  KEY `fm_locId` (`locationId`),
  KEY `fm_accyId` (`academicYear`),
  KEY `fm_feetypeId` (`feeType`),
  CONSTRAINT `fm_accyId` FOREIGN KEY (`academicYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fm_feetypeId` FOREIGN KEY (`feeType`) REFERENCES `campus_fee_type` (`feeTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fm_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_fee_reciept` */

CREATE TABLE `campus_fee_reciept` (
  `feeCollectionCode` varchar(60) DEFAULT NULL,
  `feeCode` varchar(20) DEFAULT NULL,
  `feeAmount` double DEFAULT NULL,
  `finalFeeAmtcollected` double DEFAULT NULL,
  `outstandingfee` double DEFAULT NULL,
  `feepaiddate` date DEFAULT NULL,
  `concessionPercent` double DEFAULT NULL,
  `consfeeAmount` double DEFAULT NULL,
  KEY `freceipt_fcId` (`feeCollectionCode`),
  KEY `freceipt_fcode` (`feeCode`),
  CONSTRAINT `freceipt_fcId` FOREIGN KEY (`feeCollectionCode`) REFERENCES `campus_fee_collection` (`feeCollectionCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `freceipt_fcode` FOREIGN KEY (`feeCode`) REFERENCES `campus_fee_master` (`FeeCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_setup` */

CREATE TABLE `campus_fee_setup` (
  `feeSettingcode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `ClassCode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `AccyearCode` varchar(11) CHARACTER SET utf8 NOT NULL,
  `Termcode` varchar(20) CHARACTER SET utf8 NOT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `createdby` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedby` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `updatedtime` timestamp NULL DEFAULT NULL,
  `specialization` varchar(20) CHARACTER SET utf32 DEFAULT NULL,
  PRIMARY KEY (`feeSettingcode`),
  KEY `fsetup_locId` (`locationId`),
  KEY `fsetup_accyId` (`AccyearCode`),
  KEY `fsetup_classId` (`ClassCode`),
  KEY `fsetup_termId` (`Termcode`),
  CONSTRAINT `fsetup_accyId` FOREIGN KEY (`AccyearCode`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON UPDATE CASCADE,
  CONSTRAINT `fsetup_classId` FOREIGN KEY (`ClassCode`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON UPDATE CASCADE,
  CONSTRAINT `fsetup_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON UPDATE CASCADE,
  CONSTRAINT `fsetup_termId` FOREIGN KEY (`Termcode`) REFERENCES `campus_fee_termdetails` (`termid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_fee_setupdetails` */

CREATE TABLE `campus_fee_setupdetails` (
  `feeSettingCode` varchar(20) NOT NULL,
  `feecode` varchar(20) NOT NULL,
  `feeAmount` double NOT NULL,
  `fine` double DEFAULT NULL,
  KEY `fsetupde_feecode` (`feecode`),
  KEY `fsetupde_feesetupId` (`feeSettingCode`),
  CONSTRAINT `fsetupde_feecode` FOREIGN KEY (`feecode`) REFERENCES `campus_fee_master` (`FeeCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fsetupde_feesetupId` FOREIGN KEY (`feeSettingCode`) REFERENCES `campus_fee_setup` (`feeSettingcode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_stage` */

CREATE TABLE `campus_fee_stage` (
  `stage_id` varchar(20) NOT NULL,
  `stage_name` varchar(100) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `createdby` varchar(20) DEFAULT NULL,
  `createdtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedby` varchar(20) DEFAULT NULL,
  `modifiedtime` timestamp NULL DEFAULT NULL,
  `ArrivalTime` varchar(20) DEFAULT NULL,
  `HaltTime` varchar(20) DEFAULT NULL,
  `DepTime` varchar(20) DEFAULT NULL,
  `Distance` varchar(20) DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `status` varchar(30) DEFAULT 'unmapped',
  PRIMARY KEY (`stage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_termdetails` */

CREATE TABLE `campus_fee_termdetails` (
  `termid` varchar(20) CHARACTER SET utf8 NOT NULL,
  `termname` varchar(90) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `accyear` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `ternOrder` varchar(60) DEFAULT NULL,
  `isTransportTerm` char(3) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remark` varchar(250) DEFAULT NULL,
  `createdby` varchar(90) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedby` varchar(90) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`termid`),
  KEY `fterm_locId` (`locationId`),
  KEY `fterm_accyId` (`accyear`),
  CONSTRAINT `fterm_accyId` FOREIGN KEY (`accyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fterm_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_fee_transport_termdetails` */

CREATE TABLE `campus_fee_transport_termdetails` (
  `termid` varchar(20) NOT NULL,
  `termname` varchar(30) NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `accyear` varchar(11) NOT NULL,
  `locationId` varchar(30) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `ternOrder` varchar(20) DEFAULT NULL,
  `isTransportTerm` char(1) DEFAULT NULL,
  `createdby` varchar(30) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedby` varchar(30) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`termid`),
  KEY `feetransftem_locId` (`locationId`),
  KEY `feetransftem_accId` (`accyear`),
  CONSTRAINT `feetransftem_accId` FOREIGN KEY (`accyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feetransftem_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fee_type` */

CREATE TABLE `campus_fee_type` (
  `slno` int(11) NOT NULL AUTO_INCREMENT,
  `feeTypeId` varchar(20) NOT NULL,
  `feeType` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `createdBy` varchar(50) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`slno`,`feeTypeId`),
  UNIQUE KEY `feeTypeId` (`feeTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_fineconfiguration` */

CREATE TABLE `campus_fineconfiguration` (
  `sno` int(50) NOT NULL AUTO_INCREMENT,
  `days` int(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` varchar(150) DEFAULT NULL,
  `IsApplicable` char(15) DEFAULT NULL,
  `feetype` varchar(20) DEFAULT NULL,
  KEY `sno` (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_generate_house` */

CREATE TABLE `campus_generate_house` (
  `generate_houseid` varchar(20) NOT NULL,
  `accyearid` varchar(11) DEFAULT NULL,
  `locid` varchar(20) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `class_strength` int(11) unsigned DEFAULT NULL,
  `allocated_strength` int(11) unsigned DEFAULT NULL,
  `grem` int(11) DEFAULT NULL,
  `brem` int(11) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `selection` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`generate_houseid`),
  KEY `genhou_accId` (`accyearid`),
  KEY `genhou_locId` (`locid`),
  KEY `genhou_classId` (`classid`),
  CONSTRAINT `genhou_accId` FOREIGN KEY (`accyearid`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `genhou_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `genhou_locId` FOREIGN KEY (`locid`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_generate_payroll` */

CREATE TABLE `campus_generate_payroll` (
  `payrollId` varchar(25) NOT NULL,
  `teacherId` varchar(25) DEFAULT NULL,
  `locationId` varchar(25) DEFAULT NULL,
  `payrollmonth` varchar(25) DEFAULT NULL,
  `taxpermonth` double DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`payrollId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_grade_dependency` */

CREATE TABLE `campus_grade_dependency` (
  `grade_Id` int(50) NOT NULL AUTO_INCREMENT,
  `acc_year` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `class_Id` varchar(20) DEFAULT NULL,
  `section_Id` varchar(20) DEFAULT NULL,
  `exam_code` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `project` varchar(50) DEFAULT NULL,
  `assignment` varchar(50) DEFAULT NULL,
  `practical` varchar(50) DEFAULT NULL,
  `attendance` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`grade_Id`),
  KEY `examgradedep_accyearId` (`acc_year`),
  KEY `examgradedep_locId` (`loc_id`),
  KEY `examgradedep_classId` (`class_Id`),
  KEY `examgradedep_sectionId` (`section_Id`),
  KEY `examgradedep_examId` (`exam_code`),
  CONSTRAINT `examgradedep_accyearId` FOREIGN KEY (`acc_year`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examgradedep_classId` FOREIGN KEY (`class_Id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examgradedep_examId` FOREIGN KEY (`exam_code`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examgradedep_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examgradedep_sectionId` FOREIGN KEY (`section_Id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_holidaymaster` */

CREATE TABLE `campus_holidaymaster` (
  `HOL_ID` varchar(10) NOT NULL,
  `HOLIDAY_DATE` date DEFAULT NULL,
  `WEEKDAY` varchar(20) DEFAULT NULL,
  `HOLIDAY_NAME` varchar(100) DEFAULT NULL,
  `HOLIDAY_TYPE` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `Remarks` varchar(250) DEFAULT NULL,
  `CURRENT_YEAR` varchar(11) DEFAULT NULL,
  `CREATEDDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATEDBY` varchar(20) DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `MODIFIED_DATE` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `LOC_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`HOL_ID`),
  KEY `holi_locId` (`LOC_ID`),
  KEY `holi_accId` (`CURRENT_YEAR`),
  CONSTRAINT `holi_accId` FOREIGN KEY (`CURRENT_YEAR`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `holi_locId` FOREIGN KEY (`LOC_ID`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_homework` */

CREATE TABLE `campus_homework` (
  `homeworkid` varchar(20) CHARACTER SET latin1 NOT NULL,
  `dateid` date DEFAULT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `studentId` varchar(60) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `sectionid` varchar(20) DEFAULT NULL,
  `subjectid` varchar(20) DEFAULT NULL,
  `description` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createuser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `modifyuser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `modifydate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`homeworkid`),
  KEY `homework_classId` (`classid`),
  KEY `homework_sectionId` (`sectionid`),
  KEY `homework_subId` (`subjectid`),
  KEY `homework_stuId` (`studentId`),
  KEY `homework_locID` (`LocId`),
  CONSTRAINT `homework_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homework_locID` FOREIGN KEY (`LocId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homework_sectionId` FOREIGN KEY (`sectionid`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homework_stuId` FOREIGN KEY (`studentId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homework_subId` FOREIGN KEY (`subjectid`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_house_settings` */

CREATE TABLE `campus_house_settings` (
  `house_id` varchar(20) NOT NULL,
  `accyear_id` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `housename` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'active',
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_by` varchar(100) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_book_issue_details` */

CREATE TABLE `campus_library_book_issue_details` (
  `issued_id` varchar(20) NOT NULL,
  `accession_no` varchar(20) DEFAULT NULL,
  `item_type` varchar(200) DEFAULT NULL,
  `book_title` varchar(250) DEFAULT NULL,
  `author` varchar(250) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `DDC` varchar(100) DEFAULT NULL,
  `current_status` varchar(50) DEFAULT NULL,
  `shelf_no` varchar(50) DEFAULT NULL,
  `location_id` varchar(20) DEFAULT NULL,
  `sub_id` varchar(20) DEFAULT NULL,
  `user_type` varchar(25) DEFAULT NULL,
  `issued_to` varchar(50) DEFAULT NULL,
  `book_issued_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`issued_id`),
  KEY `bookissue_stockId` (`accession_no`),
  KEY `bookissue_subId` (`sub_id`),
  KEY `bookissue_locId` (`location_id`),
  CONSTRAINT `bookissue_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_library_location` (`library_loc_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookissue_stockId` FOREIGN KEY (`accession_no`) REFERENCES `campus_library_stock_entry` (`Entry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookissue_subId` FOREIGN KEY (`sub_id`) REFERENCES `campus_library_subscriber_details` (`subscriberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_book_return_details` */

CREATE TABLE `campus_library_book_return_details` (
  `return_id` varchar(20) NOT NULL,
  `accession_no` varchar(20) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `book_titile` varchar(250) DEFAULT NULL,
  `author` varchar(250) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `DDC` varchar(50) DEFAULT NULL,
  `current_status` varchar(50) DEFAULT NULL,
  `issued_to` varchar(25) DEFAULT NULL,
  `sub_id` varchar(20) DEFAULT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `shelf_no` varchar(100) DEFAULT NULL,
  `location_id` varchar(20) DEFAULT NULL,
  `book_issued_date` date DEFAULT NULL,
  `book_returned_date` date DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `issue_Id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`return_id`),
  KEY `bookretun_stockId` (`accession_no`),
  KEY `bookreturn_subId` (`sub_id`),
  KEY `bookreturn_stuId` (`issued_to`),
  KEY `bookreturn_issueId` (`issue_Id`),
  KEY `bookreturn_locId` (`location_id`),
  CONSTRAINT `bookretun_stockId` FOREIGN KEY (`accession_no`) REFERENCES `campus_library_stock_entry` (`Entry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookreturn_issueId` FOREIGN KEY (`issue_Id`) REFERENCES `campus_library_book_issue_details` (`issued_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookreturn_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_library_location` (`library_loc_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookreturn_subId` FOREIGN KEY (`sub_id`) REFERENCES `campus_library_subscriber_details` (`subscriberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_generalsettings` */

CREATE TABLE `campus_library_generalsettings` (
  `Entry_id` varchar(10) NOT NULL,
  `Item_Description` varchar(50) DEFAULT NULL,
  `Num_Of_Day_Item_teken` int(20) DEFAULT NULL,
  `Amount_per_day` double(10,2) DEFAULT NULL,
  `Fine_Amount` double(10,2) DEFAULT NULL,
  `Fine_per_Day` int(20) DEFAULT NULL,
  `Issue_Date` varchar(20) DEFAULT NULL,
  `Return_Date` varchar(20) DEFAULT NULL,
  `Created_By` varchar(20) DEFAULT NULL,
  `Created_Date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Modified_By` varchar(20) DEFAULT NULL,
  `Modified_Date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_journal_subscription` */

CREATE TABLE `campus_library_journal_subscription` (
  `Entry_id` varchar(20) NOT NULL,
  `Accession_No` varchar(20) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `Journal_Type` varchar(50) DEFAULT NULL,
  `Publisher_Id` varchar(20) DEFAULT NULL,
  `Supplier_Id` varchar(20) DEFAULT NULL,
  `Rate_Per_Copy` varchar(20) DEFAULT NULL,
  `No_Of_Copy` varchar(20) DEFAULT NULL,
  `Total_Amount` varchar(25) DEFAULT NULL,
  `Anual_Rate_Per_Copy` varchar(20) DEFAULT NULL,
  `Department` varchar(25) DEFAULT NULL,
  `Other_Details` varchar(100) DEFAULT NULL,
  `Date_On` date DEFAULT NULL,
  `Subscription_periode` varchar(20) DEFAULT NULL,
  `To_date` date DEFAULT NULL,
  `Due_date` date DEFAULT NULL,
  `Created` varchar(20) DEFAULT NULL,
  `Created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Modify` varchar(20) DEFAULT NULL,
  `Modify_time` datetime DEFAULT NULL,
  `accyear` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Entry_id`),
  KEY `joursub_pubId` (`Publisher_Id`),
  KEY `joursub_subId` (`Supplier_Id`),
  KEY `joursub_accId` (`accyear`),
  CONSTRAINT `joursub_accId` FOREIGN KEY (`accyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `joursub_pubId` FOREIGN KEY (`Publisher_Id`) REFERENCES `campus_library_publisher_settings` (`Entry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `joursub_subId` FOREIGN KEY (`Supplier_Id`) REFERENCES `campus_library_supplier_settings` (`Entry_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_location` */

CREATE TABLE `campus_library_location` (
  `library_loc_id` varchar(20) NOT NULL,
  `loc_id` varchar(25) DEFAULT NULL,
  `library_location_name` varchar(250) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(25) DEFAULT NULL,
  `modified_by` varchar(25) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`library_loc_id`),
  KEY `libloc_libId` (`loc_id`),
  CONSTRAINT `libloc_libId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_publisher_settings` */

CREATE TABLE `campus_library_publisher_settings` (
  `Entry_id` varchar(20) NOT NULL,
  `Publisher_Name` varchar(100) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `E_Mail` varchar(50) DEFAULT NULL,
  `Fax` varchar(25) DEFAULT NULL,
  `Tel_Phone_Number` varchar(15) DEFAULT NULL,
  `Mobile_Number` varchar(15) DEFAULT NULL,
  `Pub_Date` date DEFAULT NULL,
  `Created_Date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Created_By` varchar(25) DEFAULT NULL,
  `Modified_BY` varchar(25) DEFAULT NULL,
  `Modified_Date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_reservation_details` */

CREATE TABLE `campus_library_reservation_details` (
  `reservation_id` varchar(20) NOT NULL,
  `accession_no` varchar(20) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `book_title` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `ddc` varchar(50) DEFAULT NULL,
  `current_status` varchar(50) DEFAULT NULL,
  `userType` varchar(50) DEFAULT NULL,
  `issued_to` varchar(50) DEFAULT NULL,
  `shelf_no` varchar(50) DEFAULT NULL,
  `library_location` varchar(20) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `accyear` varchar(20) DEFAULT NULL,
  `subscriber_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `resdet_locId` (`library_location`),
  KEY `resdet_accId` (`accyear`),
  KEY `resdet_stockId` (`accession_no`),
  KEY `resdet_stuId` (`issued_to`),
  KEY `resdet_subId` (`subscriber_id`),
  CONSTRAINT `resdet_accId` FOREIGN KEY (`accyear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resdet_locId` FOREIGN KEY (`library_location`) REFERENCES `campus_library_location` (`library_loc_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resdet_stockId` FOREIGN KEY (`accession_no`) REFERENCES `campus_library_stock_entry` (`Entry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resdet_subId` FOREIGN KEY (`subscriber_id`) REFERENCES `campus_library_subscriber_details` (`subscriberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_stock_entry` */

CREATE TABLE `campus_library_stock_entry` (
  `Entry_id` varchar(20) NOT NULL,
  `Accession_number` varchar(20) DEFAULT NULL,
  `Item_type` varchar(50) DEFAULT NULL,
  `Reg_date` date DEFAULT NULL,
  `Book_title` varchar(50) DEFAULT NULL,
  `Author` varchar(25) DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `Class_desc` varchar(50) DEFAULT NULL,
  `Section_desc` varchar(50) DEFAULT NULL,
  `Division_desc` varchar(50) DEFAULT NULL,
  `Language` varchar(25) DEFAULT NULL,
  `DDC` varchar(50) DEFAULT NULL,
  `Publisher` varchar(20) DEFAULT NULL,
  `No_of_copies` varchar(10) DEFAULT NULL,
  `no_of_issued` int(20) DEFAULT '0',
  `total_available_copies` int(20) DEFAULT '0',
  `Cost_per_copy` varchar(20) DEFAULT NULL,
  `Total_Cost` varchar(20) DEFAULT NULL,
  `Bill_No` varchar(25) DEFAULT NULL,
  `Size` varchar(25) DEFAULT NULL,
  `Supplied_By` varchar(20) DEFAULT NULL,
  `General_Info` varchar(200) DEFAULT NULL,
  `Publish_year` varchar(10) DEFAULT NULL,
  `Edition` varchar(25) DEFAULT NULL,
  `Editor` varchar(25) DEFAULT NULL,
  `ISBN_No` varchar(25) DEFAULT NULL,
  `Bill_date` date DEFAULT NULL,
  `No_of_pages` varchar(25) DEFAULT NULL,
  `Content_search` varchar(50) DEFAULT NULL,
  `Shelf_No` varchar(25) DEFAULT NULL,
  `Library_location` varchar(20) DEFAULT NULL,
  `Availability_status` varchar(25) DEFAULT NULL,
  `Stock_Entry_Image` varchar(100) DEFAULT NULL,
  `Created_By` varchar(25) DEFAULT NULL,
  `Created_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Entry_id`),
  KEY `libstock_liblocId` (`Library_location`),
  KEY `libstock_pubId` (`Publisher`),
  KEY `libstock_subId` (`Supplied_By`),
  KEY `libstock_catId` (`Category`),
  KEY `libstock_subcatId` (`Class_desc`),
  KEY `libstock_subcat1Id` (`Section_desc`),
  KEY `libstock_subcat2Id` (`Division_desc`),
  CONSTRAINT `libstock_catId` FOREIGN KEY (`Category`) REFERENCES `library_category` (`category_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_liblocId` FOREIGN KEY (`Library_location`) REFERENCES `campus_library_location` (`library_loc_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_pubId` FOREIGN KEY (`Publisher`) REFERENCES `campus_library_publisher_settings` (`Entry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_subId` FOREIGN KEY (`Supplied_By`) REFERENCES `campus_library_supplier_settings` (`Entry_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_subcat1Id` FOREIGN KEY (`Section_desc`) REFERENCES `library_subcategory1` (`subcategory1_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_subcat2Id` FOREIGN KEY (`Division_desc`) REFERENCES `library_subcategory2` (`subcategory2_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `libstock_subcatId` FOREIGN KEY (`Class_desc`) REFERENCES `library_subcategory` (`subcategory_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_subscriber_details` */

CREATE TABLE `campus_library_subscriber_details` (
  `subscriberId` varchar(20) NOT NULL,
  `subscriberType` varchar(30) DEFAULT NULL,
  `subscriberNumber` varchar(30) DEFAULT NULL,
  `depositType` varchar(30) DEFAULT NULL,
  `cashAmount` varchar(30) DEFAULT NULL,
  `cardNumber` varchar(30) DEFAULT NULL,
  `checkNumber` varchar(30) DEFAULT NULL,
  `paymentDate` varchar(30) DEFAULT NULL,
  `otherName` varchar(30) DEFAULT '(NULL)',
  `otherGender` varchar(30) DEFAULT NULL,
  `otherContactNo` varchar(30) DEFAULT NULL,
  `otherAddress` varchar(150) NOT NULL,
  `otherEmail` varchar(30) DEFAULT NULL,
  `studentId` varchar(30) DEFAULT '(NULL)',
  `Class_Id` varchar(30) DEFAULT NULL,
  `section_Id` varchar(30) DEFAULT NULL,
  `staffId` varchar(30) DEFAULT '(NULL)',
  `department_Id` varchar(30) DEFAULT NULL,
  `designation_Id` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `loc_ID` varchar(30) DEFAULT NULL,
  `accyear_ID` varchar(30) DEFAULT NULL,
  `blockSubscriber` varchar(10) DEFAULT 'no',
  `cardAmount` varchar(20) DEFAULT NULL,
  `checkAmount` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`subscriberId`),
  KEY `subscriberType` (`subscriberType`,`subscriberNumber`,`otherName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_library_supplier_settings` */

CREATE TABLE `campus_library_supplier_settings` (
  `Entry_Id` varchar(20) NOT NULL,
  `Supplier_Name` varchar(100) DEFAULT NULL,
  `Supplier_Address` varchar(100) DEFAULT NULL,
  `E_Mail` varchar(50) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `Tele_Phone` varchar(15) DEFAULT NULL,
  `Mobile_Number` varchar(15) DEFAULT NULL,
  `Sup_Date` date DEFAULT NULL,
  `Created_By` varchar(20) DEFAULT NULL,
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Modified_By` varchar(20) DEFAULT NULL,
  `Modified_Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Entry_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_location` */

CREATE TABLE `campus_location` (
  `Location_Id` varchar(20) NOT NULL,
  `Cust_Id` varchar(20) DEFAULT NULL,
  `Location_Name` varchar(250) DEFAULT NULL,
  `Location_Address` text,
  `Location_Phone` varchar(20) DEFAULT NULL,
  `emailId` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `board` varchar(100) DEFAULT NULL,
  `affilation` varchar(50) DEFAULT NULL,
  `schoolcode` varchar(50) DEFAULT NULL,
  `schoollogo` varchar(100) DEFAULT NULL,
  `boardlogo` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `Remarks` varchar(250) DEFAULT NULL,
  `CreateUser` varchar(50) DEFAULT NULL,
  `CreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifyUser` varchar(50) DEFAULT NULL,
  `ModifyDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Location_Id`),
  KEY `cust_id` (`Cust_Id`),
  CONSTRAINT `cust_id` FOREIGN KEY (`Cust_Id`) REFERENCES `campus_customer_details` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_marks` */

CREATE TABLE `campus_marks` (
  `StudentId` varchar(20) DEFAULT NULL,
  `Exam` varchar(10) DEFAULT NULL,
  `ScoredMarks` varchar(10) DEFAULT NULL,
  `TotalMarks` varchar(10) DEFAULT NULL,
  `Accyear` varchar(10) DEFAULT NULL,
  `Classs` varchar(10) DEFAULT NULL,
  `LocationId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_month_payroll` */

CREATE TABLE `campus_month_payroll` (
  `Emp_Id` varchar(20) DEFAULT NULL,
  `No_of_Actualdays` varchar(10) DEFAULT NULL,
  `PayableDays` varbinary(10) DEFAULT NULL,
  `Basic` double DEFAULT NULL,
  `DA` double DEFAULT NULL,
  `HRA` double DEFAULT NULL,
  `Conviance` double DEFAULT NULL,
  `performace_incentive` double DEFAULT NULL,
  `Others` double DEFAULT NULL,
  `food_allowance` double DEFAULT NULL,
  `special_allowance` double DEFAULT NULL,
  `child_edu` double DEFAULT NULL,
  `arrears` double DEFAULT NULL,
  `reimbursement` double DEFAULT NULL,
  `internet` double DEFAULT NULL,
  `drivery_sal` double DEFAULT NULL,
  `leaveEncash` double DEFAULT NULL,
  `medical` double DEFAULT NULL,
  `gross_salary` double DEFAULT NULL,
  `Employee_Pf` double DEFAULT NULL,
  `Employer_Pf` double DEFAULT NULL,
  `Employee_esi` double DEFAULT NULL,
  `Employer_esi` double DEFAULT NULL,
  `P_Tax` double DEFAULT NULL,
  `IncomeTax` double DEFAULT NULL,
  `Salary_Advance` double DEFAULT NULL,
  `Leave_Deductions` double DEFAULT NULL,
  `Other_Deductions` double DEFAULT NULL,
  `Total_Deductions` double DEFAULT NULL,
  `Net_Salary` double DEFAULT NULL,
  `salary_pending` double DEFAULT NULL,
  `CTC` double DEFAULT NULL,
  `Month` varchar(10) DEFAULT NULL,
  `Year` varchar(10) DEFAULT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `UpdatedDate` datetime DEFAULT NULL,
  `locationId` varchar(25) DEFAULT NULL,
  `accyearId` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_new_leave_bank` */

CREATE TABLE `campus_new_leave_bank` (
  `Leave_ID` varchar(10) NOT NULL,
  `Leave_name` varchar(50) DEFAULT NULL,
  `No_Of_Leaves` varchar(100) DEFAULT '0',
  `ShortName` varchar(10) DEFAULT NULL,
  `Cat_Id` varchar(10) NOT NULL,
  `Accy_Id` varchar(20) NOT NULL,
  `Loc_ID` varchar(20) NOT NULL,
  `created time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Leave_ID`),
  KEY `newleave_locId` (`Loc_ID`),
  KEY `newleave_accyId` (`Accy_Id`),
  KEY `newleave_catId` (`Cat_Id`),
  CONSTRAINT `newleave_accyId` FOREIGN KEY (`Accy_Id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `newleave_catId` FOREIGN KEY (`Cat_Id`) REFERENCES `campus_category_master` (`Cat_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `newleave_locId` FOREIGN KEY (`Loc_ID`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_occupation` */

CREATE TABLE `campus_occupation` (
  `occupationId` varchar(20) NOT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdTime` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`occupationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_parent_enquiry_details` */

CREATE TABLE `campus_parent_enquiry_details` (
  `enquiry_id` varchar(20) NOT NULL,
  `student_first_name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `student_last_name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `mobile_number` varchar(30) CHARACTER SET latin1 NOT NULL,
  `address` varchar(350) CHARACTER SET latin1 DEFAULT NULL,
  `username` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `re_password` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `role` varchar(50) DEFAULT 'ENQUIRY',
  `loc_id` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  `stu_parrelationship` varchar(50) DEFAULT NULL,
  `stream_id` varchar(20) NOT NULL,
  `class_id` varchar(20) NOT NULL,
  `parents_name` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reason` varchar(100) DEFAULT NULL,
  `rejectreason` varchar(100) DEFAULT NULL,
  `cancelreason` varchar(100) DEFAULT NULL,
  `submitreason` varchar(100) DEFAULT NULL,
  `processreason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enquiry_id`),
  KEY `parenq_streamId` (`stream_id`),
  KEY `parenq_classId` (`class_id`),
  CONSTRAINT `parenq_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parenq_streamId` FOREIGN KEY (`stream_id`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_parentchildrelation` */

CREATE TABLE `campus_parentchildrelation` (
  `parentid` varchar(60) CHARACTER SET utf8 NOT NULL,
  `stu_addmissionNo` varchar(60) CHARACTER SET utf8 NOT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`parentid`,`stu_addmissionNo`),
  KEY `parrel_stuId` (`stu_addmissionNo`),
  CONSTRAINT `parrel_parId` FOREIGN KEY (`parentid`) REFERENCES `campus_parents` (`ParentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parrel_stuId` FOREIGN KEY (`stu_addmissionNo`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_parents` */

CREATE TABLE `campus_parents` (
  `ParentID` varchar(60) NOT NULL,
  `FatherName` varchar(150) DEFAULT NULL,
  `Qualification` varchar(150) DEFAULT NULL,
  `mobileno` varchar(33) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `student_father_occupation` varchar(120) DEFAULT NULL,
  `student_mothername_var` varchar(150) DEFAULT NULL,
  `student_mothermobileno_var` varchar(45) DEFAULT NULL,
  `student_motherqualification_var` varchar(60) DEFAULT NULL,
  `student_mother_mailid` varchar(150) DEFAULT NULL,
  `student_mother_occupation` varchar(120) DEFAULT NULL,
  `student_gaurdianname_var` varchar(150) DEFAULT NULL,
  `student_gardian_mailid` varchar(150) DEFAULT NULL,
  `student_gardian_mobileno` varchar(60) DEFAULT NULL,
  `UserName` varchar(120) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `address` varchar(750) DEFAULT NULL,
  `createdby` varchar(60) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `modifiedby` varchar(150) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `pstatus` varchar(30) DEFAULT NULL,
  `fatherPanNo` varchar(50) DEFAULT NULL,
  `fatherAnnualIncome` double DEFAULT NULL,
  `motherPanNo` varchar(50) DEFAULT NULL,
  `motherAnnualIncome` double DEFAULT NULL,
  `guardianOccupation` varchar(50) DEFAULT NULL,
  `guardianPanNo` varchar(50) DEFAULT NULL,
  `guardianAnnualIncome` double DEFAULT NULL,
  `guardianQualification` varchar(50) DEFAULT NULL,
  `fatherPhoto` varchar(200) DEFAULT NULL,
  `motherPhoto` varchar(200) DEFAULT NULL,
  `guardianPhoto` varchar(200) DEFAULT NULL,
  `presentAddress` varchar(750) DEFAULT NULL,
  `fatherDesignation` varchar(50) DEFAULT '',
  `fatherDepartment` varchar(45) DEFAULT '',
  `fatherOfficeAddress` varchar(750) DEFAULT '',
  `motherDesignation` varchar(45) DEFAULT '',
  `motherDepartment` varchar(45) DEFAULT '',
  `motherOfficeAddress` varchar(750) DEFAULT '',
  `guardianDesignation` varchar(45) DEFAULT '',
  `guardianDepartment` varchar(45) DEFAULT '',
  `guardianOfficeAddress` varchar(750) DEFAULT '',
  PRIMARY KEY (`ParentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_payroll_advancedetails` */

CREATE TABLE `campus_payroll_advancedetails` (
  `userId` varchar(20) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` varchar(10) DEFAULT NULL,
  `paidamount` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_payroll_details` */

CREATE TABLE `campus_payroll_details` (
  `userId` varchar(20) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` varchar(10) DEFAULT NULL,
  `noof_leaves` float DEFAULT NULL,
  `main_adv` float DEFAULT NULL,
  `fest_Adv` float DEFAULT NULL,
  `LOP` float DEFAULT NULL,
  `salary_amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_payroll_details_board` */

CREATE TABLE `campus_payroll_details_board` (
  `userId` varchar(20) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` varchar(10) DEFAULT NULL,
  `BasicPay` float DEFAULT NULL,
  `Da` float DEFAULT NULL,
  `GradePay` float DEFAULT NULL,
  `GrossSal` float DEFAULT NULL,
  `Pf` float DEFAULT NULL,
  `OtherDeduc` float DEFAULT NULL,
  `NetSal` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_permissions` */

CREATE TABLE `campus_permissions` (
  `PermissionCode` varchar(20) NOT NULL,
  `Module` varchar(50) DEFAULT NULL,
  `SubModule` varchar(50) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `shortName` varchar(30) DEFAULT NULL,
  `isApplicable` char(1) DEFAULT NULL,
  PRIMARY KEY (`PermissionCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_project` */

CREATE TABLE `campus_project` (
  `projectCode` varchar(20) NOT NULL,
  `projectName` varchar(50) DEFAULT NULL,
  `AccYear` varchar(20) DEFAULT NULL,
  `ClassId` varchar(20) DEFAULT NULL,
  `SectionId` varchar(20) DEFAULT NULL,
  `SpecializationId` varchar(20) DEFAULT '-',
  `SubjectId` varchar(20) DEFAULT NULL,
  `LocationId` varchar(20) DEFAULT NULL,
  `AssignedDate` date DEFAULT NULL,
  `SubmissionDate` date DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `MaxMarks` double DEFAULT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`projectCode`),
  KEY `project_locId` (`LocationId`),
  KEY `project_accId` (`AccYear`),
  KEY `project_clsId` (`ClassId`),
  KEY `project_secId` (`SectionId`),
  KEY `project_subId` (`SubjectId`),
  CONSTRAINT `project_accId` FOREIGN KEY (`AccYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_clsId` FOREIGN KEY (`ClassId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_locId` FOREIGN KEY (`LocationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_secId` FOREIGN KEY (`SectionId`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_project_studentwise` */

CREATE TABLE `campus_project_studentwise` (
  `sno` int(20) NOT NULL AUTO_INCREMENT,
  `AcademicYearId` varchar(20) DEFAULT NULL,
  `LocationId` varchar(20) DEFAULT NULL,
  `projectCode` varchar(20) DEFAULT NULL,
  `studentId` varchar(20) DEFAULT NULL,
  `specializationId` varchar(20) DEFAULT '-',
  `SubmissionDate` date DEFAULT NULL,
  `MaxMarks` double DEFAULT NULL,
  `obtainedMarks` double DEFAULT NULL,
  `Remarks` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT 'Assigned',
  `CreatedBy` varchar(20) NOT NULL,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `projstu_accyId` (`AcademicYearId`),
  KEY `projstu_locId` (`LocationId`),
  KEY `projstu_projId` (`projectCode`),
  KEY `projstu_stuId` (`studentId`),
  CONSTRAINT `projstu_accyId` FOREIGN KEY (`AcademicYearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projstu_locId` FOREIGN KEY (`LocationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projstu_projId` FOREIGN KEY (`projectCode`) REFERENCES `campus_project` (`projectCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projstu_stuId` FOREIGN KEY (`studentId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_religion` */

CREATE TABLE `campus_religion` (
  `religionId` varchar(20) NOT NULL,
  `religion` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(150) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdTime` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedTime` varchar(50) DEFAULT NULL,
  `description` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`religionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_role` */

CREATE TABLE `campus_role` (
  `RoleCode` varchar(20) NOT NULL,
  `RoleName` varchar(50) DEFAULT NULL,
  `IsDefault` char(1) DEFAULT 'N',
  `Description` varchar(250) DEFAULT NULL,
  `createdby` varchar(20) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedby` varchar(20) DEFAULT NULL,
  `modifiedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_role_permissions_mapping` */

CREATE TABLE `campus_role_permissions_mapping` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `permissionCode` varchar(20) NOT NULL,
  `roleCode` varchar(20) NOT NULL,
  `shortName` varchar(25) DEFAULT NULL,
  `isApplicable` varchar(6) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`sno`),
  UNIQUE KEY `sno` (`sno`),
  KEY `rolepermission_rolecode` (`roleCode`),
  CONSTRAINT `rolepermission_rolecode` FOREIGN KEY (`roleCode`) REFERENCES `campus_role` (`RoleCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=88072 DEFAULT CHARSET=latin1;

/*Table structure for table `campus_scholorship` */

CREATE TABLE `campus_scholorship` (
  `admissionNo` varchar(45) DEFAULT NULL,
  `classId` varchar(45) DEFAULT NULL,
  `termcode` varchar(45) DEFAULT NULL,
  `feecode` varchar(45) DEFAULT NULL,
  `concessionType` varchar(10) DEFAULT NULL,
  `concession` double DEFAULT NULL,
  `academic_year` varchar(45) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remark` varchar(250) DEFAULT NULL,
  KEY `scholr_classId` (`classId`),
  KEY `scholr_accyId` (`academic_year`),
  CONSTRAINT `scholr_accyId` FOREIGN KEY (`academic_year`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scholr_classId` FOREIGN KEY (`classId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_settings` */

CREATE TABLE `campus_settings` (
  `webserver_path` varchar(500) DEFAULT NULL,
  `URL` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_staff_house_rent` */

CREATE TABLE `campus_staff_house_rent` (
  `TeacherID` varchar(20) DEFAULT NULL,
  `houseRentFromDate` date DEFAULT NULL,
  `houseRentToDate` date DEFAULT NULL,
  `houseRentCity` varchar(25) DEFAULT NULL,
  `houseRentLandlordName` varchar(40) DEFAULT NULL,
  `houseRentAddress` varchar(250) DEFAULT NULL,
  `houseRentPANNo` varchar(25) DEFAULT NULL,
  `houseRentDeclaredAmount` double DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  KEY `staffhouse_teaId` (`TeacherID`),
  KEY `staffhouse_locId` (`locationId`),
  KEY `staffhouse_accyId` (`accyearId`),
  CONSTRAINT `staffhouse_accyId` FOREIGN KEY (`accyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staffhouse_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staffhouse_teaId` FOREIGN KEY (`TeacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_staff_income_section` */

CREATE TABLE `campus_staff_income_section` (
  `TeacherID` varchar(20) DEFAULT NULL,
  `incomeOtherThanSalary` double DEFAULT '0',
  `incomeOtherHousingLoanLetout` double DEFAULT '0',
  `incomeOtherHousingLoanSelf` double DEFAULT '0',
  `incomeOtherTotal` double DEFAULT '0',
  `exemptHRA` double DEFAULT '0',
  `exemptChildEdu` double DEFAULT '0',
  `exemptMedReimb` double DEFAULT '0',
  `exemptTransAllowance` double DEFAULT '0',
  `exemptLTA` double DEFAULT '0',
  `exemptFoodCoupon` double DEFAULT '0',
  `exemptTelephoneReimb` double DEFAULT '0',
  `exemptCarExpReimb` double DEFAULT '0',
  `exemptInternetExp` double DEFAULT '0',
  `exemptDriverSal` double DEFAULT '0',
  `exemptOtherExp` double DEFAULT '0',
  `exemptTotal` double DEFAULT '0',
  `section80CEmpPF` double DEFAULT '0',
  `section80CTutionFee` double DEFAULT '0',
  `section80CFixedDeposit` double DEFAULT '0',
  `section80CLIC` double DEFAULT '0',
  `section80CMutualFund` double DEFAULT '0',
  `section80CNationalSaveCerti` double DEFAULT '0',
  `section80CAccruNSC` double DEFAULT '0',
  `section80CPublicPF` double DEFAULT '0',
  `section80CRepayHousingLoan` double DEFAULT '0',
  `section80CULIP` double DEFAULT '0',
  `section80CSeniorCitizenSaving` double DEFAULT '0',
  `section80CPostSaving` double DEFAULT '0',
  `section80COther` double DEFAULT '0',
  `section80CTotal` double DEFAULT '0',
  `section80DMediClaim1` double DEFAULT '0',
  `section80DMediClaim2` double DEFAULT '0',
  `section80DMediClaim3` double DEFAULT '0',
  `section80DDBMedTreatment1` double DEFAULT '0',
  `section80DDBMedTreatment2` double DEFAULT '0',
  `section80EEduLoanInterest` double DEFAULT '0',
  `section80CCDNPS` double DEFAULT '0',
  `section80UDeduction1` double DEFAULT '0',
  `section80UDeduction2` double DEFAULT '0',
  `section80CCGRajivEquitySaving` double DEFAULT '0',
  `section80TTASavingBankInterest` double DEFAULT '0',
  `section80DDMedTreatment1` double DEFAULT '0',
  `section80DDMedTreatment2` double DEFAULT '0',
  `section80D80UTotal` double DEFAULT '0',
  `created_date` date DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  KEY `staffincome_teaId` (`TeacherID`),
  KEY `staffincome_locId` (`locationId`),
  KEY `staffincome_accyId` (`accyearId`),
  CONSTRAINT `staffincome_accyId` FOREIGN KEY (`accyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staffincome_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staffincome_teaId` FOREIGN KEY (`TeacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_staff_salary_maxlimit` */

CREATE TABLE `campus_staff_salary_maxlimit` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `incomeOtherHousingLoanLetout` double DEFAULT NULL,
  `incomeOtherHousingLoanSelf` double DEFAULT NULL,
  `exemptMedReimb` double DEFAULT NULL,
  `exemptTransAllowance` double DEFAULT NULL,
  `section80C` double DEFAULT NULL,
  `section80DMediClaim1` double DEFAULT NULL,
  `section80DMediClaim2` double DEFAULT NULL,
  `section80DMediClaim3` double DEFAULT NULL,
  `section80DDBMedTreatment1` double DEFAULT NULL,
  `section80DDBMedTreatment2` double DEFAULT NULL,
  `section80CCDNPS` double DEFAULT NULL,
  `section80UDeduction1` double DEFAULT NULL,
  `section80UDeduction2` double DEFAULT NULL,
  `section80CCGRajivEquitySaving` double DEFAULT NULL,
  `section80TTASavingBankInterest` double DEFAULT NULL,
  `section80DDMedTreatment1` double DEFAULT NULL,
  `section80DDMedTreatment2` double DEFAULT NULL,
  `pfAmount` double DEFAULT NULL,
  `pfPercentage` float DEFAULT NULL,
  `esiAmount` double DEFAULT NULL,
  `esiPercentage` float DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `staffsal_accyId` (`accyearId`),
  KEY `staffsal_locId` (`locationId`),
  CONSTRAINT `staffsal_accyId` FOREIGN KEY (`accyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `staffsal_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_staff_salarydetails` */

CREATE TABLE `campus_staff_salarydetails` (
  `TeacherID` varchar(20) NOT NULL,
  `BankAccNumber` varchar(50) DEFAULT NULL,
  `EmployeePfNo` varchar(20) DEFAULT NULL,
  `paymentType` varchar(20) DEFAULT NULL,
  `ESI_No` varchar(20) DEFAULT NULL,
  `PanNo` varchar(20) DEFAULT NULL,
  `Basic` double DEFAULT '0',
  `Hra` double DEFAULT '0',
  `Convenience` double DEFAULT '0',
  `GrossSalary` double DEFAULT '0',
  `Ca` double DEFAULT '0',
  `Da` double DEFAULT '0',
  `EmployerPf` double DEFAULT '0',
  `EmployeePf` double DEFAULT '0',
  `IncomeTax` double DEFAULT '0',
  `Pt` double DEFAULT '0',
  `OtherDeductions` double DEFAULT '0',
  `LeaveDeductions` char(1) DEFAULT NULL,
  `LateDeductions` char(1) DEFAULT NULL,
  `IsApplicableForOt` char(1) DEFAULT NULL,
  `MedicalAllowances` double DEFAULT '0',
  `Others` double DEFAULT '0',
  `CTC` double DEFAULT '0',
  `BankName` varchar(50) DEFAULT NULL,
  `IFSCCode` varchar(20) DEFAULT NULL,
  `HRAPercentage` int(3) DEFAULT '0',
  `TelephoneAllowance` double DEFAULT '0',
  `PerformanceIncentive` double DEFAULT '0',
  `SpecialAllowance` double DEFAULT '0',
  `FoodAllowance` double DEFAULT '0',
  `WashingAllowance` double DEFAULT '0',
  `OtherAllowance` double DEFAULT '0',
  `EmployerESI` double DEFAULT '0',
  `DeduEmployerPF` double DEFAULT '0',
  `DeduEmployerESI` double DEFAULT '0',
  `EmployeeESI` double DEFAULT '0',
  `TDSCtc` double DEFAULT '0',
  `TDSStandardDeductions` double DEFAULT '0',
  `TDSTaxbleIncomeWithStatury` double DEFAULT '0',
  `TDSPT` double DEFAULT '0',
  `TDSPF` double DEFAULT '0',
  `TDSTaxbleIncome` double DEFAULT '0',
  `TDSTaxPayble` double DEFAULT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(20) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `childEdu` double DEFAULT '0',
  `arrears` double DEFAULT '0',
  `reimbursement` double DEFAULT '0',
  `internetExpense` double DEFAULT '0',
  `driverSalary` double DEFAULT '0',
  `leaveEncash` double DEFAULT '0',
  `medicalReimbursement` double DEFAULT '0',
  `totalSalary` double DEFAULT '0',
  `pfEmployerAmount` double DEFAULT '0',
  `esiEmployerAmount` double DEFAULT '0',
  `hraperamount` double DEFAULT '0',
  `TDSExemption` double DEFAULT '0',
  `TDSUnderChapterVIA` double DEFAULT '0',
  `TDSIncomeFromOther` double DEFAULT '0',
  PRIMARY KEY (`TeacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_staff_tax_tds_deduction` */

CREATE TABLE `campus_staff_tax_tds_deduction` (
  `TeacherID` varchar(20) DEFAULT NULL,
  `TDSDedMonthly1` double DEFAULT '0',
  `TDSDedMonthly2` double DEFAULT '0',
  `TDSDedMonthly3` double DEFAULT '0',
  `TDSDedMonthly4` double DEFAULT '0',
  `TDSDedMonthly5` double DEFAULT '0',
  `TDSDedMonthly6` double DEFAULT '0',
  `TDSDedMonthly7` double DEFAULT '0',
  `TDSDedMonthly8` double DEFAULT '0',
  `TDSDedMonthly9` double DEFAULT '0',
  `TDSDedMonthly10` double DEFAULT '0',
  `TDSDedMonthly11` double DEFAULT '0',
  `TDSDedMonthly12` double DEFAULT '0',
  `TDSDedMonthlyTotal` double DEFAULT '0',
  `taxableExemption` double DEFAULT '0',
  `taxableAnyOtherIncome` double DEFAULT '0',
  `taxworkUnderCapterVIA` double DEFAULT '0',
  `taxworkTaxableIncome` double DEFAULT '0',
  `taxworkTotalTax` double DEFAULT '0',
  `taxworkSurcharge` double DEFAULT '0',
  `taxworkEducationalCess` double DEFAULT '0',
  `taxworkNetTax` double DEFAULT '0',
  `taxworkToBeDeducted` double DEFAULT '0',
  `taxworkPerMonth` double DEFAULT '0',
  `created_date` date DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  KEY `tdsdeduction_teaId` (`TeacherID`),
  KEY `tdsdeduction_locId` (`locationId`),
  KEY `tdsdeduction_accyId` (`accyearId`),
  CONSTRAINT `tdsdeduction_accyId` FOREIGN KEY (`accyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tdsdeduction_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tdsdeduction_teaId` FOREIGN KEY (`TeacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_staff_tds_computation` */

CREATE TABLE `campus_staff_tds_computation` (
  `TeacherID` varchar(20) DEFAULT NULL,
  `pfMonthlyEmplrAmount` double DEFAULT NULL,
  `pfAmount` double DEFAULT NULL,
  `esiMonthlyAmount` double DEFAULT NULL,
  `esiAmount` double DEFAULT NULL,
  `salaryMonthlyBasic` double DEFAULT NULL,
  `salaryMonthlyDA` double DEFAULT NULL,
  `salaryMonthlyHRA` double DEFAULT NULL,
  `salaryMonthlyTransConveyAllow` double DEFAULT NULL,
  `salaryMonthlyChildEdu` double DEFAULT NULL,
  `salaryMonthlySplAllowance` double DEFAULT NULL,
  `salaryMonthlyArrears` double DEFAULT NULL,
  `salaryMonthlyPerformIncentive` double DEFAULT NULL,
  `salaryMonthlyMedicalReimb` double DEFAULT NULL,
  `salaryMonthlyLeaveEncash` double DEFAULT NULL,
  `salaryMonthlyFoodCoupon` double DEFAULT NULL,
  `salaryMonthlyReimbursement` double DEFAULT NULL,
  `salaryMonthlyInternetExp` double DEFAULT NULL,
  `salaryMonthlyDriversalary` double DEFAULT NULL,
  `salaryMonthlyOther` double DEFAULT NULL,
  `salaryMonthlyTotal` double DEFAULT NULL,
  `salaryGrossBasic` double DEFAULT NULL,
  `salaryGrossDA` double DEFAULT NULL,
  `salaryGrossHRA` double DEFAULT NULL,
  `salaryGrossTransConveyAllow` double DEFAULT NULL,
  `salaryGrossChildEdu` double DEFAULT NULL,
  `salaryGrossSplAllowance` double DEFAULT NULL,
  `salaryGrossArrears` double DEFAULT NULL,
  `salaryGrossPerformIncentive` double DEFAULT NULL,
  `salaryGrossMedicalReimb` double DEFAULT NULL,
  `salaryGrossLeaveEncash` double DEFAULT NULL,
  `salaryGrossFoodCoupon` double DEFAULT NULL,
  `salaryGrossReimbursement` double DEFAULT NULL,
  `salaryGrossInternetExp` double DEFAULT NULL,
  `salaryGrossDriverGross` double DEFAULT NULL,
  `salaryGrossOther` double DEFAULT NULL,
  `salaryGrossTotal` double DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `accyearId` varchar(11) DEFAULT NULL,
  KEY `stafftdscomputation_locId` (`locationId`),
  KEY `stafftdscomputation_accId` (`accyearId`),
  KEY `stafftdscomputation_teaId` (`TeacherID`),
  CONSTRAINT `stafftdscomputation_accId` FOREIGN KEY (`accyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stafftdscomputation_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stafftdscomputation_teaId` FOREIGN KEY (`TeacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student` */

CREATE TABLE `campus_student` (
  `student_id_int` varchar(60) NOT NULL,
  `student_rollno` varchar(60) DEFAULT NULL,
  `student_application_no` varchar(60) DEFAULT NULL,
  `student_admissionno_var` varchar(60) NOT NULL,
  `student_doj_var` date DEFAULT NULL,
  `fms_acadamicyear_id_int` varchar(20) DEFAULT NULL,
  `student_regno_var` varchar(75) DEFAULT NULL,
  `student_fname_var` varchar(150) DEFAULT NULL,
  `student_lname_var` varchar(150) DEFAULT NULL,
  `student_dob_var` date DEFAULT NULL,
  `student_gender_var` varchar(30) DEFAULT NULL,
  `student_bloodgroup_var` varchar(30) DEFAULT NULL,
  `student_age_int` int(11) DEFAULT NULL,
  `student_religion_var` varchar(20) DEFAULT NULL,
  `student_caste` varchar(20) DEFAULT NULL,
  `casteCategory` varchar(20) DEFAULT NULL,
  `student_nationality_var` varchar(75) DEFAULT NULL,
  `student_physicallychallenged` varchar(60) DEFAULT NULL,
  `physicallychallenged_reason` varchar(750) DEFAULT NULL,
  `student_identificationmarks_var` varchar(750) DEFAULT NULL,
  `student_identificationmarks1_var` varchar(750) DEFAULT NULL,
  `adharNo` varchar(50) DEFAULT NULL,
  `motherTounge` varchar(50) DEFAULT NULL,
  `medium` varchar(50) DEFAULT NULL,
  `student_siblingId` varchar(60) DEFAULT NULL,
  `annualParentsIncome` double DEFAULT '0',
  `transferCertificateNo` varchar(50) DEFAULT NULL,
  `isParentsGuardianWorking` varchar(3) DEFAULT 'No',
  `workingParentsGuardianId` varchar(50) DEFAULT NULL,
  `workingParentsGuardianName` varchar(100) DEFAULT NULL,
  `student_imgurl_var` varchar(600) DEFAULT NULL,
  `student_tc_path` varchar(600) DEFAULT NULL,
  `student_birthcert_path` varchar(600) DEFAULT NULL,
  `certificate1` varchar(200) DEFAULT NULL,
  `certificate2` varchar(200) DEFAULT NULL,
  `certificate3` varchar(200) DEFAULT NULL,
  `certificate4` varchar(200) DEFAULT NULL,
  `certificate5` varchar(200) DEFAULT NULL,
  `student_status_var` varchar(20) DEFAULT 'active',
  `remarks` varchar(250) DEFAULT NULL,
  `tempadmissionregno` varchar(25) DEFAULT NULL,
  `student_grade` varchar(30) DEFAULT NULL,
  `student_quota` varchar(60) DEFAULT NULL,
  `isHostel` varchar(60) DEFAULT NULL,
  `isConcession` varchar(60) DEFAULT NULL,
  `isRTE` varchar(30) DEFAULT NULL,
  `emisNo` varchar(60) DEFAULT NULL,
  `student_scholorship_var` varchar(60) DEFAULT NULL,
  `student_prehistory_var` varchar(750) DEFAULT NULL,
  `student_remarks_var` varchar(750) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `createuser` varchar(60) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifyuser` varchar(60) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`student_id_int`),
  UNIQUE KEY `admission` (`student_admissionno_var`),
  KEY `stu_reliId` (`student_religion_var`),
  KEY `stu_casteId` (`student_caste`),
  KEY `stu_casteCat` (`casteCategory`),
  KEY `stu_locId` (`locationId`),
  CONSTRAINT `stu_casteCat` FOREIGN KEY (`casteCategory`) REFERENCES `campus_caste_category` (`castCatId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu_casteId` FOREIGN KEY (`student_caste`) REFERENCES `campus_caste` (`casteId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu_reliId` FOREIGN KEY (`student_religion_var`) REFERENCES `campus_religion` (`religionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_age_certificate` */

CREATE TABLE `campus_student_age_certificate` (
  `AgeCertificate_id` varchar(50) NOT NULL,
  `Accyear_id` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `stuId` varchar(60) DEFAULT NULL,
  `classId` varchar(20) DEFAULT NULL,
  `sectionId` varchar(20) DEFAULT NULL,
  `purpose` varchar(250) DEFAULT NULL,
  `otherInfo` varchar(250) DEFAULT NULL,
  `studentStatus` varchar(25) DEFAULT NULL,
  `entryDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`AgeCertificate_id`),
  KEY `age_stuId` (`stuId`),
  KEY `age_classId` (`classId`),
  KEY `age_secId` (`sectionId`),
  KEY `age_locId` (`loc_id`),
  KEY `age_accyId` (`Accyear_id`),
  CONSTRAINT `age_accyId` FOREIGN KEY (`Accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `age_classId` FOREIGN KEY (`classId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `age_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `age_secId` FOREIGN KEY (`sectionId`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `age_stuId` FOREIGN KEY (`stuId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_appraisal` */

CREATE TABLE `campus_student_appraisal` (
  `student_appraisal_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(60) DEFAULT NULL,
  `academic_id` varchar(11) DEFAULT NULL,
  `location_id` varchar(20) DEFAULT NULL,
  `action_taken` varchar(100) DEFAULT NULL,
  `schedule_date` date DEFAULT NULL,
  `recommented_by` varchar(100) DEFAULT NULL,
  `meeting_date` date DEFAULT NULL,
  `meeting_with` varchar(50) DEFAULT NULL,
  `meeting_on` varchar(50) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `status` varchar(25) DEFAULT NULL,
  `remarks` varchar(250) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(25) DEFAULT NULL,
  `modify_date` varchar(25) DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`student_appraisal_id`),
  KEY `appraisal_stuId` (`student_id`),
  KEY `appraisal_accyId` (`academic_id`),
  KEY `appraisal_locId` (`location_id`),
  CONSTRAINT `appraisal_accyId` FOREIGN KEY (`academic_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appraisal_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appraisal_stuId` FOREIGN KEY (`student_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_bonafied_certificate` */

CREATE TABLE `campus_student_bonafied_certificate` (
  `bonafiedcertiid` varchar(25) NOT NULL,
  `stuid` varchar(60) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `sectionid` varchar(20) DEFAULT NULL,
  `accyearid` varchar(11) DEFAULT NULL,
  `locid` varchar(20) DEFAULT NULL,
  `purpose` varchar(100) DEFAULT NULL,
  `otherinfo` varchar(200) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `studentstatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bonafiedcertiid`),
  KEY `bonafied_stuId` (`stuid`),
  KEY `bonafied_accId` (`accyearid`),
  KEY `bonafied_locId` (`locid`),
  KEY `bonafied_classId` (`classid`),
  KEY `bonafied_secId` (`sectionid`),
  CONSTRAINT `bonafied_accId` FOREIGN KEY (`accyearid`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bonafied_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bonafied_locId` FOREIGN KEY (`locid`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bonafied_secId` FOREIGN KEY (`sectionid`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bonafied_stuId` FOREIGN KEY (`stuid`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_classdetails` */

CREATE TABLE `campus_student_classdetails` (
  `student_id_int` varchar(60) NOT NULL,
  `fms_acadamicyear_id_int` varchar(20) NOT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `fms_classstream_id_int` varchar(20) DEFAULT NULL,
  `classdetail_id_int` varchar(20) DEFAULT NULL,
  `classsection_id_int` varchar(20) DEFAULT NULL,
  `specilization` varchar(20) DEFAULT '-',
  `preferedlocationId` varchar(50) DEFAULT NULL,
  `firstlanguage` varchar(60) DEFAULT NULL,
  `secondlanguage` varchar(60) DEFAULT NULL,
  `thirdlanguage` varchar(60) DEFAULT NULL,
  `student_rollno` varchar(60) DEFAULT NULL,
  `student_house` varchar(20) DEFAULT NULL,
  `student_imgurl_var` varchar(600) DEFAULT NULL,
  `student_status` varchar(90) DEFAULT 'STUDYING',
  `student_promotionstatus` varchar(60) DEFAULT 'NOTPROMOTED',
  `createuser` varchar(60) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifyuser` varchar(60) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`student_id_int`,`fms_acadamicyear_id_int`),
  KEY `classId` (`classdetail_id_int`),
  KEY `locationId` (`locationId`),
  KEY `accyear` (`fms_acadamicyear_id_int`),
  KEY `stucls_strId` (`fms_classstream_id_int`),
  KEY `stucls_secId` (`classsection_id_int`),
  CONSTRAINT `stucls_accyId` FOREIGN KEY (`fms_acadamicyear_id_int`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stucls_classId` FOREIGN KEY (`classdetail_id_int`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stucls_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stucls_secId` FOREIGN KEY (`classsection_id_int`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stucls_strId` FOREIGN KEY (`fms_classstream_id_int`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stucls_stuId` FOREIGN KEY (`student_id_int`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_conduct_certificate` */

CREATE TABLE `campus_student_conduct_certificate` (
  `conductedcetificate_id` varchar(25) NOT NULL,
  `accyear_id` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `stu_id` varchar(60) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `section_id` varchar(20) DEFAULT NULL,
  `purpose` varchar(200) DEFAULT NULL,
  `otherinfo` varchar(200) DEFAULT NULL,
  `conductno` varchar(50) DEFAULT NULL,
  `conduct` varchar(200) DEFAULT NULL,
  `studentstatus` varchar(50) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createddate` date DEFAULT NULL,
  PRIMARY KEY (`conductedcetificate_id`),
  KEY `conduct_stuId` (`stu_id`),
  KEY `conduct_accId` (`accyear_id`),
  KEY `conduct_locId` (`loc_id`),
  KEY `conduct_classId` (`class_id`),
  KEY `conduct_secId` (`section_id`),
  CONSTRAINT `conduct_accId` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conduct_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conduct_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conduct_secId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conduct_stuId` FOREIGN KEY (`stu_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_course_conduct_cetificate` */

CREATE TABLE `campus_student_course_conduct_cetificate` (
  `course_conduct_certifi_id` varchar(25) NOT NULL,
  `accyear_id` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  `stu_id` varchar(60) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `section_id` varchar(20) DEFAULT NULL,
  `purpose` varchar(100) DEFAULT NULL,
  `otherinfo` varchar(200) DEFAULT NULL,
  `conductno` varchar(25) DEFAULT NULL,
  `conduct` varchar(200) DEFAULT NULL,
  `studentstatus` varchar(100) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createddate` date DEFAULT NULL,
  PRIMARY KEY (`course_conduct_certifi_id`),
  KEY `course_accyId` (`accyear_id`),
  KEY `course_locId` (`loc_id`),
  KEY `course_classId` (`class_id`),
  KEY `course_secId` (`section_id`),
  KEY `course_stuId` (`stu_id`),
  CONSTRAINT `course_accyId` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_secId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_stuId` FOREIGN KEY (`stu_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_house` */

CREATE TABLE `campus_student_house` (
  `student_id` varchar(60) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `section_id` varchar(20) DEFAULT NULL,
  `house_id` varchar(20) DEFAULT NULL,
  `academic_year` varchar(11) DEFAULT NULL,
  `loc_id` varchar(20) DEFAULT NULL,
  KEY `stuhou_stuId` (`student_id`),
  KEY `stuhou_accyId` (`academic_year`),
  KEY `stuhou_locId` (`loc_id`),
  KEY `stuhou_classId` (`class_id`),
  KEY `stuhou_secId` (`section_id`),
  KEY `stuhou_houId` (`house_id`),
  CONSTRAINT `stuhou_accyId` FOREIGN KEY (`academic_year`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuhou_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuhou_houId` FOREIGN KEY (`house_id`) REFERENCES `campus_house_settings` (`house_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuhou_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuhou_secId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuhou_stuId` FOREIGN KEY (`student_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_promotion` */

CREATE TABLE `campus_student_promotion` (
  `studentpromotion_id_int` int(11) NOT NULL AUTO_INCREMENT,
  `student_admissionno_var` varchar(60) CHARACTER SET utf8 NOT NULL,
  `student_id_int` varchar(60) CHARACTER SET utf8 NOT NULL,
  `studentpromotion_fromclass_var` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_toclass_var` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_acadamicyearfrom_var` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_acadamicyearto_var` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_fromsection_var` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_tosection_var` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_fromstream` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_tostream` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `studentpromotion_fromSpecialization` varchar(20) DEFAULT NULL,
  `studentpromotion_toSpecialization` varchar(20) DEFAULT NULL,
  `studentpromotion_status` varchar(20) DEFAULT NULL,
  `studentdemoted_region` varchar(100) DEFAULT NULL,
  `createuser` varchar(20) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  `modifyuser` varchar(20) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`studentpromotion_id_int`),
  KEY `stuprom_toaccId` (`studentpromotion_acadamicyearto_var`),
  KEY `stuprom_locId` (`locationId`),
  KEY `stuprom_stuId` (`student_id_int`),
  CONSTRAINT `stuprom_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuprom_stuId` FOREIGN KEY (`student_id_int`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuprom_toaccId` FOREIGN KEY (`studentpromotion_acadamicyearto_var`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Table structure for table `campus_student_transportdetails` */

CREATE TABLE `campus_student_transportdetails` (
  `student_id_int` varchar(60) NOT NULL,
  `fms_acadamicyear_id_int` varchar(20) NOT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `isTransport` varchar(60) DEFAULT NULL,
  `TransportType` varchar(90) DEFAULT NULL,
  `VehicleCode` varchar(60) DEFAULT NULL,
  `StageId` varchar(10) DEFAULT NULL,
  `route` varchar(50) DEFAULT NULL,
  `start_month` varchar(25) DEFAULT NULL,
  `end_month` varchar(25) DEFAULT NULL,
  `NumberOfMonth` int(11) DEFAULT NULL,
  `createuser` varchar(60) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifyuser` varchar(60) DEFAULT NULL,
  `modifydate` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`student_id_int`,`fms_acadamicyear_id_int`),
  KEY `stutras_accyId` (`fms_acadamicyear_id_int`),
  KEY `stutras_locId` (`locationId`),
  CONSTRAINT `stutras_accyId` FOREIGN KEY (`fms_acadamicyear_id_int`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stutras_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stutras_stuId` FOREIGN KEY (`student_id_int`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_visa_certificate` */

CREATE TABLE `campus_student_visa_certificate` (
  `studentvisa_id` varchar(25) NOT NULL,
  `accyear_id` varchar(25) DEFAULT NULL,
  `loc_id` varchar(25) DEFAULT NULL,
  `stu_id` varchar(25) DEFAULT NULL,
  `class_id` varchar(25) DEFAULT NULL,
  `section_id` varchar(25) DEFAULT NULL,
  `purpose` varchar(200) DEFAULT NULL,
  `passportNo` varchar(50) DEFAULT NULL,
  `studentstatus` varchar(50) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`studentvisa_id`),
  KEY `stuvisacert_accId` (`accyear_id`),
  KEY `stuvisacert_locId` (`loc_id`),
  KEY `stuvisacert_stuId` (`stu_id`),
  KEY `stuvisacert_clsId` (`class_id`),
  KEY `stuvisacert_secId` (`section_id`),
  CONSTRAINT `stuvisacert_accId` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuvisacert_clsId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuvisacert_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuvisacert_secId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuvisacert_stuId` FOREIGN KEY (`stu_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_student_withheld` */

CREATE TABLE `campus_student_withheld` (
  `withheld_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(10) DEFAULT NULL,
  `academicyear_id` varchar(11) DEFAULT NULL,
  `location_id` varchar(10) DEFAULT NULL,
  `withheld_status` varchar(20) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `withheld_reason` text,
  `withheld_date` date DEFAULT NULL,
  `withheld_cancel_reason` text,
  `withheld_cancelled_date` date DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(20) DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `modify_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`withheld_id`),
  KEY `stuwithheld_locId` (`location_id`),
  KEY `stuwithheld_stuId` (`student_id`),
  KEY `stuwithheld_accId` (`academicyear_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `campus_students_contacts` */

CREATE TABLE `campus_students_contacts` (
  `studentId` varchar(60) DEFAULT NULL,
  `emergencyNo` varchar(20) DEFAULT NULL,
  `smsNo` varchar(20) DEFAULT NULL,
  KEY `stucont_stuId` (`studentId`),
  CONSTRAINT `stucont_stuId` FOREIGN KEY (`studentId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_studentwise_mark_details` */

CREATE TABLE `campus_studentwise_mark_details` (
  `Academic_yearid` varchar(11) DEFAULT NULL,
  `stu_id` varchar(60) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Stu_mark_id` varchar(30) NOT NULL,
  `exam_id` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `admission_no` varchar(50) DEFAULT NULL,
  `classid` varchar(30) NOT NULL,
  `sectionid` varchar(30) NOT NULL,
  `student_name` varchar(30) DEFAULT NULL,
  `exam_code` varchar(30) DEFAULT NULL,
  `exam_name` varchar(30) DEFAULT NULL,
  `subject_code` varchar(25) DEFAULT NULL,
  `subject_name` varchar(25) DEFAULT NULL,
  `attendance_status` varchar(100) DEFAULT NULL,
  `total_marks` varchar(25) DEFAULT NULL,
  `pass_marks` varchar(25) DEFAULT NULL,
  `scored_marks` varchar(30) DEFAULT NULL,
  `sub_id` varchar(30) NOT NULL,
  `location_id` varchar(30) NOT NULL,
  PRIMARY KEY (`Stu_mark_id`),
  KEY `stuwisemarkdetail_accId` (`Academic_yearid`),
  KEY `stuwisemarkdetail_examId` (`exam_id`),
  KEY `stuwisemarkdetail_classId` (`classid`),
  KEY `stuwisemarkdetail_section` (`sectionid`),
  KEY `stuwisemarkdetail_subId` (`sub_id`),
  KEY `stuwisemarkdetail_locId` (`location_id`),
  KEY `stuwisemarkdetail_stuId` (`stu_id`),
  CONSTRAINT `stuwisemarkdetail_accId` FOREIGN KEY (`Academic_yearid`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuwisemarkdetail_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuwisemarkdetail_examId` FOREIGN KEY (`exam_id`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuwisemarkdetail_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuwisemarkdetail_section` FOREIGN KEY (`sectionid`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stuwisemarkdetail_stuId` FOREIGN KEY (`stu_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_subject` */

CREATE TABLE `campus_subject` (
  `subjectID` varchar(20) NOT NULL,
  `subjectName` varchar(50) NOT NULL,
  `syllabous` varchar(200) NOT NULL DEFAULT '',
  `classid` varchar(20) NOT NULL,
  `decription` varchar(500) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'active',
  `createdby` varchar(20) NOT NULL,
  `ctrate time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateby` varchar(20) DEFAULT NULL,
  `updatedtime` timestamp NULL DEFAULT NULL,
  `totalMarks` int(11) DEFAULT NULL,
  `passMarks` int(11) DEFAULT NULL,
  `Sub_Code` varchar(10) DEFAULT NULL,
  `locationId` varchar(20) DEFAULT NULL,
  `specialization` varchar(20) DEFAULT NULL,
  `isLanguage` varchar(25) DEFAULT 'N',
  PRIMARY KEY (`subjectID`),
  KEY `sub_locId` (`locationId`),
  KEY `sub_classId` (`classid`),
  KEY `sub_specialization` (`specialization`),
  CONSTRAINT `sub_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sub_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_subject_marks_wise` */

CREATE TABLE `campus_subject_marks_wise` (
  `Sub_marks_id` varchar(25) NOT NULL,
  `Accyear_Id` varchar(25) DEFAULT NULL,
  `classId` varchar(25) NOT NULL,
  `ExamId` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `SectionId` varchar(35) NOT NULL,
  `subject_id` varchar(30) NOT NULL,
  `loc_id` varchar(30) DEFAULT NULL,
  `StudentId` varchar(60) NOT NULL,
  `scoredmarks` varchar(50) DEFAULT NULL,
  `statusvalues` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Sub_marks_id`),
  KEY `submarkwisew_accyearId` (`Accyear_Id`),
  KEY `submarkwisew_classId` (`classId`),
  KEY `submarkwisew_examId` (`ExamId`),
  KEY `submarkwisew_section` (`SectionId`),
  KEY `submarkwisew_subId` (`subject_id`),
  KEY `submarkwisew_locId` (`loc_id`),
  KEY `submarkwisew_stuId` (`StudentId`),
  CONSTRAINT `submarkwisew_accyearId` FOREIGN KEY (`Accyear_Id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_classId` FOREIGN KEY (`classId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_examId` FOREIGN KEY (`ExamId`) REFERENCES `campus_examination` (`examid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_section` FOREIGN KEY (`SectionId`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_stuId` FOREIGN KEY (`StudentId`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `submarkwisew_subId` FOREIGN KEY (`subject_id`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_tc_details` */

CREATE TABLE `campus_tc_details` (
  `TCCode` varchar(30) NOT NULL,
  `TCStatus` varchar(20) DEFAULT 'Generated',
  `issueDate` date NOT NULL,
  `issuedBy` varchar(20) DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `acadamic_year` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `student_id_int` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `admissionNo` varchar(20) DEFAULT NULL,
  `latestClassID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `spec_id` varchar(20) DEFAULT NULL,
  `school_or_board_Examination` varchar(50) DEFAULT NULL,
  `reason` varchar(20) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  `last_date_stu_attend` varchar(10) DEFAULT NULL,
  `total_meeting` int(20) DEFAULT NULL,
  `meeting_attain` int(20) DEFAULT NULL,
  `genral_conduct` varchar(250) DEFAULT NULL,
  `nooftime_stu_fail` int(20) DEFAULT NULL,
  `fee_paid_status` varchar(25) DEFAULT NULL,
  `last_fepaid_date` varchar(10) DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdby` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TCCode`),
  KEY `tc_locId` (`locationId`),
  KEY `tc_accyId` (`acadamic_year`),
  KEY `tc_classId` (`latestClassID`),
  KEY `tc_stuId` (`student_id_int`),
  CONSTRAINT `tc_accyId` FOREIGN KEY (`acadamic_year`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tc_classId` FOREIGN KEY (`latestClassID`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tc_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tc_stuId` FOREIGN KEY (`student_id_int`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_teacher_attendence` */

CREATE TABLE `campus_teacher_attendence` (
  `TeacherID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `TeacherName` varchar(50) DEFAULT NULL,
  `AttendenceStatus` varchar(100) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `attendance_count` double DEFAULT NULL,
  `AttendenceDate` date NOT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(25) DEFAULT NULL,
  `isReconciled` varchar(50) DEFAULT NULL,
  `InTime` time DEFAULT NULL,
  `OutTime` time DEFAULT NULL,
  `createuser` varchar(25) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  `modifyuser` varchar(25) DEFAULT NULL,
  `modifydate` date DEFAULT NULL,
  `ETL_CODE` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`TeacherID`,`AttendenceDate`),
  KEY `teaatt_locId` (`locationId`),
  CONSTRAINT `teaatt_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teaatt_teaId` FOREIGN KEY (`TeacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_teacher_new_leave_bank_details` */

CREATE TABLE `campus_teacher_new_leave_bank_details` (
  `AccYearCode` varchar(11) DEFAULT NULL,
  `EmpId` varchar(20) DEFAULT NULL,
  `Leave_Type` varchar(50) NOT NULL,
  `Leave_Name` varchar(100) DEFAULT NULL,
  `total_available` varchar(100) DEFAULT NULL,
  `total_consumed` double DEFAULT '0',
  `total_avaliable_leaves` double DEFAULT '0',
  `LastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `Date_Of_Join` date DEFAULT NULL,
  `LOC_Id` varchar(100) DEFAULT NULL,
  `January` varchar(25) DEFAULT NULL,
  `February` varchar(25) DEFAULT NULL,
  `March` varchar(25) DEFAULT NULL,
  `April` varchar(25) DEFAULT NULL,
  `May` varchar(25) DEFAULT NULL,
  `June` varchar(25) DEFAULT NULL,
  `July` varchar(25) DEFAULT NULL,
  `August` varchar(25) DEFAULT NULL,
  `September` varchar(25) DEFAULT NULL,
  `October` varchar(25) DEFAULT NULL,
  `November` varchar(25) DEFAULT NULL,
  `December` varchar(25) DEFAULT NULL,
  KEY `newleavebankdetail_locId` (`LOC_Id`),
  KEY `newleavebankdetail_EmpId` (`EmpId`),
  KEY `newleavebankdetail_AccId` (`AccYearCode`),
  KEY `newleavebankdetail_leaveId` (`Leave_Type`),
  CONSTRAINT `newleavebankdetail_AccId` FOREIGN KEY (`AccYearCode`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `newleavebankdetail_EmpId` FOREIGN KEY (`EmpId`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `newleavebankdetail_leaveId` FOREIGN KEY (`Leave_Type`) REFERENCES `campus_new_leave_bank` (`Leave_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `newleavebankdetail_locId` FOREIGN KEY (`LOC_Id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_teachers` */

CREATE TABLE `campus_teachers` (
  `TeacherID` varchar(20) NOT NULL,
  `Abbreviative_Id` varchar(20) CHARACTER SET latin1 NOT NULL,
  `FirstName` varchar(50) CHARACTER SET latin1 NOT NULL,
  `LastName` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `registerId` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `department` varchar(20) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `qualification` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `dateofJoining` date NOT NULL,
  `teachingType` varchar(250) CHARACTER SET latin1 NOT NULL,
  `primarySubject` varchar(11) CHARACTER SET latin1 DEFAULT NULL,
  `secondarySubject` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `dateofBirth` date NOT NULL,
  `mobileNo` varchar(15) CHARACTER SET latin1 NOT NULL,
  `emailId` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `bankname` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `accountnumber` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `pannumber` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `bloodgroup` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `imagePath` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `idProofPath` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `profilePath` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `fathername` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `mothername` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `presentAddress` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `permanentAddress` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `username` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `reportingTo` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `isActive` char(1) CHARACTER SET latin1 DEFAULT 'Y',
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(20) CHARACTER SET latin1 NOT NULL,
  `updateddate` datetime DEFAULT NULL,
  `updatedby` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `role` varchar(20) CHARACTER SET latin1 DEFAULT 'NULL',
  `document1` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `document2` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `document3` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `document4` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `document5` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `is_student_studying` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `studentName` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `student_admission_id` varchar(60) DEFAULT 'NULL',
  `fatherMobile` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `motherMobile` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `aadhaarnumber` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `maritalstatus` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `spousename` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `spouseMobile` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `Loc_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TeacherID`),
  KEY `teacherID` (`TeacherID`),
  KEY `tea_deptId` (`department`),
  KEY `tea_desiId` (`designation`),
  KEY `tea_locId` (`Loc_ID`),
  CONSTRAINT `tea_deptId` FOREIGN KEY (`department`) REFERENCES `campus_department` (`DEPT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tea_desiId` FOREIGN KEY (`designation`) REFERENCES `campus_designation` (`DesignationCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tea_locId` FOREIGN KEY (`Loc_ID`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_teachers_leave_request` */

CREATE TABLE `campus_teachers_leave_request` (
  `SNO` int(11) NOT NULL AUTO_INCREMENT,
  `NoofLeaves` double DEFAULT NULL,
  `ReasonForLeave` varchar(1000) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `SessionStart` varchar(20) DEFAULT NULL,
  `SessionEnd` varchar(20) DEFAULT NULL,
  `RequestedTo` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `LeaveType` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `RequestedBy` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `RequestedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filepaath` varchar(150) DEFAULT NULL,
  `LeaveStatus` varchar(20) DEFAULT NULL,
  `ApprovedBy` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `AprovedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `TotalDaysAproved` double DEFAULT '0',
  `ApproveSessionStart` varchar(20) DEFAULT NULL,
  `ApproveSessionEnd` varchar(20) DEFAULT NULL,
  `commennts` varchar(250) DEFAULT NULL,
  `ApprovedStartDate` date DEFAULT NULL,
  `ApprovedEndDate` date DEFAULT NULL,
  PRIMARY KEY (`SNO`),
  KEY `leavereq_reqTo` (`RequestedTo`),
  KEY `leavereq_reqBy` (`RequestedBy`),
  KEY `leavereq_leaveId` (`LeaveType`),
  KEY `leavereq_aproveId` (`ApprovedBy`),
  CONSTRAINT `leavereq_aproveId` FOREIGN KEY (`ApprovedBy`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leavereq_leaveId` FOREIGN KEY (`LeaveType`) REFERENCES `campus_new_leave_bank` (`Leave_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leavereq_reqBy` FOREIGN KEY (`RequestedBy`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leavereq_reqTo` FOREIGN KEY (`RequestedTo`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_teachersettings` */

CREATE TABLE `campus_teachersettings` (
  `teacherID` varchar(10) CHARACTER SET utf8 NOT NULL,
  `classID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `subjectID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `locationId` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` varchar(20) DEFAULT NULL,
  KEY `campus_teachersettings_campus_classdetail_fk` (`classID`),
  KEY `campus_teachersettings_ccampus_teachers_fk` (`teacherID`),
  KEY `campus_teachersettings_campus_subject_fk12` (`subjectID`),
  KEY `teasub_locId` (`locationId`),
  CONSTRAINT `teasub_classId` FOREIGN KEY (`classID`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teasub_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teasub_subId` FOREIGN KEY (`subjectID`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teasub_teaId` FOREIGN KEY (`teacherID`) REFERENCES `campus_teachers` (`TeacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `campus_temporary_admisssion_details` */

CREATE TABLE `campus_temporary_admisssion_details` (
  `temporary_admission_id` varchar(20) CHARACTER SET latin1 NOT NULL,
  `studentfirstName` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `studentlastname` varchar(50) DEFAULT NULL,
  `dateofBirthId` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `stream` varchar(20) DEFAULT NULL,
  `classname` varchar(20) DEFAULT NULL,
  `group_name` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `accyear` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fathername` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `fathermobileno` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `fatheremailid` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `addressofcommunication` varchar(300) CHARACTER SET latin1 DEFAULT NULL,
  `relationship` varchar(50) CHARACTER SET latin1 DEFAULT 'father',
  `alternateMobileNo` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `alternateemailId` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `advertisement` varchar(50) CHARACTER SET latin1 DEFAULT 'true',
  `paper` varchar(50) CHARACTER SET latin1 DEFAULT 'true',
  `websites` varchar(50) CHARACTER SET latin1 DEFAULT 'true',
  `channels` varchar(50) CHARACTER SET latin1 DEFAULT 'true',
  `othersreligion` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `parents` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `current_user_portal` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `percentage` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `previous_classname` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `group_name1` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `application_recieved_by` varchar(50) CHARACTER SET latin1 DEFAULT 'ONLINE',
  `created_date` timestamp NULL DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `status` varchar(50) CHARACTER SET latin1 DEFAULT 'submitted',
  `modified_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `modified_time` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `appointment_date` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `appointment_time` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `evaluation_done_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `evaluated_date` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `test_type` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `max_marks` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `marks_secured` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `evaluation_recomendation_status` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `evaluator_remarks` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `is_director_approved` varchar(50) CHARACTER SET latin1 DEFAULT 'NOT APPROVED',
  `director_remarks` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `mothername` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `motheremailid` varchar(75) DEFAULT NULL,
  `mothermobile` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `formno` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL,
  `fatherqualification` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fatheroccupation` varchar(20) CHARACTER SET latin1 DEFAULT 'NULL',
  `fatherdesignation` varchar(25) DEFAULT NULL,
  `fatherdepartment` varchar(25) DEFAULT NULL,
  `fathermonthincome` varchar(50) DEFAULT NULL,
  `motherqualification` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `motherdesignation` varchar(25) DEFAULT NULL,
  `motheroccupation` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `motherdepartment` varchar(25) DEFAULT NULL,
  `mothermothlyincome` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `fatherofficialaddress` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `motherofficialaddress` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `nationality` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `religion` varchar(20) DEFAULT NULL,
  `state` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `caste` varchar(20) DEFAULT NULL,
  `mothertongue` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `siblingname` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `siblingclass` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fatheraluminiyear` varchar(10) DEFAULT NULL,
  `motheraluminiyear` varchar(10) DEFAULT NULL,
  `permanentaddress` varchar(120) DEFAULT NULL,
  `isaluminiparents` varchar(50) DEFAULT NULL,
  `isaluminifathername` varchar(25) DEFAULT NULL,
  `isaluminimothername` varchar(25) DEFAULT NULL,
  `issibilings` varchar(20) DEFAULT NULL,
  `BirthCertificateFile` varchar(200) DEFAULT NULL,
  `imageUrl` varchar(100) DEFAULT NULL,
  `createdTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `temp_admreg_id` varchar(25) DEFAULT NULL,
  `add_photo` varchar(50) DEFAULT NULL,
  `schl_adm_id` varchar(30) DEFAULT NULL,
  `aadharNo` varchar(20) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `preferedphno` varchar(12) DEFAULT NULL,
  `schoollocation` varchar(25) DEFAULT NULL,
  `schemeofstudy` varchar(20) DEFAULT NULL,
  `otherboard` varchar(20) DEFAULT NULL,
  `secondlanguage` varchar(25) DEFAULT NULL,
  `thirdlanguage` varchar(25) DEFAULT NULL,
  `extraactivity` varchar(300) DEFAULT NULL,
  `siblingid` varchar(20) DEFAULT NULL,
  `othercaste` varchar(25) DEFAULT NULL,
  `enquiryid` varchar(20) DEFAULT NULL,
  `castecategory` varchar(20) DEFAULT NULL,
  `othercastecategory` varchar(90) DEFAULT NULL,
  `placeofbirth` varchar(50) DEFAULT NULL,
  `group_prefered` varchar(25) DEFAULT NULL,
  `previousschool` varchar(50) DEFAULT NULL,
  `qualify_name` varchar(25) DEFAULT NULL,
  `opt_subjects` varchar(25) DEFAULT NULL,
  `marksheetupload` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`temporary_admission_id`),
  KEY `tempadmi_classId` (`classname`),
  KEY `tempadmi_reliId` (`religion`),
  KEY `tempadmi_casteId` (`caste`),
  KEY `tempadmi_castecatId` (`castecategory`),
  KEY `tempadmi_strmId` (`stream`),
  KEY `tempadmi_enqId` (`enquiryid`),
  CONSTRAINT `tempadmi_casteId` FOREIGN KEY (`caste`) REFERENCES `campus_caste` (`casteId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tempadmi_castecatId` FOREIGN KEY (`castecategory`) REFERENCES `campus_caste_category` (`castCatId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tempadmi_classId` FOREIGN KEY (`classname`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tempadmi_enqId` FOREIGN KEY (`enquiryid`) REFERENCES `campus_parent_enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tempadmi_reliId` FOREIGN KEY (`religion`) REFERENCES `campus_religion` (`religionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tempadmi_strmId` FOREIGN KEY (`stream`) REFERENCES `campus_classstream` (`classstream_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_timetable_day` */

CREATE TABLE `campus_timetable_day` (
  `daycode` varchar(20) NOT NULL,
  `dayname` varchar(20) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'N',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`daycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_timetable_student` */

CREATE TABLE `campus_timetable_student` (
  `timetable_id` varchar(20) NOT NULL,
  `classid` varchar(20) NOT NULL,
  `sectionid` varchar(20) NOT NULL,
  `accyearid` varchar(20) NOT NULL,
  `locationId` varchar(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`timetable_id`),
  KEY `student_timetable_classId` (`classid`),
  KEY `student_timetable_secId` (`sectionid`),
  KEY `student_timetable_accyId` (`accyearid`),
  KEY `student_timetable_locId` (`locationId`),
  CONSTRAINT `student_timetable_accyId` FOREIGN KEY (`accyearid`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_timetable_classId` FOREIGN KEY (`classid`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_timetable_locId` FOREIGN KEY (`locationId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_timetable_secId` FOREIGN KEY (`sectionid`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_timetable_studentdetails` */

CREATE TABLE `campus_timetable_studentdetails` (
  `timetableid` varchar(20) NOT NULL,
  `daycode` varchar(20) NOT NULL,
  `period1` varchar(30) DEFAULT NULL,
  `period2` varchar(30) DEFAULT NULL,
  `period3` varchar(30) DEFAULT NULL,
  `period4` varchar(30) DEFAULT NULL,
  `period5` varchar(30) DEFAULT NULL,
  `period6` varchar(30) DEFAULT NULL,
  `period7` varchar(30) DEFAULT NULL,
  `period8` varchar(30) DEFAULT NULL,
  `period9` varchar(30) DEFAULT NULL,
  KEY `timestudetails_timetableId` (`timetableid`),
  KEY `timestudetails_dayCode` (`daycode`),
  KEY `timestudetails_subId` (`period3`),
  CONSTRAINT `timestudetails_dayCode` FOREIGN KEY (`daycode`) REFERENCES `campus_timetable_day` (`daycode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `timestudetails_timetableId` FOREIGN KEY (`timetableid`) REFERENCES `campus_timetable_student` (`timetable_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_timetable_teacherdetails` */

CREATE TABLE `campus_timetable_teacherdetails` (
  `teachertimetable_id` varchar(20) NOT NULL,
  `daycode` varchar(20) NOT NULL,
  `period1` varchar(30) DEFAULT NULL,
  `period2` varchar(30) DEFAULT NULL,
  `period3` varchar(30) DEFAULT NULL,
  `period4` varchar(30) DEFAULT NULL,
  `period5` varchar(30) DEFAULT NULL,
  `period6` varchar(30) DEFAULT NULL,
  `period7` varchar(30) DEFAULT NULL,
  `period8` varchar(30) DEFAULT NULL,
  `period9` varchar(30) DEFAULT NULL,
  KEY `teatime_daycode` (`daycode`),
  CONSTRAINT `teatime_daycode` FOREIGN KEY (`daycode`) REFERENCES `campus_timetable_day` (`daycode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_tranport_fee_collection_details` */

CREATE TABLE `campus_tranport_fee_collection_details` (
  `admissionNo` varchar(60) DEFAULT NULL,
  `accYear` varchar(20) DEFAULT NULL,
  `termcode` varchar(20) DEFAULT NULL,
  `MonthName` varchar(50) DEFAULT NULL,
  `reciept_no` int(20) NOT NULL,
  `is_paid` char(27) DEFAULT NULL,
  `totalamount` double DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `balance_amount` double DEFAULT NULL,
  `paidDate` date DEFAULT NULL,
  `modeofpayment` varchar(50) DEFAULT NULL,
  `bankname` varchar(100) DEFAULT NULL,
  `dd_cheque_no` varchar(25) DEFAULT NULL,
  `dd_cheque_date` varchar(11) DEFAULT NULL,
  `createdby` varchar(540) DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedby` varchar(540) DEFAULT NULL,
  `updatedtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `reciept_no` (`reciept_no`),
  KEY `tranfeecoldetail_accId` (`accYear`),
  KEY `tranfeecoldetail_termId` (`termcode`),
  KEY `tranfeecoldetail_stuId` (`admissionNo`),
  CONSTRAINT `tranfeecoldetail_accId` FOREIGN KEY (`accYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tranfeecoldetail_stuId` FOREIGN KEY (`admissionNo`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tranfeecoldetail_termId` FOREIGN KEY (`termcode`) REFERENCES `campus_fee_transport_termdetails` (`termid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_transport_fees_payments` */

CREATE TABLE `campus_transport_fees_payments` (
  `receiptno` varchar(50) DEFAULT NULL,
  `totalamt` double DEFAULT NULL,
  `paidAmount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `advance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `campus_user` */

CREATE TABLE `campus_user` (
  `usercode` varchar(25) NOT NULL,
  `employeecode` varchar(25) DEFAULT NULL,
  `cust_id` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `app_userid` varchar(25) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `emailId` varchar(60) DEFAULT NULL,
  `mobileno` varchar(10) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL,
  `isActive` char(1) DEFAULT 'Y',
  `remarks` varchar(250) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `locationId` varchar(25) DEFAULT NULL,
  `createuser` varchar(25) DEFAULT NULL,
  `createdate` datetime NOT NULL,
  `modifyuser` varchar(25) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`usercode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `laboratory_details` */

CREATE TABLE `laboratory_details` (
  `lab_id` varchar(50) NOT NULL,
  `School_Name` varchar(50) DEFAULT NULL,
  `Class_Name` varchar(50) DEFAULT NULL,
  `Subject` varchar(50) DEFAULT NULL,
  `Lab_Name` varchar(50) DEFAULT NULL,
  `Total_Marks` int(50) DEFAULT NULL,
  `Specialization` varchar(50) DEFAULT NULL,
  `Pass_Marks` int(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Lab_Code` varchar(50) DEFAULT NULL,
  `Syllabus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`lab_id`),
  KEY `lab_locId` (`School_Name`),
  KEY `lab_classId` (`Class_Name`),
  KEY `lab_subId` (`Subject`),
  KEY `lab_specId` (`Specialization`),
  CONSTRAINT `lab_classId` FOREIGN KEY (`Class_Name`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lab_locId` FOREIGN KEY (`School_Name`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lab_subId` FOREIGN KEY (`Subject`) REFERENCES `campus_subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `latecomers_sms` */

CREATE TABLE `latecomers_sms` (
  `Late_Comers_Code` varchar(10) NOT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `AccyearId` varchar(11) DEFAULT NULL,
  `Student_Code` varchar(60) DEFAULT NULL,
  `Class_Code` varchar(20) DEFAULT NULL,
  `Section_Code` varchar(20) DEFAULT NULL,
  `Date` varchar(20) DEFAULT NULL,
  `Title` varchar(50) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `CreatedUser` varchar(20) DEFAULT NULL,
  `Created_Time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Late_Comers_Code`),
  KEY `latecdomerssms_classId` (`Class_Code`),
  KEY `latecdomerssms_sectionId` (`Section_Code`),
  KEY `latecdomerssms_stuId` (`Student_Code`),
  KEY `latecdomerssms_locId` (`LocId`),
  KEY `latecdomerssms_accyearId` (`AccyearId`),
  CONSTRAINT `latecdomerssms_accyearId` FOREIGN KEY (`AccyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `latecdomerssms_classId` FOREIGN KEY (`Class_Code`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `latecdomerssms_locId` FOREIGN KEY (`LocId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `latecdomerssms_sectionId` FOREIGN KEY (`Section_Code`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `latecdomerssms_stuId` FOREIGN KEY (`Student_Code`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `library_category` */

CREATE TABLE `library_category` (
  `category_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(20) NOT NULL,
  `category_name` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(50) DEFAULT 'Active',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `modify_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`,`category_code`),
  UNIQUE KEY `category_code` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `library_subcategory` */

CREATE TABLE `library_subcategory` (
  `subcategory_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(20) DEFAULT NULL,
  `subcategory_code` varchar(20) NOT NULL,
  `subcategory_name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Active',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(20) DEFAULT NULL,
  `modifydate` date DEFAULT NULL,
  `modifyby` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`subcategory_id`,`subcategory_code`),
  UNIQUE KEY `subcategory_code` (`subcategory_code`),
  KEY `libcat_catId` (`category_code`),
  CONSTRAINT `libcat_catId` FOREIGN KEY (`category_code`) REFERENCES `library_category` (`category_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `library_subcategory1` */

CREATE TABLE `library_subcategory1` (
  `subcategory1_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(20) DEFAULT NULL,
  `subcategory_code` varchar(20) DEFAULT NULL,
  `subcategory1_code` varchar(20) NOT NULL,
  `subcategory1_name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Active',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(20) DEFAULT NULL,
  `modifydate` date DEFAULT NULL,
  `modifyby` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`subcategory1_id`,`subcategory1_code`),
  KEY `subcat1_subcat1Id` (`subcategory_code`),
  KEY `subcat1_catId` (`category_code`),
  KEY `subcategory1_code` (`subcategory1_code`),
  CONSTRAINT `subcat1_catId` FOREIGN KEY (`category_code`) REFERENCES `library_category` (`category_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat1_subcat1Id` FOREIGN KEY (`subcategory_code`) REFERENCES `library_subcategory` (`subcategory_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `library_subcategory2` */

CREATE TABLE `library_subcategory2` (
  `subcategory2_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(20) DEFAULT NULL,
  `subcategory_code` varchar(20) DEFAULT NULL,
  `subcategory1_code` varchar(20) DEFAULT NULL,
  `subcategory2_code` varchar(20) NOT NULL,
  `subcategory2_name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Active',
  `createdtime` timestamp NULL DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `modify_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`subcategory2_id`,`subcategory2_code`),
  KEY `subcat2_catId` (`category_code`),
  KEY `subcat2_subcatId` (`subcategory_code`),
  KEY `subcat2_subcat1Id` (`subcategory1_code`),
  KEY `subcategory2_code` (`subcategory2_code`),
  CONSTRAINT `subcat2_catId` FOREIGN KEY (`category_code`) REFERENCES `library_category` (`category_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat2_subcat1Id` FOREIGN KEY (`subcategory1_code`) REFERENCES `library_subcategory1` (`subcategory1_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat2_subcatId` FOREIGN KEY (`subcategory_code`) REFERENCES `library_subcategory` (`subcategory_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `library_subcategory3` */

CREATE TABLE `library_subcategory3` (
  `subcategory4_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(20) DEFAULT NULL,
  `subcategory_code` varchar(20) DEFAULT NULL,
  `subcategory1_code` varchar(20) DEFAULT NULL,
  `subcategory2_code` varchar(20) DEFAULT NULL,
  `subcategory3_code` varchar(20) NOT NULL,
  `subcategory3_name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modifyby` varchar(25) DEFAULT NULL,
  `modifydate` date DEFAULT NULL,
  PRIMARY KEY (`subcategory4_id`,`subcategory3_code`),
  KEY `subcat3_catId` (`category_code`),
  KEY `subcat3_subcatId` (`subcategory_code`),
  KEY `subcat3_subcat1Id` (`subcategory1_code`),
  KEY `subcat3_subcat2Id` (`subcategory2_code`),
  CONSTRAINT `subcat3_catId` FOREIGN KEY (`category_code`) REFERENCES `library_category` (`category_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat3_subcat1Id` FOREIGN KEY (`subcategory1_code`) REFERENCES `library_subcategory1` (`subcategory1_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat3_subcat2Id` FOREIGN KEY (`subcategory2_code`) REFERENCES `library_subcategory2` (`subcategory2_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcat3_subcatId` FOREIGN KEY (`subcategory_code`) REFERENCES `library_subcategory` (`subcategory_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `licenseapi_customer_details` */

CREATE TABLE `licenseapi_customer_details` (
  `requestId` int(30) NOT NULL AUTO_INCREMENT,
  `customerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  `productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `log_activity` */

CREATE TABLE `log_activity` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(25) DEFAULT NULL,
  `module_name` varchar(30) DEFAULT NULL,
  `sub_module_name` varchar(50) DEFAULT NULL,
  `action_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `activity` varchar(50) DEFAULT NULL,
  `activity_sql` text,
  PRIMARY KEY (`sno`),
  KEY `logactivity_sessionId` (`session_id`),
  CONSTRAINT `logactivity_sessionId` FOREIGN KEY (`session_id`) REFERENCES `log_audit` (`session_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3972 DEFAULT CHARSET=utf8;

/*Table structure for table `log_audit` */

CREATE TABLE `log_audit` (
  `session_id` varchar(25) NOT NULL,
  `cust_id` varchar(25) DEFAULT NULL,
  `token_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(25) DEFAULT NULL,
  `ip_address` varchar(20) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `total_time` time DEFAULT NULL,
  `isActive` char(1) DEFAULT 'N',
  `msgId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `polling_verification_voter_list` */

CREATE TABLE `polling_verification_voter_list` (
  `voterId` varchar(20) DEFAULT NULL,
  `academicYearId` varchar(20) DEFAULT NULL,
  `electionGroupId` varchar(50) DEFAULT NULL,
  `electionTitleId` varchar(50) DEFAULT NULL,
  KEY `pollingverificationvoterlist_accId` (`academicYearId`),
  KEY `cationvoterlist_groupId` (`electionGroupId`),
  KEY `cationvoterlist_titleId` (`electionTitleId`),
  CONSTRAINT `cationvoterlist_groupId` FOREIGN KEY (`electionGroupId`) REFERENCES `campus_election_group_settings` (`election_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cationvoterlist_titleId` FOREIGN KEY (`electionTitleId`) REFERENCES `campus_election_election_setting` (`electionSettingID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pollingverificationvoterlist_accId` FOREIGN KEY (`academicYearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ps_campus_customer_details` */

CREATE TABLE `ps_campus_customer_details` (
  `order_id` varchar(20) DEFAULT NULL,
  `old_cust_id` varchar(20) DEFAULT NULL,
  `new_cust_id` varchar(20) DEFAULT NULL,
  `puchesed_product` varchar(20) DEFAULT NULL,
  `purchesed_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `licenseKey` varchar(20) DEFAULT NULL,
  `licenseExpairyDate` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_absent_details` */

CREATE TABLE `sms_absent_details` (
  `ABSENT_CODE` varchar(50) NOT NULL,
  `ABSENT_DATE` date DEFAULT NULL,
  `ABSENT_CONTENT` varchar(1000) DEFAULT NULL,
  `IsSection` int(1) DEFAULT NULL,
  `IsStudent` int(1) DEFAULT NULL,
  `AccyearId` varchar(11) DEFAULT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `TemplateCode` varchar(20) DEFAULT NULL,
  `SMS_STATUS` varchar(50) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `MODIFIED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ABSENT_CODE`),
  KEY `smsabsentdetail_LocId` (`LocId`),
  KEY `smsabsentdetail_accyearId` (`AccyearId`),
  CONSTRAINT `smsabsentdetail_LocId` FOREIGN KEY (`LocId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `smsabsentdetail_accyearId` FOREIGN KEY (`AccyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_absent_section` */

CREATE TABLE `sms_absent_section` (
  `ABSENT_CODE` varchar(50) DEFAULT NULL,
  `CLASS_ID` varchar(20) DEFAULT NULL,
  `SECTION_CODE` varchar(20) DEFAULT NULL,
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  KEY `smsabsentsec_sectionId` (`SECTION_CODE`),
  KEY `smsabsentsec_classId` (`CLASS_ID`),
  CONSTRAINT `smsabsentsec_classId` FOREIGN KEY (`CLASS_ID`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `smsabsentsec_sectionId` FOREIGN KEY (`SECTION_CODE`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_absent_student` */

CREATE TABLE `sms_absent_student` (
  `ABSENT_CODE` varchar(50) DEFAULT NULL,
  `STUDENT_ADM_NO` varchar(50) DEFAULT NULL,
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  KEY `smsabsent_stuId` (`STUDENT_ADM_NO`),
  CONSTRAINT `smsabsent_stuId` FOREIGN KEY (`STUDENT_ADM_NO`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_audit` */

CREATE TABLE `sms_audit` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `smsdate` date DEFAULT NULL,
  `templateCode` varchar(20) CHARACTER SET latin1 NOT NULL,
  `ServiceCode` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `sms_response` varchar(300) CHARACTER SET latin1 NOT NULL,
  `approve_time` timestamp NULL DEFAULT NULL,
  `senttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_time` timestamp NULL DEFAULT NULL,
  `sms_content` varchar(1000) DEFAULT NULL,
  `sms_status` varchar(250) CHARACTER SET latin1 NOT NULL,
  `sendby` varchar(20) CHARACTER SET latin1 NOT NULL,
  `approved_by` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `createdby` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `student_admission_No` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `delivery_status` varchar(50) DEFAULT NULL,
  `msgId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Table structure for table `sms_meeting` */

CREATE TABLE `sms_meeting` (
  `meetingid` varchar(20) CHARACTER SET latin1 NOT NULL,
  `meetingdate` varchar(50) DEFAULT NULL,
  `title` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `locId` varchar(20) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `sectionid` varchar(20) DEFAULT NULL,
  `starttime` varchar(50) DEFAULT NULL,
  `endtime` varchar(50) NOT NULL,
  `subjectname` varchar(20) DEFAULT NULL,
  `studentname` varchar(60) DEFAULT NULL,
  `description` varchar(500) CHARACTER SET latin1 DEFAULT NULL,
  `TemplateCode` varchar(20) DEFAULT NULL,
  `createuser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `accyear` varchar(11) DEFAULT NULL,
  `venuedetails` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`meetingid`),
  KEY `smsmeeting_classId` (`classid`),
  KEY `smsmeeting_sectionId` (`sectionid`),
  KEY `smsmeeting_accyearId` (`accyear`),
  KEY `smsmeeting_subId` (`subjectname`),
  KEY `smsmeeting_stuId` (`studentname`),
  KEY `smsmeeting_locId` (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_suddenholidays_details` */

CREATE TABLE `sms_suddenholidays_details` (
  `SUDDENHOLIDAYS_CODE` varchar(50) NOT NULL,
  `SUDDENHOLIDAYS_DATE` date DEFAULT NULL,
  `SUDDENHOLIDAYS_CONTENT` varchar(160) DEFAULT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `isSection` int(1) DEFAULT NULL,
  `isStudent` int(1) DEFAULT NULL,
  `TemplateCode` varchar(20) DEFAULT NULL,
  `SMS_STATUS` varchar(50) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `MODIFIED_TIME` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SUDDENHOLIDAYS_CODE`),
  KEY `sudhalidetail_locId` (`LocId`),
  CONSTRAINT `sudhalidetail_locId` FOREIGN KEY (`LocId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sms_suddenholidays_section` */

CREATE TABLE `sms_suddenholidays_section` (
  `SUDDENHOLIDAYS_CODE` varchar(50) DEFAULT NULL,
  `CLASS_CODE` varchar(20) DEFAULT NULL,
  `SECTION_CODE` varchar(20) DEFAULT NULL,
  `ACC_YEAR` varchar(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  KEY `smssec_suddenholiId` (`SUDDENHOLIDAYS_CODE`),
  KEY `smssec_secId` (`SECTION_CODE`),
  KEY `smssec_classId` (`CLASS_CODE`),
  CONSTRAINT `smssec_suddenholiId` FOREIGN KEY (`SUDDENHOLIDAYS_CODE`) REFERENCES `sms_suddenholidays_details` (`SUDDENHOLIDAYS_CODE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `student_course_conduct_certi` */

CREATE TABLE `student_course_conduct_certi` (
  `course_conduct_cert_id` varchar(25) DEFAULT NULL,
  `accyear_id` varchar(25) DEFAULT NULL,
  `loc_id` varchar(25) DEFAULT NULL,
  `stu_id` varchar(25) DEFAULT NULL,
  `class_id` varchar(25) DEFAULT NULL,
  `section_id` varchar(25) DEFAULT NULL,
  `purpose` varchar(100) DEFAULT NULL,
  `otherinfo` varchar(200) DEFAULT NULL,
  `conductno` varchar(50) DEFAULT NULL,
  `conduct` varchar(100) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `couconduct_accyId` (`accyear_id`),
  KEY `couconduct_locId` (`loc_id`),
  KEY `couconduct_stuId` (`stu_id`),
  KEY `couconduct_classId` (`class_id`),
  KEY `couconduct_secId` (`section_id`),
  CONSTRAINT `couconduct_accyId` FOREIGN KEY (`accyear_id`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `couconduct_classId` FOREIGN KEY (`class_id`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `couconduct_locId` FOREIGN KEY (`loc_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `couconduct_secId` FOREIGN KEY (`section_id`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `couconduct_stuId` FOREIGN KEY (`stu_id`) REFERENCES `campus_student` (`student_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tds_computation_details` */

CREATE TABLE `tds_computation_details` (
  `TDS_Computation_Id` varchar(50) DEFAULT NULL,
  `AcademicYear` varchar(11) NOT NULL,
  `EmployeeId` varchar(50) DEFAULT NULL,
  `EmployeeName` varchar(50) DEFAULT NULL,
  `DateOfJoining` date DEFAULT NULL,
  `PanNumber` varchar(50) DEFAULT NULL,
  `months` int(11) DEFAULT NULL,
  `basicandda_permonth` double DEFAULT '0',
  `basicanddaperyear` double DEFAULT '0',
  `dapermonth` double DEFAULT '0',
  `daperyear` double DEFAULT '0',
  `HRA_PERMONTH` double DEFAULT '0',
  `HRA_peryear` double DEFAULT '0',
  `transport_or_conveyance_per_month` double DEFAULT '0',
  `transport_or_conveyance_peryear` double DEFAULT '0',
  `child_education_permonth` double DEFAULT '0',
  `child_education_peryear` double DEFAULT '0',
  `specialAllowances_permonth` double DEFAULT '0',
  `specialAllowances_peryear` double DEFAULT '0',
  `arrears_permonth` double DEFAULT '0',
  `arrears_peryear` double DEFAULT '0',
  `performanceIncentive_permonth` double DEFAULT '0',
  `performanceIncentive_perYear` double DEFAULT '0',
  `medicalReimbursement_permonth` double DEFAULT '0',
  `medicalReimbursement_peryear` double DEFAULT '0',
  `leave_encashment_permonth` double DEFAULT '0',
  `leaveencashment_peryear` double DEFAULT '0',
  `foodCoupons_permonth` double DEFAULT '0',
  `foodCoupons_perYear` double DEFAULT '0',
  `reimbursements_permonth` double DEFAULT '0',
  `reimbursements_peryear` double DEFAULT '0',
  `internetExpense_perMonth` double DEFAULT '0',
  `internetExpense_perYear` double DEFAULT '0',
  `driverSalary_perMonth` double DEFAULT '0',
  `driversalary_peryear` double DEFAULT '0',
  `other_one_perMonth` double DEFAULT '0',
  `other_one_perYear` double DEFAULT '0',
  `total_one_permonth` double DEFAULT '0',
  `total_one_peryear` double DEFAULT '0',
  `tds_deducted_from_salary_perMonth` double DEFAULT '0',
  `tds_deducted_from_salary_perYear` double DEFAULT '0',
  `Employees_PF_Contribution_perMonth` double DEFAULT '0',
  `Employees_PF_Contribution_perYear` double DEFAULT '0',
  `professionalTax_perMonth` double DEFAULT '0',
  `professionalTax_perYear` double DEFAULT '0',
  `netDeductions_perMonth` double DEFAULT '0',
  `netDeductions_perYear` double DEFAULT '0',
  `houseOrprperty_perMonth` double DEFAULT '0',
  `houseOrprperty_perYear` double DEFAULT '0',
  `InterestForHousingLoan_permonth` double DEFAULT '0',
  `InterestForHousingLoan_perYear` double DEFAULT '0',
  `OtherIncome_perMonth` double DEFAULT '0',
  `OtherIncome_perYear` double DEFAULT '0',
  `TotalIncome_perMonth` double DEFAULT '0',
  `TotalIncome_perYear` double DEFAULT '0',
  `hra_exemption_permonth` double DEFAULT '0',
  `hra_exemption_peryear` double DEFAULT '0',
  `child_educationSection_perMonth` double DEFAULT '0',
  `child_educationSection_perYear` double DEFAULT '0',
  `medicalReimbursementSection_perMonth` double DEFAULT '0',
  `medicalReimbursementSection_perYear` double DEFAULT '0',
  `transport_allowances_section_perMonth` double DEFAULT '0',
  `transport_allowances_section_perYear` double DEFAULT '0',
  `lta_section_permonth` double DEFAULT '0',
  `lta_section_perYear` double DEFAULT '0',
  `food_coupons_section_perMonth` double DEFAULT '0',
  `food_coupons_section_perYear` double DEFAULT '0',
  `telephone_section_permonth` double DEFAULT '0',
  `telephone_section_perYear` double DEFAULT '0',
  `car_expense_section_perMonth` double DEFAULT '0',
  `car_expense_section_perYear` double DEFAULT '0',
  `internet_section_perMonth` double DEFAULT '0',
  `internet_section_perYear` double DEFAULT '0',
  `driver_salary_section_perMonth` double DEFAULT '0',
  `driver_Salary_section_perYear` double DEFAULT '0',
  `other_Section_PerMonth` double DEFAULT '0',
  `other_Section_PerYear` double DEFAULT '0',
  `total_Section_exemption_perMonth` double DEFAULT '0',
  `total_exemption_perYear` double DEFAULT '0',
  `lic_premium_chapter_permonth` double DEFAULT '0',
  `lic_premium_chapter_perYear` double DEFAULT '0',
  `employees_contributionChapter_pf_perMonth` double DEFAULT NULL,
  `employees_contributionChapter_pf_perYear` double DEFAULT NULL,
  `ppf_chapteperMonth` double DEFAULT NULL,
  `ppf_chapterperYear` double DEFAULT NULL,
  `nsc_chapter_perMonth` double DEFAULT NULL,
  `nsc_chapter_perYear` double DEFAULT NULL,
  `insurance_premiumChaptetr_perMonth` double DEFAULT NULL,
  `insurance_premiumChapter_perYear` double DEFAULT NULL,
  `child_tutionsChapter_perMonth` double DEFAULT NULL,
  `child_tutionChapter_perYear` double DEFAULT NULL,
  `housing_loan_Chapter_per_Month` double DEFAULT NULL,
  `housing_loan_Chapter_per_Year` double DEFAULT NULL,
  `infrastructure_chapter_permonth` double DEFAULT NULL,
  `infrastructure_chapter_perYear` double DEFAULT NULL,
  `fixed_deposits_chapter_perMonth` double DEFAULT NULL,
  `fixed_deposits_chapter_perYear` double DEFAULT NULL,
  `others_chapter_perMonth` double DEFAULT NULL,
  `others_chapter_perYear` double DEFAULT NULL,
  `totalChapter_PerMonth` double DEFAULT NULL,
  `totalChapter_perYear` double DEFAULT NULL,
  `medicalInsurance_last_permonth` double DEFAULT NULL,
  `medicalInsurance_last_peryear` double DEFAULT NULL,
  `interestOnLoan_last_perMonth` double DEFAULT NULL,
  `interestOnLoan_last_perYear` double DEFAULT NULL,
  `ph_last_permonth` double DEFAULT NULL,
  `ph_last_peryear` double DEFAULT NULL,
  `medicalInsurance_parents_last_perMonth` double DEFAULT NULL,
  `medicalInsurance_parents_last_perYear` double DEFAULT NULL,
  `specifiedDiseses_Last_perMonth` double DEFAULT NULL,
  `specifiedDiseses_Last_perYear` double DEFAULT NULL,
  `handicapped_last_perMonth` double DEFAULT NULL,
  `handicapped_last_perYear` double DEFAULT NULL,
  `charity_last_perMonth` double DEFAULT NULL,
  `charity_last_perYear` double DEFAULT NULL,
  `rentDeduction_last_perMonth` double DEFAULT NULL,
  `rentdeduction_last_perYear` double DEFAULT NULL,
  `OtherDeduction_perMonth` double DEFAULT NULL,
  `OtherDeduction_perYear` double DEFAULT NULL,
  `totalDeduction_last_month` double DEFAULT NULL,
  `total_deduction_lasperYear` double DEFAULT NULL,
  KEY `tdscomputationdetail_accId` (`AcademicYear`),
  CONSTRAINT `tdscomputationdetail_accId` FOREIGN KEY (`AcademicYear`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tds_yearly_exemption_details` */

CREATE TABLE `tds_yearly_exemption_details` (
  `AcademicYear` varchar(50) DEFAULT NULL,
  `Conveyance` double DEFAULT NULL,
  `MedicalBills` double DEFAULT NULL,
  `ElssLicHousingLoans` double DEFAULT NULL,
  `Mediclaim` double DEFAULT NULL,
  `HousingLoanInterest` double DEFAULT NULL,
  `SavingsAccountIntrest` double DEFAULT NULL,
  `RGESSExempted` double DEFAULT NULL,
  `hra` double DEFAULT NULL,
  `professionaltax` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `today_timetable` */

CREATE TABLE `today_timetable` (
  `id` varchar(20) DEFAULT NULL,
  `classId` varchar(20) DEFAULT NULL,
  `sectionid` varchar(20) DEFAULT NULL,
  `accyearid` varchar(20) DEFAULT NULL,
  `period1` varchar(50) DEFAULT NULL,
  `period2` varchar(50) DEFAULT NULL,
  `period3` varchar(50) DEFAULT NULL,
  `period4` varchar(50) DEFAULT NULL,
  `period5` varchar(50) DEFAULT NULL,
  `period6` varchar(50) DEFAULT NULL,
  `period7` varchar(50) DEFAULT NULL,
  `period8` varchar(50) DEFAULT NULL,
  `period9` varchar(50) DEFAULT NULL,
  `tperiod1` varchar(50) DEFAULT NULL,
  `tperiod2` varchar(50) DEFAULT NULL,
  `tperiod3` varchar(50) DEFAULT NULL,
  `tperiod4` varchar(50) DEFAULT NULL,
  `tperiod5` varchar(50) DEFAULT NULL,
  `tperiod6` varchar(50) DEFAULT NULL,
  `tperiod7` varchar(50) DEFAULT NULL,
  `tperiod8` varchar(50) DEFAULT NULL,
  `tperiod9` varchar(50) DEFAULT NULL,
  `today` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `updatedTime` time DEFAULT NULL,
  KEY `todaytime_id` (`id`),
  KEY `todaytime_classId` (`classId`),
  KEY `todaytime_secId` (`sectionid`),
  KEY `todaytime_accyId` (`accyearid`),
  CONSTRAINT `todaytime_accyId` FOREIGN KEY (`accyearid`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `todaytime_classId` FOREIGN KEY (`classId`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `todaytime_id` FOREIGN KEY (`id`) REFERENCES `campus_timetable_student` (`timetable_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `todaytime_secId` FOREIGN KEY (`sectionid`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_driver` */

CREATE TABLE `transport_driver` (
  `DriverCode` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `FatherName` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `MobileNo` varchar(11) NOT NULL,
  `EmergencyContactNo` varchar(11) NOT NULL,
  `Experience` double NOT NULL,
  `Address` varchar(250) NOT NULL,
  `DOJ` date NOT NULL,
  `Age` int(5) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `DLNo` varchar(25) DEFAULT NULL,
  `DLIssuedDate` date DEFAULT NULL,
  `DLExpirayDate` date DEFAULT NULL,
  `LicencetoDrive` varchar(25) DEFAULT NULL,
  `DrivingLicenceFile` varchar(200) DEFAULT NULL,
  `CreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CreateUser` varchar(20) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUser` varchar(20) DEFAULT '',
  PRIMARY KEY (`DriverCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `transport_driver_vehicle_mapping` */

CREATE TABLE `transport_driver_vehicle_mapping` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `DriverCode` varchar(20) CHARACTER SET latin1 NOT NULL,
  `VehicleCode` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Createdby` varchar(20) CHARACTER SET utf8 COLLATE utf8_swedish_ci NOT NULL,
  `Createtime` datetime NOT NULL,
  `Modifiedby` varchar(20) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`DriverCode`,`VehicleCode`),
  UNIQUE KEY `Sno` (`Sno`),
  KEY `drivervehiclemap_vehicleId` (`VehicleCode`),
  CONSTRAINT `drivervehiclemap_driverId` FOREIGN KEY (`DriverCode`) REFERENCES `transport_driver` (`DriverCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `drivervehiclemap_vehicleId` FOREIGN KEY (`VehicleCode`) REFERENCES `transport_vehicle` (`VehicleCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_route` */

CREATE TABLE `transport_route` (
  `RouteCode` varchar(50) CHARACTER SET latin1 NOT NULL,
  `RouteName` varchar(50) CHARACTER SET latin1 NOT NULL,
  `TotalDistance` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `Start_Time` time DEFAULT NULL,
  `Route_No` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `End_Time` time DEFAULT NULL,
  `Route_logical_name` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `RouteType` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `Total_Stops` varchar(11) CHARACTER SET latin1 DEFAULT NULL,
  `Cost_per_person` double DEFAULT NULL,
  `isActive` char(1) CHARACTER SET latin1 DEFAULT 'N',
  `CreateDate` datetime DEFAULT NULL,
  `CreateUser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `Destination` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `HaltTime` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `location_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`RouteCode`),
  KEY `transroute_locId` (`location_id`),
  CONSTRAINT `transroute_locId` FOREIGN KEY (`location_id`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_route_stage_mapping` */

CREATE TABLE `transport_route_stage_mapping` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `RouteCode` varchar(20) CHARACTER SET latin1 NOT NULL,
  `StageCode` varchar(20) NOT NULL,
  `CreatedBy` varchar(20) DEFAULT NULL,
  `CreatedTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `loc_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  KEY `traroutstagemap_routId` (`RouteCode`),
  KEY `traroutstagemap_stgId` (`StageCode`),
  CONSTRAINT `traroutstagemap_routId` FOREIGN KEY (`RouteCode`) REFERENCES `transport_route` (`RouteCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `traroutstagemap_stgId` FOREIGN KEY (`StageCode`) REFERENCES `campus_fee_stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_typedetails` */

CREATE TABLE `transport_typedetails` (
  `type_id` varchar(20) NOT NULL,
  `type_name` varchar(30) NOT NULL,
  `type_collectFee` char(1) NOT NULL DEFAULT 'Y',
  `type_description` varchar(250) DEFAULT NULL,
  `createdby` varchar(30) NOT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedby` varchar(30) DEFAULT NULL,
  `updaeddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_vehicle` */

CREATE TABLE `transport_vehicle` (
  `VehicleCode` varchar(20) NOT NULL,
  `Vehicle_Reg_No` varchar(50) NOT NULL,
  `VehicleName` varchar(50) NOT NULL,
  `VehicleType` varchar(50) NOT NULL,
  `Type_Of_Body` varchar(50) DEFAULT NULL,
  `Makers_name` varchar(50) DEFAULT NULL,
  `Manifacture_Date` date DEFAULT NULL,
  `Chassis_No` varchar(100) NOT NULL,
  `Seating_Capacity` int(11) DEFAULT NULL,
  `Fuel_Type` varchar(15) DEFAULT NULL,
  `RCFileUpload` varchar(400) DEFAULT NULL,
  `Body_Color` varchar(15) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `CreateUser` varchar(20) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUser` varchar(20) DEFAULT NULL,
  `Engine_number` varchar(50) DEFAULT NULL,
  `Tax_Paid` date DEFAULT NULL,
  `Pollution` date DEFAULT NULL,
  `TaxExpiryDate` date DEFAULT NULL,
  PRIMARY KEY (`VehicleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `transport_vehicle_insurancedetails` */

CREATE TABLE `transport_vehicle_insurancedetails` (
  `slno` int(11) NOT NULL AUTO_INCREMENT,
  `VehicleCode` varchar(20) CHARACTER SET latin1 NOT NULL,
  `CompanyName` varchar(30) NOT NULL,
  `InsuranceFleUpload` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `IssuedDate` date NOT NULL,
  `ExpiryDate` date NOT NULL,
  `doneby` varchar(50) NOT NULL,
  `updatedBy` varchar(30) NOT NULL,
  `updatedTime` datetime NOT NULL,
  `Fc` date DEFAULT NULL,
  `Permit_validity` date DEFAULT NULL,
  PRIMARY KEY (`slno`),
  KEY `tranvechicleinsu_vehicleId` (`VehicleCode`),
  CONSTRAINT `tranvechicleinsu_vehicleId` FOREIGN KEY (`VehicleCode`) REFERENCES `transport_vehicle` (`VehicleCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transport_vehicle_route_mapping` */

CREATE TABLE `transport_vehicle_route_mapping` (
  `RouteCode` varchar(20) NOT NULL,
  `VehicleCode` varchar(20) NOT NULL,
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `CreateDate` datetime NOT NULL,
  `CreateUser` varchar(20) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  KEY `vehicleroutemap_vehicleId` (`VehicleCode`),
  KEY `vehicleroutemap_routeId` (`RouteCode`),
  CONSTRAINT `vehicleroutemap_routeId` FOREIGN KEY (`RouteCode`) REFERENCES `transport_route` (`RouteCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicleroutemap_vehicleId` FOREIGN KEY (`VehicleCode`) REFERENCES `transport_vehicle` (`VehicleCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `uniform_sms` */

CREATE TABLE `uniform_sms` (
  `Uniform_Code` varchar(10) NOT NULL,
  `LocId` varchar(20) DEFAULT NULL,
  `AccyearId` varchar(11) DEFAULT NULL,
  `Student_Code` varchar(10) DEFAULT NULL,
  `Class_Code` varchar(10) DEFAULT NULL,
  `Section_Code` varchar(10) DEFAULT NULL,
  `Date` varchar(20) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `CreatedUser` varchar(20) DEFAULT NULL,
  `Created_Time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Uniform_Code`),
  KEY `uniformsms_stuId` (`Student_Code`),
  KEY `uniformsms_classId` (`Class_Code`),
  KEY `uniformsms_sectionId` (`Section_Code`),
  KEY `uniformsms_locId` (`LocId`),
  KEY `uniformsms_accyearId` (`AccyearId`),
  CONSTRAINT `uniformsms_accyearId` FOREIGN KEY (`AccyearId`) REFERENCES `campus_acadamicyear` (`acadamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uniformsms_classId` FOREIGN KEY (`Class_Code`) REFERENCES `campus_classdetail` (`classdetail_id_int`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uniformsms_locId` FOREIGN KEY (`LocId`) REFERENCES `campus_location` (`Location_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uniformsms_sectionId` FOREIGN KEY (`Section_Code`) REFERENCES `campus_classsection` (`classsection_id_int`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET SQL_NOTES=@OLD_SQL_NOTES;
