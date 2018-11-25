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
import com.centris.campus.dao.AddtSubjectDao;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SubjectSqlUtils;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class AddSubjectDaoImpl implements AddtSubjectDao {
	private static final Logger logger = Logger.getLogger(AddSubjectDaoImpl.class);

	@Override
	public synchronized ArrayList<ViewallSubjectsVo> subjectdetails(String schoolLocation,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : subjectdetails Starting");
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SubjectSqlUtils.VIEW_SUBJECT_DETAILS);
			pstmt.setString(1, schoolLocation);
			resultSet = pstmt.executeQuery();
			System.out.println("pstmt======"+pstmt);

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				String specCode=resultSet.getString("specialization");
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setDescription(resultSet.getString("decription"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setLocationId(resultSet.getString("locationId"));
				obj.setRemark(resultSet.getString("remark"));
				obj.setSubjectCode(resultSet.getString("Sub_Code"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("-");
				}
			
				allsubject.add(obj);
			}
			//(allsubject.size());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: subjectdetails Ending");
		return allsubject;

	}

	public String DeleteSubject(String[] voObj,String[] locationList,ViewallSubjectsVo vo, String button,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : DeleteSubject Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String status = null;
		int count = 0;
		int result = 0;
		try {
			//("inside daoimpl:" +voObj.length);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			if(vo.getStatus().equalsIgnoreCase("N")){
				
				for(int i=0;i<voObj.length;i++)
				{
					/*PreparedStatement checksub = conn.prepareStatement("SELECT COUNT(*) FROM `campus_syllabus_details` WHERE `sub_lab_id` = ?");
					checksub.setString(1, voObj[i]);
					ResultSet checkrs = checksub.executeQuery();
					int counts = 0;
					while(checkrs.next()){
						counts = checkrs.getInt(1);
					}
					checkrs.close();
					checksub.close();
					if(counts == 0){
						checksub = conn.prepareStatement("SELECT COUNT(*) FROM `laboratory_details` WHERE `Subject` = ?");
						checksub.setString(1, voObj[i]);
						checkrs = checksub.executeQuery();
						
						while(checkrs.next()){
							counts = checkrs.getInt(1);
						}
						checkrs.close();
						checksub.close();
						if(counts == 0){
							
							checksub = conn.prepareStatement("SELECT COUNT(*) FROM `campus_studentwise_mark_details` WHERE `sub_id` = ?");
							checksub.setString(1, voObj[i]);
							checkrs = checksub.executeQuery();
							
							while(checkrs.next()){
								counts = checkrs.getInt(1);
							}
							checkrs.close();
							checksub.close();
							if(counts == 0){*/
								pstmt = conn.prepareStatement(SubjectSqlUtils.DELETE_SUBJECT_DETAILS);
								pstmt.setString(1, vo.getStatus());
								pstmt.setString(2, vo.getRemark());
								pstmt.setString(3, voObj[i]);
								pstmt.setString(4, locationList[i]);
								count = pstmt.executeUpdate();
								if (count > 0){
									result++;
									status = "inactivetrue";
									HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Subject",button,vo.toString(),userLoggingsVo);
								} 
							}
			}
			else if(vo.getStatus().equalsIgnoreCase("Y")){
				for(int i=0;i<voObj.length;i++)
				{
					pstmt = conn.prepareStatement(SubjectSqlUtils.DELETE_SUBJECT_DETAILS);
					pstmt.setString(1, vo.getStatus());
					pstmt.setString(2, vo.getRemark());
					pstmt.setString(3, voObj[i]);
					pstmt.setString(4, locationList[i]);
					//("pstmt" +pstmt);
					count = pstmt.executeUpdate();
					if (count > 0) {
						result++;
						HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Subject",button,vo.toString(),userLoggingsVo);
						status = "activetrue";
					}
				}
			}
			
			if(result == voObj.length){
				conn.commit();
			}else{
				conn.rollback();
				status = "false";
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: DeleteSubject Ending");
		return status;

	}

	public synchronized List<ViewallSubjectsVo> searchsubjectdetails(
			ViewallSubjectsVo voObj,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : searchsubjectdetails Starting");
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.SEARCH_SUBJECT_DETAILS);
			
			pstmt.setString(1, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(2, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(3, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(4, "%" + voObj.getSearchName().trim() + "%");
			pstmt.setString(5, "%" + voObj.getSearchName().trim() + "%");
			
			if(!voObj.getLocationId().equalsIgnoreCase("all"))
			pstmt.setString(6, voObj.getLocationId());
			else
			pstmt.setString(6, "%%");
			//(pstmt);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				/* obj.setStatus(resultSet.getString("status")); */
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setSpecializationName(resultSet.getString("Specialization_name"));
				obj.setRemark(resultSet.getString("remark"));
				obj.setSubjectCode(resultSet.getString("subjectCode"));
				obj.setSno(sno);

				allsubject.add(obj);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: searchsubjectdetails Ending");
		return allsubject;
	}

	public synchronized String addSubject(AddSubjectVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String status = "",Id="";
		try {
	     conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			boolean flagSubExist = validateSubjectWithClass(vo,userLoggingsVo);
			
			Id=IDGenerator.getPrimaryKeyID("campus_subject",userLoggingsVo);
			
			if(vo.getHiddensubjectId().equalsIgnoreCase("")){

			if (!flagSubExist) {
				
				pstmt = conn.prepareStatement(SubjectSqlUtils.INSERT_ADDSUBJECT_FORM);
				pstmt.setString(1,	Id);
				pstmt.setString(2, vo.getSubjtname());
				pstmt.setString(3, vo.getClassname());
				pstmt.setString(4, vo.getDescription());
				pstmt.setString(5, vo.getCreatedUserId());
				pstmt.setString(6, vo.getLocationId());
				pstmt.setString(7, vo.getSpecId());
				pstmt.setString(8, vo.getSubjectCode());
				pstmt.setString(9, vo.getIsLanguage());
				pstmt.setString(10, vo.getIslab());
				pstmt.setString(11, vo.getSubType());
				
				/*//("INSERT_ADDSUBJECT_FORM -->>"+pstmt);*/
				
				result = pstmt.executeUpdate();
				

				if (result > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Subject","Insert",pstmt.toString(),userLoggingsVo);
					status = "true";
				} else {
					status = "false";
				}
			}else{
				status="alreadyexist";
			}
		 }else{
		    pstmt = conn.prepareStatement(SubjectSqlUtils.UPDATE_SUBJECT);
			pstmt.setString(1, vo.getSubjtname());
			pstmt.setString(2, vo.getClassname());
			pstmt.setString(3, vo.getDescription());
			pstmt.setString(4, vo.getCreatedUserId());
			pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			pstmt.setString(6, vo.getIslab());
			pstmt.setString(7, vo.getSubType());
			pstmt.setString(8, vo.getLocationId());
			pstmt.setString(9, vo.getSpecId());
			pstmt.setString(10, vo.getIsLanguage());
			pstmt.setString(11, vo.getSubjectCode());
			pstmt.setString(12, vo.getHiddensubjectId());
			
			/*//("UPDATE_SUBJECT -->>" +pstmt);*/
			
			int res = pstmt.executeUpdate();
			
			if (res > 0) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Settings","Subject","Update",pstmt.toString(),userLoggingsVo);
				status = "update";
			} else {
				status = "updatefail";
			}
		 }

		} catch (Exception sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		}  

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addSubject Ending");

		return status;
	}

	public synchronized ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getSubjectDetails Starting");
		ViewallSubjectsVo allsubject = new ViewallSubjectsVo();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	

		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.GET_SINGLE_SUBJECT_DETAILS);
			pstmt.setString(1, obj.getSubjectid());

			resultSet = pstmt.executeQuery();
             
			while (resultSet.next()) {

				allsubject.setSubjectid(resultSet.getString("subjectID"));
				allsubject.setSubjectname(resultSet.getString("subjectName").trim());
				allsubject.setPath(resultSet.getString("syllabous"));
				allsubject.setDescription(resultSet.getString("decription"));
				/* obj.setStatus(resultSet.getString("status")); */
				allsubject.setClassname(resultSet.getString("classid"));
				allsubject.setStatus("update");
				allsubject.setIslab(resultSet.getString("isLab"));
				allsubject.setSubType(resultSet.getString("subType"));
				allsubject.setLocationId(resultSet.getString("locationId"));
				allsubject.setSubjectCode(resultSet.getString("Sub_Code"));
				allsubject.setSpecializationId(resultSet.getString("specialization"));
				allsubject.setIsLanguage(resultSet.getString("isLanguage"));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: getSubjectDetails Ending");
		return allsubject;
	}

	public synchronized boolean updateSubjectDetails(AddSubjectForm obj,UserLoggingsPojo userLoggingsVo) {
         //("it is coming inside fo update or not");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.UPDATE_SUBJECT);
               //("update pstmt:" +pstmt);
			pstmt.setString(1, obj.getSubjtname().trim());
			pstmt.setString(2, obj.getFilename().trim());
			pstmt.setString(3, obj.getClassname().trim());
			pstmt.setString(4, obj.getDescription());
			pstmt.setString(5, obj.getCreatedUserId().trim());
			pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
			pstmt.setString(7, obj.getIslab());
			pstmt.setString(8, obj.getSubType());
			pstmt.setString(9, obj.getLocationId());
			pstmt.setString(10, obj.getSpecialization());
			pstmt.setString(11, obj.getIsLanguage());
			pstmt.setString(12, obj.getSubjectCode());
			pstmt.setString(13, obj.getHiddensubjectId().trim());
			
			
			//("pstmt,update" +pstmt);
			
			
			int res = pstmt.executeUpdate();
			if (res > 0) {
				HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Settings","Subject","Update",pstmt.toString(),userLoggingsVo);
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateSubjectDetails Ending");
		return result;
	}

	public synchronized boolean validateSubjectWithClass(AddSubjectVo vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Starting");
		Connection conn = null;
		boolean flagSubExist = false;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			int i = 0;
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SubjectSqlUtils.VALIDATE_CLASS_SUBJECT);
			pstmt.setString(1, vo.getSubjtname());
			pstmt.setString(2, vo.getClassname());
			pstmt.setString(3, vo.getLocationId());
			//(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
			if (i > 0) {
				flagSubExist = true;
			}
			else{
				flagSubExist = false;
			}
			 
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateSubjectWithClass Ending");

		return flagSubExist;
	}

	public synchronized String getpath(String classname,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getpath Starting");

		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet resultSet = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.ViewAllSubjectsPath);
			pstmt.setString(1, classname);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				path = resultSet.getString("syllabous");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getpath Ending");
		return path;
	}

	public String DdownloadsyllabuspathDao(String subjectid,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Starting");

		
		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_SYLLABUS_PATH);
			pstmt.setString(1, subjectid);
			//("999999999999999999999999:"+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				path = rs.getString("syllabous");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally{
	    	 try {
	    		 if(rs!=null && (!rs.isClosed())){
	    			 rs.close();
	    	 }
	    	    if(pstmt!=null && (!pstmt.isClosed())){
	    	    	pstmt.close();
	    	      }
	    	   if(conn!=null && (!conn.isClosed())){
	    		  conn.close();
	    	     }
	    	  } catch (SQLException e) {
				 e.printStackTrace();
		      }
	     }
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadsyllabuspathDao Ending");
		
		return path;
	}

	@Override
	public String validateSubName(AddSubjectForm form1,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : validateSubName : Starting");

		PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		String subname_available =null;
		int count = 0;
        String value=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			sub_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_SUB_NAME);
			

			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getSubjtname());
			sub_pstmt.setString(3, form1.getLocationId());
			sub_pstmt.setString(4, form1.getSpecialization());
			//("subject name:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {

				count = sub_rs.getInt(1);
				value=sub_rs.getString("isActive");

			}

			if (count > 0 && value.equalsIgnoreCase("N")) {
				
				subname_available="inactive";
				
			}
			
			else if(count > 0 && value.equalsIgnoreCase("Y")){
				subname_available ="true";
			}
			
			else {

				subname_available ="false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {

					sub_pstmt.close();
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
				+ " Control in AddSubjectDaoImpl : validateSubName : Ending");

		return subname_available;
	}

	public List<ViewallSubjectsVo> getLangauageOnClassBased(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getLangauageOnClassBased : Starting");
		 List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
		
		 
		 PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			sub_pstmt = conn.prepareStatement(SQLUtilConstants.getLangauageOnClassBased);
			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getLocationId());
			sub_pstmt.setString(3, form1.getSpecialization());
			//("subject name:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {
				ViewallSubjectsVo list=new ViewallSubjectsVo();
				list.setSubjectname(sub_rs.getString("subjectName"));
				list.setSubjectCode(sub_rs.getString("subjectID"));
				subjectlist.add(list);
			}

			

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {

					sub_pstmt.close();
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
				+ " Control in AddSubjectDaoImpl : getLangauageOnClassBased : Ending");

		return subjectlist;
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization,UserLoggingsPojo custdetails, String status) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : subjectdetailsOnchangeListingPage  Starting");

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection conn = null;
		int sno=0;
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.GET_SUB_LIST);

			pst.setString(1, locationid);
			pst.setString(2, classname);
			pst.setString(3, specialization);
		    pst.setString(4, status);
			
			//(pst);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				
				String specCode=resultSet.getString("specialization");
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				/*obj.setTotalMarks(resultSet.getInt("totalMarks"));
				obj.setPassMarks(resultSet.getInt("passMarks"));*/
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setLocationId(resultSet.getString("locationId"));
				obj.setRemark(resultSet.getString("remark"));
				obj.setSubjectCode(resultSet.getString("Sub_Code"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("-");
				}
			
				allsubject.add(obj);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : subjectdetailsOnchangeListingPage  Ending");

		return allsubject;


	}

	@Override
	public ArrayList<ViewallSubjectsVo> labdetails(String schoolLocation,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : labdetails Starting");
		ArrayList<ViewallSubjectsVo> all_lab = new ArrayList<ViewallSubjectsVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.VIEW_LAB_DETAILS);
			
			resultSet = pstmt.executeQuery();
			

			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo(); 
				
				String specCode=resultSet.getString("Specialization_name");
			
				obj.setSubjectname(resultSet.getString("Lab_Name"));
			
				obj.setDescription(resultSet.getString("Description"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				
				obj.setLocationName(resultSet.getString("Location_Name"));
			
				obj.setLabCode(resultSet.getString("Lab_Code"));
				//("specCode value is>>>>"+specCode);
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("General");
				}
			
				all_lab.add(obj);
			}
			//(all_lab.size());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: labdetails Ending");
		return all_lab;

	}
	public synchronized boolean validateLabWithClass(AddSubjectForm addSubjectPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateLabWithClass Starting");
		Connection conn = null;
		boolean flagLabExist = false;
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.VALIDATE_CLASS_LAB);
			pstmt.setString(1, addSubjectPojo.getSubjtname());
			pstmt.setString(2, addSubjectPojo.getClassname());
			//(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
			if (i > 0) {
				flagLabExist = true;
			}
			else{
				flagLabExist = false;
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: validateLabWithClass Ending");

		return flagLabExist;
	}


	@Override
	public synchronized boolean addLab(AddLabVO obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addLab Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean status = false;
		try {
			 //("..............");
		
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SubjectSqlUtils.INSERT_LAB_FORM);
				String id=new IDGenerator().getPrimaryKeyID("laboratory_details",userLoggingsVo);
				//(id);
				pstmt.setString(1,id);
				//("location name....."+obj.getLocationId());
				pstmt.setString(2, obj.getLocationId());
				//("class name....."+obj.getClassname());
				pstmt.setString(3, obj.getClassname());
				pstmt.setString(4, obj.getSubject());
				pstmt.setString(5, obj.getLab_name());
				pstmt.setString(6, obj.getSpecialization());
				pstmt.setString(7, obj.getDescription());
				pstmt.setString(8, obj.getSubjectCode());
				pstmt.setString(9, "Y");
				//lab_id,School_Name,Class_Name,SUBJECT,Lab_Name,Specialization,Description,Lab_Code	
				result = pstmt.executeUpdate();
				//(pstmt);
                 
				if (result > 0) {
					HelperClass.recordLog_Activity(obj.getLog_audit_session(),"Settings","Laboratory","Insert",pstmt.toString(),userLoggingsVo);
					status = true;
				} else {
					status = false;
				}

			
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : addLab Ending");

		return status;
	}

	@Override
	public String DeleteLab(String[] idList, String locationList, String reason,String statuss,UserLoggingsPojo userLoggingsVo) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : DeleteLab Starting");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	
		String status = null;
		int count = 0;
		int resultcount = 0;
		int result = 0;
		int unmaplen = 0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			if(statuss.equalsIgnoreCase("Y")){
				for(int i=0;i<idList.length;i++)
				{
					pstmt = conn.prepareStatement(SubjectSqlUtils.DELETE_LAB_DETAILS);
					
					pstmt.setString(1, statuss);
					pstmt.setString(2, reason);
					pstmt.setString(3, idList[i]);
					count = pstmt.executeUpdate();
				
					if (count > 0) {
						status = "activetrue";
						resultcount++;
						HelperClass.recordLog_Activity(userLoggingsVo.getSessionId(), "Master", "Laboratory",statuss, pstmt.toString(), conn);
					}
				}
				
			}else if(statuss.equalsIgnoreCase("N")){
				for(int i=0;i<idList.length;i++)
				{
					/*PreparedStatement getstatus = conn.prepareStatement("SELECT COUNT(*) FROM `campus_syllabus_details` WHERE `loc_id` = ? AND `sub_lab_id` = ? AND `isApplicable` = 'L'");
					getstatus.setString(1,locationList);
					getstatus.setString(2,idList[i]);
					ResultSet statusrs = getstatus.executeQuery();
					System.out.println("getstatus "+getstatus);
					while(statusrs.next()){
						result = statusrs.getInt(1);
					}
					statusrs.close();
					getstatus.close();
					if(result == 0){
						getstatus = conn.prepareStatement("SELECT COUNT(*) FROM `campus_studentwise_mark_details` WHERE `location_id` = ? AND `sub_id` = ?");
						getstatus.setString(1,locationList);
						getstatus.setString(2,idList[i]);
						statusrs = getstatus.executeQuery();
						while(statusrs.next()){
							result = statusrs.getInt(1);
						}
						statusrs.close();
						getstatus.close();
						if(result == 0){
							unmaplen++;*/
							pstmt = conn.prepareStatement(SubjectSqlUtils.DELETE_LAB_DETAILS);
							pstmt.setString(1, statuss);
							pstmt.setString(2, reason);
							pstmt.setString(3, idList[i]);
							count = pstmt.executeUpdate();
							if (count > 0) {
								status = "inactivetrue";
								resultcount++;
								HelperClass.recordLog_Activity(userLoggingsVo.getSessionId(),"Master","Laboratory",statuss,pstmt.toString(),conn);
							}
						}
					}
				
				/*if(resultcount!=0 && resultcount == unmaplen){
					conn.commit();
					status = "inactivetrue";
				}else if(unmaplen!=0 && unmaplen!=resultcount){
					conn.rollback();
					status = "inactivefail";
				}
				else{
					status = "inactivefalse";
				}*/
				
			if(resultcount == idList.length){
				conn.commit();
			}else{
				conn.rollback();
				status = "false";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: DeleteLab Ending");
		return status;

	}

	@Override
	public AddSubjectForm getLabDetails(AddSubjectForm obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getLabDetails Starting");
		AddSubjectForm all_lab = new AddSubjectForm();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(SubjectSqlUtils.GET_SINGLE_LAB_DETAILS);
			pstmt.setString(1, obj.getLab_id());
			//lab_id,School_Name,Class_Name,Lab_Name,Total_Marks,Specialization,Pass_Marks,Description,Lab_Code,Syllabus
			resultSet = pstmt.executeQuery();
           //("get single lab details query"+pstmt);
           //("result set "+resultSet);
			while (resultSet.next()) {

				String specCode=resultSet.getString("Specialization");
				all_lab.setLab_id(resultSet.getString("lab_id"));
				all_lab.setLocationName(resultSet.getString("School_Name"));
				
				all_lab.setClassname(resultSet.getString("Class_Name"));
				all_lab.setSubjtname(resultSet.getString("Subject"));
				all_lab.setLab_name(resultSet.getString("Lab_Name"));
				all_lab.setSubjectCode(resultSet.getString("Lab_Code"));
				all_lab.setTotalMarks(resultSet.getInt("Total_Marks"));
				
				all_lab.setPassMarks(resultSet.getInt("Pass_Marks"));
				all_lab.setDescription(resultSet.getString("Description"));
				all_lab.setSyllabus(resultSet.getString("Syllabus"));
				//("Specialization>>>"+resultSet.getString("Specialization"));
				
				if(!specCode.equalsIgnoreCase("-")){
					PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name,Specialization_Id from campus_class_specialization where Specialization_Id=? ");
					pssp.setString(1, specCode);
					ResultSet rssp=pssp.executeQuery();
					//("specialization NAME"+pssp);
					while(rssp.next()){
						all_lab.setSpecialization(rssp.getString("Specialization_name"));
						all_lab.setSpecId(rssp.getString("Specialization_Id"));
					}

				}
				else{
					all_lab.setSpecialization("General");

				}
			//("get specialization>>>"+all_lab.getSpecialization());
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
				+ " Control in AddSubjectDaoImpl: getLabDetails Ending");
		return all_lab;
	}

	@Override
	public boolean updateLabDetails(AddLabVO addSubjectForm,UserLoggingsPojo userLoggingsVo) {
        //("it is coming inside fo update or not");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateLabDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
	//Lab_Name=? ,Class_Name=?, Description=? ,School_Name=?,Specialization=?,Lab_Code=?,subject=?
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.UPDATE_LAB);
           
			pstmt.setString(1, addSubjectForm.getSubject());
            pstmt.setString(2, addSubjectForm.getLab_name());
			pstmt.setString(3, addSubjectForm.getClassname());
			pstmt.setString(4, addSubjectForm.getDescription());
			pstmt.setString(5, addSubjectForm.getLocationId().trim());
			pstmt.setString(6, addSubjectForm.getSpecialization());
		    pstmt.setString(7, addSubjectForm.getSubjectCode());
			pstmt.setString(8, addSubjectForm.getHiddenlocationid());
			
			//("addSubjectForm.getSpecialization()"+addSubjectForm.getSpecialization());
			//("update lab query" +pstmt);
			
			
			int res = pstmt.executeUpdate();
			if (res > 0) {
				HelperClass.recordLog_Activity(addSubjectForm.getLog_audit_session(),"Settings","Laboratory","Update",pstmt.toString(),userLoggingsVo);
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: updateLabDetails Ending");
		return result;
	}

	public List<AddSubjectForm> labdetailsOnchangeListingPage(String locationid, String classname, String specialization, UserLoggingsPojo custdetails, String status) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : labdetailsOnchangeListingPage  Starting");

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection conn = null;
	
		ArrayList<AddSubjectForm> all_lab = new ArrayList<AddSubjectForm>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement("SELECT  ccs.Lab_Name,cl.Location_Name,ccd.classdetails_name_var,ccd.classdetail_id_int,ccs.* ,Specialization_name,csub.subjectName FROM laboratory_details  ccs JOIN campus_classdetail ccd ON ccs.Class_Name=ccd.classdetail_id_int  AND ccd.locationId=ccs.School_Name  JOIN campus_location cl ON ccs.School_Name=cl.Location_Id JOIN  campus_subject csub ON ccs.subject=csub.subjectID LEFT JOIN campus_class_specialization spe ON spe.Specialization_Id = ccs.Specialization  WHERE ccs.School_Name LIKE ?  AND ccd.classdetail_id_int LIKE ?  AND ccs.Specialization LIKE ? AND ccs.status=? ORDER BY cl.Location_Name,LENGTH(ccd.classdetail_id_int),ccd.classdetail_id_int,  spe.Specialization_name");
			pst.setString(1, locationid);
			pst.setString(2, classname);
			pst.setString(3, specialization);
			pst.setString(4, status);
			
			//("pst query in listing"+pst);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				
				AddSubjectForm obj = new AddSubjectForm();
				String specCode=resultSet.getString("specialization");
				////("specCode value>>>>>>"+specCode);
				obj.setLab_id(resultSet.getString("lab_id"));
		  		obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSubjtname(resultSet.getString("subjectName"));
				////("subjectName value>>>>>>"+resultSet.getString("subjectName"));
				obj.setLab_name(resultSet.getString("Lab_Name"));
				obj.setSyllabus(resultSet.getString("Syllabus"));
				////("syllabus print.....>>>"+resultSet.getString("Syllabus"));
				obj.setTotalMarks(resultSet.getInt("Total_Marks"));
				obj.setPassMarks(resultSet.getInt("Pass_Marks"));
				obj.setDescription(resultSet.getString("Description"));
				obj.setSubjectCode(resultSet.getString("Lab_Code"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecialization(rssp.getString("Specialization_name"));
				}
				}
				else{
					obj.setSpecialization("-");
				}
			
				all_lab.add(obj);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : labdetailsOnchangeListingPage  Ending");

		return all_lab;


	}

	@Override
	public String validateLabName(AddSubjectForm form1 , UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : validateLabName : Starting");

		PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		String subname_available =null;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			sub_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_LAB_NAME);
			sub_pstmt.setString(1, form1.getClassname());
			sub_pstmt.setString(2, form1.getSubjtname());
			sub_pstmt.setString(3, form1.getLocationId());
			sub_pstmt.setString(4, form1.getLab_name());
			System.out.println("lab  name validating query....:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {
				count = sub_rs.getInt("subject");
			}

			if (count > 0) {
				subname_available ="true";

			} else {
				subname_available ="false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {
 
					sub_pstmt.close();
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
				+ " Control in AddSubjectDaoImpl : validateLabName : Ending");

		return subname_available;
	}

	public String DdownloadLabsyllabuspathDao(String subjectid, UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadLabsyllabuspathDao Starting");

		
		String path = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_LAB_SYLLABUS_PATH); 
			pstmt.setString(1, subjectid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				path = rs.getString("Syllabus");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally{
	    	 try {
	    		 if(rs!=null && (!rs.isClosed())){
					rs.close();
	    	 }
	    	    if(pstmt!=null && (!pstmt.isClosed())){
	    	    	pstmt.close();
	    	      }
	    	   if(conn!=null && (!conn.isClosed())){
	    		  conn.close();
	    	     }
	    	  } catch (SQLException e) {
				 e.printStackTrace();
		      }
	     }
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: DdownloadLabsyllabuspathDao Ending");
		
		return path;
	}

	@Override
	public String getSubjectName(String subjectid) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getSubjectName Starting");

		
		String subjectname = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SubjectSqlUtils.GET_SYLLABUS_PATH);
			pstmt.setString(1, subjectid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				
				subjectname = rs.getString("subjectName");
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
	    	 try {
	    		 if(rs!=null && (!rs.isClosed())){
					rs.close();
	    	 }
	    	    if(pstmt!=null && (!pstmt.isClosed())){
	    	    	pstmt.close();
	    	      }
	    	   if(conn!=null && (!conn.isClosed())){
	    		  conn.close();
	    	     }
	    	  } catch (SQLException e) {
				 e.printStackTrace();
		      }
	     }
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl: getSubjectName Ending");
		
		return subjectname;
	}

	@Override
	public List<ViewallSubjectsVo> getSubDetailsList(String locationid, String classname, String specialization,
			UserLoggingsPojo userLoggingsVo, String status) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getSubDetailsList  Starting");

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Connection conn = null;
		int sno=0;
		ArrayList<ViewallSubjectsVo> allsubject = new ArrayList<ViewallSubjectsVo>();
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_SUB_LIST_STATUS);

			pst.setString(1, locationid);
			pst.setString(2, classname);
			pst.setString(3, specialization);
		    pst.setString(4, status);
			//("GET_SUB_LIST_STATUS -->>"+pst);
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				sno++;
				ViewallSubjectsVo obj = new ViewallSubjectsVo();
				
				String specCode=resultSet.getString("specialization");
				obj.setSubjectid(resultSet.getString("subjectID"));
				obj.setSubjectname(resultSet.getString("subjectName").trim());
				obj.setPath(resultSet.getString("syllabous"));
				obj.setDescription(resultSet.getString("decription"));
				obj.setClassname(resultSet.getString("classdetails_name_var"));
				obj.setSno(sno);
				
				obj.setLocationName(resultSet.getString("Location_Name"));
				obj.setLocationId(resultSet.getString("locationId"));
				obj.setRemark(resultSet.getString("remark"));
				obj.setSubjectCode(resultSet.getString("Sub_Code"));
				if(!specCode.equalsIgnoreCase("-")){
				PreparedStatement pssp=conn.prepareStatement("SELECT Specialization_Name from campus_class_specialization where Specialization_Id=? ");
				pssp.setString(1, specCode);
				ResultSet rssp=pssp.executeQuery();
				while(rssp.next()){
					obj.setSpecializationName(rssp.getString("Specialization_name"));
				}
				
				}
				else{
					obj.setSpecializationName("-");
				}
			
				allsubject.add(obj);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getSubDetailsList  Ending");

		return allsubject;
	}

	@Override
	public String updateSyllabusPath(String realPath,String locationid,String classId,UserLoggingsPojo userLoggingsVo,String academic_Year,String type,String specialization,String subjectId,String syllabus_Id) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : updateSyllabusPath  Starting");

		PreparedStatement pst = null;
		String result = " ";
		Connection conn = null;
		
		try {
			//(syllabus_Id);
			if( ! syllabus_Id.equalsIgnoreCase("-")){
    
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pst = conn.prepareStatement(SQLUtilConstants.UPDATE_SUB_SYLLABUS);

				pst.setString(1, realPath);
				pst.setString(2, syllabus_Id);
			    
				
				//(pst);
				int executeUpdate = pst.executeUpdate();
				//(executeUpdate);
				if(executeUpdate > 0){
					result="success";
				}
				else{
					result="fail";
				}
			}
			else{
				//("daoImpl : "+ realPath+ locationid+ classId+  subjectId+  userLoggingsVo);
				
				// syllabus_url , sub_lab_id , accy_id ,  Loc_id  , class_id ,isApplicable,spec_id
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pst = conn.prepareStatement(SQLUtilConstants.INSERT_SUB_SYLLABUS);

				pst.setString(1, realPath);
				pst.setString(2, subjectId.trim());
				pst.setString(3, academic_Year);
				pst.setString(4, locationid);
				pst.setString(5, classId.trim());
				pst.setString(6, type);
				pst.setString(7, specialization);
			    
				
				//(pst);
				int executeUpdate = pst.executeUpdate();
				//(executeUpdate);
				if(executeUpdate > 0){
					result="success";
				}
				else{
					result="fail";
				}
			}
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : updateSyllabusPath  Ending");
		
		
		return result;
	}

	@Override
	public String syllabusDownload(String locationid, String class_name, String sub_Id, String academicYear2,
			UserLoggingsPojo userLoggingsVo	) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : updateSyllabusPath  Starting");

		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		String resultPath = "";
		Connection conn = null;
		ResultSet rst=null;
		int count=0;
		
		try {
			//("daoImpl : "+ locationid+ class_name+  sub_Id+ academicYear2+ userLoggingsVo);
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_SYLLABUS_URL_COUNT);
			pst2 = conn.prepareStatement(SQLUtilConstants.GET_SYLLABUS_URL);

			pst.setString(1, academicYear2.trim());
			pst.setString(2, locationid.trim());
			pst.setString(3, class_name.trim());
		    pst.setString(4, sub_Id.trim());
			//(pst);
			
			rst = pst.executeQuery();
			while(rst.next()){
			    count = rst.getInt("count");
			    }
			rst.close();
			if(count>0){
				pst2.setString(1, academicYear2.trim());
				pst2.setString(2, locationid.trim());
				pst2.setString(3, class_name.trim());
			    pst2.setString(4, sub_Id.trim());
			    
			    rst = pst2.executeQuery();
				while(rst.next()){
					resultPath = rst.getString("syllabus_url");
				    }
			  }
			else{
				resultPath="Sylabus Not Available";
			}
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : updateSyllabusPath  Ending");
		
		return resultPath;
	}


	@Override
	public String checkDuplicateSubCount(AddSubjectForm form1,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : checkDuplicateSubCount : Starting");

		PreparedStatement sub_pstmt = null;
		ResultSet sub_rs = null;

		String subname_available =null;
		int count = 0;
        String value=null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			sub_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_SUB_NAME_COUNT);
			
			sub_pstmt.setString(1, form1.getLocationId());
			sub_pstmt.setString(2, form1.getSpecialization());
			sub_pstmt.setString(3, form1.getSubjectCode());
			//("subject name:" +sub_pstmt);
			sub_rs = sub_pstmt.executeQuery();

			while (sub_rs.next()) {

				count = sub_rs.getInt(1);
				value=sub_rs.getString("isActive");

			}

			if (count > 0 && value.equalsIgnoreCase("N")) {
				
				subname_available="inactive";
				
			}
			
			else if(count > 0 && value.equalsIgnoreCase("Y")){
				subname_available ="true";
			}
			
			else {

				subname_available ="false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (sub_rs != null && (!sub_rs.isClosed())) {

					sub_rs.close();
				}
				if (sub_pstmt != null && (!sub_pstmt.isClosed())) {

					sub_pstmt.close();
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
				+ " Control in AddSubjectDaoImpl : checkDuplicateSubCount : Ending");

		return subname_available;
	}


	@Override
	public String getClassName(String classId, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getClassName  Starting");

		PreparedStatement pst = null;
		String result = "";
		Connection conn = null;
		ResultSet rst = null ;
		try
		{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst = conn.prepareStatement(SQLUtilConstants.GET_CLASS_NAME);
			pst.setString(1, classId.trim());
			rst = pst.executeQuery();
			while(rst.next()){
			      result = rst.getString("classdetails_name_var");
			    }
		 }
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : getClassName  Ending");
		
		return result;
	}

	@Override
	public String checkLabCode(SubjectPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : checkLabCode  Starting");

		PreparedStatement pst = null;
		int result =0;
		Connection conn = null;
		ResultSet rst = null ;
		String status = "valid";
		try
		{
			conn = JDBCConnection.getSeparateConnection(pojo.getDbdetails());
			pst = conn.prepareStatement(SQLUtilConstants.CHECK_LAB_CODE);
			pst.setString(1,pojo.getLocId());
			pst.setString(2,pojo.getLabCode());
			rst = pst.executeQuery();
			while(rst.next()){
			      result = rst.getInt(1);
			}
			if(result > 0){
				status = "invalid";
			}
		 }
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectDaoImpl : checkLabCode  Ending");
		
		return status;
	}

}
