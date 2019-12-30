package genetic;


/**
 * Represents a solution of the TSP,
 * always in the path represenattion
 */

//import util.SaveCopy;

public class Solution {
	
	private final int dimension;
	private PathTour tour;

	public Solution(int dimension, int[] tourInArray) {
		
		this.dimension = dimension;	
		tour = new PathTour(dimension, tourInArray);
			
	}
	
	public int getDimension() {
		return dimension;
	}

	@Override
	public String toString() {
		return "Solution [dimension=" + dimension + ", tour=" + tour + "]";
	}
	
	public PathTour getTour() {
		return this.tour;
	}	
}
