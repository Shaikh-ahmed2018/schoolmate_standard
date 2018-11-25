package com.centris.campus.serviceImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.AddDesignationDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AddDesignationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;




public class AddDesignationServiceImpl  implements AddDesignationService{

	private static 	Logger logger = Logger.getLogger(AddDesignationServiceImpl.class);
	
	public String insertDesignationDetails(AddDesignation aform, UserLoggingsPojo custdetails)
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Starting");
		
		AddDesignationDaoImpl dao=new AddDesignationDaoImpl();
		
		String check="";
		
		
		if(aform.getDesignationid().equalsIgnoreCase(""))
		
		{
		
		AddDesignationPojo apojo=new AddDesignationPojo();
		try {
			String s1=IDGenerator.getPrimaryKeyID("campus_designation",custdetails);
			
			apojo.setLog_audit_session(aform.getLog_audit_session());
			apojo.setDesgname(aform.getDesignation_name());
			apojo.setDesgdes(aform.getDesignation_description());
			apojo.setDesgid(aform.getDesignationid());
			apojo.setDesignationcode(s1);
			apojo.setCreateuser(aform.getCreatedby());
			apojo.setCustid(aform.getCustid());
			
			check= dao.insertDesignationDetails(apojo,custdetails);
		} 
		catch (Exception e)
		 {
			logger.error(e);
			e.printStackTrace();
		 }
		}
		else if(!aform.getDesignationid().equalsIgnoreCase(""))
		{
			AddDesignationPojo apojo=new AddDesignationPojo();
			apojo.setDesgname(aform.getDesignation_name());
			apojo.setDesgdes(aform.getDesignation_description());
			apojo.setDesgid(aform.getDesignationid());
			apojo.setCreateuser(aform.getCreatedby());
			apojo.setCustid(aform.getCustid());
			check= dao.updateDesignationDetails(apojo,custdetails);
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Ending");
		return check;
	}
	
	
	public  ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo, UserLoggingsPojo custdetails)
	{
		
		
		return new AddDesignationDaoImpl().DesignationDetails(vo,custdetails);
	
	}
	
	public AddDesignation EditDesignationDetails(AddDesignation aform, UserLoggingsPojo pojo)
	{

    return new AddDesignationDaoImpl().EditDesignationDetails(aform,pojo);
    		
     }


	
	public String deleteDesignationDetails(String[] designation_code, String custid, String inactiveReason, String otherReason, String status, String activeReason, String log_audit_session, UserLoggingsPojo pojo) {
		
		return new AddDesignationDaoImpl().deleteDesignationDetails(designation_code,custid,inactiveReason,otherReason,status,activeReason,log_audit_session,pojo);
	}


	
	public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal) {
		
		return new AddDesignationDaoImpl().getSearchDetails(searchTextVal);
	}


	public String getnamecount(AddDesignationVO vo, UserLoggingsPojo pojo) {
		
		return new AddDesignationDaoImpl().getnamecount(vo,pojo);
	}


	
	public ArrayList<AddDesignationVO> DesignationDetails() {
	
		return null;
	}


	public ArrayList<AddDesignationVO> InActiveDesignationList(AddDesignationVO vo, UserLoggingsPojo pojo) {
		return new AddDesignationDaoImpl().InActiveDesignationList(vo,pojo);
	}


	public ArrayList<AddDesignationVO> getdesignationList(UserLoggingsPojo custdetails) {
		return new AddDesignationDaoImpl().getdesignationList(custdetails);
	}

	
	}
