package com.centris.campus.admin;

import java.sql.Connection;

import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;

public class SMSThread implements Runnable{

	private String code;
	private String date;
	private String category;
	private String log_audit_session;
	private String module;
	private String submodule;
	private CustomerDBDetails dbdetails;
	private String custid;
	private UserLoggingsPojo custdetails;
	private int count;
	private String locid;

	public SMSThread(String code, String date,String category, String string, String string2, String string3, UserLoggingsPojo custdetails) {
		this.code=code;
		this.date=date;
		this.category=category;
		this.custdetails=custdetails;
	}

	public SMSThread(String code, String date,String category, String string, String string2, String string3, UserLoggingsPojo custdetails,String locid) {
		this.code=code;
		this.date=date;
		this.category=category;
		this.custdetails=custdetails;
		this.locid=locid;
	}
	public SMSThread(String code, String date,String category,String log_audit_session,String module,String submodule,CustomerDBDetails dbdetails) {
		this.code=code;
		this.date=date;
		this.category=category;
		this.log_audit_session=log_audit_session;
		this.module=module;
		this.submodule=submodule;
		this.dbdetails = dbdetails;
	}
	public SMSThread(String code, String date,String category,String log_audit_session,String module,String submodule,String custid) {
		this.code=code;
		this.date=date;
		this.category=category;
		this.log_audit_session=log_audit_session;
		this.module=module;
		this.submodule=submodule;
		this.custid = custid;
	}

	public SMSThread(String code, String date,String category, String string, String string2, String string3, UserLoggingsPojo custdetails,int count,String locid) {
		this.code=code;
		this.date=date;
		this.category=category;
		this.custdetails=custdetails;
		this.count = count;
		this.locid=locid;
	}

	@Override
	public void run() {
		if("meeting".equals(category)){
			new SMSDetails().getMeetingDetails(code,date,log_audit_session,module,submodule,custdetails,count,locid);
		}
		else if("event".equals(category)){
			new SMSDetails().getEventDetails(code,date,log_audit_session,module,submodule,custdetails,count,locid);
		}
		else if("LateComers".equals(category)){
			new SMSDetails().getLateComingStudentDetails(code,date,custdetails,log_audit_session,module,submodule,locid);
		}
		else if("Uniform".equals(category)){
			new SMSDetails().getUniformDetails(code,date,custdetails);
		}
		else if("Absent".equals(category)){
			new SMSDetails().getAbsentDetails(code,date,log_audit_session,module,submodule,custdetails,locid);
		}
		else if("Birthday".equals(category)){
			new SMSDetails().getBirthdayWishesDetails(code,date,custdetails);
		}
		else if("Fee".equals(category)){
			new SMSDetails().getFeeDetails(code,date,custdetails);
		}
		else if("HomeWork".equalsIgnoreCase(category)){
			new SMSDetails().getHomeWorkDetails(code,date,log_audit_session,module,submodule,custdetails,locid);
		}
		else if("OtherSMS".equalsIgnoreCase(category)){
			new SMSDetails().getOtherSMSDetails(code,date,log_audit_session,module,submodule,custdetails);
		}
		else if("Holiday".equals(category)){
			new SMSDetails().getHolidayDetails(code,date,log_audit_session,module,submodule,custdetails,count,locid);
		}
		else if("FeeSMS".equalsIgnoreCase(category)){
			new SMSDetails().getFeeSMSList(code,date,log_audit_session,module,submodule,custdetails,locid);
		}

	}

}
