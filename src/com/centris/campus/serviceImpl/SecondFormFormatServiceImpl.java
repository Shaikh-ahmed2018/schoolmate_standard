package com.centris.campus.serviceImpl;
import com.centris.campus.dao.SecadmissionformatDao;
import com.centris.campus.daoImpl.SecadmissionFormDaoImpl;
import com.centris.campus.forms.ParentRequiresAppointmentForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.SecondFormFormatService;

public class SecondFormFormatServiceImpl implements SecondFormFormatService{

	SecadmissionformatDao dao =  new SecadmissionFormDaoImpl();
	
	public String InsertSecadmissionform(ParentRequiresAppointmentForm secform,UserLoggingsPojo userLoggingsVo) {
		return  dao.InsertSecadmissionform(secform,userLoggingsVo);
	}

	@Override
	public String InsertThirdadmissionform(ParentRequiresAppointmentForm secform) {
		return  dao.InsertThirdadmissionform(secform);
	}}
