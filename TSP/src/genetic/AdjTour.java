package genetic;

import java.util.ArrayList;


import util.Printer;
import util.SaveCopy;

public class AdjTour extends AbstractTour{

	public AdjTour(int dimension, int[] tour) {
		super(dimension, tour);
	}
	
	
	 
	
	 public PathTour transformToPathTour() {
		
		
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		
		//As the adjacency representation does not strictly 
		//define which city is the first, we assume
		//that the first city is 0
		
		pathList.add(0);
		
		int index = 0;
		int steps = 1;
		while (steps != length) {
			pathList.add(tour[index]);
			index = tour[index];
			steps++;
		}
		Printer.printList(pathList);
		
		int [] path = new int[length];
		
		SaveCopy.copy(path, pathList);
		
		return new PathTour(length, path);
		
	}
	 
	 public int[] getInPathAsArr() {
		 return this.transformToPathTour().tour;
	 }
	 
	 @Override
		public String toString() {
		 	
			return this.transformToPathTour().toString();
		}


}
