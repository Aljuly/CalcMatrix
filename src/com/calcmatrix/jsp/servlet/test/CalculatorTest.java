package com.calcmatrix.jsp.servlet.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.calcmatrix.jsp.servlet.Calculator;
import com.calcmatrix.jsp.servlet.InputData;
import com.calcmatrix.jsp.servlet.JsonCreator;
import com.calcmatrix.jsp.servlet.OutputData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculatorTest {
    
    // File of data
    private File file;
    private String currentdir = "";
    
    // CreatedFlag
    private boolean Created;
    
    // Calculator
    Calculator calc;
    
    // Input and Output
    InputData inputData;
    OutputData outputData;
    
    private void readFile(String name) throws IOException {
        currentdir = System.getProperty("user.dir");
        try {
            file = new File(currentdir, name);
            if (file.createNewFile()) {
                System.out.println("Data file created");
                Created = true;
            } else {
                System.out.println("Data file already present");
                Created = true;
            }
        } catch (IOException e) {
            System.err.println("Error in connecting to file: " + e.getMessage());
            Created = false;
        }
    }
    
    private boolean getGSON() throws IOException {
        if (Created) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                inputData = objectMapper.readValue(file, InputData.class);
                return true;
            } catch (Exception e) {
                System.err.println("Error working with file: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("JSON file not created! ");
            return false;
        }
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        
    }
    
    @Test
    public void testAdd() throws IOException {
        readFile("TestValues_add.json");
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
            calc.setInputdata(inputData);
            outputData = calc.getOutputdata();
            assertEquals("matrix", outputData.getVartype());
            assertEquals(3, Double.valueOf(outputData.getValue()[0][0]), 0);
            assertEquals(10, Double.valueOf(outputData.getValue()[2][2]), 0);
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Test
    public void testMul() throws IOException {
        readFile("TestValues_mul.json");
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
            calc.setInputdata(inputData);
            outputData = calc.getOutputdata();
            assertEquals("matrix", outputData.getVartype());
            assertEquals(38, Double.valueOf(outputData.getValue()[0][0]), 0);
            assertEquals(55, Double.valueOf(outputData.getValue()[2][2]), 0);
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
        }
    }
    
    @Test
    public void testInvert() throws IOException {
        readFile("TestValues_invert.json");
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
            calc.setInputdata(inputData);
            outputData = calc.getOutputdata();
            assertEquals("matrix", outputData.getVartype());
            assertEquals(0.2, Double.valueOf(outputData.getValue()[0][0]), 0.001);
            //assertEquals(-0.6, Double.valueOf(outputData.getValue()[2][2]), 0.001);
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Test
    public void testTranspose() throws IOException {
        readFile("TestValues_tran.json");
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
            calc.setInputdata(inputData);
            outputData = calc.getOutputdata();
            assertEquals("matrix", outputData.getVartype());
            assertEquals(1, Double.valueOf(outputData.getValue()[0][0]), 0);
            assertEquals(5, Double.valueOf(outputData.getValue()[1][1]), 0);
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
        }
    }
    
    @Test
    public void testDet() throws IOException {
        readFile("TestValues_det.json");
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
            calc.setInputdata(inputData);
            outputData = calc.getOutputdata();
            assertEquals("number", outputData.getVartype());
            assertEquals(1, outputData.getValue().length);
            assertEquals(1, outputData.getValue()[0].length);
            assertEquals(0, Double.valueOf(outputData.getValue()[0][0]), 0);
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

}
