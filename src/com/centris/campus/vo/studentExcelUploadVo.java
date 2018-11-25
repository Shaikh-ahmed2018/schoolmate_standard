package com.centris.campus.vo;

public class studentExcelUploadVo {
	private String studentName;
	private String studentId;
	private String admissionNo;
	private String schoolName;
	private String locationId;
	private String academicYear;
	
	private String accyearId;
	private String className;
	private String classId;
	private String sectionName;
	private String sectionId;
	private String examName;
	private String examId;
	private String examCode;
	private String startdate;
	private String enddate;
	private String subjectName;
	private String subjectId;
	private String attandance;
	private String reason;
	private String streamName;
	private String streamId;
	private String examType;
	private String examTypePrefix;
	private String testMaximumMarks;
	private String testScoredMarks;
	private String notebookMaximumMarks;
	private String notebookScoredMarks;
	private String subjectMaximumMarks;
	private String subjectScoredMarks;
	private String workEducation;
	private String artEducation;
	private String healthEducation;
	private String descipline;
	private String remarks;
	private String studentIdNo;
	private String examTypeName;
	
	private String startdateCheck;
	private String enddateCheck;
	private int overridecount;	
	private String Stu_mark_id;
	
	public String getStu_mark_id() {
		return Stu_mark_id;
	}
	public void setStu_mark_id(String stu_mark_id) {
		Stu_mark_id = stu_mark_id;
	}
	public int getOverridecount() {
		return overridecount;
	}
	public void setOverridecount(int overridecount) {
		this.overridecount = overridecount;
	}
	public String getDataForOverRide() {
		return dataForOverRide;
	}
	public void setDataForOverRide(String dataForOverRide) {
		this.dataForOverRide = dataForOverRide;
	}
	private String dataForOverRide;
	private String presentA;
	private String absentA;
	
	public String getAbsentA() {
		return absentA;
	}
	public void setAbsentA(String absentA) {
		this.absentA = "absent";
	}
	public String getPresentA() {
		return presentA;
	}
	public void setPresentA(String presentA) {
		this.presentA = "present";
	}
	
	
	
	public String getStartdateCheck() {
		return startdateCheck;
	}
	public void setStartdateCheck(String startdateCheck) {
		this.startdateCheck = startdateCheck;
	}
	public String getEnddateCheck() {
		return enddateCheck;
	}
	public void setEnddateCheck(String enddateCheck) {
		this.enddateCheck = enddateCheck;
	}
	public String getExamTypeName() {
		return examTypeName;
	}
	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}
	public String getTestMaximumMarks() {
		return testMaximumMarks;
	}
	public void setTestMaximumMarks(String testMaximumMarks) {
		this.testMaximumMarks = testMaximumMarks;
	}
	public String getTestScoredMarks() {
		return testScoredMarks;
	}
	public void setTestScoredMarks(String testScoredMarks) {
		this.testScoredMarks = testScoredMarks;
	}
	public String getStudentIdNo() {
		return studentIdNo;
	}
	public void setStudentIdNo(String studentIdNo) {
		this.studentIdNo = studentIdNo;
	}
	public String getExamTypePrefix() {
		return examTypePrefix;
	}
	public void setExamTypePrefix(String examTypePrefix) {
		this.examTypePrefix = examTypePrefix;
	}
	public String getNotebookMaximumMarks() {
		return notebookMaximumMarks;
	}
	public void setNotebookMaximumMarks(String notebookMaximumMarks) {
		this.notebookMaximumMarks = notebookMaximumMarks;
	}
	public String getNotebookScoredMarks() {
		return notebookScoredMarks;
	}
	public void setNotebookScoredMarks(String notebookScoredMarks) {
		this.notebookScoredMarks = notebookScoredMarks;
	}
	public String getSubjectMaximumMarks() {
		return subjectMaximumMarks;
	}
	public void setSubjectMaximumMarks(String subjectMaximumMarks) {
		this.subjectMaximumMarks = subjectMaximumMarks;
	}
	public String getSubjectScoredMarks() {
		return subjectScoredMarks;
	}
	public void setSubjectScoredMarks(String subjectScoredMarks) {
		this.subjectScoredMarks = subjectScoredMarks;
	}
	public String getWorkEducation() {
		return workEducation;
	}
	public void setWorkEducation(String workEducation) {
		this.workEducation = workEducation;
	}
	public String getArtEducation() {
		return artEducation;
	}
	public void setArtEducation(String artEducation) {
		this.artEducation = artEducation;
	}
	public String getHealthEducation() {
		return healthEducation;
	}
	public void setHealthEducation(String healthEducation) {
		this.healthEducation = healthEducation;
	}
	public String getDescipline() {
		return descipline;
	}
	public void setDescipline(String descipline) {
		this.descipline = descipline;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getAccyearId() {
		return accyearId;
	}
	public void setAccyearId(String accyearId) {
		this.accyearId = accyearId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getAttandance() {
		return attandance;
	}
	public void setAttandance(String attandance) {
		this.attandance = attandance;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
