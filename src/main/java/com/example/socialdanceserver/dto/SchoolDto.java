package com.example.socialdanceserver.dto;

import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.enums.Dance;

import java.util.List;

public class SchoolDto {

    private Integer id;
    private String image;
    private String name;
    private String description;
    private EntityInfo entityInfo;
    private AverageRatingDto rating;
    private int ownerId;
    private List<Dance> dances;

    public SchoolDto() {
    }

    public SchoolDto(Integer id, String image, String name, String description, EntityInfo entityInfo, AverageRatingDto rating, int ownerId, List<Dance> dances) {
        this.id = id;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public AverageRatingDto getRating() {
        return rating;
    }

    public void setRating(AverageRatingDto rating) {
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
