package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.admin.SMSThread;
import com.centris.campus.admin.SendSMS;
import com.centris.campus.dao.SmsDao;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.CommunicateUtilConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.util.StudentAttendanceSqlUtil;
import com.centris.campus.vo.FeeSMSVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsCountVO;
import com.centris.campus.vo.SmsIntegrationApiVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TermMasterVo;

public class SmsDaoIMPL implements SmsDao{

	private static final Logger logger = Logger
			.getLogger(SmsDaoIMPL.class);

	public List<ClassPojo> getclasslistDao(ClassPojo pojo1,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getclasslistDao  Starting");

		List<ClassPojo> classlist = new ArrayList<ClassPojo>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_CLASS_LIST);

			rs = pstmt.executeQuery();

			while(rs.next()){

				ClassPojo pojo = new ClassPojo();
				pojo.setClassid(rs.getString("classdetail_id_int"));
				pojo.setClassName(rs.getString("classdetails_name_var"));

				classlist.add(pojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getclasslistDao  Ending");

		return classlist;
	}


	public ArrayList<SectionPojo> getsectionlistDao(String classid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsectionlistDao  Starting");

		ArrayList<SectionPojo> sec = new ArrayList<SectionPojo>();


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_SECTION_LIST);

			pstmt.setString(1, classid);



			rs = pstmt.executeQuery();

			while(rs.next()){

				SectionPojo pojo = new SectionPojo();

				pojo.setSecId(rs.getString("classsection_id_int"));
				pojo.setSectionName(rs.getString("classsection_name_var"));


				sec.add(pojo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}


		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsectionlistDao  Ending");

		return sec;
	}



	public ArrayList<SectionPojo> getsubjectlistDao(String classid,String locationId,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsubjectlistDao  Starting");

		ArrayList<SectionPojo> sec = new ArrayList<SectionPojo>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_SUBJECT_LIST);
			pstmt.setString(1, classid);
			pstmt.setString(2, locationId);
			
			rs = pstmt.executeQuery();

			while(rs.next()){

				SectionPojo pojo = new SectionPojo();
				pojo.setSubjectid(rs.getString("subjectID"));
				pojo.setSubjectname(rs.getString("subjectName"));

				sec.add(pojo);
			}



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getsubjectlistDao  Ending");

		return sec;
	}

