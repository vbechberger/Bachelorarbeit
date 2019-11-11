package genetic;

public enum EdgeWeightFormat {
	/**The weights are given for the region of the matrix,
	 * which lies above the main diagonal (upper triangle), 
	 * excluding the region of the main diagonal itself*/
	UPPER_ROW, 
	
	/**The weights are given for the region of the matrix,
	 * which lies above the main diagonal (upper triangle), 
	 * including the region of the main diagonal itself*/
	UPPER_DIAG_ROW, 
	
	/**The weights are given in the form of full matrix*/
	FULL_MATRIX, 
	
	/**The weights are given for the region of the matrix,
	 * which lies below the main diagonal (lower triangle), 
	 * including the region of the main diagonal itself*/
	LOWER_DIAG_ROW,
	
	/**The weights are given by a function, not explicitly, 
	 * which is defined by the edge weight type, i.e. geo, 
	 * att, 2d, ceil2d. Note that the used dataset specifies
	 * this edge weight format only for some geo instances. */
	FUNCTION;
}
