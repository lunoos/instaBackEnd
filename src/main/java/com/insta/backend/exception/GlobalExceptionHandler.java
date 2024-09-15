package com.insta.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {

    
	@ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<InstaErrorResponse> handleResNotFndExep(ResourceNotFoundException ex,WebRequest request){
    	InstaErrorResponse errorResponse = new InstaErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    	return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<InstaErrorResponse> handleAllExceptions(Exception exception, WebRequest request) {
    	InstaErrorResponse errorDetail = new InstaErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
        	errorDetail = new InstaErrorResponse(
            		401,
            		"The username or password is incorrect",
                    request.getDescription(false),
                    LocalDateTime.now());
        }

        if (exception instanceof AccountStatusException) {
        	errorDetail =new InstaErrorResponse(
            		403,
            		"The account is locked",
                    request.getDescription(false),
                    LocalDateTime.now());
        }

        if (exception instanceof AccessDeniedException) {
        	errorDetail =new InstaErrorResponse(
            		403,
            		"You are not authorized to access this resource",
                    request.getDescription(false),
                    LocalDateTime.now());
        }

        if (exception instanceof SignatureException) {
            errorDetail = new InstaErrorResponse(
            		403,
            		"The JWT signature is invalid",
                    request.getDescription(false),
                    LocalDateTime.now());
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = new InstaErrorResponse(
            		403,
            		 "The JWT token has expired",
                    request.getDescription(false),
                    LocalDateTime.now());
        }

        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<InstaErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
    	InstaErrorResponse errorResponse = new InstaErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
