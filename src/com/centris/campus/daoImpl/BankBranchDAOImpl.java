package com.centris.campus.daoImpl;

import org.apache.log4j.Logger;

import com.centris.campus.dao.BankBranchDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.BankBranchSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.BankBranchVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.apache.log4j.Level;



public class BankBranchDAOImpl  implements BankBranchDAO {
	private static Logger logger = Logger.getLogger(BankBranchDAOImpl.class);

	public String checkBranchName(String name, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchDAOImpl:saveBank Starting");

		Connection con = null;
		PreparedStatement pst = null;
		String result = null;
		int count = 0;
		ResultSet rs = null;
		String value=null;
		try {
			con =JDBCConnection.getSeparateConnection(custdetails);
			pst = con.prepareStatement(BankBranchSqlUtils.GET_BANK_BRANCH_NAME);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				
				count = rs.getInt(1);
				value=rs.getString("isActive");
			}
			if (count > 0 && value.equalsIgnoreCase("N")) {
				result="inactive";
				
				

			} else if(count > 0 && value.equalsIgnoreCase("Y")) {

				result="true";
			}
             
			else{
				result="false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null && rs != null && con != null) {
					pst.close();
					rs.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");

		return result;

	}

	public String saveBranchDetails(BankBranchVO vo, String usercode, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ "Control in BankBranchDAOImpl:saveBank Starting");

		Connection con = null;
		PreparedStatement pst = null;
		String result = null;
		ResultSet rs = null;
		int i=0;

		try {

			 con =JDBCConnection.getSeparateConnection(custdetails);
			con.setAutoCommit(false);

			String id =  IDGenerator.getPrimaryKeyID("campus_bank_branch",custdetails);
			Timestamp curr_date = HelperClass.getCurrentTimestamp();


			if(!vo.getHbankBranchId().equalsIgnoreCase(""))
			{
				pst = con.prepareStatement(BankBranchSqlUtils.UPDATE_BANK_BRANCH);
				pst.setString(1, vo.getBranchName());
				pst.setString(2, vo.getBranchShortName());
				pst.setString(3, vo.getIfscCode());
				pst.setString(4, vo.getAddress());
				pst.setString(5, usercode);
				pst.setString(6, vo.getBankname());
				pst.setString(7, vo.getHbankBranchId());
				//("2222222222222"+pst);
				i = pst.executeUpdate();

				if (i > 0)
				{   
					con.commit();
					HelperClass.recordLog_Activity(vo.getLog_audit_session(), "Master", "Bank Branch Master", "Update", pst.toString(),custdetails);
				result = "update";
				} else 
				{
					result = "fail";
				}
			}
			else{
				pst = con.prepareStatement(BankBranchSqlUtils.SAVE_BANK_BRANCH);
				pst.setString(1, id);
				pst.setString(2, vo.getBranchName());
				pst.setString(3, vo.getBranchShortName());
				pst.setString(4, vo.getIfscCode());
				pst.setString(5, vo.getAddress());
				pst.setString(6, vo.getBankname());
				pst.setString(7, usercode);
				
				i = pst.executeUpdate();


				if (i == 0) {
					result = "fail";
				} else {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(), "Master", "Bank Branch Master", "Insert", pst.toString(),custdetails);
					con.commit();
					result = "success";
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (pst != null && rs != null && con != null) {
					pst.close();
					rs.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");
		return result;
	}

	public List<BankBranchVO> getBranchList(UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchDAOImpl:saveBank Starting");

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BankBranchVO> list = new ArrayList<BankBranchVO>();
		try {
		    con =JDBCConnection.getSeparateConnection(custdetails);
			pst = con.prepareStatement(BankBranchSqlUtils.GET_BRANCH_LIST);
			rs = pst.executeQuery();
			while (rs.next()) {
				//brh.BranchId,brh.BranchName,brh.BranchShortName,brh.IFSCCode,brh.BranchAddress,brh.isActive,bnk.`BankName`
				BankBranchVO vo = new BankBranchVO();
				vo.setId(rs.getString("BranchId"));
				vo.setBankname(rs.getString("BankName"));
				vo.setBranchName(rs.getString("BranchName"));
				vo.setBranchShortName(rs.getString("BranchShortName"));
				vo.setIfscCode(rs.getString("IFSCCode"));
				vo.setAddress(rs.getString("BranchAddress"));
				vo.setStatus(rs.getString("isActive"));
				vo.setReason(rs.getString("Reason"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null && rs != null && con != null) {
					pst.close();
					rs.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");
		return list;
	}

	public String removeBranch(String[] removeId, UserLoggingsPojo custdetails, String status, String reason, String button, String log_audit_session) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchDAOImpl:removeBranch Starting");
		
		Connection con = null;
		PreparedStatement pst = null;
		int  count = 0;
		String status1=null;
		//(removeId);
		try{
			con =JDBCConnection.getSeparateConnection(custdetails);
			for(int i=0;i<removeId.length;i++){
			pst = con.prepareStatement(BankBranchSqlUtils.REMOVE_BRANCH_DETAILS);
			pst.setString(1, status);
			pst.setString(2, reason);
			pst.setString(3, removeId[i]);
		    count = pst.executeUpdate();
		    //(count);
			if (count != 0){
				HelperClass.recordLog_Activity(log_audit_session, "Master", "Bank Branch Master", button, pst.toString(), custdetails);
				status1 = "success";
			}
			else{
				status1="fail";
			}
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				if(pst != null  || con!= null){
					
					try {
						pst.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : removeBranch : Ending");
		
		return status1;
	}

	public BankBranchVO editBranchGet(String branchId, UserLoggingsPojo custdetails) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchDAOImpl:saveBank Starting");
		
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet  rs = null;
		//(branchId);
		BankBranchVO vo = new BankBranchVO();
		try{
			con =JDBCConnection.getSeparateConnection(custdetails);
			pst = con.prepareStatement(BankBranchSqlUtils.GET_BRANCH_DETAILS);
			pst.setString(1, branchId);
            rs = pst.executeQuery();
		    while(rs.next()){
           //SELECT `BranchName`,`BranchShortName`,`IFSCCode`,`BranchAddress`,`BankId`,`isActive`FROM ai_bank_branch WHERE `BranchId`='BRH1';
		    	vo.setBankname(rs.getString("BankId"));
		    	vo.setBranchName(rs.getString("BranchName"));
		    	vo.setBranchShortName(rs.getString("BranchShortName"));
		    	vo.setId(branchId);
		    	vo.setAddress(rs.getString("BranchAddress"));
		    	vo.setIfscCode(rs.getString("IFSCCode"));
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				if(rs != null || pst != null  || con!= null){
					
					try {
						rs.close();
						pst.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");
		
		return vo;
	}

	public List<BankBranchVO> getSearchBranchList(String searchText, UserLoggingsPojo custdetails, String status) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankBranchDAOImpl: getSearchBranchList Starting");

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BankBranchVO> list = new ArrayList<BankBranchVO>();
		try {
			con =JDBCConnection.getSeparateConnection(custdetails);
			pst = con.prepareStatement(BankBranchSqlUtils.GET_SEARCH_BRANCH_LIST);
			pst.setString(1, status);
			pst.setString(2, searchText +"%");
			pst.setString(3, searchText +"%");
			pst.setString(4, searchText +"%");
			pst.setString(5, searchText +"%");
		    ////("88888888888"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				BankBranchVO vo = new BankBranchVO();
				vo.setId(rs.getString("BranchId"));
				vo.setBankname(rs.getString("BankName"));
				vo.setBranchName(rs.getString("BranchName"));
				vo.setBranchShortName(rs.getString("BranchShortName"));
				vo.setIfscCode(rs.getString("IFSCCode"));
				vo.setAddress(rs.getString("BranchAddress"));
				vo.setStatus(rs.getString("isActive"));
				vo.setReason(rs.getString("Reason"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null && rs != null && con != null) {
					pst.close();
					rs.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getSearchBranchList : Ending");
		
		return list;
	}

}
