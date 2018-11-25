package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AddDesignationDao;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddDesignationVO;

public class AddDesignationDaoImpl implements AddDesignationDao {

	private static Logger logger = Logger
			.getLogger(AddDesignationDaoImpl.class);
	
	
	
	public synchronized ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo, UserLoggingsPojo custdetails) 
	
	{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: DesignationDetails : Starting");
		PreparedStatement designation_pstmt = null;
		ResultSet designation_rs = null;

		ArrayList<AddDesignationVO> list = new ArrayList<AddDesignationVO>();

		Connection conn = null;
		
		if (vo.getDesgname()==null || vo.getDesgname().equalsIgnoreCase(""))
			
		{
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			designation_pstmt = conn.prepareStatement(SQLUtilConstants.GET_DESIGNATION_DETAILS);
            //("GET_DESIGNATION_DETAILS -->>"+designation_pstmt);
			designation_rs = designation_pstmt.executeQuery();
			
				while (designation_rs.next()) {
				AddDesignationVO addDesignationVO = new AddDesignationVO();
				
				addDesignationVO.setDesgid(designation_rs.getString("DesignationCode").trim());
				addDesignationVO.setDesgname(designation_rs.getString("designationName").trim());
				addDesignationVO.setDesgdes(designation_rs.getString("description").trim());
				addDesignationVO.setCreatedate(designation_rs.getString("createdate").trim());
				addDesignationVO.setCreateuser(designation_rs.getString("CreatedBy").trim());
				addDesignationVO.setRemarks(designation_rs.getString("remarks"));
				
				list.add(addDesignationVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (designation_rs != null && (!designation_rs.isClosed())) {
					designation_rs.close();
				}
				if (designation_pstmt != null && (!designation_pstmt.isClosed())) {
					designation_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException sqle) {

				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
			} catch (Exception e1) {

				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		}
		
		
		else if (!vo.getDesgname().equalsIgnoreCase(""))
			
		{
			
			
			try {
				conn = JDBCConnection.getSeparateConnection(custdetails);

				designation_pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_DETAILS);

						
				designation_pstmt.setString(1,vo.getDesgname().trim()+"%");	
				
				designation_pstmt.setString(2,vo.getDesgname().trim()+"%");				



				designation_rs = designation_pstmt.executeQuery();
				

				while (designation_rs.next())

				{
					AddDesignationVO addDesignationVO = new AddDesignationVO();
					
					addDesignationVO.setDesgid(designation_rs.getString("DesignationCode"));
					addDesignationVO.setDesgname(designation_rs.getString("designationName"));
					addDesignationVO.setDesgdes(designation_rs.getString("description"));
					list.add(addDesignationVO);
					
				}

			}
			catch (SQLException e)

			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			finally {
				try {
					if (designation_rs != null && (!designation_rs.isClosed())) {
						designation_rs.close();
					}
					if (designation_pstmt != null && (!designation_pstmt.isClosed())) {
						designation_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: DesignationDetails : Ending");
		return list;
	
	}
	
	
public String updateDesignationDetails(AddDesignationPojo apojo, UserLoggingsPojo custdetails) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDesignationDaoImpl:updateDesignationDetails:Starting");

	PreparedStatement designation_pstmt = null;
	
	String status = null;

	Connection conn = null;
	
	int result1=0;
	
	try {
		
		conn = JDBCConnection.getSeparateConnection(custdetails);
		
		designation_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_DES_DETAILS);
		
		designation_pstmt.setString(1, apojo.getDesgname().trim());
		designation_pstmt.setString(2, apojo.getDesgdes().trim());
		designation_pstmt.setString(3, apojo.getCustid());
		designation_pstmt.setString(4, apojo.getDesgid().trim());
		
		//("UPDATE_DES_DETAILS:;"+designation_pstmt);
		
		result1 = designation_pstmt.executeUpdate();

	
		if (result1 > 0) {
			HelperClass.recordLog_Activity(apojo.getLog_audit_session(),"Settings","Designation","Update",designation_pstmt.toString(),custdetails);
			status ="Designation Update Successfully";
			
		} else {
			status = MessageConstants.UPDATE_DESIGNATION_FAIL;
			
		}
	} catch (Exception exception) {
		logger.error(exception.getMessage(), exception);
		exception.printStackTrace();
	} finally {
		try {

			if (designation_pstmt != null && !designation_pstmt.isClosed()) {
				designation_pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in AddDesignationDaoImpl: updateDesignationDetails : Ending");
	
		
		return status;
	} 
	
	
public synchronized String insertDesignationDetails(AddDesignationPojo apojo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl:insertDesignationDetails:Starting");

		PreparedStatement designation_pstmt = null;
		String status = null;
		Connection conn = null;
		int result1=0;
		try 
		{
			        conn = JDBCConnection.getSeparateConnection(custdetails);
				    designation_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_DESIGNATION_DETAILS);
					designation_pstmt.setString(1, apojo.getDesignationcode());
					designation_pstmt.setString(2, apojo.getDesgname());
					designation_pstmt.setString(3, apojo.getDesgdes());
					designation_pstmt.setString(4, apojo.getCreateuser());
					designation_pstmt.setTimestamp(5, HelperClass.getCurrentTimestamp());
					
					//("INSERT_DESIGNATION::"+designation_pstmt);
					
					result1 = designation_pstmt.executeUpdate();

					if (result1 > 0) {
						HelperClass.recordLog_Activity(apojo.getLog_audit_session(),"Settings","Designation","Insert",designation_pstmt.toString(),custdetails);
						status = "Designation Added Successfully"; 

					} else {
						status = MessageConstants.ADD_DESIGNATION_FAIL; 
					}
				}

			 catch (SQLException sqle) {

				sqle.printStackTrace();
				logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			} finally {
				try {
					if (designation_pstmt != null
							&& (!designation_pstmt.isClosed())) {
						designation_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
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
				+ " Control in AddDesignationDaoImpl:insertDesignationDetails: Ending");

		
	
		return status;
	}
	
	public synchronized AddDesignation  EditDesignationDetails(AddDesignation aform, UserLoggingsPojo pojo) 
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl:EditDesignationDetails:Starting");
		
			
		PreparedStatement designation_pstmt = null;
		
		ResultSet designation_rs = null;
		

		Connection conn = null;
		AddDesignation addDesignationVO =null;
		
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);
			designation_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_DESIGNATION);

			designation_pstmt.setString(1,aform.getDesignationid());

			designation_rs = designation_pstmt.executeQuery();

			while (designation_rs.next())
			{
				addDesignationVO = new AddDesignation();

				addDesignationVO.setDesignationid(designation_rs.getString("DesignationCode"));
				addDesignationVO.setDesignation_name(designation_rs.getString("designationName"));
				addDesignationVO.setDesignation_description(designation_rs.getString("description"));
				addDesignationVO.setCreateddate(designation_rs.getString("createdate"));
				addDesignationVO.setCreatedby(designation_rs.getString("CreatedBy"));
				
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
				if (designation_rs != null && (!designation_rs.isClosed())) {
					designation_rs.close();
				}
				if (designation_pstmt != null
						&& (!designation_pstmt.isClosed())) {
					designation_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
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
				+ " Control in AddDesignationDaoImpl:EditDesignationDetails: Ending");
		
		return addDesignationVO;
		
		}
	
	public synchronized String deleteDesignationDetails(String[] designation_code,
			String custid, String inactiveReason, String otherReason, String activestatus, String activeReason, String log_audit_session, UserLoggingsPojo pojo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: deleteDesignationDetails : Starting");

		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null,pstmt2 = null;
		
		int no = 0;
		String status = null,value=null;

		Connection conn = null;
		try
		{
			for(int i=0;i<designation_code.length;i++){
			conn = JDBCConnection.getSeparateConnection(pojo);
			pstmt1 = conn.prepareStatement(SQLUtilConstants.CHECK_DESIGNATION_MAP);
			pstmt1.setString(1, designation_code[i]);
			rs1=pstmt1.executeQuery();
			
			while(rs1.next())
			{
				no=rs1.getInt(1);
			}
			
			if(no==0)
			{
				pstmt2 = conn.prepareStatement(SQLUtilConstants.DELETE_DESIGNATION);
				
				 if(activestatus.equalsIgnoreCase("InActive")){
					 pstmt2.setString(1, "N");
					 if(otherReason=="" || otherReason==null){
						 pstmt2.setString(2, inactiveReason); 
					 }
					 else{
						 pstmt2.setString(2, otherReason);
					 }
					 value="InActive";
				 }
				 else{
					 pstmt2.setString(1, "Y");
					 if(otherReason=="" || otherReason==null){
						 pstmt2.setString(2, activeReason); 
					 }
					 else{
						 pstmt2.setString(2, otherReason);
					 }
					 value="Active";
				 }
				
				pstmt2.setString(3, designation_code[i]);
				no = pstmt2.executeUpdate();
				
				//("DELETE_DESIGNATION -->>"+pstmt2);
				
				if(no>0)
				{
					status="true";
					HelperClass.recordLog_Activity(log_audit_session,"Settings","Designation Details",value,pstmt2.toString(),pojo);
				}
				else
				{
					status="error";
				}
			}
			else
			{
				status="warning";
			}
		  }
		}
		  catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {

			try {
				if (rs1 != null && (!rs1.isClosed())) {
					rs1.close();
				}
				if (pstmt2 != null && (!pstmt2.isClosed())) {
					pstmt2.close();
				}
				if (pstmt1 != null && (!pstmt1.isClosed())) {
					pstmt1.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			}  catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl: deleteDesignationDetails : Ending");
		
		return status;
	
	}

	public synchronized ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getSearchDetails Starting");

		ArrayList<AddDesignationVO> bat_Details = new ArrayList<AddDesignationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SEARCH_DETAILS);
			pstmt.setString(1,"%"+searchTextVal+"%");
			pstmt.setString(2,"%"+searchTextVal+"%");
			rs = pstmt.executeQuery();

			while (rs.next())

			{
				AddDesignationVO bat = new AddDesignationVO();

				bat.setDesgid(rs.getString("DesignationCode"));

				bat.setDesgname(rs.getString("designationName"));

				bat.setDesgdes(rs.getString("description"));
				
				bat_Details.add(bat);

			}

		}
		catch (SQLException e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
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
				+ " Control in AddDesignationDaoImpl : getSearchDetails Ending");

		return bat_Details;
	
	}

	public String getnamecount(AddDesignationVO vo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getnamecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		String status=null,result = null;
		 
		if (vo.getDesgid().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_DESG_COUNT);
				pstmt.setString(1, vo.getDesgname().trim());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					status= rs.getString(2);
				}

				if (count > 0 && status.equalsIgnoreCase("Y"))
				{
					result = "1";
				}
				else if (count > 0 && status.equalsIgnoreCase("N"))
				{
					result = "10";
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

		if (!vo.getDesgid().equalsIgnoreCase(""))

		{

			try {
				conn = JDBCConnection.getSeparateConnection(pojo);
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_DESG_COUNT);
				pstmt.setString(1, vo.getDesgid().trim());
				pstmt.setString(2, vo.getDesgname().trim());
				
				//("EDIT_DESG_COUNT-->>"+pstmt);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
					status=rs.getString(2);
				}

				if (count > 0 && status.equalsIgnoreCase("Y"))
				{
					result = "1";
				}
				else if (count > 0 && status.equalsIgnoreCase("N"))
				{
					result = "10";
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
				+ " Control in AddDesignationDaoImpl : getnamecount Ending");
		return result;

	
		
		
	}



	public ArrayList<AddDesignationVO> DesignationDetails() {
	
		return null;
	}


	public ArrayList<AddDesignationVO> InActiveDesignationList(AddDesignationVO vo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getSearchDetails Starting");

		ArrayList<AddDesignationVO> bat_Details = new ArrayList<AddDesignationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection(pojo);

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_DESIGNATION_DETAILS_BY_STATUS_SEARCH);
			pstmt.setString(1,"%"+vo.getSearch()+"%");
			pstmt.setString(2,"%"+vo.getSearch()+"%");
			pstmt.setString(3,"%"+vo.getSearch()+"%");
			pstmt.setString(4,vo.getStatus());
			
			//("GET_DESIGNATION_DETAILS_BY_STATUS_SEARCH -->>"+pstmt);
			rs = pstmt.executeQuery();

			while (rs.next())

			{
				AddDesignationVO bat = new AddDesignationVO();
				bat.setDesgid(rs.getString("DesignationCode"));
				bat.setDesgname(rs.getString("designationName"));
				bat.setDesgdes(rs.getString("description"));
				if(rs.getString("remarks")=="" || rs.getString("remarks")==null){
					bat.setRemarks("");
				}
				else{
					bat.setRemarks(rs.getString("remarks"));
				}
				
				bat_Details.add(bat);

			}

		}
		catch (SQLException e)

		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
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
				+ " Control in AddDesignationDaoImpl : getSearchDetails Ending");

		return bat_Details;
	
	}


	public ArrayList<AddDesignationVO> getdesignationList(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationDaoImpl : getdesignationList Starting");

		ArrayList<AddDesignationVO> bat_Details = new ArrayList<AddDesignationVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_DESIGNATION_LIST);
			rs = pstmt.executeQuery();

			while (rs.next())

			{
				AddDesignationVO bat = new AddDesignationVO();
				bat.setDesgid(rs.getString("DesignationCode"));
				bat.setDesgname(rs.getString("designationName"));
				
				bat_Details.add(bat);

			}

		
		}

		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		finally {
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
				+ " Control in AddDesignationDaoImpl : getdesignationList Ending");

		return bat_Details;
	}

	
}

	

