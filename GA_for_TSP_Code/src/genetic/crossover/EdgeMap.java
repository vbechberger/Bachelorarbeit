package genetic.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import tsp.representation.PathTour;
import util.Printer;
import util.SafeCopy;

/**
 * Represents a special data structure which the ERX uses.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class EdgeMap {
	
	
	/**
	 * Edge map data structure:
	 * For each city, it contains a list of its neighbors which
	 * are either in the first or in the second parent.
	 */
	private HashMap<Integer, HashSet<Integer>> edgeMap = new HashMap<Integer, HashSet<Integer>>();
	
	/**A map, where a city is mapped to the number of its neighbors.*/
	private HashMap<Integer, Integer> nextCandidateCitiesWithNumberOfNeighbors = new HashMap<Integer, Integer>();
	
	/**The Random object*/
	private Random rand;

	/**
	 * Constructor.
	 * Creates an edge map using the given parent tours.
	 * @param firstParent is the first parent tour in path representation.
	 * @param secondParent is the second parent tour in path representation.
	 * @param rand is the Random object.
	 */
	public EdgeMap(PathTour firstParent, PathTour secondParent, Random rand) {
		fillIn(firstParent, secondParent);
		setRandom(rand);		
	}

	private void fillIn(PathTour firstParent, PathTour secondParent) {
		
		/*For each city make a list of its neighbors on both parents
		 *Note: the first city is always connected to the last city to 
		 *represent a round tour.
		 */
		for(int i = 0; i < firstParent.getLength(); i++) {

			/*Make a new list for neighbors*/
			HashSet<Integer> neighbors = new HashSet<Integer>();

			int indexofCityInPar1 = firstParent.indexOf(i);

			int one = firstParent.getLeftNeighborToCity(indexofCityInPar1);
			neighbors.add(one);
			int two = firstParent.getRightNeighborToCity(indexofCityInPar1);
			neighbors.add(two);

			int indexofCityInPar2 = secondParent.indexOf(i);

			int three = secondParent.getLeftNeighborToCity(indexofCityInPar2);
			neighbors.add(three);
			int four = secondParent.getRightNeighborToCity(indexofCityInPar2);
			neighbors.add(four);

			/*Put the list of neighbors into the map to the corresponding city*/
			edgeMap.put(i, neighbors);	
			nextCandidateCitiesWithNumberOfNeighbors.putIfAbsent(i, neighbors.size());
		}

	}
	
	/**
	 * This method goes through the cities which have not been used
	 * yet and compares their lists of neighbors. And finally
	 * it returns the city with the shortest list of neighbors,
	 * or picks a random city if there are more cities with the same minimal
	 * number of neighbors. It picks a city among them.
	 * 
	 * @return the city with the shortest list of neighbors.
	 * 
	 */
	public int getCityWithMinEdges() {
		
		/*First, find the first city with the minimum 
		 *number of neighbors.
		 */
		int minimum = Integer.MAX_VALUE;
				
		int cityWithMin = -1;
		
		/*Extract the cities from the map. These cities are potential candidates to
		 *extend the tour.
		 */
		Set<Integer> cities = nextCandidateCitiesWithNumberOfNeighbors.keySet();
		
		/*Find a city with the smallest number of neighbors,
		 * but not 0.
		 */
		for(Integer nextCity: cities) {			
			if(nextCandidateCitiesWithNumberOfNeighbors.get(nextCity) < minimum) {
				minimum = nextCandidateCitiesWithNumberOfNeighbors.get(nextCity);
				cityWithMin = nextCity;
			}			
		}
		
		if(cityWithMin == -1) {
			throw new IllegalStateException("No city was found.");
		}
		
		/*Now there can be more cities with the same value,
		 *put them all in the candidate list.
		 */
		
		ArrayList<Integer> citiesWithSameNeighborsNumber = new ArrayList<Integer>();
		
		for(Integer nextCity: cities) {			
			if(nextCandidateCitiesWithNumberOfNeighbors.get(nextCity) == minimum) {
				citiesWithSameNeighborsNumber.add(nextCity);
			}			
		}
		
		if(citiesWithSameNeighborsNumber.isEmpty()) {
			throw new IllegalStateException("No city was found.");
		}
		
		/*We take a random city from among these cities
		 * which have the same number of neighbors.
		 */
		Collections.shuffle(citiesWithSameNeighborsNumber, rand);
		
		return citiesWithSameNeighborsNumber.get(0);
	}
	
	/**
	 * After the city has been chosen to extend the tour,
	 * we have to update our lists of cities which can be used next
	 * and this method does it.
	 *  
	 * @param city is the city which has been chosen to extend the tour 
	 * 				and which has to be
	 * 				excluded from the edge map and the list of potential candidates.
	 */
	public void renewListOfCandidatesAfterTheCityChosen(int city) {
		
		/*If the city was the last in the list: i.e.
		 *its list of neighbors is empty and 
		 *there are no other cities in the edge map:
		 */

		if(edgeMap.get(city).size() == 0 && edgeMap.keySet().size() == 1) {
			
			removeCityAndItsOccurencesFromMap(city);
			
			return;
		}
		
		/*If the city is not the last one , but 
		 *its list of neighbors is empty:
		 */
		if(edgeMap.get(city).size() == 0 && edgeMap.keySet().size() > 1) {
			
			removeCityAndItsOccurencesFromMap(city);
			
		
			if (nextCandidateCitiesWithNumberOfNeighbors.keySet().size() > 1) {
				
				/*Remove this city from candidates list, but do not 
				 *clear the list so that we can work further with the remaining cities
				 */				
				nextCandidateCitiesWithNumberOfNeighbors.keySet().remove(city);
			} else {
				
				/*If there are no remaining cities, clear the list*/
				nextCandidateCitiesWithNumberOfNeighbors.clear();
				
				/*If there is no other candidates in the list,
				 *start with the next city in the edge map with 
				 *the smallest list: I.E.
				 *we have to fill nextCandidateCitiesWithNumberOfNeighbors
				 *from the remaining cities in the edge map
				 */
				for(Integer key: edgeMap.keySet()) {					
					int numbOfNeighbors = edgeMap.get(key).size();
					nextCandidateCitiesWithNumberOfNeighbors.putIfAbsent(key, numbOfNeighbors);
				}				
			}						
			return;
		}
			
		nextCandidateCitiesWithNumberOfNeighbors.clear();
				
		/*First copy the neighbors of the specified city into the 
		 *separate set, because we need to remove
		 *the given city from the map.
		 */ 
		HashSet<Integer> neighbors = new HashSet<Integer>();
		SafeCopy.copy(neighbors, edgeMap.get(city));
						
		/*Now remove the specified city from the edgeMap as a key and all its occurencies
		 *in the list of values.
		 */		
		removeCityAndItsOccurencesFromMap(city);
		
		/*Go through the list of neighbors of the specified city and put them
		 *in the candidate list.
		 */
		for(Integer neighbor: neighbors) {
			nextCandidateCitiesWithNumberOfNeighbors.putIfAbsent(neighbor, edgeMap.get(neighbor).size());
		}		 		
	}
	
	/**
	 * Removes the given city from the map and all its occurrences 
	 * in the lists of other cities as well.
	 * @param city is the city to be removed from the edge map.
	 */
	private void removeCityAndItsOccurencesFromMap(int city) {
		
		edgeMap.remove(city);
		
		/*If the city was the last one in the edge map, 
		 *after deleting it the map is empty.
		 */
		if(!edgeMap.isEmpty()) {
			for(Integer nextKey: edgeMap.keySet()) {
				
				/*Cast so that it is not considered as index*/		
				edgeMap.get(nextKey).remove((Integer)city);	
			}
		}
	}
	
	/**
	 * Checks if the edge map is empty.
	 * @return true, if the map is empty.
	 * 			false, otherwise.
	 */
	public boolean isEmpty() {
		return edgeMap.isEmpty();
	}
	
	/**
	 * Initializes the current random with the given seed.
	 * @param seed is the given seed.
	 */
	public void fixSeedForRandom(int seed) {
		rand = new Random(seed);
	}
	
	/**
	 * Sets the current random at the given random.
	 * @param rand is the given random.
	 */
	private void setRandom(Random rand) {
		this.rand = rand;
	}
	
	/**
	 * Prints the edge map.
	 */
	public void print() {
		Printer.printString("EdgeMap:");
		for(Integer nextKey: edgeMap.keySet()) {
			Printer.printInt(nextKey);
			Printer.printSet(edgeMap.get(nextKey));

		}
		
		Printer.printString("CitiesAndNeighbors");
		for(Integer nextKey: nextCandidateCitiesWithNumberOfNeighbors.keySet()) {
			System.out.print("Next city: " + nextKey + " has the length of neighbors list ");
			Printer.printInt(nextCandidateCitiesWithNumberOfNeighbors.get(nextKey));

		}
		
	}
}
