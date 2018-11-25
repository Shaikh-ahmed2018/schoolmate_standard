package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddFeeVO;

import com.centris.campus.vo.ReportMenuVo;


public interface FeeMasterService 

{

	public String insertFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo);

	public boolean getnamecount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

	public ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo, UserLoggingsPojo custdetails);

	public AddFeeVO editFeeDetails(AddFeeVO vo, UserLoggingsPojo dbdetails);

	public boolean deleteFeeDetails(AddFeeVO vo, String button, UserLoggingsPojo userLoggingsVo);

	public ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo,UserLoggingsPojo userLoggingsVo);
	public List<ReportMenuVo> getclasslistService();

	public ArrayList<AddFeeVO> feeTypeListService(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

	public String getFeeTypeCount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

	
}
