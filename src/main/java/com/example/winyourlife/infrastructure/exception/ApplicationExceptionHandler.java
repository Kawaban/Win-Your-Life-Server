package com.example.winyourlife.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorWrapper> handleAll(Exception ex, WebRequest request) {
        final ApiError apiError = ApiError.builder()
                .path(extractRequestUri(request))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorWrapper(apiError));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorWrapper> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        final String uri = extractRequestUri(request);
        final ApiError apiError = ApiError.builder()
                .path(uri)
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(uri.equals("/login") ? "Wrong password or email" : ex.getMessage())
                .status(HttpStatus.FORBIDDEN.getReasonPhrase())
                .build();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiErrorWrapper(apiError));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final String uri = extractRequestUri(request);
        final ApiError apiError = ApiError.builder()
                .path(uri)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(uri.equals("/register") ? "Data missing" : ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorWrapper(apiError));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorWrapper> handleConflict(UserAlreadyExistsException ex, WebRequest request) {
        final ApiError apiError = ApiError.builder()
                .path(extractRequestUri(request))
                .statusCode(HttpStatus.CONFLICT.value())
                .message("Email already used")
                .status(HttpStatus.CONFLICT.getReasonPhrase())
                .build();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiErrorWrapper(apiError));
    }

    @ExceptionHandler(ApplicationEntityNotFoundException.class)
    public ResponseEntity<ApiErrorWrapper> handleEntityNotFound(ApplicationEntityNotFoundException ex, WebRequest request) {
        final ApiError apiError = ApiError.builder()
                .path(extractRequestUri(request))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message("Entity not found")
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorWrapper(apiError));
    }

    private String extractRequestUri(WebRequest w) {
        if (w instanceof ServletWebRequest servletWebRequest) {
            return servletWebRequest.getRequest().getRequestURI();
        }

        return "Unknown URL";
    }
}
