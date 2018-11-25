package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;


import com.centris.campus.forms.ClassForm;
import com.centris.campus.forms.RemarksForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.CommunicationSettingsService;
import com.centris.campus.serviceImpl.CommunicationSettingsServiceImpl;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class CommunicationSettingsBD {

	
	CommunicationSettingsService service = new CommunicationSettingsServiceImpl();
	
	
	public ArrayList<UpcomingRemarksVO> getRemarksListDetails(UserLoggingsPojo userLoggingsVo) {
		
		return service.getRemarksListDetailsService(userLoggingsVo);
	}


	public List<StreamDetailsVO> getStreamListDetails(UserLoggingsPojo custdetails) {
		
		return service.getStreamListDetailsService(custdetails);
	}


	public List<ClassPojo> getClassListDetails(ClassPojo pojo, UserLoggingsPojo userLoggingsVo) {
		
		return service.getClassListDetailsService(pojo,userLoggingsVo);
	}


	public ArrayList<SectionPojo> getSectionListDetails(String[] categoryval) {
		
		return service.getSectionListDetailsService(categoryval);
	}


	public ArrayList<StudentInfoVO> getStudentListDetails(
			StudentInfoVO studentvo) {
		
		return service.getStudentListDetailsService(studentvo);
	}


	public ArrayList<TeacherRegistrationPojo> getTeacherListDetails(
			TeacherRegistrationPojo teacherpojo) {
		
		return service.getTeacherListDetailsService(teacherpojo);
	}


	public ArrayList<SubjectPojo> getSubjectListDetails(SubjectPojo subpojo) {
	
		return service.getSubjectListDetailsService(subpojo);
	}


	public String saveRemarkDetails(RemarksVo remarkform) {
		
		
		
		return service.saveRemarkDetailsService(remarkform);
	}


	public ArrayList<UpcomingRemarksVO> searchRemarkDetails(UpcomingRemarksVO remrakvo,UserLoggingsPojo userLoggingsVo) {
		
		return service.searchRemarkDetailsService(remrakvo,userLoggingsVo);
	}


	public boolean validateRemarkBD(RemarksVo remarkform) {
		
		return service.validateRemarkService(remarkform);
	}


	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails(UserLoggingsPojo userLoggingsVo) {
		
		return service.getMeetingListDetailsService(userLoggingsVo);
	}


	public String saveMeetingDetails(LstmsUpcomingMeetingVO meetingvo) {
		
		return service.saveMeetingDetailsService(meetingvo);
	}


	public ArrayList<LstmsUpcomingMeetingVO> searchMeetingDetails(
			LstmsUpcomingMeetingVO meetingvo,UserLoggingsPojo userLoggingsVo) {
		
		return service.searchMeetingDetailsService(meetingvo,userLoggingsVo);
	}


	public String createBdayDetails(UpcomingBdayVo bdayvo) {
		
		return service.createBdayDetailsService(bdayvo);
	}


	public ArrayList<UpcomingBdayVo> getBdayListDetails(UserLoggingsPojo userLoggingsVo) {
		
		return service.getBdayDetailsService(userLoggingsVo);
	}


	public ArrayList<UpcomingBdayVo> searchBdayDetails(UpcomingBdayVo bdayvo,UserLoggingsPojo userLoggingsVo) {
		
		return service.searchBdayDetailsService(bdayvo,userLoggingsVo);
	}


	public RemarksVo editRemarkDetailsBD(RemarksVo remarkvo) {
		
		return service.editRemarkDetailsService(remarkvo);
	}

	
	
	
	
}
