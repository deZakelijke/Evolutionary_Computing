#!/usr/bin/env bash

export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$(pwd)"

# Compile step:
javac -cp .:contest.jar player50.java
cd model
find -name "*.java" > ../sources.txt
cd ..
python compile.py

export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$(pwd)"

java -jar testrun.jar -submission=player50 -evaluation=BentCigarFunction -seed=1 2>./log/jeLogBitch.txt | tee ./log/jeRunBitch.txt