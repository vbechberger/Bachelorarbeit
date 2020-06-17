package launches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import genetic.Chromosome;
import tsp.DistanceTable;
import tsp.Solution;
import tsp.TSPInstance;
import tsp.TSPOptSolutionParser;
import tsp.TSPParser;

/**
 * Prints the fitness value of the optimal tour for the given 
 * instances from the given file.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class OptSolutionsCalculator {

	public static void main(String[] args) {
		
		if(args.length != 3) {
			printUsage();			
			System.exit(0);			
		}
		
	String listOfFiles = args[0];
	
	String instancesFolderString = args[1];
	
	String optSolFolderString = args[2];
		
	File fileWithListedInstances = new File(listOfFiles);
	
	if (!fileWithListedInstances.canRead() || !fileWithListedInstances.isFile()) {
		System.err.println("The file: " + listOfFiles + " could not be read!");
		System.exit(0);
	}
	
	BufferedReader in = null;
	
	try {
		in = new BufferedReader(new FileReader(fileWithListedInstances));
		String fileName = null;
		
		while ((fileName = in.readLine()) != null) {
			fileName = fileName.trim();
			
			double nextDistance = getDistance(fileName, instancesFolderString, optSolFolderString);
			
			System.out.println(fileName + "," + nextDistance);
						
		}
		
	} catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (in != null) 
            try {
                in.close();
            } catch (IOException e) {
            }
        
    }	

}
	/**
	 * Calculates the distance of the optimal solution for the specified TSP instance.
	 * @param fileName is the name of the TSP instance file.
	 * @param instancesFolderString is the name of the folder wth TSP instances.
	 * @param optSolFolderString is the name of the folder with the files
	 * 							which contain optimal solutions.
	 * @return the total distance of the optimal solution of the provided TSP instance.
	 */
	private static double getDistance(String fileName, String instancesFolderString, String optSolFolderString) {
		
		String instanceData = instancesFolderString + "/" + fileName + ".tsp";
		File fileForInstance = new File(instanceData);
		
		String optSol = optSolFolderString + "/" + fileName + ".opt.txt";
		File fileWithSOl = new File(optSol);
		
		TSPParser tsp = null;
		TSPInstance tspInst = null;

		TSPOptSolutionParser optSolParser = null;
			
		
		if (!fileForInstance.canRead() || !fileForInstance.isFile()) {
			System.err.println("The file: " +  instanceData + " could not be read!");
			System.exit(0);
		}
		
		if (!fileWithSOl.canRead() || !fileWithSOl.isFile()) {
			System.err.println("The file: " + optSol + " could not be read!");
			System.exit(0);
		}
		
		
		try {
			tsp = new TSPParser(fileForInstance);
			tspInst = tsp.getTspInstance();
					
		} catch (IndexOutOfBoundsException eob) {
			System.out.println(tspInst.getName());
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
			
		
		try {
			optSolParser = new TSPOptSolutionParser(fileWithSOl, tspInst);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		Solution sol = optSolParser.getSolution();
		
		DistanceTable distTable = tspInst.getDistanceTable();
		
		Chromosome chr = new Chromosome(distTable, sol);
		
		System.out.println("Optimal solution fitness for: " + fileName + ":" + chr.getFitness() * (-1));
		
		return chr.getFitness() * (-1);
	}
	
	/**
	 * Prints usage.
	 */
	private static void printUsage() {
		System.err.println("Invalid number of arguments");
		System.err.println("Please give the following arguments:");
		System.err.println("[0]: names_of_instances_listed");
		System.err.println("[1]: folder_with_instances");
		System.err.println("[2]: folder_with_opt_solutions");		
	}

}
