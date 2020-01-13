package genetic.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import genetic.AdjTour;
import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;

public abstract class CrossoverParentalEdges extends AdjReprCrossover{
	
	protected int[] arrKid = new int[lengthOfChromosome];
	protected int elem = -1;
	protected int numberOfAddedEdges = 0;
	
	protected HashSet<Integer> used = new HashSet<Integer>();
	protected ArrayList<Integer> unUsed = new ArrayList<Integer>();

	public CrossoverParentalEdges(FitnessFunction fitnessFct, 
								  Chromosome firstParent, 
								  Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}
	
	public CrossoverParentalEdges(FitnessFunction fitnessFct, 
			 					  Chromosome firstParent, 
			 					  Chromosome secondParent,int firstCity) {
		
		this(fitnessFct, firstParent, secondParent);
		setFirstCity(firstCity);
	}
	
	@Override
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		initUnusedCitiesList();
		int nextIndex = -1;
		
		//get the first city: if it is not given,
		//at random		
		
		if(indexOfFirstEdge == -1) {
			
			//Take the random city: 
			//minimum(first city is = 0) + 
			//rn.nextInt(maxValue( = length - 1) - minvalue(= 0) + 1)
			indexOfFirstEdge = rand.nextInt(lengthOfChromosome);
		} 
					
		checkIfSelfLoopInPar1AtFirstIndex(firstParent);
		
		nextIndex = indexOfFirstEdge;//first index
		updateUsedAndNotUsedSets(nextIndex);
				
		while(numberOfAddedEdges != lengthOfChromosome) {
						
			nextIndex = findNextCityAndPutItInKid(nextIndex, firstParent, secondParent);	
			updateUsedAndNotUsedSets(nextIndex);
			numberOfAddedEdges++;
		}

		//transform the adjacency representation into a path one
		AdjTour adjKid = new AdjTour(lengthOfChromosome, arrKid);
		
		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, adjKid.transformToPathTour()));
	}
	
	/**
	 * Defines which city will be put in the offspring at the specified index next.
	 * 
	 * @param nextIndex The index of the next edge
	 * @param firstParent firstParent The first parent
	 * @param secondParent secondParent The second parent
	 * 
	 * @return int The number of this city which will build the edge
	 *			  in the offspring chromosome with the city with number 
	 *			  specified in the given nextIndex. In other words,
	 *			  it is a number which will stand in the offspring at the 
	 *			  index, specified by the given nextIndex.
	 */
	protected abstract int findNextCityAndPutItInKid(int nextIndex, int[] firstParent, int[] secondParent);

	protected int chooseRandIfSubCycle(int elem) {
		if(used.contains(elem)) {
			//TODO: pick a random edge which does not
			//introduce a cycle from the list of unused edges
			
			//if the unused edges list is leer, it means we 
			//are at the end of the tour, pick the first city of the tour
			if(unUsed.isEmpty()) {
				elem = indexOfFirstEdge;
			} else {
				Collections.shuffle(unUsed, rand);
			elem = unUsed.get(0);
			}			
		}
		return elem;
	}
	
	protected void checkIfSelfLoopInPar1AtFirstIndex(int[] firstParent) {
		int elemAtGivenIndex = firstParent[indexOfFirstEdge];
		
		if(elemAtGivenIndex == indexOfFirstEdge) {
			throw new IllegalArgumentException("The city: " 
					+  indexOfFirstEdge +
					" goes to itself"
					+ " in the first parent.");
		}
		
	}
	
	protected void initUnusedCitiesList() {
		
		for(int i = 0; i < lengthOfChromosome; i++) {
			unUsed.add(i);
		}		
	}
	
	protected void updateUsedAndNotUsedSets(int elem) {
		used.add((Integer)elem);
		unUsed.remove((Integer)elem);		
	}

}
