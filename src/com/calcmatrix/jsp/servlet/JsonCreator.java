package com.calcmatrix.jsp.servlet;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCreator {
    
    // File of data
    private File file;
    private String currentdir = "";
    
    // CreatedFlag
    private boolean Created;
    
    // Data Structure that represents data? entered by the user
    private TaskData taskData;
    
    // The constructor 
    public JsonCreator(String name) throws IOException {
        currentdir = System.getProperty("user.dir");
        taskData = new TaskData();
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
            //ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            //mapper.registerModule(new ParameterNamesModule(Mode.PROPERTIES));
            try {
                taskData = objectMapper.readValue(file, TaskData.class);
                return true;
            } catch (Exception e) {
                System.err.println("Error working with file: " + currentdir + file.getName() + " " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("JSON file not created! ");
            return false;
        }
    }
    
    public TaskData getInputdata() {
        try {
            if (getGSON()) { System.out.println("Data from JSON Succesfully readed! "); }
        } catch (IOException e) {
            System.err.println("Error working with file: " + e.getMessage());
            return null;
        }
        return taskData;
    }
    
    public void setInputData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, taskData);//Prettified JSON
        } catch (Exception e) {
            System.err.println("Error working with file: " + currentdir + file.getName() + " " + e.getMessage());
        }
    }

}

