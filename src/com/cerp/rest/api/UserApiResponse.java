package com.cerp.rest.api;

import javax.xml.bind.annotation.XmlTransient;

public class UserApiResponse {

	 public static final int ERROR = 500;
	 public static final int WARNING = 2;
	 public static final int INFO = 3;
	 public static final int OK = 201;
	 public static final int TOO_BUSY = 5;
	 public static final int FOUND = 300;
	
	 int code;
	 String message;
	 String auserid;
	 
	 public UserApiResponse(){}
	 
	  public UserApiResponse(int code, String message,String auserid){
	        this.code = code;
	        this.auserid=auserid;
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

		public String getAuserid() {
			return auserid;
		}

		public void setAuserid(String auserid) {
			this.auserid = auserid;
		}
	    
}

