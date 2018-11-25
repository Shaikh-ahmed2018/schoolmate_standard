package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StaffPayrollService;
import com.centris.campus.serviceImpl.StaffPayrollServiceImpl;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;

public class StaffPayrollBD {
	
	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month, UserLoggingsPojo custid) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayrollList(year,month,custid);

	}
	
	public ArrayList<PayRollVo> getFlatpayRollProcess(String year,String month,String userId,UserLoggingsPojo custid) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getFlatpayRollProcess(year,month,userId,custid);

	}
	
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayrollDetails(year,month);

	}
	
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String year,String empId) {

		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getEmpMonthPayrollDetails(month,year,empId);

	}

	public List<PayRollVo> getPayRorllMonthList(String accyearid, UserLoggingsPojo userLoggingsVo) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getPayRorllMonthList(accyearid,userLoggingsVo);
	}

	public List<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId, String yearname, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
	
		return service.getPayRollList(accyearid,locationId,monthId,yearname,custid);
	}

	public String GenerateMultiplePayroll(PayRollVo vo, UserLoggingsPojo userLoggingsVo) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.GenerateMultiplePayroll(vo,userLoggingsVo);
	}

	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getAllPayrollList(payrollvo,custid);
	}

	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		
		return service.getGeneratePayrollList(payrollform,empId);
	}

	public String getLoctionName(String locationId, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getLoctionName(locationId,custid);
	}

	public String getAccYearName(String yearvalcode, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getAccYearName(yearvalcode,custid);
	}

	public String getMonthName(String yearvalcode, String monthvalcode, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getMonthName(yearvalcode,monthvalcode,custid);
	}

	public String getYearName(String month, String year,UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getYearName(month,year,custid);
	}

	public List<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getGeneratedPayRollList(accyearid,locationId,monthId,yearname,custid);
	}

	public List<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname, UserLoggingsPojo custid) {
		StaffPayrollService service = new StaffPayrollServiceImpl();
		return service.getEmpMonthPayrollDetailsDownload(accyear,locationId,monthId,yearname,custid);
	}
	
	
	

}
