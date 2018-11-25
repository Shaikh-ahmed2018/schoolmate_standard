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

import com.centris.campus.dao.ReligionCasteCasteCategoryDao;

import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.ReligionCasteCasteCategoryUtils;

import com.centris.campus.vo.ReligionCasteCasteCategoryVo;



public class ReligionCasteCasteCategoryDaoImpl implements ReligionCasteCasteCategoryDao {
	private static final Logger logger = Logger
			.getLogger(ReligionCasteCasteCategoryDaoImpl.class);

	
	public int insertReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertReligion  Starting");

		
		String religion = detailsPojo.getReligion();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_RELIGION);
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_religion",userLoggingsVo));
			pstmt.setString(2, religion.trim());
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());

			rs = pstmt.executeUpdate();
			if(rs > 0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Religion Details","Insert",pstmt.toString(),userLoggingsVo);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertReligion  Ending");

		
		return result;

	}

	
	public int updateReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Starting");
		
		String religionId = detailsPojo.getReligionId();
		String religion = detailsPojo.getReligion();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_RELIGION);

			pstmt.setString(1, religion.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, religionId.trim());

			result = pstmt.executeUpdate();
			if(result > 0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Religion Details","Update",pstmt.toString(),userLoggingsVo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
			e.printStackTrace();
		}finally {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateReligion  Ending");
		return result;
	}
	
	
	public int validateReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateReligion Starting");

		int religionName_validate = 0;
        String value=null;
		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_RELIGION_NAME);
			psCheckReligion.setString(1, detailsPojo.getReligion().trim());
			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {
				count = rsCheckReligion.getInt(1);
				value = rsCheckReligion.getString(2);
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				religionName_validate = 10;
			}
			else if (count > 0) {
				religionName_validate = 1;
			} else {
				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (rsCheckReligion != null && (!rsCheckReligion.isClosed())) {
					rsCheckReligion.close();
				}
				if (psCheckReligion!= null && (!psCheckReligion.isClosed())) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateReligion  Ending");
		return religionName_validate;
	}

	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName, String status, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchReligion Starting");

		ArrayList<ReligionCasteCasteCategoryVo> religionList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_RELIGION_DETAILS);
			pstmt.setString(1, searchName + "%");
			pstmt.setString(2, status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sno++;
				
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setSno(String.valueOf(sno));
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
				    religionVo.setStatus("Active");
				}else{
					religionVo.setStatus("Active"); 
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
				    religionVo.setRemarks("");
				}else{
					religionVo.setRemarks(rs.getString("remarks"));
				}
				religionList.add(religionVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchReligion  Ending");

		return religionList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getReligionDetails(UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails Starting");
		List<ReligionCasteCasteCategoryVo> religionList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.RELGION_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					religionVo.setStatus("Active");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null)
				{
				    religionVo.setRemarks("");
				}
				else{
					religionVo.setRemarks(rs.getString("remarks"));
				}
				
				religionList.add(religionVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getReligionDetails  Ending");
		return religionList;
	
	}


	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_RELIGION);
			pstmt.setString(1, detailsPojo.getReligionId());
			rs = pstmt.executeQuery();
			
			System.out.println("DIOMPL: GET_SINGLE_RELIGION: "+pstmt);
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				System.out.println(""+rs.getString("religionId"));
				System.out.println(""+rs.getString("religion"));
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleReligion  Ending");

		return religionVo;
	
	}


	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteReligion Starting");

		String result = null,value=null;
		int count= 0,count1= 0;
		PreparedStatement pstmt = null,pstmt1 = null;
		Connection conn = null;
		int resultcnt = 0;
		int unmappcnt = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.ACTIVE_INACTIVE_RELIGION_BY_STATUS);
			pstmt1 = conn.prepareStatement(ReligionCasteCasteCategoryUtils.ACTIVE_INACTIVE_CASTE_BY_RELIGION);
			
			if(detailsPojo.getStatus().equalsIgnoreCase("InActive")){
				 value="InActive";
				for(int i=0;i<detailsPojo.getReligionIdArray().length;i++){
					
					PreparedStatement getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` WHERE `student_religion_var` = ?");
					getst.setString(1, detailsPojo.getReligionIdArray()[i]);
					ResultSet rsst = getst.executeQuery();
					while(rsst.next()){
						count = rsst.getInt(1);
					}
					if(count == 0){
						 unmappcnt++;
						 pstmt.setString(1, "N");
						 if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
							 pstmt.setString(2, detailsPojo.getInactiveReason());
						 }
						 else{
							 pstmt.setString(2, detailsPojo.getOtherReason());
						 }
						 pstmt.setString(3, detailsPojo.getReligionIdArray()[i]);
						 count1 = pstmt.executeUpdate();
						 if(count1 > 0){
							 resultcnt ++;
							 HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Religion Details",value,pstmt.toString(),conn);
							 pstmt1.setString(1, "Y");
							 pstmt1.setString(2, detailsPojo.getReligionIdArray()[i]);
							 pstmt1.executeUpdate();
						 }
					}
				}
				
				if(unmappcnt!=0 && unmappcnt == resultcnt){
					result = "inactivetrue";
					conn.commit();
				}else if(unmappcnt!=0 && unmappcnt!=resultcnt){
					result = "inactivefail";
					conn.rollback();
				}else{
					result = "inactivefalse";
				}
				
			}
			else{
				value="Active";
			for(int i=0;i<detailsPojo.getReligionIdArray().length;i++){
						
				 pstmt.setString(1, "Y");
				 if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
					 pstmt.setString(2, detailsPojo.getInactiveReason());
				 }
				 else{
					 pstmt.setString(2, detailsPojo.getOtherReason());
				 }
				 pstmt.setString(3, detailsPojo.getReligionIdArray()[i]);
				 count1 = pstmt.executeUpdate();
				
				 if(count1 > 0){
					 resultcnt ++;
					 pstmt1.setString(1, "Y");
					 pstmt1.setString(2, detailsPojo.getReligionIdArray()[i]);
					 pstmt1.executeUpdate();
					 HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Religion Details",value,pstmt1.toString(),conn);
				 }
			}
			
			if(resultcnt == detailsPojo.getReligionIdArray().length){
				result = "activetrue";
				conn.commit();
			}else{
				result = "activefail";
				conn.rollback();
			}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			try {

				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteReligion  Ending");

		return result;
	
	}


	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName,String sts, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste Starting");

		ArrayList<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_CASTE_DETAILS);

			pstmt.setString(1, searchName + "%");
			pstmt.setString(2, searchName + "%");
			pstmt.setString(3, sts);
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));		
				religionVo.setReligion(rs.getString("religion"));
                 if(rs.getString("isActive").equalsIgnoreCase("Y")){
                	 religionVo.setStatus("Active");
                 }else{
                	 religionVo.setStatus("InActive");
                 }
                 if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
                	 religionVo.setRemarks("");
                 }else{
                	 religionVo.setRemarks(rs.getString("remarks"));
                 }
				
				casteList.add(religionVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCaste  Ending");

		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTCASTE_DETAILS);
			//pstmt.setString(1, religionId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteId(rs.getString("casteId"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					casteVo.setStatus("Active");
				}else{
					casteVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					casteVo.setRemarks("");
				}else{
					casteVo.setRemarks(rs.getString("remarks"));
				}
				
				casteList.add(casteVo);

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetails  Ending");
		return casteList;
	
	}


	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCaste Starting");

		String result = null,value=null;
		int count = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		int resultcnt = 0;
		int unmapped = 0;
		//String mappedmodu = "nomap";
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.ACTIVE_INACTIVE_CASTE_BY_STATUS);
			
			if(detailsPojo.getStatus().equalsIgnoreCase("InActive")){
				for(int i=0;i<detailsPojo.getCasteIdArray().length;i++){
					value="InActive";
					int st = 0;
					PreparedStatement getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_caste_category` WHERE `casteId` = ?");
					getst.setString(1, detailsPojo.getCasteIdArray()[i]);
					ResultSet rsgt = getst.executeQuery();
					while(rsgt.next()){
						st = rsgt.getInt(1);
						//mappedmodu = "Caste Category";
					}
					
					if(st == 0){
						getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` WHERE `student_caste` = ?");
						getst.setString(1, detailsPojo.getCasteIdArray()[i]);
						rsgt = getst.executeQuery();
						while(rsgt.next()){
							st = rsgt.getInt(1);
							//mappedmodu = "Caste Category";
						}
						if(st == 0){
						unmapped++;
						pstmt.setString(1, "N");
						if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
							pstmt.setString(2, detailsPojo.getInactiveReason()); 
						}
						else{
							 pstmt.setString(2, detailsPojo.getOtherReason());
						}
						pstmt.setString(3, detailsPojo.getCasteIdArray()[i]);
						count = pstmt.executeUpdate();
						if(count > 0){
							resultcnt++;
							HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Setting","Caste Detail",value,pstmt.toString(),conn);
						}
					}
					}
				}
				
				if(unmapped!=0 && unmapped == resultcnt){
					result = "inactivetrue";
					conn.commit();
				}else if(unmapped!=0 && unmapped!=resultcnt){
					result = "inactivefail";
					conn.rollback();
				}else{
					result = "inactivefalse";
				}
			}
			else{
				for(int i=0;i<detailsPojo.getCasteIdArray().length;i++){
					 value="Active";
					 pstmt.setString(1, "Y");
					 if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
						 pstmt.setString(2, detailsPojo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, detailsPojo.getOtherReason());
					 }
					 pstmt.setString(3, detailsPojo.getCasteIdArray()[i]);
					 count = pstmt.executeUpdate();
					 if (count > 0) {
						 resultcnt ++;
						 HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Setting","Caste Detail",value,pstmt.toString(),conn);
					 }
			}
			if (resultcnt ==  detailsPojo.getCasteIdArray().length){
				conn.commit();
				result="activetrue";
			}else{
				conn.rollback();
				result="activefalse";
			}
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteCaste  Ending");

		return result;
	}

	public int validateCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCaste Starting");
		int religionName_validate = 0;
		int count = 0;
		String value=null;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_CASTE_NAME);
			psCheckReligion.setString(1, detailsPojo.getCaste().trim());
			psCheckReligion.setString(2, detailsPojo.getMain_religion().trim());
			System.out.println("duplicate checking is "+psCheckReligion);
			rsCheckReligion = psCheckReligion.executeQuery();
			
			while (rsCheckReligion.next()) {
				count = rsCheckReligion.getInt(1);
				value = rsCheckReligion.getString(2);
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				religionName_validate = 10;
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
				religionName_validate = 1;
			}
			else {

				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {
			try {
				if (rsCheckReligion != null && (!rsCheckReligion.isClosed())) {
					rsCheckReligion.close();
				}
				if (psCheckReligion != null && (!psCheckReligion.isClosed())) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCaste  Ending");
		return religionName_validate;
	}

	public int insertCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCaste  Starting");
		
		String caste = detailsPojo.getCaste();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_CASTE);
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_caste",userLoggingsVo));
			pstmt.setString(2, caste.trim());
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			pstmt.setString(5,detailsPojo.getMain_religion());
			 
			rs = pstmt.executeUpdate();
			if(rs >0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Details","Insert",pstmt.toString(),userLoggingsVo);
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCaste  Ending");
		
		return result;

	}
	public int updateCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCaste  Starting");
		
		String casteId = detailsPojo.getCasteId();
		String caste = detailsPojo.getCaste();
		System.out.println("DIompl casteId: "+casteId);
		System.out.println("DIompl caste: "+caste);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_CASTE);

			pstmt.setString(1, caste.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, detailsPojo.getMain_religion());
			pstmt.setString(5, casteId.trim());
			System.out.println(pstmt);
			result = pstmt.executeUpdate();
			if(result>0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Details","Update",pstmt.toString(),userLoggingsVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCaste  Ending");
		return result;
	}


	public ReligionCasteCasteCategoryVo getSingleCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCaste Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_CASTE);
			pstmt.setString(1, detailsPojo.getCasteId());

			rs = pstmt.executeQuery();
			System.out.println("DIOMPL: getSingleCaste: "+pstmt);
			while (rs.next()) {
				religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religionId"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCaste  Ending");
		return religionVo;
	}


	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(String searchName, String status, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCasteCategory Starting");

		ArrayList<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_CASTE_CATEGORY_DETAILS);
			pstmt.setString(1,  searchName + "%");
			pstmt.setString(2,  searchName + "%");
			pstmt.setString(3,  searchName + "%");
			pstmt.setString(4,  status);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					religionVo.setStatus("Active");
				}else{
				    religionVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					religionVo.setRemarks("");
				}else{
					religionVo.setRemarks(rs.getString("remarks"));
				}
				casteList.add(religionVo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : searchCasteCategory  Ending");
		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteCategoryDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.CASTE_CATEGORY_DETAILS);
			System.out.println("pstmt"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//a.castCatId,a.casteCategory,b.caste,c.religion
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteCategoryId(rs.getString("castCatId"));
				casteVo.setCasteCategory(rs.getString("casteCategory"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
				        casteVo.setStatus("Active");
				}else{
					casteVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					casteVo.setRemarks("");
				}else{
					casteVo.setRemarks(rs.getString("remarks"));
				}
				
				casteList.add(casteVo);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteCategoryDetails  Ending");
		return casteList;
	}

	public int validateCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCasteCategory Starting");

		int religionName_validate = 0;
        String value=null;
		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_CASTE_CATEGORY_NAME);

			psCheckReligion.setString(1, detailsPojo.getCasteCategory().trim());
			psCheckReligion.setString(2, detailsPojo.getReligionId().trim());
			psCheckReligion.setString(3, detailsPojo.getCasteId().trim());
			System.out.println("VALIDATE_CASTE_CATEGORY_NAME -->>"+psCheckReligion);
			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {
				count = rsCheckReligion.getInt(1);
				value = rsCheckReligion.getString(2);
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				religionName_validate = 10;
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
				religionName_validate = 1;
			}
			else {
				religionName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {
			try {
				if (!rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (!psCheckReligion.isClosed()) {
					psCheckReligion.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateCasteCategory  Ending");

		return religionName_validate;
	
	
		
	}


	public int insertCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCasteCategory  Starting");
		//String casteCategory = detailsPojo.getCasteCategory();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_CASTE_CATEGORY);
			 
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_caste_category",userLoggingsVo));
			pstmt.setString(2, detailsPojo.getCasteCategory());
			pstmt.setString(3, detailsPojo.getReligionId());
			pstmt.setString(4, detailsPojo.getCasteId());
			pstmt.setString(5, detailsPojo.getCreateUser());
			pstmt.setTimestamp(6, HelperClass.getCurrentTimestamp());
			 
			result = pstmt.executeUpdate();
			if(result>0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Category Details","Insert",pstmt.toString(),userLoggingsVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertCasteCategory  Ending");
		
		return result;

	
	}


	public int updateCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCasteCategory  Starting");
		String casteCategoryId = detailsPojo.getCasteCategoryId();
		String casteCategory = detailsPojo.getCasteCategory();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_CASTE_CATEGORY);
			pstmt.setString(1, casteCategory.trim());
			pstmt.setString(2, detailsPojo.getReligionId());
			pstmt.setString(3, detailsPojo.getCasteId());
			pstmt.setString(4, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(5,  HelperClass.getCurrentTimestamp());
			pstmt.setString(6, casteCategoryId.trim());

			result = pstmt.executeUpdate();
			if(result>0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Category Details","Update",pstmt.toString(),userLoggingsVo);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateCasteCategory  Ending");
		return result;
	}

	public String delteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : delteCasteCategory Starting");
		String result = null,value=null;
		int count = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		int unmaped = 0;
		int resultst = 0;
		try {
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.ACTIVE_INACTIVE_CASTE_CATEGORY_BY_STATUS);
			if(detailsPojo.getStatus().equalsIgnoreCase("InActive")){
				for(int i=0;i<detailsPojo.getCasteCategoryIdArray().length;i++){
					 
					int st = 0;
					PreparedStatement getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_student` WHERE `casteCategory` = ?");
					getst.setString(1, detailsPojo.getCasteCategoryIdArray()[i]);
					ResultSet rsst = getst.executeQuery();
					while(rsst.next()){
						st = rsst.getInt(1);
					}
					rsst.close();
					if(st == 0){
						unmaped++;
						pstmt.setString(1, "N");
						 if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
							 pstmt.setString(2,detailsPojo.getInactiveReason()); 
						 }
						 else{
							 pstmt.setString(2,detailsPojo.getOtherReason());
						 }
						 pstmt.setString(3,detailsPojo.getCasteCategoryIdArray()[i]);
						 count = pstmt.executeUpdate();
						 value="InActive";
						 if (count > 0) {
							 resultst++;
							 HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Category Details",value,pstmt.toString(),conn);
						}
					}
				}
				if(unmaped!=0 && resultst == unmaped){
					result = "inactivetrue";
					conn.commit();
				}else if(resultst!=0 && unmaped!=resultst){
					result = "inactivefail";
					conn.rollback();
				}else{
					result = "inactivefalse";
				}
				
			}else{
				for(int i=0;i<detailsPojo.getCasteCategoryIdArray().length;i++){
				 unmaped++;
				 pstmt.setString(1, "Y");
				 if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
					 pstmt.setString(2, detailsPojo.getActiveReason()); 
				 }
				 else{
					 pstmt.setString(2, detailsPojo.getOtherReason());
				 }
				 pstmt.setString(3, detailsPojo.getCasteCategoryIdArray()[i]);
				 count = pstmt.executeUpdate();
				 value="Active";
				 if (count > 0) {
					resultst++;
					HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Caste Category Details",value,pstmt.toString(),conn);
				 }
			}
				if(resultst!=0 && resultst == unmaped){
					result = "activetrue";
					conn.commit();
				}else if(resultst!=0 && unmaped==resultst){
					result = "activefail";
					conn.rollback();
				}	
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : delteCasteCategory  Ending");

		return result;
	}


	public ReligionCasteCasteCategoryVo getSingleCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCasteCategory Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_CASTE_CATEGORY);
			pstmt.setString(1, detailsPojo.getCasteCategoryId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setCasteId(rs.getString("casteId"));
				religionVo.setCaste(rs.getString("caste"));
				religionVo.setReligion(rs.getString("religion"));
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleCasteCategory  Ending");

		return religionVo;
	
	
	}


	public int insertOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : insertOccupation  Starting");
		String occupation = detailsPojo.getOccupation();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int rs=0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.INSERT_OCCUPATION);

			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_occupation",userLoggingsVo));
			pstmt.setString(2, occupation.trim());
			pstmt.setString(3, detailsPojo.getCreateUser());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			 
			rs = pstmt.executeUpdate();
			if(rs > 0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Occupation Details","Insert",pstmt.toString(),userLoggingsVo);
			}
			System.out.println("insert Occupation: "+pstmt);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
					+ " Control in ReligionCasteCasteCategoryDaoImpl : insertOccupation  Ending");
		return result;
	}


	public int validateOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateOccupation Starting");

		int occupationName_validate = 0;
        String value=null;
		int count = 0;
		PreparedStatement psCheckReligion = null;
		ResultSet rsCheckReligion = null;
		Connection conn = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.VALIDATE_OCCUPATION_NAME);
			psCheckReligion.setString(1, detailsPojo.getOccupation().trim());
			
            System.out.println("VALIDATE_OCCUPATION_NAME -->>"+psCheckReligion);
            
			rsCheckReligion = psCheckReligion.executeQuery();
			while (rsCheckReligion.next()) {
				count = rsCheckReligion.getInt(1);
				value = rsCheckReligion.getString(2);
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				occupationName_validate = 10;
			}
			else if (count > 0 && value.equalsIgnoreCase("Y")) {
				occupationName_validate = 1;
			} 
			else {
				occupationName_validate = 0;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (!rsCheckReligion.isClosed()) {
					rsCheckReligion.close();
				}
				if (!psCheckReligion.isClosed()) {
					psCheckReligion.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : validateOccupation  Ending");
		return occupationName_validate;
	
	}
	
	public int updateOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateOccupation  Starting");
		String occupationId = detailsPojo.getOccupationId();
		String occupation = detailsPojo.getOccupation();

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_OCCUPATION);

			pstmt.setString(1, occupation.trim());
			pstmt.setString(2, detailsPojo.getCreateUser().trim());
			pstmt.setTimestamp(3,  HelperClass.getCurrentTimestamp());
			pstmt.setString(4, occupationId.trim());
			result = pstmt.executeUpdate();
			if(result >0){
				HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Occupation Details","Update",pstmt.toString(),userLoggingsVo);
			}
			
			System.out.println("update Occupation: "+pstmt);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : updateOccupation  Ending");
		return result;
	}
	
	public List<ReligionCasteCasteCategoryVo> getOccupationDetails(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails Starting");
		List<ReligionCasteCasteCategoryVo> occupationList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.OCCUPATION_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo occupationVo = new ReligionCasteCasteCategoryVo();
				occupationVo.setOccupationId(rs.getString("occupationId"));
				occupationVo.setOccupation(rs.getString("occupation"));
				if(rs.getString("isActive").equalsIgnoreCase("Y"))
				{
				   occupationVo.setStatus("Active");
				}
				else{
				   occupationVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null)
				{
				   occupationVo.setRemarks("");
				}
				else{
					 occupationVo.setRemarks(rs.getString("remarks"));
				}
				occupationList.add(occupationVo);

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetails  Ending");
		return occupationList;
	}


	public ReligionCasteCasteCategoryVo getSingleOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleOccupation Starting");

		ReligionCasteCasteCategoryVo religionVo = null;
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.GET_SINGLE_OCCUPATION);

			pstmt.setString(1, detailsPojo.getOccupationId());
			rs = pstmt.executeQuery();
			System.out.println("DIOMPL: getOccupationId: "+pstmt);
			while (rs.next()) {

				religionVo = new ReligionCasteCasteCategoryVo();

				religionVo.setOccupationId(rs.getString("occupationId"));
				religionVo.setOccupation(rs.getString("occupation"));
				System.out.println(""+rs.getString("occupationId"));
				System.out.println(""+rs.getString("occupation"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
				if (!conn.isClosed()) {
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getSingleOccupation  Ending");

		return religionVo;
	
	
	}


	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteOccupation Starting");
		String result = null;
		int count = 0;
		PreparedStatement psCheckReligion = null;
		Connection conn = null;
        String  value=null;
        int resultst = 0;
        int unmappedcnt = 0;
        int status = 0;
		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			psCheckReligion = conn.prepareStatement(ReligionCasteCasteCategoryUtils.UPDATE_OCCUPATION_BY_STATUS);
		    
			if(detailsPojo.getStatus().equalsIgnoreCase("InActive")){
				for(int i=0;i<detailsPojo.getOccupationIdArray().length;i++){
					
					PreparedStatement getst = conn.prepareStatement("SELECT COUNT(*) FROM `campus_parents` WHERE `guardianOccupation` = ? OR `student_mother_occupation` = ? OR `student_father_occupation` = ?");
					getst.setString(1, detailsPojo.getOccupationIdArray()[i]);
					getst.setString(2, detailsPojo.getOccupationIdArray()[i]);
					getst.setString(3, detailsPojo.getOccupationIdArray()[i]);
					ResultSet rsst = getst.executeQuery();
					while(rsst.next()){
						status = rsst.getInt(1);
					}
					if(status==0){
						unmappedcnt ++;
						psCheckReligion.setString(1,"N");
						if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
							psCheckReligion.setString(2, detailsPojo.getInactiveReason()); 
						}
						else{
							psCheckReligion.setString(2, detailsPojo.getOtherReason());
						}
						psCheckReligion.setString(3, detailsPojo.getOccupationIdArray()[i].trim());
						value="InActive";
						count = psCheckReligion.executeUpdate();
						if (count > 0) {
							resultst++;
							HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Occupation Details",value,psCheckReligion.toString(),conn);
						}
					}
				}
				if(unmappedcnt!=0 && unmappedcnt!=resultst){
					result = "fail";
				}else if(resultst!=0 && resultst == unmappedcnt){
					result = "inactivetrue";
				}else{
					result = "inactivefalse";
				}
			}
			else{
				for(int i=0;i<detailsPojo.getOccupationIdArray().length;i++){
					unmappedcnt ++;
					psCheckReligion.setString(1, "Y");
					if(detailsPojo.getOtherReason()=="" || detailsPojo.getOtherReason()==null){
						psCheckReligion.setString(2, detailsPojo.getActiveReason()); 
					}
					else{
						psCheckReligion.setString(2, detailsPojo.getOtherReason());
					}
					value="Active";
					psCheckReligion.setString(3, detailsPojo.getOccupationIdArray()[i].trim());
					count = psCheckReligion.executeUpdate();
					if (count > 0) {
						resultst++;
						HelperClass.recordLog_Activity(detailsPojo.getLog_audit_session(),"Settings","Occupation Details",value,psCheckReligion.toString(),conn);
					}
				}
				if(unmappedcnt!=0 && unmappedcnt!=resultst){
					result = "fail";
				}else if(resultst!=0 && resultst == unmappedcnt){
					result = "activetrue";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	
		finally {
			try {

				if (psCheckReligion != null && (!psCheckReligion.isClosed())) {
					psCheckReligion.close();
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : deleteOccupation  Ending");

		return result;
	}


	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetailsList Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.CASTE_DETAILS);
			pstmt.setString(1, religionId.trim());
			System.out.println("caste details is "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteId(rs.getString("casteId"));
				casteVo.setCaste(rs.getString("caste"));
				casteList.add(casteVo);

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteDetailsList  Ending");
		return casteList;
	
	}
	
	public List<ReligionCasteCasteCategoryVo> getListOfCaste() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getListOfCaste Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTOF_CASTE_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setCasteId(rs.getString("casteId")); 
				religionVo.setCaste(rs.getString("caste"));
				casteList.add(religionVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getListOfCaste  Ending");
		return casteList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteCategoryListDetails Starting");
		List<ReligionCasteCasteCategoryVo> casteCategoryList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SINGLE_CASTECATOGRY_DETAILS);
			pstmt.setString(1, casteId.trim());
			System.out.println("caste category query "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setCasteCategoryId(rs.getString("castCatId"));
				religionVo.setCasteCategory(rs.getString("casteCategory"));
				
				casteCategoryList.add(religionVo);

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getCasteCategoryListDetails  Ending");
		return casteCategoryList;
	
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
			String searchName, String status, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetailsSearch Starting");
		List<ReligionCasteCasteCategoryVo> occupationList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.SEARCH_OCCUPATION_DETAILS);
			pstmt.setString(1,searchName+"%"); 
			pstmt.setString(2,status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo occupationVo = new ReligionCasteCasteCategoryVo();
				occupationVo.setOccupationId(rs.getString("occupationId")); 
				occupationVo.setOccupation(rs.getString("occupation"));
				if(rs.getString("isActive").equalsIgnoreCase("Y"))
				{
				   occupationVo.setStatus("Active");
				}
				else{
				   occupationVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null)
				{
				   occupationVo.setRemarks("");
				}
				else{
					 occupationVo.setRemarks(rs.getString("remarks"));
				}
				occupationList.add(occupationVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationDetailsSearch  Ending");
		return occupationList;
	}


	public List<ReligionCasteCasteCategoryVo> getOccupationListByStatus(String status, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationListByStatus Starting");
		List<ReligionCasteCasteCategoryVo> occupationList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.OCCUPATION_DETAILS_BY_STATUS);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo occupationVo = new ReligionCasteCasteCategoryVo();
				occupationVo.setOccupationId(rs.getString("occupationId"));
				occupationVo.setOccupation(rs.getString("occupation"));
				if(rs.getString("isActive").equalsIgnoreCase("Y"))
				{
				   occupationVo.setStatus("Active");
				}
				else{
				   occupationVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null)
				{
				   occupationVo.setRemarks("");
				}
				else{
					 occupationVo.setRemarks(rs.getString("remarks"));
				}
				occupationList.add(occupationVo);

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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : getOccupationListByStatus  Ending");
		return occupationList;
	}


	public List<ReligionCasteCasteCategoryVo> ReligionListByStatus(String status, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : ReligionListByStatus Starting");
		List<ReligionCasteCasteCategoryVo> religionList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.RELGION_DETAILS_BY_STATUS);
			pstmt.setString(1,status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo religionVo = new ReligionCasteCasteCategoryVo();
				religionVo.setReligionId(rs.getString("religionId"));
				religionVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					religionVo.setStatus("Active");
				}
				else{
					religionVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null)
				{
				    religionVo.setRemarks("");
				}
				else{
					religionVo.setRemarks(rs.getString("remarks"));
				}
				religionList.add(religionVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : ReligionListByStatus  Ending");
		return religionList;
	}


	public List<ReligionCasteCasteCategoryVo> CasteListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : CasteListingByStatus Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTING_CASTE_DETAILS_BY_STATUS);
			pstmt.setString(1, vo.getStatus());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteId(rs.getString("casteId"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
					casteVo.setStatus("Active");
				}else{
					casteVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					casteVo.setRemarks("");
				}else{
					casteVo.setRemarks(rs.getString("remarks"));
				}
				casteList.add(casteVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : CasteListingByStatus  Ending");
		return casteList;
	}


	public List<ReligionCasteCasteCategoryVo> CasteCategoryListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ReligionCasteCasteCategoryDaoImpl : CasteCategoryListingByStatus Starting");
		List<ReligionCasteCasteCategoryVo> casteList = new ArrayList<ReligionCasteCasteCategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(ReligionCasteCasteCategoryUtils.LISTING_CASTECATEGORY_DETAILS_BY_STATUS);
			pstmt.setString(1, vo.getStatus());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReligionCasteCasteCategoryVo casteVo = new ReligionCasteCasteCategoryVo();
				casteVo.setCasteCategoryId(rs.getString("castCatId"));
				casteVo.setCasteCategory(rs.getString("casteCategory"));
				casteVo.setCaste(rs.getString("caste"));
				casteVo.setReligion(rs.getString("religion"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")){
				        casteVo.setStatus("Active");
				}else{
					casteVo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					casteVo.setRemarks("");
				}else{
					casteVo.setRemarks(rs.getString("remarks"));
				}
				
				casteList.add(casteVo);
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
				+ " Control in ReligionCasteCasteCategoryDaoImpl : CasteCategoryListingByStatus  Ending");
		return casteList;
	}
}