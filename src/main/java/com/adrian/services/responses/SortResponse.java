package com.adrian.services.responses;

import java.util.Arrays;

public class SortResponse<T> {
	private T[] initialArray;
	private T[] sortedArray;
	private Status status;

	public T[] getInitialArray() {
		return initialArray;
	}

	public void setInitialArray(T[] initialArray) {
		this.initialArray = initialArray;
	}

	public T[] getSortedArray() {
		return sortedArray;
	}

	public void setSortedArray(T[] sortedArray) {
		this.sortedArray = sortedArray;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SortResponse [initialArray=" + Arrays.toString(initialArray) + ", sortedArray="
				+ Arrays.toString(sortedArray) + ", status=" + status + "]";
	}
}