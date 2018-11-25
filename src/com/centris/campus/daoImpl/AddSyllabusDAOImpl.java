package com.centris.campus.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.AddSyllabusDAO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.util.AddSyllabusUtilConstants;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.SyllabusVO;

public class AddSyllabusDAOImpl  implements AddSyllabusDAO{

	private static final Logger logger = Logger.getLogger(AssignmentUploadDaoImpl.class);
	@Override
	
	public ArrayList<SyllabusVO> syllabusListforListOnchangeMethod(String location_id, String classname,
			String specialization, String academicYear,UserLoggingsPojo userLoggingsVo,String isApplicable) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AssignmentUploadDaoImpl : syllabusListforListOnchangeMethod : Starting");
		
		
		PreparedStatement ps_getsSyllabus = null;
		Connection conn = null;
		PreparedStatement ps_insetassDetails = null;
		int detailsCount=0;
		int count=0;
		String status=null;
		ResultSet executeQuery = null;
		ArrayList<SyllabusVO> syllabusList=new ArrayList<SyllabusVO>();
		String name= "";
		String id = "";
		String classId="";
		String specializ="";
		try {
			
			//("sylabus  : daoIMPL");
			
			conn = JDBCConnection.getSeparateConnection(userLoggingsVo);
			//("dao impl:   "+ isApplicable);
			
			if( isApplicable != null && (isApplicable.equalsIgnoreCase("S")))
			{
				ps_getsSyllabus = conn.prepareStatement(AddSyllabusUtilConstants.GET_SUB_SYLABUS);
				name="subjectName";
				id="subjectID";
				classId="classid";
				specializ="specialization";			
			}
			else if(isApplicable != null && (isApplicable.equalsIgnoreCase("L")))
			{
				ps_getsSyllabus = conn.prepareStatement(AddSyllabusUtilConstants.GET_LAB_SYLABUS);
				name="Lab_Name";
				id="lab_id";
				classId="Class_Name";
				specializ="Specialization";
			}
			//`accy_id` = ? AND `isApplicable` = ?  WHERE s.locationId = ? AND s.classid LIKE ? AND specialization LIKE  ?
			//`subjectName`,`classid`,`specialization`,s.`locationId`,Specialization_name,`classdetails_name_var`,`syllabus_url`
			
			ps_getsSyllabus.setString(1,academicYear);
			ps_getsSyllabus.setString(2,isApplicable);
			ps_getsSyllabus.setString(3,location_id);
			ps_getsSyllabus.setString(4,classname);
			ps_getsSyllabus.setString(5,specialization);
			
			
		executeQuery = ps_getsSyllabus.executeQuery();
			
			
			while(executeQuery.next())
			{
				SyllabusVO vo = new SyllabusVO();
	//`subjectName`,`classid`,`specialization`,s.`locationId`,Specialization_name,`classdetails_name_var`,`syllabus_url`			
				
				vo.setSyllabusId(executeQuery.getString("syllabus_id"));
				vo.setLocation_name(executeQuery.getString("Location_Name"));
				vo.setSubject_name(executeQuery.getString(name));
				vo.setSyllabus_url(executeQuery.getString("syllabus_url"));
				vo.setId(executeQuery.getString(id));
				vo.setSpecialization(executeQuery.getString(specializ));
				vo.setClassid(executeQuery.getString(classId));
				vo.setClassName(executeQuery.getString("classdetails_name_var"));
				syllabusList.add(vo);
			
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
				
				if (ps_getsSyllabus != null&& (!ps_getsSyllabus.isClosed())) {
					ps_getsSyllabus.close();
				}
				if(ps_insetassDetails!=null && !ps_insetassDetails.isClosed()){
					ps_insetassDetails.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				  }
				
		       }
			  catch (SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				sqle.printStackTrace();
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
				+ " Control in AssignmentUploadDaoImpl : syllabusListforListOnchangeMethod: Ending");
		
		return syllabusList;
	}

}
