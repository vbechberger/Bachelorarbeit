package genetic;

import java.util.ArrayList;
import java.util.HashSet;

import util.Printer;
import util.SaveCopy;

public class AdjTour extends AbstractTour{

	public AdjTour(int dimension, int[] tour) {
		super(dimension, tour);
		checkHamiltonCycle(this.tour);
	}
	
	/**
	 * Checks whether the given adjacency tour builds a hamilton cycle
	 * @param adjRepr
	 * @return
	 */
	private boolean checkHamiltonCycle(int[] adjRepr) {
		
		HashSet<Integer> used = new HashSet<Integer>();
		
		int index = 0;
		used.add(index);
		int steps = 1;
		while (steps != this.tourLength) {
			index = adjRepr[index];
			if(!used.contains(index)) {
				used.add(index);
				steps++;
			} else {
				throw new IllegalArgumentException("The given adjacency tour"
						+ "does not build a hamilton cycle!");
			}			
		}
		return true;
	}
	 
	
	 public PathTour transformToPathTour() {
		
		
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		
		//As the adjacency representation does not strictly 
		//define which city is the first, we assume
		//that the first city is 0
		
		pathList.add(0);
		
		int index = 0;
		int steps = 1;
		while (steps != tourLength) {
			pathList.add(tour[index]);
			index = tour[index];
			steps++;
		}
		Printer.printList(pathList);
		
		int [] path = new int[tourLength];
		
		SaveCopy.copy(path, pathList);
		
		return new PathTour(tourLength, path);
		
	}
	 
	 public int[] getInPathAsArr() {
		 return this.transformToPathTour().tour;
	 }
	 
	 @Override
		public String toString() {
		 	
			return this.transformToPathTour().toString();
		}


}
