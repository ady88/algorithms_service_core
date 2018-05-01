package com.adrian.services.responses;

import java.util.List;

public class SortResponseWithSteps<T> extends SortResponse<T> {

	List<Step> steps;

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "SortResponseWithSteps [steps=" + steps + "]";
	}
}