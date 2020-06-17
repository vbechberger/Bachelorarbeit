package heuristics;

import java.util.ArrayList;
import java.util.LinkedList;

import util.Pair;



/**
 * Represents the nearest-insertion and farthest-insertion heuristics,
 * depending on the chosen insertion type
 * 
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class DistanceInsertion extends AbstractInsertion{
	

	public DistanceInsertion(DistanceInsertionType insertionType,
							double[][] distances, 
							int startCityNumber) {
		super(insertionType.getSearchStrategy(), distances, startCityNumber);
	}

	
	protected Pair<Integer, Integer> nextCityToInsertToPartTourAndIndex(LinkedList<Integer> currentTour) {
		
		ArrayList<Pair<Integer, Double>> notUsedCitiesWithTheirNearestDistToPartTour 
		= new ArrayList<Pair<Integer, Double>>();
		
		/*For each not used city find the nearest neighbor in the part tour	
		 *and calculate the distance to this neighbor
		 *save the pairs of unused cities with their nearest distances to the part tour.
		 */
		for(int i = 0; i < dimension; i++) {			
			if(!usedCities.contains(i)) {
				double dist = findNearestDistanceToPartTourOfNotUsedCity(i);
				notUsedCitiesWithTheirNearestDistToPartTour.add(new Pair<Integer, Double>(i, dist));
			}
		}
		
		/*Find in the list of unused city the one with the optimal distance:
		 *smallest distance for the nearest insertion heuristic and 
		 *farthest distance for the farthest insertion heuristic.
		 */		
		int nextCityToInsert = findCityOfOptDistance(notUsedCitiesWithTheirNearestDistToPartTour);
		
		int index = findIndexToPutNextCityBest(nextCityToInsert, currentTour);

		return new Pair<Integer, Integer>(nextCityToInsert, index);
	}	
	/**
	 * Calculates the distance of the nearest neighbor in the part tour for the given unused city
	 * @param notUsedCity is the given not used city.
	 * @return the distance to the nearest neighbor which is in the current partial tour
	 */
	private double findNearestDistanceToPartTourOfNotUsedCity(int notUsedCity) {
		double closestDistance = Double.MAX_VALUE;
		int nearestNeighbor = -1;
		for(Integer usedCity: usedCities) {
			double nextDistance = distances[notUsedCity][usedCity]; 
			if( nextDistance < closestDistance && closestDistance != 0) {
				closestDistance = nextDistance;
				nearestNeighbor = usedCity;
			}
		}
		if(nearestNeighbor == -1) {
			throw new IllegalStateException("No nearest neighbor "
					+ "in the list of used cities was found for the given unused city");
		}
		
		return closestDistance;		
	}
	
	/**
	 * Gives the city with the optimal distance 
	 * Whether the smaller or the larger distance is considered more optimal 
	 * is defined by the used search strategy.
	 * @param list of pairs: city + distance
	 * @return the city with the optimal distance.
	 */
	protected int findCityOfOptDistance(ArrayList<Pair<Integer, Double>> list ) {
		
		if(list == null || list.isEmpty()) {
			throw new IllegalArgumentException("The given list"
					+ " of cities is empty or does not esxist!");
		}
		
		double optDistance = 0;
		int city = -1;
		for(Pair<Integer, Double> cityWithValue: list) {
			
			double nextDistance = cityWithValue.getSecond();
			
			if (searchStrategy.firstIsMoreOpt(nextDistance, optDistance) || optDistance == 0) {
				optDistance = nextDistance;
				city = cityWithValue.getFirst();
			}
		}		
		if(city == -1) {
			throw new IllegalStateException("No city was found!");
		}		
		return city;		
	}
	
	/**
	 * Finds the index in the tour, where the specified city
	 * has to be inserted:
	 * In detail: Finds a pair of cities, which is in the tour, 
	 * between which the city k will be inserted, so that
     * the insertion costs are the minimal. 
     * Then it defines the index of the second city of the pair,
     * which this city takes in the part tour, as the
     * specified city has to be inserted between the found pair.
  	 * 
  	 * @param nextCity The number of city to be inserted in the
  	 * 					existing tour
  	 * 
  	 * @return index to put the given city at.
	 * 
	 */
	private int findIndexToPutNextCityBest(int nextCity, 
										   LinkedList<Integer> currentTour) {
		
		Pair<Integer, Double> indexAndCosts = 
				findIndexOfPartTourToPutCityWhileMinInsertionCosts(nextCity,
																   currentTour);				
		return indexAndCosts.getFirst();
	}	
}