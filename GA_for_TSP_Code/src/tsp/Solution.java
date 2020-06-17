package tsp;

import tsp.representation.PathTour;

/**
 * Represents a solution tour of the TSP
 * It always encodes a tour in the path representation
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class Solution {
	
	private final int dimension;
	private PathTour tour;

	/**
	 * Makes a solution tour according to the tour which
	 * is given in the form of array.
	 * @param dimension is the number of cities
	 * @param tourInArray tour in from of an array
	 */
	public Solution(int dimension, int[] tourInArray) {
		
		this.dimension = dimension;	
		tour = new PathTour(dimension, tourInArray);
			
	}
	
	/**
	 * Makes a solution tour according to the tour which
	 * is given in the form of the PathTour
	 * @param dimension is the number of cities
	 * @param path is the PathTour
	 */
	public Solution(int dimension, PathTour path) {
		
		this.dimension = dimension;	
		this.tour = path;
	}
	
	
	/**
	 * 
	 * @return the dimension of the TSP (= number of cities)
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * 
	 * @return the tour in the path representation as an PathTour object
	 */
	public PathTour getTour() {
		return this.tour;
	}
	
	
	@Override
	public String toString() {
		return tour.toString();
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Solution solution = (Solution) obj;
        
        return this.tour.equals(solution.tour);
    }
	
	@Override
    public int hashCode() {
        return tour.hashCode();
    }
}
