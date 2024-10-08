package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centris.campus.forms.CreateExaminationForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.CreateExaminationServiceIMPL;
import com.centris.campus.vo.ExaminationDetailsVo;


public class CreateExaminationBD {


	public Map<String, String> getAccadamicYearsBD(UserLoggingsPojo custdetails) {
	
		
		return new CreateExaminationServiceIMPL().getAccadamicYearsServiceImpl(custdetails);
	}

	public List<Object> getAllExamNames(CreateExaminationForm examform, UserLoggingsPojo custdetails) {
		return new CreateExaminationServiceIMPL().getAllExamNames(examform,custdetails);
	}

	public String createExamination(CreateExaminationForm examform) {
	
	
		return new CreateExaminationServiceIMPL().createExamination(examform);
	}

	public ExaminationDetailsVo editExamination(ExaminationDetailsVo examvo) {
		

		
		return new CreateExaminationServiceIMPL().editExamination(examvo);
	}

	public String deleteExamination(ExaminationDetailsVo examvo) {
		
		return new CreateExaminationServiceIMPL().deleteExamination(examvo);
	}

	public ArrayList<ExaminationDetailsVo> searchExamination(String searchName, UserLoggingsPojo userLoggingsVo) {
	
		return  new CreateExaminationServiceIMPL().searchExamination(searchName,userLoggingsVo);
	}

	public boolean validateExamination(ExaminationDetailsVo examvo) {
		
		return new CreateExaminationServiceIMPL().validateExamination(examvo);
	}

}
