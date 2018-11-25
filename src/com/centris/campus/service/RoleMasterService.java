package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.RoleMasterForm;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;


public interface RoleMasterService {
	public  String addRole(RoleMasterPojo pojo, UserLoggingsPojo userLoggingsVo) throws Exception;
	public List<RoleMasterPojo> getRoles(UserLoggingsPojo custdetails, String location_id) throws Exception;
	public String updateRole(RoleMasterPojo pojo, UserLoggingsPojo userLoggingsVo)throws Exception;
	public String deleteRole(RoleMasterPojo roleMasterPojo, UserLoggingsPojo userLoggingsVo) throws Exception;
	public RoleMasterPojo updateRole1(String roleCodeId, UserLoggingsPojo userLoggingsVo) throws Exception;
	public boolean validateRoleName(String roleNameValidate, UserLoggingsPojo userLoggingsVo) throws Exception;
	public boolean validateRoleNameUpdate(String roleNameValidate, String roleid, UserLoggingsPojo userLoggingsVo)throws Exception;
	public List<RoleMasterPojo> searchRole(String searchterm, UserLoggingsPojo custdetails)throws Exception;
	
	
}
