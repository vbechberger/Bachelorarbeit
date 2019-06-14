package genetic;

import java.util.ArrayList;

import util.SaveCopy;

public class Simulation {
	  private Crossover crossover;
	  private CrossoverType type;
	  private Chromosome kid1;
	  private Chromosome kid2;
	  private Chromosome parent1;
	  private Chromosome parent2;
	  private ArrayList<Integer> indices = new ArrayList<Integer>();
	  private int startIndex = -1;
	  private int endIndex = -1;

	
	public Simulation( CrossoverType type, Chromosome parent1, Chromosome parent2, 
					ArrayList<Integer> indices, int startIndex, int endIndex) {
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.type = type;
		SaveCopy.copy(this.indices,indices);
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		
	}
	
	public Simulation (CrossoverType type, Chromosome parent1, Chromosome parent2,  
			ArrayList<Integer> indices) {
		this(type, parent1, parent2, indices, -1, -1);
	}
	
	public Simulation (CrossoverType type, Chromosome parent1, Chromosome parent2, int startIndex, int endIndex) {
		this(type, parent1, parent2, null, startIndex, endIndex);
	}
	
	
	public void start() {
		 
		  if (type == CrossoverType.ORDER) {
			  crossover = new CrossoverOX(parent1, parent2, startIndex, endIndex);
		  } else if (type == CrossoverType.ORDERBASED) {			   			   
			   crossover = new CrossoverOBX(parent1, parent2, indices);
		  } else if (type == CrossoverType.POSITIONBASED) {
			 // doPositionBased();
		  } else if (type == CrossoverType.PARTIALLYMAPPED) {
			  crossover = new CrossoverPMX(parent1, parent2, startIndex, endIndex);	  
		  } else if (type == CrossoverType.CYCLE) {
			  //doCycle();  
		  } else {
			 //other types 
		  }
		   //make children's chromosomes
		   crossover.start();
		   if (crossover.getKid1() == null || crossover.getKid2() == null) {
			   throw new IllegalStateException("No kids were made");
		   }
		   kid1 = crossover.getKid1();
		   kid2 = crossover.getKid2();
	}


	public ArrayList<Integer> getIndices() {
		return indices;
	}


	public void setIndices(ArrayList<Integer> indices) {
		this.indices = indices;
	}

	public Chromosome getKid1() {
		return kid1;
	}

	public void setKid1(Chromosome kid1) {
		this.kid1 = kid1;
	}

	public Chromosome getKid2() {
		return kid2;
	}

	public void setKid2(Chromosome kid2) {
		this.kid2 = kid2;
	}
	
	
	
	
}
