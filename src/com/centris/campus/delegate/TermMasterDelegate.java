package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeMasterService;
import com.centris.campus.service.TermMasterService;
import com.centris.campus.serviceImpl.FeeMasterServiceImpl;
import com.centris.campus.serviceImpl.TermMasterServiceIMPL;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.TermMasterVo;

public class TermMasterDelegate 

{

	TermMasterService obj_Term = new TermMasterServiceIMPL();

	public TermMasterVo getaccdetails(TermMasterVo vo, UserLoggingsPojo custdetails) {
		
		return obj_Term.getaccdetails(vo,custdetails);
	}

	public String getnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) 
	
	{
		
		return obj_Term.getnamecount(vo,userLoggingsVo);
	}

	public String addtermfeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		
		return obj_Term.addtermfeedetails(vo,userLoggingsVo);
	}

	public ArrayList<TermMasterVo> termList(TermMasterVo vo, UserLoggingsPojo custdetails) {
		
		return obj_Term.termList(vo,custdetails);
	}

	public TermMasterVo edittermDetails(TermMasterVo vo, UserLoggingsPojo custdetails) {
		
		return obj_Term.edittermDetails(vo,custdetails);
	}

	public String deleteTermDetails(TermMasterVo vo, String button, UserLoggingsPojo userLoggingsVo) {
		return obj_Term.deleteTermDetails(vo,button,userLoggingsVo);
	}

	public String dateOverLapValidate(String date, String accyear,UserLoggingsPojo userLoggingsVo) {
		 
		return obj_Term.dateOverLapValidate(date,accyear,userLoggingsVo);
	}

	public ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo vo, UserLoggingsPojo custdetails) {
		return obj_Term.separateTransportTermList(vo,custdetails);
	}

	public String addtermSeparatefeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		return obj_Term.addtermSeparatefeedetails(vo,userLoggingsVo);
	}

	public String deleteSeparateTermDetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		return obj_Term.deleteSeparateTermDetails(vo,userLoggingsVo);
	}

	public String separatedateOverLapValidate(String date, String accyear, String locationId, UserLoggingsPojo custdetails) {
		return obj_Term.separatedateOverLapValidate(date,accyear,locationId,custdetails);
	}

	public boolean getTermnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {
		return obj_Term.getTermnamecount(vo,userLoggingsVo);
	}

	public List<TermMasterVo> getTermDetails(String academic_year, String location, UserLoggingsPojo custdetails) {
		return obj_Term.getTermDetails(academic_year,location,custdetails);
	}

	public String dateOverLapValidate(String date, String accyear,
			String locationId, TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {
		return obj_Term.dateOverLapValidate(date,accyear,locationId,vo,userLoggingsVo);
	}

	


	
}
