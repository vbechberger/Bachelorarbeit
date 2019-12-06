package heuristics;

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
