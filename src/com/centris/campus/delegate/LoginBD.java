package com.centris.campus.delegate;

import java.util.List;

import org.json.JSONException;

import com.centris.campus.pojo.CustomerDBDetails;
import com.centris.campus.pojo.UserLoggingsPojo;
import com.centris.campus.serviceImpl.LoginServiceImpl;
import com.centris.campus.vo.DairyDetailsVo;
import com.centris.campus.vo.LoginVo;
import com.centris.campus.vo.UserDetailVO;

public class LoginBD {

	public LoginVo validateUserBD(String UserName, String Password, UserLoggingsPojo pojo) {

		return new LoginServiceImpl().validateUserServiceImpl(UserName,
				Password,pojo);
	}

	public UserDetailVO loadUserBD(LoginVo loginvo,UserLoggingsPojo userLoggingsVo) {

		return new LoginServiceImpl().loadUserServiceImpl(loginvo,userLoggingsVo);
	}


	public int checkCurrentPassword(String user, String currentPassword,
			String currentuserId, UserLoggingsPojo custdetails) {

		return new LoginServiceImpl().checkCurrentPassword(user,
				currentPassword, currentuserId,custdetails);
	}

	public int changePassword(String user, String newPassword,
			String currentuserId, String log_audit_session,UserLoggingsPojo pojo) {

		return new LoginServiceImpl().changePassword(user, newPassword,
				currentuserId,log_audit_session, pojo);
	}

	public String userValidCase(String uname, String password, UserLoggingsPojo custdetails) {
		return new LoginServiceImpl().userValidCase(uname, password,custdetails);
	}

	public String saveDairy(String content, String rowid, String date,
			String userId, String userType, UserLoggingsPojo custdetails, String sessionid) {
		return new LoginServiceImpl().saveDairy(content, rowid,date,userId,userType,custdetails,sessionid);
	}

	public List<DairyDetailsVo> getDairy(String userId,UserLoggingsPojo custdetails) {
		return new LoginServiceImpl().getDairy(userId,custdetails);
	}

	public UserDetailVO loadNewUserBD(LoginVo loginVo,UserLoggingsPojo userLoggingsVo) {
		return new LoginServiceImpl().loadNewUserBD(loginVo,userLoggingsVo);
	}

	public UserDetailVO validateNewUserBD(String userid, UserLoggingsPojo custdetails) {
		return new LoginServiceImpl().validateNewUserBD(userid,custdetails);
	}

	public String callPWDChangeService(String auserid, String custId, String newPassword) throws JSONException {
		return new LoginServiceImpl().callPWDChangeService(auserid,custId,newPassword);
	}

}
