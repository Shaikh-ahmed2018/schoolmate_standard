package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.DepartmentMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.DepartmentMasterVO;

public interface DepartmentMasterDao {

	public ArrayList<DepartmentMasterVO> getDepartmentDetails(UserLoggingsPojo custdetails);

	public String insertDepartmentDetails(DepartmentMasterPojo deptpojo,
			String update_dept, UserLoggingsPojo userLoggingsVo);

	public String deleteDepartmentDetails(String[] deptid,UserLoggingsPojo custdetails, DepartmentMasterVO deptvo, String button);

	public String validateDeptName(DepartmentMasterForm deptForm, UserLoggingsPojo userLoggingsVo);

	public DepartmentMasterVO getEditDepartmentDetails(String edit,UserLoggingsPojo userLoggingsVo);

	public ArrayList<DepartmentMasterVO> searchDepartment(
			String searchvo, UserLoggingsPojo custdetails, String status);

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate, UserLoggingsPojo userLoggingsVo);

	public ArrayList<DepartmentMasterVO> getDeptStatusList(UserLoggingsPojo userLoggingsVo, String status);

	public ArrayList<DepartmentMasterVO> getdepartmentlist(UserLoggingsPojo custdetails);

}
