import model.Population;
import model.mutation.GaussianMutation;
import model.mutation.EmptyMutation;
import model.mutation.MutationInterface;
import model.natural_selection.BasicNaturalSelection;
import model.natural_selection.EmptyNaturalSelection;
import model.natural_selection.NaturalSelectionInterface;
import model.recombination.BasicRecombination;
import model.recombination.EmptyRecombination;
import model.recombination.RecombinationInterface;
import model.sexual_selection.BasicSexualSelection;
import model.sexual_selection.EmptySexualSelection;
import model.sexual_selection.SexualSelectionInterface;
import model.stats.Statistics;
import model.terminator.BasicTerminator;
import model.terminator.EmptyTerminator;
import model.terminator.Terminator;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.*;

public class player50 implements ContestSubmission  {
	private Random rnd_;
	private ContestEvaluation evaluation_;
    private int evaluations_limit_;
    private static final int POPULATIONSIZE = 100;
    private static final int GENOMESIZE = 10;


	private static final String MUTATION ="MUTATION";
	private static final String NATURAL  ="NATURAL";
	private static final String SEXUAL = "SEXUAL";
	private static final String RECOMBINATION ="RECOMBINATION";
	private static final String TERMINATION ="TERMINATION";

    private Map<String, MutationInterface> mutationMap = new HashMap<>();
	private Map<String, NaturalSelectionInterface> naturalSelectionMap = new HashMap<>();
	private Map<String, SexualSelectionInterface> sexualSelectionMap = new HashMap<>();
	private Map<String, RecombinationInterface> recombinationMap = new HashMap<>();
	private Map<String, Terminator> terminatorMap = new HashMap<>();
	
	public player50()
	{
		rnd_ = new Random();
	}
	
	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	private Map getConfigF() {

		// BEPAAL HIER CONFIGURATIE
		// vanwege dependencies maar geen json inlezen maar gewoon een map
		Map<String, String> config  = new HashMap();

		config.put(TERMINATION, "basic");
		config.put(SEXUAL, "empty");
		config.put(NATURAL, "empty");
		config.put(RECOMBINATION, "empty");
		config.put(MUTATION, "empty");

		return config;
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{


		// Set evaluation problem used in the run
		evaluation_ = evaluation;
		
		// Get evaluation properties
		Properties props = evaluation.getProperties();
        // Get evaluation limit
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
        if (isMultimodal){
            // Do sth
        } else {
            // Do sth else
        }
    }
    
	public void run()
	{

		// config utils
		fillConfigMap();
		Map config = getConfigF();

		// print configuration
		System.out.println("Configuration:\n"+config.toString());

		// init configuration
		Terminator terminator = terminatorMap.get(config.get(TERMINATION));
		SexualSelectionInterface sexual = sexualSelectionMap.get(config.get(SEXUAL));
		NaturalSelectionInterface natural = naturalSelectionMap.get(config.get(NATURAL));
		RecombinationInterface recomb = recombinationMap.get(config.get(RECOMBINATION));
		MutationInterface mutation = mutationMap.get(config.get(MUTATION));

		// init logs
		Statistics stats = new Statistics();

        // init population
		Population population = new Population(POPULATIONSIZE, GENOMESIZE, evaluation_, terminator);

		// add first measurement
		stats.addStatistic(population.getStatistic());

		// initialize counter
		int generation = 0;

		// run generations
        while (true) {

        	// notify user of start
			System.out.println(String.format("Start generation: %d", generation));
			System.out.println(String.format("Number of exhausted evaluations: %d out of %d", terminator.getDoneEvaluations(), evaluations_limit_));

			// do one generation
        	population.runGeneration(evaluation_,mutation, recomb, natural, sexual, terminator);

        	// add measurement
			stats.addStatistic(population.getStatistic());

			// check termination condition
			if(terminator.isItDone(population)) {
				break;
			}

			// notify user of progress
			stats.printLastStatistics();
			System.out.println("Finished generation\n");
        }

        // final notification
		System.out.println("Final score:");
		stats.printLastStatistics();
		System.out.println("Done evolving");

		// export run
        stats.exportRun(String.format("/results/run_%s.csv", String.valueOf(new Date().getTime())));
	}



	private void fillConfigMap() {

		// mutations
		mutationMap.put("empty", new EmptyMutation());
		//mutationMap.put("basic", new BasicMutation());
		mutationMap.put("Gaussian", new GaussianMutation());

		// natural selections
		naturalSelectionMap.put("empty", new EmptyNaturalSelection());
		naturalSelectionMap.put("basic", new BasicNaturalSelection());

		// terminators
		terminatorMap.put("empty", new EmptyTerminator());
		terminatorMap.put("basic", new BasicTerminator(evaluations_limit_));

		// sexual selections
		sexualSelectionMap.put("empty", new EmptySexualSelection());
		sexualSelectionMap.put("basic", new BasicSexualSelection());

		// recombinations
		recombinationMap.put("empty", new EmptyRecombination());
		recombinationMap.put("basic", new BasicRecombination());

	}
}
