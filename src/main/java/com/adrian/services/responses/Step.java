package com.adrian.services.responses;

import java.util.Arrays;

public class Step {
	private String label;
	private int rank;
	private String description;
	private StepItem[] stepItems;

	public Step(String label, int rank, String description) {
		this.label = label;
		this.rank = rank;
		this.description = description;
	}

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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public StepItem[] getStepItems() {
		return stepItems;
	}

	public void setStepItems(StepItem[] stepItems) {
		this.stepItems = stepItems;
	}

	@Override
	public String toString() {
		return "Step [label=" + label + ", rank=" + rank + ", description=" + description + ", stepItems="
				+ Arrays.toString(stepItems) + "]";
	}
}