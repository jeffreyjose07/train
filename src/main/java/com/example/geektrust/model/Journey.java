package com.example.geektrust.model;

public class Journey {
    private final Train trainA;
    private final Train trainB;

    public Journey(Train trainA, Train trainB) {
        this.trainA = trainA;
        this.trainB = trainB;
    }

    public TrainArrival processArrival() {
        return TrainArrival.from(trainA, trainB);
    }

    public TrainDeparture processDeparture() {
        TrainArrival arrival = processArrival();
        return TrainDeparture.from(arrival);
    }
}