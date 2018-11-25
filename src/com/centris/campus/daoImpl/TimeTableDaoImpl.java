package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import com.centris.campus.dao.TimeTableDao;
import com.centris.campus.pojo.TeacherTimeTablePojo;
import com.centris.campus.pojo.TimeTablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.StudentRegistrationSQLUtilConstants;
import com.centris.campus.util.TimeTableSqlConstants;
import com.centris.campus.vo.TeacherTimeTableVo;
import com.centris.campus.vo.TimeTableVo;


public class TimeTableDaoImpl implements TimeTableDao {
	private static final Logger logger = Logger
			.getLogger(TimeTableDaoImpl.class);

	@Override
	public synchronized ArrayList<TimeTableVo> getTimeTableDetails(String timetableId,String classid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		//ps_period=null,ps_period1=null,ps_period2=null,ps_period3=null,ps_period4=null,ps_period5=null,ps_period6=null,ps_period7=null,ps_period8=null;
		ResultSet rst = null;
		//rs_period=null,rs_period1=null,rs_period2=null,rs_period3=null,rs_period4=null,rs_period5=null,rs_period6=null,rs_period7=null,rs_period8=null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		
		
		
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_COUNT);
			pstmt.setString(1, timetableId.trim());
			System.out.println("TIMETABLE_DETAILS_COUNT 1st query:::"+pstmt);
			rst = pstmt.executeQuery();
			
			while (rst.next()) {
				count = rst.getInt(1);
			}
			
			if (count > 0) {
				pstmt1 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_DETAILS);
				pstmt1.setString(1, timetableId.trim());
				
				System.out.println("TIMETABLE_GET_DETAILS 2nd query:::"+pstmt1);
							
				rst1 = pstmt1.executeQuery();

				
				while(rst1.next()) {
					
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst1.getString("daycode"));
					voObj.setDayname(rst1.getString("dayname"));
					voObj.setPeriods(rst1.getString("period"));
					voObj.setTeacherId(rst1.getString("teacherid"));
					voObj.setSubjectid(rst1.getString("subjectid"));
					
