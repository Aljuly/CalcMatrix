package com.calcmatrix.jsp.servlet;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputDescription {
	
	private String operationtag;
	private String description;
	private List<InputField> inputfields;
	
	@JsonCreator
	public InputDescription(@JsonProperty("operationtag") String operationtag, @JsonProperty("description") String description, @JsonProperty("inputfields") List<InputField> inputfields) {
		
		this.operationtag = operationtag;
		this.description = description;
		this.inputfields = inputfields;
	}

	public String getOperationtag() {
		return operationtag;
	}

	public void setOperationtag(String operationtag) {
		this.operationtag = operationtag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InputField> getInputfields() {
		return inputfields;
	}

	public void setInputfields(List<InputField> inputfields) {
		this.inputfields = inputfields;
	}
	
}
