package heuristics;

public class FarthestStrategy implements OptimalSearchStrategy<Double>{

	@Override
	public boolean firstIsMoreOpt(Double nextDistance, Double currentOptDistance) {
		
		return (nextDistance > currentOptDistance) ? true : false;		
	}

}
