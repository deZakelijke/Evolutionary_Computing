package model;

// = fenotype
public class Individual {

    private Genome genome;
    private int fitness;
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

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}