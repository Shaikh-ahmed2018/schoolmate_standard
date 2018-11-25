package com.centris.campus.delegate;

import java.util.ArrayList;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.DepartmentMasterService;
import com.centris.campus.serviceImpl.DepartmentMasterServiceImpl;
import com.centris.campus.vo.DepartmentMasterVO;

public class DepartmentMasterBD {

	public ArrayList<DepartmentMasterVO> getDepartmentDetails(UserLoggingsPojo custdetails) {

		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getDepartmentDetails(custdetails);

	}

	public String insertDepartmentDetails(DepartmentMasterForm deptform,String update_dept, UserLoggingsPojo userLoggingsVo) 
	{
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.insertDepartmentDetails(deptform,update_dept,userLoggingsVo);
	}

	public String validateDeptName(DepartmentMasterForm deptform, UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.validateDeptName(deptform,userLoggingsVo);
	}

	public String deleteDepartmentDetails(String[] deptid,UserLoggingsPojo custdetails, DepartmentMasterVO deptvo, String button) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.deleteDepartmentDetails(deptid,custdetails,deptvo,button);
	}

	public DepartmentMasterVO getEditDepartmentDetails(String edit,UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getEditDepartmentDetails(edit,userLoggingsVo);
	}

	public ArrayList<DepartmentMasterVO> searchDepartment(
			String string, UserLoggingsPojo custdetails, String status) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.searchDepartment(string,custdetails,status);

	}

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate, UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.validateDeptNameUpdate(validateUpdate,userLoggingsVo);
	}

	public ArrayList<DepartmentMasterVO> getDeptStatusList(UserLoggingsPojo userLoggingsVo, String status) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getDeptStatusList(userLoggingsVo,status);
	}

	public ArrayList<DepartmentMasterVO> getdepartmentlist(UserLoggingsPojo custdetails) {
		DepartmentMasterService departmentMasterService = new DepartmentMasterServiceImpl();
		return departmentMasterService.getdepartmentlist(custdetails);
	}


}
