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

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.FeeMasterDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.feeReportVO;

public class FeeMasterDAOIMPL implements FeeMasterDAO {

	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public synchronized String insertFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : insertFeeDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

		try {
				conn = JDBCConnection.getSeparateConnection(pojo);

				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_DETAILS);
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_master",pojo));
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getDescription());
				pstmt.setString(4, vo.getCreatedby());
				pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
				pstmt.setString(6,vo.getConcessiontype());
				pstmt.setString(7,vo.getFeeTypeId().trim());
				pstmt.setString(8,vo.getAcademicYear().trim());
				pstmt.setString(9,vo.getLocationId().trim());
				
				//System.out.println("pstmt"+pstmt);

				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Setup","Insert",pstmt.toString(),pojo);
					result_Status = MessageConstants.SuccessMsg;
				} else {
					result_Status = MessageConstants.ErrorMsg;
				}
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {
				try {

					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} 
				catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : insertFeeDetails Ending");
		return result_Status;
	}
	
	public synchronized String updateFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : updateFeeDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_DETAILS);

			pstmt.setString(1, vo.getName().trim());
			pstmt.setString(2, vo.getDescription().trim());
			pstmt.setString(3, vo.getCreatedby().trim());
			pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			pstmt.setString(5, vo.getConcessiontype());
			pstmt.setString(6, vo.getFeeTypeId());
			pstmt.setString(7, vo.getAcademicYear());
			pstmt.setString(8, vo.getLocationId());
			pstmt.setString(9, vo.getCreatedby());
			pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
			pstmt.setString(11, vo.getId().trim());

			//System.out.println("pstmtupdate"+pstmt);

			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Setup","Update",pstmt.toString(),pojo);
				result_Status = MessageConstants.SuccessUpMsg;
			} else {
				result_Status = MessageConstants.ErrorUpMsg;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : updateFeeDetails Ending");
		return result_Status;
		
		
	}
	
