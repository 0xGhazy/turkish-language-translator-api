package com.translator.App.exception;


import com.translator.App.utils.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResponseError> handleDuplicationException(
            ResourceNotFound exception,
            WebRequest webRequest)
    {
        ResponseError error = new ResponseError(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ResponseError> handleEmailDuplication(
            UserAlreadyExistException exception,
            WebRequest webRequest)
    {
        ResponseError error = new ResponseError(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<ResponseError> handleInvalidEmailOrPasswordException(
            InvalidEmailOrPasswordException exception,
            WebRequest webRequest)
    {
        ResponseError error = new ResponseError(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLessonType.class)
    public ResponseEntity<ResponseError> handleInvalidLessonTypeException(
            InvalidLessonType exception,
            WebRequest webRequest)
    {
        ResponseError error = new ResponseError(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
