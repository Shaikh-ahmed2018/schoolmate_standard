package com.centris.campus.service;





import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.vo.AddDesignationVO;






public interface AddDesignationService {

	public ArrayList<AddDesignationVO> DesignationDetails();
	public String insertDesignationDetails(AddDesignation aform,UserLoggingsPojo custdetails);
    public AddDesignation EditDesignationDetails(AddDesignation aform,UserLoggingsPojo pojo);
    
    public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal);
	String deleteDesignationDetails(String[] vo,String custid,String inactiveReason,String otherReason,String status,String activeReason,String log_audit_session,UserLoggingsPojo pojo);
}

