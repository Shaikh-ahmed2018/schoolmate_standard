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
import com.centris.campus.dao.SectionDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.SectionUtilsConstants;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.SectionVO;


public class SectionDaoImpl implements SectionDao{
	
	private static final Logger logger = Logger
			.getLogger(SectionDaoImpl.class);
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	java.util.Date today = new java.util.Date();
	java.sql.Timestamp time_stamp = new java.sql.Timestamp(today.getTime());

	@Override
	public synchronized String insertCampusClassSectionDao(SectionPojo campusClassSectionPojo,UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:insertCampusClassSectionDao  Starting");

		boolean secNameCheck = false;
		String status="";
		String secDetailsId = campusClassSectionPojo.getSecDetailsId();
		String sectionName = campusClassSectionPojo.getSectionName();
		String USER_NAME = campusClassSectionPojo.getCreateUser();
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		PreparedStatement pstmt = null;
		SectionDaoImpl impl = new SectionDaoImpl();
		secNameCheck = impl.checkSectionName(sectionName,secDetailsId,userLoggingsVo);
		
		int count=0;
		
			Connection conn = null;
			
			try {
				
				System.out.println("Inside the DAO");
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

				pstmt=conn.prepareStatement(SectionUtilsConstants.INSERT_DETAILS_CAMPUS_CLASS_SECTION);
				if(campusClassSectionPojo.getLocationId().equalsIgnoreCase("all")){
					locationList = new  LocationBD().getLocationDetails(userLoggingsVo);
					 
				}
				if(locationList.size()!=0){
					for(int j=0;j<locationList.size();j++){
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classsection",userLoggingsVo));
				pstmt.setString(2, secDetailsId);
				pstmt.setString(3, sectionName);
				pstmt.setString(4, campusClassSectionPojo.getSectionStrength());
				pstmt.setString(5, USER_NAME);
				pstmt.setTimestamp(6, time_stamp);
				pstmt.setString(7, null);
				pstmt.setString(8, null);
				pstmt.setString(9, locationList.get(j).getLocation_id());
                 System.out.println("INSERT_DETAILS_CAMPUS_CLASS_SECTION -->>"+pstmt);
				count=pstmt.executeUpdate();
				if(count>0){
					HelperClass.recordLog_Activity(campusClassSectionPojo.getLog_audit_session(),"Settings","Division Detrails","Insert",pstmt.toString(),userLoggingsVo);
				}
					}
				}
				else{
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_classsection",userLoggingsVo));
					pstmt.setString(2, secDetailsId);
					pstmt.setString(3, campusClassSectionPojo.getSectionName());
					pstmt.setString(4, campusClassSectionPojo.getSectionStrength());
					pstmt.setString(5, USER_NAME);
					pstmt.setTimestamp(6, time_stamp);
					pstmt.setString(7, null);
					pstmt.setString(8, null);
					pstmt.setString(9, campusClassSectionPojo.getLocationId());
					
					 System.out.println("INSERT_DETAILS_CAMPUS_CLASS_SECTION -->>"+pstmt);


					count=pstmt.executeUpdate();
				}
				if(count > 0){
					HelperClass.recordLog_Activity(campusClassSectionPojo.getLog_audit_session(),"Settings","Division Detrails","Insert",pstmt.toString(),userLoggingsVo);
					status="Add";
				}
			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.getStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			} finally {

				try {
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
				+ " Control in SectionDaoImpl:insertCampusClassSectionDao Ending");
		System.out.println("status"+status);
		return status;
	}

	@Override
	public synchronized String updateCampusClassSectionDao(
			SectionPojo campusClassSectionPojo, UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:updateCampusClassSectionDao  Starting");
		// TODO Auto-generated method stub
		String sectionId = campusClassSectionPojo.getSecId();

		String secNameCheck = "";
		int count = 0;

		String secDetailsId = campusClassSectionPojo.getSecDetailsId();
		String sectionName = campusClassSectionPojo.getSectionName();
		String sectionStrength = campusClassSectionPojo.getSectionStrength();
		String secId = campusClassSectionPojo.getSecId();
		String USER_NAME = campusClassSectionPojo.getCreateUser();
		String locationId=campusClassSectionPojo.getLocationId();
		
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt=conn.prepareStatement(SectionUtilsConstants.UPDATE_DETAILS_CAMPUS_CLASS_SECTION);
			pstmt.setString(1, secDetailsId);
			pstmt.setString(2, sectionName);
			pstmt.setString(3, sectionStrength);
			pstmt.setString(4, USER_NAME);
			pstmt.setTimestamp(5, time_stamp);
			pstmt.setString(6, locationId);
			pstmt.setString(7, secId);
			
			count = pstmt.executeUpdate();
			if(count>0){
				HelperClass.recordLog_Activity(campusClassSectionPojo.getLog_audit_session(),"Settings","Division Details","Update",pstmt.toString(),userLoggingsVo);
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.getStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
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

		if (count > 0) {

			secNameCheck = "Update";
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:updateCampusClassSectionDao  Ending");
		return secNameCheck;
	}

	@Override
	public String deleteCampusClassSectionDao(SectionVO sectionvo,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:deleteCampusClassSectionDao  Starting");

		String flag = null;
		String value=null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		int count=0;
		try {
			 conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			 pstmt = conn.prepareStatement(SQLUtilConstants.DIVISION_DETAILS_UPDATE_BY_STATUS);
			
			 for(int i=0;i<sectionvo.getSecIds().length;i++){
				 
				 if(sectionvo.getStatus().equalsIgnoreCase("InActive")){
					 value="InActive";
					 pstmt.setString(1, "N");
					 if(sectionvo.getOtherReason()=="" || sectionvo.getOtherReason()==null){
						 pstmt.setString(2, sectionvo.getInactiveReason()); 
					 }
					 else{
						 pstmt.setString(2, sectionvo.getOtherReason());
					 }
				 }
				 else{
					 value="Active";
					 pstmt.setString(1, "Y");
					 if(sectionvo.getOtherReason()=="" || sectionvo.getOtherReason()==null){
						 pstmt.setString(2, sectionvo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, sectionvo.getOtherReason());
					 }
				 }
				 pstmt.setString(3, sectionvo.getSecIds()[i]);
				 pstmt.setString(4, sectionvo.getLocIds()[i]);
				 
				 count= pstmt.executeUpdate();
				 
				 System.out.println("DIVISION_DETAILS_UPDATE_BY_STATUS -->> "+pstmt);
				 
				 if(count > 0){
					 HelperClass.recordLog_Activity(sectionvo.getLog_audit_session(),"Setting","Division Details",value,pstmt.toString(),userLoggingsVo);
					}
			}
			 
			 if(count > 0){
				 flag="true";
				}
			 else{
				 flag="false";
			 }
			
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		} finally {

			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if ( !conn.isClosed()) {
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
				+ " Control in SectionDaoImpl:deleteCampusClassSectionDao  Ending");
		return flag;
	}

	@Override
	public synchronized List<SectionPojo> getCampusClassSectionAndClassDetailsDao(String schoolLocation,UserLoggingsPojo custdetails)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Starting");
		ResultSet rs = null;
		
		List<SectionPojo> campusClassSectionPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		if(schoolLocation.equalsIgnoreCase("all")){
			schoolLocation="%%";
		}
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);

			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS);
			 System.out.println("GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs.getString("Location_Name"));
				campusClassSectionPojo.setLocationId(rs.getString("locationId"));
				campusClassSectionPojo.setStatus("Active");
				 
				if(rs.getString("Remarks")=="" || rs.getString("Remarks")==null){
					campusClassSectionPojo.setRemarks("");
				}else{
				campusClassSectionPojo.setRemarks(rs.getString("Remarks"));
				}
				campusClassSectionPojoList.add(campusClassSectionPojo);
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
				+ " Control in SectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Ending");
		return campusClassSectionPojoList;

	}

	@Override
	public List<SectionPojo> getCampusClassDetailsIDandClassDetailsNameDao(String locationId,UserLoggingsPojo custdetails)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:getCampusClassDetailsIDandClassDetailsNameDao  Starting");
		 
		List<SectionPojo> campusClassDetailsPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_DETAILS_ID_AND_CLASS_DETAILS_NAME);
			if(!locationId.equalsIgnoreCase("all"))
			pstmt.setString(1, locationId);
			else
			pstmt.setString(1, "%%");
			
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classdetails_name_var"));
				campusClassDetailsPojoList.add(campusClassSectionPojo);
			}
			
			System.out.println("class detail query>>>"+pstmt);
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
				+ " Control in SectionDaoImpl:getCampusClassDetailsIDandClassDetailsNameDao  Ending");
		return campusClassDetailsPojoList;

	}

	@Override
	public synchronized boolean checkSectionName(String sectionName, String secDetailId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:checkSectionName  Starting");
		boolean secNameCheck = false;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SectionUtilsConstants.CHECK_SECTION);
			pstmt.setString(1, secDetailId);
			pstmt.setString(2, sectionName);
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt("usercount") > 0) {
				secNameCheck = true;
			} else {
				secNameCheck = false;
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
				+ " Control in SectionDaoImpl:checkSectionName  Ending");
		return secNameCheck;
	}

	public synchronized boolean checkSectionNameForUpdate(String sectionId,
			String sectionName, String className,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:checkSectionNameForUpdate  Starting");
		PreparedStatement pstmt = null;
		String classId = null;
		boolean secNameCheck = false;
		Connection conn = null;
		PreparedStatement pstmtclassid = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmtclassid = conn
					.prepareStatement(SectionUtilsConstants.GET_CLASS_ID_BY_NAME);
			pstmtclassid.setString(1, className);
			ResultSet rsclassid = pstmtclassid.executeQuery();

			while (rsclassid.next()) {
				classId = rsclassid.getString("classdetail_id_int");
				pstmt = (PreparedStatement) JDBCConnection
						.getStatement(SectionUtilsConstants.CHECK_SECTION_FOR_UPDATE,userLoggingsVo);
				pstmt.setString(1, classId);
				pstmt.setString(2, sectionName);
				pstmt.setString(3, sectionId);
				ResultSet rs = pstmt.executeQuery();

				rs.next();
				if (rs.getInt("usercount") > 0) {
					secNameCheck = true;
				} else {
					secNameCheck = false;
				}
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
				if (pstmtclassid != null && !pstmtclassid.isClosed()) {
					pstmtclassid.close();
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
				+ " Control in SectionDaoImpl:checkSectionNameForUpdate Ending");
		return secNameCheck;
	}
	
	@Override
	public synchronized List<SectionForm> searchSection(SectionForm searchText,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:searchClassDetails Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int siNo = 0;
		List<SectionForm> getClassDetailsList = new ArrayList<SectionForm>();
		try {
			String searchName=searchText.getSectionName();
			String locationId=searchText.getLocationId();
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn
					.prepareStatement(SectionUtilsConstants.GET_SECTION_DETAILS_BY_SEARCH_ID);
			pstmt.setString(1, "%" + searchName + "%");
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			pstmt.setString(5, "%" + searchName + "%");
			pstmt.setString(6, locationId);
			System.out.println("section Search "+pstmt);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionForm campusClassSectionPojo = new SectionForm();
				siNo++;
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs
						.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs
						.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs
						.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs
						.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs
						.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs
						.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs
						.getString("Location_Name"));
				
			
				
				
				getClassDetailsList.add(campusClassSectionPojo);

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
				+ " Control in SectionDaoImpl :searchClassDetails  Starting");

		return getClassDetailsList;
	}
	
	public synchronized SectionForm editSection(SectionForm secCode,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl : editClass  Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SectionForm campusClassSectionPojo = new SectionForm();
		try {
			String secId=secCode.getSectionId();
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SectionUtilsConstants.EDIT_SECTION);
			pstmt.setString(1, secId);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setLocationId(rs.getString("locationId"));
			
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setStatus("update");
				
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
				+ " Control in SectionDaoImpl : editClass  Starting");
		return campusClassSectionPojo;
	}

	@Override
	public List<SectionPojo> sectiondetailsdownload() throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Starting");
		ResultSet rs = null;
		List<SectionPojo> campusClassSectionPojoList = new ArrayList<SectionPojo>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SectionUtilsConstants.GET_CAMPUS_CLASS_SECTION_AND_CLASS_DETAILS);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SectionPojo campusClassSectionPojo = new SectionPojo();
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSecDetailsId(rs.getString("classdetail_id_int"));
				campusClassSectionPojo.setSecDetailsName(rs.getString("classdetails_name_var"));
				campusClassSectionPojo.setStreamName(rs.getString("classstream_name_var"));
				campusClassSectionPojo.setSectionId(rs.getString("classsection_id_int"));
				campusClassSectionPojo.setSectionName(rs.getString("classsection_name_var"));
				campusClassSectionPojo.setSectionStrength(rs.getString("classsection_strength_int"));
				campusClassSectionPojo.setLocationName(rs.getString("Location_Name"));
				
				
				
				campusClassSectionPojoList.add(campusClassSectionPojo);
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
				+ " Control in SectionDaoImpl:getCampusClassSectionAndClassDetailsDao  Ending");
		return campusClassSectionPojoList;
	}

	@Override
	public String sectionNameCheck(SectionForm sectionForm,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl : classNameCheck  Starting");
		String status = null,value=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int count = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SectionUtilsConstants.CHECK_SEC_NAME);

			pstmt.setString(1, sectionForm.getClassId().trim());
			pstmt.setString(2, sectionForm.getSectionName());
			pstmt.setString(3, sectionForm.getLocationId());
			
			System.out.println("CHECK_SEC_NAME -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("secname");
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
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in SectionDaoImpl : classNameCheck  Starting");

		System.out.println(status);
		return status;

	}

	public List<SectionForm> getstreamdetailsforOnchange(String locationid,String classname,String streamId,String status, UserLoggingsPojo custdetails) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SectionDaoImpl : getStudentDetails  Starting");
	//List<StudentRegistrationVo> list = new ArrayList<StudentRegistrationVo>();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;
	List<SectionForm> list = new ArrayList<SectionForm>();
	try {
		conn = JDBCConnection.getSeparateConnection(custdetails);
		pst = conn.prepareStatement("SELECT DISTINCT cd.classdetail_id_int,sec.classsection_id_int,sec.locationId,sec.isActive,sec.Remarks,loc.Location_Name,"
									+ "st.classstream_name_var,cd.classdetails_name_var,sec.classsection_name_var,sec.classsection_strength_int FROM campus_classsection sec "
									+ "JOIN campus_classdetail cd ON sec.classdetail_id_int = cd.classdetail_id_int AND cd.locationId = sec.locationId AND cd.isActive='Y'  "
									+ "JOIN campus_classstream st ON st.classstream_id_int = cd.classstream_id_int AND st.locationId = cd.locationId AND st.isActive='Y' "
									+ "JOIN campus_location loc ON loc.Location_Id = sec.locationId "
									+ "WHERE loc.isActive='Y' AND loc.Location_Id  LIKE ? AND  st.classstream_id_int LIKE ? AND sec.classdetail_id_int LIKE ? "
									+ "AND  sec.isActive=? ORDER BY CAST(SUBSTR(cd.classdetail_id_int,4,LENGTH(cd.classdetail_id_int-3)) AS SIGNED),classsection_name_var");
		pst.setString(1, locationid);
		pst.setString(2, streamId);
		pst.setString(3, classname);
		pst.setString(4, status);
		
		System.out.println("sdadasd "+pst);
		rs = pst.executeQuery();
		while (rs.next()) {
			SectionForm stdvo = new SectionForm();
			
			stdvo.setLocationId(rs.getString("locationId"));
			stdvo.setSecId(rs.getString("classsection_id_int"));
			stdvo.setLocationName(rs.getString("Location_Name"));
			stdvo.setStreamName(rs.getString("classstream_name_var"));
			stdvo.setClassName(rs.getString("classdetails_name_var"));
			stdvo.setSectionName(rs.getString("classsection_name_var"));
			stdvo.setStrength(rs.getString("classsection_strength_int"));
			if(rs.getString("isActive").equalsIgnoreCase("Y")){
				stdvo.setStatus("Active");
			}else{
				stdvo.setStatus("InActive");
			}
			 if(rs.getString("Remarks")=="" || rs.getString("Remarks")==null)
			 {
			    stdvo.setRemarks("");
			 }
			 else{
				 stdvo.setRemarks(rs.getString("Remarks"));
			 }
			//stdvo.setStrength(rs.getString(""));
			//stdvo.set
			/*stdvo.setStreamName("");
			stdvo.setClassName("");
			stdvo.setSectionName("");
			stdvo.setStrength("");
	       */
			list.add(stdvo);
			 
		list.size();
		}
	}
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	} finally {
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
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SectionDaoImpl : getStudentDetails  Ending");

	return list;

	}

	public String validateDuplicateSectionLocation(SectionPojo pojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SectionDaoImpl : classNameCheck  Starting");
		String status = null,value=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SectionUtilsConstants.validate_Duplicate_Section);

			pstmt.setString(1, pojo.getClassid().trim());
			pstmt.setString(2, pojo.getSectionId());
			pstmt.setString(3, pojo.getLocationId());
			
			System.out.println("validate_Duplicate_Section -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("secname");
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
				+ " Control in SectionDaoImpl : classNameCheck  Starting");

		System.out.println(status);
		return status;
	}


	
}
