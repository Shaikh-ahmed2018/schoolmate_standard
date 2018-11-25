package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.SpecializationForm;
import com.centris.campus.pojo.SpecializationPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SpecializationVo;

public interface SpecializationDao {

	ArrayList<SpecializationVo> getspecializationList(String schoolLocation, UserLoggingsPojo custdetails);

	String insertSpecialization(SpecializationPojo pojo, String specId, UserLoggingsPojo userLoggingsVo);

	SpecializationVo editSpecialization(String edit, UserLoggingsPojo custdetails);

	String deleteSpec(SpecializationVo vo, UserLoggingsPojo userLoggingsVo);

	List<SpecializationVo> getSpecializationOnClassBased(String classId, UserLoggingsPojo userLoggingsVo);

	String getSpecializationOnClassBased(SpecializationForm form1, UserLoggingsPojo userLoggingsVo);

	ArrayList<SpecializationVo> getSearchSpecializationList(String searchterm, String school, UserLoggingsPojo custdetails);

	List<SpecializationVo> getSpecializationOnClassWithoutLocId(String classId);

	public List<SpecializationVo> getstreamdetailsforOnchange(String locationid, String classname, String streamId, String status,UserLoggingsPojo custdetails);

}
