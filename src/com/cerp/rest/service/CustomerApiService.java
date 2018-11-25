package com.cerp.rest.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONObject;

import com.cerp.rest.model.Productdetails;
import com.cerp.rest.model.Customer;
import com.cerp.rest.model.DomainVerification;

public abstract class CustomerApiService {
	 
	public abstract int addCustomer(Customer customerdetails,@Context SecurityContext securityContext);

	public abstract Object validateCustomer(String userName, String password, String domainName);

	public abstract DomainVerification verifySubDomain(String input, SecurityContext securityContext);
}