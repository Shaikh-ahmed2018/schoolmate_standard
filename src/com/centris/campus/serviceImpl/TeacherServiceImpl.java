package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.TeacherDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.TeacherDaoImpl;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TeacherService;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.StaffAttendanceVo;
import com.centris.campus.vo.TeacherMappingClassesVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class TeacherServiceImpl implements TeacherService {
	private static final Logger logger = Logger.getLogger(TeacherDaoImpl.class);

	TeacherDao daoObj = new TeacherDaoImpl();


	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails(UserLoggingsPojo custid,AllTeacherDetailsVo obj) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getAllTeacherDetails Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getAllTeacherDetails Ending");


		return daoObj.getAllTeacherDetails(custid,obj);

	}
	
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetails1(UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getAllTeacherDetails Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getAllTeacherDetails Ending");

		return daoObj.getAllTeacherDetails1(custdetails);
	}
	
	public ArrayList<AllTeacherDetailsVo> getSearchTeacherDetails(String serchName,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getSearchTeacherDetails Starting");
			
		ArrayList<AllTeacherDetailsVo> teachersList=new ArrayList<AllTeacherDetailsVo>();
		
		try{
			
			teachersList=daoObj.getSearchTeacherDetails(serchName,userLoggingsVo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : getSearchTeacherDetails Ending");

		return teachersList;
	}

	public ArrayList<AllTeacherDetailsVo> searchStaffDetails(
			AllTeacherDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : searchStaffDetails Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : searchStaffDetails Ending");

		return daoObj.searchStaffDetails(obj,userLoggingsVo);
	}

	public boolean deleteStaffDetails(String[] teachercode,TeacherForm obj,String button,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : deleteStaffDetails Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl : deleteStaffDetails Ending");

		return daoObj.deleteStaffDetails(teachercode,obj,button,userLoggingsVo);
	}

	public List<ViewallSubjectsVo> getSubjects(String classId,String locationId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getSubjects  Starting");
		List<ViewallSubjectsVo> subjectlist = new ArrayList<ViewallSubjectsVo>();
		try {
			subjectlist = new TeacherDaoImpl().getSubjects(classId,locationId,userLoggingsVo);
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getSubjects Ending");

		return subjectlist;
	}

	public boolean checkUsername(String username, String teacherId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: checkUsername  Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: checkUsername Ending");
		return new TeacherDaoImpl().checkUsername(username, teacherId,userLoggingsVo);
	}

	public boolean checkMailId(String mailid, String teacherId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: checkMailId Starting");

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: checkMailId Ending");

		return new TeacherDaoImpl().checkMail(mailid, teacherId,userLoggingsVo);
	}

	public boolean teacherregister(TeacherForm obj, UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: teacherregister  Starting");
		boolean result = false;

		try {

			TeacherRegistrationPojo teacherPojo = new TeacherRegistrationPojo();
			String teacherID = null;

			teacherID = IDGenerator.getPrimaryKeyID("campus_teachers",userLoggingsVo);

			teacherPojo.setTeacherId(teacherID);
			teacherPojo.setTfastname(obj.getTfastname());
			teacherPojo.setTlastname(obj.getTlastname());
			teacherPojo.setTqualification(obj.getTqualification());
			teacherPojo.setTeachermobno(obj.getTeachermobno());
			teacherPojo.setUsername(obj.getUsername());
			teacherPojo.setTeacheremail(obj.getTeacheremail());
			teacherPojo.setPrimary(obj.getSubjectprimary());
			teacherPojo.setSecondary(obj.getSubjectsecondary());
			teacherPojo.setProfilepath(obj.getProfilestring());
			teacherPojo.setImgpath(obj.getImagestring());
			teacherPojo.setBankName(obj.getBankName());
			teacherPojo.setAccountNumber(obj.getAccountNumber());
			teacherPojo.setPanNumber(obj.getPanNumber());
			if (obj != null) {
				teacherPojo.setDateofbirth(HelperClass.convertUIToDatabase(obj.getDateofbirth()));
			}
			if (obj != null) {
				teacherPojo.setJoiningdate(HelperClass.convertUIToDatabase(obj.getJoiningdate()));
			}
			teacherPojo.setDesignation(obj.getDesignation());
			teacherPojo.setIdproof(obj.getIdproofstring());
			teacherPojo.setTeachingtype(obj.getTeachingtype());
			teacherPojo.setDept(obj.getDepartment());
			teacherPojo.setCreatedby(obj.getCreatedBy());
			teacherPojo.setGender(obj.getGender());
			teacherPojo.setFathername(obj.getFathername());
			teacherPojo.setMothername(obj.getMothername());
			teacherPojo.setPermanentadd(obj.getPermanentadd().trim());
			teacherPojo.setPresentadd(obj.getPresentadd().trim());
			teacherPojo.setTeacheremail(obj.getTeacheremail());
			teacherPojo.setBlood(obj.getBlood());
			teacherPojo.setRegsitrationNo(obj.getRegno());
			
			teacherPojo.setRole(obj.getRoleId());
			teacherPojo.setUsertype(obj.getUsertype());
			teacherPojo.setDocument1(obj.getAttachment1string());
			teacherPojo.setDocument2(obj.getAttachment2string());
			teacherPojo.setDocument3(obj.getAttachment3string());
			teacherPojo.setDocument4(obj.getAttachment4string());
			teacherPojo.setDocument5(obj.getAttachment5string());
			teacherPojo.setIs_student_studying(obj.getIs_student_studying());
			teacherPojo.setStudentName(obj.getStudentName());
			teacherPojo.setStudent_admission_id(obj.getStudent_admission_id());
			teacherPojo.setFatherMobile(obj.getFatherMobile());
			teacherPojo.setMotherMobile(obj.getMotherMobile());
			teacherPojo.setReportingTo(obj.getReportingTo());
			teacherPojo.setAbbreviate(obj.getAbbreviate());
			teacherPojo.setAadhaarnumber(obj.getAadhaarnumber());
			teacherPojo.setMaritalstatus(obj.getMaritalstatus());
			teacherPojo.setSpousename(obj.getSpousename());
			teacherPojo.setSpouseMobile(obj.getSpouseMobile());
			teacherPojo.setSchoolName(obj.getSchoolName());
			teacherPojo.setAcademicYear(obj.getAcademicYear());
		
			teacherPojo.setLog_audit_session(obj.getLog_audit_session());
			teacherPojo.setCustid(obj.getCustid());
			result = daoObj.teacherregister(teacherPojo,userLoggingsVo);

		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();

		} 

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: teacherregister Ending");

		return result;
	}

	public TeacherRegistrationPojo getTeacherDetails(TeacherRegistrationPojo obj,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getTeacherDetails  Starting");
		TeacherRegistrationPojo obj1 = null;
		try {
			obj1 = new TeacherDaoImpl().getTeacherDetails(obj,userLoggingsVo);
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getTeacherDetails Ending");

		return obj1;

	}

	public String teacherUpdate(TeacherRegistrationPojo obj, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: teacherUpdate  Starting");
		String status = "";
		try {
			status = new TeacherDaoImpl().teacherUpdate(obj,userLoggingsVo);
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: teacherUpdate Ending");

		return status;

	}
	
	
	public ArrayList<TeacherMappingClassesVo> getMappingClasses(String teacherID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappingClasses  Starting");
		ArrayList<TeacherMappingClassesVo> mappingList = new ArrayList<TeacherMappingClassesVo>();
		TeacherDao dao=new TeacherDaoImpl();
		try {
			mappingList = dao.getMappingClasses(teacherID,userLoggingsVo);
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappingClasses Ending");

		return mappingList;

	}
	
	public ArrayList<TeacherMappingClassesVo> getMappedSection(String teacherID,String classId,String SectionID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappedSection  Starting");
		
		ArrayList<TeacherMappingClassesVo> mappingList = new ArrayList<TeacherMappingClassesVo>();
		TeacherDao dao=new TeacherDaoImpl();
		try {
			
			mappingList = dao.getMappedSection(teacherID,classId,SectionID,userLoggingsVo);
			
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappedSection Ending");

		return mappingList;

	}
	
	public ArrayList<TeacherMappingClassesVo> getMappingSubjects(String teacherID,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappingSubjects  Starting");
		
		ArrayList<TeacherMappingClassesVo> mappingList = new ArrayList<TeacherMappingClassesVo>();
		TeacherDao dao=new TeacherDaoImpl();
		try {
			
			mappingList = dao.getMappingSubjects(teacherID,userLoggingsVo);
			
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
			se.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TeacherServiceImpl: getMappingSubjects Ending");

		return mappingList;

	}

	
	public ArrayList<AllTeacherDetailsVo> reportingToList(UserLoggingsPojo userLoggingsVo) {
		TeacherDao dao=new TeacherDaoImpl();
		
		return dao.reportingToList(userLoggingsVo);
	}
	public ArrayList<AllTeacherDetailsVo> StudentAdmissionNumber(String academicYear,UserLoggingsPojo custid) {
		TeacherDao dao=new TeacherDaoImpl();
		
		return dao.StudentAdmissionNumber(academicYear,custid);
	}

	@Override
	public int checkStaffInTDS(String currentUser,UserLoggingsPojo userLoggingsVo) {
		TeacherDao dao=new TeacherDaoImpl();
		
		return dao.checkStaffInTDS(currentUser,userLoggingsVo);
	}

	@Override
	public ArrayList<AllTeacherDetailsVo> getAllTeacherDetailsByLocIdAndDeptId(StaffAttendanceVo vo,UserLoggingsPojo userLoggingsVo) {
        TeacherDao dao=new TeacherDaoImpl();
		return dao.getAllTeacherDetailsByLocIdAndDeptId(vo,userLoggingsVo);
	}

}
