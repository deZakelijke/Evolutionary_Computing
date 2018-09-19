package model.mutation;

import model.Individual;
import java.util.List;

public class EmptyMutation implements Mutation {

    private double mutationRate;

    public EmptyMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public void doMutation(List<Individual> children) {
        return;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
}
