package com.thalesgroup.codingacademy.template.elements.services;

import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;
import com.thalesgroup.codingacademy.template.elements.services.entities.ElementEntity;
import com.thalesgroup.codingacademy.template.elements.domain.api.services.ElementService;
import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage elements in the database
 *
 * @since 0.0.1
 * @author Stéphane VERNAT
 */
@Service
public class ElementServiceImpl implements ElementService {
    /**
     * The logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ElementServiceImpl.class);

    /**
     * Constructor to initialize the DAO with Autowired
     *
     * @param elementDAO : the Element DAO to initialize
     */
    public ElementServiceImpl() {

    }

    /**
     * Get all the elements from the database
     *
     * @return : the list of all elements from the database
     */
    @Override
    public List<ElementDto> getAll() {
        List<ElementDto> elementDtoList;

        List<ElementEntity> elementEntitiesList = null; // TODO : use DAO to get all element entities
        if (elementEntitiesList != null) {
            elementDtoList = new ArrayList<>();
            for (ElementEntity elementEntity : elementEntitiesList) {
                ElementDto elementDto = elementEntity.toElementDto();
                elementDtoList.add(elementDto);
                LOGGER.info("elementDto = {}", elementDto);
            }
        } else {
            elementDtoList = null;
        }

        return elementDtoList;
    }

    /**
     * Get the element with the ID from the database
     *
     * @param id : the ID of the element to get
     * @return ElementDto : the element with the ID from the database
     * @throws FunctionalException : if element with id not found
     */
    @Override
    public ElementDto get(String id) throws FunctionalException {
        ElementDto elementDto;

        //try {
            ElementEntity elementEntity = null; // TODO : use DAO to get the element by ID
            if (elementEntity != null) {
                elementDto = elementEntity.toElementDto();
            } else {
                String errorMessage = "The element with ID " + id + " doesn't exist.";
                FunctionalException exception = new FunctionalException(errorMessage);
                LOGGER.error(errorMessage, exception);
                throw exception;
            }
//        } catch (EntityNotFoundException exception) {
//            String errorMessage = "The element with ID " + id + " doesn't exist.";
//            LOGGER.warn(errorMessage, exception);
//            elementDto = null;
//        }

        return elementDto;
    }

    /**
     * Check if there is already an element with the ID in the database
     *
     * @param id : the ID of the element to check
     * @return boolean : true if there is already an element with the ID in the database, false if not
     */
    @Override
    public boolean exists(String id) {
        return true; // TODO : use DAO to check if element with ID exist
    }

    /**
     * Save (add or update) the element in the database
     *
     * @param elementDto : the element to save in the database
     */
    @Override
    public void save(ElementDto elementDto) {
        // TODO : use DAO to add or update the element
    }

    /**
     * Delete the element with the ID from the database
     *
     * @param id : the ID of the element to delete
     * @throws FunctionalException : if there is no element with the ID to delete.
     */
    @Override
    public void delete(String id) throws FunctionalException {
        boolean exists = false;

        if (!exists) { // TODO : check if element with ID exist before delete it
            String errorMessage = "There is no element with id " + id + ".";
            throw new FunctionalException(errorMessage);
        }
        // TODO : use DAO to delete the element
    }
}
