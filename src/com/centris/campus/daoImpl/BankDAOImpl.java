package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.BankDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.BankSqlUtils;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.BankVO;



public class BankDAOImpl  implements BankDAO {

	private static Logger logger = Logger.getLogger(BankDAOImpl.class);

	@Override
	public String saveBank(BankVO vo, String usercode,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl:saveBank Starting");
		Connection con=null;
		PreparedStatement pst=null;
		String result=null;
		int count=0;
		try {
			String id=new IDGenerator().getPrimaryKeyID("campus_bank",custdetails);
			Timestamp curr_date=HelperClass.getCurrentTimestamp();

			con=JDBCConnection.getSeparateConnection(custdetails);
			con.setAutoCommit(false);
			if(!vo.getHbankId().equalsIgnoreCase("")){
				pst=con.prepareStatement(BankSqlUtils.UPDATE_BANK);
				pst.setString(1, vo.getName());
				pst.setString(2, vo.getShortname());
				pst.setString(3, usercode);
				pst.setTimestamp(4, curr_date);
				pst.setString(5, vo.getHbankId());
				count=pst.executeUpdate();
				if(count > 0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(), "Master", "Bank Master", "Update", pst.toString(),custdetails);
					con.commit();
					result="update";
				}else{
					con.rollback();
					result="fail";
				}
			}else{
				pst=con.prepareStatement(BankSqlUtils.SAVE_BANK);
				pst.setString(1, id);
				pst.setString(2, vo.getName());
				pst.setString(3, vo.getShortname());
				pst.setString(4, usercode);
				pst.setTimestamp(5, curr_date);
				count=pst.executeUpdate();
				if(count > 0){
					HelperClass.recordLog_Activity(vo.getLog_audit_session(), "Master", "Bank Master", "Insert", pst.toString(),custdetails);
					con.commit();
					result="success";
				}else{
					con.rollback();
					result="fail";
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(pst !=null && con!=null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");
		return result;
	}

	@Override
	public String validateBankName(String name,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl:saveBank Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		String result=null;
		int count=0;
		ResultSet rs=null;
		String value=null;
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(BankSqlUtils.CHECK_BANK_NAME);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
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
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(pst !=null && con!=null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : saveBank : Ending");
		return result;
	}

	@Override
	public List<BankVO> getBankList(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<BankVO> list=new ArrayList<BankVO>();
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(BankSqlUtils.GET_BANK_LIST);
			rs = pst.executeQuery();
			while(rs.next()){
				BankVO vo=new BankVO();
				vo.setId(rs.getString("BankId"));
				vo.setName(rs.getString("BankName"));
				vo.setShortname(rs.getString("BankShortName"));
				
				if(rs.getString("isActive")=="Y"){
					vo.setStatus("Active");
				}
				else{
					vo.setStatus("InActive");
				}
				vo.setReason(rs.getString("Reason"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(rs!=null && pst !=null && con!=null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList : Ending");
		return list;
	}

	@Override
	public BankVO editBankDetail(String bankId,UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		BankVO vo=new BankVO();
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(BankSqlUtils.GET_BANK_SINGLE_RECORD);
			pst.setString(1, bankId);
			rs = pst.executeQuery();
			while(rs.next()){
				vo.setId(rs.getString("BankId"));
				vo.setName(rs.getString("BankName"));
				vo.setShortname(rs.getString("BankShortName"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(rs!=null && pst !=null && con!=null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList : Ending");
		return vo;
	}

	@Override
	public String removeBank(String[] bankId,UserLoggingsPojo custdetails,String reason,String status,String log_audit_session, String button ) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList Starting");

		Connection con=null;
		PreparedStatement pst=null;
		int i = 0;
		String status1 = null;
		try{
			
			con=JDBCConnection.getSeparateConnection(custdetails);
			for(int J=0;J<bankId.length;J++){
			 pst=con.prepareStatement(BankSqlUtils.REMOVE_BANK);
			 pst.setString(1,status);
			 pst.setString(2, reason);
			 pst.setString(3,bankId[J] );
			 //("11111111111"+pst);
			 i = pst.executeUpdate();
			 
			 if(i>0){
				 HelperClass.recordLog_Activity(log_audit_session, "Master", "Bank Master",button , pst.toString(), custdetails);
				 status1 = "success";
			 }else{
				 status1 = "fail";
			   }
			}
		  }catch(Exception e){
			e.printStackTrace();
		  }finally{
			try {
				if( pst !=null && con!=null){
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getBankList : Ending");
		
		return status1;
	}

	@Override
	public List<BankVO> getSearchBankList(String searchText,UserLoggingsPojo custdetails,String status) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getSearchBankList Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<BankVO> list=new ArrayList<BankVO>();
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(BankSqlUtils.GET_BANK_SEARCH_LIST);
			pst.setString(1,status);
			pst.setString(2, "%"+ searchText +"%");
			pst.setString(3, "%"+ searchText +"%");
			rs = pst.executeQuery();
			while(rs.next()){
				BankVO vo=new BankVO();
				vo.setId(rs.getString("BankId"));
				vo.setName(rs.getString("BankName"));
				vo.setShortname(rs.getString("BankShortName"));
				if(rs.getString("isActive")=="Y"){
					vo.setStatus("Active");
				}
				else{
					vo.setStatus("InActive");
				}
				vo.setReason(rs.getString("Reason"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally{
			try {
				if(rs!=null && pst !=null && con!=null){
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in BankDAOImpl : getSearchBankList : Ending");
		return list;
	}
}



	
