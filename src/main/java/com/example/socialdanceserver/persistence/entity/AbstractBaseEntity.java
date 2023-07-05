package com.example.socialdanceserver.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", nullable = false, unique = true, updatable = false, insertable = false)
    private UUID id;

    @Column(name = "created", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private ZonedDateTime created;

    @Column(name = "updated", columnDefinition = "TIMESTAMP", nullable = false)
    private ZonedDateTime updated;

    @PrePersist
    public void onPrePersist() {
        ZonedDateTime now = ZonedDateTime.now();
        created = now;
        updated = now;
    }

    @PreUpdate
    public void onPreUpdate() {
        updated = ZonedDateTime.now();
    }

}
