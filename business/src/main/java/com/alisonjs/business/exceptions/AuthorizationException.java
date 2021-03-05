package com.alisonjs.business.exceptions;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String msg){
        super(msg);
    }
}
