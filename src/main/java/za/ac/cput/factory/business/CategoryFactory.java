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
            String name,
            String description) {

        if (Helper.isNullOrEmpty(name))
        {return null;}

        if (Helper.isNullOrEmpty(description))
        {return null;}

        return new Category.Builder()
                .setName(name)
                .setDescription(description)
                .build();
    }
}

