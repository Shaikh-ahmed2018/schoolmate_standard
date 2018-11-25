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

import com.centris.campus.dao.RoleNumberGenerationDao;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.RollNumberGenerationUtilConstants;
import com.centris.campus.vo.RollnumberGenerationVo;

public class RoleNumberGenerationDaoImpl implements RoleNumberGenerationDao{
      
	private static Logger logger = Logger.getLogger(RoleMasterDaoImpl.class);
	@Override
	public List<RollnumberGenerationVo> getlanguagedetails(String acy_yearid,
			String location_id) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleNumberGenerationDaoImpl: getlanguagedetails Starting");
		ArrayList<RollnumberGenerationVo> list = new ArrayList<RollnumberGenerationVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int sno = 0;

		try {
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn
					.prepareStatement(RollNumberGenerationUtilConstants.GETLANGUAGEDETAILS);
			pstmt.setString(1, location_id);
			pstmt.setString(2, acy_yearid);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) 
			{
				RollnumberGenerationVo getlanguage= new RollnumberGenerationVo();
				getlanguage.setFirstlanguage(resultSet.getString("firstlanguage"));
				getlanguage.setSecondlanguage(resultSet.getString("secondlanguage"));
				getlanguage.setThirdlanguage(resultSet.getString("thirdlanguage"));
				list.add(getlanguage);
			}
	

		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {	
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && (!resultSet.isClosed())) {
					resultSet.close();
				}
				if (pstmt != null && (!pstmt.isClosed())) {
					pstmt.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RoleNumberGenerationDaoImpl:getlanguagedetails Ending");
		return list;

	}

	@Override
	public List<RollnumberGenerationVo> getSecondlanguageofclass(
			String classidVal, String sectionidVal, String schoolLocation) {
		
		
		
		
		return null;
	}

}
