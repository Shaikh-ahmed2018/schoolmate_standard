package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.vo.AddDesignationVO;

public class AddDesignationBD {
	
	
	
	
public  ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo, UserLoggingsPojo custdetails){
	
	

		return new AddDesignationServiceImpl().DesignationDetails(vo,custdetails);
	
}
	
	
public String insertDesignationDetails(AddDesignation aform, UserLoggingsPojo custdetails){
	
	

		return new AddDesignationServiceImpl().insertDesignationDetails(aform,custdetails);
	
}

public AddDesignation EditDesignationDetails(AddDesignation aform, UserLoggingsPojo pojo){
	
	

		return new AddDesignationServiceImpl().EditDesignationDetails(aform,pojo);
}
		
		



public String deleteDesignationDetails(String[] designation_code, String custid, String inactiveReason, String otherReason, String status, String activeReason, String log_audit_session, UserLoggingsPojo pojo) 
{
	return new AddDesignationServiceImpl().deleteDesignationDetails(designation_code,custid,inactiveReason,otherReason,status,activeReason,log_audit_session,pojo);
}

public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal)

{

	return new AddDesignationServiceImpl().getSearchDetails(searchTextVal);
}


public String getnamecount(AddDesignationVO vo, UserLoggingsPojo pojo) {
	
	return new AddDesignationServiceImpl().getnamecount(vo,pojo);
}


public ArrayList<AddDesignationVO> InActiveDesignationList(AddDesignationVO vo, UserLoggingsPojo pojo) {
	return new AddDesignationServiceImpl().InActiveDesignationList(vo,pojo);
 }


public ArrayList<AddDesignationVO> getdesignationList(UserLoggingsPojo custdetails) {
	return new AddDesignationServiceImpl().getdesignationList(custdetails);
}
}
	
