package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.AssignmentUploadForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AssignmentUploadService;
import com.centris.campus.serviceImpl.AssignmentUploadServiceImpl;
import com.centris.campus.vo.AssignmentUploadVo;
import com.centris.campus.vo.AssignmentViewVO;
import com.centris.campus.vo.ProjectVO;
import com.centris.campus.vo.ReportMenuVo;

public class AssignmentUploadBD {
	

	public ArrayList<AssignmentUploadVo> getStudentDetails(String section) {
	
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		return assignmentService.getStudentDetails(section);
	}
	
	public String insertAssignment(AssignmentViewVO viewVo,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.insertAssignment(viewVo, userLoggingsVo);
		
	}
	
	public ArrayList<AssignmentUploadVo> getAssignment(String userId,String accYearString,String searchTerm) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		return assignmentService.getAssignment(userId, accYearString,searchTerm);
	}
	
	public ArrayList<AssignmentUploadVo> getAssignmentDetails(String assignmentId) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.getAssignmentDetails(assignmentId);
	}
	
	public AssignmentUploadVo getSingleAssignment(String assignmentId) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.getSingleAssignment(assignmentId);
	}
	
	public String updateAssignmentDetails(AssignmentUploadVo assignmentVo,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.updateAssignmentDetails(assignmentVo,userLoggingsVo);
	}
	
	public String deleteAssignment(AssignmentViewVO assignmentCode,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.deleteAssignment(assignmentCode,userLoggingsVo);
	}

	public String addProject(ProjectVO viewVo,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.addProject(viewVo,userLoggingsVo);
	}
	
	public ArrayList<ProjectVO> getProjectList(String userId,String accYearString, String searchTerm) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.getProjectList(userId,accYearString,searchTerm);
	}

	public ArrayList<ProjectVO> getProjectDetails(String projectId) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.getProjectDetails(projectId);
	}

	public ProjectVO getSingleProject(String projectId) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.getSingleProject(projectId);
	}

	public String updateProjectDetails(ProjectVO projectvo,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.updateProjectDetails(projectvo,userLoggingsVo);
	}

	public String deleteProject(ProjectVO project,UserLoggingsPojo userLoggingsVo) {
		
		AssignmentUploadService assignmentService=new AssignmentUploadServiceImpl();
		
		return assignmentService.deleteProject(project,userLoggingsVo);
	}
}
