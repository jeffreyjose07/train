package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Train;
import com.example.geektrust.util.TrainConstants;

class TrainServiceTest {
    private final TrainService service = new TrainService();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void createTrainShouldParseTokens() {
        Train train = service.createTrain(Arrays.asList("TRAIN_A", "ENGINE", "NDL"));
        assertEquals("TRAIN_A", train.getTrainId());
        assertEquals("ENGINE NDL", train.toString());
    }

    @Test
    void generateOutputShouldPrintArrivalAndDeparture() {
        Train a = new Train(TrainConstants.TRAIN_A, Arrays.asList("ENGINE", "NDL", "GHY"));
        Train b = new Train(TrainConstants.TRAIN_B, Arrays.asList("ENGINE", "PTA"));
        service.generateOutput(Arrays.asList(a, b));
        String[] lines = outContent.toString().trim().split("\n");
        assertEquals("ARRIVAL TRAIN_A ENGINE NDL GHY", lines[0].trim());
        assertEquals("ARRIVAL TRAIN_B ENGINE PTA", lines[1].trim());
        assertEquals("DEPARTURE TRAIN_AB ENGINE ENGINE GHY PTA NDL", lines[2].trim());
    }

    @Test
    void generateOutputShouldPrintJourneyEndedWhenNoBogiesRemain() {
        outContent.reset();
        Train a = new Train(TrainConstants.TRAIN_A, Arrays.asList("ENGINE"));
        Train b = new Train(TrainConstants.TRAIN_B, Arrays.asList("ENGINE"));
        service.generateOutput(Arrays.asList(a, b));
        String[] lines = outContent.toString().trim().split("\n");
        assertEquals("ARRIVAL TRAIN_A ENGINE", lines[0].trim());
        assertEquals("ARRIVAL TRAIN_B ENGINE", lines[1].trim());
        assertEquals("JOURNEY_ENDED", lines[2].trim());
    }
}
