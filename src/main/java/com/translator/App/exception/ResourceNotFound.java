package com.translator.App.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFound(String resourceName, String filedName, String filedValue){
        super(String.format("%s not found with %s : {%s}", resourceName, filedName, filedValue));
    }
}
