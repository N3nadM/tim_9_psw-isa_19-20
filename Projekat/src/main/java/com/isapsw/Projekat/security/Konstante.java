package com.isapsw.Projekat.security;

public class Konstante {
    public static final String USERS_URL = "/api/users/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET ="TajnaPoruka";
    public static final String TOKEN_BEARER_PREFIX= "Bearer ";
    public static final String HEADER_BEARER_TOKEN = "Authorization";
    public static final long EXPIRE = 600_000; //30 seconds
}
