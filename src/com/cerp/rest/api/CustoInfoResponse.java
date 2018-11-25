package com.cerp.rest.api;

import com.cerp.rest.model.CustomerInfo;

public class CustoInfoResponse {
	 
	public static final int ERROR = 500;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	public static final int OK = 201;
	public static final int TOO_BUSY = 5;
	public static final int FOUND = 300; 
	    
	int code;
    String message;
    CustomerInfo customerinfo;
    
    public CustoInfoResponse(){}
    
    public CustoInfoResponse(int code, String message,CustomerInfo customerinfo){
    	
    	this.code = code;
        switch(code){
        case ERROR:
            break;
        case WARNING:
            break;
        case INFO:
            break;
        case OK:
            break;
        case TOO_BUSY:
            break;
        default:
            break;
        }
        this.message = message;
        this.customerinfo = customerinfo;
    	
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomerInfo getCustomerinfo() {
		return customerinfo;
	}

	public void setCustomerinfo(CustomerInfo customerinfo) {
		this.customerinfo = customerinfo;
	}
	
}
