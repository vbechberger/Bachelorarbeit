package heuristics;

import util.Pair;

/**
 * Represents a nearest-neighbor heuristic, 
 * which looks for a city
 * that is closest to last city of the tour, 
 * and insert this new city in the tour after it.
 * 
 * It needs a distance table
 *  
 * @author valeriyabechberger
 *
 */
public class NearestNeighbor extends ConstructionHeuristic{

	public NearestNeighbor(double[][] distances, int startCityNumber) {
		super(new NearestStrategy(), distances, startCityNumber);
	}

	/**
	 * Finds a tour, starting with the predefined city (Greedy)
	 */
	protected void findTour() {
		resultingTour[0] = startCityNumber;
		usedCities.add(startCityNumber);
		 
		int lastVisitedCity = startCityNumber;
		//count how many cities are already in the tour
		int count = 1;
		while(count < resultingTour.length) {
			
			/*find the city which is the nearest to the
			 * city, which was visited last*/
			Pair<Integer, Double> cityAndDist = findOptNeighborAndDistTo(lastVisitedCity);
			int nextCity = cityAndDist.getFirst();
			
			/*Save this city in the tour*/
			resultingTour[count] = nextCity;
			
			usedCities.add(nextCity);
			
			/*now this city becomes the last visited one*/
			lastVisitedCity = nextCity;
			
			count++;
		}	
	}

}
