package genetic;

import genetic.distancefct.ATTDistanceFct;
import genetic.distancefct.Ceil2DDistancefct;
import genetic.distancefct.Euc2DDistanceFct;
import genetic.distancefct.GeoDistanceFct;

/**
 * This class represents an edge weight type,
 * which defines which type of distance function will be used.
 * 
 * @author valeriyabechberger
 *
 */
public enum EdgeWeightType {

	/**
	 * Weights in 2D Euclidean distances
	 */
	EUC_2D, 
	
	
	/**
	 * Weights as Geographical distances
	 */
	GEO, 
	
	/**
	 * Weights in Pseudo-euclidean distances
	 */
	ATT, 
	
	/**
	 * Weights are given explicitly
	 */
	
	EXPLICIT,
	
	
	/**
	 * Weights in 2D Euclidean distances which rounded up.
	 */
	CEIL_2D;

	public DistTableFiller getDistTableFiller() {
		switch(this) {
		
		case EUC_2D: return new Euc2DDistanceFct();
		case GEO: return new GeoDistanceFct();
		case ATT: return new ATTDistanceFct();
		//case EXPLICIT: new ExplicitDistances(edgeWeightFormat);
		case CEIL_2D:	return new Ceil2DDistancefct();
		
		default:
			throw new IllegalArgumentException(
					"the distance function is not defined for " + this);
			
		}
		
	}
	
	

	
}
