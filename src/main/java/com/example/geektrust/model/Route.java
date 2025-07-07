package com.example.geektrust.model;

import java.util.*;
import java.util.stream.Collectors;

import com.example.geektrust.util.MaintainabilityConstants;

public class Route {
    private static final Map<String, Station> STATIONS = new HashMap<>();
    
    static {
        STATIONS.put("ENGINE", new Station("ENGINE"));
        STATIONS.put("HYB", new Station("HYB"));
        STATIONS.put("NGP", new Station("NGP", MaintainabilityConstants.NGP_DISTANCE_FROM_HYB));
        STATIONS.put("ITJ", new Station("ITJ", MaintainabilityConstants.ITJ_DISTANCE_FROM_HYB));
        STATIONS.put("BPL", new Station("BPL", MaintainabilityConstants.BPL_DISTANCE_FROM_HYB));
        STATIONS.put("AGA", new Station("AGA", MaintainabilityConstants.AGA_DISTANCE_FROM_HYB));
        STATIONS.put("NDL", new Station("NDL", MaintainabilityConstants.NDL_DISTANCE_FROM_HYB));
        STATIONS.put("PTA", new Station("PTA", MaintainabilityConstants.PTA_DISTANCE_FROM_HYB));
        STATIONS.put("NJP", new Station("NJP", MaintainabilityConstants.NJP_DISTANCE_FROM_HYB));
        STATIONS.put("GHY", new Station("GHY", MaintainabilityConstants.GHY_DISTANCE_FROM_HYB));
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