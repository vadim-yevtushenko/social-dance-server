package com.example.socialdanceserver.model.enums;

public enum Dance {
    SALSA("Salsa"),
    BACHATA("Bachata"),
    KIZOMBA("Kizomba"),
    ZOUK("Zouk"),
    MAMBO("Mambo"),
    MERENGE("Merenge"),
    REGGAETON("Reggaeton"),
    TANGO("Argentine Tango");

    private String name;

    Dance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
