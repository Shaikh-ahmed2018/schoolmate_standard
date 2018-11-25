package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StreamDetailsService;
import com.centris.campus.serviceImpl.StreamDetailsServiceImpl;
import com.centris.campus.serviceImpl.StudentRegistrationServiceImpl;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.VehicleDetailsVO;

public class StreamDetailsBD {

	StreamDetailsService   detailsService;
	
	public List<StreamDetailsVO> getStreamDetailsBD(String schoolLocation, UserLoggingsPojo custdetails) {
		
		detailsService=new StreamDetailsServiceImpl();
		List<StreamDetailsVO> allStreamList=new ArrayList<StreamDetailsVO>();	
		allStreamList=detailsService.getStreamDetailsService(schoolLocation,custdetails);
		return allStreamList;
	
	}
	
	
	public String insertStreamDetailsBD(StreamDetailsForm detailsForm, UserLoggingsPojo userLoggingsVo) {
		
		detailsService=new StreamDetailsServiceImpl();
		return	detailsService.insertStreamDetailsService(detailsForm,userLoggingsVo);
		 
	}
	
	public StreamDetailsVO editStreamDetailsBD(StreamDetailsVO detailsVo, UserLoggingsPojo custdetails) {
		
		
		detailsService=new StreamDetailsServiceImpl();
		
		return	detailsService.editStreamDetailsService(detailsVo,custdetails);
		
		
	}
	
public String deleteStreamDetailsBD(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo) {
		return	new StreamDetailsServiceImpl().deleteStreamDetailsService(detailsVo,userLoggingsVo);
}

public ArrayList<StreamDetailsVO> searchStreamDetailsBD(String searchTerm, UserLoggingsPojo custdetails)
{	
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.searchStreamDetailsService(searchTerm,custdetails);
}


public String validateStreamNameBD(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo) {
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.validateStreamNameService(detailsVo,userLoggingsVo);
}

public List<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear, String status, UserLoggingsPojo custdetails) {
	detailsService=new StreamDetailsServiceImpl();
	return detailsService.searchByLocationOnly(locationId,accYear,status,custdetails);
}



}
