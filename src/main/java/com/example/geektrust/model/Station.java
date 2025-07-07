package com.example.geektrust.model;

import java.util.Objects;

public class Station {
    private final String code;
    private final int distanceFromHyderabad;
    private final boolean validAfterHyderabad;

    public Station(String code, int distanceFromHyderabad) {
        this.code = code;
        this.distanceFromHyderabad = distanceFromHyderabad;
        this.validAfterHyderabad = true;
    }

    public Station(String code) {
        this.code = code;
        this.distanceFromHyderabad = 0;
        this.validAfterHyderabad = false;
    }

    public boolean canContinuePastHyderabad() {
        return validAfterHyderabad;
    }

    public boolean isHyderabad() {
        return "HYB".equals(code);
    }

    public boolean isEngine() {
        return "ENGINE".equals(code);
    }

    public int getDistanceFromHyderabad() {
        return distanceFromHyderabad;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(code, station.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}