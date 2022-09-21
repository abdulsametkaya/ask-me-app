package com.app.askme.exceptions;

import com.app.askme.exceptions.messages.ApiResponseError;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiResponseError error){
        return new ResponseEntity<>(error,error.getStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request){
        ApiResponseError error=new ApiResponseError(HttpStatus.CONFLICT,ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ApiResponseError error=new ApiResponseError(HttpStatus.CONFLICT,ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }


    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
