package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    private double[] createRandom(int size) {

        //todo: create random genome
        throw new NotImplementedException();
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