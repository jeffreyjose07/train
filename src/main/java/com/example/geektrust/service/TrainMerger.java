package com.example.geektrust.service;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Merges two trains at Hyderabad and prepares the departure order.
 */
public class TrainMerger {

    private final RouteService routeService = new RouteService();

    public Train merge(Train trainA, Train trainB) {
        List<String> bogies = new ArrayList<>();

        // collect non-engine bogies from both trains
        List<String> collected = new ArrayList<>();
        collected.addAll(trainA.getBogies().subList(1, trainA.getBogies().size()));
        collected.addAll(trainB.getBogies().subList(1, trainB.getBogies().size()));

        // order them based on route
        List<String> ordered = routeService.sortBogies(collected);

        // add two engines at the front
        bogies.add(TrainConstants.ENGINE);
        bogies.add(TrainConstants.ENGINE);
        bogies.addAll(ordered);

        return new Train(TrainConstants.TRAIN_AB, bogies);
    }
}
