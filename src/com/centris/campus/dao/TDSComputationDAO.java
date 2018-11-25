package com.centris.campus.dao;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StaffEmployementVo;

public interface TDSComputationDAO {

	StaffEmployementVo getEmployeeDetails(String user,
			String currentUserId, String academic_year, UserLoggingsPojo userLoggingsVo);

	StaffEmployementVo getStaffMaximumLimitDetails(String academic_year, String location, UserLoggingsPojo userLoggingsVo);

}
