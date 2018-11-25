package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.PeriodDetailsDao;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.BankSqlUtils;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.PeriodDetailsSqlUtils;

import com.centris.campus.vo.PeriodVo;


public class PeriodDetailsDaoImpl implements PeriodDetailsDao {
	private static Logger logger = Logger.getLogger(PeriodDetailsDaoImpl.class);
	@Override
	public String insertperiod(PeriodVo vo, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ "Control in PeriodDetailsDaoImpl:insertperiod Starting");

		Connection con = null;
		PreparedStatement pst = null;
		String result = null;
		ResultSet rs = null;
		int count=0;

		try {
			con =JDBCConnection.getSeparateConnection(userLoggingsVo);
			if(vo.getSlno()==0){
			pst=con.prepareStatement(PeriodDetailsSqlUtils.SAVE_PERIOD_DETAILS);
			pst.setString(1, vo.getLocId());
			pst.setString(2, vo.getStreamId());
			pst.setString(3, vo.getClsId());
			pst.setInt(4, vo.getNoofperiod());
			pst.setString(5, vo.getCreatedby());
			count=pst.executeUpdate();
			if(count>0){
				result="true";
			}
			else{
				result="false";
			}
		
			}
			else{
				pst=con.prepareStatement(PeriodDetailsSqlUtils.UPDATE_PERIOD_DETAILS);
				pst.setString(1, vo.getLocId());
				pst.setString(2, vo.getStreamId());
				pst.setString(3, vo.getClsId());
				pst.setInt(4, vo.getNoofperiod());
				pst.setString(5, vo.getModifyby());
				pst.setInt(6, vo.getSlno());
				count=pst.executeUpdate();
				if(count>0){
					result="update";
				}
				else{
					result="false";
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
				+ " Control in PeriodDetailsDaoImpl:insertperiod : Ending");
		return result;
	}
	@Override
	public ArrayList<PeriodVo> periodList(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl: periodList Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<PeriodVo> list=new ArrayList<PeriodVo>();
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(PeriodDetailsSqlUtils.GET_PERIOD_LIST);
			System.out.println("sdgfgtwetwetwet"+pst);
			rs = pst.executeQuery();
			while(rs.next()){
				PeriodVo vo= new PeriodVo();
				vo.setSlno(rs.getInt("period_id"));
				vo.setLocId(rs.getString("Location_Name"));
				vo.setStreamId(rs.getString("classstream_name_var"));
				vo.setClsId(rs.getString("classdetails_name_var"));
				vo.setNoofperiod(rs.getInt("no_of_period"));
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
				+ " Control in PeriodDetailsDaoImpl: periodList : Ending");
		return list;
	}
	@Override
	public PeriodVo editPeriod(String periodId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl: editPeriod Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		PeriodVo vo= new PeriodVo();
		try {
			con=JDBCConnection.getSeparateConnection(userLoggingsVo);
			pst=con.prepareStatement(PeriodDetailsSqlUtils.EDIT_PERIOD_LIST);
			pst.setString(1,periodId);
			System.out.println("sdgfgtwetwetwet"+pst);
			rs = pst.executeQuery();
			while(rs.next()){
				vo.setLocId(rs.getString("loc_id"));
				vo.setStreamId(rs.getString("stream_id"));
				vo.setClsId(rs.getString("class_id"));
				vo.setNoofperiod(rs.getInt("no_of_period"));
				vo.setSlno(rs.getInt("period_id"));
				
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
				+ " Control in PeriodDetailsDaoImpl: editPeriod : Ending");
		return vo;
	}
	@Override
	public String deletePeriod(String[] periodId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl: deletePeriod Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
	    int count=0;
	    String result=null;
		

		try {
			con=JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0;i<periodId.length;i++){
				pst=con.prepareStatement(PeriodDetailsSqlUtils.DELETE_PERIOD_LIST);
				pst.setString(1, periodId[i]);
				count=pst.executeUpdate();
				if(count>0){
					result="true";
				}
				
				else{
					result="false";
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
				+ " Control in PeriodDetailsDaoImpl: deletePeriod : Ending");
		return result;
	}
	@Override
	public ArrayList<PeriodVo> getperiodlist(PeriodVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl: periodList Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<PeriodVo> list=new ArrayList<PeriodVo>();
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(PeriodDetailsSqlUtils.GET_PERIOD_LIST_FILTER);
			pst.setString(1, vo.getLocId());
			pst.setString(2, vo.getStreamId());
			pst.setString(3, vo.getClsId());

			rs = pst.executeQuery();
			System.out.println("33333333333"+pst);
			while(rs.next()){
				PeriodVo vo1= new PeriodVo();
				vo1.setSlno(rs.getInt("period_id"));
				vo1.setLocname(rs.getString("Location_Name"));
				vo1.setStreamname(rs.getString("classstream_name_var"));
				vo1.setClsname(rs.getString("classdetails_name_var"));
				vo1.setNoofperiod(rs.getInt("no_of_period"));
				list.add(vo1);
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
				+ " Control in PeriodDetailsDaoImpl: periodList : Ending");
		return list;
	}
	@Override
	public String validateclassName(String clsId, UserLoggingsPojo custdetails,String locId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in PeriodDetailsDaoImpl:validateclassName Starting");
		
		Connection con=null;
		PreparedStatement pst=null;
		String result=null;
		int count=0;
		ResultSet rs=null;
	
		try {
			con=JDBCConnection.getSeparateConnection(custdetails);
			pst=con.prepareStatement(PeriodDetailsSqlUtils.CHECK_CLASS_NAME);
			pst.setString(1, clsId);
			pst.setString(2, locId);
			System.out.println("88888888"+pst);
			rs = pst.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
				
			}
			
			if(count>0){
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
				+ " Control in PeriodDetailsDaoImpl : validateclassName : Ending");
		return result;
	}

}
