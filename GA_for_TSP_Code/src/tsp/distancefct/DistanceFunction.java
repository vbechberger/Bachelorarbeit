package tsp.distancefct;

import tsp.DistTableFiller;

/**
 * An abstract class which represents a distance function
 * to calculate the distances between the cities.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class DistanceFunction implements DistTableFiller{
	
	/**
	 * Calculates the distances between the cities according to their coordinates.
	 * @param coordinates are 2D coordinates of the cities
	 * @param dimOfDistTable is the number of cities.
	 * @return distances between all cities in 2D array.
	 */
	public abstract double[][] fillDistTable(double[][] coordinates, int dimOfDistTable);
	
}
