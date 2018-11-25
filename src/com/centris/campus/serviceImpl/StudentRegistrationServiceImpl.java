/**
 * 
 */
package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.StudentRegistrationDao;
import com.centris.campus.daoImpl.StudentRegistrationDaoImpl;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.PageFilterpojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StudentRegistrationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.PageFilterVo;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.StageMasterVo;
import com.centris.campus.vo.StudentAttendanceVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TransportTypeVO;
import com.centris.campus.vo.ViewallSubjectsVo;
import com.centris.campus.vo.registrationvo;

/**
 * @author sathish
 * 
 */
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
	private static final Logger logger = Logger
			.getLogger(StudentRegistrationServiceImpl.class);

	@Override
	public List<StudentRegistrationVo> getAcademicYear( UserLoggingsPojo userLoggingsVo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getAcademicYear  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getAcademicYear  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getAcademicYear(userLoggingsVo);
	}

	@Override
	public Map<String, String> saveStudentRegistration(StudentRegistrationForm studentRegistrationForm, UserLoggingsPojo dbdetails) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:saveStudentRegistration  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:saveStudentRegistration Ending");
		return new StudentRegistrationDaoImpl().saveStudentRegistration(studentRegistrationForm,dbdetails);
	}

	@Override
	public List<StudentRegistrationVo> studentSearch(
			StudentRegistrationVo studentRegistrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearch  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearch  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearch(studentRegistrationVo, userLoggingsVo);
	}

	@Override
	public List<StudentRegistrationVo> searchStudentResult(
			StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:searchStudentResult  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:searchStudentResult  Ending");
		return new StudentRegistrationDaoImpl()
				.searchStudentResult(registrationVo,userLoggingsVo);
	}

	public String modifyStudentDetails(StudentRegistrationForm modifyform, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl: modifyStudentDetails Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl: modifyStudentDetails  Ending");
		// TODO Auto-generated method stub

		return new StudentRegistrationDaoImpl().modifyStudentDetails(modifyform,dbdetails);
	}

	@Override
	public List<StudentRegistrationVo> getStudentquota(UserLoggingsPojo pojo) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getStudentquota   Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:getStudentquota  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getStudentquota(pojo);
	}

	@Override
	public List<StudentRegistrationVo> studentSearchByParent(
			StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearchByParent   Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:studentSearchByParent Ending");

		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearchByParent(registrationVo,pojo);
	}

	@Override
	public List<StudentRegistrationVo> getStudentCaste(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentCaste  Starting");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentCaste  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getStudentCaste(userLoggingsVo);
	}

	@Override
	public int validateDuplicateData(StudentRegistrationForm formObj,String type, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : validateDuplicateData  Starting");
		StudentRegistrationDao obj = new StudentRegistrationDaoImpl();
		int count = 0;
		try {
			count = obj.validateDuplicateData(formObj, type,dbdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : validateDuplicateData  Ending");
		return count;
	}

	public ArrayList<StudentInfoVO> getAllStudentsDetails(String classname,
			String accodamicyr,UserLoggingsPojo userLoggingsVo) {

		return new StudentRegistrationDaoImpl().getAllStudentsDetails(
				classname, accodamicyr,userLoggingsVo);
	}

	public String getTransportCategoryType(String trnsportTypeId, UserLoggingsPojo pojo) {

		return new StudentRegistrationDaoImpl()
				.getTransportCategoryType(trnsportTypeId,pojo);
	}

	public List<StudentRegistrationVo> studentSearchbyClass(
			StudentRegistrationVo studentRegistrationVo,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentSearchbyClass  Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentSearchbyClass  Ending");
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl()
				.studentSearchbyClass(studentRegistrationVo,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getChildCategory(String schoolLocation, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getChildCategory  Starting");
		List<StudentRegistrationVo> categorylist = null;
		try {
			categorylist = new StudentRegistrationDaoImpl().getChildCategory(schoolLocation,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getChildCategory  Ending");
		// TODO Auto-generated method stub

		return categorylist;
	}

	public List<StudentRegistrationVo> getClassDetails(String catecory, String location, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetails(catecory,location,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getSection(String classval, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getSection(classval,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSection  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getConcessionDetails(UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConcessionDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConcessionDetails(userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConcessionDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<TransportTypeVO> transportypedetails(UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : transportypedetails  Starting");
		List<TransportTypeVO> list = null;
		try {
			list = new StudentRegistrationDaoImpl().transportypedetails(pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : transportypedetails  Ending");
		// TODO Auto-generated method stub

		return list;

	}

	public List<StageMasterVo> getStageDetails(UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStageDetails  Starting");
		List<StageMasterVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStageDetails(pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStageDetails  Ending");
		// TODO Auto-generated method stub
		return list;

	}

	public List<StudentRegistrationVo> getStudentDetails1(String userType, String userCode, String academic_year, String schoolLocation, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails1  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getStudentDetails1(userType,userCode,academic_year,schoolLocation,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails1  Ending");
	

		return list;
	}

	public boolean deactivateStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:deactivateStudent Starting");

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl:deactivateStudent  Ending");
		return new StudentRegistrationDaoImpl().deactivateStudent(registrationVo,pojo);
	}


	public ArrayList<ViewallSubjectsVo> getSubjectByClass(String classID, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSubjectByClass Starting");

		ArrayList<ViewallSubjectsVo> subjectList = new ArrayList<ViewallSubjectsVo>();

		try {

			subjectList = new StudentRegistrationDaoImpl()
					.getSubjectByClass(classID,custdetails);
		} catch (Exception e) {

			e.printStackTrace();
		}

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getSubjectByClass  Ending");
		return subjectList;

	}
	
	
	public StudentRegistrationVo editStudent(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo){
		// TODO Auto-generated method stub
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editStudent  Starting");
		
		StudentRegistrationVo studentVo=new StudentRegistrationVo();
		
		try{
			
			studentVo=new StudentRegistrationDaoImpl().editStudent(registrationVo,pojo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editStudent  Ending");
		return studentVo;
	}
	public List<registrationvo> StudentDetails(String academic_year, String location, UserLoggingsPojo dbdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		List<registrationvo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().StudentDetails(academic_year,location,dbdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}


	public List<StudentRegistrationVo> getTranscationcategory(String transloc)
	{
		// TODO Auto-generated method stub
		return new StudentRegistrationDaoImpl().getTranscationcategory(transloc);
	}


	public List<StudentRegistrationVo> getStudentDetails(
			String searchName, String location, String academic_year, String schoolLocation,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		
		List<StudentRegistrationVo> searchlist = null;
		
		try {
			
			searchlist = new StudentRegistrationDaoImpl().getStudentDetails(searchName,location,userLoggingsVo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails Ending");
		
		return searchlist;
	}

	public List<StudentRegistrationVo> getClassDetailWithOutStream(String location, UserLoggingsPojo pojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailWithOutStream Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailWithOutStream(location,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailWithOutStream Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailsByTemp() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailsByTemp  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailsByTemp();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailsByTemp  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailSrSecondary() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailSrSecondary  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailSrSecondary();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailSrSecondary  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTempRegistrationDetails(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTempRegistrationDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getTempRegistrationDetails(registrationVo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTempRegistrationDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getClassDetailMontessori() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailMontessori  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassDetailMontessori();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassDetailMontessori  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String userType, String userCode, String academic_year) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getTransportStudentDetails(userType,userCode,academic_year);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> getTransportStudentDetails(
			String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getTransportStudentDetails(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getTransportStudentDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> TCGeneration1(String academic_year, String location) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : TCGeneration1  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().TCGeneration1(academic_year,location);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : TCGeneration1  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getClassByLocation(String locationid, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassByLocation  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getClassByLocation(locationid,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getClassByLocation  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getAllStudentDetails(String academic_year, String location, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllStudentDetails(academic_year,location,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllStudentDetails  Ending");
		// TODO Auto-generated method stub

		return list;
	}

	public List<StudentRegistrationVo> studentFullList(String studentId,
			String accYear, String locationId, 	UserLoggingsPojo pojo ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Starting");
		
		List<StudentRegistrationVo> student_Vo = null;
		
		try{
			
			student_Vo=new StudentRegistrationDaoImpl().studentFullList(studentId,accYear,locationId,pojo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : studentFullList  Ending");
		return student_Vo;
	}

	public List<StudentRegistrationVo> getStudentDetailsList(String locationid, String accyear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsList  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDetailsList(locationid,accyear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsList  Ending");
	

		return list;
}


	public List<StudentRegistrationVo> getStudentDetailsLByFilter(String locationid, String accyear, String classname, String status, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDetailsLByFilter(locationid,accyear,classname,status,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFilter  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid, String status, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentListBySection(locationid,accyear,classname,sectionid,status,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");
	

		return list;
	}
	
	
	public List<StudentRegistrationVo> getStudentListBySections(String locationid, String accyear, String classname,
			String sectionid,String sortingby,String orderby, String status,String housename, UserLoggingsPojo custdetails, String search, String myorder,int show_per_page, int startPoint, String specialization) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySections  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentListBySections(locationid,accyear,classname,sectionid,sortingby,orderby,status,housename,custdetails,search,myorder, show_per_page,  startPoint,  specialization);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySections  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> singleStudentDetailsList(String studentId, String accYear, String locationId, String flag, UserLoggingsPojo pojo) {
		
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationServiceImpl : singleStudentDetailsList  Starting");
			List<StudentRegistrationVo> list = null;
			try {
				list = new StudentRegistrationDaoImpl().singleStudentDetailsList(studentId,accYear,locationId,flag,pojo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentRegistrationServiceImpl : singleStudentDetailsList  Ending");
		

			return list;

		}

	public List<StudentRegistrationVo> getStudentLocationList(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentLocationList(academic_year,location,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentLocationList  Ending");
	

		return list;
	}

	public List<StudentRegistrationVo> searchAllAcademicYearDetails(String locationId, String accYear, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAcademicYearDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().searchAllAcademicYearDetails(locationId,accYear,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAcademicYearDetails  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByList(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByList  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByAccYear(String searchTerm, String accyear) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchListByAccYear(searchTerm,accyear);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByAccYear  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchListByLocation(String searchTerm, String locationid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByLocation  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchListByLocation(searchTerm,locationid,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchListByLocation  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByFilter(String searchTerm, String locationid, String accyear, String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFilter  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByFilter(searchTerm,locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFilter  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByClass(String searchTerm, String locationid, String accyear,String classname) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByClass  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByClass(searchTerm,locationid,accyear,classname);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByClass  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, String housename,
			String status, String sortby, String orderbyAdmissionAndAlpha, String orderbyGender, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByAllFilter  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,housename,status,sortby,orderbyAdmissionAndAlpha,orderbyGender,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByAllFilter  Ending");
		
		return list;
	}

	public List<FeeReportDetailsVo> getfeedetails(String studentId,
			String accYear, String locationId) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getfeedetails  Starting");
		List<FeeReportDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getfeedetails(studentId,accYear,locationId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getfeedetails  Ending");
	

		return list;
	}

	public String AddConfidentialDetails(String entryDate,
			String comments, String studentId, String accyear, String locationid,String userName, String logactivity, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : AddConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : AddConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().AddConfidentialDetails(entryDate,comments,studentId,accyear,locationid,userName,logactivity,pojo);
	}

	public List<StudentRegistrationVo> getConfidentialReportStudents(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfidentialReportStudents  Starting");
		
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfidentialReportStudents(academic_year,location,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfidentialReportStudents  Ending");
		
		return list;
	}

	public List<StudentRegistrationVo> searchAllAccYearDetailsConfReport(String locationId, String accYear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAccYearDetailsConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().searchAllAccYearDetailsConfReport(locationId,accYear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : searchAllAccYearDetailsConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfDetailsLByFilter(String locationid, String accyear, String classname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfDetailsLByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfDetailsLByFilter(locationid,accyear,classname,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfDetailsLByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentBySectionForConfReport(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentBySectionForConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentBySectionForConfReport(locationid,accyear,classname,sectionid,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentBySectionForConfReport  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getStudentSearchByConfReport(
			String searchTerm, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByConfReport  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentSearchByConfReport(searchTerm, userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByConfReport  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getIDCard(String studentId,
			String accYear, String locationId, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getIDCard  Starting");
		
		List<StudentRegistrationVo> student_Vo = null;
		
		try{
			
			student_Vo=new StudentRegistrationDaoImpl().getIDCard(studentId,accYear,locationId,userLoggingsVo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getIDCard  Ending");
		return student_Vo;
	}
	
	public String saveStudentPromotion(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : saveStudentPromotion Starting");

		String student_Details = null;
		StudentRegistrationDao daoObj = new StudentRegistrationDaoImpl();
		try {

			student_Details = daoObj.saveStudentPromotion(registrationVo,pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : saveStudentPromotion Ending");

		return student_Details;
	}

	public String getNextAcademicYearId(String academicyearid, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getNextAcademicYearId  Starting");
		String academicyear = "";
		try {
			academicyear = new StudentRegistrationDaoImpl().getNextAcademicYearId(academicyearid,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getNextAcademicYearId  Ending");

		return academicyear;
	}

	public List<StudentRegistrationVo> getPromotedListDetails(StudentRegistrationVo regVo, UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getPromotedListDetails(regVo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedListDetails  Ending");

		return list;

	}

	public List<StudentRegistrationVo> singleStudentConfDetails(String studentId, String accYear, String locationId,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().singleStudentConfDetails(studentId,accYear,locationId,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : singleStudentConfDetails  Ending");

		return list;

	}

	public List<StudentRegistrationVo> getConfSearchListByAccYear(String searchTerm, String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByAccYear  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchListByAccYear(searchTerm,accyear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByAccYear  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchListByLocation(String searchTerm, String locationid, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByLocation  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchListByLocation(searchTerm,locationid,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchListByLocation  Ending");

		return list;
	
	}

	public List<ExaminationDetailsVo> getExaminationDetails(StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationDetails  Starting");
		List<ExaminationDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getExaminationDetails(svo,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationDetails  Ending");

		return list;

	}

	public List<ExaminationDetailsVo> getExaminationCodes(String loc_id,
			String acyear_id, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationCodes  Starting");
		List<ExaminationDetailsVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getExaminationCodes(loc_id,acyear_id,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationCodes  Ending");

		return list;

	}

	public String getExamName(String examcode, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExamName  Starting");
		String exmname = null;
		try {
			exmname = new StudentRegistrationDaoImpl().getExamName(examcode,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExamName  Ending");

		return exmname;

	}

	public List<StudentAttendanceVo> getStudentAttendance(String stud_id, String accId, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentAttendance  Starting");
		List<StudentAttendanceVo> stuatten = null;
		try {
			stuatten = new StudentRegistrationDaoImpl().getStudentAttendance(stud_id,accId,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentAttendance  Ending");

		return stuatten;

	}

	public List<StudentAttendanceVo> getStudentAppraisal(
			StudentRegistrationVo spvo, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentAppraisal  Starting");
		List<StudentAttendanceVo> stuaprsal = null;
		try {
			stuaprsal = new StudentRegistrationDaoImpl().getStudentAppraisal(spvo,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentAppraisal  Ending");

		return stuaprsal;

	}

	public List<ExaminationDetailsVo> getExaminationDetailsBasedonExams(
			StudentRegistrationVo svo, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationDetailsBasedonExams  Starting");
		List<ExaminationDetailsVo> stuaprsal = null;
		try {
			stuaprsal = new StudentRegistrationDaoImpl().getExaminationDetailsBasedonExams(svo,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getExaminationDetailsBasedonExams  Ending");

		return stuaprsal;

	}
	public List<PageFilterVo> getIDCardStaff(PageFilterpojo filterpojo, UserLoggingsPojo userLoggingsVo) {
		 return new StudentRegistrationDaoImpl().getIDCardStaff(filterpojo,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getConfSearchByFilter(String searchTerm,String locationid, String accyear,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByFilter(searchTerm,locationid,accyear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByFilter  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByClass(String searchTerm,String locationid, String accyear, String classname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByClass  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByClass(searchTerm,locationid,accyear,classname,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByClass  Ending");

		return list;
	}

	public List<StudentRegistrationVo> getConfSearchByAllFilter(String searchTerm, String locationid, String accyear,
			String classname, String sectionid, UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByAllFilter  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getConfSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,custdetails);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getConfSearchByAllFilter  Ending");

		return list;
	}

	public String deleteConfidentialDetails(String deleteId, String remarks, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : deleteConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : deleteConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().deleteConfidentialDetails(deleteId,remarks,log_audit_session,pojo);
	}

	public String editConfidentialDetails(String entryDate,String comments, String editId, String log_audit_session, UserLoggingsPojo pojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : editConfidentialDetails  Ending");
		
		return new StudentRegistrationDaoImpl().editConfidentialDetails(entryDate,comments,editId,log_audit_session,pojo);
	}

	public List<StudentRegistrationVo> getStudentDetails(String userType, String userCode, String academic_year,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			System.out.println("inside service impl");
			list = new StudentRegistrationDaoImpl().getStudentDetails(userType,userCode,academic_year,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetails  Ending");


		return list;
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByList(
			String searchTerm,String accyear,UserLoggingsPojo userLoggingsVo ) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchByList  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchByList  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchByList(searchTerm,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchListByAccYear(
			String searchTerm, String accyear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchListByAccYear  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchListByAccYear  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchListByAccYear(searchTerm,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentFeeSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, UserLoggingsPojo userLoggingsVo)  {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchByAllFilter  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentFeeSearchByAllFilter  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentFeeSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeClass(
			String searchTerm, String locationid, String accyear,
			String classname,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFeeClass  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFeeClass  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentSearchByFeeClass(searchTerm,locationid,accyear,classname,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentSearchByFeeFilter(
			String searchTerm, String locationid, String accyear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFeeFilter  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentSearchByFeeFilter  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentSearchByFeeFilter(searchTerm,locationid,accyear,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentDetailsLByFeeFilter(
			String locationid, String accyear, String classname, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFeeFilter  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDetailsLByFeeFilter  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentDetailsLByFeeFilter(locationid,accyear,classname,userLoggingsVo);
	}

	public List<StudentRegistrationVo> getStudentListByFeeSection(
			String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListByFeeSection  Starting");
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListByFeeSection  Ending");
		
		return new StudentRegistrationDaoImpl().getStudentListByFeeSection(locationid,accyear,classname,sectionid,userLoggingsVo);
	}

	
	
	public List<StudentRegistrationVo> getAllAcademicYearDemotedDetails(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearDemotedDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllAcademicYearDemotedDetails(locationId,accYear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearDemotedDetails  Ending");

		return list;
	}
	
	public List<StudentRegistrationVo> getStudentDemotedSearchByList(String searchTerm) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDemotedSearchByList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentDemotedSearchByList(searchTerm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentDemotedSearchByList  Ending");

		return list;
	}

	
	public List<StudentRegistrationVo> getAllAcademicYearPromotedDetails(String locationId, String accYear, UserLoggingsPojo userLoggingsVo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearPromotedDetails  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getAllAcademicYearPromotedDetails(locationId,accYear,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getAllAcademicYearPromotedDetails  Ending");

		return list;
	}




	public String toCheckNextYearAvailable(String accyear, UserLoggingsPojo pojo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : toCheckNextYearAvailable  Starting");
		String accyearid = null;
		try {
			accyearid = new StudentRegistrationDaoImpl().toCheckNextYearAvailable(accyear,pojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : toCheckNextYearAvailable  Ending");

		return accyearid;

	}
	
public List<StudentRegistrationVo> getPromotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedClassSectionList  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getPromotedClassSectionList(regVo,userLoggingsVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getPromotedClassSectionList  Ending");

		return list;

	}
	public String toCheckNextClassAvailable( String toclass,String locationId, UserLoggingsPojo pojo) {
		return new StudentRegistrationDaoImpl().toCheckNextClassAvailable(toclass,locationId,pojo);
	}
	
	public List<StudentRegistrationVo> getAllStudentPromotionClassSectionDetails(String academic_year, String location,String classId, String sectionid, UserLoggingsPojo custdetails) {
		return new StudentRegistrationDaoImpl().getAllStudentPromotionClassSectionDetails(academic_year,location,classId,sectionid,custdetails);
	}


public List<StudentRegistrationVo> getPromotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedClassList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getPromotedClassList(regVo,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getPromotedClassList  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedListDetails(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedListDetails  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedListDetails(regVo,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedListDetails  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedClassSectionList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassSectionList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedClassSectionList(regVo, userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassSectionList  Ending");

	return list;

}

public StudentRegistrationVo getStudentPromotedChange(String studentId, String accYear, String locationId, String promotedId, UserLoggingsPojo custdetails) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedChange  Starting");
	StudentRegistrationVo list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedChange(studentId,accYear,locationId,promotedId,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedChange  Ending");

	return list;

}

public List<StudentRegistrationVo> getDemotedClassList(StudentRegistrationVo regVo,UserLoggingsPojo userLoggingsVo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getDemotedClassList(regVo,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getDemotedClassList  Ending");

	return list;

}
public StudentRegistrationVo getStudentWisePromotion(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWisePromotion  Starting");
	StudentRegistrationVo list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentWisePromotion(studentId,accYear,locationId,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWisePromotion  Ending");

	return list;

}

public List<StudentRegistrationVo> getStudentPromotedSearchByList(String searchTerm, String status, UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByList(searchTerm,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByList  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchListByAccYear(String searchTerm, String accyear, String locationid, String classname, String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByAccYear  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchListByAccYear(searchTerm,accyear,locationid,classname,sectionid,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByAccYear  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchListByLocation(String searchTerm, String locationid, String status,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByLocation  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchListByLocation(searchTerm,locationid,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchListByLocation  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByFilter(String searchTerm, String locationid, String accyear, String status,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByFilter  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByFilter(searchTerm,locationid,accyear,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByFilter  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByClass(String searchTerm, String locationid, String accyear,String classname, String status,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByClass  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByClass(searchTerm,locationid,accyear,classname,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByClass  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentPromotedSearchByAllFilter(String searchTerm, String locationid, String accyear,String classname, String sectionid, String status,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByAllFilter  Starting");

	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentPromotedSearchByAllFilter(searchTerm,locationid,accyear,classname,sectionid,status,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentPromotedSearchByAllFilter  Ending");

	return list;
}


public List<StudentRegistrationVo> getStudentDetails(String searchName, String location,UserLoggingsPojo dbinfo) {
	return new StudentRegistrationDaoImpl().getStudentDetails(searchName,location,dbinfo);
}

public List<StudentRegistrationVo> getStudentDetailsexcel(String searchTerm, String location, String currentaccyear,UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationDaoImpl().getStudentDetailsexcel(searchTerm,location,currentaccyear,userLoggingsVo);
}



public List<StudentRegistrationVo> getStudentDetailsByJs(
		StudentRegistrationVo data, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().getStudentDetailsByJs(data,pojo);
}

public String toGetStreamName(String toClass, String locationId,UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationDaoImpl().toGetStreamName(toClass,locationId,userLoggingsVo);
}
public String getlocationteacher(String userCode) {
	return new StudentRegistrationDaoImpl().getlocationteacher(userCode);
}

public List<StudentRegistrationVo> individualStudentSearch(String studentId, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().individualStudentSearch(studentId,pojo);
}

public List<StudentRegistrationVo> getLanguageList(String classId, String schoolLocation) {
	return new StudentRegistrationDaoImpl().getLanguageList(classId,schoolLocation);
}

public String AddApparisalDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	
	return new StudentRegistrationDaoImpl(). AddApparisalDetails(vo,pojo);
}

public List<StudentRegistrationVo> singleStudentDetailReport(String studentId,String accyear, String locationid, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().singleStudentDetailReport(studentId,accyear,locationid,pojo);
}

public String deleteApparaisalDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl(). deleteApparaisalDetails(deleteId,log_audit_session,pojo);
}


public List<StudentRegistrationVo> getStudentWithheldList(String academic_year, String location, UserLoggingsPojo userLoggingsVo ) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWithheldList  Starting");
	
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getConfidentialReportStudents(academic_year,location, userLoggingsVo );
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentWithheldList  Ending");
	
	return list;
}

public List<StudentRegistrationVo> singleStudentWithHeld(String studentId, String accYear, String locationId) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeld  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().singleStudentWithHeld(studentId,accYear,locationId);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeld  Ending");

	return list;
}

public String AddWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : AddWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : AddWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().AddWithHeldDetails(studentvo,pojo);
}

public String EditWithHeldDetails(StudentRegistrationVo studentvo, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : EditWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : EditWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().EditWithHeldDetails(studentvo,pojo);
}

public List<StudentRegistrationVo> singleStudentWithHeldDetailsList(String studentId, String accyear,
		String locationid, String flag, String status, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeldDetailsList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().singleStudentWithHeldDetailsList(studentId,accyear,locationid,flag,status,pojo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentWithHeldDetailsList  Ending");

	return list;
}

public String deleteWithHeldDetails(String deleteId, String log_audit_session, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : deleteWithHeldDetails  Starting");
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : deleteWithHeldDetails  Ending");
	
	return new StudentRegistrationDaoImpl().deleteWithHeldDetails(deleteId,log_audit_session,pojo);
}

public List<StudentRegistrationVo> getStudentListBySpecialization(ExaminationDetailsVo details,UserLoggingsPojo userLoggingsVo) 
{
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization  Starting");
	
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentListBySpecialization(details,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListBySpecialization Ending");
	
	return list;
}

public String getAdmissionNo(String locationId, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getAdmissionNo  Starting");
	
	String admissionno = null;
	try {
		admissionno = new StudentRegistrationDaoImpl().getAdmissionNo(locationId,pojo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getAdmissionNo  Ending");
	
	return admissionno;
}


public List<StudentRegistrationVo> getIDCardPhotoSheet(String sectionId,String classId,
		String accYear, String locationId, UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getIDCardPhotoSheet  Starting");
	
	List<StudentRegistrationVo> student_Vo = null;
	
	try{
		
		student_Vo=new StudentRegistrationDaoImpl().getIDCardPhotoSheet(sectionId,classId,accYear,locationId,userLoggingsVo);
		
	}catch(Exception e){
		
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getIDCardPhotoSheet  Ending");
	return student_Vo;
}

public List<StudentRegistrationVo> ShowStudentAddress(String studentId,
		String accYear, String locationId, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : ShowStudentAddress  Starting");
	
	List<StudentRegistrationVo> student_Vo = null;
	
	try{
		
		student_Vo=new StudentRegistrationDaoImpl().ShowStudentAddress(studentId,accYear,locationId,pojo);
		
	}catch(Exception e){
		
		e.printStackTrace();
		logger.error(e.getMessage(), e);
	}
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : ShowStudentAddress  Ending");
	return student_Vo;
}


public String generateStudentTC(String[] splitlocation, String[] splitaccyear, String[] splitstudentid,String[] splitadmid,String[] splitclassid,String examdetails,String reason,String remarks,String result, String log_audit_session, UserLoggingsPojo custdetails, StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : generateStudentTC  Starting");
	String list = null;
	try {
		list = new StudentRegistrationDaoImpl().generateStudentTC(splitlocation,splitaccyear,splitstudentid,splitadmid,splitclassid,examdetails,reason,remarks,result,log_audit_session,custdetails,vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : generateStudentTC  Ending");


	return list;
}

public List<StudentRegistrationVo> TCGeneration(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : TCGeneration  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().TCGeneration(academic_year,location,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : TCGeneration  Ending");


	return list;
}

public List<StudentRegistrationVo> getStudentList1(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentList(academic_year,location,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentList  Ending");


	return list;
}


public List<StudentRegistrationVo> getNotGenTC(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getNotGenTC  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getNotGenTC(vo,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getNotGenTC  Ending");


	return list;
}

public List<StudentRegistrationVo> getStudentListByTC(String locationid, String accyear, String classname,
		String sectionid,String sortingby,String orderby, UserLoggingsPojo custdetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListByTC  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentListByTC(locationid,accyear,classname,sectionid,sortingby,orderby,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListByTC  Ending");


	return list;
}


public List<StudentRegistrationVo> TransferCertificateDownload(String locationId, String accyear, String studentid, String admid, String classid, UserLoggingsPojo cpojo){
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : TransferCertificateDownload  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().TransferCertificateDownload(locationId,accyear,studentid,admid,classid,cpojo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : TransferCertificateDownload  Ending");


	return list;
}

public List<StudentRegistrationVo> GenTCListFilter(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : GenTCListFilter  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().GenTCListFilter(vo,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : GenTCListFilter  Ending");


	return list;
}

public List<StudentRegistrationVo> GenTCListSearch(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : GenTCListSearch  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().GenTCListSearch(vo,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : GenTCListSearch  Ending");


	return list;
}

public String updateStudentPromotion(StudentRegistrationVo registrationVo, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : updateStudentPromotion  Starting");
	List<StudentRegistrationVo> list = null;
	String msg=null;
	try {
		msg = new StudentRegistrationDaoImpl().updateStudentPromotion(registrationVo,pojo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : updateStudentPromotion  Ending");

	return msg;
}

public List<StudentRegistrationVo> getStudentdisciplinaryactionListDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().getStudentdisciplinaryactionListDetails(vo,pojo);
}

public String activedeleteConfidentialDetails(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().activedeleteConfidentialDetails(vo,pojo);
}

public List<StudentRegistrationVo> searchAllAcademicYearDetails(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationDaoImpl().searchAllAcademicYearDetails(vo,custdetails);
}
 

public List<StudentRegistrationVo> getStudentDisciplinaryActionSearchByList(StudentRegistrationVo vo, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().getStudentDisciplinaryActionSearchByList(vo,pojo);
}
 

public List<StudentRegistrationVo> newClassList(String location, String preClass, UserLoggingsPojo pojo) {
	return new StudentRegistrationDaoImpl().newClassList(location,preClass,pojo);
}

public List<StudentRegistrationVo> getStudentListBySection(String locationid, String accyear, String classname,
			String sectionid,UserLoggingsPojo custdetails,String searchvalue) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Starting");
		List<StudentRegistrationVo> list = null;
		try {
			list = new StudentRegistrationDaoImpl().getStudentListBySection(locationid,accyear,classname,sectionid,custdetails,searchvalue);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentRegistrationServiceImpl : getStudentListBySection  Ending");
	

		return list;
	}

public List<StudentRegistrationVo> getSectionForSMS(String classval, StudentRegistrationVo vo,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getSectionForSMS  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getSectionForSMS(classval,vo,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getSectionForSMS  Ending");
	 

	return list;
}

public StudentRegistrationVo singleStudentDetails(String studentId, String accYear, String locationId, UserLoggingsPojo custdetails) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentDetails  Starting");
	StudentRegistrationVo list = null;
	try {
		list = new StudentRegistrationDaoImpl().singleStudentDetails(studentId,accYear,locationId,custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : singleStudentDetails  Ending");

	return list;
}

public List<StudentRegistrationVo> getTempRegistrationList(StudentRegistrationVo registrationVo,UserLoggingsPojo userLoggingsVo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getTempRegistrationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getTempRegistrationList(registrationVo, userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getTempRegistrationList  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentRegistrationList(StudentRegistrationVo data, UserLoggingsPojo pojo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentRegistrationList  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getStudentRegistrationList(data,pojo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentRegistrationList  Ending");

	return list;
}

public List<StudentRegistrationVo> getTransportConcessionStudentList(String locationid, String accyear, String classid,
		String sectionid, String searchname, UserLoggingsPojo userLoggingsVo) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getTransportConcessionStudentList  Starting");
	
	List<StudentRegistrationVo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getTransportConcessionStudentList(locationid,accyear,classid,sectionid,searchname,userLoggingsVo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getTransportConcessionStudentList  Ending");
	
	return list;

}

public List<ConcessionDetailsPojo> getConcessionTypes(UserLoggingsPojo custdetails) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getConcessionTypes  Starting");
	
	List<ConcessionDetailsPojo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getConcessionTypes(custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getConcessionTypes  Ending");
	
	return list;

}

public List<StudentRegistrationVo> getStudentListByTransportRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails)
{
	return new StudentRegistrationDaoImpl().getStudentListByTransportRequestCancel(vo,custdetails);
 }

public List<StudentRegistrationVo> getStudentListBySectionReportCard(String locationid, String accyear,
		String classname, String sectionid, UserLoggingsPojo custdetails) {
	return new StudentRegistrationDaoImpl().getStudentListBySectionReportCard(locationid,accyear,classname,sectionid,custdetails);
	 }

public List<StudentRegistrationVo> getStudentListByFeeRequestCancel(StudentRegistrationVo vo, UserLoggingsPojo custdetails) {
	return new StudentRegistrationDaoImpl().getStudentListByFeeRequestCancel(vo,custdetails);
}

public StudentRegistrationVo getFeeRequestStudentWise(StudentRegistrationVo stuvo, UserLoggingsPojo userLoggingsVo) {
	return new StudentRegistrationDaoImpl().getFeeRequestStudentWise(stuvo,userLoggingsVo);
}

public List<ConcessionDetailsPojo> getSubConcessionTypes(UserLoggingsPojo custdetails) {

	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getSubConcessionTypes  Starting");
	
	List<ConcessionDetailsPojo> list = null;
	try {
		list = new StudentRegistrationDaoImpl().getSubConcessionTypes(custdetails);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getSubConcessionTypes  Ending");
	
	return list;

}

@Override
public List<StudentRegistrationVo> getStudentList(String academic_year, String location,UserLoggingsPojo userLoggingsVo) {
	// TODO Auto-generated method stub
	return null;
}

public List<StudentRegistrationVo> getStudentListByAnalyticalPerformance(StudentRegistrationVo vo) {
	 
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListByAnalyticalPerformance  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		StudentRegistrationDao dao=new StudentRegistrationDaoImpl();
		list = dao.getStudentListByAnalyticalPerformance(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentListByAnalyticalPerformance  Ending");
	return list;
}

public String SaveStudentAnalyticalPerformance(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : SaveStudentAnalyticalPerformance  Starting");
	String list = null;
	try {
		StudentRegistrationDao dao=new StudentRegistrationDaoImpl();
		
		list = dao.SaveStudentAnalyticalPerformance(vo);
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : SaveStudentAnalyticalPerformance  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentAnalyticPerformanceSearchByList(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentFilteredListByAnalyticalPerformance  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		StudentRegistrationDao dao=new StudentRegistrationDaoImpl();
		
		list = dao.getStudentAnalyticPerformanceSearchByList(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentFilteredListByAnalyticalPerformance  Ending");

	return list;
}

public List<StudentRegistrationVo> getStudentFilteredListByAnalyticalPerformance(StudentRegistrationVo vo) {
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentFilteredListByAnalyticalPerformance  Starting");
	List<StudentRegistrationVo> list = null;
	try {
		StudentRegistrationDao dao=new StudentRegistrationDaoImpl();
		
		list = dao.getStudentFilteredListByAnalyticalPerformance(vo);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in StudentRegistrationServiceImpl : getStudentFilteredListByAnalyticalPerformance  Ending");

	return list;
}


 
}
