package com.exception;

public class ExceptionDAO extends RuntimeException {

    public ExceptionDAO(String message){
        super(message);
    }
}
