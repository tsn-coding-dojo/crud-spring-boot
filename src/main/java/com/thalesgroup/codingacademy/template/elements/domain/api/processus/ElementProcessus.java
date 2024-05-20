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
package com.thalesgroup.codingacademy.template.elements.domain.api.processus;

import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;

import java.util.List;

/**
 * The business part to manage elements in Template application
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
public interface ElementProcessus {
    /**
     * Get all elements from the database
     *
     * @return {@code List<ElementDto>} : list of all the elements from the database
     */
    List<ElementDto> getAll();

    /**
     * Get an element with ID from the database
     *
     * @param id : the ID of the element to get from the database
     * @return ElementDto : the element with ID from the database
     * @throws FunctionalException : if element with id not found
     */
    ElementDto get(String id) throws FunctionalException;

    /**
     * Create (add) the element in the database
     *
     * @param elementDto : the element to create in the database
     * @return ElementDto : the element which has been created and added in the database
     */
    ElementDto create(ElementDto elementDto);

    /**
     * Update the element in the database
     *
     * @param elementDto : the element to update in the database
     * @return ElementDto : the element which has been updated in the database
     * @throws FunctionalException : if element with id not found
     */
    ElementDto update(ElementDto elementDto) throws FunctionalException;

    /**
     * Delete the element with ID from the database
     *
     * @param id : the id of the element to delete from the database
     * @throws FunctionalException : if there is no element with ID to delete or if there is no valid token
     */
    void delete(String id) throws FunctionalException;
}
