package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.daoImpl.StaffEmployementDaoImpl;
import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StaffEmployementVo;
import com.centris.campus.vo.StaffSearchVo;

public interface StaffEmployementDao {
	
	public StaffEmployementVo getStaffEmployementEntry(String teachercode, String academic_year, UserLoggingsPojo userLoggingsVo);
	public String saveStaffSalaryDetails(StaffEmployementForm staffForm,UserLoggingsPojo userLoggingsVo);
	
	public boolean validateEmployeePfNumber(String emppfno,String empid);
		
	public boolean validateBankAccNumber(String accnumber,String empid);
	public String saveStaffIncomeTaxTDSDeductionDetails(StaffEmployementForm staffForm);
}
