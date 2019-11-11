package util;

import java.util.ArrayList;

/**
 * Makes deep copy of the arrays
 * @author valeria
 *
 */
public class SaveCopy {
	
	private static int length = 0;


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
	
	public static void copy(int[] arr1, ArrayList<Integer> arr2) {
	
		if(arr1.length != arr2.size()) {
			throw new RuntimeException("The arrays have the different length!");
		}
		
		if (arr2.size() == 0) {
			throw new RuntimeException("The second array is empty!");
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
		if(arr2 != null) {
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
	/*public static HashSet<Integer> copy(HashSet<Integer> set) {
		if(set != null) {
			if(set.isEmpty()) {
				throw new RuntimeException("The set to be copied is empty!");
			}
			HashSet<Integer> copiedSet = new HashSet<Integer>();
			copiedSet.addAll(set);
			
		}
	}*/
	
}
