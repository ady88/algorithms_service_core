package com.adrian.services.responses;

public class SortResponse<T> {
	private T[] initialArray;
	private T[] sortedArray;
	private String algorithmName;
	private Status status;
	private long duration;

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

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
}