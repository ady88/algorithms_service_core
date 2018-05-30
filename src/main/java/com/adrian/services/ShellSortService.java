package com.adrian.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ShellSort;
import com.adrian.services.responses.Step;
import com.adrian.services.responses.StepItem;
import com.adrian.utils.AlgorithmsUtil;

@ShellSort
public class ShellSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	protected void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps) {
		final int length = array.length;

		int step = 1;

		while (step < length / 3) {
			step = step * 3 + 1;
		}

		final Step initialStep = new Step("Find the sort step", 1, String.format(
				"Find the index of how these elements should be sorted. The array will initialy be %d sorted", step));
		final StepItem nSortedItemStep = new StepItem("Step value", Integer.toString(step));
		initialStep.setStepItems(new StepItem[] { nSortedItemStep });

		steps.add(initialStep);

		int stepIndex = 1;
		while (step >= 1) {
			final Step insertionSortStep = new Step("Make the array n sorted", ++stepIndex,
					String.format("Make the array %d sorted", step));
			final StepItem unitItemStep = new StepItem("The step value", Integer.toString(step));
			int numberOfExchanges = 0;
			var exchangeStepItems = new ArrayList<StepItem>();
			for (int i = step; i < length; i++) {

				for (int j = i; j >= step && predicate.test(array[j], array[j - step]); j -= step) {
					AlgorithmsUtil.exchange(array, j - step, j);
					final StepItem firstIndexItemStep = new StepItem("First index to exchange",
							Integer.toString(j - step));
					final StepItem secondIndexItemStep = new StepItem("Second index to exchange", Integer.toString(j));
					exchangeStepItems.add(firstIndexItemStep);
					exchangeStepItems.add(secondIndexItemStep);
					numberOfExchanges++;
				}
			}

			final StepItem numberExchangesItemStep = new StepItem("Number of exchanges made",
					Integer.toString(numberOfExchanges));
			// insertionSortStep.setStepItems(new StepItem[] { numberExchangesItemStep,
			// exchangeStepItems .toArray() });
			exchangeStepItems.toArray(new StepItem[] {});
			StepItem[] insertionSortStepItems = new StepItem[exchangeStepItems.size() + 2];
			insertionSortStepItems[0] = unitItemStep;
			insertionSortStepItems[1] = numberExchangesItemStep;
			for (int i = 0; i < exchangeStepItems.size(); i++) {
				insertionSortStepItems[i + 2] = exchangeStepItems.get(i);
			}

			insertionSortStep.setStepItems(insertionSortStepItems);
			step = step / 3;
			steps.add(insertionSortStep);
		}

	}

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		int length = array.length;

		int step = 1;

		while (step < length / 3) {
			step = step * 3 + 1;
		}

		while (step >= 1) {
			for (int i = step; i < length; i++) {
				for (int j = i; j >= step && predicate.test(array[j], array[j - step]); j -= step) {
					AlgorithmsUtil.exchange(array, j - step, j);
				}
			}

			step = step / 3;
		}
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.SHELL_SORT.name();
	}
}
