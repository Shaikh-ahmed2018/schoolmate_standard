package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SpecializationVo;

public interface SpecializationService {

	ArrayList<SpecializationVo> getspecializationList(String schoolLocation, UserLoggingsPojo custdetails);

	String insertSpecialization(SpecializationForm spec, String specId, UserLoggingsPojo userLoggingsVo);

	SpecializationVo editSpecialization(String edit, UserLoggingsPojo custdetails);

	String deleteSpec(SpecializationVo vo, UserLoggingsPojo userLoggingsVo);

	List<SpecializationVo> getSpecializationOnClassBased(String classId, UserLoggingsPojo userLoggingsVo);

	String validateSpecialization(SpecializationForm form1, UserLoggingsPojo userLoggingsVo);

	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId, String status,UserLoggingsPojo custdetails);

	ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school, UserLoggingsPojo custdetails);

	List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId);


}
