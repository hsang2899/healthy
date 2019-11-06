package com.healthy.config;

public class BMR_TDEE {
    public static double getBRMBOY_1830 (double weight) {
        System.out.println("getBRMBOY_1830 WEIGHT: " + weight);
        return (15.3 * weight + 679);
    }
    public static double getBMRBOY_3060 (double weight) {
        System.out.println("getBMRBOY_3060 WEIGHT: " + weight);
        return (11.6 * weight + 487);
    }

    public static double getBMRGIRL_1830 (double weight) {
        System.out.println("getBRMGIRL_1830 WEIGHT: " + weight);
        return (14.7 * weight + 496);
    }
    public static double getBMRGIRL_3060 (double weight) {
        System.out.println("getBMRGIRL_3060 WEIGHT: " + weight);
        return (8.7 * weight + 829);
    }

    public static double activity_level1BOY = 1.55;
    public static double activity_level1girl = 1.56;
    public static double activity_level2BOY = 1.78;
    public static double activity_level2GIRL = 1.61;
    public static double activity_level3BOY = 2.1;
    public static double activity_level3GIRL = 1.82;
    public static double activity_level4BOY = 2.4;
    public static double activity_level4GIRL = 2.2;

    public static double getTDEE (double BMR, double activity_level) {
        System.out.println("getTDEE BMR: " + BMR + ", activity_level: " + activity_level);
        return (BMR * activity_level);
    }

    public static double macroPercentProtein = 0.45;
    public static double macroPercentCarb = 0.30;
    public static double macroPercentFat = 0.25;
}

