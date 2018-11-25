package com.cerp.rest.util;

import com.cerp.rest.service.UserService;
import com.cerp.rest.serviceImpl.UserServiceImpl;

public class UserApiServiceFactory {
	 private final static UserService service = new UserServiceImpl();

	    public static UserService getCustomerApi() {
	        return service;
	    }
}
