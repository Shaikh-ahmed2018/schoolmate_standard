package com.centris.campus.serviceImpl;

import com.centris.campus.dao.NewRegistrationDao;
import com.centris.campus.daoImpl.NewRegistrationDaoImpl;
import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.NewRgistrationService;

public class NewRegistrationServiceImpl implements NewRgistrationService {
	
	NewRegistrationDao newregdao= new NewRegistrationDaoImpl();

	public void getregdetails(NewRegistrationForm newregform) {
		
		
		
		newregdao.getregdetails(newregform);
	}

	@Override
	public String InsertNewRegistrationUser(
			NewUserRegistrationForm registrationform,UserLoggingsPojo userLoggingsVo) {
		return newregdao.InsertNewRegistrationUser(registrationform,userLoggingsVo);
	}

	@Override
	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform,UserLoggingsPojo userLoggingsVo) {
		return newregdao.saveparentsubmittingdetailstoschool(registrationform,userLoggingsVo);
	}
}
