package com.example.socialdanceserver.api.exceptions.jwt;

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
public class JwtAuthenticationExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler({JwtAuthenticationException.class})
    protected ResponseEntity<Object> handleException(JwtAuthenticationException ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
}
