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

import com.centris.campus.dao.StaffReleivingOrderDao;

import com.centris.campus.pojo.RelievingOrderPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReportsMenuSqlConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ReleivingOrderVo;
import com.centris.campus.vo.UserDetailVO;
import com.sun.research.ws.wadl.Request;



public class StaffReleivingOrderDaoImpl implements StaffReleivingOrderDao{

	private static final Logger logger = Logger
			.getLogger(StaffReleivingOrderDaoImpl.class);
	
	
	
	public List<AllTeacherDetailsVo> getTeachingListDaoImpl(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_TEACHER_TEACHING_LIST);
			
			pstmt.setString(1, vo.getUsertype());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Ending");
		
		
		
		return teachinglist;
	}



	public List<AllTeacherDetailsVo> getNonTeachingListDaoImpl(AllTeacherDetailsVo vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Starting");
		
		ArrayList<AllTeacherDetailsVo> teachinglist = new ArrayList<AllTeacherDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection conn = null;
		
		try {
			
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_NONTEACHER_TEACHING_LIST);
			
			pstmt.setString(1, vo.getUsertype());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				AllTeacherDetailsVo teavo = new AllTeacherDetailsVo();
				
				teavo.setTeacherId(rs.getString("TeacherID"));
				teavo.setTeacherName(rs.getString("teachername"));
				
				
				
				teachinglist.add(teavo);
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getTeachingListDaoImpl Ending");
		
		
		
		return teachinglist;
	}



	
	public List<UserDetailVO> getUsersListDaoImpl(UserLoggingsPojo userLoggingsVo) {
	
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getUsersListDaoImpl Starting");
		
		 List<UserDetailVO> userlist = new  ArrayList<UserDetailVO>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_USER_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				UserDetailVO vo = new UserDetailVO();
				
				vo.setUserId(rs.getString("RoleCode"));
				vo.setUserName(rs.getString("RoleName"));
				
				userlist.add(vo);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getUsersListDaoImpl Ending");
		
		return userlist;
	}


	public List<ReleivingOrderVo> getReleivingDetailsDaoImpl(RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Starting");
		
		 List<ReleivingOrderVo> details = new  ArrayList<ReleivingOrderVo>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		 
		 
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = (PreparedStatement) conn.prepareStatement(ReportsMenuSqlConstants.GET_RELEIVING_DETAILS_LIST);
			
			pstmt.setString(1, pojo.getTeachername());
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReleivingOrderVo revpojo = new ReleivingOrderVo();
				
				revpojo.setTeachername1(rs.getString("teachername"));
				revpojo.setEmpid(rs.getString("registerId"));
				revpojo.setDoj(rs.getString("dateofJoining"));
				revpojo.setDesignation(rs.getString("designationName"));
				revpojo.setPfnumber(rs.getString("EmployeePfNo"));
				revpojo.setSalary(rs.getString("CTC"));
				
				
				
				
				details.add(revpojo);
				
				
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Ending");
		
		
		return details;
	}



	@Override
	public List<RelievingOrderPojo> SearchRelievingOrder(RelievingOrderPojo pojo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Starting");
		
		 List<RelievingOrderPojo> details = new  ArrayList<RelievingOrderPojo>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		 
		 
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			String qry =ReportsMenuSqlConstants.GET_RELIEVING_STAFF_LIST;
			HashMap map = new HashMap();
			Vector vec = new Vector();
			String key = null;
			String val = null;
			String wheresql = null;
			int count = 0;
					
			if(!pojo.getLocId().equalsIgnoreCase("%%")) {
				map.put("ct.`Loc_ID`",pojo.getLocId());
				vec.add("ct.`Loc_ID`");
			}
			if(!pojo.getDeptId().equalsIgnoreCase("%%") ) {
				map.put("ct.`department`",pojo.getDeptId());
				vec.add("ct.`department`");
			}
			if(!pojo.getDesignation().equalsIgnoreCase("%%")) {
				map.put("ct.`designation`",pojo.getDesignation());
				vec.add("ct.`designation`");
			}
			
			if(!pojo.getTeachertype().equalsIgnoreCase("%%")) {
				map.put("ct.`teachingType`",pojo.getTeachertype());
				vec.add("ct.`teachingType`");
			}
			
			
			Enumeration<String> e = vec.elements();

			while ( e.hasMoreElements() ) {
				key = e.nextElement().toString();
				val = map.get(key).toString();

				if(count == 0) {
					wheresql=" where "+ key+" = '"+val+"'";
					count++;
				}else {
					wheresql = wheresql+" and "+key+"='"+val+"'";
				}
			}
			
			String finalquery="";
			if(wheresql != null) {
				finalquery=qry+" "+wheresql; /*stu.`student_id_no` like ? or*/
			}else {
				finalquery=qry; /*stu.`student_id_no` like ? or*/
			}
			pstmt = conn.prepareStatement(finalquery);
			
			System.out.println("finalquery is "+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RelievingOrderPojo pojo1 =new RelievingOrderPojo();
				
				pojo1.setTechId(rs.getString("TeacherID"));
			    pojo1.setLocId(rs.getString("Loc_ID"));
			    pojo1.setTeachername(rs.getString("staffname"));
			    pojo1.setRegistrtionno(rs.getString("registerId"));
			    pojo1.setTeachertype(rs.getString("teachingType"));
			    pojo1.setMobno(rs.getString("mobileNo"));
			    pojo1.setEmailId(rs.getString("emailId"));
			    pojo1.setDeptname(rs.getString("DEPT_NAME"));
			    pojo1.setDesigname(rs.getString("designationName"));
			    pojo1.setLocaname(rs.getString("Location_Name"));
			    pojo1.setLocadd(rs.getString("Location_Address"));
			    pojo1.setDoj(HelperClass.convertDatabaseToUI(rs.getString("dateofJoining")));
			  	details.add(pojo1);
		
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Ending");
		
		
		return details;
	}



	@Override
	public List<RelievingOrderPojo> staffReleivingPDFReport(String[] teachercode,RelievingOrderPojo pojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Starting");
		
		 List<RelievingOrderPojo> details = new  ArrayList<RelievingOrderPojo>();
		 
		 PreparedStatement pstmt = null;
			ResultSet rs=null;
			Connection conn = null;
		 
		 
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i =0;i<teachercode.length;i++)
			{
			
			pstmt =conn.prepareStatement(ReportsMenuSqlConstants.GET_RELIEVING_STAFF_PDF);
			pstmt.setString(1,teachercode[i]);
			System.out.println("+++++++++++++++"+pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RelievingOrderPojo pojo1 =new RelievingOrderPojo();
				
				pojo1.setTechId(rs.getString("TeacherID"));
			    pojo1.setLocId(rs.getString("Loc_ID"));
			    pojo1.setTeachername(rs.getString("staffname"));
			    pojo1.setRegistrtionno(rs.getString("registerId"));
			    pojo1.setTeachertype(rs.getString("teachingType"));
			    pojo1.setMobno(rs.getString("mobileNo"));
			    pojo1.setEmailId(rs.getString("emailId"));
			    pojo1.setDeptname(rs.getString("DEPT_NAME"));
			    pojo1.setDesigname(rs.getString("designationName"));
			    pojo1.setLocaname(rs.getString("Location_Name"));
			    pojo1.setLocadd(rs.getString("Location_Address"));
			    pojo1.setDoj(HelperClass.convertDatabaseToUI(rs.getString("dateofJoining")));
			  	details.add(pojo1);
		
			}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {

				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffReleivingOrderDaoImpl : getReleivingDetailsDaoImpl Ending");
		
		
		return details;
	}

}
