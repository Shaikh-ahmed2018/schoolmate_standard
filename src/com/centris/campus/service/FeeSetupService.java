package com.centris.campus.service;

import java.util.List;

import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.FeeConcessionVO;

public interface FeeSetupService {
	
	public List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo, UserLoggingsPojo custdetails);
	
	public String insertConcesssionDetails(ConcessionForm detailsForm, UserLoggingsPojo custdetails);

	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm, UserLoggingsPojo custdetails);

	public String deleteconcession(FeeConcessionVO vo, UserLoggingsPojo pojo);

	public String getnamecount(FeeConcessionVO vo, UserLoggingsPojo custdetails); 

}
