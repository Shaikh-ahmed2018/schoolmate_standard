package com.centris.campus.service;

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

public interface SmsService {

	List<ClassPojo> getclasslistService(ClassPojo pojo, UserLoggingsPojo custdetails);

	ArrayList<SectionPojo> getsectionlistService(String classid);

	ArrayList<SectionPojo> getsubjectlistService(String classid,String locationId, UserLoggingsPojo userLoggingsVo);

	String inserthomeworkService(SmsVo vo);

	List<SmsVo> getHomeWorklistService(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate);

	List<SmsVo> getHomeWorkSearchlistService(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService(LstmsUpcomingMeetingVO vo, UserLoggingsPojo pojo, String schoolLocation);

	ArrayList<StudentInfoVO> getStudentListDetailsService(String locId,String sectionid,UserLoggingsPojo userLoggingsVo);

	ArrayList<SubjectPojo> getSubjectListDetailsService(String[] categoryval, SubjectPojo subpojo,UserLoggingsPojo userLoggingsVo);

	String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails(UserLoggingsPojo custdetails, String schoolLocation);

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo);

	ArrayList<UniformSmsPojo> getUniformListDetailsService(UserLoggingsPojo userLoggingsVo);

	String storeUniformDetails(UniformSmsPojo upojo, UserLoggingsPojo userLoggingsVo);

	String insertOtherSMS(UniformSmsPojo upojo);

	ArrayList<StudentInfoVO> getStudentMeetingAndEvent(UniformSmsPojo upojo, UserLoggingsPojo cpojo);

	List<LstmsUpcomingMeetingVO> getSearchLatecomerList(String locationid, String accyear, String classid,String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	String SendOtherSMS(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo);

	List<LstmsUpcomingMeetingVO> getOtherSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	List<SuddenHolidaySMSVO> getSuddenHolidaySmsList(String locationid, String accyear, String classid,String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	List<AbsentsSMSPojo> getAbsentSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate);

	List<LstmsUpcomingMeetingVO> getMeetingEventSmsList(String locationid, String accyear, String classid,
			String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate, String title);

	LstmsUpcomingMeetingVO getSmsDetails(UserLoggingsPojo userLoggingsVo, String accyear, String location);

	ResultSet getClassIdName(String location_id, UserLoggingsPojo userLoggingsVo, String accyear);

	ResultSet getClassSmsCount(String location_id, UserLoggingsPojo userLoggingsVo, String accyear);

	ArrayList<SmsCountVO> getAbsentSmsCount(UserLoggingsPojo userLoggingsVo);

	ArrayList<SmsCountVO> getEventSmsCount(UserLoggingsPojo userLoggingsVo);

	ArrayList<SmsCountVO> getLateComeSmsCount(UserLoggingsPojo userLoggingsVo, String location_id);

	ArrayList<SmsCountVO> getHomeworkSmsCount(UserLoggingsPojo userLoggingsVo, String location_id);

	ArrayList<TermMasterVo> getTearm(String locId, UserLoggingsPojo custdetails, String academic_year);

	ArrayList<StudentInfoVO> getUnPaidStuList(String locId, String clsid, String termid,UserLoggingsPojo userLoggingsVo, String[] divid, String academic_year);

	String InserFeeSMS(FeeSMSVO vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<FeeSMSVO> getFeeSMSList(UserLoggingsPojo pojo, String schoolLocation);

	ArrayList<FeeSMSVO> FeeSmsList(UserLoggingsPojo pojo, FeeSMSVO vo);

	

	

	

	/*String deletehomeworkService(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService();

	ArrayList<SubjectPojo> getSubjectListDetailsService(String[] categoryval);

	ArrayList<StudentInfoVO> getStudentListDetailsService(String[] categoryval);

	String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo);

	String deleteMeetingService(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsService(
			String searchTerm);


	String storeUniformDetails(UniformSmsPojo upojo);


	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	ArrayList<UniformSmsPojo> getUniformListDetailsService();


	ArrayList<UniformSmsPojo> getBdayListDetailsService();*/

	
	
	
	
	
	

}
