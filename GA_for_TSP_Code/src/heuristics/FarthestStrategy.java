package heuristics;

/**
 * Represents the type of the optimal search strategy where
 * the larger distance is considered as more optimal.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class FarthestStrategy implements OptimalSearchStrategy<Double>{

	@Override
	public boolean firstIsMoreOpt(Double nextDistance, Double currentOptDistance) {
		
		return (nextDistance > currentOptDistance) ? true : false;		
	}

}
