package model.stats;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that holds the logs of entire run
 *
 */
public class Statistics {

    private List<Statistic> run = new ArrayList<>();

    public List<Statistic> getRun() {
        return run;
    }

    public void setRun(List<Statistic> run) {
        this.run = run;
    }

    public void addStatistic(Statistic stat) {
        run.add(stat);
    }

    public void exportRun(String filename) {
        System.getProperty("user.dir");
    }

    public void printLastStatistics() {
        System.out.println(run.get(run.size()-1).toString());
    }

}
