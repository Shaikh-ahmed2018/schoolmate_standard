package com.centris.campus.delegate;

import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.NewRgistrationService;
import com.centris.campus.serviceImpl.NewRegistrationServiceImpl;

public class NewRegistrationBD {

	NewRgistrationService newregserviceimpl = new NewRegistrationServiceImpl();
	
	public void getregDetails(NewRegistrationForm newregform) {
		
		newregserviceimpl.getregdetails(newregform);
		
	}


	public String InsertNewRegistrationUser(
			NewUserRegistrationForm registrationform, UserLoggingsPojo userLoggingsVo) {
		return newregserviceimpl.InsertNewRegistrationUser(registrationform,userLoggingsVo);
	}


	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform, UserLoggingsPojo userLoggingsVo) {
		return newregserviceimpl.saveparentsubmittingdetailstoschool(registrationform,userLoggingsVo);
	}}
