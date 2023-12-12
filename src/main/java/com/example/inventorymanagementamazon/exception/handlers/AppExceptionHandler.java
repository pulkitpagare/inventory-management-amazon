package com.example.inventorymanagementamazon.exception.handlers;

import com.example.inventorymanagementamazon.exception.CategoryRequiredException;
import com.example.inventorymanagementamazon.exception.NotFoundException;
import com.example.inventorymanagementamazon.response.APIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), Boolean.TRUE );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), Boolean.TRUE );
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest webRequest ){
        return buildResponseEntity(e, HttpStatus.NOT_FOUND, e.getMessage(), Boolean.TRUE);
    }
    @ExceptionHandler(value = {CategoryRequiredException.class})
    protected ResponseEntity<Object> handleCategoryException(Exception e, WebRequest webRequest ){
        return buildResponseEntity(e, HttpStatus.NOT_FOUND, e.getMessage(), Boolean.TRUE);
    }

    private ResponseEntity<Object> buildResponseEntity(Exception exception, HttpStatus httpStatus,
                                                       String message, boolean loggable){
        if (loggable){
            log.error("exception occurred : {}", exception.getMessage());
        }
        return ResponseEntity.status(httpStatus).body(APIErrorResponse.builder().result(message).build());
    }
}
