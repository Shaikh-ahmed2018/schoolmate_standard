package com.centris.campus.service;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddStageVO;

public interface StageService
{
	
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo);

	public String insertStage(AddStageVO vo,UserLoggingsPojo custdetails);

	public AddStageForm EditStageDetails(AddStageForm aform,UserLoggingsPojo userLoggingsVo);

	public String deleteStage(String[] stageid,String log_audit_session,UserLoggingsPojo userLoggingsVo);
	
	public boolean getstagecount(AddStageVO vo);

}
