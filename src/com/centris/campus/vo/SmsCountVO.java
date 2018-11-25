package com.centris.campus.vo;

import java.util.List;

public class SmsCountVO {
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	private String absent;
	private String event;
	private String homework;
	private String lateCome;
	private String classId;
	private String count;
	
	private List <SmsCountVO> classList;
	private List <SmsCountVO> homeWorkSMSList;
	private List <SmsCountVO> lateComeSMSList;
	private List <SmsCountVO> absentSMSList;
	private List <SmsCountVO> eventSMSList;

	
	
	public List<SmsCountVO> getClassList() {
		return classList;
	}
	public void setClassList(List<SmsCountVO> classList) {
		this.classList = classList;
	}
	public List<SmsCountVO> getHomeWorkSMSList() {
		return homeWorkSMSList;
	}
	public void setHomeWorkSMSList(List<SmsCountVO> homeWorkSMSList) {
		this.homeWorkSMSList = homeWorkSMSList;
	}
	public List<SmsCountVO> getLateComeSMSList() {
		return lateComeSMSList;
	}
	public void setLateComeSMSList(List<SmsCountVO> lateComeSMSList) {
		this.lateComeSMSList = lateComeSMSList;
	}
	public List<SmsCountVO> getAbsentSMSList() {
		return absentSMSList;
	}
	public void setAbsentSMSList(List<SmsCountVO> absentSMSList) {
		this.absentSMSList = absentSMSList;
	}
	public List<SmsCountVO> getEventSMSList() {
		return eventSMSList;
	}
	public void setEventSMSList(List<SmsCountVO> eventSMSList) {
		this.eventSMSList = eventSMSList;
	}

	
	public String getAbsent() {
		return absent;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getHomework() {
		return homework;
	}
	public void setHomework(String homework) {
		this.homework = homework;
	}
	public String getLateCome() {
		return lateCome;
	}
	public void setLateCome(String lateCome) {
		this.lateCome = lateCome;
	}
	
}
