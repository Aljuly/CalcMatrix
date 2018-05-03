package com.calcmatrix.jsp.servlet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputData {
	
	private String vartype = "";
	private String[][] value;
	
	@JsonCreator
	public OutputData(@JsonProperty("vartype") String vartype, @JsonProperty("value") String[][] value) {
		
		this.vartype = vartype;
		this.value = value;
	}
	
	public String getVartype() {
		return vartype;
	}
	
	public void setVartype(String vartype) {
		this.vartype = vartype;
	}
	
	public String[][] getValue() {
		return value;
	}
	
	public void setValue(String[][] value) {
		this.value = value;
	}
	
}
