package genetic.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import genetic.PathTour;
import util.Printer;
import util.SaveCopy;

public class EdgeMap {
	
	
	private HashMap<Integer, HashSet<Integer>> edgeMap = new HashMap<Integer, HashSet<Integer>>();
	
	private HashMap<Integer, Integer>  cityWithNumberOfNeighbors = new HashMap<Integer, Integer>();
	
	private Random rand;

	public EdgeMap(PathTour firstParent, PathTour secondParent, Random rand) {
		fillIn(firstParent, secondParent);
		setRandom(rand);		
	}

	private void setRandom(Random rand) {
		this.rand = rand;
	}

	private void fillIn(PathTour firstParent, PathTour secondParent) {
		
		//for each city make a list of its neighbors on both parents
		//Note: the first city is always connected to the last city to 
		//represent a round tour.
		for(int i = 0; i < firstParent.getLength(); i++) {

			//make a new list
			HashSet<Integer> neighbors = new HashSet<Integer>();

			int indexofCityInPar1 = firstParent.indexOf(i);

			int one = firstParent.getLeftNeighborToCity(i, indexofCityInPar1);
			neighbors.add(one);
			int two = firstParent.getRightNeighborToCity(i, indexofCityInPar1);
			neighbors.add(two);

			int indexofCityInPar2 = secondParent.indexOf(i);

			int three = secondParent.getLeftNeighborToCity(i, indexofCityInPar2);
			neighbors.add(three);
			int four = secondParent.getRightNeighborToCity(i, indexofCityInPar2);
			neighbors.add(four);

			edgeMap.put(i, neighbors);	
			cityWithNumberOfNeighbors.putIfAbsent(i, neighbors.size());
		}

	}
	
	public int getCityWithMinEdges() {
		
		//first, find the first city with the minimum 
		//number of neighbors
		int minimum = Integer.MAX_VALUE;
				
		int cityWithMin = -1;
		
		Set<Integer> cities = cityWithNumberOfNeighbors.keySet();
		
		for(Integer nextCity: cities) {			
			if(cityWithNumberOfNeighbors.get(nextCity) < minimum) {
				minimum = cityWithNumberOfNeighbors.get(nextCity);
				cityWithMin = nextCity;
			}			
		}
		
		if(cityWithMin == -1) {
			throw new IllegalStateException("No city was found.");
		}
		
		//now there can be more cities with the same value,
		//put them all in the candidate list
		
		ArrayList<Integer> citiesWithSameNeighborsNumber = new ArrayList<Integer>();
		
		for(Integer nextCity: cities) {			
			if(cityWithNumberOfNeighbors.get(nextCity) == minimum) {
				citiesWithSameNeighborsNumber.add(nextCity);
			}			
		}
		
		if(citiesWithSameNeighborsNumber.isEmpty()) {
			throw new IllegalStateException("No city was found.");
		}
		
		//take a random index:
		//index = minimum(0) + rn.nextInt(maxValue - minvalue(0) + 1)
		
		int maxValue = citiesWithSameNeighborsNumber.size() - 1;
		
		int index = rand.nextInt(maxValue + 1);
		
		Printer.printInt(index);
		//Is sort the set in the reverse order only for tests,
		//so that Is can check with the seed = 0, which gives me than 
		//the last index always
		
		Collections.reverse(citiesWithSameNeighborsNumber);
		return citiesWithSameNeighborsNumber.get(index);
	}
	
	public void renewListOfCandidatesAfterTheCityChosen(int city) {
		
		//if the city was the last in the list, do nothing
		//as it has no more neighbors
		
		if(edgeMap.get(city).size() == 0) {
			removeCityAndItsOccurencesFromMap(city);
			return;
		}
		
		cityWithNumberOfNeighbors.clear();
				
		//first copy the neighbors of the specified city into the 
		//separate set, because we need to remove
		//the given city from the map 
		HashSet<Integer> neighbors = new HashSet<Integer>();
		SaveCopy.copy(neighbors, edgeMap.get(city));
						
		//now remove the specified city from the edgeMap as a key and all its occurencies
		//in the list of values		
		removeCityAndItsOccurencesFromMap(city);
		
		//go through the list of neighbors of the specified city and put them
		//in the candidate list
		for(Integer neighbor: neighbors) {
			cityWithNumberOfNeighbors.putIfAbsent(neighbor, edgeMap.get(neighbor).size());
		}
		
		 		
	}
	
	private void removeCityAndItsOccurencesFromMap(int city) {
		
		edgeMap.remove(city);
		//of the city was the last one in the edge map, 
		//after deleting it the map is empty
		if(!edgeMap.isEmpty()) {
			for(Integer nextKey: edgeMap.keySet()) {
				//cast so that it is not considered as index		
				edgeMap.get(nextKey).remove((Integer)city);	
			}
		}
	}
	
	public boolean isEmpty() {
		return edgeMap.isEmpty();
	}
	
	
	public void print() {
		Printer.printString("EdgeMap:");
		for(Integer nextKey: edgeMap.keySet()) {
			Printer.printInt(nextKey);
			Printer.printSet(edgeMap.get(nextKey));

		}
		
		Printer.printString("CitiesAndNeighbors");
		for(Integer nextKey: cityWithNumberOfNeighbors.keySet()) {
			System.out.print("Next city: " + nextKey + " has the length of neighbors list ");
			Printer.printInt(cityWithNumberOfNeighbors.get(nextKey));

		}
		
	}
	
	public void setRandom(int seed) {
		rand = new Random(seed);
	}
}
