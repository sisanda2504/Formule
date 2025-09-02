package za.ac.cput.domain.business;

/*
Brands.java
Brand enumeration class
Author: Samkelisiwe Sithabile Khanyile (222843152)
Date: 18/04/2025
*/

public enum Brands {
    INNISFREE("Innisfree"),
    MISSHA("Missha"),
    LANEIGE("Laneige"),
    SK_II("SK-II"),
    SHISEIDO("Shiseido"),
    HADA_LABO("Hada Labo"),
    SOME_BY_MI("Some By Mi"),
    COSRX("CORSRX"),
    THE_FACE_SHOP("The Face Shop");

    private final String displayName;
    private Brands(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
