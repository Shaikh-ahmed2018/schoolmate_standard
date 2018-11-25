package com.centris.campus.service;


import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;

public interface SecondFormFormatService {


	String InsertSecadmissionform(ParentRequiresAppointmentForm secform,UserLoggingsPojo userLoggingsVo);

	String InsertThirdadmissionform(ParentRequiresAppointmentForm secform);

}
