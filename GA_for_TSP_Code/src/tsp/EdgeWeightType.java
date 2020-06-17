package tsp;

import tsp.distancefct.ATTDistanceFct;
import tsp.distancefct.Ceil2DDistancefct;
import tsp.distancefct.Euc2DDistanceFct;
import tsp.distancefct.GeoDistanceFct;

/**
 * This class represents an edge weight type
 * which defines which type of distance function will be used.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
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

	/**
	 * Returns the corresponding type of function 
	 * which will be used
	 * to fill in the distance table.
	 * 
	 * @return
	 */
	public DistTableFiller getDistTableFiller() {
		switch(this) {
		
		case EUC_2D: return new Euc2DDistanceFct();
		case GEO: return new GeoDistanceFct();
		case ATT: return new ATTDistanceFct();
		case CEIL_2D:	return new Ceil2DDistancefct();
		
		/*In case of EXPLICIT and others, the distance function is not defined*/
		default:
			throw new IllegalArgumentException(
					"the distance function is not defined for " + this);			
		}		
	}
	
	

	
}
