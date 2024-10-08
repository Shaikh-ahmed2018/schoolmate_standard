package com.centris.campus.delegate;

import com.centris.campus.forms.StaffEmployementForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffEmployementService;
import com.centris.campus.serviceImpl.StaffEmployementServiceImpl;
import com.centris.campus.vo.StaffEmployementVo;

public class StaffEmployementBD {

	
	public StaffEmployementVo getStaffEmployementEntry(String teachercode, String academic_year, UserLoggingsPojo userLoggingsVo){
		
		StaffEmployementService service=new StaffEmployementServiceImpl();
		
		return service.getStaffEmployementEntry(teachercode,academic_year,userLoggingsVo);
	}
	
	public String saveStaffSalaryDetails(StaffEmployementForm staffForm,UserLoggingsPojo userLoggingsVo){
		
		StaffEmployementService service=new StaffEmployementServiceImpl();
		
		return service.saveStaffSalaryDetails(staffForm,userLoggingsVo);
	}
	
	public boolean validateEmployeePfNumber(String emppfno,String empid){
		StaffEmployementService service=new StaffEmployementServiceImpl();

		
		return new StaffEmployementServiceImpl().validateEmployeePfNumber(emppfno,empid);
	}

	public boolean validateBankAccNumber(String accnumber,String empid){
		StaffEmployementService service=new StaffEmployementServiceImpl();

		
		return new StaffEmployementServiceImpl().validateBankAccNumber(accnumber,empid);
	}

	public String saveStaffIncomeTaxTDSDeductionDetails(StaffEmployementForm staffForm) {
		StaffEmployementService service=new StaffEmployementServiceImpl();
		
		return service.saveStaffIncomeTaxTDSDeductionDetails(staffForm);
	}
	

}
