#!/bin/bash
#launch with .-heuristics.sh tsp_instances_listed

#download the tsp instances, which have the optimal value, 
#into the corresponding folder
#cd Bachelorarbeit/TSP

mkdir -p instances_with_opt_value
mkdir -p heuristic_results

#for each line in the list of the names of tsp instances, which is specified 
#as a command argument
for line in `cat $1`; do

	#take the name of the next tsp instance from the list
	instName=$line
	
	#read the content of the link
	filenameURL=http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/$instName.tsp
	
	#save the tsp instance in the folder
	cd instances_with_opt_value && { curl -O $filenameURL ; cd -; }
	
done

for line in `cat $1`; do

	#take the name of the next tsp instance from the list
	instName=$line

	#the name of the file, where to put the results of heuristics
	fileToSaveResults=heuristic_results/heuristic_results_$instName.txt
	
	#the path, where to take the next tsp instance
	instancePath=./instances_with_opt_value/$instName.tsp
	
	#calculate heuristics for the given instance 
	#and save the results in the specified file  
	java -jar HeuristicLauncher.jar $instancePath $fileToSaveResults
done