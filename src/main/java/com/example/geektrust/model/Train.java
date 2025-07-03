package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private final String trainId;
    private final List<String> bogies;

    public Train(String trainId, List<String> bogies) {
        this.trainId = trainId;
        this.bogies = new ArrayList<>(bogies);
    }

    public String getTrainId() {
        return trainId;
    }

    public List<String> getBogies() {
        return new ArrayList<>(bogies);
    }

    @Override
    public String toString() {
        return String.join(" ", bogies);
    }
}
