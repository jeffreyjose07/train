package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RouteServiceTest {
    private final RouteService routeService = new RouteService();

    @Test
    void filterValidBogiesShouldRetainEngineAndValidStations() {
        List<String> input = Arrays.asList("ENGINE", "NDL", "ABC", "PTA");
        List<String> result = routeService.filterValidBogies(input);
        assertEquals(Arrays.asList("ENGINE", "NDL", "PTA"), result);
    }

    @Test
    void sortBogiesShouldSortByDistanceDescending() {
        List<String> input = Arrays.asList("NDL", "PTA", "NJP");
        List<String> result = routeService.sortBogies(input);
        assertEquals(Arrays.asList("NJP", "PTA", "NDL"), result);
    }
}
