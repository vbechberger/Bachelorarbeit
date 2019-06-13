package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import util.SaveCopy;
import util.Printer;

public abstract class Crossover {
  private CrossoverType type;
  protected Chromosome kid1;
  protected Chromosome kid2;
  protected int [] parent1;
  protected int [] parent2;
  protected int [] arrKid;
  
  protected final int arrLength;
  
  
  public Crossover(CrossoverType type, Chromosome firstParent,Chromosome secondParent) {
	  this.type = type;	
	  arrLength = firstParent.getGenes().length;
	  parent1 = new int[arrLength];
	  parent2 = new int[arrLength];
	  SaveCopy.copy(parent1, firstParent.getGenes());
	  SaveCopy.copy(parent2, secondParent.getGenes());	  
	  arrKid = new int[arrLength];	  	  
  }
  
  public abstract void start();   

/*Getter methods*/
  public Chromosome getKid1() {
	  if(kid1 == null) {
		  throw new RuntimeException("Crossover was not started. There exists no kids!");
	  }
	  return kid1;
  }

  public Chromosome getKid2() {
	  if(kid2 == null) {
		  throw new RuntimeException("Crossover was not started. There exists no kids!");
	  }
	  return kid2;
  }
  
}
