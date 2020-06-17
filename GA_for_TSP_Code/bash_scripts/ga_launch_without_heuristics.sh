#!/bin/bash

#change to: cd bachelor-thesis/TSP

#launch with: ./ga_launch_without_heuristics.sh 
#list_of_instances list_of_general_parameters results_folder path_to_instances_folder


#cd bachelor-thesis/TSP

mkdir -p $3

for line in `cat $1`; do

	#take the name of the next tsp instance from the list
	instName=$line

	#the name of the file, where to put the results of launching the genetic algorithm
	fileToSaveResults=$3/ga_results_for_$instName.txt
	
	#the path, where to take the next tsp instance
	instancePath=$4/$instName.tsp
	
	#get the values for the general parameters
	input=$2
	while IFS= read -r line
	do 	
		configurations=$line
		java -jar GALaunch.jar $instancePath $fileToSaveResults $configurations -t 600000
	done < "$input"
	
	
done