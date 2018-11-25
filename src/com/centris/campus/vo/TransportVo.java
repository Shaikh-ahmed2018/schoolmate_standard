package com.centris.campus.vo;

import java.sql.Date;

public class TransportVo {

	private String routeName;
	private String routeNo;
	private String routeLogicName;
	private String routeType;
	private String costPerPerson;
	private String stratTime;
	private String endTime;
	private String totalStops;
	private String totalDistance;
	private String routeCode;

	private String halttime;
	private String destination;
	
	private String stopname;
	private String arrivaltime;
	private String departuretime;
	private String distance;
	private String stage_name;
	private String stage_id;
	private String stageCode;
	private String stageName;
	private int count;
	private int amount;
	private String translocation;
	
	private String studentId;
	private String classId;
	private String sectionId;
	private String termId;
	private String date;
	private String loc_id;
	private String acy_id;
	private String student_name;
	private String classname;
	private String admisssion_no;
	private String parent_id;
	private String address;
	private String section_name;
	private String status;
	private String acy_name;
	private String transport_status;
	private String term_name;
	private String start_date;
	private String end_date;
	private String[] listof_months;
	private String months;
	private Integer sno; 
	private String student_status;
	private String monthNo;
	private String stmonth;
	private String endmonth;
	private String noofmonths;
	private String receiptNo;
	private String mobileNo;
	private String custid;
	private String searchName;
	private String remarks;
	private String[] routeId; 
	private String inactivereason; 
	private String activereason; 
	private String otherreason; 
	private String log_audit_session;
	private String vehicleId;
	private String vehicleType;
	private String description;
	
	private String[] vehicleIds;
	private String typeId;
	private String typeName;
	private String studentImage;
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getNoofmonths() {
		return noofmonths;
	}

	public void setNoofmonths(String noofmonths) {
		this.noofmonths = noofmonths;
	}

	public String getStmonth() {
		return stmonth;
	}

	public void setStmonth(String stmonth) {
		this.stmonth = stmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public String getMonthNo() {
		return monthNo;
	}

	public void setMonthNo(String monthNo) {
		this.monthNo = monthNo;
	}

	public String getStudent_status() {
		return student_status;
	}

	public void setStudent_status(String student_status) {
		this.student_status = student_status;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String[] getListof_months() {
		return listof_months;
	}

	public void setListof_months(String[] listof_months) {
		this.listof_months = listof_months;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getTerm_name() {
		return term_name;
	}

	public void setTerm_name(String term_name) {
		this.term_name = term_name;
	}

	public String getTransport_status() {
		return transport_status;
	}

	public void setTransport_status(String transport_status) {
		this.transport_status = transport_status;
	}

	public String getAcy_name() {
		return acy_name;
	}

	public void setAcy_name(String acy_name) {
		this.acy_name = acy_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getAdmisssion_no() {
		return admisssion_no;
	}

	public void setAdmisssion_no(String admisssion_no) {
		this.admisssion_no = admisssion_no;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getAcy_id() {
		return acy_id;
	}

	public void setAcy_id(String acy_id) {
		this.acy_id = acy_id;
	}

	public String getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTranslocation() {
		return translocation;
	}

	public void setTranslocation(String translocation) {
		this.translocation = translocation;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStage_name() {
		return stage_name;
	}

	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}

	public String getStage_id() {
		return stage_id;
	}

	public void setStage_id(String stage_id) {
		this.stage_id = stage_id;
	}

	public String getStopname() {
		return stopname;
	}

	public void setStopname(String stopname) {
		this.stopname = stopname;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getRouteLogicName() {
		return routeLogicName;
	}

	public void setRouteLogicName(String routeLogicName) {
		this.routeLogicName = routeLogicName;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getCostPerPerson() {
		return costPerPerson;
	}

	public void setCostPerPerson(String costPerPerson) {
		this.costPerPerson = costPerPerson;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTotalStops() {
		return totalStops;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTotalStops(String totalStops) {
		this.totalStops = totalStops;
	}

	public String getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}

	public String getHalttime() {
		return halttime;
	}

	public void setHalttime(String halttime) {
		this.halttime = halttime;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String[] getRouteId() {
		return routeId;
	}

	public void setRouteId(String[] routeId) {
		this.routeId = routeId;
	}

	public String getInactivereason() {
		return inactivereason;
	}

	public void setInactivereason(String inactivereason) {
		this.inactivereason = inactivereason;
	}

	public String getActivereason() {
		return activereason;
	}

	public void setActivereason(String activereason) {
		this.activereason = activereason;
	}

	public String getOtherreason() {
		return otherreason;
	}

	public void setOtherreason(String otherreason) {
		this.otherreason = otherreason;
	}

	public String getLog_audit_session() {
		return log_audit_session;
	}

	public void setLog_audit_session(String log_audit_session) {
		this.log_audit_session = log_audit_session;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(String[] vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStudentImage() {
		return studentImage;
	}

	public void setStudentImage(String studentImage) {
		this.studentImage = studentImage;
	}

}