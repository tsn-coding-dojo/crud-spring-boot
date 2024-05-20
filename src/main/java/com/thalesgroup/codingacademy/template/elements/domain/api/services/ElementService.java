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
package com.thalesgroup.codingacademy.template.elements.domain.api.services;

import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;

import java.util.List;

/**
 * Manage elements in the database
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
public interface ElementService {
    /**
     * Get all the elements from the database
     *
     * @return {@code List<ElementDto>} : the list of all elements from the database
     */
    List<ElementDto> getAll();

    /**
     * Get the element with ID from the database
     *
     * @param id : the id of the element to get
     * @return ElementDto : the element with ID from the database
     */
    ElementDto get(String id);

    /**
     * Check if there is already an element with ID in the database
     *
     * @param id : the ID of the element to check
     * @return boolean : true if there is already an element in the database, false if not
     */
    boolean exists(String id);

    /**
     * Save (add or update) the element in the database
     *
     * @param elementDto : the element to save in the database
     */
    void save(ElementDto elementDto);

    /**
     * Delete the element with ID from the database
     *
     * @param id : the id of the element to delete
     * @throws FunctionalException : if there is no element with the ID to delete.
     */
    void delete(String id) throws FunctionalException;
}
