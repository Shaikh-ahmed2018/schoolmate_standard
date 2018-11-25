package com.centris.campus.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.FeeSetupDao;
import com.centris.campus.daoImpl.FeeSetupDaoImpl;
import com.centris.campus.forms.ConcessionForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.FeeSetupService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeConcessionVO;

public class FeeSetupServiceImpl implements FeeSetupService {
	FeeSetupDao dao = new FeeSetupDaoImpl();
	private static final Logger logger = Logger
			.getLogger(FeeSetupServiceImpl.class);

	public List<ConcessionDetailsPojo> getconcessiondetails(
			ConcessionDetailsPojo vo,UserLoggingsPojo custdetails) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getconcessiondetails Starting");
		FeeSetupDao daoImpl = null;
		try {
			daoImpl = new FeeSetupDaoImpl();
		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			exception.getStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: getconcessiondetails Ending");
		return daoImpl.getconcessiondetails(vo,custdetails);
	}

	public String insertConcesssionDetails(ConcessionForm detailsForm,UserLoggingsPojo custdetails) {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: insertConcesssionDetails Starting");
	

		System.out.println("ID" + detailsForm.getConcessionId());

		ConcessionDetailsPojo detailsPojo = new ConcessionDetailsPojo();
		String check = "";

		if (detailsForm.getConcessionId().equalsIgnoreCase(""))
		{
			try
			{
				detailsPojo.setConcessionId(detailsForm.getConcessionId());
				detailsPojo.setConcessionName(detailsForm.getConcesionName());
				detailsPojo.setDescription(detailsForm.getDescription());
				detailsPojo.setCreateUser(detailsForm.getCreateUser());
				detailsPojo.setLocId(detailsForm.getLocationnid());
				detailsPojo.setCustid(detailsForm.getCustid());
				detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
				detailsPojo.setIsApplicable(detailsForm.getIsApplicable());
				detailsPojo.setConcessionType(detailsForm.getConcessionType());
				detailsPojo.setConcessionType(detailsForm.getConcessionType());
				detailsPojo.setConcession(detailsForm.getConcession());
				check = dao.insertConcesssionDetails(detailsPojo,custdetails);
			}
			catch (Exception exception)
			{
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		} else if (!detailsForm.getConcessionId().equalsIgnoreCase(""))
		 {
			try
			{
				detailsPojo.setConcessionId(detailsForm.getConcessionId());
				detailsPojo.setConcessionName(detailsForm.getConcesionName());
				detailsPojo.setDescription(detailsForm.getDescription());
				detailsPojo.setCreateUser(detailsForm.getCreateUser());
				detailsPojo.setLocId(detailsForm.getLocationnid());
				detailsPojo.setCustid(detailsForm.getCustid());
				detailsPojo.setLog_audit_session(detailsForm.getLog_audit_session());
				detailsPojo.setIsApplicable(detailsForm.getIsApplicable());
				detailsPojo.setConcessionType(detailsForm.getConcessionType());
				detailsPojo.setConcession(detailsForm.getConcession());
				check = dao.updateConcessionDao(detailsPojo,custdetails);
			}
			catch (Exception exception)
			{
				logger.error(exception.getMessage(), exception);
				exception.getStackTrace();
			}
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);

		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeSetupServiceImpl: insertConcesssionDetails Ending");
		return check;
	}

	public ConcessionForm EditConcesssionDetails(ConcessionForm detailsForm,UserLoggingsPojo custdetails) {

		return new FeeSetupDaoImpl().EditConcesssionDetails(detailsForm, custdetails);
	}

	@Override
	public String deleteconcession(FeeConcessionVO vo,UserLoggingsPojo pojo) {
		return new FeeSetupDaoImpl().deleteconcession(vo,pojo);
	}

	@Override
	public String getnamecount(FeeConcessionVO vo, UserLoggingsPojo custdetails) {
		return new FeeSetupDaoImpl().getnamecount(vo,custdetails);
	}

}
