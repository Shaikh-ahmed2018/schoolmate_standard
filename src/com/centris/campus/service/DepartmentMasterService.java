package com.centris.campus.service;

import java.util.ArrayList;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.DepartmentMasterVO;

public interface DepartmentMasterService {

	ArrayList<DepartmentMasterVO> getDepartmentDetails(UserLoggingsPojo custdetails);

	String insertDepartmentDetails(DepartmentMasterForm deptform,
			String update_dept, UserLoggingsPojo userLoggingsVo);

	String deleteDepartmentDetails(String[] deptid,UserLoggingsPojo custdetails, DepartmentMasterVO deptvo, String button);

	String validateDeptName(DepartmentMasterForm deptform, UserLoggingsPojo userLoggingsVo);

	DepartmentMasterVO getEditDepartmentDetails(String edit,UserLoggingsPojo userLoggingsVo);

	ArrayList<DepartmentMasterVO> searchDepartment(String string, UserLoggingsPojo custdetails, String status);

	boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate, UserLoggingsPojo userLoggingsVo);

	ArrayList<DepartmentMasterVO> getDeptStatusList(UserLoggingsPojo userLoggingsVo, String status);

	ArrayList<DepartmentMasterVO> getdepartmentlist(UserLoggingsPojo custdetails);

}
