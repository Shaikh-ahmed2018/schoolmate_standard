package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.ExamDetailsDao;
import com.centris.campus.daoImpl.ExamDetailsDaoImpl;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.ExamTimetablePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ExamDetailsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ExaminationDetailsVo;


public class ExamDetailsServiceImpll implements ExamDetailsService {
	private static final Logger logger = Logger
			.getLogger(StreamDetailsServiceImpl.class);
	
	static ExamDetailsDao daoimpl;
	
	static{
		 daoimpl= new ExamDetailsDaoImpl();
	}
	
	@Override
	public List<ExaminationDetailsVo> getExamDetailsService(UserLoggingsPojo custdetails) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsServiceImpl : getExamDetailsService Starting");
		
		List<ExaminationDetailsVo> examvolist = new ArrayList<ExaminationDetailsVo>();
		List<ExamDetailsPojo> examPojoList = new ArrayList<ExamDetailsPojo>();
		
		try {
			
			daoimpl = new ExamDetailsDaoImpl();
			examPojoList=daoimpl.getExamDetailsDao(custdetails);
			
			for (ExamDetailsPojo examPojo : examPojoList) {
				ExaminationDetailsVo examVo = new ExaminationDetailsVo();
				
				examVo.setExamid(examPojo.getExamid());
				examVo.setExamName(examPojo.getExamName());
				examVo.setStartDate(examPojo.getStartDate());
				examVo.setEndDate(examPojo.getEndDate());
				examVo.setAccyear(examPojo.getAccyear());
				examVo.setDescription(examPojo.getDescription());
				examvolist.add(examVo);
				
			}	
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ExamDetailsServiceImpl : getExamDetailsService Ending");
		
		
		
		return examvolist;
	}


	@Override
	public String getaccyName(String accyear,UserLoggingsPojo userLoggingsVo) {
		
		return daoimpl.getaccyName(accyear,userLoggingsVo);
	}

	@Override
	public String insertGradeSettings(ExamDetailsPojo obj) {
		return daoimpl.insertGradeSettings(obj);
	}

	@Override
	public ArrayList<ExamDetailsPojo> displayGradeSettings(String accyear,String location,UserLoggingsPojo custdetails) {
		return daoimpl.displayGradeSettings(accyear,location,custdetails);
	}

	@Override
	public String deleteGradeSettings(String gradeid,String locname,String accyear,ExamDetailsPojo obj) {
		return daoimpl.deleteGradeSettings(gradeid,locname,accyear,obj);
	}

	@Override
	public String editGradeSettings(ExaminationDetailsVo gradeid,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.editGradeSettings(gradeid,userLoggingsVo);
	}

