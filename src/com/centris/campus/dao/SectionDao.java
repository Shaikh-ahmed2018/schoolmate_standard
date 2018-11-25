package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SectionVO;

public interface SectionDao {
	
	public String insertCampusClassSectionDao(SectionPojo campusClassSectionPojo, UserLoggingsPojo userLoggingsVo)throws Exception;
	public String updateCampusClassSectionDao(SectionPojo campusClassSectionPojo, UserLoggingsPojo userLoggingsVo)throws Exception;
	public String deleteCampusClassSectionDao(SectionVO sectionvo,UserLoggingsPojo userLoggingsVo);
	
	public List<SectionPojo>  getCampusClassSectionAndClassDetailsDao(String schoolLocation, UserLoggingsPojo custdetails) throws Exception;
	public List<SectionPojo>  getCampusClassDetailsIDandClassDetailsNameDao(String locationId, UserLoggingsPojo custdetails) throws Exception;
	public boolean checkSectionName(String sectionName,String secDetailId,UserLoggingsPojo userLoggingsVo);
	public List<SectionForm> searchSection(SectionForm  searchText,UserLoggingsPojo userLoggingsVo);
	public SectionForm editSection(SectionForm classCode,UserLoggingsPojo userLoggingsVo);
	public List<SectionPojo>  sectiondetailsdownload() throws Exception;
	public String sectionNameCheck(SectionForm sectionForm, UserLoggingsPojo userLoggingsVo);


}
