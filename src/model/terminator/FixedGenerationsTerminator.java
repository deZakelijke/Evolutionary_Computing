package model.terminator;

import model.Population;

/**
 * runs untill fixed number of generations is reached
 */
public class FixedGenerationsTerminator extends EmptyTerminator implements Terminator {

    private int maxGenerations = 0;

    public FixedGenerationsTerminator(int numberOfGenerations) {
        setMaxGenerations(numberOfGenerations);
    }

    @Override
    public boolean isItDone(Population population) {
        return maxGenerations<=getGenerations();
    }


    private void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    @Override
    public String toString() {
        return "FixedGenerationsTerminator{" +
                "maxGenerations=" + maxGenerations +
                ", doneEvaluations=" + getDoneEvaluations() +
                ", generations=" + getGenerations() +
                '}';
    }
}
