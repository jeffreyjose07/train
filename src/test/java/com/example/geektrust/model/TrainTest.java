package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TrainTest {
    @Test
    void toStringShouldReturnSpaceSeparatedBogies() {
        Train train = new Train("TRAIN_A", Arrays.asList("ENGINE", "NDL", "NJP"));
        assertEquals("ENGINE NDL NJP", train.toString());
    }
}
