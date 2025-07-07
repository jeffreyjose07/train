package com.example.geektrust;

import com.example.geektrust.service.FileProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUpStreams() {
        outputStream.reset();
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testSampleInput1() throws Exception {
        String inputFile = "sample_input/input1.txt";
        String expectedOutputFile = "sample_input/output1.txt";
        
        new FileProcessor().run(inputFile);
        
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFile)), StandardCharsets.UTF_8).trim();
        
        assertEquals(expectedOutput, actualOutput, "Output for input1.txt should match expected output1.txt");
    }
    
    @Test
    public void testSampleInput2() throws Exception {
        String inputFile = "sample_input/input2.txt";
        String expectedOutputFile = "sample_input/output2.txt";
        
        new FileProcessor().run(inputFile);
        
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFile)), StandardCharsets.UTF_8).trim();
        
        assertEquals(expectedOutput, actualOutput, "Output for input2.txt should match expected output2.txt");
    }
    
    @Test
    public void testSampleInput3() throws Exception {
        String inputFile = "sample_input/input3.txt";
        String expectedOutputFile = "sample_input/output3.txt";
        
        new FileProcessor().run(inputFile);
        
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFile)), StandardCharsets.UTF_8).trim();
        
        assertEquals(expectedOutput, actualOutput, "Output for input3.txt should match expected output3.txt");
    }
    
    @Test
    public void testSampleInput4() throws Exception {
        String inputFile = "sample_input/input4.txt";
        String expectedOutputFile = "sample_input/output4.txt";
        
        new FileProcessor().run(inputFile);
        
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFile)), StandardCharsets.UTF_8).trim();
        
        assertEquals(expectedOutput, actualOutput, "Output for input4.txt should match expected output4.txt");
    }
}