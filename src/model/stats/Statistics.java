package model.stats;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    public Statistic getLast() {
        return run.get(run.size()-1);
    }

    public void printLastStatistics() {
        System.out.println(getLast().toString());
    }

}
