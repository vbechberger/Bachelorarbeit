package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Printer {

	public static void printArray(int[] distances) {
		 System.out.println("Array of integers: ");		 
		 for(int i = 0; i < distances.length; i++) {			
			 System.out.print(" " + distances[i] + " ");
		 }
		 System.out.println();
	}
	
	public static void printArray2D(int [][] array) {
		 System.out.println("2D Array of integers: ");		 
		 for(int i = 0; i < array.length; i++) {
			 for(int j = 0; j < array[0].length; j++) {
				 System.out.print(" " + array[i][j] + " ");
			 }
			 System.out.println();			 
		 }
		 System.out.println();
	}
	
	public static void printArray2D(double [][] array) {
		 System.out.println("2D Array of doubles: ");		 
		 for(int i = 0; i < array.length; i++) {
			 for(int j = 0; j < array[0].length; j++) {
				 System.out.print(" " + array[i][j] + " ");
			 }
			 System.out.println();			 
		 }
		 System.out.println();
	}
	
	public static void printList(List<Integer> list) {
		 System.out.println("List of integers:");		 
		 for(Integer elem: list) {			
			 System.out.print(" " + elem + " ");
		 }
		 System.out.println();
	}
	
	public static void printHashMap(HashMap<Integer, Integer> map) {
		 System.out.println("HashMap of integers: index -> value");		 
		 for(Integer key: map.keySet()) {			
			 System.out.println("Key: " + key + ", value: " + map.get(key) + " ");
			 
		 }
		 System.out.println();
	}
	
	public static void printString(String string) {
		 System.out.println("///////" + string + "////////");		 			 
	}
	
	public static void printInt(Integer integer) {
		 System.out.println("Integer: " + integer + " ");		 			 
	}
	
	public static void printInt(int smallInt) {
		 System.out.println("int: " + smallInt + " ");		 			 
	}
	
	public static void printHashSet(HashSet<Integer> set) {
		System.out.println("Hashset of integers:");
		 for(Integer value: set) {
			 System.out.print(" " + value + " ");
		 }
		 System.out.println();
		 
	}

	public static void printArray(double [] array) {
		 System.out.println("Array of doubles: ");		 
		 for(int i = 0; i < array.length; i++) {			
			 System.out.print(" " + array[i] + " ");
		 }
		 System.out.println();
	}
	
	public static void printArrayListOfPairs(ArrayList<Pair<Integer,Integer>> list) {
		 System.out.println("Array of pairs: ");		 
		 for(int i = 0; i < list.size(); i++) {			
			 System.out.print(" (" + list.get(i).getFirst() + ", " + list.get(i).getSecond() + ") ");
		 }
		 System.out.println();
	}
	
	public static void printSet(Set<Integer> set) {
		 System.out.println("Set of integers:");		 
		 for(Integer elem: set) {			
			 System.out.print(" " + elem + " ");
		 }
		 System.out.println();
	}
	
	
}
