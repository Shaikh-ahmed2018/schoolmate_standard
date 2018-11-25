package com.centris.campus.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.ClassTeacherMappingDAO;
import com.centris.campus.delegate.SettingsXLUploadBD;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.classVo;
import com.itextpdf.text.log.SysoLogger;



public class ClassTeacherMappingDAOIMPL implements ClassTeacherMappingDAO {
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	
	public ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclass Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getclass Ending");

		return classList;
	}


	
	
	public ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsection Starting");

	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection conn = null;
	
		ArrayList<ClassTeacherMappingVO> sectionList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SECTION);
			pstmt.setString(1, vo.getClassid());
			pstmt.setString(2, vo.getClassid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setSectionid(rs.getString("classsection_id_int").trim());
				val.setSectionname(rs.getString("classsection_name_var").trim());
				sectionList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getsection Ending");

		return sectionList;
	}



	
	public ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getteacher Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ClassTeacherMappingVO> teacherList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teacherList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getteacher Ending");

		return teacherList;
	}




	public String addmappingdetails(ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : addmappingdetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		try {
			
				String str = IDGenerator.getPrimaryKeyID("campus_classteacher");
				conn = JDBCConnection.getSeparateConnection();

				pstmt = conn.prepareStatement(SQLUtilConstants.ADD_CLASS_TEACHER);
				pstmt.setString(1,str);
				pstmt.setString(2,vo.getClassid());
				pstmt.setString(3,vo.getSectionid());
				pstmt.setString(4,vo.getTeacherid());
				pstmt.setString(5,vo.getCreateuser());
				pstmt.setTimestamp(6,HelperClass.getCurrentTimestamp());
				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.SuccessMappingMsg;
				} else {
					result_Status = MessageConstants.ErrorMappingMsg;
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
				+ " Control in ClassTeacherMappingDAOIMPL : addmappingdetails Ending");

		
		return result_Status;

	}



	
	public ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo) 
	
	{
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : teachermapping Starting");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ClassTeacherMappingVO> MappingList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_MAPPING_DETAILS);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				MappingList.add(val);
				
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
				+ " Control in ClassTeacherMappingDAOIMPL : teachermapping Ending");

		return MappingList;
	
		
	}


	public LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassdetails Starting");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		Connection conn = null;
		boolean result = false;
		
		LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> allowanceMap=new LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>>();
		
		ArrayList<ClassTeacherMappingVO> allowanceList=new ArrayList<ClassTeacherMappingVO>();
		
		
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_DETAILS);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("ClassCode"));
				
				if(allowanceList.size()!=0){
					
					if(!(allowanceList.get(allowanceList.size()-1).getClassid().equalsIgnoreCase(rs.getString("ClassCode")))){
					
					allowanceMap.put(allowanceList.get(allowanceList.size()-1).getClassname(),allowanceList);
					
					count=0;
					allowanceList = new ArrayList<ClassTeacherMappingVO>();
				}
				
				}
				
				count++;
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				allowanceList.add(val);
			
			
		}
			
			if(allowanceList.size()!=0)
			
			{
				
				allowanceMap.put(allowanceList.get(allowanceList.size()-1).getClassname(),allowanceList);
				
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
				+ " Control in ClassTeacherMappingDAOIMPL : getclassdetails Ending");
		
		JSONArray array=new JSONArray();
		array.put(allowanceMap);
		return allowanceMap;
	}




	
	public ArrayList<ClassTeacherMappingVO> editclassdetails(
			ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : editclassdetails Starting");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		ArrayList<ClassTeacherMappingVO> MappingList = new ArrayList<ClassTeacherMappingVO>();

		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement("");
			/*pstmt.setString(1,vo.getId());*/
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO remain = new ClassTeacherMappingVO();
				
				
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
				+ " Control in ClassTeacherMappingDAOIMPL : editclassdetails Ending");
		return MappingList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getclassupdate(
			ClassTeacherMappingVO vo) 
			
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		Connection conn = null;
		

		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_UPDATE);
			pstmt.setString(1, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Ending");

		return classList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupclasslist(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Starting");

		PreparedStatement pstmt =null;
		PreparedStatement pstmt1 =null;
		ResultSet rs1 =null;
		ResultSet rs =null;
		Connection conn =null;
		

		ArrayList<ClassTeacherMappingVO> classList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_CLASS_VALUES);
			pstmt1.setString(1, vo.getClassteacherid());
			rs1 = pstmt1.executeQuery();
			while (rs1.next())

			{
			
			String classid=rs1.getString("ClassCode");
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_UPVALUES);
			pstmt.setString(1, classid);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassid(rs.getString("classdetail_id_int").trim());
				val.setClassname(rs.getString("classdetails_name_var").trim());
				classList.add(val);
			
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
				if(rs1!=null && ! rs1.isClosed()){
					rs1.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if(pstmt1 !=null && !pstmt.isClosed()){
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
				+ " Control in ClassTeacherMappingDAOIMPL : getclassupdate Ending");

		return classList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getsectionupdate(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getsectionupdate Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<ClassTeacherMappingVO> sectionList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SECTION_UPDATE);
			pstmt.setString(1, vo.getClassid());
			pstmt.setString(2, vo.getClassid());
			pstmt.setString(3, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setSectionid(rs.getString("classsection_id_int").trim());
				val.setSectionname(rs.getString("classsection_name_var").trim());
				sectionList.add(val);
			
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
				if (conn!= null && !conn.isClosed()) {
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
				+ " Control in ClassTeacherMappingDAOIMPL : getsectionupdate Ending");

		return sectionList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupdateteacherlist Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
	

		ArrayList<ClassTeacherMappingVO> teaList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_TEACHER_UPDATE);
			pstmt.setString(1, vo.getClassteacherid());
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teaList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getupdateteacherlist Ending");

		return teaList;
	}




	
	public ArrayList<ClassTeacherMappingVO> getupteacherlist(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getupteacherlist Starting");

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		Connection conn = null;
		

		ArrayList<ClassTeacherMappingVO> teaList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection();
			pstmt1 = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_COUNT);
			pstmt1.setString(1, vo.getClassteacherid());
			rs1 = pstmt1.executeQuery();
			while (rs1.next())

			{
			
			String teaid=rs1.getString("TeacherCode");
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_UPDATE);
			pstmt.setString(1, teaid);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setTeacherid(rs.getString("TeacherID").trim());
				val.setTeachername(rs.getString("FirstName").trim());
				teaList.add(val);
			
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
				if(rs1!=null && !rs1.isClosed()){
					rs1.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(pstmt1 !=null && !pstmt1.isClosed()){
					pstmt1.close();
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
				+ " Control in ClassTeacherMappingDAOIMPL : getupteacherlist Ending");

		return teaList;
	}




	
	public String updatemappingDetails(ClassTeacherMappingVO vo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : updatemappingDetails Starting");
		String result_Status = "";
		PreparedStatement pstmt = null;
		int result1 = 0;
		Connection conn = null;
		
		try {
				conn = JDBCConnection.getSeparateConnection();
				pstmt = conn.prepareStatement(SQLUtilConstants.UPDATE_MAPPING_DETAILS);
				pstmt.setString(1,vo.getClassid());
				pstmt.setString(2,vo.getSectionid());
				pstmt.setString(3,vo.getTeacherid());
				pstmt.setString(4,vo.getClassteacherid());
				result1 = pstmt.executeUpdate();

				if (result1 == 1) {
					result_Status = MessageConstants.UpdateMappingMsg;
				} else {
					result_Status = MessageConstants.ErrorUpMappingMsg;
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
				+ " Control in ClassTeacherMappingDAOIMPL : updatemappingDetails Ending");

		
		return result_Status;

	}




	
	public String deletemappingDetails(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : deletemappingDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String status="";
		int no=0;

		Connection conn = null;

		try
		
		{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_TEACHER_MAPPING);
			pstmt.setString(1, vo.getClassteacherid());
			no = pstmt.executeUpdate();
			if(no>0)
			{
				
				status=MessageConstants.DEL_MAP_SUCCESS;
				
			}
			
			else
			{
				status=MessageConstants.DEL_MAP_ERROR;
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
				+ " Control in ClassTeacherMappingDAOIMPL : deletemappingDetails Ending");
		
		return status;
	}




	
	public ArrayList<ClassTeacherMappingVO> getDownloadDetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getDownloadDetails Starting");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
	

		ArrayList<ClassTeacherMappingVO> getDownloadList = new ArrayList<ClassTeacherMappingVO>();
		
		try {

			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_DOWNLOAD_EXCEL);
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				ClassTeacherMappingVO val=new ClassTeacherMappingVO();
				val.setClassteacherid(rs.getString("CTCode"));
				val.setClassid(rs.getString("ClassCode"));
				val.setSectionid(rs.getString("SectionCode"));
				val.setTeacherid(rs.getString("TeacherCode"));
				val.setClassname(rs.getString("classdetails_name_var"));
				val.setSectionname(rs.getString("classsection_name_var"));
				val.setTeachername(rs.getString("FirstName"));
				getDownloadList.add(val);
			
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
				+ " Control in ClassTeacherMappingDAOIMPL : getDownloadDetails Ending");

		return getDownloadList;
	}




	public ArrayList<ReportMenuVo> getAccYearsOnchange(UserLoggingsPojo custdetails, String accId) {
			
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingDAOIMPL : getAccYearsOnchange Starting");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = null;
			
			ArrayList<ReportMenuVo> list = new ArrayList<ReportMenuVo>();

			
			try {

				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACCYEAR_ONCAHNGE);
				pstmt.setString(1, accId);
				
				//(pstmt);
				
				rs = pstmt.executeQuery();
				while (rs.next())

				{
					ReportMenuVo val=new ReportMenuVo();
					val.setAccyearId(rs.getString("acadamic_id"));
					val.setAccyearname(rs.getString("acadamic_year"));
					list.add(val);
				
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
					+ " Control in ClassTeacherMappingDAOIMPL : getAccYearsOnchange Ending");

			return list;
		}




	public ArrayList<TeacherVo> getTeacherByLoc(UserLoggingsPojo custdetails, String locId, String accId) {
				
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingDAOIMPL : getTeacherByLoc Starting");

				PreparedStatement pstmt = null,pstmt1=null;
				ResultSet rs = null;
				ResultSet rs1 =null;
				Connection conn = null;
				int i=1;
				int count=0;
				ArrayList<TeacherVo> list = new ArrayList<TeacherVo>();

				try {
					
					conn = JDBCConnection.getSeparateConnection(custdetails);
					pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_BY_LOC);
					pstmt.setString(1, accId);
					pstmt.setString(2, locId);
					pstmt.setString(3, locId);
					pstmt.setString(4, accId);
					
					System.out.println("GET_TEACHER_BY_LOC  -->>"+pstmt);
					
					rs = pstmt.executeQuery();
					while (rs.next())
 
					{  
						TeacherVo vo =new TeacherVo();
						vo.setTeacherId(rs.getString("TeacherID"));
						vo.setRegisterId(rs.getString("registerId"));
						vo.setTeacherName(rs.getString("teacher"));
						vo.setLocationId(rs.getString("Location_Id"));
						vo.setLocationName(rs.getString("Location_Name"));
						vo.setAccyearId(rs.getString("acadamic_id"));
						vo.setAccyearName(rs.getString("acadamic_year"));
						vo.setStatus(rs.getString("mstatus"));
						vo.setSno(i++);
						list.add(vo);
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
						+ " Control in ClassTeacherMappingDAOIMPL : getTeacherByLoc Ending");
		return list;
	}

	

	public String saveTeacherClassMapping(classVo vo, UserLoggingsPojo custdetails, HttpServletRequest request) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : saveTeacherClassMapping Starting");

		PreparedStatement pstmt = null,pstmt2 =null,pstmt1=null,pstmt3=null,pstmt5=null;
		Connection conn = null;
		int count=0;
		ResultSet rs,rs1 = null;
		String status=null;
		String mapId=null;
		int count2=0;
		int count3=0;
		int count4=0;
		int mapid = 0;
		int result=0;
		try {

				conn = JDBCConnection.getSeparateConnection(custdetails);
				conn.setAutoCommit(false);
				
				PreparedStatement pstmy=conn.prepareStatement("DELETE  FROM `campus_subjectwise_teachers`  WHERE tea_id=?");
				pstmy.setString(1, vo.getTeacherId());
				pstmy.executeUpdate();
				pstmy.close();
				
				pstmt3=conn.prepareStatement(SQLUtilConstants.TEACHER_MAP_COUNT);
				pstmt3.setString(1, vo.getTeacherId());
				pstmt3.setString(3, vo.getAccyearId());
				pstmt3.setString(2, vo.getLocationId());
				
				//("TEACHER_MAP_COUNT "+pstmt3);
				
				rs1= pstmt3.executeQuery();
				while(rs1.next()){
					count4 = rs1.getInt(1);
				}
				
				//("teacher map count value="+count4);
				
				if(count4==0){ //save_info
					
					//("save INformation #####################");
					result++;
					
					pstmt2 = conn.prepareStatement(SQLUtilConstants.SAVE_CLASSWISE_TEACHERS,Statement.RETURN_GENERATED_KEYS);
					pstmt2.setString(1, vo.getAccyearId());
					pstmt2.setString(2, vo.getLocationId());
					pstmt2.setString(3, vo.getTeacherId());
					pstmt2.setString(4, HelperClass.getCurrentUser(request));
					pstmt2.setTimestamp(5, HelperClass.getCurrentTimestamp());
					
					//("2222222222222 "+pstmt2);
					
					count2 = pstmt2.executeUpdate();
					ResultSet generatedKeys = pstmt2.getGeneratedKeys();
					  if (generatedKeys.next()) {
						  mapid = generatedKeys.getInt(1);
					  }
					  if(count2>0){
						result++;
						
						pstmt = conn.prepareStatement(SQLUtilConstants.SAVE_CLASS_TEACHER_MAPPING); //
						for(int i=0;i<vo.getClassId().split(",").length;i++){
							pstmt.setString(1, vo.getClassId().split(",")[i]);
							pstmt.setString(2, vo.getSecId().split(",")[i]);
							pstmt.setString(3, vo.getSpecId().split(",")[i]);
							pstmt.setInt(4, mapid);
							count = pstmt.executeUpdate();
							
							if(count>0){
								result++;
							}
						}
						
						if(result==vo.getClassId().split(",").length+2){
							//("resulttttttttttttt size"+result);
							conn.commit();
							status = "success";
						}
						else{
							conn.rollback();
							status = "fail";
						}
					}
					
					  return status;
				}
				
				else{ // update_info
					
					//("Update INformation #####################");
					
					pstmt1 =conn.prepareStatement(SQLUtilConstants.DELETE_TEACHER_MAPINFO);
					pstmt1.setString(1, vo.getTeacherId());
					pstmt1.setString(2, vo.getAccyearId());
					pstmt1.setString(3, vo.getLocationId());
					
					System.out.println("DELETE_TEACHER_MAPINFO  -->>"+pstmt1);
					count3=pstmt1.executeUpdate();
					
					/*System.out.println("count3 -->>"+count3);*/
					
					if(count3>0){
						result++;
						
						pstmt2 = conn.prepareStatement(SQLUtilConstants.SAVE_CLASSWISE_TEACHERS,Statement.RETURN_GENERATED_KEYS);
						pstmt2.setString(1, vo.getAccyearId());
						pstmt2.setString(2, vo.getLocationId());
						pstmt2.setString(3, vo.getTeacherId());
						pstmt2.setString(4, HelperClass.getCurrentUser(request));
						pstmt2.setTimestamp(5, HelperClass.getCurrentTimestamp());
						System.out.println("SAVE_CLASSWISE_TEACHERS  -->> "+pstmt2);
						count2 = pstmt2.executeUpdate();
						
						/*System.out.println("count2 -->>"+count2);*/
						
						ResultSet generatedKeys = pstmt2.getGeneratedKeys();
						  if (generatedKeys.next()) {
							  mapid = generatedKeys.getInt(1);
						  }
						  
						if(count2>0){
							result++;
							//("entering after count 2 values");
							
							pstmt5 = conn.prepareStatement(SQLUtilConstants.SAVE_CLASS_TEACHER_MAPPING); //
						
							//(vo.getClassId().split(",").length);

							//(vo.getClassId().split(",")[0]);
							for(int i=0;i<vo.getClassId().split(",").length;i++){
								
								//(vo.getClassId().split(",").length);
								
								pstmt5.setString(1, vo.getClassId().split(",")[i]);
								pstmt5.setString(2, vo.getSecId().split(",")[i]);
								pstmt5.setString(3, vo.getSpecId().split(",")[i]);
								pstmt5.setInt(4, mapid);
								
								System.out.println("SAVE_CLASS_TEACHER_MAPPING  -->>"+pstmt5);
									
								count = pstmt5.executeUpdate();
								
								if(count>0){
									result++;
								}
							}
							
							if(result==vo.getClassId().split(",").length+2){
								
								//("resulttttttttttttt size"+result);
								conn.commit();
								status = "success";
							}
							else{
								conn.rollback();
								status = "fail";
							}
						}
						else{
							conn.rollback();
							status = "fail";
						}
					}
					else{
						conn.rollback();
						status = "fail";
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
			/*	if (rs != null && !rs.isClosed()) {
					rs.close();
				}*/
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (pstmt2 != null && !pstmt2.isClosed()) {
					pstmt2.close();
				}
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in ClassTeacherMappingDAOIMPL : saveTeacherClassMapping Ending");
		
		
		return status;
	}




	public String checkTeacherStatus(classVo vo, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : checkTeacherStatus Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs =null;
		int count=0;
		String status=null;

		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_TEACHER_STATUS);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getTeacherId());
			
			//(pstmt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				 count  = rs.getInt(1);
			}
			//("count"+count);
			if(count==1){
				status="Set";
				
				//("status  "+status);
			}
			else{
				
				//("status  "+status);
				status="Not";
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
				+ " Control in ClassTeacherMappingDAOIMPL : checkTeacherStatus Ending");

		return status;
	}



	
	
	
	

	public List<TeacherVo> getAccyearLocationList(UserLoggingsPojo custdetails, String accYear, String loc) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : getAccyearStatus Starting");

		PreparedStatement pstmt = null,pstmt1=null,pstmt2=null;
		Connection conn = null;
		ResultSet rs =null,rs1=null,rs2=null;
		int count=0;
		String status=null;
		int count2=0;
		List<TeacherVo> list = new ArrayList<>();
		int i=0;

		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_ACCYEAR_LOCATION_LIST);
			pstmt.setString(1, accYear);
			pstmt.setString(2, loc);
			rs = pstmt.executeQuery();
			
			//(pstmt);
			
			pstmt1 = conn.prepareStatement("SELECT COUNT(DISTINCT `tea_id`) teaid FROM `campus_subjectwise_teachers` where accy_id=? AND loc_id=? ");
			pstmt1.setString(1, accYear);
			pstmt1.setString(2, loc);
			rs1=pstmt1.executeQuery();
			while(rs1.next()){
				count = rs1.getInt(1);
			}
			
			//("count :::::::::::::"+count);
			
			
			pstmt2 = conn.prepareStatement("SELECT COUNT(`teacher_id`) FROM `campus_claswise_teachers` where accy_id=? and loc_id=?");
			pstmt2.setString(1, accYear);
			pstmt2.setString(2, loc);
			
			rs2=pstmt2.executeQuery();
			while(rs2.next()){
				count2 = rs2.getInt(1);
			}
			
			//("count2 :::::::::::::"+count2);
			
			if(count<count2){
				status="Pending";
				//(status);
			}
			else if(count==count2){
				status="Not Set";
				//(status);
			}
			else{
				status="Set";
				//(status);
			}
			while(rs.next()){
				i++;
				TeacherVo vo = new TeacherVo();
				vo.setSno(i);
				vo.setAccyearId(rs.getString("acadamic_id"));
				vo.setAccyearName(rs.getString("acadamic_year"));
				vo.setLocationId(rs.getString("Location_Id"));
				vo.setLocationName(rs.getString("Location_Name"));
				vo.setStatus(status);
				list.add(vo);
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
				+ " Control in ClassTeacherMappingDAOIMPL : getAccyearStatus Ending");

		return list;
	}




	public List<classVo> displayClassTeacherList(UserLoggingsPojo custdetails, classVo vo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : displayClassTeacherList Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs =null;
		
		List<classVo> list = new ArrayList<>();
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.DISPLAY_CLASS_TEACHER_MAPPING);
			pstmt.setString(1, vo.getTeacherId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getLocationId());
			
			//("DISPLAY_CLASS_TEACHER_MAPPING"+pstmt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				classVo vo1 = new classVo();
				vo1.setClassName(rs.getString("classdetails_name_var"));
				vo1.setSecDetailName(rs.getString("classsection_name_var"));
				vo1.setSpecializationName(rs.getString("Specialization_name"));
				vo1.setSpecId(rs.getString("spec_id"));
				vo1.setSecId(rs.getString("sec_id"));
				vo1.setClassId(rs.getString("class_id"));
				vo1.setTeacherMapId(rs.getString("teacher_map_id"));
				
				list.add(vo1);
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
				+ " Control in ClassTeacherMappingDAOIMPL : displayClassTeacherList Ending");

		return list;
	}


		

		public String validateClassSectionSpec(classVo vo, UserLoggingsPojo custdetails) {
				logger.setLevel(Level.DEBUG);
				JLogger.log(0, JDate.getTimeString(new Date())
						+ MessageConstants.START_POINT);
				logger.info(JDate.getTimeString(new Date())
						+ " Control in ClassTeacherMappingDAOIMPL : validateClassSectionSpec Starting");

				PreparedStatement pstmt = null;
				Connection conn = null;
				ResultSet rs =null;
				String status =null;
				int count=0;
				
				try {
					
					conn = JDBCConnection.getSeparateConnection(custdetails);
					pstmt = conn.prepareStatement("SELECT COUNT(*) FROM `campus_teacher_class_mapping` c LEFT JOIN `campus_claswise_teachers` t ON t.`map_id`=c.`map_id` WHERE c.class_id=? AND c.`sec_id`=? AND c.`spec_id`=? AND t.`teacher_id`=?");
					for(int i=0;i<vo.getClassId().split(",").length;i++){
						pstmt.setString(1, vo.getClassId().split(",")[i]);
						pstmt.setString(2, vo.getSecId().split(",")[i]);
						pstmt.setString(3, vo.getSpecId().split(",")[i]);
						pstmt.setString(4, vo.getTeacherId());
					
					//("validateClassSectionSpec@@@@@@@@@@@@@@@@@@@@@@@@@ "+pstmt);
					
					rs = pstmt.executeQuery();
					while(rs.next()){
						count = rs.getInt(1);
					}
					if(count>0){
						status="duplicate";
					}
					else{
						status="not";
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
						+ " Control in ClassTeacherMappingDAOIMPL : validateClassSectionSpec Ending");

			return status;
		}




	public String deleteClassTeacherMap(UserLoggingsPojo custdetails, String id) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingDAOIMPL : deleteClassTeacherMap Starting");

			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs =null;
			String status =null;
			
			List<classVo> list = new ArrayList<>();
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				pstmt = conn.prepareStatement(SQLUtilConstants.DELETE_CLAS_TEACHER_MAP);
				pstmt.setString(1, id);
				
				System.out.println("DELETE_CLAS_TEACHER_MAP -->>"+pstmt);
				
				int i = pstmt.executeUpdate();
				if(i>0){
					status="success";
				}
				else{
					status="error";
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
					+ " Control in ClassTeacherMappingDAOIMPL : deleteClassTeacherMap Ending");

		return status;
	}




	public List<TeacherVo> SubjectwiseTeacherList(TeacherVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : SubjectwiseTeacherList Starting");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs =null;
		int i=1;
		List<TeacherVo> list = new ArrayList<>();
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_MAPPED_TEACHERLIST);
			pstmt.setString(1, vo.getAccyearId());
			pstmt.setString(2, vo.getLocationId());
			pstmt.setString(3, vo.getAccyearId());
			pstmt.setString(4, vo.getLocationId());
			
			//("GET_CLASS_MAPPED_TEACHERLIST"+pstmt);
			rs  = pstmt.executeQuery();
			while(rs.next()) {
				TeacherVo vo1 = new TeacherVo();
				vo1.setRegisterId(rs.getString("registerId"));
				vo1.setTeacherName(rs.getString("teacherName"));
				vo1.setTeacherId(rs.getString("teacher_id"));
				vo1.setStatus(rs.getString("mstatus"));
				vo1.setAccyearName(rs.getString("acadamic_year"));
				vo1.setLocationName(rs.getString("Location_Name"));
				vo1.setSno(i++);
				list.add(vo1);
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
				+ " Control in ClassTeacherMappingDAOIMPL : SubjectwiseTeacherList Ending");
		return list;
	}




	
	
	public List<TeacherVo> getAllSubject(TeacherVo vo, UserLoggingsPojo custdetails) {
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingDAOIMPL : getAllSubject Starting");

			PreparedStatement pstmt = null;
			Connection conn = null;
			ResultSet rs =null;
			List<TeacherVo> list = new ArrayList<>();
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
						pstmt = conn.prepareStatement(SQLUtilConstants.GET_ALL_SUBJECTS);
						pstmt.setString(2, vo.getLocationId());
						pstmt.setString(1, vo.getClassId());
						pstmt.setString(3, vo.getSpecId());
						
						System.out.println("GET_ALL_SUBJECTS -->>"+pstmt);
						rs  = pstmt.executeQuery();
						
						while(rs.next()) {
							TeacherVo vo1 = new TeacherVo();
							vo1.setSubjectId(rs.getString("subjectID"));
							vo1.setSubjectName(rs.getString("subjectName"));
							vo1.setSubjectCode(rs.getString("Sub_Code"));
							list.add(vo1);
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
					+ " Control in ClassTeacherMappingDAOIMPL : getAllSubject Ending");
			return list;
		}
	


	public String saveTeacherSubjectMapInfo(TeacherVo vo, UserLoggingsPojo custdetails, String classSecSpecIds) {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ClassTeacherMappingDAOIMPL : SaveSubjectwiseTeachersSetting Starting");

			PreparedStatement pstmt = null,pstmt2=null,pstmt1=null;
			Connection conn = null;
			ResultSet rs =null;
			int count=0;
			String status=null;
			int teacherMapId =0;
			int Result =0;
			int count2=0;
			int totalExpected = 0;
			try {
				
				conn = JDBCConnection.getSeparateConnection(custdetails);
				
			
					
								
				
				conn.setAutoCommit(false);
				
				if(vo.getUpdateMapIdArray().startsWith("new")){
					pstmt = conn.prepareStatement(SQLUtilConstants.SAVE_SUBJECTWISE_TEACHERS,Statement.RETURN_GENERATED_KEYS);
					
				}else{
					
					
					//(" update part workinggggggggggggggggg ");
					
					//("vo.getUpdateMapIdArray().split(,).length :"+vo.getUpdateMapIdArray().split(",").length);
					
					for(int i=0;i<vo.getUpdateMapIdArray().split(",").length;i++){
						pstmt1 = conn.prepareStatement("DELETE FROM campus_subjectwise_teachers where tea_map_id=?");
						
						pstmt1.setString(1, vo.getUpdateMapIdArray().split(",")[i]);
						//("delete before update:saving "+pstmt1);
						pstmt1.executeUpdate();
						pstmt = conn.prepareStatement(SQLUtilConstants.SAVE_SUBJECTWISE_TEACHERS,Statement.RETURN_GENERATED_KEYS);
					}
					
				}
				totalExpected=classSecSpecIds.split(",").length; //2
					
						for(int i=0;i<classSecSpecIds.split(",").length;i++){
						
						String ids = classSecSpecIds.split(",")[i];
							
						
						pstmt.setString(1, vo.getAccyearId());
						pstmt.setString(2, vo.getLocationId());
						pstmt.setString(3, vo.getTeacherId());
						pstmt.setString(4, ids.split(":")[0]);
						pstmt.setString(5, ids.split(":")[1]);
						pstmt.setString(6, ids.split(":")[2]);
						pstmt.setString(7, vo.getCurrentUser());
						
						//("SAVE_SUBJECTWISE_TEACHERS"+pstmt);
						
						count = pstmt.executeUpdate();
						  if(count>0) {
							  
							  ResultSet generatedKeys = pstmt.getGeneratedKeys();
							  if (generatedKeys.next()) {
								  teacherMapId = generatedKeys.getInt(1);
							  }
							  
							  //("one query executed");
								Result++;
									
								//("sub length="+vo.getSubjectNameArray().split(",").length);
									int subids = vo.getSubjectNameArray().split(",")[i].split("-").length;
									
									//("subids $$$$$$$$$$$$$$$$ "+subids);
									
									//(vo.getSubjectNameArray().split(",")[i]);
									
									totalExpected = totalExpected+subids;
									
										for(int j=0;j<subids;j++){
											
										String id = vo.getSubjectNameArray().split(",")[i].split("-")[j];
										
										pstmt2 = conn.prepareStatement(SQLUtilConstants.SAVE_SUBJECTWISE_TEACHERS_MAPPING);
										
										//(i);
										//(id);
										
										pstmt2.setInt(1, teacherMapId);
										pstmt2.setString(2,id );
										//("second query"+pstmt2);
										
										count2 = pstmt2.executeUpdate();
									}
									if(count2>0){
										//("second query executed");
										Result=Result+subids;
									}
						}
						  
						}
						
						//("totalExpected ############# "+totalExpected);
						//("Result ############# "+Result);
						
						
						
						if(totalExpected==Result){
							conn.commit();
							status = "success";
							
						}
						else{
							conn.rollback();
							status = "fail";
							
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
					+ " Control in ClassTeacherMappingDAOIMPL : SaveSubjectwiseTeachersSetting Ending");
			return status;
		}



	public List<classVo> getSubjectTeacherList(TeacherVo vo, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingDAOIMPL : displayClassTeacherList Starting");

		PreparedStatement pstmt = null,pstmt1=null;
		Connection conn = null;
		ResultSet rs =null;
		ResultSet rs2=null;
		
		List<classVo> list = new ArrayList<>();
		try {
			
			conn = JDBCConnection.getSeparateConnection(custdetails);
			
			if(vo.getStatus().equalsIgnoreCase("Set")){ // subject-teacher mapping table 
			pstmt = conn.prepareStatement(SQLUtilConstants.DISPLAY_CLASS_TEACHER_MAPPING_SET);
			pstmt.setString(1, vo.getTeacherId());
			pstmt.setString(2, vo.getAccyearId());
			pstmt.setString(3, vo.getLocationId());
			
			//("DISPLAY_CLASS_TEACHER_MAPPING_SET "+pstmt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				classVo vo1 = new classVo();
				vo1.setClassName(rs.getString("classdetails_name_var"));
				vo1.setSecDetailName(rs.getString("classsection_name_var"));
				vo1.setSpecializationName(rs.getString("specName"));
				vo1.setSpecId(rs.getString("sec_id"));
				vo1.setSecId(rs.getString("spec_id"));
				vo1.setClassId(rs.getString("class_id"));
				vo1.setTeacherMapId(rs.getString("tea_map_id"));
				
				
					
					pstmt1 =conn.prepareStatement(SQLUtilConstants.DISPLAY_CLASS_TEACHER_MAPPING_ADD_SUBJECTS);
					pstmt1.setString(1, rs.getString("tea_map_id"));
		
					
					//("sdf "+pstmt1);
					rs2 = pstmt1.executeQuery();
					while(rs2.next()){
						vo1.setSubjectName(rs2.getString("subjectName"));
						vo1.setSubjectId(rs2.getString("subjectId"));
						vo1.setSubjectIdsUpdate(rs2.getString("subjectId").replace(",", "-"));
						vo1.setTeacherMapId(rs2.getString("tea_map_id"));
					}
					
				
					
					//("Get subject IDSSS "+vo1.getSubjectId().replace(",", "-"));
					//(vo1.getSubjectName());
				
					list.add(vo1);	
				}
				
			}
			else{ // class-teacher mapping tables
				
				//("Not set partttttttttttt");
				
				pstmt = conn.prepareStatement(SQLUtilConstants.DISPLAY_CLASS_TEACHER_MAPPING_NOTSET);
				pstmt.setString(1, vo.getTeacherId());
				pstmt.setString(2, vo.getAccyearId());
				pstmt.setString(3, vo.getLocationId());
				
				//("DISPLAY_CLASS_TEACHER_MAPPING_NOTSET"+pstmt);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					classVo vo2 = new classVo();
					vo2.setClassName(rs.getString("classdetails_name_var"));
					vo2.setSecDetailName(rs.getString("classsection_name_var"));
					vo2.setSpecializationName(rs.getString("Specialization_name"));
					vo2.setSpecId(rs.getString("spec_id"));
					vo2.setSecId(rs.getString("sec_id"));
					vo2.setClassId(rs.getString("class_id"));
					vo2.setTeacherMapId("new_"+rs.getString("teacher_map_id"));
				
					vo2.setSubjectName("---");
					
					list.add(vo2);
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
				if (pstmt1 != null && !pstmt1.isClosed()) {
					pstmt1.close();
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
				+ " Control in ClassTeacherMappingDAOIMPL : displayClassTeacherList Ending");

		return list;
	}







	

}
