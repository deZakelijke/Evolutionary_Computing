#!/bin/bash

CIGAR="./log/bent_cigar.txt"
SCHAFFERS="./log/chaffers.txt"
KATSUURA="./log/katsura.txt"

export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$(pwd)"

# Compile step:
javac -cp .:contest.jar player50.java
cd model
find -name "*.java" > ../sources.txt
cd ..
python compile.py

export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$(pwd)"

#touch $CIGAR
#touch $SCHAFFERS
mkdir ./log/

java -jar testrun.jar -submission=player50 -evaluation=BentCigarFunction -seed=1 1> "$CIGAR" | tee "$CIGAR"
java -jar testrun.jar -submission=player50 -evaluation=SchaffersEvaluation -seed=1 1> "$SCHAFFERS" | tee "$SCHAFFERS"
java -jar testrun.jar -submission=player50 -evaluation=KatsuuraEvaluation -seed=1 1> "$KATSUURA" | tee "$KATSUURA"
