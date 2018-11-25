package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.forms.SectionForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.ClassService;
import com.centris.campus.service.SectionService;
import com.centris.campus.serviceImpl.ClassServiceImpl;
import com.centris.campus.serviceImpl.SectionServiceImpl;
import com.centris.campus.vo.SectionVO;

public class SectionBD {
	
	public String insertCampusClassSectionBD(
			
			SectionForm campusClassSectionForm, UserLoggingsPojo userLoggingsVo) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		 return sectionService.insertCampusClassSectionService(campusClassSectionForm,userLoggingsVo);
	}
	
	public String updateCampusClassSectionBD(
			SectionForm campusClassSectionForm, UserLoggingsPojo userLoggingsVo) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.updateCampusClassSectionService(campusClassSectionForm,userLoggingsVo);
	}
	
	public String deleteCampusClassSectionBD(SectionVO sectionvo,UserLoggingsPojo userLoggingsVo) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.deleteCampusClassSectionService(sectionvo,userLoggingsVo);
	}
	
	public List<SectionForm> getCampusClassSectionAndClassDetailsBD(String schoolLocation, UserLoggingsPojo custdetails) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionForm> sectionList=sectionService.getCampusClassSectionAndClassDetailsService(schoolLocation,custdetails);
		return sectionList;
		
	}
	public List<SectionForm> getCampusClassDetailsIDandClassDetailsNameBD(String locationId, UserLoggingsPojo custdetails) throws Exception {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionForm> sectionList=sectionService.getCampusClassDetailsIDandClassDetailsNameService(locationId,custdetails);
		return sectionList;
		
	}

	
	public boolean checkSectionNameForUpdate(String sectionId,
			String sectionName, String className,UserLoggingsPojo userLoggingsVo) {
	
		return new SectionServiceImpl().checkSectionNameForUpdate(sectionId,sectionName,className,userLoggingsVo);
	}
	
	public List<SectionForm> searchSection(SectionForm searchText,UserLoggingsPojo userLoggingsVo) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.searchSection(searchText,userLoggingsVo);
	}
	
	public SectionForm editSection(SectionForm secCode,UserLoggingsPojo userLoggingsVo) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.editSection(secCode,userLoggingsVo);
	}
	
	public List<SectionVO> sectiondetailsdownload() throws Exception  {
		SectionService sectionService=new SectionServiceImpl();
		List<SectionVO> sectionList=sectionService.sectiondetailsdownload();
		return sectionList;
		
	}

	public String sectionNameCheck(SectionForm sectionForm, UserLoggingsPojo userLoggingsVo) {
		SectionService sectionService=new SectionServiceImpl();
		return sectionService.sectionNameCheck(sectionForm,userLoggingsVo);
		
	}

	public List<SectionForm> getstreamdetailsforOnchange(String locationid,String classname, String streamId, String status, UserLoggingsPojo custdetails) {
		return  new SectionServiceImpl().getstreamdetailsforOnchange( locationid, classname,streamId,status,custdetails);
		
	}
	
	

}
