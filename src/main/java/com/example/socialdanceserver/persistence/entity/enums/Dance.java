package com.example.socialdanceserver.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

}
