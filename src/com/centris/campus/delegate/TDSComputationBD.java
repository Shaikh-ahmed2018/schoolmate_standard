package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TDSComputationService;
import com.centris.campus.serviceImpl.TDSComputationServiceIMPL;
import com.centris.campus.vo.StaffEmployementVo;

public class TDSComputationBD {
	
	TDSComputationService service = new TDSComputationServiceIMPL();

	public StaffEmployementVo getEmployeeDetails(String user,String currentUserId, String academic_year, UserLoggingsPojo userLoggingsVo) {
		// TODO Auto-generated method stub
		return service.getEmployeeDetails(user,currentUserId,academic_year,userLoggingsVo);
	}

	public StaffEmployementVo getStaffMaximumLimitDetails(String academic_year, String location, UserLoggingsPojo userLoggingsVo) {
		return service.getStaffMaximumLimitDetails(academic_year,location,userLoggingsVo);
	}

}
