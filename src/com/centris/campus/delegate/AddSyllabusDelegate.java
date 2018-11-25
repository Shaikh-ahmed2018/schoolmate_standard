package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AddSyllabusService;
import com.centris.campus.serviceImpl.AddSyllabusServiceImpl;
import com.centris.campus.vo.SyllabusVO;

public class AddSyllabusDelegate {
 
	
	AddSyllabusService service = new AddSyllabusServiceImpl(); 
	
	public ArrayList<SyllabusVO> syllabusListforListOnchangeMethod(String location_id, String classname, String specialization,
			String academicYear, UserLoggingsPojo userLoggingsVo, String isApplicable) {
		
				return service.syllabusListforListOnchangeMethod(location_id,  classname,  specialization,academicYear,userLoggingsVo,isApplicable);
	}

}
