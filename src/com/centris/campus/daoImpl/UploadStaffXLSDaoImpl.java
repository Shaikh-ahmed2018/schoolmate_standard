package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.admin.EmailContent;
import com.centris.campus.admin.SendMail;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.TeacherUtilsConstants;
import com.centris.campus.util.UploadStaffXLSqlUtil;
import com.centris.campus.util.UploadStudentXLSqlUtil;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;



public class UploadStaffXLSDaoImpl {

	private static Logger logger = Logger.getLogger(UploadStaffXLSDaoImpl.class);
	
	public String getServerUrlFromBD(Connection connection) {
		String url = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(TeacherUtilsConstants.GET_SERVER_URL);
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
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		return url;
	}
	
	public String sendEmailToEmployee(String username, String email,
			String password, String url) {
		try {
			EmailContent em = new EmailContent();
			// String[] mails = { emailId };

			System.out.println("email id in s send employee " + email);

			em.setUsername(username);
			em.setPassword(password);

			em.setContent("Greetings from E-CAMPUS PRO...  \n"
					+ " Thank you for Registering with us \n"
					+ "Please use below Url to track / view / update child activities in School \n"
					+ "Login Credentials are : \n" + "URL		: " + url + "\n"
					+ "User Name		: " + username + "\n" + "Password	: "
					+ password + "\n" + "Have a nice day\n\n\n" + "Regards \n"
					+ "E-CAMPUS PRO \n"
					+ "---------------------------------------------------\n"
					+ "This is System generated mail, Please do not reply."
					+ "\n");
			new SendMail().sendMail(email, em);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "sent";
	}
	
	public int checkStaffCountBeforeInsert(UserLoggingsPojo custdetails) {
		int beforeInsertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(UploadStaffXLSqlUtil.CHECK_BEFORINSERT_COUNT);
			/*System.out.println("CHECK_BEFORINSERT_COUNT:::" + pstmt);*/
			rs = pstmt.executeQuery();
			while (rs.next()) {
			   beforeInsertCount = rs.getInt(1);
			}
			/*System.out.println("In DIOMPL Before Insert: "+beforeInsertCount);*/
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
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
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXLSDaoImpl: Ending");

		return beforeInsertCount;
	}

	public int checkRegistrainId(String RegistrationId, Connection connection) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXSLDaoImpl : checkRegistrainId : Starting");

		PreparedStatement ps_emp_count = null;
		ResultSet rs_emp_count = null;
		int count=0;

		try {
			ps_emp_count = connection.prepareStatement(UploadStaffXLSqlUtil.REGISTRATION_DUPLICATE);
			ps_emp_count.setString(1, RegistrationId);
			rs_emp_count = ps_emp_count.executeQuery();

			while (rs_emp_count.next()) {
				count = rs_emp_count.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs_emp_count != null && (!rs_emp_count.isClosed())) {
					rs_emp_count.close();
				}
				if (ps_emp_count != null && (!ps_emp_count.isClosed())) {
					ps_emp_count.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLDaoImpl : checkStageID : Ending");
		return count;
	}


	public Set<UploadStaffXlsVO> insertStaffXSL(List<UploadStaffXlsPOJO> successlist,
			Connection connection,String log_audit_session, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+" Control in UploadStageXSLDaoImpl : insertStageXSL : Starting");
	
				int count=0;
/*			    conn.setAutoCommit(false);
*/              ResultSet resultsetcount = null;
                PreparedStatement pstmt = null,ps_insertuser= null;
                String staffIdValid=null,abbrivationIdValid=null,value=null;
               
                Set<UploadStaffXlsVO> failurelist = new LinkedHashSet<UploadStaffXlsVO>();
				
                try {
			   
			    UploadStaffXlsVO uploadStaffXlsVO = new UploadStaffXlsVO();
			    
			    connection = JDBCConnection.getSeparateConnection(custdetails);
			    pstmt = (PreparedStatement) JDBCConnection.getStatement(UploadStaffXLSqlUtil.INSERT_TEACHER,custdetails);
			   
			         for(int i=0;i<successlist.size();i++){

			        	 staffIdValid=getStaffId(successlist.get(i).getRegistrationId(),successlist.get(i).getLocation(),connection,custdetails);
			        	 abbrivationIdValid=getStaffAbbrivationId(successlist.get(i).getAbbreviation(),successlist.get(i).getLocation(),connection,custdetails);
			    	     
			        	 if(staffIdValid.equalsIgnoreCase("duplicate")){
			        		 value="Staff Id";
			        	 }else if(abbrivationIdValid.equalsIgnoreCase("duplicate")){
			        		 value="Abbrivate Id";
			        	 }
			        	 
			        	 if(!staffIdValid.equalsIgnoreCase("duplicate") || !abbrivationIdValid.equalsIgnoreCase("duplicate"))
			        	 {
			        	  TeacherRegistrationPojo obj=new TeacherRegistrationPojo();
						  obj.setTfastname(successlist.get(i).getFirstName());
						  obj.setTeachermobno(successlist.get(i).getMobileNo());
						  String genPassword = HelperClass.genaratePasswordForTeacher(obj);

						 		pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_teachers",custdetails));
								pstmt.setString(2, successlist.get(i).getFirstName());
								pstmt.setString(3, successlist.get(i).getLastName());
								pstmt.setString(4, successlist.get(i).getQualification());
								pstmt.setString(5, successlist.get(i).getPresentAddress());
								pstmt.setString(6, successlist.get(i).getMobileNo());
								pstmt.setString(7, successlist.get(i).getUserName());
								pstmt.setString(8, successlist.get(i).getEmail());
								pstmt.setString(9, HelperClass.convertUIToDatabase(successlist.get(i).getDob()));
								pstmt.setString(10, HelperClass.convertUIToDatabase(successlist.get(i).getDateOfJoining()));
								pstmt.setString(11, successlist.get(i).getDesignation());
								pstmt.setString(12, successlist.get(i).getTeachingType().toUpperCase());
								pstmt.setString(13, successlist.get(i).getDepartment());
								pstmt.setString(14, successlist.get(i).getGender().toUpperCase());
								pstmt.setString(15, successlist.get(i).getBankName());
								pstmt.setString(16, successlist.get(i).getAccountNumber());
								pstmt.setString(17, successlist.get(i).getPanNumber());
								pstmt.setString(18, successlist.get(i).getBloodGroup());
								pstmt.setString(19, successlist.get(i).getFatherName());
								pstmt.setString(20, successlist.get(i).getMotherName());
								pstmt.setString(21, successlist.get(i).getPermanentAddress().trim());
								pstmt.setString(22, successlist.get(i).getCreatedby());
								pstmt.setTimestamp(23, HelperClass.getCurrentTimestamp());
								pstmt.setString(24, successlist.get(i).getRegistrationId());
								pstmt.setString(25, genPassword);
								pstmt.setString(26, successlist.get(i).getRole());
								pstmt.setString(27,  successlist.get(i).getReportingTo());
								
								//is_student_studying,studentName,student_admission_id,fatherMobile,motherMobile
								
								if(successlist.get(i).getIsStudentStudying()=="Yes"||successlist.get(i).getIsStudentStudying().equalsIgnoreCase("Yes"))
								{
									pstmt.setString(28, "Y");
									/*pstmt.setString(29, successlist.get(i).getStudentName());*/
									pstmt.setString(29, successlist.get(i).getAdmissionNo());
								}
								else
								{
									pstmt.setString(28, "N");
									/*pstmt.setString(31, "");*/
									pstmt.setString(29, "");
								}
								
								pstmt.setString(30, successlist.get(i).getFatherMobile());
								pstmt.setString(31, successlist.get(i).getMotherMobile());
								pstmt.setString(32, successlist.get(i).getAbbreviation());
								pstmt.setString(33, successlist.get(i).getLocation());
								
								if(successlist.get(i).getMaritalStatus().equalsIgnoreCase("Yes"))
								{
									pstmt.setString(34, "Married");
								}else{
									pstmt.setString(34, "Single");
								}
								
								pstmt.setString(35, successlist.get(i).getSpouseName());
								pstmt.setString(36, successlist.get(i).getSpouseMobile());
								pstmt.setString(37, successlist.get(i).getAadharNo());
								
								/*System.out.println("INSERT STAFF::::"+pstmt);*/
								int result = pstmt.executeUpdate();
								
								if(result>0){
									HelperClass.recordLog_Activity(log_audit_session,"Staff","StaffExcelUpload","Insert",pstmt.toString(),connection);
									count++;
								}
								/*String userId=new IDGenerator().getPrimaryKeyID("campus_user",custdetails.getCustId()); 
									//connection = JDBCConnection.getSeparateConnection();
									
									ps_insertuser=connection.prepareStatement(TeacherUtilsConstants.INSERT_USER_DETAILS);
									
									ps_insertuser.setString(1, userId);
									ps_insertuser.setString(2, staffId);
									ps_insertuser.setString(3,successlist.get(i).getUserName());
									ps_insertuser.setString(4,genPassword);
									ps_insertuser.setString(5, successlist.get(i).getRole());
									ps_insertuser.setString(6, successlist.get(i).getUserType());
									ps_insertuser.setString(7, successlist.get(i).getCreatedby());
									ps_insertuser.setTimestamp(8, HelperClass.getCurrentTimestamp());
									ps_insertuser.setString(9, successlist.get(i).getLocation());
									
									System.out.println("insert user :: "+ps_insertuser);
									
									int insert=ps_insertuser.executeUpdate();
									if(insert>0){
										
										//HelperClass.recordLog_Activity(log_audit_session,"Staff","Upload Staff Excel Data File","Update",ps_insertuser.toString());
										count++;
									}
								  //sending email.
									
									String url = getServerUrlFromBD(connection);
									// String url="www.google.com";
									String set = sendEmailToEmployee(obj.getUsername(),
									obj.getTeacheremail(), genPassword, url);*/
			        	 }	
			        	 else{
			        		 uploadStaffXlsVO.setFirstName(successlist.get(i).getFirstName()+" "+successlist.get(i).getLastName());
			        		 uploadStaffXlsVO.setMobileNo(successlist.get(i).getMobileNo());
			        		 uploadStaffXlsVO.setDesignation(successlist.get(i).getDesignationName());
			        		 uploadStaffXlsVO.setDepartment(successlist.get(i).getDepartmentName()); 
			        		 uploadStaffXlsVO.setReason(value+" Already Exist..!!");
			        		 failurelist.add(uploadStaffXlsVO);
			        	 }
					   }
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					} 
			 
/*			 conn.commit();
*/		
				finally {
					try {
						if (resultsetcount != null && (!resultsetcount.isClosed())) {
							resultsetcount.close();
						}
						if (ps_insertuser != null && (!ps_insertuser.isClosed())) {
							ps_insertuser.close();
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
				+ " Control in UploadStageXSLDaoImpl : insertStageXSL : Ending");
		
		return failurelist;
	}

	public String getDepartmentId(String department, Connection connection) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getDepartmentId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String departmentId=null;
			
			try {
				 
				psDep= connection.prepareStatement(UploadStaffXLSqlUtil.GET_DEPARTMENT_ID);
				psDep.setString(1,department);
				/*System.out.println("get departmentCode ::: "+psDep);*/

				rs=psDep.executeQuery();
				
				while(rs.next()){
					departmentId=rs.getString("dept_id");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return departmentId;
		}

	public String getDesignationId(String designation, Connection connection) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getDepartmentId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String designationId=null;
			
			try {
				psDep= connection.prepareStatement(UploadStaffXLSqlUtil.GET_DESIGNATION_ID);
				psDep.setString(1,designation);
				/*System.out.println("get departmentCode ::: "+psDep);*/
				rs=psDep.executeQuery();
				
				while(rs.next()){
					designationId=rs.getString("DesignationCode");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return designationId;
		}

	public String getSubjectId(String subject) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getprimarySubjectId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String subjectId=null;
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_SUBJECT_ID);
				psDep.setString(1,subject);
				
				/*System.out.println("get departmentCode ::: "+psDep);*/
				rs=psDep.executeQuery();
				
				while(rs.next()){
					subjectId=rs.getString("subjectID");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return subjectId;
		}

	public String getRoleId(String role) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in UploadStaffXLSDaoImpl: getRoleId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String roleId=null;
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_ROLE_ID);
				psDep.setString(1,role);
				
				/*System.out.println("get RoleCOde ::: "+psDep);*/
				rs=psDep.executeQuery();
				
				while(rs.next()){
					roleId=rs.getString("RoleCode");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return roleId;
		}

	public String getReportingToId(String reportingTo) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())+ " Control in UploadStaffXLSDaoImpl: getRoleId : Starting");
			
			PreparedStatement psDep = null;
			ResultSet rs=null;
			Connection conn = null;
			String reportingToId=null;
			
			try {
				conn = JDBCConnection.getSeparateConnection();
				psDep= conn.prepareStatement(UploadStaffXLSqlUtil.GET_REPORTING_CODE);
				psDep.setString(1,reportingTo);
				/*System.out.println("get ReportingTo ::: "+psDep);*/
				rs=psDep.executeQuery();
				
				while(rs.next()){
					reportingToId=rs.getString("TeacherID");
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
					if (psDep != null&& (!psDep.isClosed())) {
						psDep.close();
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
					+ " Control in StaffAttendanceDaoImpl: getStaffAttendance: Ending");
			
			return reportingToId;
		}

	public String getStaffId(String registrationId, String locId,Connection connection, UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in UploadStaffXLSDaoImpl : getStaffId : Starting"); 

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
		int count=0;
	
		try {
			psmt = connection.prepareStatement(UploadStaffXLSqlUtil.GET_REGISTRATION_ID);
			psmt.setString(1, registrationId.trim());
			psmt.setString(2, locId);
			/*System.out.println("GET_REGISTRATION_ID-->>"+psmt);*/
			rs = psmt.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="duplicate";
			}else{
				status="valid";
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXLSDaoImpl : getStaffId :  Ending");
		return status;
	}

	public String getStaffAbbrivationId(String abbreviation, String locId,Connection connection, UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXLSDaoImpl : getStaffAbbrivationId : Starting"); 
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
		int count=0;
	
		try {
			psmt = connection.prepareStatement(UploadStaffXLSqlUtil.GET_ABBRIVATION_ID_COUNT);
			psmt.setString(1, abbreviation.trim());
			psmt.setString(2, locId);
			/*System.out.println("GET_ABBRIVATION_ID_COUNT-->>"+psmt);*/
			rs = psmt.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="duplicate";
			}else{
				status="valid";
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {

			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXLSDaoImpl : getStaffAbbrivationId :  Ending");
		return status;
	}

	public String getStudentAdmissionValid(String admissionNo, String locId,Connection connection, UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXLSDaoImpl : getStudentNameValid : Starting"); 
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String status=null;
		int count=0;
	
		try {
			psmt = connection.prepareStatement(UploadStaffXLSqlUtil.GET_STUDENT_ADMISSION_NO_VALID);
			psmt.setString(1, admissionNo.trim()); 
			psmt.setString(2, locId);
			/*System.out.println("GET_STUDENT_ADMISSION_NO_VALID-->>"+psmt);*/
			rs = psmt.executeQuery();
			if(rs.next()) {
				status=rs.getString(1);
			}
			else{
				status="notfound";
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(), e1);
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (psmt != null && (!psmt.isClosed())) {
					psmt.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXLSDaoImpl : getStudentNameValid :  Ending");
		return status;
	}
}
