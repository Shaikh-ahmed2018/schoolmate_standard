package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassFeeSetupService;
import com.centris.campus.serviceImpl.ClassFeeSetupServiceImpl;
import com.centris.campus.vo.ClassFeeSetupVo;


public class ClassFeeSetupBD {

	public ArrayList<ClassFeeSetupVo> getFeeSetupDetails(String currentaccyear, UserLoggingsPojo userLoggingsVo) {
		ClassFeeSetupService service = new ClassFeeSetupServiceImpl();
		return service.getFeeSetupDetails(currentaccyear,userLoggingsVo);
	}
	
	public ArrayList<ClassFeeSetupVo> getSearchFeeSetupDetails(String searchTerm,String currentaccyear, UserLoggingsPojo userLoggingsVo) {
		ClassFeeSetupService service = new ClassFeeSetupServiceImpl();
		return service.getSearchFeeSetupDetails(searchTerm,currentaccyear,userLoggingsVo);
	}
	
	public ArrayList<ClassFeeSetupVo> getApprovedFees(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo) {

		ClassFeeSetupService service = new ClassFeeSetupServiceImpl();

		return service.getApprovedFees(feeSetupPojo,userLoggingsVo);
	}
	
	public ArrayList<ClassFeeSetupVo> getAllFees(ClassFeeSetupPojo feeSetupPojo, String location, UserLoggingsPojo userLoggingsVo) {

		ClassFeeSetupService service = new ClassFeeSetupServiceImpl();

		return service.getAllFees(feeSetupPojo,location,userLoggingsVo);
	}
	
	public int insertApproveFees(ArrayList<ClassFeeSetupPojo> approvefeelist, UserLoggingsPojo userLoggingsVo) {

		ClassFeeSetupService service = new ClassFeeSetupServiceImpl();

		return service.insertApproveFees(approvefeelist,userLoggingsVo);
	}
	
	public boolean deleteFees(ClassFeeSetupPojo feeSetupPojo,UserLoggingsPojo userLoggingsVo) {

		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();

		return feeSetupService.deleteFees(feeSetupPojo,userLoggingsVo);
	}

	public int insertFeeAmount(ArrayList<ClassFeeSetupPojo> feeSetupList, UserLoggingsPojo userLoggingsVo) {

		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();

		return feeSetupService.insertFeeAmount(feeSetupList,userLoggingsVo);
	}

	public ArrayList<ClassFeeSetupVo> getHeading(ClassFeeSetupPojo feeSetupPojo, UserLoggingsPojo userLoggingsVo) {
		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();

		return feeSetupService.getHeading(feeSetupPojo,userLoggingsVo);
	}

	public String getSpecialization(String classId, String accYear) {
		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();
		return feeSetupService.getSpecialization(classId,accYear);
	}

	public Set<ClassFeeSetupVo> insertStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo,String log_audit_session, UserLoggingsPojo userLoggingsVo,
			String branchName) {
		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();
		return feeSetupService.insertStudentXSL(list,username,demo,log_audit_session,userLoggingsVo,branchName);
	}

	public Set<ClassFeeSetupVo> insertTransportStudentXSL(List<ClassFeeSetupPojo> list, String username, String demo,String log_audit_session, UserLoggingsPojo userLoggingsVo) {
		ClassFeeSetupService feeSetupService = new ClassFeeSetupServiceImpl();
		return feeSetupService.insertTransportStudentXSL(list,username,demo,log_audit_session,userLoggingsVo);
	}
	
}