public synchronized int getFeeNameCheckDao(String feeName) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeNameCheckDao Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_COUNT);

			pstmt.setString(1, feeName);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

			

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeNameCheckDao Ending");
		return count;
	}

	public synchronized boolean getnamecount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getId().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_COUNT);
				pstmt.setString(1, vo.getName().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		}

		if (!vo.getId().equalsIgnoreCase(""))
		{
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_COUNT);
				pstmt.setString(1, vo.getId().trim());
				//pstmt.setString(2, vo.getName().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());

				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

				if (count > 0)

				{
					result = true;
				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

				try {
					if (rs!= null && !rs.isClosed()) {
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
					exception.printStackTrace();
				}
			}

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");
		return result;

	}

	public synchronized ArrayList<AddFeeVO> getfeedetails(AddFeeVO val,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getfeedetails Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<AddFeeVO> feedetails = new ArrayList<AddFeeVO>();
		
		if (val.getName()==null || val.getName().equalsIgnoreCase("") || val.getName().equalsIgnoreCase("all") )
		{
			try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_DETAILS);
			pstmt.setString(1, val.getAcademicYear());
			
			if(val.getLocationId()=="" || val.getLocationId()==null){
				pstmt.setString(2, "%%");
			}else{
			  pstmt.setString(2, val.getLocationId());
			}
			pstmt.setString(3, val.getStatus());
			
			System.out.println("GET_FEE_DETAILS -->>"+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				AddFeeVO vo = new AddFeeVO();

				vo.setId(rs.getString("FeeCode").trim());
				vo.setName(rs.getString("FeeName").trim());
				vo.setConcession(rs.getString("IsConcession"));
				vo.setDescription(rs.getString("description").trim());
				vo.setFeeType(rs.getString("feeType").trim());
				vo.setFeeTypeId(rs.getString("feeTypeId").trim());
				vo.setAcademicYearName(rs.getString("acadamic_year"));
				vo.setLocationName(rs.getString("Location_Name"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")) {
					vo.setStatus("Active");
				}
				else {
					vo.setStatus("InActive");
				}
				if(rs.getString("remark")=="" || rs.getString("remark")==null){
					vo.setRemark("");
				}else{
				   vo.setRemark(rs.getString("remark"));
				}
				feedetails.add(vo);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		
		}
		
		else if (!val.getName().equalsIgnoreCase(""))
		
		{
		
			try {

				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_FEE_DETAILS);
				pstmt.setString(1, "%"+val.getName().trim()+"%");
				pstmt.setString(2, "%"+val.getName().trim()+"%");
				pstmt.setString(3, "%"+val.getName().trim()+"%");
				pstmt.setString(4, "%"+val.getName().trim()+"%");
				pstmt.setString(5, "%"+val.getName().trim()+"%");
				pstmt.setString(6, "%"+val.getName().trim()+"%");
				pstmt.setString(7, "%"+val.getName().trim()+"%");
				pstmt.setString(8, val.getAcademicYear());
				pstmt.setString(9, val.getLocationId());
				pstmt.setString(10, val.getStatus());
				System.out.println("search fee type: "+pstmt);
				
				
				rs = pstmt.executeQuery();
				while (rs.next())

				{

					AddFeeVO vo = new AddFeeVO();

					vo.setId(rs.getString("FeeCode").trim());
					vo.setName(rs.getString("FeeName").trim());
					vo.setDescription(rs.getString("description").trim());
					vo.setConcession(rs.getString("IsConcession"));
					vo.setFeeType(rs.getString("feeType"));
					vo.setFeeTypeId(rs.getString("feeTypeId"));
					if(rs.getString("isActive").equalsIgnoreCase("Y")) {
						vo.setStatus("Active");
					}
					else {
						vo.setStatus("InActive");
					}
					vo.setRemark(rs.getString("remark"));
					
					feedetails.add(vo);

				}

			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}
			
		}
		
		

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getfeedetails Ending");

		return feedetails;
	}

	public synchronized AddFeeVO editFeeDetails(AddFeeVO vo, UserLoggingsPojo dbdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : editFeeDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Connection conn = null;

		AddFeeVO fee = null;
		try {

			conn = JDBCConnection.getSeparateConnection(dbdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_EDIT_DETAILS);

			pstmt.setString(1, vo.getId());

			rs = pstmt.executeQuery();
			
			System.out.println("FeeEdit: ");
			while (rs.next())

			{
				fee = new AddFeeVO();
				fee.setId(rs.getString("FeeCode"));
				fee.setName(rs.getString("FeeName"));
				fee.setConcession(rs.getString("IsConcession"));
				fee.setDescription(rs.getString("description"));
				fee.setFeeTypeId(rs.getString("feeTypeId"));
				fee.setFeeType(rs.getString("feeType"));
				fee.setLocationId(rs.getString("locationId"));
				fee.setAcademicYear(rs.getString("academicyear"));
				fee.setSlno(rs.getString("slno"));
			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : editFeeDetails Ending");

		return fee;
	}

	public synchronized boolean deleteFeeDetails(AddFeeVO vo,String button, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : deleteFeeDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		int no=0;
		Connection conn = null;
		boolean result = false;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			for(int i=0;i<vo.getGetDataArray().length;i++){//-------6
				PreparedStatement pstmt1=conn.prepareStatement("SELECT COUNT(*) FROM campus_fee_setupdetails WHERE feecode=?");
				pstmt1.setString(1, vo.getGetDataArray()[i]);
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next()){
					no=rs1.getInt(1);
				}
				
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_FEE_DETAILS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getRemark());
			pstmt.setString(3, vo.getGetDataArray()[i]);//---------7
			
			if(no==0)
			count = pstmt.executeUpdate();
			//-------8
			if (count > 0)

			{
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","FeeSetup",button,pstmt.toString(),userLoggingsVo);
				result = true;
			}
			else{
				result=false;
			}
			}
		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())

				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : deleteFeeDetails Ending");

		return result;

	}

	
	public synchronized ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection conn = null;
	

		ArrayList<AddFeeVO> feedetails = new ArrayList<AddFeeVO>();
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_FEE_DETAILS);
			pstmt.setString(1, "%"+vo.getName()+"%");
			
			rs = pstmt.executeQuery();
			while (rs.next())

			{

				AddFeeVO val = new AddFeeVO();

				val.setId(rs.getString("FeeCode").trim());
				val.setName(rs.getString("FeeName").trim());
				val.setDescription(rs.getString("description").trim());
				feedetails.add(val);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : searchFeeDetails Ending");

		return feedetails;
	
		
	}
	public List<ReportMenuVo> getclasslistDao() {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getclasslistDao Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		Connection conn = null;
	
		
		
		List<ReportMenuVo> classlist = new ArrayList<ReportMenuVo>();
		
		
		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_CLASS_DETAILS);
			System.out.println("pstmt "+pstmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportMenuVo  vo = new ReportMenuVo();
				
				vo.setClassId(rs.getString("classdetail_id_int").trim());
				vo.setClassname(rs.getString("classdetails_name_var").trim());
				classlist.add(vo);
			}
			
		}catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		
		finally {
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
			} 
			catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
			JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getclasslistDao Ending");
		
		return classlist;
	}



	public ArrayList<AddFeeVO> feeTypeListDao(AddFeeVO vo1, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : feeTypeListDao Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<AddFeeVO> feeTypeList = new ArrayList<AddFeeVO>();
		

		
		try {

		conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
		pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_TPYE_LIST);

		rs = pstmt.executeQuery();
		while (rs.next())

		{

			AddFeeVO vo = new AddFeeVO();

			vo.setFeeTypeId(rs.getString("feeTypeId").trim());
		    vo.setSlno(rs.getString("slno"));
			vo.setFeeType(rs.getString("feeType").trim());
		
			feeTypeList.add(vo);

		}

	} catch (SQLException sqlExp) {
		logger.error(sqlExp.getMessage(), sqlExp);
		sqlExp.printStackTrace();
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
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
			exception.printStackTrace();
		}
	}
	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : feeTypeListDao Ending");

		return feeTypeList;
	}


	public String getFeeTypeCount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeCount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String  result = null;
		String value=null;
		if (vo.getFeeTypeId().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_FEE_TYPE_COUNT);
				pstmt.setString(1, vo.getFeeType().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
				
				System.out.println("feeType:"+pstmt);
				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					value=rs.getString("isActive");
				}

				if (count > 0 && value.equalsIgnoreCase("N") )
				{
					result ="inactive";
				}
				else if(count > 0 && value.equalsIgnoreCase("Y")){
					result="true";
				}
              
				else{
					result="false";
				}
			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		}

		if (!vo.getFeeTypeId().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_FEE_COUNT);
				pstmt.setString(1, vo.getFeeTypeId().trim());
				pstmt.setString(2, vo.getLocationId());
				pstmt.setString(3, vo.getAcademicYear());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					value=rs.getString("isActive");
				}

				if (count > 0 && value.equalsIgnoreCase("N") )
				{
					result ="inactive";
				}
				else if(count > 0 && value.equalsIgnoreCase("Y")){
					result="true";
				}
              
				else{
					result="false";
				}
			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
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
					exception.printStackTrace();
				}
			}

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeCount Ending");
		return result;

	}


	public String saveFineConfiguration(String[] accyearArray, String[] termArray,
			String[] locationArray, String[] classArray, String[] days, String[] amountArray, String isApplicable, String userCode,String accyear, String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : saveFineConfiguration Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		int count1 = 0;
		int count = 0;
		Connection conn = null;
		String result =null;
		
			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt1=conn.prepareStatement("DELETE FROM campus_fineconfiguration where accyear=? AND locationId=?");
				pstmt1.setString(1, accyear);
				pstmt1.setString(2, locationArray[0]);
				
				count1=pstmt1.executeUpdate();	
				
				for(int i=0;i<days.length;i++){
					
					
					
				pstmt = conn.prepareStatement("INSERT INTO campus_fineconfiguration (locationId,classId,date,amount,createdBy,IsApplicable,accyear,termid) VALUES(?,?,?,?,?,?,?,?)");
				pstmt.setString(1, locationArray[i]);
				pstmt.setString(2, classArray[i]);
				pstmt.setString(3, HelperClass.convertUIToDatabase(days[i]));
				pstmt.setString(4, amountArray[i]);
				pstmt.setString(5, userCode);
				pstmt.setString(6, isApplicable);
				pstmt.setString(7, accyearArray[i]);
				pstmt.setString(8, termArray[i]);
				System.out.println("feeType:  "+pstmt);
				count = pstmt.executeUpdate();
				

				if (count > 0)

				{
					result = "true";
				}
				else{
					result= "false";
				}
				}
				
				pstmt1.close();
				
			} catch (SQLException sqlExp) {
				logger.error(sqlExp.getMessage(), sqlExp);
				sqlExp.printStackTrace();
			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			} finally {

				try {
					
					if (pstmt1 != null && !pstmt1.isClosed()) {
						pstmt1.close();
					}
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
					if (conn != null && !conn.isClosed()) {

						conn.close();
					}

				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				}
			}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : saveFineConfiguration Ending");
		return result;
	}


	public List<feeReportVO> getFineConfiguration(String acyear, String current_location, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFineConfiguration Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<feeReportVO> feeTypeList = new ArrayList<feeReportVO>();
		

		
		try {

		conn = JDBCConnection.getSeparateConnection(custdetails);
		pstmt = conn.prepareStatement("SELECT * FROM campus_fineconfiguration WHERE accyear=? AND locationId=?");
		pstmt.setString(1, acyear);
		pstmt.setString(2, current_location);
		
		
		System.out.println("fine="+pstmt);
		rs = pstmt.executeQuery();
		while (rs.next())

		{

			feeReportVO vo = new feeReportVO();
			vo.setAccyearid(rs.getString(2));
			vo.setLocationid(rs.getString(3));
			vo.setClassnameid(rs.getString(4));
			vo.setTermId(rs.getString(5));
			vo.setFineDate(HelperClass.convertDatabaseToUI(rs.getString(6)));
			vo.setFine(rs.getDouble(7));
			vo.setIsApplicable(rs.getString(10));
			feeTypeList.add(vo);

		}

	} catch (SQLException sqlExp) {
		logger.error(sqlExp.getMessage(), sqlExp);
		sqlExp.printStackTrace();
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
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
			exception.printStackTrace();
		}
	}
	

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFineConfiguration Ending");

		return feeTypeList;
	}
	
	public String saveFeeType(FeeNameVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : saveFeeType Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String result = null,Operation=null;
		try{
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			if(vo.getSno()!=0){
				pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_FEE_TYPE);
				pstmt.setString(2,vo.getFeeId());
				pstmt.setString(1,vo.getFeename());
				pstmt.setString(3,vo.getUserid());
				pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
				pstmt.setInt(5,vo.getSno());
				Operation="Update";
			}else{
				pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_FEE_TYPE);
				pstmt.setString(1,vo.getFeeId());
				pstmt.setString(2,vo.getFeename());
				pstmt.setString(3,vo.getUserid());
				Operation="Insert";
			}
			count = pstmt.executeUpdate();
			if(count > 0){
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Fee Type",Operation,pstmt.toString(),userLoggingsVo);
				result = "success";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : saveFeeType Ending");

		return result;
	}


	public FeeNameVo getFeeTypeById(String feetypeId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeById Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		FeeNameVo vo = new FeeNameVo();
		ResultSet rs = null;
		try{
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_TYPE_BY_ID);
			pstmt.setString(1,feetypeId);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				vo.setSno(Integer.parseInt(feetypeId));
				vo.setFeename(rs.getString("feeType"));
				vo.setFeeId(rs.getString("feeTypeId"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeById Ending");

		return vo;
	}

	public String deletefeetype(String[] feetypeId,String log_audit_session, String reason, String status, String button,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : deletefeetype Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String result = null;
		
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_FEE_TYPE);
			System.out.println("//////////////////"+status);
			for(int i=0;i<feetypeId.length;i++){
				pstmt.setString(1, status);
				pstmt.setString(2, reason);
				pstmt.setString(3,feetypeId[i]);
				count = pstmt.executeUpdate();
				if(count > 0){
				
				HelperClass.recordLog_Activity(log_audit_session,"Fee","Fee Type",button,pstmt.toString(),userLoggingsVo);	
				}
			}
			if(count > 0){
				
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally {

			try {
				
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {

					conn.close();
				}

			} catch (Exception exception) {
				logger.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : deletefeetype Ending");

		return result;
	}


	public List<FeeNameVo> getFeeTypeList(String isActive,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeList Starting");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeeNameVo> list = new ArrayList<FeeNameVo>();
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_TYPE_LIST);
			pstmt.setString(1,isActive);
			rs = pstmt.executeQuery();
			while(rs.next()){
				FeeNameVo vo = new FeeNameVo();
				vo.setSno(rs.getInt("slno"));
				vo.setFeeId(rs.getString("feeTypeId"));
				vo.setFeename(rs.getString("feeType"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")) {
					vo.setStatus("Active");
				}
				else {
					vo.setStatus("InActive");
				}
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					vo.setRemark("");
				}else{
					vo.setRemark(rs.getString("remarks"));
				}
				
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeTypeList Ending");

		return list;
	}

	public String checkduplicatefeeId(String feetypeId,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : checkduplicatefeeId Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String result = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_FEE_TYPE_ID);
			pstmt.setString(1,feetypeId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			if(count > 0){
				result = "true";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : checkduplicatefeeId Ending");

		return result;
	}


	public String checkduplicatefeeTypeName(String feetypename,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : checkduplicatefeeTypeName Starting");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String result = null;
		try{
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_FEE_TYPE_NAME);
			pstmt.setString(1,feetypename);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			if(count > 0){
				result = "true";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {

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
				exception.printStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : checkduplicatefeeTypeName Ending");

		return result;
	}

	public List<AddFeeVO> getFeeStatusList(String locId, String accId, String status,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getFeeStatusList Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		Connection conn = null;
		

		ArrayList<AddFeeVO> feedetails = new ArrayList<AddFeeVO>();
		
		
			try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_FEE_DETAILS_STATUS_LIST);
			pstmt.setString(1,locId);
			pstmt.setString(2,accId );
			pstmt.setString(3,status );
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next())

			{

				AddFeeVO vo = new AddFeeVO();

				vo.setId(rs.getString("FeeCode").trim());
				vo.setName(rs.getString("FeeName").trim());
				vo.setConcession(rs.getString("IsConcession"));
				vo.setDescription(rs.getString("description").trim());
				vo.setFeeType(rs.getString("feeType").trim());
				vo.setFeeTypeId(rs.getString("feeTypeId").trim());
				vo.setAcademicYearName(rs.getString("acadamic_year"));
				vo.setLocationName(rs.getString("Location_Name"));
				if(rs.getString("isActive").equalsIgnoreCase("Y")) {
					vo.setStatus("Active");
				}
				else {
					vo.setStatus("InActive");
				}
				vo.setRemark(rs.getString("remark"));
				feedetails.add(vo);

			}

		} catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
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
				exception.printStackTrace();
			}
		}
		
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeMasterDAOIMPL : getFeeStatusList Ending");	
		
		return feedetails;
	}
}
