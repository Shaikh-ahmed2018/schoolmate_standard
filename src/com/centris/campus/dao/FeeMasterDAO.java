package com.centris.campus.dao;


import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddFeeVO;

import com.centris.campus.vo.ReportMenuVo;

public interface FeeMasterDAO 

{

	String insertFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo);

	int getFeeNameCheckDao(String feename);

	boolean getnamecount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

	ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo, UserLoggingsPojo custdetails);

	AddFeeVO editFeeDetails(AddFeeVO vo, UserLoggingsPojo dbdetails);

	boolean deleteFeeDetails(AddFeeVO vo, String button, UserLoggingsPojo userLoggingsVo);

	ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo,UserLoggingsPojo userLoggingsVo);

	String updateFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo);
	List<ReportMenuVo> getclasslistDao();

	ArrayList<AddFeeVO> feeTypeListDao(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

	String getFeeTypeCount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo);

}
