package com.example.geektrust.service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.geektrust.util.TrainConstants;

/**
 * Provides route related operations like filtering and ordering bogies.
 */
public class RouteService {

    /**
     * Distances of stations from HYB. Used for ordering bogies in the merged train.
     */
    private static final Map<String, Integer> DISTANCE_FROM_HYB;
    private static final Set<String> VALID_STATIONS_AFTER_HYB;
    private static final List<String> SORT_ORDER;

    static {
        Map<String, Integer> temp = new HashMap<>();
        temp.put("NGP", 400);
        temp.put("ITJ", 700);
        temp.put("BPL", 800);
        temp.put("AGA", 1300);
        temp.put("NDL", 1500);
        temp.put("PTA", 1800);
        temp.put("NJP", 2200);
        temp.put("GHY", 2700);
        DISTANCE_FROM_HYB = Collections.unmodifiableMap(temp);

        VALID_STATIONS_AFTER_HYB = new LinkedHashSet<>(DISTANCE_FROM_HYB.keySet());

        SORT_ORDER = DISTANCE_FROM_HYB.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Filters out bogies that are not valid for the route after Hyderabad.
     */
    public List<String> filterValidBogies(List<String> bogies) {
        List<String> filtered = new ArrayList<>();
        for (String bogie : bogies) {
            if (TrainConstants.ENGINE.equals(bogie) || VALID_STATIONS_AFTER_HYB.contains(bogie)) {
                filtered.add(bogie);
            }
        }
        return filtered;
    }

    /**
     * Orders bogies based on distance from Hyderabad. Farthest station first.
     */
    public List<String> sortBogies(List<String> bogies) {
        List<String> sortable = new ArrayList<>(bogies);
        sortable.sort(Comparator.comparingInt(DISTANCE_FROM_HYB::get).reversed());
        return sortable;
    }

    public List<String> getSortOrder() {
        return SORT_ORDER;
    }
}
