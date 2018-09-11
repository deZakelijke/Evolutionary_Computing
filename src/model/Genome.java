package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Random;

public class Genome {

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

    private void createRandom(int size) {
        // TODO make adjustable range
        double min = -5.0;
        double max = 5.0;
        Random r = new Random();
        this.genome = new double[size];

        for (int i = 0; i < size; i++) {
            this.genome[i] = min + (max - min) * r.nextDouble();
        }
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
