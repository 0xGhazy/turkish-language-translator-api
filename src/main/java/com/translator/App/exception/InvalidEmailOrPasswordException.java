package com.translator.App.exception;

import java.util.Date;

public class InvalidEmailOrPasswordException extends RuntimeException{

    private String message;
    private Date errorTime;

    public InvalidEmailOrPasswordException(String message, Date errorTime)
    {
        super(message);
        this.message = message;
        this.errorTime = errorTime;
    }

}
