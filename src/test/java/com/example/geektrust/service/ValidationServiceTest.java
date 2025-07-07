package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Train;

class ValidationServiceTest {
    private final ValidationService validator = new ValidationService();

    @Test
    void validateTrainTokensShouldThrowWhenFirstIsNotEngine() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateTrainTokens(Arrays.asList("TRAIN_A", "NDL")));
    }

    @Test
    void ensureBothTrainsPresentShouldThrowWhenMissingTrain() {
        List<Train> trains = Collections.singletonList(Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE")));
        assertThrows(IllegalArgumentException.class, () -> validator.ensureBothTrainsPresent(trains));
    }
}
