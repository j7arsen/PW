

package com.j7arsen.pw.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arsen on 28.02.2018.
 */

public class ValidFields {

    private static final int PASSWORD_LENGTH = 6;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isNotEmptyField(String text) {
        return !text.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (password.trim().length() < PASSWORD_LENGTH) {
            return false;
        } else {
            return true;
        }
    }

}
