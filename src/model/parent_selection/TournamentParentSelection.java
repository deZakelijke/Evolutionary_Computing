package model.parent_selection;

import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Comparator;

public class TournamentParentSelection extends EmptyParentSelection implements ParentSelection {

    private int tournament_size;

    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public TournamentParentSelection(int nr_parents, int nr_couples, int tournament_size) {
        super(nr_parents, nr_couples);
        if (nr_parents > tournament_size) {
            throw new IndexOutOfBoundsException();
        }
        this.tournament_size = tournament_size;
    }
    
    public int getTournament_size() {
        return this.tournament_size;
    }

    public void setTournament_size(int tournament_size) {
        this.tournament_size = tournament_size;
    }

    private Individual[] run_tournament(List<Individual> tournament_players) {
        Individual[] new_couple = new Individual[this.nr_parents]; 
        tournament_players.sort(Comparator.comparing(Individual::getFitness));

        for (int i = 0; i < this.nr_parents; i++) {
            new_couple[i] = tournament_players.get(i);
        }
        return new_couple;
    }

    /**
     * Select parents by tournament
     */
    @Override
    public List<Individual[]> select(List<Individual> populationList) {

        int length = populationList.size();
        Random r = new Random();
        if (length < getNr_parents()) return null;
        List<Individual[]> parents = new ArrayList<>();

        for (int i = 0; i < this.nr_couples; i++) {
            Collections.shuffle(populationList);
            parents.add(run_tournament(populationList.stream().limit(
                            this.tournament_size).collect(Collectors.toList())));
        }
        return parents;
    }

}
