package com.centris.campus.delegate;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.UploadStaffXSLServiceIMPL;
import com.centris.campus.vo.UploadStaffXlsVO;


public class UploadStaffXSLBD {

	public Set<UploadStaffXlsVO> insertStaffXSL(List<UploadStaffXlsPOJO> list, String username, String duplicate,String log_audit_session,UserLoggingsPojo custdetails) {
		return new UploadStaffXSLServiceIMPL().insertStaffXSL(list, username,duplicate,log_audit_session,custdetails);
	}

}
