#!/usr/bin/env bash

javac -cp .:contest.jar player50.java && jar cmf MainClass.txt submission.jar player50.class model/Population.class && java -jar testrun.jar -submission=player50 -evaluation=SphereEvaluation -seed=1

#javac -cp .:classes.jar:contest.jar player50.java
#jar cmf MainClass.txt submission.jar @sources.txt

#cd model
#find -name "*.java" > ../sources.txt
#cd ..
#python compile.py
#javac -cp contest.jar @sources.txt
#javac -cp contest.jar player50.java
