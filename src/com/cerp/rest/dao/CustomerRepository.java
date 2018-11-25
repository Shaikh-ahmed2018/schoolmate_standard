package com.cerp.rest.dao;

import com.cerp.rest.model.Customer;
import com.cerp.rest.model.DomainVerification;

public abstract class CustomerRepository {
	
	public abstract int addCustomer(Customer customerdetails);
	public abstract int verifyCustomer(Customer customerdetails);
	public abstract Customer getcustomerDetails(String custId);
	public abstract Object validateCustomer(String userName, String password, String domainname);
	public abstract DomainVerification verifySubDomain(int statusid);
	
}
