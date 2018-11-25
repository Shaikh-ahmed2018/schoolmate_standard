package com.centris.campus.service;

import com.centris.campus.forms.NewRegistrationForm;
import com.centris.campus.forms.NewUserRegistrationForm;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;

public interface NewRgistrationService {
	
	public void getregdetails(NewRegistrationForm newregform);

	public String InsertNewRegistrationUser(NewUserRegistrationForm registrationform, UserLoggingsPojo userLoggingsVo);

	public String saveparentsubmittingdetailstoschool(
			ParentRequiresAppointmentForm registrationform, UserLoggingsPojo userLoggingsVo);
}
