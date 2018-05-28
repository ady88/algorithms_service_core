package com.adrian.services;

import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.services.responses.Step;
import com.adrian.services.responses.StepItem;
import com.adrian.utils.AlgorithmsUtil;

@SelectionSort
public class SelectionSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < length; j++) {
				if (predicate.test(array[j], array[min])) {
					min = j;
				}
			}
			AlgorithmsUtil.exchange(array, min, i);
		}
	}

	@Override
	protected void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps) {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			final Step smallestItemStep = new Step("Find smallest item.", i + 1, "Find the smallest item in the array");
			int min = i;
			for (int j = i + 1; j < length; j++) {
				if (predicate.test(array[j], array[min])) {
					min = j;
				}
			}
			final StepItem stepItem = new StepItem("Smallest item value", array[min].toString());
			smallestItemStep.setStepItems(new StepItem[] { stepItem });

			AlgorithmsUtil.exchange(array, min, i);

			final Step smallestItemExchangeStep = new Step("Exchange smallest item.", i + 1,
					String.format("Exchange the smallest item in the array with the item in position %d", i));
			final StepItem exchangeMinStepItem = new StepItem("Smallest item index", Integer.toString(min));
			final StepItem exchangeStepItem = new StepItem("Index that is exchanged with the smallest item",
					Integer.toString(i));
			smallestItemExchangeStep.setStepItems(new StepItem[] { exchangeMinStepItem, exchangeStepItem });

			steps.add(smallestItemStep);
			steps.add(smallestItemExchangeStep);
		}
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.SELECTION_SORT.name();
	}
}
