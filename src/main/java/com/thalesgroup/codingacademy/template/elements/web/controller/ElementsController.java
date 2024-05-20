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
package com.thalesgroup.codingacademy.template.elements.web.controller;

import com.thalesgroup.codingacademy.template.elements.domain.api.processus.ElementProcessus;
import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Elements controller
 * Microservice API to manage elements in the database of the Template application
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
@Tag(name = "Microservice API to manage elements in the database of the Template application",
        description = "Microservice API to manage elements in the database of the Template application")
@RestController
@RequestMapping("/elements")
public class ElementsController {
    private static final String ID = "id";

    private final ElementProcessus elementProcessus;

    /**
     * Constructor to initialize the business layer with Autowired
     *
     * @param elementProcessus : the ElementProcessus to initialize
     */
    @Autowired
    public ElementsController(ElementProcessus elementProcessus) {
        this.elementProcessus = elementProcessus;
    }

    /**
     * The endpoint to get all element from database
     *
     * @return {@code ResponseEntity<List<ElementDto>>} : the response with the list of all elements and http
     * status code 200 (OK).
     */
    @Operation(description = "Get all elements from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all elements from database has succeed"),
            @ApiResponse(responseCode = "401",
                    description = "The user is not authenticated (no valid Keycloak credentials)",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE))
    })
    @CrossOrigin("*")
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ElementDto>> getAll() {
        List<ElementDto> elementsDtoList = elementProcessus.getAll();
        return ResponseEntity.ok(elementsDtoList);
    }

    /**
     * The endpoint to get an element with the ID from database
     *
     * @param id : the ID of the element to get
     * @return {@code ResponseEntity<ElementDto>} : the response with the element and http status code 200 (OK).
     */
    @Operation(description = "Get an element from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the element from database has succeed"),
            @ApiResponse(responseCode = "401",
                    description = "The user is not authenticated (no valid Keycloak credentials)",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE))
    })
    @CrossOrigin("*")
    @GetMapping(value = "{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElementDto> get(@PathVariable(ID) String id) {
        ElementDto elementDto;
        ResponseEntity<ElementDto> responseEntity;

        try {
            elementDto = elementProcessus.get(id);
            responseEntity = ResponseEntity.ok(elementDto);
        } catch (FunctionalException exception) {
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }

    /**
     * The endpoint to create an element to the database
     *
     * @param elementDto : the element to create in the database
     * @return ResponseEntity : the response with http status code 201 (created) and the created element in the body.
     */
    @Operation(description = "Create an element from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creating an element to the database has succeed",
                    content = @Content(schema = @Schema(implementation = ElementDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401",
                    description = "The user is not authenticated (no valid Keycloak credentials)",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElementDto> create(@RequestBody ElementDto elementDto) {
        ElementDto createdElementDto = elementProcessus.create(elementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElementDto);
    }

    /**
     * The endpoint to update an element to the database
     *
     * @param elementDto : the element to update in the database
     * @return ResponseEntity : the response with http status code 200 (updated) and the updated element in the body.
     */
    @Operation(description = "Update an element from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updating an element to the database has succeed",
                    content = @Content(schema = @Schema(implementation = ElementDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401",
                    description = "The user is not authenticated (no valid Keycloak credentials)",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE))
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElementDto> update(@RequestBody ElementDto elementDto) {
        ElementDto updatedElementDto;
        ResponseEntity<ElementDto> responseEntity;

        try {
            updatedElementDto = elementProcessus.update(elementDto);
            responseEntity = ResponseEntity.ok(updatedElementDto);
        } catch (FunctionalException e) {
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }

    /**
     * The endpoint to delete an element from the database
     *
     * @param id : the ID of the element to delete
     * @return ResponseEntity : the response with http status code 204 (no content).
     */
    @Operation(description = "Delete an element from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete an element from the database has succeed"),
            @ApiResponse(responseCode = "401",
                    description = "The user is not authenticated (no valid Keycloak credentials)",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "404",
                    description = "There is no element to delete for the ID",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            mediaType = MediaType.TEXT_HTML_VALUE))
    })
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ElementDto> delete(@PathVariable(ID) String id) {
        ResponseEntity<ElementDto> responseEntity;

        try {
            elementProcessus.delete(id);
            responseEntity = ResponseEntity.noContent().build();
        } catch (FunctionalException e) {
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }
}
