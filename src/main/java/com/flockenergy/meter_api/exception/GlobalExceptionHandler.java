
package com.flockenergy.meter_api.exception;

import com.flockenergy.meter_api.dto.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(PortalException.class)
    public ResponseEntity<ApiErrorResponse> handlePortalError(
            PortalException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "The upstream portal returned an unsuccessful response.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiErrorResponse> handleIOException(
            IOException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "Unable to communicate with the upstream portal.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ApiErrorResponse> handleInterrupted(
            InterruptedException exception,
            HttpServletRequest request) {

        Thread.currentThread().interrupt();

        return buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                "The request was interrupted. Please try again.",
                request.getRequestURI()
        );
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            String path) {

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
}
