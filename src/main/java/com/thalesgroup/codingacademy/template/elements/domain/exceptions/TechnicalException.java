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
package com.thalesgroup.codingacademy.template.elements.domain.exceptions;

/**
 * Exception Class thrown when a technical error has been raised
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
public class TechnicalException extends Exception {
    /**
     * Constructor
     *
     * @param message : the message to set in the exception
     */
    public TechnicalException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message : the message to set in the exception
     * @param cause : the cause to set in the exception
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
