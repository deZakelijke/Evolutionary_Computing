import model.Population;
import model.mutation.GaussianMutation;
import model.mutation.EmptyMutation;
import model.mutation.MutationInterface;
import model.mutation.UniformMutation;
import model.natural_selection.*;
import model.recombination.BasicRecombination;
import model.recombination.EmptyRecombination;
import model.recombination.OnePointRandomRecombination;
import model.recombination.RecombinationInterface;
import model.sexual_selection.BasicSexualSelection;
import model.sexual_selection.EmptySexualSelection;
import model.sexual_selection.SexualSelectionInterface;
import model.stats.Statistics;
import model.terminator.*;
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

	private boolean LOG;

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

	/**
	 * Determines tre configuration of EA that is to be used at next run
	 * @return configuration hashmap
	 */
	private Map getRunConfiguration() {


		/**##########################################*/
		/**  !!! yo guys, BEPAAL HIER CONFIGURATIE!!!! */
		/**##########################################*/


		// note: vanwege dependencies maar geen json inlezen maar gewoon een map
		Map<String, String> config  = new HashMap();

		config.put(	TERMINATION, 		"generation_based");
		config.put(	SEXUAL,				"basic");
		config.put(	NATURAL, 			"fixed_population_worst");
		config.put(	RECOMBINATION, 		"one_point_random");
		config.put(	MUTATION, 			"uniform");

		// determines wether run will be logged
		LOG = true;

		return config;
	}

	/**
	 * Holds information on
	 */
	private void fillConfigurationMap() {

		/**##########################################*/
		/** yo guys, voeg hier ALLE nieuwe IMPLEMENTATIES toe !! */ //temp-comment
		/**##########################################*/

		// mutations
		mutationMap.put("empty", new EmptyMutation());
		mutationMap.put("uniform", new UniformMutation());
		mutationMap.put("gaussian", new GaussianMutation());

		// natural selections
		naturalSelectionMap.put("empty", new EmptyNaturalSelection());
		naturalSelectionMap.put("basic", new BasicNaturalSelection());
		naturalSelectionMap.put("fixed_population_random", new FixedPopulationRandomNaturalSelection(POPULATIONSIZE));
		naturalSelectionMap.put("fixed_population_worst", new FixedPopulationKillWorstOffNaturalSelection(POPULATIONSIZE));

		// terminators
		terminatorMap.put("indefinite", new EmptyTerminator());
		terminatorMap.put("evaluation_based", new EvaluationsExhaustedTerminator(evaluations_limit_));
		terminatorMap.put("generation_based", new FixedGenerationsTerminator(100));
		terminatorMap.put("score_based", new FixedScoreTerminator(9.5));

		// sexual selections
		sexualSelectionMap.put("empty", new EmptySexualSelection());
		sexualSelectionMap.put("basic", new BasicSexualSelection(20, 1));

		// recombinations
		recombinationMap.put("empty", new EmptyRecombination());
		recombinationMap.put("basic", new BasicRecombination());
		recombinationMap.put("one_point_random", new OnePointRandomRecombination());

	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		//todo: deze door de VU gegeven functie begrijpen

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

	/**
	 * runs EA
	 */
	public void run()
	{

		// init logs
		Statistics stats = new Statistics();
		long run_nr = new Date().getTime();

		try {
			// config utils
			fillConfigurationMap();
			Map config = getRunConfiguration();

			// print configuration
			System.out.println("Configuration:\n" + config.toString());

			// init configuration
			Terminator terminator = terminatorMap.get(config.get(TERMINATION));
			SexualSelectionInterface sexual = sexualSelectionMap.get(config.get(SEXUAL));
			NaturalSelectionInterface natural = naturalSelectionMap.get(config.get(NATURAL));
			RecombinationInterface recomb = recombinationMap.get(config.get(RECOMBINATION));
			MutationInterface mutation = mutationMap.get(config.get(MUTATION));

			// init population
			Population population = new Population(POPULATIONSIZE, GENOMESIZE, evaluation_, terminator);

			// add first measurement
			stats.addStatistic(population.getStatistic());

			// run generations
			while (true) {

				// notify user of start
				System.out.println(String.format("Start generation: %d", terminator.getGenerationNumber()));
				System.out.println(String.format("Number of exhausted evaluations: %d out of %d", terminator.getDoneEvaluations(), evaluations_limit_));

				// do one generation
				population.runGeneration(evaluation_, mutation, recomb, natural, sexual, terminator);

				// add measurement
				stats.addStatistic(population.getStatistic());

				// check termination condition
				if (terminator.isItDone(population)) {
					break;
				}

				// notify user of progress
				stats.printLastStatistics();
				System.out.println("Finished generation\n");

				//++ generation number
				terminator.addGeneration();
			}

			// final notification
			System.out.println("Final score:");
			stats.printLastStatistics();
			System.out.println("Done evolving");

			// export run
			if (LOG) {
				stats.exportRun(String.format("/results/run_%s.csv", String.valueOf(run_nr)));
			}

		} catch (Exception e) {

			// export run
			if (LOG) {
				stats.exportRun(String.format("/results/error_run_%s.csv", String.valueOf(run_nr)));
			}
			e.printStackTrace();
			throw e;

		}


	}

}
