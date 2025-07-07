package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Train {
    private final String trainId;
    private final List<Bogie> bogies;

    public Train(String trainId, List<Bogie> bogies) {
        this.trainId = trainId;
        this.bogies = new ArrayList<>(bogies);
    }

    public static Train createFromTokens(String trainId, List<String> stationCodes) {
        List<Bogie> bogies = stationCodes.stream()
                .map(Bogie::createFromCode)
                .filter(bogie -> bogie != null)
                .collect(Collectors.toList());
        return new Train(trainId, bogies);
    }

    public Train prepareForArrivalAtHyderabad() {
        List<Bogie> validBogies = bogies.stream()
                .filter(Bogie::canArriveAtHyderabad)
                .collect(Collectors.toList());
        return new Train(trainId, validBogies);
    }

    public List<Bogie> getBogiesForDeparture() {
        return bogies.stream()
                .filter(Bogie::canDepartFromHyderabad)
                .collect(Collectors.toList());
    }


    public boolean hasInsufficientBogies() {
        return bogies.size() <= 2;
    }

    public String getTrainId() {
        return trainId;
    }

    public List<Bogie> getBogies() {
        return new ArrayList<>(bogies);
    }

    @Override
    public String toString() {
        return bogies.stream()
                .map(Bogie::toString)
                .collect(Collectors.joining(" "));
    }
}
