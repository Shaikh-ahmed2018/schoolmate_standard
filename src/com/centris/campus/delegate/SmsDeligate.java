package com.centris.campus.delegate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SmsService;
import com.centris.campus.serviceImpl.SmsServiceImpl;
import com.centris.campus.vo.FeeSMSVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsCountVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TermMasterVo;

public class SmsDeligate {

	SmsService service=new SmsServiceImpl();

	public List<ClassPojo> getclasslist(ClassPojo pojo, UserLoggingsPojo custdetails) {

		return service.getclasslistService(pojo,custdetails);
	}

	public ArrayList<SectionPojo> getsectionlist(String classid) {

		return service.getsectionlistService(classid);
	}

	public ArrayList<SectionPojo> getsubjectlist(String classid,String locationId, UserLoggingsPojo userLoggingsVo) {

		return service.getsubjectlistService(classid,locationId,userLoggingsVo);
	}

	public String inserthomework(SmsVo vo) {

		return service.inserthomeworkService(vo);
	}

	public List<SmsVo> getHomeWorklist(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate) {

		return service.getHomeWorklistService(custdetails,locid,clasid,secid,accid,startdate,enddate);
	}

	public List<SmsVo> getHomeWorkSearchlist( UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid, String startdate, String enddate) {
		return service.getHomeWorkSearchlistService(custdetails,locid,clasid,secid,accid,startdate,enddate);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails(LstmsUpcomingMeetingVO vo, UserLoggingsPojo pojo, String schoolLocation) {

		return service.getMeetingListDetailsService(vo,pojo,schoolLocation);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetails(
			String searchTerm, LstmsUpcomingMeetingVO vo, UserLoggingsPojo pojo, String schoolLocation) {
		return null;
	}

	public ArrayList<StudentInfoVO> getStudentListDetails(String locId,String sectionid,UserLoggingsPojo userLoggingsVo) {

		return service.getStudentListDetailsService(locId,sectionid,userLoggingsVo);
	}

	public ArrayList<SubjectPojo> getSubjectListDetails(String[] categoryval, SubjectPojo subpojo,UserLoggingsPojo userLoggingsVo) {

		return service.getSubjectListDetailsService(categoryval,subpojo,userLoggingsVo);
	}

	public String saveMeetingDetails(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo) {

		return service.saveMeetingDetailsService(meetingvo,userLoggingsVo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails(UserLoggingsPojo custdetails, String schoolLocation) {
		return service.getlatecomersListDetails(custdetails,schoolLocation);
	}

	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo, UserLoggingsPojo userLoggingsVo) {
		return service.addlatecomers(meetingvo,userLoggingsVo);
	}

	public ArrayList<UniformSmsPojo> getUniformListDetails(UserLoggingsPojo userLoggingsVo) {
		return service.getUniformListDetailsService(userLoggingsVo);
	}

	public String storeUniformDetails(UniformSmsPojo upojo, UserLoggingsPojo userLoggingsVo) {

		return service.storeUniformDetails(upojo,userLoggingsVo);
	}

	public String insertOtherSMS(UniformSmsPojo upojo) {
		return service.insertOtherSMS(upojo);
	}

	public ArrayList<StudentInfoVO> getStudentMeetingAndEvent(UniformSmsPojo upojo, UserLoggingsPojo cpojo) {
		return service.getStudentMeetingAndEvent(upojo,cpojo);
	}

	public List<LstmsUpcomingMeetingVO> getSearchLatecomerList(String locationid, String accyear, String classid,
			String sectionid,  UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return service.getSearchLatecomerList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	public String SendOtherSMS(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {

		return service.SendOtherSMS(meetingvo,userLoggingsVo);
	}

	public List<LstmsUpcomingMeetingVO> getOtherSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return service.getOtherSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	public List<SuddenHolidaySMSVO> getSuddenHolidaySmsList(String locationid, String accyear, String classid,String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return service.getSuddenHolidaySmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	public List<AbsentsSMSPojo> getAbsentSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return service.getAbsentSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	public List<LstmsUpcomingMeetingVO> getMeetingEventSmsList(String locationid, String accyear, String classid,
			String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate, String title) {

		return service.getMeetingEventSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate,title);
	}

	public LstmsUpcomingMeetingVO getSmsDetails(UserLoggingsPojo userLoggingsVo, String accyear, String location) {

		return service.getSmsDetails(userLoggingsVo,accyear, location);
	}

	public ResultSet getClassIdName(String location_id, UserLoggingsPojo userLoggingsVo, String accyear) {

		return service.getClassIdName( location_id,  userLoggingsVo,accyear);
	}

	public ResultSet getClassSmsCount(String location_id, UserLoggingsPojo userLoggingsVo, String accyear) {

		return service.getClassSmsCount( location_id,  userLoggingsVo,accyear);
	}

	public ArrayList<SmsCountVO> getAbsentSmsCount(UserLoggingsPojo userLoggingsVo) {

		return service.getAbsentSmsCount( userLoggingsVo);
	}

	public ArrayList<SmsCountVO> getEventSmsCount(UserLoggingsPojo userLoggingsVo) {

		return service.getEventSmsCount(userLoggingsVo);
	}

	public ArrayList<SmsCountVO> getLateComeSmsCount(UserLoggingsPojo userLoggingsVo, String location_id) {
		return service.getLateComeSmsCount(userLoggingsVo ,  location_id);
	}

	public ArrayList<SmsCountVO> getHomeworkSmsCount(String location_id, UserLoggingsPojo userLoggingsVo) {

		return service.getHomeworkSmsCount(userLoggingsVo ,  location_id);
	}

	public ArrayList<TermMasterVo> getTearm(String locId, UserLoggingsPojo custdetails, String academic_year) {
		return service.getTearm(locId ,custdetails,academic_year);
	}

	public ArrayList<StudentInfoVO> getUnPaidStuList(String locId, String clsid, String termid,
			UserLoggingsPojo userLoggingsVo, String[] divid, String academic_year) {
		return service.getUnPaidStuList(locId ,clsid,termid,userLoggingsVo,divid,academic_year);
	}

	public String InserFeeSMS(FeeSMSVO vo, UserLoggingsPojo userLoggingsVo) {
		return service.InserFeeSMS(vo ,userLoggingsVo);
	}

	public ArrayList<FeeSMSVO> getFeeSMSList(UserLoggingsPojo pojo, String schoolLocation) {
		
		return service.getFeeSMSList(pojo ,schoolLocation);
	}

	public ArrayList<FeeSMSVO> FeeSmsList(UserLoggingsPojo pojo, FeeSMSVO vo) {
		return service.FeeSmsList(pojo ,vo);
	}


	/*public List<SmsVo> getHomeWorkSearchlist(String searchTerm) {

		return service.getHomeWorkSearchlistService(searchTerm);
	}

	public String deletehomework(SmsVo vo) {



		return service.deletehomeworkService(vo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails() {

		return service.getMeetingListDetailsService();
	}

	public ArrayList<SubjectPojo> getSubjectListDetails(String[] categoryval) {

		return service.getSubjectListDetailsService(categoryval);
	}

	public ArrayList<StudentInfoVO> getStudentListDetails(String[] categoryval) {

		return service.getStudentListDetailsService(categoryval);
	}



	public String deleteMeetingDeligate(SmsVo vo) {

		return service.deleteMeetingService(vo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetails(
			String searchTerm) {

		return service.getMeetingSearchListDetailsService(searchTerm);
	}


	public String storeUniformDetails(UniformSmsPojo upojo) {

		return service.storeUniformDetails(upojo);
	}


	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) 


	{

		return service.addlatecomers(meetingvo);

	}

	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() 

	{

		return service.getlatecomersListDetails();
	}

	public ArrayList<UniformSmsPojo> getUniformListDetails() {

		return service.getUniformListDetailsService();
	}



	public ArrayList<UniformSmsPojo> getBdayListDetails() {

		return service.getBdayListDetailsService();
	}*/






}
