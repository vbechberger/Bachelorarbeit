package heuristics;

/**
 * Represents the type of the insertion heuristic.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public enum DistanceInsertionType {
	NEAREST, 
	
	FARTHEST;

	/**
	 * Returns the corresponding search strategy dependent on the type of the insertion.
	 * @return the optimal search strategy.
	 */
	public OptimalSearchStrategy<Double> getSearchStrategy() {
		switch(this) {
	
		case NEAREST: return new NearestStrategy();
		case FARTHEST: return new FarthestStrategy();
	
		default:
			throw new IllegalArgumentException(
				"the search strategy is not defined for " + this);
		
		}
	}
}
