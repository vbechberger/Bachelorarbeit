package tsp;

import genetic.FitnessFunction;
import util.SafeCopy;

/**
 * Represents a distance table which contains the distances between all cities.
 * This class inherits from the Fitness finction 
 * as it is used to calculate fitness value 
 * of the chromosomes in the population.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class DistanceTable extends FitnessFunction{
	
	private int dimension = -1;
	
	private double[][] distances;
	
	
	/**
	 * Constructor
	 * Creates a distance table according to the given coordinates of cities.
	 * @param distTableFiller is a help object which calculates 
	 * 							the distances according to the given coordinates.
	 * @param coordinates are 2D coordinates.
	 * @param dimension is the number of cities.
	 */
	public DistanceTable(DistTableFiller distTableFiller, double[][] coordinates, int dimension) {
		
		distances = distTableFiller.fillDistTable(coordinates, dimension);
		setDimension(dimension);
	}
	
	/**
	 * Constructor
	 * Creates a distance table according to the given weights (distances).
	 * @param weights are distances between cities.
	 * @param dimension is teh number of cities.
	 */
	public DistanceTable(double[][] weights, int dimension) {

		distances = new double[dimension][dimension];
		SafeCopy.copy(distances, weights);
		setDimension(dimension);
	}
	
	
	
	/*Getter- and setter- methods*/

	/**
	 * 
	 * @return 2D array with all distances
	 */
	public double[][] getDistances() {
		return distances;
	}
	
	/**
	 * 
	 * @param firstCity
	 * @param secCity
	 * @return the distance between two cities
	 */
	public double getDistanceBtwCities(int firstCity, int secCity) {
		return distances[firstCity][secCity];
	}

	/**
	 * Sets the dimention
	 * @param dimension
	 */
	private void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}		
		this.dimension = dimension;
	}
	
	/**
	 * Returns the total distance of the round tour:
	 * Note that it includes the distance from the last city to the first city.
	 * @param tour is the given tour
	 * @return the total distance
	 */
	private double calcDistanceOfTour(int[] tour) {
		
		double sum = 0;
		
		for (int i = 0; i < tour.length - 1; i++) {
			int start = tour[i];
			int end = tour[i + 1];
			sum = sum + distances[start][end];
		}
		/*calculate the distance of the edge between the last and the first city*/
		int start = tour[tour.length - 1];
		int end = tour[0];
		sum = sum + distances[start][end];	
		
		if (sum <= 0) {
			throw new IllegalStateException("The distance of "
					+ "tour is smaller or equal than 0!");
		}				
		return sum;
	}


	@Override
	public double calcFitness(int[] genes) {
		return (-1) * calcDistanceOfTour(genes);
	}


	@Override
	public int getDimention() {
		return dimension;
	}

}
