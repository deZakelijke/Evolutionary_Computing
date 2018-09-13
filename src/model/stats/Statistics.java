package model.stats;

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

    /**
     * Exports a run to a csv file for analysis and plotting
     * @param filename
     */
    public void exportRun(String filename) {

            List<String> data = accumelateData();

            try {


                String path = System.getProperty("user.dir") + "/log/"+filename;

                PrintWriter pw = new PrintWriter(new File(path));

                StringBuilder sb = new StringBuilder();

                for (String dataLine : data) {
                    sb.append(dataLine);
                    sb.append("\n");
                }

                pw.write(sb.toString());
                pw.close();

                System.out.println("Saved: "+ path);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                for (String datalLine : data) {
                    System.out.println(datalLine);
                }

                System.out.println("\n\nSaving logs failed, printed to terminal instead in order to not lose anything");
            }

    }

    /**
     * retrieves data from run list
     * @return
     */
    private List<String> accumelateData() {
        //todo: get real data

        List<String> data = new ArrayList<>();
        data.add("a,b,c");
        data.add("d,e,f");
        return data;

    }

    public void printLastStatistics() {
        System.out.println(run.get(run.size()-1).toString());
    }

}
