package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public interface AddSubjectService {
	
	public List<ViewallSubjectsVo> subjectdetails(String schoolLocation, UserLoggingsPojo custdetails);

	public String DeleteSubject(String[] subjectid, String[] locationList, ViewallSubjectsVo vo, String button, UserLoggingsPojo userLoggingsVo);

	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj, UserLoggingsPojo custdetails);

	public String addSubject(AddSubjectVo vo, UserLoggingsPojo userLoggingsVo);

	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj, UserLoggingsPojo dbdetails);

	public boolean updateSubjectDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo);

	public String getpath(String classname,UserLoggingsPojo userLoggingsVo);

	public String DdownloadsyllabuspathService(String subjectid, UserLoggingsPojo userLoggingsVo);

	public String validateSubName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);

	public List<ViewallSubjectsVo> labdetails(String schoolLocation, UserLoggingsPojo userLoggingsVo);

	public boolean addLab(AddLabVO labvo, UserLoggingsPojo userLoggingsVo);

	public List<AddSubjectForm> getLaboratoryDetails(UserLoggingsPojo userLoggingsVo);

	public String DeleteLab(String[] idList, String locationList, String reason, String statuss,UserLoggingsPojo userLoggingsVo);

	public AddSubjectForm getLabDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo);

	public boolean updateLabDetails(AddLabVO labvo, UserLoggingsPojo userLoggingsVo);

	public List<AddSubjectForm> labdetailsOnchangeListingPage(String locationid, String classname, String specialization, UserLoggingsPojo custdetails, String status);

	public String validateLabName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);



	public String DdownloadLabsyllabuspathService(String subjectid,UserLoggingsPojo userLoggingsVo);

	public String getSubjectName(String subjectid);

	public List<ViewallSubjectsVo> getSubDetailsList(String locationid, String classname, String specialization,UserLoggingsPojo userLoggingsVo, String status);

	public String updateSyllabusPath(String realPath,String location_id, String classname, UserLoggingsPojo userLoggingsVo, String custId, String academic_Year, String type, String specialization, String syllabus_Id);

	public String syllabusDownload(String locationid, String class_name, String sub_Id, String academicYear2,
			UserLoggingsPojo userLoggingsVo);


	public String checkDuplicateSubCount(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);


	public String getClassName(String classId, UserLoggingsPojo userLoggingsVo);

	public String checkLabCode(SubjectPojo pojo);



}
