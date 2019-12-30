package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.Printer;
import util.SaveCopy;

public class Tour {
	
	private int[] pathRepr;
	private int[] adjRepr;
	private final int dimension;

	public Tour(int dimension, int[] tour, ReprType reprType) {
		
		this.dimension = dimension;
		
		checkTour(tour);
		
		pathRepr = new int[dimension];
		adjRepr = new int[dimension];
		
		switch(reprType) {
		case PATH:
			SaveCopy.copy(pathRepr, tour);			
			calcAdjacencyFromPath(pathRepr);	
		break;		
		case ADJACENCY:			
			SaveCopy.copy(adjRepr, tour);
			calcPathFromAdj(adjRepr);	
		break;	
		default:
			throw new IllegalArgumentException("No such "
					+ "representation type: " + reprType.toString());
		}
	}
	
	private void checkTour(int[] tour) {
		if(tour.length != dimension) {
			throw new IllegalArgumentException("The given tour is too short!");
		}
		if(!isFeasibleTour(tour)) {
			throw new IllegalStateException("The obtained solution: " 
					+ this + " is not feasible!");
		} 
	}
	
	private boolean isFeasibleTour(int[] tour) {
		
		List<Integer> tempTour = new ArrayList<Integer>();
		
		SaveCopy.copy(tempTour, tour);

		Collections.sort(tempTour);

		for (int i = 0; i < dimension; i++) {
			if (tempTour.get(i) != i) {
				return false;
			}
		}
		tempTour = null;

		return true;

	}
	
	private void calcAdjacencyFromPath(int[] pathRepr) {
		
		for(int i = 0; i < dimension; i++) {
			int indexForAdj = pathRepr[i];
			
			/* In the path representation, 
			 * the last city is supposed to be connected
			 * with the first city which stands at the index 0
			 * to build the Hamilton cycle.
			 */
			if(i == dimension - 1) {
				adjRepr[indexForAdj] = pathRepr[0];
			} else {
				adjRepr[indexForAdj] = pathRepr[i + 1];
			}
					
		}
	}
	

	private void calcPathFromAdj(int[] adjRepr) {
		
		
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		
		//As the adjacency representation does not strictly 
		//define which city is the first, we assume
		//that the first city is 0
		
		pathList.add(0);
		
		int index = 0;
		int steps = 1;
		while (steps != dimension) {
			pathList.add(adjRepr[index]);
			index = adjRepr[index];
			steps++;
		}
		Printer.printList(pathList);
		SaveCopy.copy(pathRepr, pathList);
		
	}


	
	/**
	 * 
	 * @param city
	 * @param cityIndex
	 * @return
	 */
	public int getLeftNeighborToCity(int city, int cityIndex) {
		
		checkIndexValidity(cityIndex);				
		
		int indexNeigborLeft = (cityIndex == 0) ? dimension - 1 : cityIndex - 1;
					
		return this.pathRepr[indexNeigborLeft];
	}
	
	/**
	 * 
	 * @param city
	 * @param cityIndex
	 * @return
	 */
	public int getRightNeighborToCity(int city, int cityIndex) {
		
		checkIndexValidity(cityIndex);				
				
		int indexNeigborRight = (cityIndex == dimension - 1) ? 0 : cityIndex + 1;
		
		return this.pathRepr[indexNeigborRight];
	}

	/**
	 * 
	 * @param cityIndex
	 */
	private void checkIndexValidity(int cityIndex) {
		if(cityIndex < 0 || cityIndex > dimension - 1) {
			throw new IllegalArgumentException("The given index " 
					+ cityIndex + " is not in the tour");
		}
	}
	
	/**
	 * Finds the index of the given city in the path
	 * @param city The given city
	 * @return The index of the given city
	 * @throws
	 */
	public int indexOfCityInPath(int city) {
		
		for(int i = 0; i < dimension; i++) {
			if(this.pathRepr[i] == city) {
				return i;
			} 
		}
		throw new IllegalArgumentException("The given city " 
							+ city + " is not in the tour");
	}

	@Override
	public String toString() {
		return "Tour [pathRepr=" + Arrays.toString(pathRepr) + "]";
	}
	
	
	
				/**Getter methods*/
	
	public int getSize() {
		return this.dimension;
	}
	
	public int[] getPathRepr() {
		return pathRepr;
	}

	public int[] getAdjRepr() {
		return adjRepr;
	}
	
	

}
