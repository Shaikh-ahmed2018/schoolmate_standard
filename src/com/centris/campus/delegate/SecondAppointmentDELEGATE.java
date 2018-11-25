package com.centris.campus.delegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.forms.Secondadmissionformformat;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SecondFormFormatService;
import com.centris.campus.serviceImpl.SecondFormFormatServiceImpl;

public class SecondAppointmentDELEGATE {
	SecondFormFormatService service =(SecondFormFormatService)new SecondFormFormatServiceImpl();

	public String InsertSecadmissionform(ParentRequiresAppointmentForm secform,UserLoggingsPojo userLoggingsVo) {
		return service.InsertSecadmissionform(secform,userLoggingsVo);

	}

	public String InsertThirdadmissionform(ParentRequiresAppointmentForm secform) {
		return service.InsertThirdadmissionform(secform);
	}
}
