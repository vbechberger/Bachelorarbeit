package tsp;

/**
 * Represents an interface which enforces to fill the distance table.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 * 
 *
 */
public interface DistTableFiller {
	
	/**
	 * Fills in the distance table in form of 2D array 
	 * according to the given data.
	 * 
	 * @param data is the given 2D data
	 * @param dimOfDistTable is the number of cities
	 * @return a distance table in form of 2D array
	 */
	public abstract double[][] fillDistTable(double[][] data, int dimOfDistTable);

}
