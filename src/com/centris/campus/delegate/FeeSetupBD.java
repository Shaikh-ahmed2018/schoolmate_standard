package com.centris.campus.delegate;


import java.util.List;

import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeSetupService;
import com.centris.campus.serviceImpl.AddDesignationServiceImpl;
import com.centris.campus.serviceImpl.FeeSetupServiceImpl;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.FeeConcessionVO;

public class FeeSetupBD {
	
	FeeSetupService service = new FeeSetupServiceImpl();

	
	
	public List<ConcessionDetailsPojo> getconcessiondetails(ConcessionDetailsPojo vo, UserLoggingsPojo custdetails) {
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.getconcessiondetails(vo,custdetails);
	}

	public String insertConcesssionDetails(ConcessionForm detailsForm, UserLoggingsPojo custdetails) {
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.insertConcesssionDetails(detailsForm,custdetails);

	}
	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm, UserLoggingsPojo custdetails) {
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.EditConcesssionDetails(detailsForm,custdetails);
	}
	
	public String deleteconcession(FeeConcessionVO vo, UserLoggingsPojo pojo) 
	{
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.deleteconcession(vo,pojo);
	}
	public String getnamecount(FeeConcessionVO vo, UserLoggingsPojo custdetails) {
		
		FeeSetupService detailsService = new FeeSetupServiceImpl();
		return detailsService.getnamecount(vo,custdetails);
	}
}
