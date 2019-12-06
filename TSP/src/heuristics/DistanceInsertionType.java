package heuristics;

public enum DistanceInsertionType {
	NEAREST, 
	
	FARTHEST;

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
