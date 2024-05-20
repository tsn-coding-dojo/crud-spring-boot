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
package com.thalesgroup.codingacademy.template.elements.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for error for REST API
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
@JsonTypeName("error")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Getter
@Setter
public class RestApiErrorDto {
    private String title;
    private int status;
    private String detail;

    /**
     * Default constructor
     */
    public RestApiErrorDto() {
    }

    /**
     * Constructor to initialize object
     *
     * @param title : the title of the error
     * @param status : the {@link org.springframework.http.HttpStatus code}
     * @param detail : the error message to set in the body
     */
    public RestApiErrorDto(String title, int status, String detail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }
}
