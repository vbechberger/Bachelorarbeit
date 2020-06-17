package launches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import io.GAReader;
import io.GAWriter;

/**
 * Averages the results for each configuration over the used seeds
 * for all files in the given folder and aggregate all this data in one file
 * which contains:
 * line: configuration
 * row: the averaged result for this configuration for a next instance.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class GAResultsAnalyzer {

	/**
	 * The fitness value averaged over the used seeds.
	 */
	private static double fitnessAvereagedOverSeeds = 0.0;

	/**
	 * Results as a string.
	 */
	private static String results = "";

	/**
	 * The number of lines between the same configuration.
	 */
	private static final int numberOfLinesBtwSameConfig = 1;
	
	/**
	 * The position of the fitness value in the line with results for configuration.
	 */
	private static final int posNumbOfFitnessValueInConfig = 6;
	
	/**
	 * The number of used seeds.
	 */
	private static final int numbOfSeeds = 3;

	/**
	 * Counter.
	 */
	private static int counter = -1;


	public static void main(String[] args) {
		
		if(args.length != 3) {
			printUsage();			
			System.exit(0);			
		}
		
		String folderName = args[0];

		String analyzedResults = args[1];
		
		int numberOfConfigs = -1;
		
		try {
			numberOfConfigs = Integer.parseInt(args[2]);
		} catch (IllegalArgumentException e) {
			System.err.println("The number of "
					+ "configurations could not be parsed!");
			System.exit(0);
		}
		
		
		for(counter = 1; counter <= numberOfConfigs; counter++) {
			try (Stream<Path> paths = Files.walk(Paths.get(folderName))) {
				paths
				.filter(Files::isRegularFile)
				.sorted()
				.forEach(s->calcNConfigAveragedOverSeeds(s.toString(), counter));
			} catch (IOException e) {
				e.printStackTrace();
			} 

			GAWriter.writeResultsIntoFile(results, analyzedResults);
			results="";
		}		

	}

	/**
	 * 
	 * Averages the results for the same configuration over the used seeds.
	 * @param fileName is the given file.
	 * @param configNumber is the number of the configuration.
	 */
	private static void calcNConfigAveragedOverSeeds(String fileName, int configNumber) {

		System.out.println("File name: " + fileName);
		
		File fileWithResults = new File(fileName);

		if (!fileWithResults.canRead() || !fileWithResults.isFile()) {			
			System.err.println("The file with parameter results could not be read!");
			System.exit(0);			
		} else {
			
			BufferedReader input = null;
			Double tempSum = 0.0;
			String string = null;
			String[] splitedString = null;
			
			try {				
				for(int i = 1; i <= numbOfSeeds; i++) {

					input = new BufferedReader(new FileReader(fileWithResults));
					
					string = GAReader.getNline((configNumber * numbOfSeeds - numbOfSeeds) + numberOfLinesBtwSameConfig * i, input);
				
					splitedString = string.split(",");
					
					/*parse the string and get calculated fitness value*/				
					Double resultFitness = getDoubleInStringAtIndex(splitedString, posNumbOfFitnessValueInConfig);

					tempSum = tempSum + resultFitness;
					
				}
				
				fitnessAvereagedOverSeeds = tempSum/numbOfSeeds;
				
				results = results + fitnessAvereagedOverSeeds + ",";

			} catch (IOException e2) {
				e2.printStackTrace();

			} finally {
				if(input != null) {
					try {
						input.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * Parses double which stands in the given splited string at the specified index.
	 * 
	 * @param splitedString is the given splited string
	 * @param index the position in the given splited string.
	 * @return the parsed double
	 */
	private static double getDoubleInStringAtIndex(String[] splitedString, int index) {

		if(index < 0 || index >= splitedString.length) {
			throw new IllegalArgumentException("Illegal index: " + index);
		}

		Double result = -1.0;

		try {
			/*get the target size of population*/
			result = Double.parseDouble(splitedString[index]) * (-1);
		} catch (NumberFormatException e) {
			System.err.println("The value could not be parsed!");
			System.exit(0);
		}

		return result;
	}
	
	/**
	 * Prints usage.
	 */
	private static void printUsage() {
		System.err.println("Invalid number of arguments");
		System.err.println("Please give the following arguments:");
		System.err.println("[0]: name of the folder with the files with results");
		System.err.println("[1]: file where to write the averaged results");
		System.err.println("[2]: number of configurations");		
	}

}
