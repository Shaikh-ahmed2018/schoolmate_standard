package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ReligionCasteCasteCategoryForm;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ReligionCasteCasteCategoryPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.ReligionCasteCasteCategoryVo;
import com.centris.campus.vo.StreamDetailsVO;



public interface ReligionCasteCasteCategoryService {

	//
	public String insertReligion(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> searchReligion(String searchName, String status, UserLoggingsPojo custdetails);

	public List<ReligionCasteCasteCategoryVo> getReligionDetails(UserLoggingsPojo custdetails);

	public ReligionCasteCasteCategoryVo getSingleReligion(ReligionCasteCasteCategoryPojo detailsPojo,UserLoggingsPojo userLoggingsVo);

	public String deleteReligion(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> searchCaste(String searchName,String sts, UserLoggingsPojo custdetails);

	public List<ReligionCasteCasteCategoryVo> getCasteDetails(String religionId, UserLoggingsPojo custdetails);

	public String deleteCaste(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public String insertCaste(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo);

	public ReligionCasteCasteCategoryVo getSingleCaste(
			ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> searchCasteCategory(
			String searchName, String status, UserLoggingsPojo custdetails);

	public List<ReligionCasteCasteCategoryVo> getCasteCategoryDetails(UserLoggingsPojo custdetails);

	public String insertCasteCategory(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo);

	public String deleteCasteCategory(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public ReligionCasteCasteCategoryVo getSingleCasteCategory(
			ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public String insertOccupation(ReligionCasteCasteCategoryForm detailsForm, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> getOccupationDetails(UserLoggingsPojo custdetails);
 
	public ReligionCasteCasteCategoryVo getSingleOccupation(
			ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public String deleteOccupation(ReligionCasteCasteCategoryPojo detailsPojo, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> getCasteDetailsList(String religionId, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> getListOfCaste();

	public List<ReligionCasteCasteCategoryVo> getCasteCategoryListDetails(String casteId, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> getOccupationDetailsSearch(String searchName, String status, UserLoggingsPojo custdetails);

	public List<ReligionCasteCasteCategoryVo> ReligionListByStatus(String status, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> CasteListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo);

	public List<ReligionCasteCasteCategoryVo> CasteCategoryListingByStatus(ReligionCasteCasteCategoryVo vo, UserLoggingsPojo userLoggingsVo);
 
}
