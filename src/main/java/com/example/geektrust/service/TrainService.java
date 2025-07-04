package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles creation and processing of trains.
 */
public class TrainService {
    private final RouteService routeService = new RouteService();
    private final TrainMerger trainMerger = new TrainMerger();

    public Train createTrain(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Input tokens cannot be null or empty");
        }
        if (tokens.size() < 2 || !TrainConstants.ENGINE.equals(tokens.get(1))) {
            throw new IllegalArgumentException("Invalid format: first bogie must be ENGINE");
        }
        String trainId = tokens.get(0);
        List<String> bogies = new ArrayList<>(tokens.subList(1, tokens.size()));

        return new Train(trainId, bogies);
    }

    private Train filterArrivalTrain(Train train) {
        List<String> filtered = routeService.filterValidBogies(train.getBogies());
        return new Train(train.getTrainId(), filtered);
    }

    private Train findTrain(List<Train> trains, String id) {
        return trains.stream()
                .filter(t -> id.equals(t.getTrainId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing train " + id));
    }

    public void validateTrains(List<Train> trains) {
        boolean hasA = trains.stream().anyMatch(t -> TrainConstants.TRAIN_A.equals(t.getTrainId()));
        boolean hasB = trains.stream().anyMatch(t -> TrainConstants.TRAIN_B.equals(t.getTrainId()));
        if (!hasA || !hasB) {
            throw new IllegalArgumentException("Input must contain both TRAIN_A and TRAIN_B");
        }
    }

    public void generateOutput(List<Train> trains) {
        Train trainA = findTrain(trains, TrainConstants.TRAIN_A);
        Train trainB = findTrain(trains, TrainConstants.TRAIN_B);

        Train arrivalA = filterArrivalTrain(trainA);
        Train arrivalB = filterArrivalTrain(trainB);

        System.out.println("ARRIVAL " + arrivalA.getTrainId() + " " + arrivalA);
        System.out.println("ARRIVAL " + arrivalB.getTrainId() + " " + arrivalB);

        Train departure = trainMerger.merge(arrivalA, arrivalB);
        if (departure.getBogies().size() <= 2) {
            System.out.println("JOURNEY_ENDED");
        } else {
            System.out.println("DEPARTURE " + departure.getTrainId() + " " + departure);
        }
    }
}
