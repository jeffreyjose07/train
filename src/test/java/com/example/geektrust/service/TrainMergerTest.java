package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;

class TrainMergerTest {
    private final TrainMerger merger = new TrainMerger();

    @Test
    void mergeShouldAddTwoEnginesAndSortBogies() {
        Train a = new Train(TrainConstants.TRAIN_A, Arrays.asList("ENGINE", "NDL", "GHY"));
        Train b = new Train(TrainConstants.TRAIN_B, Arrays.asList("ENGINE", "PTA", "AGA"));
        Train result = merger.merge(a, b);
        assertEquals("ENGINE ENGINE GHY PTA NDL AGA", result.toString());
    }
}
