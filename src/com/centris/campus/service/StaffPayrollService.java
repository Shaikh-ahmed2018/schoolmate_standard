package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.LeaveRequestForm;
import com.centris.campus.forms.TeacherForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.PayRollVo;
import com.centris.campus.vo.StaffPayrollListVo;


public interface StaffPayrollService {
	
	public ArrayList<StaffPayrollListVo> getPayrollList(String year,String month, UserLoggingsPojo custid);
	public ArrayList<PayRollVo> getFlatpayRollProcess(String year,String month,String userId,UserLoggingsPojo custid);
	public ArrayList<PayRollVo> getPayrollDetails(int year,int month);
	public ArrayList<PayRollVo> getEmpMonthPayrollDetails(String month,String year,String empId);
	public List<PayRollVo> getPayRorllMonthList(String accyearid, UserLoggingsPojo userLoggingsVo);
	public List<PayRollVo> getPayRollList(String accyearid, String locationId, String monthId, String yearname, UserLoggingsPojo custid);
	public String GenerateMultiplePayroll(PayRollVo vo, UserLoggingsPojo userLoggingsVo);
	public ArrayList<PayRollVo> getAllPayrollList(PayRollVo payrollvo, UserLoggingsPojo custid);
	public ArrayList<PayRollVo> getGeneratePayrollList(LeaveRequestForm payrollform, String empId);
	public String getLoctionName(String locationId, UserLoggingsPojo custid);
	public String getAccYearName(String yearvalcode, UserLoggingsPojo custid);
	public String getMonthName(String yearvalcode, String monthvalcode, UserLoggingsPojo custid);
	public List<PayRollVo> getGeneratedPayRollList(String accyearid, String locationId, String monthId,String yearname, UserLoggingsPojo custid);
	public List<PayRollVo> getEmpMonthPayrollDetailsDownload(String accyear, String locationId, String monthId,String yearname, UserLoggingsPojo custid);
	public String getYearName(String month, String year, UserLoggingsPojo custid);

}
