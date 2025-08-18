package za.ac.cput.util;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.isEmpty())
            return true;
        return false;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) {
            return false;
        }

        //Checks for e.g 0725637252 or +27 72 563...
        String regex = "^(\\+27|0)[6-8][0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (isNullOrEmpty(password)) {
            return false;
        }
        //Checks if Password has at least one digit, one letter and is more than 8 characters long and No Special Characters.
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidDate(LocalDateTime dateTime) {
        return dateTime != null;
    }

    public static boolean isValidTotalAmount(Double totalAmount) {
        return totalAmount != null && totalAmount > 0;
    }

    public static boolean isValidPrice(double totalPrice) {
        return totalPrice > 0;}

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}

