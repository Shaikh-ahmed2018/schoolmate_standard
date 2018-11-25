package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SpecializationService;
import com.centris.campus.serviceImpl.SpecializationServiceImpl;
import com.centris.campus.vo.SpecializationVo;

public class SpecializationBD {
	
	SpecializationService service;
	public ArrayList<SpecializationVo> getspecializationList(String schoolLocation, UserLoggingsPojo custdetails) {
		service = new SpecializationServiceImpl();
		return service.getspecializationList(schoolLocation,custdetails);
	}
	public String insertSpecialization(SpecializationForm spec, String specId, UserLoggingsPojo userLoggingsVo) {
		service = new SpecializationServiceImpl();
		return service.insertSpecialization(spec,specId,userLoggingsVo);
	}
	public SpecializationVo editSpecialization(String edit, UserLoggingsPojo custdetails) {
		service = new SpecializationServiceImpl();
		return service.editSpecialization(edit,custdetails);
	}
	public String deleteSpec(SpecializationVo vo, UserLoggingsPojo userLoggingsVo) {
		service = new SpecializationServiceImpl();
		return service.deleteSpec(vo,userLoggingsVo);
	}
	public List<SpecializationVo> getSpecializationOnClassBased(String classId, UserLoggingsPojo userLoggingsVo) {
		service = new SpecializationServiceImpl();
		return service.getSpecializationOnClassBased(classId,userLoggingsVo);
	}
	public String validateSpecialization(SpecializationForm form1, UserLoggingsPojo userLoggingsVo) {
		service = new SpecializationServiceImpl();
		return service.validateSpecialization(form1,userLoggingsVo);
	}
	public ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school, UserLoggingsPojo custdetails) {
		service = new SpecializationServiceImpl();
		return service.getSearchSpecializationList(searchterm,school,custdetails);
	}
	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId, String status, UserLoggingsPojo custdetails) {
		return new SpecializationServiceImpl().getstreamdetailsforOnchange( locationid,classname,streamId,status,custdetails);
	}
	
	public List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId) {
		service = new SpecializationServiceImpl();
		return service.getSpecializationOnClassWithoutLocId(classId);
	}

}
