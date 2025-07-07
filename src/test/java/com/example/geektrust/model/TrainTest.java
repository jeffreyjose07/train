package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TrainTest

{
    @Test
    void toStringShouldReturnSpaceSeparatedBogies() {
        Train train = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "NJP"));
        assertEquals("ENGINE NDL NJP", train.toString());
    }

    @Test
    void trainShouldFilterValidBogiesForArrival() {
        Train train = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "HYB", "ABC", "PTA"));
        Train arrival = train.prepareForArrivalAtHyderabad();
        assertEquals("ENGINE NDL HYB PTA", arrival.toString());
    }

    @Test
    void trainShouldFilterValidBogiesForDeparture() {
        Train train = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "HYB", "PTA"));
        Train arrival = train.prepareForArrivalAtHyderabad();
        assertEquals(3, arrival.getBogiesForDeparture().size()); // HYB filtered out
    }

    @Test
    void trainShouldDetectInsufficientBogies() {
        Train train = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "ENGINE"));
        assertTrue(train.hasInsufficientBogies());

        Train train2 = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "PTA"));
        assertFalse(train2.hasInsufficientBogies());
    }

}
