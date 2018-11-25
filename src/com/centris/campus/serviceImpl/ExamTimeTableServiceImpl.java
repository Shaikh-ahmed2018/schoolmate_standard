package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.util.ArrayList;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;


import com.centris.campus.dao.ExamTimeTableDao;

import com.centris.campus.daoImpl.ExamTimeTableDaoImpl;
import com.centris.campus.forms.ExamDetailsForm;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ExamTimeTableService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;

public class ExamTimeTableServiceImpl implements ExamTimeTableService {
	private static final Logger logger = Logger
			.getLogger(ExamTimeTableServiceImpl.class);

	public Map<String, List<ExamTimetablePojo>> getExamDetails(
			ExamTimetablePojo groupLogReportPojo) {

		/*Map<String, Map<String, List<ExamTimetablePojo>>> consolidatedMap = new LinkedHashMap<String, Map<String, List<ExamTimetablePojo>>>();*/

		Map<String, List<ExamTimetablePojo>> listMap = null;
/*		Connection conn = null;*/
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		try {
			listMap = new LinkedHashMap<String, List<ExamTimetablePojo>>();
			List<ExamTimetablePojo> examMainList = new ArrayList<ExamTimetablePojo>();
			List<ExamTimetablePojo> examList = examTimeTableDao
					.getExamDetails();

			for (int i = 0; i < examList.size(); i++) {

				if (examMainList.size() != 0) {
					System.out.println(examMainList
							.get(examMainList.size() - 1).getExamId()
							+ " ----- " + examList.get(i).getExamId());

					if (!(examMainList.get(examMainList.size() - 1).getExamId()
							.equalsIgnoreCase(examList.get(i).getExamId()))) {

						JSONArray array1 = new JSONArray();
						array1.put(examMainList);
						System.out.println("exam list " + array1);

						listMap.put(examMainList.get(examMainList.size() - 1)
								.getExamId(), examMainList);

						// consolidatedMap.put(examMainList.get(examMainList.size()-1).getExamId(),
						// listMap);

						examMainList = new ArrayList<ExamTimetablePojo>();
					}

				}

				examMainList.add(examList.get(i));

			}

			if (examMainList.size() != 0) {

				listMap.put(examMainList.get(examMainList.size() - 1)
						.getExamName(), examMainList);
				// consolidatedMap.put(examMainList.get(examMainList.size()-1).getExamName(),
				// listMap);
			}

			JSONArray array = new JSONArray();
			array.put(listMap);

			System.out.println("consolidatedMap size " + array);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	public ArrayList<ExamTimetablePojo> getExamTimeTableDetails(String classId,
			String exam,UserLoggingsPojo userLoggingsVo) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();

		return examTimeTableDao.getExamTimeTableDetails(classId,exam,userLoggingsVo);
	}

	public ExamTimetablePojo getExamDate(String examId,UserLoggingsPojo userLoggingsVo) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExaminations Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExaminations Ending");
		return examTimeTableDao.getExamDate(examId,userLoggingsVo);
	}

	public ArrayList<ExamTimetablePojo> getAllSubjects(String classId) {

		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getAllSubjects Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getAllSubjects Ending");
		return examTimeTableDao.getAllSubjects(classId);
	}

	public String saveExaminationClassMapping(
			ArrayList<ExamTimetablePojo> examinationclassmappinglist, UserLoggingsPojo userLoggingsVo,String log_audit_session) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: saveExaminationClassMapping Starting");
		try {

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: saveExaminationClassMapping Ending");
		return examTimeTableDao
				.saveExaminationClassMapping(examinationclassmappinglist,userLoggingsVo,log_audit_session);
	}

