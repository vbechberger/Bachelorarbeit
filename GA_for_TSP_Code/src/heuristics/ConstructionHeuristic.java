package heuristics;

import java.util.HashSet;
import java.util.Set;

import util.Pair;

/**
 * An abstract class which represents construction heuristic.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class ConstructionHeuristic {

	/**
	 * The number of the city, with which we start to build the tour
	 */
	protected int startCityNumber;
	
	/**
	 * Distance matrix
	 */
	protected double[][] distances;
	
	/**
	 * The length of final tour
	 */
	protected final int dimension;
	
	/**
	 * The resulting tour
	 */
	protected int[] resultingTour;
	
	/**
	 * The cities which have been used at a moment
	 */
	protected Set<Integer> usedCities = new HashSet<Integer>();
	
	protected OptimalSearchStrategy<Double> searchStrategy;
	
	
	/**
	 * Constructor
	 * @param searchStrategy is the given strategy
	 * @param distances is the distance matrix.
	 * @param startCityNumber is the city to start with.
	 */
	public ConstructionHeuristic(OptimalSearchStrategy<Double> searchStrategy,
								 double[][] distances, 
								 int startCityNumber) {

		this.searchStrategy = searchStrategy;
		dimension = distances.length;
		setDistances(distances);
		setStartCityNumber(startCityNumber);
		resultingTour = new int[dimension];
		findTour();
		
	}
	
	/**
	 * Finds a tour, starting with the predefined city (Greedy)
	 *
	 */
	protected abstract void findTour(); 
	
	
	/**
	 * Finds the most optimal not used city to the specified city.
	 * It means that the criteria "optimal" here will
	 * be defined by the child classes
	 * 
	 * @param cityNumber City, to which the most optimal neighbor 
	 * 					 has to be found
	 * @return the most optimal neighbor with the distance.
	 */
	protected Pair<Integer, Double> findOptNeighborAndDistTo(int city) {
		double optDistance = 0;
		int optCity = city;
		for(int j = 0; j < distances.length; j++) {
			/*Do not consider the distance between the given city itself
			 *and the distances to the cities which 
			 *were already used.
			 */
			if(j != city && !usedCities.contains(j)) {
				
				double nextDistance = distances[city][j];
			
				/*If the next distance is more optimal than the 
				 *optimal distance at the moment, or if the optimal distance
				 *still has its start value zero, providing the next distance 
				 *is not zero itself, then remember the next distance as the optimal
				 *one at the moment and the number of the neighbor city.
				 */
				if((searchStrategy.firstIsMoreOpt(nextDistance, optDistance) || optDistance == 0) 
																&& nextDistance != 0) {				
					optDistance = nextDistance;
					optCity = j;
				}
			}			
		}
		
		return new Pair<Integer, Double> (optCity, optDistance);
	}
						
							/** Setter methods*/
	
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
							/** Getter methods*/
	
	/**
	 * Returns a solution tour
	 */
	public int[] getTour() {
		
		return this.resultingTour;
		
	}
	
	public double getWholeCosts() {
		
		double sum = 0;
		for(int i = 0; i < resultingTour.length - 1; i++ ) {
			int city1 = resultingTour[i];
			int city2 = resultingTour[i + 1];
			sum = sum + distances[city1][city2];
		}
		int lastCity = resultingTour[resultingTour.length - 1];
		sum = sum + distances[lastCity][resultingTour[0]];
		
		return sum;
	}
}
