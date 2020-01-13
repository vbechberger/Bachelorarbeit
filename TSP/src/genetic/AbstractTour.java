package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.SaveCopy;

public abstract class AbstractTour {
	
	protected int[] tour;
	
	protected final int tourLength;

	public AbstractTour(int dimension, int[] tour) {
		
		this.tourLength = dimension;		
		checkTour(tour);
		this.tour = new int[tourLength];		
		SaveCopy.copy(this.tour, tour);	
	}
	
	 public abstract int[] getInPathAsArr();
	
	
	private void checkTour(int[] tour) {
		if(tour.length != this.tourLength) {
			throw new IllegalArgumentException("The given tour is too short!");
		}
		if(!isFeasibleTour(tour)) {
			throw new IllegalStateException("The obtained solution: " 
					+ this + " is not feasible!");
		} 
	}
		
	private boolean isFeasibleTour(int[] tour) {
		
		List<Integer> tempTour = new ArrayList<Integer>();
		
		SaveCopy.copy(tempTour, tour);

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
	
	public int[] getTourAsArr() {
		return this.tour;
	}
	
	
	

}
