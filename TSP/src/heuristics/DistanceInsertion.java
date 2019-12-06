package heuristics;

import java.util.ArrayList;
import java.util.LinkedList;

import util.Pair;



/**
 * Represents the nearest-insertion and farthest-insertion heuristics,
 * depending on the chosen insertion type
 * 
 * 
 * @author valeriyabechberger
 *
 */
public class DistanceInsertion extends AbstractInsertion{
	

	public DistanceInsertion(double[][] distances, 
							int startCityNumber, 
							DistanceInsertionType insertionType) {
		//TODO:check for negative distances and for quadratic matrix
		super(distances, startCityNumber, insertionType.getSearchStrategy());
	}

	
	protected Pair<Integer, Integer> nextCityToInsertToPartTourAndIndex(LinkedList<Integer> currentTour) {
		
		ArrayList<Pair<Integer, Double>> notUsedCitiesWithTheirNearestDistToPartTour 
		= new ArrayList<Pair<Integer, Double>>();
		
		//for each not used city find the nearest neighbor in the part tour	
		//and calculate the distance to this neighbor
		//save the pairs of unused cities with their hearest distances to the part tour
		for(int i = 0; i < dimension; i++) {			
			if(!usedCities.contains(i)) {
				double dist = findNearestDistanceToPartTourOfNotUsedCity(i);
				notUsedCitiesWithTheirNearestDistToPartTour.add(new Pair<Integer, Double>(i, dist));
			}
		}
		
		//Find in the list of unused city the one with the optimal distance:
		//smallest distance for the nearest insertion heuristic and 
		//farthest distance for the farthest insertion heuristic		
		int nextCityToInsert = findCityOfOptDistance(notUsedCitiesWithTheirNearestDistToPartTour);
		
		int index = findIndexToPutNextCityBest(nextCityToInsert, currentTour);

		return new Pair<Integer, Integer>(nextCityToInsert, index);
	}	
	/**
	 * Find the nearest neighbor in the part tour for the given unused city
	 * @param notUsedCity
	 * @return
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
	 */
	private int findIndexToPutNextCityBest(int nextCity, 
										   LinkedList<Integer> currentTour) {
		
		Pair<Integer, Double> indexAndCosts = 
				findIndexOfPartTourToPutCityWhileMinInsertionCosts(nextCity,
																   currentTour);				
		return indexAndCosts.getFirst();
	}

	
}