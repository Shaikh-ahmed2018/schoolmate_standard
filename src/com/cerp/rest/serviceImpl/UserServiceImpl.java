package com.cerp.rest.serviceImpl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.cerp.rest.api.BlockUerApiResponse;
import com.cerp.rest.api.UserApiResponse;
import com.cerp.rest.dao.UserDao;
import com.cerp.rest.dao.UserDaoImpl;
import com.cerp.rest.model.User;
import com.cerp.rest.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userdao = null;

	@Override
	public Response addUser(User user, SecurityContext securityContext) {
		
		userdao = new UserDaoImpl();
		String custid = userdao.addUser(user);
		if(!custid.equals("fail")){
			return Response.ok().entity(new UserApiResponse(UserApiResponse.OK,"User Details Saved",custid)).build();
		}else{
			return Response.ok().entity(new UserApiResponse(UserApiResponse.ERROR,"Bad Request","unknown")).build();
		}
	}

	@Override
	public Response modifyUser(User user, SecurityContext securityContext) {
		
		userdao = new UserDaoImpl();
		String custid = userdao.modifyUser(user);
		if(!custid.equals("fail")){
			return Response.ok().entity(new UserApiResponse(UserApiResponse.OK,"User Details Saved",custid)).build();
		}else{
			return Response.ok().entity(new UserApiResponse(UserApiResponse.ERROR,"Bad Request","unknown")).build();
		}
	}

	@Override
	public Response blockUser(User user, SecurityContext securityContext) {
		userdao = new UserDaoImpl();
		String status = userdao.blockUser(user);
		if(status.equals("success")){
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.OK,"User Details Modified")).build();
		}
		else{
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.ERROR,"Bad Request")).build();
		}
	}

	@Override
	public Response verifyUser(String username, SecurityContext securityContext) {
		userdao = new UserDaoImpl();
		int status = userdao.verifyUser(username);
		if(status == 0){
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.OK,"success")).build();
		}
		else if(status == 1){
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.FOUND,"found")).build();
		}else{
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.TOO_BUSY,"busy")).build();
		}
	}

	@Override
	public Response changePwd(User user, SecurityContext securityContext) {
		userdao = new UserDaoImpl();
		int status = userdao.changePwd(user);
		if(status == 1){
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.OK,"success")).build();
		}
		else if(status == 0){
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.ERROR,"error")).build();
		}else{
			return Response.ok().entity(new BlockUerApiResponse(BlockUerApiResponse.TOO_BUSY,"busy")).build();
		}
	}
}
