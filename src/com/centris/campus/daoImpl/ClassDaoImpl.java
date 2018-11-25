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
import com.centris.campus.dao.ClassDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.ClassUtilsConstants;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LocationVO;

public class ClassDaoImpl implements ClassDao{
	private static final Logger logger = Logger.getLogger(ClassDaoImpl.class);
	

	public synchronized List<ClassPojo> getClassDetails(String schoolLocation,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :getClassDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet getStreamDetailsRs=null;
		List<ClassPojo> getClassDetailsList=new ArrayList<ClassPojo>();
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS);
				pstmt.setString(1, schoolLocation);
			 
		    getStreamDetailsRs=pstmt.executeQuery();
			
		while(getStreamDetailsRs.next()){
			ClassPojo masterPojo=new ClassPojo();
			masterPojo.setClassId(getStreamDetailsRs.getString("classdetail_id_int"));
			masterPojo.setClassName(getStreamDetailsRs.getString("classdetails_name_var"));
			masterPojo.setStreamName(getStreamDetailsRs.getString("classstream_name_var"));
			masterPojo.setLocationName(getStreamDetailsRs.getString("Location_Name"));
			masterPojo.setLocationId(getStreamDetailsRs.getString("locationId"));

			if(getStreamDetailsRs.getString("isActive").equalsIgnoreCase("Y")){
				masterPojo.setStatus("Active");
			}
			else if(getStreamDetailsRs.getString("isActive").equalsIgnoreCase("N")){
			    masterPojo.setStatus("InActive");
			}
			
			if(getStreamDetailsRs.getString("remarks")=="" || getStreamDetailsRs.getString("remarks")==null){
				masterPojo.setRemarks("");
			}else{
				masterPojo.setRemarks(getStreamDetailsRs.getString("remarks"));
			}
			
			getClassDetailsList.add(masterPojo);
			 
		}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if(getStreamDetailsRs!=null && !getStreamDetailsRs.isClosed()){
						getStreamDetailsRs.close();
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
		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ClassDaoImpl :getClassDetails  Starting");
	return getClassDetailsList;

	}
	
	public synchronized String createClass(ClassPojo classPojo,String createUser, String classcode,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : createClass  Starting");
		
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			  if(!classPojo.getStatus().equals("update")){
			
			//("classPojo.getLocationId() -->>"+classPojo.getLocationId());	
			pstmt = conn.prepareStatement(ClassUtilsConstants.CREATE_CLASS);
			if(classPojo.getLocationId().equalsIgnoreCase("all")){
				locationList = new  LocationBD().getLocationDetails(userLoggingsVo);
				//(locationList.size());
			}
			if(locationList.size()!=0){
				for(int j=0;j<locationList.size();j++){
			pstmt.setString(1, "CCD"+classPojo.getClassId());
			pstmt.setString(2, classPojo.getStreamId());
			pstmt.setString(3, classPojo.getClassName());
			pstmt.setString(4, classPojo.getCreateUser());
			pstmt.setString(5, locationList.get(j).getLocation_id());
		    //("CREATE_CLASS first-->>"+pstmt);
			count = pstmt.executeUpdate();
			if (count > 0) {
				HelperClass.recordLog_Activity(classPojo.getLog_audit_session(),"Settings","Class Details","Insert",pstmt.toString(),userLoggingsVo);
			}
				}
			}
			else{
				pstmt.setString(1, "CCD"+classPojo.getClassId());
				pstmt.setString(2, classPojo.getStreamId());
				pstmt.setString(3, classPojo.getClassName());
				pstmt.setString(4, classPojo.getCreateUser());
				pstmt.setString(5, classPojo.getLocationId());
				 //("CREATE_CLASS second-->>"+pstmt);
				count = pstmt.executeUpdate();
			}
			if (count > 0) {
				HelperClass.recordLog_Activity(classPojo.getLog_audit_session(),"Settings","Class Details","Insert",pstmt.toString(),userLoggingsVo);
				status = "Class Added Successfully";
			}
		
			  }else{
				        pstmt = conn.prepareStatement("UPDATE campus_classdetail SET classstream_id_int=?,modifyuser=?,modifydate=? WHERE classdetails_name_var=? AND locationId=? AND classstream_id_int=?");
					 
						pstmt.setString(1, classPojo.getStreamId());
						pstmt.setString(2, classPojo.getCreateUser());
						pstmt.setTimestamp(3, HelperClass.getCurrentTimestamp());
						pstmt.setString(4, classPojo.getSecDetailName());
						pstmt.setString(5, classPojo.getLocationId());
						pstmt.setString(6, classPojo.getHiddenStreamId());
						//("UPDATE_CLASS forth-->>"+pstmt); 
						
						count = pstmt.executeUpdate();
					 
					if (count > 0) {
						HelperClass.recordLog_Activity(classPojo.getLog_audit_session(),"Settings","Class Details","Update",pstmt.toString(),userLoggingsVo);
						status = "Class Updated Successfully";
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : createClass  Starting");
		return status;
	}
	
	@Override
	public synchronized List<ClassPojo> getStreamDetailDao(String school, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : getStreamDetailDao  Starting");

		List<ClassPojo> streamList = new ArrayList<ClassPojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_STREAM);
			pstmt.setString(1, school);
			//("GET_CLASS_STREAM -->>"+pstmt);
		    rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ClassPojo streamPojo = new ClassPojo();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamList.add(streamPojo);
				
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
			    if(rs!=null && !rs.isClosed()){
			    	rs.close();
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
				+ " Control in ClassDaoImpl :getStreamDetailDao  Ending");
		return streamList;
	}
	
	public synchronized String classNameCheck(ClassPojo classPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : classNameCheck  Starting");
		String status = null,value=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ClassUtilsConstants.CHECK_CLASS_NAME);

			pstmt.setString(1, classPojo.getClassName().trim());
			pstmt.setString(2, classPojo.getStreamId());
			if(!classPojo.getLocationId().equalsIgnoreCase("all"))
			pstmt.setString(3, classPojo.getLocationId());
			else
			pstmt.setString(3,"%%");
			//("CHECK_CLASS_NAME -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("Classname");
				value = rs.getString("isActive");
			}
            