	public ArrayList<ExamTimetablePojo> getExamdetails(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamdetails(userLoggingsVo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	public ArrayList<ExamTimetablePojo> getclassdetails(UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getclassdetails(userLoggingsVo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmListYear(String academicYear,UserLoggingsPojo userLoggingsVo ) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmListYear Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmListYear(academicYear,userLoggingsVo );
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmListYear Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableListYear() {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableListYear();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableListYear Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassList(String accYear) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableClassList(accYear);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getHeading(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getHeading Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getHeading(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getHeading Ending");
		return examListYear;

	}

	@Override
	public ArrayList<ExaminationDetailsVo> getClassExamTimeTableDetails(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getClassExamTimeTableDetails Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getClassExamTimeTableDetails(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getClassExamTimeTableDetails Ending");
		return examListYear;

	}

	@Override
	public String insertExam(ExamDetailsForm addExam) {
		
		ExamTimeTableDao objinsert = new ExamTimeTableDaoImpl();
		return objinsert.insertExam(addExam);
		
		
		
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingsDetails(String accyear,String schoolLocation,UserLoggingsPojo custdetails) {
		ExamTimeTableDao objinsert = new ExamTimeTableDaoImpl();
		return objinsert.getexamsettingsDetails(accyear,schoolLocation,custdetails);
	}

	@Override
	public String deleteexamSettings(String deleteid,String location,String accyear,ExamDetailsForm addExam) {
		ExamTimeTableDao delteobj = new ExamTimeTableDaoImpl();
		return delteobj.deleteexamSettings(deleteid,location,accyear,addExam);	
	}

	@Override
	public String editexamsettings(String editid, ExamDetailsForm editExam) {
		ExamTimeTableDao editobj = new ExamTimeTableDaoImpl();
		return editobj.editexamsettings(editid,editExam);	
	}

	
	@Override
	public String checkduplicateExam(String accyear, String examcode,String location,UserLoggingsPojo userLoggingsVo,String classid) {
		ExamTimeTableDao checkdupobj = new ExamTimeTableDaoImpl();
		return checkdupobj.checkduplicateExam(accyear,examcode,location,userLoggingsVo,classid);	
	}

	
	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmTimeTableClassSubjectList(ExamTimetablePojo exampojo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Starting");
		ArrayList<ExaminationDetailsVo> examListYear = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			examListYear = examTimeTableDao.getEaxmTimeTableClassSubjectList(exampojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getEaxmTimeTableClassList Ending");
		return examListYear;

	}


	@Override
	public ArrayList<ExaminationDetailsVo> getstatusexamsettingsDetails(
			String accyear, String schoolLocation,UserLoggingsPojo custdetails) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getstatusexamsettingsDetails(accyear,schoolLocation,custdetails);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getEaxmMarksListYear(String accyear, String locid,UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getEaxmMarksListYear(accyear,locid,userLoggingsVo);	
	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamdetails(String locid) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamandclass Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamdetails(locid);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamandclass Ending");
		return maplist;

	}

	@Override
	public ArrayList<ExamTimetablePojo> getExamcodeForDependency(String locid,
			String accYear, String startdate, String enddate, String examCode) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExaminationTimeTableServiceIMPL: getExamcodeForDependency Starting");
		ArrayList<ExamTimetablePojo> maplist = null;
		try {
			ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
			maplist = examTimeTableDao.getExamcodeForDependency(locid,accYear,startdate,enddate,examCode);
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in  ExaminationTimeTableServiceIMPL: getExamcodeForDependency Ending");
		return maplist;

	}

	@Override
	public String saveDependency(String[] examcode, String[] dependency,
			String mainexamcode, String mainexamname,String examId,String log_audit_session) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		return 	 examTimeTableDao.saveDependency(examcode,dependency,mainexamcode,mainexamname,examId,log_audit_session);
		
	}

	@Override
	public String insertGradeDependent(String project,String assignment,String practical,String attendance,String classId,String sectionId,String exam,String location,String accyear,String log_audit_session) {
		ExamTimeTableDao examTimeTableDao = new ExamTimeTableDaoImpl();
		return 	 examTimeTableDao.insertGradeDependent(project,assignment,practical,attendance,classId,sectionId,exam,location,accyear,log_audit_session);
		
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstatusgrdDepDetails(
			String accyear, String hschoolLocation) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getstatusgrdDepDetails(accyear,hschoolLocation);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> disstudepdetails(String accyear,
			String locid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.disstudepdetails(accyear,locid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamTypeList(UserLoggingsPojo custdetails) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamTypeList(custdetails);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingsDetailsonClass(String accyearid, String location,
			UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getexamsettingsDetailsonClass(accyearid,location,custdetails,classid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameList(String locationid, String classid, String accyear,
			UserLoggingsPojo userLoggingsVo) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamNameList(locationid,classid,accyear,userLoggingsVo);	
	}

	@Override
	public String resultDeclare(String accyear, String examcode, String location, UserLoggingsPojo custdetails,String msg) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.resultDeclare(accyear,examcode,location,custdetails,msg);	
	}

	@Override
	public String timetableset(String examid, String location, String accyear,UserLoggingsPojo custdetails) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.timetableset(examid,location,accyear,custdetails);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getexamDeclared(String academic_year, String location, UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getexamDeclared(academic_year,location,custdetails,classid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getExamNameListByPeriodicExam(String locationid, String accyear, UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getExamNameListByPeriodicExam(locationid,accyear,custdetails,classid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getHalflyExamNameList(String locationid, String accyear, UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getHalflyExamNameList(locationid,accyear,custdetails,classid);	
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getYearlyExamNameList(String locationid, String accyear, UserLoggingsPojo custdetails, String classid) {
		ExamTimeTableDao statusexamdetails = new ExamTimeTableDaoImpl();
		return statusexamdetails.getYearlyExamNameList(locationid,accyear,custdetails,classid);
	}
	
}
