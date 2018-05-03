package com.calcmatrix.jsp.servlet;

import java.util.LinkedHashMap;

public class TaskData {
	
	private LinkedHashMap<Integer, InputDescription> inputDataDesc = new LinkedHashMap<>();
	
	public LinkedHashMap<Integer, InputDescription> getInputDataDesc() {
		return inputDataDesc;
	}

    public void setInputDataDesc(LinkedHashMap<Integer, InputDescription> inputDataDesc) {
        this.inputDataDesc = inputDataDesc;
    }	
		
	public TaskData() {
	    
	}
	
}