			if (count > 0 && value.equalsIgnoreCase("N")) {
				status = "inactive";
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
				status = "true";
			}
			else {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : classNameCheck  Starting");

		//(status);
		return status;

	}
	
	public synchronized boolean updateclassNameCheck(ClassPojo classPojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateclassNameCheck  Starting");
		boolean status = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		ResultSet rs = null;
	
		int count = 0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(classPojo.getCustid());
			pstmt = conn.prepareStatement(ClassUtilsConstants.CHECK_UPDATE_CLASS_NAME);

			pstmt.setString(1, classPojo.getSecDetailName());
			pstmt.setString(2, classPojo.getStreamId());
			pstmt.setString(3, classPojo.getUpdateClassCode());
			pstmt.setString(4, classPojo.getLocationId());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("Classname");
			}

			if (count > 0) {
				status = true;
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateclassNameCheck  Starting");

		return status;

	}
	
	public synchronized ClassPojo editClass(String classCode,String locId,UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : editClass  Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClassPojo classPojo = new ClassPojo();
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(ClassUtilsConstants.EDIT_CLASS_BY_CLASS);
			pstmt.setString(1, classCode);
			pstmt.setString(2, locId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
                classPojo.setClassId(rs.getString("classdetail_id_int").trim());
				classPojo.setStreamId(rs.getString("classstream_id_int").trim());
				classPojo.setClassName(rs.getString("classdetails_name_var"));
				classPojo.setLocationId(rs.getString("locationId"));
				classPojo.setStatus("update");
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : editClass  Starting");
		return classPojo;
	}
	
	@Override
	public synchronized boolean deleteClass(ClassPojo classPojo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :deleteClass  Starting");
		int val = 0;
		PreparedStatement pstmt = null;
		boolean status = false;
		Connection conn = null;
		String name=null;
		try { 
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ClassUtilsConstants.UPDATE_CLASS_DETAIL_BY_STATUS);
			
			for(int i=0;i<classPojo.getClassIds().length;i++){
				if(classPojo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(classPojo.getOtherReason()=="" || classPojo.getOtherReason()==null){
						 pstmt.setString(2, classPojo.getInactiveReason()); 
					 }
					 else{
						 pstmt.setString(2, classPojo.getOtherReason());
					 }
					 name="InActive";
				 }
				 else{
					 pstmt.setString(1, "Y");
					 if(classPojo.getOtherReason()=="" || classPojo.getOtherReason()==null){
						 pstmt.setString(2, classPojo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, classPojo.getOtherReason());
					 }
					 name="Active";
				 }
				pstmt.setString(3,classPojo.getClassIds()[i]);
				pstmt.setString(4, classPojo.getLocIds()[i]);
				val = pstmt.executeUpdate();
				
				if(val > 0){
					HelperClass.recordLog_Activity(classPojo.getLog_audit_session(),"Settings","Class Details",name,pstmt.toString(),userLoggingsVo);
				}
			}
			
			if(val > 0){
				status=true;
			}else {
				status=false;
			}
		} 
		catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn!=null && !conn.isClosed()) {
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
				+ " Control in ClassDaoImpl :deleteClass Ending");
	return status;

	}
	public synchronized boolean updateClass(ClassPojo classPojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateClass  Starting");
		boolean status = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(ClassUtilsConstants.UPDATE_CLASS_DETAIL);

					pstmt.setString(1, classPojo.getClassName());
					
					pstmt.setString(2, classPojo.getStreamId());
					

					count = pstmt.executeUpdate();

					if (count > 0) {
						status = true;
					}


		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
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
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : updateClass  Starting");
		return status;

	}
	
	@Override
	public synchronized List<ClassPojo> searchClassDetails(String searchText,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :searchClassDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int siNo = 0;
		String searchterm=searchText.split(",")[0];
		String school=searchText.split(",")[1];
		if(school.equalsIgnoreCase("all")){
			school="%%";
		}
		List<ClassPojo> getClassDetailsList = new ArrayList<ClassPojo>();
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn
					.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS_BY_SEARCH_ID);
			pstmt.setString(1, "%" + searchterm + "%");
			pstmt.setString(2, "%" + searchterm + "%");
			pstmt.setString(3, "%" + searchterm + "%");
			pstmt.setString(4, school);
			
			 rs = pstmt.executeQuery();

			while (rs.next()) {
				ClassPojo coursePojo = new ClassPojo();
				siNo++;
				coursePojo.setClassId(rs.getString("classdetail_id_int"));
				coursePojo.setClassName(rs.getString("classdetails_name_var"));
				coursePojo.setStreamName(rs.getString("classstream_name_var"));
				coursePojo.setLocationName(rs.getString("Location_Name"));
				getClassDetailsList.add(coursePojo);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null && rs.isClosed()){
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :searchClassDetails  Starting");

		return getClassDetailsList;
	}

	public static List<ClassPojo> getClassDetailsOnChangeFunction(String streamId, String locationid, String status,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl :getClassDetailsOnChangeFunction Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet getStreamDetailsRs=null;

		List<ClassPojo> getClassDetailsList=new ArrayList<ClassPojo>();
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(ClassUtilsConstants.GET_CLASS_DETAILS_BY_JS);
				pstmt.setString(1, locationid);
				pstmt.setString(2, streamId); 
				pstmt.setString(3, status);
			//("GET_CLASS_DETAILS_BY_JS -->>"+pstmt);
			 getStreamDetailsRs=pstmt.executeQuery();
			
		while(getStreamDetailsRs.next()){
			ClassPojo masterPojo=new ClassPojo();
			masterPojo.setClassId(getStreamDetailsRs.getString("classdetail_id_int"));
			masterPojo.setClassName(getStreamDetailsRs.getString("classdetails_name_var"));
			masterPojo.setStreamName(getStreamDetailsRs.getString("classstream_name_var"));
			masterPojo.setLocationName(getStreamDetailsRs.getString("Location_Name"));
			masterPojo.setLocationId(getStreamDetailsRs.getString("locationId"));
			
			if(getStreamDetailsRs.getString("isActive").equalsIgnoreCase("Y")){
				masterPojo.setStatus("Active");
			}else{
				masterPojo.setStatus("InActive");
			}
			if(getStreamDetailsRs.getString("Remarks")==""|| getStreamDetailsRs.getString("Remarks")==null){
				masterPojo.setRemarks("");
			}else{
				masterPojo.setRemarks(getStreamDetailsRs.getString("Remarks"));
			 }
			
			getClassDetailsList.add(masterPojo);
			 
		}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if(getStreamDetailsRs!=null && !getStreamDetailsRs.isClosed()){
						getStreamDetailsRs.close();
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
		
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in ClassDaoImpl :getClassDetailsOnChangeFunction  Starting");
	return getClassDetailsList;

	}

	public String validateDuplicateLocation(ClassPojo classPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassDaoImpl : validateDuplicateLocation : Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String locame_available = null,value=null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement("SELECT COUNT(ccd.locationId) AS COUNT,ccd.isActive FROM campus_classdetail ccd JOIN campus_location cl ON cl.Location_Id=ccd.locationId JOIN campus_classstream ccs ON ccs.classstream_id_int=ccd.classstream_id_int AND cl.Location_Id=ccs.locationId WHERE ccd.classdetails_name_var=? AND ccd.locationId=? ");
			pstmt.setString(1,classPojo.getClassName());
			pstmt.setString(2,classPojo.getLocationId());
			//("validateDuplicateLocation -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT");
				value = rs.getString("isActive");
			}
			
			if (count > 0 && value.equalsIgnoreCase("N") && classPojo.getUpdateClassCode()=="") {
				locame_available = "inactive";
			}
			else if (count > 0 && value.equalsIgnoreCase("Y") && classPojo.getUpdateClassCode()=="") {
				locame_available = "true";
			} 
			else {
				locame_available = "false";
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle);
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
				+ " Control in ClassDaoImpl : validateDuplicateLocation : Ending");

		return locame_available;
	}

	

}
