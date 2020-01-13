package genetic.crossover;


import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.PathTour;
import genetic.Solution;
import util.SaveCopy;

public class ERX extends AdjReprCrossover{
		
	private EdgeMap edgeMap;
	
	public ERX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		setEdgeMap();
	}
	public ERX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent,
			 int firstCity) {
		this(fitnessFct, firstParent, secondParent);
		setFirstCity(firstCity);
	}	

	@Override
	protected Chromosome doCrossover() {
		
		ArrayList<Integer> tour = new ArrayList<Integer>();
		int[] arrKid = new int[lengthOfChromosome];
		
		//get the first city: if it is not given,
		//at random
		
		if(indexOfFirstEdge == -1) {
			//TODO Take the random city
		} else {
			tour.add(indexOfFirstEdge);
			edgeMap.renewListOfCandidatesAfterTheCityChosen(indexOfFirstEdge);
		}
		//edgeMap.print();
		
		//until we have processed all the cities in the map
		while(!edgeMap.isEmpty()) {
			
			//find the city, which has the minimum number of edges,
			//i.e. whose list of neighbors is the smallest
			int nextCity = edgeMap.getCityWithMinEdges();
			
			//add it into the tour
			tour.add(nextCity);
			
			//remove the chosen city
			//from the edge map and renew the list of candidates 
			edgeMap.renewListOfCandidatesAfterTheCityChosen(nextCity);
			
			//edgeMap.print();
						
		}
		SaveCopy.copy(arrKid, tour);
		
		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}
	
	/**
	 * Makes the initial edge map from the specified parent chromosomes.
	 * The tours of the parents are in path representation here.
	 */
	private void setEdgeMap() {
				
		PathTour firstParent = parent1.transformToPathTour();
		PathTour secondParent = parent2.transformToPathTour();
		edgeMap = new EdgeMap(firstParent, secondParent, rand);
		//edgeMap.print();
	}

	
	/**
	 * Sets seed for random for making tests.
	 * @param seed
	 */
	public void setRandom(int seed) {
		super.setRandom(seed);
		edgeMap.setRandom(seed);
	}



}
