package za.ac.cput.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) {
            return false;
        }
        // Validates South African phone numbers like 0725637252 or +27 72 563...
        String regex = "^(\\+27|0)[6-8][0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        // Simple email regex
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (isNullOrEmpty(password)) {
            return false;
        }
        // Validates password: at least one letter, one digit, and at least 8 characters long
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidDate(LocalDateTime dateTime) {
        return dateTime != null;
    }

    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }

    public static boolean isValidTotalAmount(Double totalAmount) {
        return totalAmount != null && totalAmount > 0;
    }

    public static boolean isValidPrice(double totalPrice) {
        return totalPrice > 0;}

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static boolean isValidAmount(Double amount) { return (amount != null && amount > 0);}
}

