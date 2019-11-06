package com.healthy.api;

import lombok.Getter;
import lombok.Setter;

public class FoodApi {
    @Getter
    @Setter
    public static class Request{
        private String foodName;
        private String description;
        private String unit;
        private Float calories;
        private Float proteins;
        private Float carbs;
        private Float fat;
        private Integer quantity;
    }

    @Getter
    @Setter
    public static class Response {
        private String foodName;
        private Boolean status;
        private String message;
    };
}
