package com.centris.campus.delegate;

import java.util.List;
import java.util.Set;

import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.UploadStudentXSLServiceIMPL;
import com.centris.campus.vo.UploadStudentXlsVO;

public class UploadStudentXSLBD {

	public Set<UploadStudentXlsVO> insertEmpXSL(List<UploadStudentXlsPOJO> list, String username, String duplicate, String schoolLocation, String log_audit_session, UserLoggingsPojo userLoggingsVo, long stucount) {
		return new UploadStudentXSLServiceIMPL().insertEmpXSL(list,username,duplicate,schoolLocation,log_audit_session,userLoggingsVo,stucount);
	}
}
