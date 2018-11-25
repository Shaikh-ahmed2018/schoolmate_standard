package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.AbsentSMSServiceImpl;

public class AbsentSMSBD {

	public ArrayList<AbsentsSMSPojo> absentlist(AbsentsSMSPojo pojo, UserLoggingsPojo custdetails, String schoolLocation) {
		
		return new AbsentSMSServiceImpl().absentlist(pojo,custdetails,schoolLocation);
	}

	public String storeAbsentSms(AbsentsSMSPojo absentpojo, UserLoggingsPojo custdetails) {
		
		return new AbsentSMSServiceImpl().storeAbsentSms(absentpojo,custdetails);
	}

	public boolean validateAbsentSms(String date, String smstext, AbsentsSMSPojo abpojo,UserLoggingsPojo userLoggingsVo) {
		
		return new AbsentSMSServiceImpl().validateAbsentSms(date,smstext,abpojo,userLoggingsVo);
	}

}
