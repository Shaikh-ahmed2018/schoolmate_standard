package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SectionVO;


public interface SectionService  {
	
	public String insertCampusClassSectionService(SectionForm campusClassSectionForm, UserLoggingsPojo userLoggingsVo)throws Exception;
	public String updateCampusClassSectionService(SectionForm campusClassSectionForm, UserLoggingsPojo userLoggingsVo)throws Exception;
	public String deleteCampusClassSectionService(SectionVO sectionvo,UserLoggingsPojo userLoggingsVo);
	
	public List<SectionForm>  getCampusClassSectionAndClassDetailsService(String schoolLocation, UserLoggingsPojo custdetails) throws Exception;
	public List<SectionForm>  getCampusClassDetailsIDandClassDetailsNameService(String locationId, UserLoggingsPojo custdetails) throws Exception;
	public List<SectionForm> searchSection(SectionForm searchText,UserLoggingsPojo userLoggingsVo);
	public SectionForm editSection(SectionForm classCode,UserLoggingsPojo userLoggingsVo);
	public List<SectionVO>  sectiondetailsdownload() throws Exception;
	public String sectionNameCheck(SectionForm sectionForm, UserLoggingsPojo userLoggingsVo);


}
