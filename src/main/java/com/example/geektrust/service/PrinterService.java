package com.example.geektrust.service;

import com.example.geektrust.model.Train;

/**
 * Handles printing of train details to STDOUT.
 */
public class PrinterService {

    public void printArrival(Train train) {
        System.out.println("ARRIVAL " + train.getTrainId() + " " + train);
    }

    public void printDeparture(Train train) {
        System.out.println("DEPARTURE " + train.getTrainId() + " " + train);
    }

    public void printJourneyEnded() {
        System.out.println("JOURNEY_ENDED");
    }
}
