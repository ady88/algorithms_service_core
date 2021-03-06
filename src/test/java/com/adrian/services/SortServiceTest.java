package com.adrian.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.adrian.enums.SortAlgorithm;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;

@ExtendWith(WeldJunit5Extension.class)
public class SortServiceTest {

	private static final Integer[] ARRAY_TO_BE_SORTED = new Integer[] { 11, 2, 9, 7, 12, 16, 19, 3, 8, 4, 1, 7, 5, 11,
			13, 10, 0, 6 };

	@WeldSetup
	public WeldInitiator weld = WeldInitiator.from(SortService.class, InsertionSortService.class,
			SelectionSortService.class, ShellSortService.class, MergeSortService.class, ImprovedMergeSortService.class,
			QuickSortService.class, ImprovedQuickSortService.class).build();

	private static SortService<Integer> sortService;
	private Integer[] array;

	@BeforeEach
	public void init() {
		array = ARRAY_TO_BE_SORTED.clone();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInsertionSort() {

		sortService = (SortService<Integer>) weld.select(InsertionSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.INSERTION_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInsertionSortWithDescription() {
		sortService = (SortService<Integer>) weld.select(InsertionSortService.class.getAnnotations()[0]).get();
		SortResponseWithSteps<Integer> response = sortService.sortWithSteps(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.INSERTION_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSelectionSort() {

		sortService = (SortService<Integer>) weld.select(SelectionSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.SELECTION_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSelectionSortWithDescription() {
		sortService = (SortService<Integer>) weld.select(SelectionSortService.class.getAnnotations()[0]).get();
		SortResponseWithSteps<Integer> response = sortService.sortWithSteps(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.SELECTION_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testShellSort() {

		sortService = (SortService<Integer>) weld.select(ShellSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.SHELL_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testShellSortWithDescription() {
		sortService = (SortService<Integer>) weld.select(ShellSortService.class.getAnnotations()[0]).get();
		SortResponseWithSteps<Integer> response = sortService.sortWithSteps(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.SHELL_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMergeSort() {

		sortService = (SortService<Integer>) weld.select(MergeSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.MERGE_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testImprovedMergeSort() {

		sortService = (SortService<Integer>) weld.select(ImprovedMergeSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.IMPROVED_MERGE_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testQuickSort() {

		sortService = (SortService<Integer>) weld.select(QuickSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.QUICK_SORT.name());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testImprovedQuickSort() {

		sortService = (SortService<Integer>) weld.select(ImprovedQuickSortService.class.getAnnotations()[0]).get();

		SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);
		assertIfSorted(response, SortAlgorithm.IMPROVED_QUICK_SORT.name());
	}

	private void assertIfSorted(SortResponse<Integer> response, String expectedAlgorithmName) {
		assertNotNull(response);
		Integer[] sortedArray = response.getSortedArray();
		assertNotNull(sortedArray);
		assertEquals(expectedAlgorithmName, response.getAlgorithmName());

		for (int j = 1; j < sortedArray.length; j++) {
			assertTrue(sortedArray[j - 1] <= sortedArray[j]);
		}
	}
}
