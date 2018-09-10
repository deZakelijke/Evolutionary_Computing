package model;

public class Genome {

    private String genome = "";
    private int size = 0;


    public Genome(String genome) {
        setGenome(genome);
        setSize(genome.length());
    }

    public Genome(int size) {
        setGenome(createRandom(size));
        setSize(size);
    }

    private String createRandom(int size) {

        //todo: create random genome
        return "";
    }

    public String getGenome() {
        return genome;
    }

    public void setGenome(String genome) {
        this.genome = genome;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}