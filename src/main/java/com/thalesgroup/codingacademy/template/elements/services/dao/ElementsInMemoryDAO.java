package com.thalesgroup.codingacademy.template.elements.services.dao;

import com.thalesgroup.codingacademy.template.elements.services.entities.ElementEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO to manage persistence of elements in memory as a singleton and a HashMap
 */
public final class ElementsInMemoryDAO {
    private static volatile ElementsInMemoryDAO instance = null;
    private final Map<String, ElementEntity> elements;

    /**
     * It's a singleton. Couldn't instanciate this class directly but use getInstance instead
     */
    private ElementsInMemoryDAO() {
        elements = new HashMap<>();
    }

    /**
     * To get the instance of this singleton class
     *
     * @return ElementsInMemoryDAO : the singleton class
     */
    public static ElementsInMemoryDAO getInstance() {
        if (instance == null) {
            synchronized(ElementsInMemoryDAO.class) {
                if (instance == null) {
                    instance = new ElementsInMemoryDAO();
                }
            }
        }

        return instance;
    }

    /**
     * Get all elements
     *
     * @return {@code List<ElementEntity>} : all the elements
     */
    public List<ElementEntity> getAll() {
        return elements.values().stream().toList();
    }

    /**
     * Get an element by its ID
     *
     * @param id : the ID to get the element
     * @return ElementEntity : the element with the ID
     */
    public ElementEntity getById(String id) {
        return elements.get(id);
    }

    /**
     * Check if the element exists by its ID
     *
     * @param id : the ID of the element to check
     * @return boolean : true if the element with ID exist, false if not
     */
    public boolean existsById(String id) {
        return elements.containsKey(id);
    }

    /**
     * Save the element
     *
     * @param elementEntity : the element to save
     */
    public void save(ElementEntity elementEntity) {
        elements.put(elementEntity.getId(), elementEntity);
    }

    /**
     * Delete the element by its ID
     *
     * @param id : the ID of the element to delete
     */
    public void deleteById(String id) {
        elements.remove(id);
    }
}
