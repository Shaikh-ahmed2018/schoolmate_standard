package com.centris.campus.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.LocationDao;
import com.centris.campus.forms.LocationMasterForm;
import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.LocationMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.LocationVO;

public class LocationDAOImpl implements LocationDao {

	private static Logger logger = Logger.getLogger(LocationDAOImpl.class);

	public List<LocationVO> getLocationDetails(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_LIST);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("Location_Id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("Location_Address"));
				locationList.setContactno(rs.getString("Location_Phone"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setBoard(rs.getString("board"));
				locationList.setAffilno(rs.getString("affilation"));
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setWebsite(rs.getString("website"));
				
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					locationList.setStatus("Active");
				}
				locationList.setRemarks(rs.getString("Remarks"));
				
				list.add(locationList);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationDetails : Ending");
		return list;
	}

	@Override
	public String insertLocationDetailsAction(LocationMasterPojo locationpojo,String updateId,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: insertLocationDetailsAction : Starting");

		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null,pstmt3= null;
		Connection conn = null;
		ResultSet rs1=null,rs2= null;
		int count = 0,loccount=0,custallowcatedcount=0,addcount=0,genkey=0;
		String status = null,custstatus=null,address2="";
		try{
			 
			if(locationpojo.getAddress2()==null){
				address2="";
			}else{
				address2=locationpojo.getAddress2().trim();
			}
			
			conn = JDBCConnection.getSeparateConnection(pojo);
			if(updateId.equalsIgnoreCase("")){
				
				/*pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM campus_location WHERE Cust_Id=?");
				pstmt1.setString(1, locationpojo.getCustomerId());
				System.out.println("campus_location -->>"+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					loccount=rs1.getInt(1);
				}
				pstmt2 = conn.prepareStatement("SELECT no_of_schools FROM campus_customer_details WHERE customerID=?");
				pstmt2.setString(1, locationpojo.getCustomerId());
				System.out.println("campus_customer_details -->>"+pstmt2);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					custallowcatedcount=rs2.getInt(1);
				}
				
				if(custallowcatedcount==loccount){
					custstatus="inactive";
				}
				else if(custallowcatedcount>loccount){
					custstatus="active";*/
						
				pstmt=conn.prepareStatement(SQLUtilConstants.INSERTLOCATION);
				pstmt.setString(1,  locationpojo.getLocationname().trim());
				pstmt.setString(2,  locationpojo.getEmailId());
				pstmt.setString(3,  locationpojo.getSchoollogo());
				pstmt.setString(4, locationpojo.getCreatedBy());
				pstmt.setString(5, locationpojo.getWebsite());
				pstmt.setString(6, locationpojo.getAddress());
				pstmt.setString(7, locationpojo.getAddress2());
				pstmt.setString(8, locationpojo.getCountry());
				pstmt.setString(9, locationpojo.getState());
				pstmt.setString(10, locationpojo.getCity());
				pstmt.setString(11, locationpojo.getPincode());
				
				pstmt.setString(12, locationpojo.getCperson());
				pstmt.setString(13, locationpojo.getCemailId());
				pstmt.setString(14, locationpojo.getClandline());
				pstmt.setString(15, locationpojo.getCmobileno());
				pstmt.setString(16, locationpojo.getAffilno());
				
				System.out.println("INSERTLOCATION -->>"+pstmt);
				
				count=pstmt.executeUpdate();
				/*}*/
				if (count > 0) {
					HelperClass.recordLog_Activity(locationpojo.getLog_audit_session(),"Settings","School","Insert",pstmt.toString(),pojo);
					status = MessageConstants.ADD_LOCATION_SUCCESS;
				} else {
					status = MessageConstants.ADD_LOCATION_FAIL;
				}
				
			}
			else if(!(updateId.equalsIgnoreCase(""))){
				
				pstmt=conn.prepareStatement(SQLUtilConstants.UPDATESCHOOL);
				pstmt.setString(1, locationpojo.getLocationname().trim());
				pstmt.setString(2,  locationpojo.getSchoollogo());
				pstmt.setString(3, locationpojo.getWebsite());
				pstmt.setString(4,  locationpojo.getEmailId());
				pstmt.setString(5, locationpojo.getAddress());
				pstmt.setString(6, locationpojo.getAddress2());
				pstmt.setString(7, locationpojo.getCountry());
				pstmt.setString(8, locationpojo.getState());
				pstmt.setString(9, locationpojo.getCity());
				pstmt.setString(10, locationpojo.getPincode());
				pstmt.setString(11, locationpojo.getCreatedBy());
				pstmt.setString(12, locationpojo.getCperson());
				pstmt.setString(13, locationpojo.getCemailId());
				pstmt.setString(14, locationpojo.getClandline());
				pstmt.setString(15, locationpojo.getCmobileno());
				pstmt.setString(16, locationpojo.getAffilno());
				pstmt.setString(17, updateId.trim());
				
				System.out.println("UPDATESCHOOL -->>"+pstmt);
				count=pstmt.executeUpdate();
				if (count > 0) {
					HelperClass.recordLog_Activity(locationpojo.getLog_audit_session(),"Settings","School","Update",pstmt.toString(),pojo);

					status = MessageConstants.UPDATE_LOCATION_SUCCESS;
				} else {
					status = MessageConstants.UPDATE_LOCATION_FAIL;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : insertLocationDetailsAction : Ending");

		return status;
	}
	@Override
	public String validateLocName(LocationMasterForm form1,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : validateLocName : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;
		String locame_available = null,value=null;
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_LOC_NAME);
			loc_pstmt.setString(1,form1.getLocationname());
			 
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				count = loc_rs.getInt("Location_Name");
				value = loc_rs.getString("isActive");
			}

			
			if (count > 0 && value.equalsIgnoreCase("N")) {
				locame_available = "inactive";
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
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
				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in LocationDAOImpl : validateLocName : Ending");

		return locame_available;
	}

	public String validateLocNameUpdate(LocationVO validateUpdate,UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: validateLocNameUpdate : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;

		String locame_available = null,value=null;
		int count = 0;

		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			/*SQLUtilConstants.VALIDATE_LOCATION_UPDATE*/
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.VALIDATE_SCHOOL_UPDATE);
			loc_pstmt.setString(1, validateUpdate.getLocationname());
			loc_pstmt.setString(2, validateUpdate.getLocation_id());
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				count = loc_rs.getInt("school_name");
			}
			
			if (count > 0) {
				locame_available = "true";
			} 
			else {
				locame_available = "false";
			}

		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in LocationDAOImpl: validateLocNameUpdate : Ending");

		return locame_available;

	}
	@Override
	public LocationVO editLocation(String edit,String addId,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LocationDAOImpl: editLocation : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;
		Connection conn = null;
		LocationVO addLocationVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_LOCATION);
			loc_pstmt.setString(1, edit);
			loc_pstmt.setString(2, addId);
			System.out.println("EDIT_LOCATION -->>"+loc_pstmt);
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				addLocationVO = new LocationVO();
				addLocationVO.setLocation_id(loc_rs.getString("school_id"));
				addLocationVO.setLocationname(loc_rs.getString("Location_Name"));
				addLocationVO.setAddress(loc_rs.getString("address_line1"));
				addLocationVO.setAddress2(loc_rs.getString("address_line2"));
				addLocationVO.setContactno(loc_rs.getString("mobile_no"));
				addLocationVO.setLandLineNo(loc_rs.getString("tel_phone"));
				
				addLocationVO.setCountryId(loc_rs.getString("country"));
				addLocationVO.setStateId(loc_rs.getString("state"));
				addLocationVO.setCityId(loc_rs.getString("city"));
				addLocationVO.setPinCode(loc_rs.getString("pin_code"));
				addLocationVO.setLocAddId(loc_rs.getString("scl_address_id"));
				
				addLocationVO.setEmailId(loc_rs.getString("emailId"));
				addLocationVO.setBoard(loc_rs.getString("board"));
				addLocationVO.setAffilno(loc_rs.getString("affilation"));
				addLocationVO.setSchoolcode(loc_rs.getString("schoolcode"));
				addLocationVO.setSchoollogo(loc_rs.getString("schoollogo"));
				addLocationVO.setBoardlogo(loc_rs.getString("boardlogo"));
				addLocationVO.setWebsite(loc_rs.getString("website"));
				
			}
		} catch (SQLException sqle) {

			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (loc_rs != null && (!loc_rs.isClosed())) {

					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {

					loc_pstmt.close();
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
				+ " Control in LocationDAOImpl: editLocation  : Ending");

		return addLocationVO;

	}
	@Override
	public String InactiveLocation(String[] locid,LocationVO locvo,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: InactiveLocation : Starting");

		PreparedStatement pstmt = null;
		String status = null,value=null;
		int count=0;
		Connection conn = null;
		try {
			
			 conn = JDBCConnection.getSeparateConnection(pojo);
			 pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_UPDATE_BY_STATUS);
			
			 for(int i=0;i<locid.length;i++){
				 if(locvo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(locvo.getOtherReason()=="" || locvo.getOtherReason()==null){
						 pstmt.setString(2, locvo.getInactiveReason()); 
					 }
					 else{
						 pstmt.setString(2, locvo.getOtherReason());
					 }
					 value="InActive";
				 }
				 else{
					 pstmt.setString(1, "Y");
					 if(locvo.getOtherReason()=="" || locvo.getOtherReason()==null){
						 pstmt.setString(2, locvo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, locvo.getOtherReason());
					 }
					 value="Active";
				 }
				 
				 pstmt.setString(3, locid[i]);
				 count= pstmt.executeUpdate();
				 if(count > 0){
					 HelperClass.recordLog_Activity(locvo.getLog_audit_session(),"Settings","School Details",value,pstmt.toString(),pojo);
					}
			}
			if(count > 0){
				status="true";
			}
			else{ 
				status="false";
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
				+ " Control in LocationDAOImpl: InactiveLocation : Ending");
		return status;
	}
	
	@Override
	public List<LocationVO> searchLocationDetails(String searchName,String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : searchLocationDetails Starting");

		ArrayList<LocationVO> locationlist = new ArrayList<LocationVO>();
		PreparedStatement location_pstmt = null;
		ResultSet location_rs = null;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			location_pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_LOCATION_DETAILS); 
			location_pstmt.setString(1,searchName+"%");
			location_pstmt.setString(2,searchName+"%"); 
			location_pstmt.setString(3,"%"+searchName+"%");
			location_pstmt.setString(4,"%"+searchName+"%");
			location_pstmt.setString(5,"%"+searchName+"%");
			location_pstmt.setString(6,"%"+searchName+"%");
			location_pstmt.setString(7,status);
			
			location_rs = location_pstmt.executeQuery();
			
			while (location_rs.next()) {
				LocationVO location = new LocationVO();

				location.setLocation_id(location_rs.getString("Location_Id"));
				location.setLocationname(location_rs.getString("Location_Name"));
				
				location.setAddress(location_rs.getString("Location_Address"));
				location.setContactno(location_rs.getString("Location_Phone"));
				location.setEmailId(location_rs.getString("emailId"));
				location.setBoard(location_rs.getString("board"));
				location.setAffilno(location_rs.getString("affilation"));
				location.setSchoollogo(location_rs.getString("schoollogo"));
				location.setWebsite(location_rs.getString("website"));
				
				if(location_rs.getString("isActive").equalsIgnoreCase("Y")){
					location.setStatus("Active");
				}else{
					location.setStatus("InActive");
				}
				if(location_rs.getString("Remarks")==""|| location_rs.getString("Remarks")==null){
					location.setRemarks("");
				}else{
				location.setRemarks(location_rs.getString("Remarks"));
				 }
				locationlist.add(location);
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {

				if (location_rs != null && (!location_rs.isClosed())) {
					location_rs.close();
				}
				if (location_pstmt != null && (!location_pstmt.isClosed())) {
					location_pstmt.close();
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
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LocationDAOImpl: searchLocationDetails  : Ending");
		}
		return locationlist;
	}

	@Override
	public List<LocationVO> InActiveLocationList(LocationVO locvo,UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: InActiveLocationList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			 
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LOCATION_ADDRESS_LIST_BY_STATUS);
			pstmt.setString(1, locvo.getStatus());
			/*pstmt.setString(2, locvo.getLocation_id());*/
			System.out.println("LOCATION_LIST_BY_STATUS -->>"+pstmt);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocId(rs.getString("Location_Id"));
				locationList.setLocation_id(rs.getString("school_id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("address_line1"));
				locationList.setContactno(rs.getString("mobile_no"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setBoard(rs.getString("board"));
				locationList.setLocAddId(rs.getString("loc_add_id"));
				
				if(rs.getString("affilation")==null || rs.getString("affilation")==""){
					locationList.setAffilno("");
				}else{
				    locationList.setAffilno(rs.getString("affilation"));
				}
				 
				if(rs.getString("website")==null || rs.getString("website")==""){
					locationList.setWebsite("");
				}else{
				    locationList.setWebsite(rs.getString("website"));
				}
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setBoardlogo(rs.getString("boardlogo")); 
				
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					locationList.setStatus("Active");
				}
				else{
					locationList.setStatus("InActive");
				}
				if(rs.getString("Remarks")=="" || rs.getString("Remarks")==null){
					locationList.setRemarks("");
				}
				else{
				     locationList.setRemarks(rs.getString("Remarks"));
				}
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : InActiveLocationList : Ending");

		return list;
	}

	@Override
	public List<LocationVO> getLocationList(UserLoggingsPojo dbdetails) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationList : Starting");
		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null;
		ResultSet rs = null,rs1= null,rs2= null;
		Connection conn=null;
		int loccount=0,custallowcatedcount=0;
		String custstatus=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_LIST);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("Location_Id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("Location_Address"));
				locationList.setContactno(rs.getString("Location_Phone"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setBoard(rs.getString("board"));
				locationList.setAffilno(rs.getString("affilation"));
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setWebsite(rs.getString("website"));
				
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					locationList.setStatus("Active");
				}
				locationList.setRemarks(rs.getString("Remarks"));
				
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM campus_location");
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					loccount=rs1.getInt(1);
				}
				pstmt2 = conn.prepareStatement("SELECT no_of_schools FROM campus_customer_details");
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					custallowcatedcount=rs2.getInt(1);
				}
				
				if(custallowcatedcount==loccount){
					custstatus="inactive";
				}
				else if(custallowcatedcount>loccount){
					custstatus="active";
				}
				locationList.setCuststatus(custstatus);
				
				list.add(locationList);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationList : Ending");
		return list;
	
	}

	@Override
	public List<LocationVO> searchLocationList(String searchName, String status, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : searchLocationList Starting");

		ArrayList<LocationVO> locationlist = new ArrayList<LocationVO>();
		PreparedStatement location_pstmt = null,pstmt1=null,pstmt2=null;
		ResultSet location_rs = null,rs1=null,rs2=null;
		Connection conn = null;
		int locvalue=0,custvalue=0;
		String custstatus=null;
		try {
			conn = JDBCConnection.getSeparateConnection(dbdetails);
			
			location_pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_LOCATION_DETAILS); 
			location_pstmt.setString(1,searchName+"%");
			location_pstmt.setString(2,searchName+"%"); 
			location_pstmt.setString(3,"%"+searchName+"%");
			location_pstmt.setString(4,"%"+searchName+"%");
			location_pstmt.setString(5,"%"+searchName+"%");
			location_pstmt.setString(6,"%"+searchName+"%");
			location_pstmt.setString(7,status);
			
			location_rs = location_pstmt.executeQuery();
			
			while (location_rs.next()) {
				LocationVO location = new LocationVO();

				location.setLocation_id(location_rs.getString("Location_Id"));
				location.setLocationname(location_rs.getString("Location_Name"));
				
				location.setAddress(location_rs.getString("Location_Address"));
				location.setContactno(location_rs.getString("Location_Phone"));
				location.setEmailId(location_rs.getString("emailId"));
				location.setBoard(location_rs.getString("board"));
				location.setAffilno(location_rs.getString("affilation"));
				location.setSchoollogo(location_rs.getString("schoollogo"));
				location.setWebsite(location_rs.getString("website"));
				
				if(location_rs.getString("isActive").equalsIgnoreCase("Y")){
					location.setStatus("Active");
				}else{
					location.setStatus("InActive");
				}
				if(location_rs.getString("Remarks")==""|| location_rs.getString("Remarks")==null){
					location.setRemarks("");
				}else{
				location.setRemarks(location_rs.getString("Remarks"));
				 }
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM campus_location");
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					locvalue=rs1.getInt(1);
				}
				pstmt2 = conn.prepareStatement("SELECT no_of_schools FROM campus_customer_details");
				
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					custvalue=rs2.getInt(1);
				}
				if(custvalue==locvalue){
					custstatus="inactive";
				}
				else if(custvalue>locvalue){
					custstatus="active";
				}
				
				location.setCuststatus(custstatus);
				locationlist.add(location);
			}
			

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
                 
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (location_rs != null && (!location_rs.isClosed())) {
					location_rs.close();
				}
				if (location_pstmt != null && (!location_pstmt.isClosed())) {
					location_pstmt.close();
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
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in LocationDAOImpl: searchLocationList  : Ending");
		}
		return locationlist;
	
	}

	public List<LocationVO> getCountries(LocationVO locvo, UserLoggingsPojo pojo)
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getCountries : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_COUNTRY_LIST);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setCountryId(rs.getString("id"));
				locationList.setCountryName(rs.getString("NAME"));
				 
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getCountries : Ending");

		return list;
	}

	public List<LocationVO> getStates(LocationVO locvo, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getStates : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_STATE_LIST_BY_COUNTRY);
			pstmt.setString(1,locvo.getCountryId());
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setStateId(rs.getString("id"));
				locationList.setStateName(rs.getString("name"));
				 
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getStates : Ending");

		return list;
	}

	public List<LocationVO> getCities(LocationVO locvo, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getCities : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CITY_LIST_BY_COUNTRY_AND_STATE);
			pstmt.setString(1,locvo.getStateId());
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setCityId(rs.getString("id"));
				locationList.setCityName(rs.getString("name"));
				 
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getCities : Ending");

		return list;
	}

	@Override
	public ArrayList<LocationVO> getLocationAllDetails(String loaction_id, UserLoggingsPojo userLoggingsPojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationAllDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsPojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_DETAILS);
			pstmt.setString(1, loaction_id );
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("Location_Address"));
				locationList.setContactno(rs.getString("Location_Phone"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setWebsite(rs.getString("website"));
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setBoardlogo(rs.getString("boardlogo"));
				
				list.add(locationList);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationAllDetails : Ending");
		
		return list;
	}
	
	@Override
	public String getLocationCount(UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationCount : Starting");
		PreparedStatement pstmt1= null,pstmt2= null;
		ResultSet rs1= null,rs2= null;
		Connection conn=null;
		int loccount=0,custallowcatedcount=0;
		String custstatus=null;
		try{
			conn = JDBCConnection.getSeparateConnection(dbdetails);
				
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM campus_location WHERE Cust_Id=?");
				pstmt1.setString(1, dbdetails.getCustId());
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					loccount=rs1.getInt(1);
				}
				pstmt2 = conn.prepareStatement("SELECT no_of_schools FROM campus_customer_details WHERE customerID=?");
				pstmt2.setString(1, dbdetails.getCustId());
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					custallowcatedcount=rs2.getInt(1);
				}
				
				if(custallowcatedcount==loccount){
					custstatus="inactive";
				}
				else if(loccount !=0 && custallowcatedcount>loccount){
					custstatus="active";
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationCount : Ending");
		return custstatus;
	
	}

	
	public List<LocationVO> SingleLocationDetails(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: getLocationDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.LOCATION_LIST);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("Location_Id"));
				locationList.setLocationname(rs.getString("Location_Name"));
				locationList.setAddress(rs.getString("Location_Address"));
				locationList.setContactno(rs.getString("Location_Phone"));
				locationList.setEmailId(rs.getString("emailId"));
				locationList.setBoard(rs.getString("board"));
				locationList.setAffilno(rs.getString("affilation"));
				locationList.setSchoollogo(rs.getString("schoollogo"));
				locationList.setWebsite(rs.getString("website"));
				
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					locationList.setStatus("Active");
				}
				locationList.setRemarks(rs.getString("Remarks"));
				
				list.add(locationList);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : getLocationDetails : Ending");
		return list;
	}

	@Override
	public List<LocationVO> SchoolList(LocationVO locvo,UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: SchoolList : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		try{
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt = conn.prepareStatement(SQLUtilConstants.SCHOOL_LIST);
		 
			System.out.println("LOCATION_LIST_BY_STATUS -->>"+pstmt);
			rs=pstmt.executeQuery();
			 
			while(rs.next()){
				LocationVO locationList=new LocationVO();
				locationList.setLocation_id(rs.getString("school_id"));
				locationList.setLocationname(rs.getString("school_name"));
				locationList.setEmailId(rs.getString("email_id"));
				
			    locationList.setWebsite(rs.getString("website"));
				 
				locationList.setSchoollogo(rs.getString("school_logo"));
				locationList.setAddress(rs.getString("address"));
				 
				list.add(locationList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : SchoolList : Ending");

		return list;
	}

	@Override
	public LocationVO editSchool(String locId, UserLoggingsPojo pojo)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in LocationDAOImpl: editSchool : Starting");

		PreparedStatement loc_pstmt = null;
		ResultSet loc_rs = null;
		Connection conn = null;
		LocationVO addLocationVO = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			
			loc_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_SCHOOL);
			loc_pstmt.setString(1, locId);
			System.out.println("EDIT_SCHOOL -->>"+loc_pstmt);
			loc_rs = loc_pstmt.executeQuery();

			while (loc_rs.next()) {
				addLocationVO = new LocationVO();
				addLocationVO.setLocation_id(loc_rs.getString("school_id"));
				addLocationVO.setLocationname(loc_rs.getString("school_name"));
				
				addLocationVO.setEmailId(loc_rs.getString("email_id"));
				addLocationVO.setSchoollogo(loc_rs.getString("school_logo"));
				addLocationVO.setWebsite(loc_rs.getString("website"));
				
				addLocationVO.setAddress(loc_rs.getString("address1"));
				addLocationVO.setAddress2(loc_rs.getString("address2"));
				addLocationVO.setCountryId(loc_rs.getString("country"));
				addLocationVO.setStateId(loc_rs.getString("state"));
				addLocationVO.setCityId(loc_rs.getString("city"));
				addLocationVO.setPinCode(loc_rs.getString("pincode"));
				addLocationVO.setCperson(loc_rs.getString("cperson_name"));
				addLocationVO.setCemailId(loc_rs.getString("cperson_email"));
				addLocationVO.setCmobileno(loc_rs.getString("cperson_mobile"));
				addLocationVO.setClandline(loc_rs.getString("cperson_landline_no"));
				addLocationVO.setAffilno(loc_rs.getString("schl_affiliation_no"));
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {

			try {
				if (loc_rs != null && (!loc_rs.isClosed())) {
					loc_rs.close();
				}
				if (loc_pstmt != null && (!loc_pstmt.isClosed())) {
					loc_pstmt.close();
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
				+ " Control in LocationDAOImpl: editSchool  : Ending");

		return addLocationVO;
	}
	
	@Override
	public String insertLocationAddress(LocationMasterPojo locationpojo,String updateId,UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl: insertLocationDetailsAction : Starting");

		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null,pstmt3= null;
		Connection conn = null;
		ResultSet rs1=null,rs2= null;
		int count = 0,loccount=0,custallowcatedcount=0,addcount=0,genkey=0;
		String status = null,custstatus=null,address2="";
		try{
			 
			if(locationpojo.getAddress2()==null){
				address2="";
			}else{
				address2=locationpojo.getAddress2().trim();
			}
			
			System.out.println("locationpojo.getCustomerId() -->> "+locationpojo.getCustomerId());
			System.out.println("locationpojo.getCustomerId() -->> "+locationpojo.getCustomerId());
			
			conn = JDBCConnection.getSeparateConnection(pojo);
			if(updateId.equalsIgnoreCase("")){
				
				pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM campus_location WHERE Cust_Id=?");
				pstmt1.setString(1, pojo.getCustId());
				System.out.println("campus_location -->> "+pstmt1);
				rs1=pstmt1.executeQuery();
				while(rs1.next()){
					loccount=rs1.getInt(1);
				}
				pstmt2 = conn.prepareStatement("SELECT no_of_schools FROM campus_customer_details WHERE customerID=?");
				pstmt2.setString(1, pojo.getCustId());
				System.out.println("no_of_schools -->> "+pstmt2);
				rs2=pstmt2.executeQuery();
				while(rs2.next()){
					custallowcatedcount=rs2.getInt(1);
				}
				
				System.out.println("custallowcatedcount  -->>"+custallowcatedcount);
				System.out.println("custallowcatedcount  -->>"+custallowcatedcount);
				System.out.println("loccount  -->>"+loccount);
				System.out.println("loccount  -->>"+loccount);
				
				if(custallowcatedcount==loccount){
					custstatus="inactive";
				}
				else if(custallowcatedcount>loccount){
					custstatus="active";
						
				
				pstmt3 = conn.prepareStatement(SQLUtilConstants.INSERT_SCHOOL_ADDRESS,Statement.RETURN_GENERATED_KEYS);
				pstmt3.setString(1,  locationpojo.getAddress().trim());
				pstmt3.setString(2,  address2);
				pstmt3.setString(3,  locationpojo.getLandlineno());
				pstmt3.setString(4,  locationpojo.getCountry());
				pstmt3.setString(5,  locationpojo.getState());
				pstmt3.setString(6,  locationpojo.getCity());
				pstmt3.setString(7,  locationpojo.getPincode());
				pstmt3.setString(8,  locationpojo.getContactno());
				
				pstmt3.executeUpdate();
				
				System.out.println("INSERT_SCHOOL_ADDRESS -->>"+pstmt3);
				ResultSet key = pstmt3.getGeneratedKeys();
				while(key.next()){
					genkey = key.getInt(1);
				}	
					
				pstmt=conn.prepareStatement(SQLUtilConstants.INSERTLOCATION_ADDRESS_INFO);
				pstmt.setString(1,  locationpojo.getLocation_id().trim());
				pstmt.setString(2,  locationpojo.getLocAddId().trim());
				pstmt.setString(3,  locationpojo.getEmailId());
				pstmt.setString(4,  locationpojo.getBoard());
				pstmt.setString(5,  locationpojo.getAffilno());
				pstmt.setString(6,  locationpojo.getSchoolcode());
				pstmt.setString(7,  locationpojo.getSchoollogo());
				pstmt.setString(8, locationpojo.getBoardlogo());
				pstmt.setString(9, locationpojo.getCreatedBy());
				pstmt.setString(10, locationpojo.getWebsite());
				pstmt.setString(11, locationpojo.getCustomerId());
				pstmt.setInt(12, genkey);
				pstmt.setString(13, locationpojo.getLocationname());
				
				System.out.println("INSERTLOCATION -->>"+pstmt);
				
				count=pstmt.executeUpdate();
				}
				if (count > 0) {
					HelperClass.recordLog_Activity(locationpojo.getLog_audit_session(),"Settings","Location","Insert",pstmt.toString(),pojo);
					status = MessageConstants.ADD_LOCATION_SUCCESS;
				} else {
					status = MessageConstants.ADD_LOCATION_FAIL;
				}
				
			}
			else if(!(updateId.equalsIgnoreCase(""))){
				
				pstmt2 = conn.prepareStatement(SQLUtilConstants.UPDATELOCATION_ADDRESS);
				pstmt2.setString(1,  locationpojo.getAddress()); 
				pstmt2.setString(2,  address2);
				pstmt2.setString(3,  locationpojo.getLandlineno());
				pstmt2.setString(4,  locationpojo.getContactno());
				pstmt2.setString(5,  locationpojo.getCountry());
				pstmt2.setString(6,  locationpojo.getState());
				pstmt2.setString(7,  locationpojo.getCity());
				pstmt2.setString(8,  locationpojo.getPincode());
				pstmt2.setString(9,  locationpojo.getHiddenaddId());
				System.out.println("UPDATELOCATION_ADDRESS -->>"+pstmt2);
				int count1=pstmt2.executeUpdate();
				if (count1 > 0) {
					HelperClass.recordLog_Activity(locationpojo.getLog_audit_session(),"Settings","Location","Update",pstmt2.toString(),pojo);
				}
				
				pstmt=conn.prepareStatement(SQLUtilConstants.UPDATELOCATION);
				pstmt.setString(1, locationpojo.getLocAddId().trim());
				pstmt.setString(2,  locationpojo.getEmailId());
				pstmt.setString(3,  locationpojo.getBoard());
				pstmt.setString(4,  locationpojo.getAffilno());
				pstmt.setString(5,  locationpojo.getSchoolcode());
				pstmt.setString(6,  locationpojo.getSchoollogo());
				pstmt.setString(7, locationpojo.getBoardlogo());
				pstmt.setString(8, locationpojo.getCreatedBy());
				pstmt.setString(9, locationpojo.getWebsite());
				pstmt.setString(10, locationpojo.getLocationname());
				pstmt.setString(11, updateId.trim());
				pstmt.setString(12, locationpojo.getHiddenlocaddressId());
				System.out.println("UPDATELOCATION -->>"+pstmt);
				count=pstmt.executeUpdate();
				if (count > 0) {
					HelperClass.recordLog_Activity(locationpojo.getLog_audit_session(),"Settings","Location","Update",pstmt.toString(),pojo);

					status = MessageConstants.UPDATE_LOCATION_SUCCESS;
				} else {
					status = MessageConstants.UPDATE_LOCATION_FAIL;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if (rs2 != null && (!rs2.isClosed())) {
					rs2.close();
				}
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt3 != null && (!pstmt3.isClosed())) {
					pstmt3.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LocationDAOImpl : insertLocationDetailsAction : Ending");

		return status;
	}

}