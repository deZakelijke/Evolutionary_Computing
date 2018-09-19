package model;

/**
 * Fenotype class,
 * Hold an individuals genotype, fitness and age
 */
public class Individual {

    private GenoType genoType;
    private double fitness;
    private int age;

    public Individual(GenoType genoType) {
        setGenoType(genoType);
    }

    public GenoType getGenoType() {
        return genoType;
    }

    public void setGenoType(GenoType genoType) {
        this.genoType = genoType;
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