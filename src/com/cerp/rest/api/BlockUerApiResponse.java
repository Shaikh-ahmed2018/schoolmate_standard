

	package com.cerp.rest.api;

	import javax.xml.bind.annotation.XmlTransient;

	public class BlockUerApiResponse {

		 public static final int ERROR = 400;
		 public static final int FORBIDDEN = 403;
		 public static final int INFO = 3;
		 public static final int OK = 201;
		 public static final int TOO_BUSY = 5;
		 public static final int FOUND = 300;
		 public static final int ACCEPTED = 202; 
		
		 int code;
		 String message;
		 
		 public BlockUerApiResponse(){}
		 
		  public BlockUerApiResponse(int code, String message){
		        this.code = code;
		       
		        switch(code){
		        case ERROR:
		            break;
		        case FORBIDDEN:
		            break;
		        case INFO:
		            break;
		        case OK:
		            break;
		        case TOO_BUSY:
		            break;
		        case ACCEPTED:
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
	}



