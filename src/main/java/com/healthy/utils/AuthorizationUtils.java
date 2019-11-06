package com.healthy.utils;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtils {

    public static String getEmailByToken(HttpServletRequest req) {
        String rsEmail = req.getAttribute("rsEmail").toString();
        return rsEmail;
    }
}
