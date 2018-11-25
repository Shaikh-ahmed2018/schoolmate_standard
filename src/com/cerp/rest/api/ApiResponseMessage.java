package com.cerp.rest.api;

import javax.xml.bind.annotation.XmlTransient;

public class ApiResponseMessage {
	
    public static final int ERROR = 500;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 201;
    public static final int TOO_BUSY = 5;
    public static final int FOUND = 300; 
    public static final int NOTFOUND = 404; 
    int code;
    int custid;
    String message;

    public ApiResponseMessage(){}

    public ApiResponseMessage(int code, String message,int custid){
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
        case NOTFOUND:
            break;
        default:
            break;
        }
        setCustid(custid);
        this.message = message;
    }

    @XmlTransient
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

	/**
	 * @return the custid
	 */
	public int getCustid() {
		return custid;
	}

	/**
	 * @param custid the custid to set
	 */
	public void setCustid(int custid) {
		this.custid = custid;
	}
    
    
}
