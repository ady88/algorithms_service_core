package com.adrian.services;

import java.util.Random;
import java.util.stream.IntStream;

import com.adrian.qualifiers.IntegerSortReport;

@IntegerSortReport
public class SortIntegerReportService extends SortReportService<Integer> {

	private static final int LOWER_BOUND_ELEMENT = 0;

	private static final int UPPER_BOUND_ELEMENT = 100000;

	@Override
	protected Integer[] getArray(int arraySize) {
		var random = new Random();
		var unboxedArray = random.ints(arraySize, getLowerBoundElement(), getUpperBoundElement()).toArray();

		var array = IntStream.of(unboxedArray).boxed().toArray(Integer[]::new);
		return array;
	}

	@Override
	protected Integer getLowerBoundElement() {

		return LOWER_BOUND_ELEMENT;
	}

	@Override
	protected Integer getUpperBoundElement() {
		return UPPER_BOUND_ELEMENT;
	}

}
