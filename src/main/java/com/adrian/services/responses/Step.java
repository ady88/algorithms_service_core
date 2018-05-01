package com.adrian.services.responses;

public class Step {
	private String label;
	private String description;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Step [label=" + label + ", description=" + description + "]";
	}
}