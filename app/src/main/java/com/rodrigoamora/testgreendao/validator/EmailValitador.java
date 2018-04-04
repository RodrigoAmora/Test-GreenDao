package com.rodrigoamora.testgreendao.validator;

public class EmailValitador {

    public static boolean validateEmail(String email) {
        if (!email.contains("@")) {
            return false;
        }
        if (email.contains(" ")) {
            return false;
        }
        if (!email.split("@")[1].contains(".com")) {
            return false;
        }
        return true;
    }

}
