package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DomainObjectsTest {

    @Test
    void stationShouldKnowRouteRules() {
        Station ndl = new Station("NDL", 1500);
        Station hyb = new Station("HYB");
        Station engine = new Station("ENGINE");
        
        assertTrue(ndl.canContinuePastHyderabad());
        assertFalse(hyb.canContinuePastHyderabad());
        assertFalse(engine.canContinuePastHyderabad());
        
        assertFalse(ndl.isHyderabad());
        assertTrue(hyb.isHyderabad());
        assertFalse(ndl.isEngine());
        assertTrue(engine.isEngine());
    }

    @Test
    void bogieShouldKnowItsDestinationRules() {
        Bogie engine = Bogie.createEngine();
        Bogie ndl = Bogie.createFromCode("NDL");
        Bogie hyb = Bogie.createFromCode("HYB");
        Bogie invalid = Bogie.createFromCode("INVALID");
        
        assertTrue(engine.canArriveAtHyderabad());
        assertTrue(ndl.canArriveAtHyderabad());
        assertTrue(hyb.canArriveAtHyderabad());
        assertNull(invalid);
        
        assertTrue(engine.canDepartFromHyderabad());
        assertTrue(ndl.canDepartFromHyderabad());
        assertFalse(hyb.canDepartFromHyderabad());
    }

    @Test
    void routeShouldProvideStationData() {
        Station ndl = Route.getStation("NDL");
        Station invalid = Route.getStation("INVALID");
        
        assertEquals("NDL", ndl.getCode());
        assertEquals(1500, ndl.getDistanceFromHyderabad());
        assertNull(invalid);
        
        assertTrue(Route.isValidStation("NDL"));
        assertFalse(Route.isValidStation("INVALID"));
    }

    @Test
    void journeyShouldProcessArrivalAndDeparture() {
        Train trainA = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE", "NDL", "HYB", "GHY"));
        Train trainB = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE", "PTA", "AGA"));
        
        Journey journey = new Journey(trainA, trainB);
        
        TrainArrival arrival = journey.processArrival();
        assertEquals("ENGINE NDL HYB GHY", arrival.getTrainA().toString());
        assertEquals("ENGINE PTA AGA", arrival.getTrainB().toString());
        
        TrainDeparture departure = journey.processDeparture();
        assertFalse(departure.isJourneyEnded());
        assertEquals("ENGINE ENGINE GHY PTA NDL AGA", departure.getTrain().toString());
    }

    @Test
    void journeyShouldDetectJourneyEnded() {
        Train trainA = Train.createFromTokens("TRAIN_A", Arrays.asList("ENGINE"));
        Train trainB = Train.createFromTokens("TRAIN_B", Arrays.asList("ENGINE"));
        
        Journey journey = new Journey(trainA, trainB);
        TrainDeparture departure = journey.processDeparture();
        
        assertTrue(departure.isJourneyEnded());
        assertFalse(departure.getMergedTrain().isPresent());
    }
}