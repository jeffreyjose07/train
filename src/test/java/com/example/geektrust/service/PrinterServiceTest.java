package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Train;

class PrinterServiceTest {
    private final PrinterService printer = new PrinterService();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream original;

    @BeforeEach
    void setUp() {
        original = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(original);
    }

    @Test
    void printArrivalShouldOutputArrivalLine() {
        Train train = new Train("TRAIN_A", Arrays.asList("ENGINE", "NDL"));
        printer.printArrival(train);
        assertEquals("ARRIVAL TRAIN_A ENGINE NDL\n", out.toString());
    }

    @Test
    void printJourneyEndedShouldOutputEndMessage() {
        printer.printJourneyEnded();
        assertEquals("JOURNEY_ENDED\n", out.toString());
    }
}
