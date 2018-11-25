package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.ExamDetailsDao;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.ExamDetailsSQLUtil;
import com.centris.campus.util.ExamSqlUtils;
import com.centris.campus.util.ExamTimeTableSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;


public class ExamDetailsDaoImpl implements ExamDetailsDao {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsDaoImpl.class);

	@Override
	public List<ExamDetailsPojo> getExamDetailsDao(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getExamDetailsDao Starting");
		
		List<ExamDetailsPojo> examPojoList = new ArrayList<ExamDetailsPojo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ExamSqlUtils.GET_EXAM_DETAILS);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ExamDetailsPojo examPojo = new ExamDetailsPojo();
				
				examPojo.setExamid(rs.getString("examid"));
				examPojo.setExamName(rs.getString("examname"));
				examPojo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				examPojo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				examPojo.setAccyear(rs.getString("acadamic_year"));
				examPojo.setDescription(rs.getString("description"));
				examPojoList.add(examPojo);
				
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getExamDetailsDao Endinging");
		return examPojoList;
	}

    //get Academic year Name
	public String getaccyName(String accyear,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getaccyName Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String accyName = null;
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement("select acadamic_year from campus_acadamicyear where acadamic_id = ?");
			pstmt.setString(1,accyear);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				accyName = rs.getString("acadamic_year");
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getaccyName Endinging");
		return accyName;
      }
