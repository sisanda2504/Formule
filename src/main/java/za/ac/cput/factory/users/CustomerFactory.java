package za.ac.cput.factory.users;
/*
CustomerFactory.java
Customer model class
Author: Agnes Mabusela (230020690)
Date: 23/04/2025
 */

import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {

    public static Customer createCustomer(
            String firstName,
            String lastName,
            String phoneNumber,
            String emailAddress,
            String password,
            Address address){

            if(Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)){
                return null;
            }

            if(!Helper.isValidPhoneNumber(phoneNumber)){
                return null;
            }

            if(!Helper.isValidEmail(emailAddress)){
                return null;
            }

            if(!Helper.isValidPassword(password)){
                return null;
            }

            return new Customer.Builder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhoneNumber(phoneNumber)
                    .setEmailAddress(emailAddress)
                    .setPassword(password)
                    .setAddress(address)
                    .build();
    }
}
