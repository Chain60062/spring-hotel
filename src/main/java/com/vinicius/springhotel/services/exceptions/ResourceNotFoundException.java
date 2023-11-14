package com.vinicius.springhotel.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Requested resource not found. Id: "+ id);
    }    
}