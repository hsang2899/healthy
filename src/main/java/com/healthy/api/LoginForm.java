package com.healthy.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

public class LoginForm {
    @Getter
    @Setter
    public static class Request {
        private String email;
        private String password;
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Response {
        private String token;
        private Boolean information;
        private String role;
        /**
         * Expire time í set to next 100 years for testing
         * */
        private long  expire = OffsetDateTime.now().plusYears(100).toEpochSecond();
    }
}
