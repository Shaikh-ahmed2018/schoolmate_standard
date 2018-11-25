package com.centris.campus.serviceImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SmsDao;

import com.centris.campus.daoImpl.SmsDaoIMPL;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SmsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeSMSVO;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsCountVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.SuddenHolidaySMSVO;
import com.centris.campus.vo.TermMasterVo;

public class SmsServiceImpl implements SmsService{

	private static final Logger logger = Logger
			.getLogger(SmsServiceImpl.class);

	SmsDao dao = new SmsDaoIMPL();

	public List<ClassPojo> getclasslistService(ClassPojo pojo,UserLoggingsPojo custdetails) {

		return dao.getclasslistDao(pojo,custdetails);
	}

	public ArrayList<SectionPojo> getsectionlistService(String classid) {

		return dao.getsectionlistDao(classid);
	}

	public ArrayList<SectionPojo> getsubjectlistService(String classid,String locationId,UserLoggingsPojo userLoggingsVo) {

		return dao.getsubjectlistDao(classid,locationId,userLoggingsVo);
	}

	public String inserthomeworkService(SmsVo vo) {

		return dao.inserthomeworDao(vo);
	}

	public List<SmsVo> getHomeWorklistService(UserLoggingsPojo custdetails,String locid, String clasid, String secid, String accid,String startdate, String enddate) {

		return dao.getHomeWorklistDao(custdetails,locid,clasid,secid,accid,startdate,enddate);
	}

	public List<SmsVo> getHomeWorkSearchlistService(UserLoggingsPojo custdetails, String locid, String clasid, String secid, String accid,String startdate, String enddate) {

		return dao.getHomeWorkSearchlistDao(custdetails,locid,clasid,secid,accid,startdate,enddate);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService(LstmsUpcomingMeetingVO vo,UserLoggingsPojo pojo,String schoolLocation) {

		return dao.getMeetingListDetailsServiceDao(vo,pojo,schoolLocation);
	}

	public ArrayList<StudentInfoVO> getStudentListDetailsService(
			String locId,String sectionid,UserLoggingsPojo userLoggingsVo) {

		return dao.getStudentListDetailsDao(locId,sectionid,userLoggingsVo);
	}

	public ArrayList<SubjectPojo> getSubjectListDetailsService(String[] categoryval,SubjectPojo subpojo,UserLoggingsPojo userLoggingsVo) {

		return dao.getSubjectListDetailsDao(categoryval,subpojo,userLoggingsVo);
	}

	public String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {

		return dao.saveMeetingDetailsDao(meetingvo,userLoggingsVo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails(UserLoggingsPojo custdetails,String schoolLocation) {

		return dao.getlatecomersListDetails(custdetails,schoolLocation);
	}

	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {

		return dao.addlatecomers(meetingvo,userLoggingsVo);
	}

	public ArrayList<UniformSmsPojo> getUniformListDetailsService(UserLoggingsPojo userLoggingsVo) {

		return dao.getUniformListDetailsDao(userLoggingsVo);
	}

	public String storeUniformDetails(UniformSmsPojo upojo,UserLoggingsPojo userLoggingsVo) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeUniformDetails Starting");


		try {


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}


		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeUniformDetails Ending");

		return dao.storeUniformStudent(upojo,userLoggingsVo);
	}

	@Override
	public String insertOtherSMS(UniformSmsPojo upojo) {
		return dao.insertOtherSMS(upojo);
	}

	@Override
	public ArrayList<StudentInfoVO> getStudentMeetingAndEvent(UniformSmsPojo upojo, UserLoggingsPojo cpojo) {
		return dao.getStudentMeetingAndEvent(upojo,cpojo);
	}

	@Override
	public List<LstmsUpcomingMeetingVO> getSearchLatecomerList(String locationid, String accyear, String classid,
			String sectionid,UserLoggingsPojo userLoggingsVo,String startdate, String enddate) {

		return dao.getSearchLatecomerList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	@Override
	public String SendOtherSMS(LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {

		return dao.SendOtherSMS(meetingvo,userLoggingsVo);
	}

	@Override
	public List<LstmsUpcomingMeetingVO> getOtherSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo,String startdate, String enddate) {

		return dao.getOtherSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	@Override
	public List<SuddenHolidaySMSVO> getSuddenHolidaySmsList(String locationid, String accyear, String classid,
			String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return dao.getSuddenHolidaySmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	@Override
	public List<AbsentsSMSPojo> getAbsentSmsList(String locationid, String accyear, String classid, String sectionid,UserLoggingsPojo userLoggingsVo, String startdate, String enddate) {

		return dao.getAbsentSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate);
	}

	@Override
	public List<LstmsUpcomingMeetingVO> getMeetingEventSmsList(String locationid, String accyear, String classid,
			String sectionid, UserLoggingsPojo userLoggingsVo, String startdate, String enddate,String title) {

		return dao.getMeetingEventSmsList(locationid,accyear,classid,sectionid,userLoggingsVo,startdate,enddate,title);
	}

	@Override
	public LstmsUpcomingMeetingVO getSmsDetails(UserLoggingsPojo userLoggingsVo,String accyear,String location) {

		return dao.getSmsDetails(userLoggingsVo,accyear, location);
	}

	@Override
	public ResultSet getClassIdName(String location_id, UserLoggingsPojo userLoggingsVo,String accyear) {

		return dao.getClassIdName( location_id,userLoggingsVo,accyear);
	}

	@Override
	public ResultSet getClassSmsCount(String location_id, UserLoggingsPojo userLoggingsVo, String accyear) {

		return dao.getClassSmsCount( location_id,   userLoggingsVo,accyear);
	}

	@Override
	public ArrayList getAbsentSmsCount(UserLoggingsPojo userLoggingsVo) {

		return dao.getAbsentSmsCount( userLoggingsVo);
	}

	@Override
	public ArrayList<SmsCountVO> getEventSmsCount(UserLoggingsPojo userLoggingsVo) {

		return dao.getEventSmsCount( userLoggingsVo);
	}

	@Override
	public ArrayList<SmsCountVO> getLateComeSmsCount(UserLoggingsPojo userLoggingsVo , String location_id ) {

		return dao.getLateComeSmsCount( userLoggingsVo , location_id);

	}

	@Override
	public ArrayList<SmsCountVO> getHomeworkSmsCount(UserLoggingsPojo userLoggingsVo, String location_id) {

		return dao.getHomeworkSmsCount(  userLoggingsVo,  location_id);
	}

	

	@Override
	public ArrayList<TermMasterVo> getTearm(String locId, UserLoggingsPojo custdetails, String academic_year) {
		return dao.getTearm(locId, custdetails,academic_year);
	}

	@Override
	public ArrayList<StudentInfoVO> getUnPaidStuList(String locId, String clsid, String termid,
			UserLoggingsPojo userLoggingsVo, String[] divid, String academic_year) {
		return dao.getUnPaidStuList(locId, clsid,termid,userLoggingsVo,divid,academic_year);
	}

	@Override
	public String InserFeeSMS(FeeSMSVO vo, UserLoggingsPojo userLoggingsVo) {
		return dao.InserFeeSMS(vo, userLoggingsVo);
	}

	@Override
	public ArrayList<FeeSMSVO> getFeeSMSList(UserLoggingsPojo pojo, String schoolLocation) {
		
		return dao.getFeeSMSList(pojo,schoolLocation);
	}

	@Override
	public ArrayList<FeeSMSVO> FeeSmsList(UserLoggingsPojo pojo, FeeSMSVO vo) {
		
		return dao.FeeSmsList(pojo,vo);
	}


}
