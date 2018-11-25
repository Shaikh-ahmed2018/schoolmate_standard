package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.ExpenditureDAO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ExpenditureVO;

public class ExpenditureDAOIMPL implements ExpenditureDAO {

	private static final Logger logger = Logger 
			.getLogger(AdminMenuAction.class);

	@Override
	public ArrayList<ExpenditureVO> getExpenditureDetails(ExpenditureVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeMasterDAOIMPL : getExpenditureDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		ArrayList<ExpenditureVO> expenditureDetails = new ArrayList<ExpenditureVO>();
		
		if (vo.getExpenditureTitle()==null || vo.getExpenditureTitle().equalsIgnoreCase(""))
		{ 
			try {
				
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_EXPENDITURE_DETAILS);
            pstmt.setString(1, vo.getIsActive());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ExpenditureVO vo1=new ExpenditureVO();
				vo1.setExpId(rs.getString("ExpId").trim());
				vo1.setLocationname(rs.getString("Location_Name"));
				vo1.setExpenditureTitle(rs.getString("ExpenditureTitle").trim());
				vo1.setAmount(rs.getDouble("Amount"));
				vo1.setDescription(rs.getString("Description").trim());
				vo1.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				if(rs.getString("isActive").equalsIgnoreCase("Y")) {
					vo1.setIsActive("Active");
				}
				else {
					vo1.setIsActive("InActive");
				}
				if(rs.getString("remark")=="" || rs.getString("remark")==null){
					vo1.setRemark("");
				}else{
				     vo1.setRemark(rs.getString("remark"));
				}
				expenditureDetails.add(vo1);
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
		
		
		else if (!vo.getExpenditureTitle().equalsIgnoreCase(""))
		
		{
			
			System.out.println("DIOMPL search part esle ");
			try {

				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.SEARCH_EXPENDITURE_DETAILS);
				pstmt.setString(1, vo.getExpenditureTitle()+"%");
				pstmt.setString(2, vo.getExpenditureTitle()+"%");
				pstmt.setString(3, vo.getExpenditureTitle()+"%");
				pstmt.setString(4, vo.getExpenditureTitle()+"%");
				
				System.out.println("pstmtpstmt"+pstmt);
				rs = pstmt.executeQuery();
				while (rs.next())

				{
					ExpenditureVO vo1=new ExpenditureVO();
					vo1.setExpId(rs.getString("ExpId").trim());
					vo1.setLocationname(rs.getString("Location_Name"));
					vo1.setExpenditureTitle(rs.getString("ExpenditureTitle").trim());
					vo1.setAmount(rs.getDouble("Amount"));
					vo1.setDescription(rs.getString("Description").trim());
					vo1.setDate(rs.getString("Date"));
					
					expenditureDetails.add(vo1);
					

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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");

		
	
		return expenditureDetails;
	}

	@Override
	public String insertExpenditure(ExpenditureVO vo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureDAOIMPL : insertExpenditure Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		try {
				conn = JDBCConnection.getSeparateConnection();
				if(vo.getExpId()==null || vo.getExpId().equalsIgnoreCase("")){
				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_EXPENDITURE_DETAILS);
				pstmt.setString(1,IDGenerator.getPrimaryKeyID("campus_expenditure"));
				pstmt.setString(2, vo.getExpenditureTitle());
				pstmt.setDouble(3, vo.getAmount());
				pstmt.setString(4, vo.getDescription());
				pstmt.setString(5,HelperClass.convertUIToDatabase(vo.getDate()) );
				pstmt.setString(6,vo.getLocationname());
				pstmt.setString(7, vo.getCreateUser());
				pstmt.setTimestamp(8, HelperClass.getCurrentTimestamp());
				
				System.out.println("pstmt"+pstmt);

				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					//HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Expenditure","Insert",pstmt.toString());
					result_Status = MessageConstants.SuccessMsgExpenditure;
				} else {
					result_Status = MessageConstants.ErrorMsgExpenditure;
				}
			}else{
				
				pstmt = conn.prepareStatement(SQLUtilConstants.EDIT_EXPENDITURE);
				
				pstmt.setString(1, vo.getExpenditureTitle());
				pstmt.setDouble(2, vo.getAmount());
				pstmt.setString(3, vo.getDescription());
				pstmt.setString(4,HelperClass.convertUIToDatabase(vo.getDate()) );
				pstmt.setString(5,vo.getLocationname());
				pstmt.setString(6, vo.getCreateUser());
				pstmt.setTimestamp(7, HelperClass.getCurrentTimestamp());
				pstmt.setString(8, vo.getExpId().trim());
				
				System.out.println("pstmt"+pstmt);

				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					//HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Expenditure","Update",pstmt.toString());
					result_Status = MessageConstants.SuccessUpMsgExpenditure;
				} else {
					result_Status = MessageConstants.ErrorUpMsgExpenditure;
				}
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
				+ " Control in ExpenditureDAOIMPL : insertExpenditure Ending");

		
		return result_Status;

	}

	@Override
	public ExpenditureVO editExpenditure(ExpenditureVO vo) {


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureDAOIMPL : editExpenditure Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String expId=vo.getExpId();
	
			try {
				
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_EXPENDITURE_EDIT);
			pstmt.setString(1, expId);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				
				vo.setExpId(rs.getString("ExpId").trim());
				vo.setLocationname(rs.getString("loc_Id"));
				vo.setExpenditureTitle(rs.getString("ExpenditureTitle").trim());
				vo.setAmount(rs.getDouble("Amount"));
				vo.setDescription(rs.getString("Description").trim());
				vo.setDate(HelperClass.convertDatabaseToUI(rs.getString("Date")));
				
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
				+ " Control in FeeMasterDAOIMPL : getnamecount Ending");
		
		return vo;
	}


	@Override
	public String deleteExpenditure(ExpenditureVO vo,String button) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExpenditureDAOIMPL : deleteExpenditure Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		try {
			    conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_EXPENDITURE);
				for(int i=0;i<vo.getExpIds().length;i++){
					pstmt.setString(1, vo.getIsActive());
				    pstmt.setString(2, vo.getRemark());
				    pstmt.setString(3, vo.getExpIds()[i]);
				    result1 = pstmt.executeUpdate();
				    if (result1 > 0) {
						//HelperClass.recordLog_Activity(vo.getLog_audit_session(),"Fee","Expenditure",button,pstmt.toString());
					}
				}

				if (result1 > 0) {
					result_Status="true";
				} else {
					result_Status="false";
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
				+ " Control in ExpenditureDAOIMPL : insertExpenditure Ending");
		return result_Status;

	}

}
