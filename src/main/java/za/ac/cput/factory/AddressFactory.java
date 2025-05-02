package za.ac.cput.factory;
/*
AddressFactory.java
Factory class for Address
Author: Samkelisiwe Sithabile Khanyile (222843152)
Date: 02/05/2025
*/
import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;


public class AddressFactory {

    public static Address createAddress(
            int id,
            int customerId,
            String street,
            String city,
            String province,
            String postalCode,
            String country) {

        if (Helper.isNullOrEmpty(street))
            throw new IllegalArgumentException("Please provide a street");
        if (Helper.isNullOrEmpty(city))
            throw new IllegalArgumentException("Please provide a city");
        if (Helper.isNullOrEmpty(province))
            throw new IllegalArgumentException("Please provide a province");
        if (Helper.isNullOrEmpty(postalCode))
            throw new IllegalArgumentException("Please provide a postal code");
        if (Helper.isNullOrEmpty(country))
            throw new IllegalArgumentException("Please provide a country");

        return new Address.Builder()
                .setId(id)
                .setCustomerId(customerId)
                .setStreet(street)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
