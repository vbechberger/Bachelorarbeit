package tsp.representation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.SafeCopy;

/**
 * Represents an abstract tour which contains cities starting with 0
 * to (dimension - 1)
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public abstract class AbstractTour {
	
	protected int[] tour;
	
	protected final int tourLength;

	/**
	 * Constructor
	 * @param dimension is the desired dimension of the tour
	 * @param tour is the tour in form of array
	 */
	public AbstractTour(int dimension, int[] tour) {
		
		this.tourLength = dimension;		
		checkTour(tour);
		this.tour = new int[tourLength];		
		SafeCopy.copy(this.tour, tour);	
	}
	
	/**
	 * Any type of the tour representation which inherits from this class has to 
	 * be able to be transformed into a tour in the path representation
	 * @return the tour as an array in the path representation
	 */
	public abstract int[] getInPathAsArr();
	
	
	/**
	 * Checks the feasibility of the given tour.
	 * @param tour is the tour to be checked.
	 */
	private void checkTour(int[] tour) {
		if(tour.length != this.tourLength) {
			throw new IllegalArgumentException("The given tour is too short!");
		}
		if(!isFeasibleTour(tour)) {
			throw new IllegalStateException("The obtained solution: " 
					+ this + " is not feasible!");
		} 
	}
		
	/**
	 * Checks if the tour is consistent and contains all the cities.
	 * @param tour is the tour to be checked
	 * @return true if the given tour is feasible
	 * 		   false, otherwise
	 */
	private boolean isFeasibleTour(int[] tour) {
		
		List<Integer> tempTour = new ArrayList<Integer>();
		
		SafeCopy.copy(tempTour, tour);

		Collections.sort(tempTour);

		for (int i = 0; i < tourLength; i++) {
			if (tempTour.get(i) != i) {
				return false;
			}
		}
		tempTour = null;

		return true;

	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tourLength;
		result = prime * result + Arrays.hashCode(tour);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTour other = (AbstractTour) obj;
		if (tourLength != other.tourLength)
			return false;
		if (!Arrays.equals(tour, other.tour))
			return false;
		return true;
	}


	/**Getter methods*/
	
	public int getLength() {
		return this.tourLength;
	}
	
	/**
	 * 
	 * @return the tour in the corresponding representation
	 */
	public int[] getTourAsArr() {
		return this.tour;
	}	

}
