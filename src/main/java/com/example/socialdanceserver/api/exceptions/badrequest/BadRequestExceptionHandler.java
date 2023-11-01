package com.example.socialdanceserver.api.exceptions.badrequest;

import com.example.socialdanceserver.api.exceptions.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class BadRequestExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Object> handleException(BadRequestException ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
