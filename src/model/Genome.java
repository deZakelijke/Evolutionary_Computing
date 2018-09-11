package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genome {

    private Random rnd_;

    private double[] genome;
    private int size = 0;


    public Genome(double[] genome) {
        setGenome(genome);
        setSize(genome.length);
    }

    public Genome(int size) {
        setGenome(createRandom(size));
        setSize(size);
    }

    private double[] createRandom(int size) {

        genome = new double[size];

        int i = 0;
        while (i<size) {

            genome[i] = (rnd_.nextDouble()-0.5)*1.0;

            i++;
        }

        return genome;

    }

    public double[] getGenome() {
        return genome;
    }

    public void setGenome(double[] genome) {
        this.genome = genome;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}