package com.cerp.rest.api;


public interface AsyncResponse {
   boolean resume(Object response);
   boolean resume(Throwable response);
}