package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileProcessorTest {
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
    void runShouldReadFileAndPrintOutput() throws Exception {
        Path temp = Files.createTempFile("input", ".txt");
        try (FileWriter writer = new FileWriter(temp.toFile())) {
            writer.write("TRAIN_A ENGINE NDL\n");
            writer.write("TRAIN_B ENGINE PTA\n");
        }
        new FileProcessor().run(temp.toString());
        String output = outContent.toString();
        assertTrue(output.contains("ARRIVAL TRAIN_A"));
        Files.deleteIfExists(temp);
    }
}
