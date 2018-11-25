package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.StreamDetailsVO;



public interface StreamDetailsService {

	
	public String insertStreamDetailsService(StreamDetailsForm  detailsForm, UserLoggingsPojo userLoggingsVo);
	public StreamDetailsVO editStreamDetailsService(StreamDetailsVO detailsVo, UserLoggingsPojo custdetails);
	public String deleteStreamDetailsService(StreamDetailsVO detailsVo,UserLoggingsPojo userLoggingsVo);
	public List<StreamDetailsVO>  getStreamDetailsService(String schoolLocation, UserLoggingsPojo custdetails);
	//public boolean checkStreamName(StreamDetailsPojo streamDetailsPojo);
	public ArrayList<StreamDetailsVO> searchStreamDetailsService(String searchTerm, UserLoggingsPojo custdetails);
	public String validateStreamNameService(StreamDetailsVO detailsVo, UserLoggingsPojo userLoggingsVo);
	public List<StreamDetailsVO> searchByLocationOnly(String locationId,String accYear, String status, UserLoggingsPojo custdetails);

}
