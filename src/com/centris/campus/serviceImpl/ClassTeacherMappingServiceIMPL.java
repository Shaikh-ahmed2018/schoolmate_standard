package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.ClassTeacherMappingDAO;
import com.centris.campus.daoImpl.ClassTeacherMappingDAOIMPL;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassTeacherMappingService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ClassTeacherMappingVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.TeacherVo;
import com.centris.campus.vo.classVo;


public class ClassTeacherMappingServiceIMPL implements ClassTeacherMappingService 

{
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	ClassTeacherMappingDAO obj_map = new ClassTeacherMappingDAOIMPL();
	
	public ArrayList<ClassTeacherMappingVO> getclass(ClassTeacherMappingVO vo) 
	
	{
		
		return obj_map.getclass(vo);
	}

	
	public ArrayList<ClassTeacherMappingVO> getsection(ClassTeacherMappingVO vo) {
		
		return obj_map.getsection(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getteacher(ClassTeacherMappingVO vo) {
		
		return obj_map.getteacher(vo);
	}


	
	public String addmappingdetails(ClassTeacherMappingVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingServiceIMPL : addmappingdetails Starting");
		String check="";
		if (vo.getClassteacherid().equalsIgnoreCase("")||vo.getClassteacherid()==null)
			
		{
			
			
		try
		
		{
			
			check = obj_map.addmappingdetails(vo);
			
		}
		
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		}
		
		else if (!vo.getClassteacherid().equalsIgnoreCase(""))
			
		{
			
			
		
			try
			
			{
				
				check = obj_map.updatemappingDetails(vo);
				
			}
			
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ClassTeacherMappingServiceIMPL :addmappingdetails Ending");
		return check;
		
	
	
		
		
	}


	
	public ArrayList<ClassTeacherMappingVO> teachermapping(ClassTeacherMappingVO vo) {
		
		return obj_map.teachermapping(vo);
	}



	public LinkedHashMap<String, ArrayList<ClassTeacherMappingVO>> getclassdetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
	
		return obj_map.getclassdetails(vo,userLoggingsVo);
	}


	
	public ArrayList<ClassTeacherMappingVO> editclassdetails(
			ClassTeacherMappingVO vo) {
		
		return obj_map.editclassdetails(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getclassupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getclassupdate(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getupclasslist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupclasslist(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getsectionupdate(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getsectionupdate(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getupdateteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupdateteacherlist(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getupteacherlist(
			ClassTeacherMappingVO vo) {
		
		return obj_map.getupteacherlist(vo);
	}


	
	public String deletemappingDetails(ClassTeacherMappingVO vo) {
		
		return obj_map.deletemappingDetails(vo);
	}


	
	public ArrayList<ClassTeacherMappingVO> getDownloadDetails(
			ClassTeacherMappingVO vo,UserLoggingsPojo userLoggingsVo) {
		
		return obj_map.getDownloadDetails(vo,userLoggingsVo);
	}




	
	
	//submodule: 3 :staff
	
	public ArrayList<TeacherVo> getTeacherByLoc(UserLoggingsPojo custdetails, String locId, String accId) {
		return new ClassTeacherMappingDAOIMPL().getTeacherByLoc(custdetails,locId,accId);
	}
	
	public String saveTeacherClassMapping(classVo vo, UserLoggingsPojo custdetails, HttpServletRequest request) {
		return new ClassTeacherMappingDAOIMPL().saveTeacherClassMapping(vo,custdetails,request);
	}
	
	public String checkTeacherStatus(classVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingDAOIMPL().checkTeacherStatus(vo,custdetails);
	}
	
	public List<TeacherVo> getAccyearLocationList(UserLoggingsPojo custdetails, String accYear, String loc) {
		return new ClassTeacherMappingDAOIMPL().getAccyearLocationList(custdetails,accYear,loc);
	}


	public List<classVo> displayClassTeacherList(UserLoggingsPojo custdetails, classVo vo) {
		return new ClassTeacherMappingDAOIMPL().displayClassTeacherList(custdetails,vo);
	}


	public String deleteClassTeacherMap(UserLoggingsPojo custdetails, String id) {
		return new ClassTeacherMappingDAOIMPL().deleteClassTeacherMap(custdetails,id);
	}


	public List<TeacherVo> SubjectwiseTeacherList(TeacherVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingDAOIMPL().SubjectwiseTeacherList(vo,custdetails);
	}


	public List<TeacherVo> getAllSubject(TeacherVo vo, UserLoggingsPojo custdetails) {
		return new ClassTeacherMappingDAOIMPL().getAllSubject(vo,custdetails);
	}


	public String saveTeacherSubjectMapInfo(TeacherVo vo, UserLoggingsPojo custdetails, String classSecSpecIds) {
		return new ClassTeacherMappingDAOIMPL().saveTeacherSubjectMapInfo(vo,custdetails,classSecSpecIds);
	}

	public String validateClassSectionSpec(UserLoggingsPojo custdetails, classVo vo) {
		return new ClassTeacherMappingDAOIMPL().validateClassSectionSpec(vo,custdetails);
	}


	public List<classVo> getSubjectTeacherList(UserLoggingsPojo custdetails, TeacherVo vo) {
		return new ClassTeacherMappingDAOIMPL().getSubjectTeacherList(vo,custdetails);
	}




	
}
