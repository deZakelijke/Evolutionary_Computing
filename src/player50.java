import model.Population;
import model.mutation.GaussianMutation;
import model.mutation.EmptyMutation;
import model.mutation.Mutation;
import model.mutation.UniformMutation;
import model.survival_selection.*;
import model.recombination.EmptyRecombination;
import model.recombination.OnePointRandomRecombination;
import model.recombination.Recombination;
import model.parent_selection.UniformParentSelection;
import model.parent_selection.EmptyParentSelection;
import model.parent_selection.ParentSelection;
import model.stats.Statistics;
import model.termination.*;
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
    private int RUNS_PER_CONFIG;
    private boolean DEBUG;


	private static final String MUTATION ="MUTATION";
	private static final String SURVIVAL ="SURVIVAL";
	private static final String PARENT = "PARENT";
	private static final String RECOMBINATION ="RECOMBINATION";
	private static final String TERMINATION ="TERMINATION";

	private boolean LOG;

    private Map<String, Mutation> mutationMap = new HashMap<>();
	private Map<String, SurvivalSelection> survivalSelectionMap = new HashMap<>();
	private Map<String, ParentSelection> parentSelectionMap = new HashMap<>();
	private Map<String, Recombination> recombinationMap = new HashMap<>();
	private Map<String, TerminationContext> terminationContextMap = new HashMap<>();
	
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
	 * Determines the configuration of EA that is to be used at next run
	 * @return configuration hashmap
	 */
	private Map getRunConfiguration() {


		/**##########################################*/
		/**  !!! yo guys, BEPAAL HIER CONFIGURATIE!!!! */
		/**##########################################*/


		// note: vanwege dependencies maar geen json inlezen maar gewoon een map
		Map<String, String> config  = new HashMap();

		config.put(	TERMINATION, 		"generation_based");
		config.put(	SURVIVAL, 			"fixed_population_worst");
		config.put(	MUTATION, 			"gaussian");

		// determines wether run will be logged
		LOG = false;

		//other run variables
		POPULATIONSIZE = 100;
		GENOMESIZE = 10;
		MUTATIONRATE = 0.8;
		REPRODUCTIONRATE = 0.3;
		NUMBER_OF_PARENTS = 2;
		NUMBER_OF_COUPLES = (int) Math.round((POPULATIONSIZE*REPRODUCTIONRATE)/NUMBER_OF_PARENTS);
		SCORE_TERMINATION = 9.5;
		GENERATION_TERMINATION = 100;
		RUNS_PER_CONFIG = 2;
		DEBUG = true;


		return config;
	}

	/**
	 * Holds information on possible configurations
	 */
	private void fillConfigurationMap() {

		/**##########################################*/
		/** yo guys, voeg hier ALLE nieuwe IMPLEMENTATIES toe !! */ //temp-comment
		/**##########################################*/

		// mutations
		mutationMap.put("empty", new EmptyMutation(MUTATIONRATE));
		mutationMap.put("uniform", new UniformMutation(MUTATIONRATE));
		mutationMap.put("gaussian", new GaussianMutation(MUTATIONRATE));

		// survival selections
		survivalSelectionMap.put("empty", new EmptySurvivalSelection(POPULATIONSIZE));
		survivalSelectionMap.put("fixed_population_random", new FixedPopulationRandomSurvivalSelection(POPULATIONSIZE));
		survivalSelectionMap.put("fixed_population_worst", new FixedPopulationKillWorstOffSurvivalSelection(POPULATIONSIZE));

		// terminators
		terminationContextMap.put("indefinite", new EmptyTermination());
		terminationContextMap.put("evaluation_based", new EvaluationsExhaustedTermination(evaluations_limit_));
		terminationContextMap.put("generation_based", new FixedGenerationsTermination(GENERATION_TERMINATION));
		terminationContextMap.put("score_based", new FixedScoreTermination(SCORE_TERMINATION));

		// parent selections
		parentSelectionMap.put("empty", new EmptyParentSelection(NUMBER_OF_PARENTS, NUMBER_OF_COUPLES));
		parentSelectionMap.put("uniform", new UniformParentSelection(NUMBER_OF_PARENTS, NUMBER_OF_COUPLES));

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
	 * runs all experiments EA
	 */
	public void run()  {

		long startTime = new Date().getTime();

		/** #######################################################
		 *  DEFINE TESTS
		 *  ############################################# */
		List<String> parentSchemes = Arrays.asList("uniform");
		List<String> recombinationSchemes = Arrays.asList("empty");
		List<Integer> parents = Arrays.asList(2);
		//  ##################################################   er staat geen recombination op git atm, jawel maar lokaal


        getRunConfiguration();

		// loop through nr of parents
		for (Integer parent_nr : parents) {

			System.out.println(String.format("Starting all experiments for %d parents", parent_nr));

			// loop through nr of recombinationschemes
			for (String recombinationScheme : recombinationSchemes) {

				// loop through parent schemes
				for (String parentScheme : parentSchemes) {

					System.out.println(String.format("Starting the %d experiments for %d parents with %s recombination and %s parentscheme", RUNS_PER_CONFIG, parent_nr, recombinationScheme, parentScheme));

					// do a number of experiments for statistical significance
					for (int i = 0; i < RUNS_PER_CONFIG; i++) {
						System.out.println(String.format("---Run {\"parentScheme\": %s, \"recombinationScheme\": %s, \"nr_of_parents\": %d }", parentScheme, recombinationScheme, parent_nr));
						System.out.println(String.format("Sarting run %d out of %d", i, RUNS_PER_CONFIG));

						String runName = "experiment="+ String.valueOf(startTime) + "_n=" +String.valueOf(i);

						do_EA_Run(runName, parentScheme,recombinationScheme, parent_nr);

						System.out.println("---Finished run---");
					}
				}
			}
		}

		// notify user of termination experiments
		System.out.println("DONE");

	}

	/**
	 * runs one EA experiment
	 */
	private void do_EA_Run(String name, String parentScheme, String recombinationScheme, int nr_parents) {

		// init logs
		Statistics statistics = new Statistics();
		String runName = name+"_"+parentScheme+"_"+recombinationScheme+"_"+String.valueOf(nr_parents);

		try {
			// config utils
			Map config = getRunConfiguration();
			config.put(PARENT,				parentScheme);
			config.put(RECOMBINATION, 		recombinationScheme);
			NUMBER_OF_PARENTS = nr_parents;

			fillConfigurationMap();

			// print configuration
			System.out.println("Starting: " + runName);
			System.out.println("Configuration:\n" + config.toString());

			// init configuration
			TerminationContext terminationContext = terminationContextMap.get(config.get(TERMINATION));
			ParentSelection parentSelection = parentSelectionMap.get(parentScheme);
			SurvivalSelection survivalSelection = survivalSelectionMap.get(config.get(SURVIVAL));
			Recombination recombination = recombinationMap.get(recombinationScheme);
			Mutation mutation = mutationMap.get(config.get(MUTATION));

			// possible activate debugmode (alls print statements are active)
			terminationContext.setDebug(DEBUG);

			// init population
			Population population = new Population(POPULATIONSIZE, GENOMESIZE, evaluation_, terminationContext);

			// add first measurement
			statistics.addStatistic(population.getStatistic());

			// run generations
			while (true) {

				// notify user of start
				terminationContext.debugLine(String.format("Start generation: %d", terminationContext.getGenerationNumber()));
				terminationContext.debugLine(String.format("Number of exhausted evaluations: %d out of %d", terminationContext.getDoneEvaluations(), evaluations_limit_));

				// do one generation
				population.runGeneration(evaluation_, mutation, recombination, survivalSelection, parentSelection, terminationContext);

				// add measurement
				statistics.addStatistic(population.getStatistic());

				// check termination condition
				if (terminationContext.isItDone(population)) {
					break;
				}

				// notify user of progress
				statistics.printLastStatistics();
				terminationContext.debugLine(statistics.getLast().toString());
				terminationContext.debugLine("Finished generation\n");

				//++ generation number
				terminationContext.addGeneration();
			}

			// final notification
			terminationContext.debugLine("Final score:");
			statistics.printLastStatistics();
			terminationContext.debugLine("Done evolving");

			// export run
			if (LOG) {
				statistics.exportRun(String.format("/results/run_%s.csv", runName));
			}

		} catch (Exception e) {

			// export run
			if (LOG) {
				statistics.exportRun(String.format("/results/error_run_%s.csv", runName));
			}
			e.printStackTrace();
			throw e;

		}

	}

}
