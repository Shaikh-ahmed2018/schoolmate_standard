package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.AddtSubjectDao;
import com.centris.campus.daoImpl.AddSubjectDaoImpl;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AddSubjectService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.SQLUtilConstants;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ViewallSubjectsVo;


public class AddSubjectServiceImpl implements AddSubjectService {
	private static final Logger logger = Logger
			.getLogger(AddSubjectServiceImpl.class);
	
	AddtSubjectDao daoObj=new AddSubjectDaoImpl();
	
	public synchronized List<ViewallSubjectsVo> subjectdetails(String schoolLocation,UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : subjectdetails Starting");
		ArrayList<ViewallSubjectsVo> subjectdetails = null;
		try {
			subjectdetails = daoObj.subjectdetails(schoolLocation,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : subjectdetails  Ending");
    return subjectdetails;
}

	public String DeleteSubject(String[] voObj,String[] locationList,ViewallSubjectsVo vo, String button,UserLoggingsPojo userLoggingsVo){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject Starting");
		String status=null;
		try {
			status = daoObj.DeleteSubject(voObj,locationList,vo,button,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject  Ending");
    
		return status;
	}
	
	public synchronized List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj,UserLoggingsPojo custdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : searchsubjectdetails Starting");
		List<ViewallSubjectsVo> subjectdetails = null;
		try {
			subjectdetails = daoObj.searchsubjectdetails(voObj,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : searchsubjectdetails  Ending");
    return subjectdetails;
	}
	
	public synchronized String addSubject(AddSubjectVo vo,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject Starting");
		String status="";
		try {
			status = daoObj.addSubject(vo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject  Ending");
    
		return status;
	}
	
	public synchronized ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj,UserLoggingsPojo dbdetails)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails Starting");
	
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails  Ending");
		return daoObj.getSubjectDetails(obj,dbdetails);
	}
	
	
	
	public synchronized boolean updateSubjectDetails(AddSubjectForm obj,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails Starting");
		boolean status=false;
		try {
			status = daoObj.updateSubjectDetails(obj,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails  Ending");
    
		return status;
	}
	
	public synchronized String getpath(String classname,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: getpath  Starting");
		String path=null;
		try{
		 path = new AddSubjectDaoImpl().getpath(classname,userLoggingsVo);
		}
		catch(Exception exception){
			exception.printStackTrace();
			logger.error(exception);
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: getpath Ending");
		return path;
	}

	
	public String DdownloadsyllabuspathService(String subjectid,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService  Starting");
		
		
		String path = null;
		
		try {
			
			 path = new AddSubjectDaoImpl().DdownloadsyllabuspathDao(subjectid,userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService Ending");
		return path;
	}

	@Override
	public String validateSubName(AddSubjectForm form1,UserLoggingsPojo userLoggingsVo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.validateSubName(form1, userLoggingsVo);
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization,UserLoggingsPojo custdetails, String status) {
		return new AddSubjectDaoImpl().subjectdetailsOnchangeListingPage( locationid,  classname,  specialization,custdetails,status);
	}

	@Override
	
		public synchronized List<ViewallSubjectsVo> labdetails(String schoolLocation ,  UserLoggingsPojo userLoggingsVo)
		{
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectServiceImpl : labdetails Starting");
			ArrayList<ViewallSubjectsVo> labdetails = null;
			try {
				labdetails = daoObj.labdetails(schoolLocation,userLoggingsVo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}

			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AddSubjectServiceImpl : labdetails  Ending");
	    return labdetails;
	}

	@Override
	public boolean addLab(AddLabVO obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addLab Starting");
		boolean status=false;
		try {
			status = daoObj.addLab(obj,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : addSubject  Ending");
    
		return status;
	}

	@Override
	public List<AddSubjectForm> getLaboratoryDetails(UserLoggingsPojo userLoggingsVo) {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<AddSubjectForm> list = new ArrayList<AddSubjectForm>();
		try {
			con = JDBCConnection.getSeparateConnection(userLoggingsVo);
			pstmt = con.prepareStatement(SQLUtilConstants.GET_LABORATORY_DETAILS);
			pstmt.setString(1, userLoggingsVo.getLocId());
			rs = pstmt.executeQuery();
			//System.out.println("lab details query for table display..."+pstmt);
			//cl.Location_Name,ccd.classdetails_name_var,cs.subjectName,ccs.Specialization_name,Total_Marks,Pass_Marks,ld.Description,lab_id,Lab_Code,Syllabus
			while (rs.next()) {
				AddSubjectForm lablist = new AddSubjectForm();
				lablist.setLab_id(rs.getString("lab_id"));
				lablist.setLocationName(rs.getString("Location_Name"));
				lablist.setLocationId(rs.getString("Location_Id"));
				lablist.setClassname(rs.getString("classdetails_name_var"));
				lablist.setSubjtname(rs.getString("subjectName"));
				lablist.setLab_name(rs.getString("Lab_Name"));
				lablist.setTotalMarks(rs.getInt("Total_Marks"));
				lablist.setSpecialization(rs.getString("Specialization_name"));
				lablist.setPassMarks(rs.getInt("Pass_Marks"));
				lablist.setDescription(rs.getString("Description"));
				lablist.setSubjectCode(rs.getString("Lab_Code"));
				lablist.setSyllabus(rs.getString("Syllabus"));
				list.add(lablist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return list;
	}

	@Override
	public String DeleteLab(String[] idList, String locationList,String reason,String statuss, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteLab Starting");
		String status=null;
		try {
			status = daoObj.DeleteLab(idList,locationList, reason, statuss, userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : DeleteSubject  Ending");
    
		return status;
	}

	@Override
	public AddSubjectForm getLabDetails(AddSubjectForm obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails Starting");
	
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : getSubjectDetails  Ending");
		return daoObj.getLabDetails(obj, userLoggingsVo);
	}

	@Override
	public boolean updateLabDetails(AddLabVO addSubjectForm,UserLoggingsPojo userLoggingsVo)
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails Starting");
		boolean status=false;
		try {
			status = daoObj.updateLabDetails(addSubjectForm,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddSubjectServiceImpl : updateSubjectDetails  Ending");
    
		return status;
	}

	@Override
	public List<AddSubjectForm> labdetailsOnchangeListingPage(String locationid, String classname, String specialization ,UserLoggingsPojo custdetails ,String status) {
	
		return new AddSubjectDaoImpl().labdetailsOnchangeListingPage( locationid,  classname,  specialization, custdetails,status);
	}

	@Override
	public String validateLabName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.validateLabName(form1,userLoggingsVo);
	}

	@Override
	public String DdownloadLabsyllabuspathService(String subjectid,UserLoggingsPojo userLoggingsVo) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService  Starting");
		
		
		String path = null;
		
		try {
			
			 path = new AddSubjectDaoImpl().DdownloadLabsyllabuspathDao(subjectid,userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ViewallSubjectsServiceImpl: DdownloadsyllabuspathService Ending");
		return path;
	}
	

	@Override
	public String getSubjectName(String subjectid) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.getSubjectName(subjectid);
	}

	@Override
	public List<ViewallSubjectsVo> getSubDetailsList(String locationid, String classname, String specialization,UserLoggingsPojo userLoggingsVo, String status) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.getSubDetailsList(locationid,classname,specialization, userLoggingsVo,status);
	}

	@Override
	public String updateSyllabusPath(String realPath,String locationid,String classId,UserLoggingsPojo userLoggingsVo,String academic_Year,String type,String specialization,String subjectId,String syllabus_Id) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();

		return subObj.updateSyllabusPath(realPath, locationid, classId, userLoggingsVo, academic_Year, type, specialization, subjectId, syllabus_Id);
	}

	@Override
	public String syllabusDownload(String locationid, String class_name, String sub_Id, String academicYear2,
			UserLoggingsPojo userLoggingsVo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.syllabusDownload( locationid,  class_name,  sub_Id,  academicYear2, userLoggingsVo);
	}


	@Override
	public String checkDuplicateSubCount(AddSubjectForm form1,UserLoggingsPojo userLoggingsVo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.checkDuplicateSubCount(form1,userLoggingsVo);
	}


	@Override
	public String getClassName(String classId,UserLoggingsPojo userLoggingsVo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.getClassName( classId,  userLoggingsVo);
	}

	@Override
	public String checkLabCode(SubjectPojo pojo) {
		AddtSubjectDao subObj=new AddSubjectDaoImpl();
		return subObj.checkLabCode(pojo);
	}
	
}
