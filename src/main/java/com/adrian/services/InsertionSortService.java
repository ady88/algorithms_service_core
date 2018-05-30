package com.adrian.services;

import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.services.responses.Step;
import com.adrian.services.responses.StepItem;
import com.adrian.utils.AlgorithmsUtil;

@InsertionSort
public class InsertionSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	protected void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps) {
		int length = array.length;

		for (int i = 1; i < length; i++) {
			final Step insertItemStep = new Step("Insert item step.", i,
					String.format("Insert item at position %d in the sorted array", i));
			int numberExchanges = 0;
			for (int j = i; j > 0 && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j - 1, j);
				numberExchanges++;
			}
			StepItem sortedStepItem = new StepItem("Number of items already sorted", Integer.toString(i));
			StepItem exchangesMadeStepItem = new StepItem("Number of exchanges made",
					Integer.toString(numberExchanges));
			StepItem insertionIndexStepItem = new StepItem("Index where the insertion should be made",
					Integer.toString(i - numberExchanges));

			insertItemStep
					.setStepItems(new StepItem[] { sortedStepItem, exchangesMadeStepItem, insertionIndexStepItem });
			steps.add(insertItemStep);
		}

	}

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		int length = array.length;

		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j - 1, j);
			}
		}
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.INSERTION_SORT.name();
	}
}