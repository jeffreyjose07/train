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
    private final ValidationService validationService = new ValidationService();
    private final PrinterService printerService = new PrinterService();

    public Train createTrain(List<String> tokens) {
        validationService.validateTrainTokens(tokens);
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
        validationService.ensureBothTrainsPresent(trains);
    }

    public void generateOutput(List<Train> trains) {
        Train trainA = findTrain(trains, TrainConstants.TRAIN_A);
        Train trainB = findTrain(trains, TrainConstants.TRAIN_B);

        Train arrivalA = filterArrivalTrain(trainA);
        Train arrivalB = filterArrivalTrain(trainB);

        printerService.printArrival(arrivalA);
        printerService.printArrival(arrivalB);

        Train departure = trainMerger.merge(arrivalA, arrivalB);
        if (departure.getBogies().size() <= 2) {
            printerService.printJourneyEnded();
        } else {
            printerService.printDeparture(departure);
        }
    }
}
