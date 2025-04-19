package za.ac.cput.domain;
/*
Brand.java
Brand enum
Author: Samkelisiwe Sithabile (222843152)
Date: 18/04/2025
*/

public enum Brand {
    INNISFREE,
    MISSHA,
    HADA_LABO,
    ETUDE_HOUSE,
    COSRX,
    LANEIGE,
    SKIN1004;

    @Override
    public String toString() {

        String name = name().replace("_", " ");
        String[] words = name.split(" ");
        StringBuilder formatted = new StringBuilder();
        for (String word : words) {
            formatted.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return formatted.toString().trim();
    }
}
