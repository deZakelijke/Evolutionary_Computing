package model;

import java.util.Random;

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
        for (int i = 0; i < size; i++) {
            genome[i] = (rnd_.nextDouble()-0.5)*1.0;

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

    // Might not be necessary
    public void setAllele(double allele, int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        this.genome[index] = allele;
    }
}
