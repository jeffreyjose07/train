package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.model.Journey;
import com.example.geektrust.model.TrainArrival;
import com.example.geektrust.model.TrainDeparture;
import com.example.geektrust.util.TrainConstants;
import com.example.geektrust.util.MaintainabilityConstants;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private final ValidationService validationService = new ValidationService();
    private final PrinterService printerService = new PrinterService();

    public Train createTrain(List<String> tokens) {
        validationService.validateTrainTokens(tokens);
        String trainId = extractTrainId(tokens);
        List<String> stationCodes = extractStationCodes(tokens);

        return Train.createFromTokens(trainId, stationCodes);
    }
    
    private String extractTrainId(List<String> tokens) {
        return tokens.get(MaintainabilityConstants.TRAIN_ID_TOKEN_INDEX);
    }
    
    private List<String> extractStationCodes(List<String> tokens) {
        int startIndex = MaintainabilityConstants.TRAIN_ID_TOKEN_INDEX + 1;
        return new ArrayList<>(tokens.subList(startIndex, tokens.size()));
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
        Journey journey = createJourney(trains);
        
        printArrivalResults(journey);
        printDepartureResults(journey);
    }
    
    private Journey createJourney(List<Train> trains) {
        Train trainA = findTrain(trains, TrainConstants.TRAIN_A);
        Train trainB = findTrain(trains, TrainConstants.TRAIN_B);
        return new Journey(trainA, trainB);
    }
    
    private void printArrivalResults(Journey journey) {
        TrainArrival arrival = journey.processArrival();
        arrival.getArrivedTrains().forEach(printerService::printArrival);
    }
    
    private void printDepartureResults(Journey journey) {
        TrainDeparture departure = journey.processDeparture();
        
        if (departure.isJourneyEnded()) {
            printerService.printJourneyEnded();
            return;
        }
        
        printerService.printDeparture(departure.getTrain());
    }
}
