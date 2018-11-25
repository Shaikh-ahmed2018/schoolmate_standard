package com.centris.campus.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.FeeSMSVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsCountVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TermMasterVo;

public interface SmsDao {

	List<ClassPojo> getclasslistDao(ClassPojo pojo, UserLoggingsPojo custdetails);

	ArrayList<SectionPojo> getsectionlistDao(String classid);

	ArrayList<SectionPojo> getsubjectlistDao(String classid, String locationId, UserLoggingsPojo userLoggingsVo);

	String inserthomeworDao(SmsVo vo);

	List<SmsVo> getHomeWorklistDao(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate);

	List<SmsVo> getHomeWorkSearchlistDao(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsServiceDao(LstmsUpcomingMeetingVO vo, UserLoggingsPojo pojo, String schoolLocation);

	ArrayList<StudentInfoVO> getStudentListDetailsDao(String locId,String sectionid,UserLoggingsPojo userLoggingsVo);

	ArrayList<SubjectPojo> getSubjectListDetailsDao(String[] categoryval, SubjectPojo subpojo,UserLoggingsPojo userLoggingsVo);

	String saveMeetingDetailsDao(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails(UserLoggingsPojo custdetails, String schoolLocation);

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo);

	ArrayList<UniformSmsPojo> getUniformListDetailsDao(UserLoggingsPojo userLoggingsVo);

	String storeUniformStudent(UniformSmsPojo upojo, UserLoggingsPojo userLoggingsVo);

	String insertOtherSMS(UniformSmsPojo upojo);

	ArrayList<StudentInfoVO> getStudentMeetingAndEvent(UniformSmsPojo upojo ,UserLoggingsPojo cpojo);

	List<LstmsUpcomingMeetingVO> getSearchLatecomerList(String locationid, String accyear, String classid,String sectionid,UserLoggingsPojo userLoggingsVo,String startdate, String enddate);

	String SendOtherSMS(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo);



	List<SuddenHolidaySMSVO> getSuddenHolidaySmsList(String locationid, String accyear, String classid,String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	List<AbsentsSMSPojo> getAbsentSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	List<LstmsUpcomingMeetingVO> getMeetingEventSmsList(String locationid, String accyear, String classid,String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate, String title);

	LstmsUpcomingMeetingVO getSmsDetails(UserLoggingsPojo userLoggingsVo, String accyear, String location);

	ResultSet getClassIdName(String location_id, UserLoggingsPojo userLoggingsVo, String accyear);

	ResultSet getClassSmsCount(String location_id, UserLoggingsPojo userLoggingsVo,String accyear);

	ArrayList<SmsCountVO>  getAbsentSmsCount(UserLoggingsPojo userLoggingsVo);

	ArrayList<SmsCountVO> getEventSmsCount(UserLoggingsPojo userLoggingsVo);

	ArrayList<SmsCountVO> getLateComeSmsCount(UserLoggingsPojo userLoggingsVo ,String location_id);

	ArrayList<SmsCountVO> getHomeworkSmsCount(UserLoggingsPojo userLoggingsVo, String location_id);

List<LstmsUpcomingMeetingVO> getOtherSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

ArrayList<TermMasterVo> getTearm(String locId, UserLoggingsPojo custdetails, String academic_year);

ArrayList<StudentInfoVO> getUnPaidStuList(String locId, String clsid, String termid, UserLoggingsPojo userLoggingsVo, String[] divid, String academic_year);

String InserFeeSMS(FeeSMSVO vo, UserLoggingsPojo userLoggingsVo);

ArrayList<FeeSMSVO> getFeeSMSList(UserLoggingsPojo pojo, String schoolLocation);

ArrayList<FeeSMSVO> FeeSmsList(UserLoggingsPojo pojo, FeeSMSVO vo);

	/*String deletehomeworkDao(SmsVo vo);

	

	

	ArrayList<StudentInfoVO> getStudentListDetailsDao(String[] categoryval);

	

	String deleteMeetingDao(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsDao(
			String searchTerm);

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	int storeUniformDetails(UniformSmsPojo upojo);

	int storeUniformSections(UniformSmsPojo upojo);

	int storeUniformStudent(UniformSmsPojo upojo);

	ArrayList<UniformSmsPojo> getUniformListDetailsDao();


	ArrayList<UniformSmsPojo> getBdayListDetailsDao();*/
	
	

}
