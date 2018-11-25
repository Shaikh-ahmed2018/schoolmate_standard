package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.ClassTeacherLsitDao;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.ClassTeacherVo;


public class ClassTeacherLsitDaoImpl implements ClassTeacherLsitDao{


	private static final Logger logger = Logger.getLogger(ClassTeacherLsitDaoImpl.class);
	
	
	public ArrayList<ClassTeacherVo> getClassTeacherListDao(UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Starting");
		
		ArrayList<ClassTeacherVo> tealist = new ArrayList<ClassTeacherVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(custid);
			pstmt = connection.prepareStatement(ReportsMenuSqlConstants.CLASS_TEACHER_MAPPED_LIST);
			//("START "+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ClassTeacherVo vo = new ClassTeacherVo();
				
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setSectionName(rs.getString("classsection_name_var"));
				vo.setTeacherName(rs.getString("teachername"));
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setSectionId(rs.getString("classsection_id_int"));
				vo.setTeacherId(rs.getString("teacherid"));
				vo.setLocationId(rs.getString("locationId"));
				
				tealist.add(vo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.isClosed();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.isClosed();
				}
				if(connection!=null && (!connection.isClosed())){
					connection.isClosed();
				}
			} 
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Ending");
		
		return tealist;
	}



	public ClassTeacherVo editClassTeacherDao(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Starting");
		
		
		ClassTeacherVo classteacher = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			if(vo.getTeacherId().contains("TEA")){
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_SECTION_TEACHER);
				pstmt.setString(1, vo.getClassId());
				pstmt.setString(2, vo.getSectionId());
				pstmt.setString(3, vo.getTeacherId());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					classteacher = new ClassTeacherVo();
					
					classteacher.setClassId(rs.getString("classdetail_id_int"));
					classteacher.setClassName(rs.getString("classdetails_name_var"));
					classteacher.setSectionId(rs.getString("classsection_id_int"));
					classteacher.setSectionName(rs.getString("classsection_name_var"));
					classteacher.setTeacherId(rs.getString("TeacherID"));
					classteacher.setTeacherName(rs.getString("teachername"));
					
				}
				
			}
			
			else{
				
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_CLASS_SECTION);
				pstmt.setString(1, vo.getClassId());
				pstmt.setString(2, vo.getSectionId());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					
					classteacher = new ClassTeacherVo();
					
					classteacher.setClassId(rs.getString("classdetail_id_int"));
					classteacher.setClassName(rs.getString("classdetails_name_var"));
					classteacher.setSectionId(rs.getString("classsection_id_int"));
					classteacher.setSectionName(rs.getString("classsection_name_var"));
					classteacher.setTeacherId("-");
					classteacher.setTeacherName("-");
					
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.isClosed();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.isClosed();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.isClosed();
				}
			} 
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getClassTeacherListDao Ending");
		
		return classteacher;
	}

	
	public String saveClassTeacherDao(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : saveClassTeacherDao Starting");
		
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		
		ResultSet rs = null;
		String locId = null;
		
		if(vo.getTeacherId1().contains("-")){
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHER_LOCATION);
				pstmt.setString(1, vo.getTeacherId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					locId = rs.getString(1);
				}
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.SAVE_CLASS_TEACHER);
				pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_classteacher",userLoggingsVo));
				pstmt.setString(2, vo.getClassId());
				pstmt.setString(3, vo.getSectionId());
				pstmt.setString(4, vo.getTeacherId());
				pstmt.setTimestamp(5, time_stamp);
				pstmt.setString(6, vo.getCreateuser());
				pstmt.setString(7,locId);
				pstmt.setString(8,vo.getAccYear());
			//	//("SAVE TEACHER "+pstmt);
				result1 = pstmt.executeUpdate();
				
				
				if (result1 == 1) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Staff","ClassTeacher Mapping","Insert",pstmt.toString(),userLoggingsVo);
					result_Status = MessageConstants.ClassTeacherSuccessMsg;
				} else {
					result_Status = MessageConstants.ClassTeacherErrorMsg;
				}
				
				
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			finally{
				try {
					if(rs!=null && (!rs.isClosed())){
						rs.isClosed();
					}
					if(pstmt!=null && (!pstmt.isClosed())){
						pstmt.isClosed();
					}
					if(conn!=null && (!conn.isClosed())){
						conn.isClosed();
					}
				} 
				catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}
		else{
			
			try {
				
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHER_LOCATION);
				pstmt.setString(1, vo.getTeacherId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					locId = rs.getString(1);
				}
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(ReportsMenuSqlConstants.UPDATE_CLASS_TEACHER);
			
				pstmt.setString(1, vo.getTeacherId());
				pstmt.setString(2, locId);
				pstmt.setString(3,vo.getAccYear());
				pstmt.setString(4, vo.getClassId());
				pstmt.setString(5, vo.getSectionId());
			//	//("UPDATE TEACHER "+pstmt);
				result1 =pstmt.executeUpdate();
				
				if (result1 == 1) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Staff","ClassTeacher Mapping","Update",pstmt.toString(),userLoggingsVo);
					result_Status = MessageConstants.ClassTeacherSuccessMsg;
					
				} else {
					
					result_Status = MessageConstants.ClassTeacherErrorMsg;
					
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			finally{
				try {
					if(rs!=null && (!rs.isClosed())){
						rs.isClosed();
					}
					if(pstmt!=null && (!pstmt.isClosed())){
						pstmt.isClosed();
					}
					if(conn!=null && (!conn.isClosed())){
						conn.isClosed();
					}
				} 
				catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : saveClassTeacherDao Ending");
		
		return result_Status;
	}

	
	public boolean validateClassTeacherDao(ClassTeacherVo vo,UserLoggingsPojo userLoggingsVo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : validateClassTeacherDao Starting");
		
		boolean classteacher_validate = false;

		int i = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.VALIDATE_CLASS_TEACHER);
			
			pstmt.setString(1, vo.getClassId());
			pstmt.setString(2, vo.getSectionId());
			pstmt.setString(3, vo.getTeacherId());
			
			
			 rs = pstmt.executeQuery();
			
			while (rs.next()) {
				i = rs.getInt(1);
			}
			
			if (i > 0) {
				System.err.println(vo.getHiddenid()+" "+vo.getTeacherId());
				if(vo.getHiddenid().equalsIgnoreCase(vo.getTeacherId())) {
					System.err.println("INSIDE IF");
					classteacher_validate = false;
					}else {
						System.err.println("INSIDE ELSE");
				classteacher_validate = true;
			}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.isClosed();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.isClosed();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.isClosed();
				}
			} 
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : validateClassTeacherDao Ending");
		
		return classteacher_validate;
	}



	
	public ArrayList<ClassTeacherVo> getSearchClassTeacherListDao(String searchTerm,UserLoggingsPojo custid) {
	
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getSearchClassTeacherListDao Starting");
		
		
		
		ArrayList<ClassTeacherVo> tealist = new ArrayList<ClassTeacherVo>();
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			
			pstmt = conn.prepareStatement(ReportsMenuSqlConstants.SEARCH_CLASS_TEACHER);
			pstmt.setString(1, "%"+searchTerm+"%");
			pstmt.setString(2, "%"+searchTerm+"%");
			pstmt.setString(3, "%"+searchTerm+"%");
			pstmt.setString(4, "%"+searchTerm+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClassTeacherVo voobj = new ClassTeacherVo();
				
				voobj.setClassName(rs.getString("classdetails_name_var"));
				voobj.setSectionName(rs.getString("classsection_name_var"));
				voobj.setTeacherName(rs.getString("teachername"));
				voobj.setClassId(rs.getString("classdetail_id_int"));
				voobj.setSectionId(rs.getString("classsection_id_int"));
				voobj.setTeacherId(rs.getString("teacherid"));
				
				tealist.add(voobj);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.isClosed();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.isClosed();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.isClosed();
				}
			} 
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getSearchClassTeacherListDao Ending");
		
		
		return tealist;
	}



	@Override
	public ArrayList<ClassTeacherVo> getFilterdClassTeacherListBD(String accYear, String locationId, String classId,
			String sectionId, UserLoggingsPojo custId,String dep,String searchVal) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl : getFilterdClassTeacherList DaoImpl Starting");
		
		ArrayList<ClassTeacherVo> tealist = new ArrayList<ClassTeacherVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(custId);
			String  query="";
			if(dep.equalsIgnoreCase("%%")){
				// query="SELECT DISTINCT map.academicYear,cls.classdetail_id_int,cls.locationId,cls.classdetails_name_var,sec.classsection_id_int,sec.classsection_name_var ,(CASE WHEN map.SectionCode=sec.classsection_id_int THEN (SELECT CONCAT(FirstName,' ',LastName)AS teaname FROM campus_teachers WHERE TeacherID=map.TeacherCode) ELSE '-' END)teachername ,(CASE WHEN map.SectionCode=sec.classsection_id_int THEN (SELECT TeacherID FROM  campus_teachers WHERE TeacherID=map.TeacherCode) ELSE '-' END)teacherid FROM campus_classdetail cls LEFT JOIN campus_classsection sec ON cls.`locationId`=sec.`locationId` LEFT OUTER JOIN  campus_classteacher map ON  map.SectionCode=sec.classsection_id_int WHERE cls.classdetail_id_int=sec.classdetail_id_int";
				 query="SELECT `classdetails_name_var`,`classsection_name_var`,cd.classdetail_id_int,sec.classsection_id_int,sec.`locationId`,CASE WHEN ct.`TeacherCode` IS NULL THEN '-' WHEN t.isActive='N' THEN '-' ELSE (SELECT CONCAT(`FirstName`,' ',`LastName`) FROM `campus_teachers` WHERE `TeacherID` = ct.`TeacherCode`)END teachername,CASE WHEN ct.`TeacherCode` IS NULL THEN '-' ELSE (SELECT `TeacherID` FROM `campus_teachers` WHERE `TeacherID` = ct.`TeacherCode`)END teacherid,acc.acadamic_id FROM campus_acadamicyear acc,`campus_classdetail` cd  JOIN `campus_location` loc ON loc.`Location_Id` = cd.`locationId` JOIN `campus_classsection`sec ON sec.`classdetail_id_int` = cd.`classdetail_id_int` AND sec.`locationId` = cd.`locationId` LEFT JOIN `campus_classteacher` ct ON (ct.`ClassCode` = sec.`classdetail_id_int` AND ct.`SectionCode` = sec.`classsection_id_int` AND ct.`locationId` = sec.`locationId`) LEFT JOIN campus_teachers t ON t.TeacherID=ct.`TeacherCode`";
			}else{
				 // query="SELECT DISTINCT cls.classdetail_id_int,cls.locationId,cls.classdetails_name_var,sec.classsection_id_int,sec.classsection_name_var ,(CASE WHEN map.SectionCode=sec.classsection_id_int THEN (SELECT CONCAT(FirstName,' ',LastName)AS teaname FROM campus_teachers WHERE TeacherID=map.TeacherCode) ELSE '-' END)teachername ,(CASE WHEN map.SectionCode=sec.classsection_id_int THEN (SELECT `department` FROM campus_teachers WHERE TeacherID=map.TeacherCode) ELSE '-' END)departmentid,(CASE WHEN map.SectionCode=sec.classsection_id_int THEN (SELECT TeacherID FROM  campus_teachers WHERE TeacherID=map.TeacherCode) ELSE '-' END)teacherid FROM campus_classdetail cls JOIN campus_classsection sec ON cls.`locationId`=sec.`locationId` LEFT OUTER JOIN  campus_classteacher map ON  map.SectionCode=sec.classsection_id_int JOIN campus_teachers t ON t.TeacherID=map.TeacherCode WHERE cls.classdetail_id_int=sec.classdetail_id_int";
				 query="SELECT acc.acadamic_id,`classdetails_name_var`,`classsection_name_var`,cd.classdetail_id_int,sec.classsection_id_int,sec.`locationId`,CASE WHEN ct.`TeacherCode` IS NULL THEN '-' WHEN t.isActive='N' THEN '-' ELSE (SELECT CONCAT(`FirstName`,' ',`LastName`) FROM `campus_teachers` WHERE `TeacherID` = ct.`TeacherCode`)END teachername,CASE WHEN ct.`TeacherCode` IS NULL THEN '-' ELSE (SELECT `TeacherID` FROM `campus_teachers` WHERE `TeacherID` = ct.`TeacherCode`)END teacherid,acc.acadamic_id FROM campus_acadamicyear acc,`campus_classdetail` cd  JOIN `campus_location` loc ON loc.`Location_Id` = cd.`locationId` JOIN `campus_classsection`sec ON sec.`classdetail_id_int` = cd.`classdetail_id_int` AND sec.`locationId` = cd.`locationId` LEFT JOIN `campus_classteacher` ct ON (ct.`ClassCode` = sec.`classdetail_id_int` AND ct.`SectionCode` = sec.`classsection_id_int` AND ct.`locationId` = sec.`locationId`) LEFT JOIN campus_teachers t ON t.TeacherID=ct.TeacherCode";
				}
			

			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!accYear.equalsIgnoreCase("%%")) {
				map.put("acc.acadamic_id",accYear);
				vec.add("acc.acadamic_id");
			}
			if(!locationId.equalsIgnoreCase("%%")) {
				map.put("sec.locationId",locationId);
				vec.add("sec.locationId");
			}
			if(!classId.equalsIgnoreCase("%%")) {
				map.put("cd.classdetail_id_int",classId);
				vec.add("cd.classdetail_id_int");
			}
			if(!sectionId.equalsIgnoreCase("%%")) {
				map.put("sec.classsection_id_int",sectionId);
				vec.add("sec.classsection_id_int");
			}
			if(!dep.equalsIgnoreCase("%%")) {
				map.put("t.department",dep);
				vec.add("t.department");
			}
			
			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" WHERE "+key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			
			String finalquery="";
			if(wheresql != null) {
				finalquery=query+" "+wheresql;
			}else{
				finalquery=query;
			}/*else {
				finalquery=query+" "+"( stu.student_admissionno_var like ? or stu.student_fname_var like ? or stu.student_lname_var like ? or concat(stu.student_fname_var,' ',stu.student_lname_var) like ? OR  csc.student_rollno  LIKE ? or ccd.classdetails_name_var like ? or ccs.classsection_name_var like ?  or CONCAT(ccd.classdetails_name_var,'-',ccs.classsection_name_var) like ?) GROUP BY ctc.`studentIdNo` order by ccd.classdetails_name_var,ccs.classsection_name_var,studentname"; stu.`student_id_no` like ? or
			}*/
			
			pstmt = connection.prepareStatement(finalquery+"AND cd.`isActive`='Y' AND sec.`isActive`='Y' ORDER BY `startDate`,`Location_Name`,CAST(SUBSTRING(cd.`classdetail_id_int`,4,LENGTH(cd.`classdetail_id_int`)-3) AS SIGNED),classsection_name_var;");
			//System.out.println("AFTER FILTER "+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClassTeacherVo vo = new ClassTeacherVo();
				vo.setClassName(rs.getString("classdetails_name_var"));
				vo.setSectionName(rs.getString("classsection_name_var"));
				vo.setTeacherName(rs.getString("teachername"));
				vo.setClassId(rs.getString("classdetail_id_int"));
				vo.setSectionId(rs.getString("classsection_id_int"));
				vo.setTeacherId(rs.getString("teacherid"));
				vo.setLocationId(rs.getString("locationId"));
				vo.setAccYear(rs.getString("acadamic_id"));
				tealist.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.isClosed();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.isClosed();
				}
				if(connection!=null && (!connection.isClosed())){
					connection.isClosed();
				}
			} 
			catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherLsitDaoImpl :  getFilterdClassTeacherList DaoImpl Ending");
		
		return tealist;
	}


}
