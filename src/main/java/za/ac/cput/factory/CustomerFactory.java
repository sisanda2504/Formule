package za.ac.cput.factory;
/*
CustomerFactory.java
Customer model class
Author: Agnes Mabusela (230020690)
Date: 23/04/2025
 */

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {

    public static Customer createCustomer(int id, String firstName, String lastName, String phoneNumber, String emailAddress, String password, int addressId){

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

            return new Customer.Builder().setId(id)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhoneNumber(phoneNumber)
                    .setEmailAddress(emailAddress)
                    .setPassword(password)
                    .setAddressId(addressId)
                    .build();
    }
}
