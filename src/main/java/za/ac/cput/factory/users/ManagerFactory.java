package za.ac.cput.factory.users;

/*
ManagerFactory.java
Manager model class
Author: Agnes Mabusela (230020690)
Date: 15/04/2025
*/

import za.ac.cput.domain.users.Manager;
import za.ac.cput.util.Helper;

public class ManagerFactory {

    public static Manager createManager(
            String firstName,
            String lastName,
            String phoneNumber,
            String emailAddress,
            String password) {

        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("First and last name are required");
        }

        if (!Helper.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        if (!Helper.isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (!Helper.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return new Manager.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmailAddress(emailAddress)
                .setPassword(password)
                .setRole("ROLE_MANAGER")
                .build(); 
    }
}