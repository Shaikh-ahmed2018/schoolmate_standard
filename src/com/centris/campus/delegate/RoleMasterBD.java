package com.centris.campus.delegate;

import java.util.List;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.RoleMasterService;
import com.centris.campus.serviceImpl.RoleMasterServiceImpl;

public class RoleMasterBD {

	RoleMasterService roleservice = new RoleMasterServiceImpl();

	public String addRole(RoleMasterPojo pojo, UserLoggingsPojo userLoggingsVo)
			throws Exception {
		return roleservice.addRole(pojo,userLoggingsVo);
	}

	public String updateRole(RoleMasterPojo pojo, UserLoggingsPojo userLoggingsVo)
			throws Exception {
		return roleservice.updateRole(pojo,userLoggingsVo);
	}

	public List<RoleMasterPojo> getRoles(UserLoggingsPojo custdetails, String location_id) throws Exception {
		return roleservice.getRoles(custdetails, location_id);
	}

	public String deleteRole(RoleMasterPojo roleMasterPojo, UserLoggingsPojo userLoggingsVo) throws Exception {
		return roleservice.deleteRole(roleMasterPojo,userLoggingsVo);
	}

	public RoleMasterPojo updateRole1(String roleCodeId, UserLoggingsPojo userLoggingsVo) throws Exception {
		return roleservice.updateRole1(roleCodeId,userLoggingsVo);
	}

	public boolean validateRoleName(String roleNameValidate,UserLoggingsPojo userLoggingsVo) throws Exception {
		return roleservice.validateRoleName(roleNameValidate, userLoggingsVo);
	}

	public boolean validateRoleNameUpdate(String roleNameValidate, String roleid, UserLoggingsPojo userLoggingsVo)
			throws Exception {
		return roleservice.validateRoleNameUpdate(roleNameValidate,roleid,userLoggingsVo);
	}
	
	public List<RoleMasterPojo> searchRole(String searchterm, UserLoggingsPojo custdetails) throws Exception {
		return roleservice.searchRole(searchterm,custdetails);
	}
	

}
