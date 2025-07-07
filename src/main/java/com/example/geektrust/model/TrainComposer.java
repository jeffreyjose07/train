package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class TrainComposer {

    public static List<Bogie> composeMergedTrain(List<Bogie> sortedBogies) {
        return new TrainBuilder()
                .addEngines(2)
                .addBogies(sortedBogies)
                .build();
    }

    private static class TrainBuilder {
        private final List<Bogie> bogies = new ArrayList<>();

        public TrainBuilder addEngines(int count) {
            for (int i = 0; i < count; i++) {
                bogies.add(Bogie.createEngine());
            }
            return this;
        }

        public TrainBuilder addBogies(List<Bogie> bogiesToAdd) {
            bogies.addAll(bogiesToAdd);
            return this;
        }

        public List<Bogie> build() {
            return new ArrayList<>(bogies);
        }
    }
}