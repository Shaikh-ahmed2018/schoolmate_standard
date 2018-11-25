package com.centris.campus.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ClassFeeSetupVo;


public interface ClassFeeSetupDao {
	
	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo, String location, UserLoggingsPojo userLoggingsVo);
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist,UserLoggingsPojo userLoggingsVo );
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo,UserLoggingsPojo userLoggingsVo);
	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo);
	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection, UserLoggingsPojo userLoggingsVo);
	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> successlist, Connection connection,String log_audit_session,UserLoggingsPojo userLoggingsVo);
	
}
