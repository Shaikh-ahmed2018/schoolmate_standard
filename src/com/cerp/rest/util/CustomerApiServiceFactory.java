package com.cerp.rest.util;

import com.cerp.rest.service.CustomerApiService;
import com.cerp.rest.serviceImpl.CustomerApiServiceImpl;

public class CustomerApiServiceFactory {
	 private final static CustomerApiService service = new CustomerApiServiceImpl();

	    public static CustomerApiService getCustomerApi() {
	        return service;
	    }
}
