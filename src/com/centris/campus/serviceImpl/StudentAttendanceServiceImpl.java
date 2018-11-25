package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentAttendanceDao;
import com.centris.campus.daoImpl.StudentAttendanceDaoImpl;
import com.centris.campus.pojo.StudentAttendancePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentAttendanceService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.StudentAttendanceVo;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.LstmsStudentPOJO;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentAttendanceReportVo;

public class StudentAttendanceServiceImpl implements StudentAttendanceService{

	private static final Logger logger = Logger.getLogger(StudentAttendanceServiceImpl.class);
	
	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceList(String startDate, String endDate,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				staffAttendanceList =	dao.getStudentsAttendanceList(startDate,endDate,custid);
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendanceDetails(StudentAttendancePojo studentAttPojo,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentAttendanceDetails : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getStudentAttendanceDetails(studentAttPojo,custdetails);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getStudentAttendanceDetails: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public String updateAttendanceStatus(StudentAttendancePojo studentAttPojo,UserLoggingsPojo custdetails) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: updateAttendanceStatus : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			String status=null;
			
			try {
				status =	dao.updateAttendanceStatus(studentAttPojo,custdetails);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: updateAttendanceStatus: Ending");
			
			return status;
		}

	
	
StudentAttendanceDao dao = new StudentAttendanceDaoImpl();
	
	public List<ParentVO> getAllStudentService(String classVal,
			String sectionVal,String locationid,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl:getAllStudentService Starting");
		
		List<ParentVO> student = new ArrayList<ParentVO>();
		
		ParentVO parentvo;
		try {
			
			List<LstmsStudentPOJO> POJOList =dao. getAllStudentDao(classVal,sectionVal,locationid,custid);
			
			for (int i = 0; i < POJOList.size(); i++) {
				
				parentvo = new ParentVO();
				LstmsStudentPOJO lstmsStudentPOJO = (LstmsStudentPOJO) POJOList.get(i);
				parentvo.setStudentFnameVar(lstmsStudentPOJO
						.getStudentFnameVar());
				parentvo.setStudentid((lstmsStudentPOJO.getStudentIdInt()));
				parentvo.setStdAdmisiionNo(lstmsStudentPOJO.getStudentAdmissionnoVar());
				student.add(parentvo);
				
			}
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getAllStudentService Ending");
		
		
		return student;
	}


	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceReportService(
			StudentAttendanceReportVo vo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentAttendanceReportService Starting");
		
		ArrayList<StudentAttendanceReportVo> studentlist = null;
		
		try {
			
			studentlist = dao. getStudentAttendanceReportDao(vo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentAttendanceReportService Ending");
		return studentlist;
	}


	
	public ArrayList<StudentAttendanceReportVo> getStudentAttendanceListReportService(
			StudentAttendanceReportVo stuvo,UserLoggingsPojo userLoggingsVo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentAttendanceListReportService Starting");
		
		ArrayList<StudentAttendanceReportVo> studentlist = null;
		
		try {
			
			studentlist = dao. getStudentAttendanceListReportDao(stuvo, userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentAttendanceListReportService Ending");
		
		
		
		return studentlist;
	}


	
	public StreamDetailsVO getStreamNameService(String stream,UserLoggingsPojo custid) {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStreamNameService Starting");
		
		StreamDetailsVO selected = null;
		try {
			
			selected=dao. getStreamNameDaoImpl(stream,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStreamNameService Ending");
		
		return selected;
	}


	
	public ClassPojo getClassNameService(String classname,UserLoggingsPojo custid) {
	
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getClassNameService Starting");
		
		ClassPojo selected = null;
		try {
			
			selected=dao. getClassNameDaoImpl(classname,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getClassNameService Ending");
		
		return selected;
	}


	
	public SectionPojo getSectionNameService(String sectionname,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getSectionNameService Starting");
		
		SectionPojo selected = null;
		try {
			
			selected=dao. getSectionNameDaoImpl(sectionname,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getSectionNameService Ending");
		
		return selected;
	}


	
	public ParentVO getStudentNameService(String student,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getSectionNameService Starting");
		
		ParentVO selected = null;
		try {
			
			selected=dao. getStudentNameDaoImpl(student,custid);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getSectionNameService Ending");
		
		return selected;
	}

	@Override
	public StudentAttendanceVo getStudentPeriodAttendance(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : getStudentPeriodAttendance Starting");
		
		StudentAttendanceVo selected = null;
		try {
			
			selected=dao. getStudentPeriodAttendance(AttendancePojo,custdetails);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentPeriodAttendance Ending");
		
		return selected;
	}

	@Override
	public String updateStudentPeriodAtt(StudentAttendancePojo AttendancePojo, UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl : updateStudentPeriodAtt Starting");
		
		String status = null;
		try {
			
			status=dao. updateStudentPeriodAtt(AttendancePojo, custdetails);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :updateStudentPeriodAtt Ending");
		
		return status;
	}

	@Override
	public ArrayList<StudentAttendanceVo> getteacherByClass(String classId, String sectionId,UserLoggingsPojo custid) {
		
		return dao.getteacherByClass(classId,sectionId,custid);
	}

	@Override
	public ArrayList<StudentAttendanceVo> getClassSpec(String classId,String locationId,UserLoggingsPojo custid) {
		return dao.getClassSpec(classId,locationId,custid);
	}

	@Override
	public StudentAttendanceVo editAttendance(StudentAttendancePojo pojo, UserLoggingsPojo custdetails) {
		return dao.editAttendance(pojo,  custdetails);
	}

	@Override
	public List<ParentVO> getStudentByTransport(String classId, String sectionId,UserLoggingsPojo custid) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl:getStudentByTransport Starting");
		
		List<ParentVO> student = new ArrayList<ParentVO>();
		
		
		ParentVO parentvo;
		try {
			
			
			List<LstmsStudentPOJO> POJOList =dao. getStudentByTransport(classId,sectionId,custid);
			
			for (int i = 0; i < POJOList.size(); i++) {
				
				parentvo = new ParentVO();
				LstmsStudentPOJO lstmsStudentPOJO = (LstmsStudentPOJO) POJOList.get(i);
				parentvo.setStudentFnameVar(lstmsStudentPOJO
						.getStudentFnameVar());
				parentvo.setStudentid((lstmsStudentPOJO.getStudentIdInt()));
				parentvo.setStdAdmisiionNo(lstmsStudentPOJO.getStudentAdmissionnoVar());
				student.add(parentvo);
				
			}
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl :getStudentByTransport Ending");
		
		
		return student;
	}

	@Override
	public ArrayList<StudentAttendanceVo> searchStudentsAttendanceList(String locationId, String accYear,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: searchStudentsAttendanceList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.searchStudentsAttendanceList(locationId,accYear,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: searchStudentsAttendanceList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendenceByClassList(String locationid, String accyear, String classname,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendenceByClassList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendenceByClassList(locationid,accyear,classname,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendenceByClassList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendenceByClassSectionList(String locationid, String accyear,
			String classname, String sectionid,String specialization,String startdate,String enddate,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendenceByClassSectionList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList = dao.getAttendenceByClassSectionList(locationid,accyear,classname,sectionid,specialization,startdate,enddate,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendenceByClassSectionList: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getTeacherList(String locationid,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getTeacherList : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffList =	dao.getTeacherList(locationid,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getTeacherList: Ending");
			
			return staffList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendanceListByTeacher(String locationid, String accyear,
			String classname,String sectionid, String teacherid,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendanceListByTeacher : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendanceListByTeacher(locationid,accyear,classname,sectionid,teacherid,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendanceListByTeacher: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public List<StudentAttendanceVo> getAttendanceListByDate(String startdate, String enddate,UserLoggingsPojo custid) {
		   
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendanceListByDate : Starting");
			
			StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
			ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
			
			try {
				
				staffAttendanceList =	dao.getAttendanceListByDate(startdate,enddate,custid);
				
			}catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
				e1.printStackTrace();
			} 
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentAttendanceServiceImpl: getAttendanceListByDate: Ending");
			
			return staffAttendanceList;
		}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentsAttendanceListByDownload(String startdate, String endDate,
			String acyearid, String locId, String classId, String section, UserLoggingsPojo custid) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceListByDownload : Starting");
		
		StudentAttendanceDao dao=new StudentAttendanceDaoImpl();
		ArrayList<StudentAttendanceVo> staffAttendanceList=new ArrayList<StudentAttendanceVo>();
		
		try {
			
			staffAttendanceList = dao.getStudentsAttendanceListByDownload(startdate,endDate,acyearid,locId,classId,section,custid);
			
		}catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			e1.printStackTrace();
		} 
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentAttendanceServiceImpl: getStudentsAttendanceListByDownload: Ending");
		
		return staffAttendanceList;
	}

	@Override
	public String getAccyID(String academic_year,UserLoggingsPojo custId, String location_id ) {
		
		return dao.getAccyID(academic_year, custId,location_id);
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentDetailsAcademicWise(UserLoggingsPojo userLoggingsVo,String location) {
		
		return dao.getStudentDetailsAcademicWise( userLoggingsVo, location);
	}


	@Override
	public int getperiodcount(String locId, String clsId, UserLoggingsPojo custdetails) {
		
		return dao.getperiodcount(locId,clsId,custdetails);
	}

	@Override
	public String NewupdateAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails) {
	
		return dao.NewupdateAttendanceStatus(periodId,attendancepojo,custdetails);
	}


	@Override
	public ArrayList<StudentAttendanceVo> todayStudentAttendance(UserLoggingsPojo userLoggingsVo,String location, String accYear) {
		
		return dao.todayStudentAttendance(userLoggingsVo, location,  accYear);
	}

	@Override
	public ArrayList<StudentAttendanceVo> houseWiseStudent(UserLoggingsPojo userLoggingsVo , String location, String accYear) {
		
		return dao.houseWiseStudent(userLoggingsVo, location,  accYear);
	}

	@Override
	public ArrayList<StudentAttendanceVo> getStudentAttendance(StudentAttendancePojo studentPojo,UserLoggingsPojo custdetails) {
		
		return dao.getStudentAttendance(studentPojo,custdetails);
	}

	@Override
	public String updateNewAttendanceStatus(String[] periodId, StudentAttendancePojo attendancepojo,
			UserLoggingsPojo custdetails, String[] statusid) {
		
		return dao.updateNewAttendanceStatus(periodId,attendancepojo,custdetails,statusid);
	}


	
}
