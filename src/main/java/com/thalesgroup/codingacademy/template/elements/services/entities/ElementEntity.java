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
package com.thalesgroup.codingacademy.template.elements.services.entities;

import com.thalesgroup.codingacademy.template.elements.domain.dto.ElementDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * The element representation in the database
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "element")
public class ElementEntity {
    @Id
    private String id;
    @Column(name = "created_time")
    private Instant createdTimeStamp; // TODO : column name : created_time
    @Column(name = "last_modified")
    private Instant lastModified; // TODO : column name : last_modified
    private String name;
    private String description;

    /**
     * Constructor to initialize the entity from the corresponding DTO
     *
     * @param elementDto : the element DTO to initialize the entity
     */
    public ElementEntity(ElementDto elementDto) {
        this.id = elementDto.getId();
        this.createdTimeStamp = elementDto.getCreatedTimeStamp();
        this.lastModified = elementDto.getLastModified();
        this.name = elementDto.getName();
        this.description = elementDto.getDescription();
    }

    /**
     * Generate the element DTO from this entity
     *
     * @return ElementDto : the element DTO generated from this entity
     */
    public ElementDto toElementDto() {
        ElementDto elementDto = new ElementDto();

        elementDto.setId(id);
        elementDto.setCreatedTimeStamp(createdTimeStamp);
        elementDto.setLastModified(lastModified);
        elementDto.setName(name);
        elementDto.setDescription(description);

        return elementDto;
    }
}
