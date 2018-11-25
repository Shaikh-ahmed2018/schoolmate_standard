package com.centris.campus.service;

import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.pojo.UserManagementPojo;
import com.centris.campus.vo.UserRecordVO;

public interface UserManagementService {

	List<UserRecordVO> getUserRecordsService(UserLoggingsPojo custdetails);

	List<UserRecordVO> getSearchUserDetailsService(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails);

	UserRecordVO getUserDeatils(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo);

	String changePassword(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo);

	String blockUser(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails);

	String insertUserDetails(UserManagementPojo details, UserLoggingsPojo userLoggingsVo);

	List<UserRecordVO> getUserDetails(UserLoggingsPojo pojo);

	UserRecordVO getUserDetailsforEdit(UserRecordVO details, UserLoggingsPojo userLoggingsVo);

	String blockUserByStatus(UserManagementPojo userManagementPojo,UserLoggingsPojo userLoggingsVo);

	String blockUserStatus(UserManagementPojo userManagementPojo, UserLoggingsPojo custdetails);

	List<UserRecordVO> userListSearch(UserManagementPojo userManagementPojo, UserLoggingsPojo userLoggingsVo);

}
