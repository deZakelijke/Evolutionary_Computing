package model.recombination;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnePointRandomRecombination implements Recombination {

    Random rand = new Random();

    // todo schaalbaar maken
    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {


        //todo: couples uitpakken en los bewerken

        throw new NotImplementedException();
//        List<Individual> kiddos = new ArrayList<>();
//
//        int size = selectedForReproduction.size() - 2;
//
//        for (int i = 0; i < size; i+=2) {
//            Individual dad = selectedForReproduction.get(i);
//            Individual mom = selectedForReproduction.get(i + 1);
//
//            GenoType dadGenome = dad.getGenoType();
//            GenoType momGenome = mom.getGenoType();
//
//            GenoType kiddo_1 = new GenoType(dadGenome.onePointCrossover(1+rand.nextInt(dadGenome.getSize()-2), momGenome));
//            GenoType kiddo_2 = new GenoType(momGenome.onePointCrossover(1+rand.nextInt(momGenome.getSize()-2), dadGenome));
//
//            kiddos.add(new Individual(kiddo_1));
//            kiddos.add(new Individual(kiddo_2));
//        }
//
//        return kiddos;
    }
}
