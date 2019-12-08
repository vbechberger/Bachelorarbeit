package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.SaveCopy;

//import util.SaveCopy;

public class Solution {
	
	private int[] tour;
	private final int dimension;

	public Solution(int dimension, int[] tour) {
		this.dimension = dimension;
		if(tour.length != dimension) {
			throw new IllegalArgumentException("The given tour is too short!");
		}
		setTour(tour);
	}

	public int[] getTour() {
		return tour;
	}

	public void setTour(int[] tour) {
		this.tour = new int[dimension];
		SaveCopy.copy(this.tour, tour);
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < tour.length; i++) {
			result = result + tour[i] + " ";
		}
		return result;		
	}
	
	public boolean isFeasible() {
		
		List<Integer> tempTour = new ArrayList<Integer>();
		
		SaveCopy.copy(tempTour, tour);

		Collections.sort(tempTour);

		for (int i = 0; i < dimension; i++) {
			if (tempTour.get(i) != i) {
				return false;
			}
		}
		tempTour = null;

		return true;

	}

}
