package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.AssignmentUploadForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ProjectVO;

public interface AssignmentUploadDao {
	
	public ArrayList<AssignmentUploadVo> getStudentDetails(String section) ;
	public String insertAssignment(AssignmentViewVO viewVo,UserLoggingsPojo userLoggingsVo);
	public ArrayList<AssignmentUploadVo> getAssignment(String userId,String accYearString,String searchTerm);
	public ArrayList<AssignmentUploadVo> getAssignmentDetails(String assignmentId) ;
	public AssignmentUploadVo getSingleAssignment(String assignmentId);
	public String updateAssignmentDetails(AssignmentUploadVo assignmentVo,UserLoggingsPojo userLoggingsVo);
	public String deleteAssignment(AssignmentViewVO assignmentCode,UserLoggingsPojo userLoggingsVo);
	public String addProject(ProjectVO viewVo,UserLoggingsPojo userLoggingsVo);
	public ArrayList<ProjectVO> getProjectList(String userId, String accYearString, String searchTerm);
	public ArrayList<ProjectVO> getProjectDetails(String projectId);
	public ProjectVO getSingleProject(String projectId);
	public String updateProjectDetails(ProjectVO projectvo,UserLoggingsPojo userLoggingsVo);
	public String deleteProject(ProjectVO project,UserLoggingsPojo userLoggingsVo);

}
