package com.example.geektrust.model;

public class Bogie {
    private final Station destination;

    public Bogie(Station destination) {
        this.destination = destination;
    }

    public static Bogie createEngine() {
        return new Bogie(Route.getStation("ENGINE"));
    }

    public static Bogie createFromCode(String stationCode) {
        Station station = Route.getStation(stationCode);
        if (station == null) {
            return null; // Invalid station
        }
        return new Bogie(station);
    }

    public boolean canArriveAtHyderabad() {
        return destination.isEngine() || destination.isHyderabad() || destination.canContinuePastHyderabad();
    }

    public boolean canDepartFromHyderabad() {
        return destination.isEngine() || destination.canContinuePastHyderabad();
    }

    public boolean isEngine() {
        return destination.isEngine();
    }

    public Station getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return destination.toString();
    }
}