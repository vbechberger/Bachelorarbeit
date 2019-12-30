package genetic.crossover;


import java.util.ArrayList;
import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.PathTour;
import genetic.Solution;
import util.SaveCopy;

public class CrossoverER extends AdjReprCrossover{
	
	private Random rand = new Random();
		
	private EdgeMap edgeMap;

	private int firstCity = -1;
	
	public CrossoverER(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		setEdgeMap();		
	}
	public CrossoverER(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent,
			 int firstCity) {
		this(fitnessFct, firstParent, secondParent);
		setFirstCity(firstCity);		
	}

	@Override
	protected Chromosome doCrossover(Chromosome parent1, Chromosome parent2) {
		
		ArrayList<Integer> tour = new ArrayList<Integer>();
		int[] arrKid = new int[lengthOfChromosome];
		
		//get the first city: if it is not given,
		//at random
		
		if(firstCity == -1) {
			//TODO Take the random city
		} else {
			tour.add(firstCity);
			edgeMap.renewListOfCandidatesAfterTheCityChosen(firstCity);
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
				
		PathTour firstParent = parent1.decodeIntoSolution().getTour();
		PathTour secondParent = parent2.decodeIntoSolution().getTour();
		edgeMap = new EdgeMap(firstParent, secondParent, rand);
		//edgeMap.print();
	}

	
	private void setFirstCity(int firstCity) {
		if(firstCity < 0 || firstCity >= lengthOfChromosome) {
			throw new IllegalArgumentException("the given city: " +
							firstCity + " does not exist in the tour");
		}
		this.firstCity = firstCity;	
	}
	
	/**
	 * Sets seed for random for making tests.
	 * @param seed
	 */
	public void setRandom(int seed) {
		rand = new Random(seed);
		edgeMap.setRandom(seed);
	}



}
