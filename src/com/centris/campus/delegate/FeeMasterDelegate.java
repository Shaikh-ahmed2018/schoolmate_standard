package com.centris.campus.delegate;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeMasterService;
import com.centris.campus.serviceImpl.FeeMasterServiceImpl;
import com.centris.campus.vo.AddFeeVO;

import com.centris.campus.vo.ReportMenuVo;
public class FeeMasterDelegate
{
	FeeMasterService obj_Fee = new FeeMasterServiceImpl();
public String insertFeeDetails(AddFeeVO vo, UserLoggingsPojo pojo){
		return obj_Fee.insertFeeDetails(vo,pojo);
	}
public boolean getnamecount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo){
		return obj_Fee.getnamecount(vo,userLoggingsVo);
	}
public ArrayList<AddFeeVO> getfeedetails(AddFeeVO vo, UserLoggingsPojo custdetails) {
		return obj_Fee.getfeedetails(vo,custdetails);
	}
public AddFeeVO editFeeDetails(AddFeeVO vo, UserLoggingsPojo dbdetails) {
		return obj_Fee.editFeeDetails(vo,dbdetails);
	}
public boolean deleteFeeDetails(AddFeeVO vo, String button, UserLoggingsPojo userLoggingsVo) {
		return obj_Fee.deleteFeeDetails(vo,button,userLoggingsVo);
	}
public ArrayList<AddFeeVO> searchFeeDetails(AddFeeVO vo,UserLoggingsPojo userLoggingsVo) {
		return obj_Fee.searchFeeDetails(vo,userLoggingsVo);
	}
public List<ReportMenuVo> getclasslist() {
	return obj_Fee.getclasslistService();
}
public ArrayList<AddFeeVO> feeTypeListBD(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
	return obj_Fee.feeTypeListService(vo,userLoggingsVo);
}
public String getFeeTypeCount(AddFeeVO vo, UserLoggingsPojo userLoggingsVo) {
	return obj_Fee.getFeeTypeCount(vo,userLoggingsVo);
}
}
