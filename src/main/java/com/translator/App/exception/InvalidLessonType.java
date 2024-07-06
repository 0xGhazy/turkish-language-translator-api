package com.translator.App.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLessonType extends RuntimeException{

    private String variableValue;
    private String variableName;

    public InvalidLessonType(String variableName, String variableValue){
        super(String.format("Invalid path variable {%s} value @ {%s}", variableName, variableValue));
    }

}
