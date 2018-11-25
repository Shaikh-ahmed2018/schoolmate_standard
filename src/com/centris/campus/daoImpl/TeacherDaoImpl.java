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

import java.util.ResourceBundle;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.admin.EmailContent;
import com.centris.campus.admin.SendMail;
import com.centris.campus.dao.TeacherDao;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TeacherUtilsConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.TeacherMappingClassesVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TeacherDaoImpl implements TeacherDao {
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ClientURL = res.getString("ClientURL");
	
	private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class);
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails(UserLoggingsPojo custid,AllTeacherDetailsVo obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.ALL_TEACHER_DETAILS);
			pstmt.setString(1, obj.getStatus());
            pstmt.setString(2, obj.getLocid());
            pstmt.setString(3, obj.getDeptid());
            pstmt.setString(4, obj.getDesgId());
            System.out.println("ALL_TEACHER_DETAILS -->>"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				
				System.out.println("AAAA"+rs.getString("Qualification")+"AAA");
				
				if(!rs.getString("Qualification").trim().equalsIgnoreCase("")){
					allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				}else{
					allTeacherDetailsVo.setQualification("-");
				}
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				if(!rs.getString("emailId").trim().equalsIgnoreCase("")){
					allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				}else{
					allTeacherDetailsVo.setEmail("-");
				}
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				allTeacherDetailsVo.setLocid(rs.getString("Loc_ID"));
				
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("totalSalary"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				allTeacherDetailsVo.setPannum(rs.getString("pannumber"));
				allTeacherDetailsVo.setBankname(rs.getString("bankname"));
				allTeacherDetailsVo.setRemark(rs.getString("reason"));
				getTeacherDetails.add(allTeacherDetailsVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails Ending");
		return getTeacherDetails;
	}
	
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails1 Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.ALL_TEACHER_DETAILS1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("CTC"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				allTeacherDetailsVo.setPannum(rs.getString("pannumber"));
				allTeacherDetailsVo.setBankname(rs.getString("bankname"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetails1 Ending");
		return getTeacherDetails;
	}
	
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String searchterm,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSearchTeacherDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.SEARCH_TEACHER_EMPLOYEMENT_DETAILS);
			
			pstmt.setString(1, searchterm+"%");
			pstmt.setString(2, searchterm+"%");
			pstmt.setString(3, searchterm+"%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs
						.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs
						.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				slNo++;
				
				if(rs.getString("BankAccNumber")==null || "".equalsIgnoreCase(rs.getString("BankAccNumber"))){
					
					allTeacherDetailsVo.setBankaccountNo("-");
					
				}else{
				
					allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
				}
				
				if(rs.getString("EmployeePfNo")==null || "".equalsIgnoreCase(rs.getString("EmployeePfNo"))){
					
					allTeacherDetailsVo.setPfnumber("-");
					
				}else{
				
					allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				}
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("totalSalary"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSearchTeacherDetails Ending");
		return getTeacherDetails;
	}

	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(AllTeacherDetailsVo obj,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : searchStaffDetails Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//String qry = TeacherUtilsConstants.SEARCH_TEACHER_DETAILS;
			String qry = "SELECT t.registerId,t.Abbreviative_Id,CONCAT(t.FirstName,' ',t.LastName) AS teacherName,CASE WHEN t.Qualification IS NULL THEN '-' ELSE t.Qualification END Qualification,d.designationName,t.mobileNo,t.TeacherID,CASE WHEN t.emailId IS NULL THEN '-' ELSE t.emailId END emailId,case when t.reason is null then '' when t.reason='' then ''else t.reason end reason  FROM campus_teachers t,campus_designation d WHERE t.designation=d.DesignationCode AND";
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!obj.getStatus().equalsIgnoreCase("%%")) {
				map.put("t.isActive",obj.getStatus());
				vec.add("t.isActive");
			}
			if(!obj.getLocid().equalsIgnoreCase("%%") ) {
				map.put("t.`Loc_ID`",obj.getLocid());
				vec.add("t.`Loc_ID`");
			}
			if(!obj.getDeptid().equalsIgnoreCase("%%")) {
				map.put("t.`department`",obj.getDeptid());
				vec.add("t.`department`");
			}
			if(!obj.getDesgId().equalsIgnoreCase("%%")) {
				map.put("t.`designation`",obj.getDesgId());
				vec.add("t.`designation`");
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
				finalquery=qry+" "+wheresql+" and "+"(CONCAT(t.FirstName,' ',t.LastName) LIKE ? OR t.mobileNo LIKE ?  OR t.Qualification LIKE ? OR d.designationName LIKE ? OR t.emailId LIKE ? OR t.registerId LIKE ? OR t.reason LIKE ?) order by t.registerId"; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry+" "+"(CONCAT(t.FirstName,' ',t.LastName) LIKE ? OR t.mobileNo LIKE ?  OR t.Qualification LIKE ? OR d.designationName LIKE ? OR t.emailId LIKE ? OR t.registerId LIKE ? OR t.reason LIKE ?) order by t.registerId"; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			pstmt.setString(1, obj.getSearchTerm() + "%%");
			pstmt.setString(2, obj.getSearchTerm()+ "%%");
			pstmt.setString(3, obj.getSearchTerm() + "%%");
			pstmt.setString(4, obj.getSearchTerm() + "%%");
			pstmt.setString(5, obj.getSearchTerm() + "%%");
			pstmt.setString(6, obj.getSearchTerm() + "%%");
			pstmt.setString(7, obj.getSearchTerm() + "%%");
			System.out.println("finalquery is "+pstmt);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				
				if(!rs.getString("Qualification").equalsIgnoreCase("")){
					allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				}else{
					allTeacherDetailsVo.setQualification("-");
				}
				
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				if(!rs.getString("emailId").equalsIgnoreCase("")){
					allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				}else{
					allTeacherDetailsVo.setEmail("-");
				}
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				if(!rs.getString("reason").equalsIgnoreCase("")){
					allTeacherDetailsVo.setRemark(rs.getString("reason"));
				}else{
					allTeacherDetailsVo.setRemark("-");
				}
				slNo++; 
				getTeacherDetails.add(allTeacherDetailsVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : searchStaffDetails Ending");
		return getTeacherDetails;

	}

	public boolean deleteStaffDetails(String[]  teachercode,TeacherForm obj,String button,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : deleteStaffDetails Starting");

		Connection conn = null;
		boolean status = false;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			for(int i=0;i<teachercode.length;i++){
				
			pstmt = conn.prepareStatement(TeacherUtilsConstants.DELETE_TEACHER_DETAILS);
			pstmt.setString(1, obj.getStatus());
			pstmt.setString(2, obj.getReason());
			pstmt.setString(3, teachercode[i]);
			
			count = pstmt.executeUpdate();
			if (count > 0) {
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration",button,pstmt.toString(),userLoggingsVo);
				status = true;
				pstmt = conn.prepareStatement(TeacherUtilsConstants.DELETE_TEACHER_DETAILS_USERTABLE);
				pstmt.setString(1, obj.getStatus());
				pstmt.setString(2, obj.getReason());
				pstmt.setString(3, teachercode[i]);
				pstmt.executeUpdate();
				
				
			} else {
				status = true;
			}

		} 
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : deleteStaffDetails Ending");
		return status;
	}

	public List<ViewallSubjectsVo> getSubjects(String classId, String locationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getSubjects  Starting");
		ResultSet resultset = null;
		ArrayList<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_SUBJECTS);
			pstmt.setString(1, classId);
			pstmt.setString(2, locationId);
			
			System.out.println("GET_SUBJECTS -->> "+pstmt);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				ViewallSubjectsVo viewallSubjectsVo = new ViewallSubjectsVo();
				viewallSubjectsVo
						.setSubjectid(resultset.getString("subjectID"));
				viewallSubjectsVo.setSubjectname(resultset
						.getString("subjectName"));
				subjectlist.add(viewallSubjectsVo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getSubjects  Ending");

		return subjectlist;
	}

	public boolean checkUsername(String username, String teacherId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkUsername  Starting");
		boolean status = false;
		int count = 0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_TEACHER_COUNT);
			pstmt.setString(1, username);
			pstmt.setString(2, teacherId);
			resultset = pstmt.executeQuery();
			System.out.println("prepared statement " + pstmt);

			while (resultset.next()) {
				count = resultset.getInt(1);
			}

			if (count > 0) {
				status = true;

			} else {

				status = false;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkUsername  Ending");
		return status;
	}

	public boolean checkMail(String mailid, String teacherId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkMail  Starting");
		boolean status = false;
		int count = 0;
		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(TeacherUtilsConstants.GET_TEACHERMAIL_COUNT);
			pstmt.setString(1, mailid);
			pstmt.setString(2, teacherId);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				count = resultset.getInt(1);
			}
			if (count > 0) {
				status = true;
			} else {
				status = false;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkMail  Ending");
		return status;
	}

	public boolean teacherregister(TeacherRegistrationPojo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : teacherregister  Starting");
		
		boolean result = false;
	

		ResultSet resultsetcount = null;
		PreparedStatement pstmtcount = null;

		Connection conn = null;

		PreparedStatement  ps_insertuser= null;
		
	

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			

			String genPassword = HelperClass.genaratePasswordForTeacher(obj);

			System.out.println("genPassword:::::"+genPassword);

			pstmtcount = conn.prepareStatement(TeacherUtilsConstants.CHECK_TACHER_COUNT_);
			pstmtcount.setString(1, obj.getUsername());
			pstmtcount.setString(2, obj.getDept());
			pstmtcount.setString(3, obj.getDesignation());
			pstmtcount.setString(4, obj.getTeachermobno());
			pstmtcount.setString(5, obj.getDateofbirth());
			pstmtcount.setString(6, obj.getJoiningdate());
			resultsetcount = pstmtcount.executeQuery();

			System.out.println("DIOMPL obj.getDocument1(): "+obj.getDocument1());

			while (resultsetcount.next()) {
				int count = resultsetcount.getInt(1);

				if (count > 0) {
					
					result = false;
					return result;
				}else {

					pstmt = (PreparedStatement) JDBCConnection.getStatement(TeacherUtilsConstants.INSERT_TEACHER,userLoggingsVo);

					pstmt.setString(1, obj.getTeacherId());
					pstmt.setString(2, obj.getTfastname());
					pstmt.setString(3, obj.getTlastname());
					pstmt.setString(4, obj.getTqualification());
					pstmt.setString(5, obj.getPresentadd());
					pstmt.setString(6, obj.getTeachermobno());
					pstmt.setString(7, obj.getUsername());
					pstmt.setString(8, obj.getTeacheremail());
					pstmt.setString(9, obj.getProfilepath());
					pstmt.setString(10, obj.getImgpath());
					pstmt.setString(11, obj.getDateofbirth());
					pstmt.setString(12, obj.getJoiningdate());
					pstmt.setString(13, obj.getDesignation());
					pstmt.setString(14, obj.getIdproof());
					pstmt.setString(15, obj.getTeachingtype());
					pstmt.setString(16, obj.getDept());
					pstmt.setString(17, obj.getGender());
					pstmt.setString(18, obj.getBankName());
					pstmt.setString(19, obj.getAccountNumber());
					pstmt.setString(20, obj.getPanNumber());
					pstmt.setString(21, obj.getBlood());
					pstmt.setString(22, obj.getFathername());
					pstmt.setString(23, obj.getMothername());
					pstmt.setString(24, obj.getPermanentadd().trim());
					pstmt.setString(25, obj.getCreatedby());
					pstmt.setTimestamp(26, HelperClass.getCurrentTimestamp());
					pstmt.setString(27, obj.getRegsitrationNo());
					pstmt.setString(28, genPassword);
					pstmt.setString(29, obj.getRole());
					pstmt.setString(30, obj.getUsertype());
					pstmt.setString(31, obj.getDocument1());
					pstmt.setString(32, obj.getDocument2());
					pstmt.setString(33, obj.getDocument3());
					pstmt.setString(34, obj.getDocument4());
					pstmt.setString(35, obj.getDocument5());
					pstmt.setString(36,  obj.getReportingTo());

					if(obj.getIs_student_studying()=="Y"||obj.getIs_student_studying().equalsIgnoreCase("Y"))
					{
						pstmt.setString(37, obj.getIs_student_studying());
						pstmt.setString(38, obj.getStudentName());
						pstmt.setString(39, obj.getStudent_admission_id());
					}else
					{
						pstmt.setString(37, obj.getIs_student_studying());
						pstmt.setString(38, "");
						pstmt.setString(39, "");
					}
					pstmt.setString(40, obj.getFatherMobile());
					pstmt.setString(41, obj.getMotherMobile());
					pstmt.setString(42, obj.getAbbreviate());
					pstmt.setString(43, obj.getAadhaarnumber());
					pstmt.setString(44, obj.getMaritalstatus());
					pstmt.setString(45, obj.getSpousename());
					pstmt.setString(46, obj.getSpouseMobile());
					pstmt.setString(47, obj.getSchoolName());
					System.out.println("INSERT STAFF::::"+pstmt);
					result = pstmt.execute();
					System.out.println("//////////"+result);
					result = true;

					if(result){
						HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration","Insert",pstmt.toString(),userLoggingsVo);
						System.out.println("999999999999999"+obj.getRole());
						if(obj.getRole()!=null && !obj.getRole().trim().equalsIgnoreCase("")){
						System.out.println("000000000"+obj.getRole());
						String userId=IDGenerator.getPrimaryKeyID("campus_user",userLoggingsVo); 
						ps_insertuser=conn.prepareStatement(TeacherUtilsConstants.INSERT_USER_DETAILS);
						ps_insertuser.setString(1, userId);
						ps_insertuser.setString(2, obj.getTeacherId());
						ps_insertuser.setString(3,obj.getUsername().trim());
						ps_insertuser.setString(4,genPassword);
						ps_insertuser.setString(5, obj.getRole());
						ps_insertuser.setString(6, obj.getUsertype());
						ps_insertuser.setString(7, obj.getCreatedby());
						ps_insertuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
						ps_insertuser.setString(9, obj.getSchoolName());
						ps_insertuser.setString(10, obj.getCustid());
						
						System.out.println("insert user :: "+ps_insertuser);
					    count=ps_insertuser.executeUpdate();
					    if(count>0){
					    	HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration","Insert",ps_insertuser.toString(),userLoggingsVo);
					    }
						}
						
					}
					
					
					if (result) {
					System.out.println("55555555555"+obj.getTeacheremail());
						//String url = getServerUrlFromBD(conn);
						String url=ClientURL;
						// String url="www.google.com";
						if(obj.getTeacheremail()!= null && !obj.getTeacheremail().trim().equalsIgnoreCase("") && obj.getRole()!=null && !obj.getRole().trim().equalsIgnoreCase("")){
						String set = sendEmailToEmployee(obj.getUsername(),	obj.getTeacheremail(), genPassword, url);
						result = true;
					}
					}
					
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultsetcount != null && (!resultsetcount.isClosed())) {
					resultsetcount.close();
				}
				if (pstmtcount != null && (!pstmtcount.isClosed())) {
					pstmtcount.close();
				}
				if (ps_insertuser != null && (!ps_insertuser.isClosed())) {
					ps_insertuser.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : teacherregister  Ending");
		return result;
	}

	public String getServerUrlFromBD(Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getServerUrlFromBD  Starting");
		
		String url = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement(TeacherUtilsConstants.GET_SERVER_URL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				url = rs.getString("URL");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		 finally {

				try {

					if (rs != null
							&& (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null
							&& (!pstmt.isClosed())) {
						pstmt.close();
					}
					if (connection != null && (!connection.isClosed())) {
						connection.close();
					}
				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.getStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getServerUrlFromBD  Ending");
		
		return url;
	}

	public String sendEmailToEmployee(String username, String email,
			String password, String url) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : sendEmailToEmployee  Starting");
		
		try {
			EmailContent em = new EmailContent();
			 String[] mails = { email };

			System.out.println("email id in s send employee " + email);
			em.setMailids(mails);
			em.setUsername(username);
			em.setPassword(password);
			em.setSubject("Staff Registration Confirmation Mail");
			em.setContent("Greetings from E-CAMPUS PRO...  \n"
					+ " Thank you for Registering with us \n"
					+ "Login Credentials are : \n" + "URL		: " + url + "\n"
					+ "User Name		: " + username + "\n" + "Password	: "
					+ password + "\n" + "Have a nice day\n\n\n" + "Regards \n"
					+ "E-CAMPUS PRO \n"
					+ "---------------------------------------------------\n"
					+ "This is System generated mail, Please do not reply."
					+ "\n");
			new SendMail().sendMail(em);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : sendEmailToEmployee  Ending");
		return "sent";
	}

	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getTeacherDetails  Starting");
	
		ResultSet resultset = null;
		Connection conn = null;
		TeacherRegistrationPojo obj1 = null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_SINGLE_TEACHER_DEATILS);
			pstmt.setString(1, obj.getTeacherId());
			resultset = pstmt.executeQuery();
			System.out.println("TEACHER "+pstmt);
			while (resultset.next()) {
				obj1 = new TeacherRegistrationPojo();
				obj1.setTeacherId(obj.getTeacherId());
				obj1.setTfastname(resultset.getString("FirstName"));
				obj1.setTlastname(resultset.getString("LastName"));
				obj1.setRegsitrationNo(resultset.getString("registerId"));
				obj1.setDept(resultset.getString("department"));
				obj1.setDesignation(resultset.getString("designation"));
				obj1.setTqualification(resultset.getString("qualification"));
				obj1.setJoiningdate(HelperClass.convertDatabaseToUI(resultset.getString("dateofJoining")));
				obj1.setTeachingtype(resultset.getString("teachingType"));
				obj1.setGender(resultset.getString("gender"));
				obj1.setBankName(resultset.getString("bankname"));
				obj1.setAccountNumber(resultset.getString("accountnumber"));
				obj1.setPanNumber(resultset.getString("pannumber"));
				obj1.setDateofbirth(HelperClass.convertDatabaseToUI(resultset.getString("dateofBirth")));
				obj1.setTeachermobno(resultset.getString("mobileNo"));
				obj1.setTeacheremail(resultset.getString("emailId"));
				obj1.setBlood(resultset.getString("bloodgroup"));
				obj1.setImgpath(resultset.getString("imagePath"));
				obj1.setProfilepath(resultset.getString("profilePath"));
				obj1.setIdproof(resultset.getString("idProofPath"));
				obj1.setFathername(resultset.getString("fathername"));
				obj1.setMothername(resultset.getString("mothername"));
				obj1.setPresentadd(resultset.getString("presentAddress").trim());
				obj1.setPermanentadd(resultset.getString("permanentAddress").trim());
				obj1.setUsername(resultset.getString("username"));
				obj1.setStatus("update");
				obj1.setRole(resultset.getString("role"));
				obj1.setUsertype(resultset.getString("userType"));
				obj1.setDocument1(resultset.getString("document1"));
				obj1.setDocument2(resultset.getString("document2"));
				obj1.setDocument3(resultset.getString("document3"));
				obj1.setDocument4(resultset.getString("document4"));
				obj1.setDocument5(resultset.getString("document5"));
				obj1.setReportingTo(resultset.getString("reportingTo"));
				obj1.setIs_student_studying(resultset.getString("is_student_studying"));
				obj1.setStudentName(resultset.getString("studentName"));
				obj1.setStudent_admission_id(resultset.getString("student_admission_id"));
				obj1.setFatherMobile(resultset.getString("fatherMobile"));
				obj1.setMotherMobile(resultset.getString("motherMobile"));
				
				obj1.setAbbreviate(resultset.getString("Abbreviative_Id"));
				obj1.setAadhaarnumber(resultset.getString("aadhaarnumber"));
				obj1.setMaritalstatus(resultset.getString("maritalstatus"));
				obj1.setSpouseMobile(resultset.getString("spouseMobile"));
				obj1.setSpousename(resultset.getString("spousename"));
				/*obj1.setUsertype(resultset.getString("type"));*/
				obj1.setSchoolName(resultset.getString("Location_Name"));
				obj1.setSchoolNameId(resultset.getString("Loc_ID"));
				
				

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getTeacherDetails  Ending");
		return obj1;
	}

	public String teacherUpdate(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : teacherUpdate Starting");

		String result = "";
		ResultSet resultsetcount = null;
		PreparedStatement pstmtcount = null;
		Connection conn = null;
		PreparedStatement  ps_updateuser= null;
		int count=0;
		String genPassword = HelperClass.genaratePasswordForTeacher(obj);
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TeacherDaoImpl : teacherregister  Starting");

			pstmt = (PreparedStatement) JDBCConnection.getStatement(TeacherUtilsConstants.UPDATE_TEACHER,userLoggingsVo);

			
			pstmt.setString(1,  obj.getTfastname());
			pstmt.setString(2,  obj.getTlastname());
			pstmt.setString(3,  obj.getTqualification());
			pstmt.setString(4,  obj.getPresentadd());
			pstmt.setString(5,  obj.getTeachermobno());
			pstmt.setString(6,  obj.getUsername());
			pstmt.setString(7,  obj.getTeacheremail());			
			pstmt.setString(8, obj.getProfilepath());
			pstmt.setString(9, obj.getImgpath());
			pstmt.setString(10, HelperClass.convertUIToDatabase(obj.getDateofbirth()));
			pstmt.setString(11, HelperClass.convertUIToDatabase(obj.getJoiningdate()));
			pstmt.setString(12, obj.getDesignation());
			pstmt.setString(13, obj.getIdproof());
			pstmt.setString(14, obj.getTeachingtype());
			pstmt.setString(15, obj.getDept());
			pstmt.setString(16, obj.getGender());
			pstmt.setString(17, obj.getBlood());
			pstmt.setString(18, obj.getFathername());
			pstmt.setString(19, obj.getMothername());
			pstmt.setString(20, obj.getPermanentadd());
			pstmt.setString(21, obj.getCreatedby());
			pstmt.setTimestamp(22, HelperClass.getCurrentTimestamp());
			pstmt.setString(23, obj.getRole());
			pstmt.setString(24, obj.getReportingTo());
			pstmt.setString(25, obj.getBankName());
			pstmt.setString(26, obj.getAccountNumber());
			pstmt.setString(27, obj.getPanNumber());
			pstmt.setString(28, obj.getDocument1());
			pstmt.setString(29, obj.getDocument2());
			pstmt.setString(30, obj.getDocument3());
			pstmt.setString(31, obj.getDocument4());
			pstmt.setString(32, obj.getDocument5());
			
			if(obj.getIs_student_studying()=="Y"||obj.getIs_student_studying().equalsIgnoreCase("Y"))
			{
			pstmt.setString(33, obj.getIs_student_studying());
			pstmt.setString(34, obj.getStudentName());
			pstmt.setString(35, obj.getStudent_admission_id());
			}
			else
			{
				pstmt.setString(33, obj.getIs_student_studying());
				pstmt.setString(34, "");
				pstmt.setString(35, "");
			}
			
			pstmt.setString(36, obj.getFatherMobile());
			pstmt.setString(37, obj.getMotherMobile());
			pstmt.setString(38, obj.getAbbreviate());
			pstmt.setString(39, obj.getAadhaarnumber());
			pstmt.setString(40, obj.getMaritalstatus());
			pstmt.setString(41, obj.getSpousename());
			pstmt.setString(42, obj.getSpouseMobile());
			pstmt.setString(43, obj.getSchoolName());
			pstmt.setString(44, obj.getUsertype());
			pstmt.setString(45, "-");
			pstmt.setString(46, obj.getTeacherId());
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println(" update:::::: "+pstmt);
			
			int update=pstmt.executeUpdate();
			
			if(update>0){
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration","Update",pstmt.toString(),userLoggingsVo);
				System.out.println("1111111111111"+obj.getRole());
				if(obj.getRole()!=null && !obj.getRole().trim().equalsIgnoreCase("")){
					int teachercount=checkteachercount(obj.getTeacherId(),userLoggingsVo);
					System.out.println("2222222222"+teachercount);
					if(teachercount==0){
						System.out.println("000000000"+obj.getRole());
						String userId=IDGenerator.getPrimaryKeyID("campus_user",userLoggingsVo); 
						ps_updateuser=conn.prepareStatement(TeacherUtilsConstants.INSERT_USER_DETAILS);
						ps_updateuser.setString(1, userId);
						ps_updateuser.setString(2, obj.getTeacherId());
						ps_updateuser.setString(3,obj.getUsername().trim());
						ps_updateuser.setString(4,genPassword);
						ps_updateuser.setString(5, obj.getRole());
						ps_updateuser.setString(6, obj.getUsertype());
						ps_updateuser.setString(7, obj.getCreatedby());
						ps_updateuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
						ps_updateuser.setString(9, obj.getSchoolName());
						ps_updateuser.setString(10, obj.getCustid());
					
						System.out.println("insert user :: "+ps_updateuser);
						count=ps_updateuser.executeUpdate();
						if(count>0){
							
							HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration","Insert",ps_updateuser.toString(),userLoggingsVo);
						}
					}
					else{
				System.out.println("obj.getRole() for UPDATE USER DETAILS "+obj.getRole());
				ps_updateuser= conn.prepareStatement(TeacherUtilsConstants.UPDATE_USER_DETAILS);
				ps_updateuser.setString(1, obj.getUsername().trim());
				ps_updateuser.setString(2, obj.getRole());
				ps_updateuser.setString(3, obj.getUsertype());
				ps_updateuser.setString(4, obj.getCreatedby());
				ps_updateuser.setTimestamp(5, HelperClass.getCurrentTimestamp());
				ps_updateuser.setString(6, obj.getTeacherId());
				System.out.println("update user :: "+ps_updateuser);
				count=ps_updateuser.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Staff","StaffRegistration","Update",ps_updateuser.toString(),userLoggingsVo);
				}
				
			 }
					
			}
				else if(obj.getTeacheremail()== null || obj.getTeacheremail().trim().equalsIgnoreCase("") || obj.getRole()==null || obj.getRole().trim().equalsIgnoreCase("")){
					pstmtcount= conn.prepareStatement(TeacherUtilsConstants.DELETE_USER_DETAILS);
					pstmtcount.setString(1, obj.getTeacherId());
					pstmtcount.executeUpdate();
			  }
				
			}
			
			/*if(update>0){
				if(obj.getTeacheremail()== null || obj.getTeacheremail().trim().equalsIgnoreCase("") || obj.getRole()==null || obj.getRole().trim().equalsIgnoreCase("")){
					pstmtcount= conn.prepareStatement(TeacherUtilsConstants.DELETE_USER_DETAILS);
					pstmtcount.setString(1, obj.getTeacherId());
					pstmtcount.executeUpdate();
			  }
			}*/
			
			if (update>0) {
				System.out.println("55555555555"+obj.getTeacheremail());
					//String url = getServerUrlFromBD(conn);
					String url=ClientURL;
					// String url="www.google.com";
					if(obj.getTeacheremail()!= null && !obj.getTeacheremail().trim().equalsIgnoreCase("") && obj.getRole()!=null && !obj.getRole().trim().equalsIgnoreCase("")){
					String set = sendEmailToEmployee(obj.getUsername(),	obj.getTeacheremail(), genPassword, url);
					result = "true";
				}
				}
			
			
			if(update > 0)
			{
			
				result="true";
			}
			else{
				result="false";
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultsetcount != null && (!resultsetcount.isClosed())) {
					resultsetcount.close();
				}
				if (pstmtcount != null && (!pstmtcount.isClosed())) {
					pstmtcount.close();
				}
				
				if (ps_updateuser != null && (!ps_updateuser.isClosed())) {
					ps_updateuser.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : teacherUpdate  Ending");
		return result;

	}

	public String checkRegistrationNo(String registratn, String teacherId, String locId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Starting");

		int count = 0;
		String value=null;
		String result=null;
		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_REGISTRAION_COUNT);
			pstmt.setString(1, registratn);
			pstmt.setString(2, teacherId);
			pstmt.setString(3, locId);
			resultset = pstmt.executeQuery();
			//System.out.println("prepared statement " + pstmt);

			while (resultset.next()) {
				count = resultset.getInt(1);
				value=resultset.getString("isActive");
			}
			
			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
			}
			else if(count > 0 && value.equalsIgnoreCase("Y")) {
				result="true";
			}
             
			else{
				result="false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkRegistrationNo  Ending");
		return result;
	}

	@Override
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingClasses  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_MAPPING_CLASSES);
			mappingpstmt.setString(1, teacherID);
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				mappingvo.setMappintID(resultset.getString("CTCode"));
				mappingvo.setClassId(resultset.getString("ClassCode"));
				mappingvo.setSectionId(resultset.getString("SectionCode"));
				mappingvo.setTeacherID(teacherID);
				
				mappinglist.add(mappingvo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getMappingClasses  Ending");
		
		return mappinglist;
	}

	@Override
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID, String classId,String SectionID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappedSection  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();
		
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_UNMAPPING_SECTION);
			mappingpstmt.setString(1, classId);
			mappingpstmt.setString(2, teacherID);
			mappingpstmt.setString(3, classId);
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				
				mappingvo.setSectionId(resultset.getString("classsection_id_int"));
				mappingvo.setSectionname(resultset.getString("classsection_name_var"));
				
				mappinglist.add(mappingvo);
			}
			
			/*sectionpstmt =conn.prepareStatement(TeacherUtilsConstants.GET_SECTION_NAME);
			sectionpstmt.setString(1, SectionID);
			
			sectionRs=sectionpstmt.executeQuery();
			
			while(sectionRs.next()){
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				
				mappingvo.setSectionId(SectionID);
				mappingvo.setSectionname(sectionRs.getString("classsection_name_var"));
				
				mappinglist.add(mappingvo);
			}*/
			

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getMappedSection  Ending");
		
		return mappinglist;
	}
	
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getMappingSubjects  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<TeacherMappingClassesVo> mappinglist=new ArrayList<TeacherMappingClassesVo>();

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_MAPPING_SUBJECTS);
			mappingpstmt.setString(1, teacherID);
			
			
			resultset = mappingpstmt.executeQuery();
			System.out.println("MAPPING SUBJECTS "+mappingpstmt);

			while (resultset.next()) {
				
				TeacherMappingClassesVo mappingvo=new TeacherMappingClassesVo();
				mappingvo.setClassId(resultset.getString("classID"));
				mappingvo.setSubjectId(resultset.getString("subjectID"));
				mappingvo.setTeacherID(teacherID);
				
				mappinglist.add(mappingvo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getMappingSubjects  Ending");
		
		return mappinglist;
	}
	
	public ArrayList<AllTeacherDetailsVo> reportingToList(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : reportingToList  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<AllTeacherDetailsVo> reportingToList=new ArrayList<AllTeacherDetailsVo>();

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.REPORTING_TO_LIST);
			
			
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				AllTeacherDetailsVo teacherVo=new AllTeacherDetailsVo();
				teacherVo.setTeacherId(resultset.getString("TeacherID"));
				teacherVo.setTeacherName(resultset.getString("NAME"));
				System.out.println("DIOMPL:  TeacherID "+resultset.getString("TeacherID"));
				System.out.println("DIOMPL:  TeacherID "+resultset.getString("NAME"));
				reportingToList.add(teacherVo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : reportingToList  Ending");
		
		return reportingToList;
	}

	public ArrayList<AllTeacherDetailsVo> StudentAdmissionNumber(String academicYear,UserLoggingsPojo custid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : StudentAdmissionNumber  Starting");
		
		PreparedStatement mappingpstmt = null;
		ResultSet resultset = null;
		Connection conn = null;
		ArrayList<AllTeacherDetailsVo> reportingToList=new ArrayList<AllTeacherDetailsVo>();

		try {
			conn = JDBCConnection.getSeparateConnection(custid);
			mappingpstmt = conn.prepareStatement(TeacherUtilsConstants.GET_STUDENT_ADMISSION_DETAILS);
			
			mappingpstmt.setString(1, academicYear);
			System.out.println("prepared statement " + mappingpstmt);
			
			resultset = mappingpstmt.executeQuery();
			

			while (resultset.next()) {
				
				AllTeacherDetailsVo teacherVo=new AllTeacherDetailsVo();
				
				teacherVo.setStudentid(resultset.getString("student_id_int"));
				teacherVo.setAdmission(resultset.getString("student_admissionno_var"));
				reportingToList.add(teacherVo);
			}

		
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (mappingpstmt != null && (!mappingpstmt.isClosed())) {
					mappingpstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : StudentAdmissionNumber  Ending");
		
		return reportingToList;
	
		
	}

	public List<TeacherRegistrationPojo> getTeacherDetailsSingle(
			TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getTeacherDetailsSingle  Starting");
	
		ResultSet resultset = null;

		Connection conn = null;
		List<TeacherRegistrationPojo> objlist=new ArrayList<TeacherRegistrationPojo>();

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_SINGLE_TEACHER_DEATIL);
			pstmt.setString(1, obj.getTeacherId());
			System.out.println(" pstmt " + pstmt);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				TeacherRegistrationPojo obj1 = new TeacherRegistrationPojo();
				obj1.setTeacherId(obj.getTeacherId());
				obj1.setTfastname(resultset.getString("FirstName"));
				obj1.setTlastname(resultset.getString("LastName"));
				obj1.setRegsitrationNo(resultset.getString("registerId"));
				obj1.setDept(resultset.getString("department"));
				obj1.setDesignation(resultset.getString("designation"));
				obj1.setTqualification(resultset.getString("qualification"));
				obj1.setJoiningdate(HelperClass.convertDatabaseToUI(resultset.getString("dateofJoining")));
				obj1.setTeachingtype(resultset.getString("teachingType"));
				obj1.setPrimary(resultset.getString("primarySubject"));
				obj1.setSecondary(resultset.getString("secondarySubject"));
				obj1.setGender(resultset.getString("gender"));
				
				obj1.setBankName(resultset.getString("bankname"));
				obj1.setAccountNumber(resultset.getString("accountnumber"));
				obj1.setPanNumber(resultset.getString("pannumber"));
				
				obj1.setDateofbirth(HelperClass.convertDatabaseToUI(resultset.getString("dateofBirth")));
				obj1.setTeachermobno(resultset.getString("mobileNo"));
				obj1.setTeacheremail(resultset.getString("emailId"));
				obj1.setBlood(resultset.getString("bloodgroup"));
				System.out.println("BooldGroup in Diompl: "+resultset.getString("bloodgroup"));
				
				obj1.setImgpath(resultset.getString("imagePath"));
				System.out.println("DIOMPL image path:"+ resultset.getString("imagePath"));
				
				obj1.setProfilepath(resultset.getString("profilePath"));
				obj1.setIdproof(resultset.getString("idProofPath"));
				obj1.setFathername(resultset.getString("fathername"));
				obj1.setMothername(resultset.getString("mothername"));
				obj1.setPresentadd(resultset.getString("presentAddress").trim());
				obj1.setPermanentadd(resultset.getString("permanentAddress").trim());
				obj1.setUsername(resultset.getString("username"));
				obj1.setStatus("update");
				obj1.setRole(resultset.getString("role"));
				obj1.setDocument1(resultset.getString("document1"));
				
				obj1.setDocument2(resultset.getString("document2"));
				obj1.setDocument3(resultset.getString("document3"));
				obj1.setDocument4(resultset.getString("document4"));
				obj1.setDocument5(resultset.getString("document5"));
				
				obj1.setReportingTo(resultset.getString("reportingTo"));
				

				
				
				obj1.setIs_student_studying(resultset.getString("is_student_studying"));
				obj1.setStudentName(resultset.getString("studentName"));
				obj1.setStudent_admission_id(resultset.getString("student_admission_id"));
				
				objlist.add(obj1);
				System.out.println("DIOMPL get Single Teacher Reporting To: "+resultset.getString("reportingTo"));

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : getTeacherDetailsSingle  Ending");
		return objlist;
	}

	public String checkAbbreviativeId(String abbreviate,String locId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkAbbreviativeId  Starting");
		
		int count = 0;
		String value=null;
		String result=null;
		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_ABBREVATIVE_COUNT);
			
			pstmt.setString(1, abbreviate);
			pstmt.setString(2, locId);
			
			resultset = pstmt.executeQuery();
			
			//System.out.println("abbreviate iD::::::: " + pstmt);

			while (resultset.next())
			{
				count = resultset.getInt(1);
				value=resultset.getString("isActive");
			}
			
			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
			}
			else if(count > 0 && value.equalsIgnoreCase("Y")) {
				result="true";
			}
             
			else{
				result="false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkAbbreviativeId  Ending");
		return result;
	}

	@Override
	public int checkStaffInTDS(String currentUser,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkStaffInTDS  Starting");
		int count=0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_STAFF_COUNT);
			
			pstmt.setString(1, currentUser);
			
			resultset = pstmt.executeQuery();
			
			System.out.println("abbreviate iD::::::: " + pstmt);

			while (resultset.next())
			{
				count = resultset.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkStaffInTDS  Ending");
		return count;
	}


	@Override
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetailsByLocIdAndDeptId(StaffAttendanceVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetailsByLocIdAndDeptId Starting");

		ArrayList<AllTeacherDetailsVo> getTeacherDetails = new ArrayList<AllTeacherDetailsVo>();
		Connection conn = null;
		int slNo = 1;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(TeacherUtilsConstants.ALL_TEACHER_DETAILS_BY_LOCID_AND_DEPRTID);
			pstmt.setString(1, vo.getLocId());
			pstmt.setString(2, vo.getDepartment());
			pstmt.setString(3, vo.getSearchtearm()+"%");
			pstmt.setString(4, vo.getSearchtearm()+"%");
			pstmt.setString(5, vo.getSearchtearm()+"%");
			pstmt.setString(6, vo.getSearchtearm()+"%");
			pstmt.setString(7, vo.getSearchtearm()+"%");
			pstmt.setString(8, vo.getSearchtearm()+"%");
			pstmt.setString(9, vo.getSearchtearm()+"%");
			
			System.out.println("ALL_TEACHER_DETAILS_BY_LOCID_AND_DEPRTID -- "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllTeacherDetailsVo allTeacherDetailsVo = new AllTeacherDetailsVo();
				allTeacherDetailsVo.setSlno(String.valueOf(slNo));
				allTeacherDetailsVo.setTeacherName(rs.getString("teacherName"));
				allTeacherDetailsVo.setTeacherId(rs.getString("TeacherID"));
				allTeacherDetailsVo.setQualification(rs.getString("Qualification"));
				allTeacherDetailsVo.setDesignation(rs.getString("designationName"));
				allTeacherDetailsVo.setMobileNo(rs.getString("MobileNo"));
				allTeacherDetailsVo.setEmail(rs.getString("emailId"));
				allTeacherDetailsVo.setAbbreviative_Id(rs.getString("Abbreviative_Id"));
				
				slNo++;
				
			    allTeacherDetailsVo.setBankaccountNo(rs.getString("BankAccNumber"));
			    allTeacherDetailsVo.setPfnumber(rs.getString("EmployeePfNo"));
				
				allTeacherDetailsVo.setRegistartionNo(rs.getString("registerId"));
				allTeacherDetailsVo.setCtc(rs.getDouble("totalSalary"));
				allTeacherDetailsVo.setDepartment(rs.getString("DEPT_NAME"));
				allTeacherDetailsVo.setPannum(rs.getString("pannumber"));
				allTeacherDetailsVo.setBankname(rs.getString("bankname"));
				
				getTeacherDetails.add(allTeacherDetailsVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : getAllTeacherDetailsByLocIdAndDeptId Ending");
		return getTeacherDetails;
	}



	public int checkteachercount(String teacherId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherDaoImpl : checkteacher  Starting");
		int count=0;

		ResultSet resultset = null;

		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			pstmt = conn.prepareStatement(TeacherUtilsConstants.GET_STAFF_COUNT_USERTABLE);
			
			pstmt.setString(1, teacherId);
			
			resultset = pstmt.executeQuery();
			
			System.out.println("TeacherId::::::: " + pstmt);

			while (resultset.next())
			{
				count = resultset.getInt(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultset != null && (!resultset.isClosed())) {
					resultset.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in TeacherDaoImpl : checkteacher  Ending");
		return count;
	}


	public List<StudentConcessionVo> getStudentListbyAdmissionNo(String admissionNo, String locId, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in TeacherDaoImpl : getStudentListbyAdmissionNo Starting");
	
	List<StudentConcessionVo> list = new ArrayList<StudentConcessionVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	
	try{
		StudentConcessionVo stuReg = new StudentConcessionVo();
		conn = JDBCConnection.getSeparateConnection(pojo);
		pst = conn.prepareStatement("SELECT st.student_id_int,st.student_fname_var,st.student_lname_var,cc.classdetail_id_int,cc.classdetails_name_var,cs.classsection_id_int,cs.classsection_name_var,csc.specilization FROM campus_student st JOIN campus_student_classdetails csc ON st.student_id_int=csc.student_id_int  JOIN campus_classdetail cc ON (csc.classdetail_id_int=cc.classdetail_id_int AND csc.locationId=cc.locationId) JOIN campus_classsection cs ON (csc.classsection_id_int=cs.classsection_id_int AND csc.locationId=cs.locationId) WHERE st.student_admissionno_var=? AND csc.locationId=?");
		pst.setString(1, admissionNo);
		pst.setString(2, locId);
		//System.out.println("pst---------"+pst);
		rs = pst.executeQuery();
			
		if(rs.next()){
			stuReg.setAdmissionNo(admissionNo);
			stuReg.setStudentId(rs.getString("student_id_int"));
			stuReg.setStudent(rs.getString("student_fname_var")+" "+rs.getString("student_lname_var"));
			stuReg.setStatus("found");
			list.add(stuReg);
		}else{
			stuReg.setStatus("notfound");
			list.add(stuReg);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {

			if (rs != null && (!rs.isClosed())) {
				rs.close();
			}
			if (pst != null && (!pst.isClosed())) {
				pst.close();
			}
			if (conn != null && (!conn.isClosed())) {
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
			+ " Control in TeacherDaoImpl : getStudentListbyAdmissionNo Ending");
	
	return list;
}


}