	@Override
	public String checkduplicateGrade(String accyear, String gradename,String loc,UserLoggingsPojo custdetails) {
		return daoimpl.checkduplicateGrade(accyear,gradename,loc,custdetails);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClass(String accyear,
			String examid,String locid,String classid,UserLoggingsPojo custdetails) {
		return daoimpl.getSubjectClass(accyear,examid,locid,classid,custdetails);
		
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamclassDetails(
			ExaminationDetailsVo obj,UserLoggingsPojo custdetails) {
		return daoimpl.getexamclassDetails(obj,custdetails);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksStatus(String acyear) {
		return daoimpl.getSubjectmarksStatus(acyear);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectmarksList(String accyear,String schoolLocation,UserLoggingsPojo custdetails) {
		return daoimpl.getSubjectmarksList(accyear,schoolLocation,custdetails);
	}
	
	@Override
	public String getexamName(String examid, String accyear,String locid,UserLoggingsPojo custdetails) {
		return daoimpl.getexamName(examid,accyear,locid,custdetails);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubject(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.classWiseSubject(obj,userLoggingsVo);
	}

	@Override
	public String getsubDetails(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getsubDetails(obj,userLoggingsVo);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> getstudentsList(ExaminationDetailsVo obj,String schoolLocation, UserLoggingsPojo custdetails) {
		return daoimpl.getstudentsList(obj,schoolLocation,custdetails);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> classWiseStudent(
			ExaminationDetailsVo obj,UserLoggingsPojo custdetails) {
		return daoimpl.classWiseStudent(obj,custdetails);
	}

  @Override
	public ArrayList<ExaminationDetailsVo> getStudentDetails(ExaminationDetailsVo obj1, UserLoggingsPojo custdetails) {
		return daoimpl.getStudentDetails(obj1,custdetails);
	}


	@Override
	public String insertmarkentrydetails(
			ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.insertmarkentrydetails(obj,userLoggingsVo);
	}
	
	
	@Override

	public String getlocationname(String schoolLocation,UserLoggingsPojo pojo) {
		return daoimpl.getlocationname(schoolLocation,pojo);

	}
	
	@Override
	public String insertmarkentrysubjectwise(ExaminationDetailsVo obj,String schoolLocation) {
		return daoimpl.insertmarkentrysubjectwise(obj,schoolLocation);
	
	}
	
	@Override
	public String getclassname(String classid,UserLoggingsPojo pojo) {
		return daoimpl.getclassname(classid,pojo);
	}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getsubjectstudent(String accyear,
			String examid, String locid,UserLoggingsPojo userLoggingsVo,String classid) {
		return daoimpl.getsubjectstudent(accyear,examid,locid,userLoggingsVo,classid);
	}
	
	@Override
	public ArrayList<ExaminationDetailsVo> getlistofExamCodes(String schoolLocation) {
		return daoimpl.getlistofExamCodes(schoolLocation);
	}


	@Override
	public String updatemarkentrysubjectwise(ExaminationDetailsVo obj,
			String schoolLocation) {
		return daoimpl.updatemarkentrysubjectwise(obj,schoolLocation);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> examTimeTableListYear(String accyear,String loc,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.examTimeTableListYear(accyear,loc,userLoggingsVo);
	}


	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String loc,String accyear,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getExamClassByLocation(loc,accyear,userLoggingsVo);
	}


	@Override
	public List<ExaminationDetailsVo> getexamlistbyclass(ExamTimetablePojo pojo,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getexamlistbyclass(pojo,userLoggingsVo);
	}


	@Override
	public ExaminationDetailsVo getexamdetails(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return daoimpl.getexamdetails(pojo,custdetails);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetails(ExamTimetablePojo pojo, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getsubdetails(pojo,userLoggingsVo);
	}


	@Override
	public String savetimetabledetails(ExamTimetablePojo pojo, String[] subid1, String[] starttime1, String[] endtime1,
			String[] startdate, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.savetimetabledetails(pojo,subid1,starttime1,endtime1,startdate,userLoggingsVo);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamsbtselection(String accyear, String locid, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getexamsbtselection(accyear,locid,userLoggingsVo);
	}


	@Override
	public List<ExaminationDetailsVo> getExamClassByLocation(String locid, String accyear, String examid, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getExamClassByLocation(accyear,locid,examid,userLoggingsVo);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslist(String accyear, String locid,UserLoggingsPojo custdetails) {
		return daoimpl.getexamsettingslist(accyear,locid,custdetails);
	}


	@Override
	public String checkduplicatedate(ExamTimetablePojo pojo) {
		return daoimpl.checkduplicatedate(pojo);
	}


	@Override
	public ExaminationDetailsVo getexamdetailsbyset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return daoimpl.getexamdetailsbyset(pojo,custdetails);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getsubdetailsset(ExamTimetablePojo pojo, UserLoggingsPojo custdetails) {
		return daoimpl.getsubdetailsset(pojo,custdetails);
	}


	@Override
	public String savetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate, UserLoggingsPojo custdetails) {
		return daoimpl.savetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate,custdetails);
	}


	@Override
	public String updatetimetabledetailsset(ExamTimetablePojo pojo, String[] subid1, String[] starttime1,
			String[] endtime1, String[] startdate) {
		return daoimpl.updatetimetabledetailsset(pojo,subid1,starttime1,endtime1,startdate);
	}
	@Override
	public ArrayList<ExaminationDetailsVo> getexamsettingslistfordep(String accyear, String locid) {
		return daoimpl.getexamsettingslistfordep(accyear,locid);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getSubjectClassBySpec(String accyear, String examid, String locid,
			String classid, UserLoggingsPojo userLoggingsVo) {
		return daoimpl.getSubjectClassBySpec(accyear,examid,locid,classid,userLoggingsVo);
		}


	@Override
	public String insertmarkentrydetailsSubjectWise(ExaminationDetailsVo obj,UserLoggingsPojo userLoggingsVo) {
		return daoimpl.insertmarkentrydetailsSubjectWise(obj,userLoggingsVo);
		}


	@Override
	public List<ExaminationDetailsVo> getExamByClassWise(String locid, String accyear, String classid,
			UserLoggingsPojo custdetails) {
		return daoimpl.getExamByClassWise(locid,accyear,classid,custdetails);
		}


	@Override
	public List<ExaminationDetailsVo> getStudentMarkListSearch(ExaminationDetailsVo vo, UserLoggingsPojo custdetails) {
		return daoimpl.getStudentMarkListSearch(vo,custdetails);
	}


	@Override
	public ArrayList<ExaminationDetailsVo> getReportCardSettingList(String accyear, String locid, UserLoggingsPojo custdetails) {
		return daoimpl.getReportCardSettingList(accyear,locid,custdetails);
	}

	@Override
	public List<ExaminationDetailsVo> getMaximumMarkClassList(String accyear, String locid, UserLoggingsPojo custdetails, String classId) {
		return daoimpl.getMaximumMarkClassList(accyear,locid,custdetails,classId);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubjectInMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.classWiseSubjectInMaximumMark(obj,custdetails);
	}

	@Override
	public ArrayList<ExaminationDetailsVo> classWiseSubjectWithLabMaximumMark(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.classWiseSubjectWithLabMaximumMark(obj,custdetails);
	}


	@Override
	public String insertMaximumExammarkDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.insertMaximumExammarkDetails(obj,custdetails);
	}


	@Override
	public String insertReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.insertReportSetupDetails(obj,custdetails);
	}


	@Override
	public ExaminationDetailsVo getTerm1ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.getTerm1ReportSetupDetails(obj,custdetails);
	}


	@Override
	public ExaminationDetailsVo getTerm2ReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.getTerm2ReportSetupDetails(obj,custdetails);
	}


	@Override
	public ExaminationDetailsVo getAcademicReportSetupDetails(ExaminationDetailsVo obj, UserLoggingsPojo custdetails) {
		return daoimpl.getAcademicReportSetupDetails(obj,custdetails);
	}
}
