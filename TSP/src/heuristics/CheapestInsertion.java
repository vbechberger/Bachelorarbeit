package heuristics;

import java.util.LinkedList;

import util.Checker;
import util.Pair;

public class CheapestInsertion extends AbstractInsertion{

	public CheapestInsertion(double[][] distances, 
							 int startCityNumber) {
		super(new NearestStrategy(), distances, startCityNumber);
	}

	@Override
	protected Pair<Integer, Integer> nextCityToInsertToPartTourAndIndex(LinkedList<Integer> currentTour) {
		Checker.isNullOrEmpty(currentTour);
		
		int cheapestCity = -1;
		double lowestCosts = Double.MAX_VALUE;
		int indexToInsert = -1;
				
		//for all not used cities find the pair of used cities, where to put it at cheapest
		//and remember the index to put and the costs 
		//and put all these pairs into a list
		
		for(int i = 0; i < dimension; i++) {
			if (!usedCities.contains(i)) {
				Pair<Integer, Double> pairWithIndexAndCosts 
										= findIndexOfPartTourToPutCityWhileMinInsertionCosts(i, currentTour);
				if(pairWithIndexAndCosts.getSecond() < lowestCosts) {
					cheapestCity = i;
					lowestCosts = pairWithIndexAndCosts.getSecond();
					indexToInsert = pairWithIndexAndCosts.getFirst();
				}
			}
		}
				
		if(cheapestCity == - 1) {
			throw new IllegalStateException("No cheapest city was found!");
		}
		
		return new Pair<Integer, Integer>(cheapestCity, indexToInsert);
	}

}
