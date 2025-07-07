package com.example.geektrust.util;

public final class MaintainabilityConstants {
    
    // Distance constants from Hyderabad station (in kilometers)
    public static final int NGP_DISTANCE_FROM_HYB = 400;
    public static final int ITJ_DISTANCE_FROM_HYB = 700;
    public static final int BPL_DISTANCE_FROM_HYB = 800;
    public static final int AGA_DISTANCE_FROM_HYB = 1300;
    public static final int NDL_DISTANCE_FROM_HYB = 1500;
    public static final int PTA_DISTANCE_FROM_HYB = 1800;
    public static final int NJP_DISTANCE_FROM_HYB = 2200;
    public static final int GHY_DISTANCE_FROM_HYB = 2700;
    
    // Train composition constants
    public static final int ENGINES_IN_MERGED_TRAIN = 2;
    public static final int MINIMUM_BOGIES_FOR_JOURNEY = 2;
    
    // Validation constants
    public static final int MINIMUM_TRAIN_TOKENS = 2;
    public static final int ENGINE_TOKEN_INDEX = 1;
    public static final int TRAIN_ID_TOKEN_INDEX = 0;
    
    private MaintainabilityConstants() {
        // Utility class
    }
}