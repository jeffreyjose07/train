package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;
import com.example.geektrust.util.MaintainabilityConstants;

import java.util.List;

public class ValidationService {

    public void validateTrainTokens(List<String> tokens) {
        validateTokensNotNullOrEmpty(tokens);
        validateMinimumTokenCount(tokens);
        validateEngineToken(tokens);
    }
    
    private void validateTokensNotNullOrEmpty(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Input tokens cannot be null or empty");
        }
    }
    
    private void validateMinimumTokenCount(List<String> tokens) {
        if (tokens.size() < MaintainabilityConstants.MINIMUM_TRAIN_TOKENS) {
            throw new IllegalArgumentException("Invalid format: minimum " + 
                MaintainabilityConstants.MINIMUM_TRAIN_TOKENS + " tokens required");
        }
    }
    
    private void validateEngineToken(List<String> tokens) {
        String engineToken = tokens.get(MaintainabilityConstants.ENGINE_TOKEN_INDEX);
        if (!TrainConstants.ENGINE.equals(engineToken)) {
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
