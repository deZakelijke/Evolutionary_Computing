package model.recombination;


import model.Individual;

import java.util.List;

public interface RecombinationInterface {


    List<Individual> reproduce(List<Individual> selectedForReproduction);
}