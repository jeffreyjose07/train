package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;

import java.util.List;

/**
 * Performs validation on trains and input tokens.
 */
public class ValidationService {

    public void validateTrainTokens(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Input tokens cannot be null or empty");
        }
        if (tokens.size() < 2 || !TrainConstants.ENGINE.equals(tokens.get(1))) {
            throw new IllegalArgumentException("Invalid format: first bogie must be ENGINE");
        }
    }

    public void ensureBothTrainsPresent(List<Train> trains) {
        boolean hasA = trains.stream().anyMatch(t -> TrainConstants.TRAIN_A.equals(t.getTrainId()));
        boolean hasB = trains.stream().anyMatch(t -> TrainConstants.TRAIN_B.equals(t.getTrainId()));
        if (!hasA || !hasB) {
            throw new IllegalArgumentException("Input must contain both TRAIN_A and TRAIN_B");
        }
    }
}
