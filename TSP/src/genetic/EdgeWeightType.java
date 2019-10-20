package genetic;

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
	
	public NodeCoordType getNodeCoordType() {
		switch (this) {
		case EUC_2D:
		case CEIL_2D:
		case GEO:
		case ATT:
			return NodeCoordType.COORDS_2D;
		
		default:
			throw new IllegalArgumentException(
					"the node coordinate type is not defined for " + this);
		}
	}
}
