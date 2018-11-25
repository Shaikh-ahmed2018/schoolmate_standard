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
import com.centris.campus.dao.TermMasterDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.TermMasterVo;

public class TermMasterDAOIMPL implements TermMasterDAO {

	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	public synchronized TermMasterVo getaccdetails(TermMasterVo vo, UserLoggingsPojo custdetails)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getaccdetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection conn = null;
		
		TermMasterVo termvo = null;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACC_DETAILS);
			pstmt.setString(1, vo.getAccid());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				termvo = new TermMasterVo();

				termvo.setAccid(rs.getString("acadamic_id"));
				termvo.setAccyear(rs.getString("acadamic_year"));
				termvo.setAcastartdate(HelperClass.convertDatabaseToUI(rs.getString("startDate")));
				termvo.setAcaenddate(HelperClass.convertDatabaseToUI(rs.getString("endDate")));

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
				+ " Control in TermMasterDAOIMPL : getaccdetails Ending");

		return termvo;
	}
 
	public synchronized String getnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String result = null;
        String value=null;
		if (vo.getTermid().equalsIgnoreCase(""))
		{
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.TERM_COUNT);
				pstmt.setString(1, vo.getTermname().trim());
				pstmt.setString(2, vo.getAccyear());
				pstmt.setString(3, vo.getLocationId());

				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					value=rs.getString("isActive");
				}

				if (count > 0 && value.equalsIgnoreCase("N"))
				{
					result = "inactive";
				}
				else if(count > 0 && value.equalsIgnoreCase("Y")){
					result = "true";
				}
				else{
					
					result = "false";
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

		else if (!vo.getTermid().equalsIgnoreCase(""))
		{
			try {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_TERM_COUNT);
				pstmt.setString(1, vo.getTermid().trim());
				pstmt.setString(2, vo.getTermname().trim());
				pstmt.setString(3, vo.getLocationId());
				pstmt.setString(4, vo.getAccyear());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					value=rs.getString("isActive");
				}

				if (count > 0 && value.equalsIgnoreCase("N"))
				{
					result ="inactive";
				}
				else if(count > 0 && value.equalsIgnoreCase("Y")){
					result ="true";
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
				+ " Control in TermMasterDAOIMPL : getnamecount Ending");

		return result;
	}

	public synchronized String addtermfeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : addtermfeedetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

		try {

			String str = IDGenerator.getPrimaryKeyID("campus_fee_termdetails",userLoggingsVo);

			String[] part = str.split("(?<=\\D)(?=\\d)");

			String ternorder = part[1];

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SQLUtilConstants.ADD_TERM);
			pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_fee_termdetails",userLoggingsVo));
			pstmt.setString(2, vo.getTermname());
			pstmt.setString(3,HelperClass.convertUIToDatabase(vo.getStartdate()));
			pstmt.setString(4, HelperClass.convertUIToDatabase(vo.getEnddate()));
			pstmt.setString(5, vo.getAccyear());
			pstmt.setString(6, vo.getDescription());
			pstmt.setString(7, ternorder);
			pstmt.setString(8, vo.getTransporttype());
			pstmt.setString(9, vo.getCreateuser());
			pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
			pstmt.setString(11,vo.getLocationId());
			pstmt.setString(12,"Y");
			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Term Setup","Insert",pstmt.toString(),userLoggingsVo);
				result_Status = MessageConstants.SuccesstermMsg;
			} else {
				result_Status = MessageConstants.ErrortermMsg;
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
				+ " Control in TermMasterDAOIMPL : addtermfeedetails Ending");

		return result_Status;

	}
	
	public synchronized String updatetermDetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : updatetermDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_TERM_DETAILS);

			pstmt.setString(1, vo.getTermname());
			pstmt.setString(2,
					HelperClass.convertUIToDatabase(vo.getStartdate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(vo.getEnddate()));
			pstmt.setString(4, vo.getAccyear());
			pstmt.setString(5, vo.getDescription());
			pstmt.setString(6, vo.getTransporttype());
			pstmt.setString(7, vo.getCreateuser());
			pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
			pstmt.setString(9, vo.getLocationId());
			pstmt.setString(10, vo.getTermid());

			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Term Setup","Update",pstmt.toString(),userLoggingsVo);
				result_Status = MessageConstants.SuccesstermUpMsg;
			} else {
				result_Status = MessageConstants.ErrortermUpMsg;
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
				+ " Control in TermMasterDAOIMPL : updatetermDetails Ending");
		return result_Status;

	}
	public synchronized String updatesepataretermDetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : updatesepataretermDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_TRANPORT_TERM_DETAILS);

			pstmt.setString(1, vo.getTermname());
			pstmt.setString(2, HelperClass.convertUIToDatabase(vo.getStartdate()));
			pstmt.setString(3, HelperClass.convertUIToDatabase(vo.getEnddate()));
			pstmt.setString(4, vo.getAccyear());
			pstmt.setString(5, vo.getDescription());
			pstmt.setString(6, vo.getTransporttype());
			pstmt.setString(7, vo.getCreateuser());
			pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
			pstmt.setString(9, vo.getLocationId());
			pstmt.setString(10, vo.getTermid());

			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","Term SetUp","Update",pstmt.toString(),userLoggingsVo);
				result_Status = MessageConstants.SuccesstermUpMsg;
			} else {
				result_Status = MessageConstants.ErrortermUpMsg;
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
				+ " Control in TermMasterDAOIMPL : updatesepataretermDetails Ending");
		return result_Status;

	}

	public synchronized ArrayList<TermMasterVo> termList(TermMasterVo val,UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : termList Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String locationId=val.getLocationId();
		if(locationId==null){
			locationId="%%";
		}
		ArrayList<TermMasterVo> termList = new ArrayList<TermMasterVo>();
		if (val.getTermname() == null || val.getTermname().equalsIgnoreCase(""))
		{
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_TERM);
				pstmt.setString(1, val.getAccyear());
				pstmt.setString(2,locationId);
				/*pstmt.setString(3, val.getStatus());*/
				System.out.println("GET_TERM -->>"+pstmt);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					TermMasterVo vo = new TermMasterVo();
					count++;
					vo.setSno(count);
					vo.setTermid(rs.getString("termid"));
					vo.setTermname(rs.getString("termname"));
					vo.setStartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					vo.setEnddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					vo.setAccyear(rs.getString("acadamic_year"));
					vo.setAccid(rs.getString("accyear"));
					vo.setLocationName(rs.getString("Location_Name"));
					/*if(rs.getString("isActive").equalsIgnoreCase("Y")) {
						vo.setStatus("Active");
					}
					else {
						vo.setStatus("InActive");
					}
					if(rs.getString("remark")=="" || rs.getString("remark")==null){
						vo.setRemark("");
					}else{
						vo.setRemark(rs.getString("remark"));
					}*/
					termList.add(vo);
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

		else if (!val.getTermname().equalsIgnoreCase(""))

		{

			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_TERM_DETAILS);
				pstmt.setString(1, "%"+val.getTermname().trim()+ "%");

				pstmt.setString(2, "%"+val.getTermname()+ "%");

				pstmt.setString(3, "%"+val.getTermname()+ "%");

				pstmt.setString(4, "%"+val.getTermname()+ "%");

				pstmt.setString(5, "%"+val.getTermname()+ "%");

				//System.out.println("pstmt" + pstmt);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					TermMasterVo vo = new TermMasterVo();
					count++;
					vo.setSno(count);
					vo.setTermid(rs.getString("termid"));
					vo.setTermname(rs.getString("termname"));
					vo.setStartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					vo.setEnddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					vo.setAccyear(rs.getString("acadamic_year"));
					vo.setAccid(rs.getString("accyear"));
					termList.add(vo);

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
				+ " Control in TermMasterDAOIMPL : termList Ending");

		return termList;
	}

	public synchronized TermMasterVo edittermDetails(TermMasterVo vo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : edittermDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		TermMasterVo term = null;
		try {

			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_TERM);
			pstmt.setString(1, vo.getTermid());
			System.out.println("EDIT_TERM -->>"+pstmt); 
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				term = new TermMasterVo();

				term.setTermid(rs.getString("termid").trim());
				term.setTermname(rs.getString("termname").trim());
				term.setDescription(rs.getString("description"));
				term.setStartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
				term.setEnddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
				term.setAccyear(rs.getString("accyear"));

				if (rs.getString("isTransportTerm").equalsIgnoreCase("Y")) {
					term.setTransporttype("YES");
				} else if (rs.getString("isTransportTerm")
						.equalsIgnoreCase("N"))

				{
					term.setTransporttype("NO");
				}
               term.setLocationId(rs.getString("locationId"));    
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
				+ " Control in TermMasterDAOIMPL : edittermDetails Ending");
		return term;
	}

	public synchronized String deleteTermDetails(TermMasterVo vo,String button, UserLoggingsPojo userLoggingsVo)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : deleteTermDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status = "";
		int no = 0;
		Connection conn = null;

		try

		{
/*			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<vo.getGetDataArray().length;i++){//-------6
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_FEE_DETAILS);
			pstmt.setString(1, vo.getGetDataArray()[i]);//---------7
			
			count = pstmt.executeUpdate();
			}//-------8
*/			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			for(int i=0;i<vo.getGetDataArray().length;i++){
			pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_TERM_MAP);
			pstmt.setString(1, vo.getGetDataArray()[i]);

			rs = pstmt.executeQuery();
		}
			while (rs.next()) {
				no = rs.getInt(1);
			}

			if (no == 0) {
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_TERM_MAP1);
				pstmt.setString(1, vo.getGetDataArray()[i]);

				rs = pstmt.executeQuery();
			}
				while (rs.next()) {
					no = rs.getInt(1);
				}
			}

			if (no == 0) {
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_TERM);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getRemark());
				pstmt.setString(3, vo.getGetDataArray()[i]);
				no = pstmt.executeUpdate();
				System.out.println(pstmt);
				}
				if (no > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Term Setup",button,pstmt.toString(),userLoggingsVo);
					status = "true";
				}
				else {
					status = "false";
				}
			}
			else
			{
				status = "warning";
			}
		}

		catch (SQLException sqlExp) {
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
				+ " Control in TermMasterDAOIMPL : deleteTermDetails Ending");

		return status;
	}


	
	public synchronized String deleteSeparateTermDetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : deleteSeparateTermDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String status = "";
		int no = 0;
		Connection conn = null;

		try

		{
			
			
/*			conn = JDBCConnection.getSeparateConnection();
			for(int i=0;i<vo.getGetDataArray().length;i++){//-------6
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_FEE_DETAILS);
			pstmt.setString(1, vo.getGetDataArray()[i]);//---------7
			
			count = pstmt.executeUpdate();
			}//-------8
*/			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			for(int i=0;i<vo.getGetDataArray().length;i++){
			pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_SEPARATE_TERM_MAP);
			pstmt.setString(1, vo.getGetDataArray()[i]);

			rs = pstmt.executeQuery();
		}
			while (rs.next()) {

				no = rs.getInt(1);
			}


			if (no == 0) {
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_SEPARATE_TERM_MAP1);
				pstmt.setString(1, vo.getGetDataArray()[i]);

				rs = pstmt.executeQuery();
			}
				while (rs.next()) {

					no = rs.getInt(1);
				}
			}

			if (no == 0) {
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_SEPARATE_TERM);
				pstmt.setString(1, vo.getGetDataArray()[i]);
				no = pstmt.executeUpdate();
				if (no > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","Term Setup","Delete",pstmt.toString(),userLoggingsVo);
				 }
				}
				if (no > 0) {
					status = MessageConstants.TERM_SUCCESS;
				}
				else {
					status = MessageConstants.TERM_ERROR;
				}
			}
			else
			{
				status = MessageConstants.TERM_WARNING;
			}
		}

		catch (SQLException sqlExp) {
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
				+ " Control in TermMasterDAOIMPL : deleteSeparateTermDetails Ending");

		return status;
	}
	
	
	
	public ArrayList<TermMasterVo> sapareteTransportTermList(TermMasterVo val) {
		
	

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in TermMasterDAOIMPL : sapareteTransportTermList Starting");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = null;
			

			ArrayList<TermMasterVo> termList = new ArrayList<TermMasterVo>();

			if (val.getTermname() == null || val.getTermname().equalsIgnoreCase(""))

			{

				try {

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_TERM);
					rs = pstmt.executeQuery();
					while (rs.next())

					{
						TermMasterVo vo = new TermMasterVo();
						vo.setTermid(rs.getString("termid"));
						vo.setTermname(rs.getString("termname"));
						vo.setStartdate(HelperClass.convertDatabaseToUI(rs
								.getString("startdate")));
						vo.setEnddate(HelperClass.convertDatabaseToUI(rs
								.getString("enddate")));
						vo.setAccyear(rs.getString("acadamic_year"));
						vo.setAccid(rs.getString("accyear"));
						termList.add(vo);

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

			else if (!val.getTermname().equalsIgnoreCase(""))

			{

				try {

					conn = JDBCConnection.getSeparateConnection();
					pstmt = conn
							.prepareStatement(SQLUtilConstants.SEARCH_TERM_DETAILS);
					pstmt.setString(1, "%" + val.getTermname() + "%");

					pstmt.setString(2, "%" + val.getTermname() + "%");

					pstmt.setString(3, "%" + val.getTermname() + "%");

					pstmt.setString(4, "%" + val.getTermname() + "%");

					pstmt.setString(5, "%" + val.getTermname() + "%");

					System.out.println("pstmt" + pstmt);
					rs = pstmt.executeQuery();
					while (rs.next())

					{

						TermMasterVo vo = new TermMasterVo();
						vo.setTermid(rs.getString("termid"));
						vo.setTermname(rs.getString("termname"));
						vo.setStartdate(HelperClass.convertDatabaseToUI(rs
								.getString("startdate")));
						vo.setEnddate(HelperClass.convertDatabaseToUI(rs
								.getString("enddate")));
						vo.setAccyear(rs.getString("acadamic_year"));
						vo.setAccid(rs.getString("accyear"));
						termList.add(vo);

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
					+ " Control in TermMasterDAOIMPL : sapareteTransportTermList Ending");

			return termList;
		}

	

	@Override
	public String dateOverLapValidate(String date,String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lastExistingDate=null;
		String no = "";

		Connection conn = null;

		try

		{
			List<String> datevalid=null;
 
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement(SQLUtilConstants.DATE_OVERLAP);
			pstmt.setString(1, accyear);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				no=rs.getString(1);

				System.out.println("hello");
				 datevalid=HelperClass.getDateListBetweenDates(date, no);
				 if(datevalid.size()==0){
				datevalid=HelperClass.getDateListBetweenDates(no,date);
				lastExistingDate="-"+Integer.toString(datevalid.size()-2);
				 }
				 else{
					 lastExistingDate=Integer.toString(datevalid.size());
				 }
			}
		}
		catch (SQLException sqlExp) {
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
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Ending");

		return lastExistingDate;
	}

	
	//SEPARATE_DATE_OVERLAP
	
	
	public String separatedateOverLapValidate(String date, String accyear,
			String locationId,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : separatedateOverLapValidate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lastExistingDate=null;
		String no = "";
		Connection conn = null;

		try
		{
			List<String> datevalid=null;
			conn = JDBCConnection.getSeparateConnection(custdetails
					);
			pstmt=conn.prepareStatement("SELECT enddate FROM campus_fee_transport_termdetails where accyear=? AND locationId=? ORDER BY enddate DESC LIMIT 1");
			pstmt.setString(1, accyear);
			pstmt.setString(2, locationId);
			System.out.println("separatedateOverLapValidate -->>"+pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				no=rs.getString(1);
				 datevalid=HelperClass.getDateListBetweenDates(date, no);
				 if(datevalid.size()==0){
				datevalid=HelperClass.getDateListBetweenDates(no,date);
				lastExistingDate="-"+Integer.toString(datevalid.size()-2);
				 }
				 else{
					 lastExistingDate=Integer.toString(datevalid.size());
				 }
			}
		}
		catch (SQLException sqlExp) {
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
				+ " Control in TermMasterDAOIMPL : separatedateOverLapValidate Ending");

		return lastExistingDate;
	}
	
	public synchronized String addtermSeparatefeedetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : addtermSeparatefeedetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;

		try {

			String str = IDGenerator.getPrimaryKeyID("campus_fee_transport_termdetails",userLoggingsVo);

			String[] part =str.split("(?<=\\D)(?=\\d)");

			String ternorder = part[1];

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);

			pstmt = conn.prepareStatement(SQLUtilConstants.ADD_SEPARATE_TERM);
			pstmt.setString(1, IDGenerator.getPrimaryKeyID("campus_fee_transport_termdetails",userLoggingsVo));
			pstmt.setString(2, vo.getTermname());
			pstmt.setString(3, HelperClass.convertUIToDatabase(vo.getStartdate()));
			pstmt.setString(4, HelperClass.convertUIToDatabase(vo.getEnddate()));
			pstmt.setString(5, vo.getAccyear());
			pstmt.setString(6, vo.getDescription());
			pstmt.setString(7, ternorder);
			pstmt.setString(8, vo.getTransporttype());
			pstmt.setString(9, vo.getCreateuser());
			pstmt.setTimestamp(10, HelperClass.getCurrentTimestamp());
			pstmt.setString(11,vo.getLocationId());
			pstmt.setString(12,vo.getNoofmonths());
			result1 = pstmt.executeUpdate();

			if (result1 == 1) {
				HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","Term SetUp","Insert",pstmt.toString(),userLoggingsVo);
				result_Status = MessageConstants.SuccesstermMsg;
			} else {
				result_Status = MessageConstants.ErrortermMsg;
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
				+ " Control in TermMasterDAOIMPL : addtermSeparatefeedetails Ending");

		return result_Status;

	}
	

	public synchronized ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo val,UserLoggingsPojo custdetails)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : separateTransportTermList Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
	

		String locationId=val.getLocationId();
		if(locationId==null){
			locationId="%%";
		}
		ArrayList<TermMasterVo> termList = new ArrayList<TermMasterVo>();

		if (val.getTermname() == null || val.getTermname().equalsIgnoreCase(""))

		{

			try {

				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEPARATE_TERM);
				pstmt.setString(1, val.getAccyear());
				pstmt.setString(2,locationId);
				rs = pstmt.executeQuery();
				while (rs.next())

				{
					TermMasterVo vo = new TermMasterVo();
					count++;
					vo.setSno(count);
					vo.setTermid(rs.getString("termid"));
					vo.setTermname(rs.getString("termname"));
					vo.setStartdate(HelperClass.convertDatabaseToUI(rs.getString("startdate")));
					vo.setEnddate(HelperClass.convertDatabaseToUI(rs.getString("enddate")));
					vo.setAccyear(rs.getString("acadamic_year"));
					vo.setAccid(rs.getString("accyear"));
					vo.setLocationName(rs.getString("Location_Name"));
					termList.add(vo);
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

		else if (!val.getTermname().equalsIgnoreCase(""))

		{

			try {

				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn
						.prepareStatement(SQLUtilConstants.SEARCH_SEPARATE_TRANSPORT_TERM_DETAILS);
				pstmt.setString(1, "%" + val.getTermname() + "%");

				pstmt.setString(2, "%" + val.getTermname() + "%");

				pstmt.setString(3, "%" + val.getTermname() + "%");

				pstmt.setString(4, "%" + val.getTermname() + "%");

				pstmt.setString(5, "%" + val.getTermname() + "%");

				System.out.println("pstmt" + pstmt);
				rs = pstmt.executeQuery();
				while (rs.next())

				{

					TermMasterVo vo = new TermMasterVo();

					count++;
					vo.setSno(count);
					vo.setTermid(rs.getString("termid"));
					vo.setTermname(rs.getString("termname"));
					vo.setStartdate(HelperClass.convertDatabaseToUI(rs
							.getString("startdate")));
					vo.setEnddate(HelperClass.convertDatabaseToUI(rs
							.getString("enddate")));
					vo.setAccyear(rs.getString("acadamic_year"));
					vo.setAccid(rs.getString("accyear"));
					termList.add(vo);

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
				+ " Control in TermMasterDAOIMPL : separateTransportTermList Ending");

		return termList;
	}
	
	
	public synchronized boolean getTermnamecount(TermMasterVo vo,UserLoggingsPojo userLoggingsVo)

	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getTermnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;

		if (vo.getTermid().equalsIgnoreCase(""))

		{

			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.SEPARATE_TERM_COUNT);
				pstmt.setString(1, vo.getTermname().trim());
				pstmt.setString(2, vo.getAccyear());
				pstmt.setString(3, vo.getLocationId());

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

		else if (!vo.getTermid().equalsIgnoreCase(""))

		{

			try {

				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_SEPARATE_TERM_COUNT);
				pstmt.setString(1, vo.getTermid().trim());
				pstmt.setString(2, vo.getTermname().trim());
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

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getTermnamecount Ending");

		return result;
	}

	

	@Override
	public List<TermMasterVo> getTermDetails(String academic_year,String location,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : getTermDetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		List<TermMasterVo> list = new ArrayList<TermMasterVo>();
		
		try{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("select termid,termname from campus_fee_transport_termdetails where accyear = ? and locationId = ? order by startdate");
			pstmt.setString(1,academic_year);
			pstmt.setString(2,location);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TermMasterVo vo = new TermMasterVo();
				vo.setTermid(rs.getString("termid"));
				vo.setTermname(rs.getString("termname"));
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
				+ " Control in TermMasterDAOIMPL : getTermDetails Ending");
		return list;
	}





	@Override
	public String dateOverLapValidate(String date, String accyear,
			String locationId, TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lastExistingDate=null;
		String no = "";
		Connection conn = null;

		try

		{
			
			List<String> datevalid=null;
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt=conn.prepareStatement("SELECT enddate FROM campus_fee_termdetails where accyear=? AND locationId=? ORDER BY enddate DESC LIMIT 1");
			pstmt.setString(1, accyear);
			pstmt.setString(2, locationId);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				no=rs.getString(1);

				System.out.println("hello");
				 datevalid=HelperClass.getDateListBetweenDates(date, no);
				 if(datevalid.size()==0){
				datevalid=HelperClass.getDateListBetweenDates(no,date);
				lastExistingDate="-"+Integer.toString(datevalid.size()-2);
				 }
				 else{
					 lastExistingDate=Integer.toString(datevalid.size());
				 }

			}
			
			
		}

		catch (SQLException sqlExp) {
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
				+ " Control in TermMasterDAOIMPL : dateOverLapValidate Ending");

		return lastExistingDate;
	}

	public String deleteTermDetailsByOrder(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterDAOIMPL : deleteTermDetailsByOrder Starting");

		PreparedStatement pstmt = null,pstmt1= null,pstmt2= null;
		ResultSet rs = null,rs1= null;
		String status = "";
		int no = 0;
		Connection conn = null;
		try
		{
 			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			
			for(int i=0;i<vo.getGetDataArray().length;i++){
			pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_TERM_MAP);
			pstmt.setString(1, vo.getGetDataArray()[i]);

			rs = pstmt.executeQuery();
		}
			while (rs.next()) {
				no = rs.getInt(1);
			}
			if (no == 0) {
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt1 = conn.prepareStatement(SQLUtilConstants.CHECK_TERM_MAP1);
				pstmt1.setString(1, vo.getGetDataArray()[i]);

				rs1 = pstmt1.executeQuery();
			}
				while (rs1.next()) {
					no = rs1.getInt(1);
				}
			}

			if (no == 0) {
				for(int i=0;i<vo.getGetDataArray().length;i++){
				pstmt2 = conn.prepareStatement("delete from campus_fee_termdetails where termid=?");
				pstmt2.setString(1, vo.getGetDataArray()[i]);
				no = pstmt2.executeUpdate();
				}
				if (no > 0) {
					HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Term Setup","Delete",pstmt2.toString(),userLoggingsVo);
					status = "true";
				}
				else {
					status ="false";
				}
			}
			else
			{
				status = "mapped";
			}

		}

		catch (SQLException sqlExp) {
			logger.error(sqlExp.getMessage(), sqlExp);
			sqlExp.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {

			try {
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (rs1 != null && !rs1.isClosed()) {
					rs1.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
				}
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
				+ " Control in TermMasterDAOIMPL : deleteTermDetailsByOrder Ending");

		return status;
	
	}

}

