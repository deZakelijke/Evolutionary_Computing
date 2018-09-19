package model.termination;

import model.Population;

/**
 * runs untill fixed number of generations is reached
 */
public class FixedScoreTermination extends EmptyTermination implements TerminationContext {

    private double score = 0;

    public FixedScoreTermination(double desiredScore) {
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
        return "FixedScoreTermination{" +
                "score=" + score +
                '}';
    }
}
