package genetic.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import tsp.representation.AdjTour;

/**
 * An abstract class which represents a crossover operator which uses the
 * adjacency representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public abstract class AdjReprX extends Crossover{

	protected AdjTour parent1;
	protected AdjTour parent2;
	
	/**The offsping which will be returned when the crossover is done*/
	protected int[] arrKid = new int[SIZE_OF_CHROMOSOME];
	
	/**The city we currently work with*/
	protected int elem = -1;
	
	/**The counter for the number of used edges*/
	protected int numberOfAddedEdges = 0;
	
	/**Set with the used cities*/
	protected HashSet<Integer> used = new HashSet<Integer>();
	
	/**The list with cities which have not been used yet*/
	protected ArrayList<Integer> unUsed = new ArrayList<Integer>();
	
	/**The index of the edge which will be used first*/
	protected int indexOfFirstEdge = -1;
	
	protected Random rand;
	
	/**
	 * Creates a crossover operator of adjacency representation type.
	 * @param fitnessFct is the given fitness function
	 * @param firstParent is the first parent chromosome
	 * @param secondParent is the second parent chromosome
	 * @param firstEdge is the index of the first addge
	 * @param rand is the Random object so that we can check the results using seeds.
	 */
	public AdjReprX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent, 
			 int firstEdge,
			 Random rand) {
		
		super(fitnessFct, firstParent, secondParent);
		parent1 = firstParent.getGenesAsPathTour().transformIntoAdj();
		parent2 = secondParent.getGenesAsPathTour().transformIntoAdj();
		setIndexOfFirstEdge(firstEdge);
		this.rand = rand;
	}
	
	@Override
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		initUnusedCitiesList();
		int nextIndex = -1;
					
		/*check if the edge to be used first in the first parent contains the self loop:
		 * i.e. if the index at which the edge stands equals the 
		 * element which stands at this index*/
		checkIfSelfLoopInPar1AtFirstIndex(firstParent);
		
		/*Remember the next index which will be updated*/
		nextIndex = indexOfFirstEdge;
		
		/*Update the lists of used and unused cities*/
		updateUsedAndNotUsedSets(nextIndex);
				
		/*If we have not used all edges yet*/
		while(numberOfAddedEdges != SIZE_OF_CHROMOSOME) {
					
			/*Define the next edge*/
			nextIndex = findNextCityAndPutItInKid(nextIndex, firstParent, secondParent);
			
			/*Update the lists of used and unused cities*/
			updateUsedAndNotUsedSets(nextIndex);
			
			/*Increment the number of used edges*/
			numberOfAddedEdges++;
		}

		/*transform the adjacency representation into a path one*/
		AdjTour adjKid = new AdjTour(SIZE_OF_CHROMOSOME, arrKid);
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, adjKid.transformToPathTour()));
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
			
			/*if the unused edges list is empty, it means we 
			 *are at the end of the tour, pick the first city of the tour
			 */
			if(unUsed.isEmpty()) {
				elem = indexOfFirstEdge;
			} else {
				/*pick a random edge which does not
				 *introduce a cycle from the list of unused edges
				 */			
				Collections.shuffle(unUsed, rand);
			elem = unUsed.get(0);
			}			
		}
		return elem;
	}
	
	/**
	 * Checks for the self loop for the edge to be used first in the given parent.
	 * @param firstParent is the given parent
	 */
	private void checkIfSelfLoopInPar1AtFirstIndex(int[] firstParent) {
		int elemAtGivenIndex = firstParent[indexOfFirstEdge];
		
		if(elemAtGivenIndex == indexOfFirstEdge) {
			throw new IllegalArgumentException("The city: " 
					+  indexOfFirstEdge +
					" goes to itself"
					+ " in the first parent.");
		}
		
	}
	
	/**
	 * Puts all the cities in the list of unused cities.
	 */
	private void initUnusedCitiesList() {
		
		for(int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
			unUsed.add(i);
		}		
	}
	
	/**
	 * Add the specified city to the list of used cities
	 * and removes it from the list of the unused cities
	 * @param elem is the city which will be used
	 */
	private void updateUsedAndNotUsedSets(int elem) {
		used.add((Integer)elem);
		unUsed.remove((Integer)elem);		
	}
	
	/**
	 * Sets the index of the edge to be used first
	 * @param indexOfFirstEdge
	 */
	protected void setIndexOfFirstEdge(int indexOfFirstEdge) {
		if(indexOfFirstEdge < 0 || indexOfFirstEdge >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("the given city: " +
					indexOfFirstEdge + " does not exist in the tour");
		}
		this.indexOfFirstEdge = indexOfFirstEdge;	
	}	


}