	public String inserthomeworDao(SmsVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : inserthomeworDao  Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		String result_Status = "";
		ResultSet rs = null;
		int result = 0;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(vo.getDbDetails());

			pstmt = conn.prepareStatement(SmsUtilsConstants.INSERT_HOMEWORK);

			if(vo.getStudentList().length > vo.getBalanceSMS()){
				result_Status = "insufficientSMSBalance";
			}else{

				for(int i=0; i<vo.getStudentList().length;i++){

					String str = IDGenerator.getPrimaryKeyID("campus_homework",vo.getDbDetails());
					pstmt.setString(1, str);
					pstmt.setString(2, (HelperClass.convertUIToDatabase(vo.getDate())));
					pstmt.setString(3, vo.getClassid());
					pstmt.setString(4, vo.getSectionid());
					pstmt.setString(5, vo.getSubjectid());
					pstmt.setString(6,vo.getDescription());
					pstmt.setTimestamp(7, time_stamp);
					pstmt.setString(8,vo.getCreateuser());
					pstmt.setString(9,vo.getLocId());
					pstmt.setString(10,vo.getStudentList()[i]);
					pstmt.setString(11, vo.getAccid());

					result = pstmt.executeUpdate();
					if (result > 0) {
						Runnable r = new SMSThread(str,vo.getDate(),"HomeWork",vo.getLog_audit_session(),"Interaction","Home Work",vo.getDbDetails(),vo.getLocId());
						new Thread(r).start();

						HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Interaction","Home Work","Insert",pstmt.toString(),vo.getDbDetails());
					}
				}

				if (result == 1) {
					result_Status ="success";
				} else {
					result_Status ="failure";
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && (rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && (pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && (conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : inserthomeworDao  Ending");


		return result_Status;
	}

	public List<SmsVo> getHomeWorklistDao(UserLoggingsPojo custdetails,String locid, String clasid, String secid, String accid, String startdate, String enddate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorklistDao  Starting");
		List<SmsVo> homeworklist = new ArrayList<SmsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_HOMEWORK_LIST);
			pstmt.setString(1, locid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count++;
				
				SmsVo vo = new SmsVo();
				vo.setSlNo(count);
				vo.setHomeworkid(rs.getString("homeworkid"));
				vo.setLocationname(rs.getString("Location_Name"));
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("dateid")));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setSubjectname(rs.getString("subjectName"));
				vo.setDescription(rs.getString("description"));
				vo.setSmsStatus(rs.getString("sms_status"));
				vo.setCurrentdate(HelperClass.convertDatabaseToUI(rs.getString("createdate")));
				homeworklist.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorklistDao  Ending");
		return homeworklist;
	}

	public List<SmsVo> getHomeWorkSearchlistDao(UserLoggingsPojo custdetails,String locid, String clasid, String secid, String accid,String startdate, String enddate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorkSearchlistDao  Starting");

		List<SmsVo> searchlist = new ArrayList<SmsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count1=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			/*	pstmt = conn.prepareStatement(SmsUtilsConstants.SEARCH_HOMEWORK_LIST);*/

			String qry = "SELECT DISTINCT hm.homeworkid,hm.dateid,cls.classdetails_name_var,sec.classsection_name_var,sub.subjectName,hm.description ,cl.`Location_Name`,sa.`sms_status`,hm.createdate FROM campus_homework hm JOIN campus_classdetail cls ON cls.classdetail_id_int=hm.classid AND cls.`isActive`='Y'  AND cls.`locationId`=hm.`LocId`  JOIN campus_classsection sec ON sec.classsection_id_int=hm.sectionid AND sec.`locationId`=hm.`LocId` AND sec.`isActive`='Y' JOIN campus_subject sub ON sub.subjectID=hm.subjectid AND sub.`isActive`='Y'JOIN `campus_location` cl ON cl.`Location_Id`=hm.`LocId` AND cl.`isActive`='Y' JOIN `sms_audit` sa ON sa.`ServiceCode`=hm.`homeworkid` WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locid.equalsIgnoreCase("%%")) {
				map.put("hm.`LocId`",locid);
				vec.add("hm.`LocId`");
			}
			if(!clasid.equalsIgnoreCase("%%") ) {
				map.put("hm.`classid`",clasid);
				vec.add("hm.`classid`");
			}
			if(!secid.equalsIgnoreCase("%%")) {
				map.put("hm.`sectionid`",secid);
				vec.add("hm.`sectionid`");
			}
			if(!accid.equalsIgnoreCase("%%")) {
				map.put("hm.accyId",accid);
				vec.add("hm.accyId");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(hm.dateid between ? and ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(hm.dateid between ? and ?)"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count1++;
				SmsVo vo = new SmsVo();
				vo.setSlNo(count1);
				vo.setHomeworkid(rs.getString("homeworkid"));
				vo.setLocationname(rs.getString("Location_Name"));
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("dateid")));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSectionname(rs.getString("classsection_name_var"));
				vo.setSubjectname(rs.getString("subjectName"));
				vo.setDescription(rs.getString("description"));
				vo.setSmsStatus(rs.getString("sms_status"));
				vo.setCurrentdate(HelperClass.convertDatabaseToUI(rs.getString("createdate")));
				searchlist.add(vo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeWorkSearchlistDao  Ending");

		return searchlist;
	}



	public String deletehomeworkDao(SmsVo vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Starting");

		String status = "";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SmsUtilsConstants.DELETE_HOMEWORK);

			pstmt.setString(1, vo.getHomeworkid());

			count = pstmt.executeUpdate();

			if(count==1){

				status = "HomeWork Deleted Successfully";
			}
			else{
				status = "HomeWork Deleting Failed";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deletehomeworkDao  Ending");

		return status;
	}


	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsServiceDao(LstmsUpcomingMeetingVO vo,UserLoggingsPojo pojo,String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingListDetailsServiceDao  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;

		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		ResultSet rs2 = null;
		Connection conn = null;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_MEETING_LIST);
			pstmt.setString(1, schoolLocation);
			rs = pstmt.executeQuery();
			while(rs.next()){

				String studentid = rs.getString("studentname");
				String locid=rs.getString("locId");
				String accid=rs.getString("accyear");
				String[] categoryval=studentid.split(",");
				pstmt2 = conn.prepareStatement(CommunicateUtilConstants.GET_STUDENT_MEETING);

				for(int i=0;i<categoryval.length;i++){

					pstmt2.setString(1, categoryval[i]);
					pstmt2.setString(2, accid);
					pstmt2.setString(3, locid);
					rs2 = pstmt2.executeQuery();

					while(rs2.next()){
						count++;
						LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
						meetvo.setSno(count);
						meetvo.setMeetingid(rs.getString("meetingid"));
						meetvo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("meetingdate")));
						meetvo.setStartTime(rs.getString("starttime"));
						meetvo.setEndTime(rs.getString("endtime"));
						meetvo.setTitle(rs.getString("title"));
						meetvo.setVenueid(rs.getString("venuedetails"));
						meetvo.setSubjectName(rs.getString("subjectname"));
						meetvo.setSmsstatus(rs.getString("sms_status"));
						meetvo.setMeetingwith(rs2.getString("student_fname_var"));
						meetvo.setClassname(rs2.getString("classdetails_name_var"));
						meetvo.setSectionname(rs2.getString("classsection_name_var"));
						meetvo.setCreatedate(HelperClass.convertDatabaseToUI(rs.getString("createdate")));
						meetinglist.add(meetvo);

					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(rs2!=null && !(rs2.isClosed())){
					rs2.close();
				}

				if(pstmt2!=null && !(pstmt2.isClosed())){
					pstmt2.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingListDetailsServiceDao  Ending");
		return meetinglist;
	}




	public ArrayList<StudentInfoVO> getStudentListDetailsDao(String locId,String sectionid,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Starting");

		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection( userLoggingsVo);

			String accyear = HelperClass.getCurrentYearID( userLoggingsVo);

			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS);

			pstmt.setString(1, sectionid);
			pstmt.setString(2, accyear);
			pstmt.setString(3, locId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				StudentInfoVO student = new StudentInfoVO();
				student.setId(rs.getString("student_id_int"));
				student.setName(rs.getString("studentname"));
				studentlist.add(student);

			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentListDetailsDao  Ending");
		return studentlist;
	}


	public ArrayList<SubjectPojo> getSubjectListDetailsDao(String[] categoryval,SubjectPojo subpojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSubjectListDetailsDao  Starting");

		ArrayList<SubjectPojo> sec = new ArrayList<SubjectPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(SmsUtilsConstants.GET_SUBJECT_DETAILS);

			for(int i=0;i<categoryval.length;i++){

				pstmt.setString(1, categoryval[i]);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					SubjectPojo subject = new SubjectPojo();

					subject.setSubjectId(rs.getString("subjectID"));

					subject.setSubjectName(rs.getString("subjectName"));

					/*section.setClassid(rs.getString("classdetail_id_int"));
					section.setClassname(rs.getString("classdetails_name_var"));
					section.setSectionId(rs.getString("classsection_id_int"));
					section.setSectionName(rs.getString("classsection_name_var"));*/

					sec.add(subject);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSubjectListDetailsDao  Ending");

		return sec;
	}



	public String saveMeetingDetailsDao(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : saveMeetingDetailsDao  Starting");

		java.util.Date today = new java.util.Date();
		java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());
		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null ;
		ResultSet rs1 = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_SECTION_BY_STUID);

			if(meetingvo.getStudentname().length > meetingvo.getBalanceSMS()){
				
				result_Status = "insufficientSMSBalance";

			}else{
				for (int i = 0; i < meetingvo.getStudentname().length; i++) {

					pstmt1.setString(1, meetingvo.getStudentname()[i]);
					pstmt1.setString(2, meetingvo.getAccyearid());
					pstmt1.setString(3, meetingvo.getLocId());

					rs1 = pstmt1.executeQuery();

					while (rs1.next()) {
						String str = IDGenerator.getPrimaryKeyID("sms_meeting",userLoggingsVo);
						String date = HelperClass.convertUIToDatabase(meetingvo.getMeetingDate());
						pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_MEETING); 
						pstmt.setString(1, str);
						pstmt.setString(2, date);
						pstmt.setString(3, meetingvo.getTitle());
						pstmt.setString(4, meetingvo.getLocId());
						pstmt.setString(5, meetingvo.getClassid());
						pstmt.setString(6, rs1.getString("classsection_id_int"));
						pstmt.setString(7, meetingvo.getStartTime());
						pstmt.setString(8, meetingvo.getEndTime());
						pstmt.setString(9, meetingvo.getSubjectid());
						pstmt.setString(10,meetingvo.getStudentname()[i]);
						pstmt.setString(11,meetingvo.getDescription());
						pstmt.setString(12,meetingvo.getCreatedby());
						pstmt.setTimestamp(13,time_stamp);
						pstmt.setString(14, meetingvo.getAccyearid());

						result = pstmt.executeUpdate();
						if (result >0 ) {

							Runnable r = new SMSThread(str,date,meetingvo.getTitle(),meetingvo.getLog_audit_session(),"Interaction", meetingvo.getTitle(),userLoggingsVo,meetingvo.getSmsCharacters(), meetingvo.getLocId());
							new Thread(r).start();
							HelperClass.recordLog_Activity(meetingvo.getLog_audit_session(),"Interaction", meetingvo.getTitle(),"Insert",pstmt.toString(),userLoggingsVo);
						}
					}
				}
				if (result > 0) {
					result_Status ="success";
				} else {
					result_Status ="failure";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs1!=null && !(rs1.isClosed())){
					rs1.close();
				}
				if(pstmt1!=null && !(pstmt1.isClosed())){
					pstmt1.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : saveMeetingDetailsDao  Ending");
		return result_Status;
	}


	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails(UserLoggingsPojo custdetails,String schoolLocation) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getlatecomersListDetails  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_LATECOMERS_LIST);
			pstmt.setString(1, schoolLocation);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				count++;
				LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
				meetvo.setSno(count);
				meetvo.setLateId(rs.getString("Late_Comers_Code"));
				meetvo.setLateDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				meetvo.setLocId(rs.getString("Location_Name"));
				meetvo.setMeetingwith(rs.getString("studentname"));
				meetvo.setClassname(rs.getString("classdetails_name_var"));
				meetvo.setSectionname(rs.getString("classsection_name_var"));
				meetvo.setDescription(rs.getString("Description"));
				meetvo.setSmsstatus(rs.getString("sms_status"));
				meetvo.setCreatedate(HelperClass.convertDatabaseToUI(rs.getString("Created_Time")));
				meetinglist.add(meetvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getlatecomersListDetails  Ending");
		return meetinglist;
	}

	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : addlatecomers  Starting");



		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null ;

		ResultSet rs1 = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_SECTION_BY_STUID);
			
			if(meetingvo.getStudentname().length > meetingvo.getBalanceSMS()){
				result_Status = "insufficientSMSBalance";
			}else{
				
				for (int i = 0; i < meetingvo.getStudentname().length; i++) {
					pstmt1.setString(1, meetingvo.getStudentname()[i]);
					pstmt1.setString(2, meetingvo.getAccyearid());
					pstmt1.setString(3, meetingvo.getLocId());
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						String sectionval = rs1.getString("classsection_id_int");
						String str = IDGenerator.getPrimaryKeyID("latecomers_sms",userLoggingsVo);
						pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_LATE); 
						pstmt.setString(1, str);
						pstmt.setString(2, meetingvo.getLocId());
						pstmt.setString(3, meetingvo.getAccyearid());
						pstmt.setString(4, meetingvo.getStudentname()[i]);
						pstmt.setString(5, meetingvo.getClassid());
						pstmt.setString(6, sectionval);
						pstmt.setString(7, HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
						pstmt.setString(8, meetingvo.getDescription());
						pstmt.setString(9, meetingvo.getCreatedby());

						result = pstmt.executeUpdate();
						
						if (result >0) {
							Runnable r = new SMSThread(str,meetingvo.getMeetingDate(),"LateComers",meetingvo.getLog_audit_session(),"Interaction","Late Coming Students",userLoggingsVo,meetingvo.getLocId());
							new Thread(r).start();

							HelperClass.recordLog_Activity(meetingvo.getLog_audit_session(),"Interaction","Late Coming Students","Insert",pstmt.toString(),userLoggingsVo);
						}
					}
				}
				if (result == 1) {
					result_Status ="success";
				} else {
					result_Status ="failure";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs1!=null && (rs1.isClosed())){
					rs1.close();
				}
				if(pstmt1!=null && (pstmt1.isClosed())){
					pstmt1.close();
				}
				if(pstmt!=null && (pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && (conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : addlatecomers  Ending");
		return result_Status;
	}


	public ArrayList<UniformSmsPojo> getUniformListDetailsDao(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Starting");


		ArrayList<UniformSmsPojo> uniformlist = new ArrayList<UniformSmsPojo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_UNIFORM_LIST);

			rs = pstmt.executeQuery();
			while(rs.next()){
				count++;
				UniformSmsPojo pojo = new UniformSmsPojo();
				pojo.setSlNo(count);
				pojo.setUniformcode(rs.getString("Uniform_Code"));
				pojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				pojo.setLocId(rs.getString("Location_Name"));
				pojo.setStudentname(rs.getString("studentname"));
				pojo.setClassname(rs.getString("classdetails_name_var"));
				pojo.setSectionname(rs.getString("classsection_name_var"));
				pojo.setSmstext(rs.getString("Description"));
				uniformlist.add(pojo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getUniformListDetailsDao  Ending");

		return uniformlist;
	}



	public String storeUniformStudent(UniformSmsPojo upojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : storeUniformStudent  Starting");


		PreparedStatement pstmt = null;
		int result = 0;
		String value=null;
		Connection conn = null;
		PreparedStatement pstmt1 = null ;
		ResultSet rs1 = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_SECTION_BY_STUID);

			for (int i = 0; i < upojo.getStudentid().length; i++) {
				pstmt1.setString(1, upojo.getStudentid()[i]);
				pstmt1.setString(2, upojo.getAccYearId());
				pstmt1.setString(3, upojo.getLocId());

				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					String sectionval = rs1.getString("classsection_id_int");
					String str = IDGenerator.getPrimaryKeyID("uniform_sms",userLoggingsVo);
					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_UNIFORM); 

					pstmt.setString(1, str);
					pstmt.setString(2, upojo.getLocId());
					pstmt.setString(3, upojo.getAccYearId());
					pstmt.setString(4, upojo.getStudentid()[i]);
					pstmt.setString(5, upojo.getClassname());
					pstmt.setString(6, sectionval);
					pstmt.setString(7, HelperClass.convertUIToDatabase(upojo.getDate()));
					pstmt.setString(8, upojo.getCreatedby());
					pstmt.setString(9, upojo.getSmstext());

					//String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"),upojo.getSmstext());
					result = pstmt.executeUpdate();
					if (result >0) {
						Runnable r = new SMSThread(str,upojo.getDate(),"Uniform",upojo.getLog_audit_session(),"Interaction","Uniform",userLoggingsVo);
						new Thread(r).start();

						HelperClass.recordLog_Activity(upojo.getLog_audit_session(),"Interaction","Uniform","Insert",pstmt.toString(),userLoggingsVo);
					}
				}
			}
			if (result == 1) {
				value ="success";
			} else {
				value ="failure";
			}
					/*if(result==1){
						Runnable r = new SMSThread(str,meetingvo.getMeetingDate(),"LateComers",meetingvo.getLog_audit_session(),"Interaction","Late Coming Students",userLoggingsVo);
						new Thread(r).start();
						
						HelperClass.recordLog_Activity(upojo.getLog_audit_session(),"Interaction","Uniform","Insert",pstmt.toString(),userLoggingsVo);
						value="success";
					}
					else{
						value="failure";
					}
				}
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs1!=null && !(rs1.isClosed())){
					rs1.close();
				}
				if(pstmt1!=null && !(pstmt1.isClosed())){
					pstmt1.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in SmsDaoIMPL : storeUniformStudent  Ending");
		return value;
	}


	@Override
	public String insertOtherSMS(UniformSmsPojo upojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : insertOtherSMS  Starting");

		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 0;
		String result = null;
		try{
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection();
			for(int i = 0; i < upojo.getStudentid().length; i++)

			{
				pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);

				pstmt.setString(1, upojo.getStudentid()[i]);
				rs = pstmt.executeQuery();

				while(rs.next())
				{
					String classval = rs.getString("classdetail_id_int");
					String sectionval = rs.getString("classsection_id_int");
					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_OTHERS);

					pstmt.setString(1,IDGenerator.getPrimaryKeyID("sms_othersms"));

					pstmt.setString(2, upojo.getStudentid()[i]);

					pstmt.setString(3, classval);

					pstmt.setString(4, sectionval);

					pstmt.setString(5, HelperClass.convertUIToDatabase(upojo.getDate()));

					pstmt.setString(6, upojo.getSmstext());

					String sms = new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),upojo.getSmstext(),smsdetails);
					status = pstmt.executeUpdate();
				}
			}

			if (status == 1) {
				result = "success";
			} else {
				result = "failed";
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : insertOtherSMS  Ending");
		return null;
	}


	public String deleteHomeWork(String[] Id, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deleteHomeWork  Starting");

		Connection conn =null;
		PreparedStatement pstmt = null;
		int status = 0;
		String result = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SmsUtilsConstants.DELETE_HOME_WORK_DETAILS);

			for(int i=0;i<Id.length;i++)
			{
				pstmt.setString(1, Id[i]);
				status = pstmt.executeUpdate();
			}

			if (status == 1) {
				result ="success";
			} else {
				result ="failed";
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt !=null && !(pstmt.isClosed())) {

					pstmt.close();
				}  

				if (conn !=null && !(conn.isClosed())) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : deleteHomeWork  Ending");
		return result;
	}

	@Override
	public ArrayList<StudentInfoVO> getStudentMeetingAndEvent(UniformSmsPojo upojo, UserLoggingsPojo cpojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentMeetingAndEvent  Starting");

		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String value=null;

		try {
			conn = JDBCConnection.getSeparateConnection(cpojo);
			String accyear = HelperClass.getCurrentYearID(cpojo);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS);


			pstmt.setString(1, upojo.getLocId()); 
			pstmt.setString(2, accyear);
			pstmt.setString(3, upojo.getClassnameid());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentInfoVO student = new StudentInfoVO();
				student.setId(rs.getString("student_id_int"));
				student.setName(rs.getString("studentname"));
				studentlist.add(student);
			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentMeetingAndEvent  Ending");
		return studentlist;
	}


	public ArrayList<StudentInfoVO> getStudentMeetingAndEventBySections(UniformSmsPojo upojo, UserLoggingsPojo cpojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentMeetingAndEventBySections  Starting");

		ArrayList<StudentInfoVO> studentlist = new ArrayList<StudentInfoVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String value=null;

		try {
			conn = JDBCConnection.getSeparateConnection(cpojo);
			String accyear = HelperClass.getCurrentYearID(cpojo);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_BY_SECTION);

			for(int i=0;i<upojo.getSectionid().length;i++){
				pstmt.setString(1, upojo.getLocId()); 
				pstmt.setString(2, accyear);
				pstmt.setString(3, upojo.getClassnameid());
				pstmt.setString(4, upojo.getSectionid()[i]);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					StudentInfoVO student = new StudentInfoVO();
					student.setId(rs.getString("student_id_int"));
					student.setName(rs.getString("studentname"));
					studentlist.add(student);
				}
			}



		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getStudentMeetingAndEventBySections  Ending");
		return studentlist;
	}


	@Override
	public List<LstmsUpcomingMeetingVO> getSearchLatecomerList(String locationid, String accyear, String classid,String sectionid,UserLoggingsPojo userLoggingsVo,String startdate, String enddate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSearchLatecomerList  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count1=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*	pstmt = conn.prepareStatement(SmsUtilsConstants.GET_LATECOMERS_LIST_SEARCH);*/

			String qry = "SELECT DISTINCT lcs.Late_Comers_Code,lcs.Date,cl.Location_Name,CONCAT(cs.student_fname_var,' ',cs.student_lname_var)AS studentname,ccd.classdetails_name_var,ccs.classsection_name_var,lcs.Description,sa.`sms_status`,lcs.Created_Time FROM latecomers_sms lcs JOIN campus_location cl ON cl.Location_Id=lcs.LocId AND cl.`isActive`='Y' JOIN campus_classdetail ccd ON ccd.classdetail_id_int=lcs.Class_Code AND ccd.`locationId`=lcs.`LocId`AND ccd.`isActive`='Y' JOIN campus_classsection ccs ON ccs.classsection_id_int=lcs.Section_Code  AND ccs.`locationId`=lcs.`LocId` AND ccs.`isActive`='Y' JOIN campus_student_classdetails csc ON (csc.classdetail_id_int=lcs.Class_Code  AND csc.classsection_id_int= lcs.Section_Code)  JOIN campus_student cs ON cs.student_id_int=lcs.Student_Code JOIN campus_acadamicyear cay ON cay.acadamic_id=lcs.AccyearId  AND cay.`isActive`='Y' JOIN `sms_audit` sa ON sa.`ServiceCode`=lcs.`Late_Comers_Code` WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("`LocId`",locationid);
				vec.add("`LocId`");
			}
			if(!classid.equalsIgnoreCase("%%") ) {
				map.put("`Class_Code`",classid);
				vec.add("`Class_Code`");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("`Section_Code`",sectionid);
				vec.add("`Section_Code`");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("`AccyearId`",accyear);
				vec.add("`AccyearId`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(lcs.Date BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(lcs.Date BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);

			rs = pstmt.executeQuery();
			while(rs.next())
			{
				count1++;
				LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
				meetvo.setSno(count1);
				meetvo.setLateId(rs.getString("Late_Comers_Code"));
				meetvo.setLateDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				meetvo.setLocId(rs.getString("Location_Name"));
				meetvo.setMeetingwith(rs.getString("studentname"));
				meetvo.setClassname(rs.getString("classdetails_name_var"));
				meetvo.setSectionname(rs.getString("classsection_name_var"));
				meetvo.setDescription(rs.getString("Description"));
				meetvo.setSmsstatus(rs.getString("sms_status"));
				meetvo.setCreatedate(HelperClass.convertDatabaseToUI(rs.getString("Created_Time")));
				meetinglist.add(meetvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSearchLatecomerList  Ending");
		return meetinglist;

	}


	@Override
	public String SendOtherSMS(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : SendOtherSMS  Starting");

		PreparedStatement pstmt = null;
		String result_Status = "";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt1 = null;

		ResultSet rs1 = null;
		try {
			SmsIntegrationApiVO smsdetails = HelperClass.getSmsApiInfo();
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			for(int i = 0; i < meetingvo.getStudentname().length; i++)

			{
				pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_STUDENT_DETAILS_VAL);

				pstmt1.setString(1, meetingvo.getStudentname()[i]);
				rs1 = pstmt1.executeQuery();

				while(rs1.next())

				{

					String classval = rs1.getString("classdetail_id_int");

					String sectionval = rs1.getString("classsection_id_int");




					pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_OTHER_SMS);
					String str = IDGenerator.getPrimaryKeyID("sms_any_other_sms",userLoggingsVo);
					pstmt.setString(1, str);
					pstmt.setString(2, meetingvo.getStudentname()[i]);
					pstmt.setString(3, classval);
					pstmt.setString(4, sectionval);
					pstmt.setString(5, HelperClass.convertUIToDatabase(meetingvo.getMeetingDate()));
					pstmt.setString(6, meetingvo.getDescription());
					pstmt.setString(7, meetingvo.getAccid());
					pstmt.setString(8, meetingvo.getLocId());
					pstmt.setString(9, meetingvo.getCreatedby());
					result = pstmt.executeUpdate();
					if(result>0){
						/*String sms = new SendSMS().sendSMS(rs1.getString("student_contact_mobileno"), meetingvo.getDescription(),smsdetails);*/
						Runnable r = new SMSThread(str,meetingvo.getMeetingDate(),"OtherSMS",meetingvo.getLog_audit_session(),"Interaction","Other SMS",userLoggingsVo);
						new Thread(r).start();
						HelperClass.recordLog_Activity(meetingvo.getLog_audit_session(),"Interaction","Other SMS","Insert",pstmt.toString(),userLoggingsVo);
					}
				}
			}

			if (result == 1) {
				result_Status = "success";

			} else {
				result_Status = "failed";

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : SendOtherSMS  Ending");

		return result_Status;
	}


	@Override
	public List<LstmsUpcomingMeetingVO> getOtherSmsList(String locationid, String accyear, String classid, String sectionid,
			UserLoggingsPojo userLoggingsVo,String startdate, String enddate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in SmsDaoIMPL: OtherSMSList : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LstmsUpcomingMeetingVO> list = new ArrayList<LstmsUpcomingMeetingVO>();
		Connection conn = null;
		int count1=0;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			/*pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.GET_OTHER_SMS_DETAILS_FILTER);*/



			String qry = "SELECT DISTINCT sms.Date,sms.Description,CONCAT(cls.classdetails_name_var,'-',sec.classsection_name_var) AS classname,CONCAT(st.student_fname_var,' ',student_lname_var) NAME FROM sms_any_other_sms sms JOIN campus_classdetail cls ON cls.classdetail_id_int = sms.Class_Code JOIN campus_classsection sec ON sec.classsection_id_int = sms.Section_Code JOIN campus_student st ON st.student_id_int = sms.Student_Code WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("sms.`locId`",locationid);
				vec.add("sms.`locId`");
			}
			if(!classid.equalsIgnoreCase("%%") ) {
				map.put("sms.`Class_Code`",classid);
				vec.add("sms.`Class_Code`");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("sms.`Section_Code`",sectionid);
				vec.add("sms.`Section_Code`");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("sms.`academicYear`",accyear);
				vec.add("sms.`academicYear`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(sms.Date BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(sms.Date BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);

			rs=pstmt.executeQuery();
			while (rs.next()) {
				count1++;
				LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();

				vo.setSno(count1);
				vo.setTodate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				vo.setDescription(rs.getString("Description"));
				vo.setClassname(rs.getString("classname"));
				vo.setStudentName(rs.getString("name"));


				list.add(vo);
			}



		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
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
				+ " Control in SmsDaoIMPL: OtherSMSList : Ending");

		return list;
	}


	@Override
	public List<SuddenHolidaySMSVO> getSuddenHolidaySmsList(String locationid, String accyear, String classid,
			String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())	+ " Control in SmsDaoIMPL: getSuddenHolidaySmsList : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SuddenHolidaySMSVO> list = new ArrayList<SuddenHolidaySMSVO>();
		Connection conn = null;
		int count1=0;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*	pstmt = conn.prepareStatement(SuddenHolidaySqlUtil.GET_SUDDEN_HOLIDAY_DETAILS_FILTER);*/


			String qry = "SELECT DISTINCT sh.`smsDate`,cl.`Location_Name`,sh.`smsContent`,ccl.`classdetails_name_var`,ccs.`classsection_name_var`,sa.`sms_status`,sh.`smsCode`,sh.`created_time` FROM `sms_suddenholidays_details` sh JOIN `sms_suddenholidays_section` shs ON shs.`smsCode`= sh.`smsCode` JOIN `campus_classdetail` ccl ON ccl.`classdetail_id_int` = shs.`class_code` AND ccl.`locationId`=sh.`locationId` AND ccl.`isActive`='Y' JOIN `campus_classsection` ccs ON ccs.`classsection_id_int` = shs.`section_code` AND  ccs.`locationId`=sh.`locationId` AND ccs.`isActive`='Y' JOIN `campus_location` cl ON cl.`Location_Id` =sh.`locationId` AND cl.`isActive`='Y' JOIN `sms_audit` sa ON sa.`ServiceCode`=sh.`smsCode` WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("sh.`locationId`",locationid);
				vec.add("sh.`locationId`");
			}
			if(!classid.equalsIgnoreCase("%%") ) {
				map.put("shs.`class_code`",classid);
				vec.add("shs.`class_code`");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("shs.`section_code`",sectionid);
				vec.add("shs.`section_code`");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("shs.`acc_yearId`",accyear);
				vec.add("shs.`acc_yearId`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(sh.`smsDate` BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(sh.`smsDate` BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);

			rs=pstmt.executeQuery();
			while (rs.next()) {
				count1++;
				SuddenHolidaySMSVO vo = new SuddenHolidaySMSVO();
				vo.setSlNo(count1);
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("smsDate")));
				vo.setLocaname(rs.getString("Location_Name"));
				vo.setClassname(rs.getString("classdetails_name_var"));
				vo.setSecname(rs.getString("classsection_name_var"));
				vo.setSmstext(rs.getString("smsContent"));
				vo.setSmsstatus(rs.getString("sms_status"));
				vo.setSuddenholidayscode(rs.getString("smsCode"));
				vo.setCredate(HelperClass.convertDatabaseToUI(rs.getString("created_time")));

				list.add(vo);
			}


		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
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
				+ " Control in SmsDaoIMPL: getSuddenHolidaySmsList : Ending");

		return list;
	}


	@Override
	public List<AbsentsSMSPojo> getAbsentSmsList(String locationid, String accyear, String classid, String sectionid,
			UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getAbsentSmsList Starting");

		PreparedStatement ps_studentdetails = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<AbsentsSMSPojo> list = new ArrayList<AbsentsSMSPojo>();
		AbsentsSMSPojo absentsSMSPojo = null;
		int count1=0;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*ps_studentdetails = conn.prepareStatement(SuddenHolidaySqlUtil.GET_ABSENT_STUDENT_DETAILS_FILTER);*/


			String qry ="SELECT DISTINCT cl.Location_Name,se.classsection_name_var,ccd.classdetails_name_var,CONCAT(st.student_fname_var,' ',st.student_lname_var)AS NAME,de.ABSENT_CODE,de.ABSENT_DATE,de.ABSENT_CONTENT,sa.sms_status,de.CREATE_TIME FROM sms_absent_details de JOIN campus_location cl ON de.LocId=cl.Location_Id AND cl.`isActive`='Y' JOIN sms_absent_section sas ON sas.ABSENT_CODE=de.ABSENT_CODE JOIN campus_classdetail ccd ON ccd.classdetail_id_int = sas.CLASS_ID AND ccd.`locationId`=de.`LocId` AND ccd.`isActive`='Y' JOIN campus_classsection se ON se.classsection_id_int = sas.SECTION_CODE AND se.`locationId`=de.`LocId` AND se.`isActive`='Y' JOIN campus_acadamicyear cay ON cay.acadamic_id = de.AccyearId  AND cay.`isActive`='Y' JOIN campus_student_classdetails csc ON (csc.classdetail_id_int=sas.CLASS_ID AND csc.classsection_id_int=sas.SECTION_CODE) JOIN sms_absent_student sast ON sast.ABSENT_CODE=de.ABSENT_CODE JOIN campus_student st ON st.student_id_int = sast.STUDENT_ADM_NO JOIN sms_audit sa ON sa.ServiceCode=de.ABSENT_CODE WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("de.`LocId`",locationid);
				vec.add("de.`LocId`");
			}
			if(!classid.equalsIgnoreCase("%%") ) {
				map.put("sas.`CLASS_ID`",classid);
				vec.add("sas.`CLASS_ID`");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("sas.`SECTION_CODE`",sectionid);
				vec.add("sas.`SECTION_CODE`");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("de.`AccyearId`",accyear);
				vec.add("de.`AccyearId`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(de.`ABSENT_DATE` BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(de.`ABSENT_DATE` BETWEEN ? AND ?)"; /*stu.`student_id_no` like ? or*/
			}
			ps_studentdetails = conn.prepareStatement(finalquery);
			ps_studentdetails.setString(1, startdate);
			ps_studentdetails.setString(2, enddate);
			rs = ps_studentdetails.executeQuery();

			while (rs.next()) {
				count1++;
				absentsSMSPojo = new AbsentsSMSPojo();
				absentsSMSPojo.setSlNo(count1);
				absentsSMSPojo.setAbsentcode(rs.getString("ABSENT_CODE"));
				absentsSMSPojo.setLocId(rs.getString("Location_Name"));
				absentsSMSPojo.setDate(HelperClass.convertDatabaseToUI(rs.getString("ABSENT_DATE")));
				absentsSMSPojo.setStudentName(rs.getString("NAME"));
				absentsSMSPojo.setSection(rs.getString("classsection_name_var"));
				absentsSMSPojo.setClassname(rs.getString("classdetails_name_var"));
				absentsSMSPojo.setSmstext(rs.getString("ABSENT_CONTENT"));
				absentsSMSPojo.setStatus(rs.getString("sms_status"));
				absentsSMSPojo.setSenddate(HelperClass.convertDatabaseToUI(rs.getString("CREATE_TIME")));
				list.add(absentsSMSPojo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(ps_studentdetails!=null && (!ps_studentdetails.isClosed())){
					ps_studentdetails.close();
				}
				if(conn!=null && (!conn.isClosed())){
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
				+ " Control in SmsDaoIMPL : getAbsentSmsList ending");
		return list;
	}


	@Override
	public List<LstmsUpcomingMeetingVO> getMeetingEventSmsList(String locationid, String accyear, String classid,
			String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate,String title) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingEventSmsList  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;

		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		ResultSet rs2 = null;
		Connection conn = null;
		int count1=0;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*pstmt = conn.prepareStatement(SmsUtilsConstants.GET_MEETING_LIST_FILTER);*/
			String qry = "SELECT sm.`meetingid`,sm.`meetingdate`,sm.`title`,sm.`starttime`,sm.`endtime`,ccl.`classdetails_name_var`,ccs.`classsection_name_var`,CONCAT(stu.`student_fname_var`,' ',stu.`student_lname_var`)AS student,sa.`sms_status`,sm.createdate FROM  `sms_meeting` sm JOIN `campus_classdetail` ccl ON ccl.`classdetail_id_int`=sm.`classid` AND ccl.`locationId`= sm.`locId` AND ccl.`isActive`='Y' JOIN `campus_classsection` ccs ON ccs.`classsection_id_int`=sm.`sectionid` AND ccs.`locationId`= sm.`locId` AND ccs.`isActive`='Y' JOIN `campus_student` stu ON stu.`student_id_int`=sm.`studentname` AND stu.`student_status_var`='active' JOIN `campus_location` cl ON cl.`Location_Id`= sm.`locId` AND cl.`isActive`='Y' JOIN  `sms_audit` sa ON sa.`ServiceCode`=sm.`meetingid` and stu.`student_status_var`='active' WHERE";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!locationid.equalsIgnoreCase("%%")) {
				map.put("sm.`locId`",locationid);
				vec.add("sm.`locId`");
			}
			if(!classid.equalsIgnoreCase("%%") ) {
				map.put("sm.`classid`",classid);
				vec.add("sm.`classid`");
			}
			if(!sectionid.equalsIgnoreCase("%%")) {
				map.put("sm.`sectionid`",sectionid);
				vec.add("sm.`sectionid`");
			}
			if(!accyear.equalsIgnoreCase("%%")) {
				map.put("sm.`accyear`",accyear);
				vec.add("sm.`accyear`");
			}

			if(!title.equalsIgnoreCase("%%")) {
				map.put("sm.`title`",title);
				vec.add("sm.`title`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(sm.`meetingdate` between ? and ?)"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(sm.`meetingdate` between ? and ?)"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);

			rs = pstmt.executeQuery();
			while(rs.next()){
				count1++;
				LstmsUpcomingMeetingVO meetvo = new LstmsUpcomingMeetingVO();
				meetvo.setSno(count1);
				meetvo.setMeetingid(rs.getString("meetingid"));
				meetvo.setMeetingDate(HelperClass.convertDatabaseToUI(rs.getString("meetingdate")));
				meetvo.setStartTime(rs.getString("starttime"));
				meetvo.setEndTime(rs.getString("endtime"));
				meetvo.setTitle(rs.getString("title"));
				/*meetvo.setVenueid(rs.getString("venuedetails"));
					meetvo.setSubjectName(rs.getString("subjectname"));*/
				meetvo.setSmsstatus(rs.getString("sms_status"));
				meetvo.setMeetingwith(rs.getString("student"));
				meetvo.setClassname(rs.getString("classdetails_name_var"));
				meetvo.setSectionname(rs.getString("classsection_name_var"));
				meetvo.setCreatedate(HelperClass.convertDatabaseToUI(rs.getString("createdate")));
				meetinglist.add(meetvo);

			}


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(rs2!=null && !(rs2.isClosed())){
					rs2.close();
				}

				if(pstmt2!=null && !(pstmt2.isClosed())){
					pstmt2.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getMeetingListDetailsServiceDao  Ending");
		return meetinglist;
	}


	@Override
	public LstmsUpcomingMeetingVO getSmsDetails(UserLoggingsPojo userLoggingsVo,String accyear,String location) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSmsDetails  Starting");

		
		PreparedStatement pstmt = null,pstmt2=null;
		ResultSet rs = null,rs2=null;
		Connection conn = null;
		LstmsUpcomingMeetingVO vo = new LstmsUpcomingMeetingVO();
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `total_sms` , CASE WHEN `total_sent_sms` IS NULL THEN 0 ELSE `total_sent_sms` END total_sent_sms  , CASE WHEN `total_remaining` IS NULL THEN 0 ELSE `total_remaining` END `total_remaining` FROM  `campus_total_sms_count` sc  JOIN  `campus_location` l ON l.`Location_Id` = sc.`loc_id` AND l.`isActive` = 'Y'  WHERE sc.`loc_id` = ?");
			pstmt.setString(1, location);
			rs = pstmt.executeQuery();
			if(rs.next()){
				vo.setTotalsms(rs.getInt("total_sms"));
				vo.setSuccesscount(rs.getInt("total_sent_sms"));
				vo.setFailurecount(rs.getInt("total_remaining"));
			}
			
			pstmt2 = conn.prepareStatement("SELECT `total_sms_sent`,`sucess_count`,`failure_count` FROM `campus_daily_smscount` WHERE `loc_id`=? AND `date`= CURDATE()");
			pstmt2.setString(1, location);
			rs2=pstmt2.executeQuery();
			if(rs2.next()){
				vo.setTodayTotalSms(rs2.getInt("total_sms_sent"));
				vo.setTodaySuccessSms(rs2.getInt("sucess_count"));
				vo.setTodayFailureSms(rs2.getInt("failure_count"));
			}
		
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getSmsDetails  Ending");
		return vo;
	}


	@Override
	public ResultSet getClassIdName(String location_id,  UserLoggingsPojo userLoggingsVo,String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getClassIdName  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();
		PreparedStatement pstmt = null;

		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `classdetail_id_int` ,`classdetails_name_var` FROM `campus_classdetail` WHERE  `locationId` = ? ORDER BY  CAST(SUBSTRING(`classdetail_id_int`,4,LENGTH(classdetail_id_int)-3) AS SIGNED)");
			pstmt.setString(1, location_id );
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getClassIdName  Ending");

		return rs;
	}


	@Override
	public ResultSet getClassSmsCount(String location_id, UserLoggingsPojo userLoggingsVo, String accyear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getClassSmsCount  Starting");

		ArrayList<LstmsUpcomingMeetingVO> meetinglist = new ArrayList<LstmsUpcomingMeetingVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection( userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT  `classid` , COUNT(*) AS total   FROM  `campus_homework`  WHERE `LocId`= ? and accyId=?  GROUP BY `classid` ");
			pstmt.setString(1, location_id );
			pstmt.setString(2, accyear );
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {


			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getClassSmsCount  Ending");

		return rs;
	}


	@Override
	public ArrayList<SmsCountVO> getAbsentSmsCount(UserLoggingsPojo userLoggingsVo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getAbsentSmsCount  Starting");

		ArrayList<SmsCountVO> list = new ArrayList<SmsCountVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT  `CLASS_ID`,COUNT(*) as count   FROM  `sms_absent_section`  GROUP BY  `CLASS_ID`");
			rs = pstmt.executeQuery();

			while(rs.next()){
				SmsCountVO vo = new SmsCountVO ();
				vo.setClassId(rs.getString("CLASS_ID"));
				vo.setCount(rs.getString("count"));

				list.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getAbsentSmsCount  Ending");

		return list;
	}


	@Override
	public ArrayList<SmsCountVO> getEventSmsCount(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getEventSmsCount  Starting");

		ArrayList<SmsCountVO> list = new ArrayList<SmsCountVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection( userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT `Class_Code` ,COUNT(*) AS COUNT  FROM  `sms_any_other_sms`  GROUP BY  `Class_Code` ");
			rs = pstmt.executeQuery();

			while(rs.next()){
				SmsCountVO vo = new SmsCountVO ();
				vo.setClassId(rs.getString("Class_Code"));
				vo.setCount(rs.getString("COUNT"));

				list.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getEventSmsCount  Ending");

		return list;
	}


	@Override
	public ArrayList<SmsCountVO> getLateComeSmsCount(UserLoggingsPojo userLoggingsVo ,String location_id) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getEventSmsCount  Starting");

		ArrayList<SmsCountVO> list = new ArrayList<SmsCountVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT  `Class_Code`,COUNT(*) as count  FROM  `latecomers_sms`  WHERE  `LocId` = ?  GROUP BY  `Class_Code` ");
			pstmt.setString(1,  location_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				SmsCountVO vo = new SmsCountVO ();
				vo.setClassId(rs.getString("Class_Code"));
				vo.setCount(rs.getString("count"));
				list.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getEventSmsCount  Ending");

		return list;
	}


	@Override
	public ArrayList<SmsCountVO> getHomeworkSmsCount(UserLoggingsPojo userLoggingsVo, String location_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeworkSmsCount  Starting");



		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SmsCountVO> list = new ArrayList<SmsCountVO>();

		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT  `classid` , COUNT(*) AS total   FROM  `campus_homework`  WHERE `LocId`= ?  GROUP BY `classid` ");
			pstmt.setString(1, location_id );
			rs = pstmt.executeQuery();

			while(rs.next()){
				SmsCountVO vo = new SmsCountVO();
				vo.setClassId(rs.getString("classid"));
				vo.setCount(rs.getString("total"));
				list.add(vo);
			}		


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getHomeworkSmsCount  Ending");

		return list;
	}


	@Override
	public ArrayList<TermMasterVo> getTearm(String locId, UserLoggingsPojo custdetails,String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getTearm  Starting");

		ArrayList<TermMasterVo> list = new ArrayList<TermMasterVo>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT `termid`,`termname` FROM `campus_fee_termdetails` WHERE `locationId`=? and `accyear`=?");
			pstmt.setString(1, locId);
			pstmt.setString(2, academic_year);
			rs = pstmt.executeQuery();

			while(rs.next()){
				TermMasterVo vo = new TermMasterVo ();
			    vo.setTermid(rs.getString("termid"));
			    vo.setTermname(rs.getString("termname"));

				list.add(vo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getTearm  Ending");

		return list;
	}


	@Override
	public ArrayList<StudentInfoVO> getUnPaidStuList(String locId, String clsid, String termid,
			UserLoggingsPojo userLoggingsVo, String[] divid, String academic_year) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getTearm  Starting");

		ArrayList<StudentInfoVO> list = new ArrayList<StudentInfoVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status = "";
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_UNPAID_STUDENT_LIST);
			for(int i=0;i<divid.length;i++){
			pstmt.setString(1, academic_year);
			pstmt.setString(2, termid);
			pstmt.setString(3, locId);
			pstmt.setString(4, clsid);
			pstmt.setString(5, divid[i]);
			pstmt.setString(6, academic_year);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StudentInfoVO vo = new StudentInfoVO ();
			    vo.setStudentId(rs.getString("student_id_int"));
			    vo.setStudentnamelabel(rs.getString("studentname"));
			    list.add(vo);
			}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getTearm  Ending");

		return list;
	}


	@Override
	public String InserFeeSMS(FeeSMSVO vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL: InserFeeSMS : Starting");

		PreparedStatement ps_count = null;
		PreparedStatement ps_insert = null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;
		ResultSet rs=null;
		Connection conn = null;
		int count=0;
		int statuscount=0;
		int count1=0;
		String result=null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			if(vo.getStudeid().length > vo.getBalanceSMS()){
				result = "insufficientSMSBalance";
			}else{
				ps_insert = conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_SMSFEE,Statement.RETURN_GENERATED_KEYS);
				ps_insert.setString(1,vo.getLocId());
				ps_insert.setString(2, vo.getAccid());
				ps_insert.setString(3, vo.getClsId());
				ps_insert.setString(4, vo.getTerm());
				ps_insert.setString(5, vo.getSmstext());
				ps_insert.setString(6,HelperClass.convertUIToDatabase(vo.getDate()));
				ps_insert.setString(7, vo.getCreatedby());
				statuscount=ps_insert.executeUpdate();
				if(statuscount > 0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"SMS","Fee SMS","Insert",ps_insert.toString(),conn);
					int genkey = 0;
					ResultSet key = ps_insert.getGeneratedKeys();
					while(key.next()){
						genkey = key.getInt(1);
					}

					for(int i=0;i<vo.getDiv().length;i++){
						pst1 = conn.prepareStatement(StudentAttendanceSqlUtil.INSERT_SMSFEE_DIV);
						pst1.setInt(1, genkey);
						pst1.setString(2, vo.getDiv()[i]);
						count1=pst1.executeUpdate();
						if(count1>0){
							HelperClass.recordLog_Activity(vo.getLog_audit_session(),"SMS","Fee SMS","Insert",pst1.toString(),conn); 
						}
					}
					for(int j=0;j<vo.getStudeid().length;j++){
						pst=conn.prepareStatement(SQLUtilConstants.INSERT_STUDENT_SMS_FEE);
						String id=IDGenerator.getPrimaryKeyID("campus_sms_fee_students", userLoggingsVo);
						pst.setString(1, id);
						pst.setInt(2, genkey);
						pst.setString(3, vo.getStudeid()[j]);
						count=pst.executeUpdate();
						if(count>0){
							Runnable r = new SMSThread(id,vo.getDate(),"FeeSMS",vo.getLog_audit_session(),"SMS","FeeSMS",userLoggingsVo,vo.getLocId());
							new Thread(r).start();
							HelperClass.recordLog_Activity(vo.getLog_audit_session(),"SMS","Fee SMS","Insert",pst.toString(),conn);
							result="true";

						}
						else{
							result="false";
						}
					}

				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null&& (!rs.isClosed())) {
					rs.close();
				}
				if (ps_insert != null&& (!ps_insert.isClosed())) {
					ps_insert.close();
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
				+ " Control in SmsDaoIMPL: InserFeeSMS: Ending");

		return result;
	}


	@Override
	public ArrayList<FeeSMSVO> getFeeSMSList(UserLoggingsPojo pojo, String schoolLocation) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getFeeSMSList  Starting");

		ArrayList<FeeSMSVO> feesmslist = new ArrayList<FeeSMSVO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			String accyear = HelperClass.getCurrentYearID(pojo);
			pstmt = conn.prepareStatement(SmsUtilsConstants.GET_FEE_SMS_LIST);
			pstmt.setString(1, schoolLocation);
			pstmt.setString(2, accyear);
			rs = pstmt.executeQuery();

			while(rs.next()){
				count++;
				FeeSMSVO vo = new FeeSMSVO();
			    vo.setSlno(count);
			    vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
			    vo.setCreatedtime(HelperClass.convertDatabaseToUI(rs.getString("created_time")));
			    vo.setClsId(rs.getString("classdetails_name_var"));
			    vo.setDivId(rs.getString("classsection_name_var"));
			    vo.setStudentname(rs.getString("studentname"));
			    vo.setStatus(rs.getString("sms_status"));
			    feesmslist.add(vo);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.getStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getFeeSMSList  Ending");

		return feesmslist;
	}


	@Override
	public ArrayList<FeeSMSVO> FeeSmsList(UserLoggingsPojo pojo, FeeSMSVO vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : FeeSmsList  Starting");

		ArrayList<FeeSMSVO> feesmslist = new ArrayList<FeeSMSVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int count1=0;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
		   String qry = SQLUtilConstants.FILTERTION_FEE_SMS_LIST;
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;

			if(!vo.getLocId().equalsIgnoreCase("%%")) {
				map.put("csf.loc_id",vo.getLocId());
				vec.add("csf.loc_id");
			}
			if(!vo.getClsId().equalsIgnoreCase("%%") ) {
				map.put("csf.`class_id`",vo.getClsId());
				vec.add("csf.`class_id`");
			}
			if(!vo.getDivId().equalsIgnoreCase("%%")) {
				map.put("cscd.`classsection_id_int`",vo.getDivId());
				vec.add("cscd.`classsection_id_int`");
			}
			if(!vo.getAccid().equalsIgnoreCase("%%")) {
				map.put("csf.`accy_id`",vo.getAccid());
				vec.add("csf.`accy_id`");
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

			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql+" and "+"(csf.`date` between ? and ?)"; 
			}else {
				finalquery=qry+" "+"(csf.`date` between ? and ?)"; 
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, vo.getStartdate());
			pstmt.setString(2, vo.getEnddate());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count++;
				FeeSMSVO vo1 = new FeeSMSVO();
			    vo1.setSlno(count);
			    vo1.setDate(HelperClass.convertDatabaseToUI(rs.getString("date")));
			    vo1.setCreatedtime(HelperClass.convertDatabaseToUI(rs.getString("created_time")));
			    vo1.setClsId(rs.getString("classdetails_name_var"));
			    vo1.setDivId(rs.getString("classsection_name_var"));
			    vo1.setStudentname(rs.getString("studentname"));
			    vo1.setStatus(rs.getString("sms_status"));
			    feesmslist.add(vo1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : FeeSmsList  Ending");

		return feesmslist;
	}


	public int getBalanceSMSCount(UserLoggingsPojo pojo, String locationid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getBalanceSMSCount  Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int balance = 0;
		try {

			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_BALANCE_SMS_COUNT);
			pstmt.setString(1, locationid);
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				String totalSMS = rs.getString("total_sms");
				String totalSent = rs.getString("total_sent_sms");
				balance = rs.getInt("total_remaining"); 
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally{
			try {
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}

				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && !(conn.isClosed())){
					conn.close();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsDaoIMPL : getBalanceSMSCount  Ending");

		return balance;
	}





}
