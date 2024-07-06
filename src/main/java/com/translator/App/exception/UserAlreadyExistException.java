package com.translator.App.exception;

import java.util.Date;

public class UserAlreadyExistException extends RuntimeException{

    private String message;
    private Date errorTime;
    private String field;

    public UserAlreadyExistException(String message, Date errorTime, String field)
    {
        super("Passed email is already exist");
        this.message = message;
        this.errorTime = errorTime;
        this.field = field;

    }

}
