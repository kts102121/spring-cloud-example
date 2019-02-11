package org.ron.inventoryservice.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String AUTH_TOKEN = "Authorization";

    private static final ThreadLocal<String> authToken = new ThreadLocal<>();

    public static String getAuthToken() { return authToken.get(); }
    public static void setAuthToken(String token) {authToken.set(token);}
}
