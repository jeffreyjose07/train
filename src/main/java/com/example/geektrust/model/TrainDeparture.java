package com.example.geektrust.model;

import com.example.geektrust.util.TrainConstants;
import java.util.List;
import java.util.Optional;

public class TrainDeparture {
    private final Optional<Train> mergedTrain;

    private TrainDeparture(Train mergedTrain) {
        this.mergedTrain = Optional.ofNullable(mergedTrain);
    }

    public static TrainDeparture from(TrainArrival arrival) {
        List<Bogie> allBogies = arrival.getAllBogiesForDeparture();
        
        if (hasInsufficientBogies(allBogies)) {
            return createJourneyEnded();
        }
        
        Train merged = createMergedTrain(allBogies);
        return createWithTrain(merged);
    }

    public static TrainDeparture createJourneyEnded() {
        return new TrainDeparture(null);
    }

    public static TrainDeparture createWithTrain(Train train) {
        return new TrainDeparture(train);
    }

    private static boolean hasInsufficientBogies(List<Bogie> bogies) {
        return bogies.size() <= 2; // Only engines, no passenger bogies
    }

    private static Train createMergedTrain(List<Bogie> bogies) {
        List<Bogie> sortedBogies = SortingStrategy.sortBogiesByDistance(bogies);
        List<Bogie> trainBogies = TrainComposer.composeMergedTrain(sortedBogies);
        return new Train(TrainConstants.TRAIN_AB, trainBogies);
    }

    public boolean isJourneyEnded() {
        return !mergedTrain.isPresent();
    }

    public Optional<Train> getMergedTrain() {
        return mergedTrain;
    }

    public Train getTrain() {
        return mergedTrain.orElseThrow(() -> 
            new IllegalStateException("Journey has ended, no train available"));
    }
}