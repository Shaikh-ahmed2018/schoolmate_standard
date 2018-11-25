package com.centris.campus.dao;


import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;

public interface SecadmissionformatDao {

	String InsertSecadmissionform(ParentRequiresAppointmentForm secform,UserLoggingsPojo userLoggingsVo);

	String InsertThirdadmissionform(ParentRequiresAppointmentForm secform);
}
