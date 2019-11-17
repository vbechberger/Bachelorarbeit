package heuristics;


/**
 * Represents a nearest-neighbor heuristic, 
 * which looks for a city in a given set of cities
 * that is closest to the city, which was visited at last.
 * 
 * It needs a distance table
 *  
 * @author valeriyabechberger
 *
 */
public class NearestNeighbor extends NeighborHeuristic{

	public NearestNeighbor(double[][] distances, int startCityNumber) {
		super(distances, startCityNumber);
	}

	/**
	 * Finds a tour, starting with the predefined city (Greedy)
	 */
	protected void findTour() {
		tour[0] = startCityNumber;
		usedCities.add(startCityNumber);
		 
		int lastVisitedCity = startCityNumber;
		//count how many cities are already in the tour
		int count = 1;
		while(count < tour.length) {
			
			/*find the city which is the nearest to the
			 * city, which was visited last*/
			int nextCity = findTheNearestCityTo(lastVisitedCity);
			
			/*Save this city in the tour*/
			tour[count] = nextCity;
			
			usedCities.add(nextCity);
			
			/*now this city becomes the last visited one*/
			lastVisitedCity = nextCity;
			
			count++;
		}	
	}

}
