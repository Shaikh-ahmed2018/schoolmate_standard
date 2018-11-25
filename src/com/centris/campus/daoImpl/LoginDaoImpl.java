package com.centris.campus.daoImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LoginSqlUtil;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.UserLoggingsVo;
import com.cerp.rest.model.Customer;
import com.cerp.rest.util.DBConnection;
public class LoginDaoImpl {
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

	public LoginVo validateUserDaoImpl(String username, String password, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : validateUserDaoImpl  Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		LoginVo loginvo=new LoginVo();
		int status = 0;
		int count = 0;
		try {
			
			Boolean ivalid = false;
			String sql = null;
			con = JDBCConnection.getSeparateConnection(pojo);
			
			sql = "select COUNT(*),usr.app_userid,usr.employeecode,usr.cust_id,usr.username,usr.password,usr.type,usr.role,CASE WHEN rol.IsDefault IS NULL THEN 'N' ELSE rol.IsDefault END IsDefault from  campus_user usr left outer join campus_role rol on rol.RoleCode = usr.role  where  binary usr.username = ? and binary usr.password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			
				while(rs.next()) {
								count = rs.getInt(1);
								if(count > 0){
									ivalid	= true;		
									loginvo.setUsercode(rs.getString("employeecode"));
									loginvo.setUsername(rs.getString("username"));
									loginvo.setUsertype(rs.getString("type"));
									loginvo.setUserrole(rs.getString("role"));
									loginvo.setPassword(rs.getString("password"));
									loginvo.setIsAdministrator(rs.getString("IsDefault"));
									loginvo.setAuserid(rs.getString("app_userid"));
									loginvo.setStatus("licvalid");
								}else{
									ivalid	= false;		
								}
				}
			if(!ivalid){
					loginvo.setStatus("Enter valid username and password");
			}
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : validateUserDaoImpl  Ending");
		
		return loginvo;
	}
	
	
	/*public String isValidUser(String username, String password) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : validateUserDaoImpl  Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String userexist=null;
		LoginVo loginvo=new LoginVo();
		try {

			String sql = null;
			con = JDBCConnection.getSeparateConnection();
			sql = "select usr.employeecode,usr.cust_id,usr.username,usr.password,usr.type,usr.role,CASE WHEN rol.IsDefault IS NULL THEN 'N' ELSE rol.IsDefault END IsDefault from  campus_user usr left outer join campus_role rol on rol.RoleCode = usr.role  where  usr.username = ? and usr.password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				loginvo.setUsercode(rs.getString("employeecode"));
				loginvo.setUsername(rs.getString("username"));
				loginvo.setUsertype(rs.getString("type"));
				loginvo.setUserrole(rs.getString("role"));
				loginvo.setPassword(rs.getString("password"));
				loginvo.setIsAdministrator(rs.getString("IsDefault"));
				userexist="true";
			}else{
				userexist="false";
			}
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : validateUserDaoImpl  Ending");
		
		return 	userexist;
	}*/
	public UserDetailVO loadUserDaoImpl(LoginVo loginvo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadUserDaoImpl  Starting");
		UserDetailVO userDetails = new UserDetailVO();
		PreparedStatement statement = null;
		ResultSet userDetailsResultSet = null;
		
		PreparedStatement ps_permission = null;
		ResultSet rs_permission = null;
		
		HashMap<String, String> permissionmap=new HashMap<String, String>();
		
		Connection con = null;
		String roleCode=null;
		boolean flag=true;
		try {

			con=JDBCConnection.getSeparateConnection(userLoggingsVo);
			System.out.println(loginvo.getUsertype());
			if(loginvo.getUsertype().equalsIgnoreCase("parent")){
				
				PreparedStatement pstmt=con.prepareStatement(LoginSqlUtil.GET_PARENT_CHAILD_RELATION);
				pstmt.setString(1, loginvo.getUsercode());
				
				ResultSet rs=pstmt.executeQuery();
				
				String relationship=null;
				
				System.out.println("pstmt :: "+pstmt);
				
				while(rs.next()){
					
					relationship=rs.getString("relationship");
				}
			
				if(relationship.endsWith("father")){
					statement=con.prepareStatement(LoginSqlUtil.GET_FATHER_DETAILS);
				}else if(relationship.endsWith("mother")){
					statement=con.prepareStatement(LoginSqlUtil.GET_MOTHER_DETAILS);
				}else{
					statement=con.prepareStatement(LoginSqlUtil.GET_GUARDIAN_DETAILS);
				}
			
				
			
			statement.setString(1, loginvo.getUsercode());
			
			System.out.println("statement :: "+statement);
			
			userDetailsResultSet = statement.executeQuery();

			while (userDetailsResultSet.next()) {

				userDetails.setUserId(userDetailsResultSet.getString("ParentID"));
				userDetails.setFirstName(userDetailsResultSet.getString("parentname"));
				userDetails.setQualification(userDetailsResultSet.getString("Qualification"));
				userDetails.setAddress(userDetailsResultSet.getString("address"));
				userDetails.setMobileno(userDetailsResultSet.getString("mobileno"));
				userDetails.setUserName(userDetailsResultSet.getString("UserName"));
				userDetails.setGender("");
				userDetails.setEmail(userDetailsResultSet.getString("email"));
				userDetails.setPassword(userDetailsResultSet.getString("password"));
				userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
				userDetails.setRoleCode(userDetailsResultSet.getString("role"));
				userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
				roleCode=userDetailsResultSet.getString("role");
				userDetails.setUserNametype(userDetailsResultSet.getString("type"));
			}
		 }else if(loginvo.getUsertype().equalsIgnoreCase("admin") && loginvo.getUsercode().contains("eCamp")){
			 statement=con.prepareStatement(LoginSqlUtil.GET_CUSTOMER_DETAILS);
			 statement.setString(1,loginvo.getUsercode());
			 userDetailsResultSet = statement.executeQuery();
			 while (userDetailsResultSet.next()) {
				userDetails.setUserId(userDetailsResultSet.getString("customerID"));
				userDetails.setFirstName(userDetailsResultSet.getString("customer"));
				userDetails.setMobileno(userDetailsResultSet.getString("cust_phone_no"));
				userDetails.setUserName(userDetailsResultSet.getString("username"));
				userDetails.setPassword(userDetailsResultSet.getString("password"));
				userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
				userDetails.setRoleCode(userDetailsResultSet.getString("role"));
				userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
				userDetails.setUserNametype(userDetailsResultSet.getString("type"));
				roleCode=userDetails.getRoleCode();
				//userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
			 }
			 
		 }
		else{
				statement=con.prepareStatement(LoginSqlUtil.GET_STAFF_DETAILS);
				statement.setString(1, loginvo.getUsercode());
				System.out.println("statement::::"+statement);
				userDetailsResultSet = statement.executeQuery();

				while (userDetailsResultSet.next()) {

					userDetails.setUserId(userDetailsResultSet.getString("TeacherID"));
					userDetails.setFirstName(userDetailsResultSet.getString("teachername"));
					userDetails.setQualification(userDetailsResultSet.getString("qualification"));
					userDetails.setAddress(userDetailsResultSet.getString("presentAddress"));
					userDetails.setMobileno(userDetailsResultSet.getString("mobileNo"));
					userDetails.setUserName(userDetailsResultSet.getString("username"));
					userDetails.setGender(userDetailsResultSet.getString("gender"));
					userDetails.setEmail(userDetailsResultSet.getString("emailId"));
					userDetails.setPassword(userDetailsResultSet.getString("password"));
					userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
					userDetails.setRoleCode(userDetailsResultSet.getString("role"));
					userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
					roleCode=userDetailsResultSet.getString("role");
					System.out.println("roleCode::::"+roleCode);
					userDetails.setUserNametype(userDetailsResultSet.getString("type"));
					userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
				}
			 
		 }
			
			if(roleCode!=null || roleCode !=""){
				
				ps_permission = con.prepareStatement(LoginSqlUtil.GET_PERMISSION_DETAILS);
				ps_permission.setString(1, roleCode);
				System.out.println("Permission Details ::: "+ps_permission);
				rs_permission = ps_permission.executeQuery();
				while(rs_permission.next()) {
					
					flag=false;
				
					permissionmap.put(rs_permission.getString("shortName"),rs_permission.getString("isApplicable"));
							
					userDetails.setPermissionmap(permissionmap);
					
				} 
				
				if(flag){
					
					userDetails.setPermissionmap(permissionmap);
				}
				
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
			exception.printStackTrace();
			
		} finally {

			try {
				if (userDetailsResultSet != null && !userDetailsResultSet.isClosed()) {
					userDetailsResultSet.close();
				}
				if (ps_permission != null && !ps_permission.isClosed()) {
					ps_permission.close();
				}
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}
				if (rs_permission != null && (!rs_permission.isClosed())) {
					rs_permission.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadUserDaoImpl  Ending");
	
			return userDetails;
		
	}

	public int checkCurrentPassword(String currentuser, String currentPassword,
			String currentuserId, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Starting");
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		Connection con = null;
		try {
			con = JDBCConnection.getSeparateConnection(custdetails);
			if (currentuser.equalsIgnoreCase("userDetails")) {
				statement = con.prepareStatement(SQLUtilConstants.CHECK_PARENTS_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());
				 System.out.println("CHECK_PARENTS_PASSWORD -->>"+statement);
			} else if (currentuser.equalsIgnoreCase("Teacher")) {
				statement = con.prepareStatement(SQLUtilConstants.CHECK_TEACHERS_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());
				 System.out.println("CHECK_TEACHERS_PASSWORD -->>"+statement);
			} else if (currentuser.equalsIgnoreCase("Principle")) {

				statement = con.prepareStatement(SQLUtilConstants.CHECK_PRINCIPAL_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());
                System.out.println("CHECK_PRINCIPAL_PASSWORD -->>"+statement);
			} else {
				statement = con.prepareStatement(SQLUtilConstants.CHECK_ADMIN_PASSWORD);
				statement.setString(1, currentuserId.trim());
				statement.setString(2, currentPassword.trim());
				 System.out.println("CHECK_ADMIN_PASSWORD -->>"+statement);
			}
			rs = statement.executeQuery();
			while (rs.next()) {

				count = rs.getInt(1);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : checkCurrentPassword Ending");

		return count;
	}

	public int changePassword(String currentuser, String newpassword,
			String currentuserId, String log_audit_session,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : changePassword Starting");
		PreparedStatement statement = null,pstmt=null;
		int count = 0,count1=0;
		Connection con = null;
		
		try {
			con = JDBCConnection.getSeparateConnection(pojo);

			if (currentuser.equalsIgnoreCase("userDetails")) {
				statement = con.prepareStatement(SQLUtilConstants.UPDATE_PERENTS_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());
                System.out.println("UPDATE_PERENTS_PASSWORD -->>"+statement);
			} else if (currentuser.equalsIgnoreCase("Teacher")) {
				statement = con.prepareStatement(SQLUtilConstants.UPDATE_TEACHERS_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());
				System.out.println("UPDATE_TEACHERS_PASSWORD -->>"+statement);
			} else if (currentuser.equalsIgnoreCase("Principle")) {
				statement = con.prepareStatement(SQLUtilConstants.UPDATE_PRINCIPAL_PASSWORD);

				statement.setString(1, newpassword.trim());
				statement.setString(2, currentuserId.trim());
				System.out.println("UPDATE_PRINCIPAL_PASSWORD -->>"+statement);
			} else {
				
				pstmt = con.prepareStatement(SQLUtilConstants.UPDATE_CAMPUS_USER_PASSWORD);  
				pstmt.setString(1, newpassword.trim());
				pstmt.setString(2, currentuserId.trim());
				pstmt.executeUpdate();
				count= pstmt.executeUpdate();
				
//				if(count1>0){
//					HelperClass.recordLog_Activity(log_audit_session,"USER","Change Password","Update",pstmt.toString(),pojo);
//				}
				
//				UpdateMasterDB(pojo.getCustId(),newpassword);
//				statement = con.prepareStatement(SQLUtilConstants.UPDATE_ADMIN_PASSWORD); /*UPDATE_ADMIN_PASSWORD*/
//				statement.setString(1, newpassword.trim());
//				statement.setString(2, currentuserId.trim());
//				System.out.println("UPDATE_ADMIN_PASSWORD -->>"+statement);
			}

			count = statement.executeUpdate();
			
			if(count>0){
				HelperClass.recordLog_Activity(log_audit_session,"Login","Change Password","Update",statement.toString(),pojo);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : changePassword Ending");

		return count;
	}

	public String userValidCase(String uname, String password, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:userValidCase Starting");
	

		LoginVo lvo=new LoginVo();
		String userexist = null;
		String query = SQLUtilConstants.LOGINCHECK;

		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{
			con=  JDBCConnection.getSeparateConnection(custdetails);
			pstmt =  con.prepareStatement(query); 
			pstmt.setString(1, uname);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next())
			{
				
				/*SELECT u.,u., u. ,u.role,r.RoleName roleName
				from campus_user u,campus_role r WHERE u.role=r.RoleCode and u.username= ? AND u.password= ?
		*/

				lvo.setUsername(rs.getString("username"));
				lvo.setPassword(rs.getString("password"));
				lvo.setUsercode(rs.getString("usercode"));
				
				if(uname.equals(rs.getString("username")) && password.equals(rs.getString("password"))){
					
					userexist = MessageConstants.TRUE;
					
				} else {
					userexist = MessageConstants.FALSE;
				}
				
				
			}

		}catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		finally{

			try {
				
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL: userValidCase Ending");
		return userexist;
	}

	public String saveDairy(String content, String rowid, String date,
			String userId, String userType, UserLoggingsPojo custdetails, String sessionid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : saveDairy  Starting");
		
		PreparedStatement pstmtcheck = null;
		ResultSet rscheck=null;
		PreparedStatement pstmt = null;
		int rs = 0;
		int count=0;
		Connection con = null;
		String status=null;
		String operation = null;
		try {

			String sql = null;

			con = JDBCConnection.getSeparateConnection(custdetails);
			pstmtcheck=con.prepareStatement("SELECT COUNT(*) FROM campus_dairy WHERE rowId=? AND userId=?");
			pstmtcheck.setString(1, rowid);
			pstmtcheck.setString(2, userId);
			rscheck=pstmtcheck.executeQuery();
			while (rscheck.next()) {
				count = rscheck.getInt(1);
				
			}
			if(count>0){
				sql = "UPDATE campus_dairy SET content=?,contentDate=?,createdBy=? where rowId=? AND userId=?";
				operation = "Update";
			}
			else{
			sql = "INSERT INTO campus_dairy (content,contentDate,createdBy,rowId,userId) VALUES(?,?,?,?,?)";
			operation = "Insert";
			}
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, content);
			pstmt.setString(2, HelperClass.convertUIToDatabase(date));
			pstmt.setString(3, userId);
			pstmt.setString(4, rowid);
			pstmt.setString(5, userId);
			
			System.out.println(pstmt);

			rs = pstmt.executeUpdate();
			if(rs>0){
				status="true";
				HelperClass.recordLog_Activity(sessionid,"Home Page","Dairy",operation,pstmt.toString(),custdetails);
			}
			else{
				status="false";
			}
			
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				
				if (rscheck != null && (!rscheck.isClosed())) {
					rscheck.close();
				}
				if (pstmtcheck != null && (!pstmtcheck.isClosed())) {
					pstmtcheck.getConnection().close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : saveDairy  Ending");
		
		return status;
	}

	public List<DairyDetailsVo> getDairy(String userId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : getDairy  Starting");
		List<DairyDetailsVo> commentList=new ArrayList<DairyDetailsVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			String sql = null;

			con = JDBCConnection.getSeparateConnection(custdetails);
			
			sql = "select * from campus_dairy where userId=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DairyDetailsVo vo=new DairyDetailsVo();
				vo.setRowId(rs.getString("rowId"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getString("contentDate"));
				commentList.add(vo);
			}
			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : getDairy  Ending");
		
		return commentList;
	}

	public ArrayList<UserDetailVO> getteacherClassDetails(LoginVo loginVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : getteacherClassDetails  Starting");
		
		ArrayList<UserDetailVO> list =new ArrayList<UserDetailVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try{
				//con = JDBCConnection.getSeparateConnection();
				PreparedStatement pstmt1 = con.prepareStatement("select CTCode,ClassCode,SectionCode from campus_classteacher where TeacherCode =?");
				pstmt1.setString(1,loginVo.getUsercode());
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					UserDetailVO loginvo= new UserDetailVO();
							
					loginvo.setTeamapid(rs1.getString("CTCode"));
					loginvo.setClassid(rs1.getString("ClassCode"));
					loginvo.setSectionid(rs1.getString("SectionCode"));
					list.add(loginvo);
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : getteacherClassDetails  Ending");
		return list;
	}


	public Object getlastLoginTime(String userName, String password,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : getlastLoginTime  Starting");
		
		  String lastlogintime="";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = con.prepareStatement("SELECT `lastLogin` FROM `campus_user` WHERE `username`=? AND `password`=?");
				pstmt.setString(1, userName);
				pstmt.setString(2, password);
			
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					if(rs.getTimestamp("lastLogin")!=null){
						
						lastlogintime = rs.getTimestamp("lastLogin").toString().trim().substring(0, 19);
					}
					
				} 

				if(rs!=null && !(rs.isClosed())){
					
					rs.close();
				}
				
				if(pstmt!=null && !(pstmt.isClosed())){
					
					pstmt.close();
				}
				
				pstmt = con.prepareStatement("UPDATE `campus_user` SET `lastLogin`=? WHERE `username`=? AND `password`=?");
				pstmt.setTimestamp(1, HelperClass.getCurrentTimestamp());
				pstmt.setString(2, userName);
				pstmt.setString(3, password);
				
			    pstmt.executeUpdate();
				
							
				
			}catch(SQLException sqlException){
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage(),sqlException);
			}catch(Exception e)	{
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
			finally {

				try {

					if (rs != null && (!rs.isClosed())) {

						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {

						pstmt.close();
					}
					if (con != null && !con.isClosed()) {
						con.close();
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
					+ " Control in LoginDAOIMPL:  getlastLoginTime Ending");

			return lastlogintime;
		}


	public UserLoggingsVo InsertUserLoggings(UserLoggingsPojo pojo, UserLoggingsPojo custdetails) throws SQLException {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:   InsertUserLoggings  Starting");
	
	   	Connection con = null;
		PreparedStatement pstmt = null;
		
		UserLoggingsVo userLoggingsVO = new UserLoggingsVo();
		String key= IDGenerator.getPrimaryKeyID("log_audit",custdetails);

		try {
			
			con = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = con.prepareStatement("INSERT INTO `log_audit`(`session_id`,`user_id`,`ip_address`,`login_time`,cust_id,token_id,isActive) VALUES (?,?,?,?,?,?,'Y')");
	
			pstmt.setString(1,key);
			pstmt.setString(2, pojo.getUsercode());
			pstmt.setString(3, pojo.getIpaddress());
			pstmt.setTimestamp(4, pojo.getLogintime());
			pstmt.setString(5, pojo.getCustId());
			pstmt.setString(6, pojo.getTokenNo());
			System.out.println("log audit :::"+pstmt);
			int count = pstmt.executeUpdate();
			if(count>0){
								
				if(pstmt!=null && !(pstmt.isClosed())){
					
					pstmt.close();
				}
				userLoggingsVO.setSessionId(key);
				userLoggingsVO.setLogintime(pojo.getLogintime());
			}
			
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage(),sqlException);
		}catch(Exception e)	{
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		finally {

			try {
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
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
				+ " Control in LoginDAOIMPL:  InsertUserLoggings Ending");

		return userLoggingsVO;
	}


	public Object getUserDetails(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getUserID(String userName, String password, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:   getUserID  Starting");
		Connection con = null;
		CallableStatement pstmt = null;
		ResultSet rs = null;
		String userId=null;
		
		try {
			con = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = con.prepareCall("{CALL getUserCode(?,?)}");
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if(rs.next()){
				userId=rs.getString("usercode");
			}
			
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage(),sqlException);
		}catch(Exception e)	{
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		finally {

			try {

				if (rs != null && (!rs.isClosed())) {

					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
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
				+ " Control in LoginDAOIMPL:  getUserID Ending");

		System.out.println("userId "+userId);
		
		return userId;
	}


	


	public void updateUserLoggings(String sessionId, Timestamp logintime,UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:   updateUserLoggings  Starting");
	   	Connection con = null;
		PreparedStatement pstmt = null;

		
		String status="false";
		
		try {
			
			con = JDBCConnection.getSeparateConnection(pojo);
			pstmt = con.prepareStatement("UPDATE `log_audit` SET `logout_time`=?,`total_time`=TIMEDIFF(?,`login_time`),isActive = 'N' WHERE `session_id`=?");
			
			pstmt.setTimestamp(1, HelperClass.getCurrentTimestamp());
			pstmt.setTimestamp(2, HelperClass.getCurrentTimestamp());
			pstmt.setString(3,sessionId);
		
			
			//System.out.println("update_user_loggins@@@@@@@@@@@@@@@@@@@@@@@"+pstmt);
			int count = pstmt.executeUpdate();
			
			if(count>0){
				status=MessageConstants.TRUE;
			}
			//System.out.println("&&&&&&&&&&&&& Result &&&&&&&&&&&&&&&&&&&&"+status);
			
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage(),sqlException);
		}catch(Exception e)	{
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		finally {

			try {

				
				if (pstmt != null && (!pstmt.isClosed())) {

					pstmt.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
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
				+ " Control in LoginDAOIMPL:  updateUserLoggings Ending");
		
	}

	private void UpdateMasterDB(String custId, String newpassword) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL:UpdateMasterDB Starting");
		Connection con=null;
		PreparedStatement pstmt = null;
		try{
			con=  DBConnection.getmasterConnection();
			pstmt =  con.prepareStatement("UPDATE `campus_customer_details` SET `cust_pwd` = ?  WHERE `customerID` = ? "); 
			pstmt.setString(1, newpassword);
			pstmt.setString(2, custId);
			pstmt.executeUpdate();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		finally{

			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDAOIMPL: UpdateMasterDB Ending");
		
	}


	public UserDetailVO loadNewUserBD(LoginVo loginvo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadNewUserBD  Starting");
		UserDetailVO userDetails = new UserDetailVO();
		PreparedStatement statement = null;
		ResultSet userDetailsResultSet = null;
		
		PreparedStatement ps_permission = null;
		ResultSet rs_permission = null;
		
		HashMap<String, String> permissionmap=new HashMap<String, String>();
		
		Connection con = null;
		String roleCode=null;
		boolean flag=true;
		try {

			con=JDBCConnection.getSeparateConnection(userLoggingsVo);
			System.out.println(loginvo.getUsertype());
			if(loginvo.getUsertype().equalsIgnoreCase("admin") || loginvo.getUsertype().contains("user")){
			 statement=con.prepareStatement(LoginSqlUtil.GET_CUSTOMER_DETAILS);
			 statement.setString(1,loginvo.getCustId());
			 statement.setString(2,loginvo.getUsercode());
			 userDetailsResultSet = statement.executeQuery();
			 while (userDetailsResultSet.next()) {
				
				userDetails.setUserId(userDetailsResultSet.getString("customerID"));
				userDetails.setFirstName(userDetailsResultSet.getString("customer"));
				userDetails.setMobileno(userDetailsResultSet.getString("cust_phone_no"));
				userDetails.setUserName(userDetailsResultSet.getString("username"));
				userDetails.setPassword(userDetailsResultSet.getString("password"));
				userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
				userDetails.setRoleCode(userDetailsResultSet.getString("role"));
				userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
				userDetails.setUserNametype(userDetailsResultSet.getString("type"));
				roleCode=userDetails.getRoleCode();
				//userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
				userDetails.setLocationid("all");
			 }
			 
		 }
			if(roleCode!=null || roleCode !=""){
				
				ps_permission = con.prepareStatement(LoginSqlUtil.GET_PERMISSION_DETAILS);
				ps_permission.setString(1, roleCode);
				System.out.println("Permission Details ::: "+ps_permission);
				rs_permission = ps_permission.executeQuery();
				while(rs_permission.next()) {
					
					flag=false;
				
					permissionmap.put(rs_permission.getString("shortName"),rs_permission.getString("isApplicable"));
							
					userDetails.setPermissionmap(permissionmap);
					
				} 
				
				if(flag){
					
					userDetails.setPermissionmap(permissionmap);
				}
				
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
			exception.printStackTrace();
			
		} finally {

			try {
				if (userDetailsResultSet != null && !userDetailsResultSet.isClosed()) {
					userDetailsResultSet.close();
				}
				if (ps_permission != null && !ps_permission.isClosed()) {
					ps_permission.close();
				}
				if (statement != null && (!statement.isClosed())) {
					statement.getConnection().close();
				}
				if (rs_permission != null && (!rs_permission.isClosed())) {
					rs_permission.close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : loadUserDaoImpl  Ending");
	
			return userDetails;
		
	}


	public UserDetailVO validateNewUserBD(String userid, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LoginDaoImpl : validateNewUserBD  Starting");
		
		PreparedStatement pstmt = null, statement = null, ps_permission = null;
		ResultSet rs = null, userDetailsResultSet = null, rs_permission =null;
		Connection con = null;
		UserDetailVO userDetails = new UserDetailVO();
		int status = 0;
		int count = 0;
		String roleCode = null;
		HashMap<String, String> permissionmap=new HashMap<String, String>();
		boolean flag=true;
		try {/*
			
			String sql = null;
			con = JDBCConnection.getSeparateConnection(customerDBDetails);
			
			sql = "select COUNT(*),usr.app_userid,usr.employeecode,usr.cust_id,usr.username,usr.password,usr.type,usr.role,CASE WHEN rol.IsDefault IS NULL THEN 'N' ELSE rol.IsDefault END IsDefault from  campus_user usr left outer join campus_role rol on rol.RoleCode = usr.role  where  usr.app_userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auserid);
			
			System.out.println(pstmt);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
						
				count = rs.getInt(1);
				loginvo.setUsercode(rs.getString("employeecode"));
				//loginvo.setUsercode(rs.getString("app_userid"));
				loginvo.setUsername(rs.getString("username"));
				loginvo.setUsertype(rs.getString("type"));
				loginvo.setUserrole(rs.getString("role"));
				loginvo.setPassword(rs.getString("password"));
				loginvo.setIsAdministrator(rs.getString("IsDefault"));
		}
			
		*/
			con = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = con.prepareStatement(LoginSqlUtil.GET_USER_DETAILS);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				String empcode = rs.getString("employeecode");
				
				if(rs.getString("employeecode").startsWith("eCamp")){
					
					 statement=con.prepareStatement(LoginSqlUtil.GET_CUSTOMER_DETAILS);
					 statement.setString(1,empcode);
					 userDetailsResultSet = statement.executeQuery();
					 System.out.println(statement);
					 while (userDetailsResultSet.next()) {
						
						userDetails.setUserId(userDetailsResultSet.getString("customerID"));
						userDetails.setFirstName(userDetailsResultSet.getString("customer"));
						userDetails.setMobileno(userDetailsResultSet.getString("cust_phone_no"));
						userDetails.setUserName(userDetailsResultSet.getString("username"));
						userDetails.setPassword(userDetailsResultSet.getString("password"));
						userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
						userDetails.setRoleCode(userDetailsResultSet.getString("role"));
						userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
						userDetails.setUserNametype(userDetailsResultSet.getString("type"));
						userDetails.setIsAdministrator(userDetailsResultSet.getString("IsDefault"));
						userDetails.setRoletype(userDetailsResultSet.getString("role_type"));
						//userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
						userDetails.setLocationid(userDetailsResultSet.getString("locationId"));
						userDetails.setUserCode(userDetailsResultSet.getString("usercode"));
						userDetails.setDoj("2017-04-30");
						roleCode = userDetails.getRoleCode();
					 }
				}else if(rs.getString("employeecode").startsWith("TEA")){
					statement=con.prepareStatement(LoginSqlUtil.GET_STAFF_DETAILS);
					statement.setString(1, empcode);
					System.out.println("statement::::"+statement);
					userDetailsResultSet = statement.executeQuery();

					while (userDetailsResultSet.next()) {

						userDetails.setUserId(userDetailsResultSet.getString("TeacherID"));
						userDetails.setFirstName(userDetailsResultSet.getString("teachername"));
						userDetails.setQualification(userDetailsResultSet.getString("qualification"));
						userDetails.setAddress(userDetailsResultSet.getString("presentAddress"));
						userDetails.setMobileno(userDetailsResultSet.getString("mobileNo"));
						userDetails.setUserName(userDetailsResultSet.getString("username"));
						userDetails.setGender(userDetailsResultSet.getString("gender"));
						userDetails.setEmail(userDetailsResultSet.getString("emailId"));
						userDetails.setPassword(userDetailsResultSet.getString("password"));
						userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
						userDetails.setRoleCode(userDetailsResultSet.getString("role"));
						userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
						userDetails.setUserNametype(userDetailsResultSet.getString("type"));
						userDetails.setLocationid(userDetailsResultSet.getString("Loc_ID"));
						userDetails.setIsAdministrator(userDetailsResultSet.getString("IsDefault"));
						userDetails.setRoletype(userDetailsResultSet.getString("role_type"));
						userDetails.setUserCode(userDetailsResultSet.getString("usercode"));
						userDetails.setDoj(userDetailsResultSet.getString("dateofJoining"));
						roleCode = userDetails.getRoleCode();
					}
				}//another condition need to be added for parent login
				else if(rs.getString("employeecode").startsWith("PAR")){
					
					PreparedStatement parent =con.prepareStatement(LoginSqlUtil.GET_PARENT_CHAILD_RELATION);
					parent.setString(1, empcode);
					
					ResultSet parentrs = parent.executeQuery();
					String relationship=null;
					
					System.out.println("pstmt :: "+parent);
					
					while(parentrs.next()){
						
						relationship=parentrs.getString("relationship");
					}
				
					if(relationship.endsWith("father")){
						statement=con.prepareStatement(LoginSqlUtil.GET_FATHER_DETAILS);
					}else if(relationship.endsWith("mother")){
						statement=con.prepareStatement(LoginSqlUtil.GET_MOTHER_DETAILS);
					}else{
						statement=con.prepareStatement(LoginSqlUtil.GET_GUARDIAN_DETAILS);
					}
				
					statement.setString(1, empcode);
				
					System.out.println("statement :: "+statement);
				
					userDetailsResultSet = statement.executeQuery();

					while (userDetailsResultSet.next()) {

					userDetails.setUserId(userDetailsResultSet.getString("ParentID"));
					userDetails.setFirstName(userDetailsResultSet.getString("parentname"));
					userDetails.setQualification(userDetailsResultSet.getString("Qualification"));
					userDetails.setAddress(userDetailsResultSet.getString("address"));
					userDetails.setMobileno(userDetailsResultSet.getString("mobileno"));
					userDetails.setUserName(userDetailsResultSet.getString("UserName"));
					userDetails.setGender("");
					userDetails.setEmail(userDetailsResultSet.getString("email"));
					userDetails.setPassword(userDetailsResultSet.getString("password"));
					userDetails.setLasttimevisit(userDetailsResultSet.getDate("lastLogin"));
					userDetails.setRoleCode(userDetailsResultSet.getString("role"));
					userDetails.setRoleName(userDetailsResultSet.getString("RoleName"));
					roleCode=userDetailsResultSet.getString("role");
					userDetails.setUserNametype(userDetailsResultSet.getString("type"));
				}
			 }
				if(roleCode!=null && !roleCode.trim().equalsIgnoreCase("")){
					
					ps_permission = con.prepareStatement(LoginSqlUtil.GET_PERMISSION_DETAILS);
					ps_permission.setString(1, userDetails.getRoleCode());
					System.out.println("Permission Details ::: "+ps_permission);
					rs_permission = ps_permission.executeQuery();
					while(rs_permission.next()) {
						
						flag=false;
						permissionmap.put(rs_permission.getString("shortName"),rs_permission.getString("isApplicable"));
						userDetails.setPermissionmap(permissionmap);
						
					} 
					
					if(flag){
						
						userDetails.setPermissionmap(permissionmap);
					}
				}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.getConnection().close();
				}
				if (con != null && (!con.isClosed())) {
					con.close();
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LoginDaoImpl : validateNewUserBD  Ending");
		
		return userDetails;
	}

 }