package com.wallet.controller.exception;


import com.wallet.service.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = { BusinessException.class})
    protected ResponseEntity<Object> exceptionGeneric(BusinessException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(ex.getStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
    }
}
