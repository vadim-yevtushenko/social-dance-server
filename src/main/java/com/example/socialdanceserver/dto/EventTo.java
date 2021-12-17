package com.example.socialdanceserver.dto;

import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.enums.Dance;

import java.util.Date;
import java.util.List;

public class EventTo {

    private Integer id;
    private String name;
    private String description;
    private EntityInfo entityInfo;
    private Date dateEvent;
    private Date dateFinishEvent;
    private Date datePublication;
    private List<Dance> dances;
    private int ownerId;

    public EventTo() {
    }

    public EventTo(Integer id, String name, String description, EntityInfo entityInfo, Date dateEvent, Date dateFinishEvent, Date datePublication, List<Dance> dances, int ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.entityInfo = entityInfo;
        this.dateEvent = dateEvent;
        this.dateFinishEvent = dateFinishEvent;
        this.datePublication = datePublication;
        this.dances = dances;
        this.ownerId = ownerId;
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

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Date getDateFinishEvent() {
        return dateFinishEvent;
    }

    public void setDateFinishEvent(Date dateFinishEvent) {
        this.dateFinishEvent = dateFinishEvent;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public void setDances(List<Dance> dances) {
        this.dances = dances;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "EventTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", entityInfo=" + entityInfo +
                ", dateEvent=" + dateEvent +
                ", dateFinishEvent=" + dateFinishEvent +
                ", datePublication=" + datePublication +
                ", dances=" + dances +
                ", ownerId=" + ownerId +
                '}';
    }
}
