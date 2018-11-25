package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.TermMasterVo;

public interface TermMasterDAO {

	TermMasterVo getaccdetails(TermMasterVo vo, UserLoggingsPojo custdetails);

	String getnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);

	String addtermfeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<TermMasterVo> termList(TermMasterVo vo, UserLoggingsPojo custdetails);

	TermMasterVo edittermDetails(TermMasterVo vo, UserLoggingsPojo custdetails);

	String updatetermDetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);

	String deleteTermDetails(TermMasterVo vo, String button, UserLoggingsPojo userLoggingsVo);

	String dateOverLapValidate(String date, String accyear, UserLoggingsPojo userLoggingsVo);

	String addtermSeparatefeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo vo, UserLoggingsPojo custdetails);

	String deleteSeparateTermDetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);


	String separatedateOverLapValidate(String date, String accyear, String locationId, UserLoggingsPojo custdetails);

	boolean getTermnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo);

	List<TermMasterVo> getTermDetails(String academic_year, String location, UserLoggingsPojo custdetails);

	String dateOverLapValidate(String date, String accyear, String locationId, TermMasterVo vo,UserLoggingsPojo userLoggingsVo);

	String updatesepataretermDetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo);

	


	
}
