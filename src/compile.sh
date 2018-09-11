#!/usr/bin/env bash


# Compile step:
javac -cp .:contest.jar player50.java && jar cmf MainClass.txt submission.jar player50.class model/mutation/BasicMutation.class model/mutation/EmptyMutation.class model/mutation/MutationInterface.class model/recombination/RecombinationInterface.class model/recombination/EmptyRecombination.class model/recombination/BasicRecombination.class model/Population.class model/Individual.class model/terminator/EmptyTerminator.class model/terminator/BasicTerminator.class model/terminator/Terminator.class model/natural_selection/EmptyNaturalSelection.class model/natural_selection/NaturalSelectionInterface.class model/natural_selection/BasicNaturalSelection.class model/Genome.class model/sexual_selection/SexualSelectionInterface.class model/sexual_selection/BasicSexualSelection.class model/sexual_selection/EmptySexualSelection.class player50.class


#javac -cp .:classes.jar:contest.jar player50.java
#jar cmf MainClass.txt submission.jar @sources.txt

#cd model
#find -name "*.java" > ../sources.txt
#cd ..
#python compile.py
#javac -cp contest.jar @sources.txt
#javac -cp contest.jar player50.java