public String insertGradeSettings(ExamDetailsPojo obj) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String msg =null;
		int count =0;
		try{
			conn = JDBCConnection.getSeparateConnection(obj.getCustid());
			pstmt = conn.prepareStatement("insert into campus_exam_gradesettings (grade_id,grade_name,comments,min_marks,max_marks,accyear,created_by,loc_id) values (?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_exam_gradesettings",obj.getCustid()));
			pstmt.setString(2,obj.getGradename());
			pstmt.setString(3,obj.getComments());
			pstmt.setString(4,obj.getMin_marks());
			pstmt.setString(5,obj.getMax_marks());
			pstmt.setString(6,obj.getAccyearId());
			pstmt.setString(7,obj.getCreatedBy());
			pstmt.setString(8,obj.getLocid());
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","GradeSetup","Insert",pstmt.toString(),obj.getCustid());
				msg="success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertGradeSettings Ending");
		return msg;
	}

	@Override
	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear,String location,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : displayGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExamDetailsPojo> list = new ArrayList<ExamDetailsPojo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
		if(location.equalsIgnoreCase("%%")){
			pstmt = conn.prepareStatement("select * from  campus_exam_gradesettings where accyear=? and loc_id like ? order by substring(grade_name, 1, 1),case substring(grade_name,2)when '++' then 1 when '+' then 2 end ");
		}else{
			pstmt = conn.prepareStatement("select * from  campus_exam_gradesettings where accyear=? and loc_id=? order by substring(grade_name, 1, 1),case substring(grade_name,2)when '++' then 1 when '+' then 2 end ");
		}
			pstmt.setString(1,accyear);
			pstmt.setString(2,location);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExamDetailsPojo pojo = new ExamDetailsPojo();
				pojo.setGradename(rs.getString("grade_name"));
				pojo.setComments(rs.getString("comments"));
				pojo.setMin_marks(rs.getString("min_marks"));
				pojo.setMax_marks(rs.getString("max_marks"));
				pojo.setGradeid(rs.getString("grade_id"));
				list.add(pojo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : displayGradeSettings Ending");
		return list;
	}

	@Override
	public String deleteGradeSettings(String gradeid,String locname,String accyear,ExamDetailsPojo obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : deleteGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection(obj.getCustid());
			pstmt = conn.prepareStatement("delete from campus_exam_gradesettings where grade_id = ? and loc_id=? and accyear=?");
			pstmt.setString(1,gradeid);
			pstmt.setString(2,locname);
			pstmt.setString(3,accyear);
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","deleteGradeSettings","Delete",pstmt.toString(),obj.getCustid());
				msg = "success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : deleteGradeSettings Ending");
		return msg;
	}

	@Override
	public String editGradeSettings(ExaminationDetailsVo list,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("update campus_exam_gradesettings set grade_name=?,comments=?,min_marks=?,max_marks=? where grade_id=? and loc_id=? and accyear=?");
			pstmt.setString(1,list.getGradename());
			pstmt.setString(2,list.getComments());
			pstmt.setString(3,list.getMin_marks());
			pstmt.setString(4,list.getMax_marks());	
			pstmt.setString(5,list.getGradeid());
			pstmt.setString(6,list.getLocationid());
			pstmt.setString(7,list.getAccyear());
			System.out.println("pstmt" + pstmt);
			count = pstmt.executeUpdate();
			if(count >0){
				HelperClass.recordLog_Activity(list.getLog_audit_session(),"Exam","GradeSetup","Update",pstmt.toString(),userLoggingsVo);
				msg = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Ending");
		return msg;
	}

	@Override
	public String checkduplicateGrade(String accyear, String gradename,String loc,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : checkduplicateGrade Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select count(*) gradename from campus_exam_gradesettings where grade_name = ? and accyear = ? and loc_id=?");
			pstmt.setString(1,gradename);
			pstmt.setString(2,accyear);
			pstmt.setString(3,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("gradename");
			}
			if(count > 0){
				msg = "true";
			}else{
				msg = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : checkduplicateGrade Ending");
		return msg;
	}

	
	// SubjectWise Marks
	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("select examid,examcode,examname,startdate,enddate from campus_examination where acadamicyear =?");
			pstmt.setString(1,acyear.trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(*) from campus_subject_marks_wise where examid = ? and Accyear_Id = ?");
				pstmtstatus.setString(1,rs.getString("examid"));
				pstmtstatus.setString(2,acyear.trim());
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				pstmtstatus.close();
				if(count > 0){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear,String schoolLocation,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getSubjectmarksList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count1 = 0;
	
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
		//	pstmt = conn.prepareStatement("SELECT exm.classid,exm.`examid`,exm.`examcode`,exm.`examname`,exm.`startdate`,exm.`enddate`,exm.`isapplicableperiodic`,cls.`classdetails_name_var`,cet.`examtypename`,cet.`examtypeid` FROM campus_examination exm LEFT JOIN `campus_classdetail` cls ON cls.`classdetail_id_int`=exm.`classid` AND cls.`locationId`=exm.`loc_id` LEFT JOIN `campus_examtype` cet ON cet.`examtypeid`=exm.`examtype` WHERE acadamicyear=? AND loc_id =? AND cls.`isActive`='Y' ORDER BY LENGTH(cls.classdetail_id_int),cls.classdetail_id_int,exm.`startdate`");
			pstmt = conn.prepareStatement("SELECT exm.status,exm.classid,exm.`examid`,exm.`examcode`,exm.`examname`,exm.`startdate`,exm.`enddate`,exm.`isapplicableperiodic`,cls.`classdetails_name_var`,cet.`examtypename`,cet.`examtypeid` FROM campus_examination exm LEFT JOIN `campus_classdetail` cls ON cls.`classdetail_id_int`=exm.`classid` AND cls.`locationId`=exm.`loc_id` JOIN `campus_classstream` ccs ON ccs.`classstream_id_int`=cls.`classstream_id_int` LEFT JOIN `campus_examtype` cet ON cet.`examtypeid`=exm.`examtype` WHERE acadamicyear=? AND loc_id =? AND cls.`isActive`='Y' AND ccs.`isActive`='Y'  ORDER BY LENGTH(cls.classdetail_id_int),cls.classdetail_id_int,exm.`startdate`");
			pstmt.setString(1,accyear.trim());
			pstmt.setString(2,schoolLocation.trim());
		
			rs = pstmt.executeQuery();
			while(rs.next()){
				count1++;
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				obj.setCount(count1);
				obj.setClassid(rs.getString("classid"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				int count = 0;
				PreparedStatement pstmtstatus = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` st LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=st.`student_id_int` WHERE csc.`fms_acadamicyear_id_int`=? AND csc.`locationId`=? AND csc.`classdetail_id_int`=? AND st.`student_status_var`='active'");
				pstmtstatus.setString(1, accyear.trim());
				pstmtstatus.setString(2, schoolLocation.trim());
				pstmtstatus.setString(3, rs.getString("classid"));
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count =statusrs.getInt(1);
				}
				if(count==0){
					obj.setStatus("Pending");
				}else{
				int classcount=0,subcount=0;
				PreparedStatement classcnt=conn.prepareStatement("SELECT `subjectID` FROM `campus_subject` WHERE `classid`=? AND `locationId`=? AND isActive='Y'");
				classcnt.setString(1, rs.getString("classid"));
				classcnt.setString(2, schoolLocation.trim());
			//	classcnt.setString(3, rs.getString("examid"));
				ResultSet clsc = classcnt.executeQuery();
				while(clsc.next()){
					subcount++;
					
					PreparedStatement pstmtcountstatus = conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` WHERE sub_id=? AND `classid` = ? AND `Academic_yearid` = ? AND `location_id` = ? AND exam_id=?");
					pstmtcountstatus.setString(1, clsc.getString("subjectID"));
					pstmtcountstatus.setString(2,  rs.getString("classid"));
					pstmtcountstatus.setString(3, accyear);
					pstmtcountstatus.setString(4, schoolLocation);
					pstmtcountstatus.setString(5, rs.getString("examid"));
					ResultSet rs2 = pstmtcountstatus.executeQuery();
					while(rs2.next())
					{
						classcount=classcount+rs2.getInt(1);
					}
					statusrs.close();
					pstmtstatus.close();
					rs2.close();
					pstmtcountstatus.close();
					
				}
			
					if(count!=0 && classcount!=0 &&(count == classcount/subcount)){
					obj.setStatus("Completed");
					}else{
					obj.setStatus("Pending");
					}
					clsc.close();
					classcnt.close();
				}
				/*PreparedStatement pstmtstatus = conn.prepareStatement("select count(classdetail_id_int) from campus_classsection where locationId=?");
				pstmtstatus.setString(1,schoolLocation);
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement pstmtcountstatus = conn.prepareStatement("select count(distinct (classId)) from campus_subject_marks_wise where loc_id=?");
				pstmtcountstatus.setString(1, schoolLocation);
				ResultSet rs2 = pstmtcountstatus.executeQuery();
				while(rs2.next())
				{
					classcount=rs2.getInt(1);
				}*/
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getSubjectmarksList Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear, String examid,String locid,String classid,UserLoggingsPojo custdetails) {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getSubjectClass Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,totalstrength=0;
		
		String msg = null;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_id_int,ccs.classsection_name_var FROM campus_classdetail ccd LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId WHERE ccd.locationId=? AND ccd.classdetail_id_int=? ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var");
			pstmt.setString(1, locid);
			pstmt.setString(2, classid);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				PreparedStatement pstmtcount=conn.prepareStatement("SELECT COUNT(*) AS studentcount FROM `campus_student` st JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=st.`student_id_int` WHERE csc.`fms_acadamicyear_id_int`=? AND csc.`locationId`=? AND csc.`classdetail_id_int`=? AND csc.`classsection_id_int`=? AND st.`student_status_var`='active'");
				pstmtcount.setString(1,accyear);
				pstmtcount.setString(2,locid);
				pstmtcount.setString(3,rs.getString("classdetail_id_int"));
				pstmtcount.setString(4, rs.getString("classsection_id_int"));
				System.out.println("NO OF STUDENTS "+pstmtcount);
				ResultSet rs1 =pstmtcount.executeQuery();
				int sc=0;
				while(rs1.next())
				{
					sc=rs1.getInt("studentcount");
					obj.setTot_strength(Integer.toString(sc));		
				}
				System.out.println(sc);
				if(sc>0){
				slno++;
				obj.setSno1(slno);
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setSpecialization("-");
				
				
				int checktotal = Integer.parseInt(obj.getTot_strength());
			    if(checktotal>0)
			    {
			    	int subcount=0;
			    	int noofsubjects=0;
				//PreparedStatement pstmtstatus = conn.prepareStatement("select count(cm.scored_marks) from campus_studentwise_mark_details cm where cm.classid=? and cm.sectionid=?");
				PreparedStatement countstatus = conn.prepareStatement("select subjectID from campus_subject where classid=? AND isActive='Y'");
				countstatus.setString(1,obj.getClassId());
				ResultSet counrs = countstatus.executeQuery();
				while(counrs.next())
				{
					noofsubjects++;
					 PreparedStatement pstmtstatus = conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` WHERE `classid` = ? AND `sectionid` = ? AND `Academic_yearid` = ? AND `location_id` = ? AND `exam_id`=? AND sub_id=?");
						pstmtstatus.setString(1,rs.getString("classdetail_id_int"));
						pstmtstatus.setString(2,rs.getString("classsection_id_int"));
						pstmtstatus.setString(3,accyear);
						pstmtstatus.setString(4,locid);
						pstmtstatus.setString(5,examid);
						pstmtstatus.setString(6,counrs.getString("subjectID"));
						ResultSet statusrs = pstmtstatus.executeQuery();
						while(statusrs.next()){
							//count = statusrs.getInt(1);
							subcount =subcount+statusrs.getInt(1);
						}
						
				//	
						 statusrs.close();
						 pstmtstatus.close();
				}
				String total=obj.getTot_strength();
				int foo = Integer.parseInt(total);
				if(noofsubjects!=0){
					//System.out.println(subcount+"/"+noofsubjects+"||"+subcount/noofsubjects+" == "+foo);
				if(subcount/noofsubjects==foo){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
				}
				}
				else
			    {
			    	obj.setStatus("Pending");
			    }
				counrs.close();
			    countstatus.close();
			   
			    }
			    else
			    {
			    	obj.setStatus("Pending");
			    }
			   
			    pstmtcount.close();
			    rs1.close();
				list.add(obj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getSubjectClass Ending");
		
		return list;
	
	}

	
	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear, String examid,String locid,UserLoggingsPojo userLoggingsVo,String classid) {

		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt3 = null;
		ResultSet rs = null,rs2=null,rs7=null;
		
		int slno = 0;
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
		/*	if(classid.equals("CCD14") || classid.equals("CCD15")){
				pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_name_var,spec.`Specialization_Id`,CONCAT(spec.`Specialization_name`,'-',ccs.classsection_name_var) AS section,ccs.classsection_id_int FROM campus_classdetail ccd LEFT JOIN campus_class_specialization spec ON spec.`ClassDetails_Id`=ccd.classdetail_id_int LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=ccd.classdetail_id_int WHERE ccd.locationId=? AND ccd.classdetail_id_int=? ORDER BY ccs.classsection_name_var");
			}else{*/
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,ccs.classsection_id_int,ccs.classsection_name_var FROM campus_classdetail ccd LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=ccd.classdetail_id_int AND ccd.locationId=ccs.locationId WHERE ccd.locationId=? AND ccd.classdetail_id_int=? AND ccd.isActive='Y' AND ccs.isActive='Y' ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,ccs.classsection_name_var");
			//}
			pstmt.setString(1, locid);
			pstmt.setString(2, classid);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				int sc=0;
				pstmt3 =conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` st LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=st.`student_id_int` AND st.`fms_acadamicyear_id_int`=csc.`fms_acadamicyear_id_int` WHERE csc.`fms_acadamicyear_id_int`=? AND csc.`locationId`=? AND csc.`classdetail_id_int`=? AND csc.`classsection_id_int`=? AND `student_status_var`='active'");					
				pstmt3.setString(1,accyear);
				pstmt3.setString(2,locid);
				pstmt3.setString(3,rs.getString("classdetail_id_int"));
				pstmt3.setString(4,rs.getString("classsection_id_int"));
				rs7=pstmt3.executeQuery();
				while(rs7.next())
				{
					sc=rs7.getInt(1);
				}
				
				System.out.println("STUDENT COUNT "+sc);
				
				if(sc>0){
					slno++;
					obj.setSno1(slno);
					obj.setClassId(rs.getString("classdetail_id_int"));
					obj.setClassname(rs.getString("classdetails_name_var"));
					obj.setSection(rs.getString("classsection_id_int"));
					obj.setSectionName(rs.getString("classsection_name_var"));
					/*if(classid.equals("CCD14") || classid.equals("CCD15")){
						obj.setSpecialization(rs.getString("section"));
					}else{
						obj.setSpecialization("-");
					}*/
					int stucount1=0;
					int subjectidcount=0;
					int nosub = 0;
					//PreparedStatement pstmtcount=conn.prepareStatement("select count(subjectName) as subjectcount from campus_subject where locationId=? and classid=?");
					PreparedStatement pstmtcount=conn.prepareStatement("SELECT `subjectID` FROM `campus_subject` WHERE `classid`=? AND `locationId`=? AND isActive='Y'");
					pstmtcount.setString(1,obj.getClassId());
					pstmtcount.setString(2,locid);
					ResultSet rs1 =pstmtcount.executeQuery(); 

					//int checktotal = Integer.parseInt(obj.getTot_strength());
					while(rs1.next())
					{
						nosub++;
						PreparedStatement stucount=conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` st LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=st.`student_id_int` WHERE csc.`fms_acadamicyear_id_int`=? AND csc.`locationId`=? AND csc.`classdetail_id_int`=? AND csc.`classsection_id_int`=? AND st.`student_status_var`='active'");					
						stucount.setString(1,accyear);
						stucount.setString(2,locid);
						stucount.setString(3,obj.getClassId());
						stucount.setString(4,obj.getSection());
						rs2=stucount.executeQuery();
						while(rs2.next())
						{
							stucount1=stucount1+rs2.getInt(1);
						}


						//	PreparedStatement pstmtcount1=conn.prepareStatement("select count(distinct subject_id) as subjectid from campus_subject_marks_wise where classId=? and SectionId=? and loc_id=?");
						PreparedStatement pstmtcount1=conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` WHERE sub_id=? AND `classid` = ? AND `sectionid` = ? AND `Academic_yearid` = ? AND `location_id` = ? AND exam_id=?");
						pstmtcount1.setString(1,rs1.getString("subjectID"));
						pstmtcount1.setString(2,obj.getClassId());
						pstmtcount1.setString(3,obj.getSection());
						pstmtcount1.setString(4,accyear);
						pstmtcount1.setString(5,locid);
						pstmtcount1.setString(6,examid);
						ResultSet rs3 =pstmtcount1.executeQuery();
						while(rs3.next())
						{
							subjectidcount=subjectidcount+rs3.getInt(1);
						}
						rs3.close();
						pstmtcount1.close();
						rs2.close();
						stucount.close();
					}
					//obj.setTot_strength(rs1.getString("subjectcount"));	
					obj.setTot_strength(Integer.toString(nosub));	

					//System.out.println(stucount1+"=="+subjectidcount);
					if(stucount1==subjectidcount && stucount1!=0)
					{
						obj.setStatus("Completed");
					}
					else{
						obj.setStatus("Pending");
					}

					list.add(obj);
					rs1.close();
					pstmtcount.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				//ConnectionS Are closed inside While Loop 
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs7 != null&& (!rs7.isClosed())) {
					rs7.close();
				}
				if (pstmt3 != null&& (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	
	}
	
	
	@Override
	public String getexamName(String examid,String accyear,String locid,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getexamName Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String msg = null;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select examname,examcode from campus_examination where examid = ? and acadamicyear =? and loc_id=?");
			pstmt.setString(1,examid.trim());
			pstmt.setString(2,accyear.trim());
			pstmt.setString(3,locid.trim());
			rs = pstmt.executeQuery();
			while(rs.next()){
				msg = rs.getString("examname") +","+rs.getString("examcode");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getexamName Ending");
		return msg;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamclassDetails(ExaminationDetailsVo obj,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getexamclassDetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			//pstmt = conn.prepareStatement("select classdetails_name_var,classsection_name_var,ca.acadamic_year,ce.examcode,ce.examname,ce.startdate,ce.enddate from campus_acadamicyear ca,campus_classdetail cd,campus_classsection cs,campus_examination ce where cd.classdetail_id_int=? and classsection_id_int=? and ce.examid =? and ca.acadamic_id =?");
			pstmt = conn.prepareStatement("SELECT ce.examcode,ce.examname,ce.startdate,ce.enddate,cd.classdetails_name_var,cs.classsection_name_var,ca.acadamic_year,cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade`,cext.`exam_prefix` FROM campus_examination ce LEFT JOIN campus_classdetail cd ON cd.classdetail_id_int=ce.`classid` LEFT JOIN campus_classsection cs ON cs.classdetail_id_int=cd.classdetail_id_int LEFT JOIN campus_acadamicyear ca ON ca.acadamic_id=ce.`acadamicyear` LEFT JOIN `campus_student_co_scholastic_areas` cscsa ON cscsa.`exam_id`=ce.`examid` LEFT JOIN `campus_examtype` cext ON cext.`examtypeid`=ce.`examtype` WHERE ce.classid=? AND cs.classsection_id_int=? AND ce.examid =? AND ce.`acadamicyear`=? AND ce.`loc_id`=?");
			//pstmt.setString(1,obj.getStudentid());
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,obj.getExamid());
			pstmt.setString(4,obj.getAccyearid());
			pstmt.setString(5,obj.getLocationid());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obje = new ExaminationDetailsVo();
				obje.setAccyear(rs.getString("acadamic_year"));
				obje.setSectionName(rs.getString("classsection_name_var"));
				obje.setClassname(rs.getString("classdetails_name_var"));
				obje.setExamCode(rs.getString("examcode"));
				obje.setExamName(rs.getString("examname"));
				obje.setExamtypeprefix(rs.getString("exam_prefix"));
				obje.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obje.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				
				
				pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM `campus_student_co_scholastic_areas` WHERE `exam_id`=? AND `location_id`=? AND `student_id`=? AND `acc_yearid`=?");
				pstmt1.setString(1,obj.getExamid());
				pstmt1.setString(2,obj.getLocationid());
				pstmt1.setString(3,obj.getStudentid());
				pstmt1.setString(4,obj.getAccyearid());
				rs1 = pstmt1.executeQuery();
				int count=0;
				while(rs1.next()){
					count=rs1.getInt(1);
				}
				if(count>0){
					pstmt2 = conn.prepareStatement("SELECT cscsa.`work_edu_grade`,cscsa.`art_edu_grade`,cscsa.`health_edu_grade`,cscsa.`discipline_grade` FROM  `campus_student_co_scholastic_areas` cscsa LEFT JOIN campus_examination ce ON cscsa.`exam_id`=ce.`examid` LEFT JOIN campus_classdetail cd ON cd.classdetail_id_int=ce.`classid` AND cscsa.`location_id`=cd.locationId LEFT JOIN campus_classsection cs ON cs.classdetail_id_int=cd.classdetail_id_int LEFT JOIN `campus_examtype` cext ON cext.`examtypeid`=ce.`examtype` LEFT JOIN campus_acadamicyear ca ON ca.acadamic_id=ce.`acadamicyear` WHERE cscsa.`student_id`=? AND ce.classid=? AND cs.classsection_id_int=? AND ce.examid =? AND ce.`acadamicyear`=? AND ce.`loc_id`=?");
					pstmt2.setString(1,obj.getStudentid());
					pstmt2.setString(2,obj.getClassId());
					pstmt2.setString(3,obj.getSection());
					pstmt2.setString(4,obj.getExamid());
					pstmt2.setString(5,obj.getAccyearid());
					pstmt2.setString(6,obj.getLocationid());
					rs2 = pstmt2.executeQuery();
					while(rs2.next()){
						obje.setWorkedu_grade(rs2.getString("work_edu_grade"));
						obje.setArtedu_grade(rs2.getString("art_edu_grade"));
						obje.setHealthedu_grade(rs2.getString("health_edu_grade"));
						obje.setDisciplinedu_grade(rs2.getString("discipline_grade"));
					}
				}
				list.add(obje);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs2 != null&& (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getexamclassDetails Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,classcount=0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		ArrayList<ExaminationDetailsVo> list2 = new ArrayList<ExaminationDetailsVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select subjectID,subjectName,Sub_Code from campus_subject where classid = ? and locationId=? AND isActive='Y' order by subjectName");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getLocationid());

			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setSno1(slno);
				objec.setSubId(rs.getString("subjectID"));
				objec.setSubjectName(rs.getString("subjectName"));
				objec.setSubCode(rs.getString("Sub_Code"));
		
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*)FROM `campus_student` st LEFT JOIN `campus_student_classdetails` csc ON csc.`student_id_int`=st.`student_id_int` WHERE csc.`fms_acadamicyear_id_int`=? AND csc.`locationId`=? AND csc.`classdetail_id_int`=? AND csc.`classsection_id_int`=? AND st.`student_status_var`='active'");
		//		PreparedStatement statuspstmt = conn.prepareStatement("select count(subject_id) from campus_subject_marks_wise where subject_id=? and classId = ? and SectionId = ? and Accyear_Id = ? and loc_id = ?");
				//statuspstmt.setString(1,objec.getSubId());
				statuspstmt.setString(1,obj.getAccyearid());
				statuspstmt.setString(2,obj.getLocationid());
				statuspstmt.setString(3,obj.getClassId());
				statuspstmt.setString(4,obj.getSection());
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				//PreparedStatement anotherstpstmt= conn.prepareStatement("select count(classdetail_id_int) from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int = ?");
				PreparedStatement anotherstpstmt= conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` csm LEFT JOIN `campus_student` st ON st.`student_id_int`=csm.stu_id WHERE sub_id=? AND `classid` = ? AND `sectionid` =? AND `Academic_yearid` = ? AND `location_id` = ? AND exam_id=? AND st.`student_status_var`='active'");
				anotherstpstmt.setString(1,objec.getSubId());
				anotherstpstmt.setString(2, obj.getClassId());
				anotherstpstmt.setString(3,obj.getSection());
				anotherstpstmt.setString(4,obj.getAccyearid());
				anotherstpstmt.setString(5,obj.getLocationid());
				anotherstpstmt.setString(6,obj.getExamid());
				ResultSet stsrs1 =anotherstpstmt.executeQuery();
				while(stsrs1.next()){
					classcount=stsrs1.getInt(1);
				}
				stsrs1.close();
				anotherstpstmt.close();
				if(count!=0 && classcount!=0 && (count == classcount)){
					objec.setStatus("Completed");
				}else{
					objec.setStatus("Pending");
				}
				list.add(objec);
			}
			count = 0;classcount=0;
			/*PreparedStatement pstmtlab = conn.prepareStatement("SELECT lab_id,Lab_Name,Lab_Code,Total_Marks,Pass_Marks FROM laboratory_details WHERE Class_Name = ? AND School_Name = ? order by Lab_Name");
			pstmtlab.setString(1,obj.getClassId());
			pstmtlab.setString(2,obj.getLocationid());
		
			ResultSet labrs = pstmtlab.executeQuery();
			while(labrs.next()){
				slno++;
				ExaminationDetailsVo objec1 = new ExaminationDetailsVo();
				objec1.setSno1(slno);
				objec1.setSubId(labrs.getString("lab_id"));
				objec1.setSubjectName(labrs.getString("Lab_Name"));
				objec1.setSubCode(labrs.getString("Lab_Code"));
				PreparedStatement statuspstmtlab = conn.prepareStatement("select count(subject_id) from campus_subject_marks_wise where subject_id=? and classId = ? and SectionId = ? and Accyear_Id = ? and loc_id = ?");
				statuspstmtlab.setString(1,objec1.getSubId());
				statuspstmtlab.setString(2,obj.getClassId());
				statuspstmtlab.setString(3,obj.getSection());
				statuspstmtlab.setString(4,obj.getAccyearid());
				statuspstmtlab.setString(5,obj.getLocationid());
				ResultSet statusrslab = statuspstmtlab.executeQuery();
				while(statusrslab.next()){
					count = statusrslab.getInt(1);
								}
				statusrslab.close();
				statuspstmtlab.close();
				PreparedStatement anotherstpstmtlab = conn.prepareStatement("select count(classdetail_id_int) from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int = ?");
				anotherstpstmtlab.setString(1, obj.getClassId());
				anotherstpstmtlab.setString(2,obj.getSection());
				anotherstpstmtlab.setString(3,obj.getLocationid());
				anotherstpstmtlab.setString(4,obj.getAccyearid());
				ResultSet stsrslab =anotherstpstmtlab.executeQuery();
			
				while(stsrslab.next()){
					classcount=stsrslab.getInt(1);
				
				}
				if(count!=0 && classcount!=0 && (count == classcount)){
					objec1.setStatus("Completed");
				}else{
					objec1.setStatus("Pending");
				}
				list2.add(objec1);
				stsrslab.close();
				anotherstpstmtlab.close();
			}
			list.addAll(list2);
			labrs.close();
			pstmtlab.close();*/
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String getsubDetails(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getsubDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			if(obj.getSubId().startsWith("SUB")){
				pstmt = conn.prepareStatement("select subjectName,Sub_Code,totalMarks,passMarks from campus_subject where subjectID = ? and locationId = ?");
				pstmt.setString(1,obj.getSubId());
				pstmt.setString(2,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					result = rs.getString("subjectName")+","+rs.getString("Sub_Code")+","+rs.getString("totalMarks")+","+rs.getString("passMarks");
				}
			}else if(obj.getSubId().startsWith("LAB")){
				pstmt = conn.prepareStatement("select Lab_Name,Lab_Code,Total_Marks,Pass_Marks from laboratory_details where lab_id = ? and School_Name = ?");
				pstmt.setString(1,obj.getSubId());
				pstmt.setString(2,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					result = rs.getString("Lab_Name")+","+rs.getString("Lab_Code")+","+rs.getString("Total_Marks")+","+rs.getString("Pass_Marks");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,String schoolLocation,UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getstudentsList Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstscoredmarks=null,pstmt2=null,pstmt3=null;
		PreparedStatement pstmt1=null;
		ResultSet rs = null,rsscored=null;
		ResultSet rs1=null,rs2=null,rs3=null;
		int sno=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			//pstmt = conn.prepareStatement("select cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student from campus_student cs join campus_student_classdetails csc on csc.student_id_int=cs.student_id_int where csc.classdetail_id_int=? and csc.classsection_id_int=? and csc.locationId=? and  csc.fms_acadamicyear_id_int =? AND cs.`student_status_var`='active' order by length(csc.student_rollno),csc.student_rollno");
			pstmt = conn.prepareStatement("select cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student from campus_student cs join campus_student_classdetails csc on csc.student_id_int=cs.student_id_int where csc.classdetail_id_int=? and csc.classsection_id_int=? and csc.locationId=? and  csc.fms_acadamicyear_id_int =? AND cs.`student_status_var`='active' order by student,cs.student_admissionno_var");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,schoolLocation);
			pstmt.setString(4,obj.getAccyearid());
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				sno++;
				ExaminationDetailsVo objresult = new ExaminationDetailsVo();
				objresult.setCount(sno);
				objresult.setStudentid(rs.getString("student_id_int"));
				objresult.setRollno(rs.getString("student_rollno"));
				objresult.setAddmisiionno(rs.getString("student_admissionno_var"));
				objresult.setStudentname(rs.getString("student"));
				
				int examcount=getExitExamCount(obj,custdetails);
				if(examcount > 0){
					//pstmt2 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks,csmd.attendance_status FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid AND csmd.`sub_id`=cs.`subjectID` LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? and csmd.`exam_id`=? and cs.specialization=? AND cs.`subjectID`=? GROUP BY subjectID");
					pstmt2 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks,csmd.attendance_status FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid AND csmd.`sub_id`=cs.`subjectID` LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? and csmd.`exam_id`=? AND cs.`subjectID`=? AND csmd.`stu_id`=? GROUP BY subjectID");
					pstmt2.setString(1,obj.getClassId());
					pstmt2.setString(2,obj.getLocationid());
					pstmt2.setString(3,obj.getExamid());
					//pstmt2.setString(4,obj.getSpecializationId());
					pstmt2.setString(4,obj.getSubId());
					pstmt2.setString(5,rs.getString("student_id_int"));
					//System.out.println("MARKS ID "+pstmt2); 
					int stucount=0;
					rs2 = pstmt2.executeQuery();
					while(rs2.next()){
						stucount++;
						objresult.setSubId(rs2.getString("subjectID"));
						objresult.setSubCode(rs2.getString("Sub_Code"));
						objresult.setSubjectName(rs2.getString("subjectName"));
						objresult.setSubjectName(rs2.getString("subjectName"));
						if(rs2.getString("total_marks") != null){
							objresult.setTot_marks(rs2.getString("total_marks"));
						}else{
							objresult.setTot_marks("0");
						}
						if(rs2.getString("max_notebook_marks") != null){
							objresult.setMax_notebook_marks(rs2.getString("max_notebook_marks"));
							
						}else{
							objresult.setMax_notebook_marks("0");
						}
						if(rs2.getString("max_subjenrich_marks") != null){
							objresult.setMax_subenrich_marks(rs2.getString("max_subjenrich_marks"));
							
						}else{
							objresult.setMax_subenrich_marks("0");
						}
						if(rs2.getString("max_periodic_marks") != null){
							objresult.setMaxperiodicmark(rs2.getString("max_periodic_marks"));
						}else{
							objresult.setMaxperiodicmark("0");
						}
						objresult.setAttendace(rs2.getString("attendance_status"));
						
						pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
						pstscoredmarks.setString(1,rs.getString("student_id_int"));
						pstscoredmarks.setString(2,objresult.getSubId());
						pstscoredmarks.setString(3,obj.getExamid());
					//	System.out.println("pstmt2 "+pstscoredmarks);
						rsscored=pstscoredmarks.executeQuery();
						while(rsscored.next())
						{
							if(rsscored.getString("scored_marks") != null ){
								objresult.setScoredmarks(rsscored.getString("scored_marks")); 
							}else{
								objresult.setScoredmarks("0");
							}
							if(rsscored.getString("Stu_mark_id") != null){
								objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
							}else{
								objresult.setStudenprimid("0");
							}

							objresult.setAttendace(rsscored.getString("attendance_status"));

							if(rsscored.getString("notebook_marks") !=null){
								objresult.setNotebooks(rsscored.getString("notebook_marks"));
							}else{
								objresult.setNotebooks("0");
							}
							if(rsscored.getString("subject_enrich_marks") !=null){
								objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
							}else{
								objresult.setSubjectenrichmarks("0");
							}
							if(rsscored.getString("periodic_test") !=null){
								objresult.setPertest(rsscored.getString("periodic_test")); 
							}else{
								objresult.setPertest("0");
							}
						}
					}
					if(stucount==0){
						pstmt3 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid AND csmd.`sub_id`=cs.`subjectID` LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? and csmd.`exam_id`=? AND cs.`subjectID`=? GROUP BY subjectID");
						pstmt3.setString(1,obj.getClassId());
						pstmt3.setString(2,obj.getLocationid());
						pstmt3.setString(3,obj.getExamid());
						//pstmt2.setString(4,obj.getSpecializationId());
						pstmt3.setString(4,obj.getSubId());
						//System.out.println("MARKS ID "+pstmt2); 
						rs3 = pstmt3.executeQuery();
						while(rs3.next()){
							objresult.setStudenprimid("0");
							objresult.setScoredmarks("0");
							objresult.setNotebooks("0");
							objresult.setSubjectenrichmarks("0");
							objresult.setPertest("0");
							objresult.setMaxperiodicmark(rs3.getString("max_periodic_marks"));
							objresult.setMax_subenrich_marks(rs3.getString("max_subjenrich_marks"));
							objresult.setMax_notebook_marks(rs3.getString("max_notebook_marks"));
							objresult.setTot_marks(rs3.getString("total_marks"));
							objresult.setAttendace("Present");
						}
					}
				}else{
					pstmt2 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND `specialization`=?");
					pstmt2.setString(1,obj.getClassid());
					pstmt2.setString(2,obj.getLocationid());
					pstmt2.setString(3,obj.getSpecializationId());
					
					//System.out.println(pstmt);
					rs2= pstmt2.executeQuery();
					while(rs2.next()){
						//sno++;
						//objresult.setSno1(sno);
						objresult.setSubId(rs2.getString("subjectID"));
						objresult.setSubCode(rs2.getString("Sub_Code"));
						objresult.setSubjectName(rs2.getString("subjectName"));
						objresult.setTot_marks("0");
						objresult.setMax_notebook_marks("0");
						objresult.setMax_subenrich_marks("0");
						objresult.setMaxperiodicmark("0");

						pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
						pstscoredmarks.setString(1,rs2.getString("student_id_int"));
						pstscoredmarks.setString(2,objresult.getSubId());
						pstscoredmarks.setString(3,obj.getExamid());
				//		System.out.println(pstscoredmarks);
						rsscored=pstscoredmarks.executeQuery();
						while(rsscored.next())
						{
							if(rsscored.getString("scored_marks") != null ){
								objresult.setScoredmarks(rsscored.getString("scored_marks")); 
							}else{
								objresult.setScoredmarks("0");
							}
							if(rsscored.getString("Stu_mark_id") != null){
								objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
							}else{
								objresult.setStudenprimid("0");
							}

							objresult.setAttendace(rsscored.getString("attendance_status"));

							if(rsscored.getString("notebook_marks") !=null){
								objresult.setNotebooks(rsscored.getString("notebook_marks"));
							}else{
								objresult.setNotebooks("0");
							}
							if(rsscored.getString("subject_enrich_marks") !=null){
								objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
							}else{
								objresult.setSubjectenrichmarks("0");
							}
							if(rsscored.getString("periodic_test") !=null){
								objresult.setPertest(rsscored.getString("periodic_test")); 
							}else{
								objresult.setPertest("0");
							}
						}
					}
				}
				
				/*pstmt1 = conn.prepareStatement("select statusvalues,Sub_marks_id,scoredmarks from campus_subject_marks_wise where StudentId=? and subject_id=?");
				pstmt1.setString(1,rs.getString("student_id_int"));
				pstmt1.setString(2, obj.getSubId());
				System.out.println(pstmt1);
				rs1 = pstmt1.executeQuery();
				while (rs1.next())
				{    objec.setPrimaryid(rs1.getString("Sub_marks_id"));
					 objec.setScoredmarks(rs1.getString("scoredmarks"));
					 objec.setAttendace(rs1.getString("statusvalues"));
				}*/
				list.add(objresult);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseStudent(ExaminationDetailsVo obj,UserLoggingsPojo custdetails ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : classWiseStudent Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			//pstmt = conn.prepareStatement("select cs.student_id_int,csc.student_rollno,csc.specilization,cs.student_admissionno_var,concat(cs.student_fname_var,' ',cs.student_lname_var) as student from campus_student cs join campus_student_classdetails csc on csc.student_id_int=cs.student_id_int and csc.classdetail_id_int=? and classsection_id_int=? where csc.locationId=? and csc.fms_acadamicyear_id_int=? and cs.`student_status_var`='active' order by student ");
			pstmt = conn.prepareStatement("SELECT cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS student,spec.`Specialization_name`,spec.Specialization_Id FROM campus_student cs JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int AND csc.classdetail_id_int=? AND classsection_id_int=? LEFT JOIN `campus_class_specialization` spec ON spec.`Specialization_Id`=csc.specilization WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND cs.`student_status_var`='active' ORDER BY student,cs.student_admissionno_var ");

			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getSection());
			pstmt.setString(3,obj.getLocationid());
			pstmt.setString(4,obj.getAccyearid());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int count = 0;
				int stu_count=0;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setStudentid(rs.getString("student_id_int"));
				objec.setStudentrollno(rs.getString("student_rollno"));
				objec.setAdmissionno(rs.getString("student_admissionno_var"));
				objec.setStudentname(rs.getString("student"));
				
				if(rs.getString("Specialization_name") == null){
					objec.setSpecialization("-");
					objec.setSpecializationId("-");
				}else{
					objec.setSpecialization(rs.getString("Specialization_name"));
					objec.setSpecializationId(rs.getString("Specialization_Id"));
				}
				
				//PreparedStatement statuspstmt = conn.prepareStatement("select count(scored_marks) from campus_studentwise_mark_details where stu_id=? and exam_id = ?");
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_subject` WHERE `locationId`=? AND `classid`=? AND isActive='Y'");
				statuspstmt.setString(1,obj.getLocationid());
				statuspstmt.setString(2,obj.getClassId());
			//	System.out.println(statuspstmt);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement stu = conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` WHERE `stu_id`=? AND `classid` = ? AND `sectionid` = ? AND `Academic_yearid` = ? AND `location_id` = ? AND `exam_id`=?");
				stu.setString(1,rs.getString("student_id_int"));
				stu.setString(2,obj.getClassId());
				stu.setString(3,obj.getSection());
				stu.setString(4,obj.getAccyearid());
				stu.setString(5,obj.getLocationid());
				stu.setString(6,obj.getExamid());
			//	System.out.println(stu);
				ResultSet sturs = stu.executeQuery();
				while(sturs.next()){
					stu_count = sturs.getInt(1);
				}
				if(count != 0 && count==stu_count){
					objec.setStatus("Completed");
				}else{
					objec.setStatus("Pending");
				}
				list.add(objec);
				sturs.close();
				stu.close();
				statusrs.close();
				statuspstmt.close();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : classWiseStudent Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getsubDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstscoredmarks=null,labpstmt=null,labpstscoredmarks=null,pstmt1 = null;
		ResultSet rs = null,rsscored=null,labrs=null,labrsscored=null,rs1=null;
		int sno=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			int examcount=getExitExamCount(obj1,custdetails);
			
			if(examcount > 0){

				//pstmt1 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND `specialization`=?");
				pstmt1 = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND cs.specialization=? and isActive='Y' order by subjectName");
				pstmt1.setString(1,obj1.getClassid());
				pstmt1.setString(2,obj1.getLocationid());
				pstmt1.setString(3,obj1.getSpecializationId());
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					ExaminationDetailsVo objresult = new ExaminationDetailsVo();
					//pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid AND cs.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? and csmd.`exam_id`=? and cs.specialization=? AND cs.subjectID=? GROUP BY subjectID");
					pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName,csmd.`total_marks`,csmd.`max_notebook_marks`,csmd.`max_subjenrich_marks`,csmd.max_periodic_marks FROM campus_subject cs LEFT JOIN `campus_studentwise_mark_details` csmd ON csmd.`classid`=cs.classid AND cs.`subjectID`=csmd.`sub_id` LEFT JOIN `campus_examination` ce ON ce.`examid`=csmd.`exam_id` AND ce.`classid`=csmd.classid WHERE cs.classid=? AND cs.locationId=? and csmd.`exam_id`=? AND cs.subjectID=? GROUP BY subjectID");
					pstmt.setString(1,obj1.getClassid());
					pstmt.setString(2,obj1.getLocationid());
					pstmt.setString(3,obj1.getExamid());
					//pstmt.setString(4,obj1.getSpecializationId());
					pstmt.setString(4,rs1.getString("subjectID"));
					//System.out.println("1573"+pstmt);
					rs = pstmt.executeQuery();
					int zero=0;
					while(rs.next()){
						zero++;
						sno++;
						objresult.setSno1(sno);
						objresult.setSubId(rs.getString("subjectID"));
						objresult.setSubCode(rs.getString("Sub_Code"));
						objresult.setSubjectName(rs.getString("subjectName"));
						if(rs.getString("total_marks") != null){
							objresult.setTot_marks(rs.getString("total_marks"));
						}else{
							objresult.setTot_marks("0");
						}
						if(rs.getString("max_notebook_marks") != null){
							objresult.setMax_notebook_marks(rs.getString("max_notebook_marks"));
						}else{
							objresult.setMax_notebook_marks("0");
						}
						if(rs.getString("max_subjenrich_marks") != null){
							objresult.setMax_subenrich_marks(rs.getString("max_subjenrich_marks"));
						}else{
							objresult.setMax_subenrich_marks("0");
						}
						if(rs.getString("max_periodic_marks") != null){
							objresult.setMaxperiodicmark(rs.getString("max_periodic_marks"));
						}else{
							objresult.setMaxperiodicmark("0");
						}

						pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
						pstscoredmarks.setString(1,obj1.getStudentid());
						pstscoredmarks.setString(2,objresult.getSubId());
						pstscoredmarks.setString(3,obj1.getExamid());

						rsscored=pstscoredmarks.executeQuery();
						while(rsscored.next())
						{
							if(rsscored.getString("scored_marks") != null ){
								objresult.setScoredmarks(rsscored.getString("scored_marks")); 
							}else{
								objresult.setScoredmarks("0");
							}
							if(rsscored.getString("Stu_mark_id") != null){
								objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
							}else{
								objresult.setStudenprimid("0");
							}

							objresult.setAttendace(rsscored.getString("attendance_status"));

							if(rsscored.getString("notebook_marks") !=null){
								objresult.setNotebooks(rsscored.getString("notebook_marks"));
							}else{
								objresult.setNotebooks("0");
							}
							if(rsscored.getString("subject_enrich_marks") !=null){
								objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
							}else{
								objresult.setSubjectenrichmarks("0");
							}
							if(rsscored.getString("periodic_test") !=null){
								objresult.setPertest(rsscored.getString("periodic_test")); 
							}else{
								objresult.setPertest("0");
							}
						}
					}
					if(zero==0){
						sno++;
						objresult.setSno1(sno);
						objresult.setSubId(rs1.getString("subjectID"));
						objresult.setSubCode(rs1.getString("Sub_Code"));
						objresult.setSubjectName(rs1.getString("subjectName"));
						objresult.setTot_marks("0");
						objresult.setMax_notebook_marks("0");
						objresult.setMax_subenrich_marks("0");
						objresult.setMaxperiodicmark("0");
					}

					list.add(objresult);
				}
			}else{
				
				//pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND `specialization`=?");
				pstmt = conn.prepareStatement("SELECT subjectID,Sub_Code,subjectName FROM campus_subject cs WHERE cs.classid=? AND cs.locationId=? AND cs.specialization=? and cs.isActive='Y' order by subjectName");
				//pstmt = conn.prepareStatement("SELECT cs.subjectID,cs.Sub_Code,cs.subjectName,mas.`theory_marks` FROM campus_subject cs LEFT JOIN `campus_exam_max_marks_setup` ma ON cs.classid=ma.`class_id` AND ma.`accy_id`=? LEFT JOIN `campus_exam_max_marks_subwise` mas ON ma.`id`=mas.`exam_max_marks_id` AND mas.`sub_id`=cs.`subjectID` WHERE cs.classid=? AND cs.locationId=? AND cs.isActive='Y' ORDER BY cs.subjectName");
				/*pstmt.setString(1,obj1.getAccyearid());
				pstmt.setString(2,obj1.getExamid());*/
				pstmt.setString(1,obj1.getClassid());
				pstmt.setString(2,obj1.getLocationid());
				pstmt.setString(3,obj1.getSpecializationId());
				
				System.out.println("1690 "+pstmt);
				rs = pstmt.executeQuery();
				while(rs.next()){
					sno++;
					ExaminationDetailsVo objresult = new ExaminationDetailsVo();
					objresult.setSno1(sno);
					objresult.setSubId(rs.getString("subjectID"));
					objresult.setSubCode(rs.getString("Sub_Code"));
					objresult.setSubjectName(rs.getString("subjectName"));
					objresult.setTot_marks("0");
					objresult.setMax_notebook_marks("0");
					objresult.setMax_subenrich_marks("0");
					objresult.setMaxperiodicmark("0");

					pstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,notebook_marks,subject_enrich_marks,periodic_test from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
					pstscoredmarks.setString(1,obj1.getStudentid());
					pstscoredmarks.setString(2,objresult.getSubId());
					pstscoredmarks.setString(3,obj1.getExamid());
					rsscored=pstscoredmarks.executeQuery();
					while(rsscored.next())
					{
						if(rsscored.getString("scored_marks") != null ){
							objresult.setScoredmarks(rsscored.getString("scored_marks")); 
						}else{
							objresult.setScoredmarks("0");
						}
						if(rsscored.getString("Stu_mark_id") != null){
							objresult.setStudenprimid(rsscored.getString("Stu_mark_id"));
						}else{
							objresult.setStudenprimid("0");
						}

						objresult.setAttendace(rsscored.getString("attendance_status"));

						if(rsscored.getString("notebook_marks") !=null){
							objresult.setNotebooks(rsscored.getString("notebook_marks"));
						}else{
							objresult.setNotebooks("0");
						}
						if(rsscored.getString("subject_enrich_marks") !=null){
							objresult.setSubjectenrichmarks(rsscored.getString("subject_enrich_marks")); 
						}else{
							objresult.setSubjectenrichmarks("0");
						}
						if(rsscored.getString("periodic_test") !=null){
							objresult.setPertest(rsscored.getString("periodic_test")); 
						}else{
							objresult.setPertest("0");
						}
					}
					list.add(objresult);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (labrsscored != null&& (!labrsscored.isClosed())) {
					labrsscored.close();
				}
				if (labpstscoredmarks != null&& (!labpstscoredmarks.isClosed())) {
					labpstscoredmarks.close();
				}
				if (labrs != null&& (!labrs.isClosed())) {
					labrs.close();
				}
				if (labpstmt != null&& (!labpstmt.isClosed())) {
					labpstmt.close();
				}
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}
				if (pstscoredmarks != null&& (!pstscoredmarks.isClosed())) {
					pstscoredmarks.close();
				}
				if (rsscored != null&& (!rsscored.isClosed())) {
					rsscored.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getStudentDetails Ending");
		return list;
	}


	@Override
	public String insertmarkentrydetails(
			ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertmarkentrydetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt3=null,pstmt4=null;
		ResultSet rs = null;
		String primaryid=null;
		int count=0,count1=0,count3=0,count4=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_MARK_ENTRY_STUDENTTWISE);
			pstmt1=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_STUDENTTWISE);
			pstmt3=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_GRADING_SCALE);
			pstmt4=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_GRADING_SCALE);
			String[] studentlist=obj.getStuprimaryid();
			for(int i=0;i<studentlist.length;i++){
				if(studentlist[i].equals("")){
					primaryid = IDGenerator.getPrimaryKeyID("campus_studentwise_mark_details",userLoggingsVo);
					pstmt.setString(1,primaryid);
					pstmt.setString(2,obj.getClassid());
					pstmt.setString(3,obj.getSectionid());
					pstmt.setString(4,obj.getExamid());
					pstmt.setString(5,obj.getAccyear());
					pstmt.setString(6,obj.getSubid()[i].trim());
					pstmt.setString(7,obj.getStatusvalues()[i].trim());
					pstmt.setString(8,obj.getScoremarks()[i].trim());
					pstmt.setString(9,obj.getStudentid().trim());
					pstmt.setString(10,obj.getLocationid().trim());
					pstmt.setString(11,obj.getNotebookmarks()[i].trim());
					pstmt.setString(12,obj.getSubjectenrichment()[i].trim());
					pstmt.setString(13,obj.getMax_scored_marks()[i].trim());
					pstmt.setString(14,obj.getMaxnotebookmarks()[i].trim());
					pstmt.setString(15,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt.setString(16,obj.getCreatedBy());
					//`max_periodic_marks`,`periodic_test`
					pstmt.setString(17,obj.getMaxperiodicmarks()[i].trim());
					pstmt.setString(18,obj.getPeriodicscoredmarks()[i].trim());
					count =pstmt.executeUpdate();
				}else{
					pstmt1.setString(1, obj.getStatusvalues()[i].trim());
					pstmt1.setString(2,obj.getScoremarks()[i].trim());
					pstmt1.setString(3,obj.getNotebookmarks()[i].trim());
					pstmt1.setString(4,obj.getSubjectenrichment()[i].trim());
					pstmt1.setString(5,obj.getMax_scored_marks()[i].trim());
					pstmt1.setString(6,obj.getMaxnotebookmarks()[i].trim());
					pstmt1.setString(7,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt1.setString(8,obj.getCreatedBy());
					pstmt1.setDate(9,HelperClass.getCurrentSqlDate());
					pstmt1.setString(10,obj.getMaxperiodicmarks()[i].trim());
					pstmt1.setString(11,obj.getPeriodicscoredmarks()[i].trim());
					pstmt1.setString(12,obj.getStuprimaryid()[i].trim());
					pstmt1.setString(13,obj.getExamid());

					count1 =pstmt1.executeUpdate();

				}
			}
			if(count > 0){
				if(obj.getExamtypeprefix().equalsIgnoreCase("hlfym")){
					pstmt3.setString(1, obj.getStudentid().trim());
					pstmt3.setString(2,obj.getLocationid().trim());
					pstmt3.setString(3,obj.getAccyear());
					pstmt3.setString(4,obj.getClassid());
					pstmt3.setString(5,obj.getSectionid());
					pstmt3.setString(6,obj.getWorkedu_grade().trim());
					pstmt3.setString(7,obj.getArtedu_grade().trim());
					pstmt3.setString(8,obj.getHealthedu_grade().trim());
					pstmt3.setString(9,obj.getDisciplinedu_grade().trim());
					pstmt3.setString(10,obj.getCreatedBy());
					pstmt3.setString(11,obj.getExamid());
					count3=pstmt3.executeUpdate();
					if(count3 >0){
						msg="inserted";
						HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntry","Insert",pstmt.toString(),userLoggingsVo);
					}
				}else{
					msg="inserted";
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntry","Insert",pstmt.toString(),userLoggingsVo);
				}
				
			}else if(count1 > 0){
				if(obj.getExamtypeprefix().equalsIgnoreCase("hlfym")){
					pstmt4.setString(1,obj.getWorkedu_grade().trim());
					pstmt4.setString(2,obj.getArtedu_grade().trim());
					pstmt4.setString(3,obj.getHealthedu_grade().trim());
					pstmt4.setString(4,obj.getDisciplinedu_grade().trim());
					pstmt4.setString(5,obj.getCreatedBy());
					pstmt4.setDate(6, HelperClass.getCurrentSqlDate());
					pstmt4.setString(7, obj.getStudentid().trim());
					pstmt4.setString(8,obj.getLocationid().trim());
					pstmt4.setString(9,obj.getAccyear());
					pstmt4.setString(10,obj.getClassid());
					pstmt4.setString(11,obj.getExamid());
					count4=pstmt4.executeUpdate();
					if(count4 >0){
						msg="inserted";
						HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntry","Update",pstmt.toString(),userLoggingsVo);
					}
				}else{
					msg="inserted";
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntry","Update",pstmt.toString(),userLoggingsVo);
				}
				
			}
			else
			{
				msg="failed";

			}
		}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertmarkentrydetails Ending");
		return msg;
	

	
	

	}

	@Override

	public String getlocationname(String schoolLocation, UserLoggingsPojo dbdetails) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String locationname=null; 
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_LOCATION);
		    pstmt.setString(1, schoolLocation);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				locationname=rs.getString("Location_Name");
			}
				
           
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return locationname;
	

	
	

	}

	@Override
	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,String schoolLocation) {
		
		//System.out.println("it is coming inside the daoimpl:");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String primaryid=null;
		int count=0,count1=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_MARK_ENTRY_SUBJECTTWISE);
			pstmt1=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_SUBJECTTWISE);
			
			String[] subjectlist=obj.getSubmarksid();
			for(int i=0;i<subjectlist.length;i++){
				if(subjectlist[i].equals("")){
						primaryid = IDGenerator.getPrimaryKeyID("campus_subject_marks_wise");
						pstmt.setString(1,primaryid);
						pstmt.setString(2, obj.getAccyear());
						pstmt.setString(3, obj.getClassid());
						pstmt.setString(4, obj.getExamid());
						pstmt.setString(5, obj.getSectionid());
						pstmt.setString(6, obj.getSubId());
						pstmt.setString(7, schoolLocation);
						pstmt.setString(8, obj.getStudentids()[i].trim());
						pstmt.setString(9,obj.getScoremarks()[i].trim());
						pstmt.setString(10, obj.getStatusvalues()[i].trim());
						count =pstmt.executeUpdate();
					
				}else{
					pstmt1.setString(1,obj.getScoremarks()[i].trim());
					pstmt1.setString(2,obj.getStatusvalues()[i].trim());
					pstmt1.setString(3,obj.getSubmarksid()[i].trim());

					count1 =pstmt1.executeUpdate();
					//count1=updatemarkentrysubjectwiselist(obj,schoolLocation);
				}
			}
			
			
			//System.out.println(count);
		  if(count>0 || count1>0)
            {
            	msg="inserted";
            }
            else
            {
            	msg="failed";
            	
            }
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return msg;
	}
	
	@Override
	public String getclassname(String classid,UserLoggingsPojo pojo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String classname=null; 
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_CLASS);
		    pstmt.setString(1,classid);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				classname=rs.getString("classdetails_name_var");
			}
           
		}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return classname;
}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(
			String schoolLocation) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String examcode=null; 
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();

		try {
			ExaminationDetailsVo examcodes = new ExaminationDetailsVo();
			conn = JDBCConnection.getSeparateConnection();
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_LISTOFEXAM_CODES);
		    pstmt.setString(1, schoolLocation);
