package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StageDAOIMPL;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.service.StageService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddStageVO;

public class StageServiceIMPL implements StageService

{
	private static 	Logger logger = Logger.getLogger(StageServiceIMPL.class);

	
	
	public ArrayList<AddStageVO> StageDetails(UserLoggingsPojo userLoggingsVo)
	{
		return new StageDAOIMPL().StageDetails(userLoggingsVo);

	}

	public String insertStage(AddStageVO vo, UserLoggingsPojo custdetails) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageServiceIMPL:insertStage: Starting");
		
		StageDAOIMPL dao=new StageDAOIMPL();
		
		String check="";
		
		
		if(vo.getStage_id().equalsIgnoreCase(""))
		
		{
		AddStagePojo apojo=new AddStagePojo();
		
		try {

			/*String s1=IDGenerator.getPrimaryKeyID("campus_fee_stage",custdetails);*/

			apojo.setStage_name(vo.getStageName());
			apojo.setAmount(vo.getAmount());
			apojo.setStage_description(vo.getDescription());
			apojo.setStageid(vo.getStage_id());
			/*apojo.setStagecode(s1);*/
			apojo.setCreateuser(vo.getCreatedby());
			apojo.setLog_audit_session(vo.getLog_audit_session());
			apojo.setCustid(vo.getCustid());
			apojo.setAccYearId(vo.getAccyearCode());
			apojo.setLocId(vo.getLocId());
			
			check= dao.insertstage(apojo,custdetails);
			
		} 
		catch (Exception e)
		{
			
			logger.error(e);
			e.printStackTrace();
			
		}
		}
		
		else if(!vo.getStage_id().equalsIgnoreCase(""))
		{
			
			AddStagePojo apojo=new AddStagePojo();
			apojo.setStage_name(vo.getStageName());
			apojo.setAmount(vo.getAmount());
			apojo.setStage_description(vo.getDescription());
			apojo.setStageid(vo.getStage_id());
			apojo.setCreateuser(vo.getCreatedby());
			apojo.setLog_audit_session(vo.getLog_audit_session());
			apojo.setCustid(vo.getCustid());
			apojo.setLocId(vo.getLocId());
			check= dao.updatestage(apojo,custdetails);
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StageServiceIMPL:insertStage: Ending");
		return check;
		
	
	}

	
	public AddStageForm EditStageDetails(AddStageForm aform,UserLoggingsPojo userLoggingsVo)
	
	{
	    return new StageDAOIMPL().EditStageDetails(aform,userLoggingsVo);
	}

	public String deleteStage(String[] stageid,String log_audit_session, UserLoggingsPojo userLoggingsVo)
	{
		return new StageDAOIMPL().deleteStage(stageid,log_audit_session,userLoggingsVo);
	}

	public boolean getstagecount(AddStageVO vo)
	{
	return new StageDAOIMPL().getstagecount(vo);

	}

	public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo, UserLoggingsPojo userLoggingsVo) {
		return new StageDAOIMPL().SelectAllSatges(vo,userLoggingsVo);
	}

	public List<AddStageVO> searchStage(String searchName, UserLoggingsPojo custdetails, String loc) {
		return new StageDAOIMPL().searchStage(searchName,custdetails,loc);
	}

	@Override
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AddStageVO> StageDetailsList(String loc, UserLoggingsPojo custdetails) {
		return new StageDAOIMPL().StageDetailsList(loc,custdetails);
	}

	public List<AddStageVO> stagedetailsListByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {
		return new StageDAOIMPL().stagedetailsListByStatus(vo,custdetails);
	}

	public String deleteStageByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {
		return new StageDAOIMPL().deleteStageByStatus(vo,custdetails);
	}

	
	}


