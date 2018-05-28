package com.adrian.services.responses;

public class StepItem {
	String name;
	String value;

	
	
	public StepItem(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StepItem [name=" + name + ", value=" + value + "]";
	}
}
