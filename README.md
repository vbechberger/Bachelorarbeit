Bachelor thesis "Genetic algorithms for the traveling salesman problem"

-> LAUNCH THE GENETIC ALGORITHM:

Output file format (txt):
Seed Pop_size(factor) Mut_rate Cros_type Mut_type Sel_type Best_fitness numb_iterations

1) Launch genetic algorithm with random initialization of population:

Change to: cd GA_for_TSP_Code/bash_scripts

Launch bash script: ./ga_launch_without_heuristics.sh
with parameters:
1. list_of_instances 
2. list_of_general_parameters 
3. results_folder 
4. path_to_instances_folder

2) Launch genetic algorithm using heuristics for the initialization of population: 

Change to: cd GA_for_TSP_Code/bash_scripts

Launch bash script: ./ga_launch_with_heuristics.sh 
with parameters:
1. list_of_instances 
2. list_of_general_parameters 
3. results_folder 
4. heuristics_results_folder 
5. path_to_instances_folder
