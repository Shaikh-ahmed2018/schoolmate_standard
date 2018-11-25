package com.centris.campus.delegate;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.UploadStageXSLServiceIMPL;
import com.centris.campus.vo.UploadStageXlsVO;


public class UploadStageXSLBD {

	public Set<UploadStageXlsVO> insertStageXSL(List<UploadStageXlsPOJO> list, String username, String duplicate,String log_audit_session,  UserLoggingsPojo userLoggingsVo, String location) {
		return new UploadStageXSLServiceIMPL().insertStageXSL(list, username,duplicate,log_audit_session,userLoggingsVo,location);
	}

	public String updateStageXSL(String[] stageId, String[] acc, String[] loc, String[] amount, String user, UploadStageXlsPOJO pojo,UserLoggingsPojo userLoggingsVo) {
		
		return new UploadStageXSLServiceIMPL().updateStageXSL(stageId,acc,loc,amount,user,pojo,userLoggingsVo);
	}

}
