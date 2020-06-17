package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Makes deep copy of the given data structures.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class SafeCopy {
	
	/**
	 * Length of the array/list
	 */
	private static int length = 0;

	/**
	 * Copies one 2D array to the other.
	 * @param arr1 is the array where the copy is made.
	 * @param arr2 is the array to be copied.
	 */
	public static void copy(double[][] arr1, double[][] arr2) {
		if(arr1.length != arr2.length) {
			throw new RuntimeException("The arrays have the different length!");
		}
		
		if (arr2.length == 0) {
			throw new RuntimeException("The second array is empty!");
		}
		
		length = arr2.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
		
	}
	
	/**
	 * Copies one 1D array to the other.
	 * @param arr1 is the array where the copy is made.
	 * @param arr2 is the array to be copied.
	 */
	public static void copy(int[] arr1, int[] arr2) {
		
		if(arr1.length != arr2.length) {
			throw new RuntimeException("The arrays have the different length!");
		}
		
		if (arr2.length == 0) {
			throw new RuntimeException("The second array is empty!");
		}
		
		length = arr2.length;
		
		for (int i = 0; i < length; i++) {
			arr1[i] = arr2[i];
		}
	}
	
	/**
	 * Copies a list of integers into the array.
	 * @param arr is the array where the copy is made.
	 * @param list is the list to be copied.
	 */
	public static void copy(int[] arr, List<Integer> list) {
	
		if(arr.length != list.size()) {
			throw new RuntimeException("The arrays have the different length!");
		}
		
		if (list.size() == 0 || list == null) {
			throw new RuntimeException("The second array is empty or does not exist!");
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
	}	
	
	/**
	 * Copies the given array into the list of integers
	 * @param list is the list where the copy is made.
	 * @param arr is the array which is to be copied.
	 */
	public static void copy(List<Integer> list, int[] arr) {
		if(arr.length == 0) {
			throw new RuntimeException("The second array is empty!");
		}
		if(list.size() > arr.length) {
			throw new RuntimeException("The first array list is longer than the second array!");
		}
		length = arr.length;
		for (int i = 0; i < length; i++) {
			list.add(i, arr[i]);
		}
	}
	
	/**
	 * Copies the given list into the other list of integers
	 * @param list1 is the list where the copy is made.
	 * @param list2 is the array which is to be copied.
	 */
	public static void copy(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		if(list2 != null) {
			if(list2.size() == 0) {
				throw new RuntimeException("The second array list is empty!");
			}
			length = list2.size();
			if(list1.size() > list2.size()) {
				throw new RuntimeException("The first array list is longer than the second one!");
			}
			for (int i = 0; i < length; i++) {
				list1.add(i, list2.get(i));
			}
		}
	}
	
	/**
	 * Copies the given set into the other set of integers
	 * @param set1 is the set where the copy is made.
	 * @param set2 is the set which is to be copied.
	 */
	public static void copy(HashSet<Integer>set1, HashSet<Integer> set2) {
		if(set1 == null) {
			throw new IllegalArgumentException("The first set, is not initialized");
		}
		if(set2 == null || set2.isEmpty()) {

			throw new RuntimeException("The second set to be copied is empty or null!");
		}
		
		set1.addAll(set2);
	}
	
}
