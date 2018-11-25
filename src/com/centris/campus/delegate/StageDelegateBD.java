package com.centris.campus.delegate;
import java.util.ArrayList;
import java.util.List;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.StageServiceIMPL;

import com.centris.campus.vo.AddStageVO;


public class StageDelegateBD {

	public  ArrayList<AddStageVO> StageDetails(UserLoggingsPojo userLoggingsVo){
		
			return new StageServiceIMPL().StageDetails(userLoggingsVo);
		
	}
	
	public String insertStage(AddStageVO vo, UserLoggingsPojo custdetails){
		return new StageServiceIMPL().insertStage(vo,custdetails);
	}
	
	public AddStageForm EditStageDetails(AddStageForm aform,UserLoggingsPojo userLoggingsVo){
		return new StageServiceIMPL().EditStageDetails(aform,userLoggingsVo);
	}
	
	public String deleteStage(String[] stageid,String log_audit_session,UserLoggingsPojo userLoggingsVo) 
	{
		return new StageServiceIMPL().deleteStage(stageid,log_audit_session,userLoggingsVo);
	}

public boolean getstagecount(AddStageVO vo) {
	
	return new StageServiceIMPL().getstagecount(vo);
}

public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo, UserLoggingsPojo userLoggingsVo) {
	return new StageServiceIMPL().SelectAllSatges(vo,userLoggingsVo);
	
}

public List<AddStageVO> searchStage(String searchName, UserLoggingsPojo custdetails, String loc) {
	return new StageServiceIMPL().searchStage(searchName,custdetails,loc);
}

public List<AddStageVO> StageDetailsList(String loc, UserLoggingsPojo custdetails) {
	return new StageServiceIMPL().StageDetailsList(loc,custdetails);
}

public List<AddStageVO> stagedetailsListByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {
	return new StageServiceIMPL().stagedetailsListByStatus(vo,custdetails);
}

public String deleteStageByStatus(AddStageVO vo, UserLoggingsPojo custdetails) {
	return new StageServiceIMPL().deleteStageByStatus(vo,custdetails);
}

	
}
