package genetic.crossover;

import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.DistanceTable;

/**
 * Represents the heuristic crossover operator
 * which works with the adjacency representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class HX extends AdjReprX{
	
	/**
	 * The distance table which contains 
	 * all the distances between the cities.
	 */
	private DistanceTable distTable;
	
	/**
	 * Constructor.
	 * Creates the HX operator.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param distTable is the given distance table.
	 * @param edgeIndex is the index of an edge with which we start.
	 * @param rand is the random object.
	 */
	public HX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent,
			 DistanceTable distTable,
			 int edgeIndex,
			 Random rand) {
		super(fitnessFct, firstParent, secondParent, edgeIndex, rand);
		setDistanceTable(distTable);
	}	

	
	/**
	 * Defines which the next edge of the offspring,
	 * according to the weights on the edges of both parents.
	 * The edge with the smaller weight wins. However, if it
	 * introduces a sub cycle, then the edge from the other
	 * parent will be used. If it also introduces a sub cycle, then a random
	 * edge will be chosen, which does not introduce a sub cycle.
	 * 
	 * @param index The index of the next edge
	 * @param firstParent The first parent
	 * @param secondParent The second parent
	 * 
	 * @return int The number of this city which will build the edge
	 *			  in the offspring chromosome with the city with number 
	 *			  specified in the given nextIndex. In other words,
	 *			  it is a number which will stand in the offspring at the 
	 *			  index, specified by the given nextIndex.
	 * 
	 */
	protected int findNextCityAndPutItInKid(int index, 
								int[] firstParent,
								int[] secondParent) {
		
		/*edge in the first parent*/
		int edgePar1 = firstParent[index];
		
		/*edge in the second parent*/
		int edgePar2 = secondParent[index];
		
		int nextCity = -1;
		
		/*If both parents have the same edge*/
		if(edgePar1 == edgePar2) {
			
			/*take the edge from the first parent*/
			nextCity = edgePar1;
		} else {
			/*If not, compare the distances on both edges.*/
			double dist1 = distTable.getDistanceBtwCities(index, edgePar1);
			double dist2 = distTable.getDistanceBtwCities(index, edgePar2);

			/*Compare the edges which go from the specified city
			 *in both parents and take the shortest.
			 */
			nextCity = dist1 <= dist2 ? edgePar1: edgePar2;

			/*If this city introduces a cycle, choose the other edge.*/
			if(used.contains(nextCity)) {
				nextCity = (nextCity == edgePar1) ? edgePar2: edgePar1;
			}
		}
				
		/*If the other city also introduces a cycle,
		 *choose a random city, which does not introduce a cycle.
		 */
		nextCity = chooseRandIfSubCycle(nextCity);
		
		arrKid[index] = nextCity;
		
		return nextCity;
	}
	
	/**
	 * Sets the given distance table.
	 * @param distTable is the given distance table.
	 */
	private void setDistanceTable(DistanceTable distTable) {
		this.distTable = distTable;		
	}
}
