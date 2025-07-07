package com.example.geektrust.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingStrategy {

    public static List<Bogie> sortBogiesByDistance(List<Bogie> bogies) {
        return bogies.stream()
                .filter(bogie -> !bogie.isEngine())
                .sorted(createDistanceComparator())
                .collect(Collectors.toList());
    }

    private static Comparator<Bogie> createDistanceComparator() {
        return Comparator
                .comparingInt((Bogie bogie) -> bogie.getDestination().getDistanceFromHyderabad())
                .reversed(); // Farthest first
    }
}