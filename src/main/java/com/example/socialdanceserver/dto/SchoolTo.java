package com.example.socialdanceserver.dto;

import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.enums.Dance;

import java.util.List;

public class SchoolTo {

    private Integer id;
    private String name;
    private String description;
    private EntityInfo entityInfo;
    private String rating;
    private int ownerId;
    private List<Dance> dances;

    public SchoolTo() {
    }

    public SchoolTo(Integer id, String name, String description, EntityInfo entityInfo, String rating, int ownerId, List<Dance> dances) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.entityInfo = entityInfo;
        this.rating = rating;
        this.ownerId = ownerId;
        this.dances = dances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public void setDances(List<Dance> dances) {
        this.dances = dances;
    }
}
