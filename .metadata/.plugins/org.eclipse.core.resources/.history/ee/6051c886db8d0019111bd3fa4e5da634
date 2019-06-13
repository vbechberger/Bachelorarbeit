package util;

import java.util.ArrayList;

/**
 * Makes deep copy of the arrays
 * @author valeria
 *
 */
public class SaveCopy {
	
	private static int length = 0;


	public static void copy(int[] arr1, int[] arr2) {
		if (arr1.length == 0) {
			throw new RuntimeException("The first array is not initialized!");
		}
		if(arr1.length!= arr2.length) {
			throw new RuntimeException("Two arrays have different lengths! Cannot be copied!");
		}
		length = arr2.length;
		for (int i = 0; i < length; i++) {
			arr1[i] = arr2[i];
		}
	}
	
	public static void copy(int[] arr1, ArrayList<Integer> arr2) {
		if (arr1.length == 0) {
			throw new RuntimeException("The first array is not initialized!");
		}
		if(arr1.length!= arr2.size()) {
			throw new RuntimeException("Two arrays (array and array list) have different lengths! Cannot be copied!");
		}
		length = arr2.size();
		for (int i = 0; i < length; i++) {
			arr1[i] = arr2.get(i);
		}
	}
	
	public static void copy(ArrayList<Integer> arr1, int[] arr2) {
		if(arr2.length == 0) {
			throw new RuntimeException("The second array is empty!");
		}
		if(arr1.size() > arr2.length) {
			throw new RuntimeException("The first array list is longer than the second array!");
		}
		length = arr2.length;
		for (int i = 0; i < length; i++) {
			arr1.add(i, arr2[i]);
		}
	}
	public static void copy(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		if(arr2.size() == 0) {
			throw new RuntimeException("The second array list is empty!");
		}
		length = arr2.size();
		if(arr1.size() > arr2.size()) {
			throw new RuntimeException("The first array list is longer than the second one!");
		}
		for (int i = 0; i < length; i++) {
			arr1.add(i, arr2.get(i));
		}
	}
}
