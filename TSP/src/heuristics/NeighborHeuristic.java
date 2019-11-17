package heuristics;

import java.util.HashSet;
import java.util.Set;

public abstract class NeighborHeuristic {

	protected int startCityNumber;
	protected double[][] distances;
	protected int[] tour;
	protected Set<Integer> usedCities = new HashSet<Integer>();
	
	public NeighborHeuristic(double[][] distances, int startCityNumber) {
		//TODO:check for negative distances
		setDistances(distances);
		setStartCityNumber(startCityNumber);
		tour = new int[distances.length];
		findTour();
		
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
		//TODO: check valid values
		this.distances = distances;
		
	}
	
	protected int findTheNearestCityTo(int cityNumber) {
		
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
	 * Finds a tour, starting with the predefined city (Greedy)
	 */
	protected abstract void findTour();
	
	/**
	 * Returns a solution tour
	 */
	public int[] getTour() {
		
		return this.tour;
		
	}

}
