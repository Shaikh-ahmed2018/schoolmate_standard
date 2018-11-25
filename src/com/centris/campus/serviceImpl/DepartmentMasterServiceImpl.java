package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.dao.DepartmentMasterDao;
import com.centris.campus.daoImpl.DepartmentMasterDAOImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.forms.DepartmentMasterForm;
import com.centris.campus.pojo.DepartmentMasterPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.DepartmentMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;

public class DepartmentMasterServiceImpl implements DepartmentMasterService {

	private static Logger logger = Logger
			.getLogger(DepartmentMasterServiceImpl.class);

	public ArrayList<DepartmentMasterVO> getDepartmentDetails(UserLoggingsPojo custdetails) {

		DepartmentMasterDao departmentMasterDao = new DepartmentMasterDAOImpl();
		return departmentMasterDao.getDepartmentDetails(custdetails);
	}

	@Override
	public String insertDepartmentDetails(DepartmentMasterForm deptform,
			String update_dept,UserLoggingsPojo userLoggingsVo) {

		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl: insertDepartmentDetails : Starting");

		String result = null;

		try {
			DepartmentMasterPojo deptpojo = new DepartmentMasterPojo();

			

			deptpojo.setDepartmentid(IDGenerator.getPrimaryKeyID("campus_department",userLoggingsVo));

			deptpojo.setDeptname(deptform.getDept_name());
			deptpojo.setDeptdescription(deptform.getDepartment_description());
			deptpojo.setCreatedby(deptform.getUsername());
			deptpojo.setLog_audit_session(deptform.getLog_audit_session());

		

			result = DepartmentMasterDao.insertDepartmentDetails(deptpojo,update_dept,userLoggingsVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in DepartmentMasterServiceImpl : insertDepartmentDetails: Ending");
		return result;
	}

	public String deleteDepartmentDetails(String[] deptid,UserLoggingsPojo custdetails,DepartmentMasterVO deptvo,String button) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.deleteDepartmentDetails(deptid,custdetails,deptvo,button);
	}

	public String validateDeptName(DepartmentMasterForm deptfForm,UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.validateDeptName(deptfForm,userLoggingsVo);
	}

	public DepartmentMasterVO getEditDepartmentDetails(String edit,UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.getEditDepartmentDetails(edit,userLoggingsVo);
	}

	public ArrayList<DepartmentMasterVO> searchDepartment(String searchvo, UserLoggingsPojo custdetails,String status) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.searchDepartment(searchvo,custdetails,status);

	}

	public boolean validateDeptNameUpdate(DepartmentMasterVO validateUpdate, UserLoggingsPojo userLoggingsVo) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.validateDeptNameUpdate(validateUpdate,userLoggingsVo);
	}

	@Override
	public ArrayList<DepartmentMasterVO> getDeptStatusList(UserLoggingsPojo userLoggingsVo, String status) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.getDeptStatusList(userLoggingsVo,status);
	}

	@Override
	public ArrayList<DepartmentMasterVO> getdepartmentlist(UserLoggingsPojo custdetails) {
		DepartmentMasterDao DepartmentMasterDao = new DepartmentMasterDAOImpl();
		return DepartmentMasterDao.getdepartmentlist(custdetails);
	}

}
