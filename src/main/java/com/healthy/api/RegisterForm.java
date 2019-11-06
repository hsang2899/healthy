package com.healthy.api;

import lombok.Getter;
import lombok.Setter;

public class RegisterForm {
    @Getter
    @Setter
    public static class Request{
        private String accountId;
        private String email;
        private String userName;
        private String password;
        private String status;
    }
}
