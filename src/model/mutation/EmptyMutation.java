package model.mutation;

import model.Individual;
import java.util.List;

public class EmptyMutation implements Mutation {

    private double mutationRate;

    public EmptyMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public List<Individual> doMutation(List<Individual> children) {
        return children;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
}
