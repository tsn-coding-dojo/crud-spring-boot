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
package com.thalesgroup.codingacademy.template.elements.processus;


import com.thalesgroup.codingacademy.template.elements.domain.api.processus.ElementProcessus;
import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;

import java.time.Instant;
import java.util.List;

/**
 * The business part to manage elements in Template application
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
// TODO : Tell to Spring Boot that it is a bean which implement the interface
public class ElementProcessusImpl implements ElementProcessus {
    /**
     * Constructor to initialize the service layer with Autowired
     *
     * @param elementService : the ElementService to initialize
     */
    // TODO : autowired the persistence layer
    public ElementProcessusImpl() {

    }

    /**
     * Get all the elements from the database
     *
     * @return {@code List<ElementDto>} : the list of all elements from the database
     */
    @Override
    public List<ElementDto> getAll() {
        return null; // TODO : get all elements from persistence layer
    }

    /**
     * Get the element with ID from the database
     *
     * @param id : the ID of the element to get
     * @return ElementDto : the element with ID from the database
     */
    @Override
    public ElementDto get(String id) {
        return null; // TODO : get the element by ID from persistence layer
    }

    /**
     * Create (add) the element in the database
     *
     * @param elementDto : the element to create in the database
     * @return ElementDto : the element which has been created and added in the database
     */
    @Override
    public ElementDto create(ElementDto elementDto) {
        elementDto.setId("uuid"); // TODO : use UUID to generate a unique ID
        Instant actualDate = Instant.now();
        elementDto.setCreatedTimeStamp(actualDate);
        elementDto.setLastModified(actualDate);
        // TODO : create the element using persistence layer
        return elementDto;
    }

    /**
     * Update the element in the database
     *
     * @param elementDto : the element to update in the database
     * @return ElementDto : the element which has been updated in the database
     */
    @Override
    public ElementDto update(ElementDto elementDto) {
        // TODO : get the created time from the existing element from persistence layer --> createdTimeStamp
        Instant createdTimeStamp = null;

        // Keep the existing creation date
        elementDto.setCreatedTimeStamp(createdTimeStamp);

        Instant actualDate = Instant.now();
        elementDto.setLastModified(actualDate);

        // TODO : save the element to update using persistence layer

        return elementDto;
    }

    /**
     * Delete the element with ID from the database
     *
     * @param id : the ID of the element to delete from the database
     * @throws FunctionalException : if there is no element with ID to delete in the database
     */
    @Override
    public void delete(String id) throws FunctionalException {
        // TODO : delete the element by ID using the persistence layer
    }
}
