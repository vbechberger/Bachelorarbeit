package heuristics;

import java.util.ArrayList;


import java.util.LinkedList;
import java.util.List;

import util.Pair;
import util.SafeCopy;

/**
 * An abstract class which represents insertion heuristics.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class AbstractInsertion extends  ConstructionHeuristic{
	
	
		
	/**
	 * Constructor.
	 * @param searchStrategy is the specified strategy how to make the insertion.
	 * @param distances is the given distance matrix in form of 2D array.
	 * @param startCityNumber is the number of the city where to start.
	 */
	public AbstractInsertion(OptimalSearchStrategy<Double> searchStrategy, 
							 double[][] distances, 
							 int startCityNumber) {
		super(searchStrategy, distances, startCityNumber);
	}
	
	/**
	 * Finds a tour, starting with the predefined city (Greedy)
	 *
	 */
	protected void findTour() {
		
		LinkedList<Integer> currentTour = new LinkedList<Integer>();
		
		//add the specified starting node to the part tour
		addToEndOfPartTourAndToUsedCities(currentTour, startCityNumber);
		
		//find the node, closest to this first node, and	
		Pair<Integer, Double> secCityAndDistance 
									= findOptNeighborAndDistTo(startCityNumber);
		int secondCity = secCityAndDistance.getFirst();
		
		//insert it after the first city
		addToEndOfPartTourAndToUsedCities(currentTour, secondCity);
		
		while(currentTour.size() < dimension) {
			/*Find a city, which is not is the part tour,
			 *that is the best in the specified criteria to any of the part tour cities.
			 */
			
			Pair<Integer, Integer> cityAndIndex = nextCityToInsertToPartTourAndIndex(currentTour);
			
			int cityToInsert = cityAndIndex.getFirst();
			int indexToInsert = cityAndIndex.getSecond();
			
			/*If it was found that the specified city has to be 
			 *insert between the last and the first city of the tour,
			 *we insert it at the end of the tour.
			 */
			if(indexToInsert == 0) {
				addToEndOfPartTourAndToUsedCities(currentTour, cityToInsert);
			} else {
				addToPartTourAtIndexAndToUsedCities(currentTour, 
												indexToInsert, 
												cityToInsert);
			}						
		}		
		SafeCopy.copy(resultingTour, currentTour);				
	}

	/**
	 * The method which is to be defined.
	 * @param currentTour is the current tour.
	 * @return the index and the city to be inserted at.
	 */
	protected abstract Pair<Integer, Integer> nextCityToInsertToPartTourAndIndex(LinkedList<Integer> currentTour);

	
	/**
	 * Adds the specified city to the part tour and the list of used cities
	 * @param currentTour
	 * @param startCityNumber
	 */
	private void addToEndOfPartTourAndToUsedCities(LinkedList<Integer> currentTour, 
												   int cityNumber) {
		currentTour.add(cityNumber);
		usedCities.add(cityNumber);		
	}
	
	/**
	 * Adds the given city to the current partial tour at the specified index
	 * and renews the list of used cities.
	 * @param currentTour is the current partial tour.
	 * @param index is the index to insert at.
	 * @param cityNumber is the city to be inserted.
	 */
	private void addToPartTourAtIndexAndToUsedCities(LinkedList<Integer> currentTour, 
													 int index, 
													 int cityNumber) {
		currentTour.add(index, cityNumber);
		usedCities.add(cityNumber);	
	}
	
	/**
	 * Finds the nearest not used city to the specified city.
	 * It means that the criteria "best" here is the
	 * nearest distance
	 * 
	 * @param cityNumber City, to which the nearest neighbor 
	 * 					 has to be found
	 */
	protected Pair<Integer, Double> findNearestNotUsedCityWithValueToCity(int cityNumber) {
		double nearestDistance = Double.MAX_VALUE;
		int nearestCity = cityNumber;
		for(int j = 0; j < distances.length; j++) {
			/*Do not consider the distance between the given city itself
			 *and the distances to the cities which 
			 *were already used
			 */
			if(j != cityNumber && !usedCities.contains(j)) {
				
				double nextDistance = distances[cityNumber][j];
			
				if(nextDistance < nearestDistance && nextDistance != 0) {
					nearestDistance = nextDistance;
					nearestCity = j;
				}
			}
			
		}
		
		return new Pair<Integer, Double> (nearestCity, nearestDistance);
	}
	/**
	 * Finds the pair of the cities of the existing part tour,
	 * so that the costs of inserting the specified city between this pair
	 * are the lowest
	 * 
	 * @param pairsOfUsedCities the list of pairs between 
	 * 							the neighbors which were already used in the tour
	 * @param city is the number of the city, the insertion 
	 * 					costs of which are to be calculated and compared.
	 * @param partTour the actual part tour
	 * 
	 * @return Pair<Integer, Double> the index, at which
	 * 								 the given city is to be inserted
	 * 								(= the index of the second city of the pair in the tour)
	 * 								 and the costs of insertion
	 */
	protected Pair<Integer, Double> findIndexOfPartTourToPutCityWhileMinInsertionCosts(
												int city, List<Integer> partTour) {
		
		//make list of pairs of neighbors
							
		ArrayList<Pair<Integer, Integer>> pairsOfUsedCities = makePairsBtwNeighborsInTour(partTour);
		
		double shortestDistance = Double.MAX_VALUE;
		int indexWhereToInsert = -1;
		for(Pair<Integer, Integer> pair: pairsOfUsedCities) {
			double nextCosts = insertionCostsOf(city, (int)pair.getFirst(), (int)pair.getSecond());
			
			if (nextCosts < shortestDistance) {
				
				shortestDistance = nextCosts;
				/*Define the number of the index, which the second city 
				 *in the considered pair 
				 *takes in the part tour,
				 *as we want to put the specified city between this pair.
				 */
				indexWhereToInsert = partTour.indexOf(pair.getSecond());
				
			}
			
		}
				
		if (indexWhereToInsert == -1) {
			
			throw new IllegalStateException("No city was found!");
		}
		
		return new Pair<Integer, Double>(indexWhereToInsert, shortestDistance);
	}
	
	/**
	 * Makes the pairs between all the cities of the provided list
	 * 
	 * @param partTour is the provided list.
	 * @return the list of pairs of the cities
	 */
	protected ArrayList<Pair<Integer,Integer>> makePairsBtwNeighborsInTour(List<Integer> partTour) {
		
		if(partTour == null || partTour.size() == 0) {
			throw new IllegalArgumentException("The given list is empty or does not exist!");
		}
		
		ArrayList<Pair<Integer, Integer>> pairsOfUsedCities = new ArrayList<Pair<Integer, Integer>>();
		
		//make a list of pairs of used cities 
		for(int i = 0; i < partTour.size(); i++) {
			
			//if we look at the last city in the last tour, make a pair of it with the first city
			if(i == partTour.size() - 1) {
				pairsOfUsedCities.add(new Pair<Integer, Integer>(partTour.get(i), partTour.get(0)));
			} else {
				pairsOfUsedCities.add(new Pair<Integer, Integer>(partTour.get(i), partTour.get(i + 1)));
			}			
		}
		
		return pairsOfUsedCities;
	}
	
	/**
	 * Calculates the costs of inserting the given city between the specified cities.
	 * @param cityToBeinserted is the city to be inserted.
	 * @param firstCityOfPartTour is the city of the tour to insert after.
	 * @param secCityOfPartTour is the city to insert before.
	 * @return the insertion cocts.
	 */
	private double insertionCostsOf(int cityToBeinserted, int firstCityOfPartTour, int secCityOfPartTour) {
		
		double distBtwCityAndFirstNeighbor = distances[cityToBeinserted][firstCityOfPartTour];
		
		double distBtwCityAndSecNeighbor = distances[cityToBeinserted][secCityOfPartTour];
		
		double distBtwPair = distances[firstCityOfPartTour][secCityOfPartTour];
		
		return distBtwCityAndFirstNeighbor + distBtwCityAndSecNeighbor - distBtwPair;
		
	}	

}

