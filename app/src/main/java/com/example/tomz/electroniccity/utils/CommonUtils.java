package com.example.tomz.electroniccity.utils;

import android.util.Patterns;

public class CommonUtils {

    private CommonUtils(){
        // This utility class is not publicly instantiable
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
