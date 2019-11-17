package heuristics;

import java.util.HashSet;
import java.util.Set;

import genetic.DistanceTable;

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
public class NearestNeighbor {
	
	private int startCityNumber;
	private double[][] distances;
	private int[] tour;
	private Set<Integer> usedCities = new HashSet<Integer>();;

	public NearestNeighbor(double[][] distances, int startCityNumber) {
		
		
		setDistances(distances);
		setStartCityNumber(startCityNumber);
		tour = new int[distances.length];
		tour = findTour(startCityNumber);
	}
	
	private void setStartCityNumber(int startCityNumber) {
		if(startCityNumber < 0 || startCityNumber >= distances.length) {
			throw new IllegalArgumentException("the start city "
					+ "has illegal index: " + startCityNumber);
		}
		this.startCityNumber = startCityNumber;
		
	}

	private void setDistances(double[][] distances) {
		if (distances.length != distances[0].length) {
			throw new IllegalArgumentException("the matrix "
					+ "with distances has to be quadratic.");
		}
		this.distances = distances;
		
	}

	/**
	 * Finds a tour, starting with the city 0(Greedy)
	 * @param startCityNumber 
	 * @param distanceTable
	 */
	private int[] findTour(int startCityNumber) {
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
		
		return tour;
		
	}

	private int findTheNearestCityTo(int cityNumber) {
		
		double minimum = Double.MAX_VALUE;
		int nearestCity = cityNumber;
		for(int j = 0; j < distances.length; j++) {
			//do not consider the distance between the given city itself
			//and the distances to the cities which 
			//were already used
			if(j != cityNumber && !usedCities.contains(j)) {
				
				double nextDistance = distances[cityNumber][j];
			
				if(nextDistance < minimum && nextDistance != 0) {
					minimum = nextDistance;
					nearestCity = j;
				}
			}
			
		}
		return nearestCity;
	}

	/**
	 * Returns a solution tour
	 */
	public int[] getTour() {
		
		return this.tour;
		
	}
}
