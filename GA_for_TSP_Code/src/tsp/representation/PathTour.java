package tsp.representation;

/**
 * Represents a tour in the path representation
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class PathTour extends AbstractTour {

	/**
	 * Builds a tour accroding to the given array and dimension
	 * @param dimension is the number of cities
	 * @param tour is the given array of cities
	 */
	public PathTour(int dimension, int[] tour) {
		super(dimension, tour);
	}
	
	/**
	 * Transforms this tour in the path representation into
	 * the tour in the adjacency representation.
	 * @return a tour in the adjacency representation.
	 */
	public AdjTour transformIntoAdj() {
		
		int[] adjRepr = new int[tourLength];
		
		for(int i = 0; i < tourLength; i++) {
			int indexForAdj = tour[i];
			
			/* In the path representation, 
			 * the last city is supposed to be connected
			 * with the first city which stands at the index 0
			 * to build the Hamiltonian cycle.
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
	 * @param cityIndex is the index of the city in the tour
	 * @return the left neighbor of the given city in the tour
	 */
	public int getLeftNeighborToCity(int cityIndex) {
		
		checkIndexValidity(cityIndex);				
		
		int indexNeigborLeft = (cityIndex == 0) ? tourLength - 1 : cityIndex - 1;
					
		return this.tour[indexNeigborLeft];
	}
	
	/** 
	 * @param cityIndex is the index of the city in the tour
	 * @return the right neighbor of the given city in the tour
	 */
	public int getRightNeighborToCity(int cityIndex) {
		
		checkIndexValidity(cityIndex);				
				
		int indexNeigborRight = (cityIndex == tourLength - 1) ? 0 : cityIndex + 1;
		
		return this.tour[indexNeigborRight];
	}
	
	 public int[] getInPathAsArr() {
		 return this.tour;
	 }
	
	/**
	 * 
	 * @param index is the given index
	 * @return a city at the specified index
	 */
	public int get(int index) {
		
		checkIndexValidity(index);
		return this.tour[index];
	}
	
	/**
	 * 
	 * @param city is the city the index of which is looked for
	 * @return the index of the specified city
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
	 * Checks the validity of the given index
	 * @param cityIndex is the given index
	 */
	private void checkIndexValidity(int cityIndex) {
		if(cityIndex < 0 || cityIndex > tourLength - 1) {
			throw new IllegalArgumentException("The given index " 
					+ cityIndex + " is not in the tour");
		}
	}
	
	
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < tour.length; i++) {
			if(i == tourLength - 1) {
				result = result + tour[i];
			} else {
				result = result + tour[i] + " ";
			}		
		}
		return result;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        PathTour pathTour = (PathTour) obj;
        
        return equalTours(this, pathTour);
    }

	/**
	 * The tours are considered to be equal if they have the same
     * cyclic tour, independent from the start city, i.e.:
	 * the tour 12345 is equal to the tour 23451
	 * 
	 * @param thisTour the first tour
	 * @param pathTour the second tour
	 * @return true if both tours have the same cyclic tour
	 * 		   false, otherwise
	 */
	private boolean equalTours(PathTour thisTour, PathTour pathTour) {
		
		/*If the given tours have different length, they are not equal*/
		if (thisTour.tourLength != pathTour.tourLength) {
			return false;
		}
		
		int[] first = thisTour.getTourAsArr();
		int[] second = pathTour.getTourAsArr();
		
		/*find the position at which the first city of 
		 *the first array stands in the second array
		 **/
		int indexInSecArr = findPos(first[0], second);
		for(int i = 0; i < first.length; i++) {
			
			if(first[i] != second[indexInSecArr]) {
				return false;
			}
			
			/*If we are at the end of the second array*/
			if(indexInSecArr == second.length - 1) {
				/*start the search from the beginning of it*/
				indexInSecArr = 0;
			} else {
				/*otherwise take the next position*/
				indexInSecArr++;
			}
			
		}
		return true;		
		
	}
	
	/**
	 * Finds the index at which the specified city stands in the given array
	 * @param city is the city to be found
	 * @param array is the array where this city is searched for
	 * @return the index which the specified city takes in the specified array
	 * @throws IllegalStateException, if the given city was not found in the
	 * 								  specified array.
	 */
	private int findPos(int city, int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] == city) {
				return i;
			}
		}
		throw new IllegalStateException("The second array has no city with the number: " + city);
	}
	
	@Override
    public int hashCode() {
        return tour.hashCode();
    }

}
