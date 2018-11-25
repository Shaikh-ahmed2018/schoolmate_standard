package com.centris.campus.dao;




import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddDesignationVO;



public interface AddDesignationDao {
	
	public ArrayList<AddDesignationVO>  DesignationDetails();

	public String insertDesignationDetails(AddDesignationPojo apojo,UserLoggingsPojo custdetails);
	
	public AddDesignation EditDesignationDetails(AddDesignation aform,UserLoggingsPojo pojo);

	public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal) ;

}
