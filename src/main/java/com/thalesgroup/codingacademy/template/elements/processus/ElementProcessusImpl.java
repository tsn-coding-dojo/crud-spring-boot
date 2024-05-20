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
import com.thalesgroup.codingacademy.template.elements.domain.api.services.ElementService;
import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * The business part to manage elements in Template application
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
@Service
public class ElementProcessusImpl implements ElementProcessus {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElementProcessusImpl.class);

    private final ElementService elementService;
    /**
     * Constructor to initialize the service layer with Autowired
     *
     * @param elementService : the ElementService to initialize
     */
    @Autowired
    public ElementProcessusImpl(ElementService elementService) {
        this.elementService = elementService;
    }

    /**
     * Get all the elements from the database
     *
     * @return {@code List<ElementDto>} : the list of all elements from the database
     */
    @Override
    public List<ElementDto> getAll() {
        LOGGER.debug("Get all elements");
        return elementService.getAll();
    }

    /**
     * Get the element with ID from the database
     *
     * @param id : the ID of the element to get
     * @return ElementDto : the element with ID from the database
     * @throws FunctionalException : if element with id not found
     */
    @Override
    public ElementDto get(String id) throws FunctionalException {
        LOGGER.debug("Get element {}", id);
        return elementService.get(id);
    }

    /**
     * Create (add) the element in the database
     *
     * @param elementDto : the element to create in the database
     * @return ElementDto : the element which has been created and added in the database
     */
    @Override
    public ElementDto create(ElementDto elementDto) {
        LOGGER.debug("Create element {} with description : {}", elementDto.getName(), elementDto.getDescription());
        UUID uuid = UUID.randomUUID();
        elementDto.setId(uuid.toString());
        Instant actualDate = Instant.now();
        elementDto.setCreatedTimeStamp(actualDate);
        elementDto.setLastModified(actualDate);
        elementService.save(elementDto);
        return elementDto;
    }

    /**
     * Update the element in the database
     *
     * @param elementDto : the element to update in the database
     * @return ElementDto : the element which has been updated in the database
     * @throws FunctionalException : if element with id not found
     */
    @Override
    public ElementDto update(ElementDto elementDto) throws FunctionalException {
        LOGGER.debug("Update element {}", elementDto.getId());

        ElementDto oldElementDto = elementService.get(elementDto.getId());
        Instant createdTimeStamp = oldElementDto.getCreatedTimeStamp();

        // Keep the existing creation date
        elementDto.setCreatedTimeStamp(createdTimeStamp);

        Instant actualDate = Instant.now();
        elementDto.setLastModified(actualDate);

        elementService.save(elementDto);
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
        LOGGER.debug("Delete element {}", id);
        elementService.delete(id);
    }
}
