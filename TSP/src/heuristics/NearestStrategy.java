package heuristics;

public class NearestStrategy implements OptimalSearchStrategy<Double>{

	@Override
	public boolean firstIsMoreOpt(Double nextDistance, Double currentOptDistance) {
		
		return (nextDistance < currentOptDistance) ? true : false;		
	}
}
