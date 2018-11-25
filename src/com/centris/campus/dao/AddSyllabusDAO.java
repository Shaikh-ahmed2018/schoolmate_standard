package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.SyllabusVO;

public interface AddSyllabusDAO {

	ArrayList<SyllabusVO> syllabusListforListOnchangeMethod(String location_id, String classname, String specialization,
			String academicYear, UserLoggingsPojo userLoggingsVo, String isApplicable);

}
