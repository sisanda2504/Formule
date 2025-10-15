package za.ac.cput.factory.users;

import za.ac.cput.domain.users.Admin;
import za.ac.cput.util.Helper;

public class AdminFactory {

    public static Admin createAdmin(String firstName, String lastName, String emailAddress, String password) {

        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        if (!Helper.isValidEmail(emailAddress)) {
            return null;
        }

        if (!Helper.isValidPassword(password)) {
            return null;
        }

        return new Admin.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmailAddress(emailAddress)
                .setPassword(password)
                .build(); 
    }
}