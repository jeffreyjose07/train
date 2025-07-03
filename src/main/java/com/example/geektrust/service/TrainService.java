package com.example.geektrust.service;

import com.example.geektrust.model.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    public Train createTrain(List<String> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Input tokens cannot be null or empty");
        }
        String trainId = tokens.get(0);
        List<String> bogies = new ArrayList<>(tokens);
        
        return new Train(trainId, bogies);
    }

    public void generateOutput(List<Train> trains) {
//1. print The bogie order of arrival of Train A and Train B at Hyderabad
        //2. print Train AB's departure bogie order from Hyderabad
    }
}
