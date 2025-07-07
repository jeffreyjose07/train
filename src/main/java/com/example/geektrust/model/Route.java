package com.example.geektrust.model;

import java.util.*;
import java.util.stream.Collectors;

public class Route {
    private static final Map<String, Station> STATIONS = new HashMap<>();
    
    static {
        STATIONS.put("ENGINE", new Station("ENGINE"));
        STATIONS.put("HYB", new Station("HYB"));
        STATIONS.put("NGP", new Station("NGP", 400));
        STATIONS.put("ITJ", new Station("ITJ", 700));
        STATIONS.put("BPL", new Station("BPL", 800));
        STATIONS.put("AGA", new Station("AGA", 1300));
        STATIONS.put("NDL", new Station("NDL", 1500));
        STATIONS.put("PTA", new Station("PTA", 1800));
        STATIONS.put("NJP", new Station("NJP", 2200));
        STATIONS.put("GHY", new Station("GHY", 2700));
    }

    public static Station getStation(String code) {
        return STATIONS.get(code);
    }

    public static boolean isValidStation(String code) {
        return STATIONS.containsKey(code);
    }

    public static List<Station> getStationsOrderedByDistance() {
        return STATIONS.values().stream()
                .filter(s -> s.canContinuePastHyderabad())
                .sorted(Comparator.comparingInt(Station::getDistanceFromHyderabad).reversed())
                .collect(Collectors.toList());
    }
}