package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.StageDAO;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddStageVO;

public class StageDAOIMPL implements StageDAO
{
	private static final Logger logger = Logger.getLogger(StageDAOIMPL.class);

	
	
	public synchronized ArrayList<AddStageVO> StageDetails(UserLoggingsPojo userLoggingsVo)
	{

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Starting");
		PreparedStatement stage_pstmt = null;
		
		ResultSet stage_rs = null;

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();

		Connection conn = null;
		
		
		try {

			/*System.out.println("if "+vo.getStageName());*/

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_STAGEMASTER_DETAILS);
			stage_rs = stage_pstmt.executeQuery();

			while (stage_rs.next()) {
				AddStageVO addstageVO = new AddStageVO();

				addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
				addstageVO.setStageName(stage_rs.getString("stage_name").trim());
				//addstageVO.setAmount(stage_rs.getString("amount").trim());
				addstageVO.setDescription(stage_rs.getString("description").trim());

				list.add(addstageVO);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null
						&& (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
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
	

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Ending");
		return list;
		
		}

	public synchronized String insertstage(AddStagePojo apojo, UserLoggingsPojo custdetails) 
		
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:insertstage:Starting");

		PreparedStatement stage_pstmt = null,pstmt=null;
		ResultSet rs=null;
		String status = null;
        int stageId=0;
		Connection conn = null;
		
		int result1=0;
		
		try 
		{
			conn = JDBCConnection.getSeparateConnection(custdetails);
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.INSERT_STAGE_DETAILS,Statement.RETURN_GENERATED_KEYS);
			stage_pstmt.setString(1, apojo.getStagecode());
			stage_pstmt.setString(2, apojo.getStage_name());
			/*stage_pstmt.setString(3, apojo.getAmount());*/
			stage_pstmt.setString(3, apojo.getStage_description());
			stage_pstmt.setString(4, apojo.getCreateuser());
			stage_pstmt.setString(5, apojo.getAccYearId());
			stage_pstmt.setString(6, apojo.getLocId());
		
		    result1 = stage_pstmt.executeUpdate();
			
		    rs=stage_pstmt.getGeneratedKeys();
		    
		    while(rs.next()){
				stageId=rs.getInt(1);
			}
		    
		    pstmt=conn.prepareStatement("INSERT INTO `campus_fee_stage_amount`(`stageId`,`accyearId`,`locationId`,`stageamount`,`createdby`,createdtime) VALUES(?,?,?,?,?,now())");
		    pstmt.setInt(1, stageId);
		    pstmt.setString(2, apojo.getAccYearId());
		    pstmt.setString(3, apojo.getLocId()); 
		    pstmt.setInt(4, 0);
		    pstmt.setString(5, apojo.getCreateuser());
		    int value=pstmt.executeUpdate();
		     
		    if (value > 0) {
				HelperClass.recordLog_Activity(apojo.getLog_audit_session(),"Transport","Stage Wise Amount Setup Master","Insert",pstmt.toString(),custdetails);
		    }
		    
			if (result1 > 0) {
				HelperClass.recordLog_Activity(apojo.getLog_audit_session(),"Transport","Stage Master","Insert",stage_pstmt.toString(),custdetails);
				status = MessageConstants.ADD_STAGE_SUCCESS; 
			} else {
				status = MessageConstants.ADD_STAGE_FAIL; 
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
					if (rs != null && (!rs.isClosed())) {
						rs.close();
					}
					if (pstmt != null && (!pstmt.isClosed())) {
						pstmt.close();
					}
					
					if (stage_pstmt != null && (!stage_pstmt.isClosed())) {
						stage_pstmt.close();
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
				+ " Control in StageDAOIMPL:insertstage: Ending");
	
		return status;
	}
	
	public synchronized String updatestage(AddStagePojo apojo, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:updatestage:Starting");

		PreparedStatement stage_pstmt = null;
		String status = null;
		Connection conn = null;
		int result1=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_STAGE_DETAILS);
			stage_pstmt.setString(1, apojo.getStage_name().trim());
			/*stage_pstmt.setString(2, apojo.getAmount().trim());*/
			stage_pstmt.setString(2, apojo.getStage_description().trim());
			stage_pstmt.setString(3, apojo.getCreateuser().trim());
			stage_pstmt.setTimestamp(4, HelperClass.getCurrentTimestamp());
			stage_pstmt.setString(5, apojo.getLocId());
			stage_pstmt.setString(6, apojo.getStageid().trim());
			/* HelperClass.getCurrentTimestamp()*/
			
			result1 = stage_pstmt.executeUpdate();
			if (result1 > 0) {
				 HelperClass.recordLog_Activity(apojo.getLog_audit_session(),"Transport","Stage Master","Update",stage_pstmt.toString(),custdetails);
				status = MessageConstants.UPDATE_STAGE_SUCCESS;
			} else {
				status = MessageConstants.UPDATE_STAGE_FAIL;
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		} finally {
			try {
				if (stage_pstmt != null && !stage_pstmt.isClosed()) {
					stage_pstmt.close();
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
				+ " Control in StageDAOIMPL:updatestage: Ending");
			return status;
		}

	public synchronized AddStageForm EditStageDetails(AddStageForm aform,UserLoggingsPojo userLoggingsVo)
	{
		{

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StageDAOIMPL:EditStageDetails:Starting");

			PreparedStatement stage_pstmt = null;
			ResultSet stage_rs = null;
			Connection conn = null;
			AddStageForm addDesignationVO =null;
			
			try
			{
				conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
				stage_pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_STAGE);
				stage_pstmt.setString(1,aform.getStageid());
				stage_rs = stage_pstmt.executeQuery();

				while (stage_rs.next())
				{
					addDesignationVO = new AddStageForm();
					addDesignationVO.setStageid(stage_rs.getString("stage_id"));
					addDesignationVO.setStage_name(stage_rs.getString("stage_name"));
					addDesignationVO.setAmount(stage_rs.getString("amount"));
					addDesignationVO.setStage_description(stage_rs.getString("description"));
					addDesignationVO.setCreateddate(stage_rs.getString("createdtime"));
					addDesignationVO.setCreatedby(stage_rs.getString("createdby"));
				}
			} 
			  catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			finally {
				try {
					if (stage_rs != null && (!stage_rs.isClosed())) {
						stage_rs.close();
					}
					if (stage_pstmt != null
							&& (!stage_pstmt.isClosed())) {
						stage_pstmt.close();
					}
					if (conn != null && (!conn.isClosed())) {
						conn.close();
					}
				}  
				catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
					e1.printStackTrace();
				}
			}
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StageDAOIMPL:EditStageDetails: Ending");
			return addDesignationVO;
			
			}
		}

	public synchronized String deleteStage(String[] stageid,String log_audit_session,UserLoggingsPojo userLoggingsVo) 
	
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL:deleteStage : Starting");

		ResultSet stage_rs = null;
		ResultSet rs_deletestage = null;
		PreparedStatement deletestage_pstmt = null;
		PreparedStatement ps_deletestage = null;
		int no = 0;
		String status = null;

		Connection conn = null;
		try
		{
			
			for(int i=0;i<stageid.length;i++){
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			deletestage_pstmt = conn.prepareStatement(SQLUtilConstants.CHECK_STAGE_MAP);
		
			deletestage_pstmt.setString(1, stageid[i]);
			
			System.out.println("CHECK_STAGE_MAP::"+deletestage_pstmt);
			
			stage_rs=deletestage_pstmt.executeQuery();
			
			while(stage_rs.next())
			{
				no=stage_rs.getInt(1);
			}
			
			if(no == 0)
			{
				ps_deletestage = conn.prepareStatement(SQLUtilConstants.DELETE_STAGEMASTER);
				ps_deletestage.setString(1, stageid[i]);
				
				System.out.println("DELETE_STAGEMASTER:: "+ps_deletestage);
				
				no = ps_deletestage.executeUpdate();
				
				if(no>0)
				{
					HelperClass.recordLog_Activity(log_audit_session,"Transport","Stage Master","Delete",ps_deletestage.toString(),userLoggingsVo);
					status=MessageConstants.STAGE_SUCCESS;
				}
				else
				{
					status=MessageConstants.STAGE_ERROR;
				}
			}
			else
			{
				status=MessageConstants.STAGE_WARNING;
			}
		}
	 }
			
			 catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {

			try {

				if (rs_deletestage != null
						&& (!rs_deletestage.isClosed())) {
					rs_deletestage.close();
				}
				if (rs_deletestage != null && (!rs_deletestage.isClosed())) {

					rs_deletestage.close();
				}
				if (ps_deletestage != null
						&& (!ps_deletestage.isClosed())) {

					ps_deletestage.close();
				}
				if (deletestage_pstmt != null
						&& (!deletestage_pstmt.isClosed())) {
					deletestage_pstmt.close();
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
				+ " Control in StageDAOIMPL: deleteStage : Ending");
		
		return status;
	
	}

	
	

	public boolean getstagecount(AddStageVO vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL :  getstagecount Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		if (vo.getStageCode().equalsIgnoreCase(""))
			
		{
			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_STAGE_COUNT);
				pstmt.setString(1, vo.getStageName().trim());
				
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

		if (!vo.getStageCode().equalsIgnoreCase(""))

		{

			

			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_STAGE_COUNT);
				pstmt.setString(1, vo.getStageCode().trim());
				pstmt.setString(2, vo.getStageName().trim());
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
				+ " Control in StageDAOIMPL :  getstagecount Ending");
		return result;

	
	}

	public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: SelectAllSatges : Starting");
		PreparedStatement stage_pstmt = null;
		
		ResultSet stage_rs = null;

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();

		Connection conn = null;
		try {
			 conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			 stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_ALL_STAGE_DETAILS);
			 stage_pstmt.setString(1, vo.getLocId());
			 System.out.println("GET_ALL_STAGE_DETAILS -->>"+stage_pstmt);
			 stage_rs = stage_pstmt.executeQuery();
				while (stage_rs.next()) {
					AddStageVO addstageVO = new AddStageVO();
				
					addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
					addstageVO.setStageName(stage_rs.getString("stage_name").trim());
		
				list.add(addstageVO);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null && (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: SelectAllSatges : Ending");
		return list;
		
		
	}

	public List<AddStageVO> searchStage(String searchName, UserLoggingsPojo custdetails, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL : searchStage Starting");

		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();
		PreparedStatement pstmt = null;
		ResultSet stage_rs = null;
		Connection conn = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_STAGE_DETAILS);

			pstmt.setString(1, loc);
			pstmt.setString(2, "%" + searchName + "%");
			pstmt.setString(3, "%" + searchName + "%");
			pstmt.setString(4, "%" + searchName + "%");
			stage_rs = pstmt.executeQuery();

			while (stage_rs.next()) {
				sno++;
				
				AddStageVO searchvo = new AddStageVO();
				searchvo.setSno(String.valueOf(sno));
				searchvo.setStageCode(stage_rs.getString("stage_id"));
				searchvo.setStageName(stage_rs.getString("stage_name"));
				searchvo.setAmount(stage_rs.getString("amount"));
				searchvo.setDescription(stage_rs.getString("description"));
				list.add(searchvo);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
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
				+ " Control in StageDAOIMPL : searchStage  Ending");

		return list;
	
	}

	@Override
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AddStageVO> StageDetailsList(String loc, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Starting");
		PreparedStatement stage_pstmt = null;
		ResultSet stage_rs = null;
		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_STAGE_LIST_DETAILS);
			stage_pstmt.setString(1, loc.split("-")[1]);
			stage_pstmt.setString(2, loc.split("-")[0]);
			System.out.println("GET_STAGE_LIST_DETAILS -->>"+stage_pstmt);
			stage_rs = stage_pstmt.executeQuery();
			while (stage_rs.next()) {
				AddStageVO addstageVO = new AddStageVO();
				addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
				addstageVO.setStageName(stage_rs.getString("stage_name").trim());
				addstageVO.setAmount(stage_rs.getString("stageamount"));
				addstageVO.setDescription(stage_rs.getString("description").trim());
				addstageVO.setRemarks(stage_rs.getString("remarks"));
				list.add(addstageVO);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null
						&& (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
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
	

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Ending");
		return list;
		
		}

	public String validateStage(AddStageVO vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Starting");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String status=null,value=null;
		int count=0;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement("SELECT COUNT(*),isActive FROM campus_fee_stage WHERE stage_name=? and location=? AND stage_id <> ?");
			pstmt.setString(1, vo.getStageName());
			pstmt.setString(2, vo.getLocId());
			pstmt.setString(3, vo.getStage_id());
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				count=rs.getInt(1);
				value=rs.getString(2);
			}
			if(count>0 && value.equalsIgnoreCase("Y")){
				status="duplicate";
			}
			else if(count>0 && value.equalsIgnoreCase("N")){
				status="duplicateinactive";
			}
			else{
				status="noduplicate";
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e1) {

			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && (!rs.isClosed())) {
					rs.close();
				}
				if (pstmt != null&& (!pstmt.isClosed())) {
					pstmt.close();
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

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: StageDetails : Ending");
		return status;
		
		}

	public List<AddStageVO> stagedetailsListByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: stagedetailsListByStatus : Starting");
		PreparedStatement stage_pstmt = null;
		ResultSet stage_rs = null;
		ArrayList<AddStageVO> list = new ArrayList<AddStageVO>();
		Connection conn = null;
		try {
			conn = JDBCConnection.getSeparateConnection(custdetails);
			stage_pstmt = conn.prepareStatement(SQLUtilConstants.GET_STAGE_LIST_DETAILS_BY_STATUS);
			stage_pstmt.setString(1, vo.getAccyearCode());
			stage_pstmt.setString(2, vo.getLocId());
			stage_pstmt.setString(3, vo.getStatus());
			stage_pstmt.setString(4, vo.getSearchName()+"%");
			stage_pstmt.setString(5, vo.getSearchName()+"%");
			stage_pstmt.setString(6, vo.getSearchName()+"%");
			stage_pstmt.setString(7, vo.getSearchName()+"%");
			
			System.out.println("GET_STAGE_LIST_DETAILS_BY_STATUS -->>"+stage_pstmt);
			stage_rs = stage_pstmt.executeQuery();
			while (stage_rs.next()) {
				AddStageVO addstageVO = new AddStageVO();
				addstageVO.setStageCode(stage_rs.getString("stage_id").trim());
				addstageVO.setStageName(stage_rs.getString("stage_name").trim());
				addstageVO.setAmount(stage_rs.getString("stageamount"));
				addstageVO.setDescription(stage_rs.getString("description").trim()); 
				addstageVO.setRemarks(stage_rs.getString("remarks"));
				list.add(addstageVO);
			}
		} 
		   catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} finally {
			try {
				if (stage_rs != null && (!stage_rs.isClosed())) {
					stage_rs.close();
				}
				if (stage_pstmt != null
						&& (!stage_pstmt.isClosed())) {
					stage_pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} 
			  catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: stagedetailsListByStatus : Ending");
		return list;
     }

	public String deleteStageByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageDAOIMPL: deleteStageByStatus : Starting");

		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs1 =null;
		String status = null,value=null;
		int count=0,count1=0,unmappedcnt=0,resultst=0;
		Connection conn = null;
		try {
			
			 conn = JDBCConnection.getSeparateConnection(custdetails);
			 pstmt = conn.prepareStatement("UPDATE campus_fee_stage SET isActive=?,remarks=?,present_status=? WHERE  stage_id=?");
			
			 if(vo.getStatus().equalsIgnoreCase("InActive")){
			    for(int i=0;i<vo.getStageId().length;i++){
				 
				 pstmt1 = conn.prepareStatement("SELECT COUNT(cst.StageId) FROM  campus_tranport_fee_collection_details ctfcd JOIN campus_student_transportdetails cst ON cst.student_id_int=ctfcd.admissionNo AND cst.fms_acadamicyear_id_int=ctfcd.accYear AND cst.locationId=ctfcd.locId WHERE ctfcd.locId=? AND cst.StageId=? AND cst.isTransport='Y' AND ctfcd.is_paid='Y'");
				 pstmt1.setString(1, vo.getLocId());
				 pstmt1.setString(2, vo.getStageId()[i]);
				  rs1 = pstmt1.executeQuery();
					while(rs1.next()){
						count1 = rs1.getInt(1);
					}
				 
			    if(count1==0){
				   unmappedcnt ++;	
				   if(vo.getStatus().equalsIgnoreCase("InActive")){
					 pstmt.setString(1, "N");
					 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
						 pstmt.setString(2, vo.getInactiveReason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherReason());
					 }
					 pstmt.setString(3, "Inactive");
					 value="InActive";
				  }
				  else{
					 pstmt.setString(1, "Y");
					 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
						 pstmt.setString(2, vo.getActiveReason()); 
					 }
					 else{
						 pstmt.setString(2, vo.getOtherReason());
					 }
					 pstmt.setString(3, "Active");
					 value="Active";
				 }
				 pstmt.setString(4, vo.getStageId()[i]);
				 
				 /*System.out.println("deleteStageByStatus -->>"+pstmt);*/
				 
				 count= pstmt.executeUpdate();
				 if(count > 0){
					 resultst++;
					 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","Stage Master",value,pstmt.toString(),custdetails);
					}
			  }
			}
			 if(unmappedcnt!=0 && unmappedcnt!=resultst){
				 status = "fail";
				}else if(resultst!=0 && resultst == unmappedcnt){
					status = "inactivetrue";
				}else{
					status = "inactivefalse";
				}
			 }else{
				 for(int i=0;i<vo.getStageId().length;i++){
					 unmappedcnt ++;
					   if(vo.getStatus().equalsIgnoreCase("InActive")){
						 pstmt.setString(1, "N");
						 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
							 pstmt.setString(2, vo.getInactiveReason()); 
						 }
						 else{
							 pstmt.setString(2, vo.getOtherReason());
						 }
						 pstmt.setString(3, "Inactive");
						 value="InActive";
					  }
					  else{
						 pstmt.setString(1, "Y");
						 if(vo.getOtherReason()=="" || vo.getOtherReason()==null){
							 pstmt.setString(2, vo.getActiveReason()); 
						 }
						 else{
							 pstmt.setString(2, vo.getOtherReason());
						 }
						 pstmt.setString(3, "Active");
						 value="Active";
					 }
					 pstmt.setString(4, vo.getStageId()[i]);
					 
					 /*System.out.println("deleteStageByStatus -->>"+pstmt);*/
					 
					 count= pstmt.executeUpdate();
					 if(count > 0){
						 resultst++;
						 HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Transport","Stage Master",value,pstmt.toString(),custdetails);
						}
				   
					 if(unmappedcnt!=0 && unmappedcnt!=resultst){
						 status = "fail";
						}else if(resultst!=0 && resultst == unmappedcnt){
							status = "activetrue";
						}
			 }
			 
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
				+ " Control in StageDAOIMPL: deleteStageByStatus : Ending");
		return status;
	}

	@Override
	public String insertstage(AddStagePojo apojo) {
		// TODO Auto-generated method stub
		return null;
	}

	
} 
		
	
		


	

	


