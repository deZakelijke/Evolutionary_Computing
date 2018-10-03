#!/bin/bash

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

CIGAR="./log/bent_cigar.txt"
SCHAFFERS="./log/chaffers.txt"
KATSUURA="./log/katsura.txt"

rm ${CIGAR} ${SCHAFFERS} ${KATSUURA}

