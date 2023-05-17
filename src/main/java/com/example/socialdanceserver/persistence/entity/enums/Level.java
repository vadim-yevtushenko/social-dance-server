package com.example.socialdanceserver.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {

    JUNIOR("Junior"),

    MIDDLE("Middle"),

    ADVANCED("Advanced"),

    TEACHER("Teacher");

    private String name;

}
