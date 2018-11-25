package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ClassFeeSetupVo;


public interface ClassFeeSetupService {
	
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo, String location, UserLoggingsPojo userLoggingsVo);
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist,  UserLoggingsPojo userLoggingsVo);
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupList, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public String getSpecialization(String classId, String accYear);
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo,String log_audit_session, UserLoggingsPojo userLoggingsVo, String branchName);
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo,String log_audit_session, UserLoggingsPojo userLoggingsVo);
	
	
}
