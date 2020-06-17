#!/bin/bash

#change to: cd bachelor-thesis/TSP

#launch with: ./ga_launch_with_heuristics.sh 
#list_of_instances list_of_general_parameters results_folder heuristics_results_folder path_to_instances_folder



mkdir -p $3

for line in `cat $1`; do

	#take the name of the next tsp instance from the list
	instName=$line

	#the name of the file, where to put the results of launching the genetic algorithm
	fileToSaveResults=$3/ga_results_for_$instName.txt
	
	#the path, where to take the next tsp instance
	instancePath=$5/$instName.tsp
	
	#the folder with heuristics results
	folder=$4
	
	#the path, where to take the heuristic results for this instance
	heuristicResults=./$folder/heuristic_results_$instName.txt
	
	#get the values for the general parameters
	input=$2
	while IFS= read -r line
	do 	
		configurations=$line
		java -jar GALaunch.jar $instancePath $fileToSaveResults $configurations -t 600000 -h $heuristicResults
	done < "$input"
	
	
done