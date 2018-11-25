package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SettingsFileUploadPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentExcelUploadPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.SettingsFileUploadVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.classVo;
import com.centris.campus.vo.studentExcelUploadVo;
import com.itextpdf.text.log.SysoLogger;


public class SettingsFileUploadDaoImpl {
	private static Logger logger = Logger.getLogger(SettingsFileUploadDaoImpl.class);
	
	public int checkLocationName(String locationName, String locationId, Connection connection) {
		return 0;
	}
	
	public Set<StreamDetailsVO> insertStream(List<StreamDetailsPojo> successlist, Set<StreamDetailsVO> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertStream : Starting");

		Set<StreamDetailsVO> failurelistOnDiompl = new LinkedHashSet<StreamDetailsVO>();
		StreamDetailsVO uploadStream = new StreamDetailsVO();
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt = null;
		ResultSet rs=null;
		
		try{
			/*System.out.println("DAOIMPL Is Working Library Excel file Upload");*/
			conn = JDBCConnection.getSeparateConnection(custdetails);
		
			for(int i=0;i<successlist.size();i++){
				
				//System.out.println("========"+successlist.size());
				/*System.out.println("========"+successlist.size());*/
				conn.setAutoCommit(false);

				StreamDetailsVO vo = new StreamDetailsVO();
				vo.setStreamName(successlist.get(i).getStreamName());
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setDescription(successlist.get(i).getDescription());
				vo.setLocationId(successlist.get(i).getLocationId());
				
				pstm1=conn.prepareStatement("SELECT count(*) FROM `campus_classstream` WHERE `locationId`=? AND `classstream_name_var`=?");
				pstm1.setString(1,vo.getLocationId());
				pstm1.setString(2,vo.getStreamName());
				
				////System.out.println("check status"+pstm1);
				/*System.out.println("check status"+pstm1);*/
				rs = pstm1.executeQuery();
				while(rs.next()){
					if(rs.getInt(1) == 0){
						////System.out.println("count data***********"+rs.getInt(1));
						/*System.out.println("count data***********"+rs.getInt(1));*/
						pstmt = conn.prepareStatement("INSERT INTO campus_classstream (classstream_id_int,classstream_name_var,description, createuser, createdate,locationId) VALUES (?,?,?,?,?,?)");
						pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_classstream",custdetails));
						pstmt.setString(2, vo.getStreamName());
						pstmt.setString(3, vo.getDescription());
						pstmt.setString(4, userId);
						pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
						pstmt.setString(6, vo.getLocationId());
						
						int result =pstmt.executeUpdate();
						if(result > 0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","StreamExcelUpload","Insert",pstmt.toString(),conn);
							conn.commit();
					    }
						else{
							uploadStream.setReason("Stream already available with '"+vo.getLocationName()+"'");
						}
				}
			}
				
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertStream : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertClass(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertClass : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				////System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);
				
				classVo vo = new classVo();
				classVo uploadClass = new classVo();
				
				vo.setStreamName(successlist.get(i).getStreamName());
				vo.setStreamId(successlist.get(i).getStreamId());
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassName(successlist.get(i).getClassName());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND locationId=?");
				pstm1.setString(1, vo.getClassName());
				pstm1.setString(2,vo.getStreamId());
				pstm1.setString(3,vo.getLocationId());
				////System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setReason("Class "+vo.getClassName()+" already added for "+vo.getStreamName()+" in Branch:"+vo.getLocationName()+"");
					failurelist.add(uploadClass);
				}
				else{
					try{
					pstmt = conn.prepareStatement("INSERT INTO campus_classdetail(classdetail_id_int,classstream_id_int, classdetails_name_var, createuser,createdate,locationId) VALUES (?,?,?,?,?,?)");
					pstmt.setString(1, "CCD"+HelperClass.classNameExcelUpload(vo.getClassName()));
					pstmt.setString(2, vo.getStreamId());
					pstmt.setString(3, vo.getClassName());
					pstmt.setString(4,userId);
					pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
					pstmt.setString(6, vo.getLocationId());
					
				/*	//System.out.println("insert data::::::::: Class Insert"+pstmt);
					//System.out.println("Location Id"+vo.getLocationId());
					//System.out.println("getStream Id"+vo.getStreamId());
					//System.out.println("vo.getStreamName()"+vo.getStreamName());
					//System.out.println("vo.getLocationName()"+vo.getLocationName());*/
					
					int result =pstmt.executeUpdate();
					if(result > 0)
						HelperClass.recordLog_Activity(log_audit_session,"Settings","ClassExcelUpload","Insert",pstmt.toString(),conn);
					conn.commit();
					}
					catch(SQLIntegrityConstraintViolationException e){
						////System.out.println("SQLIntegrityConstraintViolationException happend ");
						
						uploadClass.setStreamName(vo.getStreamName());
						uploadClass.setLocationName(vo.getLocationName());
						uploadClass.setClassName(vo.getClassName());
						
						uploadClass.setReason("Duplicate entry for Class");
						
						failurelist.add(uploadClass);
					}
					
					}
			}
		}
		
		
				catch (SQLException sqle) {
					sqle.printStackTrace();
					logger.error(sqle.getMessage(),sqle);
				} 
				
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertClass : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertDivision(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertDivision : Starting");
		
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		int value=0;
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(custdetails);
		
			for(int i=0;i<successlist.size();i++){
				
				////System.out.println("========"+successlist.size());
				

				classVo vo = new classVo();
				
				classVo uploadClass = new classVo();
				
				vo.setStreamName(successlist.get(i).getStreamName());
				vo.setStreamId(successlist.get(i).getStreamId());
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setDivisionName(successlist.get(i).getDivisionName());
				vo.setStrength(successlist.get(i).getStrength());
				
				pstm1=conn.prepareStatement("SELECT COUNT(*) FROM campus_classsection ccs JOIN campus_classdetail ccd ON ccd.classdetail_id_int=ccs.classdetail_id_int JOIN campus_classstream cstr ON cstr.classstream_id_int=ccd.classstream_id_int AND cstr.locationId=ccd.locationId WHERE ccs.classdetail_id_int=? AND ccs.classsection_name_var=? AND ccs.locationId=? AND ccd.classstream_id_int=?");
				pstm1.setString(1,vo.getClassId());
				pstm1.setString(2, vo.getDivisionName());
				pstm1.setString(3,vo.getLocationId());
				pstm1.setString(4,vo.getStreamId());
				
			    System.out.println("campus_classsection -->> "+pstm1);
				rs = pstm1.executeQuery();
				while(rs.next()){
					value=rs.getInt(1);
				}
					
				if(value > 0)
				  {
					
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setDivisionName(vo.getDivisionName());
					uploadClass.setStrength(vo.getStrength());
					uploadClass.setReason("Duplicate class and division");
					failurelist.add(uploadClass);
				}
				
				else{
					
					pstmt = conn.prepareStatement("INSERT INTO `campus_classsection`(`classsection_id_int`,`classdetail_id_int`,`classsection_name_var`,`classsection_strength_int`,`locationId`,`createuser`,`createdate`)VALUES(?,?,?,?,?,?,?)");
					pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_classsection",custdetails));
					pstmt.setString(2, vo.getClassId());
					pstmt.setString(3, vo.getDivisionName());
					pstmt.setString(4, vo.getStrength());
					pstmt.setString(5, vo.getLocationId());
					pstmt.setString(6,userId);
					pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
					System.out.println("insert data Class Insert -->>"+pstmt);
					int result = pstmt.executeUpdate();
					
					if(result>0){
						HelperClass.recordLog_Activity(log_audit_session,"Settings","DivisionExcelUpload","Insert",pstmt.toString(),conn);
					}
			
				/*	uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setDivisionName(vo.getDivisionName());
					uploadClass.setStrength(vo.getStrength());
					uploadClass.setReason("Duplicate class and division");
					failurelist.add(uploadClass);*/
					
				   }
				 
			  }
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			try{
				
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertDivision : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertOccupation(List<ClassPojo> successlist, Connection connection, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertOccupation : Starting");
		
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null;
		ResultSet rs1=null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);

			for(int i=0;i<successlist.size();i++){
				IDGenerator id = new IDGenerator();
				classVo vo = new classVo();
				
				vo.setOccupationName(successlist.get(i).getOccupationName());

				pstm1 =conn.prepareStatement("SELECT COUNT(`occupation`) FROM `campus_occupation` WHERE `occupation`=?");
				pstm1.setString(1, successlist.get(i).getOccupationName());
				rs1 = pstm1.executeQuery();
				while(rs1.next()){
					if(rs1.getInt(1)==0){
						//System.out.println("INSIDE SECOND IF LOOP");
						pstm=conn.prepareStatement("INSERT INTO `campus_occupation`(`occupationId`,`occupation`,`createdBy`,`createdTime`) VALUES(?,?,?,?)");
						pstm.setString(1, IDGenerator.getPrimaryKeyID("campus_occupation",userLoggingsVo));
						pstm.setString(2,vo.getOccupationName());
						pstm.setString(3, userId);
						pstm.setTimestamp(4, HelperClass.getCurrentTimestamp());
						int result = pstm.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","OccupationExcelUpload","Insert",pstm.toString(),conn);
							conn.commit();
							pstm.close();
								}
					
					}
			}
		}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs1!=null && !(rs1.isClosed())){
					rs1.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(pstm!=null && !(pstm.isClosed())){
					pstm.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertOccupation : Ending");
	
		return failurelistOnDiompl;
	}

	public Set<classVo> insertReligion(List<ClassPojo> successlist, Connection connection, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertReligion : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm=null,pstm1=null;
		ResultSet rs1=null;
		try{
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			conn.setAutoCommit(false);
			for(int i=0;i<successlist.size();i++){
				classVo vo = new classVo();
				vo.setReligionName(successlist.get(i).getReligionName());

				pstm1 =conn.prepareStatement("SELECT COUNT(`religion`) FROM `campus_religion` WHERE `religion`=?");
				pstm1.setString(1, successlist.get(i).getReligionName());
				rs1 = pstm1.executeQuery();
				while(rs1.next()){
					if(rs1.getInt(1)==0){
						//System.out.println("INSIDE SECOND IF LOOP");
						pstm=conn.prepareStatement("INSERT INTO `campus_religion`(`religionId`,`religion`,`createdBy`,`createdTime`) VALUES(?,?,?,?)");
						pstm.setString(1, IDGenerator.getPrimaryKeyID("campus_religion",userLoggingsVo));
						pstm.setString(2,vo.getReligionName());
						pstm.setString(3, userId);
						pstm.setTimestamp(4, HelperClass.getCurrentTimestamp());
						int result = pstm.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","ReligionExcelUpload","Insert",pstm.toString(),conn);
							conn.commit();/*uploaLibraryXSLVo.getCreated_by()*/
							pstm.close();
					}
					}
			}
		}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs1!=null && !(rs1.isClosed())){
					rs1.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(pstm!=null && !(pstm.isClosed())){
					pstm.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertReligion : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertCaste(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertCaste : Starting");
		
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				//////System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);

				classVo vo = new classVo();
				vo.setReligionName(successlist.get(i).getReligionName());
				vo.setReligionId(successlist.get(i).getReligionId());
				vo.setCasteName(successlist.get(i).getCasteName());
				
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_caste` WHERE `religionId` =? AND `caste` =?");
				pstm1.setString(1, vo.getReligionId());
				pstm1.setString(2,vo.getCasteName());
				////System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setReligionId(vo.getReligionId());
					uploadClass.setCasteName(vo.getCasteName());
					uploadClass.setReligionName(vo.getReligionName());
					uploadClass.setReason("Caste '"+vo.getCasteName()+"' already added with Religion '"+vo.getReligionName()+"' ");
					failurelist.add(uploadClass);
				}

				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_caste`(`casteId`,`religionId`,`caste`,`createdBy`,`createdTime`)VALUES(?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_caste",userLoggingsVo));
						pstmt.setString(2, vo.getReligionId());
						pstmt.setString(3, vo.getCasteName());
						pstmt.setString(4,userId);
						pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
						////System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result = pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","CasteExcelUpload","Insert",pstmt.toString(),conn);
						conn.commit();
					}
			}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertCaste : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertCasteCategory(List<ClassPojo> successlist, Set<classVo> failurelist,
			Connection connection, String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertCasteCategory : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstmt=null,pstm1=null;
		ResultSet rs=null;
		
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				////System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);

				classVo vo = new classVo();
				vo.setReligionName(successlist.get(i).getReligionName());
				vo.setCasteName(successlist.get(i).getCasteName());
				vo.setCasteCategoryName(successlist.get(i).getCasteCategoryName());
				vo.setReligionId(successlist.get(i).getReligionId());
				vo.setCasteId(successlist.get(i).getCasteId());
				
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_caste_category` WHERE  `casteCategory`=? AND `religionId`=? AND `casteId`=?");
				pstm1.setString(1, vo.getCasteCategoryName());
				pstm1.setString(2,vo.getReligionId());
				pstm1.setString(3,vo.getCasteId());
				////System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setReligionId(vo.getReligionId());
					uploadClass.setReligionName(vo.getReligionName());
					uploadClass.setCasteId(vo.getCasteId());
					uploadClass.setCasteName(vo.getCasteName());
					uploadClass.setCasteCategoryName(vo.getCasteCategoryName());
					uploadClass.setReason("Caste Category already exists in Caste and Religion ");
					failurelist.add(uploadClass);
				}

				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_caste_category`(`castCatId`,`religionId`,`casteId`,`casteCategory`,`createdBy`,`createdTime`)VALUES(?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_caste_category",userLoggingsVo));
						pstmt.setString(2, vo.getReligionId());
						pstmt.setString(3, vo.getCasteId());
						pstmt.setString(4, vo.getCasteCategoryName());
						pstmt.setString(5,userId);
						pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
						////System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result=pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","CasteCategoryExcelUpload","Insert",pstmt.toString(),conn);
							conn.commit();
						}
				}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertCasteCategory : Ending");
		return failurelistOnDiompl;
		
	}

	public Set<classVo> insertSpec(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstmt=null,pstm1=null;
		ResultSet rs=null;
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				////System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);

		
				classVo vo = new classVo();
				
				vo.setStreamName(successlist.get(i).getStreamName());
				vo.setStreamId(successlist.get(i).getStreamId());
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setSpecializationName(successlist.get(i).getSpecializationName());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_class_specialization` WHERE `locationId`=? AND `ClassDetails_Id`=? AND `Stream_Id` =? AND `Specialization_name`=?");
				pstm1.setString(1,vo.getLocationId());
				pstm1.setString(2, vo.getClassId());
				pstm1.setString(3,vo.getStreamId());
				pstm1.setString(4, vo.getSpecializationName());
				////System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setClassId(vo.getClassId());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setSpecializationName(vo.getSpecializationName());
					uploadClass.setReason("Duplicate Specialization entry with class");
					failurelist.add(uploadClass);
				}
				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_class_specialization`(`Specialization_Id`,`locationId`,`ClassDetails_Id`,`Stream_Id`,`Specialization_name`,`CreateUser`,`CreateDate`) VALUES(?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_class_specialization",userLoggingsVo));
						pstmt.setString(2, vo.getLocationId());
						pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getStreamId());
						pstmt.setString(5, vo.getSpecializationName());
						pstmt.setString(6,userId);
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						
						////System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result=pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","SpecializationExcelUpload","Insert",pstmt.toString(),conn);
						conn.commit();
					}
			}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Ending");
		return failurelistOnDiompl;
	}

	public Set<classVo> insertHoliday(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String accyear, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertHoliday : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		
		try{
			////System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				////System.out.println("========"+successlist.size());
				conn.setAutoCommit(false);


				classVo vo = new classVo();
				
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setHolidaytype(successlist.get(i).getHolidaytype());
				vo.setHolidayDate(successlist.get(i).getHolidayDate());
				vo.setHolidayName(successlist.get(i).getHolidayName());
				vo.setAccyearId(successlist.get(i).getAccyearId());
				vo.setAccyearName(successlist.get(i).getAccyearName());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_holidaymaster` WHERE `HOLIDAY_DATE`=? AND `HOLIDAY_NAME`=? AND `HOLIDAY_TYPE`=? AND `LOC_ID`=? AND `CURRENT_YEAR`=?");
				pstm1.setString(1,HelperClass.convertUIToDatabase(vo.getHolidayDate()));
				pstm1.setString(2, vo.getHolidayName());
				pstm1.setString(3,vo.getHolidaytype());
				pstm1.setString(4, vo.getLocationId());
				pstm1.setString(5,vo.getAccyearId());
				////System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setHolidayName(vo.getHolidayName());
					uploadClass.setHolidayDate(vo.getHolidayDate());
					uploadClass.setHolidaytype(vo.getHolidaytype());
					uploadClass.setAccyearId(vo.getAccyearId());
					uploadClass.setAccyearName(HelperClass.getAcademicYearFace(vo.getAccyearId(),userLoggingsVo));
					uploadClass.setReason("Holiday setup already exists with '"+HelperClass.getAcademicYearFace(vo.getAccyearId(),userLoggingsVo)+"");
					failurelist.add(uploadClass);
				}
				else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_holidaymaster`(`HOL_ID`,`HOLIDAY_DATE`,`WEEKDAY`,`HOLIDAY_NAME`,`HOLIDAY_TYPE`,`CURRENT_YEAR`,`CREATEDDATE`,`CREATEDBY`,`LOC_ID`)VALUES(?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_holidaymaster",userLoggingsVo));
						pstmt.setString(2, HelperClass.convertUIToDatabaseSettingReports(vo.getHolidayDate()));
					/*	pstmt.setString(3, HelperClass.getWeekDay(vo.getHolidayDate()));getWeekDaySettingReports*/
						pstmt.setString(3, HelperClass.getWeekDaySettingReports(vo.getHolidayDate()));
						pstmt.setString(4, vo.getHolidayName());
						pstmt.setString(5, vo.getHolidaytype());
						pstmt.setString(6, vo.getAccyearId());
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8,userId);
						pstmt.setString(9, vo.getLocationId());
						////System.out.println("insert data::::::::: Class Insert"+pstmt);
						int result=  pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Settings","SpecializationExcelUpload","Insert",pstmt.toString(),conn);
							conn.commit();
					}
				}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertHoliday : Ending");
		return failurelistOnDiompl;
	}
	public String getAccyearId(String academicYear, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String accyearId = null;
		String StudentName = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `acadamic_id` FROM `campus_acadamicyear` WHERE `acadamic_year`=?  and `isActive`='Y'");
			psmt.setString(1,academicYear);
			rs = psmt.executeQuery();
			if(rs.next()){
				accyearId = rs.getString("acadamic_id");
			}
			else{
				accyearId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return accyearId;
	}

	public String getLocationId(String locationName, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String locId = null;
		String StudentName = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `Location_Id` FROM `campus_location` WHERE `Location_Name`=?  and `isActive`='Y'");
			psmt.setString(1,locationName);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				locId = rs.getString("Location_Id");
			}
			else{
				locId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return locId;
		
	}

	public String getStudentId(String studentIdNo, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String studentIdNbr = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `student_id_int` FROM `campus_student` WHERE student_admissionno_var=?  and student_status_var='active'");
			psmt.setString(1,studentIdNo);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				studentIdNbr = rs.getString("student_id_int");
			}
			else{
				studentIdNbr = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return studentIdNbr;
	}
	public String getExamTypeId(String examType, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String examTypeId = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `examtypeid`,exam_prefix FROM `campus_examtype` WHERE `examtypename`=?"); /*  and `isActive`='Y'*/
			psmt.setString(1,examType);
			rs = psmt.executeQuery();
			////System.out.println("INDEX OUT OF BOND "+psmt);
			if(rs.next()){
				examTypeId = rs.getString("examtypeid")+","+ rs.getString("exam_prefix");
			}
			else{
				examTypeId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return examTypeId;
	}

	public String getstreamId(String streamName, String locationId, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String streamId = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `classstream_id_int` FROM `campus_classstream` WHERE `classstream_name_var`=? AND `locationId`=?  and `isActive`='Y'");
			psmt.setString(1,streamName);
			psmt.setString(2,locationId);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				streamId = rs.getString("classstream_id_int");
			}
			else{
				streamId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return streamId;
	}

public String getClassId(String className, String streamId, String locationId, Connection connection) {
	PreparedStatement psmt= null;
	ResultSet rs =null;
	String classId = null;
	Connection conn=null;
	try{
		psmt= connection.prepareStatement("SELECT `classdetail_id_int` FROM `campus_classdetail` WHERE `classdetails_name_var`=? AND `classstream_id_int`=? AND `locationId`=?  and `isActive`='Y'");
		psmt.setString(1,className);
		psmt.setString(2,streamId);
		psmt.setString(3,locationId);
		////System.out.println(psmt);
		rs = psmt.executeQuery();
		if(rs.next()){
			classId = rs.getString("classdetail_id_int");
		}
		else{
			classId = "notfound";
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try{
			if(rs != null && !(rs.isClosed())){
				rs.close();
			}
			if(psmt != null && !(psmt.isClosed())){
				psmt.close();
			}
			if(conn != null && !(conn.isClosed())){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return classId;
}

	public String getsubjectId(String subject, String classId, String locationId, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String subjectId = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `subjectID` FROM `campus_subject` WHERE `subjectName`=? AND `locationId`=? AND `classid`=?  and `isActive`='Y'");
			psmt.setString(1,subject);
			psmt.setString(2,classId);
			psmt.setString(3,locationId);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				subjectId = rs.getString("subjectID");
			}
			else{
				subjectId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return subjectId;
	}

	public String getsectionId(String sectionName, String classId, String locationId, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String sectionId = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `classsection_id_int` FROM `campus_classsection` WHERE `classsection_name_var`=? AND `classdetail_id_int`=? AND `locationId`=?  and `isActive`='Y'");
			psmt.setString(1,sectionName);
			psmt.setString(2,classId);
			psmt.setString(3,locationId);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				sectionId = rs.getString("classsection_id_int");
			}
			else{
				sectionId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sectionId;
	}

	public String checkValidStartEnddates(String startDate, String academicYear, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String validdates = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `acadamic_id` FROM `campus_acadamicyear` WHERE ?  BETWEEN startDate AND endDate and acadamic_year=?  and `isActive`='Y'");
			psmt.setString(1,HelperClass.convertUIToDatabase(startDate));
			psmt.setString(2,academicYear);
			rs = psmt.executeQuery();
			if(rs.next()){
				validdates = rs.getString("acadamic_id");
			}
			else{
				validdates = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return validdates;
	}

	public String getExamIdValue(String examCode, String locationId, String accyearId, String examType, String classId,
			Connection connection, String ExamName) {
		
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String examId = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `examid` FROM `campus_examination` WHERE `examcode`=? AND `loc_id`=? AND `acadamicyear`=? and examtype=? and classid=? and examname=?");
			psmt.setString(1,examCode);
			psmt.setString(2,locationId);
			psmt.setString(3,accyearId);
			psmt.setString(4,examType);
			psmt.setString(5, classId);
			psmt.setString(6, ExamName);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				examId = rs.getString("examid");
			}
			else{
				examId = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return examId;
	}

	public String getexamCode(String examName, String examcode, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String examCode = null;
		Connection conn=null;
		try{
			psmt= connection.prepareStatement("SELECT `examcode` FROM `campus_examination` WHERE `examname`=? and `examcode`=? ");
			psmt.setString(1,examName);
			psmt.setString(2,examcode);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				examCode = rs.getString("examcode");
			}
			else{
				examCode = "notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return examCode;
	}

	public List<studentExcelUploadVo> getstartEndDates(String examTypeId, String classId, String examName, String academicId, String schoolId, Connection connection) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		Connection conn=null;
		List<studentExcelUploadVo> list = new ArrayList<studentExcelUploadVo>();
		try{
			psmt= connection.prepareStatement("SELECT `startdate`,`enddate` FROM  `campus_examination` WHERE `examtype`=? AND `classid`=?  AND `examname`=? AND `acadamicyear`=? AND `loc_id`=?");
			psmt.setString(1,examTypeId);
			psmt.setString(2,classId);
			psmt.setString(3,examName);
			psmt.setString(4,academicId);
			psmt.setString(5,schoolId);
			////System.out.println(psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				studentExcelUploadVo vo = new studentExcelUploadVo();
				vo.setStartdateCheck(rs.getString("startdate"));
				vo.setEnddateCheck(rs.getString("enddate"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	public String checkStudentIdNoMatchId(String studentName, String sectionId, Connection connection, String studentIdNo) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String id = null;
		Connection conn=null;
		try{
			
			//////System.out.println("Nameeeeeeeeeeeeeeeeeeeeeee id");
			psmt=connection.prepareStatement("SELECT s.`student_id_int`,CONCAT(`student_fname_var`,'',`student_lname_var`) AS studentName,c.`classdetail_id_int`,c.`classsection_id_int`,s.`student_admissionno_var` FROM `campus_student` s LEFT JOIN `campus_student_classdetails` c ON c.`student_id_int`=s.`student_id_int`WHERE CONCAT(s.`student_fname_var`,' ',`student_lname_var`) = ? && `classsection_id_int`=? && s.student_admissionno_var=?  ");
			psmt.setString(1, studentName);
			psmt.setString(2, sectionId);
			psmt.setString(3, studentIdNo);
			
		//	//System.out.println(psmt);
			rs = psmt.executeQuery();
			while(rs.next()){
				id = rs.getString("student_admissionno_var");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return id;
	}
	public String checkStudentIdNoMatchName(String studentName, String sectionId, Connection connection, String studentIdNo) {
		PreparedStatement psmt= null;
		ResultSet rs =null;
		String name = null;
		Connection conn=null;
		try{
			
			psmt=connection.prepareStatement("SELECT s.`student_id_int`,CONCAT(`student_fname_var`,' ',`student_lname_var`) AS studentName,c.`classdetail_id_int`,c.`classsection_id_int`,s.`student_admissionno_var` FROM `campus_student` s LEFT JOIN `campus_student_classdetails` c ON c.`student_id_int`=s.`student_id_int` WHERE CONCAT(s.`student_fname_var`,' ',`student_lname_var`) = ? && `classsection_id_int`=? and s.student_admissionno_var=?");
			psmt.setString(1, studentName);
			psmt.setString(2, sectionId);
			psmt.setString(3, studentIdNo);
		////System.out.println("STUDENT ID NUMBER "+psmt);
			rs = psmt.executeQuery();
			if(rs.next()){
				name = rs.getString("studentName");
			}
			else{
				name="notfound";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null && !(rs.isClosed())){
					rs.close();
				}
				if(psmt != null && !(psmt.isClosed())){
					psmt.close();
				}
				if(conn != null && !(conn.isClosed())){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return name;
	}
	public Set<studentExcelUploadVo> insertExamReport(List<StudentExcelUploadPojo> successlist,
			Set<studentExcelUploadVo> failurelist, Connection connection, UserLoggingsPojo userLoggingsVo, String log_audit_session, UserDetailVO user) throws SQLException {
		Set<studentExcelUploadVo> failurelistOnDiompl = new LinkedHashSet<studentExcelUploadVo>();
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null;
		ResultSet rs=null;
		int overridecount=0;
		try{
			//conn = JDBCConnection.getSeparateConnection();
			conn=connection;
			for(int i=0;i<successlist.size();i++){
				studentExcelUploadVo uploadClass = new studentExcelUploadVo();
				conn.setAutoCommit(false);
				studentExcelUploadVo vo = new studentExcelUploadVo();

				vo.setStudentName(successlist.get(i).getStudentName());
				vo.setStudentId(successlist.get(i).getStudentId());
				vo.setStudentIdNo(successlist.get(i).getStudentIdNo());
				vo.setSchoolName(successlist.get(i).getSchoolName());
				vo.setStreamName(successlist.get(i).getStreamName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setAcademicYear(successlist.get(i).getAcademicYear());
				vo.setAccyearId(successlist.get(i).getAccyearId());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setSectionId(successlist.get(i).getSectionId());
				vo.setSectionName(successlist.get(i).getSectionName());
				vo.setExamId(successlist.get(i).getExamId());
				vo.setExamName(successlist.get(i).getExamName());
				vo.setExamCode(successlist.get(i).getExamCode());
				vo.setExamType(successlist.get(i).getExamTypeName());
				vo.setStartdate(successlist.get(i).getStartdate());
				vo.setEnddate(successlist.get(i).getEnddate());
				vo.setSubjectName(successlist.get(i).getSubjectName());
				vo.setAttandance(successlist.get(i).getAttandance());
				vo.setSubjectId(successlist.get(i).getSubjectId());
				vo.setTestMaximumMarks(successlist.get(i).getTestMaximumMarks());
				vo.setTestScoredMarks(successlist.get(i).getTestScoredMarks());
				vo.setNotebookMaximumMarks(successlist.get(i).getNotebookMaximumMarks());
				vo.setNotebookScoredMarks(successlist.get(i).getNotebookScoredMarks());
				vo.setSubjectMaximumMarks(successlist.get(i).getSubjectMaximumMarks());
				vo.setSubjectScoredMarks(successlist.get(i).getSubjectScoredMarks());
				vo.setWorkEducation(successlist.get(i).getWorkEducation());
				vo.setArtEducation(successlist.get(i).getArtEducation());
				vo.setHealthEducation(successlist.get(i).getHealthEducation());
				vo.setDescipline(successlist.get(i).getDescipline());
				vo.setRemarks(successlist.get(i).getRemarks());
				pstm1=conn.prepareStatement("SELECT * FROM `campus_studentwise_mark_details` WHERE `Academic_yearid`=? AND `stu_id`=? AND `exam_id`=? AND `classid`=? AND`sectionid`=? AND `sub_id`=? AND `location_id`=?");
				pstm1.setString(1,vo.getAccyearId());
				pstm1.setString(2,vo.getStudentId());
				pstm1.setString(3,vo.getExamId());
				pstm1.setString(4,vo.getClassId());
				pstm1.setString(5,vo.getSectionId());
				pstm1.setString(6,vo.getSubjectId());
				pstm1.setString(7,vo.getLocationId());

				rs = pstm1.executeQuery();
				if(rs.next()){
					uploadClass.setStu_mark_id(rs.getString("Stu_mark_id"));
					uploadClass.setAcademicYear(vo.getAcademicYear());
					uploadClass.setStudentName(vo.getStudentName());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setSectionName(vo.getSectionName());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSchoolName(vo.getSchoolName());
					uploadClass.setStudentIdNo(vo.getStudentIdNo());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setExamCode(vo.getExamCode());
					uploadClass.setAttandance(vo.getAttandance());
					uploadClass.setExamType(vo.getExamType());
					uploadClass.setStartdate(vo.getStartdate());
					uploadClass.setEnddate(vo.getEnddate());
					uploadClass.setTestMaximumMarks(vo.getTestMaximumMarks());
					uploadClass.setTestScoredMarks(vo.getTestScoredMarks());
					uploadClass.setNotebookMaximumMarks(vo.getNotebookMaximumMarks());
					uploadClass.setNotebookScoredMarks(vo.getNotebookScoredMarks());
					uploadClass.setSubjectMaximumMarks(vo.getSubjectMaximumMarks());
					uploadClass.setSubjectScoredMarks(vo.getSubjectScoredMarks());
					uploadClass.setReason("Duplicate Data Upload ");
					List<Object> overRideIteratorList=new ArrayList<Object>();
					String overRideString="";
					overRideIteratorList.add(rs.getString("Stu_mark_id"));
					overRideIteratorList.add(vo.getAccyearId());
					overRideIteratorList.add(vo.getStudentId());
					overRideIteratorList.add(vo.getExamId());
					overRideIteratorList.add(vo.getStudentIdNo());
					overRideIteratorList.add(vo.getClassId());
					overRideIteratorList.add(vo.getSectionId());
					overRideIteratorList.add(vo.getExamCode());
					overRideIteratorList.add(vo.getExamName());
					overRideIteratorList.add(vo.getSubjectId());
					overRideIteratorList.add(vo.getSubjectName());
					overRideIteratorList.add(vo.getAttandance());

					overRideIteratorList.add(vo.getTestMaximumMarks());
					overRideIteratorList.add(vo.getTestScoredMarks());

					overRideIteratorList.add(vo.getSubjectId());
					overRideIteratorList.add(vo.getLocationId());

					if(vo.getNotebookScoredMarks()==""){
						overRideIteratorList.add("null");
					}
					else{
						overRideIteratorList.add(vo.getNotebookScoredMarks());
					}

					if(vo.getSubjectScoredMarks()==""){
						overRideIteratorList.add("null");
					}
					else{
						overRideIteratorList.add(vo.getSubjectScoredMarks());
					}


					if(vo.getNotebookMaximumMarks()==""){
						overRideIteratorList.add("null");
					}
					else{
						overRideIteratorList.add(vo.getNotebookMaximumMarks());
					}


					if(vo.getSubjectMaximumMarks()==""){
						overRideIteratorList.add("null");
					}
					else{
						overRideIteratorList.add(vo.getSubjectMaximumMarks());
					}

					overRideIteratorList.add(vo.getExamType());

					for(int k=0;k<overRideIteratorList.size();k++){
						overRideString=overRideString+overRideIteratorList.get(k)+"'";
					}
					uploadClass.setDataForOverRide(overRideString);
					uploadClass.setOverridecount(overridecount);

					failurelist.add(uploadClass);

				}else {
					//System.out.println("else loop"+(vo.getExamType()));
					if(vo.getExamType().equalsIgnoreCase("EMT2") || vo.getExamType().equalsIgnoreCase("EMT3")){
						pstmt = conn.prepareStatement("INSERT INTO `campus_studentwise_mark_details`"
								+ "(`Stu_mark_id`,`Academic_yearid`,`stu_id`,`exam_id`,`admission_no`,"
								+ "`classid`,`sectionid`,`exam_code`,`exam_name`,`subject_code`,`subject_name`,`attendance_status`,"
								+ "`total_marks`,`scored_marks`,`sub_id`,`location_id`,`notebook_marks`,`subject_enrich_marks`,"
								+ "`max_notebook_marks`,`max_subjenrich_marks`,created_by)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					}
					else{
						pstmt = conn.prepareStatement("INSERT INTO `campus_studentwise_mark_details`"
								+ "(`Stu_mark_id`,`Academic_yearid`,`stu_id`,`exam_id`,`admission_no`,"
								+ "`classid`,`sectionid`,`exam_code`,`exam_name`,`subject_code`,`subject_name`,`attendance_status`,"
								+ "`max_periodic_marks`,`periodic_test`,`sub_id`,`location_id`,`notebook_marks`,`subject_enrich_marks`,"
								+ "`max_notebook_marks`,`max_subjenrich_marks`,created_by)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					}

					pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_studentwise_mark_details",userLoggingsVo));
					pstmt.setString(2, vo.getAccyearId());
					pstmt.setString(3, vo.getStudentId());
					pstmt.setString(4, vo.getExamId());
					pstmt.setString(5, vo.getStudentIdNo());
					pstmt.setString(6, vo.getClassId());
					pstmt.setString(7, vo.getSectionId());
					pstmt.setString(8, vo.getExamCode());
					pstmt.setString(9, vo.getExamName());
					pstmt.setString(10,vo.getSubjectId());
					pstmt.setString(11,vo.getSubjectName());
					if(vo.getAttandance().equalsIgnoreCase("present")){
						pstmt.setString(12, "Present");
					}else if(vo.getAttandance().equalsIgnoreCase("absent")){
						pstmt.setString(12, "Absent");
					}
					if(vo.getTestMaximumMarks().trim() == "" || vo.getTestMaximumMarks().equalsIgnoreCase(null)){
						pstmt.setString(13, "0");
					}else{
						pstmt.setString(13, vo.getTestMaximumMarks());
					}

					if(vo.getTestScoredMarks().trim() == "" || vo.getTestScoredMarks().equalsIgnoreCase(null)){
						pstmt.setString(14, "0");
					}else{
						pstmt.setString(14, vo.getTestScoredMarks());
					}
					pstmt.setString(15, vo.getSubjectId());
					pstmt.setString(16, vo.getLocationId());
					if(vo.getNotebookScoredMarks().trim() == "" || vo.getNotebookScoredMarks().equalsIgnoreCase(null)){
						pstmt.setString(17, "0");
					}else{
						pstmt.setString(17, vo.getNotebookScoredMarks());
					}

					if(vo.getSubjectScoredMarks().trim() == "" || vo.getSubjectScoredMarks().equalsIgnoreCase(null)){
						pstmt.setString(18, "0");
					}else{
						pstmt.setString(18, vo.getSubjectScoredMarks());
					}
					if(vo.getNotebookMaximumMarks().trim() == "" || vo.getNotebookMaximumMarks().equalsIgnoreCase(null)){
						pstmt.setString(19, "0");
					}else{
						pstmt.setString(19, vo.getNotebookMaximumMarks());
					}
					if(vo.getSubjectMaximumMarks().trim() == "" || vo.getSubjectMaximumMarks().equalsIgnoreCase(null)){
						pstmt.setString(20, "0");
					}else{
						pstmt.setString(20, vo.getSubjectMaximumMarks());
					}
					
					pstmt.setString(21, user.getUserId());
					int result = pstmt.executeUpdate();

					if(result > 0)
					{
						conn.commit();
						HelperClass.recordLog_Activity(log_audit_session,"Exam","insertExamReportExcelUpload","Insert",pstmt.toString(),conn);
					}
						
				}
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstm1!=null && (!pstm1.isClosed())){
					pstm1.close();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return failurelistOnDiompl;
	}
	public Set<studentExcelUploadVo> GradeSetting(List<StudentExcelUploadPojo> successlist,
			Set<studentExcelUploadVo> failurelist, Connection connection, String userId, String log_audit_session, String examTypeName,UserLoggingsPojo userLoggingsVo) throws SQLException {

		Set<studentExcelUploadVo> failurelistOnDiompl = new LinkedHashSet<studentExcelUploadVo>();
		
		
		Connection conn = null;
		PreparedStatement pstm1=null,pstmt=null,pstm2=null;
		ResultSet rs=null,rs1=null;
		String examId=null;
		int data=0;
		int count=0;
		try{
			//System.out.println("DAOIMPL Is Working Library Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				studentExcelUploadVo uploadClass = new studentExcelUploadVo();
				//System.out.println("======="+successlist.size());
				//System.out.println("======="+i);
				conn.setAutoCommit(false);
				//System.out.println("Exam type name "+successlist.get(i).getExamType());
				
				studentExcelUploadVo vo = new studentExcelUploadVo();
				
				//System.out.println("vo.getExamTypeName())))))))))))))))))))))))))));  "+examTypeName);
				vo.setStudentName(successlist.get(i).getStudentName());
				vo.setStudentId(successlist.get(i).getStudentId());
				vo.setStudentIdNo(successlist.get(i).getStudentIdNo());
				vo.setSchoolName(successlist.get(i).getSchoolName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setAcademicYear(successlist.get(i).getAcademicYear());
				vo.setAccyearId(successlist.get(i).getAccyearId());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setSectionId(successlist.get(i).getSectionId());
				vo.setSectionName(successlist.get(i).getSectionName());
				vo.setExamId(successlist.get(i).getExamId());
				vo.setExamType(successlist.get(i).getExamType());
				vo.setAttandance(successlist.get(i).getAttandance());
				vo.setWorkEducation(successlist.get(i).getWorkEducation());
				vo.setArtEducation(successlist.get(i).getArtEducation());
				vo.setHealthEducation(successlist.get(i).getHealthEducation());
				vo.setDescipline(successlist.get(i).getDescipline());
				vo.setRemarks(successlist.get(i).getRemarks());
				
				//System.out.println("Exam type name "+successlist.get(i).getExamType());
				
	/*			if(vo.getAttandance().equalsIgnoreCase("present")){
					vo.setWorkEducation(successlist.get(i).getWorkEducation());
					vo.setArtEducation(successlist.get(i).getArtEducation());
					vo.setHealthEducation(successlist.get(i).getHealthEducation());
					vo.setDescipline(successlist.get(i).getDescipline());
					vo.setRemarks(successlist.get(i).getRemarks());
					
				}else{
					vo.setWorkEducation("0");
					vo.setArtEducation("0");
					vo.setHealthEducation("0");
					vo.setDescipline("0");
					vo.setRemarks("0");
				}
				*/
				pstm1=conn.prepareStatement("SELECT exam_id FROM `campus_student_co_scholastic_areas` WHERE `exam_id`=(SELECT `examid` FROM `campus_examination` WHERE `examtype`=? AND `classid`=? AND `acadamicyear`=? AND `loc_id`=?) AND `student_id`=?");
				
				pstm1.setString(1,vo.getExamType());
				pstm1.setString(2,vo.getClassId());
				pstm1.setString(3,vo.getAccyearId());
				pstm1.setString(4,vo.getLocationId());
				pstm1.setString(5,vo.getStudentId());
				
				//System.out.println("check status"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
	/*			data = rs.getInt(1);
				}
				//System.out.println("daaaaaaaaaaaaaaaaaa "+data);
				if(data>0){*/
					uploadClass.setAcademicYear(vo.getAcademicYear());
					uploadClass.setStudentName(vo.getStudentName());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setSectionName(vo.getSectionName());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSchoolName(vo.getSchoolName());
					uploadClass.setStudentIdNo(vo.getStudentIdNo());
					uploadClass.setExamName(vo.getExamName());
					uploadClass.setExamCode(vo.getExamCode());
					
					uploadClass.setExamType(examTypeName);
					uploadClass.setStreamName(vo.getStreamName());
					uploadClass.setArtEducation(vo.getArtEducation());
					uploadClass.setHealthEducation(vo.getHealthEducation());
					uploadClass.setWorkEducation(vo.getWorkEducation());
					uploadClass.setDescipline(vo.getDescipline());
					
					
					
					uploadClass.setReason("Duplicate Data's Are Present");
					failurelist.add(uploadClass);
				}
				else {
						examId =getExamId(vo.getExamType(),vo.getClassId(),vo.getAccyearId(),vo.getLocationId(),userLoggingsVo);
						
						pstmt = conn.prepareStatement("INSERT INTO `campus_student_co_scholastic_areas`(`exam_id`,`student_id`,`location_id`,`acc_yearid`,`class_id`,`section_id`,`work_edu_grade`,`art_edu_grade`,`health_edu_grade`,`discipline_grade`,`remarks`,`creadted_by`,`created_date`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, examId);
						pstmt.setString(2, vo.getStudentId());
						pstmt.setString(3, vo.getLocationId());
						pstmt.setString(4, vo.getAccyearId());
						pstmt.setString(5, vo.getClassId());
						pstmt.setString(6, vo.getSectionId());
						pstmt.setString(7, vo.getWorkEducation());
						pstmt.setString(8, vo.getArtEducation());
						pstmt.setString(9, vo.getHealthEducation());
						pstmt.setString(10, vo.getDescipline());
						pstmt.setString(11, vo.getRemarks());
						pstmt.setString(12,userId);
						pstmt.setTimestamp(13,HelperClass.getCurrentTimestamp());
						//System.out.println(pstmt);
						count = pstmt.executeUpdate();
						//System.out.println("count  "  +count);
						if(count>0)
						HelperClass.recordLog_Activity(log_audit_session,"Exam","GradeExcelUpload","Insert",pstmt.toString(),conn);
						conn.commit();
				}
			}
		}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}
		finally{
			try{
				if(rs!=null && (!rs.isClosed())){
					rs.close();
				}
				if(pstm1!=null && (!pstm1.isClosed())){
					pstm1.close();
				}
				if(pstmt!=null && (!pstmt.isClosed())){
					pstmt.close();
				}
				if(conn!=null && (!conn.isClosed())){
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return failurelistOnDiompl;
	}
	private String getExamId(String getExamType, String getClassId, String getAccyearId, String getLocationId,UserLoggingsPojo userLoggingsVo) {
		Connection conn=null;
		PreparedStatement pstm2=null;
		ResultSet rs =null;
		String examId=null;
		try{
		conn=JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstm2 = conn.prepareStatement("SELECT `examid` FROM `campus_examination` WHERE `examtype`=? AND `classid`=? AND `acadamicyear`=? AND `loc_id`=?");
		pstm2.setString(1, getExamType);
		pstm2.setString(2, getClassId);
		pstm2.setString(3, getAccyearId);
		pstm2.setString(4, getLocationId);
		//System.out.println("insert data::::::::: getExampId"+pstm2);
		 rs = pstm2.executeQuery();
		 while(rs.next()){
			 examId=rs.getString("examid");
		 }
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		finally{
			try{
				if(rs!=null ||  !rs.isClosed()){
					rs.close();
				}
				if(pstm2!=null ||  !(pstm2.isClosed())){
					pstm2.close();
				}
				if(conn!=null ||  !(conn.isClosed())){
					conn.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return examId;
	}


	/*public Set<classVo> insertSubject(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstmt=null,pstm1=null;
		ResultSet rs=null;
		
		try{
			//System.out.println("DAOIMPL Is Working Subject Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				//System.out.println("========"+successlist.size());
		
				classVo vo = new classVo();
				
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setSpecializationName(successlist.get(i).getSpecializationName());
				vo.setSpecId(successlist.get(i).getSpecId());
				vo.setSubjectName(successlist.get(i).getSubjectName());
				vo.setSubjectCode(successlist.get(i).getSubjectCode());
				vo.setSubjectType(successlist.get(i).getSubjectType());
				vo.setIsLanguage(successlist.get(i).getIsLanguage());
				vo.setIsLaboratory(successlist.get(i).getIsLaboratory());
				vo.setDescription(successlist.get(i).getDescription());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_subject` WHERE `subjectName`=? AND `classid`=? AND `status`='active' AND `locationId`=? ");
				pstm1.setString(1,vo.getSubjectName());
				pstm1.setString(2,vo.getClassId());
				pstm1.setString(3,vo.getLocationId());
				//System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setClassId(vo.getClassId());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setSubjectCode(vo.getSubjectCode());
					uploadClass.setSubjectType(vo.getSubjectType());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSpecializationName(vo.getSpecializationName());
					uploadClass.setReason("Row Entry already saved in database");
					failurelist.add(uploadClass);
				}
				else{
					
						pstmt = conn.prepareStatement("INSERT INTO `campus_subject`(subjectID,`subjectName`,`classid`,`decription`,`status`,"
								+ "`createdby`,`createdtime`,`Sub_Code`,`locationId`,`specialization`,`isLanguage`,`isLab`,"
								+ "`subType`,`isActive`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_subject",userLoggingsVo));
						pstmt.setString(2, vo.getSubjectName());
						pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getDescription());
						pstmt.setString(5, "active");
						pstmt.setString(6,userId);
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8, vo.getSubjectCode());
						pstmt.setString(9, vo.getLocationId());
						pstmt.setString(10, vo.getSpecializationName());
						pstmt.setString(11, vo.getIsLanguage());
						pstmt.setString(12, vo.getIsLaboratory());
						pstmt.setString(13, vo.getSubjectType());
						pstmt.setString(14, "Y");
						int result=pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Bulk Upload","Subject","Insert",pstmt.toString(),conn);
					 }
			}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Ending");
		return failurelistOnDiompl;
	}*/
	
	

	
	
	public Set<classVo> insertSubject(List<ClassPojo> successlist, Set<classVo> failurelist, Connection connection,
			String userId, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Starting");
		Set<classVo> failurelistOnDiompl = new LinkedHashSet<classVo>();
		
		Connection conn = null;
		PreparedStatement pstmt=null,pstm1=null;
		ResultSet rs=null;
		
		try{
			//System.out.println("DAOIMPL Is Working Subject Excel file Upload");
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		
			for(int i=0;i<successlist.size();i++){
				
				//System.out.println("========"+successlist.size());
		
				classVo vo = new classVo();
				
				vo.setLocationName(successlist.get(i).getLocationName());
				vo.setClassName(successlist.get(i).getClassName());
				vo.setLocationId(successlist.get(i).getLocationId());
				vo.setClassId(successlist.get(i).getClassId());
				vo.setSpecializationName(successlist.get(i).getSpecializationName());
				vo.setSpecId(successlist.get(i).getSpecId());
				vo.setSubjectName(successlist.get(i).getSubjectName());
				vo.setSubjectCode(successlist.get(i).getSubjectCode());
				vo.setSubjectType(successlist.get(i).getSubjectType());
				vo.setIsLanguage(successlist.get(i).getIsLanguage());
				vo.setIsLaboratory(successlist.get(i).getIsLaboratory());
				vo.setDescription(successlist.get(i).getDescription());
				
				pstm1=conn.prepareStatement("SELECT * FROM `campus_subject` WHERE `subjectName`=? AND `classid`=? AND `status`='active' AND `locationId`=? ");
				pstm1.setString(1,vo.getSubjectName());
				pstm1.setString(2,vo.getClassId());
				pstm1.setString(3,vo.getLocationId());
				//System.out.println("check condition count :::::::::::@@@@@@@@"+pstm1);
				rs = pstm1.executeQuery();
				if(rs.next()){
					classVo uploadClass = new classVo();
					uploadClass.setLocationId(vo.getLocationId());
					uploadClass.setStreamId(vo.getStreamId());
					uploadClass.setClassName(vo.getClassName());
					uploadClass.setClassId(vo.getClassId());
					uploadClass.setLocationName(vo.getLocationName());
					uploadClass.setSubjectCode(vo.getSubjectCode());
					uploadClass.setSubjectType(vo.getSubjectType());
					uploadClass.setSubjectName(vo.getSubjectName());
					uploadClass.setSpecializationName(vo.getSpecializationName());
					uploadClass.setReason("Row Entry already saved in database");
					failurelist.add(uploadClass);
				}
				else{
			
			System.out.println("speciD="+vo.getSpecId());		
					if(vo.getSpecId().equalsIgnoreCase("-")){
						List<String> specArray=new ArrayList<String>();
					PreparedStatement pstmt1 =  conn.prepareStatement("SELECT `Specialization_Id` FROM `campus_class_specialization` WHERE `ClassDetails_Id`=? AND `isActive`='Y' AND `locationId`=?");
					pstmt1.setString(1, vo.getClassId());
					pstmt1.setString(2, vo.getLocationId());
					ResultSet rs9 =pstmt1.executeQuery();
					while(rs9.next()){
						
						specArray.add(rs9.getString("Specialization_Id"));
						
						}
					if(specArray.size()>0) {
						for(int k=0;k<specArray.size();k++) {
							pstmt = conn.prepareStatement("INSERT INTO `campus_subject`(subjectID,`subjectName`,`classid`,`decription`,`status`,"
									+ "`createdby`,`createdtime`,`Sub_Code`,`locationId`,`specialization`,`isLanguage`,`isLab`,"
									+ "`subType`,`isActive`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_subject",userLoggingsVo));
							pstmt.setString(2, vo.getSubjectName());
							pstmt.setString(3, vo.getClassId());
							pstmt.setString(4, vo.getDescription());
							pstmt.setString(5, "active");
							pstmt.setString(6,userId);
							pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
							pstmt.setString(8, vo.getSubjectCode());
							pstmt.setString(9, vo.getLocationId());
							pstmt.setString(10, specArray.get(k));
							pstmt.setString(11, vo.getIsLanguage());
							pstmt.setString(12, vo.getIsLaboratory());
							pstmt.setString(13, vo.getSubjectType());
							pstmt.setString(14, "Y");
							int result=pstmt.executeUpdate();
							if(result>0)
								HelperClass.recordLog_Activity(log_audit_session,"Bulk Upload","Subject","Insert",pstmt.toString(),userLoggingsVo);
						
						}
					}
					else {

						pstmt = conn.prepareStatement("INSERT INTO `campus_subject`(subjectID,`subjectName`,`classid`,`decription`,`status`,"
								+ "`createdby`,`createdtime`,`Sub_Code`,`locationId`,`specialization`,`isLanguage`,`isLab`,"
								+ "`subType`,`isActive`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_subject",userLoggingsVo));
						pstmt.setString(2, vo.getSubjectName());
						pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getDescription());
						pstmt.setString(5, "active");
						pstmt.setString(6,userId);
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8, vo.getSubjectCode());
						pstmt.setString(9, vo.getLocationId());
						pstmt.setString(10, vo.getSpecId());
						pstmt.setString(11, vo.getIsLanguage());
						pstmt.setString(12, vo.getIsLaboratory());
						pstmt.setString(13, vo.getSubjectType());
						pstmt.setString(14, "Y");
						int result=pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Bulk Upload","Subject","Insert",pstmt.toString(),userLoggingsVo);
					
						
					}
					}
					else{
						
							System.out.println("singleSpec="+vo.getSpecId());
						
						pstmt = conn.prepareStatement("INSERT INTO `campus_subject`(subjectID,`subjectName`,`classid`,`decription`,`status`,"
								+ "`createdby`,`createdtime`,`Sub_Code`,`locationId`,`specialization`,`isLanguage`,`isLab`,"
								+ "`subType`,`isActive`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_subject",userLoggingsVo));
						pstmt.setString(2, vo.getSubjectName());
						pstmt.setString(3, vo.getClassId());
						pstmt.setString(4, vo.getDescription());
						pstmt.setString(5, "active");
						pstmt.setString(6,userId);
						pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
						pstmt.setString(8, vo.getSubjectCode());
						pstmt.setString(9, vo.getLocationId());
						pstmt.setString(10, vo.getSpecId());
						pstmt.setString(11, vo.getIsLanguage());
						pstmt.setString(12, vo.getIsLaboratory());
						pstmt.setString(13, vo.getSubjectType());
						pstmt.setString(14, "Y");
						int result=pstmt.executeUpdate();
						if(result>0)
							HelperClass.recordLog_Activity(log_audit_session,"Bulk Upload","Subject","Insert",pstmt.toString(),userLoggingsVo);
					}
				
				}
			}
			}
				catch (SQLException sqle) {
			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		}finally{
			try{
				if(rs!=null && !(rs.isClosed())){
					rs.close();
				}
				
				if(pstmt!=null && !(pstmt.isClosed())){
					pstmt.close();
				}
				if(pstm1!=null && !(pstm1.isClosed())){
					pstm1.close();
				}
				if(conn!=null && !(conn.isClosed())){
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
				+ " Control in SettingsFileUploadDaoImpl: insertSpec : Ending");
		return failurelistOnDiompl;
	}
	
	}