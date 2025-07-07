package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.model.Journey;
import com.example.geektrust.model.TrainArrival;
import com.example.geektrust.model.TrainDeparture;
import com.example.geektrust.util.TrainConstants;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private final ValidationService validationService = new ValidationService();
    private final PrinterService printerService = new PrinterService();

    public Train createTrain(List<String> tokens) {
        validationService.validateTrainTokens(tokens);
        String trainId = tokens.get(0);
        List<String> stationCodes = new ArrayList<>(tokens.subList(1, tokens.size()));

        return Train.createFromTokens(trainId, stationCodes);
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

        Journey journey = new Journey(trainA, trainB);
        
        TrainArrival arrival = journey.processArrival();
        arrival.getArrivedTrains().forEach(printerService::printArrival);

        TrainDeparture departure = journey.processDeparture();
        if (departure.isJourneyEnded()) {
            printerService.printJourneyEnded();
        } else {
            printerService.printDeparture(departure.getTrain());
        }
    }
}
