#!/usr/bin/env bash

parent_scheme=$1
recomb_scheme=$2
parents=$3

CIGAR="./log/bent_cigar.txt"
SCHAFFERS="./log/chaffers.txt"
KATSUURA="./log/katsura.txt"

declare -a ps=("uniform" "deterministic_fitness" "ranking_parent" "stochastic_parent" "stochastic_parent_no_repitition" "tournament_parent")
declare -a rc=("uniform" "simple_arithmetic" "whole_arithmetic" "center_of_mass")

for i in {2..10}
do
    parents=${i}
    for j in "${ps[@]}"
    do
       parent_scheme=$j
        for k in "${rc[@]}"
        do
        recomb_scheme=$k
            for m in {1..5}
            do
               echo "Run number: " $m
               echo "Computing with params:" $parent_scheme $recomb_scheme $parents

               echo "cigar"
               java -Dparent_scheme=${parent_scheme} -Drecomb_scheme=${recomb_scheme} -Dparents=${parents} -jar testrun.jar -submission=player50 -evaluation=BentCigarFunction -seed=1 1>> "$CIGAR" 2> ./log/error.txt

#               echo "schaffers"
#               java -Dparent_scheme=${parent_scheme} -Drecomb_scheme=${recomb_scheme} -Dparents=${parents} -jar testrun.jar -submission=player50 -evaluation=SchaffersEvaluation -seed=1 1>> "$SCHAFFERS" 2> /dev/null

#               echo "katsuura"
#               java -Dparent_scheme=${parent_scheme} -Drecomb_scheme=${recomb_scheme} -Dparents=${parents} -jar testrun.jar -submission=player50 -evaluation=KatsuuraEvaluation -seed=1 1>> "$KATSUURA" 2> ./log/error_kats.txt
           done
        done
    done
done
