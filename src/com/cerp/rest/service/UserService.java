package com.cerp.rest.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.cerp.rest.model.User;

public interface UserService {

	public Response addUser(User user, SecurityContext securityContext);

	public Response modifyUser(User user, SecurityContext securityContext);

	public Response blockUser(User user, SecurityContext securityContext);

	public Response verifyUser(String username, SecurityContext securityContext);
	
	public Response changePwd(User user, SecurityContext securityContext);
	
}
