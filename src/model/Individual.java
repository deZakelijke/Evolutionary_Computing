package model;

/**
 * Fenotype class,
 * Hold an individuals genotype, fitness and age
 */
public class Individual {

    private Genome genome;
    private double fitness;
    private int age;

    public Individual(Genome genome) {
        setGenome(genome);
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void ageOneYear() {
        age++;
    }
}