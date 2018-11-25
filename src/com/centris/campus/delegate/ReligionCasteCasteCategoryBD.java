package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ReligionCasteCasteCategoryService;
import com.centris.campus.serviceImpl.ReligionCasteCasteCategoryServiceImpl;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;

public class ReligionCasteCasteCategoryBD {

	ReligionCasteCasteCategoryService detailsServices;
	
public String insertReligion(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertReligion(detailsForm,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName, String status, UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchReligion(searchName,status,custdetails);
}

public List<ReligionCasteCasteCategoryVo> getReligionDetails(UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getReligionDetails(custdetails);
}

public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleReligion(detailsPojo,userLoggingsVo);
}

public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteReligion(detailsPojo,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName, String sts, UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchCaste(searchName,sts,custdetails);
}

public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId, UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteDetails(religionId,custdetails);
}

public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteCaste(detailsPojo,userLoggingsVo);
}

public String insertCaste(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertCaste(detailsForm,userLoggingsVo);
}

public ReligionCasteCasteCategoryVo getSingleCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleCaste(detailsPojo,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> searchCasteCategory(String searchName, String status, UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.searchCasteCategory(searchName,status,custdetails);
}

public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails(UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteCategoryDetails(custdetails);
}

public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertCasteCategory(detailsForm,userLoggingsVo);
}

public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteCasteCategory(detailsPojo,userLoggingsVo);
}

public ReligionCasteCasteCategoryVo getSingleCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleCasteCategory(detailsPojo,userLoggingsVo);
}

public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.insertOccupation(detailsForm,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> getOccupationDetails(UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getOccupationDetails(custdetails);
}

public ReligionCasteCasteCategoryVo getSingleOccupation(
		ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getSingleOccupation(detailsPojo,userLoggingsVo);
}

public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.deleteOccupation(detailsPojo,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteDetailsList(religionId,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> getListOfCaste() {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getListOfCaste();
}

public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getCasteCategoryListDetails(casteId,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(
		String searchName, String status, UserLoggingsPojo custdetails) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.getOccupationDetailsSearch(searchName,status,custdetails);
}

public List<ReligionCasteCasteCategoryVo> ReligionListByStatus(String status, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.ReligionListByStatus(status,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> CasteListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.CasteListingByStatus(vo,userLoggingsVo);
}

public List<ReligionCasteCasteCategoryVo> CasteCategoryListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo) {
	detailsServices=new ReligionCasteCasteCategoryServiceImpl();
	return	detailsServices.CasteCategoryListingByStatus(vo,userLoggingsVo);
}

}
