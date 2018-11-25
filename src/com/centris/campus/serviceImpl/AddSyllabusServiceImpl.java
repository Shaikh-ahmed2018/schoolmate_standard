package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.AddSyllabusDAO;
import com.centris.campus.daoImpl.AddSyllabusDAOImpl;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.AddSyllabusService;
import com.centris.campus.vo.SyllabusVO;

public class AddSyllabusServiceImpl implements AddSyllabusService {

	AddSyllabusDAO service = new AddSyllabusDAOImpl();
	
	
	@Override
	public ArrayList<SyllabusVO> syllabusListforListOnchangeMethod(String location_id, String classname,
			String specialization, String academicYear,UserLoggingsPojo userLoggingsVo,String isApplicable) {
		
		return service.syllabusListforListOnchangeMethod( location_id,  classname, specialization,  academicYear, userLoggingsVo,isApplicable);
	}

}
