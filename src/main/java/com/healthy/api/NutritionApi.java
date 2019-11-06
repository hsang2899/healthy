package com.healthy.api;

import lombok.Getter;
import lombok.Setter;

public class NutritionApi {
    @Getter
    @Setter
    public static class Request{
        private double weight;
        private Integer month;
        private Integer activityLevel;
        private Integer type;
    }

    @Getter
    @Setter
    public static class Response {
        private double calories;
        private double proteins;
        private double carbon;
        private double fat;
        private double weightStart;
        private double weight;
        private double weightFinish;
        private Integer month;
        private Integer type;
        private Integer activityLevel;
    };
}
