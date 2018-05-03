package com.calcmatrix.jsp.servlet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInput {
	
	private String vartype;
	private String[][] value;
	
	@JsonCreator
	public UserInput(@JsonProperty("Vartype") String vartype, @JsonProperty("Value") String[][] value) {
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
