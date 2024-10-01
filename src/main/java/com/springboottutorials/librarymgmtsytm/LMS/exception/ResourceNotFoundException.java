package com.springboottutorials.librarymgmtsytm.LMS.exception;



public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String message){
        super(message);
    }
}
