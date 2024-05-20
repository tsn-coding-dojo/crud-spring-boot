package com.thalesgroup.codingacademy.template.elements.services;

import com.thalesgroup.codingacademy.template.elements.domain.exceptions.FunctionalException;
import com.thalesgroup.codingacademy.template.elements.services.dao.IElementDAO;
import com.thalesgroup.codingacademy.template.elements.services.entities.ElementEntity;
import com.thalesgroup.codingacademy.template.elements.domain.api.services.ElementService;
import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IElementDAO elementDAO;

    /**
     * Constructor to initialize the DAO with Autowired
     */
    @Autowired
    public ElementServiceImpl(IElementDAO elementDAO) {
        this.elementDAO = elementDAO;
    }

    /**
     * Get all the elements from the database
     *
     * @return : the list of all elements from the database
     */
    @Override
    public List<ElementDto> getAll() {
        List<ElementDto> elementDtoList = new ArrayList<>();

        List<ElementEntity> elementEntitiesList = elementDAO.findAll();
        if (elementEntitiesList != null) {
            for (ElementEntity elementEntity : elementEntitiesList) {
                ElementDto elementDto = elementEntity.toElementDto();
                elementDtoList.add(elementDto);
            }
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

        try {
            ElementEntity elementEntity = elementDAO.getReferenceById(id);
            if (elementEntity != null) {
                elementDto = elementEntity.toElementDto();
            } else {
                String errorMessage = "The element with ID " + id + " doesn't exist.";
                FunctionalException exception = new FunctionalException(errorMessage);
                LOGGER.error(errorMessage, exception);
                throw exception;
            }
        } catch (EntityNotFoundException exception) {
            String errorMessage = "The element with ID " + id + " doesn't exist.";
            LOGGER.error(errorMessage, exception);
            throw new FunctionalException(errorMessage, exception);
        }

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
        return elementDAO.existsById(id);
    }

    /**
     * Save (add or update) the element in the database
     *
     * @param elementDto : the element to save in the database
     */
    @Override
    public void save(ElementDto elementDto) {
        ElementEntity elementEntity = new ElementEntity(elementDto);
        elementDAO.save(elementEntity);
    }

    /**
     * Delete the element with the ID from the database
     *
     * @param id : the ID of the element to delete
     * @throws FunctionalException : if there is no element with the ID to delete.
     */
    @Override
    public void delete(String id) throws FunctionalException {
        if (!elementDAO.existsById(id)) {
            String errorMessage = "There is no element with id " + id + ".";
            FunctionalException exception = new FunctionalException(errorMessage);
            LOGGER.error(errorMessage, exception);
            throw exception;
        }

        elementDAO.deleteById(id);
    }
}
