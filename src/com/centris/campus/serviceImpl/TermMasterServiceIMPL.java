package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.TermMasterDAO;
import com.centris.campus.daoImpl.TermMasterDAOIMPL;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.TermMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.TermMasterVo;

public class TermMasterServiceIMPL implements TermMasterService 

{
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	TermMasterDAO obj_Term = new TermMasterDAOIMPL();


	public TermMasterVo getaccdetails(TermMasterVo vo, UserLoggingsPojo custdetails) 
	{
		return obj_Term.getaccdetails(vo,custdetails);
	}


	public String getnamecount(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {

		return obj_Term.getnamecount(vo,userLoggingsVo);
	}

	public boolean getTermnamecount(TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {

		return obj_Term.getTermnamecount(vo,userLoggingsVo);
	}
	
	
	
	


	public String addtermfeedetails(TermMasterVo vo, UserLoggingsPojo userLoggingsVo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterServiceIMPL :addtermfeedetails  Starting");
		// TODO Auto-generated method stub

		String check="";
		TermMasterDAO TermMasterDAO = new TermMasterDAOIMPL();

		if (vo.getTermid().equalsIgnoreCase(""))
		{
			try
			{
				check = TermMasterDAO.addtermfeedetails(vo,userLoggingsVo);
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		else if (!vo.getTermid().equalsIgnoreCase(""))
		{
			try
			{
				check = TermMasterDAO.updatetermDetails(vo,userLoggingsVo);
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterServiceIMPL :addtermfeedetails Ending");
		return check;
	}




	public ArrayList<TermMasterVo> termList(TermMasterVo vo,UserLoggingsPojo custdetails) {

		return obj_Term.termList(vo,custdetails);
	}



	public ArrayList<TermMasterVo> separateTransportTermList(TermMasterVo vo,UserLoggingsPojo custdetails) {

		return obj_Term.separateTransportTermList(vo,custdetails);
	}



	public TermMasterVo edittermDetails(TermMasterVo vo, UserLoggingsPojo custdetails) {

		return obj_Term.edittermDetails(vo,custdetails);
	}




	public String deleteTermDetails(TermMasterVo vo,String button, UserLoggingsPojo userLoggingsVo) {
		return obj_Term.deleteTermDetails(vo,button,userLoggingsVo);
	}


	public String deleteSeparateTermDetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {

		return obj_Term.deleteSeparateTermDetails(vo,userLoggingsVo);
	}



	@Override
	public String dateOverLapValidate(String date,String accyear,UserLoggingsPojo userLoggingsVo) {
		return obj_Term.dateOverLapValidate(date,accyear,userLoggingsVo);
	}


	public String separatedateOverLapValidate(String date,String accyear ,String locationId,UserLoggingsPojo custdetails) {
		return obj_Term.separatedateOverLapValidate(date,accyear,locationId, custdetails);
	}



	@Override
	public ArrayList<TermMasterVo> transportTermList(TermMasterVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addtermSeparatefeedetails(TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {



		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterServiceIMPL :addtermSeparatefeedetails  Starting");
		// TODO Auto-generated method stub

		String check="";
		TermMasterDAO TermMasterDAO = new TermMasterDAOIMPL();

		if (vo.getTermid().equalsIgnoreCase(""))
		{
			try
			{
				check = TermMasterDAO.addtermSeparatefeedetails(vo,userLoggingsVo);
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		else if (!vo.getTermid().equalsIgnoreCase(""))
		{
			try
			{
				check = TermMasterDAO.updatesepataretermDetails(vo,userLoggingsVo);
			}

			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}


		}



		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TermMasterServiceIMPL :addtermSeparatefeedetails Ending");
		return check;


	}


	@Override
	public List<TermMasterVo> getTermDetails(String academic_year,String location, UserLoggingsPojo custdetails) {
		return obj_Term.getTermDetails(academic_year,location,custdetails);
	}


	@Override
	public String dateOverLapValidate(String date, String accyear,
			String locationId,TermMasterVo vo,UserLoggingsPojo userLoggingsVo) {
		return obj_Term.dateOverLapValidate(date,accyear,locationId,vo,userLoggingsVo);
	}
	

}
