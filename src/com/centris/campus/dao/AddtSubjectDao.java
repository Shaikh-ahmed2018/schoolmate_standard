package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.AddSubjectForm;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddLabVO;
import com.centris.campus.vo.AddSubjectVo;
import com.centris.campus.vo.ViewallSubjectsVo;

public interface AddtSubjectDao {
	public ArrayList<ViewallSubjectsVo> subjectdetails(String schoolLocation, UserLoggingsPojo custdetails);
	
	public List<ViewallSubjectsVo> searchsubjectdetails(ViewallSubjectsVo voObj, UserLoggingsPojo custdetails);
	public String addSubject(AddSubjectVo vo, UserLoggingsPojo userLoggingsVo);
	public ViewallSubjectsVo getSubjectDetails(ViewallSubjectsVo obj, UserLoggingsPojo dbdetails);
	
	public boolean updateSubjectDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo);
	public String validateSubName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);
	public  ArrayList<ViewallSubjectsVo> labdetails(String schoolLocation, UserLoggingsPojo userLoggingsVo);
	public boolean addLab(AddLabVO obj, UserLoggingsPojo userLoggingsVo);
	public String DeleteLab(String[] idList, String locationList, String reason, String custId, UserLoggingsPojo userLoggingsVo);
	public AddSubjectForm getLabDetails(AddSubjectForm obj, UserLoggingsPojo userLoggingsVo);
	public boolean updateLabDetails(AddLabVO addSubjectForm, UserLoggingsPojo userLoggingsVo);
	public String validateLabName(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);
	public String getSubjectName(String subjectid);
	public List<ViewallSubjectsVo> getSubDetailsList(String locationid, String classname, String specialization,UserLoggingsPojo userLoggingsVo, String status);
	public String updateSyllabusPath(String realPath,String location_id, String classname, UserLoggingsPojo userLoggingsVo, String custId,String academic_Year, String type, String specialization, String syllabus_Id);
	public String syllabusDownload(String locationid, String class_name, String sub_Id, String academicYear2,UserLoggingsPojo userLoggingsVo);
	public String checkDuplicateSubCount(AddSubjectForm form1, UserLoggingsPojo userLoggingsVo);
	public String getClassName(String classId, UserLoggingsPojo userLoggingsVo);

	public String DeleteSubject(String[] voObj, String[] locationList, ViewallSubjectsVo vo, String button,
			UserLoggingsPojo userLoggingsVo);

	public String checkLabCode(SubjectPojo pojo);

}
