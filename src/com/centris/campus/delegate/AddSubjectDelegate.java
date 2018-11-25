package com.centris.campus.delegate;

import java.util.List;
import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AddSubjectService;
import com.centris.campus.serviceImpl.AddSubjectServiceImpl;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public class AddSubjectDelegate {

	AddSubjectService service = new AddSubjectServiceImpl();

	public List<ViewallSubjectsVo> subjectdetails(String schoolLocation, UserLoggingsPojo custdetails) {
		return service.subjectdetails(schoolLocation,custdetails);
	}

	public String DeleteSubject(String[] subjectid, String[] locationList, ViewallSubjectsVo vo, String button, UserLoggingsPojo userLoggingsVo) {
		return service.DeleteSubject(subjectid,locationList,vo,button,userLoggingsVo);
	}

	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj, UserLoggingsPojo custdetails) {
		return service.searchsubjectdetails(voObj,custdetails);
	}

	public String addSubject(AddSubjectVo vo, UserLoggingsPojo userLoggingsVo) {
		return service.addSubject(vo,userLoggingsVo);
	}

	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj, UserLoggingsPojo dbdetails) {
		return service.getSubjectDetails(obj,dbdetails);
	}

	public boolean updateSubjectDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo) {
		return service.updateSubjectDetails(obj,userLoggingsVo);
	}

	public String getpath(String classname,UserLoggingsPojo userLoggingsVo) {
		return service.getpath(classname,userLoggingsVo);
	}

	public String DdownloadsyllabuspathBD(String subjectid, UserLoggingsPojo userLoggingsVo) {
		
		return service.DdownloadsyllabuspathService(subjectid,userLoggingsVo);
	}
	public String validateSubName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo) {
		return service.validateSubName(form1,userLoggingsVo);
	}

	public List<ViewallSubjectsVo> subjectdetailsOnchangeListingPage(String locationid, String classname, String specialization,UserLoggingsPojo custdetails, String status) {
		return new AddSubjectServiceImpl().subjectdetailsOnchangeListingPage( locationid,  classname,  specialization,custdetails,status);
	}

	public List<ViewallSubjectsVo> labdetails(String schoolLocation, UserLoggingsPojo userLoggingsVo) {
		return service.labdetails(schoolLocation,userLoggingsVo);
	}

	public boolean addLab(AddLabVO labvo, UserLoggingsPojo userLoggingsVo) {
		return service.addLab(labvo,userLoggingsVo);
	}

	public List<AddSubjectForm> getLaboratoryDetails(UserLoggingsPojo userLoggingsVo) {
		return service.getLaboratoryDetails(userLoggingsVo);
	}

	public String DeleteLab(String[] idList, String locationList, String reason, String status, UserLoggingsPojo userLoggingsVo) {
		return service.DeleteLab(idList,locationList, reason, status, userLoggingsVo);
	}

	public AddSubjectForm getLabDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo) {
		return service.getLabDetails(obj,userLoggingsVo);
	}

	public boolean updateLabDetails(AddLabVO labvo, UserLoggingsPojo userLoggingsVo) {
		
		return service.updateLabDetails(labvo,userLoggingsVo); 
	}

	public List<AddSubjectForm> labdetailsOnchangeListingPage(
			String locationid, String classname, String specialization, UserLoggingsPojo custdetails, String status) {
		return service.labdetailsOnchangeListingPage(locationid,classname,specialization,custdetails,status);
	}

	public String validateLabName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo) {
		return service.validateLabName(form1,userLoggingsVo);
	}

	public String DdownloadLabsyllabuspathBD(String subjectid,UserLoggingsPojo userLoggingsVo) {
		return service.DdownloadLabsyllabuspathService(subjectid,userLoggingsVo);
	}

	public String getSubjectName(String subjectid) {
		return service.getSubjectName(subjectid);
	}

	public List<ViewallSubjectsVo> getSubDetailsList(String locationid, String classname, String specialization,
			UserLoggingsPojo userLoggingsVo, String status) {
		
		return service.getSubDetailsList(locationid,classname,specialization,userLoggingsVo,status);
	}

	public String updateSyllabusPath(String realPath,String locationid,String classId,UserLoggingsPojo userLoggingsVo,String academic_Year,String type,String specialization,String subjectId, String syllabus_Id) {
		return service.updateSyllabusPath(realPath, locationid, classId, userLoggingsVo, academic_Year, type, specialization, subjectId, syllabus_Id);
	}

	public String syllabusDownload(String locationid, String class_name, String sub_Id, String academicYear2,
			UserLoggingsPojo userLoggingsVo) {
		
		return service.syllabusDownload( locationid,  class_name,  sub_Id,  academicYear2,userLoggingsVo);
	}


	public String checkDuplicateSubCount(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo) {
		return service.checkDuplicateSubCount(form1,userLoggingsVo);
	}


	public String getClassName(String classId, UserLoggingsPojo userLoggingsVo) {
		
		return  service.getClassName( classId,  userLoggingsVo);
	}

	public String checkLabCode(SubjectPojo pojo) {
		return  service.checkLabCode(pojo);
	}



}
