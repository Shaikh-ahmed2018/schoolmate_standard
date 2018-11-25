package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.TransportTypePOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TransportTypeService;
import com.centris.campus.serviceImpl.TransportTypeServiceImpl;
import com.centris.campus.vo.TransportTypeVO;

public class TransportTypeBD {

	public ArrayList<TransportTypeVO> getAllTransportypeDetails(UserLoggingsPojo custdetails) {
		
		TransportTypeService transportTypeService = new TransportTypeServiceImpl();
		
		return transportTypeService.getAllTransportypeDetails(custdetails);
	}

	public ArrayList<TransportTypeVO> getSearchDetails(TransportTypePOJO transportTypePOJO, UserLoggingsPojo custdetails) {
		
      TransportTypeService transportTypeService = new TransportTypeServiceImpl();
		
		return transportTypeService.getSearchDetails(transportTypePOJO,custdetails);
	}

	public String validateTypeName(TransportTypePOJO transportTypePOJO) {


        TransportTypeService transportTypeService = new TransportTypeServiceImpl();
		
		return transportTypeService.validateTypeName(transportTypePOJO);
	}

	public String AddTransportType(TransportTypePOJO transportTypePOJO) {
		
        TransportTypeService transportTypeService = new TransportTypeServiceImpl();
		
		return transportTypeService.AddTransportType(transportTypePOJO);
	}

	public String UpdateTransportType(TransportTypePOJO transportTypePOJO) {

        TransportTypeService transportTypeService = new TransportTypeServiceImpl();
		
		return transportTypeService.UpdateTransportType(transportTypePOJO);
	}

	
	public TransportTypeVO editType(TransportTypePOJO transportTypePOJO) {
		
		 TransportTypeService transportTypeService = new TransportTypeServiceImpl();
			
			return transportTypeService.editType(transportTypePOJO);
	}

	
	public String deleteType(TransportTypePOJO transportTypePOJO) {
		
		 TransportTypeService transportTypeService = new TransportTypeServiceImpl();
			
			return transportTypeService.deleteType(transportTypePOJO);
	}
	

}
