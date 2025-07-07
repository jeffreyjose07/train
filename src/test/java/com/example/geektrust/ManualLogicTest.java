package com.example.geektrust;

import com.example.geektrust.model.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManualLogicTest {
    
    @Test
    public void testInput1Logic() {
        Train trainA = Train.createFromTokens("TRAIN_A", 
            Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR"));
        
        Train trainB = Train.createFromTokens("TRAIN_B", 
            Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA"));
        
        Journey journey = new Journey(trainA, trainB);
        
        TrainArrival arrival = journey.processArrival();
        assertEquals("ENGINE NDL NDL GHY NJP NGP", arrival.getTrainA().toString());
        assertEquals("ENGINE NJP GHY AGA BPL PTA", arrival.getTrainB().toString());
        
        TrainDeparture departure = journey.processDeparture();
        assertEquals("ENGINE ENGINE GHY GHY NJP NJP PTA NDL NDL AGA BPL NGP", 
            departure.getTrain().toString());
    }
    
    @Test
    public void testInput2Logic() {
        Train trainA = Train.createFromTokens("TRAIN_A", 
            Arrays.asList("ENGINE", "SLM", "BLR", "KRN", "HYB", "SLM", "NGP", "ITJ"));
        
        Train trainB = Train.createFromTokens("TRAIN_B", 
            Arrays.asList("ENGINE", "SRR", "MAO", "NJP", "PNE", "PTA"));
        
        Journey journey = new Journey(trainA, trainB);
        
        // Keep HYB in arrival
        TrainArrival arrival = journey.processArrival();
        assertEquals("ENGINE HYB NGP ITJ", arrival.getTrainA().toString());
        assertEquals("ENGINE NJP PTA", arrival.getTrainB().toString());
        
        // Remove HYB in departure
        TrainDeparture departure = journey.processDeparture();
        assertEquals("ENGINE ENGINE NJP PTA ITJ NGP", 
            departure.getTrain().toString());
    }
}