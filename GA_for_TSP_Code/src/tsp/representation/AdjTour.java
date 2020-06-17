package tsp.representation;

import java.util.ArrayList;
import java.util.HashSet;

import util.SafeCopy;

/**
 * Represents a tour in the adjacency representation.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 */
public class AdjTour extends AbstractTour{

	/**
	 * Constructor
	 * @param dimension is the length of the tour
	 * @param tour is the tour in the form of array
	 */
	public AdjTour(int dimension, int[] tour) {
		super(dimension, tour);
		checkHamiltonianCycle(this.tour);
	}
	
	/**
	 * Checks whether the given adjacency tour builds a Hamiltonian cycle
	 * @param adjRepr a tour in the adjacency representation
	 * @return true, if the given tour builds the Hamiltonian cycle
	 * 			false, otherwise.
	 */
	private boolean checkHamiltonianCycle(int[] adjRepr) {
		
		HashSet<Integer> used = new HashSet<Integer>();
		
		int index = 0;
		used.add(index);
		int steps = 1;
		while (steps != this.tourLength) {
			index = adjRepr[index];
			if(!used.contains(index)) {
				used.add(index);
				steps++;
			} else {
				throw new IllegalArgumentException("The given adjacency tour"
						+ "does not build a hamilton cycle!");
			}			
		}
		return true;
	}
	 
	
	/**
	 * Transforms this tour in the adjacency representation into the path representation.
	 * @return the tour in the path representation
	 */
	 public PathTour transformToPathTour() {
				
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		
		/*As the adjacency representation does not strictly 
		 *define which city is the first, we assume
		 *that the first city is 0
		 */
		
		pathList.add(0);
		
		int index = 0;
		int steps = 1;
		while (steps != tourLength) {
			pathList.add(tour[index]);
			index = tour[index];
			steps++;
		}
		
		int [] path = new int[tourLength];
		
		SafeCopy.copy(path, pathList);
		
		return new PathTour(tourLength, path);
		
	}
	 
	 /**
	  * Returns the tour in the path representation in the form of the array
	  * @return the tour in the path representation in the form of the array
	  */
	 public int[] getInPathAsArr() {
		 return this.transformToPathTour().tour;
	 }
	 
	 @Override
		public String toString() {
		 	
			return this.transformToPathTour().toString();
		}


}
