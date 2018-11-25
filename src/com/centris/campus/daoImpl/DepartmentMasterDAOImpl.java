package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.DepartmentMasterDao;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.DepartmentMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.DepartmentMasterVO;



public class DepartmentMasterDAOImpl implements DepartmentMasterDao {

	private static Logger logger = Logger
			.getLogger(DepartmentMasterDAOImpl.class);

	public synchronized ArrayList<DepartmentMasterVO> getDepartmentDetails(UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : getDepartmentDetails : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		ArrayList<DepartmentMasterVO> depdetails = new ArrayList<DepartmentMasterVO>();

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			department_pstmt = conn.prepareStatement(SQLUtilConstants.GET_DEPARTMENT_DETAILS);

			department_rs = department_pstmt.executeQuery();

			while (department_rs.next()) {
				DepartmentMasterVO departmentMasterVO = new DepartmentMasterVO();

				departmentMasterVO.setDepId(department_rs.getString("DEPT_ID"));
				departmentMasterVO.setDepName(department_rs.getString("DEPT_NAME"));
				departmentMasterVO.setDesc(department_rs.getString("DESCRIPTION"));
				departmentMasterVO.setRemark(department_rs.getString("remark"));
				depdetails.add(departmentMasterVO);
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl : getDepartmentDetails : Ending");

		return depdetails;

	}

	public synchronized String insertDepartmentDetails(DepartmentMasterPojo deptpojo, String update_dept,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl :insertDepartmentDetails:Starting");

		PreparedStatement department_pstmt = null;

		Connection conn = null;
		int count = 0;
		String status = null;

		if (!update_dept.equalsIgnoreCase("")) {

			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

				department_pstmt = conn
						.prepareStatement(SQLUtilConstants.UPDATE_DEPARTMENT);

				department_pstmt.setString(1, deptpojo.getDeptname().trim());
				department_pstmt.setString(2, deptpojo.getDeptdescription()
						.trim());
				department_pstmt.setString(3, "Y");
			 	department_pstmt.setString(4, deptpojo.getCreatedby());
				department_pstmt.setString(5, update_dept.trim());

				
				
				count = department_pstmt.executeUpdate();

				if (count > 0) {
					HelperClass.recordLog_Activity(deptpojo.getLog_audit_session(),"Settings","Department","Update",department_pstmt.toString(),userLoggingsVo);
					status = MessageConstants.UPDATE_DEPARTMENT_SUCCESS;
				} else {
					status = MessageConstants.UPDATE_DEPARTMENT_FAIL;

				}
			}

			catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {

				try {
                     
					if (department_pstmt != null
							&& (!department_pstmt.isClosed())) {

						department_pstmt.close();
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
		} else if (update_dept.equalsIgnoreCase(""))

			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

				department_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_DEPARTMENT_DETAILS);

				department_pstmt.setString(1, deptpojo.getDepartmentid());
				department_pstmt.setString(2, deptpojo.getDeptname());
				department_pstmt.setString(3, deptpojo.getDeptdescription());
				department_pstmt.setString(4, "Y");
				department_pstmt.setTimestamp(5, deptpojo.getCreate_date());
				department_pstmt.setString(6, deptpojo.getCreatedby());
				
				count = department_pstmt.executeUpdate();

				if (count > 0) {
					HelperClass.recordLog_Activity(deptpojo.getLog_audit_session(),"Settings","Department","Insert",department_pstmt.toString(), userLoggingsVo);
					status = MessageConstants.ADD_DEPARTMENT_SUCCESS;
				} else {
					status = MessageConstants.ADD_DEPARTMENT_FAIL;
				}

			}

			catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				logger.error(e.getMessage(), e);
			} finally {

				try {

					if (department_pstmt != null
							&& (!department_pstmt.isClosed())) {

						department_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl:insertDepartmentDetails: Ending");

		return status;

	}

	public synchronized String validateDeptName(DepartmentMasterForm deptfForm,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : validateDeptName : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;

		
		int count = 0;
		String value=null;
		String result=null;
		Connection conn = null;
		
		if(deptfForm.getDepartmentid().equalsIgnoreCase("")){
		try {
			
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			department_pstmt = conn	.prepareStatement(SQLUtilConstants.VALIDATE_DEPT_NAME);

			department_pstmt.setString(1, deptfForm.getDept_name());
			department_rs = department_pstmt.executeQuery();
		     
			while (department_rs.next()) {

				count = department_rs.getInt(1);
				value=department_rs.getString("isActive");

			}

			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
				

			} else if(count > 0 && value.equalsIgnoreCase("Y")) {

				result="true";
			}
             
			else{
				result="false";
			}
		 
		
		}
			
			catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		
		
		
		
		finally {

			try {
				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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

		}
		
		if(!deptfForm.getDepartmentid().equalsIgnoreCase("")){
             try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			department_pstmt = conn	.prepareStatement(SQLUtilConstants.VALIDATE_DEPT_NAME);

			department_pstmt.setString(1, deptfForm.getDept_name());
			department_rs = department_pstmt.executeQuery();
		     //("7777777777777777777:"+department_pstmt);
			while (department_rs.next()) {

				count = department_rs.getInt(1);
				value=department_rs.getString("isActive");

			}

			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
				

			} else if(count > 0 && value.equalsIgnoreCase("Y")) {

				result="true";
			}
             
			else{
				result="false";
			}
		 
		
		}
			
			catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
		
		
		
		
		finally {

			try {
				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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

		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : validateDeptName : Ending");

		return result;
	}

	public synchronized String deleteDepartmentDetails(String[] deptid,UserLoggingsPojo custdetails,DepartmentMasterVO deptvo,String button) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: deleteDepartmentDetails : Starting");

		ResultSet department_rs = null;

		PreparedStatement ps_deletedepartment = null, deleteUser = null;
		ResultSet rs_deletedepartment = null;
		int no = 0;
		String status = null;
		Connection conn = null;
		int i = 0;
		 
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			for(i=0;i<deptid.length;i++){
				ps_deletedepartment = (PreparedStatement) JDBCConnection.getStatement(SQLUtilConstants.DELETE_DEPARTMENT,custdetails);
				ps_deletedepartment.setString(1, deptvo.getStatus());
				ps_deletedepartment.setString(2, deptvo.getRemark());
				ps_deletedepartment.setString(3, deptid[i]);
				no = ps_deletedepartment.executeUpdate();
				
				deleteUser = (PreparedStatement) JDBCConnection.getStatement(SQLUtilConstants.DELETE_DEPARTMENTS_STAFF,custdetails);
				deleteUser.setString(1, deptvo.getStatus());
				deleteUser.setString(2, deptid[i]);
				deleteUser.executeUpdate();
				if (no > 0) {
					HelperClass.recordLog_Activity(deptvo.getLog_audit_session(),"Settings","Department",button,deptvo.toString(),custdetails);
					status = "true";
				} else {
					status ="false";
				}
			}



		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		} finally {

			try {

				if (rs_deletedepartment != null
						&& (!rs_deletedepartment.isClosed())) {

					rs_deletedepartment.close();
				}
				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (ps_deletedepartment != null
						&& (!ps_deletedepartment.isClosed())) {

					ps_deletedepartment.close();
				}
				if (deleteUser != null
						&& (!deleteUser.isClosed())) {

					deleteUser.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: deleteDepartmentDetails : Ending");

		return status;
	}

	public synchronized DepartmentMasterVO getEditDepartmentDetails(String edit,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		Connection conn = null;

		DepartmentMasterVO addDepartmentVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			department_pstmt = conn
					.prepareStatement(SQLUtilConstants.EDIT_DEPARTMENT);

			department_pstmt.setString(1, edit);

			department_rs = department_pstmt.executeQuery();

			while (department_rs.next()) {

				addDepartmentVO = new DepartmentMasterVO();
            	addDepartmentVO.setDepName(department_rs.getString("DEPT_NAME"));
				addDepartmentVO.setDesc(department_rs.getString("DESCRIPTION"));
				addDepartmentVO.setDepId(department_rs.getString("DEPT_ID"));

			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: getEditDepartmentDetails  : Ending");

		return addDepartmentVO;

	}

	public synchronized ArrayList<DepartmentMasterVO> searchDepartment(String searchvo,UserLoggingsPojo custdetails,String status) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : searchDepartment Starting");

		ArrayList<DepartmentMasterVO> getdeptlist = new ArrayList<DepartmentMasterVO>();
		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		Connection conn = null;
		// int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			department_pstmt = conn
					.prepareStatement(SQLUtilConstants.SEARCH_DEPARTMENT_DETAILS);

			department_pstmt.setString(1,searchvo+ "%");
			department_pstmt.setString(2,searchvo+ "%");
			department_pstmt.setString(3,searchvo+ "%");
			department_pstmt.setString(4,status);
			//("99999999999999999999999999"+department_pstmt);
			department_rs = department_pstmt.executeQuery();

			while (department_rs.next()) {
				// sno++;
				DepartmentMasterVO deptdetailsVO = new DepartmentMasterVO();

				deptdetailsVO.setDepId(department_rs.getString("DEPT_ID"));
				deptdetailsVO.setDepName(department_rs.getString("DEPT_NAME"));
				deptdetailsVO.setDesc(department_rs.getString("DESCRIPTION"));
				deptdetailsVO.setRemark(department_rs.getString("remark"));
				getdeptlist.add(deptdetailsVO);

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: searchDepartment  : Ending");

		return getdeptlist;
	}

	public synchronized boolean validateDeptNameUpdate(
			DepartmentMasterVO validateUpdate, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: validateDeptNameUpdate : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		boolean deptname_available = false;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			department_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_DEPARTMENT_UPDATE);

			department_pstmt.setString(1, validateUpdate.getDepName());
			department_pstmt.setString(2, validateUpdate.getDepId());
			department_rs = department_pstmt.executeQuery();
              
			while (department_rs.next()) {

				count = department_rs.getInt(1);
				
			}
			if (count > 0) {

				deptname_available = true;

			} else {

				deptname_available = false;

			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl: validateDeptNameUpdate : Ending");

		return deptname_available;
	}

	@Override
	public ArrayList<DepartmentMasterVO> getDeptStatusList(UserLoggingsPojo userLoggingsVo, String status) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : getDeptStatusList : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		ArrayList<DepartmentMasterVO> depdetails = new ArrayList<DepartmentMasterVO>();

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			department_pstmt = conn.prepareStatement(SQLUtilConstants.GET_DEPARTMENT_DETAILS_STATUSLIST);
			department_pstmt.setString(1, status);
            //("///////////////////////"+department_pstmt);
			department_rs = department_pstmt.executeQuery();

			while (department_rs.next()) {
				DepartmentMasterVO departmentMasterVO = new DepartmentMasterVO();
				departmentMasterVO.setDepId(department_rs.getString("DEPT_ID"));
				departmentMasterVO.setDepName(department_rs.getString("DEPT_NAME"));
				departmentMasterVO.setDesc(department_rs.getString("DESCRIPTION"));
				departmentMasterVO.setRemark(department_rs.getString("remark"));
				depdetails.add(departmentMasterVO);
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl : getDeptStatusList : Ending");

		return depdetails;
	}

	@Override
	public ArrayList<DepartmentMasterVO> getdepartmentlist(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterDAOImpl : getdepartmentlist : Starting");

		PreparedStatement department_pstmt = null;
		ResultSet department_rs = null;
		ArrayList<DepartmentMasterVO> depdetails = new ArrayList<DepartmentMasterVO>();

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			department_pstmt = conn.prepareStatement(SQLUtilConstants.GET_DEPARTMENT_DETAILS_BY_LOCATION);
     
			department_rs = department_pstmt.executeQuery();

			while (department_rs.next()) {
				DepartmentMasterVO departmentMasterVO = new DepartmentMasterVO();
				departmentMasterVO.setDepId(department_rs.getString("DEPT_ID"));
				departmentMasterVO.setDepName(department_rs.getString("DEPT_NAME"));
				depdetails.add(departmentMasterVO);
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (department_rs != null && (!department_rs.isClosed())) {

					department_rs.close();
				}
				if (department_pstmt != null && (!department_pstmt.isClosed())) {

					department_pstmt.close();
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
				+ " Control in DepartmentMasterDAOImpl : getdepartmentlist : Ending");

		return depdetails;
	}



}
