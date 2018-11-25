package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.AddDesignation;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.vo.AddDesignationVO;
import com.centris.campus.vo.AddStageVO;

public interface StageDAO

{
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo);
	public String insertstage(AddStagePojo apojo);
	public String updatestage(AddStagePojo apojo,UserLoggingsPojo custdetails);
	public AddStageForm EditStageDetails(AddStageForm aform,UserLoggingsPojo userLoggingsVo);
	public String deleteStage(String[] stageid,String log_audit_session,UserLoggingsPojo userLoggingsVo);
	public boolean getstagecount(AddStageVO vo);


}
