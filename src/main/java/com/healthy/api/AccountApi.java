package com.healthy.api;

import lombok.Getter;
import lombok.Setter;

public class AccountApi {
    @Getter
    @Setter
    public static class Response{
        private String email;
        private String userName;
        private String role;
        private long createdAt;
        private long updatedAt;
    }
}
