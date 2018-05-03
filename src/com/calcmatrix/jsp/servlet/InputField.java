package com.calcmatrix.jsp.servlet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputField {
	
	private String varname;
	private String vartype;
	private String vardesc;
	private String defaultvalue;
	
	@JsonCreator
	public InputField(@JsonProperty("varname") String varname, @JsonProperty("vartype") String vartype, @JsonProperty("vardesc")String vardesc, @JsonProperty("defaultval") String defaultvalue) {
		this.varname = varname;
		this.vartype = vartype;
		this.vardesc = vardesc;
		this.defaultvalue = defaultvalue;
	}

	public String getVarname() {
		return varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}

	public String getVartype() {
		return vartype;
	}

	public void setVartype(String vartype) {
		this.vartype = vartype;
	}

	public String getVardesc() {
		return vardesc;
	}

	public void setVardesc(String vardesc) {
		this.vardesc = vardesc;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

}
