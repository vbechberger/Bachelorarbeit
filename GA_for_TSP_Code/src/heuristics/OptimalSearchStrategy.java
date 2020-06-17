package heuristics;

/**
 * Represents a strategy which 
 * defines which of the two given parameter is more optimal than the other
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 * @param <T> is the type of the parameters which will be compared.
 */
public interface OptimalSearchStrategy<T> {
	
	/**
	 * Checks if the first parameter is more optimal
	 * than the second one.
	 * 
	 * @param firstParameter First parameter
	 * @param secondParameter Second parameter
	 * 
	 * @return true, if the first parameter is more optimal than the second one.
	 * 		   false, otherwise.
	 */
	public abstract boolean firstIsMoreOpt(T firstParameter, T secondParameter);

}
