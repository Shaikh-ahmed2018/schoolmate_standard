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

import com.centris.campus.dao.StreamDetailsDao;
import com.centris.campus.delegate.LocationBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.util.StreamSqlUtils;
import com.centris.campus.vo.LocationVO;
import com.centris.campus.vo.StreamDetailsVO;

public class StreamDetailsDaoImpl implements StreamDetailsDao {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsDaoImpl.class);

	public List<StreamDetailsPojo> getStreamDetailsDao(String schoolLocation,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : getStreamDetailsDao Starting");
		List<StreamDetailsPojo> streamList = new ArrayList<StreamDetailsPojo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if(schoolLocation.equalsIgnoreCase("all")){
				schoolLocation="%%";
			}

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(StreamSqlUtils.GET_STREAM_LIST);
			pstmt.setString(1, schoolLocation);
			System.out.println("GET_STREAM_LIST -->>"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StreamDetailsPojo streamPojo = new StreamDetailsPojo();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamPojo.setLocationName(rs.getString("Location_Name"));
				streamPojo.setDescription(rs.getString("description"));
				
				System.out.println("rs.getString('description') -->>"+rs.getString("description"));
				/*if(rs.getString("isActive").equalsIgnoreCase("Y")){
					streamPojo.setStatus("Active");
				}
				else{
					streamPojo.setStatus("InActive");
				}*/
				if(rs.getString("Remarks")==""){
					streamPojo.setReason("");
				}
				else{
					streamPojo.setReason(rs.getString("Remarks"));
				}
				streamList.add(streamPojo);

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
				+ " Control in StreamDetailsDaoImpl : getStreamDetailsDao  Ending");
		return streamList;
	}

	
	
	
	public int insertStreamDetailsDao(StreamDetailsPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : insertStreamDetailsDao  Starting");

		String streamName = detailsPojo.getStreamName();
		String desc = detailsPojo.getDescription();
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		 
		int rs=0;
		
		try {
			
			String properDescription = desc.replaceAll("[^\\x00-\\x7F]", "");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(StreamSqlUtils.INSERT_STREAM_DETAILS);
			
			if(detailsPojo.getLocationId().equalsIgnoreCase("all")){
				locationList = new  LocationBD().getLocationDetails(userLoggingsVo);
				System.out.println(locationList.size());
			}
			if(locationList.size()!=0){
				
			for(int j=0;j<locationList.size();j++){
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classstream",userLoggingsVo));
			pstmt.setString(2, streamName.trim());
			pstmt.setString(3, properDescription.trim());
			pstmt.setString(4, detailsPojo.getCreateUser());
			pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
			pstmt.setString(6, null);
			pstmt.setString(7, null);
			pstmt.setString(8, locationList.get(j).getLocation_id());
			 
			rs = pstmt.executeUpdate();
			if(rs==1 || rs>0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Stream Details","Insert",pstmt.toString(),userLoggingsVo);
			    }
			  }
			}
			else{
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classstream",userLoggingsVo));
				pstmt.setString(2, streamName.trim());
				pstmt.setString(3, properDescription.trim());
				pstmt.setString(4, detailsPojo.getCreateUser());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6, null);
				pstmt.setString(7, null);
				pstmt.setString(8, detailsPojo.getLocationId());
				 
				rs = pstmt.executeUpdate();
				if(rs==1 || rs>0){
					HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Stream Details","Insert",pstmt.toString(),userLoggingsVo);
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		/*if (!detailsPojo.getStreamId().equalsIgnoreCase("")) {

			{

				try {

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(StreamSqlUtils.UPDATE_STREAM_DETAILS);

					pstmt.setString(1, detailsPojo.getStreamName().trim());
					pstmt.setString(2, detailsPojo.getDescription().trim());
					pstmt.setString(3, detailsPojo.getCreateUser().trim());

					pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
					pstmt.setString(5, null);
					pstmt.setString(6, null);
					pstmt.setString(7, detailsPojo.getStreamId().trim());

					result = pstmt.executeUpdate();

					if (result == 1) {
						results = true;
					} else {
						results = false;
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}

		} else if (detailsPojo.getStreamId().equalsIgnoreCase("")) {

			try {

				String properDescription = desc
						.replaceAll("[^\\x00-\\x7F]", "");
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn
						.prepareStatement(StreamSqlUtils.INSERT_STREAM_DETAILS);

				pstmt.setString(1,
						IDGenerator.getPrimaryKeyID("campus_classstream"));

				pstmt.setString(2, streamName.trim());
				pstmt.setString(3, properDescription.trim());
				pstmt.setString(4, detailsPojo.getCreateUser());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6, null);
				pstmt.setString(7, null);

				result = pstmt.executeUpdate();

				if (result == 1) {
					results = true;
				} else {
					results = false;
				}

			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
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
		}*/
		finally {
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
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StreamDetailsDaoImpl : insertStreamDetailsDao  Ending");

		
		return rs;

	}

	
	
	
	public int updateStreamDetailsDao(StreamDetailsPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : updateStreamDetailsDao  Starting");
		
		
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
	
		
		
		
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(StreamSqlUtils.UPDATE_STREAM_DETAILS);

			pstmt.setString(1, detailsPojo.getStreamName().trim());
			pstmt.setString(2, detailsPojo.getDescription().trim());
			pstmt.setString(3, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(4,  HelperClass.getCurrentTimestamp());
			pstmt.setString(5, detailsPojo.getLocationId());
			pstmt.setString(6, detailsPojo.getStreamId().trim());

			result = pstmt.executeUpdate();
			if(result>0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Stream Details","Update",pstmt.toString(),userLoggingsVo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		finally {
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
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : updateStreamDetailsDao  Ending");
		
		
		
		return result;
	}
	

	
	public StreamDetailsVO editStreamDetailsDao(StreamDetailsVO detailsVo,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : editStreamDetailsDao Starting");

		StreamDetailsVO stream = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(StreamSqlUtils.GET_STREAM_ID);

			pstmt.setString(1, detailsVo.getStreamId());

			rs = pstmt.executeQuery();
			while (rs.next()) {

				stream = new StreamDetailsVO();

				stream.setStreamId(rs.getString("classstream_id_int"));
				stream.setStreamName(rs.getString("classstream_name_var"));
				stream.setDescription(rs.getString("description"));
				stream.setLocationId(rs.getString("locationId"));
			}

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
				+ " Control in StreamDetailsDaoImpl : editStreamDetailsDao  Ending");

		return stream;
	}

	public String deleteStreamDetailsDao(StreamDetailsVO detailsVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : deleteStreamDetailsDao Starting");

		PreparedStatement pstmt = null;
		PreparedStatement classst = null;
		String check = "",value=null;
		int count = 0;
		Connection conn = null;
		String classsts = null;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			   for(int i=0;i<detailsVo.getStreamIds().length;i++){
					pstmt = conn.prepareStatement(StreamSqlUtils.ACTIVE_INACTIVE_STREAM_DETAILS);
					
					if(detailsVo.getStatus().equalsIgnoreCase("InActive")){
						 pstmt.setString(1, "N");
						 if(detailsVo.getOtherReason()=="" || detailsVo.getOtherReason()==null){
							 pstmt.setString(2, detailsVo.getInactiveReason()); 
						 }
						 else{
							 pstmt.setString(2, detailsVo.getOtherReason());
						 }
						 value="InActive";
						 classsts = "N";
					 }
					 else{
						 pstmt.setString(1, "Y");
						 if(detailsVo.getOtherReason()=="" || detailsVo.getOtherReason()==null){
							 pstmt.setString(2, detailsVo.getActiveReason()); 
						 }
						 else{
							 pstmt.setString(2, detailsVo.getOtherReason());
						 }
						 value="Active";
						 classsts = "Y";
					 }
					pstmt.setString(3, detailsVo.getStreamIds()[i]);
					count = pstmt.executeUpdate();
					if (count > 0) {
						HelperClass.recordLog_Activity(detailsVo.getLog_audit_session(),"Settings","Stream Details",value,pstmt.toString(),conn);
						classst = conn.prepareStatement("UPDATE `campus_classdetail` SET `isActive` = ? WHERE `classstream_id_int` = ?");
						classst.setString(1, classsts);
						classst.setString(2, detailsVo.getStreamIds()[i]);
						classst.executeUpdate();
						System.out.println(classst);
					}
			}
			
			if (count > 0) {
				check ="true";
			} else {
				check ="false";
			}
			
		}  catch (Exception e) {
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : deleteStreamDetailsDao  Ending");

		return check;
		
	}

	public ArrayList<StreamDetailsVO> searchStreamDetailsDao(String searchTerm,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : searchStreamDetailsDao Starting");

		ArrayList<StreamDetailsVO> getstreamlist = new ArrayList<StreamDetailsVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;
		String searchName=searchTerm.split(",")[0];
		String school=searchTerm.split(",")[1];

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(StreamSqlUtils.SEARCH_STREAM_DETAILS);

			pstmt.setString(1, searchName + "%");
			pstmt.setString(2, searchName + "%");
			pstmt.setString(3, searchName + "%");
			if(!school.equalsIgnoreCase("all"))
			pstmt.setString(4, school);
			else
			pstmt.setString(4, "%%");	

			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;

				StreamDetailsVO streamvo = new StreamDetailsVO();

				streamvo.setSno(String.valueOf(sno));
				streamvo.setStreamId(rs.getString("classstream_id_int"));
				streamvo.setStreamName(rs.getString("classstream_name_var"));
				streamvo.setDescription(rs.getString("description"));
				streamvo.setLocationName(rs.getString("Location_Name"));

				getstreamlist.add(streamvo);
			}

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
				+ " Control in StreamDetailsDaoImpl : searchStreamDetailsDao  Ending");

		return getstreamlist;
	}

	public String validateStreamNameDao(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : validateStreamNameDetailsDao Starting");

		String streamname_validate = null,value=null;

		int count = 0;
		PreparedStatement pscheckStreamName = null;
		ResultSet rsCheckStreamName = null;
		Connection conn = null;

		if (detailsVo.getStreamId().equalsIgnoreCase("") || detailsVo.getStreamId()==null) {

			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pscheckStreamName = conn.prepareStatement(StreamSqlUtils.VALIDATE_STREAM_NAME_UPDATE);

				pscheckStreamName.setString(1, detailsVo.getStreamname().trim());
				pscheckStreamName.setString(2, detailsVo.getLocationId().trim());

				rsCheckStreamName = pscheckStreamName.executeQuery();
				while (rsCheckStreamName.next()) {

					count = rsCheckStreamName.getInt(1);
					value = rsCheckStreamName.getString(2);
				}
				
				if (count > 0 && value.equalsIgnoreCase("N")) {
					streamname_validate = "inactive";
				}
				else if (count > 0 && value.equalsIgnoreCase("Y")) {
					streamname_validate = "true";
				} 
				else {
					streamname_validate = "false";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			 finally {
					try {
						if (rsCheckStreamName != null && (!rsCheckStreamName.isClosed())) {
							rsCheckStreamName.close();
						}

						if (pscheckStreamName != null
								&& (!pscheckStreamName.isClosed())) {
							pscheckStreamName.close();
						}
						if (conn != null && (!conn.isClosed())) {
							conn.close();
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						e.printStackTrace();
					}
				}
		}

		else if (!detailsVo.getStreamId().equalsIgnoreCase(""))

		{
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pscheckStreamName = conn.prepareStatement(StreamSqlUtils.VALIDATE_STREAM_NAME_EDIT);

				pscheckStreamName.setString(1, detailsVo.getStreamId().trim());
				pscheckStreamName.setString(2, detailsVo.getStreamname().trim());
				pscheckStreamName.setString(3, detailsVo.getLocationId().trim());
                System.out.println("VALIDATE_STREAM_NAME_EDIT -->>"+pscheckStreamName);
				rsCheckStreamName = pscheckStreamName.executeQuery();

				while (rsCheckStreamName.next()) {
					count = rsCheckStreamName.getInt(1);
					value = rsCheckStreamName.getString(2);

				}
				
				if (count > 0 && value.equalsIgnoreCase("N")) {
					streamname_validate = "inactive";
				}
				else if (count > 0 && value.equalsIgnoreCase("Y")) {
					streamname_validate = "true";
				} 
				else {
					streamname_validate = "false";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rsCheckStreamName != null && (!rsCheckStreamName.isClosed())) {
						rsCheckStreamName.close();
					}

					if (pscheckStreamName != null
							&& (!pscheckStreamName.isClosed())) {
						pscheckStreamName.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : validateStreamNameDao  Ending");

		return streamname_validate;
	}
	
	public ArrayList<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear,String status, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : searchByLocationOnly Starting");
		
		ArrayList<StreamDetailsVO> streamList = new ArrayList<StreamDetailsVO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		String locationid = locationId;
		
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pst = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_BY_LOCATION_ONLY);
			pst.setString(1,locationid);
			pst.setString(2,status);
			rs = pst.executeQuery();
				
			while(rs.next()){
				StreamDetailsVO streamPojo = new StreamDetailsVO();
				streamPojo.setStreamId(rs.getString("classstream_id_int"));
				streamPojo.setStreamName(rs.getString("classstream_name_var"));
				streamPojo.setDescription(rs.getString("description"));
				streamPojo.setLocationName(rs.getString("Location_Name"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					streamPojo.setStatus("Active");
				}
				else{
					streamPojo.setStatus("InActive");
				}
				
				if(rs.getString("Remarks")=="" || rs.getString("Remarks")==null){
					 
					streamPojo.setReason("");
				}
				else{
					streamPojo.setReason(rs.getString("Remarks"));
				}
				
				streamList.add(streamPojo);
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
				+ " Control in StreamDetailsDaoImpl : searchByLocationOnly Ending");
		
		return streamList;
	}




	public String validateDuplicateLocation(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsDaoImpl : validateDuplicateLocation Starting");

		String streamname_validate = null,value=null;

		int count = 0;
		PreparedStatement pscheckStreamName = null;
		ResultSet rsCheckStreamName = null;
		Connection conn = null;

			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pscheckStreamName = conn.prepareStatement(StreamSqlUtils.VALIDATE_LOCATION_STREAM_NAME_EDIT);
                
				pscheckStreamName.setString(1, detailsVo.getLocationId().trim());
				pscheckStreamName.setString(2, detailsVo.getStreamname().trim());
				
                System.out.println("VALIDATE_LOCATION_STREAM_NAME_EDIT -->>"+pscheckStreamName);
				rsCheckStreamName = pscheckStreamName.executeQuery();

				while (rsCheckStreamName.next()) {
					count = rsCheckStreamName.getInt(1);
					value = rsCheckStreamName.getString(2);
				}
				
				if (count > 0 && value.equalsIgnoreCase("N")) {
					streamname_validate = "inactive";
				}
				else if (count > 0 && value.equalsIgnoreCase("Y")) {
					streamname_validate = "true";
				} 
				else {
					streamname_validate = "false";
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				try {
					if (rsCheckStreamName != null && (!rsCheckStreamName.isClosed())) {
						rsCheckStreamName.close();
					}

					if (pscheckStreamName != null
							&& (!pscheckStreamName.isClosed())) {
						pscheckStreamName.close();
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
				+ " Control in StreamDetailsDaoImpl : validateDuplicateLocation  Ending");

		return streamname_validate; 
	}
	
}