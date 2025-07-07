package com.example.geektrust.model;

import java.util.List;
import java.util.stream.Stream;

public class TrainArrival {
    private final Train trainA;
    private final Train trainB;

    private TrainArrival(Train trainA, Train trainB) {
        this.trainA = trainA;
        this.trainB = trainB;
    }

    public static TrainArrival from(Train trainA, Train trainB) {
        Train filteredA = trainA.prepareForArrivalAtHyderabad();
        Train filteredB = trainB.prepareForArrivalAtHyderabad();
        return new TrainArrival(filteredA, filteredB);
    }

    public Stream<Train> getArrivedTrains() {
        return Stream.of(trainA, trainB);
    }

    public List<Bogie> getAllBogiesForDeparture() {
        return getArrivedTrains()
                .flatMap(train -> train.getBogiesForDeparture().stream())
                .collect(java.util.stream.Collectors.toList());
    }

    public Train getTrainA() {
        return trainA;
    }

    public Train getTrainB() {
        return trainB;
    }
}