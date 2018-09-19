import model.Population;
import model.mutation.GaussianMutation;
import model.mutation.EmptyMutation;
import model.mutation.Mutation;
import model.mutation.UniformMutation;
import model.natural_selection.*;
import model.recombination.EmptyRecombination;
import model.recombination.OnePointRandomRecombination;
import model.recombination.Recombination;
import model.sexual_selection.UniformParentSelection;
import model.sexual_selection.EmptyParentSelection;
import model.sexual_selection.ParentSelection;
import model.stats.Statistics;
import model.terminator.*;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.*;

public class player50 implements ContestSubmission  {
	private Random rnd_;
	private ContestEvaluation evaluation_;
    private int evaluations_limit_;
    private int POPULATIONSIZE;
    private int GENOMESIZE;
    private double MUTATIONRATE;
    private double REPRODUCTIONRATE;
    private int NUMBER_OF_PARENTS;
    private int NUMBER_OF_COUPLES;
    private double SCORE_TERMINATION;
    private int GENERATION_TERMINATION;


	private static final String MUTATION ="MUTATION";
	private static final String NATURAL  ="NATURAL";
	private static final String SEXUAL = "SEXUAL";
	private static final String RECOMBINATION ="RECOMBINATION";
	private static final String TERMINATION ="TERMINATION";

	private boolean LOG;

    private Map<String, Mutation> mutationMap = new HashMap<>();
	private Map<String, SurvivalSelection> naturalSelectionMap = new HashMap<>();
	private Map<String, ParentSelection> sexualSelectionMap = new HashMap<>();
	private Map<String, Recombination> recombinationMap = new HashMap<>();
	private Map<String, TerminationContext> terminatorMap = new HashMap<>();
	
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

		config.put(	TERMINATION, 		"evaluation_based");
		config.put(	SEXUAL,				"basic");
		config.put(	NATURAL, 			"fixed_population_worst");
		config.put(	RECOMBINATION, 		"one_point_random");
		config.put(	MUTATION, 			"gaussian");

		// determines wether run will be logged
		LOG = false;

		//other run variables
		POPULATIONSIZE = 100;
		GENOMESIZE = 10;
		MUTATIONRATE = 1.0;
		REPRODUCTIONRATE = 0.3;
		NUMBER_OF_PARENTS = 2;
		NUMBER_OF_COUPLES = (int) Math.round((POPULATIONSIZE*REPRODUCTIONRATE)/NUMBER_OF_PARENTS);
		SCORE_TERMINATION = 9.5;
		GENERATION_TERMINATION = 100;


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
		mutationMap.put("empty", new EmptyMutation(MUTATIONRATE));
		mutationMap.put("uniform", new UniformMutation(MUTATIONRATE));
		mutationMap.put("gaussian", new GaussianMutation(MUTATIONRATE));

		// natural selections
		naturalSelectionMap.put("empty", new EmptySurvivalSelection(POPULATIONSIZE));
		naturalSelectionMap.put("fixed_population_random", new FixedPopulationRandomSurvivalSelection(POPULATIONSIZE));
		naturalSelectionMap.put("fixed_population_worst", new FixedPopulationKillWorstOffSurvivalSelection(POPULATIONSIZE));

		// terminators
		terminatorMap.put("indefinite", new EmptyTermination());
		terminatorMap.put("evaluation_based", new EvaluationsExhaustedTermination(evaluations_limit_));
		terminatorMap.put("generation_based", new FixedGenerationsTermination(GENERATION_TERMINATION));
		terminatorMap.put("score_based", new FixedScoreTermination(SCORE_TERMINATION));

		// sexual selections
		sexualSelectionMap.put("empty", new EmptyParentSelection(NUMBER_OF_PARENTS, NUMBER_OF_COUPLES));
		sexualSelectionMap.put("basic", new UniformParentSelection(NUMBER_OF_PARENTS, NUMBER_OF_COUPLES));

		// recombinations
		recombinationMap.put("empty", new EmptyRecombination());
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
			Map config = getRunConfiguration();
			fillConfigurationMap();

			// print configuration
			System.out.println("Configuration:\n" + config.toString());

			// init configuration
			TerminationContext terminator = terminatorMap.get(config.get(TERMINATION));
			ParentSelection sexual = sexualSelectionMap.get(config.get(SEXUAL));
			SurvivalSelection natural = naturalSelectionMap.get(config.get(NATURAL));
			Recombination recomb = recombinationMap.get(config.get(RECOMBINATION));
			Mutation mutation = mutationMap.get(config.get(MUTATION));

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
