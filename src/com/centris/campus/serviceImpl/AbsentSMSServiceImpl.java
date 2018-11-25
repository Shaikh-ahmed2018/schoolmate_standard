package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.AbsentSMSDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AbsentSMSService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class AbsentSMSServiceImpl implements AbsentSMSService{

	private static final Logger logger = Logger.getLogger(AbsentSMSServiceImpl.class);

	public ArrayList<AbsentsSMSPojo> absentlist(AbsentsSMSPojo pojo, UserLoggingsPojo custdetails, String schoolLocation) {

		return new AbsentSMSDaoImpl().absentlist(pojo,custdetails,schoolLocation);
	}

	public String storeAbsentSms(AbsentsSMSPojo absentpojo, UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSServiceImpl: storeAbsentSms Starting");

		String absentcode=null,status=null;
		int count=0,count1=0,count2=0;

		try {
			absentcode=IDGenerator.getPrimaryKeyID("sms_absent_details",custdetails);
			absentpojo.setAbsentcode(absentcode);

			count = new AbsentSMSDaoImpl().storeAbsentDetails(absentpojo,custdetails);
			
			if(count != 10){
				count1 = new AbsentSMSDaoImpl().storeAbsentStudent(absentpojo,custdetails);
				count2 = new AbsentSMSDaoImpl().storeAbsentSections(absentpojo,custdetails);

				if(count>0 && count1>0 && count2 >0){
					status="success";
				}	
				else{
					status="failure";
				}
			}else{
				status = "insufficientSMSBalance";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AbsentSMSServiceImpl: storeAbsentSms Ending");
		return status;
	}

	public boolean validateAbsentSms(String date, String smstext, AbsentsSMSPojo abpojo,UserLoggingsPojo userLoggingsVo) {

		return new AbsentSMSDaoImpl().validateAbsentSms(date,smstext,abpojo,userLoggingsVo);
	}

}
