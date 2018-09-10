package model.mutation;


import model.Individual;

import java.util.List;

public interface MutationInterface {


    List<Individual> doMutation(List<Individual> children);
}