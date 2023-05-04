package com.example.socialdanceserver.model.enums;

import lombok.Getter;

@Getter
public enum Dance {

    SALSA(1, "Salsa"),

    BACHATA(2, "Bachata"),

    KIZOMBA(3, "Kizomba"),

    ZOUK(4, "Zouk"),

    MERENGE(5, "Merenge"),

    REGGAETON(6, "Reggaeton"),

    TANGO(7, "Argentine Tango");

    private int id;

    private String name;

    Dance(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
