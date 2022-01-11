package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.TypeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends AbstractBaseEntity{

    private int ownerId;
    private LocalDateTime dateEvent;
    private LocalDateTime dateFinishEvent;
    private LocalDateTime datePublication;

    public Event() {
        this.setTypeEntity(TypeEntity.EVENT);
    }

    public Event(String image, String name, String description, EntityInfo entityInfo, int ownerId, LocalDateTime dateEvent, LocalDateTime dateFinishEvent, LocalDateTime datePublication) {
        super(image, name, description, entityInfo);
        this.ownerId = ownerId;
        this.dateEvent = dateEvent;
        this.dateFinishEvent = dateFinishEvent;
        this.datePublication = datePublication;
        this.setTypeEntity(TypeEntity.EVENT);
    }

    public Event(Integer id, String image, String name, String description, int ownerId, LocalDateTime dateEvent, LocalDateTime dateFinishEvent, LocalDateTime datePublication) {
        super(id, image, name, description);
        this.ownerId = ownerId;
        this.dateEvent = dateEvent;
        this.dateFinishEvent = dateFinishEvent;
        this.datePublication = datePublication;
        this.setTypeEntity(TypeEntity.EVENT);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public LocalDateTime getDateFinishEvent() {
        return dateFinishEvent;
    }

    public void setDateFinishEvent(LocalDateTime dateFinishEvent) {
        this.dateFinishEvent = dateFinishEvent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "ownerId=" + ownerId +
                ", dateEvent=" + dateEvent +
                ", dateFinishEvent=" + dateFinishEvent +
                ", datePublication=" + datePublication +
                "} " + super.toString();
    }
}
