package genetic;

import java.util.Arrays;


public class PathTour extends AbstractTour {

	public PathTour(int dimension, int[] tour) {
		super(dimension, tour);
	}
	
	public AdjTour transformIntoAdj() {
		
		int[] adjRepr = new int[tourLength];
		
		for(int i = 0; i < tourLength; i++) {
			int indexForAdj = tour[i];
			
			/* In the path representation, 
			 * the last city is supposed to be connected
			 * with the first city which stands at the index 0
			 * to build the Hamilton cycle.
			 */
			if(i == tourLength - 1) {
				adjRepr[indexForAdj] = tour[0];
			} else {
				adjRepr[indexForAdj] = tour[i + 1];
			}
					
		}
		
		return new AdjTour(tourLength, adjRepr);
		
	}
	
	/** 
	 * @param city
	 * @param cityIndex
	 * @return
	 */
	public int getLeftNeighborToCity(int city, int cityIndex) {
		
		checkIndexValidity(cityIndex);				
		
		int indexNeigborLeft = (cityIndex == 0) ? tourLength - 1 : cityIndex - 1;
					
		return this.tour[indexNeigborLeft];
	}
	
	/**
	 * 
	 * @param city
	 * @param cityIndex
	 * @return
	 */
	public int getRightNeighborToCity(int city, int cityIndex) {
		
		checkIndexValidity(cityIndex);				
				
		int indexNeigborRight = (cityIndex == tourLength - 1) ? 0 : cityIndex + 1;
		
		return this.tour[indexNeigborRight];
	}
	
	 public int[] getInPathAsArr() {
		 return this.tour;
	 }
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public int get(int index) {
		
		checkIndexValidity(index);
		return this.tour[index];
	}
	
	/**
	 * 
	 * @param city
	 * @return
	 */
	public int indexOf(int city) {
		for(int i = 0; i < tourLength; i++) {
			if(this.tour[i] == city) {
				return i;
			}
		}
		throw new IllegalArgumentException("The given city: "
							+ city + " is not in the tour");
	}
	
	/**
	 * 
	 * @param cityIndex
	 */
	private void checkIndexValidity(int cityIndex) {
		if(cityIndex < 0 || cityIndex > tourLength - 1) {
			throw new IllegalArgumentException("The given index " 
					+ cityIndex + " is not in the tour");
		}
	}
	
	
	@Override
	public String toString() {
		return "Tour [=" + Arrays.toString(tour) + "]";
	}

}
