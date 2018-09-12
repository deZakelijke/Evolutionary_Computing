package model;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Genotype class
 * holds a gene-array and its size
 */
public class Genome {

    private Random rnd_ = new Random();

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

    /**
     * create random genome uniformly of predetemined size
     *
     * @param size
     * @return
     */
    private double[] createRandom(int size) {

        //todo: verdeling verbeteren zodat niet uniform maar goed verdeeld
        genome = new double[size];

        for (int i = 0; i < size; i++) {

            // todo: moet dit tussen 5 en -5 of -0.5 en 0.5? nu= [-5 en 5]
            genome[i] = (rnd_.nextDouble()-0.5)*10.0;
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

    /**
     * set allele to certain value
     *
     * @param allele
     * @param index
     */
    public void setAllele(double allele, int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        this.genome[index] = allele;
    }
}
