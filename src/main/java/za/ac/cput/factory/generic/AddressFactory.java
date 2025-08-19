package za.ac.cput.factory.generic;
/*
AddressFactory.java
Factory class for Address
Author: Samkelisiwe Sithabile Khanyile (222843152)
Date: 02/05/2025
*/
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.util.Helper;


public class AddressFactory {

    public static Address createAddress(
            Customer customer,
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

        if(customer == null)
            throw new IllegalArgumentException("Please provide a valid customer");
        return new Address.Builder()
                .setCustomer(customer)
                .setStreet(street)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
