package genetic.crossover;


import java.util.ArrayList;

import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * Represents the edge recombination crossover operator.
 * It works with the path representation, therefore it inherits from the PathReprX.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class ERX extends PathReprX{
		
	/**Edge map data structure:
	 * Mapping: city -> list of its neighbors in the first and the second parents
	 */
	private EdgeMap edgeMap;
	
	/**Random object*/
	private Random rand;
	
	/**
	 * Constructor.
	 * Creates the ERX operator.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param rand is the Random object.
	 */
	public ERX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent,
			 Random rand) {
		super(fitnessFct, firstParent, secondParent);
		this.rand = rand;
		setEdgeMap(firstParent, secondParent);		
	}	

	@Override
	protected Chromosome doCrossover() {
		
		ArrayList<Integer> tour = new ArrayList<Integer>();
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];
		
		/*Until we have processed all the cities in the map*/
		while(!edgeMap.isEmpty()) {
			
			/*Find the city, which has the minimum number of edges,
			 *i.e. whose list of neighbors is the smallest
			 */
			int nextCity = edgeMap.getCityWithMinEdges();
			
			/*add it into the tour*/
			tour.add(nextCity);
			
			/*Remove the chosen city
			 *from the edge map and renew the list of candidates. 
			 **/

			edgeMap.renewListOfCandidatesAfterTheCityChosen(nextCity);
						
		}
		SafeCopy.copy(arrKid, tour);
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}
	
	/**
	 * Makes the initial edge map from the specified parent chromosomes.
	 * The tours of the parents are in path representation here.
	 * 
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome. 
	 */
	private void setEdgeMap(Chromosome firstParent, Chromosome secondParent) {
				
		edgeMap = new EdgeMap(parent1, parent2, rand);
	}
}
