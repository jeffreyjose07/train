package com.example.geektrust.service;

import com.example.geektrust.model.Train;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileProcessor {
    private final TrainService trainService = new TrainService();

    public void run(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Train> trains = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                trains.add(processLineAndReturnTrain(line.trim()));
            }
            trainService.generateOutput(trains);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private Train processLineAndReturnTrain(String line) throws IOException {
        if (line.isEmpty()) {
            throw new IOException("line is empty");
        }
        List<String> tokens = Arrays.asList(line.split("\\s+"));
        return trainService.createTrain(tokens);
    }
}