					details.add(voObj);
					
				
					
				}
			} 
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	
	@Override
	public synchronized ArrayList<TimeTableVo> getClassName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassName Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_CLASSID);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TimeTableVo voobj = new TimeTableVo();
				voobj.setClassid(rst.getString("classdetail_id_int").trim());
				voobj.setClassname(rst.getString("classdetails_name_var")
						.trim());
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}

				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassName Ending");
		return details;
	}

	@Override
	public synchronized ArrayList<TimeTableVo> getSectionName(String classid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionName Starting");
		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TIMETABLE_GET_SECTIONID);
			pstmt.setString(1, classid.trim());
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TimeTableVo voobj = new TimeTableVo();
				voobj.setSectionid(rst.getString("classsection_id_int"));
				voobj.setSectionname(rst.getString("classsection_name_var"));
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionName Ending");
		return details;
	}

	@Override
	public String updateTimeTableDetails(TimeTablePojo pojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Starting");
		PreparedStatement pstmt = null,pstmt1 = null, pstmt2 = null,pstmt3 = null,pstmt4 = null,pstmt5 = null,pstmt6 = null,pstmt7 = null;
		PreparedStatement tpstmt =null;
		ResultSet rst = null;
		String result = null;
		String classid=null;
		String sectionid=null;
		Connection connection = null;
		int count=0;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			/*pstmt2 = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_ID_BY_NAME);
			pstmt2.setString(1, pojo.getClassid().trim());
			rst=pstmt2.executeQuery();
		
			while(rst.next())
			{
				classid=rst.getString("classdetail_id_int");
			}
			rst.close();
			//pstmt3 = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_ID_BY_NAME);
			pstmt3 = connection.prepareStatement("SELECT sec.classsection_id_int FROM campus_classsection  sec JOIN campus_classdetail cls ON cls.classdetail_id_int=sec.classdetail_id_int AND cls.`locationId`=sec.`locationId` WHERE sec. classsection_name_var =? AND cls.classdetails_name_var=? AND sec.`locationId`=? ");
			pstmt3.setString(1, pojo.getSectionid().trim());
			pstmt3.setString(2, pojo.getClassid().trim());
			pstmt3.setString(3, pojo.getLocationId());
			rst=pstmt3.executeQuery();
			while(rst.next())
			{
				sectionid=rst.getString("classsection_id_int");
			
			}*/
			
			System.out.println("time table id isssss "+pojo.getTimetableID());
			if ("-".equals(pojo.getTimetableID())) {
			
				String tableId = IDGenerator.getPrimaryKeyID("campus_timetable_student",userLoggingsVo);
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_INSERT);
				
				pstmt.setString(1, tableId);
				pstmt.setString(2, pojo.getClassid().trim());
				pstmt.setString(3, pojo.getSectionid().trim());
				pstmt.setString(4, pojo.getAccyearid().trim());
				pstmt.setString(5, HelperClass.getCurrentSqlDate() + " "+ HelperClass.getCurrentTime());
				pstmt.setString(6, pojo.getUserid());
				pstmt.setString(7, pojo.getLocationId());
				System.out.println("Save "+pstmt);
				int status = pstmt.executeUpdate();

				if (status > 0) {
					HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Settings","TimeTable","Insert",pstmt.toString(),userLoggingsVo);
					for (int i = 0; i < pojo.getDayid().length; i++) {

						
						for (int j = 1; j <=pojo.getPeriods().split(":")[i].substring(1).split(",").length; j++) {
							pstmt6=connection.prepareStatement("INSERT INTO `campus_timetable_details`(`teacherid`,`timtable_id`,`subjectid`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
							 pstmt6.setString(2, tableId);
							 pstmt6.setString(6, pojo.getAccyearid().trim());
							 pstmt6.setString(7, pojo.getUserid());
							if(!pojo.getPeriods().split(":")[i].substring(1).split(",")[j-1].equalsIgnoreCase("-")&& !pojo.getTperiods().split(":")[i].substring(1).split(",")[j-1].equalsIgnoreCase("-")){
								 pstmt6.setString(1, pojo.getTperiods().split(":")[i].substring(1).split(",")[j-1]);
								 pstmt6.setString(3, pojo.getPeriods().split(":")[i].substring(1).split(",")[j-1]);
								 pstmt6.setString(4, pojo.getDayid()[i]);
								 pstmt6.setString(5, "period"+(j));
								 count= pstmt6.executeUpdate();
								 if(count>0) {
									 HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Settings","TimeTable","Insert",pstmt6.toString(),userLoggingsVo);
								 }
						    }
							
						}
						
					}
					
					
					result = "success";
					
				} else {
					
						result = "fail";
					
				}
			} else {
				
				pstmt5 = connection.prepareStatement(TimeTableSqlConstants.TIMETABLE_DETAILS_MODIFY);
				pstmt5.setString(1, pojo.getUserid());
				pstmt5.setString(2, HelperClass.getCurrentSqlDate() + " "+ HelperClass.getCurrentTime());
				pstmt5.setString(3, pojo.getTimetableID());
				
				int value1=pstmt5.executeUpdate();
				if(value1 > 0){
					  HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Settings","TimeTable","Update",pstmt5.toString(),userLoggingsVo);
					}
				

				pstmt7 = connection.prepareStatement("DELETE FROM campus_timetable_details WHERE `timtable_id`=?");
				pstmt7.setString(1, pojo.getTimetableID().trim());
			
				
				int status = 	pstmt7.executeUpdate();
				
				
				
				if (status > 0 ) {

					for (int i = 0; i < pojo.getDayid().length; i++) {for (int j = 1; j <=pojo.getPeriods().split(":")[i].substring(1).split(",").length; j++) {
						pstmt6=connection.prepareStatement("INSERT INTO `campus_timetable_details`(`teacherid`,`timtable_id`,`subjectid`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
						 pstmt6.setString(2, pojo.getTimetableID());
						 pstmt6.setString(6, pojo.getAccyearid().trim());
						 pstmt6.setString(7, pojo.getUserid());
						if(!pojo.getPeriods().split(":")[i].substring(1).split(",")[j-1].equalsIgnoreCase("-")&& !pojo.getTperiods().split(":")[i].substring(1).split(",")[j-1].equalsIgnoreCase("-")){
							 pstmt6.setString(1, pojo.getTperiods().split(":")[i].substring(1).split(",")[j-1]);
							 pstmt6.setString(3, pojo.getPeriods().split(":")[i].substring(1).split(",")[j-1]);
							 pstmt6.setString(4, pojo.getDayid()[i]);
							 pstmt6.setString(5, "period"+(j));
							 count= pstmt6.executeUpdate();
							 if(count>0) {
								 HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Settings","TimeTable","Insert",pstmt6.toString(),userLoggingsVo);
							 }
					    }
						
					}
					}
						result = "success";
					
				} else {
						result = "fail";
					
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				if (pstmt4 != null && (!pstmt4.isClosed())) {
					pstmt4.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Ending");

		return result;
	}
	
	
	

	@Override
	public synchronized ArrayList<TimeTableVo> getTeacherTimeTableDetails(String teacherid, String accyearid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int count = 0;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_COUNT);
			pstmt.setString(1, teacherid.trim());
			pstmt.setString(2, accyearid.trim());
			rst = pstmt.executeQuery();
			
			
			
			while (rst.next()) {
				count = rst.getInt(1);
			}
			rst.close();
			pstmt.close();
			if (count > 0) {
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_GET_DETAILS);
				pstmt.setString(1, teacherid.trim());
				pstmt.setString(2, accyearid.trim());
				
				
				rst = pstmt.executeQuery();

				while (rst.next()) {
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst.getString("daycode"));
					voObj.setDayname(rst.getString("dayname"));
					voObj.setPeriodId1(rst.getString("period1"));
					voObj.setPeriodId2(rst.getString("period2"));
					voObj.setPeriodId3(rst.getString("period3"));
					voObj.setPeriodId4(rst.getString("period4"));
					voObj.setPeriodId5(rst.getString("period5"));
					voObj.setPeriodId6(rst.getString("period6"));
					voObj.setPeriodId7(rst.getString("period7"));
					voObj.setPeriodId8(rst.getString("period8"));
					voObj.setPeriodId9(rst.getString("period9"));
					
					voObj.setPeriod1(getSubjectfNameval(rst.getString("period1")));
					voObj.setPeriod2(getSubjectfNameval(rst.getString("period2")));
					voObj.setPeriod3(getSubjectfNameval(rst.getString("period3")));
					voObj.setPeriod4(getSubjectfNameval(rst.getString("period4")));
					voObj.setPeriod5(getSubjectfNameval(rst.getString("period5")));
					voObj.setPeriod6(getSubjectfNameval(rst.getString("period6")));
					voObj.setPeriod7(getSubjectfNameval(rst.getString("period7")));
					voObj.setPeriod8(getSubjectfNameval(rst.getString("period8")));
					voObj.setPeriod9(getSubjectfNameval(rst.getString("period9")));
					details.add(voObj);
					
					
				}
			} else {

				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_GET_DAYS);
				
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					TimeTableVo voObj = new TimeTableVo();
					voObj.setDayid(rst.getString("daycode"));
					voObj.setDayname(rst.getString("dayname"));
					voObj.setPeriod1("");
					voObj.setPeriod2("");
					voObj.setPeriod3("");
					voObj.setPeriod4("");
					voObj.setPeriod5("");
					voObj.setPeriod6("");
					voObj.setPeriod7("");
					voObj.setPeriod8("");
					voObj.setPeriod9("");
					voObj.setPeriodId1("");
					voObj.setPeriodId2("");
					voObj.setPeriodId3("");
					voObj.setPeriodId4("");
					voObj.setPeriodId5("");
					voObj.setPeriodId6("");
					voObj.setPeriodId7("");
					voObj.setPeriodId8("");
					voObj.setPeriodId9("");
					details.add(voObj);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableDetails Ending");
		return details;
	}

	private String getSubjectfNameval(String class_section) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		Connection connection = null;
		String subjectName=null;
		String classname = "";
		String subjectname = "";
		
		try {
			
			if(!class_section.equalsIgnoreCase("-"))
				
			{
			
			String[] class_sectionval = class_section.split("-");
			
			connection = JDBCConnection.getSeparateConnection();

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_NAME);
			pstmt.setString(1, class_sectionval[0]);
			rst = pstmt.executeQuery();

				if (rst.next()) {
					
					/*subjectName=rst.getString("subjectName");*/
					
					classname =  rst.getString("classdetails_name_var");
					
					
				}
				
				else
					
				{
					
					classname= " ";
					
				}
				
				
				pstmt1 = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_NAME);
				pstmt1.setString(1, class_sectionval[1]);
				rst1 = pstmt1.executeQuery();

					if (rst1.next()) {
						
						/*subjectName=rst.getString("subjectName");*/
						
						subjectname =  rst1.getString("classsection_name_var");
						
						
					}
					
					else
						
					{
						
						subjectname = " ";
						
					}
				
					
					subjectName = classname.concat("-"+subjectname);
			}
			
			else
				
			{
				
				subjectName = "-";
				
			}
				
				
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return subjectName;
	}

	public synchronized ArrayList<TeacherTimeTableVo> getTeacherName() {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherName Starting");

		ArrayList<TeacherTimeTableVo> details = new ArrayList<TeacherTimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TEACHER_IDANDNAME);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TeacherTimeTableVo voobj = new TeacherTimeTableVo();
				voobj.setTeacherId(rst.getString("TeacherID").trim());
				voobj.setTeacherName(rst.getString("TeacherName").trim());
				details.add(voobj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherName Ending");
		return details;
	}

	@Override
	public String updateTeacherTimeTableDetails(TeacherTimeTablePojo pojo) {
		
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTeacherTimeTableDetails Starting");
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst = null;
		String result = "fail";
		Connection connection = null;
		try {
			connection = JDBCConnection.getSeparateConnection();
			int count = 0;
			pstmt = connection
					.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_UPDATECOUNT);
			pstmt.setString(1, pojo.getTeacherid().trim());
			pstmt.setString(2, pojo.getAccyearid().trim());
			
			
			
			rst = pstmt.executeQuery();
			while (rst.next()) {
				count = rst.getInt(1);
			}
			if (count == 0) {
				String tableId = IDGenerator.getPrimaryKeyID("campus_timetable_teacher");
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERT);
				pstmt.setString(1, tableId);
				pstmt.setString(2, pojo.getTeacherid().trim());
				pstmt.setString(3, pojo.getAccyearid().trim());
				pstmt.setString(4, HelperClass.getCurrentSqlDate() + " "
						+ HelperClass.getCurrentTime());
				pstmt.setString(5, pojo.getUserid());
				
				
				
				int status = pstmt.executeUpdate();

				if (status > 0) {

					for (int i = 0; i < pojo.getDayid().length; i++) {

						pstmt1 = connection
								.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERTDETAILS);
						pstmt1.setString(1, tableId);
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getPeriod1()[i]);
						pstmt1.setString(4, pojo.getPeriod2()[i]);
						pstmt1.setString(5, pojo.getPeriod3()[i]);
						pstmt1.setString(6, pojo.getPeriod4()[i]);
						pstmt1.setString(7, pojo.getPeriod5()[i]);
						pstmt1.setString(8, pojo.getPeriod6()[i]);
						pstmt1.setString(9, pojo.getPeriod7()[i]);
						pstmt1.setString(10, pojo.getPeriod8()[i]);
						pstmt1.setString(11, pojo.getPeriod9()[i]);
						
						
						
						pstmt1.executeUpdate();
					}
					
				result = "success";
					
				}
			} else {
				
				
				
				String exist_id = null;
				pstmt = connection.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_ID);
				pstmt.setString(1, pojo.getTeacherid().trim());
				pstmt.setString(2, pojo.getAccyearid().trim());
				
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					exist_id = rst.getString("teachertimetable_id");
				}

				PreparedStatement pstmt2 = connection
						.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_DELETE);
				pstmt2.setString(1, pojo.getTeacherid().trim());
				pstmt2.setString(2, pojo.getAccyearid().trim());
				
				
				
				int status = pstmt2.executeUpdate();
				if (status > 0) {

					for (int i = 0; i < pojo.getDayid().length; i++) {

						pstmt1 = connection
								.prepareStatement(TimeTableSqlConstants.TEACHER_TIMETABLE_DETAILS_INSERTDETAILS);
						pstmt1.setString(1, exist_id);
						pstmt1.setString(2, pojo.getDayid()[i]);
						pstmt1.setString(3, pojo.getPeriod1()[i]);
						pstmt1.setString(4, pojo.getPeriod2()[i]);
						pstmt1.setString(5, pojo.getPeriod3()[i]);
						pstmt1.setString(6, pojo.getPeriod4()[i]);
						pstmt1.setString(7, pojo.getPeriod5()[i]);
						pstmt1.setString(8, pojo.getPeriod6()[i]);
						pstmt1.setString(9, pojo.getPeriod7()[i]);
						pstmt1.setString(10, pojo.getPeriod8()[i]);
						pstmt1.setString(11, pojo.getPeriod9()[i]);
						
						pstmt1.executeUpdate();
					}
					
						result = "success";
					
				}
				pstmt2.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		 finally {
				try {
					if (rst != null && (!rst.isClosed())) {
						rst.close();
					}
					if (pstmt1 != null && (!pstmt1.isClosed())) {
						pstmt1.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (connection != null && (!connection.isClosed())) {
						connection.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTeacherTimeTableDetails Ending");

		return result;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableList(String accyearid,String classId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			
			if(classId == null || classId == "ALL"){
				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, "%%");
				System.out.println(pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));

					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by"),custdetails));
						voObj.setCreateddate(HelperClass.convertDatabaseToUI(rst.getString("created_date")));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){
							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");
						}else{
							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by"),custdetails));
							voObj.setLastupdated(HelperClass.convertDatabaseToUI(rst.getString("updated_time")));
						}
						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));

					}
					details.add(voObj);
				}
				rst.close();

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_CLASS);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, "%%");
				pstmt.setString(3, "%%");
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));

					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by"),custdetails));
						voObj.setCreateddate(HelperClass.convertDatabaseToUI(rst.getString("created_date")));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by"),custdetails));
							voObj.setLastupdated(HelperClass.convertDatabaseToUI(rst.getString("updated_time")));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			}else{
				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_CLASS);
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, classId);
			
				rst = pstmt.executeQuery();

				while (rst.next()) {

					TimeTableVo voObj = new TimeTableVo();

					if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Yet to Create");
						voObj.setCreatedby("-");
						voObj.setCreateddate("-");
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
						voObj.setTimetableId("-");
						voObj.setTeachername(rst.getString("teachername"));
					}else{

						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						voObj.setTimetableStatus("Created");
						voObj.setCreatedby(getStaffName(rst.getString("created_by"),custdetails));
						voObj.setCreateddate(HelperClass.convertDatabaseToUI(rst.getString("created_date")));
						if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");

						}else{

							voObj.setLastupdatedby(getStaffName(rst.getString("updated_by"),custdetails));
							voObj.setLastupdated(HelperClass.convertDatabaseToUI(rst.getString("updated_time")));
						}

						voObj.setTimetableId(rst.getString("timetable_id"));
						voObj.setTeachername(rst.getString("teachername"));
					}
					details.add(voObj);
				}
			}
			
			/*else{

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHER_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());
				
				rst = pstmt.executeQuery();

					while (rst.next()) {
						
						TimeTableVo voObj = new TimeTableVo();
						
						if(rst.getString("teachertimetable_id")==null || "".equalsIgnoreCase(rst.getString("teachertimetable_id"))){
							
							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Yet to Create");
							voObj.setCreatedby("-");
							voObj.setCreateddate("-");
							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");
							voObj.setTimetableId("-");
							
						}else{
							
							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Created");
							voObj.setCreatedby(getStaffName(rst.getString("created_by")));
							voObj.setCreateddate(rst.getString("created_date"));
							if(rst.getString("updated_date")==null || "".equalsIgnoreCase(rst.getString("updated_date"))){
								
								voObj.setLastupdatedby("-");
								voObj.setLastupdated("-");
						
							}else{
								
								voObj.setLastupdatedby(getStaffName(rst.getString("updated_date")));
								voObj.setLastupdated(rst.getString("updated_time"));
							}
							
							voObj.setTimetableId(rst.getString("teachertimetable_id"));
						
						}
						details.add(voObj);
					}
				
				}*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}
	
	public String getStaffName(String staffId,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getStaffName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String staffname=null;
		
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_STAFF_NAME);
			pstmt.setString(1, staffId);
			
			rst = pstmt.executeQuery();

				while (rst.next()) {
					
					staffname=rst.getString("StaffName");
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getStaffName Ending");
		return staffname;
	}
	
	public String getSubjectfName(String subjectId,String classid,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String subjectName=null;
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
			pstmt.setString(1, subjectId);
			//pstmt.setString(1, classid);
			
			System.out.println("subject list is "+pstmt);
			rst = pstmt.executeQuery();
			if (rst.next()) {
				subjectName=rst.getString("subjectName");
			}else{
				subjectName="";
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return subjectName;
	}

	public ArrayList<TimeTableVo> getClassSectionList(UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TimeTableDaoImpl : getClassSectionList Starting");

	PreparedStatement pstmt = null;
	ResultSet rst = null;
	Connection connection = null;
	ArrayList<TimeTableVo> classSectionList=new ArrayList<TimeTableVo>();
	
	try {
		connection = JDBCConnection.getSeparateConnection(userLoggingsVo);

		pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_SECTION_LIST);
		
		rst = pstmt.executeQuery();

			while (rst.next()) {
				
				TimeTableVo timetableVo=new TimeTableVo();
				
				timetableVo.setClassid(rst.getString("classid"));
				timetableVo.setClassname(rst.getString("classname"));
				
				classSectionList.add(timetableVo);
			}
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
		try {
			if (rst != null && (!rst.isClosed())) {
				rst.close();
			}
			if (pstmt != null && (!pstmt.isClosed())) {
				pstmt.close();
			}
			if (connection != null && (!connection.isClosed())) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TimeTableDaoImpl : getClassSectionList Ending");
	return classSectionList;
}

	public static void main(String[] args) {
	
		JSONArray array=new JSONArray();
		array.put(new TimeTableDaoImpl().getTeacherTimeTableDetails("TEA23", "ACY1"));
		
		
	}
	
	public String getClassNameDetailsDao(String classid,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Starting");
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String classname = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_NAME);
			pstmt.setString(1, classid);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				
				 classname = rst.getString("classdetails_name_var");
					
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Ending");
		
		return classname;
	}
	
	public String getSectionNameDetailsDao(String sectionid,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionNameDetailsDao Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String sectionname = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SECTION_NAME);
			pstmt.setString(1, sectionid);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				
				sectionname = rst.getString("classsection_name_var");
				 
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSectionNameDetailsDao Ending");
		
		return sectionname;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListByClass(String accyearid, String viewBy,
			String classId,String locationId,UserLoggingsPojo custdetails,String section) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			String query="SELECT cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id,CAST(st.created_date AS DATE) AS created_date,st.created_by,st.updated_by,CAST(st.updated_time AS DATE) AS updated_time,CONCAT(ct.FirstName,' ',ct.LastName) AS teachername FROM campus_classdetail cd JOIN campus_classsection sec ON cd.classdetail_id_int=sec.classdetail_id_int AND cd.`classdetail_id_int`=sec.`classdetail_id_int` AND sec.`locationId`=cd.locationId LEFT JOIN campus_timetable_student st ON sec.classsection_id_int=st.sectionid AND cd.classdetail_id_int=st.`classid` AND st.accyearid = ? JOIN campus_classteacher t ON sec.classsection_id_int=t.SectionCode AND t.`ClassCode`=cd.classdetail_id_int JOIN campus_teachers ct ON t.TeacherCode=ct.TeacherID WHERE ";
			
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!locationId.equalsIgnoreCase("%%")) {
				map.put("cd.locationId",locationId);
				vec.add("cd.locationId");
			}
			if(!classId.equalsIgnoreCase("%%")) {
				map.put("cd.classdetail_id_int",classId);
				vec.add("cd.classdetail_id_int");
			}
			if(!section.equalsIgnoreCase("%%")) {
				map.put("sec.classsection_id_int",section);
				vec.add("sec.classsection_id_int");
			}
			
			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql= key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			
			 
			String finalquery=query+" "+wheresql;
			pstmt = connection.prepareStatement(finalquery);
			pstmt.setString(1,accyearid);
			System.out.println("PSTMT "+pstmt);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				TimeTableVo voObj = new TimeTableVo();
				if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){
					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Yet to Create");
					voObj.setCreatedby("-");
					voObj.setCreateddate("-");
					voObj.setLastupdatedby("-");
					voObj.setLastupdated("-");
					voObj.setTimetableId("-");
					voObj.setTeachername(rst.getString("teachername"));
				}else{
					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Created");
					voObj.setCreatedby(getStaffName(rst.getString("created_by"),custdetails));
					voObj.setCreateddate(HelperClass.convertDatabaseToUI(rst.getString("created_date")));
					if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){
						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");
					}else{
						voObj.setLastupdatedby(getStaffName(rst.getString("updated_by"),custdetails));
						voObj.setLastupdated(HelperClass.convertDatabaseToUI(rst.getString("updated_time")));
					}
					voObj.setTimetableId(rst.getString("timetable_id"));
					voObj.setTeachername(rst.getString("teachername"));
				}
				details.add(voObj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	@Override
	public ArrayList<TimeTableVo> getClassTimeTableListBySection(String accyearid, String classId, String sectionId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_CLASS_TIMETABLE_LIST_BY_SECTION);
			pstmt.setString(1, accyearid.trim());
			pstmt.setString(2, classId);
			pstmt.setString(3, sectionId);
			System.out.println("pstmt is "+pstmt);

			rst = pstmt.executeQuery();

			while (rst.next()) {

				TimeTableVo voObj = new TimeTableVo();

				if(rst.getString("timetable_id")==null || "".equalsIgnoreCase(rst.getString("timetable_id"))){

					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Yet to Create");
					voObj.setCreatedby("-");
					voObj.setCreateddate("-");
					voObj.setLastupdatedby("-");
					voObj.setLastupdated("-");
					voObj.setTimetableId("-");
					voObj.setTeachername(rst.getString("teachername"));
				}else{

					voObj.setClassid(rst.getString("classdetail_id_int"));
					voObj.setClassname(rst.getString("classdetails_name_var"));
					voObj.setSectionid(rst.getString("classsection_id_int"));
					voObj.setSectionname(rst.getString("classsection_name_var"));
					voObj.setTimetableStatus("Created");
					voObj.setCreatedby(getStaffName(rst.getString("created_by"),custdetails));
					voObj.setCreateddate(rst.getString("created_date"));
					if(rst.getString("updated_by")==null || "".equalsIgnoreCase(rst.getString("updated_by"))){

						voObj.setLastupdatedby("-");
						voObj.setLastupdated("-");

					}else{

						voObj.setLastupdatedby(getStaffName(rst.getString("updated_by"),custdetails));
						voObj.setLastupdated(rst.getString("updated_time"));
					}

					voObj.setTimetableId(rst.getString("timetable_id"));
					voObj.setTeachername(rst.getString("teachername"));
				}
				details.add(voObj);
			}

			/*else{

				pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHER_TIMETABLE_LIST);
				pstmt.setString(1, accyearid.trim());

				rst = pstmt.executeQuery();

					while (rst.next()) {

						TimeTableVo voObj = new TimeTableVo();

						if(rst.getString("teachertimetable_id")==null || "".equalsIgnoreCase(rst.getString("teachertimetable_id"))){

							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Yet to Create");
							voObj.setCreatedby("-");
							voObj.setCreateddate("-");
							voObj.setLastupdatedby("-");
							voObj.setLastupdated("-");
							voObj.setTimetableId("-");

						}else{

							voObj.setTeacherId(rst.getString("TeacherID"));
							voObj.setRegno(rst.getString("registerId"));
							voObj.setTeachername(rst.getString("teachername"));
							voObj.setTimetableStatus("Created");
							voObj.setCreatedby(getStaffName(rst.getString("created_by")));
							voObj.setCreateddate(rst.getString("created_date"));
							if(rst.getString("updated_date")==null || "".equalsIgnoreCase(rst.getString("updated_date"))){

								voObj.setLastupdatedby("-");
								voObj.setLastupdated("-");

							}else{

								voObj.setLastupdatedby(getStaffName(rst.getString("updated_date")));
								voObj.setLastupdated(rst.getString("updated_time"));
							}

							voObj.setTimetableId(rst.getString("teachertimetable_id"));

						}
						details.add(voObj);
					}

				}*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}

	@Override
	public String getTeacherNameDetails(String classId, String sectionId,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		
		String teachername = null;
		try {
			
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_TEACHERNAME_BY_CLASS_SECTION);
			pstmt.setString(1, classId);
			pstmt.setString(2, sectionId);
			rst = pstmt.executeQuery();
			
			while(rst.next()){
				teachername = rst.getString("teachername");
					
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassNameDetailsDao Ending");
		
		return teachername;
	}
	
	
	private List<String> getSubjectDetailForClass(String subId,String classid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Starting");

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Connection connection = null;
		String subjectName=null;
		List<String> arr = new ArrayList<String>();
		try {
			connection = JDBCConnection.getSeparateConnection();
			
			pstmt = connection.prepareStatement(TimeTableSqlConstants.GET_SUBJECT_NAME);
			pstmt.setString(1, classid);
			
			rst = pstmt.executeQuery();
			while (rst.next()) {
				subjectName=rst.getString("subjectName");
				arr.add(subjectName);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubjectfName Ending");
		return arr;
	}


	public ArrayList<TimeTableVo> getClassTimeTableTodayListByClass(String accyearid, String sectionid, String classId, String locationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassTimeTableTodayListByClass Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		Connection connection = null;
		if(locationId.equalsIgnoreCase("ALL")){
			locationId="%%";
		}
		if(classId.equalsIgnoreCase("ALL")){
			classId="%%";
		}
		if(sectionid.equalsIgnoreCase("ALL")){
			sectionid="%%";
		}
		try {
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
	

				pstmt = connection.prepareStatement("SELECT DISTINCT cd.classdetails_name_var,cd.classdetail_id_int,sec.classsection_name_var,sec.classsection_id_int,st.timetable_id FROM campus_timetable_student st JOIN campus_classdetail cd ON st.classId=cd.classdetail_id_int AND st.locationId=cd.locationId JOIN campus_classsection sec ON sec.classsection_id_int=st.sectionid AND st.locationId = sec.locationId  WHERE  st.accyearid=? AND st.locationId LIKE ? AND st.classid LIKE ? AND st.sectionid LIKE ? ORDER BY LENGTH(cd.classdetail_id_int),cd.classdetail_id_int,LENGTH(sec.classsection_id_int),sec.classsection_id_int");
				pstmt.setString(1, accyearid.trim());
				pstmt.setString(2, locationId);
				pstmt.setString(3, classId);
				pstmt.setString(4, sectionid);
				
				System.out.println(pstmt);
				rst = pstmt.executeQuery();

				while (rst.next()) {
					int count=0;
					String timetable_id=rst.getString("timetable_id");
					
					TimeTableVo voObj = new TimeTableVo();

						voObj.setTimetableId(timetable_id);
						voObj.setClassid(rst.getString("classdetail_id_int"));
						voObj.setClassname(rst.getString("classdetails_name_var"));
						voObj.setSectionid(rst.getString("classsection_id_int"));
						voObj.setSectionname(rst.getString("classsection_name_var"));
						pstmt1 = connection.prepareStatement("SELECT tperiod1 AS teacher FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod2 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod3 FROM `today_timetable` WHERE id=?  UNION ALL SELECT tperiod4 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod5 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod6 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod7 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod8 FROM `today_timetable` WHERE id=? UNION ALL SELECT tperiod9 FROM `today_timetable` WHERE id=? ");		
						for(int i=1;i<=9;i++)
							pstmt1.setString(i, timetable_id);
						
						rst1=pstmt1.executeQuery();
						while(rst1.next()){
							PreparedStatement chkpstmt=connection.prepareStatement("SELECT COUNT(*) FROM `campus_teacher_attendence` WHERE TeacherID=? AND AttendenceDate=CURDATE() AND AttendenceStatus='Present' ");
							chkpstmt.setString(1, rst1.getString("teacher"));
							ResultSet chkRs=chkpstmt.executeQuery();
							if(chkRs.next()){
								if(chkRs.getInt(1)==0)
								count++;
							}
							chkRs.close();
							chkpstmt.close();
						}
					voObj.setCount(count);
					details.add(voObj);
				}
			

			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getClassTimeTableTodayListByClass Ending");
		return details;
	}


	public synchronized ArrayList<TimeTableVo> getTodayTimeTableDetails(String timetableId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		Connection connection = null;
		try {
				connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
				pstmt1 = connection.prepareStatement("SELECT DISTINCT vd.sectionid,vd.date,vd.classId,vd.today,vd.period1,vd.period2,vd.period3,vd.period4,vd.period5,vd.period6,vd.period7,vd.period8,vd.period9,vd.tperiod1,vd.tperiod2,vd.tperiod3,vd.tperiod4,vd.tperiod5,vd.tperiod6,vd.tperiod7,vd.tperiod8,vd.tperiod9 FROM today_timetable vd  WHERE vd.id=?");
				pstmt1.setString(1, timetableId.trim());
				//pstmt1.setString(2, classid);
				
							
				rst1 = pstmt1.executeQuery();
				System.out.println("2nd query:::"+pstmt1);
				
				while(rst1.next()) {
					TimeTableVo voObj = new TimeTableVo();
					
					voObj.setClassid(rst1.getString("classId"));
					voObj.setSectionid(rst1.getString("sectionid"));
					voObj.setDayid(rst1.getString("date"));
					voObj.setDayname(rst1.getString("today"));
					voObj.setPeriodId1(rst1.getString("period1"));
					voObj.setPeriodId2(rst1.getString("period2"));
					voObj.setPeriodId3(rst1.getString("period3"));
					voObj.setPeriodId4(rst1.getString("period4"));
					voObj.setPeriodId5(rst1.getString("period5"));
					voObj.setPeriodId6(rst1.getString("period6"));
					voObj.setPeriodId7(rst1.getString("period7"));
					voObj.setPeriodId8(rst1.getString("period8"));
					voObj.setPeriodId9(rst1.getString("period9"));
					voObj.setTeacherId1(rst1.getString("tperiod1"));
					voObj.setTeacherId2(rst1.getString("tperiod2"));
					voObj.setTeacherId3(rst1.getString("tperiod3"));
					voObj.setTeacherId4(rst1.getString("tperiod4"));
					voObj.setTeacherId5(rst1.getString("tperiod5"));
					voObj.setTeacherId6(rst1.getString("tperiod6"));
					voObj.setTeacherId7(rst1.getString("tperiod7"));
					voObj.setTeacherId8(rst1.getString("tperiod8"));
					voObj.setTeacherId9(rst1.getString("tperiod9"));
					voObj.setPeriod1(getSubjectfName(rst1.getString("period1"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod2(getSubjectfName(rst1.getString("period2"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod3(getSubjectfName(rst1.getString("period3"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod4(getSubjectfName(rst1.getString("period4"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod5(getSubjectfName(rst1.getString("period5"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod6(getSubjectfName(rst1.getString("period6"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod7(getSubjectfName(rst1.getString("period7"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod8(getSubjectfName(rst1.getString("period8"),voObj.getClassid(),userLoggingsVo));
					voObj.setPeriod9(getSubjectfName(rst1.getString("period9"),voObj.getClassid(),userLoggingsVo));
					details.add(voObj);
				}
		 
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTimeTableDetails Ending");
		return details;
	}


	public String updateTodayTimeTableDetails(TimeTablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Starting");
		
		PreparedStatement tpstmt =null;
		String result = null;
		Connection connection = null;
		try {
			int status=0;
			
			connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
					for (int i = 0; i < pojo.getDayid().length; i++) {
					tpstmt=connection.prepareStatement("update  today_timetable set tperiod1=?,tperiod2=?,tperiod3=?,tperiod4=?,tperiod5=?,tperiod6=?,tperiod7=?,tperiod8=?,tperiod9=? where id=?");
					
					
					tpstmt.setString(1, pojo.getTperiod1()[i]);
					tpstmt.setString(2, pojo.getTperiod2()[i]);
					tpstmt.setString(3, pojo.getTperiod3()[i]);
					tpstmt.setString(4, pojo.getTperiod4()[i]);
					tpstmt.setString(5, pojo.getTperiod5()[i]);
					tpstmt.setString(6, pojo.getTperiod6()[i]);
					tpstmt.setString(7, pojo.getTperiod7()[i]);
					tpstmt.setString(8, pojo.getTperiod8()[i]);
					tpstmt.setString(9, pojo.getTperiod9()[i]);
					tpstmt.setString(10, pojo.getTimetableID());
					
					status=tpstmt.executeUpdate();
					
					}
			
				if (status > 0 ) {
					HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Staff","TimeTable Substitution","Update",tpstmt.toString(),userLoggingsVo);
					result="success";
				} else {
						result = "fail";
					
				}
				
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : updateTimeTableDetails Ending");

		return result;
	}


	@Override
	public String checkTeacher(String dayId, String teacherId,UserLoggingsPojo userLoggingsVo, String location,String period) {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : checkTeacher Starting");
		
		PreparedStatement tpstmt =null;
		ResultSet rs =null;
		String result = null;
		Connection connection = null;
		try {
			int status=0;
					connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
					tpstmt=connection.prepareStatement("SELECT COUNT(*) FROM `campus_timetable_details` WHERE `daycode`=? AND period=? AND teacherid=? AND accyearid=?");
					tpstmt.setString(1,dayId);
					tpstmt.setString(2, period);
					tpstmt.setString(3, teacherId);
					tpstmt.setString(4, userLoggingsVo.getAcaYearId());
					//System.out.println("tpstmt"+tpstmt);
					rs=tpstmt.executeQuery();
					while(rs.next()){
						status=rs.getInt(1);
					}
					if(status>0){
						result="alreadymapped";
					}else{
						result="notmapped";
					}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} finally {
						try {
							
							if (rs != null && (!rs.isClosed())) {
								rs.close();
							}
							
							
							if (tpstmt != null && (!tpstmt.isClosed())) {
								tpstmt.close();
							}
							
							if (connection != null && (!connection.isClosed())) {
								connection.close();
							}

						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						}
					}
					JLogger.log(0, JDate.getTimeString(new Date())
							+ MessageConstants.END_POINT);
					logger.info(JDate.getTimeString(new Date())
							+ " Control in TimeTableDaoImpl : checkTeacher Ending");

					return result;
	}

	public ArrayList<TimeTableVo> getTeacherTimeTableTodayListByClass(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableTodayListByClass Starting");

		ArrayList<TimeTableVo> details = new ArrayList<TimeTableVo>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int count=0;
		int attendancecount=0;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		String status="";
		
		Connection connection = null;
		
		
		try {
				connection = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt1=connection.prepareStatement("SELECT COUNT(*) FROM `substitution_log_audit` WHERE createdTime LIKE ?");
				pstmt1.setString(1, HelperClass.getCurrentSqlDate()+"%");
				rst1=pstmt1.executeQuery();
				while(rst1.next()) {
					count=rst1.getInt(1);
				}
				if(count==0) {
					pstmt = connection.prepareStatement("SELECT ct.`Abbreviative_Id`,ct.`FirstName`,GROUP_CONCAT(CONCAT(cc.`classdetails_name_var`,'-',cs.`classsection_name_var`)) class ,GROUP_CONCAT(csub.`subjectName`) subj,GROUP_CONCAT(ctd.period) period FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid JOIN `campus_subject` csub ON csub.subjectID=ctd.subjectid  JOIN `campus_timetable_student` cts ON cts.`timetable_id`=ctd.timtable_id JOIN `campus_classsection` cs ON cs.`classsection_id_int`=cts.sectionid JOIN `campus_classdetail` cc ON (cc.`classdetail_id_int`=cs.classdetail_id_int AND cc.locationId=cs.locationId)  WHERE ctd.teacherid IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `TeacherID` IN(SELECT DISTINCT `teacherid` FROM `today_timetable_details_for_substitution` ) AND `AttendenceDate`=CURDATE() AND AttendenceStatus!='Present') AND cday.dayname=DAYNAME(CURDATE()) GROUP BY ctd.teacherid");
					status="notSubstituted";
				}
				else {
					pstmt = connection.prepareStatement("SELECT ct.`Abbreviative_Id`,ct.`FirstName`,GROUP_CONCAT(CONCAT(cc.`classdetails_name_var`,'-',cs.`classsection_name_var`)) class,GROUP_CONCAT(csub.`subjectName`) subj,GROUP_CONCAT(ctd.period) period FROM `today_timetable_details` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid JOIN `campus_subject` csub ON csub.subjectID=ctd.subjectid  JOIN `campus_timetable_student` cts ON cts.`timetable_id`=ctd.timtable_id JOIN `campus_classsection` cs ON cs.`classsection_id_int`=cts.sectionid JOIN `campus_classdetail` cc ON (cc.`classdetail_id_int`=cs.classdetail_id_int AND cc.locationId=cs.locationId)  WHERE  cday.dayname=DAYNAME(CURDATE()) GROUP BY ctd.teacherid");
					status="substituted";
				}
				rst = pstmt.executeQuery();
				while (rst.next()) {
					TimeTableVo vo=new TimeTableVo();
					attendancecount++;
					vo.setTeachername(rst.getString("Abbreviative_Id")+"-"+rst.getString("FirstName"));
					vo.setClassname(rst.getString("class"));
					vo.setPeriod1(rst.getString("period")+"-"+rst.getString("subj"));
					vo.setStatus(status);
					details.add(vo);
				}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}	
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getTeacherTimeTableTodayListByClass Ending");
		return details;
	}
	public String getSubstitute(String userid,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubstitute Starting");

		
		PreparedStatement pstmt0 = null;
		PreparedStatement tpstmt=null;
		PreparedStatement trntmt=null;
		PreparedStatement pstmt = null;
		PreparedStatement upstmt=null;
		PreparedStatement tupstmt=null;
		ResultSet rst = null;
		int count=0;
		PreparedStatement pstmt1 = null;
		ResultSet rst1 = null;
		String status="";
		
		Connection connection = null;
		
		
		try {
			connection = JDBCConnection.getSeparateConnection(custdetails);
			connection.setAutoCommit(false);
				trntmt=connection.prepareStatement("DELETE FROM today_timetable_details");
				trntmt.executeUpdate();
				pstmt0=connection.prepareStatement("INSERT INTO `substitution_log_audit`(createdBy) VALUES (?)");
				pstmt0.setString(1,userid);
				pstmt0.executeUpdate();
				pstmt = connection.prepareStatement("SELECT ct.designation,ctd.*  FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode JOIN `campus_teachers` ct ON ct.`TeacherID`=ctd.teacherid  WHERE ctd.teacherid IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `TeacherID` IN(SELECT DISTINCT `teacherid` FROM `today_timetable_details_for_substitution` ) AND `AttendenceDate`=CURDATE() AND AttendenceStatus!='Present') AND cday.dayname=DAYNAME(CURDATE())");
				
				rst = pstmt.executeQuery();
				while (rst.next()) {
					String teacherId="";
				
					pstmt1=connection.prepareStatement("SELECT TeacherID FROM `campus_teachers` WHERE TeacherID IN(SELECT TeacherID FROM `campus_teacher_attendence` WHERE `AttendenceDate`=CURDATE() AND AttendenceStatus='Present') AND TeacherID NOT IN(SELECT DISTINCT ctd.`teacherid` FROM `today_timetable_details_for_substitution` ctd JOIN `campus_timetable_day` cday ON cday.daycode=ctd.daycode WHERE  cday.dayname=DAYNAME(CURDATE()) AND ctd.period =?) AND designation=? ORDER BY RAND() LIMIT 1");
					pstmt1.setString(1, rst.getString("period"));
					pstmt1.setString(2,rst.getString("designation"));
					System.out.println("Teacher="+pstmt1);
					rst1=pstmt1.executeQuery();
					if(rst1.next()) {
						teacherId=rst1.getString("TeacherID");
						
						 tpstmt=connection.prepareStatement("INSERT INTO `today_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
						tpstmt.setString(1, teacherId);
						tpstmt.setString(2, rst.getString("subjectid"));
						tpstmt.setString(3, rst.getString("timtable_id"));
						tpstmt.setString(4, rst.getString("daycode"));
						tpstmt.setString(5, rst.getString("period"));
						tpstmt.setString(6, rst.getString("accyearid"));
						tpstmt.setString(7, userid);
						tpstmt.executeUpdate();
						upstmt=connection.prepareStatement("UPDATE today_timetable_details_for_substitution SET teacherid=? WHERE teacherid=? AND timtable_id=? AND daycode=? AND period=? AND accyearid=?");
						upstmt.setString(1, teacherId);
						upstmt.setString(2, rst.getString("teacherid"));
						upstmt.setString(3, rst.getString("timtable_id"));
						upstmt.setString(4, rst.getString("daycode"));
						upstmt.setString(5, rst.getString("period"));
						upstmt.setString(6, rst.getString("accyearid"));
						upstmt.executeUpdate();
						
						
						tupstmt=connection.prepareStatement("UPDATE today_timetable SET t"+rst.getString("period")+"=? WHERE  id=? AND daycode=?  AND accyearid=?");
						tupstmt.setString(1, teacherId);
						tupstmt.setString(2, rst.getString("timtable_id"));
						tupstmt.setString(3, rst.getString("daycode"));
						tupstmt.setString(4, rst.getString("accyearid"));
						tupstmt.executeUpdate();
						
						
						count++;
					}
					else {
						
						 tpstmt=connection.prepareStatement("INSERT INTO `today_timetable_details` (`teacherid`,`subjectid`,`timtable_id`,`daycode`,`period`,`accyearid`,`created_by`) VALUES(?,?,?,?,?,?,?)");
							tpstmt.setString(1, "");
							tpstmt.setString(2, rst.getString("subjectid"));
							tpstmt.setString(3, rst.getString("timtable_id"));
							tpstmt.setString(4, rst.getString("daycode"));
							tpstmt.setString(5, rst.getString("period"));
							tpstmt.setString(6, rst.getString("accyearid"));
							tpstmt.setString(7, userid);
							tpstmt.executeUpdate();
							count++;
					}
				
				}
			
			
				if(count>0) {
					connection.commit();
					status="true";
				}
				else {
					status="false";
				}
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (rst != null && (!rst.isClosed())) {
					rst.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (rst1 != null && (!rst1.isClosed())) {
					rst1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (tpstmt != null && (!tpstmt.isClosed())) {
					tpstmt.close();
				}
				if (upstmt != null && (!upstmt.isClosed())) {
					upstmt.close();
				}
				if (tupstmt != null && (!tupstmt.isClosed())) {
					tupstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TimeTableDaoImpl : getSubstitute Ending");
		return status;
	}
	}