//		    pstmt.setString(2, acyear);
			rs =pstmt.executeQuery();
			while(rs.next())
			{
				examcode=rs.getString("examcode");
			}
			examcodes.setExamCode("examcode");
				
			 list.add(examcodes);
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		
	//	System.out.println("it is coming inside the daoimpl:");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_SUBJECTTWISE);
			
			for(int j=0;j<obj.getStudentids().length;j++){
			
			
			pstmt.setString(1,obj.getScoremarks()[j].trim());
			pstmt.setString(2, obj.getStatusvalues()[j].trim());
			pstmt.setString(3,obj.getSubmarksid()[j].trim());
			
			//System.out.println("pstmt for run query:" +pstmt);
			
			}
			
			
			count =pstmt.executeUpdate();
		
            if(count>0)
            {
            	msg="inserted";
            }
            else
            {
            	msg="failed";
            	
            }
			}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		return msg;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear,String loc,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: examTimeTableListYear : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select cay.acadamic_id,cay.acadamic_year,cl.Location_Id,cl.Location_Name from campus_acadamicyear cay,campus_location cl where cay.acadamic_id like ? and cl.Location_Id like ? and cay.isActive='Y' and cl.isActive='Y' order by startDate");
			pstmt.setString(1,accyear);
			pstmt.setString(2,loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String locid = rs.getString("Location_Id");
				String academicyear = rs.getString("acadamic_id");
				int totexamcount = 0;
				
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setAccyearid(academicyear);
				obj.setAccyear(rs.getString("acadamic_year"));
				obj.setLocationid(locid);
				obj.setLocname(rs.getString("Location_Name"));
				obj.setSno1(slno);
				
				//PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id like ?");
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `locationId`= ? AND `isActive`='Y'");
				statuspstmt.setString(1,locid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					PreparedStatement examcount = conn.prepareStatement("SELECT classsection_id_int FROM `campus_classsection` WHERE `locationId`=? AND `classdetail_id_int`=? AND `isActive`='Y'");
					examcount.setString(1, locid);
					examcount.setString(2, statusrs.getString("classdetail_id_int"));
					ResultSet examrs = examcount.executeQuery();
					while(examrs.next()){
						PreparedStatement examcount2 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_examination` WHERE `classid`=? AND `acadamicyear`=? AND `loc_id`=?");
						examcount2.setString(1, statusrs.getString("classdetail_id_int"));
						examcount2.setString(2, academicyear);
						examcount2.setString(3, locid);
						ResultSet examrs2 = examcount2.executeQuery();
						while(examrs2.next()){
							totexamcount = totexamcount+examrs2.getInt(1);
						}
						examrs2.close();
						examcount2.close();
					}
					examrs.close();
					examcount.close();
				}
				statusrs.close();
				statuspstmt.close();
				
				int timetablecount=0;
				PreparedStatement examcount1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_exam_timetable` t JOIN `campus_location` l ON  l.`Location_Id`=t.`loc_id` WHERE `loc_id`=? AND `accyear_id`=? AND l.`isActive`='Y'");
				examcount1.setString(1,locid);
				examcount1.setString(2,academicyear);
				ResultSet examrs1 = examcount1.executeQuery();
				while(examrs1.next()){
					timetablecount = examrs1.getInt(1);
				}
				examrs1.close();
				examcount1.close();
				
				
				System.out.println(totexamcount+"ss"+timetablecount);
				
				if(timetablecount==0){
					obj.setStatus("Not Set");
				}else if(totexamcount==0){
					obj.setStatus("Not Set");
				}else{
					if(totexamcount==timetablecount){
						obj.setStatus("Set");
					}else{
						obj.setStatus("Not Set");
					}
				}
				
				
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String loc,String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT DISTINCT ccd.classdetail_id_int,ccd.classdetails_name_var FROM campus_classdetail ccd JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int WHERE ccd.locationId = ? AND ccd.isActive='Y' AND ccs.isActive='Y' ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int");
			pstmt.setString(1, loc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String classid = rs.getString("classdetail_id_int");
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setClassId(classid);
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSno1(slno);
				
				//get the section count in each class
				int seccount =0;
				PreparedStatement secpstmt = conn.prepareStatement("SELECT COUNT(*)classsection_id_int FROM campus_classsection css JOIN campus_classdetail ccd ON ccd.classdetail_id_int=css.classdetail_id_int AND ccd.`locationId` = css.`locationId` AND ccd.isActive='Y' JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int AND ccs.isActive='Y' JOIN campus_location cl ON cl.Location_Id=ccs.locationId AND cl.isActive='Y' WHERE css.classdetail_id_int = ? AND css.locationId = ? AND css.isActive='Y'");
				secpstmt.setString(1,classid);
				secpstmt.setString(2,loc);
				ResultSet rssec =secpstmt.executeQuery();
				while(rssec.next()){
					seccount = rssec.getInt(1);
				}
				rssec.close();
				secpstmt.close();
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id = ? and class_id = ?");
				statuspstmt.setString(1,accyear);
				statuspstmt.setString(2,loc);
				statuspstmt.setString(3,classid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				int examcount=0;
				PreparedStatement statuspstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_examination` WHERE `classid`=? AND `loc_id`=? AND `acadamicyear`=?");
				statuspstmt1.setString(1,classid);
				statuspstmt1.setString(2,loc);
				statuspstmt1.setString(3,accyear);
				ResultSet statusrs1 = statuspstmt1.executeQuery();
				while(statusrs1.next()){
					examcount = statusrs1.getInt(1);
				}
				statusrs1.close();
				statuspstmt1.close();
				if(count==0){
					obj.setStatus("Not Set");
					obj.setCount(examcount);
				}else{
					obj.setCount(examcount);
					if(count==(examcount*seccount)){
						obj.setStatus("Set");
					}
					else{
						obj.setStatus("Not Set");
					}
				}
				
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		String timetable = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select DISTINCT examid,examcode,examname,startdate,enddate,cs.classsection_id_int,classsection_name_var from campus_examination join campus_classsection cs on cs.locationId = loc_id where cs.`isActive`='Y' AND cs.classdetail_id_int =? and acadamicyear = ? and loc_id like ? and cs.classsection_id_int like ? and examid like ? order by classsection_name_var,startdate");
			pstmt.setString(1,pojo.getClassId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
		//	System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setEndTime(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				obj.setSectionName(rs.getString("classsection_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSno1(slno);
				PreparedStatement statuspstmt = conn.prepareStatement("select count(*) from campus_exam_timetable where accyear_id = ? and loc_id = ? and class_id = ? and section_id like ? and examcode = ?");
				statuspstmt.setString(1,pojo.getAccyearid());
				statuspstmt.setString(2,pojo.getLocid());
				statuspstmt.setString(3,pojo.getClassId());
				statuspstmt.setString(4,rs.getString("classsection_id_int"));
				statuspstmt.setString(5,rs.getString("examid"));
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				if(count > 0){
					PreparedStatement setstatus = conn.prepareStatement("select DISTINCT timetable_id from campus_exam_timetable where accyear_id=? and loc_id=? and class_id=? and section_id=? and examcode=?");
					setstatus.setString(1,pojo.getAccyearid());
					setstatus.setString(2,pojo.getLocid());
					setstatus.setString(3,pojo.getClassId());
					setstatus.setString(4,rs.getString("classsection_id_int"));
					setstatus.setString(5,rs.getString("examid"));
					ResultSet setrs = setstatus.executeQuery();
					while(setrs.next()){
						timetable = setrs.getString("timetable_id");
					}
					setrs.close();
					setstatus.close();
					obj.setTimetableid(timetable);
					obj.setStatus("Set");
					obj.setStatus1("set");
				}
				else{
					obj.setStatus("Not Set");
					obj.setStatus1("not");
				}
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		//System.out.println(slno);
		return list;
	}

	@Override
	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ExaminationDetailsVo obj = null;
		try {
			obj = new ExaminationDetailsVo();
	
			conn = JDBCConnection.getSeparateConnection(custdetails);
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement("select DISTINCT examcode,examname,startdate,enddate from campus_examination where examid = ? and acadamicyear = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
		//	System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			}
			pstmt1=conn.prepareStatement("select DISTINCT classdetails_name_var,classsection_name_var from campus_classdetail cd,campus_classsection cs where cd.classdetail_id_int = ? and cs.classsection_id_int = ?");
			pstmt1.setString(1,pojo.getClassId());
			pstmt1.setString(2,pojo.getSectionid());
			//System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				obj.setClassname(rs1.getString("classdetails_name_var"));
				obj.setSectionName(rs1.getString("classsection_name_var"));
			}
			conn.commit();
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		String startdate = null;
		
		int slno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement("select DISTINCT startdate from campus_examination where examid = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getLocid());
			//System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				startdate = (HelperClass.convertDatabaseToUI(rs.getString("startdate")));
			}
			pstmt1=conn.prepareStatement("select DISTINCT subjectID,subjectName,Sub_Code,isLanguage from campus_subject where locationId = ? and classid = ? and subType='Major' AND `isActive`='Y' order by subjectName");
			pstmt1.setString(1,pojo.getLocid());
			pstmt1.setString(2,pojo.getClassId());
			//System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				slno++;
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setSno1(slno);
				obj.setSubId(rs1.getString("subjectID"));
				obj.setSubjectName(rs1.getString("subjectName"));
				obj.setSubCode(rs1.getString("Sub_Code"));
				obj.setExamstartdate(startdate);
				obj.setIslanguage(rs1.getString("isLanguage"));
				list.add(obj);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	
	@Override
	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate, UserLoggingsPojo userLoggingsVo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		

		String key = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			if(pojo.getSectionid().equalsIgnoreCase("%%")){
				pstmt2 = conn.prepareStatement("select DISTINCT classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
					key = IDGenerator.getPrimaryKeyID("campus_exam_timetable",userLoggingsVo);
					pstmt=conn.prepareStatement("insert into campus_exam_timetable (timetable_id,accyear_id,loc_id,class_id,section_id,examcode) values (?,?,?,?,?,?)");
					pstmt.setString(1,key);
					pstmt.setString(2,pojo.getAccyearid());
					pstmt.setString(3,pojo.getLocid());
					pstmt.setString(4,pojo.getClassId());
					pstmt.setString(5,rs.getString("classsection_id_int"));
					pstmt.setString(6,pojo.getExamId());
					
					//System.out.println(pstmt);
					count = pstmt.executeUpdate();
					if (count > 0) {
						HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt.toString(),userLoggingsVo);
					}
						
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("insert into campus_detailed_timetable(examtimetablecode,sub_id,startdate,starttime,endtime) values(?,?,?,?,?)");
						pstmt1.setString(1,key);
						pstmt1.setString(2,subid1[i]);
						pstmt1.setString(3,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(4,starttime1[i]);
						pstmt1.setString(5,endtime1[i]);
						count1 = pstmt1.executeUpdate();
						 
						if (count1 > 0) {
							HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt1.toString(),userLoggingsVo);
						 }
						
						}
						
						if(count1 > 0){
							result = "true";
						}
				}
			}
			else{
			key = IDGenerator.getPrimaryKeyID("campus_exam_timetable",userLoggingsVo);
			pstmt=conn.prepareStatement("insert into campus_exam_timetable (timetable_id,accyear_id,loc_id,class_id,section_id,examcode) values (?,?,?,?,?,?)");
			pstmt.setString(1,key);
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			pstmt.setString(4,pojo.getClassId());
			pstmt.setString(5,pojo.getSectionid());
			pstmt.setString(6,pojo.getExamId());
			
		//	System.out.println(pstmt);
			count = pstmt.executeUpdate();
			
			if (count > 0) {
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt.toString(),userLoggingsVo);
			 }
		
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("insert into campus_detailed_timetable(examtimetablecode,sub_id,startdate,starttime,endtime) values(?,?,?,?,?)");
			pstmt1.setString(1,key);
			pstmt1.setString(2,subid1[i]);
			pstmt1.setString(3,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(4,starttime1[i]);
			pstmt1.setString(5,endtime1[i]);
			count1 = pstmt1.executeUpdate();
			
			if (count1 > 0) {
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt1.toString(),userLoggingsVo);
			 }
			
			}
			
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid, UserLoggingsPojo userLoggingsVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		
		try{
			conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select DISTINCT examid,examcode,examname from campus_examination where acadamicyear = ? and loc_id like ?");
			pstmt.setString(1,accyear);
			pstmt.setString(2,locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setExamid(rs.getString("examid"));
				obj.setExamName(rs.getString("examcode")+" - "+rs.getString("examname"));
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		return list;
	}

	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String accyear, String locid, String examid, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamClassByLocation : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
	
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("select DISTINCT ccd.classdetail_id_int,ccd.classdetails_name_var from campus_classdetail ccd JOIN campus_examination ce ON ce.classid=ccd.classdetail_id_int where ccd.locationId = ? and ccd.isActive='Y' order by length(ccd.classdetail_id_int),ccd.classdetail_id_int");
			pstmt.setString(1, accyear);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String classid = rs.getString("classdetail_id_int");
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setClassId(classid);
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSno1(slno);
				
				//get the section count in each class
				int seccount =0;
				PreparedStatement secpstmt = conn.prepareStatement("SELECT COUNT(*)classsection_id_int FROM campus_classsection WHERE classdetail_id_int = ? AND locationId = ? and isActive='Y'");
				secpstmt.setString(1,classid);
				secpstmt.setString(2,accyear);
				ResultSet rssec =secpstmt.executeQuery();
				while(rssec.next()){
					seccount = rssec.getInt(1);
				}
				rssec.close();
				secpstmt.close();
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*) FROM campus_exam_timetable cet JOIN campus_examination ce ON ce.examid=cet.`examcode` WHERE cet.accyear_id = ? AND cet.loc_id = ? AND cet.class_id = ? AND cet.examcode = ? ");
				statuspstmt.setString(1,locid);
				statuspstmt.setString(2,accyear);
				statuspstmt.setString(3,classid);
				statuspstmt.setString(4,examid);
				
			    System.out.println("campus_exam_timetable  -->>"+statuspstmt);
			    
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				statusrs.close();
				statuspstmt.close();
				obj.setTotseccount(seccount);
				obj.setSetseccount(count);
				
				if(count !=0 && seccount!=0){
					
					if(count == seccount){
						obj.setStatus1("Set");
						obj.setStatus("Set");
						obj.setSetmsg("Time Table is Set for All the Sections");
					}
					else{
						obj.setStatus1("Not");
						obj.setStatus("Not Set");
						obj.setSetmsg("Out of"+" "+seccount+" "+"Sections"+" "+"Set"+" "+count);
					}
				}
				else{
					obj.setStatus1("Not");
					obj.setStatus("Not Set");
					obj.setSetmsg("");
				}
				
				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR1);
			pstmt.setString(1,locid);
			pstmt.setString(2,accyear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement(ExamTimeTableSqlUtils.GET_ACCYEAR_COUNT);
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				System.out.println("YEAR COUNt "+pstmt1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				examlistyear.add(exampojo);
			}
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public String checkduplicatedate(ExamTimetablePojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: checkduplicatedate Starting");
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String result=null;
		int count=0;
		try{
			
			conn = JDBCConnection.getSeparateConnection(pojo.getCustid());
			pstmt = conn.prepareStatement("select count(*) from campus_examination where acadamicyear = ? and loc_id = ? and classid=? and ((startdate between ? and ?) or (enddate between ? and ?))");
			pstmt.setString(1, pojo.getAccyearid());
			pstmt.setString(2, pojo.getLocid());
			pstmt.setString(3, pojo.getClassId());
			pstmt.setString(4, pojo.getExamStartdate());
			pstmt.setString(5, pojo.getExamEndDate());
			pstmt.setString(6, pojo.getExamStartdate());
			pstmt.setString(7, pojo.getExamEndDate());
		
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1) ;
			}
			if(count>0){
				System.out.println(count);
				result = "true";
			}else{
				result = "false";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: checkduplicatedate Ending");
		return result;
	}

	@Override
	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
	
		ResultSet rs1 = null;
		ResultSet rs = null;
	
		ExaminationDetailsVo obj = null;
		try {
			obj = new ExaminationDetailsVo();
	
			conn = JDBCConnection.getSeparateConnection(custdetails);
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement("select DISTINCT examcode,examname,startdate,enddate from campus_examination where examid = ? and acadamicyear = ? and loc_id = ?");
			pstmt.setString(1,pojo.getExamId());
			pstmt.setString(2,pojo.getAccyearid());
			pstmt.setString(3,pojo.getLocid());
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				obj.setExamCode(rs.getString("examcode"));
				obj.setExamName(rs.getString("examname"));
				obj.setExamstartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamenddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
			}
			pstmt1=conn.prepareStatement("select DISTINCT classdetails_name_var,classsection_name_var from campus_classdetail cd,campus_classsection cs where cd.classdetail_id_int = ? and cs.classsection_id_int = ?");
			pstmt1.setString(1,pojo.getClassId());
			pstmt1.setString(2,pojo.getSectionid());
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				obj.setClassname(rs1.getString("classdetails_name_var"));
				obj.setSectionName(rs1.getString("classsection_name_var"));
			}
			
			conn.commit();
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		String startdate = null;
		
		int slno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement("select DISTINCT sub_id,startdate,starttime,endtime from campus_detailed_timetable where examtimetablecode = ?");
			pstmt.setString(1,pojo.getTimetableid());
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				obj.setExamstarttime(rs.getString("starttime"));
				obj.setExamendtime(rs.getString("endtime"));
			pstmt1=conn.prepareStatement("select DISTINCT subjectID,subjectName,Sub_Code,isLanguage from campus_subject where locationId = ? and classid = ? and subjectID = ? order by subjectName");
			pstmt1.setString(1,pojo.getLocid());
			pstmt1.setString(2,pojo.getClassId());
			pstmt1.setString(3,rs.getString("sub_id"));
			System.out.println(pstmt1);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				slno++;
				obj.setSno1(slno);
				obj.setSubId(rs1.getString("subjectID"));
				obj.setSubjectName(rs1.getString("subjectName"));
				obj.setSubCode(rs1.getString("Sub_Code"));
				obj.setExamstartdate(startdate);
				obj.setIslanguage(rs1.getString("isLanguage"));
				list.add(obj);
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (rs1 != null&& (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate, UserLoggingsPojo custdetails) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(pojo.getSectionid().equalsIgnoreCase("all")){
				pstmt2 = conn.prepareStatement("select DISTINCT classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
					String sectionid = rs.getString("classsection_id_int");
					PreparedStatement pstmttimeid = conn.prepareStatement("SELECT  timetable_id FROM campus_exam_timetable WHERE section_id = ? AND accyear_id = ? AND loc_id = ? AND `examcode`=?");
					pstmttimeid.setString(1,sectionid);
					pstmttimeid.setString(2,pojo.getAccyearid());
					pstmttimeid.setString(3,pojo.getLocid());
					pstmttimeid.setString(4,pojo.getExamId());
					ResultSet rstimeid = pstmttimeid.executeQuery();
					String key = "";
					while(rstimeid.next()){
						key = rstimeid.getString("timetable_id");
					}
					if(!key.equalsIgnoreCase("")){
					rstimeid.close();
					pstmttimeid.close();
					pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where timetable_id=? ");
					pstmt.setString(1,pojo.getAccyearid());
					pstmt.setString(2,pojo.getLocid());
					pstmt.setString(3,pojo.getClassId());
					pstmt.setString(4,sectionid);
					pstmt.setString(5,pojo.getExamId());
					pstmt.setString(6,key);
					//System.out.println("EXAM IDS "+pstmt);
					count = pstmt.executeUpdate();
					if(count > 0){
						HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","Update",pstmt.toString(),custdetails);
						result = "true";
					 }
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set startdate=?,starttime=?,endtime=? where examtimetablecode =? and sub_id=?");
						pstmt1.setString(5,subid1[i]);
						pstmt1.setString(1,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(2,starttime1[i]);
						pstmt1.setString(3,endtime1[i]);
						pstmt1.setString(4,key);
						System.out.println("UPDATION "+pstmt1);
						count1 = pstmt1.executeUpdate();
						if(count1 > 0){
							HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","Update",pstmt1.toString(),custdetails);
						 }
						}
					
					if(count1 > 0){
						result = "true";
					}
				}else{
					key = IDGenerator.getPrimaryKeyID("campus_exam_timetable",custdetails);
					pstmt=conn.prepareStatement("insert into campus_exam_timetable (timetable_id,accyear_id,loc_id,class_id,section_id,examcode) values (?,?,?,?,?,?)");
					pstmt.setString(1,key);
					pstmt.setString(2,pojo.getAccyearid());
					pstmt.setString(3,pojo.getLocid());
					pstmt.setString(4,pojo.getClassId());
					pstmt.setString(5,rs.getString("classsection_id_int"));
					pstmt.setString(6,pojo.getExamId());
					//System.out.println(pstmt);
					count = pstmt.executeUpdate();
					if (count > 0) {
						HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt.toString(),custdetails);
					}
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("insert into campus_detailed_timetable(examtimetablecode,sub_id,startdate,starttime,endtime) values(?,?,?,?,?)");
						pstmt1.setString(1,key);
						pstmt1.setString(2,subid1[i]);
						pstmt1.setString(3,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(4,starttime1[i]);
						pstmt1.setString(5,endtime1[i]);
						count1 = pstmt1.executeUpdate();
						if (count1 > 0) {
							HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","insert",pstmt1.toString(),custdetails);
						 }
						}
						if(count1 > 0){
							result = "true";
						}
				}
				}
			}
			else{
			pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where timetable_id=?");
			pstmt.setString(1,pojo.getAccyearid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassId());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
			pstmt.setString(6,pojo.getTimetableid());
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","Update",pstmt.toString(),custdetails);
				result = "true";
			 }
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set startdate=?,starttime=?,endtime=? where examtimetablecode =? and sub_id=?");
			
			pstmt1.setString(5,subid1[i]);
			pstmt1.setString(1,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(2,starttime1[i]);
			pstmt1.setString(3,endtime1[i]);
			pstmt1.setString(4,pojo.getTimetableid());
		
			count1 = pstmt1.executeUpdate();
			if(count1 > 0){
				HelperClass.recordLog_Activity(pojo.getLog_audit_session(),"Exam","ExamTimeTable","Update",pstmt1.toString(),custdetails);
				result = "true";
			 }
			}
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String result = null;
		int count = 0;
		int count1= 0;
		

	
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			
			if(pojo.getSectionid().equalsIgnoreCase("%%")){
				pstmt2 = conn.prepareStatement("select classsection_id_int from campus_classsection where locationId = ? and classdetail_id_int = ? order by classsection_name_var");
				pstmt2.setString(1, pojo.getLocid());
				pstmt2.setString(2, pojo.getClassId());
				rs = pstmt2.executeQuery();
				while(rs.next()){
				
					pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where examtimetablecode=? ");
					
					pstmt.setString(1,pojo.getAccyearid());
					pstmt.setString(2,pojo.getLocid());
					pstmt.setString(3,pojo.getClassId());
					pstmt.setString(4,rs.getString("classsection_id_int"));
					pstmt.setString(5,pojo.getExamId());
					pstmt.setString(6,pojo.getTimetableid());
					System.out.println(pstmt);
					count = pstmt.executeUpdate();
					for(int i=0;i<subid1.length;i++){
						pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set sub_id=?,startdate=?,starttime=?,endtime=? where examtimetablecode =?");
						
						pstmt1.setString(1,subid1[i]);
						pstmt1.setString(2,HelperClass.convertUIToDatabase(startdate[i]));
						pstmt1.setString(3,starttime1[i]);
						pstmt1.setString(4,endtime1[i]);
						pstmt1.setString(5,pojo.getTimetableid());
						count1 = pstmt1.executeUpdate();
						}
						
						if(count1 > 0){
							result = "true";
						}
				}
			}
			else{
			
			pstmt=conn.prepareStatement("update campus_exam_timetable set accyear_id=?,loc_id=?,class_id=?,section_id=?,examcode=? where examtimetablecode=?");
		
			pstmt.setString(1,pojo.getAccyearid());
			pstmt.setString(2,pojo.getLocid());
			pstmt.setString(3,pojo.getClassId());
			pstmt.setString(4,pojo.getSectionid());
			pstmt.setString(5,pojo.getExamId());
			pstmt.setString(6,pojo.getTimetableid());
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
		
			for(int i=0;i<subid1.length;i++){
			pstmt1 = conn.prepareStatement("update  campus_detailed_timetable set sub_id=?,startdate=?,starttime=?,endtime=? where examtimetablecode =?");
			
			pstmt1.setString(1,subid1[i]);
			pstmt1.setString(2,HelperClass.convertUIToDatabase(startdate[i]));
			pstmt1.setString(3,starttime1[i]);
			pstmt1.setString(4,endtime1[i]);
			pstmt1.setString(5,pojo.getTimetableid());
			count1 = pstmt1.executeUpdate();
			}
			
			if(count1 > 0){
				result = "true";
			}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null&& (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null;
	    PreparedStatement pstmt1=null;
	    PreparedStatement pstmt2=null;
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int yearcount=0;
		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR1);
			pstmt.setString(1,locid);
			pstmt.setString(2,accyear);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement("SELECT `examid` FROM `campus_examination` WHERE acadamicyear=? AND loc_id=?");
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					pstmt2 = conn.prepareStatement("SELECT COUNT(`Exam_code`) FROM `campus_exam_dependency` WHERE `Exam_code`=?");
					pstmt2.setString(1, rs1.getString("examid"));
					rs2=pstmt2.executeQuery();
                   while(rs2.next()){
                	   yearcount=rs2.getInt(1);
                	   }
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				examlistyear.add(exampojo);
			}
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}if (rs2 != null && !rs2.isClosed()) {
					rs2.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examlistyear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,
			String classid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0,totalstrength=0,noofsubjects=0;
		String msg = null;
		int slno = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT ccd.classdetail_id_int,ccd.classdetails_name_var,spec.`Specialization_Id`,CONCAT(spec.`Specialization_name`,'-',ccs.classsection_name_var) AS section,ccs.classsection_id_int FROM campus_classdetail ccd LEFT JOIN campus_class_specialization spec ON spec.`ClassDetails_Id`=ccd.classdetail_id_int LEFT JOIN campus_classsection_specilization_mapping specmap ON specmap.`specilizationId`=spec.`Specialization_Id` LEFT JOIN campus_classsection ccs ON ccs.`classdetail_id_int`=specmap.`classId` AND ccs.`classsection_id_int`=specmap.`sectionId` WHERE ccd.locationId=? AND ccd.classdetail_id_int=? ORDER BY ccs.classsection_name_var");
			pstmt.setString(1, locid);
			pstmt.setString(2, classid);
			System.out.println("Mapping "+pstmt);
		    rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo obj =new ExaminationDetailsVo();
				slno++;
				obj.setSno1(slno);
				obj.setClassId(rs.getString("classdetail_id_int"));
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSection(rs.getString("classsection_id_int"));
				obj.setSectionName(rs.getString("section"));
				obj.setSpecialization(rs.getString("Specialization_Id"));
				
				PreparedStatement pstmtcount=conn.prepareStatement("select count(student_id_int) as studentcount from campus_student_classdetails where classdetail_id_int=? and classsection_id_int=? and locationId=? and fms_acadamicyear_id_int=?");
				pstmtcount.setString(1,obj.getClassId());
				pstmtcount.setString(2, obj.getSection());
				pstmtcount.setString(3,locid);
				pstmtcount.setString(4,accyear);
				ResultSet rs1 =pstmtcount.executeQuery(); 
				while(rs1.next())
				{
					obj.setTot_strength(rs1.getString("studentcount"));		
				}
				int checktotal = Integer.parseInt(obj.getTot_strength());
			    if(checktotal>0)
			    {
				PreparedStatement pstmtstatus = conn.prepareStatement("select count(cm.scored_marks) from campus_studentwise_mark_details cm where cm.classid=? and cm.sectionid=?");
				pstmtstatus.setString(1,rs.getString("classdetail_id_int"));
				pstmtstatus.setString(2,rs.getString("classsection_id_int"));
				ResultSet statusrs = pstmtstatus.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement countstatus = conn.prepareStatement("select count(subjectName) as subject from campus_subject where classid=?");
				countstatus.setString(1,obj.getClassId());
				ResultSet counrs = countstatus.executeQuery();
				while(counrs.next())
				{
					noofsubjects=counrs.getInt(1);
					
				}
				countstatus.setString(1,obj.getClassId());
				String total=obj.getTot_strength();
				int foo = Integer.parseInt(total);
				if(noofsubjects!=0){
				if(count/noofsubjects==foo){
					obj.setStatus("Completed");
				}else{
					obj.setStatus("Pending");
				}
				}
				else
			    {
			    	obj.setStatus("Pending");
			    }
			    }
			    else
			    {
			    	obj.setStatus("Pending");
			    }
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;
	
	}
	private int getExitExamCount(ExaminationDetailsVo obj1, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in ExamDetailsDaoImpl : getExitExamCount Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.GET_EXAM_COUNT);
			pstmt.setString(1, obj1.getExamid());
			pstmt.setString(2, obj1.getAccyearid());
			pstmt.setString(3, obj1.getLocationid());
			rs=pstmt.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getExitExamCount Ending");
		return count;
	}

	@Override
	public String insertmarkentrydetailsSubjectWise(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertmarkentrydetailsSubjectWise Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2=null,pstmt3=null;
		ResultSet rs = null;
		String primaryid=null;
		int count=0,count1=0,count3=0,count4=0;
		String msg=null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(ExamDetailsSQLUtil.INSERT_MARK_ENTRY_STUDENTTWISE);
			pstmt1=conn.prepareStatement(ExamDetailsSQLUtil.UPDATE_MARK_ENTRY_STUDENTTWISE);
			String[] studentlist=obj.getStudentids();
			for(int i=0;i<studentlist.length;i++){
				if(obj.getStuprimaryid()[i].trim().equalsIgnoreCase("")){
					primaryid = IDGenerator.getPrimaryKeyID("campus_studentwise_mark_details",userLoggingsVo);
					pstmt.setString(1,primaryid);
					pstmt.setString(2,obj.getClassid());
					pstmt.setString(3,obj.getSectionid());
					pstmt.setString(4,obj.getExamid());
					pstmt.setString(5,obj.getAccyear());
					pstmt.setString(6,obj.getSubId());
					pstmt.setString(7,obj.getStatusvalues()[i].trim());
					pstmt.setString(8,obj.getScoremarks()[i].trim());
					pstmt.setString(9,obj.getStudentids()[i]);
					pstmt.setString(10,obj.getLocationid().trim());
					pstmt.setString(11,obj.getNotebookmarks()[i].trim());
					pstmt.setString(12,obj.getSubjectenrichment()[i].trim());
					pstmt.setString(13,obj.getMax_scored_marks()[i].trim());
					pstmt.setString(14,obj.getMaxnotebookmarks()[i].trim());
					pstmt.setString(15,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt.setString(16,obj.getCreatedBy());
					//`max_periodic_marks`,`periodic_test`
					pstmt.setString(17,obj.getMaxperiodicmarks()[i].trim());
					pstmt.setString(18,obj.getPeriodicscoredmarks()[i].trim());
					//System.out.println("PSTMT "+pstmt);
					count =pstmt.executeUpdate();
				}else{
					pstmt1.setString(1, obj.getStatusvalues()[i].trim());
					pstmt1.setString(2,obj.getScoremarks()[i].trim());
					pstmt1.setString(3,obj.getNotebookmarks()[i].trim());
					pstmt1.setString(4,obj.getSubjectenrichment()[i].trim());
					pstmt1.setString(5,obj.getMax_scored_marks()[i].trim());
					pstmt1.setString(6,obj.getMaxnotebookmarks()[i].trim());
					pstmt1.setString(7,obj.getMaxsubenrichmentmarks()[i].trim());
					pstmt1.setString(8,obj.getCreatedBy());
					pstmt1.setDate(9,HelperClass.getCurrentSqlDate());
					pstmt1.setString(10,obj.getMaxperiodicmarks()[i].trim());
					pstmt1.setString(11,obj.getPeriodicscoredmarks()[i].trim());
					pstmt1.setString(12,obj.getStuprimaryid()[i].trim());
					pstmt1.setString(13,obj.getExamid());
					//System.out.println("PSTMT "+pstmt1);
					count1 =pstmt1.executeUpdate();
				}
			}
			if(count>0){
				msg="inserted";
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntrySubjectWise","Insert",pstmt.toString(),userLoggingsVo);
			}else if(count1>0){
				msg="inserted";
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","StudentWiseMarksEntrySubjectWise","Insert",pstmt.toString(),userLoggingsVo);
			}else{
				msg="field";
			}
			
		}
	
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertmarkentrydetailsSubjectWise Ending");
		return msg;
	}

	public ArrayList<ExaminationDetailsVo> getStudentLabDetails(ArrayList<ExaminationDetailsVo> resultlist,UserLoggingsPojo custdetails, String classId1, String hschoolLocation1, String studentid, String examid, String accyear, ExaminationDetailsVo obj1) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getStudentLabDetails Starting");
		Connection conn = null;
		PreparedStatement labpstmt=null,labpstscoredmarks=null;
		ResultSet labrs=null,labrsscored=null;
		int sno=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			labpstmt = conn.prepareStatement("SELECT Lab_Code,lab_id,Lab_Name FROM laboratory_details lab WHERE lab.Subject = ? AND lab.Class_Name=? AND lab.School_Name=? AND lab.`status`='Y'");
			for(int i=0;i<resultlist.size();i++){
				labpstmt.setString(1,resultlist.get(i).getSubId());
				labpstmt.setString(2,classId1);
				labpstmt.setString(3,hschoolLocation1);
				labrs = labpstmt.executeQuery();
				while(labrs.next()){
					sno++;
					ExaminationDetailsVo objresult1 = new ExaminationDetailsVo();
					objresult1.setSno1(sno);
					objresult1.setSubId(labrs.getString("lab_id"));
					objresult1.setSubjectName(labrs.getString("Lab_Name"));
					objresult1.setSubCode(labrs.getString("Lab_Code"));
					objresult1.setTot_marks("0");

					labpstscoredmarks = conn.prepareStatement("select attendance_status,Stu_mark_id,scored_marks,total_marks from campus_studentwise_mark_details where stu_id=? and sub_id=? and exam_id=?");
					labpstscoredmarks.setString(1,studentid);
					labpstscoredmarks.setString(2,objresult1.getSubId());
					labpstscoredmarks.setString(3,examid);
					labrsscored=labpstscoredmarks.executeQuery();
					while(labrsscored.next())
					{
						if(labrsscored.getString("scored_marks") != null ){
							objresult1.setTot_marks(labrsscored.getString("total_marks"));
							objresult1.setScoredmarks(labrsscored.getString("scored_marks")); 
						}else{
							objresult1.setScoredmarks("0");
						}

						objresult1.setStudenprimid(labrsscored.getString("Stu_mark_id"));
						objresult1.setAttendace(labrsscored.getString("attendance_status"));
					}
					list.add(objresult1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (labrsscored != null&& (!labrsscored.isClosed())) {
					labrsscored.close();
				}
				if (labpstscoredmarks != null&& (!labpstscoredmarks.isClosed())) {
					labpstscoredmarks.close();
				}
				if (labrs != null&& (!labrs.isClosed())) {
					labrs.close();
				}
				if (labpstmt != null&& (!labpstmt.isClosed())) {
					labpstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getStudentLabDetails Ending");
		return list;
	}

	@Override
	public List<ExaminationDetailsVo> getExamByClassWise(String locid, String accyear, String classid,
			UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getExamByClassWise : Starting");
		PreparedStatement pstmt= null,pstmt1= null,pstmt2= null;
		ResultSet rs=null,rs1=null,rs2=null;
		Connection conn = null;
		ArrayList<ExaminationDetailsVo> list =new ArrayList<>();
		int count=1;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `examid`,`examcode`,`examname`,`startdate`,`enddate` FROM `campus_examination` WHERE `acadamicyear`=? AND `loc_id`=? AND `classid`=? ORDER BY `startdate`");
			pstmt.setString(1, accyear);
			pstmt.setString(2, locid);
			pstmt.setString(3, classid);
			System.out.println("1st "+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ExaminationDetailsVo vo=new ExaminationDetailsVo();
				vo.setSno(Integer.toString(count));
				count++;
				vo.setExamid(rs.getString("examid"));
				vo.setExamcode(rs.getString("examcode"));
				vo.setExamName(rs.getString("examname"));
				vo.setStartDate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				vo.setEndDate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				int sectioncount=0;
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_classsection` WHERE `classdetail_id_int`=? AND `locationId`=?  AND `isActive`='Y'");
				pstmt1.setString(1, classid);
				pstmt1.setString(2, locid);
				System.out.println("2nd "+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					sectioncount=rs1.getInt(1);
				}
				int examcount=0;
				pstmt2 = conn.prepareStatement("SELECT COUNT(*) FROM `campus_exam_timetable` WHERE `examcode`=? AND `class_id`=? AND `loc_id`=? AND `accyear_id`=? ");
				pstmt2.setString(1, rs.getString("examid"));
				pstmt2.setString(2, classid);
				pstmt2.setString(3, locid);
				pstmt2.setString(4, accyear);
				System.out.println("3rd "+pstmt2);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					examcount=rs2.getInt(1);
				}

				if(examcount==0){
					vo.setStatus("Not Set");
				}else{
					if(sectioncount==examcount){
						vo.setStatus("Set");
					}else{
						vo.setStatus("Not Set");
					}
				}
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public List<ExaminationDetailsVo> getStudentMarkListSearch(ExaminationDetailsVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getStudentMarkListSearch Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(!vo.getSpecializationId().equals("")){
				pstmt = conn.prepareStatement("SELECT cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS student,spec.`Specialization_name`,spec.Specialization_Id FROM campus_student cs JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int AND csc.classdetail_id_int=? AND classsection_id_int=? LEFT JOIN `campus_class_specialization` spec ON spec.`Specialization_Id`=csc.specilization WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND cs.`student_status_var`='active' and csc.specilization=? and (csc.student_rollno like ? OR cs.student_admissionno_var like ? OR cs.student_fname_var like ? OR cs.student_lname_var like ? OR spec.`Specialization_name` like ?) ORDER BY student ");
				pstmt.setString(5,vo.getSpecializationId());
			}else{
				pstmt = conn.prepareStatement("SELECT cs.student_id_int,csc.student_rollno,cs.student_admissionno_var,CONCAT(cs.student_fname_var,' ',cs.student_lname_var) AS student,spec.`Specialization_name`,spec.Specialization_Id FROM campus_student cs JOIN campus_student_classdetails csc ON csc.student_id_int=cs.student_id_int AND csc.classdetail_id_int=? AND classsection_id_int=? LEFT JOIN `campus_class_specialization` spec ON spec.`Specialization_Id`=csc.specilization WHERE csc.locationId=? AND csc.fms_acadamicyear_id_int=? AND cs.`student_status_var`='active' and csc.specilization like ? and (csc.student_rollno like ? OR cs.student_admissionno_var like ? OR cs.student_fname_var like ? OR cs.student_lname_var like ? OR spec.`Specialization_name` like ?) ORDER BY student ");
				pstmt.setString(5,"%%");
			}
			pstmt.setString(1,vo.getClassId());
			pstmt.setString(2,vo.getSectionid());
			pstmt.setString(3,vo.getLocationid());
			pstmt.setString(4,vo.getAccyearId());
			pstmt.setString(6,"%"+vo.getSearchText()+"%");
			pstmt.setString(7,"%"+vo.getSearchText()+"%");
			pstmt.setString(8,"%"+vo.getSearchText()+"%");
			pstmt.setString(9,"%"+vo.getSearchText()+"%");
			pstmt.setString(10,"%"+vo.getSearchText()+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int count = 0;
				int stu_count=0;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setStudentid(rs.getString("student_id_int"));
				
				if(rs.getString("student_rollno") == null){
					objec.setStudentrollno("");
				}else{
					objec.setStudentrollno(rs.getString("student_rollno"));
				}
				
				objec.setAdmissionno(rs.getString("student_admissionno_var"));
				objec.setStudentname(rs.getString("student"));
				objec.setSpecializationId(rs.getString("Specialization_Id"));
				if(rs.getString("Specialization_name").equals(null)){
					objec.setSpecialization("-");
				}else{
					objec.setSpecialization(rs.getString("Specialization_name"));
				}
				
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_subject` WHERE `locationId`=? AND `classid`=? AND isActive='Y'");
				statuspstmt.setString(1,vo.getLocationid());
				statuspstmt.setString(2,vo.getClassId());
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
				}
				PreparedStatement stu = conn.prepareStatement("SELECT COUNT(`stu_id`) FROM `campus_studentwise_mark_details` WHERE `stu_id`=? AND `classid` = ? AND `sectionid` = ? AND `Academic_yearid` = ? AND `location_id` = ? AND `exam_id`=?");
				stu.setString(1,rs.getString("student_id_int"));
				stu.setString(2,vo.getClassId());
				stu.setString(3,vo.getSection());
				stu.setString(4,vo.getAccyearid());
				stu.setString(5,vo.getLocationid());
				stu.setString(6,vo.getExamid());
				ResultSet sturs = stu.executeQuery();
				while(sturs.next()){
					stu_count = sturs.getInt(1);
				}
				if(count != 0 && count==stu_count){
					objec.setStatus("Completed");
				}else{
					objec.setStatus("Pending");
				}
				list.add(objec);
				sturs.close();
				stu.close();
				statusrs.close();
				statuspstmt.close();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getStudentMarkListSearch Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getReportCardSettingList(String accyear, String locid, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getReportCardSettingList Starting");
		ArrayList<ExaminationDetailsVo> examlistyear = new ArrayList<ExaminationDetailsVo>();

		PreparedStatement pstmt = null,pstmt1=null,pstmt2=null;
		Connection conn = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		int yearcount=0,yearcount1=0;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = conn.prepareStatement(ExamTimeTableSqlUtils.GET_EXAMYEAR1);
			pstmt.setString(1,locid);
			pstmt.setString(2,accyear);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				ExaminationDetailsVo exampojo = new ExaminationDetailsVo();
				count++;
				exampojo.setSno1(count);
				exampojo.setAccyearid(rs.getString("acadamic_id"));
				exampojo.setAccyear(rs.getString("acadamic_year"));
				exampojo.setStartDate(rs.getString("startDate"));
				exampojo.setEndDate(rs.getString("endDate"));
				exampojo.setLocationid(rs.getString("Location_Id"));
				exampojo.setLocname(rs.getString("Location_Name"));
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) AS accyearcount FROM `campus_exam_max_marks_setup` st WHERE st.`accy_id`=? AND st.loc_id=?");
				pstmt1.setString(1, rs.getString("acadamic_id"));
				pstmt1.setString(2, rs.getString("Location_Id"));
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					yearcount=rs1.getInt("accyearcount");
				}	
				exampojo.setAccyearcount(yearcount);
				if (yearcount > 0) {
					exampojo.setStatus("Set");
				} else {
					exampojo.setStatus("Not Set");
				}
				pstmt2 = conn.prepareStatement("SELECT COUNT(*) AS accyearcount FROM `campus_exam_report_setup` st WHERE st.`accy_id`=? AND st.loc_id=?");
				pstmt2.setString(1, rs.getString("acadamic_id"));
				pstmt2.setString(2, rs.getString("Location_Id"));
				rs2 = pstmt2.executeQuery();
				while(rs2.next()) {
					yearcount1=rs2.getInt("accyearcount");
				}	
				if (yearcount1> 0) {
					exampojo.setReportstatus("Set");
				} else {
					exampojo.setReportstatus("Not Set");
				}
				examlistyear.add(exampojo);
			}
			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs2 != null && !rs2.isClosed()) {
					rs2.close();
				}
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getReportCardSettingList Ending");
		return examlistyear;
	}

	@Override
	public List<ExaminationDetailsVo> getMaximumMarkClassList(String accyear, String locid, UserLoggingsPojo custdetails, String classId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl: getMaximumMarkClassList : Starting");
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		Connection conn = null;
		int count = 0,count1=0,slno = 0;
		String markId="",setupId="",maxlimit="",report_type="";
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			if(!classId.equalsIgnoreCase("all")){
				pstmt = conn.prepareStatement("SELECT DISTINCT ccd.classdetail_id_int,ccd.classdetails_name_var FROM campus_classdetail ccd JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int WHERE ccd.locationId = ? AND ccd.isActive='Y' AND ccs.isActive='Y' and ccd.classdetail_id_int=?");
				pstmt.setString(1, locid);
				pstmt.setString(2, classId);
			}else{
				pstmt = conn.prepareStatement("SELECT DISTINCT ccd.classdetail_id_int,ccd.classdetails_name_var FROM campus_classdetail ccd JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int WHERE ccd.locationId = ? AND ccd.isActive='Y' AND ccs.isActive='Y' ORDER BY LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int");
				pstmt.setString(1, locid);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno ++ ;
				String classid = rs.getString("classdetail_id_int");
				ExaminationDetailsVo obj = new ExaminationDetailsVo();
				obj.setClassId(classid);
				obj.setClassname(rs.getString("classdetails_name_var"));
				obj.setSno1(slno);

				PreparedStatement pst=conn.prepareStatement("SELECT COUNT(*),`periodic_exam`,`notebook`,`subenrichment` FROM `campus_exam_max_marks_setup` WHERE `accy_id` = ? AND loc_id = ?");
				pst.setString(1, accyear);
				pst.setString(2, locid);
				ResultSet rst=pst.executeQuery();
				while(rst.next()){
					if(rst.getInt(1) > 0){
						obj.setPeriodicmark(rst.getString("periodic_exam"));
						obj.setMax_notebook_marks(rst.getString("notebook"));
						obj.setMax_subenrich_marks(rst.getString("subenrichment"));
					}else{
						obj.setPeriodicmark(" ");
						obj.setMax_notebook_marks(" ");
						obj.setMax_subenrich_marks(" ");
					}
				}
				rst.close();
				pst.close();
				
				PreparedStatement statuspstmt = conn.prepareStatement("SELECT COUNT(*),`mark_id` FROM `campus_exam_max_marks_setup` WHERE `accy_id` = ? AND loc_id = ? AND `class_id` = ?");
				statuspstmt.setString(1,accyear);
				statuspstmt.setString(2,locid);
				statuspstmt.setString(3,classid);
				ResultSet statusrs = statuspstmt.executeQuery();
				while(statusrs.next()){
					count = statusrs.getInt(1);
					markId = statusrs.getString(2);
				}
				if(count>0){
					obj.setStatus("Set");
					obj.setMarkId(markId);
				}
				else{
					obj.setStatus("Not Set");
					obj.setMarkId("");
				}
				statusrs.close();
				statuspstmt.close();
				
				PreparedStatement statuspstmt1 = conn.prepareStatement("SELECT COUNT(*),report_setup_id,report_type,maxlimit FROM `campus_exam_report_setup` WHERE `accy_id` = ? AND loc_id = ? AND `class_id` = ?");
				statuspstmt1.setString(1,accyear);
				statuspstmt1.setString(2,locid);
				statuspstmt1.setString(3,classid);
				ResultSet statusrs1 = statuspstmt1.executeQuery();
				while(statusrs1.next()){
					count1 = statusrs1.getInt(1);
					setupId = statusrs1.getString(2);
					report_type = statusrs1.getString(3);
					maxlimit = statusrs1.getString(4);
				}
				statusrs1.close();
				statuspstmt1.close();

				if(count1>0){
					obj.setReportstatus("Set");
					obj.setSetupId(setupId);
					obj.setMaxLimit(maxlimit);
					obj.setReporttype(report_type);
				}
				else{
					obj.setReportstatus("Not Set");
					obj.setSetupId("");
				}

				list.add(obj);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getMaximumMarkClassList Ending");
		return list;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubjectInMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : classWiseSubjectInMaximumMark Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2= null;
		int slno = 0;
		int specializationCount=0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt1=conn.prepareStatement("SELECT COUNT(`Specialization_Id`) FROM `campus_class_specialization` WHERE `locationId`=? AND `ClassDetails_Id`=? AND `isActive`='Y'");
			pstmt1.setString(2,obj.getClassId());
			pstmt1.setString(1,obj.getLocationid());
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				specializationCount=rs1.getInt(1);
			}
			if(specializationCount > 0){
				pstmt = conn.prepareStatement("SELECT `Specialization_Id`,Specialization_name FROM `campus_class_specialization` WHERE `locationId`=? AND `ClassDetails_Id`=? AND `isActive`='Y'");
				pstmt.setString(2,obj.getClassId());
				pstmt.setString(1,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					ExaminationDetailsVo objec = new ExaminationDetailsVo();
					objec.setSpecilazationCount(specializationCount);
					objec.setSpecialization(rs.getString("Specialization_name"));
					ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
					pstmt2 = conn.prepareStatement("select subjectID,subjectName,Sub_Code from campus_subject where classid = ? and locationId=? AND `specialization`=? AND isActive='Y' AND isLab='N' order by subjectName");
					pstmt2.setString(1,obj.getClassId());
					pstmt2.setString(2,obj.getLocationid());
					pstmt2.setString(3,rs.getString("Specialization_Id"));
					rs2 = pstmt2.executeQuery();
					while(rs2.next()){
						slno++;
						ExaminationDetailsVo objec1 = new ExaminationDetailsVo();
						objec1.setSno1(slno);
						objec1.setSubId(rs2.getString("subjectID"));
						objec1.setSubjectName(rs2.getString("subjectName"));
						objec1.setSubCode(rs2.getString("Sub_Code"));
						objec1.setLabid(" ");
						list1.add(objec1);
					}
					objec.setExamlist(list1);
					list.add(objec);
				}
			}else{
			pstmt = conn.prepareStatement("select subjectID,subjectName,Sub_Code from campus_subject where classid = ? and locationId=? AND isActive='Y' AND isLab='N' order by subjectName");
			pstmt.setString(1,obj.getClassId());
			pstmt.setString(2,obj.getLocationid());

			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setSpecilazationCount(specializationCount);
				objec.setSno1(slno);
				objec.setSubId(rs.getString("subjectID"));
				objec.setSubjectName(rs.getString("subjectName"));
				objec.setSubCode(rs.getString("Sub_Code"));
				objec.setLabid(" ");
				list.add(objec);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: classWiseSubjectInMaximumMark Ending");
		return list;
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubjectWithLabMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : editGradeSettings Starting");
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		int slno = 0;
		int specializationCount = 0;
		ArrayList<ExaminationDetailsVo> list = new ArrayList<ExaminationDetailsVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			/*if(obj.getMarkId() == ""){
				pstmt = conn.prepareStatement("select subjectID,subjectName,Sub_Code from campus_subject where classid = ? and locationId=? AND isActive='Y' AND isLab='Y' order by subjectName");
				pstmt.setString(1,obj.getClassId());
				pstmt.setString(2,obj.getLocationid());
			}else{*/
			pstmt1=conn.prepareStatement("SELECT COUNT(`Specialization_Id`) FROM `campus_class_specialization` WHERE `locationId`=? AND `ClassDetails_Id`=? AND `isActive`='Y'");
			pstmt1.setString(2,obj.getClassId());
			pstmt1.setString(1,obj.getLocationid());
			rs1 = pstmt1.executeQuery();
			while(rs1.next()){
				specializationCount=rs1.getInt(1);
			}
			if(specializationCount>0){
				pstmt = conn.prepareStatement("SELECT `Specialization_Id`,Specialization_name FROM `campus_class_specialization` WHERE `locationId`=? AND `ClassDetails_Id`=? AND `isActive`='Y'");
				pstmt.setString(2,obj.getClassId());
				pstmt.setString(1,obj.getLocationid());
				rs = pstmt.executeQuery();
				while(rs.next()){
					ExaminationDetailsVo objec = new ExaminationDetailsVo();
					objec.setSpecilazationCount(specializationCount);
					objec.setSpecialization(rs.getString("Specialization_name"));
					ArrayList<ExaminationDetailsVo> list1 = new ArrayList<ExaminationDetailsVo>();
					pstmt2 = conn.prepareStatement("SELECT sub.subjectID,sub.subjectName,sub.Sub_Code,msub.`theory_marks`,msub.`practical_marks`,lab.`lab_id` FROM campus_subject sub LEFT JOIN `laboratory_details` lab ON sub.subjectID=lab.`Subject` LEFT JOIN `campus_exam_max_marks_subwise` msub ON msub.`sub_id`= sub.subjectID AND msub.`exam_max_marks_id`=? WHERE sub.classid = ? AND sub.locationId=? AND sub.`specialization`= ? AND sub.isActive='Y' AND sub.isLab='Y' ORDER BY sub.subjectName");
					pstmt2.setString(1,obj.getMarkId());
					pstmt2.setString(2,obj.getClassId());
					pstmt2.setString(3,obj.getLocationid());
					pstmt2.setString(4,rs.getString("Specialization_Id"));
					rs2 = pstmt2.executeQuery();
					while(rs2.next()){
						slno++;
						ExaminationDetailsVo objec1 = new ExaminationDetailsVo();
						objec1.setSno1(slno);
						objec1.setSubId(rs2.getString("subjectID"));
						objec1.setSubjectName(rs2.getString("subjectName"));
						objec1.setSubCode(rs2.getString("Sub_Code"));
						objec1.setLabid(rs2.getString("lab_id"));
						if(rs2.getString("theory_marks") != null){
							objec1.setTheoryMarks(rs2.getString("theory_marks"));
						}else{
							objec1.setTheoryMarks("");
						}
						if(rs2.getString("practical_marks") != null){
							objec1.setPracticalMarks(rs2.getString("practical_marks"));
						}else{
							objec1.setPracticalMarks("");
						}
						list1.add(objec1);
					}
					objec.setExamlist(list1);
					list.add(objec);
				}
			}else{
				pstmt = conn.prepareStatement("SELECT sub.subjectID,sub.subjectName,sub.Sub_Code,msub.`theory_marks`,msub.`practical_marks`,lab.`lab_id` FROM campus_subject sub LEFT JOIN `laboratory_details` lab ON sub.subjectID=lab.`Subject` LEFT JOIN `campus_exam_max_marks_subwise` msub ON msub.`sub_id`= sub.subjectID AND msub.`exam_max_marks_id`=? WHERE sub.classid = ? AND sub.locationId=? AND sub.isActive='Y' AND sub.isLab='Y' ORDER BY sub.subjectName");
				pstmt.setString(1,obj.getMarkId());
				pstmt.setString(2,obj.getClassId());
				pstmt.setString(3,obj.getLocationid());
				
			rs = pstmt.executeQuery();
			while(rs.next()){
				slno++;
				ExaminationDetailsVo objec = new ExaminationDetailsVo();
				objec.setSpecilazationCount(specializationCount);
				objec.setSno1(slno);
				objec.setSubId(rs.getString("subjectID"));
				objec.setSubjectName(rs.getString("subjectName"));
				objec.setSubCode(rs.getString("Sub_Code"));
				objec.setLabid(rs.getString("lab_id"));
				if(rs.getString("theory_marks") != null){
					objec.setTheoryMarks(rs.getString("theory_marks"));
				}else{
					objec.setTheoryMarks("");
				}
				if(rs.getString("practical_marks") != null){
					objec.setPracticalMarks(rs.getString("practical_marks"));
				}else{
					objec.setPracticalMarks("");
				}
				list.add(objec);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: editGradeSettings Ending");
		return list;
	}

	@Override
	public String insertMaximumExammarkDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertMaximumExammarkDetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		String status = "";
		int count=0,count1=0,count2=0,count3=0;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			PreparedStatement pst=conn.prepareStatement("SELECT COUNT(*) FROM `campus_exam_max_marks_setup` WHERE `accy_id` = ? AND loc_id = ? ");
			pst.setString(1, obj.getAccyearid());
			pst.setString(2,obj.getLocationid());
			ResultSet rst=pst.executeQuery();
			while(rst.next()){
				count2=rst.getInt(1);
			}
			rst.close();
			pst.close();
			if(count2 > 0){
				PreparedStatement pst1=conn.prepareStatement("update campus_exam_max_marks_setup set periodic_exam=?,`notebook`=?,`subenrichment`=? where `accy_id` = ? AND loc_id = ?");
				pst1.setString(1, obj.getPeriodicmark());
				pst1.setString(2, obj.getMax_notebook_marks());
				pst1.setString(3, obj.getMax_subenrich_marks());
				pst1.setString(4, obj.getAccyearid());
				pst1.setString(5, obj.getLocationid());
				pst1.executeUpdate();
				pst1.close();
			}
			if(!obj.getMarkId().trim().equalsIgnoreCase("")){
				pstmt=conn.prepareStatement("update campus_exam_max_marks_setup set periodic_exam=?,`notebook`=?,`subenrichment`=?,`modified_by`=?,`modified_date`=?  where `accy_id` = ? AND loc_id = ? and class_id=?");
				pstmt.setString(1, obj.getPeriodicmark());
				pstmt.setString(2, obj.getMax_notebook_marks());
				pstmt.setString(3, obj.getMax_subenrich_marks());
				pstmt.setString(4, obj.getCreatedBy());
				pstmt.setDate(5, HelperClass.getCurrentSqlDate());
				pstmt.setString(6, obj.getAccyearid());
				pstmt.setString(7, obj.getLocationid());
				pstmt.setString(8, obj.getClassId());
				count = pstmt.executeUpdate();
				if (count > 0) {
					PreparedStatement pst3=conn.prepareStatement("SELECT COUNT(*) FROM `campus_exam_max_marks_subwise` WHERE `exam_max_marks_id`=?");
					pst3.setString(1, obj.getMarkId().trim());
					ResultSet rst3=pst3.executeQuery();
					while(rst3.next()){
						count3=rst3.getInt(1);
					}
					rst3.close();
					pst3.close();
					if(count3 > 0){
						PreparedStatement pst2=conn.prepareStatement("delete from campus_exam_max_marks_subwise where exam_max_marks_id=?");
						pst2.setString(1, obj.getMarkId().trim());
						pst2.executeUpdate();
						pst2.close();
					}
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Maximum Mark Setup","update",pstmt.toString(),custdetails);
					if(obj.getSubId() != ""){
						for(int i=0;i<obj.getSubId().split(",").length;i++){
							pstmt1 = conn.prepareStatement("INSERT INTO `campus_exam_max_marks_subwise` (`exam_max_marks_id`,`sub_id`,`theory_marks`,`practical_marks`,lab_id) VALUES(?,?,?,?,?)");
							pstmt1.setString(1, obj.getMarkId().trim());
							pstmt1.setString(2, obj.getSubId().split(",")[i]);
							pstmt1.setString(3, obj.getTheoryMarks().split(",")[i]);
							pstmt1.setString(4, obj.getPracticalMarks().split(",")[i]);
							pstmt1.setString(5, obj.getLabid().split(",")[i]);
							count1 = pstmt1.executeUpdate();
							if (count1 > 0) {
								HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Maximum Mark Subject Wise Setup","insert",pstmt1.toString(),custdetails);
							}
						}
					}
					status="update";
				}else{
					status="failure";
				}
			}else{
				String key = IDGenerator.getPrimaryKeyID("campus_exam_max_marks_setup",custdetails);
				pstmt = conn.prepareStatement("INSERT INTO `campus_exam_max_marks_setup` (mark_id,`accy_id`,`loc_id`,`maxlimit`,`periodic_exam`,`class_id`,`created_by`,`notebook`,`subenrichment`) VALUES(?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1,key);
				pstmt.setString(2, obj.getAccyearid());
				pstmt.setString(3,obj.getLocationid());
				pstmt.setString(4,obj.getMax_marks());
				pstmt.setString(5,obj.getPeriodicmark());
				pstmt.setString(6,obj.getClassId());
				pstmt.setString(7,obj.getCreatedBy());
				pstmt.setString(8, obj.getMax_notebook_marks());
				pstmt.setString(9, obj.getMax_subenrich_marks());
				count = pstmt.executeUpdate();
				if (count > 0) {
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Maximum Mark Setup","insert",pstmt.toString(),custdetails);
					if(obj.getSubId() != ""){
						for(int i=0;i<obj.getSubId().split(",").length;i++){
							pstmt1 = conn.prepareStatement("INSERT INTO `campus_exam_max_marks_subwise` (`exam_max_marks_id`,`sub_id`,`theory_marks`,`practical_marks`,lab_id) VALUES(?,?,?,?,?)");
							pstmt1.setString(1, key);
							pstmt1.setString(2, obj.getSubId().split(",")[i]);
							pstmt1.setString(3, obj.getTheoryMarks().split(",")[i]);
							pstmt1.setString(4, obj.getPracticalMarks().split(",")[i]);
							pstmt1.setString(5, obj.getLabid().split(",")[i]);
							count1 = pstmt1.executeUpdate();
							if (count1 > 0) {
								HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Maximum Mark Subject Wise Setup","insert",pstmt1.toString(),custdetails);
							}
						}
					}
					status="success";
				}else{
					status="failure";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: insertMaximumExammarkDetails Ending");
		return status;
	}

	@Override
	public String insertReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : insertReportSetupDetails Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		String status = "";
		int count=0,count1=0;
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			if(!obj.getSetupId().trim().equalsIgnoreCase("")){
				pstmt=conn.prepareStatement("update campus_exam_report_setup set report_type=?,`modified_by`=?,`modified_date`=?  where `accy_id` = ? AND loc_id = ? and class_id=?");
				pstmt.setString(1, obj.getReporttype());
				pstmt.setString(2, obj.getCreatedBy());
				pstmt.setDate(3, HelperClass.getCurrentSqlDate());
				pstmt.setString(4, obj.getAccyearid());
				pstmt.setString(5, obj.getLocationid());
				pstmt.setString(6, obj.getClassId());
				count = pstmt.executeUpdate();
				if (count > 0) {
					PreparedStatement pst2=conn.prepareStatement("delete from campus_exam_report_setup_detail where report_setup_id=?");
					pst2.setString(1, obj.getSetupId().trim());
					pst2.executeUpdate();
					pst2.close();
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Report Setup","update",pstmt.toString(),custdetails);
					if(obj.getTermname() != ""){
						for(int i=0;i<obj.getTermname().split(",").length;i++){
							pstmt1 = conn.prepareStatement("INSERT INTO `campus_exam_report_setup_detail` (`report_setup_id`,`report_name`,`periodic_exam1`,`periodic_exam2`,`yearly_exam`,`periodic_mark1`,`periodic_mark2`,`notebook_mark`,`subenrichment`,`yearlytheorymark`,`yearlypracticalmark`,halfyearlyexam,halfyearlytheorymark,yearlypracticaltheorymark) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							pstmt1.setString(1, obj.getSetupId().trim());
							pstmt1.setString(2, obj.getTermname().split(",")[i]);
							pstmt1.setString(3, obj.getPeriodicexam().split(",")[i]);
							pstmt1.setString(4, obj.getPeriodicexam1().split(",")[i]);
							pstmt1.setString(5, obj.getFinalexam().split(",")[i]);
							pstmt1.setString(6, obj.getPeriodicmark().split(",")[i]);
							pstmt1.setString(7, obj.getPeriodicmark1().split(",")[i]);
							pstmt1.setString(8, obj.getNotebookmark().split(",")[i]);
							pstmt1.setString(9, obj.getSubenrichmentmark().split(",")[i]);
							pstmt1.setString(10, obj.getNonpracticalmark().split(",")[i]);
							pstmt1.setString(11, obj.getPracticalMarks().split(",")[i]);
							pstmt1.setString(12, obj.getHalfyearlyexam().split(",")[i]);
							pstmt1.setString(13, obj.getHalfyearlymark().split(",")[i]);
							pstmt1.setString(14, obj.getTheoryMarks().split(",")[i]);
							count1 = pstmt1.executeUpdate();
							if (count1 > 0) {
								HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Report Setup","insert",pstmt1.toString(),custdetails);
							}
						}
					}
					status="update";
				}else{
					status="failure";
				}
			}else{
				String key = IDGenerator.getPrimaryKeyID("campus_exam_report_setup",custdetails);
				pstmt = conn.prepareStatement("INSERT INTO `campus_exam_report_setup` (`report_setup_id`,`accy_id`,`loc_id`,`class_id`,`report_type`,`maxlimit`,`created_by`) VALUE(?,?,?,?,?,?,?)");
				pstmt.setString(1,key);
				pstmt.setString(2, obj.getAccyearid());
				pstmt.setString(3,obj.getLocationid());
				pstmt.setString(4,obj.getClassId());
				pstmt.setString(5,obj.getReporttype());
				pstmt.setString(6,obj.getMax_marks());
				pstmt.setString(7,obj.getCreatedBy());
				count = pstmt.executeUpdate();
				if (count > 0) {
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Report Setup","insert",pstmt.toString(),custdetails);
					if(obj.getTermname() != ""){
						for(int i=0;i<obj.getTermname().split(",").length;i++){
							pstmt1 = conn.prepareStatement("INSERT INTO `campus_exam_report_setup_detail` (`report_setup_id`,`report_name`,`periodic_exam1`,`periodic_exam2`,`yearly_exam`,`periodic_mark1`,`periodic_mark2`,`notebook_mark`,`subenrichment`,`yearlytheorymark`,`yearlypracticalmark`,halfyearlyexam,halfyearlytheorymark,yearlypracticaltheorymark) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							pstmt1.setString(1, key);
							pstmt1.setString(2, obj.getTermname().split(",")[i]);
							pstmt1.setString(3, obj.getPeriodicexam().split(",")[i]);
							pstmt1.setString(4, obj.getPeriodicexam1().split(",")[i]);
							pstmt1.setString(5, obj.getFinalexam().split(",")[i]);
							pstmt1.setString(6, obj.getPeriodicmark().split(",")[i]);
							pstmt1.setString(7, obj.getPeriodicmark1().split(",")[i]);
							pstmt1.setString(8, obj.getNotebookmark().split(",")[i]);
							pstmt1.setString(9, obj.getSubenrichmentmark().split(",")[i]);
							pstmt1.setString(10, obj.getNonpracticalmark().split(",")[i]);
							pstmt1.setString(11, obj.getPracticalMarks().split(",")[i]);
							pstmt1.setString(12, obj.getHalfyearlyexam().split(",")[i]);
							pstmt1.setString(13, obj.getHalfyearlymark().split(",")[i]);
							pstmt1.setString(14, obj.getTheoryMarks().split(",")[i]);
							count1 = pstmt1.executeUpdate();
							if (count1 > 0) {
								HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Exam","Report Setup","insert",pstmt1.toString(),custdetails);
							}
						}
					}
					status="success";
				}else{
					status="failure";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (pstmt1 != null&& (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: insertReportSetupDetails Ending");
		return status;
	}

	@Override
	public ExaminationDetailsVo getTerm1ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getTerm1ReportSetupDetails Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ExaminationDetailsVo vo = new ExaminationDetailsVo();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `report_name`,`periodic_exam1`,`yearly_exam`,`periodic_mark1`,`notebook_mark`,`subenrichment`,yearlytheorymark FROM `campus_exam_report_setup_detail` WHERE `report_setup_id`=? AND `report_name`='term1'");
			pstmt.setString(1,obj.getSetupId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setTermname(rs.getString("report_name"));
				vo.setPeriodicexam(rs.getString("periodic_exam1"));
				vo.setFinalexam(rs.getString("yearly_exam"));
				vo.setPeriodicmark(rs.getString("periodic_mark1"));
				vo.setNotebookmark(rs.getString("notebook_mark"));
				vo.setSubenrichmentmark(rs.getString("subenrichment"));
				vo.setTheoryMarks(rs.getString("yearlytheorymark"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getTerm1ReportSetupDetails Ending");
		return vo;
	}

	@Override
	public ExaminationDetailsVo getTerm2ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getTerm2ReportSetupDetails Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ExaminationDetailsVo vo = new ExaminationDetailsVo();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `report_name`,`periodic_exam1`,`yearly_exam`,`periodic_mark1`,`notebook_mark`,`subenrichment`,yearlytheorymark FROM `campus_exam_report_setup_detail` WHERE `report_setup_id`=? AND `report_name`='term2'");
			pstmt.setString(1,obj.getSetupId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setTermname(rs.getString("report_name"));
				vo.setPeriodicexam(rs.getString("periodic_exam1"));
				vo.setFinalexam(rs.getString("yearly_exam"));
				vo.setPeriodicmark(rs.getString("periodic_mark1"));
				vo.setNotebookmark(rs.getString("notebook_mark"));
				vo.setSubenrichmentmark(rs.getString("subenrichment"));
				vo.setTheoryMarks(rs.getString("yearlytheorymark"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getTerm2ReportSetupDetails Ending");
		return vo;
	}

	@Override
	public ExaminationDetailsVo getAcademicReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsDaoImpl : getAcademicReportSetupDetails Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ExaminationDetailsVo vo = new ExaminationDetailsVo();
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `report_name`,`periodic_exam1`,`periodic_exam2`,`halfyearlyexam`,`yearly_exam`,`periodic_mark1`,periodic_mark2,`halfyearlytheorymark`,yearlytheorymark,`yearlypracticalmark`,yearlypracticaltheorymark FROM `campus_exam_report_setup_detail` WHERE `report_setup_id`=? AND `report_name`='academic'");
			pstmt.setString(1,obj.getSetupId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setTermname(rs.getString("report_name"));
				vo.setPeriodicexam(rs.getString("periodic_exam1"));
				vo.setPeriodicexam1(rs.getString("periodic_exam2"));
				vo.setHalfyearlyexam(rs.getString("halfyearlyexam"));
				vo.setFinalexam(rs.getString("yearly_exam"));
				vo.setPeriodicmark(rs.getString("periodic_mark1"));
				vo.setPeriodicmark1(rs.getString("periodic_mark2"));
				vo.setNonpracticalmark(rs.getString("yearlytheorymark"));
				vo.setHalfyearlymark(rs.getString("halfyearlytheorymark"));
				vo.setPracticalMarks(rs.getString("yearlypracticalmark"));
				vo.setTheoryMarks(rs.getString("yearlypracticaltheorymark"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getAcademicReportSetupDetails Ending");
		return vo;
	}
}
