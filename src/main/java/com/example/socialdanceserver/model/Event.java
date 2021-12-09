package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.TypeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends School{

    private LocalDateTime dateEvent;
    private LocalDateTime dateFinishEvent;
    private LocalDateTime datePublication;

    public Event() {
        this.setTypeEntity(TypeEntity.EVENT);
    }

    public Event(String name, String description, EntityInfo entityInfo, LocalDateTime dateEvent,
                 LocalDateTime dateFinishEvent, LocalDateTime datePublication) {
        super(name, description, entityInfo);
        this.dateEvent = dateEvent;
        this.dateFinishEvent = dateFinishEvent;
        this.datePublication = datePublication;
        this.setTypeEntity(TypeEntity.EVENT);
    }

    public Event(Integer id, String name, String description, LocalDateTime dateEvent,
                 LocalDateTime dateFinishEvent, LocalDateTime datePublication) {
        super(id, name, description);
        this.dateEvent = dateEvent;
        this.dateFinishEvent = dateFinishEvent;
        this.datePublication = datePublication;
        this.setTypeEntity(TypeEntity.EVENT);
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
                "dateEvent=" + dateEvent +
                ", dateFinishEvent=" + dateFinishEvent +
                ", datePublication=" + datePublication +
                "} " + super.toString();
    }
}
