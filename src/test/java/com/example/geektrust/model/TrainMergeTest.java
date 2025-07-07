package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TrainMergeTest {

    @Test
    void trainArrivalShouldCreateFromTwoTrains() {
        Train a = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "GHY"));
        Train b = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE", "PTA", "AGA"));
        
        TrainArrival arrival = TrainArrival.from(a, b);
        assertEquals("ENGINE NDL GHY", arrival.getTrainA().toString());
        assertEquals("ENGINE PTA AGA", arrival.getTrainB().toString());
    }

    @Test
    void trainDepartureShouldMergeAndSortBogies() {
        Train a = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "GHY"));
        Train b = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE", "PTA", "AGA"));
        
        TrainArrival arrival = TrainArrival.from(a, b);
        TrainDeparture departure = TrainDeparture.from(arrival);
        
        assertFalse(departure.isJourneyEnded());
        assertEquals("ENGINE ENGINE GHY PTA NDL AGA", departure.getTrain().toString());
    }

    @Test
    void trainDepartureShouldFilterHYB() {
        Train a = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "HYB"));
        Train b = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE", "PTA", "AGA"));
        
        TrainArrival arrival = TrainArrival.from(a, b);
        TrainDeparture departure = TrainDeparture.from(arrival);
        
        assertEquals("ENGINE ENGINE PTA NDL AGA", departure.getTrain().toString()); // HYB filtered out
    }

    @Test
    void trainDepartureShouldDetectJourneyEnded() {
        Train a = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE"));
        Train b = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE"));
        
        TrainArrival arrival = TrainArrival.from(a, b);
        TrainDeparture departure = TrainDeparture.from(arrival);
        
        assertTrue(departure.isJourneyEnded());
        assertFalse(departure.getMergedTrain().isPresent());
    }
}
