package com.calcmatrix.jsp.servlet;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputData {
	
	private String calcmode = "0";
	private List<UserInput> userinputs = new LinkedList<>();
	
	@JsonCreator
	public InputData(@JsonProperty("Calcmode") String calcmode, @JsonProperty("Userinputs") List<UserInput> userinputs) {
		this.calcmode = calcmode;
		this.userinputs = userinputs;
	}
	
	public String getCalcmode() {
		return calcmode;
	}
	public void setCalcmode(String calcmode) {
		this.calcmode = calcmode;
	}
	public List<UserInput> getUserinputs() {
		return userinputs;
	}
	public void setUserinputs(List<UserInput> userinputs) {
		this.userinputs = userinputs;
	}
	
}
