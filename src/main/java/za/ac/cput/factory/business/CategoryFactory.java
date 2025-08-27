package za.ac.cput.factory.business;
/*
CategoryFactory.java
Factory class for Category
Author: Samkelisiwe Sithabile Khanyile (222843152)
Date: 02/05/2025
*/
import za.ac.cput.domain.business.Category;
import za.ac.cput.util.Helper;


public class CategoryFactory {

    public static Category createCategory(
            int id,
            String name,
            String description) {

        if (Helper.isNullOrEmpty(name))
            throw new IllegalArgumentException("Please provide a name");
        if (Helper.isNullOrEmpty(description))
            throw new IllegalArgumentException("Please provide a description");

        return new Category.Builder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .build();
    }
}

