package net.egork.arrays;

import net.egork.collections.CollectionUtils;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.misc.Factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ArrayUtils {
	public static Integer[] generateOrder(int size) {
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		return order;
	}

	public static<T extends Comparable<T>> int minIndex(ArrayWrapper<T> array) {
		return array.indexOf(CollectionUtils.minElement(array));
	}

	public static<T extends Comparable<T>> int maxIndex(ArrayWrapper<T> array) {
		return array.indexOf(CollectionUtils.maxElement(array));
	}

	public static<T> int minIndex(ArrayWrapper<T> array, Comparator<T> comparator) {
		return array.indexOf(CollectionUtils.minElement(array, comparator));
	}

	public static<T> int maxIndex(ArrayWrapper<T> array, Comparator<T> comparator) {
		return array.indexOf(CollectionUtils.maxElement(array, comparator));
	}

	public static<T> void fill(ArrayWrapper<T> array, T value) {
		for (int i = 0; i < array.size(); i++)
			array.set(i, value);			
	}

	public static<T> void fill(ArrayWrapper<T> array, Factory<T> factory) {
		for (int i = 0; i < array.size(); i++)
			array.set(i, factory.create());
	}

	public static void fill(long[][] array, long value) {
		for (long[] row : array)
			Arrays.fill(row, value);
	}

	public static void fillColumn(long[][] array, int index, long value) {
		for (long[] row : array)
			row[index] = value;
	}

	public static void fillColumn(int[][] array, int index, int value) {
		for (int[] row : array)
			row[index] = value;
	}

	public static void fill(int[][] array, int value) {
		for (int[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(boolean[][] array, boolean value) {
		for (boolean[] row : array)
			Arrays.fill(row, value);
	}

	public static<T extends Comparable<T>> long countUnorderedPairs(final ArrayWrapper<T> array) {
		long result = 0;
		Integer[] order = generateOrder(array.size());
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return array.get(o1).compareTo(array.get(o2));
			}
		});
		SumIntervalTree tree = new SumIntervalTree(array.size());
		for (int i : order) {
			result += i - tree.getSegment(0, i);
			tree.put(i, 1);
		}
		return result;
	}

	public static<T extends Comparable<T>> boolean nextPermutation(ArrayWrapper<T> array) {
		for (int i = array.size() - 2; i >= 0; i--) {
			if (array.get(i).compareTo(array.get(i + 1)) < 0) {
				int index = i + 1;
				for (int j = i + 2; j < array.size(); j++) {
					if (array.get(i).compareTo(array.get(j)) >= 0)
						break;
					else
						index = j;
				}
				T temp = array.get(i);
				array.set(i, array.get(index));
				array.set(index, temp);
				sort(array.subArray(i + 1));
				return true;
			}
		}
		return false;
	}

	public static long sumArray(int[] array) {
		long result = 0;
		for (int element : array)
			result += element;
		return result;
	}

	public static int[] range(int from, int to) {
		int[] result = new int[Math.max(from, to) - Math.min(from, to) + 1];
		int index = 0;
		if (to > from) {
			for (int i = from; i <= to; i++)
				result[index++] = i;
		} else {
			for (int i = from; i >= to; i--)
				result[index++] = i;
		}
		return result;
	}

	@SuppressWarnings({"unchecked"})
	public static<T extends Comparable<T>> ArrayWrapper<T> sort(ArrayWrapper<T> array) {
		Object underlying = array.underlying;
		if (underlying instanceof char[])
			Arrays.sort((char[]) underlying, array.from, array.to);
		else if (underlying instanceof int[])
			Arrays.sort((int[]) underlying, array.from, array.to);
		else if (underlying instanceof long[])
			Arrays.sort((long[]) underlying, array.from, array.to);
		else if (underlying instanceof double[])
			Arrays.sort((double[]) underlying, array.from, array.to);
		else if (underlying instanceof Object[])
			Arrays.sort((T[]) underlying, array.from, array.to);
		else if (underlying instanceof List)
			Collections.sort(((List<T>) underlying).subList(array.from, array.to));
		else
			throw new IllegalArgumentException();
		return array;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	public static<T> ArrayWrapper<T> sort(ArrayWrapper<T> array, Comparator<? super T> comparator) {
		Object underlying = array.underlying;
		if (underlying instanceof char[]) {
			Character[] copy = new Character[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((char[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Character>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((char[])underlying)[j] = copy[i];
		} else if (underlying instanceof int[]) {
			Integer[] copy = new Integer[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((int[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Integer>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((int[])underlying)[j] = copy[i];
		} else if (underlying instanceof long[]) {
			Long[] copy = new Long[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((long[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Long>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((long[])underlying)[j] = copy[i];
		} else if (underlying instanceof double[]) {
			Double[] copy = new Double[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((double[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Double>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((double[])underlying)[j] = copy[i];
		} else if (underlying instanceof Object[])
			Arrays.sort((T[]) underlying, array.from, array.to, comparator);
		else if (underlying instanceof List)
			Collections.sort(((List<T>) underlying).subList(array.from, array.to), comparator);
		else
			throw new IllegalArgumentException();
		return array;
	}

	public static<T> boolean isSubSequence(ArrayWrapper<T> array, ArrayWrapper<T> sample) {
		int index = 0;
		for (T element : array) {
			if (element.equals(sample.get(index))) {
				if (++index == sample.size)
					return true;
			}
		}
		return false;
	}

	public static Integer[] order(int size, Comparator<Integer> comparator) {
		Integer[] order = generateOrder(size);
		Arrays.sort(order, comparator);
		return order;
	}

	public static Integer[] order(final ArrayWrapper<? extends Comparable>...array) {
		return order(array[0].size, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				for (ArrayWrapper<? extends Comparable> anArray : array) {
					//noinspection unchecked
					int value = anArray.get(o1).compareTo(anArray.get(o2));
					if (value != 0)
						return value;
				}
				return 0;
			}
		});
	}

	public static void fill(int[][][] array, int value) {
		for (int[][] subArray : array)
			fill(subArray, value);
	}

	public static<T> Integer[] order(final ArrayWrapper<T> array, final Comparator<T> comparator) {
		return order(array.size, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return comparator.compare(array.get(o1), array.get(o2));
			}
		});
	}
}