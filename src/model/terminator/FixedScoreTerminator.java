package model.terminator;

import model.Population;

/**
 * runs untill fixed number of generations is reached
 */
public class FixedScoreTerminator extends EmptyTerminator implements Terminator {

    private double score = 0;

    public FixedScoreTerminator(double desiredScore) {
        setScore(desiredScore);
    }

    @Override
    public boolean isItDone(Population population) {
        return score<=population.getHighestFitness();
    }


    private void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "FixedScoreTerminator{" +
                "score=" + score +
                '}';
    }
}
