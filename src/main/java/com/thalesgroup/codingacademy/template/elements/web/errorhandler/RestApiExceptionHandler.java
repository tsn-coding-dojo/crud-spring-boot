/*
 * Copyright © 2021-2021 Thales Services Numériques
 *
 * Licensed under the Thales Inner Source Software License:
 *
 * Version 1.2, InnerOpen - Distribution Not Controlled
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://gitlab.thalesdigital.io/innersource/licenses
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.thalesgroup.codingacademy.template.elements.web.errorhandler;

import com.thalesgroup.codingacademy.template.elements.domain.dto.RestApiErrorDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.TechnicalException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler for the API
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * The logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiExceptionHandler.class);
    private static final String TECHNICAL_ERROR_MESSAGE = "Something went wrong, please try again later.";

    /**
     * Handle {@link TechnicalException} to return HTTP response with an internal server error code and an already defined
     * message
     *
     * @param exception : the {@link TechnicalException} which has been handled
     * @return {@code ResponseEntity<Object>} : HTTP response with {@link HttpStatus#INTERNAL_SERVER_ERROR code} and an
     * already defined message
     */
    @ExceptionHandler(TechnicalException.class)
    protected ResponseEntity<Object> handleTechnicalException(TechnicalException exception) {
        // Log the error
        LOGGER.error(exception.getMessage(), exception);

        // Return a HTTP response with internal server error code and an already defined message
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, TECHNICAL_ERROR_MESSAGE);
    }

    /**
     * Handle {@link FunctionalException} to return HTTP response with HTTP error code and an error message
     *
     * @param exception : the {@link FunctionalException} which has been handled
     * @return {@code ResponseEntity<Object>} : HTTP response with {@link HttpStatus error code} and an error
     * message
     */
    @ExceptionHandler(FunctionalException.class)
    protected ResponseEntity<Object> handleFunctionalException(FunctionalException exception) {
        String errorMessage = exception.getMessage();

        // Log the error
        LOGGER.error(errorMessage, exception);

        // Calculate the HTTP status code and the message for the HTTP response
        HttpStatus httpStatus;
        Throwable cause = exception.getCause();
        if (cause instanceof HttpClientErrorException) {
            httpStatus = (HttpStatus) ((HttpClientErrorException) cause).getStatusCode();
            switch (httpStatus) {
                case UNAUTHORIZED:
                    errorMessage = "Authentication failed.";
                    break;
                case FORBIDDEN:
                    errorMessage = "Forbidden: You don't have permission to access this resource.";
                    break;
                default:
            }
        } else if (cause instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        // Return a HTTP response with a HTTP status code from the original error if it is an HttpClientErrorException
        // or an internal server error code and a context message
        return buildErrorResponse(httpStatus, errorMessage);
    }

    /**
     * Build the HTTP response with a body and HTTP status code
     *
     * @param httpStatus : the {@link HttpStatus code}
     * @param errorMessage : the error message
     * @return {@code ResponseEntity<Object>} : the HTTP response with a body and {@link HttpStatus code}
     */
    private ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, String errorMessage) {
        var error = new RestApiErrorDto(httpStatus.getReasonPhrase(), httpStatus.value(), errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }
}
